package xyz.omniplay.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivitySettingsBinding
import xyz.omniplay.util.ThemeHelper
import xyz.omniplay.util.ThemeStyle

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

    private val folderPickerLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { treeUri: Uri? ->
        if (treeUri != null) {
            try {
                contentResolver.takePersistableUriPermission(
                    treeUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (ignored: Exception) {}

            getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(MainActivity.KEY_MUSIC_FOLDER_URI, treeUri.toString())
                .apply()

            isFolderChanged = true
            prepareResult()
            updateFolderSubtitle(treeUri.toString())
            Toast.makeText(this, "Music folder updated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            isThemeChanged = savedInstanceState.getBoolean(EXTRA_THEME_CHANGED, false)
            isFolderChanged = savedInstanceState.getBoolean(EXTRA_FOLDER_CHANGED, false)
        }

        // Setup Toolbar back button
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val prefs = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)

        // 1. OmniSync
        binding.settingOmnisync.setOnClickListener {
            xyz.omniplay.sync.OmniSyncBottomSheet.newInstance()
                .show(supportFragmentManager, xyz.omniplay.sync.OmniSyncBottomSheet.TAG)
        }

        // 2. Theme Style Selector
        updateThemeSubtitle()
        binding.settingTheme.setOnClickListener {
            showThemeStyleDialog()
        }

        // 3. Select Music Folder
        val currentFolder = prefs.getString(MainActivity.KEY_MUSIC_FOLDER_URI, null)
        updateFolderSubtitle(currentFolder)

        binding.settingSelectFolder.setOnClickListener {
            try {
                folderPickerLauncher.launch(null)
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to open folder picker: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Show Album Art Switch & Row Click (Compact Split Button)
        binding.titleAlbumArt.isSelected = true
        val isShowArt = prefs.getBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)
        binding.switchShowAlbumArt.isChecked = isShowArt

        binding.switchShowAlbumArt.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(MainActivity.KEY_SHOW_ALBUM_ART_IN_PLAYLIST, isChecked).apply()
        }

        binding.btnAlbumArtLabel.setOnClickListener {
            binding.switchShowAlbumArt.toggle()
        }

        binding.btnAlbumArtSwitch.setOnClickListener {
            binding.switchShowAlbumArt.toggle()
        }

        // 5. Rescan Music
        binding.settingRescanMusic.setOnClickListener {
            isRescanRequested = true
            prepareResult()
            Toast.makeText(this, "Rescanning music...", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 6. About Omniplay
        binding.settingAbout.setOnClickListener {
            showAboutDialog()
        }

        prepareResult()
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

    private fun updateThemeSubtitle() {
        val currentStyle = ThemeHelper.getThemeStyle(this)
        binding.settingThemeSubtitle.setText(currentStyle.titleRes)
    }

    private fun showThemeStyleDialog() {
        val currentStyle = ThemeHelper.getThemeStyle(this)
        val styles = ThemeStyle.values()
        val names = styles.map { getString(it.titleRes) }.toTypedArray()
        val selectedIndex = styles.indexOf(currentStyle)

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

    private fun updateFolderSubtitle(folderUriString: String?) {
        if (folderUriString != null) {
            try {
                val uri = Uri.parse(folderUriString)
                val docFile = DocumentFile.fromTreeUri(this, uri)
                binding.settingFolderSubtitle.text = docFile?.name ?: uri.lastPathSegment ?: folderUriString
            } catch (e: Exception) {
                binding.settingFolderSubtitle.text = folderUriString
            }
        } else {
            binding.settingFolderSubtitle.text = getString(R.string.music_folder_desc)
        }
    }

    private fun showAboutDialog() {
        val message = """
            Omniplay 0.5
            Open Source Music Player
            
            Supports: MP3, WAV, FLAC, AAC, M4A, OGG, OPUS, DSD (DSF/DFF), and more.
            Architectures: armv7, armv8, x86, x86_64, Universal
            
            Built with pure Android.
        """.trimIndent()

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.about_omniplay)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
