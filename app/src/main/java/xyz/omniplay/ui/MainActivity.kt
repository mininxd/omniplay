package xyz.omniplay.ui

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.media.audiofx.Equalizer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.data.MusicScanner
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.databinding.DialogEqualizerBinding
import xyz.omniplay.databinding.LayoutQueueBottomSheetBinding
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import java.io.File
import java.util.Locale

class MainActivity : AppCompatActivity(), PlaybackService.PlaybackListener {

    private lateinit var binding: ActivityMainBinding
    private var playbackService: PlaybackService? = null
    private var isBound = false
    private var isUserTrackingSeekBar = false

    private val musicScanner by lazy { MusicScanner(this) }
    private var scannedSongs = listOf<Song>()
    private var songAdapter: SongAdapter? = null

    private var sleepTimer: CountDownTimer? = null
    private var sleepTimerRemainingMs: Long = 0L

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as PlaybackService.LocalBinder
            playbackService = binder.getService()
            isBound = true
            playbackService?.addListener(this@MainActivity)

            // If service has no songs yet and we already scanned songs, provide them
            if (playbackService?.queue.isNullOrEmpty() && scannedSongs.isNotEmpty()) {
                playbackService?.setSongQueue(scannedSongs, startIndex = 0, startPlaying = false)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            playbackService?.removeListener(this@MainActivity)
            playbackService = null
            isBound = false
        }
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val audioGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions[Manifest.permission.READ_MEDIA_AUDIO] == true
        } else {
            permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true
        }

        if (audioGranted) {
            loadMusicLibrary()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDefaultScreenshotView()
        setupListeners()
        bindPlaybackService()
        checkAndRequestPermissions()
    }

    /**
     * Initializes the UI to perfectly reflect the screenshot @[1790665358030.jpg]
     * Immediately visible upon launch with no blank state or flash.
     */
    private fun setupDefaultScreenshotView() {
        val defaultSong = Song.getDefaultMockSong()
        binding.songTitleText.text = defaultSong.title
        binding.artistNameText.text = defaultSong.artist
        binding.albumNameText.text = defaultSong.album
        binding.currentTimeText.text = getString(R.string.default_current_time)
        binding.totalTimeText.text = getString(R.string.default_total_time)
        binding.albumArtImage.setImageResource(R.drawable.default_album_art)

        binding.playbackSeekBar.max = (defaultSong.duration / 1000).toInt()
        binding.playbackSeekBar.progress = 135 // 2:15 out of 4:30

        updateShuffleButton(false)
        updateRepeatButton(PlaybackService.REPEAT_OFF)
        updatePlayPauseButton(isPlaying = false)
    }

    private fun setupListeners() {
        // Menu button (Hamburger)
        binding.btnMenu.setOnClickListener { view ->
            showOptionsMenu(view)
        }

        // Play / Pause Circular Card
        binding.btnPlayPauseCard.setOnClickListener {
            if (isBound) {
                playbackService?.togglePlayPause()
            }
        }

        // Next
        binding.btnNext.setOnClickListener {
            if (isBound) {
                playbackService?.skipNext()
            }
        }

        // Previous
        binding.btnPrevious.setOnClickListener {
            if (isBound) {
                playbackService?.skipPrevious()
            }
        }

        // Shuffle
        binding.btnShuffle.setOnClickListener {
            if (isBound) {
                playbackService?.toggleShuffle()
            }
        }

        // Repeat
        binding.btnRepeat.setOnClickListener {
            if (isBound) {
                playbackService?.cycleRepeatMode()
            }
        }

        // Seekbar
        binding.playbackSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    binding.currentTimeText.text = Song.formatTime(progress * 1000L)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isUserTrackingSeekBar = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isUserTrackingSeekBar = false
                seekBar?.let {
                    playbackService?.seekTo(it.progress * 1000)
                }
            }
        })

        // Expand Queue Chevron (^)
        binding.btnExpandQueue.setOnClickListener {
            showQueueBottomSheet()
        }

        // Album Art click also expands bottom sheet
        binding.albumArtCard.setOnClickListener {
            showQueueBottomSheet()
        }
    }

    private fun bindPlaybackService() {
        val serviceIntent = Intent(this, PlaybackService::class.java)
        startService(serviceIntent)
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun checkAndRequestPermissions() {
        val permissions = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        if (permissions.isNotEmpty()) {
            permissionLauncher.launch(permissions.toTypedArray())
        } else {
            loadMusicLibrary()
        }
    }

    private fun loadMusicLibrary() {
        lifecycleScope.launch {
            val songs = musicScanner.scanMusic()
            scannedSongs = songs
            songAdapter?.setSongs(songs)

            if (songs.isNotEmpty()) {
                playbackService?.setSongQueue(songs, startIndex = 0, startPlaying = false)
            }
        }
    }

    private fun showQueueBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val sheetBinding = LayoutQueueBottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(sheetBinding.root)

        val adapter = SongAdapter { song, index ->
            playbackService?.let { service ->
                val songsToPlay = scannedSongs.ifEmpty { listOf(song) }
                service.setSongQueue(songsToPlay, startIndex = index, startPlaying = true)
            }
            bottomSheetDialog.dismiss()
        }
        songAdapter = adapter

        sheetBinding.songsRecyclerView.adapter = adapter
        sheetBinding.songsRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val songsToShow = scannedSongs.ifEmpty { listOf(Song.getDefaultMockSong()) }
        adapter.setSongs(songsToShow)
        playbackService?.currentSong?.let {
            adapter.setCurrentPlayingSongId(it.id)
        }

        sheetBinding.songCountText.text = "(${songsToShow.size} songs)"

        sheetBinding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        sheetBinding.btnRescanEmpty.setOnClickListener {
            loadMusicLibrary()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun showOptionsMenu(anchor: View) {
        val popup = PopupMenu(this, anchor, Gravity.END)
        popup.menuInflater.inflate(R.menu.main_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_equalizer -> {
                    showEqualizerDialog()
                    true
                }
                R.id.action_sleep_timer -> {
                    showSleepTimerDialog()
                    true
                }
                R.id.action_track_details -> {
                    showTrackDetailsDialog()
                    true
                }
                R.id.action_rescan -> {
                    Toast.makeText(this, "Scanning music library...", Toast.LENGTH_SHORT).show()
                    loadMusicLibrary()
                    true
                }
                R.id.action_about -> {
                    showAboutDialog()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun showEqualizerDialog() {
        val eq = playbackService?.equalizer
        val bass = playbackService?.bassBoost
        val virt = playbackService?.virtualizer

        val dialogBinding = DialogEqualizerBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        if (eq != null) {
            dialogBinding.switchEqualizer.isChecked = eq.enabled
            dialogBinding.switchEqualizer.setOnCheckedChangeListener { _, isChecked ->
                eq.enabled = isChecked
                bass?.enabled = isChecked
                virt?.enabled = isChecked
            }

            // Bass Boost
            bass?.let {
                dialogBinding.seekBassBoost.progress = it.roundedStrength.toInt()
                dialogBinding.seekBassBoost.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) it.setStrength(progress.toShort())
                    }
                    override fun onStartTrackingTouch(sb: SeekBar?) {}
                    override fun onStopTrackingTouch(sb: SeekBar?) {}
                })
            }

            // Virtualizer
            virt?.let {
                dialogBinding.seekVirtualizer.progress = it.roundedStrength.toInt()
                dialogBinding.seekVirtualizer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) it.setStrength(progress.toShort())
                    }
                    override fun onStartTrackingTouch(sb: SeekBar?) {}
                    override fun onStopTrackingTouch(sb: SeekBar?) {}
                })
            }

            // EQ Bands
            val bands = eq.numberOfBands
            val minBandLevel = eq.bandLevelRange[0]
            val maxBandLevel = eq.bandLevelRange[1]
            val range = maxBandLevel - minBandLevel

            dialogBinding.bandsContainer.removeAllViews()

            for (i in 0 until bands) {
                val band = i.toShort()
                val freq = eq.getCenterFreq(band) / 1000
                val freqLabel = if (freq < 1000) "${freq}Hz" else "${freq / 1000}kHz"

                val bandLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(0, 8, 0, 8)
                }

                val label = TextView(this).apply {
                    text = "$freqLabel (${eq.getBandLevel(band) / 100} dB)"
                    setTextColor(ContextCompat.getColor(this@MainActivity, R.color.text_secondary))
                    textSize = 12f
                }

                val seekBar = androidx.appcompat.widget.AppCompatSeekBar(this).apply {
                    max = range.toInt()
                    progress = (eq.getBandLevel(band) - minBandLevel).toInt()
                    progressDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.seekbar_progress)
                    thumb = ContextCompat.getDrawable(this@MainActivity, R.drawable.seekbar_thumb)
                    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                            if (fromUser) {
                                val level = (progress + minBandLevel).toShort()
                                eq.setBandLevel(band, level)
                                label.text = "$freqLabel (${level / 100} dB)"
                            }
                        }
                        override fun onStartTrackingTouch(sb: SeekBar?) {}
                        override fun onStopTrackingTouch(sb: SeekBar?) {}
                    })
                }

                bandLayout.addView(label)
                bandLayout.addView(seekBar)
                dialogBinding.bandsContainer.addView(bandLayout)
            }
        }

        dialogBinding.btnCloseEqualizer.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showSleepTimerDialog() {
        val options = arrayOf("15 minutes", "30 minutes", "45 minutes", "60 minutes", "Cancel Timer")
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.sleep_timer)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> startSleepTimer(15 * 60 * 1000L)
                    1 -> startSleepTimer(30 * 60 * 1000L)
                    2 -> startSleepTimer(45 * 60 * 1000L)
                    3 -> startSleepTimer(60 * 60 * 1000L)
                    4 -> cancelSleepTimer()
                }
            }
            .show()
    }

    private fun startSleepTimer(durationMs: Long) {
        sleepTimer?.cancel()
        sleepTimerRemainingMs = durationMs
        sleepTimer = object : CountDownTimer(durationMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                sleepTimerRemainingMs = millisUntilFinished
            }

            override fun onFinish() {
                playbackService?.pause()
                Toast.makeText(this@MainActivity, "Sleep timer finished. Playback paused.", Toast.LENGTH_LONG).show()
            }
        }.start()
        val minutes = durationMs / (60 * 1000)
        Toast.makeText(this, "Sleep timer set for $minutes minutes", Toast.LENGTH_SHORT).show()
    }

    private fun cancelSleepTimer() {
        sleepTimer?.cancel()
        sleepTimer = null
        sleepTimerRemainingMs = 0L
        Toast.makeText(this, "Sleep timer cancelled", Toast.LENGTH_SHORT).show()
    }

    private fun showTrackDetailsDialog() {
        val song = playbackService?.currentSong ?: Song.getDefaultMockSong()
        val sizeMb = String.format(Locale.US, "%.2f MB", song.fileSize / (1024.0 * 1024.0))

        val details = """
            Title: ${song.title}
            Artist: ${song.artist}
            Album: ${song.album}
            Duration: ${Song.formatTime(song.duration)}
            Format: ${song.format}
            File Size: $sizeMb
            Path: ${song.filePath.ifEmpty { "Bundled Demo Track" }}
        """.trimIndent()

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.track_details)
            .setMessage(details)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showAboutDialog() {
        val message = """
            Omniplay v0.1
            Open Source Material You Music Player
            
            Supports: MP3, WAV, FLAC, AAC, M4A, OGG, OPUS, and more.
            Architectures: armv7, armv8, x86, x86_64, Universal
            
            Built with pure Android & Material You Design.
        """.trimIndent()

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.about_omniplay)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    // PlaybackListener callbacks
    override fun onTrackChanged(song: Song) {
        binding.songTitleText.text = song.title
        binding.artistNameText.text = song.artist
        binding.albumNameText.text = song.album
        binding.totalTimeText.text = Song.formatTime(song.duration)
        binding.playbackSeekBar.max = (song.duration / 1000).toInt()

        songAdapter?.setCurrentPlayingSongId(song.id)

        // Load album art
        if (song.albumArtUri != null) {
            try {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, song.albumArtUri))
                } else {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(contentResolver, song.albumArtUri)
                }
                binding.albumArtImage.setImageBitmap(bitmap)
            } catch (e: Exception) {
                binding.albumArtImage.setImageResource(R.drawable.default_album_art)
            }
        } else {
            binding.albumArtImage.setImageResource(R.drawable.default_album_art)
        }
    }

    override fun onPlaybackStateChanged(isPlaying: Boolean) {
        updatePlayPauseButton(isPlaying)
    }

    override fun onProgressUpdate(currentPositionMs: Int, totalDurationMs: Int) {
        if (!isUserTrackingSeekBar) {
            val seconds = currentPositionMs / 1000
            val totalSeconds = totalDurationMs / 1000

            binding.playbackSeekBar.max = totalSeconds
            binding.playbackSeekBar.progress = seconds
            binding.currentTimeText.text = Song.formatTime(currentPositionMs.toLong())
            binding.totalTimeText.text = Song.formatTime(totalDurationMs.toLong())
        }
    }

    override fun onShuffleModeChanged(enabled: Boolean) {
        updateShuffleButton(enabled)
    }

    override fun onRepeatModeChanged(mode: Int) {
        updateRepeatButton(mode)
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        val iconRes = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        binding.btnPlayPauseIcon.setImageResource(iconRes)
    }

    private fun updateShuffleButton(enabled: Boolean) {
        val tintColor = if (enabled) {
            ContextCompat.getColor(this, R.color.control_tint_active)
        } else {
            ContextCompat.getColor(this, R.color.control_tint)
        }
        binding.btnShuffle.setColorFilter(tintColor)
    }

    private fun updateRepeatButton(mode: Int) {
        when (mode) {
            PlaybackService.REPEAT_ALL -> {
                binding.btnRepeat.setImageResource(R.drawable.ic_repeat)
                binding.btnRepeat.setColorFilter(ContextCompat.getColor(this, R.color.control_tint_active))
            }
            PlaybackService.REPEAT_ONE -> {
                binding.btnRepeat.setImageResource(R.drawable.ic_repeat_one)
                binding.btnRepeat.setColorFilter(ContextCompat.getColor(this, R.color.control_tint_active))
            }
            else -> {
                binding.btnRepeat.setImageResource(R.drawable.ic_repeat)
                binding.btnRepeat.setColorFilter(ContextCompat.getColor(this, R.color.control_tint))
            }
        }
    }

    override fun onDestroy() {
        if (isBound) {
            playbackService?.removeListener(this)
            unbindService(serviceConnection)
            isBound = false
        }
        sleepTimer?.cancel()
        super.onDestroy()
    }
}
