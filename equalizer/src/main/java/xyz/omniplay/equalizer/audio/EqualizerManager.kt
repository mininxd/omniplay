package xyz.omniplay.equalizer.audio

import android.content.Context
import android.content.SharedPreferences
import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.media.audiofx.LoudnessEnhancer
import android.media.audiofx.Virtualizer
import android.util.Log
import xyz.omniplay.equalizer.service.EqualizerService
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * High-Precision Audio Engine & DSP Sound Enhancement Suite for OmniQualizer.
 *
 * Core audio features:
 * - 10-Band Graphic Equalizer (32Hz, 64Hz, 125Hz, 250Hz, 500Hz, 1kHz, 2kHz, 4kHz, 8kHz, 16kHz)
 * - Intelligent Equalizer (IEQ): Off, Balanced, Warm, Detailed
 * - Dialogue Enhancer: Vocal presence & clarity enhancement (0 to 10 scale)
 * - ±12 dB Precision Gain Range
 * - Sub-Bass (-10 dB to +10 dB resonance slider)
 * - Loudness Enhancer (ITU-R BS.1770 dynamic gain compression)
 * - Automatic Optimisation (headphone acoustic response & impedance compensation)
 * - Clear Phase (acoustic phase correction for natural sound reproduction)
 * - xLOUD (dynamic speaker & output volume booster)
 * - S-Force Front Surround 3D (wide frontal sound field spatialization)
 * - Surround Modes (VPT): Off, Studio, Club, Concert Hall
 *
 * Multi-session architecture:
 * - Session 0: Global mix audio effect (system-wide equalization).
 * - Per-player sessions: Dynamically attached when apps send OPEN_AUDIO_EFFECT_CONTROL_SESSION.
 */
class EqualizerManager private constructor(private val context: Context) {

    interface EqualizerListener {
        fun onEqualizerConfigChanged()
    }

    data class Preset(val name: String, val bands: IntArray, val clearBass: Int)

    private class SessionAudioEffects(val sessionId: Int, var packageName: String? = null) {
        var equalizer: Equalizer? = null
        var bassBoost: BassBoost? = null
        var virtualizer: Virtualizer? = null
        var loudnessEnhancer: LoudnessEnhancer? = null
        val nativeHardware = NativeAudioEffectBinding()

        init {
            try {
                equalizer = Equalizer(1000, sessionId)
            } catch (e: Throwable) {
                try {
                    equalizer = Equalizer(0, sessionId)
                } catch (e2: Throwable) {
                    Log.w(TAG, "Failed to instantiate Equalizer on session $sessionId", e2)
                }
            }

            try {
                bassBoost = BassBoost(1000, sessionId)
            } catch (e: Throwable) {
                try {
                    bassBoost = BassBoost(0, sessionId)
                } catch (e2: Throwable) {
                    Log.w(TAG, "Failed to instantiate BassBoost on session $sessionId", e2)
                }
            }

            try {
                virtualizer = Virtualizer(1000, sessionId)
            } catch (e: Throwable) {
                try {
                    virtualizer = Virtualizer(0, sessionId)
                } catch (e2: Throwable) {
                    Log.w(TAG, "Failed to instantiate Virtualizer on session $sessionId", e2)
                }
            }

            if (sessionId != 0) {
                try {
                    loudnessEnhancer = LoudnessEnhancer(sessionId)
                } catch (e: Throwable) {
                    Log.w(TAG, "Failed to instantiate LoudnessEnhancer on session $sessionId", e)
                }
            }

            try {
                nativeHardware.initSession(sessionId)
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to init native hardware on session $sessionId", e)
            }
        }

        fun release() {
            try { nativeHardware.release() } catch (_: Throwable) {}
            try { equalizer?.enabled = false; equalizer?.release() } catch (_: Throwable) {}
            try { bassBoost?.enabled = false; bassBoost?.release() } catch (_: Throwable) {}
            try { virtualizer?.enabled = false; virtualizer?.release() } catch (_: Throwable) {}
            try { loudnessEnhancer?.enabled = false; loudnessEnhancer?.release() } catch (_: Throwable) {}
            equalizer = null
            bassBoost = null
            virtualizer = null
            loudnessEnhancer = null
        }
    }

    private val listeners = CopyOnWriteArrayList<EqualizerListener>()
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val activeSessions = ConcurrentHashMap<Int, SessionAudioEffects>()

