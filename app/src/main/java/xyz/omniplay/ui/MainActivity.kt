package xyz.omniplay.ui

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.GestureDetector
import android.view.Gravity
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.text.Editable
import android.text.TextWatcher
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.data.MusicDatabase
import xyz.omniplay.data.MusicScanner
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.lyrics.LyricLine
import xyz.omniplay.lyrics.Lyrics
import xyz.omniplay.lyrics.LyricsResult
import xyz.omniplay.lyrics.LrcLibClient
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.AudioTrackInfo
import xyz.omniplay.util.MusicFolderManager
import xyz.omniplay.util.ThemeColors
import java.io.File
import java.util.Locale

class MainActivity : AppCompatActivity(), PlaybackService.PlaybackListener {

    companion object {
        const val PREFS_NAME = "omniplay_prefs"
        const val KEY_MUSIC_FOLDER_URI = "key_music_folder_uri"
        const val KEY_MUSIC_FOLDERS_SET = "key_music_folders_set"
        const val KEY_SHOW_ALBUM_ART_IN_PLAYLIST = "key_show_album_art_in_playlist"
        const val KEY_FETCH_ONLINE_ARTWORK = "key_fetch_online_artwork"
        private const val KEY_SORT_FIELD = "key_sort_field"
        private const val KEY_SORT_ASCENDING = "key_sort_ascending"
        const val KEY_UNIVERSAL_SEARCH = "key_universal_search"
    }

    private lateinit var binding: ActivityMainBinding
    private var playbackService: PlaybackService? = null
    private var isBound = false
    private var isUserTrackingSlider = false

    // Lyrics State
    private var lyricAdapter: LyricAdapter? = null
    private var currentLyrics: Lyrics? = null
    private var lastOnlineLyricsSongId: Long? = null
    private var isLyricsShowing = false
    private var isUserScrollingLyrics = false
    private val lyricsScrollResetHandler = Handler(Looper.getMainLooper())
    private val resetUserScrollingRunnable = Runnable { isUserScrollingLyrics = false }
    private var lyricsFetchJob: Job? = null

    enum class LibraryFilterMode {
        TRACK, ARTIST, ALBUM, FOLDER
    }

    enum class SortField {
        TITLE, DATE, ARTIST, ALBUM
    }

    private data class SortOption(
        val label: String,
        val field: SortField,
        val ascending: Boolean
    )

    private var currentSortField: SortField = SortField.TITLE
    private var isSortAscending: Boolean = true

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private val musicScanner by lazy { MusicScanner(this) }
    private val musicDatabase by lazy { MusicDatabase.getInstance(this) }
    private var scannedSongs = listOf<Song>()
    private var allScannedSongs = listOf<Song>()
    private var displayedSongs = listOf<Song>()
    private var currentFilterMode = LibraryFilterMode.TRACK
    private var selectedFilterValue: String? = null
    private var isFilterPreviewActive = false
    private var activeOngoingQueueTitle: String? = null
    private var activeOngoingFilterMode = LibraryFilterMode.TRACK
    private var activeOngoingQueue = listOf<Song>()
    private var currentFolderNavigationPath = mutableListOf<String>()
    private var isAllTracksDrillDown = false
    private var currentArtistDrillDown: String? = null
    private var currentAlbumDrillDown: String? = null
    private var drawerFilterAdapter: DrawerFilterAdapter? = null
    private var songAdapter: SongAdapter? = null
    private var currentSearchQuery = ""
    private var isUniversalSearch = false
    private var safeTopCutoutInset: Int = 0
    private var safeLeftCutoutInset: Int = 0
    private var safeRightCutoutInset: Int = 0

    private var isFilterMenuOpen = false
    private var filterDrawerAnimator: ValueAnimator? = null

    private var pendingExternalUri: Uri? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as PlaybackService.LocalBinder
            playbackService = binder.getService()
            isBound = true
            playbackService?.addListener(this@MainActivity)

            val pendingUri = pendingExternalUri
            if (pendingUri != null) {
                pendingExternalUri = null
                playExternalAudioUri(pendingUri)
            }

            val currentQueueList = displayedSongs.ifEmpty { scannedSongs }
            if (currentQueueList.isNotEmpty()) {
                if (playbackService?.currentSong != null) {
                    playbackService?.refreshQueue(currentQueueList)
                } else if (playbackService?.queue.isNullOrEmpty()) {
                    playbackService?.setSongQueue(currentQueueList, startIndex = 0, startPlaying = false)
                }
            }

