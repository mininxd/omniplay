package xyz.omniplay.audio

import android.content.Context
import android.content.SharedPreferences
import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.Virtualizer
import android.util.Log
import xyz.omniplay.R
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * High-performance Equalizer and DSP Audio Enhancement Manager.
 *
 * Reverse-engineered & inspired by Sony Xperia SoundEnhancement and Dolby Atmos GEQ architectures:
 * - Dynamic band discovery with intelligent spectrum interpolation (supports 5 to 10+ hardware bands).
 * - Multi-band graphical equalization with reference frequency tuning.
 * - ClearBass / Bass Boost effect management.
 * - 3D Surround sound / Spatial Virtualizer effect management.
 * - Auto-persistence of user custom bands, presets, and toggle state.
 */
class EqualizerManager private constructor(private val context: Context) {

    data class Preset(
        val id: Int,
        val nameRes: Int,
        val defaultBandsMb: IntArray // Standard 5-band reference gain in milliBels (mB)
    )

    interface EqualizerListener {
        fun onEqualizerConfigChanged()
    }

    private val listeners = CopyOnWriteArrayList<EqualizerListener>()

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private var equalizer: Equalizer? = null
    private var bassBoost: BassBoost? = null
    private var virtualizer: Virtualizer? = null
    private var activeAudioSessionId: Int = 0

    val presets = listOf(
        Preset(PRESET_FLAT, R.string.preset_flat, intArrayOf(0, 0, 0, 0, 0)),
        Preset(PRESET_ROCK, R.string.preset_rock, intArrayOf(400, 200, -200, 300, 500)),
        Preset(PRESET_POP, R.string.preset_pop, intArrayOf(-100, 200, 500, 100, -200)),
        Preset(PRESET_JAZZ, R.string.preset_jazz, intArrayOf(300, 200, -100, 200, 400)),
        Preset(PRESET_CLASSICAL, R.string.preset_classical, intArrayOf(400, 300, -200, 200, 300)),
        Preset(PRESET_HIPHOP, R.string.preset_hiphop, intArrayOf(600, 300, 0, 100, 300)),
        Preset(PRESET_ELECTRONIC, R.string.preset_electronic, intArrayOf(500, 300, 0, 200, 400)),
        Preset(PRESET_VOCAL, R.string.preset_vocal, intArrayOf(-200, 100, 400, 300, 0)),
        Preset(PRESET_BASS_BOOST, R.string.preset_bass, intArrayOf(700, 400, 0, 0, 0)),
        Preset(PRESET_TREBLE_BOOST, R.string.preset_treble, intArrayOf(0, 0, 100, 400, 800)),
        Preset(PRESET_CUSTOM, R.string.preset_custom, intArrayOf(0, 0, 0, 0, 0))
    )

