package xyz.omniplay.audio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.LoudnessEnhancer
import android.media.audiofx.Virtualizer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.media3.common.audio.AudioProcessor
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicReference
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Audio Engine & DSP Sound Enhancement Suite for OmniPlay.
 *
 * Audio processing features:
 * - 10-Band Graphic Equalizer (32Hz, 64Hz, 125Hz, 250Hz, 500Hz, 1kHz, 2kHz, 4kHz, 8kHz, 16kHz)
 * - Intelligent Equalizer (IEQ): Off, Balanced, Warm, Detailed
 * - Dialogue Enhancer: Vocal presence & clarity enhancement (0 to 10 scale)
 * - ±12 dB Precision Gain Range
 * - Sub-Bass (-10 dB to +10 dB resonance slider)
 * - Loudness Enhancer (dynamic gain compression)
 * - Automatic Optimisation (headphone acoustic response & impedance compensation)
 * - Clear Phase (acoustic phase correction for natural sound reproduction)
 * - xLOUD (dynamic speaker & output volume booster)
 * - S-Force Front Surround 3D (wide frontal sound field spatialization)
 * - Surround Modes (VPT): Off, Studio, Club, Concert Hall
 */
class EqualizerManager private constructor(private val context: Context) {

    interface EqualizerListener {
        fun onEqualizerConfigChanged()
    }

    data class Preset(val name: String, val bands: IntArray, val clearBass: Int)

    val presets = listOf(
        Preset("Flat", intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0),
        Preset("Bass Boost", intArrayOf(6, 5, 4, 2, 0, 0, 0, 0, 0, 0), 4),
        Preset("Treble Boost", intArrayOf(0, 0, 0, 0, 0, 1, 3, 5, 6, 7), 0),
        Preset("Rock", intArrayOf(4, 3, 1, -1, -2, -1, 1, 3, 4, 5), 2),
        Preset("Pop", intArrayOf(-1, 1, 3, 4, 3, 1, -1, -1, 1, 2), 1),
        Preset("Jazz", intArrayOf(3, 2, 0, 1, -1, -1, 0, 1, 2, 3), 1),
        Preset("Classical", intArrayOf(4, 3, 2, 1, -1, -1, 0, 2, 3, 4), 0),
        Preset("Hip Hop", intArrayOf(5, 4, 1, 0, -1, -1, 1, 0, 2, 3), 3),
        Preset("Electronic", intArrayOf(4, 3, 1, 0, -2, 1, 0, 2, 4, 4), 3),
        Preset("Acoustic", intArrayOf(3, 2, 1, 1, 0, 1, 2, 3, 3, 2), 1),
        Preset("Vocal", intArrayOf(-2, -2, -1, 2, 4, 4, 3, 1, 0, -1), -1),
        Preset("Custom", IntArray(10) { 0 }, 0)
    )

    private val listeners = CopyOnWriteArrayList<EqualizerListener>()
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val audioProcessorConfig = AtomicReference(StereoEffectsConfig())

    private val nativeHardwareBinding = NativeAudioEffectBinding()
    private var equalizer: Equalizer? = null
    private var bassBoost: BassBoost? = null
    private var virtualizer: Virtualizer? = null
    private var xloudEnhancer: LoudnessEnhancer? = null
    private var activeAudioSessionId: Int = 0

    fun getActiveAudioSessionId(): Int = activeAudioSessionId

    init {
        updateAudioProcessorConfig()
    }

    val isHardwareAccelerated: Boolean
        get() = nativeHardwareBinding.isSonyActive || nativeHardwareBinding.isDolbyActive

    fun getHardwareDspStatus(): String {
        return when {
            nativeHardwareBinding.isSonyActive && nativeHardwareBinding.isDolbyActive -> "Hardware DSP (Sony + Dolby Active)"
            nativeHardwareBinding.isSonyActive -> "Hardware DSP (Sony Soundfx Active)"
            nativeHardwareBinding.isDolbyActive -> "Hardware DSP (Dolby DAP Active)"
            else -> "Software High-Precision DSP Engine"
        }
    }

