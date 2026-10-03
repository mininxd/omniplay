package xyz.omniplay.lyrics

import android.content.Context
import android.util.Log
import android.util.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import xyz.omniplay.model.Song
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Locale

object LrcLibClient {
    private const val TAG = "LrcLibClient"
    private const val BASE_URL = "https://lrclib.net/api"
    private const val USER_AGENT = "Omniplay/0.4.1 (https://github.com/mininxd/omniplay)"
    private const val TIMEOUT_MS = 8000

    // In-memory cache for the current session
    private val memoryCache = LruCache<Long, Lyrics>(100)

    /**
     * Checks if offline cached lyrics or a local .lrc file exist for [song].
     * Synchronized sources are prioritized over static/plain sources.
     * This is non-blocking and executes with zero network requests.
     */
    fun getCachedLyrics(context: Context, song: Song): Lyrics? {
        // 1. Check memory cache (fast return if already synced or instrumental)
        memoryCache.get(song.id)?.let { cached ->
            if (cached.hasSynced || cached.isInstrumental) return cached
        }

        // 2. PRIORITY: Local sidecar .lrc file next to audio file on storage (always synced)
        loadFromLocalLrc(song)?.let {
            memoryCache.put(song.id, it)
            return it
        }

        // 3. PRIORITY: Embedded container lyrics with timestamps
        val embedded = EmbeddedLyricsExtractor.extract(context, song)
        if (embedded != null && (embedded.hasSynced || embedded.isInstrumental)) {
            memoryCache.put(song.id, embedded)
            return embedded
        }

        // 4. PRIORITY: Persistent disk cache with synchronized lyrics
        val disk = loadFromDisk(context, song.id, song.title, song.artist)
        if (disk != null && (disk.hasSynced || disk.isInstrumental)) {
            memoryCache.put(song.id, disk)
            return disk
        }

        // 5. Fallback: Static / unsynced lyrics (prefer embedded over disk, or memory)
        val staticCandidate = embedded ?: disk ?: memoryCache.get(song.id)
        if (staticCandidate != null) {
            memoryCache.put(song.id, staticCandidate)
            return staticCandidate
        }

        return null
    }

