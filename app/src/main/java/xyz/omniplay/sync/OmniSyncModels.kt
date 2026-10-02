package xyz.omniplay.sync

enum class OmniSyncRole {
    IDLE,
    HOST,
    LISTENER
}

data class OmniSyncHost(
    val name: String,
    val address: String,
    val port: Int = OmniSyncManager.CONTROL_PORT,
    val streamPort: Int = OmniSyncManager.STREAM_PORT
)

data class OmniSyncPeer(
    val id: String,
    val name: String,
    val ip: String
)
