package xyz.omniplay.ui

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SeekParameters
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.ExtractorsFactory
import androidx.media3.extractor.mp4.Mp4Extractor
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory
import androidx.media3.extractor.ts.TsExtractor
import androidx.media3.ui.AspectRatioFrameLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityVideoPlayerBinding
import xyz.omniplay.service.PlaybackService
import java.io.InputStream
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * High-performance, lightweight (<5MB) video player powered by Media3 ExoPlayer.
 * Features intelligent container stream sniffing to flawlessly play MP4, AV1, MPEG-TS,
 * MPEG-PS, MKV, AVI, and mislabeled containers (like MP4/AV1 in .mpeg files) without heavy native binaries.
 */
@OptIn(UnstableApi::class)
class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private val handler = Looper.myLooper()?.let { Handler(it) } ?: Handler(Looper.getMainLooper())

    private var player: ExoPlayer? = null
    private var audioManager: AudioManager? = null

    private var isUserTrackingSeekBar = false
    private var areControlsVisible = true
    private var savedPlaybackPosition = 0L
    private var wasPlayingBeforePause = true

    // Touch gesture slider variables (30% left brightness, 30% right volume, horizontal seek)
    private var touchSlop = 0
    private var activeTouchZone = TouchZone.NONE
    private var touchDownX = 0f
    private var touchDownY = 0f
    private var isDraggingSlider = false
    private var initialBrightness = 0.5f
    private var initialVolume = 0f
    private var maxVolume = 15f
    private var initialSeekPosition = 0L
    private var targetSeekPosition = 0L

    private enum class TouchZone {
        NONE, BRIGHTNESS, VOLUME, SEEK
    }

    private val hideControlsRunnable = Runnable {
        hideControls()
    }

    private val hideGestureIndicatorRunnable = Runnable {
        hideGestureIndicator()
    }

    private val progressUpdateRunnable = object : Runnable {
        override fun run() {
            val p = player
            if (::binding.isInitialized && p != null && !isUserTrackingSeekBar) {
                val current = p.currentPosition.coerceAtLeast(0L)
                val duration = p.duration
                val total = if (duration > 0L) duration else 0L
                updateProgressUI(current, total)
            }
            handler.postDelayed(this, 250)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make window fullscreen & permanently hide status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        keepStatusBarsHidden()

        touchSlop = ViewConfiguration.get(this).scaledTouchSlop
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Pause any background audio playback currently running in Omniplay
        pauseBackgroundMusic()

        val videoUri = resolveVideoUri(intent)
        if (videoUri == null) {
            Toast.makeText(this, "No video file provided", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupVideoTitle(videoUri)
        initializePlayer(videoUri)
        setupControls()
        setupGestures()
    }

    private fun resolveVideoUri(intent: Intent?): Uri? {
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

    private fun setupVideoTitle(uri: Uri) {
        val title = getVideoTitle(uri)
        binding.videoTitleText.text = title
    }

    private fun getVideoTitle(uri: Uri): String {
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

        return uri.lastPathSegment?.substringAfterLast('/')?.takeIf { it.isNotBlank() } ?: "Video"
    }

    /**
     * Inspects the stream magic bytes directly to detect the true media container,
     * resolving cases where MP4/AV1 or WebM files are misnamed as .mpeg, .ts, etc.
     */
    private fun sniffVideoContainer(context: Context, uri: Uri): String? {
        return try {
            context.contentResolver.openInputStream(uri)?.use { stream ->
                val header = ByteArray(64)
                val read = stream.read(header)
                if (read >= 8) {
                    // Check MP4 / QuickTime: "ftyp", "moov", "mdat", "wide", "free", "skip" at offset 4
                    val boxType = String(header, 4, 4, Charsets.US_ASCII)
                    if (boxType in setOf("ftyp", "moov", "mdat", "wide", "free", "skip")) {
                        return MimeTypes.VIDEO_MP4
                    }
                    // Check Matroska / WebM: EBML header 0x1A 0x45 0xDF 0xA3
                    if (header[0] == 0x1A.toByte() && header[1] == 0x45.toByte() &&
                        header[2] == 0xDF.toByte() && header[3] == 0xA3.toByte()
                    ) {
                        return MimeTypes.VIDEO_WEBM
                    }
                    // Check MPEG-TS: Sync byte 0x47
                    if (header[0] == 0x47.toByte()) {
                        return MimeTypes.VIDEO_MP2T
                    }
                    // Check MPEG-PS: 0x00 0x00 0x01 0xBA
                    if (header[0] == 0x00.toByte() && header[1] == 0x00.toByte() &&
                        header[2] == 0x01.toByte() && header[3] == 0xBA.toByte()
                    ) {
                        return MimeTypes.VIDEO_PS
                    }
                    // Check AVI: "RIFF" ... "AVI "
                    if (read >= 12 && String(header, 0, 4, Charsets.US_ASCII) == "RIFF" &&
                        String(header, 8, 4, Charsets.US_ASCII) == "AVI "
                    ) {
                        return MimeTypes.VIDEO_AVI
                    }
                    // Check FLV: "FLV"
                    if (read >= 3 && header[0] == 0x46.toByte() && header[1] == 0x4C.toByte() && header[2] == 0x56.toByte()) {
                        return MimeTypes.VIDEO_FLV
                    }
                }
                null
            }
        } catch (ignored: Throwable) {
            null
        }
    }

    private fun initializePlayer(uri: Uri) {
        binding.loadingProgress.visibility = View.VISIBLE

        val sniffedMime = sniffVideoContainer(this, uri)

        val defaultExtractorsFactory = DefaultExtractorsFactory()
            .setConstantBitrateSeekingEnabled(true)
            .setMp4ExtractorFlags(Mp4Extractor.FLAG_READ_MOTION_PHOTO_METADATA)
            .setTsExtractorMode(TsExtractor.MODE_SINGLE_PMT)
            .setTsExtractorFlags(
                DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES or
                DefaultTsPayloadReaderFactory.FLAG_DETECT_ACCESS_UNITS
            )

        val extractorsFactory = ExtractorsFactory {
            val defaults = defaultExtractorsFactory.createExtractors()
            if (sniffedMime == MimeTypes.VIDEO_MP4) {
                arrayOf(Mp4Extractor(Mp4Extractor.FLAG_READ_MOTION_PHOTO_METADATA), *defaults)
            } else {
                defaults
            }
        }

        val dataSourceFactory = DefaultDataSource.Factory(this)
        val mediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory, extractorsFactory)

        // Renderers with automatic software decoder fallback for AV1 / VP9 / HEVC
        val renderersFactory = DefaultRenderersFactory(this)
            .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
            .setEnableDecoderFallback(true)

        val exoPlayer = ExoPlayer.Builder(this, renderersFactory)
            .setMediaSourceFactory(mediaSourceFactory)
            .setSeekParameters(SeekParameters.EXACT)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                    .build(),
                true // Auto handle audio focus
            )
            .build()

        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        binding.loadingProgress.visibility = View.VISIBLE
                    }
                    Player.STATE_READY -> {
                        binding.loadingProgress.visibility = View.GONE
                        val duration = exoPlayer.duration
                        val total = if (duration > 0L) duration else 0L
                        updateProgressUI(exoPlayer.currentPosition.coerceAtLeast(0L), total)
                    }
                    Player.STATE_ENDED -> {
                        updatePlayPauseButton(false)
                        exoPlayer.seekTo(0L)
                        exoPlayer.pause()
                        val duration = exoPlayer.duration
                        updateProgressUI(0L, if (duration > 0L) duration else 0L)
                        showControls()
                    }
                    Player.STATE_IDLE -> {}
                }
            }

            override fun onTimelineChanged(timeline: Timeline, reason: Int) {
                val duration = exoPlayer.duration
                if (duration > 0L) {
                    updateProgressUI(exoPlayer.currentPosition.coerceAtLeast(0L), duration)
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlayPauseButton(isPlaying)
                if (isPlaying) {
                    scheduleControlsHide(3000)
                } else {
                    handler.removeCallbacks(hideControlsRunnable)
                    showControls()
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                binding.loadingProgress.visibility = View.GONE
                MaterialAlertDialogBuilder(this@VideoPlayerActivity)
                    .setTitle("Playback Error")
                    .setMessage("Unable to play video (${error.errorCodeName}).")
                    .setPositiveButton("OK") { _, _ -> finish() }
                    .setCancelable(false)
                    .show()
            }
        })

        val mediaItem = MediaItem.Builder()
            .setUri(uri)
            .apply {
                if (sniffedMime != null) {
                    setMimeType(sniffedMime)
                }
            }
            .build()

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        if (savedPlaybackPosition > 0L) {
            exoPlayer.seekTo(savedPlaybackPosition)
        }
        exoPlayer.playWhenReady = true

        binding.playerView.player = exoPlayer
        player = exoPlayer

        startProgressUpdates()
        scheduleControlsHide(3000)
    }

    private fun setupControls() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnPlayPauseCard.setOnClickListener {
            togglePlayPause()
        }

        binding.btnRewind10.setOnClickListener {
            seekRelative(-10000L)
            showSeekBadge(isForward = false)
        }

        binding.btnForward10.setOnClickListener {
            seekRelative(10000L)
            showSeekBadge(isForward = true)
        }

        binding.btnAspectRatio.setOnClickListener {
            cycleAspectRatio()
        }

        binding.btnRotateScreen.setOnClickListener {
            toggleScreenOrientation()
        }

        binding.videoSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    binding.currentTimeText.text = formatTime(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isUserTrackingSeekBar = true
                handler.removeCallbacks(hideControlsRunnable)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    val target = it.progress.toLong()
                    player?.seekTo(target)
                    val duration = player?.duration ?: 0L
                    updateProgressUI(target, if (duration > 0L) duration else 0L)
                }
                isUserTrackingSeekBar = false
                scheduleControlsHide(3000)
            }
        })
    }

    private fun setupGestures() {
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean = true

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                if (!isDraggingSlider) {
                    toggleControls()
                }
                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (isDraggingSlider) return false
                val screenWidth = binding.videoRootLayout.width
                if (e.x < screenWidth / 2) {
                    seekRelative(-10000L)
                    showSeekBadge(isForward = false)
                } else {
                    seekRelative(10000L)
                    showSeekBadge(isForward = true)
                }
                return true
            }
        })

        val touchListener = View.OnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    touchDownX = event.x
                    touchDownY = event.y
                    isDraggingSlider = false
                    val width = binding.videoRootLayout.width.toFloat().coerceAtLeast(1f)

                    activeTouchZone = when {
                        touchDownX < width * 0.30f -> TouchZone.BRIGHTNESS
                        touchDownX > width * 0.70f -> TouchZone.VOLUME
                        else -> TouchZone.NONE
                    }

                    val lp = window.attributes
                    initialBrightness = if (lp.screenBrightness < 0f) {
                        try {
                            val sys = android.provider.Settings.System.getInt(
                                contentResolver,
                                android.provider.Settings.System.SCREEN_BRIGHTNESS
                            )
                            (sys / 255f).coerceIn(0.01f, 1f)
                        } catch (e: Exception) {
                            0.5f
                        }
                    } else {
                        lp.screenBrightness.coerceIn(0.01f, 1f)
                    }

                    val am = audioManager
                    if (am != null) {
                        initialVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
                        maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat().coerceAtLeast(1f)
                    }

                    val p = player
                    if (p != null) {
                        initialSeekPosition = p.currentPosition.coerceAtLeast(0L)
                        targetSeekPosition = initialSeekPosition
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaX = event.x - touchDownX
                    val deltaY = event.y - touchDownY

                    if (!isDraggingSlider) {
                        if (abs(deltaY) > touchSlop && abs(deltaY) > abs(deltaX)) {
                            if (activeTouchZone == TouchZone.BRIGHTNESS || activeTouchZone == TouchZone.VOLUME) {
                                isDraggingSlider = true
                                handler.removeCallbacks(hideGestureIndicatorRunnable)
                            }
                        } else if (abs(deltaX) > touchSlop && abs(deltaX) > abs(deltaY) && activeTouchZone == TouchZone.NONE) {
                            isDraggingSlider = true
                            activeTouchZone = TouchZone.SEEK
                            handler.removeCallbacks(hideGestureIndicatorRunnable)
                        }
                    }

                    if (isDraggingSlider) {
                        val screenHeight = binding.videoRootLayout.height.toFloat().coerceAtLeast(1f)
                        val screenWidth = binding.videoRootLayout.width.toFloat().coerceAtLeast(1f)

                        when (activeTouchZone) {
                            TouchZone.BRIGHTNESS -> {
                                val change = -deltaY / screenHeight
                                val newBrightness = (initialBrightness + change).coerceIn(0.01f, 1f)
                                val lp = window.attributes
                                lp.screenBrightness = newBrightness
                                window.attributes = lp
                                showBrightnessIndicator(newBrightness)
                            }
                            TouchZone.VOLUME -> {
                                val change = (-deltaY / screenHeight) * maxVolume
                                val newVolume = (initialVolume + change).roundToInt().coerceIn(0, maxVolume.toInt())
                                audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
                                showVolumeIndicator(newVolume, maxVolume.toInt())
                            }
                            TouchZone.SEEK -> {
                                val p = player
                                val duration = p?.duration ?: 0L
                                val validDuration = if (duration > 0L) duration else 0L
                                val seekWindow = if (validDuration > 0L) 90000L.coerceAtMost(validDuration) else 90000L
                                val deltaMs = ((deltaX / screenWidth) * seekWindow).toLong()
                                targetSeekPosition = if (validDuration > 0L) {
                                    (initialSeekPosition + deltaMs).coerceIn(0L, validDuration)
                                } else {
                                    (initialSeekPosition + deltaMs).coerceAtLeast(0L)
                                }
                                showSeekIndicator(targetSeekPosition, validDuration, deltaMs)
                            }
                            TouchZone.NONE -> {}
                        }
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    if (isDraggingSlider) {
                        if (activeTouchZone == TouchZone.SEEK) {
                            player?.seekTo(targetSeekPosition)
                            val duration = player?.duration ?: 0L
                            updateProgressUI(targetSeekPosition, if (duration > 0L) duration else 0L)
                        }
                        isDraggingSlider = false
                        activeTouchZone = TouchZone.NONE
                        handler.removeCallbacks(hideGestureIndicatorRunnable)
                        handler.postDelayed(hideGestureIndicatorRunnable, 1000)
                    }
                }
            }
            gestureDetector.onTouchEvent(event)
            true
        }

        binding.videoRootLayout.setOnTouchListener(touchListener)
    }

    private fun togglePlayPause() {
        val p = player ?: return
        if (p.isPlaying) {
            p.pause()
            updatePlayPauseButton(false)
            handler.removeCallbacks(hideControlsRunnable)
            showControls()
        } else {
            p.play()
            updatePlayPauseButton(true)
            scheduleControlsHide(3000)
        }
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        val iconRes = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        binding.btnPlayPauseIcon.setImageResource(iconRes)
    }

    private fun seekRelative(offsetMs: Long) {
        val p = player ?: return
        val current = p.currentPosition.coerceAtLeast(0L)
        val duration = p.duration
        val target = if (duration > 0L) {
            (current + offsetMs).coerceIn(0L, duration)
        } else {
            (current + offsetMs).coerceAtLeast(0L)
        }
        p.seekTo(target)
        updateProgressUI(target, if (duration > 0L) duration else 0L)
        scheduleControlsHide(3000)
    }

    private fun showSeekBadge(isForward: Boolean) {
        val badge = if (isForward) binding.badgeForward else binding.badgeRewind
        badge.alpha = 1f
        badge.visibility = View.VISIBLE
        badge.animate()
            .alpha(0f)
            .setDuration(600L)
            .withEndAction { badge.visibility = View.GONE }
            .start()
    }

    private fun cycleAspectRatio() {
        val nextMode = when (binding.playerView.resizeMode) {
            AspectRatioFrameLayout.RESIZE_MODE_FIT -> AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            AspectRatioFrameLayout.RESIZE_MODE_ZOOM -> AspectRatioFrameLayout.RESIZE_MODE_FILL
            AspectRatioFrameLayout.RESIZE_MODE_FILL -> AspectRatioFrameLayout.RESIZE_MODE_FIT
            else -> AspectRatioFrameLayout.RESIZE_MODE_FIT
        }
        binding.playerView.resizeMode = nextMode

        val label = when (nextMode) {
            AspectRatioFrameLayout.RESIZE_MODE_FIT -> "Fit to Screen"
            AspectRatioFrameLayout.RESIZE_MODE_ZOOM -> "Fill Screen (Crop)"
            AspectRatioFrameLayout.RESIZE_MODE_FILL -> "Stretch to Fill"
            else -> "Fit to Screen"
        }
        Toast.makeText(this, label, Toast.LENGTH_SHORT).show()
        scheduleControlsHide(3000)
    }

    private fun toggleScreenOrientation() {
        val currentOrientation = resources.configuration.orientation
        requestedOrientation = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }
        scheduleControlsHide(3000)
    }

    private fun toggleControls() {
        if (areControlsVisible) {
            hideControls()
        } else {
            showControls()
        }
    }

    private fun showControls() {
        areControlsVisible = true
        binding.controlsOverlay.animate()
            .alpha(1f)
            .setDuration(200L)
            .withStartAction { binding.controlsOverlay.visibility = View.VISIBLE }
            .start()

        keepStatusBarsHidden()
        if (player?.isPlaying == true) {
            scheduleControlsHide(3500)
        }
    }

    private fun hideControls() {
        areControlsVisible = false
        binding.controlsOverlay.animate()
            .alpha(0f)
            .setDuration(200L)
            .withEndAction { binding.controlsOverlay.visibility = View.GONE }
            .start()

        keepStatusBarsHidden()
    }

    private fun scheduleControlsHide(delayMs: Long = 3500) {
        handler.removeCallbacks(hideControlsRunnable)
        handler.postDelayed(hideControlsRunnable, delayMs)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            keepStatusBarsHidden()
        }
    }

    private fun keepStatusBarsHidden() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.statusBars())
        controller.hide(WindowInsetsCompat.Type.navigationBars())
    }

    private fun showBrightnessIndicator(brightness: Float) {
        val percent = (brightness * 100).roundToInt().coerceIn(1, 100)
        binding.gestureIndicatorIcon.setImageResource(R.drawable.ic_brightness)
        binding.gestureIndicatorProgress.progress = percent
        binding.gestureIndicatorText.text = "$percent%"

        binding.gestureIndicatorCard.animate().cancel()
        binding.gestureIndicatorCard.alpha = 1f
        binding.gestureIndicatorCard.visibility = View.VISIBLE
    }

    private fun showVolumeIndicator(current: Int, max: Int) {
        val percent = if (max > 0) ((current.toFloat() / max) * 100).roundToInt().coerceIn(0, 100) else 0
        val iconRes = if (current <= 0) R.drawable.ic_volume_off else R.drawable.ic_volume_up
        binding.gestureIndicatorIcon.setImageResource(iconRes)
        binding.gestureIndicatorProgress.progress = percent
        binding.gestureIndicatorText.text = "$percent%"

        binding.gestureIndicatorCard.animate().cancel()
        binding.gestureIndicatorCard.alpha = 1f
        binding.gestureIndicatorCard.visibility = View.VISIBLE
    }

    private fun showSeekIndicator(targetMs: Long, totalMs: Long, deltaMs: Long) {
        val sign = if (deltaMs >= 0) "+" else ""
        val diffSec = (deltaMs / 1000).toInt()
        val percent = if (totalMs > 0) ((targetMs.toFloat() / totalMs) * 100).roundToInt().coerceIn(0, 100) else 0
        val iconRes = if (deltaMs >= 0) R.drawable.ic_forward_10 else R.drawable.ic_replay_10
        binding.gestureIndicatorIcon.setImageResource(iconRes)
        binding.gestureIndicatorProgress.progress = percent
        binding.gestureIndicatorText.text = "${formatTime(targetMs)} [${sign}${diffSec}s]"

        binding.gestureIndicatorCard.animate().cancel()
        binding.gestureIndicatorCard.alpha = 1f
        binding.gestureIndicatorCard.visibility = View.VISIBLE

        binding.currentTimeText.text = formatTime(targetMs)
        if (totalMs > 0L) {
            if (binding.videoSeekBar.max != totalMs.toInt()) {
                binding.videoSeekBar.max = totalMs.toInt()
            }
            binding.videoSeekBar.progress = targetMs.toInt().coerceIn(0, totalMs.toInt())
        }
    }

    private fun hideGestureIndicator() {
        binding.gestureIndicatorCard.animate()
            .alpha(0f)
            .setDuration(250L)
            .withEndAction { binding.gestureIndicatorCard.visibility = View.GONE }
            .start()
    }

    private fun updateProgressUI(currentMs: Long, totalMs: Long) {
        binding.currentTimeText.text = formatTime(currentMs)
        if (totalMs > 0L) {
            binding.totalTimeText.text = formatTime(totalMs)
            if (binding.videoSeekBar.max != totalMs.toInt()) {
                binding.videoSeekBar.max = totalMs.toInt()
            }
            binding.videoSeekBar.progress = currentMs.toInt().coerceIn(0, totalMs.toInt())
        }
    }

    private fun startProgressUpdates() {
        handler.removeCallbacks(progressUpdateRunnable)
        handler.post(progressUpdateRunnable)
    }

    private fun stopProgressUpdates() {
        handler.removeCallbacks(progressUpdateRunnable)
    }

    private fun formatTime(ms: Long): String {
        if (ms <= 0) return "00:00"
        val totalSeconds = ms / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format(Locale.US, "%02d:%02d", minutes, seconds)
        }
    }

    override fun onPause() {
        super.onPause()
        val p = player
        if (p != null) {
            wasPlayingBeforePause = p.isPlaying
            if (p.isPlaying) {
                p.pause()
                updatePlayPauseButton(false)
            }
            savedPlaybackPosition = p.currentPosition
        }
        stopProgressUpdates()
    }

    override fun onResume() {
        super.onResume()
        val p = player
        if (p != null) {
            if (savedPlaybackPosition > 0L) {
                p.seekTo(savedPlaybackPosition)
            }
            if (wasPlayingBeforePause && !p.isPlaying) {
                p.play()
                updatePlayPauseButton(true)
                startProgressUpdates()
                scheduleControlsHide(2500)
            }
        }
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        stopProgressUpdates()
        try {
            player?.release()
            player = null
        } catch (ignored: Exception) {}
        super.onDestroy()
    }
}