    fun addListener(listener: EqualizerListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    fun removeListener(listener: EqualizerListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        for (l in listeners) {
            try {
                l.onEqualizerConfigChanged()
            } catch (e: Exception) {
                Log.e(TAG, "Error notifying equalizer listener", e)
            }
        }
    }

    @Synchronized
    fun initAudioSession(audioSessionId: Int) {
        if (audioSessionId <= 0) return
        if (audioSessionId == activeAudioSessionId && equalizer != null) {
            // Already attached
            applyCurrentStateToEffects()
            return
        }

        releaseEffects()
        activeAudioSessionId = audioSessionId

        try {
            equalizer = Equalizer(0, audioSessionId)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to instantiate Equalizer on session $audioSessionId", e)
            equalizer = null
        }

        try {
            bassBoost = BassBoost(0, audioSessionId)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to instantiate BassBoost on session $audioSessionId", e)
            bassBoost = null
        }

        try {
            virtualizer = Virtualizer(0, audioSessionId)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to instantiate Virtualizer on session $audioSessionId", e)
            virtualizer = null
        }

        applyCurrentStateToEffects()
        notifyListeners()
    }

    @Synchronized
    fun releaseEffects() {
        try {
            equalizer?.enabled = false
            equalizer?.release()
        } catch (ignored: Throwable) {}
        equalizer = null

        try {
            bassBoost?.enabled = false
            bassBoost?.release()
        } catch (ignored: Throwable) {}
        bassBoost = null

        try {
            virtualizer?.enabled = false
            virtualizer?.release()
        } catch (ignored: Throwable) {}
        virtualizer = null

        activeAudioSessionId = 0
    }

    fun isEnabled(): Boolean {
        return prefs.getBoolean(KEY_ENABLED, false)
    }

    fun setEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_ENABLED, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun getCurrentPresetId(): Int {
        return prefs.getInt(KEY_PRESET_ID, PRESET_FLAT)
    }

    fun getBandCount(): Int {
        val eq = equalizer
        if (eq != null) {
            try {
                val bands = eq.numberOfBands.toInt()
                if (bands > 0) return bands
            } catch (ignored: Throwable) {}
        }
        return 5
    }

    fun getBandCenterFreqHz(bandIndex: Int): Int {
        val eq = equalizer
        if (eq != null) {
            try {
                val mHz = eq.getCenterFreq(bandIndex.toShort())
                return mHz / 1000
            } catch (ignored: Throwable) {}
        }
        // Fallback standard 5-band center frequencies: 60Hz, 230Hz, 910Hz, 3.6kHz, 14kHz
        return when (bandIndex) {
            0 -> 60
            1 -> 230
            2 -> 910
            3 -> 3600
            4 -> 14000
            else -> 1000
        }
    }

    fun formatFrequency(freqHz: Int): String {
        return if (freqHz < 1000) {
            "$freqHz Hz"
        } else {
            val kHz = freqHz / 1000.0
            if (kHz == kHz.toInt().toDouble()) {
                "${kHz.toInt()} kHz"
            } else {
                String.format(java.util.Locale.US, "%.1f kHz", kHz)
            }
        }
    }

    fun getBandLevelRange(): Pair<Short, Short> {
        val eq = equalizer
        if (eq != null) {
            try {
                val range = eq.bandLevelRange
                if (range != null && range.size >= 2) {
                    return Pair(range[0], range[1])
                }
            } catch (ignored: Throwable) {}
        }
        return Pair((-1500).toShort(), (1500).toShort())
    }

    fun getBandLevel(bandIndex: Int): Short {
        val range = getBandLevelRange()
        val defaultLevel = 0
        val saved = prefs.getInt(KEY_BAND_LEVEL_PREFIX + bandIndex, defaultLevel)
        return saved.coerceIn(range.first.toInt(), range.second.toInt()).toShort()
    }

    fun setBandLevel(bandIndex: Int, levelMb: Short, fromUser: Boolean = true) {
        val range = getBandLevelRange()
        val clamped = levelMb.coerceIn(range.first, range.second)
        prefs.edit().putInt(KEY_BAND_LEVEL_PREFIX + bandIndex, clamped.toInt()).apply()

        if (fromUser) {
            prefs.edit().putInt(KEY_PRESET_ID, PRESET_CUSTOM).apply()
        }

        try {
            equalizer?.setBandLevel(bandIndex.toShort(), clamped)
        } catch (ignored: Throwable) {}

        if (fromUser) {
            notifyListeners()
        }
    }

    fun applyPreset(presetId: Int) {
        prefs.edit().putInt(KEY_PRESET_ID, presetId).apply()
        val preset = presets.find { it.id == presetId } ?: presets[0]

        if (presetId != PRESET_CUSTOM) {
            val numBands = getBandCount()
            val refBands = preset.defaultBandsMb
            val refCount = refBands.size

            for (i in 0 until numBands) {
                val interpolatedMb: Int = if (numBands == refCount) {
                    refBands[i]
                } else {
                    val pos = i.toFloat() / (numBands - 1).coerceAtLeast(1) * (refCount - 1)
                    val lower = floor(pos).toInt().coerceIn(0, refCount - 1)
                    val upper = ceil(pos).toInt().coerceIn(0, refCount - 1)
                    val fraction = pos - lower
                    ((1f - fraction) * refBands[lower] + fraction * refBands[upper]).roundToInt()
                }
                setBandLevel(i, interpolatedMb.toShort(), fromUser = false)
            }
        }

        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun resetToFlat() {
        applyPreset(PRESET_FLAT)
        setBassBoostStrength(0)
        setVirtualizerStrength(0)
    }

    fun getBassBoostStrength(): Short {
        return prefs.getInt(KEY_BASS_BOOST, 0).toShort()
    }

    fun setBassBoostStrength(strength: Short) {
        val clamped = strength.coerceIn(0, 1000)
        prefs.edit().putInt(KEY_BASS_BOOST, clamped.toInt()).apply()
        try {
            bassBoost?.let {
                if (it.strengthSupported) {
                    it.setStrength(clamped)
                }
            }
        } catch (ignored: Throwable) {}
        notifyListeners()
    }

    fun getVirtualizerStrength(): Short {
        return prefs.getInt(KEY_VIRTUALIZER, 0).toShort()
    }

    fun setVirtualizerStrength(strength: Short) {
        val clamped = strength.coerceIn(0, 1000)
        prefs.edit().putInt(KEY_VIRTUALIZER, clamped.toInt()).apply()
        try {
            virtualizer?.let {
                if (it.strengthSupported) {
                    it.setStrength(clamped)
                }
            }
        } catch (ignored: Throwable) {}
        notifyListeners()
    }

    @Synchronized
    private fun applyCurrentStateToEffects() {
        val isEqOn = isEnabled()

        // 1. Equalizer bands
        equalizer?.let { eq ->
            try {
                eq.enabled = isEqOn
                if (isEqOn) {
                    val bandCount = getBandCount()
                    for (i in 0 until bandCount) {
                        val level = getBandLevel(i)
                        eq.setBandLevel(i.toShort(), level)
                    }
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying Equalizer parameters", e)
            }
        }

        // 2. Bass Boost (ClearBass)
        bassBoost?.let { bb ->
            try {
                val strength = getBassBoostStrength()
                bb.enabled = isEqOn && strength > 0
                if (bb.enabled && bb.strengthSupported) {
                    bb.setStrength(strength)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying BassBoost parameters", e)
            }
        }

        // 3. Virtualizer (Surround Sound)
        virtualizer?.let { virt ->
            try {
                val strength = getVirtualizerStrength()
                virt.enabled = isEqOn && strength > 0
                if (virt.enabled && virt.strengthSupported) {
                    virt.setStrength(strength)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying Virtualizer parameters", e)
            }
        }
    }

    companion object {
        private const val TAG = "EqualizerManager"
        private const val PREFS_NAME = "omniplay_equalizer_prefs"

        const val KEY_ENABLED = "key_equalizer_enabled"
        const val KEY_PRESET_ID = "key_equalizer_preset_id"
        const val KEY_BAND_LEVEL_PREFIX = "key_band_level_"
        const val KEY_BASS_BOOST = "key_bass_boost"
        const val KEY_VIRTUALIZER = "key_virtualizer"

        const val PRESET_FLAT = 0
        const val PRESET_ROCK = 1
        const val PRESET_POP = 2
        const val PRESET_JAZZ = 3
        const val PRESET_CLASSICAL = 4
        const val PRESET_HIPHOP = 5
        const val PRESET_ELECTRONIC = 6
        const val PRESET_VOCAL = 7
        const val PRESET_BASS_BOOST = 8
        const val PRESET_TREBLE_BOOST = 9
        const val PRESET_CUSTOM = 10

        @Volatile
        private var INSTANCE: EqualizerManager? = null

        fun getInstance(context: Context): EqualizerManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EqualizerManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
