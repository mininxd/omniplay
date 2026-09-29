package xyz.omniplay.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.omniplay.model.Song

object AlbumArtLoader {

    suspend fun loadAlbumArt(context: Context, song: Song): Bitmap? = withContext(Dispatchers.IO) {
        // 1. Try extracting embedded picture directly from audio file (works for SAF URIs and Content URIs)
        try {
            if (song.contentUri != Uri.EMPTY) {
                val retriever = MediaMetadataRetriever()
                try {
                    var loaded = false
                    try {
                        context.contentResolver.openFileDescriptor(song.contentUri, "r")?.use { pfd ->
                            retriever.setDataSource(pfd.fileDescriptor)
                            loaded = true
                        }
                    } catch (ignored: Exception) {}

                    if (!loaded) {
                        try {
                            retriever.setDataSource(context, song.contentUri)
                            loaded = true
                        } catch (ignored: Exception) {}
                    }

                    if (loaded) {
                        val rawPicture = retriever.embeddedPicture
                        if (rawPicture != null && rawPicture.isNotEmpty()) {
                            val bitmap = BitmapFactory.decodeByteArray(rawPicture, 0, rawPicture.size)
                            if (bitmap != null) {
                                return@withContext bitmap
                            }
                        }
                    }
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Exception) {}
                }
            }
        } catch (ignored: Exception) {}

        // 2. Try MediaStore album art URI
        if (song.albumArtUri != null) {
            try {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, song.albumArtUri))
                } else {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(context.contentResolver, song.albumArtUri)
                }
                if (bitmap != null) {
                    return@withContext bitmap
                }
            } catch (ignored: Exception) {}
        }

        // 3. Fallback: try file path if contentUri didn't yield
        if (song.filePath.isNotBlank()) {
            try {
                val retriever = MediaMetadataRetriever()
                try {
                    retriever.setDataSource(song.filePath)
                    val rawPicture = retriever.embeddedPicture
                    if (rawPicture != null && rawPicture.isNotEmpty()) {
                        val bitmap = BitmapFactory.decodeByteArray(rawPicture, 0, rawPicture.size)
                        if (bitmap != null) {
                            return@withContext bitmap
                        }
                    }
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Exception) {}
                }
            } catch (ignored: Exception) {}
        }

        null
    }
}
