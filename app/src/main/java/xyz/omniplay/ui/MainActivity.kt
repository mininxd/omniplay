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
import android.os.IBinder
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.data.MusicDatabase
import xyz.omniplay.data.MusicScanner
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.sync.OmniSyncManager
import xyz.omniplay.sync.OmniSyncRole
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.AudioTrackInfo
import xyz.omniplay.util.MusicFolderManager
import xyz.omniplay.util.ThemeColors
import xyz.omniplay.util.ThemeHelper
import xyz.omniplay.util.UpdateChecker
import java.util.Locale

class MainActivity : AppCompatActivity(), PlaybackService.PlaybackListener {

    companion object {
        const val PREFS_NAME = "omniplay_prefs"
        const val KEY_MUSIC_FOLDER_URI = "key_music_folder_uri"
        const val KEY_MUSIC_FOLDERS_SET = "key_music_folders_set"
        const val KEY_SHOW_ALBUM_ART_IN_PLAYLIST = "key_show_album_art_in_playlist"
        const val KEY_FETCH_ONLINE_ARTWORK = "key_fetch_online_artwork"
        const val KEY_SORT_FIELD = "key_sort_field"
        const val KEY_SORT_ASCENDING = "key_sort_ascending"
        const val KEY_CUSTOM_SONG_ORDER = "key_custom_song_order"
        const val KEY_UNIVERSAL_SEARCH = "key_universal_search"
    }

    private lateinit var binding: ActivityMainBinding
    private var playbackService: PlaybackService? = null
    private var isBound = false
    private var isUserTrackingSlider = false

    // Modular UI Controllers
    lateinit var lyricsController: LyricsController
        private set
    lateinit var playlistPanelController: PlaylistPanelController
        private set
    lateinit var drawerFilterController: DrawerFilterController
        private set
    lateinit var playbackGestureController: PlaybackGestureController
        private set

    // Backward-compatibility delegating properties
    val isLyricsShowing: Boolean
        get() = if (::lyricsController.isInitialized) lyricsController.isAnyLyricsShowing else false
    val isFilterMenuOpen: Boolean
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.isFilterMenuOpen else false
    var isFilterPreviewActive: Boolean
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.isFilterPreviewActive else false
        set(value) { if (::drawerFilterController.isInitialized) drawerFilterController.isFilterPreviewActive = value }
    var currentFilterMode: LibraryFilterMode
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.currentFilterMode else LibraryFilterMode.TRACK
        set(value) { if (::drawerFilterController.isInitialized) drawerFilterController.currentFilterMode = value }
    var selectedFilterValue: String?
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.selectedFilterValue else null
        set(value) { if (::drawerFilterController.isInitialized) drawerFilterController.selectedFilterValue = value }
    var activeOngoingQueueTitle: String?
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.activeOngoingQueueTitle else null
        set(value) { if (::drawerFilterController.isInitialized) drawerFilterController.activeOngoingQueueTitle = value }
    var activeOngoingFilterMode: LibraryFilterMode
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.activeOngoingFilterMode else LibraryFilterMode.TRACK
        set(value) { if (::drawerFilterController.isInitialized) drawerFilterController.activeOngoingFilterMode = value }
    var activeOngoingQueue: List<Song>
        get() = if (::drawerFilterController.isInitialized) drawerFilterController.activeOngoingQueue else emptyList()
        set(value) { if (::drawerFilterController.isInitialized) drawerFilterController.activeOngoingQueue = value }
    val bottomSheetBehavior: BottomSheetBehavior<View>
        get() = playlistPanelController.bottomSheetBehavior

    private var currentSortField: SortField = SortField.TITLE
    private var isSortAscending: Boolean = true
    private var customSongOrder: List<Long> = emptyList()

