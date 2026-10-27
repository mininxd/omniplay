package xyz.omniplay.equalizer

import android.app.Application
import com.google.android.material.color.DynamicColors
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.service.EqualizerService

class EqualizerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)

        val eqManager = EqualizerManager.getInstance(this)
        if (eqManager.isEnabled()) {
            EqualizerService.start(this)
        }
    }
}
