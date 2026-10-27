package xyz.omniplay.equalizer.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.service.EqualizerService

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        if (action == Intent.ACTION_BOOT_COMPLETED ||
            action == "android.intent.action.QUICKBOOT_POWERON" ||
            action == Intent.ACTION_MY_PACKAGE_REPLACED
        ) {
            val eqManager = EqualizerManager.getInstance(context)
            if (eqManager.isEnabled()) {
                EqualizerService.start(context)
                try {
                    context.sendBroadcast(Intent("xyz.omniplay.action.REQUEST_AUDIO_SESSION").apply {
                        setPackage("xyz.omniplay")
                    })
                } catch (_: Throwable) {}
            }
        }
    }
}
