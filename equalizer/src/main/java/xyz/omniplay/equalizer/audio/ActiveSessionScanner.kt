package xyz.omniplay.equalizer.audio

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Scanner for detecting active media audio sessions across Android apps and web browsers.
 *
 * Browsers (Google Chrome, Brave, Kiwi, Opera, Firefox) play audio through AudioTrack
 * with dynamic non-zero session IDs and do not broadcast OPEN_AUDIO_EFFECT_CONTROL_SESSION.
 *
 * This scanner provides:
 * 1. Deep reflection extraction of audio session IDs from AudioPlaybackConfiguration.
 * 2. Fallback parsing of active audio sessions from dumpsys audio / dumpsys media.audio_flinger.
 * 3. Asynchronous non-blocking background scanning so the main thread is never stalled.
 */
object ActiveSessionScanner {

    private const val TAG = "ActiveSessionScanner"

    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())
    private val isScanning = AtomicBoolean(false)

    @Volatile
    private var canUseDirectDumpsys: Boolean? = null

    @Volatile
    internal var canUseRootSu: Boolean? = null

    /**
     * Extracts the real audio session ID from an AudioPlaybackConfiguration object.
     * Uses multi-tiered reflection to handle various Android versions and vendor ROMs.
     */
    fun extractSessionId(config: Any): Int {
        val clazz = config.javaClass

        // Strategy 1: Public or @SystemApi getSessionId()
        try {
            val method = clazz.getMethod("getSessionId")
            val result = method.invoke(config)
            if (result is Int && result > 0) {
                return result
            }
        } catch (_: Throwable) {}

        // Strategy 2: Declared getSessionId() with access override
        try {
            val method = clazz.getDeclaredMethod("getSessionId")
            method.isAccessible = true
            val result = method.invoke(config)
            if (result is Int && result > 0) {
                return result
            }
        } catch (_: Throwable) {}

        // Strategy 3: Declared field mSessionId
        try {
            val field = clazz.getDeclaredField("mSessionId")
            field.isAccessible = true
            val result = field.getInt(config)
            if (result > 0) {
                return result
            }
        } catch (_: Throwable) {}

        // Strategy 4: Scan all declared fields for session / sessionId
        try {
            for (field in clazz.declaredFields) {
                if (field.name.contains("session", ignoreCase = true) && field.type == Int::class.javaPrimitiveType) {
                    field.isAccessible = true
                    val result = field.getInt(config)
                    if (result > 0) {
                        return result
                    }
                }
            }
        } catch (_: Throwable) {}

        // Strategy 5: PlayerProxy / mPlayerProxy getAudioSessionId()
        try {
            for (field in clazz.declaredFields) {
                if (field.name.contains("player", ignoreCase = true) || field.name.contains("proxy", ignoreCase = true)) {
                    field.isAccessible = true
                    val proxy = field.get(config) ?: continue
                    for (method in proxy.javaClass.methods) {
                        if (method.name.contains("SessionId", ignoreCase = true) && method.parameterTypes.isEmpty()) {
                            method.isAccessible = true
                            val result = method.invoke(proxy)
                            if (result is Int && result > 0) {
                                return result
                            }
                        }
                    }
                }
            }
        } catch (_: Throwable) {}

        return -1
    }

    /**
     * Extracts the client package name from an AudioPlaybackConfiguration object via client UID.
     */
    fun extractClientPackageName(config: Any, pm: android.content.pm.PackageManager): String? {
        val clazz = config.javaClass
        var uid = -1
        try {
            val method = clazz.getMethod("getClientUid")
            val result = method.invoke(config)
            if (result is Int && result > 0) uid = result
        } catch (_: Throwable) {}

        if (uid <= 0) {
            try {
                val method = clazz.getDeclaredMethod("getClientUid")
                method.isAccessible = true
                val result = method.invoke(config)
                if (result is Int && result > 0) uid = result
            } catch (_: Throwable) {}
        }

        if (uid <= 0) {
            try {
                val field = clazz.getDeclaredField("mClientUid")
                field.isAccessible = true
                val result = field.getInt(config)
                if (result > 0) uid = result
            } catch (_: Throwable) {}
        }

        if (uid > 0) {
            try {
                val packages = pm.getPackagesForUid(uid)
                val pkg = packages?.firstOrNull()
                if (!pkg.isNullOrBlank()) return pkg
            } catch (_: Throwable) {}
        }

        return null
    }

    /**
     * Parses active audio sessions from dumpsys audio or dumpsys media.audio_flinger output.
     */
    fun parseSessionIdsFromDump(dump: String): Set<Int> {
        val results = mutableSetOf<Int>()
        if (dump.isBlank()) return results

        val sessionRegex = Regex("""session(?:Id)?[:=\s]+(\d+)""", RegexOption.IGNORE_CASE)
        val startedRegex = Regex("""state[:=\s]*(?:started|active|playing)""", RegexOption.IGNORE_CASE)

        // Pass 1: Line-by-line inspection (standard AOSP PlaybackActivityMonitor dumps)
        for (line in dump.lines()) {
            if (startedRegex.containsMatchIn(line)) {
                sessionRegex.find(line)?.groupValues?.get(1)?.toIntOrNull()?.let { id ->
                    if (id > 0) results.add(id)
                }
            }
        }

        // Pass 2: Block-based inspection (e.g. multi-line player states)
        if (results.isEmpty()) {
            val blocks = dump.split(Regex("""(?=(?:piid:\s*\d+|Track\s+\d+|Player\s+\d+|\n\s*\n))"""))
            for (block in blocks) {
                if (startedRegex.containsMatchIn(block)) {
                    sessionRegex.find(block)?.groupValues?.get(1)?.toIntOrNull()?.let { id ->
                        if (id > 0) results.add(id)
                    }
                }
            }
        }

        // Pass 3: AudioFlinger Tracks table format (Active == yes)
        if (results.isEmpty() && dump.contains("Active", ignoreCase = true)) {
            for (line in dump.lines()) {
                val lower = line.lowercase()
                if (lower.contains("yes") && !lower.contains("paused")) {
                    sessionRegex.find(line)?.groupValues?.get(1)?.toIntOrNull()?.let { id ->
                        if (id > 0) results.add(id)
                    }
                }
            }
        }

        return results
    }

    /**
     * Scans active audio playback sessions via dumpsys audio (direct or root fallback).
     */
    fun scanActiveAudioSessions(): Set<Int> {
        // Try direct dumpsys if not marked unavailable
        if (canUseDirectDumpsys != false) {
            val directOutput = executeCommand("dumpsys audio")
            if (!directOutput.isNullOrBlank() && !directOutput.contains("Permission Denial")) {
                canUseDirectDumpsys = true
                val sessions = parseSessionIdsFromDump(directOutput)
                if (sessions.isNotEmpty()) return sessions
            } else {
                canUseDirectDumpsys = false
            }
        }

        // Try root su dumpsys if available
        if (canUseRootSu != false) {
            val suOutput = executeCommandWithSu("dumpsys audio")
            if (!suOutput.isNullOrBlank() && !suOutput.contains("Permission Denial")) {
                canUseRootSu = true
                val sessions = parseSessionIdsFromDump(suOutput)
                if (sessions.isNotEmpty()) return sessions
            } else {
                canUseRootSu = false
            }
        }

        return emptySet()
    }

    /**
     * Executes scanActiveAudioSessions asynchronously and posts the result to the main thread.
     */
    fun scanAsync(callback: (Set<Int>) -> Unit) {
        if (!isScanning.compareAndSet(false, true)) {
            return // Scan already running
        }

        executor.execute {
            try {
                val sessions = scanActiveAudioSessions()
                mainHandler.post {
                    callback(sessions)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Error during asynchronous active session scan", e)
            } finally {
                isScanning.set(false)
            }
        }
    }

    private fun executeCommand(cmd: String): String? {
        return try {
            val process = Runtime.getRuntime().exec(cmd)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val sb = java.lang.StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.appendLine(line)
            }
            reader.close()
            process.waitFor()
            sb.toString()
        } catch (_: Throwable) {
            null
        }
    }

    private fun executeCommandWithSu(cmd: String): String? {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("su", "-c", cmd))
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val sb = java.lang.StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.appendLine(line)
            }
            reader.close()
            process.waitFor()
            sb.toString()
        } catch (_: Throwable) {
            null
        }
    }

    fun stop() {
        // Safe placeholder; executor remains ready for subsequent requests
    }
}
