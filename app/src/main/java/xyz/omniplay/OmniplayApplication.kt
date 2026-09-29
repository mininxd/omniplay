package xyz.omniplay

import android.app.Application
import com.google.android.material.color.DynamicColors

class OmniplayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (DynamicColors.isDynamicColorAvailable()) {
            DynamicColors.applyToActivitiesIfAvailable(this)
        }
    }
}