    fun saveCustomSongOrder(order: List<Long>) {
        val mergedOrder = mergeCustomOrder(customSongOrder, order)
        customSongOrder = mergedOrder
        currentSortField = SortField.CUSTOM
        isSortAscending = true
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_CUSTOM_SONG_ORDER, mergedOrder.joinToString(","))
            .putString(KEY_SORT_FIELD, SortField.CUSTOM.name)
            .putBoolean(KEY_SORT_ASCENDING, true)
            .apply()
    }

    private fun mergeCustomOrder(baseOrder: List<Long>, newSubsetOrder: List<Long>): List<Long> {
        val allIds = allScannedSongs.map { it.id }
        val fullBase = if (baseOrder.isNotEmpty()) {
            baseOrder + allIds.filterNot { it in baseOrder }
        } else {
            allIds
        }
        val subsetSet = newSubsetOrder.toSet()
        var subsetIdx = 0
        val result = fullBase.map { id ->
            if (id in subsetSet && subsetIdx < newSubsetOrder.size) {
                newSubsetOrder[subsetIdx++]
            } else {
                id
            }
        }.toMutableList()
        while (subsetIdx < newSubsetOrder.size) {
            val remainingId = newSubsetOrder[subsetIdx++]
            if (remainingId !in result) {
                result.add(remainingId)
            }
        }
        return result
    }

    private val musicScanner by lazy { MusicScanner(this) }
    private val musicDatabase by lazy { MusicDatabase.getInstance(this) }
    private var scannedSongs = listOf<Song>()
    private var allScannedSongs = listOf<Song>()
    private var displayedSongs = listOf<Song>()
    private var songAdapter: SongAdapter? = null
    private var currentSearchQuery = ""
    private var isUniversalSearch = false

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
        ThemeHelper.applyTheme(this)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ThemeHelper.applySystemBars(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val customOrderStr = prefs.getString(KEY_CUSTOM_SONG_ORDER, null)
        customSongOrder = customOrderStr?.split(",")?.mapNotNull { it.trim().toLongOrNull() } ?: emptyList()

        currentSortField = try {
            val field = SortField.valueOf(prefs.getString(KEY_SORT_FIELD, SortField.TITLE.name) ?: SortField.TITLE.name)
            if (field == SortField.CUSTOM && customSongOrder.isEmpty()) SortField.TITLE else field
        } catch (e: Exception) {
            SortField.TITLE
        }
        isSortAscending = prefs.getBoolean(KEY_SORT_ASCENDING, true)
        isUniversalSearch = prefs.getBoolean(KEY_UNIVERSAL_SEARCH, false)

        initControllers()

        setupBackPressHandler()
        setupDefaultView()
        playlistPanelController.setup()
        drawerFilterController.setup()
        lyricsController.setup { /* seek listener */ }
        playbackGestureController.setup()
        setupInWindowPlaylistAdapter()
        setupListeners()
        bindPlaybackService()
        checkAndRequestPermissions()
        OmniSyncManager.getInstance(this).addListener(omniSyncListener)
        handleIncomingIntent(intent)
        checkForUpdatesOnStart()
    }

    private fun initControllers() {
        lyricsController = LyricsController(
            activity = this,
            binding = binding,
            getPlaybackService = { playbackService },
            onQueueDrag = { event ->
                playlistPanelController.handleSlidingPanelDrag(event, isFromFullscreen = true)
            }
        )

        playlistPanelController = PlaylistPanelController(
            activity = this,
            binding = binding,
            onUpdateAppTitle = { updateAppTitle() },
            onPanelCollapsed = {
                val dp = resources.displayMetrics.density
                binding.playlistSlidingPanel.elevation = 12 * dp
                binding.queueScrimOverlay.elevation = 6 * dp
                if (binding.searchBarContainer.visibility == View.VISIBLE) {
                    closeSearchBar(clearQuery = true)
                }
                if (drawerFilterController.isFilterPreviewActive) {
                    drawerFilterController.revertToOngoingQueue()
                }
            }
        )

        drawerFilterController = DrawerFilterController(
            activity = this,
            binding = binding,
            getAllScannedSongs = { allScannedSongs },
            getCurrentPlayingSongId = { playbackService?.currentSong?.id },
            getCurrentSortField = { currentSortField },
            isSortAscending = { isSortAscending },
            onPlaySongRequested = { song, queue, targetIdx, _, _ ->
                displayedSongs = queue
                songAdapter?.setSongs(queue)
                songAdapter?.setCurrentPlayingSongId(song.id)

                playbackService?.let { service ->
                    val queueMatches = service.queue.size == queue.size && service.queue.any { it.id == song.id }
                    if (queueMatches) {
                        service.playSongFromPlaylist(song, startPlaying = true)
                    } else {
                        service.setSongQueue(queue, startIndex = targetIdx, startPlaying = true)
                    }
                }
            },
            onApplyFilterRequested = { applyCurrentFilter() },
            onUpdateAppTitle = { updateAppTitle() },
            onCloseSearchBar = {
                if (binding.searchBarContainer.visibility == View.VISIBLE) {
                    closeSearchBar(clearQuery = true)
                }
            },
            onCollapseBottomSheet = { playlistPanelController.collapse() },
            onShowSortDialog = { showSortDialog() },
            getCustomSongOrder = { customSongOrder },
            onCustomSongOrderChanged = { order ->
                saveCustomSongOrder(order)
                applyCurrentFilter()
                if (!drawerFilterController.isFilterPreviewActive && playbackService != null && displayedSongs.isNotEmpty()) {
                    playbackService?.refreshQueue(displayedSongs)
                }
            },
            onSongLongClicked = { song: Song, anchor: View ->
                showSongOptionsMenu(anchor, song)
                true
            }
        )

        playbackGestureController = PlaybackGestureController(
            activity = this,
            binding = binding,
            getPlaybackService = { playbackService },
            lyricsController = lyricsController,
            drawerFilterController = drawerFilterController,
            onSkipNext = { playbackService?.skipNext(forceNext = true) },
            onSkipPrevious = { playbackService?.skipPrevious(forcePrevious = false) },
            onAlbumArtTap = { playlistPanelController.expand() }
        )
    }

    private fun checkForUpdatesOnStart() {
        lifecycleScope.launch {
            val release = UpdateChecker.checkLatestRelease(this@MainActivity)
            if (isFinishing || isDestroyed) return@launch

            if (release != null && release.isNewer) {
                UpdateChecker.showUpdateDialog(this@MainActivity, release)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateOmniSyncButtonState(OmniSyncManager.getInstance(this).currentRole)
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
                playlistPanelController.collapse()
            }
        }
    }

    private fun openQueueFromFullscreen() {
        val dp = resources.displayMetrics.density
        binding.playlistSlidingPanel.elevation = 20 * dp
        binding.queueScrimOverlay.elevation = 18 * dp
        binding.queueScrimOverlay.bringToFront()
        binding.playlistSlidingPanel.bringToFront()
        playlistPanelController.expand()
    }

    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerFilterController.handleBackPressed()) {
                    return
                } else if (playlistPanelController.handleBackPressed()) {
                    return
                } else if (lyricsController.handleBackPressed()) {
                    return
                } else if (binding.searchBarContainer.visibility == View.VISIBLE) {
                    closeSearchBar()
                    return
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
        if (drawerFilterController.handleBackPressed()) {
            return
        }
        if (playlistPanelController.handleBackPressed()) {
            return
        }
        if (lyricsController.handleBackPressed()) {
            return
        }
        if (binding.searchBarContainer.visibility == View.VISIBLE) {
            closeSearchBar()
            return
        }
        super.onBackPressed()
    }

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

        binding.fullscreenPlaybackSlider.setDuration(1000L)
        binding.fullscreenPlaybackSlider.setProgress(0L)
        binding.fullscreenPlaybackSlider.setPlaying(false)
        binding.fullscreenPlaybackSlider.isEnabled = false
        binding.fullscreenLyricsSongTitle.text = getString(R.string.no_track_selected)
        binding.fullscreenLyricsArtistName.text = ""
        binding.fullscreenCurrentTimeText.text = getString(R.string.default_time)
        binding.fullscreenTotalTimeText.text = getString(R.string.default_time)

        updateAudioBadges(null, null)

        updateShuffleButton(false)
        updateRepeatButton(PlaybackService.REPEAT_OFF)
        updatePlayPauseButton(isPlaying = false)
        updateAppTitle()
    }

    private fun setupInWindowPlaylistAdapter() {
        val isShowArt = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)

        var isDragging = false

        val songItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT
        ) {
            override fun isLongPressDragEnabled(): Boolean = false

            override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                if (drawerFilterController.isFilterPreviewActive || currentSearchQuery.isNotBlank()) {
                    return 0
                }
                return ItemTouchHelper.RIGHT
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.bindingAdapterPosition
                val toPos = target.bindingAdapterPosition
                if (fromPos != RecyclerView.NO_POSITION && toPos != RecyclerView.NO_POSITION) {
                    songAdapter?.moveItem(fromPos, toPos)
                    return true
                }
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    val pos = viewHolder.bindingAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        binding.songsRecyclerView.post {
                            removeSongFromQueueAt(pos)
                        }
                    }
                }
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    isDragging = true
                    viewHolder?.itemView?.alpha = 0.85f
                    viewHolder?.itemView?.scaleX = 1.02f
                    viewHolder?.itemView?.scaleY = 1.02f
                    viewHolder?.itemView?.translationZ = 16f
                } else if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    isDragging = false
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.alpha = 1.0f
                viewHolder.itemView.scaleX = 1.0f
                viewHolder.itemView.scaleY = 1.0f
                viewHolder.itemView.translationZ = 0f

                if (isDragging) {
                    isDragging = false
                    val newSongs = songAdapter?.getSongs() ?: emptyList()
                    if (newSongs.isNotEmpty()) {
                        displayedSongs = newSongs
                        saveCustomSongOrder(newSongs.map { it.id })
                        if (!drawerFilterController.isFilterPreviewActive && playbackService != null) {
                            playbackService?.refreshQueue(displayedSongs)
                        }
                        drawerFilterController.updateFilterSubList()
                    }
                }
            }
        })

        songAdapter = SongAdapter(
            showAlbumArt = isShowArt,
            onSongClicked = { song, _ ->
                if (OmniSyncManager.getInstance(this).currentRole == OmniSyncRole.LISTENER) {
                    Toast.makeText(this, "OmniSync is active: Disconnect from OmniSync to play local media", Toast.LENGTH_SHORT).show()
                    return@SongAdapter
                }

                if (binding.searchBarContainer.visibility == View.VISIBLE) {
                    closeSearchBar(clearQuery = true)
                }

                val wasPreview = drawerFilterController.isFilterPreviewActive
                drawerFilterController.isFilterPreviewActive = false
                drawerFilterController.activeOngoingQueueTitle = drawerFilterController.selectedFilterValue
                drawerFilterController.activeOngoingFilterMode = drawerFilterController.currentFilterMode

                playlistPanelController.collapse()

                playbackService?.let { service ->
                    val fullList = if (displayedSongs.isNotEmpty()) {
                        displayedSongs
                    } else if (allScannedSongs.isNotEmpty()) {
                        SongSortHelper.sortSongs(this, allScannedSongs, currentSortField, isSortAscending, customSongOrder)
                    } else {
                        listOf(song)
                    }
                    drawerFilterController.activeOngoingQueue = fullList

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
            },
            onStartDragListener = { vh -> songItemTouchHelper.startDrag(vh) },
            onSongLongClicked = { song: Song, anchor: View ->
                showSongOptionsMenu(anchor, song, isFromQueue = true)
                true
            }
        )

        binding.swipeRefreshDrawer.setColorSchemeColors(
            ThemeColors.getPrimary(this),
            ThemeColors.getSecondary(this)
        )
        binding.swipeRefreshDrawer.setProgressBackgroundColorSchemeColor(
            ThemeColors.getSurfaceContainerHigh(this)
        )
        binding.swipeRefreshDrawer.setOnRefreshListener {
            rescanMusic()
        }

        songItemTouchHelper.attachToRecyclerView(binding.songsRecyclerView)
        binding.songsRecyclerView.adapter = songAdapter
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songsRecyclerView.isNestedScrollingEnabled = true

        binding.songsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 4 && playlistPanelController.getState() == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                    playlistPanelController.setState(BottomSheetBehavior.STATE_EXPANDED)
                }
            }
        })

        binding.btnSelectFolderEmpty.setOnClickListener {
            openFolderPicker()
        }

        binding.sheetTitleText.isSelected = true

        binding.btnSearchQueue.setOnClickListener {
            if (playlistPanelController.isCollapsed()) {
                playlistPanelController.expand()
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
            if (playlistPanelController.isCollapsed()) {
                playlistPanelController.expand()
            }
            showSortDialog()
        }
    }

    private fun removeSongFromQueueAt(position: Int) {
        val removed = songAdapter?.removeItem(position)
        val service = playbackService
        if (service != null && service.queue.isNotEmpty()) {
            if (position in service.queue.indices) {
                val songToRemove = service.queue[position]
                if (customSongOrder.isNotEmpty()) {
                    saveCustomSongOrder(customSongOrder.filter { it != songToRemove.id })
                }
                service.removeSongAt(position)
                displayedSongs = service.queue.toList()
                binding.songCountText.text = "${service.queue.size} songs"
                if (service.queue.isEmpty()) {
                    binding.emptyStateLayout.visibility = View.VISIBLE
                    binding.songsRecyclerView.visibility = View.GONE
                }
            }
        } else {
            val mutable = displayedSongs.toMutableList()
            if (position in mutable.indices) {
                val songToRemove = mutable.removeAt(position)
                displayedSongs = mutable
                binding.songCountText.text = "${mutable.size} songs"
                if (customSongOrder.isNotEmpty()) {
                    saveCustomSongOrder(customSongOrder.filter { it != songToRemove.id })
                }
                if (mutable.isEmpty()) {
                    binding.emptyStateLayout.visibility = View.VISIBLE
                    binding.songsRecyclerView.visibility = View.GONE
                }
            }
        }
    }

    private fun setupListeners() {
        updateOmniSyncButtonState(OmniSyncManager.getInstance(this).currentRole)
        binding.btnOmnisync.setOnClickListener {
            xyz.omniplay.sync.OmniSyncBottomSheet.newInstance()
                .show(supportFragmentManager, xyz.omniplay.sync.OmniSyncBottomSheet.TAG)
        }

        binding.btnMenu.setOnClickListener {
            openSettingsMenu()
        }

        binding.appTitleText.setOnClickListener {
            drawerFilterController.openLeftMenu()
        }

        val badgeClickListener = View.OnClickListener {
            showTrackDetailsDialog()
        }
        binding.audioBadgeContainer.setOnClickListener(badgeClickListener)
        binding.badgeFormat.setOnClickListener(badgeClickListener)
        binding.badgeQuality.setOnClickListener(badgeClickListener)
        binding.badgeHires.setOnClickListener(badgeClickListener)
        binding.badgeBitPerfect.setOnClickListener(badgeClickListener)

        binding.btnPlayPauseCard.setOnClickListener {
            if (OmniSyncManager.getInstance(this).currentRole == OmniSyncRole.LISTENER) {
                OmniSyncManager.getInstance(this).toggleListenerPlayback()
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

        binding.btnNext.setOnClickListener {
            if (OmniSyncManager.getInstance(this).currentRole == OmniSyncRole.LISTENER) return@setOnClickListener
            if (isBound) {
                playbackService?.skipNext(forceNext = true)
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (OmniSyncManager.getInstance(this).currentRole == OmniSyncRole.LISTENER) return@setOnClickListener
            if (isBound) {
                playbackService?.skipPrevious(forcePrevious = false)
            }
        }

        binding.btnShuffle.setOnClickListener {
            if (isBound) {
                playbackService?.toggleShuffle()
            }
        }

        binding.btnRepeat.setOnClickListener {
            if (isBound) {
                playbackService?.cycleRepeatMode()
            }
        }

        binding.playbackSlider.seekListener = object : SquigglySeekBar.OnSeekListener {
            override fun onStartTracking() {
                isUserTrackingSlider = true
            }

            override fun onProgressChanged(progressMs: Long, fromUser: Boolean, isCancelled: Boolean) {
                if (fromUser) {
                    binding.currentTimeText.text = Song.formatTime(progressMs)
                    binding.fullscreenCurrentTimeText.text = Song.formatTime(progressMs)
                }
            }

            override fun onStopTracking(progressMs: Long, isCancelled: Boolean) {
                isUserTrackingSlider = false
                if (isCancelled) {
                    val currentPos = playbackService?.getCurrentPosition()?.toLong() ?: progressMs
                    binding.playbackSlider.setProgress(currentPos)
                    binding.fullscreenPlaybackSlider.setProgress(currentPos)
                    binding.currentTimeText.text = Song.formatTime(currentPos)
                    binding.fullscreenCurrentTimeText.text = Song.formatTime(currentPos)
                } else {
                    playbackService?.seekTo(progressMs.toInt())
                }
            }
        }

        binding.fullscreenBtnPlayPauseCard.setOnClickListener {
            binding.btnPlayPauseCard.performClick()
        }

        binding.fullscreenBtnNext.setOnClickListener {
            binding.btnNext.performClick()
        }

        binding.fullscreenBtnPrevious.setOnClickListener {
            binding.btnPrevious.performClick()
        }

        binding.fullscreenBtnShuffle.setOnClickListener {
            binding.btnShuffle.performClick()
        }

        binding.fullscreenBtnRepeat.setOnClickListener {
            binding.btnRepeat.performClick()
        }

        binding.fullscreenPlaybackSlider.seekListener = object : SquigglySeekBar.OnSeekListener {
            override fun onStartTracking() {
                isUserTrackingSlider = true
            }

            override fun onProgressChanged(progressMs: Long, fromUser: Boolean, isCancelled: Boolean) {
                if (fromUser) {
                    binding.fullscreenCurrentTimeText.text = Song.formatTime(progressMs)
                    binding.currentTimeText.text = Song.formatTime(progressMs)
                }
            }

            override fun onStopTracking(progressMs: Long, isCancelled: Boolean) {
                isUserTrackingSlider = false
                if (isCancelled) {
                    val currentPos = playbackService?.getCurrentPosition()?.toLong() ?: progressMs
                    binding.fullscreenPlaybackSlider.setProgress(currentPos)
                    binding.playbackSlider.setProgress(currentPos)
                    binding.fullscreenCurrentTimeText.text = Song.formatTime(currentPos)
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
                if (isRescan) {
                    binding.swipeRefreshDrawer.isRefreshing = true
                }

                val cachedSongs = withContext(Dispatchers.IO) {
                    musicDatabase.getAllSongs()
                }

                if (!isRescan && cachedSongs.isNotEmpty()) {
                    updateSongList(cachedSongs)
                    return@launch
                }

                if (isUserInitiated) {
                    Toast.makeText(
                        this@MainActivity,
                        if (isRescan) "Rescanning music..." else "Scanning music...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // Stage 1: Prioritize nested or specific folder if currently being browsed
                val priorityFolder = drawerFilterController.getActiveBrowsingFolder()
                if (!priorityFolder.isNullOrBlank() && isRescan) {
                    val prioritySongs = withContext(Dispatchers.IO) {
                        musicScanner.scanPriorityFolder(priorityFolder, folderUris, cachedSongs = cachedSongs)
                    }
                    if (prioritySongs.isNotEmpty()) {
                        val mergedPriority = (prioritySongs + allScannedSongs).distinctBy {
                            it.filePath.ifEmpty { it.contentUri.toString() }
                        }
                        allScannedSongs = mergedPriority
                        scannedSongs = mergedPriority
                        applyCurrentFilter()
                    }
                }

                // Stage 2: Complete scan across all configured music folders
                val allSongs = mutableListOf<Song>()
                for (uri in folderUris) {
                    try {
                        val folderSongs = musicScanner.scanFolder(
                            treeUri = uri,
                            priorityFolderName = priorityFolder,
                            cachedSongs = cachedSongs,
                            onProgressBatch = { batch ->
                                lifecycleScope.launch(Dispatchers.Main) {
                                    if (batch.isNotEmpty()) {
                                        val merged = (batch + allScannedSongs).distinctBy {
                                            it.filePath.ifEmpty { it.contentUri.toString() }
                                        }
                                        allScannedSongs = merged
                                        scannedSongs = merged
                                        applyCurrentFilter()
                                    }
                                }
                            }
                        )
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
            } catch (e: Exception) {
                updateSongList(emptyList())
                Toast.makeText(this@MainActivity, "Error scanning folders: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.swipeRefreshDrawer.isRefreshing = false
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

    fun updateAppTitle() {
        binding.appTitleText.text = getString(R.string.app_name)
    }

    private fun applyCurrentFilter() {
        val baseList = when (drawerFilterController.currentFilterMode) {
            LibraryFilterMode.TRACK -> allScannedSongs
            LibraryFilterMode.ARTIST -> {
                val filterVal = drawerFilterController.selectedFilterValue
                if (filterVal.isNullOrEmpty()) {
                    allScannedSongs
                } else {
                    allScannedSongs.filter {
                        val artistName = it.artist.trim().ifEmpty { getString(R.string.unknown_artist) }
                        artistName.equals(filterVal, ignoreCase = true)
                    }
                }
            }
            LibraryFilterMode.ALBUM -> {
                val filterVal = drawerFilterController.selectedFilterValue
                if (filterVal.isNullOrEmpty()) {
                    allScannedSongs
                } else {
                    allScannedSongs.filter {
                        val albumName = it.album.trim().ifEmpty { getString(R.string.unknown_album) }
                        albumName.equals(filterVal, ignoreCase = true)
                    }
                }
            }
            LibraryFilterMode.FOLDER -> {
                val filterVal = drawerFilterController.selectedFilterValue
                if (filterVal.isNullOrEmpty()) {
                    allScannedSongs
                } else {
                    allScannedSongs.filter { song ->
                        val segs = drawerFilterController.getSongFolderSegments(song)
                        val folderPath = segs.joinToString("/")
                        folderPath.equals(filterVal, ignoreCase = true) ||
                                folderPath.endsWith("/$filterVal", ignoreCase = true) ||
                                segs.any { it.equals(filterVal, ignoreCase = true) } ||
                                song.getResolvedFolderName().equals(filterVal, ignoreCase = true)
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

        val filtered = SongSortHelper.sortSongs(this, filteredBySearch, currentSortField, isSortAscending, customSongOrder)
        displayedSongs = filtered
        songAdapter?.setSongs(filtered)
        playbackService?.currentSong?.let { songAdapter?.setCurrentPlayingSongId(it.id) }

        val filterVal = drawerFilterController.selectedFilterValue
        if (!filterVal.isNullOrEmpty()) {
            binding.sheetTitleText.text = filterVal
        } else if (drawerFilterController.isFilterPreviewActive && drawerFilterController.currentFilterMode == LibraryFilterMode.TRACK) {
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

    private fun updateSongList(songs: List<Song>) {
        allScannedSongs = songs
        scannedSongs = songs
        if (drawerFilterController.activeOngoingQueue.isEmpty() || drawerFilterController.activeOngoingQueueTitle == null) {
            drawerFilterController.activeOngoingQueue = songs
        }
        binding.drawerSubtitleText.text = "${songs.size} songs"
        drawerFilterController.updateAllTracksCardUI(isActive = drawerFilterController.isAllTracksDrillDown)
        drawerFilterController.updateFolderFilterVisibility()
        drawerFilterController.updateFilterSubList()
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
        AudioDetailsHelper.showTrackDetailsDialog(this, playbackService)
    }

    private fun updateAudioBadges(song: Song?, audioInfo: AudioTrackInfo?) {
        AudioDetailsHelper.updateAudioBadges(this, binding, song, audioInfo, playbackService)
    }

    private fun isHiResAudio(song: Song?, audioInfo: AudioTrackInfo?, format: String, quality: String): Boolean {
        return AudioDetailsHelper.isHiResAudio(song, audioInfo, format, quality)
    }

    private fun showSortDialog() {
        val isDrawerSorting = drawerFilterController.isFilterMenuOpen
        val filterMode = if (isDrawerSorting) {
            when {
                drawerFilterController.isAllTracksDrillDown -> LibraryFilterMode.TRACK
                drawerFilterController.currentArtistDrillDown != null -> LibraryFilterMode.TRACK
                drawerFilterController.currentAlbumDrillDown != null -> LibraryFilterMode.TRACK
                binding.filterToggleGroup.checkedButtonId == R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
                binding.filterToggleGroup.checkedButtonId == R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
                else -> LibraryFilterMode.ARTIST
            }
        } else {
            LibraryFilterMode.TRACK
        }

        SongSortHelper.showSortDialog(
            activity = this,
            currentFilterMode = filterMode,
            currentSortField = currentSortField,
            isSortAscending = isSortAscending,
            hasCustomSort = customSongOrder.isNotEmpty()
        ) { field, ascending ->
            currentSortField = field
            isSortAscending = ascending

            getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_SORT_FIELD, currentSortField.name)
                .putBoolean(KEY_SORT_ASCENDING, isSortAscending)
                .apply()

            if (isDrawerSorting) {
                drawerFilterController.updateFilterSubList()
            } else {
                val currentQueue = playbackService?.queue?.takeIf { it.isNotEmpty() }
                    ?: displayedSongs.takeIf { it.isNotEmpty() }
                    ?: allScannedSongs

                val sortedQueue = SongSortHelper.sortSongs(this, currentQueue, field, ascending, customSongOrder)
                displayedSongs = sortedQueue
                songAdapter?.setSongs(sortedQueue)
                binding.songCountText.text = "${sortedQueue.size} songs"
                playbackService?.refreshQueue(sortedQueue)
                drawerFilterController.activeOngoingQueue = sortedQueue
            }
        }
    }

    private fun sortSongs(songs: List<Song>, field: SortField, ascending: Boolean): List<Song> {
        return SongSortHelper.sortSongs(this, songs, field, ascending, customSongOrder)
    }

    fun showSongOptionsMenu(anchor: View, song: Song, isFromQueue: Boolean = false) {
        val themedContext = ContextThemeWrapper(this, R.style.ThemeOverlay_Omniplay_PopupMenu)
        val popup = PopupMenu(themedContext, anchor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popup.setForceShowIcon(true)
        } else {
            try {
                val fieldMPopup = popup.javaClass.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popup)
                mPopup.javaClass.getMethod("setForceShowIcon", Boolean::class.java).invoke(mPopup, true)
            } catch (ignored: Exception) {}
        }

        val iconColor = ThemeColors.getPrimary(this)
        val textColor = ThemeColors.getOnSurface(this)

        fun addMenuItem(id: Int, order: Int, title: String, iconRes: Int) {
            val spanTitle = SpannableString(title).apply {
                setSpan(ForegroundColorSpan(textColor), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            val item = popup.menu.add(Menu.NONE, id, order, spanTitle)
            ContextCompat.getDrawable(this, iconRes)?.mutate()?.let { drawable ->
                DrawableCompat.setTint(drawable, iconColor)
                item.icon = drawable
            }
        }

        var order = 0
        if (!isFromQueue) {
            addMenuItem(1, order++, "Add to queue", R.drawable.ic_add)
            addMenuItem(2, order++, "Play next", R.drawable.ic_skip_next)
        }
        addMenuItem(3, order++, "Go to artist", R.drawable.ic_person)
        addMenuItem(4, order++, "Go to album", R.drawable.ic_album)
        addMenuItem(5, order++, "Go to folders", R.drawable.ic_folder)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                1 -> {
                    onSongAddedToQueue(song, playNext = false)
                    true
                }
                2 -> {
                    onSongAddedToQueue(song, playNext = true)
                    true
                }
                3 -> {
                    playlistPanelController.collapse()
                    if (binding.searchBarContainer.visibility == View.VISIBLE) {
                        closeSearchBar(clearQuery = true)
                    }
                    drawerFilterController.navigateToArtist(song.artist, song.id)
                    true
                }
                4 -> {
                    playlistPanelController.collapse()
                    if (binding.searchBarContainer.visibility == View.VISIBLE) {
                        closeSearchBar(clearQuery = true)
                    }
                    drawerFilterController.navigateToAlbum(song.album, song.id)
                    true
                }
                5 -> {
                    playlistPanelController.collapse()
                    if (binding.searchBarContainer.visibility == View.VISIBLE) {
                        closeSearchBar(clearQuery = true)
                    }
                    drawerFilterController.navigateToSongFolder(song, song.id)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun onSongAddedToQueue(song: Song, playNext: Boolean) {
        val service = playbackService
        if (service != null) {
            if (playNext) {
                service.playNext(song)
            } else {
                service.addToQueue(song)
            }
        } else {
            val mutable = displayedSongs.toMutableList()
            if (playNext) {
                mutable.add(0.coerceAtMost(mutable.size), song)
            } else {
                mutable.add(song)
            }
            displayedSongs = mutable
            songAdapter?.setSongs(mutable)
        }
        if (customSongOrder.isNotEmpty()) {
            val mutable = customSongOrder.toMutableList()
            mutable.remove(song.id)
            if (playNext) {
                val currentId = service?.currentSong?.id
                val insertIdx = if (currentId != null) (mutable.indexOf(currentId) + 1).coerceIn(0, mutable.size) else 0
                mutable.add(insertIdx, song.id)
            } else {
                mutable.add(song.id)
            }
            customSongOrder = mutable
            getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_CUSTOM_SONG_ORDER, mutable.joinToString(","))
                .apply()
        }
    }

    // PlaybackListener callbacks
    override fun onTrackChanged(song: Song?) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            lyricsController.onTrackChanged(song)

            if (song == null) {
                setupDefaultView()
                songAdapter?.setCurrentPlayingSongId(-1L)
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

            binding.fullscreenLyricsSongTitle.text = song.title
            binding.fullscreenLyricsArtistName.text = displayArtist
            binding.fullscreenTotalTimeText.text = Song.formatTime(song.duration)
            binding.fullscreenCurrentTimeText.text = getString(R.string.default_time)
            binding.fullscreenPlaybackSlider.setDuration(song.duration)
            binding.fullscreenPlaybackSlider.setProgress(0L)
            binding.fullscreenPlaybackSlider.setPlaying(playbackService?.isPlaying() == true)
            binding.fullscreenPlaybackSlider.isEnabled = true

            songAdapter?.setCurrentPlayingSongId(song.id)

            binding.albumArtCard.translationX = 0f
            binding.albumArtCard.rotation = 0f
            binding.peekAlbumArtCard.visibility = View.INVISIBLE

            if (!lyricsController.isLyricsShowing) {
                binding.albumArtCard.visibility = View.VISIBLE
                binding.albumArtCard.translationY = 0f
                binding.lyricsCard.visibility = View.INVISIBLE
            }

            val cachedArt = AlbumArtLoader.getCachedAlbumArt(song)
            if (cachedArt != null) {
                binding.albumArtImage.setImageBitmap(cachedArt)
            } else {
                binding.albumArtImage.setImageResource(R.drawable.default_album_art)
            }

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

    override fun onPlaybackStateChanged(isPlaying: Boolean) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            updatePlayPauseButton(isPlaying)
            binding.playbackSlider.setPlaying(isPlaying)
            binding.fullscreenPlaybackSlider.setPlaying(isPlaying)
        }
    }

    override fun onProgressUpdate(currentPositionMs: Int, totalDurationMs: Int) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            if (!isUserTrackingSlider && binding.playbackSlider.isEnabled &&
                OmniSyncManager.getInstance(this).currentRole != OmniSyncRole.LISTENER) {
                binding.playbackSlider.setProgress(currentPositionMs.toLong())
                binding.currentTimeText.text = Song.formatTime(currentPositionMs.toLong())
                binding.totalTimeText.text = Song.formatTime(totalDurationMs.toLong())
                binding.fullscreenPlaybackSlider.setProgress(currentPositionMs.toLong())
                binding.fullscreenCurrentTimeText.text = Song.formatTime(currentPositionMs.toLong())
                binding.fullscreenTotalTimeText.text = Song.formatTime(totalDurationMs.toLong())
            }
            if (lyricsController.isLyricsShowing || lyricsController.isFullScreenLyricsShowing) {
                lyricsController.updateLyricsProgress(currentPositionMs.toLong())
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
            displayedSongs = queue
            if (songAdapter?.getSongs() != queue) {
                songAdapter?.setSongs(queue)
            }
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
        binding.fullscreenBtnPlayPauseIcon.setImageResource(iconRes)
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
        binding.fullscreenBtnShuffle.setColorFilter(tintColor)
        binding.fullscreenBtnShuffle.alpha = if (enabled) 1.0f else 0.45f
    }

    private fun updateRepeatButton(mode: Int) {
        val iconRes = when (mode) {
            PlaybackService.REPEAT_ONE -> R.drawable.ic_repeat_one
            else -> R.drawable.ic_repeat
        }
        val tintColor = if (mode != PlaybackService.REPEAT_OFF) getActiveControlTint() else getInactiveControlTint()
        val alphaVal = if (mode != PlaybackService.REPEAT_OFF) 1.0f else 0.45f

        binding.btnRepeat.setImageResource(iconRes)
        binding.btnRepeat.setColorFilter(tintColor)
        binding.btnRepeat.alpha = alphaVal

        binding.fullscreenBtnRepeat.setImageResource(iconRes)
        binding.fullscreenBtnRepeat.setColorFilter(tintColor)
        binding.fullscreenBtnRepeat.alpha = alphaVal
    }

    private fun updateOmniSyncButtonState(role: OmniSyncRole) {
        val tint = if (role == OmniSyncRole.IDLE) {
            ThemeColors.getOnSurfaceVariant(this)
        } else {
            ThemeColors.getPrimary(this)
        }
        binding.btnOmnisync.setColorFilter(tint)
    }

    private val omniSyncListener = object : OmniSyncManager.OmniSyncListener {
        override fun onRoleChanged(role: OmniSyncRole) {
            runOnUiThread {
                updateOmniSyncButtonState(role)
                if (role == OmniSyncRole.LISTENER) {
                    val hostName = OmniSyncManager.getInstance(this@MainActivity).currentHostRoomName
                    binding.albumNameText.text = "OmniSync • ${hostName ?: "Listening"}"
                    binding.playbackSlider.isEnabled = true
                    binding.playbackSlider.isSeekable = false
                    binding.playbackSlider.setNeutralMode(true)
                    binding.fullscreenPlaybackSlider.isEnabled = true
                    binding.fullscreenPlaybackSlider.isSeekable = false
                    binding.fullscreenPlaybackSlider.setNeutralMode(true)
                    binding.btnShuffle.isEnabled = false
                    binding.btnRepeat.isEnabled = false
                    binding.btnPrevious.isEnabled = false
                    binding.btnNext.isEnabled = false
                    binding.fullscreenBtnShuffle.isEnabled = false
                    binding.fullscreenBtnRepeat.isEnabled = false
                    binding.fullscreenBtnPrevious.isEnabled = false
                    binding.fullscreenBtnNext.isEnabled = false
                    binding.btnShuffle.alpha = 0.45f
                    binding.btnRepeat.alpha = 0.45f
                    binding.btnPrevious.alpha = 0.45f
                    binding.btnNext.alpha = 0.45f
                    binding.fullscreenBtnShuffle.alpha = 0.45f
                    binding.fullscreenBtnRepeat.alpha = 0.45f
                    binding.fullscreenBtnPrevious.alpha = 0.45f
                    binding.fullscreenBtnNext.alpha = 0.45f
                    binding.btnShuffle.setColorFilter(getInactiveControlTint())
                    binding.btnRepeat.setColorFilter(getInactiveControlTint())
                    binding.btnPrevious.setColorFilter(getInactiveControlTint())
                    binding.btnNext.setColorFilter(getInactiveControlTint())
                    binding.fullscreenBtnShuffle.setColorFilter(getInactiveControlTint())
                    binding.fullscreenBtnRepeat.setColorFilter(getInactiveControlTint())
                    binding.fullscreenBtnPrevious.setColorFilter(getInactiveControlTint())
                    binding.fullscreenBtnNext.setColorFilter(getInactiveControlTint())
                    updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
                    val dur = OmniSyncManager.getInstance(this@MainActivity).currentStreamDurationMs
                    if (dur > 0L) {
                        binding.playbackSlider.updateDuration(dur)
                        binding.totalTimeText.text = Song.formatTime(dur)
                        binding.fullscreenPlaybackSlider.updateDuration(dur)
                        binding.fullscreenTotalTimeText.text = Song.formatTime(dur)
                    }
                    updatePlayPauseButton(isPlaying = true)
                } else if (role == OmniSyncRole.IDLE) {
                    binding.playbackSlider.isSeekable = true
                    binding.playbackSlider.setNeutralMode(false)
                    binding.fullscreenPlaybackSlider.isSeekable = true
                    binding.fullscreenPlaybackSlider.setNeutralMode(false)
                    binding.badgeLatency.visibility = View.GONE
                    binding.btnShuffle.isEnabled = true
                    binding.btnRepeat.isEnabled = true
                    binding.btnPrevious.isEnabled = true
                    binding.btnNext.isEnabled = true
                    binding.fullscreenBtnShuffle.isEnabled = true
                    binding.fullscreenBtnRepeat.isEnabled = true
                    binding.fullscreenBtnPrevious.isEnabled = true
                    binding.fullscreenBtnNext.isEnabled = true
                    binding.btnPrevious.alpha = 1.0f
                    binding.btnNext.alpha = 1.0f
                    binding.fullscreenBtnPrevious.alpha = 1.0f
                    binding.fullscreenBtnNext.alpha = 1.0f
                    binding.btnPrevious.setColorFilter(getStandardControlTint())
                    binding.btnNext.setColorFilter(getStandardControlTint())
                    binding.fullscreenBtnPrevious.setColorFilter(getStandardControlTint())
                    binding.fullscreenBtnNext.setColorFilter(getStandardControlTint())
                    updateShuffleButton(playbackService?.isShuffleEnabled == true)
                    updateRepeatButton(playbackService?.repeatMode ?: PlaybackService.REPEAT_OFF)
                    playbackService?.currentSong?.let {
                        binding.playbackSlider.isEnabled = true
                        binding.fullscreenPlaybackSlider.isEnabled = true
                        onTrackChanged(it)
                        updatePlayPauseButton(playbackService?.isPlaying() == true)
                    } ?: run {
                        binding.playbackSlider.isEnabled = false
                        binding.playbackSlider.setProgress(0L)
                        binding.fullscreenPlaybackSlider.isEnabled = false
                        binding.fullscreenPlaybackSlider.setProgress(0L)
                        binding.currentTimeText.text = getString(R.string.default_time)
                        binding.totalTimeText.text = getString(R.string.default_time)
                        binding.fullscreenCurrentTimeText.text = getString(R.string.default_time)
                        binding.fullscreenTotalTimeText.text = getString(R.string.default_time)
                        binding.songTitleText.text = getString(R.string.no_track_selected)
                        binding.fullscreenLyricsSongTitle.text = getString(R.string.no_track_selected)
                        binding.artistNameText.text = ""
                        binding.fullscreenLyricsArtistName.text = ""
                        binding.albumNameText.text = ""
                        binding.audioBadgeContainer.visibility = View.GONE
                        updatePlayPauseButton(false)
                    }
                }
            }
        }

        override fun onTrackInfoChanged(title: String, artist: String) {
            runOnUiThread {
                if (OmniSyncManager.getInstance(this@MainActivity).currentRole == OmniSyncRole.LISTENER) {
                    if (title.isNotEmpty()) {
                        binding.songTitleText.text = title
                        binding.artistNameText.text = if (artist.isNotEmpty()) artist else "OmniSync Stream"
                        val dur = OmniSyncManager.getInstance(this@MainActivity).currentStreamDurationMs
                        if (dur > 0L) {
                            binding.playbackSlider.updateDuration(dur)
                            binding.totalTimeText.text = Song.formatTime(dur)
                            binding.fullscreenPlaybackSlider.updateDuration(dur)
                            binding.fullscreenTotalTimeText.text = Song.formatTime(dur)
                        }
                        binding.fullscreenLyricsSongTitle.text = title
                        binding.fullscreenLyricsArtistName.text = if (artist.isNotEmpty()) artist else "OmniSync Stream"
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
                        lyricsController.loadLyricsForSong(streamSong)
                    }
                }
            }
        }

        override fun onTrackAudioInfoChanged(format: String, quality: String, isHiRes: Boolean) {
            runOnUiThread {
                if (OmniSyncManager.getInstance(this@MainActivity).currentRole == OmniSyncRole.LISTENER) {
                    updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
                }
            }
        }

        override fun onStreamQualityChanged(quality: xyz.omniplay.sync.OmniSyncQuality) {
            runOnUiThread {
                if (OmniSyncManager.getInstance(this@MainActivity).currentRole == OmniSyncRole.LISTENER) {
                    updateAudioBadges(playbackService?.currentSong, playbackService?.currentAudioInfo)
                }
            }
        }

        override fun onPlaybackStateChanged(isPlaying: Boolean) {
            runOnUiThread {
                if (OmniSyncManager.getInstance(this@MainActivity).currentRole == OmniSyncRole.LISTENER) {
                    updatePlayPauseButton(isPlaying)
                    binding.playbackSlider.setPlaying(isPlaying)
                    binding.fullscreenPlaybackSlider.setPlaying(isPlaying)
                }
            }
        }

        override fun onProgressUpdate(currentPositionMs: Long, durationMs: Long) {
            runOnUiThread {
                if (OmniSyncManager.getInstance(this@MainActivity).currentRole == OmniSyncRole.LISTENER) {
                    if (durationMs > 0L) {
                        binding.playbackSlider.updateDuration(durationMs)
                        binding.totalTimeText.text = Song.formatTime(durationMs)
                        binding.fullscreenPlaybackSlider.updateDuration(durationMs)
                        binding.fullscreenTotalTimeText.text = Song.formatTime(durationMs)
                    }
                    binding.playbackSlider.setProgress(currentPositionMs)
                    binding.currentTimeText.text = Song.formatTime(currentPositionMs)
                    binding.fullscreenPlaybackSlider.setProgress(currentPositionMs)
                    binding.fullscreenCurrentTimeText.text = Song.formatTime(currentPositionMs)
                    if (lyricsController.isLyricsShowing || lyricsController.isFullScreenLyricsShowing) {
                        lyricsController.updateLyricsProgress(currentPositionMs)
                    }
                }
            }
        }

        override fun onLatencyUpdate(latencyMs: Long) {
            runOnUiThread {
                if (OmniSyncManager.getInstance(this@MainActivity).currentRole == OmniSyncRole.LISTENER) {
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
        OmniSyncManager.getInstance(this).removeListener(omniSyncListener)
        lyricsController.onDestroy()
        if (isBound) {
            playbackService?.removeListener(this)
            unbindService(serviceConnection)
            isBound = false
        }
        super.onDestroy()
    }
}
