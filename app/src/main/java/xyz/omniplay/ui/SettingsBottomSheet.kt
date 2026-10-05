package xyz.omniplay.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import xyz.omniplay.databinding.BottomSheetSettingsBinding

class SettingsBottomSheet : BottomSheetDialogFragment() {

    interface SettingsListener {
        fun onOpenFolderPicker()
        fun onRescanMusic()
        fun onShowAbout()
        fun onToggleShowAlbumArt(show: Boolean)
        fun onOpenOmniSync()
    }

    private var _binding: BottomSheetSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)

        // Close Button
        binding.btnCloseSettings.setOnClickListener {
            dismiss()
        }

        // OmniSync Option
        binding.cardSettingOmnisync.setOnClickListener {
            dismiss()
            (activity as? SettingsListener)?.onOpenOmniSync()
        }

        // Folder Path display
        val savedFolderUri = prefs.getString(MainActivity.KEY_MUSIC_FOLDER_URI, null)
        if (savedFolderUri != null) {
            try {
                val uri = Uri.parse(savedFolderUri)
                val docFile = DocumentFile.fromTreeUri(context, uri)
                binding.settingFolderSubtitle.text = docFile?.name ?: uri.lastPathSegment ?: savedFolderUri
            } catch (e: Exception) {
                binding.settingFolderSubtitle.text = savedFolderUri
            }
        }

        // Select Folder Click
        binding.settingSelectFolder.setOnClickListener {
            dismiss()
            (activity as? SettingsListener)?.onOpenFolderPicker()
        }

        // Rescan Music Click
        binding.settingRescanMusic.setOnClickListener {
            dismiss()
            (activity as? SettingsListener)?.onRescanMusic()
        }

        // Show Album Art Switch & Row Click
        val isShowArt = prefs.getBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)
        binding.switchShowAlbumArt.isChecked = isShowArt

        binding.switchShowAlbumArt.setOnCheckedChangeListener { _, isChecked ->
            if (prefs.getBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true) != isChecked) {
                prefs.edit().putBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, isChecked).apply()
                (activity as? SettingsListener)?.onToggleShowAlbumArt(isChecked)
            }
        }

        binding.settingAlbumArtLayout.setOnClickListener {
            binding.switchShowAlbumArt.toggle()
        }

        // About Omniplay Click
        binding.settingAbout.setOnClickListener {
            dismiss()
            (activity as? SettingsListener)?.onShowAbout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SettingsBottomSheet"

        fun newInstance(): SettingsBottomSheet {
            return SettingsBottomSheet()
        }
    }
}
