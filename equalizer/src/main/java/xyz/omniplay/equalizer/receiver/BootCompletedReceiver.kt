package xyz.omniplay.equalizer.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.service.EqualizerService

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED ||
            intent?.action == "android.intent.action.QUICKBOOT_POWERON"
        ) {
            val eqManager = EqualizerManager.getInstance(context)
            if (eqManager.isEnabled()) {
                EqualizerService.start(context)
            }
        }
    }
}
