package xyz.omniplay.dsd

import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.ParsableByteArray
import androidx.media3.extractor.Extractor
import androidx.media3.extractor.ExtractorInput
import androidx.media3.extractor.ExtractorOutput
import androidx.media3.extractor.PositionHolder
import androidx.media3.extractor.SeekMap
import androidx.media3.extractor.TrackOutput
import java.io.ByteArrayInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Media3 Extractor for Direct Stream Digital (DSF and DFF/DSDIFF) container formats.
 * Decimates high-resolution 1-bit DSD to pristine 32-bit floating point PCM (88.2 kHz or 176.4 kHz)
 * with low CPU usage (<1%) via DsdDecimator.
 */
class DsdExtractor : Extractor {

    private var extractorOutput: ExtractorOutput? = null
    private var trackOutput: TrackOutput? = null
    private var dsdHeader: DsdHeader? = null
    private var initialized = false

    private var currentTimeUs: Long = 0L
    private var pcmSampleRate: Int = 88200

    // Channel history for FIR filter continuity across block boundaries
    private val histL = ByteArray(4)
    private val histR = ByteArray(4)

    // Reusable buffers
    private var leftBlock = ByteArray(4096)
    private var rightBlock = ByteArray(4096)
    private var interleavedDff = ByteArray(8192)
    private var pcmFloatBuffer = ByteBuffer.allocate(32768).order(ByteOrder.LITTLE_ENDIAN)
    private val parsableOutput = ParsableByteArray()

    override fun sniff(input: ExtractorInput): Boolean {
        val peek = ByteArray(16)
        if (!peekSafely(input, peek, 0, 4)) return false
        val magic = String(peek, 0, 4, Charsets.US_ASCII)

        if (magic == "DSD ") {
            return true
        }

        if (magic == "FRM8") {
            if (!peekSafely(input, peek, 4, 12)) return false
            val formType = String(peek, 12, 4, Charsets.US_ASCII)
            return formType == "DSD "
        }

        return false
    }

    override fun init(output: ExtractorOutput) {
        this.extractorOutput = output
    }

    override fun read(input: ExtractorInput, seekPosition: PositionHolder): Int {
        if (!initialized) {
            if (!initializeHeader(input)) {
                return Extractor.RESULT_END_OF_INPUT
            }
        }

        val header = dsdHeader ?: return Extractor.RESULT_END_OF_INPUT
        val track = trackOutput ?: return Extractor.RESULT_END_OF_INPUT

        return when (header.type) {
            DsdType.DSF -> readDsf(input, header, track)
            DsdType.DFF -> readDff(input, header, track)
        }
    }

    private fun readDsf(input: ExtractorInput, header: DsdHeader, track: TrackOutput): Int {
        val blockSize = header.blockSizePerChannel
        if (leftBlock.size < blockSize) {
            leftBlock = ByteArray(blockSize)
            rightBlock = ByteArray(blockSize)
        }

        val channels = header.channelCount
        // Read Left channel block
        if (!readSafely(input, leftBlock, 0, blockSize)) {
            return Extractor.RESULT_END_OF_INPUT
        }

        // If stereo, read Right channel block
        if (channels >= 2) {
            if (!readSafely(input, rightBlock, 0, blockSize)) {
                return Extractor.RESULT_END_OF_INPUT
            }
        } else {
            System.arraycopy(leftBlock, 0, rightBlock, 0, blockSize)
        }

        // Output PCM size: blockSize / 4 frames * 2 channels * 4 bytes per float
        val pcmBytesNeeded = (blockSize / 4) * 2 * 4
        if (pcmFloatBuffer.capacity() < pcmBytesNeeded) {
            pcmFloatBuffer = ByteBuffer.allocate(pcmBytesNeeded).order(ByteOrder.LITTLE_ENDIAN)
        }
        pcmFloatBuffer.clear()

        DsdDecimator.decimateDsfStereo(
            leftBlock = leftBlock,
            rightBlock = rightBlock,
            byteOffset = 0,
            byteCount = blockSize,
            histL = histL,
            histR = histR,
            outBuffer = pcmFloatBuffer
        )

        val bytesProduced = pcmFloatBuffer.position()
        if (bytesProduced > 0) {
            parsableOutput.reset(pcmFloatBuffer.array(), bytesProduced)
            track.sampleData(parsableOutput, bytesProduced)
            track.sampleMetadata(currentTimeUs, C.BUFFER_FLAG_KEY_FRAME, bytesProduced, 0, null)

            val frames = bytesProduced / (2 * 4)
            currentTimeUs += (frames * 1_000_000L) / pcmSampleRate
        }

        return Extractor.RESULT_CONTINUE
    }