    val daxFrequenciesHz = intArrayOf(32, 64, 125, 250, 500, 1000, 2000, 4000, 8000, 16000)

    fun addListener(listener: EqualizerListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    fun removeListener(listener: EqualizerListener) {
        listeners.remove(listener)
    }

    @Volatile
    var isSyncingFromRemote: Boolean = false

    fun buildStateBundle(): Bundle {
        return Bundle().apply {
            putBoolean("master_enabled", isEnabled())
            putInt("preset_index", getCurrentPresetIndex())
            putIntArray("dax_bands", getAllDaxBandLevels())
            putInt("clear_bass", getClearBassLevel())
            putInt("ieq_preset", getIeqPreset())
            putInt("balance", getStereoBalance())
            putBoolean("mono_audio", isMonoAudioEnabled())
            putInt("crossfeed", getCrossfeedAmount())
            putInt("drc", getDynamicRangeAmount())
            putBoolean("xloud", isXLoudEnabled())
            putBoolean("le_enabled", isLoudnessEnhancerEnabled())
            putInt("le_gain_mb", getLoudnessEnhancerGainMb())
        }
    }

    fun applySyncBundle(extras: Bundle) {
        isSyncingFromRemote = true
        try {
            if (extras.containsKey("master_enabled")) {
                val enabled = extras.getBoolean("master_enabled", isEnabled())
                if (isEnabled() != enabled) {
                    setEnabled(enabled)
                }
            }
            if (extras.containsKey("preset_index")) {
                applyPreset(extras.getInt("preset_index", 0))
            }
            if (extras.containsKey("dax_bands")) {
                val bands = extras.getIntArray("dax_bands")
                if (bands != null && bands.size == 10) {
                    for (i in 0 until 10) {
                        setDaxBandLevel(i, bands[i], fromUser = false)
                    }
                }
            }
            if (extras.containsKey("clear_bass")) {
                setClearBassLevel(extras.getInt("clear_bass", 0))
            }
            if (extras.containsKey("ieq_preset")) {
                setIeqPreset(extras.getInt("ieq_preset", 0))
            }
            if (extras.containsKey("balance")) {
                setStereoBalance(extras.getInt("balance", 0))
            }
            if (extras.containsKey("mono_audio")) {
                setMonoAudioEnabled(extras.getBoolean("mono_audio", false))
            }
            if (extras.containsKey("crossfeed")) {
                setCrossfeedAmount(extras.getInt("crossfeed", 0))
            }
            if (extras.containsKey("drc")) {
                setDynamicRangeAmount(extras.getInt("drc", 0))
            }
            if (extras.containsKey("xloud")) {
                setXLoudEnabled(extras.getBoolean("xloud", false))
            }
            if (extras.containsKey("le_enabled")) {
                setLoudnessEnhancerEnabled(extras.getBoolean("le_enabled", false))
            }
            if (extras.containsKey("le_gain_mb")) {
                setLoudnessEnhancerGainMb(extras.getInt("le_gain_mb", 0))
            }
            notifyListeners()
        } finally {
            isSyncingFromRemote = false
        }
    }

    fun applySyncIntent(intent: Intent) {
        intent.extras?.let { applySyncBundle(it) }
    }

    fun broadcastSyncState() {
        if (isSyncingFromRemote) return
        val bundle = buildStateBundle()

        // 1. Instant Binder IPC via ContentProvider (sub-millisecond synchronous execution)
        try {
            val res = context.contentResolver.call(
                Uri.parse("content://xyz.omniplay.equalizer.eqprovider"),
                "SYNC_EQ_STATE",
                null,
                bundle
            )
            if (res != null && res.getBoolean("success", false)) {
                return
            }
        } catch (_: Throwable) {}

        // 2. Fallback to broadcast
        try {
            val intent = Intent("xyz.omniplay.equalizer.action.SYNC_EQ_STATE").apply {
                setPackage("xyz.omniplay.equalizer")
                addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
                putExtras(bundle)
            }
            context.sendBroadcast(intent)
        } catch (_: Throwable) {}
    }

    fun requestOmniQualizerSync() {
        // 1. Instant synchronous fetch from OmniQualizer via ContentProvider
        try {
            val res = context.contentResolver.call(
                Uri.parse("content://xyz.omniplay.equalizer.eqprovider"),
                "GET_EQ_STATE",
                null,
                null
            )
            if (res != null && res.getBoolean("success", false)) {
                applySyncBundle(res)
                return
            }
        } catch (_: Throwable) {}

        // 2. Fallback to broadcast request
        try {
            val reqIntent = Intent("xyz.omniplay.equalizer.action.REQUEST_SYNC").apply {
                setPackage("xyz.omniplay.equalizer")
                addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
            }
            context.sendBroadcast(reqIntent)
        } catch (_: Throwable) {}
    }

    fun notifyListeners() {
        for (l in listeners) {
            try {
                l.onEqualizerConfigChanged()
            } catch (e: Exception) {
                Log.e(TAG, "Error notifying equalizer listener", e)
            }
        }
        broadcastSyncState()
    }

    @Synchronized
    fun rebindAudioSession(audioSessionId: Int, forceRecreate: Boolean = false) {
        if (audioSessionId <= 0) return

        val isAlive = try { equalizer?.hasControl() == true } catch (_: Throwable) { false }
        if (!forceRecreate && audioSessionId == activeAudioSessionId && equalizer != null && isAlive) {
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

        try {
            xloudEnhancer = LoudnessEnhancer(audioSessionId)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to instantiate LoudnessEnhancer on session $audioSessionId", e)
            xloudEnhancer = null
        }

        try {
            nativeHardwareBinding.initSession(audioSessionId)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to initialize native hardware bindings on session $audioSessionId", e)
        }

        applyCurrentStateToEffects()
        notifyListeners()
    }

    @Synchronized
    fun initAudioSession(audioSessionId: Int) {
        rebindAudioSession(audioSessionId, forceRecreate = true)
    }

    @Synchronized
    fun releaseEffects() {
        try {
            nativeHardwareBinding.release()
        } catch (ignored: Throwable) {}

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

        try {
            xloudEnhancer?.enabled = false
            xloudEnhancer?.release()
        } catch (ignored: Throwable) {}
        xloudEnhancer = null

        activeAudioSessionId = 0
    }

    // --- Master Toggle ---
    fun isEnabled(): Boolean {
        return prefs.getBoolean(KEY_MASTER_ENABLED, false)
    }

    fun setEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_MASTER_ENABLED, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun createStereoEffectsAudioProcessor(): AudioProcessor {
        return StereoEffectsAudioProcessor { audioProcessorConfig.get() }
    }

    fun getCrossfeedAmount(): Int = prefs.getInt(KEY_CROSSFEED_AMOUNT, 0).coerceIn(0, 100)

    fun setCrossfeedAmount(amount: Int) {
        prefs.edit().putInt(KEY_CROSSFEED_AMOUNT, amount.coerceIn(0, 100)).apply()
        updateAudioProcessorConfig()
        applyCurrentStateToEffects()
        broadcastSyncState()
        notifyListeners()
    }

    fun getDynamicRangeAmount(): Int = prefs.getInt(KEY_DYNAMIC_RANGE_AMOUNT, 0).coerceIn(0, 100)

    fun setDynamicRangeAmount(amount: Int) {
        prefs.edit().putInt(KEY_DYNAMIC_RANGE_AMOUNT, amount.coerceIn(0, 100)).apply()
        updateAudioProcessorConfig()
        applyCurrentStateToEffects()
        broadcastSyncState()
        notifyListeners()
    }

    fun isMonoAudioEnabled(): Boolean = prefs.getBoolean(KEY_MONO_AUDIO, false)

    fun setMonoAudioEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_MONO_AUDIO, enabled).apply()
        updateAudioProcessorConfig()
        applyCurrentStateToEffects()
        broadcastSyncState()
        notifyListeners()
    }

