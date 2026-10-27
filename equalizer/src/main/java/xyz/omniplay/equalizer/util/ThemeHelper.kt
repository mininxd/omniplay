package xyz.omniplay.equalizer.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeHelper {
    fun applyTheme(context: Context) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
