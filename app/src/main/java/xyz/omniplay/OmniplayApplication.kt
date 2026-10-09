package xyz.omniplay

import android.app.Application
import com.google.android.material.color.DynamicColors
import xyz.omniplay.util.ThemeHelper

class OmniplayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ThemeHelper.applyNightMode(this)
        if (DynamicColors.isDynamicColorAvailable()) {
            DynamicColors.applyToActivitiesIfAvailable(this)
        }
    }
}