            if (playbackService?.currentSong != null) {
                updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            playbackService?.removeListener(this@MainActivity)
            playbackService = null
            isBound = false
        }
    }

    // Settings launcher for Activity-based settings window
    private val settingsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isShowArt = prefs.getBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)
        songAdapter?.setShowAlbumArt(isShowArt)

        val themeChanged = result.data?.getBooleanExtra(SettingsActivity.EXTRA_THEME_CHANGED, false) == true
        if (themeChanged) {
            recreate()
            return@registerForActivityResult
        }

        val rescanRequested = result.data?.getBooleanExtra(SettingsActivity.EXTRA_RESCAN, false) == true
        val folderChanged = result.data?.getBooleanExtra(SettingsActivity.EXTRA_FOLDER_CHANGED, false) == true

        if (rescanRequested) {
            rescanMusic()
        } else if (folderChanged) {
            val folders = MusicFolderManager.getFolders(this)
            if (folders.isNotEmpty()) {
                loadMusicFromConfiguredFolders(isUserInitiated = true, isRescan = true)
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    musicDatabase.clearAll()
                }
                updateSongList(emptyList())
            }
        }
    }

    // Storage Access Framework Folder Picker for directory selection
    private val folderPickerLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) { treeUri: Uri? ->
        if (treeUri != null) {
            MusicFolderManager.addFolder(this, treeUri)
            loadMusicFromConfiguredFolders(isUserInitiated = true, isRescan = true)
        } else {
            val folders = MusicFolderManager.getFolders(this)
            if (folders.isNotEmpty()) {
                loadMusicFromConfiguredFolders(isUserInitiated = false, isRescan = false)
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    musicDatabase.clearAll()
                }
                updateSongList(emptyList())
                Toast.makeText(this, "No folder selected. Please select a music folder.", Toast.LENGTH_SHORT).show()
            }
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
        DynamicColors.applyIfAvailable(this)
        xyz.omniplay.util.ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        xyz.omniplay.util.ThemeHelper.applySystemBars(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        currentSortField = try {
            SortField.valueOf(prefs.getString(KEY_SORT_FIELD, SortField.TITLE.name) ?: SortField.TITLE.name)
        } catch (e: Exception) {
            SortField.TITLE
        }
        isSortAscending = prefs.getBoolean(KEY_SORT_ASCENDING, true)
        isUniversalSearch = prefs.getBoolean(KEY_UNIVERSAL_SEARCH, false)

        setupBackPressHandler()
        setupDefaultView()
        setupInWindowPlaylistPanel()
        setupLeftDrawerMenu()
        setupBottomSwipeGesture()
        setupAlbumArtSwipeGesture()
        setupLyricsView()
        setupTitleDoubleTapGestures()
        setupListeners()
        bindPlaybackService()
        checkAndRequestPermissions()
        xyz.omniplay.sync.OmniSyncManager.getInstance(this).addListener(omniSyncListener)
        handleIncomingIntent(intent)
        checkForUpdatesOnStart()
    }

    private fun checkForUpdatesOnStart() {
        lifecycleScope.launch {
            val release = xyz.omniplay.util.UpdateChecker.checkLatestRelease(this@MainActivity)
            if (isFinishing || isDestroyed) return@launch

            if (release != null && release.isNewer) {
                xyz.omniplay.util.UpdateChecker.showUpdateDialog(this@MainActivity, release)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateOmniSyncButtonState(xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole)
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isShowArt = prefs.getBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)
        songAdapter?.setShowAlbumArt(isShowArt)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIncomingIntent(intent)
    }

    private fun handleIncomingIntent(intent: Intent?) {
        if (intent?.action == Intent.ACTION_VIEW) {
            val audioUri = intent.data ?: intent.clipData?.let {
                if (it.itemCount > 0) it.getItemAt(0).uri else null
            }
            if (audioUri != null) {
                try {
                    contentResolver.takePersistableUriPermission(
                        audioUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (ignored: Exception) {}

                playExternalAudioUri(audioUri)
            }
        }
    }

    private fun playExternalAudioUri(uri: Uri) {
        val s = playbackService
        if (s == null || !isBound) {
            pendingExternalUri = uri
            return
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val externalSong = musicScanner.extractSongFromUri(uri)
            withContext(Dispatchers.Main) {
                s.playExternalSong(externalSong)
                updateAudioBadges(externalSong, s.currentAudioInfo)
                if (::bottomSheetBehavior.isInitialized) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }
    }

    /**
     * Closes left drawer, lyrics card, or playlist panel when pressing back button instead of exiting the app.
     */
    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isFilterMenuOpen) {
                    when {
                        isAllTracksDrillDown -> {
                            isAllTracksDrillDown = false
                            updateFilterSubList()
                        }
                        currentArtistDrillDown != null -> {
                            currentArtistDrillDown = null
                            updateFilterSubList()
                        }
                        currentAlbumDrillDown != null -> {
                            currentAlbumDrillDown = null
                            updateFilterSubList()
                        }
                        currentFolderNavigationPath.isNotEmpty() && binding.filterToggleGroup.checkedButtonId == R.id.btn_filter_folders -> {
                            currentFolderNavigationPath.removeAt(currentFolderNavigationPath.lastIndex)
                            updateFilterSubList()
                        }
                        else -> {
                            closeLeftMenu()
                        }
                    }
                } else if (isLyricsShowing) {
                    closeLyricsCard()
                } else if (binding.searchBarContainer.visibility == View.VISIBLE) {
                    closeSearchBar()
                } else if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
            }
        })
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (isFilterMenuOpen) {
            when {
                isAllTracksDrillDown -> {
                    isAllTracksDrillDown = false
                    updateFilterSubList()
                    return
                }
                currentArtistDrillDown != null -> {
                    currentArtistDrillDown = null
                    updateFilterSubList()
                    return
                }
                currentAlbumDrillDown != null -> {
                    currentAlbumDrillDown = null
                    updateFilterSubList()
                    return
                }
                currentFolderNavigationPath.isNotEmpty() && binding.filterToggleGroup.checkedButtonId == R.id.btn_filter_folders -> {
                    currentFolderNavigationPath.removeAt(currentFolderNavigationPath.lastIndex)
                    updateFilterSubList()
                    return
                }
                else -> {
                    closeLeftMenu()
                    return
                }
            }
        }
        if (isLyricsShowing) {
            closeLyricsCard()
            return
        }
        if (binding.searchBarContainer.visibility == View.VISIBLE) {
            closeSearchBar()
            return
        }
        if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            return
        }
        super.onBackPressed()
    }

    /**
     * Initializes the UI cleanly with no mock / hardcoded songs.
     */
    private fun setupDefaultView() {
        binding.songTitleText.text = getString(R.string.no_track_selected)
        binding.songTitleText.isSelected = true
        binding.artistNameText.text = ""
        binding.artistNameText.isSelected = true
        binding.albumNameText.text = ""
        binding.currentTimeText.text = getString(R.string.default_time)
        binding.totalTimeText.text = getString(R.string.default_time)
        binding.albumArtImage.setImageResource(R.drawable.default_album_art)

        binding.playbackSlider.setDuration(1000L)
        binding.playbackSlider.setProgress(0L)
        binding.playbackSlider.setPlaying(false)
        binding.playbackSlider.isEnabled = false

        updateAudioBadges(null, null)

        updateShuffleButton(false)
        updateRepeatButton(PlaybackService.REPEAT_OFF)
        updatePlayPauseButton(isPlaying = false)
        updateAppTitle()
    }

    /**
     * Configures the in-window persistent sliding panel for the playlist.
     */
    private fun setupInWindowPlaylistPanel() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.playlistSlidingPanel)
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.halfExpandedRatio = 0.62f
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        fun updateSheetDimensions(topInset: Int = 0) {
            bottomSheetBehavior.isFitToContents = false
            bottomSheetBehavior.halfExpandedRatio = 0.62f
            bottomSheetBehavior.maxHeight = -1
            bottomSheetBehavior.expandedOffset = 0
        }

        // Apply system window insets so queue peek header and list items are never hidden behind navigation bar or cutout
        ViewCompat.setOnApplyWindowInsetsListener(binding.playlistSlidingPanel) { _, insets ->
            val navInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            val statusInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val cutoutInsets = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
            val topCutout = insets.displayCutout?.safeInsetTop ?: 0
            val leftCutout = insets.displayCutout?.safeInsetLeft ?: 0
            val rightCutout = insets.displayCutout?.safeInsetRight ?: 0

            safeTopCutoutInset = maxOf(statusInsets.top, cutoutInsets.top, topCutout)
            safeLeftCutoutInset = maxOf(cutoutInsets.left, leftCutout)
            safeRightCutoutInset = maxOf(cutoutInsets.right, rightCutout)

            val basePeekHeight = (64 * resources.displayMetrics.density).toInt()
            bottomSheetBehavior.peekHeight = basePeekHeight + navInsets.bottom

            binding.playlistPeekHeader.setPadding(
                binding.playlistPeekHeader.paddingLeft,
                binding.playlistPeekHeader.paddingTop,
                binding.playlistPeekHeader.paddingRight,
                navInsets.bottom
            )

            binding.songsRecyclerView.setPadding(
                binding.songsRecyclerView.paddingLeft,
                binding.songsRecyclerView.paddingTop,
                binding.songsRecyclerView.paddingRight,
                navInsets.bottom + (28 * resources.displayMetrics.density).toInt()
            )

            updateSheetDimensions(0)
            updatePlaylistCutoutPadding()
            insets
        }

        binding.coordinatorRoot.post {
            updateSheetDimensions(0)
            updatePlaylistCutoutPadding()
        }

        // Outer Scrim: Close queue when pressing / tapping / dragging down on the area outside the half-expanded queue
        val queueScrimGestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    return true
                }
                return false
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                if (velocityY > 200f && bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    return true
                }
                return false
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
                if (distanceY < -20f && bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    return true
                }
                return false
            }
        })
        binding.queueScrimOverlay.setOnTouchListener { v, event ->
            if (queueScrimGestureDetector.onTouchEvent(event)) {
                true
            } else {
                if (event.action == MotionEvent.ACTION_UP) {
                    v.performClick()
                }
                true
            }
        }
        binding.queueScrimOverlay.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.ivChevron.rotation = 180f
                        binding.queueScrimOverlay.visibility = View.VISIBLE
                        binding.queueScrimOverlay.alpha = 0.5f
                        updatePlaylistCutoutPadding(1f)
                        updateAppTitle()
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        binding.ivChevron.rotation = 90f
                        binding.queueScrimOverlay.visibility = View.VISIBLE
                        binding.queueScrimOverlay.alpha = 0.4f
                        updatePlaylistCutoutPadding(0f)
                        updateAppTitle()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.ivChevron.rotation = 0f
                        binding.queueScrimOverlay.visibility = View.GONE
                        binding.queueScrimOverlay.alpha = 0f
                        updatePlaylistCutoutPadding(0f)
                        if (binding.searchBarContainer.visibility == View.VISIBLE) {
                            closeSearchBar(clearQuery = true)
                        }
                        if (isFilterPreviewActive) {
                            revertToOngoingQueue()
                        }
                        updateAppTitle()
                    }
                    BottomSheetBehavior.STATE_DRAGGING,
                    BottomSheetBehavior.STATE_SETTLING -> {
                        if (binding.queueScrimOverlay.visibility != View.VISIBLE) {
                            binding.queueScrimOverlay.visibility = View.VISIBLE
                        }
                    }
                    else -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > 0.01f) {
                    binding.queueScrimOverlay.visibility = View.VISIBLE
                    binding.queueScrimOverlay.alpha = (slideOffset * 0.45f).coerceIn(0f, 0.5f)
                } else if (slideOffset <= 0f && bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.queueScrimOverlay.visibility = View.GONE
                    binding.queueScrimOverlay.alpha = 0f
                }

                if (slideOffset >= 0f) {
                    val parentH = binding.coordinatorRoot.height.takeIf { it > 0 } ?: binding.root.height
                    val collapsedOffset = (parentH - bottomSheetBehavior.peekHeight).toFloat()
                    val halfExpandedOffset = parentH * (1f - bottomSheetBehavior.halfExpandedRatio)
                    val expandedOffset = bottomSheetBehavior.expandedOffset.toFloat()

                    val travelToHalf = (collapsedOffset - halfExpandedOffset).coerceAtLeast(1f)
                    val totalTravel = (collapsedOffset - expandedOffset).coerceAtLeast(travelToHalf + 1f)
                    val halfSlideOffset = (travelToHalf / totalTravel).coerceIn(0.1f, 0.9f)

                    val rotation = if (slideOffset <= halfSlideOffset) {
                        (slideOffset / halfSlideOffset).coerceIn(0f, 1f) * 90f
                    } else {
                        90f + ((slideOffset - halfSlideOffset) / (1f - halfSlideOffset)).coerceIn(0f, 1f) * 90f
                    }
                    binding.ivChevron.rotation = rotation

                    val progressToExpanded = if (slideOffset <= halfSlideOffset) {
                        0f
                    } else {
                        ((slideOffset - halfSlideOffset) / (1f - halfSlideOffset)).coerceIn(0f, 1f)
                    }
                    updatePlaylistCutoutPadding(progressToExpanded)
                }
            }
        })

        // Tap on peek header toggles panel between collapsed and half expanded (YouTube Music height)
        binding.playlistPeekHeader.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        binding.sheetTitleText.isSelected = true

        binding.btnSearchQueue.setOnClickListener {
            if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            if (binding.searchBarContainer.visibility == View.VISIBLE) {
                closeSearchBar()
            } else {
                openSearchBar()
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?.trim() ?: ""
                currentSearchQuery = query
                binding.btnClearSearch.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
                applyCurrentFilter()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
                true
            } else {
                false
            }
        }

        binding.btnClearSearch.setOnClickListener {
            binding.searchEditText.setText("")
            currentSearchQuery = ""
            applyCurrentFilter()
        }

        updateSearchModeUI()

        binding.btnSearchMode.setOnClickListener {
            isUniversalSearch = !isUniversalSearch
            getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_UNIVERSAL_SEARCH, isUniversalSearch)
                .apply()
            updateSearchModeUI()
            applyCurrentFilter()
        }

        binding.btnSortQueue.setOnClickListener {
            if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
            showSortDialog()
        }

        // Initialize playlist adapter
        val isShowArt = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)

        songAdapter = SongAdapter(showAlbumArt = isShowArt) { song, index ->
            if (xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                Toast.makeText(this, "OmniSync is active: Disconnect from OmniSync to play local media", Toast.LENGTH_SHORT).show()
                return@SongAdapter
            }

            if (binding.searchBarContainer.visibility == View.VISIBLE) {
                closeSearchBar(clearQuery = true)
            }

            val wasPreview = isFilterPreviewActive
            isFilterPreviewActive = false
            activeOngoingQueueTitle = selectedFilterValue
            activeOngoingFilterMode = currentFilterMode

            if (::bottomSheetBehavior.isInitialized) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            playbackService?.let { service ->
                val fullList = if (displayedSongs.isNotEmpty()) {
                    displayedSongs
                } else if (allScannedSongs.isNotEmpty()) {
                    sortSongs(allScannedSongs, currentSortField, isSortAscending)
                } else {
                    listOf(song)
                }
                activeOngoingQueue = fullList

                val targetIdx = fullList.indexOfFirst { it.id == song.id }.coerceAtLeast(0)

                val queueMatches = !wasPreview &&
                        service.queue.size == fullList.size &&
                        service.queue.any { it.id == song.id }

                if (queueMatches) {
                    service.playSongFromPlaylist(song, startPlaying = true)
                } else {
                    service.setSongQueue(fullList, startIndex = targetIdx, startPlaying = true)
                }
            }
        }

        binding.songsRecyclerView.adapter = songAdapter
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Auto full height when scrolling the queue list
        binding.songsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!::bottomSheetBehavior.isInitialized) return

                if (dy > 4 && bottomSheetBehavior.state == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else if (dy < -4 && bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    val lm = recyclerView.layoutManager as? LinearLayoutManager
                    val firstVisible = lm?.findFirstCompletelyVisibleItemPosition() ?: -1
                    if (firstVisible == 0 || !recyclerView.canScrollVertically(-1)) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
                }
            }
        })

        binding.btnSelectFolderEmpty.setOnClickListener {
            openFolderPicker()
        }

    }

    /**
     * Interactive gesture listener on the bottom section below playback controls.
     * Allows real-time dragging/peeking of the playlist panel up and down without snapping directly open.
     * If dragged back down towards the bottom, the playlist remains collapsed.
     */
    private fun setupBottomSwipeGesture() {
        var startY = 0f
        var isDraggingSheet = false
        var velocityTracker: VelocityTracker? = null
        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop
        var activeAnimator: ValueAnimator? = null

        binding.bottomGestureArea.setOnTouchListener { _, event ->
            if (!::bottomSheetBehavior.isInitialized || bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                return@setOnTouchListener false
            }

            val parentH = binding.coordinatorRoot.height.takeIf { it > 0 } ?: binding.root.height
            val maxSheetH = (parentH * 0.62f).toInt()
            val targetTop = (parentH - maxSheetH).coerceAtLeast(0)
            val collapsedTop = parentH - bottomSheetBehavior.peekHeight
            val maxTravel = (collapsedTop - targetTop).toFloat().coerceAtLeast(1f)

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    activeAnimator?.cancel()
                    startY = event.rawY
                    isDraggingSheet = false
                    velocityTracker?.recycle()
                    velocityTracker = VelocityTracker.obtain().apply {
                        addMovement(event)
                    }
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    velocityTracker?.addMovement(event)
                    val deltaTotalY = event.rawY - startY

                    if (!isDraggingSheet && Math.abs(deltaTotalY) > touchSlop) {
                        if (deltaTotalY < 0) { // Moving upwards
                            isDraggingSheet = true
                        }
                    }

                    if (isDraggingSheet) {
                        // Clamp translation between -maxTravel (expanded) and 0f (collapsed)
                        val targetTranslation = deltaTotalY.coerceIn(-maxTravel, 0f)
                        binding.playlistSlidingPanel.translationY = targetTranslation
                        val progress = (-targetTranslation / maxTravel).coerceIn(0f, 1f)
                        binding.ivChevron.rotation = progress * 90f
                    }
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    velocityTracker?.addMovement(event)
                    if (isDraggingSheet) {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val yVel = velocityTracker?.yVelocity ?: 0f
                        val currentTrans = binding.playlistSlidingPanel.translationY
                        val progress = (-currentTrans / maxTravel).coerceIn(0f, 1f)

                        // If user flung upward fast (< -800) or dragged past 40% (0.4) and didn't fling downward
                        val shouldExpand = when {
                            yVel < -800f -> true
                            yVel > 800f -> false
                            else -> progress >= 0.4f
                        }

                        val targetY = if (shouldExpand) -maxTravel else 0f
                        val duration = (250 * if (shouldExpand) (1f - progress) else progress).toLong().coerceIn(100L, 300L)

                        activeAnimator = ValueAnimator.ofFloat(currentTrans, targetY).apply {
                            this.duration = duration
                            interpolator = DecelerateInterpolator()
                            addUpdateListener { anim ->
                                val v = anim.animatedValue as Float
                                binding.playlistSlidingPanel.translationY = v
                                binding.ivChevron.rotation = (-v / maxTravel).coerceIn(0f, 1f) * 90f
                            }
                            addListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    val panel = binding.playlistSlidingPanel
                                    if (shouldExpand) {
                                        val offset = targetTop - panel.top
                                        panel.offsetTopAndBottom(offset)
                                        panel.translationY = 0f
                                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                                        binding.ivChevron.rotation = 90f
                                    } else {
                                        panel.translationY = 0f
                                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                                        binding.ivChevron.rotation = 0f
                                    }
                                    activeAnimator = null
                                }
                            })
                            start()
                        }
                        isDraggingSheet = false
                    }
                    velocityTracker?.recycle()
                    velocityTracker = null
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Interactive gesture listener on album art card.
     * Allows real-time dragging/peeking left or right.
     * Peeking dynamically reveals the peek_album_art_card underneath with the actual next or previous song's
     * album art, title, and artist, so users can see what songs next or prev before committing!
     * Dragging back to center snaps back to original position with no track change.
     * Dragging past threshold or fast fling commits to skipNext (swipe left) or skipPrevious (swipe right).
     * Simple tap without dragging expands the playlist sheet.
     */
    private fun setupAlbumArtSwipeGesture() {
        var startX = 0f
        var startY = 0f
        var isDraggingX = false
        var isDraggingY = false
        var velocityTracker: VelocityTracker? = null
        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop
        var activeAnimator: ValueAnimator? = null
        var currentPeekSong: Song? = null
        var peekDirection = 0 // -1 = next (swipe left), +1 = prev (swipe right)

        fun updatePeekCard(direction: Int) {
            val peekSong = if (direction == -1) {
                playbackService?.getNextSong()
            } else {
                playbackService?.getPreviousSong()
            }

            if (peekSong != null) {
                if (currentPeekSong?.id != peekSong.id || peekDirection != direction) {
                    currentPeekSong = peekSong
                    peekDirection = direction
                    binding.peekAlbumArtCard.visibility = View.VISIBLE

                    val isNext = (direction == -1)
                    val gravity = if (isNext) Gravity.END else Gravity.START
                    val textAlignment = if (isNext) View.TEXT_ALIGNMENT_VIEW_END else View.TEXT_ALIGNMENT_VIEW_START

                    binding.peekBadgeLayout.gravity = gravity

                    (binding.peekLabelText.layoutParams as? android.widget.LinearLayout.LayoutParams)?.let { lp ->
                        lp.gravity = gravity
                        binding.peekLabelText.layoutParams = lp
                    }
                    binding.peekLabelText.gravity = gravity
                    binding.peekLabelText.textAlignment = textAlignment
                    binding.peekLabelText.text = if (isNext) "NEXT TRACK" else "PREVIOUS TRACK"

                    (binding.peekTitleText.layoutParams as? android.widget.LinearLayout.LayoutParams)?.let { lp ->
                        lp.gravity = gravity
                        binding.peekTitleText.layoutParams = lp
                    }
                    binding.peekTitleText.gravity = gravity
                    binding.peekTitleText.textAlignment = textAlignment
                    binding.peekTitleText.text = peekSong.title

                    (binding.peekArtistText.layoutParams as? android.widget.LinearLayout.LayoutParams)?.let { lp ->
                        lp.gravity = gravity
                        binding.peekArtistText.layoutParams = lp
                    }
                    binding.peekArtistText.gravity = gravity
                    binding.peekArtistText.textAlignment = textAlignment
                    binding.peekArtistText.text = peekSong.artist.trim().ifEmpty { getString(R.string.unknown_artist) }

                    val cached = AlbumArtLoader.getCachedAlbumArt(peekSong)
                    if (cached != null) {
                        binding.peekAlbumArtImage.setImageBitmap(cached)
                        updatePeekTextContrast(isBitmapBright(cached))
                    } else {
                        binding.peekAlbumArtImage.setImageResource(R.drawable.default_album_art)
                        updatePeekTextContrast(isBright = false)
                        lifecycleScope.launch {
                            val bitmap = AlbumArtLoader.loadAlbumArt(this@MainActivity, peekSong)
                            if (currentPeekSong?.id == peekSong.id && ::binding.isInitialized) {
                                if (bitmap != null) {
                                    binding.peekAlbumArtImage.setImageBitmap(bitmap)
                                    updatePeekTextContrast(isBitmapBright(bitmap))
                                }
                            }
                        }
                    }
                }
            } else {
                currentPeekSong = null
                peekDirection = direction
                binding.peekAlbumArtCard.visibility = View.INVISIBLE
            }
        }

        binding.albumArtCard.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    activeAnimator?.cancel()
                    binding.peekAlbumArtCard.animate().cancel()
                    binding.lyricsCard.animate().cancel()
                    startX = event.rawX
                    startY = event.rawY
                    isDraggingX = false
                    isDraggingY = false
                    currentPeekSong = null
                    peekDirection = 0
                    binding.peekAlbumArtCard.visibility = View.INVISIBLE
                    binding.peekAlbumArtCard.scaleX = 0.90f
                    binding.peekAlbumArtCard.scaleY = 0.90f
                    binding.peekAlbumArtCard.alpha = 0.6f
                    velocityTracker?.recycle()
                    velocityTracker = VelocityTracker.obtain().apply {
                        addMovement(event)
                    }
                    v.parent?.requestDisallowInterceptTouchEvent(true)
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    velocityTracker?.addMovement(event)
                    val deltaX = event.rawX - startX
                    val deltaY = event.rawY - startY

                    if (!isDraggingX && !isDraggingY) {
                        if (Math.abs(deltaX) > 8f && Math.abs(deltaX) > Math.abs(deltaY) * 0.7f) {
                            isDraggingX = true
                            v.parent?.requestDisallowInterceptTouchEvent(true)
                        } else if (deltaY < -8f && Math.abs(deltaY) > Math.abs(deltaX) * 0.7f) {
                            isDraggingY = true
                            v.parent?.requestDisallowInterceptTouchEvent(true)
                            binding.peekAlbumArtCard.visibility = View.INVISIBLE
                            binding.lyricsCard.visibility = View.VISIBLE
                            binding.lyricsCard.translationX = 0f
                            binding.lyricsCard.translationY = 0f
                            binding.lyricsCard.scaleX = 0.92f
                            binding.lyricsCard.scaleY = 0.92f
                            binding.lyricsCard.alpha = 0.5f
                            prepareLyricsForDisplay()
                        } else if (deltaY > 35f && Math.abs(deltaY) > Math.abs(deltaX) * 2f) {
                            v.parent?.requestDisallowInterceptTouchEvent(false)
                        }
                    }

                    if (isDraggingX) {
                        val cardWidth = v.width.toFloat().coerceAtLeast(1f)
                        val dir = if (deltaX < 0) -1 else 1
                        updatePeekCard(dir)

                        val effectiveDeltaX = if (currentPeekSong == null) deltaX * 0.25f else deltaX
                        val rotationDeg = (effectiveDeltaX / cardWidth) * 12f
                        v.translationX = effectiveDeltaX
                        v.translationY = 0f
                        v.rotation = rotationDeg

                        if (currentPeekSong != null) {
                            val progress = (Math.abs(effectiveDeltaX) / (cardWidth * 0.35f)).coerceIn(0f, 1f)
                            binding.peekAlbumArtCard.scaleX = 0.92f + 0.08f * progress
                            binding.peekAlbumArtCard.scaleY = 0.92f + 0.08f * progress
                            binding.peekAlbumArtCard.alpha = 0.5f + 0.5f * progress
                        }
                    } else if (isDraggingY) {
                        val cardHeight = v.height.toFloat().coerceAtLeast(1f)
                        val effectiveDeltaY = if (deltaY < 0f) deltaY else deltaY * 0.2f
                        v.translationY = effectiveDeltaY
                        v.translationX = 0f
                        v.rotation = 0f

                        val progress = (Math.abs(effectiveDeltaY) / (cardHeight * 0.35f)).coerceIn(0f, 1f)
                        binding.lyricsCard.scaleX = 0.92f + 0.08f * progress
                        binding.lyricsCard.scaleY = 0.92f + 0.08f * progress
                        binding.lyricsCard.alpha = 0.5f + 0.5f * progress
                    }
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    velocityTracker?.addMovement(event)
                    v.parent?.requestDisallowInterceptTouchEvent(false)

                    if (isDraggingX) {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val xVel = velocityTracker?.xVelocity ?: 0f
                        val currentX = v.translationX
                        val cardWidth = v.width.toFloat().coerceAtLeast(1f)
                        val absX = Math.abs(currentX)
                        val ratio = absX / cardWidth
                        val isCanceled = (event.actionMasked == MotionEvent.ACTION_CANCEL)

                        val isSwipeNext = !isCanceled && currentX < 0 && (
                            ratio >= 0.50f ||
                            (ratio >= 0.18f && xVel < 350f) ||
                            (absX >= 15f && xVel < -250f)
                        )
                        val isSwipePrev = !isCanceled && currentX > 0 && (
                            ratio >= 0.50f ||
                            (ratio >= 0.18f && xVel > -350f) ||
                            (absX >= 15f && xVel > 250f)
                        )

                        val nextSong = currentPeekSong ?: playbackService?.getNextSong()
                        val prevSong = currentPeekSong ?: playbackService?.getPreviousSong()

                        if (isSwipeNext && nextSong != null) {
                            val targetX = -cardWidth * 1.25f
                            binding.peekAlbumArtCard.animate()
                                .scaleX(1f).scaleY(1f).alpha(1f)
                                .setDuration(180L).start()

                            activeAnimator = ValueAnimator.ofFloat(currentX, targetX).apply {
                                duration = 180L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    val x = anim.animatedValue as Float
                                    v.translationX = x
                                    v.rotation = (x / cardWidth) * 12f
                                }
                                var isCanc = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanc = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanc) return
                                        val songBefore = playbackService?.currentSong
                                        playbackService?.skipNext(forceNext = true)
                                        val songAfter = playbackService?.currentSong

                                        if (songAfter != null && songAfter.id != songBefore?.id) {
                                            binding.peekAlbumArtImage.drawable?.let {
                                                binding.albumArtImage.setImageDrawable(it)
                                            }
                                        } else {
                                            val current = songBefore ?: songAfter
                                            if (current != null) {
                                                val cached = AlbumArtLoader.getCachedAlbumArt(current)
                                                if (cached != null) {
                                                    binding.albumArtImage.setImageBitmap(cached)
                                                } else {
                                                    binding.albumArtImage.setImageResource(R.drawable.default_album_art)
                                                }
                                            }
                                        }

                                        v.translationX = 0f
                                        v.translationY = 0f
                                        v.rotation = 0f
                                        binding.peekAlbumArtCard.visibility = View.INVISIBLE
                                        binding.peekAlbumArtCard.scaleX = 0.90f
                                        binding.peekAlbumArtCard.scaleY = 0.90f
                                        binding.peekAlbumArtCard.alpha = 0.6f
                                        currentPeekSong = null
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        } else if (isSwipePrev && prevSong != null) {
                            val targetX = cardWidth * 1.25f
                            binding.peekAlbumArtCard.animate()
                                .scaleX(1f).scaleY(1f).alpha(1f)
                                .setDuration(180L).start()

                            activeAnimator = ValueAnimator.ofFloat(currentX, targetX).apply {
                                duration = 180L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    val x = anim.animatedValue as Float
                                    v.translationX = x
                                    v.rotation = (x / cardWidth) * 12f
                                }
                                var isCanc = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanc = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanc) return
                                        val songBefore = playbackService?.currentSong
                                        playbackService?.skipPrevious(forcePrevious = true)
                                        val songAfter = playbackService?.currentSong

                                        if (songAfter != null && songAfter.id != songBefore?.id) {
                                            binding.peekAlbumArtImage.drawable?.let {
                                                binding.albumArtImage.setImageDrawable(it)
                                            }
                                        } else {
                                            val current = songBefore ?: songAfter
                                            if (current != null) {
                                                val cached = AlbumArtLoader.getCachedAlbumArt(current)
                                                if (cached != null) {
                                                    binding.albumArtImage.setImageBitmap(cached)
                                                } else {
                                                    binding.albumArtImage.setImageResource(R.drawable.default_album_art)
                                                }
                                            }
                                        }

                                        v.translationX = 0f
                                        v.translationY = 0f
                                        v.rotation = 0f
                                        binding.peekAlbumArtCard.visibility = View.INVISIBLE
                                        binding.peekAlbumArtCard.scaleX = 0.90f
                                        binding.peekAlbumArtCard.scaleY = 0.90f
                                        binding.peekAlbumArtCard.alpha = 0.6f
                                        currentPeekSong = null
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        } else {
                            binding.peekAlbumArtCard.animate()
                                .scaleX(0.90f).scaleY(0.90f).alpha(0.6f)
                                .setDuration(200L).start()

                            activeAnimator = ValueAnimator.ofFloat(currentX, 0f).apply {
                                duration = 200L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    val x = anim.animatedValue as Float
                                    v.translationX = x
                                    v.rotation = (x / cardWidth) * 12f
                                }
                                var isCanc = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanc = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanc) return
                                        v.translationX = 0f
                                        v.translationY = 0f
                                        v.rotation = 0f
                                        binding.peekAlbumArtCard.visibility = View.INVISIBLE
                                        binding.peekAlbumArtCard.scaleX = 0.90f
                                        binding.peekAlbumArtCard.scaleY = 0.90f
                                        binding.peekAlbumArtCard.alpha = 0.6f
                                        currentPeekSong = null
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        }
                        isDraggingX = false
                    } else if (isDraggingY) {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val yVel = velocityTracker?.yVelocity ?: 0f
                        val currentY = v.translationY
                        val cardHeight = v.height.toFloat().coerceAtLeast(1f)
                        val absY = Math.abs(currentY)
                        val ratio = absY / cardHeight
                        val isCanceled = (event.actionMasked == MotionEvent.ACTION_CANCEL)

                        // Upward swipe commits to opening lyrics if dragged > 22% or flicked up
                        val isOpenLyrics = !isCanceled && currentY < 0 && (
                            ratio >= 0.22f ||
                            (ratio >= 0.10f && yVel < -300f) ||
                            (absY >= 15f && yVel < -600f)
                        )

                        if (isOpenLyrics) {
                            val targetY = -cardHeight * 1.25f
                            binding.lyricsCard.animate()
                                .scaleX(1f).scaleY(1f).alpha(1f)
                                .setDuration(180L).start()

                            activeAnimator = ValueAnimator.ofFloat(currentY, targetY).apply {
                                duration = 180L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    v.translationY = anim.animatedValue as Float
                                }
                                var isCanc = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanc = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanc) return
                                        v.visibility = View.INVISIBLE
                                        v.translationY = 0f
                                        isLyricsShowing = true
                                        binding.lyricsCard.visibility = View.VISIBLE
                                        binding.lyricsCard.scaleX = 1f
                                        binding.lyricsCard.scaleY = 1f
                                        binding.lyricsCard.alpha = 1f
                                        prepareLyricsForDisplay()
                                        playbackService?.let { service ->
                                            updateLyricsProgress(service.getCurrentPosition().toLong())
                                        }
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        } else {
                            binding.lyricsCard.animate()
                                .scaleX(0.92f).scaleY(0.92f).alpha(0.5f)
                                .setDuration(180L)
                                .withEndAction {
                                    if (!isLyricsShowing) {
                                        binding.lyricsCard.visibility = View.INVISIBLE
                                    }
                                }
                                .start()

                            activeAnimator = ValueAnimator.ofFloat(currentY, 0f).apply {
                                duration = 180L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    v.translationY = anim.animatedValue as Float
                                }
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        v.translationY = 0f
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        }
                        isDraggingY = false
                    } else {
                        if (v.translationX != 0f || v.translationY != 0f) {
                            v.animate().translationX(0f).translationY(0f).rotation(0f).setDuration(150L).start()
                        }
                        if (event.actionMasked == MotionEvent.ACTION_UP) {
                            v.performClick()
                        }
                    }
                    velocityTracker?.recycle()
                    velocityTracker = null
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Initializes the lyrics view components, adapter, and interaction listeners.
     */
    private fun setupLyricsView() {
        lyricAdapter = LyricAdapter { line ->
            playbackService?.seekTo(line.timeMs.toInt())
            binding.playbackSlider.setProgress(line.timeMs)
            binding.currentTimeText.text = Song.formatTime(line.timeMs)
            isUserScrollingLyrics = false
            try {
                binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            } catch (ignored: Exception) {}
        }
        binding.lyricsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.lyricsRecyclerView.adapter = lyricAdapter

        binding.lyricsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isUserScrollingLyrics = true
                    lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE && isUserScrollingLyrics) {
                    lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
                    lyricsScrollResetHandler.postDelayed(resetUserScrollingRunnable, 3500L)
                }
            }
        })

        binding.btnCloseLyrics.setOnClickListener {
            closeLyricsCard()
        }

        binding.lyricsHeaderBar.setOnClickListener {
            closeLyricsCard()
        }

        binding.btnLyricsRetry.setOnClickListener {
            loadLyricsForCurrentSong(forceRefresh = true)
        }
    }

    private fun openLyricsCard() {
        if (isLyricsShowing) return
        val card = binding.albumArtCard
        val cardHeight = card.height.toFloat().coerceAtLeast(1f)

        binding.peekAlbumArtCard.visibility = View.INVISIBLE
        binding.lyricsCard.visibility = View.VISIBLE
        binding.lyricsCard.translationX = 0f
        binding.lyricsCard.translationY = 0f
        binding.lyricsCard.scaleX = 0.94f
        binding.lyricsCard.scaleY = 0.94f
        binding.lyricsCard.alpha = 0.6f

        prepareLyricsForDisplay()

        binding.lyricsCard.animate()
            .scaleX(1f).scaleY(1f).alpha(1f)
            .setDuration(220L).start()

        card.animate()
            .translationY(-cardHeight * 1.25f)
            .setDuration(220L)
            .setInterpolator(DecelerateInterpolator())
            .withEndAction {
                card.visibility = View.INVISIBLE
                card.translationY = 0f
                isLyricsShowing = true
                binding.lyricsCard.scaleX = 1f
                binding.lyricsCard.scaleY = 1f
                binding.lyricsCard.alpha = 1f
                playbackService?.let { service ->
                    updateLyricsProgress(service.getCurrentPosition().toLong())
                }
            }
            .start()
    }

    private fun closeLyricsCard() {
        if (!isLyricsShowing) return
        val lyrics = binding.lyricsCard
        val album = binding.albumArtCard
        val cardHeight = album.height.toFloat().coerceAtLeast(1f)

        album.visibility = View.VISIBLE
        album.translationX = 0f
        album.translationY = -cardHeight * 1.15f
        album.scaleX = 1f
        album.scaleY = 1f
        album.alpha = 1f

        album.animate()
            .translationY(0f)
            .setDuration(200L)
            .setInterpolator(DecelerateInterpolator())
            .start()

        lyrics.animate()
            .scaleX(0.92f).scaleY(0.92f).alpha(0f)
            .setDuration(200L)
            .setInterpolator(DecelerateInterpolator())
            .withEndAction {
                lyrics.visibility = View.INVISIBLE
                lyrics.translationX = 0f
                lyrics.translationY = 0f
                lyrics.scaleX = 1f
                lyrics.scaleY = 1f
                lyrics.alpha = 1f
                isLyricsShowing = false
            }
            .start()
    }

    private fun prepareLyricsForDisplay() {
        val song = playbackService?.currentSong ?: return
        val lyrics = currentLyrics ?: LrcLibClient.getCachedLyrics(this@MainActivity, song)?.also {
            currentLyrics = it
        }

        if (lyrics != null && lyrics.songId == song.id) {
            renderLyricsResult(LyricsResult.Success(lyrics, isOffline = lyrics.isOffline))
            // If currently displayed lyrics are static, force an online search for synchronized lyrics
            if (!lyrics.hasSynced && !lyrics.isInstrumental && lastOnlineLyricsSongId != song.id) {
                loadLyricsForSong(song, forceRefresh = false)
            }
        } else {
            showLyricsLoading()
            loadLyricsForSong(song, forceRefresh = false)
        }
    }

    private fun loadLyricsForCurrentSong(forceRefresh: Boolean = false) {
        val song = playbackService?.currentSong ?: return
        loadLyricsForSong(song, forceRefresh)
    }

    private fun loadLyricsForSong(song: Song, forceRefresh: Boolean = false) {
        lyricsFetchJob?.cancel()

        val isSyncedOrInstrumental = currentLyrics?.let { it.hasSynced || it.isInstrumental } == true
        val alreadyFetchedOnline = (lastOnlineLyricsSongId == song.id)

        // If we already have synced lyrics or already queried online for this song (and not force refreshing), no need to re-query
        if (currentLyrics?.songId == song.id && !forceRefresh && (isSyncedOrInstrumental || alreadyFetchedOnline)) {
            if (isLyricsShowing) {
                renderLyricsResult(LyricsResult.Success(currentLyrics!!, isOffline = currentLyrics!!.isOffline))
            }
            return
        }

        // Only show full loading spinner if we don't have static lyrics already showing, or if user explicitly requested a refresh
        if (isLyricsShowing && (currentLyrics == null || forceRefresh)) {
            showLyricsLoading()
        }

        lastOnlineLyricsSongId = song.id
        lyricsFetchJob = lifecycleScope.launch {
            val result = LrcLibClient.getLyrics(this@MainActivity, song, forceRefresh)
            if (playbackService?.currentSong?.id == song.id) {
                when (result) {
                    is LyricsResult.Success -> {
                        currentLyrics = result.lyrics
                        if (isLyricsShowing) {
                            renderLyricsResult(result)
                        }
                    }
                    is LyricsResult.NotFound, is LyricsResult.Error -> {
                        if (currentLyrics == null || forceRefresh) {
                            currentLyrics = null
                            if (isLyricsShowing) {
                                renderLyricsResult(result)
                            }
                        }
                    }
                    is LyricsResult.Loading -> {}
                }
            }
        }
    }

    private fun showLyricsLoading() {
        if (!::binding.isInitialized) return
        binding.lyricsRecyclerView.visibility = View.GONE
        binding.lyricsPlainScroll.visibility = View.GONE
        binding.lyricsStatusLayout.visibility = View.VISIBLE
        binding.lyricsProgressBar.visibility = View.VISIBLE
        binding.lyricsStatusIcon.visibility = View.GONE
        binding.lyricsStatusText.text = "Loading lyrics..."
        binding.lyricsStatusSubtext.visibility = View.GONE
        binding.btnLyricsRetry.visibility = View.GONE
    }

    private fun renderLyricsResult(result: LyricsResult) {
        if (!::binding.isInitialized) return
        when (result) {
            is LyricsResult.Loading -> {
                showLyricsLoading()
            }
            is LyricsResult.Success -> {
                val lyrics = result.lyrics
                if (lyrics.isInstrumental) {
                    binding.lyricsRecyclerView.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.GONE
                    binding.lyricsStatusLayout.visibility = View.VISIBLE
                    binding.lyricsProgressBar.visibility = View.GONE
                    binding.lyricsStatusIcon.visibility = View.VISIBLE
                    binding.lyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                    binding.lyricsStatusText.text = "Instrumental Track"
                    binding.lyricsStatusSubtext.text = "This track contains no vocal lyrics"
                    binding.lyricsStatusSubtext.visibility = View.VISIBLE
                    binding.btnLyricsRetry.visibility = View.GONE
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                if (lyrics.hasSynced) {
                    binding.lyricsStatusLayout.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.GONE
                    binding.lyricsRecyclerView.visibility = View.VISIBLE
                    lyricAdapter?.submitLines(lyrics.syncedLyrics ?: emptyList())
                    playbackService?.let { service ->
                        updateLyricsProgress(service.getCurrentPosition().toLong())
                    }
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                if (lyrics.hasPlain) {
                    binding.lyricsStatusLayout.visibility = View.GONE
                    binding.lyricsRecyclerView.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.VISIBLE
                    binding.lyricsPlainText.text = lyrics.plainLyrics
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                binding.lyricsStatusText.text = "No lyrics available"
                binding.lyricsStatusSubtext.text = "No lyric lines available for this song"
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Search Again"
            }
            is LyricsResult.NotFound -> {
                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                binding.lyricsStatusText.text = "No lyrics found"
                binding.lyricsStatusSubtext.text = result.message
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Search Again"
            }
            is LyricsResult.Error -> {
                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_info)
                if (result.isRateLimited) {
                    binding.lyricsStatusText.text = "Too many requests"
                } else {
                    binding.lyricsStatusText.text = "Could not load lyrics"
                }
                binding.lyricsStatusSubtext.text = result.message
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Retry"
            }
        }
    }

    private fun updateLyricsSourceBadge(source: String, isOffline: Boolean) {
        val badge = when {
            source.startsWith("EMBEDDED") -> source.removePrefix("EMBEDDED (").removeSuffix(")")
            source == "LOCAL FILE" -> "LOCAL"
            isOffline -> "OFFLINE"
            else -> "LRCLIB"
        }
        binding.lyricsSourceBadge.text = badge
    }

    private fun updateLyricsProgress(currentPositionMs: Long) {
        if (!::binding.isInitialized || !isLyricsShowing) return
        val lyrics = currentLyrics ?: return
        val synced = lyrics.syncedLyrics ?: return
        if (synced.isEmpty()) return

        val activeIndex = synced.indexOfLast { it.timeMs <= currentPositionMs }
        val adapter = lyricAdapter ?: return
        if (activeIndex != adapter.activeIndex) {
            val changed = adapter.setActiveIndex(activeIndex)
            if (changed && activeIndex >= 0 && !isUserScrollingLyrics) {
                val layoutManager = binding.lyricsRecyclerView.layoutManager as? LinearLayoutManager
                if (layoutManager != null && binding.lyricsRecyclerView.height > 0) {
                    val offset = (binding.lyricsRecyclerView.height / 2) - dpToPx(24)
                    layoutManager.scrollToPositionWithOffset(activeIndex, offset)
                }
            }
        }
    }

    private fun dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()

    /**
     * Determines whether the given album art bitmap is perceived as bright or dark,
     * specifically sampling the bottom area where peek title, artist, and badge are displayed.
     */
    private fun isBitmapBright(bitmap: Bitmap?): Boolean {
        if (bitmap == null) return false
        return try {
            val width = bitmap.width
            val height = bitmap.height
            if (width <= 0 || height <= 0) return false

            val startY = (height * 0.5f).toInt().coerceIn(0, height - 1)
            val stepX = (width / 20).coerceAtLeast(1)
            val stepY = ((height - startY) / 10).coerceAtLeast(1)

            var totalLuma = 0.0
            var sampleCount = 0

            for (y in startY until height step stepY) {
                for (x in 0 until width step stepX) {
                    val pixel = bitmap.getPixel(x, y)
                    val r = (pixel shr 16) and 0xFF
                    val g = (pixel shr 8) and 0xFF
                    val b = pixel and 0xFF
                    val luma = 0.299 * r + 0.587 * g + 0.114 * b
                    totalLuma += luma
                    sampleCount++
                }
            }

            if (sampleCount == 0) false else (totalLuma / sampleCount) > 130.0
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Dynamically adjusts peek text color based on album art luminance without any dark gradient overlay.
     * If the album art is bright, uses crisp dark text.
     * If the album art is dark, uses clean white text.
     */
    private fun updatePeekTextContrast(isBright: Boolean) {
        if (isBright) {
            // Bright album art: use dynamic theme colors
            binding.peekLabelText.setTextColor(ThemeColors.getPrimary(this))
            binding.peekLabelText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#80FFFFFF"))

            binding.peekTitleText.setTextColor(ThemeColors.getOnSurface(this))
            binding.peekTitleText.setShadowLayer(4f, 0f, 1f, Color.parseColor("#99FFFFFF"))

            binding.peekArtistText.setTextColor(ThemeColors.getOnSurfaceVariant(this))
            binding.peekArtistText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#99FFFFFF"))
        } else {
            // Dark album art: use white text
            binding.peekLabelText.setTextColor(ThemeColors.getPrimary(this))
            binding.peekLabelText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#99000000"))

            binding.peekTitleText.setTextColor(Color.parseColor("#FFFFFF"))
            binding.peekTitleText.setShadowLayer(4f, 0f, 1f, Color.parseColor("#B3000000"))

            binding.peekArtistText.setTextColor(Color.parseColor("#D1D5DB"))
            binding.peekArtistText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#B3000000"))
        }
    }

    /**
     * Handles double tap in the empty space left and right of the song titles to seek 5 seconds,
     * and horizontal slide/swipe to the right in the titles area to open the left filter library menu.
     * The slide tracks the user's finger in real-time 1:1 without sudden snapping.
     */
    private fun setupTitleDoubleTapGestures() {
        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop
        var startX = 0f
        var startY = 0f
        var isDraggingDrawer = false
        var velocityTracker: VelocityTracker? = null

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean = true

            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (isDraggingDrawer) return false
                val width = binding.titleGestureArea.width.toFloat().coerceAtLeast(1f)
                if (e.x <= width * 0.40f) {
                    seekRelative(-5000L)
                    showTitleSeekBadge(isForward = false)
                } else if (e.x >= width * 0.60f) {
                    seekRelative(5000L)
                    showTitleSeekBadge(isForward = true)
                }
                return true
            }
        })

        binding.titleGestureArea.setOnTouchListener { v, event ->
            if (isFilterMenuOpen) {
                return@setOnTouchListener false
            }

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    filterDrawerAnimator?.cancel()
                    startX = event.rawX
                    startY = event.rawY
                    isDraggingDrawer = false
                    velocityTracker?.recycle()
                    velocityTracker = VelocityTracker.obtain().apply {
                        addMovement(event)
                    }
                    gestureDetector.onTouchEvent(event)
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    velocityTracker?.addMovement(event)
                    val deltaX = event.rawX - startX
                    val deltaY = event.rawY - startY

                    if (!isDraggingDrawer) {
                        // Slide trigger has generous space across titles area
                        val titleAreaWidth = v.width.toFloat().coerceAtLeast(1f)
                        val touchXInView = event.x - deltaX
                        val inTriggerZone = touchXInView <= titleAreaWidth * 0.85f

                        if (inTriggerZone && deltaX > touchSlop && Math.abs(deltaX) > Math.abs(deltaY) * 1.1f) {
                            isDraggingDrawer = true
                            v.parent?.requestDisallowInterceptTouchEvent(true)
                            val drawerWidth = getDrawerWidth()
                            binding.leftDrawerMenu.translationX = -drawerWidth
                            binding.leftDrawerMenu.visibility = View.VISIBLE
                            binding.filterDrawerScrim.visibility = View.VISIBLE
                        } else {
                            gestureDetector.onTouchEvent(event)
                        }
                    }

                    if (isDraggingDrawer) {
                        val drawerWidth = getDrawerWidth()
                        val targetTranslation = (-drawerWidth + deltaX).coerceIn(-drawerWidth, 0f)
                        binding.leftDrawerMenu.translationX = targetTranslation
                        val progress = ((targetTranslation + drawerWidth) / drawerWidth).coerceIn(0f, 1f)
                        binding.filterDrawerScrim.alpha = progress * 0.6f
                    }
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    velocityTracker?.addMovement(event)
                    v.parent?.requestDisallowInterceptTouchEvent(false)

                    if (isDraggingDrawer) {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val xVel = velocityTracker?.xVelocity ?: 0f
                        val drawerWidth = getDrawerWidth()
                        val currentTrans = binding.leftDrawerMenu.translationX
                        val progress = ((currentTrans + drawerWidth) / drawerWidth).coerceIn(0f, 1f)

                        // Trigger open if flung right (>700) or dragged towards center UI (~55%-60% progress)
                        val shouldOpen = when {
                            xVel > 700f -> true
                            xVel < -700f -> false
                            else -> progress >= 0.55f
                        }
                        animateLeftMenu(toOpen = shouldOpen, currentTrans = currentTrans, drawerWidth = drawerWidth)
                        isDraggingDrawer = false
                    } else {
                        gestureDetector.onTouchEvent(event)
                        if (event.actionMasked == MotionEvent.ACTION_UP) {
                            v.performClick()
                        }
                    }
                    velocityTracker?.recycle()
                    velocityTracker = null
                    true
                }
                else -> false
            }
        }

        // Slide to right in the top Omniplay title bar or press to open
        var titleStartX = 0f
        var titleStartY = 0f
        var isTitleDragging = false
        var titleVelocityTracker: VelocityTracker? = null

        binding.appTitleText.setOnTouchListener { v, event ->
            if (isFilterMenuOpen) {
                return@setOnTouchListener false
            }
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    filterDrawerAnimator?.cancel()
                    titleStartX = event.rawX
                    titleStartY = event.rawY
                    isTitleDragging = false
                    titleVelocityTracker?.recycle()
                    titleVelocityTracker = VelocityTracker.obtain().apply {
                        addMovement(event)
                    }
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    titleVelocityTracker?.addMovement(event)
                    val deltaX = event.rawX - titleStartX
                    val deltaY = event.rawY - titleStartY

                    if (!isTitleDragging && deltaX > touchSlop && Math.abs(deltaX) > Math.abs(deltaY) * 1.1f) {
                        isTitleDragging = true
                        v.parent?.requestDisallowInterceptTouchEvent(true)
                        val drawerWidth = getDrawerWidth()
                        binding.leftDrawerMenu.translationX = -drawerWidth
                        binding.leftDrawerMenu.visibility = View.VISIBLE
                        binding.filterDrawerScrim.visibility = View.VISIBLE
                    }

                    if (isTitleDragging) {
                        val drawerWidth = getDrawerWidth()
                        val targetTranslation = (-drawerWidth + deltaX).coerceIn(-drawerWidth, 0f)
                        binding.leftDrawerMenu.translationX = targetTranslation
                        val progress = ((targetTranslation + drawerWidth) / drawerWidth).coerceIn(0f, 1f)
                        binding.filterDrawerScrim.alpha = progress * 0.6f
                    }
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    titleVelocityTracker?.addMovement(event)
                    v.parent?.requestDisallowInterceptTouchEvent(false)

                    if (isTitleDragging) {
                        titleVelocityTracker?.computeCurrentVelocity(1000)
                        val xVel = titleVelocityTracker?.xVelocity ?: 0f
                        val drawerWidth = getDrawerWidth()
                        val currentTrans = binding.leftDrawerMenu.translationX
                        val progress = ((currentTrans + drawerWidth) / drawerWidth).coerceIn(0f, 1f)

                        val shouldOpen = when {
                            xVel > 700f -> true
                            xVel < -700f -> false
                            else -> progress >= 0.55f
                        }
                        animateLeftMenu(toOpen = shouldOpen, currentTrans = currentTrans, drawerWidth = drawerWidth)
                        isTitleDragging = false
                    } else if (event.actionMasked == MotionEvent.ACTION_UP) {
                        openLeftMenu()
                    }
                    titleVelocityTracker?.recycle()
                    titleVelocityTracker = null
                    true
                }
                else -> false
            }
        }
    }

    private fun seekRelative(offsetMs: Long) {
        val service = playbackService ?: return
        if (service.currentSong == null) return
        val current = service.getCurrentPosition().toLong()
        val duration = service.getDuration().toLong()
        val target = if (duration > 0L) {
            (current + offsetMs).coerceIn(0L, duration)
        } else {
            (current + offsetMs).coerceAtLeast(0L)
        }
        service.seekTo(target.toInt())
        binding.playbackSlider.setProgress(target)
        binding.currentTimeText.text = Song.formatTime(target)
        try {
            binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
        } catch (ignored: Exception) {}
    }

    private fun showTitleSeekBadge(isForward: Boolean) {
        val badge = if (isForward) binding.titleSeekBadgeForward else binding.titleSeekBadgeRewind
        badge.animate().cancel()
        badge.alpha = 1f
        badge.visibility = View.VISIBLE
        badge.animate()
            .alpha(0f)
            .setDuration(600L)
            .withEndAction { badge.visibility = View.GONE }
            .start()
    }

    private fun setupListeners() {
        // OmniSync button (left of gear button) -> open OmniSync bottom sheet
        updateOmniSyncButtonState(xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole)
        binding.btnOmnisync.setOnClickListener {
            xyz.omniplay.sync.OmniSyncBottomSheet.newInstance()
                .show(supportFragmentManager, xyz.omniplay.sync.OmniSyncBottomSheet.TAG)
        }

        // Settings button (Gear) -> open settings bottom sheet
        binding.btnMenu.setOnClickListener {
            openSettingsMenu()
        }

        // Omniplay Title -> press to open left side menu
        binding.appTitleText.setOnClickListener {
            openLeftMenu()
        }

        // Material You Audio Info Badges -> tap to inspect track and audio details
        val badgeClickListener = View.OnClickListener {
            showTrackDetailsDialog()
        }
        binding.audioBadgeContainer.setOnClickListener(badgeClickListener)
        binding.badgeFormat.setOnClickListener(badgeClickListener)
        binding.badgeQuality.setOnClickListener(badgeClickListener)
        binding.badgeHires.setOnClickListener(badgeClickListener)
        binding.badgeBitPerfect.setOnClickListener(badgeClickListener)

        // Play / Pause Circular Card
        binding.btnPlayPauseCard.setOnClickListener {
            if (xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                xyz.omniplay.sync.OmniSyncManager.getInstance(this).toggleListenerPlayback()
                return@setOnClickListener
            }
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
            if (xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                return@setOnClickListener
            }
            if (isBound) {
                playbackService?.skipNext(forceNext = true)
            }
        }

        // Previous
        binding.btnPrevious.setOnClickListener {
            if (xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                return@setOnClickListener
            }
            if (isBound) {
                playbackService?.skipPrevious(forcePrevious = false)
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

        // Android 13/14 Squiggly Progress Line Seek Bar
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
                if (isCancelled) {
                    val currentPos = playbackService?.getCurrentPosition()?.toLong() ?: progressMs
                    binding.playbackSlider.setProgress(currentPos)
                    binding.currentTimeText.text = Song.formatTime(currentPos)
                } else {
                    playbackService?.seekTo(progressMs.toInt())
                }
            }
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
     * Checks if user already selected music directories. If not (first run), launches folder picker.
     */
    private fun checkFolderOrScan() {
        val folders = MusicFolderManager.getFolders(this)
        if (folders.isNotEmpty()) {
            loadMusicFromConfiguredFolders()
        } else {
            Toast.makeText(this, "Select your music folder to scan songs", Toast.LENGTH_LONG).show()
            openFolderPicker()
        }
    }

    private fun openFolderPicker() {
        try {
            folderPickerLauncher.launch(null)
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to open folder picker: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMusicFromConfiguredFolders(isUserInitiated: Boolean = false, isRescan: Boolean = false) {
        val folderUris = MusicFolderManager.getFolders(this).mapNotNull {
            try { Uri.parse(it) } catch (e: Exception) { null }
        }
        if (folderUris.isEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                musicDatabase.clearAll()
            }
            updateSongList(emptyList())
            return
        }
        lifecycleScope.launch {
            try {
                // If this is NOT an explicit rescan and we already have cached songs in the database,
                // load and display them immediately without rescanning the disk!
                if (!isRescan) {
                    val cachedSongs = withContext(Dispatchers.IO) {
                        musicDatabase.getAllSongs()
                    }
                    if (cachedSongs.isNotEmpty()) {
                        updateSongList(cachedSongs)
                        return@launch
                    }
                }

                if (isUserInitiated) {
                    Toast.makeText(
                        this@MainActivity,
                        if (isRescan) "Rescanning music..." else "Scanning music...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                val allSongs = mutableListOf<Song>()
                for (uri in folderUris) {
                    try {
                        val folderSongs = musicScanner.scanFolder(uri)
                        allSongs.addAll(folderSongs)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                val distinctSongs = allSongs.distinctBy {
                    it.filePath.ifEmpty { it.contentUri.toString() }
                }

                withContext(Dispatchers.IO) {
                    musicDatabase.replaceAllSongs(distinctSongs)
                }

                updateSongList(distinctSongs)

                if (isUserInitiated) {
                    val message = if (distinctSongs.isNotEmpty()) {
                        "Scan complete: ${distinctSongs.size} songs found"
                    } else {
                        "No songs found in selected folders"
                    }
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                updateSongList(emptyList())
                Toast.makeText(this@MainActivity, "Error scanning folders: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadMusicFromFolder(treeUri: Uri, isUserInitiated: Boolean = false, isRescan: Boolean = false) {
        MusicFolderManager.addFolder(this, treeUri)
        loadMusicFromConfiguredFolders(isUserInitiated, isRescan = true)
    }

    private fun rescanMusic() {
        val folders = MusicFolderManager.getFolders(this)
        if (folders.isNotEmpty()) {
            loadMusicFromConfiguredFolders(isUserInitiated = true, isRescan = true)
        } else {
            Toast.makeText(this, "Select a music folder to scan", Toast.LENGTH_SHORT).show()
            openFolderPicker()
        }
    }

    private fun getDrawerWidth(): Float {
        return binding.leftDrawerMenu.width.toFloat().takeIf { it > 0f }
            ?: (320f * resources.displayMetrics.density)
    }

    private fun updateAppTitle() {
        val isBottomSheetOpen = ::bottomSheetBehavior.isInitialized &&
                bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED
        if (isFilterMenuOpen || isFilterPreviewActive || isBottomSheetOpen) {
            binding.appTitleText.text = getString(R.string.music_library)
        } else {
            binding.appTitleText.text = getString(R.string.app_name)
        }
    }

    private fun openLeftMenu() {
        if (isFilterMenuOpen) return
        binding.appTitleText.text = getString(R.string.music_library)
        val drawer = binding.leftDrawerMenu
        val scrim = binding.filterDrawerScrim
        drawer.visibility = View.VISIBLE
        scrim.visibility = View.VISIBLE
        val drawerWidth = getDrawerWidth()
        val currentTrans = drawer.translationX.coerceAtMost(0f)
        animateLeftMenu(toOpen = true, currentTrans = currentTrans, drawerWidth = drawerWidth)
        try {
            binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
        } catch (ignored: Exception) {}
    }

    private fun closeLeftMenu() {
        if (!isFilterMenuOpen && binding.leftDrawerMenu.visibility != View.VISIBLE) return
        val drawerWidth = getDrawerWidth()
        animateLeftMenu(toOpen = false, currentTrans = binding.leftDrawerMenu.translationX, drawerWidth = drawerWidth)
    }

    private fun animateLeftMenu(toOpen: Boolean, currentTrans: Float, drawerWidth: Float) {
        filterDrawerAnimator?.cancel()
        val targetX = if (toOpen) 0f else -drawerWidth
        val progress = ((currentTrans + drawerWidth) / drawerWidth).coerceIn(0f, 1f)
        val remaining = if (toOpen) (1f - progress) else progress
        val duration = (260L * remaining).toLong().coerceIn(120L, 280L)

        if (toOpen) {
            binding.appTitleText.text = getString(R.string.music_library)
        }

        binding.leftDrawerMenu.visibility = View.VISIBLE
        binding.filterDrawerScrim.visibility = View.VISIBLE

        filterDrawerAnimator = ValueAnimator.ofFloat(currentTrans, targetX).apply {
            this.duration = duration
            interpolator = DecelerateInterpolator()
            addUpdateListener { anim ->
                val v = anim.animatedValue as Float
                binding.leftDrawerMenu.translationX = v
                val p = ((v + drawerWidth) / drawerWidth).coerceIn(0f, 1f)
                binding.filterDrawerScrim.alpha = p * 0.6f
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (toOpen) {
                        binding.leftDrawerMenu.translationX = 0f
                        binding.filterDrawerScrim.alpha = 0.6f
                        isFilterMenuOpen = true
                        updateAppTitle()
                    } else {
                        binding.leftDrawerMenu.translationX = -drawerWidth
                        binding.leftDrawerMenu.visibility = View.GONE
                        binding.filterDrawerScrim.alpha = 0f
                        binding.filterDrawerScrim.visibility = View.GONE
                        isFilterMenuOpen = false
                        updateAppTitle()
                    }
                    filterDrawerAnimator = null
                }
            })
            start()
        }
    }

    private fun setupLeftDrawerMenu() {
        binding.leftDrawerMenu.post {
            val drawerWidth = getDrawerWidth()
            binding.leftDrawerMenu.translationX = -drawerWidth
            binding.leftDrawerMenu.visibility = View.GONE
            binding.filterDrawerScrim.alpha = 0f
            binding.filterDrawerScrim.visibility = View.GONE
        }

        binding.btnCloseDrawer.setOnClickListener {
            closeLeftMenu()
        }

        binding.filterDrawerScrim.setOnClickListener {
            closeLeftMenu()
        }

        // Allow dragging drawer back closed
        var closeStartX = 0f
        var closeStartY = 0f
        var isDraggingClose = false
        var closeVelocityTracker: VelocityTracker? = null
        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop

        binding.leftDrawerMenu.setOnTouchListener { _, event ->
            if (!isFilterMenuOpen) return@setOnTouchListener false

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    filterDrawerAnimator?.cancel()
                    closeStartX = event.rawX
                    closeStartY = event.rawY
                    isDraggingClose = false
                    closeVelocityTracker?.recycle()
                    closeVelocityTracker = VelocityTracker.obtain().apply {
                        addMovement(event)
                    }
                    false
                }
                MotionEvent.ACTION_MOVE -> {
                    closeVelocityTracker?.addMovement(event)
                    val deltaX = event.rawX - closeStartX
                    val deltaY = event.rawY - closeStartY

                    if (!isDraggingClose && deltaX < -touchSlop && Math.abs(deltaX) > Math.abs(deltaY) * 1.1f) {
                        isDraggingClose = true
                        binding.leftDrawerMenu.parent?.requestDisallowInterceptTouchEvent(true)
                    }

                    if (isDraggingClose) {
                        val drawerWidth = getDrawerWidth()
                        val targetTranslation = deltaX.coerceIn(-drawerWidth, 0f)
                        binding.leftDrawerMenu.translationX = targetTranslation
                        val progress = ((targetTranslation + drawerWidth) / drawerWidth).coerceIn(0f, 1f)
                        binding.filterDrawerScrim.alpha = progress * 0.6f
                        true
                    } else {
                        false
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    closeVelocityTracker?.addMovement(event)
                    binding.leftDrawerMenu.parent?.requestDisallowInterceptTouchEvent(false)

                    if (isDraggingClose) {
                        closeVelocityTracker?.computeCurrentVelocity(1000)
                        val xVel = closeVelocityTracker?.xVelocity ?: 0f
                        val drawerWidth = getDrawerWidth()
                        val progress = ((binding.leftDrawerMenu.translationX + drawerWidth) / drawerWidth).coerceIn(0f, 1f)

                        val shouldStayOpen = when {
                            xVel < -700f -> false
                            xVel > 700f -> true
                            else -> progress >= 0.55f
                        }
                        animateLeftMenu(toOpen = shouldStayOpen, currentTrans = binding.leftDrawerMenu.translationX, drawerWidth = drawerWidth)
                        isDraggingClose = false
                        closeVelocityTracker?.recycle()
                        closeVelocityTracker = null
                        true
                    } else {
                        closeVelocityTracker?.recycle()
                        closeVelocityTracker = null
                        false
                    }
                }
                else -> false
            }
        }

        drawerFilterAdapter = DrawerFilterAdapter { item ->
            onFilterItemSelected(item)
        }
        binding.drawerFilterRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.drawerFilterRecyclerView.adapter = drawerFilterAdapter

        binding.cardAllTracks.setOnClickListener {
            isAllTracksDrillDown = true
            currentArtistDrillDown = null
            currentAlbumDrillDown = null
            currentFolderNavigationPath.clear()
            updateFilterSubList()
        }

        binding.filterToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            isAllTracksDrillDown = false
            currentArtistDrillDown = null
            currentAlbumDrillDown = null
            currentFolderNavigationPath.clear()
            when (checkedId) {
                R.id.btn_filter_artists -> setFilterMode(LibraryFilterMode.ARTIST)
                R.id.btn_filter_albums -> setFilterMode(LibraryFilterMode.ALBUM)
                R.id.btn_filter_folders -> setFilterMode(LibraryFilterMode.FOLDER)
            }
        }

        binding.btnFilterArtists.setOnClickListener {
            if (isAllTracksDrillDown || currentArtistDrillDown != null || currentAlbumDrillDown != null || currentFolderNavigationPath.isNotEmpty()) {
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                setFilterMode(LibraryFilterMode.ARTIST)
            }
        }

        binding.btnFilterAlbums.setOnClickListener {
            if (isAllTracksDrillDown || currentArtistDrillDown != null || currentAlbumDrillDown != null || currentFolderNavigationPath.isNotEmpty()) {
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                setFilterMode(LibraryFilterMode.ALBUM)
            }
        }

        binding.btnFilterFolders.setOnClickListener {
            if (isAllTracksDrillDown || currentArtistDrillDown != null || currentAlbumDrillDown != null || currentFolderNavigationPath.isNotEmpty()) {
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                setFilterMode(LibraryFilterMode.FOLDER)
            }
        }

        binding.btnClearFilter.setOnClickListener {
            isAllTracksDrillDown = false
            currentArtistDrillDown = null
            currentAlbumDrillDown = null
            currentFolderNavigationPath.clear()
            selectedFilterValue = null
            currentFilterMode = LibraryFilterMode.TRACK
            if (activeOngoingQueueTitle != null || activeOngoingFilterMode != LibraryFilterMode.TRACK) {
                isFilterPreviewActive = true
            } else {
                isFilterPreviewActive = false
            }
            updateAppTitle()
            binding.activeFilterBar.visibility = View.GONE
            val subMode = when (binding.filterToggleGroup.checkedButtonId) {
                R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
                R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
                else -> LibraryFilterMode.ARTIST
            }
            val count = when (subMode) {
                LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { getString(R.string.unknown_album) } }.distinct().size
                LibraryFilterMode.FOLDER -> getRootFolderCount()
                else -> allScannedSongs.map { it.artist.trim().ifEmpty { getString(R.string.unknown_artist) } }.distinct().size
            }
            binding.drawerFilterInfoText.text = when (subMode) {
                LibraryFilterMode.ALBUM -> "Select from $count albums"
                LibraryFilterMode.FOLDER -> "Select from $count folders"
                else -> "Select from $count artists"
            }
            updateFilterSubList()
            applyCurrentFilter()
        }
    }

    private fun getSongFolderSegments(song: Song): List<String> {
        val filePath = song.filePath.trim()
        if (filePath.isNotEmpty() && (filePath.contains('/') || filePath.contains(File.separator))) {
            val pClean = filePath
                .replace(Regex("^/storage/emulated/[0-9]+/"), "")
                .replace(Regex("^/storage/[^/]+/"), "")
                .replace(Regex("^/(sdcard|mnt/sdcard)/"), "")
                .trimStart('/')
            if (pClean.contains('/')) {
                val dirPart = pClean.substringBeforeLast('/')
                val segs = dirPart.split('/').filter { it.isNotBlank() && it != "0" && it != "emulated" }
                if (segs.isNotEmpty()) return segs
            } else if (pClean.isNotBlank() && !pClean.contains('.')) {
                return listOf(pClean)
            }
        }

        val uriStr = song.contentUri.toString()
        val decoded = try { Uri.decode(uriStr) } catch (_: Exception) { uriStr }
        if (decoded.contains("documents") && decoded.contains(':')) {
            val lastColonPart = decoded.substringAfterLast(':').trimStart('/')
            if (lastColonPart.contains('/')) {
                val dirPart = lastColonPart.substringBeforeLast('/')
                val segs = dirPart.split('/').filter {
                    it.isNotBlank() && it != "document" && it != "tree" && it != "primary" && it != "raw"
                }
                if (segs.isNotEmpty()) return segs
            }
        }

        if (song.folderName.isNotBlank()) {
            val segs = song.folderName.trim().trim('/').split('/').filter { it.isNotBlank() }
            if (segs.isNotEmpty()) return segs
        }

        val fallback = song.getResolvedFolderName()
        return if (fallback.isNotBlank()) listOf(fallback) else listOf("Music")
    }

    private fun getCommonFolderPrefixDepth(allSegments: List<List<String>>): Int {
        if (allSegments.isEmpty()) return 0
        val genericRoots = setOf("music", "audio", "audios", "sound", "sounds")
        val first = allSegments[0]
        var depth = 0
        while (depth < first.size) {
            val seg = first[depth].lowercase(Locale.ROOT)
            if (seg in genericRoots && allSegments.all { it.size > depth + 1 && it[depth].equals(seg, ignoreCase = true) }) {
                depth++
            } else {
                break
            }
        }
        return depth
    }

    private fun getRootFolderCount(): Int {
        val allSegs = allScannedSongs.map { getSongFolderSegments(it) }
        val depth = getCommonFolderPrefixDepth(allSegs)
        return allSegs.map { segs ->
            if (depth > 0 && segs.size > depth) segs[depth] else segs.firstOrNull() ?: "Music"
        }.distinct().size
    }

    private fun getSongsInCurrentFolder(): List<Song> {
        val allSongSegs = allScannedSongs.map { getSongFolderSegments(it) }
        val depth = getCommonFolderPrefixDepth(allSongSegs)
        val trimmedSongs = allScannedSongs.mapIndexed { idx, song ->
            val segs = allSongSegs[idx]
            Pair(song, if (depth > 0 && segs.size > depth) segs.subList(depth, segs.size) else segs)
        }
        val currentPath = currentFolderNavigationPath.toList()
        val matchingSongs = trimmedSongs.filter { (_, segs) ->
            segs.size >= currentPath.size &&
                    segs.subList(0, currentPath.size).map { it.lowercase(Locale.ROOT) } == currentPath.map { it.lowercase(Locale.ROOT) }
        }.map { it.first }

        return sortSongs(matchingSongs, currentSortField, isSortAscending)
    }

    private fun setFilterMode(mode: LibraryFilterMode) {
        isAllTracksDrillDown = false
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        currentFolderNavigationPath.clear()
        currentFilterMode = mode
        binding.drawerFilterRecyclerView.visibility = View.VISIBLE
        updateFilterSubList()
        val matchesCurrentFilter = (activeOngoingFilterMode == mode && !selectedFilterValue.isNullOrEmpty())
        when (mode) {
            LibraryFilterMode.ARTIST -> {
                if (matchesCurrentFilter) {
                    binding.activeFilterBar.visibility = View.VISIBLE
                    binding.activeFilterText.text = "Artist: $selectedFilterValue"
                    binding.drawerFilterInfoText.text = "Filtered by artist: $selectedFilterValue"
                } else {
                    binding.activeFilterBar.visibility = View.GONE
                    val count = allScannedSongs.map { it.artist.trim().ifEmpty { getString(R.string.unknown_artist) } }.distinct().size
                    binding.drawerFilterInfoText.text = "Select from $count artists"
                    if (activeOngoingFilterMode == LibraryFilterMode.TRACK) {
                        applyCurrentFilter()
                    }
                }
            }
            LibraryFilterMode.ALBUM -> {
                if (matchesCurrentFilter) {
                    binding.activeFilterBar.visibility = View.VISIBLE
                    binding.activeFilterText.text = "Album: $selectedFilterValue"
                    binding.drawerFilterInfoText.text = "Filtered by album: $selectedFilterValue"
                } else {
                    binding.activeFilterBar.visibility = View.GONE
                    val count = allScannedSongs.map { it.album.trim().ifEmpty { getString(R.string.unknown_album) } }.distinct().size
                    binding.drawerFilterInfoText.text = "Select from $count albums"
                    if (activeOngoingFilterMode == LibraryFilterMode.TRACK) {
                        applyCurrentFilter()
                    }
                }
            }
            LibraryFilterMode.FOLDER -> {
                if (matchesCurrentFilter) {
                    binding.activeFilterBar.visibility = View.VISIBLE
                    binding.activeFilterText.text = "Folder: $selectedFilterValue"
                    binding.drawerFilterInfoText.text = "Filtered by folder: $selectedFilterValue"
                } else {
                    binding.activeFilterBar.visibility = View.GONE
                    val count = getRootFolderCount()
                    binding.drawerFilterInfoText.text = "Select from $count folders"
                    if (activeOngoingFilterMode == LibraryFilterMode.TRACK) {
                        applyCurrentFilter()
                    }
                }
            }
            LibraryFilterMode.TRACK -> {
                binding.activeFilterBar.visibility = View.GONE
                val subMode = when (binding.filterToggleGroup.checkedButtonId) {
                    R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
                    R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
                    else -> LibraryFilterMode.ARTIST
                }
                val count = when (subMode) {
                    LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { getString(R.string.unknown_album) } }.distinct().size
                    LibraryFilterMode.FOLDER -> getRootFolderCount()
                    else -> allScannedSongs.map { it.artist.trim().ifEmpty { getString(R.string.unknown_artist) } }.distinct().size
                }
                binding.drawerFilterInfoText.text = when (subMode) {
                    LibraryFilterMode.ALBUM -> "Select from $count albums"
                    LibraryFilterMode.FOLDER -> "Select from $count folders"
                    else -> "Select from $count artists"
                }
                applyCurrentFilter()
            }
        }
    }

    private fun updateFilterSubList() {
        if (allScannedSongs.isEmpty()) {
            drawerFilterAdapter?.setItems(emptyList())
            return
        }

        val unknownAlbumStr = getString(R.string.unknown_album)
        val unknownArtistStr = getString(R.string.unknown_artist)

        if (isAllTracksDrillDown) {
            val allSongsSorted = sortSongs(allScannedSongs, currentSortField, isSortAscending)
            val items = mutableListOf<FilterItem>()
            items.add(
                FilterItem(
                    title = "..",
                    count = 0,
                    isAlbum = false,
                    isFolder = false,
                    isBack = true,
                    subtitle = "Back to Filters"
                )
            )
            items.addAll(
                allSongsSorted.map { song ->
                    FilterItem(
                        title = song.title,
                        count = 0,
                        isAlbum = false,
                        isFolder = false,
                        song = song,
                        representativeSong = song,
                        subtitle = song.artist.trim().ifEmpty { unknownArtistStr },
                        isSelected = (playbackService?.currentSong?.id == song.id)
                    )
                }
            )
            binding.drawerFilterInfoText.text = "All Tracks (${allScannedSongs.size} songs)"
            drawerFilterAdapter?.setItems(items)
            binding.drawerFilterRecyclerView.scrollToPosition(0)
            return
        }

        val artistTarget = currentArtistDrillDown
        if (artistTarget != null) {
            val artistSongs = allScannedSongs.filter {
                it.artist.trim().ifEmpty { unknownArtistStr }.equals(artistTarget, ignoreCase = true)
            }
            val sortedArtistSongs = sortSongs(artistSongs, currentSortField, isSortAscending)
            val items = mutableListOf<FilterItem>()
            items.add(
                FilterItem(
                    title = "..",
                    count = 0,
                    isAlbum = false,
                    isFolder = false,
                    isBack = true,
                    subtitle = "All Artists"
                )
            )
            items.addAll(
                sortedArtistSongs.map { song ->
                    FilterItem(
                        title = song.title,
                        count = 0,
                        isAlbum = false,
                        isFolder = false,
                        song = song,
                        representativeSong = song,
                        subtitle = song.album.trim().ifEmpty { unknownAlbumStr },
                        isSelected = (playbackService?.currentSong?.id == song.id)
                    )
                }
            )
            binding.drawerFilterInfoText.text = "Artist: $artistTarget (${artistSongs.size} songs)"
            drawerFilterAdapter?.setItems(items)
            binding.drawerFilterRecyclerView.scrollToPosition(0)
            return
        }

        val albumTarget = currentAlbumDrillDown
        if (albumTarget != null) {
            val albumSongs = allScannedSongs.filter {
                it.album.trim().ifEmpty { unknownAlbumStr }.equals(albumTarget, ignoreCase = true)
            }
            val sortedAlbumSongs = sortSongs(albumSongs, currentSortField, isSortAscending)
            val items = mutableListOf<FilterItem>()
            items.add(
                FilterItem(
                    title = "..",
                    count = 0,
                    isAlbum = false,
                    isFolder = false,
                    isBack = true,
                    subtitle = "All Albums"
                )
            )
            items.addAll(
                sortedAlbumSongs.map { song ->
                    FilterItem(
                        title = song.title,
                        count = 0,
                        isAlbum = false,
                        isFolder = false,
                        song = song,
                        representativeSong = song,
                        subtitle = song.artist.trim().ifEmpty { unknownArtistStr },
                        isSelected = (playbackService?.currentSong?.id == song.id)
                    )
                }
            )
            binding.drawerFilterInfoText.text = "Album: $albumTarget (${albumSongs.size} songs)"
            drawerFilterAdapter?.setItems(items)
            binding.drawerFilterRecyclerView.scrollToPosition(0)
            return
        }

        val browseMode = when (binding.filterToggleGroup.checkedButtonId) {
            R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
            R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
            else -> LibraryFilterMode.ARTIST
        }

        val items = when (browseMode) {
            LibraryFilterMode.ARTIST -> {
                val artistGroups = allScannedSongs
                    .groupBy { it.artist.trim().ifEmpty { unknownArtistStr } }
                    .map { (artist, songs) ->
                        FilterItem(
                            title = artist,
                            count = songs.size,
                            isAlbum = false,
                            isSelected = (currentFilterMode == LibraryFilterMode.ARTIST) && artist.equals(selectedFilterValue, ignoreCase = true),
                            representativeSong = songs.firstOrNull()
                        )
                    }
                    .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })
                if (activeOngoingFilterMode == LibraryFilterMode.ARTIST && !selectedFilterValue.isNullOrEmpty()) {
                    binding.drawerFilterInfoText.text = "Filtered by artist: $selectedFilterValue"
                } else {
                    binding.drawerFilterInfoText.text = "Select from ${artistGroups.size} artists"
                }
                artistGroups
            }
            LibraryFilterMode.ALBUM -> {
                val albumGroups = allScannedSongs
                    .groupBy { it.album.trim().ifEmpty { unknownAlbumStr } }
                    .map { (album, songs) ->
                        FilterItem(
                            title = album,
                            count = songs.size,
                            isAlbum = true,
                            isSelected = (currentFilterMode == LibraryFilterMode.ALBUM) && album.equals(selectedFilterValue, ignoreCase = true),
                            representativeSong = songs.firstOrNull()
                        )
                    }
                    .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })
                if (activeOngoingFilterMode == LibraryFilterMode.ALBUM && !selectedFilterValue.isNullOrEmpty()) {
                    binding.drawerFilterInfoText.text = "Filtered by album: $selectedFilterValue"
                } else {
                    binding.drawerFilterInfoText.text = "Select from ${albumGroups.size} albums"
                }
                albumGroups
            }
            LibraryFilterMode.FOLDER -> {
                val allSongSegs = allScannedSongs.map { getSongFolderSegments(it) }
                val depth = getCommonFolderPrefixDepth(allSongSegs)
                val trimmedSongs = allScannedSongs.mapIndexed { idx, song ->
                    val segs = allSongSegs[idx]
                    Pair(song, if (depth > 0 && segs.size > depth) segs.subList(depth, segs.size) else segs)
                }

                val currentPath = currentFolderNavigationPath.toList()
                val itemsList = mutableListOf<FilterItem>()

                if (currentPath.isNotEmpty()) {
                    val parentName = if (currentPath.size > 1) currentPath[currentPath.size - 2] else "All Folders"
                    itemsList.add(
                        FilterItem(
                            title = "..",
                            count = 0,
                            isAlbum = false,
                            isFolder = false,
                            isBack = true,
                            subtitle = parentName
                        )
                    )
                }

                val matching = trimmedSongs.filter { (_, segs) ->
                    segs.size >= currentPath.size &&
                            segs.subList(0, currentPath.size).map { it.lowercase(Locale.ROOT) } == currentPath.map { it.lowercase(Locale.ROOT) }
                }

                val subfolders = matching
                    .filter { (_, segs) -> segs.size > currentPath.size }
                    .groupBy { (_, segs) -> segs[currentPath.size] }

                val subfolderItems = subfolders.map { (subName, pairs) ->
                    FilterItem(
                        title = subName,
                        count = pairs.size,
                        isAlbum = false,
                        isFolder = true,
                        representativeSong = pairs.firstOrNull()?.first
                    )
                }.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })

                itemsList.addAll(subfolderItems)

                val directSongs = matching
                    .filter { (_, segs) -> segs.size == currentPath.size }
                    .map { (song, _) ->
                        FilterItem(
                            title = song.title,
                            count = 0,
                            isAlbum = false,
                            isFolder = false,
                            song = song,
                            representativeSong = song,
                            subtitle = song.artist.trim().ifEmpty { unknownArtistStr },
                            isSelected = (playbackService?.currentSong?.id == song.id)
                        )
                    }
                    .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })

                itemsList.addAll(directSongs)

                if (currentPath.isNotEmpty()) {
                    val pathDisplay = currentPath.joinToString(" / ")
                    binding.drawerFilterInfoText.text = "$pathDisplay (${matching.size} songs)"
                } else {
                    val count = subfolderItems.size + directSongs.size
                    binding.drawerFilterInfoText.text = "Select from $count folders"
                }

                itemsList
            }
            LibraryFilterMode.TRACK -> emptyList()
        }
        drawerFilterAdapter?.setItems(items)
    }

    private fun onFilterItemSelected(item: FilterItem) {
        if (item.isBack) {
            when {
                isAllTracksDrillDown -> {
                    isAllTracksDrillDown = false
                    updateFilterSubList()
                }
                currentArtistDrillDown != null -> {
                    currentArtistDrillDown = null
                    updateFilterSubList()
                }
                currentAlbumDrillDown != null -> {
                    currentAlbumDrillDown = null
                    updateFilterSubList()
                }
                currentFolderNavigationPath.isNotEmpty() -> {
                    currentFolderNavigationPath.removeAt(currentFolderNavigationPath.lastIndex)
                    updateFilterSubList()
                }
            }
            return
        }

        if (item.isFolder) {
            currentFolderNavigationPath.add(item.title)
            updateFilterSubList()
            return
        }

        if (item.isAlbum) {
            currentAlbumDrillDown = item.title
            currentArtistDrillDown = null
            isAllTracksDrillDown = false
            updateFilterSubList()
            return
        }

        if (item.song == null) {
            currentArtistDrillDown = item.title
            currentAlbumDrillDown = null
            isAllTracksDrillDown = false
            updateFilterSubList()
            return
        }

        val song = item.song
        if (xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
            Toast.makeText(this, "OmniSync is active: Disconnect from OmniSync to play local media", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.searchBarContainer.visibility == View.VISIBLE) {
            closeSearchBar(clearQuery = true)
        }

        val queueToSet: List<Song>
        val filterModeToSet: LibraryFilterMode
        val filterValueToSet: String?

        if (isAllTracksDrillDown) {
            val allSorted = sortSongs(allScannedSongs, currentSortField, isSortAscending)
            queueToSet = if (allSorted.isNotEmpty()) allSorted else listOf(song)
            filterModeToSet = LibraryFilterMode.TRACK
            filterValueToSet = null
        } else if (currentArtistDrillDown != null) {
            val unknownArtistStr = getString(R.string.unknown_artist)
            val artistSongs = allScannedSongs.filter {
                it.artist.trim().ifEmpty { unknownArtistStr }.equals(currentArtistDrillDown, ignoreCase = true)
            }
            val sorted = sortSongs(artistSongs, currentSortField, isSortAscending)
            queueToSet = if (sorted.isNotEmpty()) sorted else listOf(song)
            filterModeToSet = LibraryFilterMode.ARTIST
            filterValueToSet = currentArtistDrillDown
        } else if (currentAlbumDrillDown != null) {
            val unknownAlbumStr = getString(R.string.unknown_album)
            val albumSongs = allScannedSongs.filter {
                it.album.trim().ifEmpty { unknownAlbumStr }.equals(currentAlbumDrillDown, ignoreCase = true)
            }
            val sorted = sortSongs(albumSongs, currentSortField, isSortAscending)
            queueToSet = if (sorted.isNotEmpty()) sorted else listOf(song)
            filterModeToSet = LibraryFilterMode.ALBUM
            filterValueToSet = currentAlbumDrillDown
        } else {
            val folderSongs = getSongsInCurrentFolder()
            queueToSet = if (folderSongs.isNotEmpty()) folderSongs else listOf(song)
            filterModeToSet = LibraryFilterMode.FOLDER
            filterValueToSet = if (currentFolderNavigationPath.isNotEmpty()) {
                currentFolderNavigationPath.joinToString("/")
            } else {
                item.title
            }
        }

        val targetIdx = queueToSet.indexOfFirst { it.id == song.id }.coerceAtLeast(0)

        isFilterPreviewActive = false
        currentFilterMode = filterModeToSet
        selectedFilterValue = filterValueToSet
        activeOngoingQueueTitle = selectedFilterValue
        activeOngoingFilterMode = filterModeToSet
        activeOngoingQueue = queueToSet

        displayedSongs = queueToSet
        songAdapter?.setSongs(queueToSet)
        songAdapter?.setCurrentPlayingSongId(song.id)

        playbackService?.let { service ->
            val queueMatches = service.queue.size == queueToSet.size &&
                    service.queue.any { it.id == song.id }
            if (queueMatches) {
                service.playSongFromPlaylist(song, startPlaying = true)
            } else {
                service.setSongQueue(queueToSet, startIndex = targetIdx, startPlaying = true)
            }
        }

        updateAppTitle()
        if (filterValueToSet != null) {
            binding.activeFilterBar.visibility = View.VISIBLE
            val prefix = when (filterModeToSet) {
                LibraryFilterMode.ALBUM -> "Album: "
                LibraryFilterMode.FOLDER -> "Folder: "
                else -> "Artist: "
            }
            val typeStr = when (filterModeToSet) {
                LibraryFilterMode.ALBUM -> "album"
                LibraryFilterMode.FOLDER -> "folder"
                else -> "artist"
            }
            binding.activeFilterText.text = "$prefix$selectedFilterValue"
            binding.drawerFilterInfoText.text = "Filtered by $typeStr: $selectedFilterValue"
        } else {
            binding.activeFilterBar.visibility = View.GONE
        }

        closeLeftMenu()
        if (::bottomSheetBehavior.isInitialized) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun revertToOngoingQueue() {
        isFilterPreviewActive = false
        updateAppTitle()
        isAllTracksDrillDown = false
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        selectedFilterValue = activeOngoingQueueTitle
        currentFilterMode = activeOngoingFilterMode
        currentFolderNavigationPath.clear()
        if (selectedFilterValue != null) {
            binding.activeFilterBar.visibility = View.VISIBLE
            val prefix = when (activeOngoingFilterMode) {
                LibraryFilterMode.ALBUM -> "Album: "
                LibraryFilterMode.FOLDER -> "Folder: "
                else -> "Artist: "
            }
            val typeStr = when (activeOngoingFilterMode) {
                LibraryFilterMode.ALBUM -> "album"
                LibraryFilterMode.FOLDER -> "folder"
                else -> "artist"
            }
            binding.activeFilterText.text = "$prefix$selectedFilterValue"
            binding.drawerFilterInfoText.text = "Filtered by $typeStr: $selectedFilterValue"
            when (activeOngoingFilterMode) {
                LibraryFilterMode.ALBUM -> binding.filterToggleGroup.check(R.id.btn_filter_albums)
                LibraryFilterMode.FOLDER -> binding.filterToggleGroup.check(R.id.btn_filter_folders)
                else -> binding.filterToggleGroup.check(R.id.btn_filter_artists)
            }
        } else {
            binding.activeFilterBar.visibility = View.GONE
            val subMode = when (binding.filterToggleGroup.checkedButtonId) {
                R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
                R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
                else -> LibraryFilterMode.ARTIST
            }
            val count = when (subMode) {
                LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { getString(R.string.unknown_album) } }.distinct().size
                LibraryFilterMode.FOLDER -> getRootFolderCount()
                else -> allScannedSongs.map { it.artist.trim().ifEmpty { getString(R.string.unknown_artist) } }.distinct().size
            }
            binding.drawerFilterInfoText.text = when (subMode) {
                LibraryFilterMode.ALBUM -> "Select from $count albums"
                LibraryFilterMode.FOLDER -> "Select from $count folders"
                else -> "Select from $count artists"
            }
        }
        updateFilterSubList()
        applyCurrentFilter()
    }

    private fun clearFilter() {
        isFilterPreviewActive = false
        updateAppTitle()
        isAllTracksDrillDown = false
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        activeOngoingQueueTitle = null
        activeOngoingFilterMode = LibraryFilterMode.TRACK
        selectedFilterValue = null
        currentFolderNavigationPath.clear()
        currentFilterMode = LibraryFilterMode.TRACK
        binding.activeFilterBar.visibility = View.GONE
        val subMode = when (binding.filterToggleGroup.checkedButtonId) {
            R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
            R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
            else -> LibraryFilterMode.ARTIST
        }
        val count = when (subMode) {
            LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { getString(R.string.unknown_album) } }.distinct().size
            LibraryFilterMode.FOLDER -> getRootFolderCount()
            else -> allScannedSongs.map { it.artist.trim().ifEmpty { getString(R.string.unknown_artist) } }.distinct().size
        }
        binding.drawerFilterInfoText.text = when (subMode) {
            LibraryFilterMode.ALBUM -> "Select from $count albums"
            LibraryFilterMode.FOLDER -> "Select from $count folders"
            else -> "Select from $count artists"
        }
        updateFilterSubList()
        applyCurrentFilter()
        if (playbackService != null && allScannedSongs.isNotEmpty()) {
            val sortedAll = sortSongs(allScannedSongs, currentSortField, isSortAscending)
            activeOngoingQueue = sortedAll
            playbackService?.refreshQueue(sortedAll)
        }
    }

    private fun sortSongs(songs: List<Song>, field: SortField, ascending: Boolean): List<Song> {
        val comparator = Comparator<Song> { a, b ->
            when (field) {
                SortField.TITLE -> {
                    val res = a.title.compareTo(b.title, ignoreCase = true)
                    if (ascending) res else -res
                }
                SortField.DATE -> {
                    val res = a.dateModified.compareTo(b.dateModified)
                    if (res != 0) {
                        if (ascending) res else -res
                    } else {
                        a.title.compareTo(b.title, ignoreCase = true)
                    }
                }
                SortField.ARTIST -> {
                    val aArtist = a.artist.trim().ifEmpty { getString(R.string.unknown_artist) }
                    val bArtist = b.artist.trim().ifEmpty { getString(R.string.unknown_artist) }
                    val res = aArtist.compareTo(bArtist, ignoreCase = true)
                    if (res != 0) {
                        if (ascending) res else -res
                    } else {
                        a.title.compareTo(b.title, ignoreCase = true)
                    }
                }
                SortField.ALBUM -> {
                    val aAlbum = a.album.trim().ifEmpty { getString(R.string.unknown_album) }
                    val bAlbum = b.album.trim().ifEmpty { getString(R.string.unknown_album) }
                    val res = aAlbum.compareTo(bAlbum, ignoreCase = true)
                    if (res != 0) {
                        if (ascending) res else -res
                    } else {
                        a.title.compareTo(b.title, ignoreCase = true)
                    }
                }
            }
        }
        return songs.sortedWith(comparator)
    }

    private fun showSortDialog() {
        val options = mutableListOf<SortOption>()
        options.add(SortOption("Title (A → Z)", SortField.TITLE, true))
        options.add(SortOption("Title (Z → A)", SortField.TITLE, false))
        options.add(SortOption("Date Added (Newest)", SortField.DATE, false))
        options.add(SortOption("Date Added (Oldest)", SortField.DATE, true))

        when (currentFilterMode) {
            LibraryFilterMode.TRACK, LibraryFilterMode.FOLDER -> {
                options.add(SortOption("Artist (A → Z)", SortField.ARTIST, true))
                options.add(SortOption("Artist (Z → A)", SortField.ARTIST, false))
                options.add(SortOption("Album (A → Z)", SortField.ALBUM, true))
                options.add(SortOption("Album (Z → A)", SortField.ALBUM, false))
            }
            LibraryFilterMode.ARTIST -> {
                options.add(SortOption("Album (A → Z)", SortField.ALBUM, true))
                options.add(SortOption("Album (Z → A)", SortField.ALBUM, false))
            }
            LibraryFilterMode.ALBUM -> {
                options.add(SortOption("Artist (A → Z)", SortField.ARTIST, true))
                options.add(SortOption("Artist (Z → A)", SortField.ARTIST, false))
            }
        }

        val checkedIndex = options.indexOfFirst { it.field == currentSortField && it.ascending == isSortAscending }
            .let { if (it >= 0) it else 0 }

        val labels = options.map { it.label }.toTypedArray()

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.sort_queue)
            .setSingleChoiceItems(labels, checkedIndex) { dialog, which ->
                val selected = options[which]
                currentSortField = selected.field
                isSortAscending = selected.ascending

                getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .putString(KEY_SORT_FIELD, currentSortField.name)
                    .putBoolean(KEY_SORT_ASCENDING, isSortAscending)
                    .apply()

                applyCurrentFilter()

                if (!isFilterPreviewActive && playbackService != null && displayedSongs.isNotEmpty()) {
                    playbackService?.refreshQueue(displayedSongs)
                }

                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun applyCurrentFilter() {
        val baseList = when (currentFilterMode) {
            LibraryFilterMode.TRACK -> allScannedSongs
            LibraryFilterMode.ARTIST -> {
                if (selectedFilterValue.isNullOrEmpty()) {
                    allScannedSongs
                } else {
                    allScannedSongs.filter {
                        val artistName = it.artist.trim().ifEmpty { getString(R.string.unknown_artist) }
                        artistName.equals(selectedFilterValue, ignoreCase = true)
                    }
                }
            }
            LibraryFilterMode.ALBUM -> {
                if (selectedFilterValue.isNullOrEmpty()) {
                    allScannedSongs
                } else {
                    allScannedSongs.filter {
                        val albumName = it.album.trim().ifEmpty { getString(R.string.unknown_album) }
                        albumName.equals(selectedFilterValue, ignoreCase = true)
                    }
                }
            }
            LibraryFilterMode.FOLDER -> {
                if (selectedFilterValue.isNullOrEmpty()) {
                    allScannedSongs
                } else {
                    allScannedSongs.filter { song ->
                        val segs = getSongFolderSegments(song)
                        val folderPath = segs.joinToString("/")
                        folderPath.equals(selectedFilterValue, ignoreCase = true) ||
                                folderPath.endsWith("/$selectedFilterValue", ignoreCase = true) ||
                                segs.any { it.equals(selectedFilterValue, ignoreCase = true) } ||
                                song.getResolvedFolderName().equals(selectedFilterValue, ignoreCase = true)
                    }
                }
            }
        }

        val filteredBySearch = if (currentSearchQuery.isNotBlank()) {
            val q = currentSearchQuery.lowercase(Locale.getDefault())
            val locale = Locale.getDefault()
            baseList.filter { song ->
                if (isUniversalSearch) {
                    song.title.lowercase(locale).contains(q)
                } else {
                    matchesFrontText(song.title, q, locale)
                }
            }
        } else {
            baseList
        }

        val filtered = sortSongs(filteredBySearch, currentSortField, isSortAscending)
        displayedSongs = filtered
        songAdapter?.setSongs(filtered)
        playbackService?.currentSong?.let { songAdapter?.setCurrentPlayingSongId(it.id) }

        val filterVal = selectedFilterValue
        if (!filterVal.isNullOrEmpty()) {
            binding.sheetTitleText.text = filterVal
        } else if (isFilterPreviewActive && currentFilterMode == LibraryFilterMode.TRACK) {
            binding.sheetTitleText.text = getString(R.string.all_tracks)
        } else {
            binding.sheetTitleText.text = getString(R.string.queue_title)
        }
        binding.sheetTitleText.isSelected = true
        updateAppTitle()

        if (filtered.isNotEmpty()) {
            binding.songCountText.text = "${filtered.size} songs"
            binding.emptyStateLayout.visibility = View.GONE
            binding.songsRecyclerView.visibility = View.VISIBLE
        } else {
            binding.songCountText.text = "0 songs"
            binding.emptyStateLayout.visibility = View.VISIBLE
            binding.songsRecyclerView.visibility = View.GONE
            if (currentSearchQuery.isNotBlank()) {
                binding.emptyStateText.text = getString(R.string.no_tracks_found_format, currentSearchQuery)
                binding.btnSelectFolderEmpty.visibility = View.GONE
            } else {
                binding.emptyStateText.text = getString(R.string.no_songs_found)
                binding.btnSelectFolderEmpty.visibility = if (allScannedSongs.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun matchesFrontText(text: String, query: String, locale: Locale): Boolean {
        val lower = text.trim().lowercase(locale)
        if (lower.startsWith(query)) return true
        val strippedPunctuation = lower.trimStart('"', '\'', '`', '“', '”', '‘', '’', '(', '[', '{', '.', '-', '_', ' ')
        if (strippedPunctuation.isNotEmpty() && strippedPunctuation.startsWith(query)) return true
        val strippedNumbers = strippedPunctuation.replaceFirst(Regex("^(\\d{1,3}[.\\-\\s_]+|[(\\[]\\d{1,3}[)\\]][.\\-\\s_]*)"), "").trim()
        if (strippedNumbers.isNotEmpty() && strippedNumbers.startsWith(query)) return true
        return false
    }

    private fun updateSearchModeUI() {
        if (isUniversalSearch) {
            val primaryColor = ThemeColors.getPrimary(this)
            binding.btnSearchMode.setColorFilter(primaryColor)
            binding.btnSearchMode.alpha = 1.0f
        } else {
            val onSurfaceVariant = ThemeColors.getOnSurfaceVariant(this)
            binding.btnSearchMode.setColorFilter(onSurfaceVariant)
            binding.btnSearchMode.alpha = 0.5f
        }
        binding.searchEditText.hint = getString(R.string.search_tracks_hint)
        binding.btnSearchMode.contentDescription = getString(R.string.search_mode)
    }

    private fun updatePlaylistCutoutPadding(customProgress: Float? = null) {
        if (!::bottomSheetBehavior.isInitialized) return
        val progress = customProgress ?: when (bottomSheetBehavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> 1f
            else -> 0f
        }
        val topPadding = (safeTopCutoutInset * progress).toInt()
        val leftPadding = if (progress > 0.05f) safeLeftCutoutInset else 0
        val rightPadding = if (progress > 0.05f) safeRightCutoutInset else 0

        if (binding.playlistSlidingPanel.paddingTop != topPadding ||
            binding.playlistSlidingPanel.paddingLeft != leftPadding ||
            binding.playlistSlidingPanel.paddingRight != rightPadding
        ) {
            binding.playlistSlidingPanel.setPadding(
                leftPadding,
                topPadding,
                rightPadding,
                0
            )
        }
    }

    private fun openSearchBar(clearExisting: Boolean = false) {
        if (clearExisting) {
            binding.searchEditText.setText("")
            currentSearchQuery = ""
        }
        updateSearchModeUI()
        binding.searchBarContainer.visibility = View.VISIBLE
        binding.searchEditText.post {
            binding.searchEditText.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT)
        }
        applyCurrentFilter()
    }

    private fun closeSearchBar(clearQuery: Boolean = true) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
        binding.searchEditText.clearFocus()
        if (clearQuery) {
            binding.searchEditText.setText("")
            currentSearchQuery = ""
        }
        binding.searchBarContainer.visibility = View.GONE
        applyCurrentFilter()
    }

    private fun updateFolderFilterVisibility() {
        val allSongSegs = allScannedSongs.map { getSongFolderSegments(it) }
        val hasMultipleFolders = allSongSegs.mapNotNull { it.firstOrNull() }.distinct().size > 1 ||
                allSongSegs.flatten().distinct().size > 1 ||
                allScannedSongs.map { it.getResolvedFolderName() }.filter { it.isNotBlank() }.distinct().size > 1
        binding.btnFilterFolders.visibility = if (hasMultipleFolders) View.VISIBLE else View.GONE
        if (!hasMultipleFolders && binding.filterToggleGroup.checkedButtonId == R.id.btn_filter_folders) {
            binding.filterToggleGroup.check(R.id.btn_filter_artists)
            if (currentFilterMode == LibraryFilterMode.FOLDER) {
                setFilterMode(LibraryFilterMode.ARTIST)
            }
        }
    }

    private fun updateSongList(songs: List<Song>) {
        allScannedSongs = songs
        scannedSongs = songs
        if (activeOngoingQueue.isEmpty() || activeOngoingQueueTitle == null) {
            activeOngoingQueue = songs
        }
        binding.drawerSubtitleText.text = "${songs.size} songs"
        binding.cardAllTracksSubtitle.text = "${songs.size} songs"
        updateFolderFilterVisibility()
        updateFilterSubList()
        applyCurrentFilter()
        if (isBound && playbackService != null && playbackService?.queue.isNullOrEmpty() && displayedSongs.isNotEmpty()) {
            playbackService?.refreshQueue(displayedSongs)
        }
        AlbumArtLoader.preloadAll(applicationContext, songs, lifecycleScope)
    }

    private fun openSettingsMenu() {
        val intent = Intent(this, SettingsActivity::class.java)
        settingsLauncher.launch(intent)
    }

    private fun showOptionsMenu(anchor: View? = null) {
        openSettingsMenu()
    }

    private fun showTrackDetailsDialog() {
        val syncManager = xyz.omniplay.sync.OmniSyncManager.getInstance(this)
        if (syncManager.currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
            val format = syncManager.currentTrackFormat.ifEmpty { "Audio" }
            val quality = syncManager.currentTrackQuality
            val isHiRes = syncManager.currentTrackIsHiRes
            val latency = syncManager.currentLatencyMs
            val hostName = syncManager.currentHostRoomName ?: syncManager.currentHost?.name ?: "Host"
            val streamQualityStr = if (syncManager.streamQuality == xyz.omniplay.sync.OmniSyncQuality.LOW) "Low (32-bit Float Direct)" else "High (Native)"

            val details = """
                AUDIO INPUT
                Source Format: $format
                Resolution: ${quality.ifEmpty { if (isHiRes) "24-bit PCM" else "16-bit PCM" }}
                Stream Quality: $streamQualityStr
                Broadcast Host: $hostName

                PROCESSING PIPELINE
                Audio Engine: OmniSync Ultra-Low-Latency Audio Engine
                Latency: ${latency}ms
                Pipeline: ${if (syncManager.streamQuality == xyz.omniplay.sync.OmniSyncQuality.LOW) "Direct 32-bit Float Pipeline" else "Native Bitstream Pipeline"}

                AUDIO OUTPUT
                Output Mode: Android AudioTrack (${if (syncManager.streamQuality == xyz.omniplay.sync.OmniSyncQuality.LOW) "32-bit Float Engine" else "Native Engine"})
            """.trimIndent()

            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.track_details)
                .setMessage(details)
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val song = playbackService?.currentSong
        if (song == null) {
            Toast.makeText(this, "No track currently playing", Toast.LENGTH_SHORT).show()
            return
        }

        val info = playbackService?.currentAudioInfo
        val format = info?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val quality = info?.formatQualityString()?.takeIf { it.isNotEmpty() } ?: song.audioQuality
        val isHiRes = isHiResAudio(song, info, format, quality)
        val isBitPerfect = info?.isBitPerfect == true || playbackService?.isBitPerfectActive == true
        val outputDevice = playbackService?.getAudioOutputDeviceInfo() ?: "Default Audio Output"

        val inputResolution = when {
            format.startsWith("DSD") -> {
                val mhz = when {
                    info != null && info.sampleRate >= 11289600 -> "11.28 MHz"
                    info != null && info.sampleRate >= 5644800 -> "5.64 MHz"
                    info != null && info.sampleRate >= 2822400 -> "2.82 MHz"
                    else -> "2.82 MHz"
                }
                "1-bit PDM @ $mhz"
            }
            info != null && info.bitDepth > 0 && info.sampleRate > 0 -> {
                val khz = String.format(Locale.US, "%.1f kHz", info.sampleRate / 1000.0)
                "${info.bitDepth}-bit PCM @ $khz"
            }
            song.audioQuality.isNotEmpty() -> song.audioQuality
            else -> if (isHiRes) "24-bit PCM @ 96.0 kHz" else "16-bit PCM @ 44.1 kHz"
        }

        val inputBitrate = when {
            info != null && info.bitrate > 0 -> "${info.bitrate / 1000} kbps"
            song.duration > 0 && song.fileSize > 0 -> "${(song.fileSize * 8) / song.duration} kbps"
            else -> "Lossless Variable"
        }

        val inputChannels = "${info?.channels ?: 2}.0 Stereo"

        val pipelineProcessing = when {
            format.startsWith("DSD") -> "64-tap Blackman-Nuttall Windowed-Sinc FIR Filter -> Studio 32-bit Float PCM"
            isHiRes -> "Native 32-bit Float High-Res Audio Engine"
            else -> "Native 32-bit Float Audio Engine"
        }

        val audioEngine = if (isHiRes) {
            "32-bit Floating Point High-Res PCM"
        } else {
            "32-bit Floating Point PCM"
        }

        val audioOutputResolution = if (isBitPerfect) {
            when {
                format.startsWith("DSD") -> "Direct DSD Bitstream (Bit-Exact)"
                info != null && info.bitDepth > 0 && info.sampleRate > 0 -> {
                    val khz = if (info.sampleRate % 1000 == 0) "${info.sampleRate / 1000}khz" else String.format(Locale.US, "%.1fkhz", info.sampleRate / 1000.0)
                    "${info.bitDepth}bit/$khz (Bit-Exact)"
                }
                else -> "Bit-Exact Pass-Through"
            }
        } else {
            "16bit/48khz"
        }

        val outputMode = if (isBitPerfect) {
            "Direct HAL / USB Hardware Pass-Through"
        } else if (isHiRes) {
            "Android AudioTrack (High-Res 32-bit Float Engine)"
        } else {
            "Android AudioTrack (Direct 32-bit Float Engine)"
        }

        val details = """
            AUDIO INPUT
            Source Format: $format
            Resolution: $inputResolution
            Bitrate: $inputBitrate
            Channels: $inputChannels

            PROCESSING PIPELINE
            Audio Engine: $audioEngine
            Signal Processing: $pipelineProcessing
            Software Resampling: ${if (isBitPerfect) "Bypassed (Bit-Exact)" else "Direct Float"}
            Hardware Acceleration: Enabled (Zero-Copy Buffer Queue)

            AUDIO OUTPUT
            Active Device: $outputDevice
            Output: $audioOutputResolution
            Output Mode: $outputMode
        """.trimIndent()

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.track_details)
            .setMessage(details)
            .setPositiveButton("OK", null)
            .show()
    }

    // PlaybackListener callbacks
    override fun onTrackChanged(song: Song?) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            // Immediately reset lyrics state and clear UI so previous song's lyrics NEVER persist
            lyricsFetchJob?.cancel()
            currentLyrics = null
            lastOnlineLyricsSongId = null
            lyricAdapter?.submitLines(emptyList())
            binding.lyricsPlainText.text = ""

            if (song == null) {
                setupDefaultView()
                songAdapter?.setCurrentPlayingSongId(-1L)
                renderLyricsResult(LyricsResult.NotFound("No song is currently playing"))
                return@runOnUiThread
            }

            binding.songTitleText.text = song.title
            binding.songTitleText.isSelected = true
            val displayArtist = song.artist.trim().ifEmpty { getString(R.string.unknown_artist) }
            val displayAlbum = song.album.trim().ifEmpty { getString(R.string.unknown_album) }
            binding.artistNameText.text = displayArtist
            binding.artistNameText.isSelected = true
            binding.albumNameText.text = displayAlbum
            binding.totalTimeText.text = Song.formatTime(song.duration)

            updateAudioBadges(song, playbackService?.currentAudioInfo)

            binding.playbackSlider.setDuration(song.duration)
            binding.playbackSlider.setProgress(0L)
            binding.playbackSlider.setPlaying(playbackService?.isPlaying() == true)
            binding.playbackSlider.isEnabled = true

            songAdapter?.setCurrentPlayingSongId(song.id)

            binding.albumArtCard.translationX = 0f
            binding.albumArtCard.rotation = 0f
            binding.peekAlbumArtCard.visibility = View.INVISIBLE

            if (!isLyricsShowing) {
                binding.albumArtCard.visibility = View.VISIBLE
                binding.albumArtCard.translationY = 0f
                binding.lyricsCard.visibility = View.INVISIBLE
            }

            if (isLyricsShowing) {
                val cached = LrcLibClient.getCachedLyrics(this@MainActivity, song)
                if (cached != null) {
                    currentLyrics = cached
                    renderLyricsResult(LyricsResult.Success(cached, isOffline = cached.isOffline))
                    if (!cached.hasSynced && !cached.isInstrumental) {
                        loadLyricsForSong(song, forceRefresh = false)
                    }
                } else {
                    showLyricsLoading()
                    loadLyricsForSong(song, forceRefresh = false)
                }
            } else {
                // If lyrics card is hidden, check embedded/offline lyrics immediately without making any network requests
                LrcLibClient.getCachedLyrics(this@MainActivity, song)?.let { cached ->
                    currentLyrics = cached
                }
            }

            val cachedArt = AlbumArtLoader.getCachedAlbumArt(song)
            if (cachedArt != null) {
                binding.albumArtImage.setImageBitmap(cachedArt)
            } else {
                binding.albumArtImage.setImageResource(R.drawable.default_album_art)
            }

            // Asynchronously load real album art
            lifecycleScope.launch {
                val bitmap = AlbumArtLoader.loadAlbumArt(this@MainActivity, song)
                if (::binding.isInitialized && playbackService?.currentSong?.id == song.id) {
                    if (bitmap != null) {
                        binding.albumArtImage.setImageBitmap(bitmap)
                    } else {
                        binding.albumArtImage.setImageResource(R.drawable.default_album_art)
                    }
                }
            }
        }
    }

    override fun onAudioInfoChanged(audioInfo: AudioTrackInfo?) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            updateAudioBadges(playbackService?.currentSong, audioInfo)
        }
    }

    private fun isHiResAudio(song: Song?, audioInfo: AudioTrackInfo?, format: String, quality: String): Boolean {
        if (song == null) return false
        val qNorm = quality.lowercase(Locale.ROOT).replace(" ", "").replace("-", "")
        val isHiResQuality = qNorm.contains("24bit") ||
                qNorm.contains("32bit") ||
                qNorm.contains("24/") ||
                qNorm.contains("32/") ||
                qNorm.contains("/24") ||
                qNorm.contains("/32") ||
                qNorm.contains("88.2") ||
                qNorm.contains("96") ||
                qNorm.contains("176.4") ||
                qNorm.contains("192") ||
                qNorm.contains("352.8") ||
                qNorm.contains("384") ||
                qNorm.contains("mhz") ||
                qNorm.contains("dsd") ||
                qNorm.contains("hires") ||
                qNorm.contains("hi-res")

        val sampleRate = maxOf(audioInfo?.sampleRate ?: 0, 0)
        val bitDepth = maxOf(audioInfo?.bitDepth ?: 0, 0)
        val isHiResNumeric = sampleRate > 48000 || bitDepth > 16 || bitDepth == 1

        val isHiResFormat = format.startsWith("DSD", true) ||
                format.equals("DSF", true) ||
                format.equals("DFF", true)

        val pathNorm = song.filePath.lowercase(Locale.ROOT)
        val isHiResPath = pathNorm.contains("hires") ||
                pathNorm.contains("hi-res") ||
                pathNorm.contains("24bit") ||
                pathNorm.contains("32bit") ||
                pathNorm.contains("96khz") ||
                pathNorm.contains("192khz") ||
                pathNorm.contains("dsd")

        return (audioInfo?.checkHiRes() == true) ||
                song.isHiRes ||
                isHiResNumeric ||
                isHiResFormat ||
                isHiResQuality ||
                isHiResPath
    }

    private fun updateAudioBadges(song: Song?, audioInfo: AudioTrackInfo?) {
        val syncManager = xyz.omniplay.sync.OmniSyncManager.getInstance(this)
        val isListener = syncManager.currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER

        if (isListener) {
            val format = syncManager.currentTrackFormat
            val quality = syncManager.currentTrackQuality
            val isHiRes = syncManager.currentTrackIsHiRes

            var hasAnyBadge = false
            if (format.isNotEmpty()) {
                binding.badgeFormat.text = format
                binding.badgeFormat.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeFormat.visibility = View.GONE
            }

            if (quality.isNotEmpty()) {
                binding.badgeQuality.text = quality
                binding.badgeQuality.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeQuality.visibility = View.GONE
            }

            if (isHiRes) {
                binding.badgeHires.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeHires.visibility = View.GONE
            }

            binding.badgeBitPerfect.visibility = View.GONE

            val latency = syncManager.currentLatencyMs
            if (latency > 0L) {
                binding.badgeLatency.text = "${latency}ms"
                binding.badgeLatency.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeLatency.visibility = View.GONE
            }

            binding.audioBadgeContainer.visibility = if (hasAnyBadge) View.VISIBLE else View.GONE
            return
        }

        binding.badgeLatency.visibility = View.GONE
        if (song == null) {
            binding.audioBadgeContainer.visibility = View.GONE
            return
        }

        val format = audioInfo?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val quality = audioInfo?.formatQualityString()?.takeIf { it.isNotEmpty() } ?: song.audioQuality
        val isHiRes = isHiResAudio(song, audioInfo, format, quality)

        if (format.isNotEmpty()) {
            binding.audioBadgeContainer.visibility = View.VISIBLE
            binding.badgeFormat.text = format
            binding.badgeFormat.visibility = View.VISIBLE

            if (quality.isNotEmpty()) {
                binding.badgeQuality.visibility = View.VISIBLE
                binding.badgeQuality.text = quality
            } else {
                binding.badgeQuality.visibility = View.GONE
            }

            if (isHiRes) {
                binding.badgeHires.visibility = View.VISIBLE
            } else {
                binding.badgeHires.visibility = View.GONE
            }

            val isBitPerfect = audioInfo?.isBitPerfect == true || playbackService?.isBitPerfectActive == true
            if (isBitPerfect) {
                binding.badgeBitPerfect.visibility = View.VISIBLE
            } else {
                binding.badgeBitPerfect.visibility = View.GONE
            }
        } else {
            binding.audioBadgeContainer.visibility = View.GONE
        }
    }

    override fun onPlaybackStateChanged(isPlaying: Boolean) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            updatePlayPauseButton(isPlaying)
            binding.playbackSlider.setPlaying(isPlaying)
        }
    }

    override fun onProgressUpdate(currentPositionMs: Int, totalDurationMs: Int) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            if (!isUserTrackingSlider && binding.playbackSlider.isEnabled &&
                xyz.omniplay.sync.OmniSyncManager.getInstance(this).currentRole != xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                binding.playbackSlider.setProgress(currentPositionMs.toLong())
                binding.currentTimeText.text = Song.formatTime(currentPositionMs.toLong())
                binding.totalTimeText.text = Song.formatTime(totalDurationMs.toLong())
            }
            if (isLyricsShowing) {
                updateLyricsProgress(currentPositionMs.toLong())
            }
        }
    }

    override fun onShuffleModeChanged(enabled: Boolean) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            updateShuffleButton(enabled)
        }
    }

    override fun onRepeatModeChanged(mode: Int) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            updateRepeatButton(mode)
        }
    }

    override fun onQueueChanged(queue: List<Song>) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            songAdapter?.setSongs(queue)
            binding.songCountText.text = "${queue.size} songs"
            if (queue.isNotEmpty()) {
                binding.emptyStateLayout.visibility = View.GONE
                binding.songsRecyclerView.visibility = View.VISIBLE
            } else {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.songsRecyclerView.visibility = View.GONE
            }
            val currentId = playbackService?.currentSong?.id ?: -1L
            songAdapter?.setCurrentPlayingSongId(currentId)
            if (playbackService?.isShuffleEnabled == true) {
                binding.songsRecyclerView.scrollToPosition(0)
            }
        }
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        val iconRes = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        binding.btnPlayPauseIcon.setImageResource(iconRes)
    }

    private fun getActiveControlTint(): Int {
        return ThemeColors.getPrimary(this)
    }

    private fun getInactiveControlTint(): Int {
        return ThemeColors.getOnSurfaceVariant(this)
    }

    private fun getStandardControlTint(): Int {
        return ThemeColors.getOnSurfaceVariant(this)
    }

    private fun updateShuffleButton(enabled: Boolean) {
        val tintColor = if (enabled) {
            getActiveControlTint()
        } else {
            getInactiveControlTint()
        }
        binding.btnShuffle.setColorFilter(tintColor)
        binding.btnShuffle.alpha = if (enabled) 1.0f else 0.45f
    }

    private fun updateRepeatButton(mode: Int) {
        when (mode) {
            PlaybackService.REPEAT_ALL -> {
                binding.btnRepeat.setImageResource(R.drawable.ic_repeat)
                binding.btnRepeat.setColorFilter(getActiveControlTint())
                binding.btnRepeat.alpha = 1.0f
            }
            PlaybackService.REPEAT_ONE -> {
                binding.btnRepeat.setImageResource(R.drawable.ic_repeat_one)
                binding.btnRepeat.setColorFilter(getActiveControlTint())
                binding.btnRepeat.alpha = 1.0f
            }
            else -> {
                binding.btnRepeat.setImageResource(R.drawable.ic_repeat)
                binding.btnRepeat.setColorFilter(getInactiveControlTint())
                binding.btnRepeat.alpha = 0.45f
            }
        }
    }

    private fun updateOmniSyncButtonState(role: xyz.omniplay.sync.OmniSyncRole) {
        val tint = if (role == xyz.omniplay.sync.OmniSyncRole.IDLE) {
            ThemeColors.getOnSurfaceVariant(this)
        } else {
            ThemeColors.getPrimary(this)
        }
        binding.btnOmnisync.setColorFilter(tint)
    }

    private val omniSyncListener = object : xyz.omniplay.sync.OmniSyncManager.OmniSyncListener {
        override fun onRoleChanged(role: xyz.omniplay.sync.OmniSyncRole) {
            runOnUiThread {
                updateOmniSyncButtonState(role)
                if (role == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    val hostName = xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentHostRoomName
                    binding.albumNameText.text = "OmniSync • ${hostName ?: "Listening"}"
                    binding.playbackSlider.isEnabled = true
                    binding.playbackSlider.isSeekable = false
                    binding.playbackSlider.setNeutralMode(true)
                    binding.btnShuffle.isEnabled = false
                    binding.btnRepeat.isEnabled = false
                    binding.btnPrevious.isEnabled = false
                    binding.btnNext.isEnabled = false
                    binding.btnShuffle.alpha = 0.45f
                    binding.btnRepeat.alpha = 0.45f
                    binding.btnPrevious.alpha = 0.45f
                    binding.btnNext.alpha = 0.45f
                    binding.btnShuffle.setColorFilter(getInactiveControlTint())
                    binding.btnRepeat.setColorFilter(getInactiveControlTint())
                    binding.btnPrevious.setColorFilter(getInactiveControlTint())
                    binding.btnNext.setColorFilter(getInactiveControlTint())
                    updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
                    val dur = xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentStreamDurationMs
                    if (dur > 0L) {
                        binding.playbackSlider.updateDuration(dur)
                        binding.totalTimeText.text = Song.formatTime(dur)
                    }
                    updatePlayPauseButton(isPlaying = true)
                } else if (role == xyz.omniplay.sync.OmniSyncRole.IDLE) {
                    binding.playbackSlider.isSeekable = true
                    binding.playbackSlider.setNeutralMode(false)
                    binding.badgeLatency.visibility = View.GONE
                    binding.btnShuffle.isEnabled = true
                    binding.btnRepeat.isEnabled = true
                    binding.btnPrevious.isEnabled = true
                    binding.btnNext.isEnabled = true
                    binding.btnPrevious.alpha = 1.0f
                    binding.btnNext.alpha = 1.0f
                    binding.btnPrevious.setColorFilter(getStandardControlTint())
                    binding.btnNext.setColorFilter(getStandardControlTint())
                    updateShuffleButton(playbackService?.isShuffleEnabled == true)
                    updateRepeatButton(playbackService?.repeatMode ?: PlaybackService.REPEAT_OFF)
                    playbackService?.currentSong?.let {
                        binding.playbackSlider.isEnabled = true
                        onTrackChanged(it)
                        updatePlayPauseButton(playbackService?.isPlaying() == true)
                    } ?: run {
                        binding.playbackSlider.isEnabled = false
                        binding.playbackSlider.setProgress(0L)
                        binding.currentTimeText.text = getString(R.string.default_time)
                        binding.totalTimeText.text = getString(R.string.default_time)
                        binding.songTitleText.text = getString(R.string.no_track_selected)
                        binding.artistNameText.text = ""
                        binding.albumNameText.text = ""
                        binding.audioBadgeContainer.visibility = View.GONE
                        updatePlayPauseButton(false)
                    }
                }
            }
        }

        override fun onTrackInfoChanged(title: String, artist: String) {
            runOnUiThread {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    if (title.isNotEmpty()) {
                        binding.songTitleText.text = title
                        binding.artistNameText.text = if (artist.isNotEmpty()) artist else "OmniSync Stream"
                        val dur = xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentStreamDurationMs
                        if (dur > 0L) {
                            binding.playbackSlider.updateDuration(dur)
                            binding.totalTimeText.text = Song.formatTime(dur)
                        }
                        updatePlayPauseButton(isPlaying = true)
                        updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)

                        val streamSong = Song(
                            id = (java.util.UUID.nameUUIDFromBytes((title + artist).toByteArray()).mostSignificantBits and Long.MAX_VALUE).let { if (it == 0L) 1L else it },
                            title = title,
                            artist = artist,
                            album = "",
                            duration = dur,
                            contentUri = Uri.EMPTY
                        )
                        loadLyricsForSong(streamSong)
                    }
                }
            }
        }

        override fun onTrackAudioInfoChanged(format: String, quality: String, isHiRes: Boolean) {
            runOnUiThread {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
                }
            }
        }

        override fun onStreamQualityChanged(quality: xyz.omniplay.sync.OmniSyncQuality) {
            runOnUiThread {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
                }
            }
        }

        override fun onPlaybackStateChanged(isPlaying: Boolean) {
            runOnUiThread {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    updatePlayPauseButton(isPlaying)
                    binding.playbackSlider.setPlaying(isPlaying)
                }
            }
        }

        override fun onProgressUpdate(currentPositionMs: Long, durationMs: Long) {
            runOnUiThread {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    if (durationMs > 0L) {
                        binding.playbackSlider.updateDuration(durationMs)
                        binding.totalTimeText.text = Song.formatTime(durationMs)
                    }
                    binding.playbackSlider.setProgress(currentPositionMs)
                    binding.currentTimeText.text = Song.formatTime(currentPositionMs)
                    if (isLyricsShowing) {
                        updateLyricsProgress(currentPositionMs)
                    }
                }
            }
        }

        override fun onLatencyUpdate(latencyMs: Long) {
            runOnUiThread {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(this@MainActivity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    if (latencyMs > 0L) {
                        binding.audioBadgeContainer.visibility = View.VISIBLE
                        binding.badgeLatency.visibility = View.VISIBLE
                        binding.badgeLatency.text = "${latencyMs}ms"
                    } else {
                        binding.badgeLatency.visibility = View.GONE
                    }
                }
            }
        }

        override fun onHostsDiscovered(hosts: List<xyz.omniplay.sync.OmniSyncHost>) {}
        override fun onPeersChanged(peers: List<xyz.omniplay.sync.OmniSyncPeer>) {}
        override fun onError(message: String) {}
    }

    override fun onDestroy() {
        xyz.omniplay.sync.OmniSyncManager.getInstance(this).removeListener(omniSyncListener)
        if (isBound) {
            playbackService?.removeListener(this)
            unbindService(serviceConnection)
            isBound = false
        }
        super.onDestroy()
    }
}
