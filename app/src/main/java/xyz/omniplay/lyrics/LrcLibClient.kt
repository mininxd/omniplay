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

object LrcLibClient {
    private const val TAG = "LrcLibClient"
    private const val BASE_URL = "https://lrclib.net/api"
    private const val USER_AGENT = "Omniplay/0.3 (https://github.com/mininxd/omniplay)"
    private const val TIMEOUT_MS = 6000

    // Memory cache for active session
    private val memoryCache = LruCache<Long, Lyrics>(50)

    /**
     * Retrieves lyrics for [song], checking memory cache, then local disk cache,
     * and finally querying LRCLIB with smart fallbacks.
     */
    suspend fun getLyrics(context: Context, song: Song): Lyrics? = withContext(Dispatchers.IO) {
        // 1. Check memory cache
        memoryCache.get(song.id)?.let { return@withContext it }

        // 2. Check disk cache
        loadFromDisk(context, song.id)?.let {
            memoryCache.put(song.id, it)
            return@withContext it
        }

        val rawTitle = song.title.trim()
        val rawArtist = song.artist.trim()
        if (rawTitle.isEmpty() ||
            rawTitle.equals("Unknown", ignoreCase = true) ||
            rawTitle.equals("No track selected", ignoreCase = true)
        ) {
            return@withContext null
        }

        val durationSec = (song.duration / 1000).toInt()

        // 3. Query LRCLIB with smart fallback strategy
        val lyrics = fetchFromLrcLib(song.id, rawTitle, rawArtist, durationSec)

        if (lyrics != null && lyrics.hasAnyLyrics) {
            memoryCache.put(song.id, lyrics)
            saveToDisk(context, lyrics)
        }

        return@withContext lyrics
    }

    private fun fetchFromLrcLib(
        songId: Long,
        title: String,
        artist: String,
        durationSec: Int
    ): Lyrics? {
        val cleanTitle = cleanTrackName(title)
        val cleanArtist = cleanArtistName(artist)

        // Attempt 1: Exact GET with raw title and artist
        queryGet(title, artist)?.let { obj ->
            val parsed = parseLyricsJson(songId, obj)
            if (parsed.hasAnyLyrics) return parsed
        }

        // Attempt 2: GET with cleaned title and artist (if different)
        if (cleanTitle != title || cleanArtist != artist) {
            queryGet(cleanTitle, cleanArtist)?.let { obj ->
                val parsed = parseLyricsJson(songId, obj)
                if (parsed.hasAnyLyrics) return parsed
            }
        }

        // Attempt 3: Search with query string
        val queries = mutableListOf<String>()
        if (cleanArtist.isNotEmpty() && !cleanArtist.equals("Unknown Artist", ignoreCase = true)) {
            queries.add("$cleanTitle $cleanArtist")
        }
        queries.add(cleanTitle)

        for (q in queries) {
            val results = querySearch(q)
            if (results.isNotEmpty()) {
                val candidate = selectBestCandidate(results, durationSec)
                if (candidate != null) {
                    val parsed = parseLyricsJson(songId, candidate)
                    if (parsed.hasAnyLyrics) return parsed
                }
            }
        }

        return null
    }

    private fun queryGet(trackName: String, artistName: String): JSONObject? {
        val params = StringBuilder()
        params.append("track_name=").append(URLEncoder.encode(trackName, "UTF-8"))
        if (artistName.isNotEmpty() && !artistName.equals("Unknown Artist", ignoreCase = true)) {
            params.append("&artist_name=").append(URLEncoder.encode(artistName, "UTF-8"))
        }

        val urlString = "$BASE_URL/get?$params"
        return executeGet(urlString)
    }

    private fun querySearch(query: String): List<JSONObject> {
        return try {
            val encoded = URLEncoder.encode(query, "UTF-8")
            val urlString = "$BASE_URL/search?q=$encoded"
            val jsonText = executeGetRaw(urlString) ?: return emptyList()
            val array = JSONArray(jsonText)
            val list = ArrayList<JSONObject>(array.length())
            for (i in 0 until array.length()) {
                val item = array.optJSONObject(i)
                if (item != null) list.add(item)
            }
            list
        } catch (e: Exception) {
            emptyList()
        }
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
            LrcParser.parse(syncedRaw)
        } else null

        return Lyrics(
            songId = songId,
            trackName = json.optString("trackName"),
            artistName = json.optString("artistName"),
            syncedLyrics = syncedLines,
            plainLyrics = plainLyrics,
            isInstrumental = isInstrumental,
            syncedRaw = syncedRaw,
            source = "LRCLIB"
        )
    }

    private fun executeGet(urlString: String): JSONObject? {
        val raw = executeGetRaw(urlString) ?: return null
        return try {
            JSONObject(raw)
        } catch (e: Exception) {
            null
        }
    }

    private fun executeGetRaw(urlString: String): String? {
        var conn: HttpURLConnection? = null
        return try {
            val url = URL(urlString)
            conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                setRequestProperty("User-Agent", USER_AGENT)
                setRequestProperty("Accept", "application/json")
                connectTimeout = TIMEOUT_MS
                readTimeout = TIMEOUT_MS
                instanceFollowRedirects = true
            }

            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
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
        } catch (e: Exception) {
            null
        } finally {
            conn?.disconnect()
        }
    }

    private fun saveToDisk(context: Context, lyrics: Lyrics) {
        try {
            val dir = File(context.cacheDir, "lyrics")
            if (!dir.exists()) dir.mkdirs()
            val file = File(dir, "${lyrics.songId}.json")
            val json = JSONObject().apply {
                put("songId", lyrics.songId)
                put("trackName", lyrics.trackName)
                put("artistName", lyrics.artistName)
                put("isInstrumental", lyrics.isInstrumental)
                put("plainLyrics", lyrics.plainLyrics ?: "")
                put("syncedRaw", lyrics.syncedRaw ?: "")
            }
            file.writeText(json.toString(), Charsets.UTF_8)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to cache lyrics to disk", e)
        }
    }

    private fun loadFromDisk(context: Context, songId: Long): Lyrics? {
        return try {
            val file = File(File(context.cacheDir, "lyrics"), "$songId.json")
            if (!file.exists()) return null
            val json = JSONObject(file.readText(Charsets.UTF_8))
            val isInstrumental = json.optBoolean("isInstrumental", false)
            val plain = json.optString("plainLyrics").takeIf { it.isNotBlank() }
            val syncedRaw = json.optString("syncedRaw").takeIf { it.isNotBlank() }
            val syncedLines = if (syncedRaw != null) LrcParser.parse(syncedRaw) else null
            Lyrics(
                songId = songId,
                trackName = json.optString("trackName"),
                artistName = json.optString("artistName"),
                syncedLyrics = syncedLines,
                plainLyrics = plain,
                isInstrumental = isInstrumental,
                syncedRaw = syncedRaw,
                source = "LRCLIB"
            )
        } catch (e: Exception) {
            null
        }
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
