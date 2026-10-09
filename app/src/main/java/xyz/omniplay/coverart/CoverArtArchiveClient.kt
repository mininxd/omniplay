package xyz.omniplay.coverart

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import android.net.Uri
import org.json.JSONArray
import org.json.JSONObject
import xyz.omniplay.model.Song
import xyz.omniplay.util.FlacHeaderParser
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.security.MessageDigest
import java.util.Locale

/**
 * Client for Cover Art Archive (https://coverartarchive.org/doc/Cover_Art_Archive/API)
 * and MusicBrainz API (https://musicbrainz.org/ws/2/).
 *
 * Retrieves front album covers and artist images when audio files have no embedded
 * or local artwork. Includes disk & memory caching, rate-limiting compliance,
 * and multi-source fallback (Release Group -> Release -> Recording -> Artist portrait).
 */
object CoverArtArchiveClient {

    private const val TAG = "CoverArtArchiveClient"
    private const val CAA_BASE_URL = "https://coverartarchive.org"
    private const val MB_BASE_URL = "https://musicbrainz.org/ws/2"
    private const val WIKIDATA_API_URL = "https://www.wikidata.org/w/api.php"
    private const val WIKIPEDIA_API_URL = "https://en.wikipedia.org/w/api.php"
    private const val WIKIMEDIA_COMMONS_FILE_URL = "https://commons.wikimedia.org/wiki/Special:FilePath"

    private const val USER_AGENT = "Omniplay/0.7 (https://github.com/mininxd/omniplay)"
    private const val TIMEOUT_MS = 10000
    private const val MAX_DIMENSION = 800
    private const val MAX_DISK_CACHE_SIZE = 40 * 1024 * 1024L // 40MB
    private const val CACHE_SUBDIR = "online_art_cache"

    // Mutex & timestamp to respect MusicBrainz 1 req/sec rate limit
    private val rateLimitMutex = Mutex()
    private var lastMusicBrainzTime = 0L

