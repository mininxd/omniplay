package xyz.omniplay.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.android.material.color.ColorResourcesOverride
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColorUtilitiesHelper
import com.google.android.material.color.utilities.DynamicScheme
import com.google.android.material.color.utilities.Hct
import com.google.android.material.color.utilities.SchemeExpressive
import com.google.android.material.color.utilities.SchemeMonochrome
import com.google.android.material.color.utilities.SchemeTonalSpot
import com.google.android.material.color.utilities.SchemeVibrant
import com.google.android.material.color.utilities.TonalPalette
import com.google.android.material.color.utilities.Variant
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
    DYNAMIC("dynamic", R.string.theme_style_dynamic, 0),
    NEUTRAL("neutral", R.string.theme_style_neutral, R.style.ThemeOverlay_Omniplay_Neutral),
    MONOCHROME("monochrome", R.string.theme_style_monochrome, R.style.ThemeOverlay_Omniplay_Monochrome),
    TONAL_SPOT("tone", R.string.theme_style_tone, R.style.ThemeOverlay_Omniplay_Tone),
    VIBRANT("vibrant", R.string.theme_style_vibrant, R.style.ThemeOverlay_Omniplay_Vibrant),
    RAINBOW("rainbow", R.string.theme_style_rainbow, R.style.ThemeOverlay_Omniplay_Rainbow),
    EXPRESSIVE("expressive", R.string.theme_style_expressive, R.style.ThemeOverlay_Omniplay_Expressive);

    companion object {
        fun getDefault(): ThemeStyle {
            return if (DynamicColors.isDynamicColorAvailable()) DYNAMIC else NEUTRAL
        }

        fun fromKey(key: String?): ThemeStyle {
            return values().firstOrNull { it.key == key } ?: getDefault()
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

    fun getAvailableStyles(): List<ThemeStyle> {
        return if (DynamicColors.isDynamicColorAvailable()) {
            ThemeStyle.values().toList()
        } else {
            ThemeStyle.values().filter { it != ThemeStyle.DYNAMIC }
        }
    }

    fun getThemeStyle(context: Context): ThemeStyle {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val defaultKey = ThemeStyle.getDefault().key
        val key = prefs.getString(KEY_THEME_STYLE, defaultKey)
        val style = ThemeStyle.fromKey(key)
        return if (!DynamicColors.isDynamicColorAvailable() && style == ThemeStyle.DYNAMIC) {
            ThemeStyle.NEUTRAL
        } else {
            style
        }
    }

    fun setThemeStyle(context: Context, style: ThemeStyle) {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME_STYLE, style.key).apply()
    }

    fun applyTheme(activity: Activity) {
        val mode = getThemeMode(activity)
        AppCompatDelegate.setDefaultNightMode(mode.nightMode)
        val style = getThemeStyle(activity)
        val hasDynamicScheme = applyDynamicScheme(activity, style)
        if (!hasDynamicScheme && style.themeOverlayRes != 0) {
            activity.theme.applyStyle(style.themeOverlayRes, true)
        }
        applySystemBars(activity)
    }

    @SuppressLint("RestrictedApi")
    private fun applyDynamicScheme(activity: Activity, style: ThemeStyle): Boolean {
        if (style == ThemeStyle.DYNAMIC || !DynamicColors.isDynamicColorAvailable()) return false

        val sourceColor = ContextCompat.getColor(activity, R.color.primary_accent)
        val sourceColorHct = Hct.fromInt(sourceColor)
        val isDark = isNightMode(activity)
        val scheme: DynamicScheme = when (style) {
            ThemeStyle.DYNAMIC -> return false
            ThemeStyle.NEUTRAL -> createSpritzScheme(sourceColorHct, isDark)
            ThemeStyle.MONOCHROME -> SchemeMonochrome(sourceColorHct, isDark, 0.0)
            ThemeStyle.TONAL_SPOT -> SchemeTonalSpot(sourceColorHct, isDark, 0.0)
            ThemeStyle.VIBRANT -> SchemeVibrant(sourceColorHct, isDark, 0.0)
            ThemeStyle.RAINBOW -> createRainbowScheme(sourceColorHct, isDark)
            ThemeStyle.EXPRESSIVE -> SchemeExpressive(sourceColorHct, isDark, 0.0)
        }
        val colorResourcesOverride = ColorResourcesOverride.getInstance() ?: return false
        val colorValues = MaterialColorUtilitiesHelper.createColorResourcesIdsToColorValues(scheme)
        return colorResourcesOverride.applyIfPossible(activity, colorValues)
    }

    @SuppressLint("RestrictedApi")
    private fun createSpritzScheme(source: Hct, isDark: Boolean): DynamicScheme {
        val hue = source.getHue()
        val neutralHue = rotateHue(hue, 24.0)
        return DynamicScheme(
            source,
            Variant.FRUIT_SALAD,
            isDark,
            0.0,
            TonalPalette.fromHueAndChroma(rotateHue(hue, 12.0), 56.0),
            TonalPalette.fromHueAndChroma(rotateHue(hue, 42.0), 40.0),
            TonalPalette.fromHueAndChroma(rotateHue(hue, 155.0), 38.0),
            TonalPalette.fromHueAndChroma(neutralHue, 10.0),
            TonalPalette.fromHueAndChroma(neutralHue, 16.0)
        )
    }

    @SuppressLint("RestrictedApi")
    private fun createRainbowScheme(source: Hct, isDark: Boolean): DynamicScheme {
        val hue = source.getHue()
        return DynamicScheme(
            source,
            Variant.RAINBOW,
            isDark,
            0.0,
            TonalPalette.fromHueAndChroma(hue, 48.0),
            TonalPalette.fromHueAndChroma(rotateHue(hue, 120.0), 44.0),
            TonalPalette.fromHueAndChroma(rotateHue(hue, 240.0), 48.0),
            TonalPalette.fromHueAndChroma(hue, 8.0),
            TonalPalette.fromHueAndChroma(hue, 14.0)
        )
    }

    private fun rotateHue(hue: Double, degrees: Double): Double = (hue + degrees) % 360.0

    fun applySystemBars(activity: Activity) {
        if (activity.javaClass.simpleName == "VideoPlayerActivity") return
        val isLight = !isNightMode(activity)
        val insetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        insetsController.isAppearanceLightStatusBars = isLight
        insetsController.isAppearanceLightNavigationBars = isLight
    }
}
