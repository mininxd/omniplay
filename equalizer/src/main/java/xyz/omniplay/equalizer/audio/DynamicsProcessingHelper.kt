package xyz.omniplay.equalizer.audio

import android.media.audiofx.DynamicsProcessing
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlin.math.log10

/**
 * Handles per-channel audio input gain for Stereo Balance via Android's
 * DynamicsProcessing AudioEffect (API 28+).
 */
@RequiresApi(Build.VERSION_CODES.P)
class DynamicsProcessingHelper(val sessionId: Int) {

    private var dp: DynamicsProcessing? = null

    init {
        if (sessionId != 0) {
            try {
                val config = DynamicsProcessing.Config.Builder(
                    DynamicsProcessing.VARIANT_FAVOR_FREQUENCY_RESOLUTION,
                    2, // Stereo (2 channels)
                    false, 0, // pre-eq
                    false, 0, // mbc
                    false, 0, // post-eq
                    false     // limiter
                ).build()
                dp = DynamicsProcessing(0, sessionId, config)
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to instantiate DynamicsProcessing on session $sessionId", e)
            }
        }
    }

    fun applyBalance(balance: Int) {
        val effect = dp ?: return
        try {
            val clamped = balance.coerceIn(-100, 100)
            if (clamped == 0) {
                effect.enabled = false
                return
            }
            effect.enabled = true
            val (leftDb, rightDb) = calculateChannelGainsDb(clamped)
            effect.setInputGainByChannelIndex(0, leftDb)
            effect.setInputGainByChannelIndex(1, rightDb)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to apply balance to DynamicsProcessing on session $sessionId", e)
        }
    }

    fun release() {
        try {
            dp?.enabled = false
            dp?.release()
        } catch (_: Throwable) {}
        dp = null
    }

    companion object {
        private const val TAG = "DynamicsProcessing"

        fun calculateChannelGainsDb(balance: Int): Pair<Float, Float> {
            val clamped = balance.coerceIn(-100, 100)
            return when {
                clamped < 0 -> {
                    // Panned left: Left channel full (0 dB), right channel attenuated
                    val factor = (100 + clamped) / 100.0
                    val rightAtten = if (factor <= 0.001) -90f else (20.0 * log10(factor)).toFloat().coerceIn(-90f, 0f)
                    0f to rightAtten
                }
                clamped > 0 -> {
                    // Panned right: Right channel full (0 dB), left channel attenuated
                    val factor = (100 - clamped) / 100.0
                    val leftAtten = if (factor <= 0.001) -90f else (20.0 * log10(factor)).toFloat().coerceIn(-90f, 0f)
                    leftAtten to 0f
                }
                else -> 0f to 0f
            }
        }
    }
}
