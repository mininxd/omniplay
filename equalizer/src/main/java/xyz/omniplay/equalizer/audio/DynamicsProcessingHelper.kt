package xyz.omniplay.equalizer.audio

import android.media.audiofx.DynamicsProcessing
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlin.math.log10

/**
 * Handles per-channel audio gain for Stereo Balance and Limiter for Dynamic Range Control
 * via Android's DynamicsProcessing AudioEffect (API 28+).
 */
@RequiresApi(Build.VERSION_CODES.P)
class DynamicsProcessingHelper(val sessionId: Int) {

    private var dp: DynamicsProcessing? = null

    init {
        try {
            val config = DynamicsProcessing.Config.Builder(
                DynamicsProcessing.VARIANT_FAVOR_FREQUENCY_RESOLUTION,
                2,
                false, 0,
                false, 0,
                false, 0,
                true // Limiter enabled in schema
            ).build()
            dp = DynamicsProcessing(1000, sessionId, config)
        } catch (_: Throwable) {
            try {
                val config = DynamicsProcessing.Config.Builder(
                    DynamicsProcessing.VARIANT_FAVOR_FREQUENCY_RESOLUTION,
                    2,
                    false, 0,
                    false, 0,
                    false, 0,
                    true
                ).build()
                dp = DynamicsProcessing(0, sessionId, config)
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to instantiate DynamicsProcessing on session $sessionId", e)
            }
        }
    }

    fun applyEffects(balance: Int, drcAmount: Int) {
        val effect = dp ?: return
        try {
            val shouldEnable = (balance != 0 || drcAmount > 0)
            if (!shouldEnable) {
                effect.enabled = false
                return
            }
            effect.enabled = true
            val (leftDb, rightDb) = when {
                balance < 0 -> {
                    // Panned left: left at 0 dB, right attenuated
                    val factor = (100 + balance) / 100f
                    val rightAtten = if (factor <= 0.001f) -90f else (20f * log10(factor.toDouble())).toFloat().coerceIn(-90f, 0f)
                    0f to rightAtten
                }
                balance > 0 -> {
                    // Panned right: right at 0 dB, left attenuated
                    val factor = (100 - balance) / 100f
                    val leftAtten = if (factor <= 0.001f) -90f else (20f * log10(factor.toDouble())).toFloat().coerceIn(-90f, 0f)
                    leftAtten to 0f
                }
                else -> 0f to 0f
            }
            effect.setInputGainbyChannel(0, leftDb)
            effect.setInputGainbyChannel(1, rightDb)

            if (drcAmount > 0) {
                try {
                    val limiter = effect.getLimiterByChannelIndex(0)
                    if (limiter != null) {
                        limiter.isEnabled = true
                        limiter.setThreshold(-((drcAmount / 100f) * 18f))
                        limiter.setRatio(1f + (drcAmount / 10f))
                        effect.setLimiterAllChannelsTo(limiter)
                    }
                } catch (_: Throwable) {}
            } else {
                try {
                    val limiter = effect.getLimiterByChannelIndex(0)
                    if (limiter != null && limiter.isEnabled) {
                        limiter.isEnabled = false
                        effect.setLimiterAllChannelsTo(limiter)
                    }
                } catch (_: Throwable) {}
            }
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to apply effects to DynamicsProcessing on session $sessionId", e)
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
    }
}
