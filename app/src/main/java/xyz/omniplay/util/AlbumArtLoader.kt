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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.omniplay.model.Song

object AlbumArtLoader {

    private val memoryCache: LruCache<Long, Bitmap> by lazy {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = (maxMemory / 8).coerceAtLeast(1024)
        object : LruCache<Long, Bitmap>(cacheSize) {
            override fun sizeOf(key: Long, bitmap: Bitmap): Int {
                return (bitmap.byteCount / 1024).coerceAtLeast(1)
            }
        }
    }

    fun getCachedAlbumArt(songId: Long): Bitmap? {
        return memoryCache.get(songId)
    }

    suspend fun loadAlbumArt(context: Context, song: Song): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val cached = memoryCache.get(song.id)
            if (cached != null) {
                return@withContext cached
            }

            var decodedBitmap: Bitmap? = null

            // 1. Try MediaStore album art URI first if available (official indexed artwork)
            if (song.albumArtUri != null) {
                try {
                    decodedBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, song.albumArtUri)) { decoder, _, _ ->
                            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                            decoder.isMutableRequired = false
                        }
                    } else {
                        @Suppress("DEPRECATION")
                        MediaStore.Images.Media.getBitmap(context.contentResolver, song.albumArtUri)
                    }
                } catch (ignored: Throwable) {}
            }

            // 2. Try extracting embedded ID3 / FLAC picture directly from audio content URI
            if (decodedBitmap == null && song.contentUri != Uri.EMPTY) {
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
                            decodedBitmap = BitmapFactory.decodeByteArray(rawPicture, 0, rawPicture.size)
                        }
                    }
                } catch (ignored: Throwable) {
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Throwable) {}
                }
            }

            // 3. Try extracting FLAC picture directly via FlacHeaderParser if retriever failed
            if (decodedBitmap == null && (song.format.equals("FLAC", ignoreCase = true) || song.contentUri.toString().endsWith(".flac", ignoreCase = true) || song.filePath.endsWith(".flac", ignoreCase = true))) {
                try {
                    context.contentResolver.openInputStream(song.contentUri)?.use { stream ->
                        val picData = FlacHeaderParser.extractPicture(stream)
                        if (picData != null && picData.isNotEmpty()) {
                            decodedBitmap = BitmapFactory.decodeByteArray(picData, 0, picData.size)
                        }
                    }
                } catch (ignored: Throwable) {}

                if (decodedBitmap == null && song.filePath.isNotBlank()) {
                    try {
                        val file = java.io.File(song.filePath)
                        if (file.exists()) {
                            file.inputStream().use { stream ->
                                val picData = FlacHeaderParser.extractPicture(stream)
                                if (picData != null && picData.isNotEmpty()) {
                                    decodedBitmap = BitmapFactory.decodeByteArray(picData, 0, picData.size)
                                }
                            }
                        }
                    } catch (ignored: Throwable) {}
                }
            }

            // 4. Fallback: try file path if contentUri didn't yield artwork
            if (decodedBitmap == null && song.filePath.isNotBlank()) {
                val retriever = MediaMetadataRetriever()
                try {
                    retriever.setDataSource(song.filePath)
                    val rawPicture = retriever.embeddedPicture
                    if (rawPicture != null && rawPicture.isNotEmpty()) {
                        decodedBitmap = BitmapFactory.decodeByteArray(rawPicture, 0, rawPicture.size)
                    }
                } catch (ignored: Throwable) {
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Throwable) {}
                }
            }

            // 4. Validate decoded bitmap: reject corrupted or solid black/blank placeholders
            if (decodedBitmap != null) {
                if (isSolidOrBlankBitmap(decodedBitmap)) {
                    return@withContext null
                }
                memoryCache.put(song.id, decodedBitmap)
                return@withContext decodedBitmap
            }

            null
        } catch (t: Throwable) {
            null
        }
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
