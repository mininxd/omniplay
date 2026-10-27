package xyz.omniplay.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivitySettingsBinding
import xyz.omniplay.util.MusicFolderManager
import xyz.omniplay.util.ThemeHelper
import xyz.omniplay.util.ThemeMode
import xyz.omniplay.util.ThemeStyle
import xyz.omniplay.util.UpdateChecker

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RESCAN = "extra_rescan"
        const val EXTRA_FOLDER_CHANGED = "extra_folder_changed"
        const val EXTRA_THEME_CHANGED = "extra_theme_changed"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var isFolderChanged = false
    private var isRescanRequested = false
    private var isThemeChanged = false

    private var manageFoldersDialog: AlertDialog? = null
    private var refreshFoldersCallback: (() -> Unit)? = null

    private val folderPickerLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { treeUri: Uri? ->
        if (treeUri != null) {
            val folderName = MusicFolderManager.getDisplayName(this, treeUri.toString())
            val added = MusicFolderManager.addFolder(this, treeUri)
            isFolderChanged = true
            prepareResult()
            updateFolderSubtitle()
            if (added) {
                Toast.makeText(this, getString(R.string.folder_added, folderName), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.folder_already_added, Toast.LENGTH_SHORT).show()
            }
            refreshFoldersCallback?.invoke()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ThemeHelper.applySystemBars(this)

        if (savedInstanceState != null) {
            isThemeChanged = savedInstanceState.getBoolean(EXTRA_THEME_CHANGED, false)
            isFolderChanged = savedInstanceState.getBoolean(EXTRA_FOLDER_CHANGED, false)
        }

        // Setup Toolbar back button
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)

        // 1. Theme Mode (Auto / Light / Dark)
        updateThemeModeSubtitle()
        binding.settingThemeMode.setOnClickListener {
            showThemeModeDialog()
        }

        // Keep fallback scheme choices available on older Android versions too.
        binding.settingTheme.visibility = View.VISIBLE
        binding.dividerThemeStyle.visibility = View.VISIBLE
        updateThemeSubtitle()
        binding.settingTheme.setOnClickListener {
            showThemeStyleDialog()
        }

        // 2. Select Music Folder (Shows Manage Folders Modal)
        updateFolderSubtitle()

        binding.settingSelectFolder.setOnClickListener {
            showManageFoldersDialog()
        }

        // Filter Short Audio Switch & Row Click
        binding.titleFilterShortAudio.isSelected = true
        val isFilterShortAudio = prefs.getBoolean(MainActivity.KEY_IGNORE_SHORT_AUDIO, true)
        binding.switchFilterShortAudio.isChecked = isFilterShortAudio

        binding.switchFilterShortAudio.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_IGNORE_SHORT_AUDIO, isChecked).apply()
            isRescanRequested = true
            prepareResult()
        }

        binding.settingFilterShortAudio.setOnClickListener {
            binding.switchFilterShortAudio.toggle()
        }

        // 3. Show Album Art Switch & Row Click (Unified Row)
        binding.titleAlbumArt.isSelected = true
        val isShowArt = prefs.getBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)
        binding.switchShowAlbumArt.isChecked = isShowArt

        binding.switchShowAlbumArt.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, isChecked).apply()
        }

        binding.settingShowAlbumArt.setOnClickListener {
            binding.switchShowAlbumArt.toggle()
        }

        // 4. Download Missing Artwork Switch & Row Click
        binding.titleOnlineArt.isSelected = true
        val isFetchOnlineArt = prefs.getBoolean(MainActivity.KEY_FETCH_ONLINE_ARTWORK, true)
        binding.switchFetchOnlineArtwork.isChecked = isFetchOnlineArt

        binding.switchFetchOnlineArtwork.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_FETCH_ONLINE_ARTWORK, isChecked).apply()
            if (isChecked) {
                xyz.omniplay.util.AlbumArtLoader.clearMemoryCache()
            }
        }

        binding.settingFetchOnlineArtwork.setOnClickListener {
            binding.switchFetchOnlineArtwork.toggle()
        }

        // Direct Fullscreen Lyrics Switch & Row Click
        binding.titleDirectFullscreenLyrics.isSelected = true
        val isDirectFullscreenLyrics = prefs.getBoolean(MainActivity.KEY_DIRECT_FULLSCREEN_LYRICS, false)
        binding.switchDirectFullscreenLyrics.isChecked = isDirectFullscreenLyrics

        binding.switchDirectFullscreenLyrics.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_DIRECT_FULLSCREEN_LYRICS, isChecked).apply()
        }

        binding.settingDirectFullscreenLyrics.setOnClickListener {
            binding.switchDirectFullscreenLyrics.toggle()
        }

        // Crossfade Setting
        binding.titleCrossfade.isSelected = true
        val crossfadeDuration = prefs.getInt(MainActivity.KEY_CROSSFADE_DURATION, 0)
        binding.sliderCrossfade.value = crossfadeDuration.toFloat()
        binding.textCrossfadeDuration.text = if (crossfadeDuration > 0) "${crossfadeDuration}s" else "Off"

        // Crossfade on Next Button Setting
        binding.titleCrossfadeOnNext.isSelected = true
        val isCrossfadeOnNext = prefs.getBoolean(MainActivity.KEY_CROSSFADE_ON_NEXT, false)
        binding.switchCrossfadeOnNext.isChecked = isCrossfadeOnNext
        binding.settingCrossfadeOnNext.alpha = if (crossfadeDuration > 0) 1f else 0.5f
        binding.switchCrossfadeOnNext.isEnabled = crossfadeDuration > 0
        binding.settingCrossfadeOnNext.isEnabled = crossfadeDuration > 0

        binding.switchCrossfadeOnNext.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_CROSSFADE_ON_NEXT, isChecked).apply()
        }

        binding.settingCrossfadeOnNext.setOnClickListener {
            if (binding.switchCrossfadeOnNext.isEnabled) {
                binding.switchCrossfadeOnNext.toggle()
            }
        }

        binding.sliderCrossfade.addOnChangeListener { _, value, _ ->
            val duration = value.toInt()
            binding.textCrossfadeDuration.text = if (duration > 0) "${duration}s" else "Off"
            val isEnabled = duration > 0
            binding.settingCrossfadeOnNext.alpha = if (isEnabled) 1f else 0.5f
            binding.switchCrossfadeOnNext.isEnabled = isEnabled
            binding.settingCrossfadeOnNext.isEnabled = isEnabled
        }

        binding.sliderCrossfade.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                val duration = slider.value.toInt()
                prefs.edit().putInt(MainActivity.KEY_CROSSFADE_DURATION, duration).apply()
            }
        })

        // Replay Gain Switch & Row Click
        binding.titleVolumeNormalization.isSelected = true
        val isVolumeNormalization = prefs.getBoolean(MainActivity.KEY_VOLUME_NORMALIZATION, false)
        binding.switchVolumeNormalization.isChecked = isVolumeNormalization

        binding.switchVolumeNormalization.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_VOLUME_NORMALIZATION, isChecked).apply()
        }

        binding.settingVolumeNormalization.setOnClickListener {
            binding.switchVolumeNormalization.toggle()
        }

        // Equalizer and Sound Enhancement
        updateEqualizerSubtitle()
        binding.settingEqualizer.setOnClickListener {
            val fullIntent = packageManager.getLaunchIntentForPackage("xyz.omniplay.equalizer")
            if (isOmniQualizerSupported() && fullIntent != null) {
                startActivity(fullIntent)
            } else {
                val intent = Intent(this, SoundEnhancementActivity::class.java)
                startActivity(intent)
            }
        }

        // 4. Rescan Music
        binding.settingRescanMusic.setOnClickListener {
            isRescanRequested = true
            prepareResult()
            Toast.makeText(this, "Rescanning music...", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 5. Check for Updates
        val currentVersion = UpdateChecker.getAppVersion(this)
        binding.settingCheckUpdateSubtitle.text = getString(R.string.current_version_format, currentVersion)

        binding.settingCheckUpdate.setOnClickListener {
            checkAppUpdate(isManual = true)
        }

        prepareResult()
    }

    private var isCheckingUpdate = false

    private fun checkAppUpdate(isManual: Boolean) {
        if (isCheckingUpdate) return
        isCheckingUpdate = true
        binding.settingCheckUpdateSubtitle.setText(R.string.checking_updates)
        lifecycleScope.launch {
            val currentVersion = UpdateChecker.getAppVersion(this@SettingsActivity)
            val release = UpdateChecker.checkLatestRelease(this@SettingsActivity)
            isCheckingUpdate = false
            if (isFinishing || isDestroyed) return@launch

            if (release != null) {
                val displayTag = release.tagName.removePrefix("v").removePrefix("V")
                if (release.isNewer) {
                    binding.settingCheckUpdateSubtitle.text = getString(R.string.update_available_format, displayTag)
                    UpdateChecker.showUpdateDialog(this@SettingsActivity, release)
                } else {
                    binding.settingCheckUpdateSubtitle.text = getString(R.string.current_version_format, currentVersion)
                    if (isManual) {
                        Toast.makeText(this@SettingsActivity, R.string.latest_version_installed, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                binding.settingCheckUpdateSubtitle.text = getString(R.string.current_version_format, currentVersion)
                if (isManual) {
                    Toast.makeText(this@SettingsActivity, R.string.check_update_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateEqualizerSubtitle()
    }

    private fun updateEqualizerSubtitle() {
        val isOmniQualizerInstalled = isOmniQualizerSupported() &&
            packageManager.getLaunchIntentForPackage("xyz.omniplay.equalizer") != null
        if (isOmniQualizerInstalled) {
            binding.subtitleEqualizer.text = "OmniQualizer DSP Engine"
        } else {
            val eq = xyz.omniplay.audio.EqualizerManager.getInstance(this)
            if (eq.isEnabled()) {
                binding.subtitleEqualizer.setText(R.string.equalizer_active)
            } else {
                binding.subtitleEqualizer.setText(R.string.sound_enhancement_desc)
            }
        }
    }

    private fun isOmniQualizerSupported(): Boolean {
        return xyz.omniplay.BuildConfig.VERSION_CODE >= 35
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_THEME_CHANGED, isThemeChanged)
        outState.putBoolean(EXTRA_FOLDER_CHANGED, isFolderChanged)
    }

    private fun prepareResult() {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_FOLDER_CHANGED, isFolderChanged)
            putExtra(EXTRA_RESCAN, isRescanRequested)
            putExtra(EXTRA_THEME_CHANGED, isThemeChanged)
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }

    private fun updateThemeModeSubtitle() {
        val currentMode = ThemeHelper.getThemeMode(this)
        binding.settingThemeModeSubtitle.setText(currentMode.titleRes)
    }

    private fun showThemeModeDialog() {
        val currentMode = ThemeHelper.getThemeMode(this)
        val modes = ThemeMode.values()
        val names = modes.map { getString(it.titleRes) }.toTypedArray()
        val selectedIndex = modes.indexOf(currentMode)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.theme_setting)
            .setSingleChoiceItems(names, selectedIndex) { dialog, which ->
                val chosenMode = modes[which]
                if (chosenMode != currentMode) {
                    ThemeHelper.setThemeMode(this, chosenMode)
                    isThemeChanged = true
                    prepareResult()
                    dialog.dismiss()
                    recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(R.string.close, null)
            .show()
    }

    private fun updateThemeSubtitle() {
        val currentStyle = ThemeHelper.getThemeStyle(this)
        binding.settingThemeSubtitle.setText(currentStyle.titleRes)
    }

    private fun showThemeStyleDialog() {
        val currentStyle = ThemeHelper.getThemeStyle(this)
        val styles = ThemeHelper.getAvailableStyles()
        val names = styles.map { getString(it.titleRes) }.toTypedArray()
        val selectedIndex = styles.indexOf(currentStyle).let { if (it >= 0) it else 0 }

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.theme_style)
            .setSingleChoiceItems(names, selectedIndex) { dialog, which ->
                val chosenStyle = styles[which]
                if (chosenStyle != currentStyle) {
                    ThemeHelper.setThemeStyle(this, chosenStyle)
                    isThemeChanged = true
                    prepareResult()
                    dialog.dismiss()
                    recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(R.string.close, null)
            .show()
    }

    private fun updateFolderSubtitle() {
        binding.settingFolderSubtitle.text = MusicFolderManager.getFoldersSummary(this)
    }

    private fun showManageFoldersDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_manage_folders, null)
        val container = dialogView.findViewById<LinearLayout>(R.id.folders_list_container)
        val emptyText = dialogView.findViewById<TextView>(R.id.text_empty_folders)
        val btnAddFolder = dialogView.findViewById<View>(R.id.btn_add_folder)
        val btnDone = dialogView.findViewById<View>(R.id.btn_done)

        fun refreshFoldersList() {
            container.removeAllViews()
            val folders = MusicFolderManager.getFolders(this)
            if (folders.isEmpty()) {
                container.addView(emptyText)
                emptyText.visibility = View.VISIBLE
            } else {
                emptyText.visibility = View.GONE
                for (folderUriString in folders) {
                    val itemView = layoutInflater.inflate(R.layout.item_manage_folder, container, false)
                    val nameText = itemView.findViewById<TextView>(R.id.folder_name_text)
                    val pathText = itemView.findViewById<TextView>(R.id.folder_path_text)
                    val btnRemove = itemView.findViewById<ImageButton>(R.id.btn_remove_folder)

                    nameText.text = MusicFolderManager.getRootFolderTitle(this, folderUriString)
                    pathText.text = MusicFolderManager.getDisplayPath(this, folderUriString)

                    btnRemove.setOnClickListener {
                        val removedName = MusicFolderManager.getRootFolderTitle(this, folderUriString)
                        MusicFolderManager.removeFolder(this, folderUriString)
                        isFolderChanged = true
                        prepareResult()
                        updateFolderSubtitle()
                        Toast.makeText(
                            this,
                            getString(R.string.folder_removed, removedName),
                            Toast.LENGTH_SHORT
                        ).show()
                        refreshFoldersList()
                    }
                    container.addView(itemView)
                }
            }
        }

        refreshFoldersList()
        refreshFoldersCallback = { refreshFoldersList() }

        btnAddFolder.setOnClickListener {
            try {
                folderPickerLauncher.launch(null)
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to open folder picker: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .create()

        btnDone.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setOnDismissListener {
            refreshFoldersCallback = null
            manageFoldersDialog = null
        }

        manageFoldersDialog = dialog
        dialog.show()
    }
}