    // In-memory cache for downloaded bitmaps
    private val memoryCache: LruCache<String, Bitmap> by lazy {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = (maxMemory / 8).coerceAtLeast(1024)
        object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return (bitmap.byteCount / 1024).coerceAtLeast(1)
            }
        }
    }

    // Negative cache to prevent redundant network calls for items without artwork
    private val negativeCache = LruCache<String, Boolean>(1000)

    /**
     * Attempts to fetch cover art for [song] using the Cover Art Archive API.
     * Falls back to artist image if album cover art is not found.
     */
    suspend fun fetchCoverArt(context: Context, song: Song): Bitmap? = withContext(Dispatchers.IO) {
        val album = cleanAlbumName(song.album)
        val artist = cleanArtistName(song.artist)
        val title = cleanTrackName(song.title)

        // Check direct MusicBrainz tags if present in FLAC file
        if (song.format.equals("FLAC", ignoreCase = true) || song.filePath.endsWith(".flac", ignoreCase = true) || song.contentUri.toString().endsWith(".flac", ignoreCase = true)) {
            try {
                val flacHeader = if (song.contentUri != Uri.EMPTY) {
                    context.contentResolver.openInputStream(song.contentUri)?.use {
                        FlacHeaderParser.parse(it)
                    }
                } else if (song.filePath.isNotBlank()) {
                    val f = File(song.filePath)
                    if (f.exists()) f.inputStream().use { FlacHeaderParser.parse(it) } else null
                } else null

                if (flacHeader != null) {
                    flacHeader.musicbrainzReleaseGroupId?.let { rgId ->
                        val directBitmap = fetchCaaImage("release-group", rgId)
                        if (directBitmap != null) return@withContext directBitmap
                    }
                    flacHeader.musicbrainzReleaseId?.let { relId ->
                        val directBitmap = fetchCaaImage("release", relId)
                        if (directBitmap != null) return@withContext directBitmap
                    }
                }
            } catch (ignored: Throwable) {}
        }

        // Try album cover first
        val cover = fetchCoverArt(context, album, artist, title)
        if (cover != null) return@withContext cover

        // Fallback to artist image if no album art found
        if (artist.isNotBlank() && !isUnknownArtist(artist)) {
            val artistArt = fetchArtistImage(context, artist)
            if (artistArt != null) return@withContext artistArt
        }

        null
    }

    /**
     * Fetches album cover art from Cover Art Archive.
     * Strategy:
     * 1. Query MusicBrainz release-group for exact album & artist -> CAA /release-group/{mbid}/front-500
     * 2. Query MusicBrainz release for album & artist -> CAA /release/{mbid}/front-500
     * 3. Query MusicBrainz recording by title & artist -> extract release/release-group -> CAA
     */
    suspend fun fetchCoverArt(
        context: Context,
        album: String,
        artist: String,
        title: String
    ): Bitmap? = withContext(Dispatchers.IO) {
        val cleanAlbum = cleanAlbumName(album)
        val cleanArtist = cleanArtistName(artist)
        val cleanTitle = cleanTrackName(title)

        val cacheKey = "cover_${cleanArtist}_${cleanAlbum}_${cleanTitle}".lowercase(Locale.ROOT)
        memoryCache.get(cacheKey)?.let { return@withContext it }
        loadFromDisk(context, cacheKey)?.let {
            memoryCache.put(cacheKey, it)
            return@withContext it
        }

        if (negativeCache.get(cacheKey) == true) {
            return@withContext null
        }

        var resultBitmap: Bitmap? = null

        // Attempt 1: Query release-group by album & artist
        if (cleanAlbum.isNotBlank() && !isUnknownAlbum(cleanAlbum) && cleanArtist.isNotBlank() && !isUnknownArtist(cleanArtist)) {
            val releaseGroupMbids = searchReleaseGroups(cleanAlbum, cleanArtist)
            for (mbid in releaseGroupMbids) {
                resultBitmap = fetchCaaImage("release-group", mbid)
                if (resultBitmap != null) break
            }

            // Attempt 2: Query release by album & artist if release-group didn't yield cover
            if (resultBitmap == null) {
                val releaseMbids = searchReleases(cleanAlbum, cleanArtist)
                for (mbid in releaseMbids) {
                    resultBitmap = fetchCaaImage("release", mbid)
                    if (resultBitmap != null) break
                }
            }
        }

        // Attempt 3: Query recording by title & artist
        if (resultBitmap == null && cleanTitle.isNotBlank() && cleanArtist.isNotBlank() && !isUnknownArtist(cleanArtist)) {
            val recordingMbids = searchRecordingReleases(cleanTitle, cleanArtist)
            for ((type, mbid) in recordingMbids) {
                resultBitmap = fetchCaaImage(type, mbid)
                if (resultBitmap != null) break
            }
        }

        if (resultBitmap != null) {
            memoryCache.put(cacheKey, resultBitmap)
            saveToDisk(context, cacheKey, resultBitmap)
            return@withContext resultBitmap
        }

        negativeCache.put(cacheKey, true)
        null
    }

    /**
     * Fetches artist portrait / image from MusicBrainz relations
     * (Wikimedia Commons, Wikidata, Wikipedia, or artist release group fallback).
     * Uses [representativeSong] to disambiguate artists sharing the same name.
     */
    suspend fun fetchArtistImage(context: Context, artistName: String, representativeSong: Song? = null): Bitmap? = withContext(Dispatchers.IO) {
        val cleanArtist = cleanArtistName(artistName)
        if (cleanArtist.isBlank() || isUnknownArtist(cleanArtist)) return@withContext null

        val cacheKey = "artist_${cleanArtist}".lowercase(Locale.ROOT)
        memoryCache.get(cacheKey)?.let { return@withContext it }
        loadFromDisk(context, cacheKey)?.let {
            memoryCache.put(cacheKey, it)
            return@withContext it
        }

        if (negativeCache.get(cacheKey) == true) {
            return@withContext null
        }

        // Step 1: Find candidate artist MBIDs (with song/album disambiguation if available)
        val candidateMbids = searchArtistMbids(cleanArtist, representativeSong)
        if (candidateMbids.isEmpty()) {
            negativeCache.put(cacheKey, true)
            return@withContext null
        }

        var bitmap: Bitmap? = null

        // Step 2: Try relations for each candidate artist
        for (artistMbid in candidateMbids) {
            val imageUrl = fetchArtistImageUrlFromRelations(artistMbid)
            if (imageUrl != null) {
                bitmap = downloadAndDecodeImage(imageUrl)
                if (bitmap != null) break
            }
        }

        // Step 3: Fallback to primary release-group cover art for candidates
        if (bitmap == null) {
            for (artistMbid in candidateMbids) {
                val fallbackRgMbid = fetchArtistPrimaryReleaseGroup(artistMbid)
                if (fallbackRgMbid != null) {
                    bitmap = fetchCaaImage("release-group", fallbackRgMbid)
                    if (bitmap != null) break
                }
            }
        }

        if (bitmap != null) {
            memoryCache.put(cacheKey, bitmap)
            saveToDisk(context, cacheKey, bitmap)
            return@withContext bitmap
        }

        negativeCache.put(cacheKey, true)
        null
    }

    // =========================================================================
    // MusicBrainz Search Queries (Throttled to 1 req/sec)
    // =========================================================================

    private suspend fun searchReleaseGroups(album: String, artist: String): List<String> {
        val q1 = URLEncoder.encode("releasegroup:\"$album\" AND artist:\"$artist\"", "UTF-8")
        var json = executeMusicBrainzRequest("$MB_BASE_URL/release-group/?query=$q1&fmt=json&limit=5")
        var array = json?.optJSONArray("release-groups")
        if (array == null || array.length() == 0) {
            val q2 = URLEncoder.encode("releasegroup:$album AND artist:$artist", "UTF-8")
            json = executeMusicBrainzRequest("$MB_BASE_URL/release-group/?query=$q2&fmt=json&limit=5")
            array = json?.optJSONArray("release-groups")
        }
        val mbids = mutableListOf<String>()
        if (array != null) {
            for (i in 0 until array.length()) {
                val item = array.optJSONObject(i) ?: continue
                val id = item.optString("id")
                if (id.isNotBlank() && !mbids.contains(id)) mbids.add(id)
            }
        }
        return mbids
    }

    private suspend fun searchReleases(album: String, artist: String): List<String> {
        val q1 = URLEncoder.encode("release:\"$album\" AND artist:\"$artist\"", "UTF-8")
        var json = executeMusicBrainzRequest("$MB_BASE_URL/release/?query=$q1&fmt=json&limit=5")
        var array = json?.optJSONArray("releases")
        if (array == null || array.length() == 0) {
            val q2 = URLEncoder.encode("release:$album AND artist:$artist", "UTF-8")
            json = executeMusicBrainzRequest("$MB_BASE_URL/release/?query=$q2&fmt=json&limit=5")
            array = json?.optJSONArray("releases")
        }
        val mbids = mutableListOf<String>()
        if (array != null) {
            for (i in 0 until array.length()) {
                val item = array.optJSONObject(i) ?: continue
                val id = item.optString("id")
                if (id.isNotBlank() && !mbids.contains(id)) mbids.add(id)
            }
        }
        return mbids
    }

    private suspend fun searchRecordingReleases(title: String, artist: String): List<Pair<String, String>> {
        val q1 = URLEncoder.encode("recording:\"$title\" AND artist:\"$artist\"", "UTF-8")
        var json = executeMusicBrainzRequest("$MB_BASE_URL/recording/?query=$q1&fmt=json&limit=5")
        var recordings = json?.optJSONArray("recordings")
        if (recordings == null || recordings.length() == 0) {
            val q2 = URLEncoder.encode("recording:$title AND artist:$artist", "UTF-8")
            json = executeMusicBrainzRequest("$MB_BASE_URL/recording/?query=$q2&fmt=json&limit=5")
            recordings = json?.optJSONArray("recordings")
        }
        val results = mutableListOf<Pair<String, String>>()
        if (recordings != null) {
            for (i in 0 until recordings.length()) {
                val rec = recordings.optJSONObject(i) ?: continue
                val releases = rec.optJSONArray("releases") ?: continue
                for (j in 0 until releases.length()) {
                    val rel = releases.optJSONObject(j) ?: continue
                    val rg = rel.optJSONObject("release-group")
                    val rgId = rg?.optString("id")
                    if (!rgId.isNullOrBlank()) {
                        results.add("release-group" to rgId)
                    }
                    val relId = rel.optString("id")
                    if (relId.isNotBlank()) {
                        results.add("release" to relId)
                    }
                }
            }
        }
        return results
    }

    private suspend fun searchArtistMbids(artist: String, representativeSong: Song? = null): List<String> {
        val mbids = mutableListOf<String>()

        // 1. If representative song has album or title, query release-group or recording to find exact artist
        if (representativeSong != null) {
            val album = cleanAlbumName(representativeSong.album)
            if (album.isNotBlank() && !isUnknownAlbum(album)) {
                val q = URLEncoder.encode("releasegroup:\"$album\" AND artist:\"$artist\"", "UTF-8")
                val json = executeMusicBrainzRequest("$MB_BASE_URL/release-group/?query=$q&fmt=json&limit=3")
                val rgs = json?.optJSONArray("release-groups")
                if (rgs != null) {
                    for (i in 0 until rgs.length()) {
                        val rg = rgs.optJSONObject(i) ?: continue
                        val credits = rg.optJSONArray("artist-credit") ?: continue
                        for (j in 0 until credits.length()) {
                            val art = credits.optJSONObject(j)?.optJSONObject("artist")
                            val id = art?.optString("id")
                            if (!id.isNullOrBlank() && !mbids.contains(id)) {
                                mbids.add(id)
                            }
                        }
                    }
                }
            }

            val title = cleanTrackName(representativeSong.title)
            if (mbids.isEmpty() && title.isNotBlank()) {
                val q = URLEncoder.encode("recording:\"$title\" AND artist:\"$artist\"", "UTF-8")
                val json = executeMusicBrainzRequest("$MB_BASE_URL/recording/?query=$q&fmt=json&limit=3")
                val recs = json?.optJSONArray("recordings")
                if (recs != null) {
                    for (i in 0 until recs.length()) {
                        val rec = recs.optJSONObject(i) ?: continue
                        val credits = rec.optJSONArray("artist-credit") ?: continue
                        for (j in 0 until credits.length()) {
                            val art = credits.optJSONObject(j)?.optJSONObject("artist")
                            val id = art?.optString("id")
                            if (!id.isNullOrBlank() && !mbids.contains(id)) {
                                mbids.add(id)
                            }
                        }
                    }
                }
            }
        }

        // 2. Query MusicBrainz artist endpoint with up to 6 results
        val q = URLEncoder.encode("artist:\"$artist\"", "UTF-8")
        val json = executeMusicBrainzRequest("$MB_BASE_URL/artist/?query=$q&fmt=json&limit=6")
        val artists = json?.optJSONArray("artists")
        if (artists != null) {
            for (i in 0 until artists.length()) {
                val id = artists.optJSONObject(i)?.optString("id")
                if (!id.isNullOrBlank() && !mbids.contains(id)) {
                    mbids.add(id)
                }
            }
        }

        return mbids
    }

    private suspend fun fetchArtistImageUrlFromRelations(artistMbid: String): String? {
        val json = executeMusicBrainzRequest("$MB_BASE_URL/artist/$artistMbid?inc=url-rels&fmt=json") ?: return null
        val relations = json.optJSONArray("relations") ?: return null

        var wikidataQid: String? = null
        var wikipediaTitle: String? = null

        for (i in 0 until relations.length()) {
            val rel = relations.optJSONObject(i) ?: continue
            val type = rel.optString("type")
            val target = rel.optJSONObject("url")?.optString("resource") ?: ""

            // 1. Direct Wikimedia Commons image relation
            if (type.equals("image", ignoreCase = true) || target.contains("commons.wikimedia.org/wiki/File:")) {
                val fileName = target.substringAfter("File:")
                if (fileName.isNotBlank()) {
                    val encoded = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20")
                    return "$WIKIMEDIA_COMMONS_FILE_URL/$encoded?width=500"
                }
            }

            // 2. Wikidata relation
            if (wikidataQid == null && (type.equals("wikidata", ignoreCase = true) || target.contains("wikidata.org/wiki/"))) {
                val qid = target.substringAfterLast('/').trim()
                if (qid.startsWith("Q", ignoreCase = true)) {
                    wikidataQid = qid
                }
            }

            // 3. Wikipedia relation
            if (wikipediaTitle == null && (type.equals("wikipedia", ignoreCase = true) || target.contains("wikipedia.org/wiki/"))) {
                val title = target.substringAfterLast('/').trim()
                if (title.isNotBlank()) {
                    wikipediaTitle = title
                }
            }
        }

        // If Wikidata QID found, query Wikidata claims for P18 (image property)
        if (wikidataQid != null) {
            val claimImage = fetchWikidataP18Image(wikidataQid)
            if (claimImage != null) return claimImage
        }

        // If Wikipedia title found, query Wikipedia pageimages API
        if (wikipediaTitle != null) {
            val wikiImage = fetchWikipediaThumbnail(wikipediaTitle)
            if (wikiImage != null) return wikiImage
        }

        return null
    }

    private suspend fun fetchWikidataP18Image(qid: String): String? {
        return try {
            val url = "$WIKIDATA_API_URL?action=wbgetclaims&entity=$qid&property=P18&format=json"
            val body = executeDirectGet(url) ?: return null
            val json = JSONObject(body)
            val claims = json.optJSONObject("claims")?.optJSONArray("P18") ?: return null
            if (claims.length() == 0) return null
            val fileName = claims.optJSONObject(0)
                ?.optJSONObject("mainsnak")
                ?.optJSONObject("datavalue")
                ?.optString("value") ?: return null
            val encoded = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20")
            "$WIKIMEDIA_COMMONS_FILE_URL/$encoded?width=500"
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchWikipediaThumbnail(title: String): String? {
        return try {
            val encTitle = URLEncoder.encode(title, "UTF-8")
            val url = "$WIKIPEDIA_API_URL?action=query&titles=$encTitle&prop=pageimages&format=json&pithumbsize=500"
            val body = executeDirectGet(url) ?: return null
            val json = JSONObject(body)
            val pages = json.optJSONObject("query")?.optJSONObject("pages") ?: return null
            val keys = pages.keys()
            while (keys.hasNext()) {
                val page = pages.optJSONObject(keys.next()) ?: continue
                val thumb = page.optJSONObject("thumbnail")?.optString("source")
                if (!thumb.isNullOrBlank()) return thumb
            }
            null
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchArtistPrimaryReleaseGroup(artistMbid: String): String? {
        val json = executeMusicBrainzRequest("$MB_BASE_URL/release-group?artist=$artistMbid&limit=1&fmt=json") ?: return null
        val rgs = json.optJSONArray("release-groups") ?: return null
        if (rgs.length() == 0) return null
        return rgs.optJSONObject(0)?.optString("id")?.takeIf { it.isNotBlank() }
    }

    // =========================================================================
    // Cover Art Archive Image Fetching
    // =========================================================================

    /**
     * Queries Cover Art Archive for front image of release or release-group.
     * Tries 500px thumbnail first, then 250px, then full front image.
     */
    private suspend fun fetchCaaImage(entityType: String, mbid: String): Bitmap? {
        val sizes = listOf("front-500", "front-250", "front")
        for (size in sizes) {
            val url = "$CAA_BASE_URL/$entityType/$mbid/$size"
            val bitmap = downloadAndDecodeImage(url)
            if (bitmap != null) return bitmap
        }
        return null
    }

    // =========================================================================
    // Network & Image Download Helpers
    // =========================================================================

    private suspend fun executeMusicBrainzRequest(urlString: String): JSONObject? {
        // Enforce rate limiting (~1 req/sec for musicbrainz.org)
        rateLimitMutex.withLock {
            val now = System.currentTimeMillis()
            val diff = now - lastMusicBrainzTime
            if (diff < 1100) {
                delay(1100 - diff)
            }
            lastMusicBrainzTime = System.currentTimeMillis()
        }

        var response = executeRequestWithStatus(urlString)
        // If 503 rate limited, backoff and retry once
        if (response.code == 503) {
            delay(1500)
            response = executeRequestWithStatus(urlString)
        }

        return if (response.code == HttpURLConnection.HTTP_OK && response.body != null) {
            try {
                JSONObject(response.body)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    private fun executeDirectGet(urlString: String): String? {
        val resp = executeRequestWithStatus(urlString)
        return if (resp.code == HttpURLConnection.HTTP_OK) resp.body else null
    }

    private class StatusResponse(val code: Int, val body: String?)

    private fun executeRequestWithStatus(urlString: String): StatusResponse {
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

            val code = conn.responseCode
            val body = if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader(InputStreamReader(conn.inputStream, Charsets.UTF_8)).use { it.readText() }
            } else null
            StatusResponse(code, body)
        } catch (e: Exception) {
            StatusResponse(-1, null)
        } finally {
            conn?.disconnect()
        }
    }

    /**
     * Downloads an image from [urlString] following HTTP redirects (up to 5 hops),
     * and decodes it as a sampled Bitmap.
     */
    private suspend fun downloadAndDecodeImage(urlString: String): Bitmap? = withContext(Dispatchers.IO) {
        val bytes = downloadImageBytesWithRedirects(urlString, maxRedirects = 5) ?: return@withContext null
        decodeSampledBitmap(bytes, MAX_DIMENSION)
    }

    private fun downloadImageBytesWithRedirects(startUrl: String, maxRedirects: Int): ByteArray? {
        var currentUrl = startUrl
        var redirects = 0

        while (redirects < maxRedirects) {
            var conn: HttpURLConnection? = null
            try {
                val url = URL(currentUrl)
                conn = (url.openConnection() as HttpURLConnection).apply {
                    requestMethod = "GET"
                    setRequestProperty("User-Agent", USER_AGENT)
                    setRequestProperty("Accept", "image/*,*/*")
                    connectTimeout = TIMEOUT_MS
                    readTimeout = TIMEOUT_MS
                    instanceFollowRedirects = false // Manually handle redirects across protocols/domains
                }

                val code = conn.responseCode
                if (code == HttpURLConnection.HTTP_MOVED_PERM ||
                    code == HttpURLConnection.HTTP_MOVED_TEMP ||
                    code == HttpURLConnection.HTTP_SEE_OTHER ||
                    code == 307 || code == 308
                ) {
                    val location = conn.getHeaderField("Location")
                    if (location.isNullOrBlank()) return null

                    currentUrl = if (location.startsWith("http://") || location.startsWith("https://")) {
                        location
                    } else {
                        URL(url, location).toString()
                    }
                    redirects++
                    continue
                }

                if (code == HttpURLConnection.HTTP_OK) {
                    val stream = conn.inputStream
                    val buffer = ByteArrayOutputStream()
                    val data = ByteArray(8192)
                    var count: Int
                    while (stream.read(data, 0, data.size).also { count = it } != -1) {
                        buffer.write(data, 0, count)
                    }
                    val result = buffer.toByteArray()
                    return if (result.isNotEmpty()) result else null
                }

                return null
            } catch (e: Exception) {
                return null
            } finally {
                conn?.disconnect()
            }
        }
        return null
    }

    private fun decodeSampledBitmap(data: ByteArray, targetMaxDim: Int): Bitmap? {
        return try {
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeByteArray(data, 0, data.size, options)
            val origW = options.outWidth
            val origH = options.outHeight
            if (origW <= 0 || origH <= 0) return null

            var inSampleSize = 1
            val maxDim = origW.coerceAtLeast(origH)
            while (maxDim / (inSampleSize * 2) >= targetMaxDim) {
                inSampleSize *= 2
            }

            options.inSampleSize = inSampleSize
            options.inJustDecodeBounds = false
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            BitmapFactory.decodeByteArray(data, 0, data.size, options)
        } catch (t: Throwable) {
            null
        }
    }

    // =========================================================================
    // Cache Helpers
    // =========================================================================

    private fun getDiskCacheDir(context: Context): File {
        val dir = File(context.cacheDir, CACHE_SUBDIR)
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    private fun hashKey(key: String): String {
        return try {
            val md = MessageDigest.getInstance("MD5")
            val bytes = md.digest(key.toByteArray(Charsets.UTF_8))
            bytes.joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            key.hashCode().toString()
        }
    }

    private fun loadFromDisk(context: Context, key: String): Bitmap? {
        try {
            val dir = getDiskCacheDir(context)
            val file = File(dir, "${hashKey(key)}.art")
            if (file.exists() && file.length() > 0) {
                file.setLastModified(System.currentTimeMillis())
                return BitmapFactory.decodeFile(file.absolutePath)
            }
        } catch (ignored: Throwable) {}
        return null
    }

    private fun saveToDisk(context: Context, key: String, bitmap: Bitmap) {
        try {
            val dir = getDiskCacheDir(context)
            val hash = hashKey(key)
            val tempFile = File(dir, "$hash.tmp")
            val targetFile = File(dir, "$hash.art")
            FileOutputStream(tempFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
                out.flush()
            }
            tempFile.renameTo(targetFile)
            trimDiskCacheIfNeeded(dir, MAX_DISK_CACHE_SIZE)
        } catch (ignored: Throwable) {}
    }

    private fun trimDiskCacheIfNeeded(dir: File, maxSizeBytes: Long) {
        try {
            val files = dir.listFiles { f -> f.extension == "art" } ?: return
            var totalSize = files.sumOf { it.length() }
            if (totalSize > maxSizeBytes) {
                val sorted = files.sortedBy { it.lastModified() }
                for (f in sorted) {
                    val len = f.length()
                    if (f.delete()) {
                        totalSize -= len
                        if (totalSize <= maxSizeBytes * 0.8) break
                    }
                }
            }
        } catch (ignored: Throwable) {}
    }

    fun clearCaches() {
        memoryCache.evictAll()
        negativeCache.evictAll()
    }

    // =========================================================================
    // Name Cleaning Helpers
    // =========================================================================

    fun cleanAlbumName(name: String): String {
        var a = name.trim()
        if (isUnknownAlbum(a)) return ""
        a = a.replace(
            Regex(
                """\s*\(.*?(deluxe|remaster|edition|bonus|anniversary|special|version|live|explicit|reissue).*?\)""",
                RegexOption.IGNORE_CASE
            ),
            ""
        )
        a = a.replace(Regex("""\s*\[.*?\]"""), "")
        return a.trim().trimEnd('-', ' ')
    }

    fun cleanArtistName(name: String): String {
        var a = name.trim()
        if (isUnknownArtist(a)) return ""
        a = a.replace(Regex("""\s*(feat\.|ft\.).*""", RegexOption.IGNORE_CASE), "")
        val primary = a.split(',', '/', ';').firstOrNull()?.trim()
        return if (!primary.isNullOrEmpty()) primary else a.trim()
    }

    fun cleanTrackName(name: String): String {
        var t = name.trim()
        // Strip audio extensions
        t = t.replace(Regex("""\.(mp3|flac|wav|m4a|aac|ogg|opus|dsf|dff)$""", RegexOption.IGNORE_CASE), "")
        // Strip track numbering at start e.g. "01 - ", "01. ", "1. "
        t = t.replace(Regex("""^\d{1,3}[\s.-]+"""), "")
        // Strip parenthetical tags
        t = t.replace(
            Regex(
                """\s*\(.*?(feat\.|ft\.|official|audio|video|music\s*video|remaster|deluxe|bonus|version|live|explicit|radio\s*edit).*?\)""",
                RegexOption.IGNORE_CASE
            ),
            ""
        )
        t = t.replace(Regex("""\s*\[.*?\]"""), "")
        return t.trim().trimEnd('-', ' ')
    }

    private fun isUnknownAlbum(album: String): Boolean {
        val lower = album.trim().lowercase(Locale.ROOT)
        return lower.isEmpty() ||
                lower == "unknown album" ||
                lower == "unknown" ||
                lower == "<unknown>" ||
                lower == "untitled"
    }

    private fun isUnknownArtist(artist: String): Boolean {
        val lower = artist.trim().lowercase(Locale.ROOT)
        return lower.isEmpty() ||
                lower == "unknown artist" ||
                lower == "unknown" ||
                lower == "<unknown>"
    }
}
