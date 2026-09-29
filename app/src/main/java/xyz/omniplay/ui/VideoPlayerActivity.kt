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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityVideoPlayerBinding
import xyz.omniplay.service.PlaybackService
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Dedicated high-performance video player powered by VideoLAN's native C/C++ engine (LibVLC).
 * Provides hardware-accelerated playback with native C dav1d/FFmpeg software fallback,
 * effortlessly handling MPEG-TS, TS, MPEG container, AV1, HEVC, VP9, MKV, MP4, and all complex streams.
 */
class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private val handler = Looper.myLooper()?.let { Handler(it) } ?: Handler(Looper.getMainLooper())

    private var libVLC: LibVLC? = null
    private var mediaPlayer: MediaPlayer? = null
    private var audioManager: AudioManager? = null

    private var isUserTrackingSeekBar = false
    private var areControlsVisible = true
    private var savedPlaybackPosition = 0L
    private var wasPlayingBeforePause = true

    // Aspect ratio modes
    private enum class AspectMode { FIT, SIXTEEN_NINE, FOUR_THREE, FILL }
    private var currentAspectMode = AspectMode.FIT

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
            val mp = mediaPlayer
            if (::binding.isInitialized && mp != null && mp.isPlaying && !isUserTrackingSeekBar) {
                val current = mp.time.coerceAtLeast(0L)
                val total = mp.length.coerceAtLeast(0L)
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

    private fun initializePlayer(uri: Uri) {
        binding.loadingProgress.visibility = View.VISIBLE

        val options = ArrayList<String>().apply {
            add("--no-drop-late-frames")
            add("--no-skip-frames")
            add("--rtsp-tcp")
            add("--audio-time-stretch")
            add("-vvv")
        }

        val vlc = LibVLC(this, options)
        libVLC = vlc

        val mp = MediaPlayer(vlc)
        mediaPlayer = mp
        mp.attachViews(binding.vlcVideoLayout, null, false, false)

        val media = Media(vlc, uri).apply {
            // Enable hardware acceleration with automatic software fallback (dav1d/FFmpeg)
            setHWDecoderEnabled(true, false)
        }
        mp.media = media
        media.release()

        mp.setEventListener { event ->
            when (event.type) {
                MediaPlayer.Event.Buffering -> {
                    if (event.buffering < 100f) {
                        binding.loadingProgress.visibility = View.VISIBLE
                    } else {
                        binding.loadingProgress.visibility = View.GONE
                    }
                }
                MediaPlayer.Event.Playing -> {
                    binding.loadingProgress.visibility = View.GONE
                    updatePlayPauseButton(true)
                    scheduleControlsHide(3000)
                }
                MediaPlayer.Event.Paused -> {
                    updatePlayPauseButton(false)
                    handler.removeCallbacks(hideControlsRunnable)
                    showControls()
                }
                MediaPlayer.Event.Stopped -> {
                    updatePlayPauseButton(false)
                }
                MediaPlayer.Event.EndReached -> {
                    updatePlayPauseButton(false)
                    mp.time = 0L
                    mp.pause()
                    updateProgressUI(0L, mp.length.coerceAtLeast(0L))
                    showControls()
                }
                MediaPlayer.Event.TimeChanged -> {
                    val current = event.timeChanged
                    val duration = mp.length.coerceAtLeast(0L)
                    if (!isUserTrackingSeekBar) {
                        updateProgressUI(current, duration)
                    }
                }
                MediaPlayer.Event.LengthChanged -> {
                    val duration = event.lengthChanged.coerceAtLeast(0L)
                    binding.totalTimeText.text = formatTime(duration)
                    binding.videoSeekBar.max = duration.toInt()
                }
                MediaPlayer.Event.EncounteredError -> {
                    binding.loadingProgress.visibility = View.GONE
                    MaterialAlertDialogBuilder(this@VideoPlayerActivity)
                        .setTitle("Playback Error")
                        .setMessage("Unable to play video with native engine.")
                        .setPositiveButton("OK") { _, _ -> finish() }
                        .setCancelable(false)
                        .show()
                }
            }
        }

        if (savedPlaybackPosition > 0L) {
            mp.time = savedPlaybackPosition
        }
        mp.play()

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
                    mediaPlayer?.time = target
                    updateProgressUI(target, mediaPlayer?.length?.coerceAtLeast(0L) ?: 0L)
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

                    val mp = mediaPlayer
                    if (mp != null) {
                        initialSeekPosition = mp.time.coerceAtLeast(0L)
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
                                val mp = mediaPlayer
                                val duration = mp?.length?.coerceAtLeast(0L) ?: 0L
                                if (duration > 0L) {
                                    val seekWindow = 90000L.coerceAtMost(duration)
                                    val deltaMs = ((deltaX / screenWidth) * seekWindow).toLong()
                                    targetSeekPosition = (initialSeekPosition + deltaMs).coerceIn(0L, duration)
                                    showSeekIndicator(targetSeekPosition, duration, deltaMs)
                                }
                            }
                            TouchZone.NONE -> {}
                        }
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    if (isDraggingSlider) {
                        if (activeTouchZone == TouchZone.SEEK) {
                            mediaPlayer?.let {
                                it.time = targetSeekPosition
                                updateProgressUI(targetSeekPosition, it.length.coerceAtLeast(0L))
                            }
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
        val mp = mediaPlayer ?: return
        if (mp.isPlaying) {
            mp.pause()
            updatePlayPauseButton(false)
            handler.removeCallbacks(hideControlsRunnable)
            showControls()
        } else {
            mp.play()
            updatePlayPauseButton(true)
            scheduleControlsHide(3000)
        }
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        val iconRes = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        binding.btnPlayPauseIcon.setImageResource(iconRes)
    }

    private fun seekRelative(offsetMs: Long) {
        val mp = mediaPlayer ?: return
        val current = mp.time.coerceAtLeast(0L)
        val duration = mp.length.coerceAtLeast(0L)
        val target = (current + offsetMs).coerceIn(0L, duration)
        mp.time = target
        updateProgressUI(target, duration)
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
        val mp = mediaPlayer ?: return
        currentAspectMode = when (currentAspectMode) {
            AspectMode.FIT -> AspectMode.SIXTEEN_NINE
            AspectMode.SIXTEEN_NINE -> AspectMode.FOUR_THREE
            AspectMode.FOUR_THREE -> AspectMode.FILL
            AspectMode.FILL -> AspectMode.FIT
        }

        val label = when (currentAspectMode) {
            AspectMode.FIT -> {
                mp.aspectRatio = null
                mp.scale = 0f
                "Fit to Screen"
            }
            AspectMode.SIXTEEN_NINE -> {
                mp.aspectRatio = "16:9"
                mp.scale = 0f
                "16:9"
            }
            AspectMode.FOUR_THREE -> {
                mp.aspectRatio = "4:3"
                mp.scale = 0f
                "4:3"
            }
            AspectMode.FILL -> {
                mp.aspectRatio = null
                mp.scale = 1.35f
                "Fill Screen (Crop)"
            }
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
        if (mediaPlayer?.isPlaying == true) {
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
        binding.videoSeekBar.progress = targetMs.toInt()
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
        binding.totalTimeText.text = formatTime(totalMs)
        binding.videoSeekBar.progress = currentMs.toInt()
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
        val mp = mediaPlayer
        if (mp != null) {
            wasPlayingBeforePause = mp.isPlaying
            if (mp.isPlaying) {
                mp.pause()
                updatePlayPauseButton(false)
            }
            savedPlaybackPosition = mp.time
        }
        stopProgressUpdates()
    }

    override fun onResume() {
        super.onResume()
        val mp = mediaPlayer
        if (mp != null) {
            if (savedPlaybackPosition > 0L) {
                mp.time = savedPlaybackPosition
            }
            if (wasPlayingBeforePause && !mp.isPlaying) {
                mp.play()
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
            mediaPlayer?.let {
                it.stop()
                it.detachViews()
                it.release()
            }
            mediaPlayer = null
            libVLC?.release()
            libVLC = null
        } catch (ignored: Exception) {}
        super.onDestroy()
    }
}
