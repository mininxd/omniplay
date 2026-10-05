package xyz.omniplay.util

import android.app.Activity
import android.content.Context
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import xyz.omniplay.R
import xyz.omniplay.ui.MainActivity

enum class ThemeStyle(
    val key: String,
    @StringRes val titleRes: Int,
    @StyleRes val themeOverlayRes: Int
) {
    TONE("tone", R.string.theme_style_tone, R.style.ThemeOverlay_Omniplay_Tone),
    EXPRESSIVE("expressive", R.string.theme_style_expressive, R.style.ThemeOverlay_Omniplay_Expressive),
    SALAD("salad", R.string.theme_style_salad, R.style.ThemeOverlay_Omniplay_Salad),
    MONOCHROME("monochrome", R.string.theme_style_monochrome, R.style.ThemeOverlay_Omniplay_Monochrome),
    VIBRANT("vibrant", R.string.theme_style_vibrant, R.style.ThemeOverlay_Omniplay_Vibrant);

    companion object {
        fun fromKey(key: String?): ThemeStyle {
            return values().firstOrNull { it.key == key } ?: TONE
        }
    }
}

object ThemeHelper {
    const val KEY_THEME_STYLE = "key_theme_style"

    fun getThemeStyle(context: Context): ThemeStyle {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        return ThemeStyle.fromKey(prefs.getString(KEY_THEME_STYLE, ThemeStyle.TONE.key))
    }

    fun setThemeStyle(context: Context, style: ThemeStyle) {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME_STYLE, style.key).apply()
    }

    fun applyTheme(activity: Activity) {
        val style = getThemeStyle(activity)
        if (style.themeOverlayRes != 0) {
            activity.theme.applyStyle(style.themeOverlayRes, true)
        }
    }
}