    val daxFrequenciesHz = intArrayOf(32, 64, 125, 250, 500, 1000, 2000, 4000, 8000, 16000)

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

    init {
        // Automatically bind global mix session (session 0)
        bindSession(0)
        applyCurrentStateToEffects()
    }

    val isHardwareAccelerated: Boolean
        get() {
            return activeSessions.values.any { it.nativeHardware.isSonyActive || it.nativeHardware.isDolbyActive }
        }

    fun getHardwareDspStatus(): String {
        return if (isHardwareAccelerated) "Hardware DSP Audio Engine" else "High-Precision DSP Audio Engine"
    }

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
    fun openAudioSession(sessionId: Int, packageName: String? = null) {
        val cleanPkg = packageName?.substringBefore(':')?.trim()
        if (cleanPkg == "xyz.omniplay" && !isOmniPlaySupported(context)) {
            Log.i(TAG, "Skipping session $sessionId from unsupported OmniPlay version (< 0.17.0)")
            return
        }
        Log.i(TAG, "Opening audio session: $sessionId from $packageName (active sessions: ${activeSessions.size})")
        bindSession(sessionId, packageName)
        applyCurrentStateToEffects()

        if (isEnabled()) {
            EqualizerService.start(context)
        }
        notifyListeners()
    }

    @Synchronized
    fun closeAudioSession(sessionId: Int) {
        if (sessionId == 0) return // Preserve global session
        Log.i(TAG, "Closing audio session: $sessionId")
        val effects = activeSessions.remove(sessionId)
        effects?.release()
        if (effects != null) {
            notifyListeners()
        }
    }

    @Synchronized
    fun bindSession(sessionId: Int, packageName: String? = null) {
        val cleanPkg = packageName?.substringBefore(':')?.trim()
        if (cleanPkg == "xyz.omniplay" && !isOmniPlaySupported(context)) {
            Log.i(TAG, "Skipping bindSession $sessionId from unsupported OmniPlay version (< 0.17.0)")
            return
        }
        if (activeSessions.containsKey(sessionId)) {
            val existing = activeSessions[sessionId]
            if (packageName != null) {
                existing?.packageName = packageName
            }
            if (existing != null && (existing.equalizer != null || existing.bassBoost != null || existing.loudnessEnhancer != null)) {
                return
            }
            existing?.release()
            activeSessions.remove(sessionId)
        }
        if (activeSessions.size > 16) {
            val oldestKey = activeSessions.keys.firstOrNull { it != 0 && it != sessionId }
            if (oldestKey != null) {
                activeSessions.remove(oldestKey)?.release()
            }
        }
        val effects = SessionAudioEffects(sessionId, packageName)
        activeSessions[sessionId] = effects
    }

    data class ActiveSessionInfo(
        val sessionId: Int,
        val packageName: String?,
        val displayName: String,
        val isGlobal: Boolean
    )

    fun getActiveSessionList(): List<ActiveSessionInfo> {
        val pm = context.packageManager
        return activeSessions.entries.map { (sessionId, effects) ->
            val pkg = effects.packageName
            val cleanPkg = pkg?.substringBefore(':')?.trim()
            val isGlobal = (sessionId == 0)
            val name = when {
                isGlobal -> "Global Audio Mix"
                !cleanPkg.isNullOrBlank() && cleanPkg != "playback_callback" && cleanPkg != "dumpsys_scan" && cleanPkg != "keep_alive_scan" -> {
                    try {
                        val appInfo = pm.getApplicationInfo(cleanPkg, 0)
                        val label = pm.getApplicationLabel(appInfo).toString()
                        if (label.isNotBlank()) label else cleanPkg
                    } catch (_: Exception) {
                        cleanPkg
                    }
                }
                cleanPkg == "playback_callback" -> "Media Stream"
                cleanPkg == "dumpsys_scan" -> "Detected Audio Player"
                else -> "Media Player"
            }
            ActiveSessionInfo(
                sessionId = sessionId,
                packageName = cleanPkg,
                displayName = name,
                isGlobal = isGlobal
            )
        }.sortedBy { if (it.isGlobal) 0 else 1 }
    }

    @Synchronized
    fun releaseAllEffects() {
        for ((_, effects) in activeSessions) {
            effects.release()
        }
        activeSessions.clear()
    }

