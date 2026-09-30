package xyz.omniplay.dsd

import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object DsdHeaderParser {

    /**
     * Inspects the input stream to parse DSF or DFF header information.
     */
    fun parse(stream: InputStream): DsdHeader? {
        val magic = ByteArray(4)
        if (!readFully(stream, magic)) return null

        val magicStr = String(magic, Charsets.US_ASCII)
        return when (magicStr) {
            "DSD " -> parseDsf(stream)
            "FRM8" -> parseDff(stream)
            else -> null
        }
    }

    private fun parseDsf(stream: InputStream): DsdHeader? {
        // We already read 4 bytes ("DSD ")
        // Remaining header chunk: 24 bytes (chunkSize: 8, fileSize: 8, metadataOffset: 8)
        val dsdRest = ByteArray(24)
        if (!readFully(stream, dsdRest)) return null
        val dsdBuf = ByteBuffer.wrap(dsdRest).order(ByteOrder.LITTLE_ENDIAN)
        val dsdChunkSize = dsdBuf.long
        val fileSize = dsdBuf.long
        val metadataOffset = dsdBuf.long

        // Next chunk: "fmt "
        val fmtHdr = ByteArray(12)
        if (!readFully(stream, fmtHdr)) return null
        val fmtHdrBuf = ByteBuffer.wrap(fmtHdr).order(ByteOrder.LITTLE_ENDIAN)
        val fmtMagic = String(fmtHdr, 0, 4, Charsets.US_ASCII)
        if (fmtMagic != "fmt ") return null
        val fmtChunkSize = fmtHdrBuf.getLong(4)

        val fmtPayloadSize = (fmtChunkSize - 12).toInt().coerceAtLeast(40)
        val fmtPayload = ByteArray(fmtPayloadSize)
        if (!readFully(stream, fmtPayload)) return null
        val fmtBuf = ByteBuffer.wrap(fmtPayload).order(ByteOrder.LITTLE_ENDIAN)

        val formatVersion = fmtBuf.int
        val formatId = fmtBuf.int // 0 = DSD raw
        val channelType = fmtBuf.int
        val channelCount = fmtBuf.int.coerceAtLeast(1)
        val sampleRate = fmtBuf.int.coerceAtLeast(2822400)
        val bitsPerSample = fmtBuf.int // 1
        val sampleCount = fmtBuf.long
        val blockSize = fmtBuf.int.coerceAtLeast(4096)

        // Next chunk: "data"
        val dataHdr = ByteArray(12)
        if (!readFully(stream, dataHdr)) return null
        val dataMagic = String(dataHdr, 0, 4, Charsets.US_ASCII)
        if (dataMagic != "data") return null
        val dataBuf = ByteBuffer.wrap(dataHdr).order(ByteOrder.LITTLE_ENDIAN)
        val dataChunkSize = dataBuf.getLong(4)

        val dataOffset = 28L + fmtChunkSize + 12L
        val dataLength = dataChunkSize - 12L
        val durationMs = if (sampleRate > 0) (sampleCount * 1000L) / sampleRate else 0L

        return DsdHeader(
            type = DsdType.DSF,
            formatName = DsdHeader.getFormatName(sampleRate),
            channelCount = channelCount,
            sampleRate = sampleRate,
            sampleCount = sampleCount,
            durationMs = durationMs,
            dataOffset = dataOffset,
            dataLength = dataLength,
            blockSizePerChannel = blockSize
        )
    }

    private fun parseDff(stream: InputStream): DsdHeader? {
        // We already read 4 bytes ("FRM8")
        val frm8Rest = ByteArray(12)
        if (!readFully(stream, frm8Rest)) return null
        val frm8Buf = ByteBuffer.wrap(frm8Rest).order(ByteOrder.BIG_ENDIAN)
        val fileSize = frm8Buf.long
        val formType = String(frm8Rest, 8, 4, Charsets.US_ASCII)
        if (formType != "DSD ") return null

        var sampleRate = 2822400
        var channelCount = 2
        var dataOffset = 0L
        var dataLength = 0L
        var currentOffset = 16L

        // Read chunks (4-byte ID, 8-byte size BE)
        val chunkHdr = ByteArray(12)
        while (currentOffset < fileSize && currentOffset < 1_000_000L) { // scan up to first 1MB of headers
            if (!readFully(stream, chunkHdr)) break
            currentOffset += 12L
            val chunkId = String(chunkHdr, 0, 4, Charsets.US_ASCII)
            val chunkSize = ByteBuffer.wrap(chunkHdr, 4, 8).order(ByteOrder.BIG_ENDIAN).long

            if (chunkId == "PROP") {
                val propType = ByteArray(4)
                if (!readFully(stream, propType)) break
                currentOffset += 4L
                var propRemaining = chunkSize - 4L

                while (propRemaining >= 12L) {
                    val subHdr = ByteArray(12)
                    if (!readFully(stream, subHdr)) break
                    currentOffset += 12L
                    propRemaining -= 12L
                    val subId = String(subHdr, 0, 4, Charsets.US_ASCII)
                    val subSize = ByteBuffer.wrap(subHdr, 4, 8).order(ByteOrder.BIG_ENDIAN).long

                    when (subId) {
                        "FS  " -> {
                            val fsBytes = ByteArray(4)
                            if (readFully(stream, fsBytes)) {
                                sampleRate = ByteBuffer.wrap(fsBytes).order(ByteOrder.BIG_ENDIAN).int
                                currentOffset += 4L
                                propRemaining -= 4L
                                val skip = (subSize - 4L).coerceAtLeast(0L)
                                skipFully(stream, skip)
                                currentOffset += skip
                                propRemaining -= skip
                            }
                        }
                        "CHNL" -> {
                            val chBytes = ByteArray(2)
                            if (readFully(stream, chBytes)) {
                                channelCount = (ByteBuffer.wrap(chBytes).order(ByteOrder.BIG_ENDIAN).short.toInt() and 0xFFFF)
                                currentOffset += 2L
                                propRemaining -= 2L
                                val skip = (subSize - 2L).coerceAtLeast(0L)
                                skipFully(stream, skip)
                                currentOffset += skip
                                propRemaining -= skip
                            }
                        }
                        else -> {
                            skipFully(stream, subSize)
                            currentOffset += subSize
                            propRemaining -= subSize
                        }
                    }
                }
            } else if (chunkId == "DSD ") {
                dataOffset = currentOffset
                dataLength = chunkSize
                break
            } else {
                skipFully(stream, chunkSize)
                currentOffset += chunkSize
            }
        }

        if (dataLength <= 0L) {
            dataLength = (fileSize - currentOffset).coerceAtLeast(0L)
        }
        val sampleCount = if (channelCount > 0) (dataLength * 8L) / channelCount else 0L
        val durationMs = if (sampleRate > 0) (sampleCount * 1000L) / sampleRate else 0L

        return DsdHeader(
            type = DsdType.DFF,
            formatName = DsdHeader.getFormatName(sampleRate),
            channelCount = channelCount,
            sampleRate = sampleRate,
            sampleCount = sampleCount,
            durationMs = durationMs,
            dataOffset = dataOffset,
            dataLength = dataLength
        )
    }

    private fun readFully(stream: InputStream, buffer: ByteArray): Boolean {
        var total = 0
        while (total < buffer.size) {
            val r = stream.read(buffer, total, buffer.size - total)
            if (r < 0) return false
            total += r
        }
        return true
    }

    private fun skipFully(stream: InputStream, n: Long) {
        var remaining = n
        val buf = ByteArray(4096)
        while (remaining > 0L) {
            val toSkip = remaining.coerceAtMost(buf.size.toLong()).toInt()
            val r = stream.read(buf, 0, toSkip)
            if (r < 0) break
            remaining -= r
        }
    }
}
