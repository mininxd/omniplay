package xyz.omniplay.sync

enum class OmniSyncRole {
    IDLE,
    HOST,
    LISTENER
}

enum class OmniSyncQuality {
    HIGH,
    LOW
}

data class OmniSyncHost(
    val name: String,
    val address: String,
    val port: Int = OmniSyncManager.PORT,
    val streamPort: Int = OmniSyncManager.PORT
)

data class OmniSyncPeer(
    val id: String,
    val name: String,
    val ip: String
)