    @Synchronized
    fun ensureGlobalSession() {
        val session0 = activeSessions[0]
        if (session0 == null || (session0.equalizer == null && session0.bassBoost == null && session0.loudnessEnhancer == null)) {
            Log.d(TAG, "Re-binding global session 0 (was lost or dead)")
            activeSessions.remove(0)?.release()
            val effects = SessionAudioEffects(0)
            activeSessions[0] = effects
            applyCurrentStateToEffects()
        } else {
            try {
                val eq = session0.equalizer
                if (eq != null && isEnabled()) {
                    if (!eq.enabled || !eq.hasControl()) {
                        eq.enabled = true
                    }
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Global session 0 equalizer is dead, rebinding", e)
                activeSessions.remove(0)?.release()
                val effects = SessionAudioEffects(0)
                activeSessions[0] = effects
                applyCurrentStateToEffects()
            }
        }
    }

    fun getActiveSessionCount(): Int = activeSessions.size

    // --- Master Toggle ---
    fun isEnabled(): Boolean {
        return prefs.getBoolean(KEY_MASTER_ENABLED, true)
    }

    fun setEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_MASTER_ENABLED, enabled).apply()
        if (enabled) {
            if (!activeSessions.containsKey(0)) {
                bindSession(0)
            }
            EqualizerService.start(context)
        } else {
            EqualizerService.stop(context)
        }
        applyCurrentStateToEffects()
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

    fun resetEqToFlat() {
        applyPreset(0) // Apply Flat preset
    }

    // --- Intelligent Equalizer (IEQ) ---
    fun getIeqPreset(): Int {
        return prefs.getInt(KEY_IEQ_PRESET, IEQ_OFF)
    }

