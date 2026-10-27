package xyz.omniplay.equalizer.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import xyz.omniplay.equalizer.R
import xyz.omniplay.equalizer.databinding.ActivitySettingsBinding
import xyz.omniplay.equalizer.util.ThemeHelper
import xyz.omniplay.equalizer.util.ThemeMode
import xyz.omniplay.equalizer.util.ThemeStyle

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_THEME_CHANGED = "extra_theme_changed"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var isThemeChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ThemeHelper.applySystemBars(this)

        if (savedInstanceState != null) {
            isThemeChanged = savedInstanceState.getBoolean(EXTRA_THEME_CHANGED, false)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        updateThemeModeSubtitle()
        binding.settingThemeMode.setOnClickListener {
            showThemeModeDialog()
        }

        updateThemeSubtitle()
        binding.settingTheme.setOnClickListener {
            showThemeStyleDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_THEME_CHANGED, isThemeChanged)
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

    private fun prepareResult() {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_THEME_CHANGED, isThemeChanged)
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }

    override fun finish() {
        prepareResult()
        super.finish()
    }
}
