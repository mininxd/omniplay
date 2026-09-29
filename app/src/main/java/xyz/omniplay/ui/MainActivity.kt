package xyz.omniplay.ui

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.data.MusicScanner
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.util.AlbumArtLoader
import java.util.Locale

class MainActivity : AppCompatActivity(), PlaybackService.PlaybackListener {

    companion object {
        private const val PREFS_NAME = "omniplay_prefs"
        private const val KEY_MUSIC_FOLDER_URI = "key_music_folder_uri"
    }

    private lateinit var binding: ActivityMainBinding
    private var playbackService: PlaybackService? = null
    private var isBound = false
    private var isUserTrackingSlider = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
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

    // Storage Access Framework Folder Picker for directory selection
    private val folderPickerLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { treeUri: Uri? ->
        if (treeUri != null) {
            try {
                contentResolver.takePersistableUriPermission(
                    treeUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (ignored: Exception) {}

            getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_MUSIC_FOLDER_URI, treeUri.toString())
                .apply()

            loadMusicFromFolder(treeUri)
        } else {
            loadMusicLibrary()
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
            checkFolderOrScan()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDefaultView()
        setupInWindowPlaylistPanel()
        setupListeners()
        bindPlaybackService()
        checkAndRequestPermissions()
    }

    /**
     * Initializes the UI cleanly with no mock / hardcoded songs.
     */
    private fun setupDefaultView() {
        binding.songTitleText.text = getString(R.string.no_track_selected)
        binding.artistNameText.text = ""
        binding.albumNameText.text = ""
        binding.currentTimeText.text = getString(R.string.default_time)
        binding.totalTimeText.text = getString(R.string.default_time)
        binding.albumArtImage.setImageResource(R.drawable.default_album_art)

        binding.playbackSlider.valueFrom = 0.0f
        binding.playbackSlider.valueTo = 1.0f
        binding.playbackSlider.value = 0.0f
        binding.playbackSlider.isEnabled = false

        updateShuffleButton(false)
        updateRepeatButton(PlaybackService.REPEAT_OFF)
        updatePlayPauseButton(isPlaying = false)
    }

    /**
     * Configures the in-window persistent sliding panel for the playlist.
     * All playlist interaction stays in this single window without any modals or popups.
     */
    private fun setupInWindowPlaylistPanel() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.playlistSlidingPanel)
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> binding.ivChevron.rotation = 180f
                    BottomSheetBehavior.STATE_COLLAPSED -> binding.ivChevron.rotation = 0f
                    else -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Smoothly rotate the chevron as the user slides the panel up/down
                binding.ivChevron.rotation = slideOffset.coerceIn(0f, 1f) * 180f
            }
        })

        // Tap on peek header toggles panel between collapsed and expanded
        binding.playlistPeekHeader.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        // Initialize playlist adapter
        songAdapter = SongAdapter { song, index ->
            playbackService?.let { service ->
                val songsToPlay = scannedSongs.ifEmpty { listOf(song) }
                service.setSongQueue(songsToPlay, startIndex = index, startPlaying = true)
            }
        }

        binding.songsRecyclerView.adapter = songAdapter
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnSelectFolderEmpty.setOnClickListener {
            openFolderPicker()
        }
    }

    private fun setupListeners() {
        // Menu button (Hamburger)
        binding.btnMenu.setOnClickListener { view ->
            showOptionsMenu(view)
        }

        // Play / Pause Circular Card
        binding.btnPlayPauseCard.setOnClickListener {
            if (isBound) {
                playbackService?.let {
                    if (it.currentSong == null && scannedSongs.isNotEmpty()) {
                        it.setSongQueue(scannedSongs, startIndex = 0, startPlaying = true)
                    } else {
                        it.togglePlayPause()
                    }
                }
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

        // Material You Slider (YouTube Music style)
        binding.playbackSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                isUserTrackingSlider = true
            }

            override fun onStopTrackingTouch(slider: Slider) {
                isUserTrackingSlider = false
                playbackService?.seekTo((slider.value * 1000).toInt())
            }
        })

        binding.playbackSlider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                binding.currentTimeText.text = Song.formatTime((value * 1000).toLong())
            }
        }

        // Clicking album art opens/slides up the playlist panel
        binding.albumArtCard.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
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
            checkFolderOrScan()
        }
    }

    /**
     * Checks if user already selected a music directory. If not (first run), launches folder picker.
     */
    private fun checkFolderOrScan() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedFolderUri = prefs.getString(KEY_MUSIC_FOLDER_URI, null)

        if (savedFolderUri != null) {
            loadMusicFromFolder(Uri.parse(savedFolderUri))
        } else {
            Toast.makeText(this, "Select your music folder to scan songs", Toast.LENGTH_LONG).show()
            openFolderPicker()
        }
    }

    private fun openFolderPicker() {
        try {
            folderPickerLauncher.launch(null)
        } catch (e: Exception) {
            loadMusicLibrary()
        }
    }

    private fun loadMusicFromFolder(treeUri: Uri) {
        lifecycleScope.launch {
            Toast.makeText(this@MainActivity, "Scanning music folder...", Toast.LENGTH_SHORT).show()
            val folderSongs = musicScanner.scanFolder(treeUri)
            val songs = if (folderSongs.isNotEmpty()) {
                folderSongs
            } else {
                musicScanner.scanMediaStore()
            }

            updateSongList(songs)
        }
    }

    private fun loadMusicLibrary() {
        lifecycleScope.launch {
            val songs = musicScanner.scanMediaStore()
            updateSongList(songs)
        }
    }

    private fun updateSongList(songs: List<Song>) {
        scannedSongs = songs
        songAdapter?.setSongs(songs)

        if (songs.isNotEmpty()) {
            binding.songCountText.text = "${songs.size} songs"
            binding.emptyStateLayout.visibility = View.GONE
            binding.songsRecyclerView.visibility = View.VISIBLE
            if (playbackService?.currentSong == null) {
                playbackService?.setSongQueue(songs, startIndex = 0, startPlaying = false)
            }
        } else {
            binding.songCountText.text = "0 songs"
            binding.emptyStateLayout.visibility = View.VISIBLE
            binding.songsRecyclerView.visibility = View.GONE
        }
    }

    private fun showOptionsMenu(anchor: View) {
        val popup = PopupMenu(this, anchor, Gravity.END)
        popup.menuInflater.inflate(R.menu.main_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_select_folder -> {
                    openFolderPicker()
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
                    checkFolderOrScan()
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
        val song = playbackService?.currentSong
        if (song == null) {
            Toast.makeText(this, "No track currently playing", Toast.LENGTH_SHORT).show()
            return
        }

        val sizeMb = String.format(Locale.US, "%.2f MB", song.fileSize / (1024.0 * 1024.0))

        val details = """
            Title: ${song.title}
            Artist: ${song.artist}
            Album: ${song.album}
            Duration: ${Song.formatTime(song.duration)}
            Format: ${song.format}
            File Size: $sizeMb
            Path: ${song.filePath.ifEmpty { "Audio File" }}
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

        val durationSec = (song.duration / 1000).toFloat().coerceAtLeast(1.0f)
        binding.playbackSlider.valueFrom = 0.0f
        binding.playbackSlider.valueTo = durationSec
        binding.playbackSlider.value = 0.0f
        binding.playbackSlider.isEnabled = true

        songAdapter?.setCurrentPlayingSongId(song.id)

        // Asynchronously load real album art
        lifecycleScope.launch {
            val bitmap = AlbumArtLoader.loadAlbumArt(this@MainActivity, song)
            if (bitmap != null) {
                binding.albumArtImage.setImageBitmap(bitmap)
            } else {
                binding.albumArtImage.setImageResource(R.drawable.default_album_art)
            }
        }
    }

    override fun onPlaybackStateChanged(isPlaying: Boolean) {
        updatePlayPauseButton(isPlaying)
    }

    override fun onProgressUpdate(currentPositionMs: Int, totalDurationMs: Int) {
        if (!isUserTrackingSlider && binding.playbackSlider.isEnabled) {
            val currentSec = (currentPositionMs / 1000).toFloat()
            val totalSec = (totalDurationMs / 1000).toFloat().coerceAtLeast(1.0f)

            if (binding.playbackSlider.valueTo != totalSec) {
                binding.playbackSlider.valueTo = totalSec
            }
            binding.playbackSlider.value = currentSec.coerceIn(0.0f, binding.playbackSlider.valueTo)
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
