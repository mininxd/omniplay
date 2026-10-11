package xyz.omniplay.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.net.Uri
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.model.Song
import xyz.omniplay.sync.OmniSyncManager
import xyz.omniplay.sync.OmniSyncRole
import xyz.omniplay.util.ThemeColors
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.util.Locale

class DrawerFilterController(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding,
    private val getAllScannedSongs: () -> List<Song>,
    private val getCurrentPlayingSongId: () -> Long?,
    private val getCurrentSortField: () -> SortField,
    private val isSortAscending: () -> Boolean,
    private val onPlaySongRequested: (song: Song, queue: List<Song>, startIndex: Int, filterMode: LibraryFilterMode, filterValue: String?) -> Unit,
    private val onApplyFilterRequested: () -> Unit,
    private val onUpdateAppTitle: () -> Unit,
    private val onCloseSearchBar: () -> Unit,
    private val onCollapseBottomSheet: () -> Unit,
    private val onShowSortDialog: () -> Unit = {},
    private val getCustomSongOrder: () -> List<Long> = { emptyList() },
    private val onCustomSongOrderChanged: (List<Long>) -> Unit = {},
    private val onSongLongClicked: (Song, View) -> Boolean = { _, _ -> false }
) {

    var currentFilterMode: LibraryFilterMode = LibraryFilterMode.TRACK
    var selectedFilterValue: String? = null
    var isFilterPreviewActive: Boolean = false
    var activeOngoingQueueTitle: String? = null
    var activeOngoingFilterMode: LibraryFilterMode = LibraryFilterMode.TRACK
    var activeOngoingQueue: List<Song> = listOf()

    var isAllTracksDrillDown: Boolean = false
        private set
    var currentArtistDrillDown: String? = null
        private set
    var currentAlbumDrillDown: String? = null
        private set
    val currentFolderNavigationPath = mutableListOf<String>()

    var isFilterMenuOpen: Boolean = false
        private set
    private var filterDrawerAnimator: ValueAnimator? = null
    private var drawerFilterAdapter: DrawerFilterAdapter? = null

    fun setup() {
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
        val touchSlop = ViewConfiguration.get(activity).scaledTouchSlop

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

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
        ) {
            override fun isLongPressDragEnabled(): Boolean = false

            override fun canDropOver(
                recyclerView: RecyclerView,
                current: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val targetItem = drawerFilterAdapter?.getItemAt(target.bindingAdapterPosition)
                return targetItem?.song != null
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos = viewHolder.bindingAdapterPosition
                val toPos = target.bindingAdapterPosition
                val currentItem = drawerFilterAdapter?.getItemAt(fromPos)
                val targetItem = drawerFilterAdapter?.getItemAt(toPos)
                if (currentItem?.song != null && targetItem?.song != null) {
                    drawerFilterAdapter?.moveItem(fromPos, toPos)
                    return true
                }
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    binding.swipeRefreshDrawer.isEnabled = false
                    viewHolder?.itemView?.alpha = 0.85f
                    viewHolder?.itemView?.translationZ = 12f
                }
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                binding.swipeRefreshDrawer.isEnabled = true
                viewHolder.itemView.alpha = 1.0f
                viewHolder.itemView.translationZ = 0f
                val songs = drawerFilterAdapter?.getItems()?.mapNotNull { it.song } ?: emptyList()
                if (songs.isNotEmpty()) {
                    onCustomSongOrderChanged(songs.map { it.id })
                }
            }
        })

        drawerFilterAdapter = DrawerFilterAdapter(
            onItemClick = { item -> onFilterItemSelected(item) },
            onStartDragListener = { vh -> itemTouchHelper.startDrag(vh) },
            onItemLongClick = { item, view ->
                item.song?.let { song -> onSongLongClicked(song, view) } ?: false
            }
        )
        itemTouchHelper.attachToRecyclerView(binding.drawerFilterRecyclerView)
        binding.drawerFilterRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.drawerFilterRecyclerView.adapter = drawerFilterAdapter

        // All Tracks Quick-Action Card Click
        binding.cardAllTracks.setOnClickListener {
            if (isAllTracksDrillDown) {
                // Toggle back if already active
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                updateAllTracksCardUI(isActive = false)
                binding.browseByContainer.visibility = View.VISIBLE
                binding.drawerFilterInfoText.visibility = View.VISIBLE
                updateFilterSubList()
            } else {
                // Activate All Tracks
                isAllTracksDrillDown = true
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                updateAllTracksCardUI(isActive = true)
                binding.browseByContainer.visibility = View.GONE
                updateFilterSubList()
            }
        }

        binding.cardAllTracksSortContainer.setOnClickListener {
            onShowSortDialog()
        }

        binding.filterToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            isAllTracksDrillDown = false
            currentArtistDrillDown = null
            currentAlbumDrillDown = null
            currentFolderNavigationPath.clear()
            updateAllTracksCardUI(isActive = false)
            binding.browseByContainer.visibility = View.VISIBLE
            when (checkedId) {
                R.id.btn_filter_artists -> setFilterMode(LibraryFilterMode.ARTIST)
                R.id.btn_filter_albums -> setFilterMode(LibraryFilterMode.ALBUM)
                R.id.btn_filter_folders -> setFilterMode(LibraryFilterMode.FOLDER)
            }
            updateSegmentedButtonsTheming()
        }

        binding.btnFilterArtists.setOnClickListener {
            if (isAllTracksDrillDown || currentArtistDrillDown != null || currentAlbumDrillDown != null || currentFolderNavigationPath.isNotEmpty()) {
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                updateAllTracksCardUI(isActive = false)
                binding.browseByContainer.visibility = View.VISIBLE
                setFilterMode(LibraryFilterMode.ARTIST)
            } else if (currentFilterMode != LibraryFilterMode.ARTIST) {
                setFilterMode(LibraryFilterMode.ARTIST)
            }
        }

        binding.btnFilterAlbums.setOnClickListener {
            if (isAllTracksDrillDown || currentArtistDrillDown != null || currentAlbumDrillDown != null || currentFolderNavigationPath.isNotEmpty()) {
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                updateAllTracksCardUI(isActive = false)
                binding.browseByContainer.visibility = View.VISIBLE
                setFilterMode(LibraryFilterMode.ALBUM)
            } else if (currentFilterMode != LibraryFilterMode.ALBUM) {
                setFilterMode(LibraryFilterMode.ALBUM)
            }
        }

        binding.btnFilterFolders.setOnClickListener {
            if (isAllTracksDrillDown || currentArtistDrillDown != null || currentAlbumDrillDown != null || currentFolderNavigationPath.isNotEmpty()) {
                isAllTracksDrillDown = false
                currentArtistDrillDown = null
                currentAlbumDrillDown = null
                currentFolderNavigationPath.clear()
                updateAllTracksCardUI(isActive = false)
                binding.browseByContainer.visibility = View.VISIBLE
                setFilterMode(LibraryFilterMode.FOLDER)
            } else if (currentFilterMode != LibraryFilterMode.FOLDER) {
                setFilterMode(LibraryFilterMode.FOLDER)
            }
        }

        binding.btnBrowseSort.setOnClickListener {
            onShowSortDialog()
        }

        binding.btnClearFilter.setOnClickListener {
            clearFilter()
        }

        updateAllTracksCardUI(isActive = false)
        updateSegmentedButtonsTheming()
    }

    /**
     * Updates the "All Tracks" button UI with Material 3 Expressive styling.
     * When active, morphs into a connected header with tonal surface elevation, pill badge, and action container.
     * When inactive, uses standard tonal surface container styling.
     */
    fun updateAllTracksCardUI(isActive: Boolean) {
        val density = activity.resources.displayMetrics.density
        val totalSongs = getAllScannedSongs().size

        if (isActive) {
            // Material 3 Expressive Active State (Connected Header)
            val surfaceContainerHigh = ThemeColors.getSurfaceContainerHigh(activity)
            val surfaceContainerHighest = ThemeColors.getSurfaceContainerHighest(activity)
            val primaryContainer = ThemeColors.getPrimaryContainer(activity)
            val primary = ThemeColors.getPrimary(activity)
            val onSurface = ThemeColors.getOnSurface(activity)

            binding.cardAllTracks.setCardBackgroundColor(surfaceContainerHigh)
            binding.cardAllTracks.strokeWidth = 0

            // Expressive shape morphing: top rounded, bottom connects cleanly to the song list
            binding.cardAllTracks.shapeAppearanceModel = binding.cardAllTracks.shapeAppearanceModel.toBuilder()
                .setTopLeftCornerSize(20f * density)
                .setTopRightCornerSize(20f * density)
                .setBottomLeftCornerSize(4f * density)
                .setBottomRightCornerSize(4f * density)
                .build()

            binding.cardAllTracksIconContainer.setCardBackgroundColor(primaryContainer)
            binding.cardAllTracksIconContainer.strokeWidth = 0
            binding.cardAllTracksIcon.setColorFilter(primary)

            binding.cardAllTracksTitle.setTextColor(onSurface)

            binding.cardAllTracksSubtitle.setTextColor(primary)
            binding.cardAllTracksSubtitle.text = "$totalSongs tracks"
            binding.cardAllTracksSubtitle.setBackgroundResource(R.drawable.bg_badge_material_you)
            binding.cardAllTracksSubtitle.setPadding((8 * density).toInt(), (2 * density).toInt(), (8 * density).toInt(), (2 * density).toInt())

            binding.cardAllTracksSortContainer.visibility = View.VISIBLE
            binding.cardAllTracksSortContainer.setCardBackgroundColor(surfaceContainerHighest)
            binding.cardAllTracksSort.setColorFilter(primary)

            binding.cardAllTracksArrowContainer.setCardBackgroundColor(surfaceContainerHighest)
            binding.cardAllTracksArrow.setImageResource(R.drawable.ic_chevron_down)
            binding.cardAllTracksArrow.setColorFilter(primary)
        } else {
            // Material 3 Expressive Inactive State
            val surfaceContainer = ThemeColors.getSurfaceContainer(activity)
            val surfaceContainerHigh = ThemeColors.getSurfaceContainerHigh(activity)
            val outlineVariant = ThemeColors.getOutlineVariant(activity)
            val primary = ThemeColors.getPrimary(activity)
            val onSurface = ThemeColors.getOnSurface(activity)
            val onSurfaceVariant = ThemeColors.getOnSurfaceVariant(activity)
            val outline = ThemeColors.getOutline(activity)

            binding.cardAllTracksSortContainer.visibility = View.GONE

            binding.cardAllTracks.setCardBackgroundColor(surfaceContainer)
            binding.cardAllTracks.strokeColor = outlineVariant
            binding.cardAllTracks.strokeWidth = (1f * density).toInt()

            // Restore all 4 rounded corners
            binding.cardAllTracks.shapeAppearanceModel = binding.cardAllTracks.shapeAppearanceModel.toBuilder()
                .setTopLeftCornerSize(20f * density)
                .setTopRightCornerSize(20f * density)
                .setBottomLeftCornerSize(20f * density)
                .setBottomRightCornerSize(20f * density)
                .build()

            binding.cardAllTracksIconContainer.setCardBackgroundColor(surfaceContainerHigh)
            binding.cardAllTracksIconContainer.strokeColor = outlineVariant
            binding.cardAllTracksIconContainer.strokeWidth = (1f * density).toInt()
            binding.cardAllTracksIcon.setColorFilter(primary)

            binding.cardAllTracksTitle.setTextColor(onSurface)

            binding.cardAllTracksSubtitle.setTextColor(onSurfaceVariant)
            binding.cardAllTracksSubtitle.text = "$totalSongs songs"
            binding.cardAllTracksSubtitle.background = null
            binding.cardAllTracksSubtitle.setPadding(0, 0, 0, 0)

            binding.cardAllTracksArrowContainer.setCardBackgroundColor(surfaceContainerHigh)
            binding.cardAllTracksArrow.setImageResource(R.drawable.ic_chevron_right)
            binding.cardAllTracksArrow.setColorFilter(outline)
        }
    }

    fun getDrawerWidth(): Float {
        val width = binding.leftDrawerMenu.width.toFloat()
        return if (width > 0f) width else (320 * activity.resources.displayMetrics.density)
    }

    fun cancelFilterDrawerAnimator() {
        filterDrawerAnimator?.cancel()
    }

    fun openLeftMenu() {
        if (isFilterMenuOpen) return
        val drawer = binding.leftDrawerMenu
        drawer.visibility = View.VISIBLE
        binding.filterDrawerScrim.visibility = View.VISIBLE
        val drawerWidth = getDrawerWidth()
        val currentTrans = drawer.translationX.coerceAtMost(0f)
        animateLeftMenu(toOpen = true, currentTrans = currentTrans, drawerWidth = drawerWidth)
        try {
            binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
        } catch (ignored: Exception) {}
    }

    fun closeLeftMenu() {
        val drawerWidth = getDrawerWidth()
        animateLeftMenu(toOpen = false, currentTrans = binding.leftDrawerMenu.translationX, drawerWidth = drawerWidth)
    }

    fun navigateToArtist(artistName: String) {
        val unknownArtistStr = activity.getString(R.string.unknown_artist)
        val targetArtist = artistName.trim().ifEmpty { unknownArtistStr }
        binding.filterToggleGroup.check(R.id.btn_filter_artists)
        currentFilterMode = LibraryFilterMode.ARTIST
        currentArtistDrillDown = targetArtist
        currentAlbumDrillDown = null
        isAllTracksDrillDown = false
        currentFolderNavigationPath.clear()
        updateAllTracksCardUI(isActive = false)
        updateFilterSubList()
        openLeftMenu()
    }

    fun navigateToAlbum(albumName: String) {
        val unknownAlbumStr = activity.getString(R.string.unknown_album)
        val targetAlbum = albumName.trim().ifEmpty { unknownAlbumStr }
        binding.filterToggleGroup.check(R.id.btn_filter_albums)
        currentFilterMode = LibraryFilterMode.ALBUM
        currentAlbumDrillDown = targetAlbum
        currentArtistDrillDown = null
        isAllTracksDrillDown = false
        currentFolderNavigationPath.clear()
        updateAllTracksCardUI(isActive = false)
        updateFilterSubList()
        openLeftMenu()
    }

    fun navigateToSongFolder(song: Song) {
        val allScannedSongs = getAllScannedSongs()
        val allSongSegs = allScannedSongs.map { getSongFolderSegments(it) }
        val depth = getCommonFolderPrefixDepth(allSongSegs)
        val segs = getSongFolderSegments(song)
        val trimmedSegs = if (depth > 0 && segs.size > depth) segs.subList(depth, segs.size) else segs

        binding.filterToggleGroup.check(R.id.btn_filter_folders)
        currentFilterMode = LibraryFilterMode.FOLDER
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        isAllTracksDrillDown = false
        currentFolderNavigationPath.clear()
        currentFolderNavigationPath.addAll(trimmedSegs)
        updateAllTracksCardUI(isActive = false)
        updateFilterSubList()
        openLeftMenu()
    }

    fun animateLeftMenu(toOpen: Boolean, currentTrans: Float, drawerWidth: Float) {
        val targetX = if (toOpen) 0f else -drawerWidth
        val progress = ((currentTrans + drawerWidth) / drawerWidth).coerceIn(0f, 1f)
        val remaining = if (toOpen) (1f - progress) else progress
        val duration = (260L * remaining).toLong().coerceIn(80L, 260L)

        filterDrawerAnimator?.cancel()
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
                    binding.leftDrawerMenu.translationX = targetX
                    isFilterMenuOpen = toOpen
                    if (!toOpen) {
                        binding.leftDrawerMenu.visibility = View.GONE
                        binding.filterDrawerScrim.visibility = View.GONE
                        binding.leftDrawerMenu.translationX = -drawerWidth
                        revertToOngoingQueue()
                    } else {
                        binding.leftDrawerMenu.visibility = View.VISIBLE
                        binding.filterDrawerScrim.visibility = View.VISIBLE
                    }
                    filterDrawerAnimator = null
                }
            })
            start()
        }
    }

    fun getSongFolderSegments(song: Song): List<String> {
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

    fun getCommonFolderPrefixDepth(allSegments: List<List<String>>): Int {
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

    fun getRootFolderCount(): Int {
        val allScannedSongs = getAllScannedSongs()
        val allSegs = allScannedSongs.map { getSongFolderSegments(it) }
        val depth = getCommonFolderPrefixDepth(allSegs)
        return allSegs.map { segs ->
            if (depth > 0 && segs.size > depth) segs[depth] else segs.firstOrNull() ?: "Music"
        }.distinct().size
    }

    fun getSongsInCurrentFolder(): List<Song> {
        val allScannedSongs = getAllScannedSongs()
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

        return SongSortHelper.sortSongs(activity, matchingSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
    }

    fun setFilterMode(mode: LibraryFilterMode) {
        isAllTracksDrillDown = false
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        currentFolderNavigationPath.clear()
        updateAllTracksCardUI(isActive = false)
        binding.browseByContainer.visibility = View.VISIBLE
        currentFilterMode = mode
        binding.drawerFilterRecyclerView.visibility = View.VISIBLE
        updateFilterSubList()

        val allScannedSongs = getAllScannedSongs()
        val matchesCurrentFilter = (activeOngoingFilterMode == mode && !selectedFilterValue.isNullOrEmpty())
        when (mode) {
            LibraryFilterMode.ARTIST -> {
                if (matchesCurrentFilter) {
                    binding.activeFilterBar.visibility = View.VISIBLE
                    binding.activeFilterText.text = "Artist: $selectedFilterValue"
                    binding.drawerFilterInfoText.text = "Filtered by artist: $selectedFilterValue"
                } else {
                    binding.activeFilterBar.visibility = View.GONE
                    val count = allScannedSongs.map { it.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) } }.distinct().size
                    binding.drawerFilterInfoText.text = "Select from $count artists"
                    if (activeOngoingFilterMode == LibraryFilterMode.TRACK) {
                        onApplyFilterRequested()
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
                    val count = allScannedSongs.map { it.album.trim().ifEmpty { activity.getString(R.string.unknown_album) } }.distinct().size
                    binding.drawerFilterInfoText.text = "Select from $count albums"
                    if (activeOngoingFilterMode == LibraryFilterMode.TRACK) {
                        onApplyFilterRequested()
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
                        onApplyFilterRequested()
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
                    LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { activity.getString(R.string.unknown_album) } }.distinct().size
                    LibraryFilterMode.FOLDER -> getRootFolderCount()
                    else -> allScannedSongs.map { it.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) } }.distinct().size
                }
                binding.drawerFilterInfoText.text = when (subMode) {
                    LibraryFilterMode.ALBUM -> "Select from $count albums"
                    LibraryFilterMode.FOLDER -> "Select from $count folders"
                    else -> "Select from $count artists"
                }
                onApplyFilterRequested()
            }
        }
    }

    fun updateFilterSubList() {
        val allScannedSongs = getAllScannedSongs()
        if (allScannedSongs.isEmpty()) {
            drawerFilterAdapter?.setItems(emptyList())
            return
        }

        val unknownAlbumStr = activity.getString(R.string.unknown_album)
        val unknownArtistStr = activity.getString(R.string.unknown_artist)

        if (isAllTracksDrillDown) {
            val allSongsSorted = SongSortHelper.sortSongs(activity, allScannedSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
            val currentPlayingId = getCurrentPlayingSongId()
            val items = mutableListOf<FilterItem>()
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
                        isSelected = (currentPlayingId == song.id)
                    )
                }
            )
            binding.drawerFilterInfoText.visibility = View.GONE
            drawerFilterAdapter?.setItems(items)
            binding.drawerFilterRecyclerView.scrollToPosition(0)
            return
        }

        binding.drawerFilterInfoText.visibility = View.VISIBLE

        val artistTarget = currentArtistDrillDown
        if (artistTarget != null) {
            val artistSongs = allScannedSongs.filter {
                it.artist.trim().ifEmpty { unknownArtistStr }.equals(artistTarget, ignoreCase = true)
            }
            val sortedArtistSongs = SongSortHelper.sortSongs(activity, artistSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
            val currentPlayingId = getCurrentPlayingSongId()
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
                        isSelected = (currentPlayingId == song.id)
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
            val sortedAlbumSongs = SongSortHelper.sortSongs(activity, albumSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
            val currentPlayingId = getCurrentPlayingSongId()
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
                        isSelected = (currentPlayingId == song.id)
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

        val sortField = getCurrentSortField()
        val ascending = isSortAscending()

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

                val sortedArtistGroups = when (sortField) {
                    SortField.DATE -> {
                        val artistDates = allScannedSongs
                            .groupBy { it.artist.trim().ifEmpty { unknownArtistStr } }
                            .mapValues { (_, songs) -> songs.maxOfOrNull { it.dateModified } ?: 0L }
                        artistGroups.sortedWith(Comparator { a, b ->
                            val da = artistDates[a.title] ?: 0L
                            val db = artistDates[b.title] ?: 0L
                            val res = da.compareTo(db)
                            if (res != 0) {
                                if (ascending) res else -res
                            } else {
                                a.title.compareTo(b.title, ignoreCase = true)
                            }
                        })
                    }
                    else -> {
                        if (ascending) {
                            artistGroups.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })
                        } else {
                            artistGroups.sortedWith(compareByDescending(String.CASE_INSENSITIVE_ORDER) { it.title })
                        }
                    }
                }

                if (activeOngoingFilterMode == LibraryFilterMode.ARTIST && !selectedFilterValue.isNullOrEmpty()) {
                    binding.drawerFilterInfoText.text = "Filtered by artist: $selectedFilterValue"
                } else {
                    binding.drawerFilterInfoText.text = "Select from ${sortedArtistGroups.size} artists"
                }
                sortedArtistGroups
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

                val sortedAlbumGroups = when (sortField) {
                    SortField.ARTIST -> {
                        val albumArtists = allScannedSongs
                            .groupBy { it.album.trim().ifEmpty { unknownAlbumStr } }
                            .mapValues { (_, songs) -> songs.firstOrNull()?.artist?.trim()?.ifEmpty { unknownArtistStr } ?: unknownArtistStr }
                        albumGroups.sortedWith(Comparator { a, b ->
                            val artA = albumArtists[a.title] ?: ""
                            val artB = albumArtists[b.title] ?: ""
                            val res = artA.compareTo(artB, ignoreCase = true)
                            if (res != 0) {
                                if (ascending) res else -res
                            } else {
                                a.title.compareTo(b.title, ignoreCase = true)
                            }
                        })
                    }
                    SortField.DATE -> {
                        val albumDates = allScannedSongs
                            .groupBy { it.album.trim().ifEmpty { unknownAlbumStr } }
                            .mapValues { (_, songs) -> songs.maxOfOrNull { it.dateModified } ?: 0L }
                        albumGroups.sortedWith(Comparator { a, b ->
                            val da = albumDates[a.title] ?: 0L
                            val db = albumDates[b.title] ?: 0L
                            val res = da.compareTo(db)
                            if (res != 0) {
                                if (ascending) res else -res
                            } else {
                                a.title.compareTo(b.title, ignoreCase = true)
                            }
                        })
                    }
                    else -> {
                        if (ascending) {
                            albumGroups.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })
                        } else {
                            albumGroups.sortedWith(compareByDescending(String.CASE_INSENSITIVE_ORDER) { it.title })
                        }
                    }
                }

                if (activeOngoingFilterMode == LibraryFilterMode.ALBUM && !selectedFilterValue.isNullOrEmpty()) {
                    binding.drawerFilterInfoText.text = "Filtered by album: $selectedFilterValue"
                } else {
                    binding.drawerFilterInfoText.text = "Select from ${sortedAlbumGroups.size} albums"
                }
                sortedAlbumGroups
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
                }

                val sortedSubfolderItems = when (sortField) {
                    SortField.DATE -> {
                        val folderDates = subfolders.mapValues { (_, pairs) ->
                            pairs.map { it.first }.maxOfOrNull { it.dateModified } ?: 0L
                        }
                        subfolderItems.sortedWith(Comparator { a, b ->
                            val da = folderDates[a.title] ?: 0L
                            val db = folderDates[b.title] ?: 0L
                            val res = da.compareTo(db)
                            if (res != 0) {
                                if (ascending) res else -res
                            } else {
                                a.title.compareTo(b.title, ignoreCase = true)
                            }
                        })
                    }
                    else -> {
                        if (ascending) {
                            subfolderItems.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.title })
                        } else {
                            subfolderItems.sortedWith(compareByDescending(String.CASE_INSENSITIVE_ORDER) { it.title })
                        }
                    }
                }

                itemsList.addAll(sortedSubfolderItems)

                val directSongsRaw = matching
                    .filter { (_, segs) -> segs.size == currentPath.size }
                    .map { it.first }

                val sortedDirectSongs = SongSortHelper.sortSongs(activity, directSongsRaw, sortField, ascending, getCustomSongOrder())
                val currentPlayingId = getCurrentPlayingSongId()
                val directSongItems = sortedDirectSongs.map { song ->
                    FilterItem(
                        title = song.title,
                        count = 0,
                        isAlbum = false,
                        isFolder = false,
                        song = song,
                        representativeSong = song,
                        subtitle = song.artist.trim().ifEmpty { unknownArtistStr },
                        isSelected = (currentPlayingId == song.id)
                    )
                }

                itemsList.addAll(directSongItems)

                if (currentPath.isNotEmpty()) {
                    val pathDisplay = currentPath.joinToString(" / ")
                    binding.drawerFilterInfoText.text = "$pathDisplay (${matching.size} songs)"
                } else {
                    val count = sortedSubfolderItems.size + directSongItems.size
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
                    updateAllTracksCardUI(isActive = false)
                    binding.browseByContainer.visibility = View.VISIBLE
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
            updateAllTracksCardUI(isActive = false)
            updateFilterSubList()
            return
        }

        if (item.song == null) {
            currentArtistDrillDown = item.title
            currentAlbumDrillDown = null
            isAllTracksDrillDown = false
            updateAllTracksCardUI(isActive = false)
            updateFilterSubList()
            return
        }

        val song = item.song
        if (OmniSyncManager.getInstance(activity).currentRole == OmniSyncRole.LISTENER) {
            Toast.makeText(activity, "OmniSync is active: Disconnect from OmniSync to play local media", Toast.LENGTH_SHORT).show()
            return
        }

        onCloseSearchBar()

        val allScannedSongs = getAllScannedSongs()
        val queueToSet: List<Song>
        val filterModeToSet: LibraryFilterMode
        val filterValueToSet: String?

        if (isAllTracksDrillDown) {
            val allSorted = SongSortHelper.sortSongs(activity, allScannedSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
            queueToSet = if (allSorted.isNotEmpty()) allSorted else listOf(song)
            filterModeToSet = LibraryFilterMode.TRACK
            filterValueToSet = null
        } else if (currentArtistDrillDown != null) {
            val unknownArtistStr = activity.getString(R.string.unknown_artist)
            val artistSongs = allScannedSongs.filter {
                it.artist.trim().ifEmpty { unknownArtistStr }.equals(currentArtistDrillDown, ignoreCase = true)
            }
            val sorted = SongSortHelper.sortSongs(activity, artistSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
            queueToSet = if (sorted.isNotEmpty()) sorted else listOf(song)
            filterModeToSet = LibraryFilterMode.ARTIST
            filterValueToSet = currentArtistDrillDown
        } else if (currentAlbumDrillDown != null) {
            val unknownAlbumStr = activity.getString(R.string.unknown_album)
            val albumSongs = allScannedSongs.filter {
                it.album.trim().ifEmpty { unknownAlbumStr }.equals(currentAlbumDrillDown, ignoreCase = true)
            }
            val sorted = SongSortHelper.sortSongs(activity, albumSongs, getCurrentSortField(), isSortAscending(), getCustomSongOrder())
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

        onPlaySongRequested(song, queueToSet, targetIdx, filterModeToSet, filterValueToSet)

        onUpdateAppTitle()
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
        onCollapseBottomSheet()
    }

    fun revertToOngoingQueue() {
        isFilterPreviewActive = false
        onUpdateAppTitle()
        isAllTracksDrillDown = false
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        selectedFilterValue = activeOngoingQueueTitle
        currentFilterMode = activeOngoingFilterMode
        currentFolderNavigationPath.clear()
        updateAllTracksCardUI(isActive = false)
        binding.browseByContainer.visibility = View.VISIBLE

        if (selectedFilterValue != null) {
            binding.activeFilterBar.visibility = View.VISIBLE
            val prefix = when (currentFilterMode) {
                LibraryFilterMode.ALBUM -> "Album: "
                LibraryFilterMode.FOLDER -> "Folder: "
                else -> "Artist: "
            }
            val typeStr = when (currentFilterMode) {
                LibraryFilterMode.ALBUM -> "album"
                LibraryFilterMode.FOLDER -> "folder"
                else -> "artist"
            }
            binding.activeFilterText.text = "$prefix$selectedFilterValue"
            binding.drawerFilterInfoText.text = "Filtered by $typeStr: $selectedFilterValue"
            when (currentFilterMode) {
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
            val allScannedSongs = getAllScannedSongs()
            val count = when (subMode) {
                LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { activity.getString(R.string.unknown_album) } }.distinct().size
                LibraryFilterMode.FOLDER -> getRootFolderCount()
                else -> allScannedSongs.map { it.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) } }.distinct().size
            }
            binding.drawerFilterInfoText.text = when (subMode) {
                LibraryFilterMode.ALBUM -> "Select from $count albums"
                LibraryFilterMode.FOLDER -> "Select from $count folders"
                else -> "Select from $count artists"
            }
        }
        updateFilterSubList()
    }

    fun clearFilter() {
        isAllTracksDrillDown = false
        currentArtistDrillDown = null
        currentAlbumDrillDown = null
        currentFolderNavigationPath.clear()
        updateAllTracksCardUI(isActive = false)
        binding.browseByContainer.visibility = View.VISIBLE

        selectedFilterValue = null
        activeOngoingQueueTitle = null
        activeOngoingFilterMode = LibraryFilterMode.TRACK
        activeOngoingQueue = getAllScannedSongs()
        isFilterPreviewActive = false
        currentFilterMode = LibraryFilterMode.TRACK
        onUpdateAppTitle()
        binding.activeFilterBar.visibility = View.GONE

        val subMode = when (binding.filterToggleGroup.checkedButtonId) {
            R.id.btn_filter_albums -> LibraryFilterMode.ALBUM
            R.id.btn_filter_folders -> LibraryFilterMode.FOLDER
            else -> LibraryFilterMode.ARTIST
        }
        val allScannedSongs = getAllScannedSongs()
        val count = when (subMode) {
            LibraryFilterMode.ALBUM -> allScannedSongs.map { it.album.trim().ifEmpty { activity.getString(R.string.unknown_album) } }.distinct().size
            LibraryFilterMode.FOLDER -> getRootFolderCount()
            else -> allScannedSongs.map { it.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) } }.distinct().size
        }
        binding.drawerFilterInfoText.text = when (subMode) {
            LibraryFilterMode.ALBUM -> "Select from $count albums"
            LibraryFilterMode.FOLDER -> "Select from $count folders"
            else -> "Select from $count artists"
        }
        updateFilterSubList()
        onApplyFilterRequested()
    }

    fun updateFolderFilterVisibility() {
        val allScannedSongs = getAllScannedSongs()
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
        updateSegmentedButtonsTheming()
    }

    /**
     * Updates the segmented buttons (Artists, Albums, Folders) theming dynamically
     * to guarantee consistency across all themes and dark/light modes.
     * The active button uses surfaceContainerHigh (subtle, non-bright) with primary stroke and text,
     * and shows a sort icon immediately following the label text.
     */
    fun updateSegmentedButtonsTheming() {
        val density = activity.resources.displayMetrics.density
        val surfaceContainerHigh = ThemeColors.getSurfaceContainerHigh(activity)
        val surfaceContainer = ThemeColors.getSurfaceContainer(activity)
        val primary = ThemeColors.getPrimary(activity)
        val onSurfaceVariant = ThemeColors.getOnSurfaceVariant(activity)
        val outlineVariant = ThemeColors.getOutlineVariant(activity)
        val highlight = ThemeColors.getColorControlHighlight(activity)

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_selected),
            intArrayOf()
        )
        val bgColors = intArrayOf(surfaceContainerHigh, surfaceContainerHigh, surfaceContainer)
        val bgTintList = ColorStateList(states, bgColors)

        val textColors = intArrayOf(primary, primary, onSurfaceVariant)
        val textColorList = ColorStateList(states, textColors)

        val strokeColors = intArrayOf(primary, primary, outlineVariant)
        val strokeColorList = ColorStateList(states, strokeColors)

        val checkedId = binding.filterToggleGroup.checkedButtonId

        listOf(binding.btnFilterArtists, binding.btnFilterAlbums, binding.btnFilterFolders).forEach { btn ->
            btn.backgroundTintList = bgTintList
            btn.setTextColor(textColorList)
            btn.iconTint = textColorList
            btn.strokeColor = strokeColorList
            val isChecked = (btn.id == checkedId)
            btn.strokeWidth = ((if (isChecked) 1.5f else 1f) * density).toInt()
            btn.rippleColor = ColorStateList.valueOf(highlight)
            btn.icon = null
        }

        binding.btnBrowseSort.setCardBackgroundColor(surfaceContainer)
        binding.btnBrowseSort.strokeColor = outlineVariant
        binding.btnBrowseSort.strokeWidth = (1f * density).toInt()
        binding.browseSortIcon.setColorFilter(primary)
        binding.btnBrowseSort.rippleColor = ColorStateList.valueOf(highlight)
    }

    /**
     * Returns the name/path of the folder currently being browsed, if any.
     */
    fun getActiveBrowsingFolder(): String? {
        if (currentFolderNavigationPath.isNotEmpty()) {
            return currentFolderNavigationPath.last()
        }
        if (currentFilterMode == LibraryFilterMode.FOLDER && !selectedFilterValue.isNullOrEmpty()) {
            return selectedFilterValue
        }
        if (activeOngoingFilterMode == LibraryFilterMode.FOLDER && !selectedFilterValue.isNullOrEmpty()) {
            return selectedFilterValue
        }
        return null
    }

    fun handleBackPressed(): Boolean {
        if (!isFilterMenuOpen) return false

        when {
            isAllTracksDrillDown -> {
                isAllTracksDrillDown = false
                updateAllTracksCardUI(isActive = false)
                binding.browseByContainer.visibility = View.VISIBLE
                binding.drawerFilterInfoText.visibility = View.VISIBLE
                updateFilterSubList()
                return true
            }
            currentArtistDrillDown != null -> {
                currentArtistDrillDown = null
                updateFilterSubList()
                return true
            }
            currentAlbumDrillDown != null -> {
                currentAlbumDrillDown = null
                updateFilterSubList()
                return true
            }
            currentFolderNavigationPath.isNotEmpty() && binding.filterToggleGroup.checkedButtonId == R.id.btn_filter_folders -> {
                currentFolderNavigationPath.removeAt(currentFolderNavigationPath.lastIndex)
                updateFilterSubList()
                return true
            }
            else -> {
                closeLeftMenu()
                return true
            }
        }
    }
}