    /**
     * Retrieves lyrics for [song].
     * If local or offline cached lyrics are already synchronized (or instrumental), returns them immediately.
     * If local or offline cached lyrics are static (plain text only), forces an online search via LRCLIB
     * to find synchronized / animated lyrics, falling back to the static lyrics if online fails or has no synced version.
     */
    suspend fun getLyrics(
        context: Context,
        song: Song,
        forceRefresh: Boolean = false
    ): LyricsResult = withContext(Dispatchers.IO) {
        if (forceRefresh) {
            memoryCache.remove(song.id)
        }

        val cached = if (!forceRefresh) getCachedLyrics(context, song) else null
        if (cached != null && (cached.hasSynced || cached.isInstrumental)) {
            return@withContext LyricsResult.Success(cached, isOffline = true)
        }

        // If local or offline cached lyrics are static, keep them as fallback while forcing an online search
        val fallbackStaticLyrics = cached ?: if (forceRefresh) {
            getCachedLyrics(context, song)
        } else null

        val rawTitle = song.title.trim()
        val rawArtist = song.artist.trim()
        if (rawTitle.isEmpty() ||
            rawTitle.equals("Unknown", ignoreCase = true) ||
            rawTitle.equals("No track selected", ignoreCase = true)
        ) {
            return@withContext fallbackStaticLyrics?.let {
                LyricsResult.Success(it, isOffline = true)
            } ?: LyricsResult.NotFound("No track selected")
        }

        val durationSec = (song.duration / 1000).toInt()
        val cleanTitle = cleanTrackName(rawTitle)
        val cleanArtist = cleanArtistName(rawArtist)

        var lastHttpCode = 200

        // Helper to query and process response
        fun tryGet(t: String, a: String): JSONObject? {
            val params = StringBuilder()
            params.append("track_name=").append(URLEncoder.encode(t, "UTF-8"))
            if (a.isNotEmpty() && !a.equals("Unknown Artist", ignoreCase = true)) {
                params.append("&artist_name=").append(URLEncoder.encode(a, "UTF-8"))
            }
            val res = executeRequest("$BASE_URL/get?$params")
            lastHttpCode = res.code
            return if (res.code == HttpURLConnection.HTTP_OK && res.body != null) {
                try { JSONObject(res.body) } catch (e: Exception) { null }
            } else null
        }

        fun trySearch(paramsUrl: String, duration: Int): JSONObject? {
            val res = executeRequest(paramsUrl)
            lastHttpCode = res.code
            if (res.code == HttpURLConnection.HTTP_OK && res.body != null) {
                try {
                    val array = JSONArray(res.body)
                    val list = ArrayList<JSONObject>(array.length())
                    for (i in 0 until array.length()) {
                        array.optJSONObject(i)?.let { list.add(it) }
                    }
                    return selectBestCandidate(list, duration)
                } catch (e: Exception) {
                    return null
                }
            }
            return null
        }

        var plainCandidate: Lyrics? = null

        // Attempt 1: Exact GET with raw title and artist
        val candidate1 = tryGet(rawTitle, rawArtist)
        if (lastHttpCode == 429) {
            return@withContext fallbackStaticLyrics?.let {
                LyricsResult.Success(it, isOffline = true)
            } ?: LyricsResult.Error("LRCLIB rate limit reached (HTTP 429). Please wait a moment before retrying.", isRateLimited = true)
        }
        if (candidate1 != null) {
            val parsed = parseLyricsJson(song.id, candidate1)
            if (parsed.hasSynced || parsed.isInstrumental) {
                saveToDisk(context, parsed, rawTitle, rawArtist)
                memoryCache.put(song.id, parsed)
                return@withContext LyricsResult.Success(parsed, isOffline = false)
            } else if (parsed.hasAnyLyrics && plainCandidate == null) {
                plainCandidate = parsed
            }
        }

        // Attempt 2: GET with cleaned title and artist (if different)
        if (cleanTitle != rawTitle || cleanArtist != rawArtist) {
            val candidate2 = tryGet(cleanTitle, cleanArtist)
            if (lastHttpCode == 429) {
                return@withContext fallbackStaticLyrics?.let {
                    LyricsResult.Success(it, isOffline = true)
                } ?: (plainCandidate?.let {
                    saveToDisk(context, it, rawTitle, rawArtist)
                    memoryCache.put(song.id, it)
                    LyricsResult.Success(it, isOffline = false)
                } ?: LyricsResult.Error("LRCLIB rate limit reached (HTTP 429). Please wait a moment before retrying.", isRateLimited = true))
            }
            if (candidate2 != null) {
                val parsed = parseLyricsJson(song.id, candidate2)
                if (parsed.hasSynced || parsed.isInstrumental) {
                    saveToDisk(context, parsed, rawTitle, rawArtist)
                    memoryCache.put(song.id, parsed)
                    return@withContext LyricsResult.Success(parsed, isOffline = false)
                } else if (parsed.hasAnyLyrics && plainCandidate == null) {
                    plainCandidate = parsed
                }
            }
        }

        // Attempt 3: Structured Search (/api/search?track_name=...&artist_name=...)
        val encCleanTrack = URLEncoder.encode(cleanTitle, "UTF-8")
        val encCleanArtist = URLEncoder.encode(cleanArtist, "UTF-8")
        val structuredUrl = if (cleanArtist.isNotEmpty() && !cleanArtist.equals("Unknown Artist", ignoreCase = true)) {
            "$BASE_URL/search?track_name=$encCleanTrack&artist_name=$encCleanArtist"
        } else {
            "$BASE_URL/search?track_name=$encCleanTrack"
        }

        val candidate3 = trySearch(structuredUrl, durationSec)
        if (lastHttpCode == 429) {
            return@withContext fallbackStaticLyrics?.let {
                LyricsResult.Success(it, isOffline = true)
            } ?: (plainCandidate?.let {
                saveToDisk(context, it, rawTitle, rawArtist)
                memoryCache.put(song.id, it)
                LyricsResult.Success(it, isOffline = false)
            } ?: LyricsResult.Error("LRCLIB rate limit reached (HTTP 429). Please wait a moment before retrying.", isRateLimited = true))
        }
        if (candidate3 != null) {
            val parsed = parseLyricsJson(song.id, candidate3)
            if (parsed.hasSynced || parsed.isInstrumental) {
                saveToDisk(context, parsed, rawTitle, rawArtist)
                memoryCache.put(song.id, parsed)
                return@withContext LyricsResult.Success(parsed, isOffline = false)
            } else if (parsed.hasAnyLyrics && plainCandidate == null) {
                plainCandidate = parsed
            }
        }

        // Attempt 4: Free-text Search (/api/search?q=...)
        val query = if (cleanArtist.isNotEmpty() && !cleanArtist.equals("Unknown Artist", ignoreCase = true)) {
            "$cleanTitle $cleanArtist"
        } else {
            cleanTitle
        }
        val qUrl = "$BASE_URL/search?q=" + URLEncoder.encode(query, "UTF-8")
        val candidate4 = trySearch(qUrl, durationSec)
        if (lastHttpCode == 429) {
            return@withContext fallbackStaticLyrics?.let {
                LyricsResult.Success(it, isOffline = true)
            } ?: (plainCandidate?.let {
                saveToDisk(context, it, rawTitle, rawArtist)
                memoryCache.put(song.id, it)
                LyricsResult.Success(it, isOffline = false)
            } ?: LyricsResult.Error("LRCLIB rate limit reached (HTTP 429). Please wait a moment before retrying.", isRateLimited = true))
        }
        if (candidate4 != null) {
            val parsed = parseLyricsJson(song.id, candidate4)
            if (parsed.hasSynced || parsed.isInstrumental) {
                saveToDisk(context, parsed, rawTitle, rawArtist)
                memoryCache.put(song.id, parsed)
                return@withContext LyricsResult.Success(parsed, isOffline = false)
            } else if (parsed.hasAnyLyrics && plainCandidate == null) {
                plainCandidate = parsed
            }
        }

        // Fallback priority when no synchronized lyrics are found online:
        // 1. If local / cached static lyrics exist, keep them (offline source)
        if (fallbackStaticLyrics != null) {
            return@withContext LyricsResult.Success(fallbackStaticLyrics, isOffline = true)
        }

        // 2. If online search found plain lyrics, use them
        if (plainCandidate != null) {
            saveToDisk(context, plainCandidate, rawTitle, rawArtist)
            memoryCache.put(song.id, plainCandidate)
            return@withContext LyricsResult.Success(plainCandidate, isOffline = false)
        }

        if (lastHttpCode == -1) {
            return@withContext LyricsResult.Error("Network error. Please check your internet connection.", isRateLimited = false)
        }

        return@withContext LyricsResult.NotFound("No lyrics found for \"$rawTitle\"")
    }

