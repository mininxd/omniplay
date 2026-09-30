package xyz.omniplay.mesh

enum class MeshRole {
    STANDALONE,
    HOST,
    SATELLITE
}

enum class AudioChannel(val displayName: String) {
    STEREO("Stereo (All)"),
    LEFT_ONLY("Left Channel"),
    RIGHT_ONLY("Right Channel"),
    CENTER("Center (Vocal)")
}

data class MeshPeer(
    val id: String,
    val name: String,
    val ip: String,
    var channel: AudioChannel = AudioChannel.STEREO,
    var volumeTrim: Float = 1.0f,
    var latencyMs: Long = 0L,
    var isHost: Boolean = false
)

data class MeshRoom(
    val roomName: String,
    val hostName: String,
    val hostAddress: String,
    val port: Int,
    val streamPort: Int
)

data class SyncCommand(
    val action: String, // "HELLO", "ROOM_STATE", "PLAY", "PAUSE", "SEEK", "PING", "PONG", "SET_CHANNEL"
    val timestamp: Long = System.currentTimeMillis(),
    val positionMs: Long = 0L,
    val scheduledAt: Long = 0L,
    val songTitle: String = "",
    val songArtist: String = "",
    val songDuration: Long = 0L,
    val channel: String = "STEREO",
    val peerId: String = "",
    val peerName: String = "",
    val streamPort: Int = 8998
)