    fun setIeqPreset(preset: Int) {
        val clamped = preset.coerceIn(IEQ_OFF, IEQ_DETAILED)
        prefs.edit().putInt(KEY_IEQ_PRESET, clamped).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Sub-Bass ---
    fun getClearBassLevel(): Int {
        return prefs.getInt(KEY_CLEAR_BASS, 0).coerceIn(-10, 10)
    }

    fun setClearBassLevel(levelDb: Int) {
        val clamped = levelDb.coerceIn(-10, 10)
        prefs.edit().putInt(KEY_CLEAR_BASS, clamped).apply()
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

    // --- Volume Boost ---
    fun isXLoudEnabled(): Boolean {
        return prefs.getBoolean(KEY_XLOUD, false)
    }

    fun setXLoudEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_XLOUD, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    // --- Playback Effects ---
    fun getCrossfeedAmount(): Int = prefs.getInt(KEY_CROSSFEED_AMOUNT, 0).coerceIn(0, 100)

    fun setCrossfeedAmount(amount: Int) {
        prefs.edit().putInt(KEY_CROSSFEED_AMOUNT, amount.coerceIn(0, 100)).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun getDynamicRangeAmount(): Int = prefs.getInt(KEY_DYNAMIC_RANGE_AMOUNT, 0).coerceIn(0, 100)

    fun setDynamicRangeAmount(amount: Int) {
        prefs.edit().putInt(KEY_DYNAMIC_RANGE_AMOUNT, amount.coerceIn(0, 100)).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun isMonoAudioEnabled(): Boolean = prefs.getBoolean(KEY_MONO_AUDIO, false)

    fun setMonoAudioEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_MONO_AUDIO, enabled).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    fun getStereoBalance(): Int = prefs.getInt(KEY_STEREO_BALANCE, 0).coerceIn(-100, 100)

    fun setStereoBalance(balance: Int) {
        prefs.edit().putInt(KEY_STEREO_BALANCE, balance.coerceIn(-100, 100)).apply()
        applyCurrentStateToEffects()
        notifyListeners()
    }

    @Synchronized
    fun applyCurrentStateToEffects() {
        val isMasterOn = isEnabled()
        val cbLevel = getClearBassLevel()
        val isLeEnabled = isLoudnessEnhancerEnabled()
        val leGainMb = getLoudnessEnhancerGainMb()
        val ieq = getIeqPreset()
        val isXloud = isXLoudEnabled()

        val daxLevels = getAllDaxBandLevels()

        for ((_, sessionEffects) in activeSessions) {
            val eq = sessionEffects.equalizer
            if (eq != null) {
                try {
                    eq.enabled = isMasterOn
                    if (isMasterOn) {
                        val hwBands = eq.numberOfBands.toInt().coerceAtLeast(1)
                        val range = eq.bandLevelRange ?: shortArrayOf(-1200, 1200)
                        val minMb = range[0].toInt()
                        val maxMb = range[1].toInt()

                        for (hwIndex in 0 until hwBands) {
                            val hwFreqHz = (eq.getCenterFreq(hwIndex.toShort()) / 1000).coerceAtLeast(20)

                            var closestDaxIndex = 0
                            var minDiff = abs(daxFrequenciesHz[0] - hwFreqHz)
                            for (d in 1 until 10) {
                                val diff = abs(daxFrequenciesHz[d] - hwFreqHz)
                                if (diff < minDiff) {
                                    minDiff = diff
                                    closestDaxIndex = d
                                }
                            }

                            val freqRange = try { eq.getBandFreqRange(hwIndex.toShort()) } catch (_: Throwable) { null }
                            val gainDb = if (freqRange != null && freqRange.size == 2 && freqRange[1] > freqRange[0]) {
                                val lowHz = freqRange[0] / 1000
                                val highHz = freqRange[1] / 1000
                                var sumGain = 0
                                var count = 0
                                for (d in 0 until 10) {
                                    if (daxFrequenciesHz[d] in lowHz..highHz) {
                                        sumGain += daxLevels[d]
                                        count++
                                    }
                                }
                                if (count > 0) {
                                    (sumGain.toFloat() / count).roundToInt()
                                } else {
                                    daxLevels[closestDaxIndex]
                                }
                            } else {
                                daxLevels[closestDaxIndex]
                            }

                            var finalGainDb = gainDb

                            if (hwFreqHz <= 125) {
                                finalGainDb += cbLevel
                            } else if (hwFreqHz <= 250) {
                                finalGainDb += (cbLevel / 2)
                            }

                            // If LoudnessEnhancer is unavailable for this session (e.g. Session 0),
                            // apply Volume Boost (+3 dB) and Loudness Enhancer gain directly via Graphic EQ
                            if (sessionEffects.loudnessEnhancer == null) {
                                if (isXloud) {
                                    finalGainDb += 3
                                }
                                if (isLeEnabled && leGainMb > 0) {
                                    finalGainDb += (leGainMb / 100)
                                }
                            }

                            when (ieq) {
                                IEQ_BALANCED -> {
                                    if (hwFreqHz in 800..1500) finalGainDb += 1
                                    if (hwFreqHz in 3500..5000) finalGainDb += 2
                                    if (hwFreqHz >= 8000) finalGainDb += 1
                                }
                                IEQ_WARM -> {
                                    if (hwFreqHz in 200..600) finalGainDb += 2
                                    if (hwFreqHz >= 8000) finalGainDb -= 1
                                }
                                IEQ_DETAILED -> {
                                    if (hwFreqHz in 1500..3000) finalGainDb += 2
                                    if (hwFreqHz in 3500..9000) finalGainDb += 3
                                    if (hwFreqHz >= 14000) finalGainDb += 2
                                }
                            }

                            val millibels = (finalGainDb * 100).coerceIn(minMb, maxMb).toShort()
                            eq.setBandLevel(hwIndex.toShort(), millibels)
                        }
                    }
                } catch (e: Throwable) {
                    Log.w(TAG, "Failed to apply EQ settings to session ${sessionEffects.sessionId}", e)
                }
            }

            // BassBoost
            val bb = sessionEffects.bassBoost
            if (bb != null) {
                try {
                    val shouldEnable = isMasterOn && cbLevel > 0
                    bb.enabled = shouldEnable
                    if (shouldEnable) {
                        val strength = ((cbLevel / 10f) * 1000).roundToInt().coerceIn(0, 1000).toShort()
                        if (bb.strengthSupported) {
                            bb.setStrength(strength)
                        }
                    }
                } catch (e: Throwable) {
                    Log.w(TAG, "Failed to apply BassBoost to session ${sessionEffects.sessionId}", e)
                }
            }

            // Virtualizer
            val virt = sessionEffects.virtualizer
            if (virt != null) {
                try {
                    virt.enabled = false
                } catch (e: Throwable) {
                    Log.w(TAG, "Failed to disable Virtualizer for session ${sessionEffects.sessionId}", e)
                }
            }

            // LoudnessEnhancer (Standalone + Volume Boost integration)
            val le = sessionEffects.loudnessEnhancer
            if (le != null) {
                try {
                    val shouldEnable = isMasterOn && (isLeEnabled || isXloud)
                    le.enabled = shouldEnable
                    if (shouldEnable) {
                        var targetGain = 0
                        if (isLeEnabled) {
                            targetGain += leGainMb
                        }
                        if (isXloud) {
                            targetGain += 300 // +300 mB (+3 dB) volume boost
                        }
                        le.setTargetGain(targetGain)
                    }
                } catch (e: Throwable) {
                    Log.w(TAG, "Failed to apply LoudnessEnhancer to session ${sessionEffects.sessionId}", e)
                }
            }

            // Hardware Audio HAL
            val hw = sessionEffects.nativeHardware
            if (hw.isSonyActive || hw.isDolbyActive) {
                hw.setEnabled(isMasterOn)
                if (isMasterOn) {
                    if (hw.isSonyActive) {
                        hw.setSonyParam(NativeAudioEffectBinding.SONY_PARAM_XLOUD_ON, if (isXloud) 1 else 0)
                    }

                    if (hw.isDolbyActive) {
                        hw.setDolbyParam(NativeAudioEffectBinding.DOLBY_PARAM_GEQ_ENABLE, 1)
                        hw.setDolbyGeqBands(daxLevels)
                        val dolbyIeq = when (ieq) {
                            IEQ_BALANCED -> 1
                            IEQ_WARM -> 2
                            IEQ_DETAILED -> 3
                            else -> 0
                        }
                        hw.setDolbyParam(NativeAudioEffectBinding.DOLBY_PARAM_IEQ_PRESET, dolbyIeq)
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "EqualizerManager"
        private const val PREFS_NAME = "equalizer_prefs"

        const val MIN_OMNIPLAY_VERSION_CODE = 35L
        const val MIN_OMNIPLAY_VERSION_NAME = "0.17.0"

        fun isOmniPlaySupported(context: Context): Boolean {
            return try {
                val pInfo = context.packageManager.getPackageInfo("xyz.omniplay", 0)
                val vCode = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    pInfo.longVersionCode
                } else {
                    @Suppress("DEPRECATION")
                    pInfo.versionCode.toLong()
                }
                if (vCode >= MIN_OMNIPLAY_VERSION_CODE) {
                    return true
                }
                val vName = pInfo.versionName ?: ""
                compareSemVer(vName, MIN_OMNIPLAY_VERSION_NAME) >= 0
            } catch (_: Exception) {
                false
            }
        }

        fun compareSemVer(v1: String, v2: String): Int {
            val parts1 = v1.split('.').mapNotNull { it.takeWhile { c -> c.isDigit() }.toIntOrNull() }
            val parts2 = v2.split('.').mapNotNull { it.takeWhile { c -> c.isDigit() }.toIntOrNull() }
            val maxLen = maxOf(parts1.size, parts2.size)
            for (i in 0 until maxLen) {
                val p1 = parts1.getOrElse(i) { 0 }
                val p2 = parts2.getOrElse(i) { 0 }
                if (p1 != p2) return p1.compareTo(p2)
            }
            return 0
        }

        const val KEY_MASTER_ENABLED = "master_enabled"
        const val KEY_PRESET_INDEX = "preset_index"
        const val KEY_DAX_BAND_PREFIX = "dax_band_"
        const val KEY_IEQ_PRESET = "ieq_preset"
        const val KEY_CLEAR_BASS = "clear_bass"
        const val KEY_LOUDNESS_ENHANCER_ENABLED = "loudness_enhancer_enabled"
        const val KEY_LOUDNESS_ENHANCER_GAIN_MB = "loudness_enhancer_gain_mb"
        const val KEY_XLOUD = "xloud"
        const val KEY_CROSSFEED_AMOUNT = "crossfeed_amount"
        const val KEY_DYNAMIC_RANGE_AMOUNT = "dynamic_range_amount"
        const val KEY_MONO_AUDIO = "mono_audio"
        const val KEY_STEREO_BALANCE = "stereo_balance"

        const val IEQ_OFF = 0
        const val IEQ_BALANCED = 1
        const val IEQ_WARM = 2
        const val IEQ_DETAILED = 3

        @Volatile
        private var INSTANCE: EqualizerManager? = null

        fun getInstance(context: Context): EqualizerManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EqualizerManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