    private fun selectBestCandidate(results: List<JSONObject>, targetDurationSec: Int): JSONObject? {
        val valid = results.filter {
            it.optString("syncedLyrics").isNotBlank() ||
            it.optString("plainLyrics").isNotBlank() ||
            it.optBoolean("instrumental", false)
        }
        if (valid.isEmpty()) return null

        // Prioritize synced lyrics
        val synced = valid.filter { it.optString("syncedLyrics").isNotBlank() }
        val pool = if (synced.isNotEmpty()) synced else valid

        if (targetDurationSec > 0) {
            return pool.minByOrNull { obj ->
                val d = obj.optDouble("duration", 0.0)
                if (d > 0) Math.abs(d - targetDurationSec) else Double.MAX_VALUE
            } ?: pool.firstOrNull()
        }
        return pool.firstOrNull()
    }

    private fun parseLyricsJson(songId: Long, json: JSONObject): Lyrics {
        val isInstrumental = json.optBoolean("instrumental", false)
        val syncedRaw = json.optString("syncedLyrics").takeIf { it.isNotBlank() }
        val plainLyrics = json.optString("plainLyrics").takeIf { it.isNotBlank() }

        val syncedLines = if (syncedRaw != null) {
            val parsed = LrcParser.parse(syncedRaw)
            if (parsed.isNotEmpty()) parsed else null
        } else null

        return Lyrics(
            songId = songId,
            trackName = json.optString("trackName"),
            artistName = json.optString("artistName"),
            syncedLyrics = syncedLines,
            plainLyrics = plainLyrics,
            isInstrumental = isInstrumental,
            syncedRaw = if (syncedLines != null) syncedRaw else null,
            source = "LRCLIB",
            isOffline = false
        )
    }

    private class HttpResponse(val code: Int, val body: String?)

