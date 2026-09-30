package xyz.omniplay.ui

import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import android.view.View
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.ExtractorsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityAudioPlayerBinding
import xyz.omniplay.dsd.DsdExtractor
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.util.AudioInfoExtractor
import xyz.omniplay.util.AudioTrackInfo
import xyz.omniplay.util.FlacHeaderParser
import java.io.File
import java.util.Locale

/**
 * Standalone Audio Player Activity for playing individual audio files opened
 * from file managers, downloaders, or external apps.
 * Features 32-bit float Hi-Res audio output, DSD/FLAC native extraction,
 * Squiggly progress line seeking, and embedded album art rendering.
 */
@OptIn(UnstableApi::class)
class AudioPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAudioPlayerBinding
    private val handler = Handler(Looper.getMainLooper())

    private var player: ExoPlayer? = null
    private var isUserTrackingSlider = false
    private var isBecomingNoisyRegistered = false

    private var audioTrackInfo: AudioTrackInfo? = null
    private var durationMs = 0L

    private val becomingNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
                player?.pause()
            }
        }
    }

    private val progressUpdateRunnable = object : Runnable {
        override fun run() {
            val p = player
            if (::binding.isInitialized && p != null && !isUserTrackingSlider) {
                val current = p.currentPosition.coerceAtLeast(0L)
                binding.playbackSlider.setProgress(current)
                binding.currentTimeText.text = Song.formatTime(current)

                val dur = p.duration
                if (dur > 0L && dur != durationMs) {
                    durationMs = dur
                    binding.totalTimeText.text = Song.formatTime(dur)
                    binding.playbackSlider.setDuration(dur)
                }
            }
            handler.postDelayed(this, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_dark)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.background_dark)

        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pause any background playback running in Omniplay
        pauseBackgroundMusic()

        val audioUri = resolveAudioUri(intent)
        if (audioUri == null) {
            Toast.makeText(this, "No audio file provided", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupControls()
        loadAudioMetadataAndArt(audioUri)
        initPlayer(audioUri)
        registerNoisyReceiver()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        val audioUri = resolveAudioUri(intent)
        if (audioUri != null) {
            player?.stop()
            loadAudioMetadataAndArt(audioUri)
            initPlayer(audioUri)
        }
    }

    private fun resolveAudioUri(intent: Intent?): Uri? {
        if (intent == null) return null
        return intent.data ?: intent.clipData?.let {
            if (it.itemCount > 0) it.getItemAt(0).uri else null
        }
    }

    private fun pauseBackgroundMusic() {
        try {
            val pauseIntent = Intent(this, PlaybackService::class.java).apply {
                action = PlaybackService.ACTION_PAUSE
            }
            startService(pauseIntent)
        } catch (ignored: Exception) {}
    }

    private fun setupControls() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnPlayPause.setOnClickListener {
            val p = player ?: return@setOnClickListener
            if (p.isPlaying) {
                p.pause()
            } else {
                if (p.playbackState == Player.STATE_ENDED) {
                    p.seekTo(0)
                }
                p.play()
            }
        }

        binding.btnReplay10.setOnClickListener {
            seekBy(-10_000L)
        }

        binding.btnForward10.setOnClickListener {
            seekBy(10_000L)
        }

        binding.playbackSlider.seekListener = object : SquigglySeekBar.OnSeekListener {
            override fun onStartTracking() {
                isUserTrackingSlider = true
            }

            override fun onProgressChanged(progressMs: Long, fromUser: Boolean, isCancelled: Boolean) {
                if (fromUser) {
                    binding.currentTimeText.text = Song.formatTime(progressMs)
                }
            }

            override fun onStopTracking(progressMs: Long, isCancelled: Boolean) {
                isUserTrackingSlider = false
                if (!isCancelled) {
                    player?.seekTo(progressMs)
                }
            }
        }
    }

    private fun seekBy(offsetMs: Long) {
        val p = player ?: return
        val current = p.currentPosition.coerceAtLeast(0L)
        val maxDur = if (p.duration > 0L) p.duration else durationMs
        val target = if (maxDur > 0L) {
            (current + offsetMs).coerceIn(0L, maxDur)
        } else {
            (current + offsetMs).coerceAtLeast(0L)
        }
        p.seekTo(target)
        binding.playbackSlider.setProgress(target)
        binding.currentTimeText.text = Song.formatTime(target)
    }

    private fun loadAudioMetadataAndArt(uri: Uri) {
        binding.loadingProgress.visibility = View.VISIBLE

        val displayName = getAudioDisplayName(uri)
        val fallbackTitle = if (displayName.contains('.')) {
            displayName.substringBeforeLast('.')
        } else {
            displayName.ifEmpty { "Audio Track" }
        }

        // Show filename immediately while deep metadata parses
        binding.songTitleText.text = fallbackTitle
        binding.songArtistText.text = "Loading..."
        binding.songTitleText.isSelected = true

        val extension = displayName.substringAfterLast('.', "").lowercase(Locale.ROOT)

        lifecycleScope.launch(Dispatchers.IO) {
            var title = fallbackTitle
            var artist = "Unknown Artist"
            var album = "Unknown Album"
            var dur = 0L
            var albumArtBitmap: Bitmap? = null

            // 1. If FLAC, use high-precision FlacHeaderParser
            if (extension == "flac" || uri.toString().endsWith(".flac", ignoreCase = true)) {
                try {
                    contentResolver.openInputStream(uri)?.use { stream ->
                        FlacHeaderParser.parse(stream)?.let { flac ->
                            if (!flac.title.isNullOrBlank()) title = flac.title
                            if (!flac.artist.isNullOrBlank()) artist = flac.artist
                            if (!flac.album.isNullOrBlank()) album = flac.album
                            if (flac.durationMs > 0L) dur = flac.durationMs
                            if (flac.pictureData != null && flac.pictureData.isNotEmpty()) {
                                albumArtBitmap = BitmapFactory.decodeByteArray(flac.pictureData, 0, flac.pictureData.size)
                            }
                        }
                    }
                } catch (ignored: Throwable) {}
            }

            // 2. Fallback to MediaMetadataRetriever if title/artist or art not found
            if (title == fallbackTitle || artist == "Unknown Artist" || albumArtBitmap == null) {
                val retriever = MediaMetadataRetriever()
                try {
                    var loaded = false
                    try {
                        retriever.setDataSource(this@AudioPlayerActivity, uri)
                        loaded = true
                    } catch (ignored: Throwable) {}

                    if (!loaded) {
                        try {
                            contentResolver.openFileDescriptor(uri, "r")?.use { pfd ->
                                retriever.setDataSource(pfd.fileDescriptor)
                                loaded = true
                            }
                        } catch (ignored: Throwable) {}
                    }

                    if (loaded) {
                        val rawTitle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                        val rawArtist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                        val rawAlbum = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
                        val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                        val parsedDur = durationStr?.toLongOrNull() ?: 0L
                        if (dur <= 0L && parsedDur > 0L) dur = parsedDur

                        if (!rawTitle.isNullOrBlank() && rawTitle != "<unknown>") title = rawTitle.trim()
                        if (!rawArtist.isNullOrBlank() && rawArtist != "<unknown>") artist = rawArtist.trim()
                        if (!rawAlbum.isNullOrBlank() && rawAlbum != "<unknown>") album = rawAlbum.trim()

                        if (albumArtBitmap == null) {
                            val pic = retriever.embeddedPicture
                            if (pic != null && pic.isNotEmpty()) {
                                albumArtBitmap = BitmapFactory.decodeByteArray(pic, 0, pic.size)
                            }
                        }
                    }
                } catch (ignored: Throwable) {
                } finally {
                    try {
                        retriever.release()
                    } catch (ignored: Throwable) {}
                }
            }

            // 3. Audio format and resolution detection
            val fallbackFormat = if (extension.isNotEmpty() && extension.length in 2..5) {
                extension.uppercase(Locale.ROOT)
            } else {
                "AUDIO"
            }
            val audioInfo = AudioInfoExtractor.extractFromUri(this@AudioPlayerActivity, uri, fallbackFormat)

            withContext(Dispatchers.Main) {
                audioTrackInfo = audioInfo
                if (dur > 0L) {
                    durationMs = dur
                    binding.totalTimeText.text = Song.formatTime(dur)
                    binding.playbackSlider.setDuration(dur)
                }

                binding.songTitleText.text = title
                binding.songTitleText.isSelected = true

                binding.songArtistText.text = if (album.isNotEmpty() && album != "Unknown Album") {
                    "$artist • $album"
                } else {
                    artist
                }

                val formatStr = audioInfo.format.ifEmpty { fallbackFormat }
                binding.topFormatBadge.text = formatStr
                binding.topFormatBadge.visibility = View.VISIBLE

                val qualityStr = audioInfo.formatQualityString()
                if (qualityStr.isNotEmpty()) {
                    binding.badgeQuality.text = qualityStr
                    binding.badgeQuality.visibility = View.VISIBLE
                } else {
                    binding.badgeQuality.visibility = View.GONE
                }

                if (audioInfo.checkHiRes() || audioInfo.isHiRes) {
                    binding.badgeHires.visibility = View.VISIBLE
                } else {
                    binding.badgeHires.visibility = View.GONE
                }

                if (albumArtBitmap != null) {
                    binding.albumArtImage.setImageBitmap(albumArtBitmap)
                } else {
                    binding.albumArtImage.setImageResource(R.drawable.default_album_art)
                }

                binding.loadingProgress.visibility = View.GONE
            }
        }
    }

    private fun getAudioDisplayName(uri: Uri): String {
        try {
            if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
                contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (nameIndex != -1) {
                            val name = cursor.getString(nameIndex)
                            if (!name.isNullOrBlank()) return name
                        }
                    }
                }
            }
        } catch (ignored: Exception) {}

        return uri.lastPathSegment?.substringAfterLast('/')?.takeIf { it.isNotBlank() } ?: "Audio Track"
    }

    private fun initPlayer(uri: Uri) {
        player?.release()

        val renderersFactory = DefaultRenderersFactory(applicationContext)
            .setEnableAudioFloatOutput(true) // Native 32-bit float Hi-Res output
            .setEnableAudioTrackPlaybackParams(true)

        val extractorsFactory = ExtractorsFactory {
            arrayOf(
                DsdExtractor(),
                *DefaultExtractorsFactory().setConstantBitrateSeekingEnabled(true).createExtractors()
            )
        }
        val mediaSourceFactory = DefaultMediaSourceFactory(applicationContext, extractorsFactory)

        val exo = ExoPlayer.Builder(applicationContext, renderersFactory)
            .setMediaSourceFactory(mediaSourceFactory)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                true
            )
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()

        exo.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> {
                        binding.loadingProgress.visibility = View.GONE
                        val dur = exo.duration
                        if (dur > 0L) {
                            durationMs = dur
                            binding.totalTimeText.text = Song.formatTime(dur)
                            binding.playbackSlider.setDuration(dur)
                        }
                        updatePlayPauseUI(exo.isPlaying)
                    }
                    Player.STATE_BUFFERING -> {
                        binding.loadingProgress.visibility = View.VISIBLE
                    }
                    Player.STATE_ENDED -> {
                        binding.loadingProgress.visibility = View.GONE
                        updatePlayPauseUI(false)
                        binding.playbackSlider.setPlaying(false)
                        binding.playbackSlider.setProgress(durationMs)
                        binding.currentTimeText.text = Song.formatTime(durationMs)
                    }
                    Player.STATE_IDLE -> {
                        updatePlayPauseUI(false)
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlayPauseUI(isPlaying)
                binding.playbackSlider.setPlaying(isPlaying)
            }

            override fun onPlayerError(error: PlaybackException) {
                binding.loadingProgress.visibility = View.GONE
                Toast.makeText(this@AudioPlayerActivity, "Playback error: ${error.message}", Toast.LENGTH_SHORT).show()
                updatePlayPauseUI(false)
            }
        })

        player = exo

        val mediaItem = MediaItem.fromUri(uri)
        exo.setMediaItem(mediaItem)
        exo.prepare()
        exo.playWhenReady = true

        handler.post(progressUpdateRunnable)
    }

    private fun updatePlayPauseUI(isPlaying: Boolean) {
        if (isPlaying) {
            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
            binding.btnPlayPause.contentDescription = getString(R.string.pause)
        } else {
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            binding.btnPlayPause.contentDescription = getString(R.string.play)
        }
    }

    private fun registerNoisyReceiver() {
        if (!isBecomingNoisyRegistered) {
            val filter = IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(becomingNoisyReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
            } else {
                registerReceiver(becomingNoisyReceiver, filter)
            }
            isBecomingNoisyRegistered = true
        }
    }

    private fun unregisterNoisyReceiver() {
        if (isBecomingNoisyRegistered) {
            try {
                unregisterReceiver(becomingNoisyReceiver)
            } catch (ignored: Exception) {}
            isBecomingNoisyRegistered = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(progressUpdateRunnable)
        unregisterNoisyReceiver()
        player?.stop()
        player?.release()
        player = null
    }
}
