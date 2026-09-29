package xyz.omniplay.ui

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityVideoPlayerBinding
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.ui.view.ScaleableVideoView
import java.util.Locale

/**
 * Dedicated video playback activity for Omniplay.
 * Launched via file managers or "Open With..." intents.
 * Keeps the main music player UI unchanged while providing a sleek,
 * hardware-accelerated video player with gesture controls.
 */
class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private val handler = Looper.myLooper()?.let { Handler(it) } ?: Handler(Looper.getMainLooper())

    private var audioManager: AudioManager? = null
    private var audioFocusRequest: AudioFocusRequest? = null

    private var isUserTrackingSeekBar = false
    private var areControlsVisible = true
    private var savedPlaybackPosition = 0
    private var wasPlayingBeforePause = true

    private val hideControlsRunnable = Runnable {
        hideControls()
    }

    private val progressUpdateRunnable = object : Runnable {
        override fun run() {
            if (::binding.isInitialized && binding.videoView.isPlaying && !isUserTrackingSeekBar) {
                val current = binding.videoView.currentPosition.toLong()
                val total = binding.videoView.duration.toLong()
                updateProgressUI(current, total)
            }
            handler.postDelayed(this, 250)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make window edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

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
        setupVideoView(videoUri)
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

    private fun setupVideoView(uri: Uri) {
        binding.loadingProgress.visibility = View.VISIBLE

        binding.videoView.setOnPreparedListener { mp ->
            binding.loadingProgress.visibility = View.GONE
            binding.videoView.setVideoDimensions(mp.videoWidth, mp.videoHeight)

            val duration = mp.duration.toLong()
            binding.totalTimeText.text = formatTime(duration)
            binding.videoSeekBar.max = mp.duration

            if (savedPlaybackPosition > 0) {
                binding.videoView.seekTo(savedPlaybackPosition)
            }

            requestAudioFocus()
            binding.videoView.start()
            updatePlayPauseButton(true)
            startProgressUpdates()
            scheduleControlsHide(3000)
        }

        binding.videoView.setOnCompletionListener {
            updatePlayPauseButton(false)
            binding.videoView.seekTo(0)
            updateProgressUI(0, binding.videoView.duration.toLong())
            showControls()
        }

        binding.videoView.setOnErrorListener { _, what, extra ->
            binding.loadingProgress.visibility = View.GONE
            MaterialAlertDialogBuilder(this)
                .setTitle("Playback Error")
                .setMessage("Unable to play video format (code $what, $extra).")
                .setPositiveButton("OK") { _, _ -> finish() }
                .setCancelable(false)
                .show()
            true
        }

        binding.videoView.setVideoURI(uri)
    }

    private fun setupControls() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnPlayPauseCard.setOnClickListener {
            togglePlayPause()
        }

        binding.btnRewind10.setOnClickListener {
            seekRelative(-10000)
            showSeekBadge(isForward = false)
        }

        binding.btnForward10.setOnClickListener {
            seekRelative(10000)
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
                    binding.videoView.seekTo(it.progress)
                    updateProgressUI(it.progress.toLong(), binding.videoView.duration.toLong())
                }
                isUserTrackingSeekBar = false
                scheduleControlsHide(3000)
            }
        })
    }

    private fun setupGestures() {
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                toggleControls()
                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                val screenWidth = binding.videoRootLayout.width
                if (e.x < screenWidth / 2) {
                    seekRelative(-10000)
                    showSeekBadge(isForward = false)
                } else {
                    seekRelative(10000)
                    showSeekBadge(isForward = true)
                }
                return true
            }
        })

        binding.videoRootLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun togglePlayPause() {
        if (binding.videoView.isPlaying) {
            binding.videoView.pause()
            updatePlayPauseButton(false)
            handler.removeCallbacks(hideControlsRunnable)
        } else {
            requestAudioFocus()
            binding.videoView.start()
            updatePlayPauseButton(true)
            scheduleControlsHide(2500)
        }
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        val iconRes = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        binding.btnPlayPauseIcon.setImageResource(iconRes)
    }

    private fun seekRelative(offsetMs: Int) {
        val current = binding.videoView.currentPosition
        val duration = binding.videoView.duration
        val target = (current + offsetMs).coerceIn(0, duration)
        binding.videoView.seekTo(target)
        updateProgressUI(target.toLong(), duration.toLong())
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
        val nextMode = when (binding.videoView.scaleMode) {
            ScaleableVideoView.ScaleMode.FIT -> ScaleableVideoView.ScaleMode.FILL
            ScaleableVideoView.ScaleMode.FILL -> ScaleableVideoView.ScaleMode.STRETCH
            ScaleableVideoView.ScaleMode.STRETCH -> ScaleableVideoView.ScaleMode.FIT
        }
        binding.videoView.scaleMode = nextMode

        val label = when (nextMode) {
            ScaleableVideoView.ScaleMode.FIT -> "Fit to Screen"
            ScaleableVideoView.ScaleMode.FILL -> "Fill Screen (Crop)"
            ScaleableVideoView.ScaleMode.STRETCH -> "Stretch to Fill"
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

        setSystemBarsVisible(true)
        if (binding.videoView.isPlaying) {
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

        setSystemBarsVisible(false)
    }

    private fun scheduleControlsHide(delayMs: Long = 3500) {
        handler.removeCallbacks(hideControlsRunnable)
        handler.postDelayed(hideControlsRunnable, delayMs)
    }

    private fun setSystemBarsVisible(visible: Boolean) {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        if (visible) {
            controller.show(WindowInsetsCompat.Type.systemBars())
        } else {
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(WindowInsetsCompat.Type.systemBars())
        }
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

    private fun requestAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val req = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                        .build()
                )
                .build()
            audioFocusRequest = req
            audioManager?.requestAudioFocus(req)
        } else {
            @Suppress("DEPRECATION")
            audioManager?.requestAudioFocus(
                null,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            )
        }
    }

    private fun abandonAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioFocusRequest?.let { audioManager?.abandonAudioFocusRequest(it) }
            audioFocusRequest = null
        } else {
            @Suppress("DEPRECATION")
            audioManager?.abandonAudioFocus(null)
        }
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
        wasPlayingBeforePause = binding.videoView.isPlaying
        if (binding.videoView.isPlaying) {
            binding.videoView.pause()
            updatePlayPauseButton(false)
        }
        savedPlaybackPosition = binding.videoView.currentPosition
        stopProgressUpdates()
        abandonAudioFocus()
    }

    override fun onResume() {
        super.onResume()
        if (savedPlaybackPosition > 0) {
            binding.videoView.seekTo(savedPlaybackPosition)
        }
        if (wasPlayingBeforePause && !binding.videoView.isPlaying) {
            requestAudioFocus()
            binding.videoView.start()
            updatePlayPauseButton(true)
            startProgressUpdates()
            scheduleControlsHide(2500)
        }
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        abandonAudioFocus()
        try {
            binding.videoView.stopPlayback()
        } catch (ignored: Exception) {}
        super.onDestroy()
    }
}