    private fun executeRequest(urlString: String): HttpResponse {
        var conn: HttpURLConnection? = null
        return try {
            val url = URL(urlString)
            conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                setRequestProperty("User-Agent", USER_AGENT)
                setRequestProperty("Lrclib-Client", USER_AGENT)
                setRequestProperty("Accept", "application/json")
                connectTimeout = TIMEOUT_MS
                readTimeout = TIMEOUT_MS
                instanceFollowRedirects = true
            }

            val code = conn.responseCode
            val body = if (code == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8"))
                val sb = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                reader.close()
                sb.toString()
            } else {
                null
            }
            HttpResponse(code, body)
        } catch (e: Exception) {
            HttpResponse(-1, null)
        } finally {
            conn?.disconnect()
        }
    }

    private fun saveToDisk(context: Context, lyrics: Lyrics, songTitle: String, songArtist: String) {
        try {
            val dir = File(context.filesDir, "lyrics")
            if (!dir.exists()) dir.mkdirs()

            // If saving static lyrics, do not overwrite if synced lyrics are already saved
            if (!lyrics.hasSynced && !lyrics.isInstrumental) {
                val existing = loadFromDisk(context, lyrics.songId, songTitle, songArtist)
                if (existing != null && (existing.hasSynced || existing.isInstrumental)) {
                    return
                }
            }

            val json = JSONObject().apply {
                put("songId", lyrics.songId)
                put("trackName", lyrics.trackName)
                put("artistName", lyrics.artistName)
                put("isInstrumental", lyrics.isInstrumental)
                put("plainLyrics", lyrics.plainLyrics ?: "")
                put("syncedRaw", lyrics.syncedRaw ?: "")
                put("source", lyrics.source)
            }
            val jsonStr = json.toString()
            // 1. Save by songId
            File(dir, "${lyrics.songId}.json").writeText(jsonStr, Charsets.UTF_8)
            // 2. Save by normalized title-artist key for persistent recovery across library rescans
            val key = getCacheKey(songTitle, songArtist)
            if (key.isNotEmpty()) {
                File(dir, "$key.json").writeText(jsonStr, Charsets.UTF_8)
            }
        } catch (e: Exception) {
            Log.w(TAG, "Failed to cache lyrics to persistent storage", e)
        }
    }

    private fun loadFromDisk(context: Context, songId: Long, title: String, artist: String): Lyrics? {
        val dirs = listOf(File(context.filesDir, "lyrics"), File(context.cacheDir, "lyrics"))
        val key = getCacheKey(title, artist)
        val fileNames = mutableListOf("$songId.json")
        if (key.isNotEmpty()) fileNames.add("$key.json")

        for (dir in dirs) {
            if (!dir.exists()) continue
            for (fn in fileNames) {
                val file = File(dir, fn)
                if (file.exists() && file.length() > 0) {
                    try {
                        val json = JSONObject(file.readText(Charsets.UTF_8))
                        val isInstrumental = json.optBoolean("isInstrumental", false)
                        val plain = json.optString("plainLyrics").takeIf { it.isNotBlank() }
                        val syncedRaw = json.optString("syncedRaw").takeIf { it.isNotBlank() }
                        val syncedLines = if (syncedRaw != null) LrcParser.parse(syncedRaw) else null
                        return Lyrics(
                            songId = songId,
                            trackName = json.optString("trackName", title),
                            artistName = json.optString("artistName", artist),
                            syncedLyrics = syncedLines,
                            plainLyrics = plain,
                            isInstrumental = isInstrumental,
                            syncedRaw = syncedRaw,
                            source = json.optString("source", "CACHED"),
                            isOffline = true
                        )
                    } catch (e: Exception) {
                        // ignore and try next
                    }
                }
            }
        }
        return null
    }

    private fun loadFromLocalLrc(song: Song): Lyrics? {
        if (song.filePath.isBlank()) return null
        return try {
            val audioFile = File(song.filePath)
            if (!audioFile.exists()) return null
            val baseName = audioFile.nameWithoutExtension
            val lrcFile = File(audioFile.parentFile, "$baseName.lrc")
            if (lrcFile.exists() && lrcFile.canRead() && lrcFile.length() > 0) {
                val text = lrcFile.readText(Charsets.UTF_8)
                val lines = LrcParser.parse(text)
                if (lines.isNotEmpty()) {
                    Lyrics(
                        songId = song.id,
                        trackName = song.title,
                        artistName = song.artist,
                        syncedLyrics = lines,
                        plainLyrics = null,
                        isInstrumental = false,
                        syncedRaw = text,
                        source = "LOCAL FILE",
                        isOffline = true
                    )
                } else null
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun getCacheKey(title: String, artist: String): String {
        val t = cleanTrackName(title).lowercase(Locale.US).replace(Regex("[^a-z0-9]"), "_")
        val a = cleanArtistName(artist).lowercase(Locale.US).replace(Regex("[^a-z0-9]"), "_")
        return if (t.isNotEmpty()) "${a}_$t".take(64) else ""
    }

    fun cleanTrackName(name: String): String {
        var t = name
        // Strip audio extensions
        t = t.replace(Regex("""\.(mp3|flac|wav|m4a|aac|ogg|dsf|dff)$""", RegexOption.IGNORE_CASE), "")
        // Strip track numbering at start e.g. "01 - ", "01. ", "1. "
        t = t.replace(Regex("""^\d{1,3}[\s.-]+"""), "")
        // Strip parenthetical tags with feat, ft, official, audio, video, remaster, live, bonus, deluxe, etc.
        t = t.replace(
            Regex(
                """\s*\(.*?(feat\.|ft\.|official|audio|video|music\s*video|remaster|deluxe|bonus|version|live|explicit|radio\s*edit).*?\)""",
                RegexOption.IGNORE_CASE
            ),
            ""
        )
        // Strip brackets [feat. ...], [Remastered], etc.
        t = t.replace(Regex("""\s*\[.*?\]"""), "")
        return t.trim().trimEnd('-', ' ')
    }

    fun cleanArtistName(name: String): String {
        var a = name
        a = a.replace(Regex("""\s*(feat\.|ft\.).*""", RegexOption.IGNORE_CASE), "")
        val primary = a.split(',', '/', ';').firstOrNull()?.trim()
        return if (!primary.isNullOrEmpty()) primary else a.trim()
    }
}
