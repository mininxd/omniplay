package xyz.omniplay.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.LruCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.coverart.CoverArtArchiveClient
import xyz.omniplay.model.Song
import xyz.omniplay.ui.MainActivity
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import java.util.Locale

object AlbumArtLoader {

    private const val MAX_DIMENSION = 800
    private const val MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024L // 50MB
    private const val DISK_CACHE_SUBDIR = "album_art_cache"

    // 1. Fast in-memory LRU Cache for decoded bitmaps, keyed by unique song cacheKey
    private val memoryCache: LruCache<String, Bitmap> by lazy {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = (maxMemory / 4).coerceAtLeast(2048)
        object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return (bitmap.byteCount / 1024).coerceAtLeast(1)
            }
        }
    }

    // 2. Pre-rendered Album-level cache: keyed strictly by "artist::album" to prevent cross-artist collisions
    private val albumArtCache: LruCache<String, Bitmap> by lazy {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = (maxMemory / 6).coerceAtLeast(2048)
        object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return (bitmap.byteCount / 1024).coerceAtLeast(1)
            }
        }
    }

    // 3. Negative Cache: prevents repeatedly parsing songs that have NO album art
    private val negativeCache: LruCache<String, Boolean> by lazy {
        LruCache<String, Boolean>(1000)
    }

    // 4. Pre-rendered Artist-level cache: keyed by lowercase artist name
    private val artistArtCache: LruCache<String, Bitmap> by lazy {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = (maxMemory / 8).coerceAtLeast(1024)
        object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return (bitmap.byteCount / 1024).coerceAtLeast(1)
            }
        }
    }

    private var preloadJob: Job? = null

    fun getCacheKey(song: Song): String {
        return if (song.contentUri != Uri.EMPTY) {
            song.contentUri.toString()
        } else if (song.filePath.isNotBlank()) {
            song.filePath
        } else {
            song.id.toString()
        }
    }

    /**
     * Builds a strict, collision-free album key combining artist and album.
     * Returns null for generic or unknown albums to prevent songs from different artists
     * from sharing artwork.
     */
    private fun getAlbumKey(song: Song): String? {
        val album = song.album.trim()
        val artist = song.artist.trim()

        if (album.isEmpty() ||
            album.equals("Unknown Album", ignoreCase = true) ||
            album.equals("Unknown", ignoreCase = true) ||
            album.equals("<unknown>", ignoreCase = true) ||
            album.equals("Untitled", ignoreCase = true)
        ) {
            return null
        }

        if (artist.isEmpty() ||
            artist.equals("Unknown Artist", ignoreCase = true) ||
            artist.equals("Unknown", ignoreCase = true) ||
            artist.equals("<unknown>", ignoreCase = true)
        ) {
            return null
        }

        val cleanAlbum = album.lowercase(Locale.ROOT)
        val cleanArtist = artist.lowercase(Locale.ROOT)
        return "$cleanArtist::$cleanAlbum"
    }

    /**
     * Synchronously returns cached album art in memory specifically for [song].
     * Never returns another song's artwork to avoid wrong/randomized art display.
     */
    fun getCachedAlbumArt(song: Song): Bitmap? {
        val key = getCacheKey(song)
        return memoryCache.get(key)
    }

    fun getCachedAlbumArt(songId: Long): Bitmap? {
        return null
    }

    fun getAlbumArt(albumName: String, artistName: String? = null): Bitmap? {
        if (albumName.isBlank() || albumName.equals("Unknown Album", ignoreCase = true)) return null
        val cleanAlbum = albumName.trim().lowercase(Locale.ROOT)
        if (artistName != null && artistName.isNotBlank() && !artistName.equals("Unknown Artist", ignoreCase = true)) {
            val cleanArtist = artistName.trim().lowercase(Locale.ROOT)
            val key = "$cleanArtist::$cleanAlbum"
            val bitmap = albumArtCache.get(key)
            if (bitmap != null) return bitmap
        }
        return albumArtCache.snapshot().entries.firstOrNull { it.key.endsWith("::$cleanAlbum") }?.value
    }

    fun clearMemoryCache() {
        memoryCache.evictAll()
        albumArtCache.evictAll()
        artistArtCache.evictAll()
        negativeCache.evictAll()
        CoverArtArchiveClient.clearCaches()
    }

    /**
     * Synchronously returns cached artist image in memory for [artistName].
     */
    fun getCachedArtistArt(artistName: String): Bitmap? {
        if (artistName.isBlank() || isUnknownArtist(artistName)) return null
        val cleanArtist = artistName.trim().lowercase(Locale.ROOT)
        return artistArtCache.get(cleanArtist)
    }

    /**
     * Loads artist portrait / photo asynchronously.
     * Checks in-memory cache -> disk cache -> online (MusicBrainz / Wikimedia / CAA) -> representative song's album art.
     */
    suspend fun loadArtistArt(context: Context, artistName: String, representativeSong: Song? = null): Bitmap? = withContext(Dispatchers.IO) {
        if (artistName.isBlank() || isUnknownArtist(artistName)) return@withContext null
        val cleanArtist = artistName.trim().lowercase(Locale.ROOT)

        val cachedMem = artistArtCache.get(cleanArtist)
        if (cachedMem != null) return@withContext cachedMem

        val diskKey = "artist_$cleanArtist"
        val diskBitmap = loadFromDiskCache(context, diskKey)
        if (diskBitmap != null) {
            artistArtCache.put(cleanArtist, diskBitmap)
            return@withContext diskBitmap
        }

        if (isOnlineArtEnabled(context)) {
            val onlineArt = CoverArtArchiveClient.fetchArtistImage(context, artistName, representativeSong)
            if (onlineArt != null) {
                artistArtCache.put(cleanArtist, onlineArt)
                saveToDiskCache(context, diskKey, onlineArt)
                return@withContext onlineArt
            }
        }

        if (representativeSong != null) {
            val repBitmap = loadAlbumArt(context, representativeSong)
            if (repBitmap != null) {
                artistArtCache.put(cleanArtist, repBitmap)
                saveToDiskCache(context, diskKey, repBitmap)
                return@withContext repBitmap
            }
        }

        null
    }

    fun isOnlineArtEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(MainActivity.KEY_FETCH_ONLINE_ARTWORK, true)
    }

    private fun isUnknownArtist(artist: String): Boolean {
        val lower = artist.trim().lowercase(Locale.ROOT)
        return lower.isEmpty() ||
                lower == "unknown artist" ||
                lower == "unknown" ||
                lower == "<unknown>"
    }

    /**
     * Pre-renders and pre-loads album art for all songs in the background.
     * Each song is loaded individually to ensure its authentic embedded artwork is preserved.
     */
    fun preloadAll(context: Context, songs: List<Song>, scope: CoroutineScope) {
        preloadJob?.cancel()
        preloadJob = scope.launch(Dispatchers.IO) {
            if (songs.isEmpty()) return@launch

            for (song in songs) {
                val key = getCacheKey(song)
                if (memoryCache.get(key) != null || negativeCache.get(key) == true) {
                    continue
                }
                loadAlbumArt(context, song)
            }
        }
    }

    suspend fun loadAlbumArt(context: Context, song: Song): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val cacheKey = getCacheKey(song)

            // Step 1: Check in-memory LRU cache
            val cachedMem = memoryCache.get(cacheKey)
            if (cachedMem != null) {
                return@withContext cachedMem
            }

            // Step 2: Check persistent disk LRU cache
            val diskBitmap = loadFromDiskCache(context, cacheKey)
            if (diskBitmap != null) {
                memoryCache.put(cacheKey, diskBitmap)
                val albumKey = getAlbumKey(song)
                if (albumKey != null) {
                    albumArtCache.put(albumKey, diskBitmap)
                }
                return@withContext diskBitmap
            }

            // Step 3: Check negative cache (known to have no art)
            if (negativeCache.get(cacheKey) == true) {
                return@withContext null
            }

            var decodedBitmap: Bitmap? = null

            // Step 4: Extract embedded artwork directly from audio file (TOP PRIORITY)
            if (song.contentUri != Uri.EMPTY) {
                val retriever = MediaMetadataRetriever()
                try {
                    var loaded = false
                    try {
                        retriever.setDataSource(context, song.contentUri)
                        loaded = true
                    } catch (ignored: Throwable) {}

                    if (!loaded) {
                        try {
                            context.contentResolver.openAssetFileDescriptor(song.contentUri, "r")?.use { afd ->
                                if (afd.declaredLength < 0) {
                                    retriever.setDataSource(afd.fileDescriptor)
                                } else {
                                    retriever.setDataSource(afd.fileDescriptor, afd.startOffset, afd.declaredLength)
                                }
                                loaded = true
                            }
                        } catch (ignored: Throwable) {}
                    }

                    if (!loaded) {
                        try {
                            context.contentResolver.openFileDescriptor(song.contentUri, "r")?.use { pfd ->
                                retriever.setDataSource(pfd.fileDescriptor)
                                loaded = true
                            }
                        } catch (ignored: Throwable) {}
                    }

                    if (loaded) {
                        val rawPicture = retriever.embeddedPicture
                        if (rawPicture != null && rawPicture.isNotEmpty()) {
                            decodedBitmap = decodeSampledBitmap(rawPicture, MAX_DIMENSION)
                        }
                    }
                } catch (ignored: Throwable) {
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Throwable) {}
                }
            }

            // Step 5: Try extracting FLAC picture directly via FlacHeaderParser if retriever failed
            if (decodedBitmap == null && (song.format.equals("FLAC", ignoreCase = true) || song.contentUri.toString().endsWith(".flac", ignoreCase = true) || song.filePath.endsWith(".flac", ignoreCase = true))) {
                try {
                    context.contentResolver.openInputStream(song.contentUri)?.use { stream ->
                        val picData = FlacHeaderParser.extractPicture(stream)
                        if (picData != null && picData.isNotEmpty()) {
                            decodedBitmap = decodeSampledBitmap(picData, MAX_DIMENSION)
                        }
                    }
                } catch (ignored: Throwable) {}

                if (decodedBitmap == null && song.filePath.isNotBlank()) {
                    try {
                        val file = File(song.filePath)
                        if (file.exists()) {
                            file.inputStream().use { stream ->
                                val picData = FlacHeaderParser.extractPicture(stream)
                                if (picData != null && picData.isNotEmpty()) {
                                    decodedBitmap = decodeSampledBitmap(picData, MAX_DIMENSION)
                                }
                            }
                        }
                    } catch (ignored: Throwable) {}
                }
            }

            // Step 6: Fallback to file path embedded picture if contentUri didn't yield artwork
            if (decodedBitmap == null && song.filePath.isNotBlank()) {
                val retriever = MediaMetadataRetriever()
                try {
                    retriever.setDataSource(song.filePath)
                    val rawPicture = retriever.embeddedPicture
                    if (rawPicture != null && rawPicture.isNotEmpty()) {
                        decodedBitmap = decodeSampledBitmap(rawPicture, MAX_DIMENSION)
                    }
                } catch (ignored: Throwable) {
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Throwable) {}
                }
            }

            // Step 7: Check local directory sidecar image (cover.jpg, folder.jpg, etc.) in the audio file's folder
            if (decodedBitmap == null && song.filePath.isNotBlank()) {
                try {
                    val parentDir = File(song.filePath).parentFile
                    if (parentDir != null && parentDir.exists() && parentDir.isDirectory) {
                        val sidecarNames = listOf("cover.jpg", "cover.png", "folder.jpg", "folder.png", "album.jpg", "front.jpg", "cover.jpeg", "folder.jpeg")
                        for (name in sidecarNames) {
                            val sidecarFile = File(parentDir, name)
                            if (sidecarFile.exists() && sidecarFile.canRead() && sidecarFile.length() > 0) {
                                val sidecarBitmap = decodeSampledBitmapFromFile(sidecarFile.absolutePath, MAX_DIMENSION)
                                if (sidecarBitmap != null) {
                                    decodedBitmap = sidecarBitmap
                                    break
                                }
                            }
                        }
                    }
                } catch (ignored: Throwable) {}
            }

            // Step 8: Fallback to verified album art of the exact same artist and album
            if (decodedBitmap == null) {
                val albumKey = getAlbumKey(song)
                if (albumKey != null) {
                    val albumArt = albumArtCache.get(albumKey)
                    if (albumArt != null) {
                        decodedBitmap = albumArt
                    }
                }
            }

            // Step 9: Final fallback: MediaStore album art URI (only for verified known artist and album)
            if (decodedBitmap == null && song.albumArtUri != null && getAlbumKey(song) != null) {
                try {
                    decodedBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, song.albumArtUri)) { decoder, info, _ ->
                            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                            decoder.isMutableRequired = false
                            val maxDim = info.size.width.coerceAtLeast(info.size.height)
                            if (maxDim > MAX_DIMENSION) {
                                val scale = MAX_DIMENSION.toFloat() / maxDim
                                decoder.setTargetSize((info.size.width * scale).toInt(), (info.size.height * scale).toInt())
                            }
                        }
                    } else {
                        @Suppress("DEPRECATION")
                        val orig = MediaStore.Images.Media.getBitmap(context.contentResolver, song.albumArtUri)
                        if (orig != null && (orig.width > MAX_DIMENSION || orig.height > MAX_DIMENSION)) {
                            val maxDim = orig.width.coerceAtLeast(orig.height)
                            val scale = MAX_DIMENSION.toFloat() / maxDim
                            val scaled = Bitmap.createScaledBitmap(orig, (orig.width * scale).toInt(), (orig.height * scale).toInt(), true)
                            if (scaled != orig) orig.recycle()
                            scaled
                        } else {
                            orig
                        }
                    }
                } catch (ignored: Throwable) {}
            }

            // Step 10: Online fallback via Cover Art Archive API & MusicBrainz (when there is no image at all in the music file)
            if (decodedBitmap == null && isOnlineArtEnabled(context)) {
                try {
                    decodedBitmap = CoverArtArchiveClient.fetchCoverArt(context, song)
                } catch (ignored: Throwable) {}
            }

            // Step 11: Validate decoded bitmap and write to caches
            val finalBitmap = decodedBitmap
            if (finalBitmap != null) {
                if (isSolidOrBlankBitmap(finalBitmap)) {
                    negativeCache.put(cacheKey, true)
                    return@withContext null
                }
                memoryCache.put(cacheKey, finalBitmap)
                val albumKey = getAlbumKey(song)
                if (albumKey != null) {
                    albumArtCache.put(albumKey, finalBitmap)
                    saveToDiskCache(context, "album_$albumKey", finalBitmap)
                }
                saveToDiskCache(context, cacheKey, finalBitmap)
                return@withContext finalBitmap
            }

            negativeCache.put(cacheKey, true)
            null
        } catch (t: Throwable) {
            null
        }
    }

    private fun decodeSampledBitmapFromFile(filePath: String, targetMaxDim: Int): Bitmap? {
        return try {
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeFile(filePath, options)
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
            BitmapFactory.decodeFile(filePath, options)
        } catch (t: Throwable) {
            null
        }
    }

    private fun decodeSampledBitmap(data: ByteArray, targetMaxDim: Int): Bitmap? {
        try {
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
            return BitmapFactory.decodeByteArray(data, 0, data.size, options)
        } catch (t: Throwable) {
            return null
        }
    }

    private fun getDiskCacheDir(context: Context): File {
        val dir = File(context.cacheDir, DISK_CACHE_SUBDIR)
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

    private fun loadFromDiskCache(context: Context, key: String): Bitmap? {
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

    private fun saveToDiskCache(context: Context, key: String, bitmap: Bitmap) {
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

    private fun isSolidOrBlankBitmap(bitmap: Bitmap): Boolean {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && bitmap.config == Bitmap.Config.HARDWARE) {
                return false
            }
            val w = bitmap.width
            val h = bitmap.height
            if (w <= 0 || h <= 0) return true

            val samples = intArrayOf(
                bitmap.getPixel(w / 2, h / 2),
                bitmap.getPixel(w / 4, h / 4),
                bitmap.getPixel(3 * w / 4, 3 * w / 4),
                bitmap.getPixel(w / 4, 3 * w / 4),
                bitmap.getPixel(3 * w / 4, h / 4),
                bitmap.getPixel(w / 2, h / 4),
                bitmap.getPixel(w / 2, 3 * w / 4)
            )
            val first = samples[0]
            val isDarkOrEmpty = (first == 0xFF000000.toInt() || (first ushr 24) == 0)
            val allSame = samples.all { it == first }
            return allSame && isDarkOrEmpty
        } catch (t: Throwable) {
            return false
        }
    }
}