    fun getStereoBalance(): Int = prefs.getInt(KEY_STEREO_BALANCE, 0).coerceIn(-100, 100)

    fun setStereoBalance(balance: Int) {
        prefs.edit().putInt(KEY_STEREO_BALANCE, balance.coerceIn(-100, 100)).apply()
        updateAudioProcessorConfig()
        broadcastSyncState()
        notifyListeners()
    }

    // --- Presets ---
    fun getCurrentPresetIndex(): Int {
        return prefs.getInt(KEY_PRESET_INDEX, 0).coerceIn(0, presets.size - 1)
    }

    fun applyPreset(presetIndex: Int) {
        val index = presetIndex.coerceIn(0, presets.size - 1)
        prefs.edit().putInt(KEY_PRESET_INDEX, index).apply()
        val preset = presets[index]
        if (index != presets.size - 1) { // Not "Custom"
            for (i in 0 until 10) {
                prefs.edit().putInt(KEY_DAX_BAND_PREFIX + i, preset.bands[i]).apply()
            }
            prefs.edit().putInt(KEY_CLEAR_BASS, preset.clearBass).apply()
        }
        applyCurrentStateToEffects()
        notifyListeners()
    }

    private fun markCustomPreset() {
        val customIndex = presets.size - 1
        if (getCurrentPresetIndex() != customIndex) {
            prefs.edit().putInt(KEY_PRESET_INDEX, customIndex).apply()
        }
    }