    private fun readDff(input: ExtractorInput, header: DsdHeader, track: TrackOutput): Int {
        val bytesToRead = 4096 // Read 4KB chunks of interleaved DSD
        if (interleavedDff.size < bytesToRead) {
            interleavedDff = ByteArray(bytesToRead)
        }

        if (!readSafely(input, interleavedDff, 0, bytesToRead)) {
            return Extractor.RESULT_END_OF_INPUT
        }

        val pcmBytesNeeded = (bytesToRead / 8) * 2 * 4
        if (pcmFloatBuffer.capacity() < pcmBytesNeeded) {
            pcmFloatBuffer = ByteBuffer.allocate(pcmBytesNeeded).order(ByteOrder.LITTLE_ENDIAN)
        }
        pcmFloatBuffer.clear()

        DsdDecimator.decimateDffStereo(
            interleavedDsd = interleavedDff,
            byteOffset = 0,
            byteCount = bytesToRead,
            histL = histL,
            histR = histR,
            outBuffer = pcmFloatBuffer
        )

        val bytesProduced = pcmFloatBuffer.position()
        if (bytesProduced > 0) {
            parsableOutput.reset(pcmFloatBuffer.array(), bytesProduced)
            track.sampleData(parsableOutput, bytesProduced)
            track.sampleMetadata(currentTimeUs, C.BUFFER_FLAG_KEY_FRAME, bytesProduced, 0, null)

            val frames = bytesProduced / (2 * 4)
            currentTimeUs += (frames * 1_000_000L) / pcmSampleRate
        }

        return Extractor.RESULT_CONTINUE
    }

    private fun initializeHeader(input: ExtractorInput): Boolean {
        // Peek up to 4096 bytes of stream header
        val headerPeek = ByteArray(4096)
        if (!peekSafely(input, headerPeek, 0, headerPeek.size)) {
            return false
        }

        val parsed = DsdHeaderParser.parse(ByteArrayInputStream(headerPeek)) ?: return false
        dsdHeader = parsed

        pcmSampleRate = (parsed.sampleRate / DsdDecimator.DECIMATION_RATIO).coerceAtLeast(44100)

        val output = extractorOutput ?: return false
        val track = output.track(0, C.TRACK_TYPE_AUDIO)
        trackOutput = track

        val format = Format.Builder()
            .setId("dsd")
            .setSampleMimeType(MimeTypes.AUDIO_RAW)
            .setPcmEncoding(C.ENCODING_PCM_FLOAT)
            .setChannelCount(2) // We output normalized stereo float PCM
            .setSampleRate(pcmSampleRate)
            .build()
        track.format(format)

        val durationUs = parsed.durationMs * 1000L
        output.seekMap(SeekMap.Unseekable(durationUs))
        output.endTracks()

        // Skip input up to dataOffset
        val currentPos = input.position
        val toSkip = parsed.dataOffset - currentPos
        if (toSkip > 0) {
            input.skipFully(toSkip.toInt())
        }

        initialized = true
        return true
    }

    override fun seek(position: Long, timeUs: Long) {
        currentTimeUs = timeUs
        histL.fill(0)
        histR.fill(0)
    }

    override fun release() {
        // No native handles to release
    }

    private fun peekSafely(input: ExtractorInput, target: ByteArray, offset: Int, length: Int): Boolean {
        return try {
            input.peekFully(target, offset, length, false)
        } catch (e: Throwable) {
            false
        }
    }

    private fun readSafely(input: ExtractorInput, target: ByteArray, offset: Int, length: Int): Boolean {
        return try {
            input.readFully(target, offset, length, false)
        } catch (e: Throwable) {
            false
        }
    }
}
