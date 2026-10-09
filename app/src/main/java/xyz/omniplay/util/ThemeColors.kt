package xyz.omniplay.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.google.android.material.color.MaterialColors
import xyz.omniplay.R

object ThemeColors {
    @ColorInt
    fun getPrimary(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorPrimary,
            ContextCompat.getColor(context, R.color.primary_accent)
        )
    }

    @ColorInt
    fun getOnPrimary(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnPrimary,
            ContextCompat.getColor(context, R.color.on_primary)
        )
    }

    @ColorInt
    fun getPrimaryContainer(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorPrimaryContainer,
            ContextCompat.getColor(context, R.color.theme_tone_primary_container)
        )
    }

    @ColorInt
    fun getOnPrimaryContainer(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnPrimaryContainer,
            ContextCompat.getColor(context, R.color.theme_tone_on_primary_container)
        )
    }

    @ColorInt
    fun getSurface(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurface,
            ContextCompat.getColor(context, R.color.surface_dark)
        )
    }

    @ColorInt
    fun getOnSurface(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnSurface,
            ContextCompat.getColor(context, R.color.text_primary)
        )
    }

    @ColorInt
    fun getOnSurfaceVariant(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnSurfaceVariant,
            ContextCompat.getColor(context, R.color.text_secondary)
        )
    }

    @ColorInt
    fun getSurfaceContainer(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurfaceContainer,
            ContextCompat.getColor(context, R.color.surface_container)
        )
    }

    @ColorInt
    fun getSurfaceContainerLow(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurfaceContainerLow,
            ContextCompat.getColor(context, R.color.surface_container)
        )
    }

    @ColorInt
    fun getSurfaceContainerHigh(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurfaceContainerHigh,
            ContextCompat.getColor(context, R.color.surface_container_high)
        )
    }

    @ColorInt
    fun getSurfaceContainerHighest(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorSurfaceContainerHighest,
            ContextCompat.getColor(context, R.color.surface_container_highest)
        )
    }

    @ColorInt
    fun getOutline(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOutline,
            ContextCompat.getColor(context, R.color.text_tertiary)
        )
    }

    @ColorInt
    fun getOutlineVariant(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOutlineVariant,
            ContextCompat.getColor(context, R.color.badge_stroke)
        )
    }

    @ColorInt
    fun getError(context: Context): Int {
        return MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorError,
            ContextCompat.getColor(context, R.color.slider_cancel_accent)
        )
    }
}
