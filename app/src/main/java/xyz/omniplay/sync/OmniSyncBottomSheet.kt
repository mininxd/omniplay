package xyz.omniplay.sync

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.databinding.BottomSheetOmnisyncBinding
import xyz.omniplay.databinding.ItemOmnisyncHostBinding

/**
 * Material You (Material 3) OmniSync Bottom Sheet:
 * - Host Mode: Manage room broadcast, view connected listeners and now-playing track
 * - Listener Mode: Scan nearby local hosts, join as a listener, or connect via manual IP
 */
class OmniSyncBottomSheet : BottomSheetDialogFragment(), OmniSyncManager.OmniSyncListener {

    private var _binding: BottomSheetOmnisyncBinding? = null
    private val binding get() = _binding!!

    private lateinit var syncManager: OmniSyncManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetOmnisyncBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        syncManager = OmniSyncManager.getInstance(requireContext())
        syncManager.addListener(this)

        setupTabs()
        setupHostView()
        setupListenerView()
        updateUIForCurrentRole(syncManager.currentRole)
    }

    private fun setupTabs() {
        binding.syncModeToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_tab_host -> {
                        binding.hostLayoutContainer.visibility = View.VISIBLE
                        binding.listenerLayoutContainer.visibility = View.GONE
                        if (syncManager.currentRole == OmniSyncRole.IDLE) {
                            syncManager.stopScanningHosts()
                        }
                    }
                    R.id.btn_tab_listener -> {
                        binding.hostLayoutContainer.visibility = View.GONE
                        binding.listenerLayoutContainer.visibility = View.VISIBLE
                        if (syncManager.currentRole == OmniSyncRole.IDLE) {
                            syncManager.startScanningHosts()
                        }
                    }
                }
            }
        }
    }

    private fun setupHostView() {
        binding.hostRoomNameText.text = syncManager.deviceName
        val ip = syncManager.getLocalIPv4Address() ?: "Wi-Fi / Hotspot"
        binding.hostIpText.text = "$ip:${OmniSyncManager.STREAM_PORT}"

        val currentSong = syncManager.hostSongProvider?.invoke()
        if (currentSong != null) {
            binding.hostNowPlayingText.text = "${currentSong.title} • ${currentSong.artist}"
        } else {
            binding.hostNowPlayingText.text = "No track playing in Omniplay"
        }

        binding.btnHostAction.setOnClickListener {
            if (syncManager.currentRole == OmniSyncRole.HOST) {
                syncManager.stopHost()
            } else if (syncManager.currentRole == OmniSyncRole.LISTENER) {
                Toast.makeText(requireContext(), "Disconnect listener before starting host", Toast.LENGTH_SHORT).show()
            } else {
                syncManager.startHost(syncManager.deviceName)
            }
        }
    }

    private fun setupListenerView() {
        binding.btnManualConnect.setOnClickListener {
            if (syncManager.currentRole == OmniSyncRole.HOST) {
                Toast.makeText(requireContext(), "Stop hosting before joining another room", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val raw = binding.editManualIp.text?.toString()?.trim() ?: ""
            if (raw.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter host IP address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val hostIp = if (raw.contains(":")) raw.substringBefore(":") else raw
            val port = if (raw.contains(":")) raw.substringAfter(":").toIntOrNull() ?: OmniSyncManager.PORT else OmniSyncManager.PORT
            val host = OmniSyncHost(
                name = hostIp,
                address = hostIp,
                port = port,
                streamPort = port
            )
            binding.btnManualConnect.isEnabled = false
            syncManager.connectToHost(host)
        }

        binding.listenerVolumeSlider.progress = (syncManager.listenerVolume * 100).toInt()
        binding.listenerVolumeSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    syncManager.setListenerVolume(progress / 100f)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.btnDisconnectListener.setOnClickListener {
            syncManager.disconnectListener()
            binding.syncModeToggleGroup.check(R.id.btn_tab_listener)
            syncManager.startScanningHosts()
        }
    }

    private fun updateUIForCurrentRole(role: OmniSyncRole) {
        if (_binding == null) return

        when (role) {
            OmniSyncRole.HOST -> {
                binding.hostLayoutContainer.visibility = View.VISIBLE
                binding.listenerLayoutContainer.visibility = View.GONE
                binding.syncModeToggleGroup.check(R.id.btn_tab_host)
                binding.btnHostAction.isEnabled = true
                binding.btnHostAction.text = getString(R.string.omnisync_stop_host)
                binding.hostStatusBadge.text = "STREAMING"
                binding.hostStatusBadge.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_accent))
                binding.syncBadge.text = "Host Active"
                binding.btnManualConnect.isEnabled = false
                onPeersChanged(syncManager.connectedPeers)

                val song = syncManager.hostSongProvider?.invoke()
                if (song != null) {
                    binding.hostNowPlayingText.text = "${song.title} • ${song.artist}"
                } else {
                    binding.hostNowPlayingText.text = "No track playing in Omniplay"
                }
            }
            OmniSyncRole.LISTENER -> {
                binding.hostLayoutContainer.visibility = View.GONE
                binding.listenerLayoutContainer.visibility = View.VISIBLE
                binding.listenerDiscoveryContainer.visibility = View.GONE
                binding.listenerConnectedContainer.visibility = View.VISIBLE
                binding.syncModeToggleGroup.check(R.id.btn_tab_listener)
                binding.btnHostAction.isEnabled = false
                binding.btnHostAction.text = "Cannot Host While Listening"

                val host = syncManager.currentHost
                binding.connectedHostNameText.text = syncManager.currentHostRoomName ?: host?.name ?: "Connected Host"
                binding.connectedHostIpText.text = host?.address ?: ""
                binding.syncBadge.text = "Listener"

                if (syncManager.currentTrackTitle.isNotEmpty()) {
                    binding.connectedSongTitleText.text = syncManager.currentTrackTitle
                    binding.connectedSongArtistText.text = syncManager.currentTrackArtist.ifEmpty { "Host Broadcast" }
                } else {
                    binding.connectedSongTitleText.text = "Waiting for stream…"
                    binding.connectedSongArtistText.text = "Host Broadcast"
                }

                if (syncManager.currentLatencyMs > 0L) {
                    binding.listenerLatencyBadge.text = "(${syncManager.currentLatencyMs}ms)"
                    binding.listenerLatencyBadge.visibility = View.VISIBLE
                } else {
                    binding.listenerLatencyBadge.visibility = View.GONE
                }
            }
            OmniSyncRole.IDLE -> {
                val isHostTab = binding.syncModeToggleGroup.checkedButtonId == R.id.btn_tab_host
                binding.hostLayoutContainer.visibility = if (isHostTab) View.VISIBLE else View.GONE
                binding.listenerLayoutContainer.visibility = if (isHostTab) View.GONE else View.VISIBLE
                binding.btnHostAction.isEnabled = true
                binding.btnHostAction.text = getString(R.string.omnisync_start_host)
                binding.hostStatusBadge.text = "IDLE"
                binding.hostStatusBadge.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_secondary))
                binding.listenerDiscoveryContainer.visibility = View.VISIBLE
                binding.listenerConnectedContainer.visibility = View.GONE
                binding.btnManualConnect.isEnabled = true
                binding.syncBadge.text = "Offline P2P"
                binding.listenerLatencyBadge.visibility = View.GONE
                if (!isHostTab) {
                    syncManager.startScanningHosts()
                }
                onHostsDiscovered(syncManager.discoveredHosts)
            }
        }
    }

    // =========================================================================
    // OmniSyncListener Callbacks
    // =========================================================================

    override fun onRoleChanged(role: OmniSyncRole) {
        if (_binding != null) {
            updateUIForCurrentRole(role)
        }
    }

    override fun onHostsDiscovered(hosts: List<OmniSyncHost>) {
        if (_binding == null) return
        val container = binding.discoveredHostsContainer
        container.removeAllViews()

        if (hosts.isEmpty()) {
            binding.listenerEmptyHostsText.visibility = View.VISIBLE
            return
        }

        binding.listenerEmptyHostsText.visibility = View.GONE
        val inflater = LayoutInflater.from(requireContext())
        val isHosting = syncManager.currentRole == OmniSyncRole.HOST
        for (host in hosts) {
            val itemBinding = ItemOmnisyncHostBinding.inflate(inflater, container, false)
            itemBinding.hostNameText.text = host.name
            itemBinding.hostAddressText.text = "${host.address} • Ready"
            if (isHosting) {
                itemBinding.btnJoinHost.isEnabled = false
                itemBinding.btnJoinHost.text = "Host Active"
            } else {
                itemBinding.btnJoinHost.isEnabled = true
                itemBinding.btnJoinHost.text = getString(R.string.omnisync_connect)
                itemBinding.btnJoinHost.setOnClickListener { v ->
                    if (syncManager.currentRole == OmniSyncRole.HOST) {
                        Toast.makeText(requireContext(), "Stop hosting before joining another room", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    v.isEnabled = false
                    (v as? com.google.android.material.button.MaterialButton)?.text = "Connecting…"
                    syncManager.connectToHost(host)
                }
            }
            container.addView(itemBinding.root)
        }
    }

    override fun onPeersChanged(peers: List<OmniSyncPeer>) {
        if (_binding == null) return
        val count = peers.size
        binding.hostListenersCountText.text = if (count == 1) "1 listener connected" else "$count listeners connected"
    }

    override fun onTrackInfoChanged(title: String, artist: String) {
        if (_binding != null && syncManager.currentRole == OmniSyncRole.LISTENER) {
            binding.connectedSongTitleText.text = title.ifEmpty { "Waiting for stream…" }
            binding.connectedSongArtistText.text = artist.ifEmpty { "Host Broadcast" }
        }
    }

    override fun onPlaybackStateChanged(isPlaying: Boolean) {
        if (_binding != null && syncManager.currentRole == OmniSyncRole.LISTENER) {
            binding.listenerStatusBadge.text = if (isPlaying) "STREAMING" else "PAUSED"
        }
    }

    override fun onError(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
        if (_binding != null && syncManager.currentRole == OmniSyncRole.IDLE) {
            binding.btnManualConnect.isEnabled = true
            onHostsDiscovered(syncManager.discoveredHosts)
        }
    }

    override fun onProgressUpdate(currentPositionMs: Long, durationMs: Long) {}

    override fun onLatencyUpdate(latencyMs: Long) {
        if (_binding != null && syncManager.currentRole == OmniSyncRole.LISTENER) {
            if (latencyMs > 0L) {
                binding.listenerLatencyBadge.text = "(${latencyMs}ms)"
                binding.listenerLatencyBadge.visibility = View.VISIBLE
            } else {
                binding.listenerLatencyBadge.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        syncManager.removeListener(this)
        if (syncManager.currentRole == OmniSyncRole.IDLE) {
            syncManager.stopScanningHosts()
        }
        _binding = null
    }

    companion object {
        const val TAG = "OmniSyncBottomSheet"

        fun newInstance(): OmniSyncBottomSheet {
            return OmniSyncBottomSheet()
        }
    }
}
