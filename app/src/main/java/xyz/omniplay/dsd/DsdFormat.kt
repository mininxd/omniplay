package xyz.omniplay.dsd

enum class DsdType {
    DSF,
    DFF
}

data class DsdHeader(
    val type: DsdType,
    val formatName: String,
    val channelCount: Int,
    val sampleRate: Int,
    val sampleCount: Long,
    val durationMs: Long,
    val dataOffset: Long,
    val dataLength: Long,
    val blockSizePerChannel: Int = 4096,
    val title: String? = null,
    val artist: String? = null,
    val album: String? = null
) {
    val bitrateKbps: Int
        get() = ((sampleRate.toLong() * channelCount) / 1000L).toInt()

    companion object {
        fun getFormatName(sampleRate: Int): String = when {
            sampleRate >= 22579200 -> "DSD512"
            sampleRate >= 11289600 -> "DSD256"
            sampleRate >= 5644800 -> "DSD128"
            sampleRate >= 2822400 -> "DSD64"
            else -> "DSD"
        }
    }
}
