package xyz.omniplay.equalizer.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.audiofx.AudioEffect
import android.util.Log
import xyz.omniplay.equalizer.audio.EqualizerManager

class AudioSessionReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "AudioSessionReceiver"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        val eqManager = EqualizerManager.getInstance(context)

        if (action == "xyz.omniplay.equalizer.action.REQUEST_SYNC") {
            Log.d(TAG, "Received REQUEST_SYNC, broadcasting current OmniQualizer state")
            eqManager.broadcastStereoEffects()
            return
        }

        if (action == "xyz.omniplay.equalizer.action.SYNC_EQ_STATE") {
            Log.d(TAG, "Received SYNC_EQ_STATE from OmniPlay")
            intent.extras?.let { eqManager.applySyncBundle(it) }
            return
        }

        val sessionId = intent.getIntExtra(AudioEffect.EXTRA_AUDIO_SESSION, AudioEffect.ERROR)
        val packageName = intent.getStringExtra(AudioEffect.EXTRA_PACKAGE_NAME)

        if (sessionId == AudioEffect.ERROR) {
            return
        }

        when (action) {
            AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION -> {
                val cleanPkg = packageName?.substringBefore(':')?.trim()
                Log.d(TAG, "Opening audio session $sessionId for $packageName")
                eqManager.openAudioSession(sessionId, packageName)
                if (cleanPkg == "xyz.omniplay") {
                    eqManager.broadcastStereoEffects()
                }
                if (eqManager.isEnabled()) {
                    xyz.omniplay.equalizer.service.EqualizerService.start(context)
                }
            }
            AudioEffect.ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION -> {
                Log.d(TAG, "Closing audio session $sessionId for $packageName")
                eqManager.closeAudioSession(sessionId)
            }
        }
    }
}