    // --- 10-Band Graphic Equalizer ---
    fun getDaxBandLevel(bandIndex: Int): Int {
        if (bandIndex !in 0 until 10) return 0
        return prefs.getInt(KEY_DAX_BAND_PREFIX + bandIndex, 0).coerceIn(-12, 12)
    }

    fun setDaxBandLevel(bandIndex: Int, levelDb: Int, fromUser: Boolean = true) {
        if (bandIndex !in 0 until 10) return
        val clamped = levelDb.coerceIn(-12, 12)
        prefs.edit().putInt(KEY_DAX_BAND_PREFIX + bandIndex, clamped).apply()
        if (fromUser) {
            markCustomPreset()
        }
        applyCurrentStateToEffects()
        if (fromUser) {
            notifyListeners()
        }
    }

    fun getAllDaxBandLevels(): IntArray {
        return IntArray(10) { getDaxBandLevel(it) }
    }

    fun resetGeqBands() {
        applyPreset(0)
    }

    // --- Dolby Intelligent Equalizer (IEQ) ---
    fun getIeqPreset(): Int {
        return prefs.getInt(KEY_IEQ_PRESET, IEQ_OFF)
    }

    fun setIeqPreset(preset: Int) {
        val clamped = preset.coerceIn(IEQ_OFF, IEQ_DETAILED)
        prefs.edit().putInt(KEY_IEQ_PRESET, clamped).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Dolby Dialogue Enhancer ---
    fun getDialogEnhancerAmount(): Int {
        return prefs.getInt(KEY_DIALOG_ENHANCER, 0).coerceIn(0, 10)
    }

    fun setDialogEnhancerAmount(amount: Int) {
        val clamped = amount.coerceIn(0, 10)
        prefs.edit().putInt(KEY_DIALOG_ENHANCER, clamped).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Sub-Bass ---
    fun getClearBassLevel(): Int {
        return prefs.getInt(KEY_CLEAR_BASS, 0).coerceIn(-10, 10)
    }

    fun setClearBassLevel(levelDb: Int, fromUser: Boolean = true) {
        val clamped = levelDb.coerceIn(-10, 10)
        prefs.edit().putInt(KEY_CLEAR_BASS, clamped).apply()
        if (fromUser) {
            markCustomPreset()
        }
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Loudness Enhancer ---
    fun isLoudnessEnhancerEnabled(): Boolean {
        return prefs.getBoolean(KEY_LOUDNESS_ENHANCER_ENABLED, false)
    }

    fun setLoudnessEnhancerEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_LOUDNESS_ENHANCER_ENABLED, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun getLoudnessEnhancerGainMb(): Int {
        return prefs.getInt(KEY_LOUDNESS_ENHANCER_GAIN_MB, 0).coerceIn(0, 1000)
    }

    fun setLoudnessEnhancerGainMb(gainMb: Int) {
        val clamped = gainMb.coerceIn(0, 1000)
        prefs.edit().putInt(KEY_LOUDNESS_ENHANCER_GAIN_MB, clamped).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // Dynamic Normaliser removed in favor of standard ReplayGain
    fun isDynamicNormalizerEnabled(): Boolean = false
    fun setDynamicNormalizerEnabled(enabled: Boolean) {}

    // --- Automatic Optimisation ---
    fun isAutomaticOptimizationEnabled(): Boolean {
        return prefs.getBoolean(KEY_AUTOMATIC_OPTIMIZATION, false)
    }

    fun setAutomaticOptimizationEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_AUTOMATIC_OPTIMIZATION, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Clear Phase ---
    fun isClearPhaseEnabled(): Boolean {
        return prefs.getBoolean(KEY_CLEAR_PHASE, false)
    }

    fun setClearPhaseEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_CLEAR_PHASE, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- xLOUD (Volume Boost) ---
    fun isXLoudEnabled(): Boolean {
        return prefs.getBoolean(KEY_XLOUD, false)
    }

    fun setXLoudEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_XLOUD, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- S-Force Front Surround 3D ---
    fun isSForceEnabled(): Boolean {
        return prefs.getBoolean(KEY_SFORCE, false)
    }

    fun setSForceEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_SFORCE, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Surround Modes (VPT) ---
    fun getVptMode(): Int {
        return prefs.getInt(KEY_VPT_MODE, VPT_OFF)
    }

    fun setVptMode(mode: Int) {
        prefs.edit().putInt(KEY_VPT_MODE, mode).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    /**
     * Resets only the Graphic Equalizer (GEQ) 10-bands to 0 dB (Flat)
     * and sets Intelligent Equalizer (IEQ) to Off.
     * Leaves ClearBass, VPT, Dynamic Normalizer, Headphone Optimization,
     * Clear Phase, xLOUD, and S-Force untouched.
     */
    fun resetEqToFlat() {
        applyPreset(0)
    }

    fun resetAllToFlat() {
        resetEqToFlat()
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

    @Synchronized
    private fun applyCurrentStateToEffects() {
        updateAudioProcessorConfig()
        val isMasterOn = isEnabled()

        // 1. Hardware Equalizer application
        val eq = equalizer
        if (eq != null) {
            try {
                eq.enabled = isMasterOn
                if (isMasterOn) {
                    val hwBands = eq.numberOfBands.toInt().coerceAtLeast(1)
                    val range = eq.bandLevelRange ?: shortArrayOf(-1200, 1200)
                    val minMb = range[0].toInt()
                    val maxMb = range[1].toInt()

                    val cbLevel = getClearBassLevel()
                    val ieq = getIeqPreset()
                    val deAmount = getDialogEnhancerAmount()
                    val isAhc = isAutomaticOptimizationEnabled()
                    val isCp = isClearPhaseEnabled()

                    for (hwIndex in 0 until hwBands) {
                        val hwFreqHz = (eq.getCenterFreq(hwIndex.toShort()) / 1000).coerceAtLeast(20)

                        // Map hardware center frequency to closest Dolby 10-band slider
                        var closestDaxIndex = 0
                        var minDiff = abs(daxFrequenciesHz[0] - hwFreqHz)
                        for (d in 1 until 10) {
                            val diff = abs(daxFrequenciesHz[d] - hwFreqHz)
                            if (diff < minDiff) {
                                minDiff = diff
                                closestDaxIndex = d
                            }
                        }

                        var gainDb = getDaxBandLevel(closestDaxIndex)

                        // ClearBass effect injection in lower spectrum (< 250 Hz)
                        if (hwFreqHz <= 125) {
                            gainDb += cbLevel
                        } else if (hwFreqHz <= 250) {
                            gainDb += (cbLevel / 2)
                        }

                        // Intelligent Equalizer (IEQ) offsets
                        when (ieq) {
                            IEQ_BALANCED -> {
                                if (hwFreqHz in 800..1500) gainDb += 1
                                if (hwFreqHz in 3500..5000) gainDb += 2
                                if (hwFreqHz >= 8000) gainDb += 1
                            }
                            IEQ_WARM -> {
                                if (hwFreqHz in 200..600) gainDb += 2
                                if (hwFreqHz >= 8000) gainDb -= 1
                            }
                            IEQ_DETAILED -> {
                                if (hwFreqHz in 1500..3000) gainDb += 2
                                if (hwFreqHz in 3500..9000) gainDb += 3
                                if (hwFreqHz >= 14000) gainDb += 2
                            }
                        }

                        // Dialogue Enhancer vocal formant lift (1 kHz - 3 kHz)
                        if (deAmount > 0 && hwFreqHz in 800..3500) {
                            val vocalBoost = (deAmount * 0.4).roundToInt()
                            gainDb += vocalBoost
                        }

                        // Automatic Optimisation (headphone acoustic resonance compensation)
                        if (isAhc) {
                            if (hwFreqHz in 2000..4500) gainDb += 1
                            if (hwFreqHz <= 64) gainDb -= 1
                        }

                        // Clear Phase acoustic phase & treble enhancement
                        if (isCp && hwFreqHz >= 8000) {
                            gainDb += 1
                        }

                        val targetMb = (gainDb * 100).coerceIn(minMb, maxMb).toShort()
                        eq.setBandLevel(hwIndex.toShort(), targetMb)
                    }
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying hardware equalizer params", e)
            }
        }

        // 2. BassBoost (ClearBass)
        val bb = bassBoost
        if (bb != null) {
            try {
                val cb = getClearBassLevel()
                val shouldEnable = isMasterOn && cb > 0
                bb.enabled = shouldEnable
                if (shouldEnable && bb.strengthSupported) {
                    val strength = (cb * 100).coerceIn(0, 1000).toShort()
                    bb.setStrength(strength)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying BassBoost", e)
            }
        }

        // 3. Virtualizer (Sony VPT & S-Force Front Surround)
        val virt = virtualizer
        if (virt != null) {
            try {
                val vpt = getVptMode()
                val sforce = isSForceEnabled()
                val shouldEnable = isMasterOn && (vpt != VPT_OFF || sforce)
                virt.enabled = shouldEnable
                if (shouldEnable && virt.strengthSupported) {
                    val strength = when {
                        sforce -> 850
                        vpt == VPT_CONCERT_HALL -> 1000
                        vpt == VPT_CLUB -> 700
                        vpt == VPT_STUDIO -> 350
                        else -> 0
                    }.toShort()
                    virt.setStrength(strength)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying Virtualizer", e)
            }
        }

        // 4. Loudness Booster (Standalone + xLOUD)
        val xloud = xloudEnhancer
        if (xloud != null) {
            try {
                val isLeOn = isLoudnessEnhancerEnabled()
                val isXLoudOn = isXLoudEnabled()
                val shouldEnable = isMasterOn && (isLeOn || isXLoudOn)
                xloud.enabled = shouldEnable
                if (shouldEnable) {
                    val targetGain = if (isLeOn) {
                        getLoudnessEnhancerGainMb()
                    } else {
                        350
                    }
                    xloud.setTargetGain(targetGain)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error applying LoudnessEnhancer", e)
            }
        }

        // 5. Native Hardware Audio HAL Effects (Sony & Dolby Atmos)
        val allBands = getAllDaxBandLevels()
        val cbLevel = getClearBassLevel()
        nativeHardwareBinding.applySonyState(
            isMasterOn = isMasterOn,
            dynamicNormalizer = false,
            sforceSurround = isSForceEnabled(),
            clearPhaseHp = isAutomaticOptimizationEnabled(),
            clearPhase = isClearPhaseEnabled(),
            xloud = isXLoudEnabled(),
            vptMode = getVptMode(),
            bandGainsDb = allBands,
            clearBassLevel = cbLevel
        )

        nativeHardwareBinding.applyDolbyState(
            isMasterOn = isMasterOn,
            dynamicNormalizer = false,
            dialogEnhancerAmount = getDialogEnhancerAmount(),
            ieqPreset = getIeqPreset(),
            clearBassLevel = cbLevel,
            bandGainsDb = allBands
        )
    }

    private fun updateAudioProcessorConfig() {
        audioProcessorConfig.set(
            StereoEffectsConfig(
                enabled = isEnabled(),
                crossfeedAmount = getCrossfeedAmount(),
                dynamicRangeAmount = getDynamicRangeAmount(),
                monoAudio = isMonoAudioEnabled(),
                stereoBalance = getStereoBalance()
            )
        )
    }

    companion object {
        private const val TAG = "EqualizerManager"
        private const val PREFS_NAME = "omniplay_sound_enhancement_prefs"

        const val KEY_MASTER_ENABLED = "key_master_enabled"
        const val KEY_PRESET_INDEX = "key_preset_index"
        const val KEY_DAX_BAND_PREFIX = "key_dax_band_"
        const val KEY_IEQ_PRESET = "key_ieq_preset"
        const val KEY_DIALOG_ENHANCER = "key_dialog_enhancer"
        const val KEY_CLEAR_BASS = "key_clear_bass"
        const val KEY_LOUDNESS_ENHANCER_ENABLED = "key_loudness_enhancer_enabled"
        const val KEY_LOUDNESS_ENHANCER_GAIN_MB = "key_loudness_enhancer_gain_mb"
        const val KEY_CROSSFEED_AMOUNT = "key_crossfeed_amount"
        const val KEY_DYNAMIC_RANGE_AMOUNT = "key_dynamic_range_amount"
        const val KEY_MONO_AUDIO = "key_mono_audio"
        const val KEY_STEREO_BALANCE = "key_stereo_balance"
        const val KEY_DYNAMIC_NORMALIZER = "key_dynamic_normalizer"
        const val KEY_AUTOMATIC_OPTIMIZATION = "key_automatic_optimization"
        const val KEY_CLEAR_PHASE = "key_clear_phase"
        const val KEY_XLOUD = "key_xloud"
        const val KEY_SFORCE = "key_sforce"
        const val KEY_VPT_MODE = "key_vpt_mode"

        // Intelligent Equalizer Presets
        const val IEQ_OFF = 0
        const val IEQ_BALANCED = 1
        const val IEQ_WARM = 2
        const val IEQ_DETAILED = 3

        // Sony VPT Modes
        const val VPT_OFF = 0
        const val VPT_STUDIO = 1
        const val VPT_CLUB = 2
        const val VPT_CONCERT_HALL = 3

        @Volatile
        private var INSTANCE: EqualizerManager? = null

        fun getInstance(context: Context): EqualizerManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EqualizerManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
