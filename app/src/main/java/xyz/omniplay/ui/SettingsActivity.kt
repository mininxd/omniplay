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
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivitySettingsBinding
import xyz.omniplay.util.MusicFolderManager
import xyz.omniplay.util.ThemeHelper
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

        // 1. Theme Style Selector
        updateThemeSubtitle()
        binding.settingTheme.setOnClickListener {
            showThemeStyleDialog()
        }

        // 2. Select Music Folder (Shows Manage Folders Modal)
        updateFolderSubtitle()

        binding.settingSelectFolder.setOnClickListener {
            showManageFoldersDialog()
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

        // 4. Rescan Music
        binding.settingRescanMusic.setOnClickListener {
            isRescanRequested = true
            prepareResult()
            Toast.makeText(this, "Rescanning music...", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 5. About Omniplay
        binding.settingAbout.setOnClickListener {
            showAboutDialog()
        }

        // 6. Check for Updates
        binding.settingCheckUpdate.setOnClickListener {
            checkAppUpdate(isManual = true)
        }

        prepareResult()
    }

    private fun checkAppUpdate(isManual: Boolean) {
        binding.settingCheckUpdateSubtitle.setText(R.string.checking_updates)
        lifecycleScope.launch {
            val release = UpdateChecker.checkLatestRelease(this@SettingsActivity)
            if (isFinishing || isDestroyed) return@launch

            if (release != null) {
                if (release.isNewer) {
                    binding.settingCheckUpdateSubtitle.text = getString(R.string.update_available_format, release.tagName)
                    UpdateChecker.showUpdateDialog(this@SettingsActivity, release)
                } else {
                    val displayTag = if (release.tagName.startsWith("v", ignoreCase = true)) release.tagName.drop(1) else release.tagName
                    binding.settingCheckUpdateSubtitle.text = getString(R.string.up_to_date_format, displayTag)
                    if (isManual) {
                        Toast.makeText(this@SettingsActivity, R.string.latest_version_installed, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                binding.settingCheckUpdateSubtitle.setText(R.string.check_for_updates_desc)
                if (isManual) {
                    Toast.makeText(this@SettingsActivity, R.string.check_update_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
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

                    nameText.text = MusicFolderManager.getDisplayName(this, folderUriString)
                    pathText.text = MusicFolderManager.getDisplayPath(this, folderUriString)

                    btnRemove.setOnClickListener {
                        val removedName = MusicFolderManager.getDisplayName(this, folderUriString)
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

    private fun showAboutDialog() {
        val message = """
            Omniplay 0.6.1
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
