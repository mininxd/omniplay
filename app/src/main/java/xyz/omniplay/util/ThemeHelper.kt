package xyz.omniplay.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import xyz.omniplay.R
import xyz.omniplay.ui.MainActivity

enum class ThemeMode(
    val key: String,
    @StringRes val titleRes: Int,
    val nightMode: Int
) {
    AUTO("auto", R.string.theme_mode_auto, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("light", R.string.theme_mode_light, AppCompatDelegate.MODE_NIGHT_NO),
    DARK("dark", R.string.theme_mode_dark, AppCompatDelegate.MODE_NIGHT_YES);

    companion object {
        fun fromKey(key: String?): ThemeMode {
            return values().firstOrNull { it.key == key } ?: AUTO
        }
    }
}

enum class ThemeStyle(
    val key: String,
    @StringRes val titleRes: Int,
    @StyleRes val themeOverlayRes: Int
) {
    EXPRESSIVE("expressive", R.string.theme_style_expressive, R.style.ThemeOverlay_Omniplay_Expressive),
    TONE("tone", R.string.theme_style_tone, R.style.ThemeOverlay_Omniplay_Tone),
    SALAD("salad", R.string.theme_style_salad, R.style.ThemeOverlay_Omniplay_Salad),
    MONOCHROME("monochrome", R.string.theme_style_monochrome, R.style.ThemeOverlay_Omniplay_Monochrome),
    VIBRANT("vibrant", R.string.theme_style_vibrant, R.style.ThemeOverlay_Omniplay_Vibrant);

    companion object {
        fun fromKey(key: String?): ThemeStyle {
            return values().firstOrNull { it.key == key } ?: EXPRESSIVE
        }
    }
}

object ThemeHelper {
    const val KEY_THEME_MODE = "key_theme_mode"
    const val KEY_THEME_STYLE = "key_theme_style"

    fun getThemeMode(context: Context): ThemeMode {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        return ThemeMode.fromKey(prefs.getString(KEY_THEME_MODE, ThemeMode.AUTO.key))
    }

    fun setThemeMode(context: Context, mode: ThemeMode) {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME_MODE, mode.key).apply()
        AppCompatDelegate.setDefaultNightMode(mode.nightMode)
    }

    fun applyNightMode(context: Context) {
        val mode = getThemeMode(context)
        AppCompatDelegate.setDefaultNightMode(mode.nightMode)
    }

    fun isNightMode(context: Context): Boolean {
        return when (getThemeMode(context)) {
            ThemeMode.LIGHT -> false
            ThemeMode.DARK -> true
            ThemeMode.AUTO -> {
                val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                nightModeFlags == Configuration.UI_MODE_NIGHT_YES
            }
        }
    }

    fun getThemeStyle(context: Context): ThemeStyle {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        return ThemeStyle.fromKey(prefs.getString(KEY_THEME_STYLE, ThemeStyle.EXPRESSIVE.key))
    }

    fun setThemeStyle(context: Context, style: ThemeStyle) {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME_STYLE, style.key).apply()
    }

    fun applyTheme(activity: Activity) {
        val mode = getThemeMode(activity)
        AppCompatDelegate.setDefaultNightMode(mode.nightMode)
        val style = getThemeStyle(activity)
        if (style.themeOverlayRes != 0) {
            activity.theme.applyStyle(style.themeOverlayRes, true)
        }
        applySystemBars(activity)
    }

    fun applySystemBars(activity: Activity) {
        if (activity.javaClass.simpleName == "VideoPlayerActivity") return
        val isLight = !isNightMode(activity)
        val insetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        insetsController.isAppearanceLightStatusBars = isLight
        insetsController.isAppearanceLightNavigationBars = isLight
    }
}
