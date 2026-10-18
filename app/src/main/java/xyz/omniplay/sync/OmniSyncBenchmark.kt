package xyz.omniplay.sync

import android.app.ActivityManager
import android.content.Context
import android.os.SystemClock
import kotlin.math.sin

enum class OmniSyncDeviceTier {
    LOW_END,
    MID_RANGE,
    HIGH_END
}

data class OmniSyncBenchmarkResult(
    val tier: OmniSyncDeviceTier,
    val totalRamMb: Long,
    val cpuCores: Int,
    val microBenchmarkScoreMs: Long,
    val isLowRamDevice: Boolean,
    val recommendedQuality: OmniSyncQuality,
    val minBufferMs: Int,
    val maxBufferMs: Int,
    val bufferForPlaybackMs: Int,
    val bufferForPlaybackAfterRebufferMs: Int,
    val driftDeadbandMs: Long,
    val driftSeekSnapMs: Long
)

/**
 * OmniSync Benchmark:
 * Silently evaluates device hardware capabilities (RAM, CPU cores, micro-benchmark throughput)
 * to configure optimal audio buffers, drift deadbands, and rendering engines behind the scenes
 * without altering any user-facing text or UI.
 */
object OmniSyncBenchmark {
    private const val PREFS_NAME = "omnisync_benchmark_prefs"
    private const val KEY_CACHED_TIER = "cached_device_tier"
    private const val KEY_CACHED_SCORE = "cached_benchmark_score"

    @Volatile
    private var cachedResult: OmniSyncBenchmarkResult? = null

    fun getOrRunBenchmark(context: Context): OmniSyncBenchmarkResult {
        cachedResult?.let { return it }

        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val cachedTierName = prefs.getString(KEY_CACHED_TIER, null)
        val cachedScore = prefs.getLong(KEY_CACHED_SCORE, -1L)

        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        activityManager?.getMemoryInfo(memInfo)

        val totalRamMb = memInfo.totalMem / (1024 * 1024)
        val isLowRam = activityManager?.isLowRamDevice == true
        val cores = Runtime.getRuntime().availableProcessors().coerceAtLeast(1)

        val scoreMs = if (cachedScore >= 0L) {
            cachedScore
        } else {
            runSilentCpuMicroBenchmark().also {
                prefs.edit().putLong(KEY_CACHED_SCORE, it).apply()
            }
        }

        val tier = if (cachedTierName != null) {
            try {
                OmniSyncDeviceTier.valueOf(cachedTierName)
            } catch (e: Exception) {
                determineTier(totalRamMb, cores, isLowRam, scoreMs)
            }
        } else {
            determineTier(totalRamMb, cores, isLowRam, scoreMs).also {
                prefs.edit().putString(KEY_CACHED_TIER, it.name).apply()
            }
        }

        return createBenchmarkResult(tier, totalRamMb, cores, scoreMs, isLowRam).also {
            cachedResult = it
        }
    }

    private fun runSilentCpuMicroBenchmark(): Long {
        return try {
            val start = SystemClock.elapsedRealtimeNanos()
            var sum = 0.0f
            val count = 5000
            for (i in 0 until count) {
                val sample = sin((i * 0.05).toFloat())
                sum += sample * 0.95f
            }
            val elapsedNanos = SystemClock.elapsedRealtimeNanos() - start
            val elapsedMs = elapsedNanos / 1_000_000L
            elapsedMs.coerceAtLeast(0L)
        } catch (e: Exception) {
            5L
        }
    }

    private fun determineTier(
        totalRamMb: Long,
        cores: Int,
        isLowRam: Boolean,
        scoreMs: Long
    ): OmniSyncDeviceTier {
        if (isLowRam || totalRamMb <= 3500L || cores <= 4 || scoreMs > 12L) {
            return OmniSyncDeviceTier.LOW_END
        }

        if (totalRamMb <= 6000L || cores <= 6 || scoreMs > 5L) {
            return OmniSyncDeviceTier.MID_RANGE
        }

        return OmniSyncDeviceTier.HIGH_END
    }

    private fun createBenchmarkResult(
        tier: OmniSyncDeviceTier,
        totalRamMb: Long,
        cores: Int,
        scoreMs: Long,
        isLowRam: Boolean
    ): OmniSyncBenchmarkResult {
        return when (tier) {
            OmniSyncDeviceTier.LOW_END -> OmniSyncBenchmarkResult(
                tier = tier,
                totalRamMb = totalRamMb,
                cpuCores = cores,
                microBenchmarkScoreMs = scoreMs,
                isLowRamDevice = isLowRam,
                recommendedQuality = OmniSyncQuality.LOW,
                minBufferMs = 1500,
                maxBufferMs = 4500,
                bufferForPlaybackMs = 400,
                bufferForPlaybackAfterRebufferMs = 800,
                driftDeadbandMs = 120L,
                driftSeekSnapMs = 650L
            )
            OmniSyncDeviceTier.MID_RANGE -> OmniSyncBenchmarkResult(
                tier = tier,
                totalRamMb = totalRamMb,
                cpuCores = cores,
                microBenchmarkScoreMs = scoreMs,
                isLowRamDevice = isLowRam,
                recommendedQuality = OmniSyncQuality.HIGH,
                minBufferMs = 800,
                maxBufferMs = 2500,
                bufferForPlaybackMs = 150,
                bufferForPlaybackAfterRebufferMs = 350,
                driftDeadbandMs = 70L,
                driftSeekSnapMs = 450L
            )
            OmniSyncDeviceTier.HIGH_END -> OmniSyncBenchmarkResult(
                tier = tier,
                totalRamMb = totalRamMb,
                cpuCores = cores,
                microBenchmarkScoreMs = scoreMs,
                isLowRamDevice = isLowRam,
                recommendedQuality = OmniSyncQuality.HIGH,
                minBufferMs = 350,
                maxBufferMs = 1500,
                bufferForPlaybackMs = 80,
                bufferForPlaybackAfterRebufferMs = 150,
                driftDeadbandMs = 40L,
                driftSeekSnapMs = 350L
            )
        }
    }
}
