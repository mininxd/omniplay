package xyz.omniplay.mesh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import xyz.omniplay.R
import xyz.omniplay.databinding.BottomSheetAcousticMeshBinding
import xyz.omniplay.databinding.ItemConnectedSpeakerBinding
import xyz.omniplay.databinding.ItemDiscoveredRoomBinding

/**
 * Material You (Material 3) Acoustic Mesh Bottom Sheet:
 * - Host Mode: Manage room broadcast, view connected speakers with real-time sync metrics
 * - Join Mode: Scan nearby local rooms, join as a satellite speaker, select channel (Left/Right/Center/Stereo)
 */
class AcousticMeshBottomSheet : BottomSheetDialogFragment(), AcousticMeshManager.MeshListener {

    private var _binding: BottomSheetAcousticMeshBinding? = null
    private val binding get() = _binding!!

    private lateinit var meshManager: AcousticMeshManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetAcousticMeshBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meshManager = AcousticMeshManager.getInstance(requireContext())
        meshManager.addListener(this)

        setupTabs()
        setupHostView()
        setupSatelliteView()
        updateUIForCurrentRole(meshManager.currentRole)
    }

    private fun setupTabs() {
        binding.meshModeToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_tab_host -> {
                        binding.hostLayoutContainer.visibility = View.VISIBLE
                        binding.joinLayoutContainer.visibility = View.GONE
                        meshManager.stopScanningRooms()
                    }
                    R.id.btn_tab_join -> {
                        binding.hostLayoutContainer.visibility = View.GONE
                        binding.joinLayoutContainer.visibility = View.VISIBLE
                        if (meshManager.currentRole == MeshRole.STANDALONE) {
                            meshManager.startScanningRooms()
                        }
                    }
                }
            }
        }
    }

    private fun setupHostView() {
        binding.hostRoomNameText.text = "${meshManager.deviceName}'s Room"

        binding.btnHostAction.setOnClickListener {
            if (meshManager.currentRole == MeshRole.HOST) {
                meshManager.leaveRoom()
            } else {
                meshManager.startHost("${meshManager.deviceName}'s Room")
            }
        }
    }

    private fun setupSatelliteView() {
        binding.channelChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            val channel = when (checkedIds.firstOrNull()) {
                R.id.chip_left -> AudioChannel.LEFT_ONLY
                R.id.chip_right -> AudioChannel.RIGHT_ONLY
                R.id.chip_center -> AudioChannel.CENTER
                else -> AudioChannel.STEREO
            }
            meshManager.setChannel(channel)
        }

        binding.satelliteVolumeSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    meshManager.setVolumeBalance(progress / 100f)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.btnDisconnectSatellite.setOnClickListener {
            meshManager.leaveRoom()
            binding.meshModeToggleGroup.check(R.id.btn_tab_join)
            meshManager.startScanningRooms()
        }
    }

    private fun updateUIForCurrentRole(role: MeshRole) {
        when (role) {
            MeshRole.HOST -> {
                binding.hostLayoutContainer.visibility = View.VISIBLE
                binding.joinLayoutContainer.visibility = View.GONE
                binding.meshModeToggleGroup.check(R.id.btn_tab_host)
                binding.btnHostAction.text = getString(R.string.mesh_stop_broadcast)
                binding.hostStatusText.text = "Broadcasting master audio on local network"
                binding.meshSyncBadge.text = "Master Host"
            }
            MeshRole.SATELLITE -> {
                binding.hostLayoutContainer.visibility = View.GONE
                binding.joinLayoutContainer.visibility = View.VISIBLE
                binding.joinScanningContainer.visibility = View.GONE
                binding.satelliteControlsContainer.visibility = View.VISIBLE
                binding.meshModeToggleGroup.check(R.id.btn_tab_join)
                binding.satelliteRoomNameText.text = meshManager.currentRoomName ?: "Connected Room"
                binding.meshSyncBadge.text = "Satellite Node"

                when (meshManager.currentChannel) {
                    AudioChannel.STEREO -> binding.chipStereo.isChecked = true
                    AudioChannel.LEFT_ONLY -> binding.chipLeft.isChecked = true
                    AudioChannel.RIGHT_ONLY -> binding.chipRight.isChecked = true
                    AudioChannel.CENTER -> binding.chipCenter.isChecked = true
                }
                binding.satelliteVolumeSlider.progress = (meshManager.volumeTrim * 100).toInt()
            }
            MeshRole.STANDALONE -> {
                val isHostTab = binding.meshModeToggleGroup.checkedButtonId == R.id.btn_tab_host
                binding.hostLayoutContainer.visibility = if (isHostTab) View.VISIBLE else View.GONE
                binding.joinLayoutContainer.visibility = if (isHostTab) View.GONE else View.VISIBLE
                binding.btnHostAction.text = getString(R.string.mesh_start_broadcast)
                binding.hostStatusText.text = "Ready to broadcast local audio to nearby friends"
                binding.joinScanningContainer.visibility = View.VISIBLE
                binding.satelliteControlsContainer.visibility = View.GONE
                binding.meshSyncBadge.text = "Offline P2P"
            }
        }
    }

    // =========================================================================
    // MeshListener Implementation
    // =========================================================================

    override fun onRoleChanged(role: MeshRole) {
        if (_binding != null) {
            updateUIForCurrentRole(role)
        }
    }

    override fun onRoomsDiscovered(rooms: List<MeshRoom>) {
        if (_binding == null) return
        val container = binding.discoveredRoomsContainer
        container.removeAllViews()

        if (rooms.isEmpty()) {
            (binding.joinEmptyRoomsText.parent as? ViewGroup)?.removeView(binding.joinEmptyRoomsText)
            container.addView(binding.joinEmptyRoomsText)
            binding.joinEmptyRoomsText.visibility = View.VISIBLE
            return
        }

        binding.joinEmptyRoomsText.visibility = View.GONE
        val inflater = LayoutInflater.from(requireContext())
        for (room in rooms) {
            val itemBinding = ItemDiscoveredRoomBinding.inflate(inflater, container, false)
            itemBinding.roomNameText.text = room.roomName
            itemBinding.roomHostText.text = "${room.hostAddress} • Ready"
            itemBinding.btnJoinRoom.setOnClickListener {
                meshManager.joinRoom(room)
            }
            container.addView(itemBinding.root)
        }
    }

    override fun onPeersChanged(peers: List<MeshPeer>) {
        if (_binding == null) return
        val container = binding.hostSpeakersContainer
        container.removeAllViews()

        if (peers.isEmpty()) {
            (binding.hostEmptySpeakersText.parent as? ViewGroup)?.removeView(binding.hostEmptySpeakersText)
            container.addView(binding.hostEmptySpeakersText)
            binding.hostEmptySpeakersText.visibility = View.VISIBLE
            return
        }

        binding.hostEmptySpeakersText.visibility = View.GONE
        val inflater = LayoutInflater.from(requireContext())
        for (peer in peers) {
            val itemBinding = ItemConnectedSpeakerBinding.inflate(inflater, container, false)
            itemBinding.speakerNameText.text = peer.name
            itemBinding.speakerIpText.text = "${peer.ip} • Locked"
            itemBinding.speakerChannelBadge.text = peer.channel.displayName
            container.addView(itemBinding.root)
        }
    }

    override fun onSyncStatusChanged(latencyMs: Long, clockOffsetMs: Long) {
        if (_binding != null) {
            binding.satelliteLatencyBadge.text = "${latencyMs}ms"
        }
    }

    override fun onChannelChanged(channel: AudioChannel) {}

    override fun onError(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        meshManager.removeListener(this)
        if (meshManager.currentRole == MeshRole.STANDALONE) {
            meshManager.stopScanningRooms()
        }
        _binding = null
    }

    companion object {
        const val TAG = "AcousticMeshBottomSheet"

        fun newInstance(): AcousticMeshBottomSheet {
            return AcousticMeshBottomSheet()
        }
    }
}
