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
            eqManager.isSyncingFromRemote = true
            try {
                if (intent.hasExtra("master_enabled")) {
                    val enabled = intent.getBooleanExtra("master_enabled", eqManager.isEnabled())
                    if (enabled != eqManager.isEnabled()) {
                        eqManager.setEnabled(enabled)
                    }
                }
                if (intent.hasExtra("preset_index")) {
                    eqManager.applyPreset(intent.getIntExtra("preset_index", 0))
                }
                if (intent.hasExtra("dax_bands")) {
                    val bands = intent.getIntArrayExtra("dax_bands")
                    if (bands != null && bands.size == 10) {
                        for (i in 0 until 10) {
                            eqManager.setDaxBandLevel(i, bands[i], fromUser = false)
                        }
                    }
                }
                if (intent.hasExtra("clear_bass")) {
                    eqManager.setClearBassLevel(intent.getIntExtra("clear_bass", 0))
                }
                if (intent.hasExtra("ieq_preset")) {
                    eqManager.setIeqPreset(intent.getIntExtra("ieq_preset", 0))
                }
                if (intent.hasExtra("balance")) {
                    eqManager.setStereoBalance(intent.getIntExtra("balance", 0))
                }
                if (intent.hasExtra("mono_audio")) {
                    eqManager.setMonoAudioEnabled(intent.getBooleanExtra("mono_audio", false))
                }
                if (intent.hasExtra("crossfeed")) {
                    eqManager.setCrossfeedAmount(intent.getIntExtra("crossfeed", 0))
                }
                if (intent.hasExtra("drc")) {
                    eqManager.setDynamicRangeAmount(intent.getIntExtra("drc", 0))
                }
                if (intent.hasExtra("xloud")) {
                    eqManager.setXLoudEnabled(intent.getBooleanExtra("xloud", false))
                }
                if (intent.hasExtra("le_enabled")) {
                    eqManager.setLoudnessEnhancerEnabled(intent.getBooleanExtra("le_enabled", false))
                }
                if (intent.hasExtra("le_gain_mb")) {
                    eqManager.setLoudnessEnhancerGainMb(intent.getIntExtra("le_gain_mb", 0))
                }
                eqManager.notifyListeners()
            } finally {
                eqManager.isSyncingFromRemote = false
            }
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
