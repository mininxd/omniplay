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
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.data.MusicScanner
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.AudioTrackInfo
import java.util.Locale

class MainActivity : AppCompatActivity(), PlaybackService.PlaybackListener {

    companion object {
        private const val PREFS_NAME = "omniplay_prefs"
        private const val KEY_MUSIC_FOLDER_URI = "key_music_folder_uri"
        private const val KEY_SHOW_ALBUM_ART_IN_PLAYLIST = "key_show_album_art_in_playlist"
    }

    private lateinit var binding: ActivityMainBinding
    private var playbackService: PlaybackService? = null
    private var isBound = false
    private var isUserTrackingSlider = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private val musicScanner by lazy { MusicScanner(this) }
    private var scannedSongs = listOf<Song>()
    private var songAdapter: SongAdapter? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as PlaybackService.LocalBinder
            playbackService = binder.getService()
            isBound = true
            playbackService?.addListener(this@MainActivity)

            if (scannedSongs.isNotEmpty()) {
                if (playbackService?.currentSong != null) {
                    playbackService?.refreshQueue(scannedSongs)
                } else if (playbackService?.queue.isNullOrEmpty()) {
                    playbackService?.setSongQueue(scannedSongs, startIndex = 0, startPlaying = false)
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

            loadMusicFromFolder(treeUri, isUserInitiated = true)
        } else {
            val savedFolderUri = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_MUSIC_FOLDER_URI, null)
            if (savedFolderUri != null) {
                loadMusicFromFolder(Uri.parse(savedFolderUri), isUserInitiated = false)
            } else {
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
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBackPressHandler()
        setupDefaultView()
        setupInWindowPlaylistPanel()
        setupBottomSwipeGesture()
        setupAlbumArtSwipeGesture()
        setupListeners()
        bindPlaybackService()
        checkAndRequestPermissions()
    }

    /**
     * Closes playlist panel when pressing back button instead of exiting the app.
     */
    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
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
        if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
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
        binding.artistNameText.text = ""
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
    }

    /**
     * Configures the in-window persistent sliding panel for the playlist.
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
        val isShowArt = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)

        songAdapter = SongAdapter(showAlbumArt = isShowArt) { song, index ->
            playbackService?.let { service ->
                if (service.queue.isEmpty() && scannedSongs.isNotEmpty()) {
                    service.setSongQueue(scannedSongs, startIndex = index, startPlaying = true)
                } else {
                    service.playSongFromPlaylist(song, index)
                }
            }
        }

        binding.songsRecyclerView.adapter = songAdapter
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(this)

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

            val maxTravel = binding.playlistSlidingPanel.top.toFloat().takeIf { it > 0f }
                ?: (binding.root.height - bottomSheetBehavior.peekHeight).toFloat().coerceAtLeast(1f)

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
                        // Clamp translation between -maxTravel (fully expanded) and 0f (collapsed)
                        val targetTranslation = deltaTotalY.coerceIn(-maxTravel, 0f)
                        binding.playlistSlidingPanel.translationY = targetTranslation
                        val progress = (-targetTranslation / maxTravel).coerceIn(0f, 1f)
                        binding.ivChevron.rotation = progress * 180f
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
                                binding.ivChevron.rotation = (-v / maxTravel).coerceIn(0f, 1f) * 180f
                            }
                            addListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    val panel = binding.playlistSlidingPanel
                                    if (shouldExpand) {
                                        val targetTop = bottomSheetBehavior.expandedOffset
                                        val offset = targetTop - panel.top
                                        panel.offsetTopAndBottom(offset)
                                        panel.translationY = 0f
                                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                                        binding.ivChevron.rotation = 180f
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
        var isDragging = false
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
                    binding.peekArtistText.text = peekSong.artist

                    val cached = AlbumArtLoader.getCachedAlbumArt(peekSong.id)
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
                    startX = event.rawX
                    startY = event.rawY
                    isDragging = false
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
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    velocityTracker?.addMovement(event)
                    val deltaX = event.rawX - startX
                    val deltaY = event.rawY - startY

                    if (!isDragging) {
                        val minSlop = (touchSlop * 0.35f).coerceAtLeast(8f)
                        if (Math.abs(deltaX) > minSlop && Math.abs(deltaX) > Math.abs(deltaY) * 0.7f) {
                            isDragging = true
                            v.parent?.requestDisallowInterceptTouchEvent(true)
                        }
                    }

                    if (isDragging) {
                        val cardWidth = v.width.toFloat().coerceAtLeast(1f)
                        val dir = if (deltaX < 0) -1 else 1
                        updatePeekCard(dir)

                        // If no song in that direction, apply rubber band resistance
                        val effectiveDeltaX = if (currentPeekSong == null) deltaX * 0.25f else deltaX
                        val rotationDeg = (effectiveDeltaX / cardWidth) * 12f
                        v.translationX = effectiveDeltaX
                        v.rotation = rotationDeg

                        // Reveal peek card behind with swift, responsive scaling
                        if (currentPeekSong != null) {
                            val progress = (Math.abs(effectiveDeltaX) / (cardWidth * 0.35f)).coerceIn(0f, 1f)
                            binding.peekAlbumArtCard.scaleX = 0.92f + 0.08f * progress
                            binding.peekAlbumArtCard.scaleY = 0.92f + 0.08f * progress
                            binding.peekAlbumArtCard.alpha = 0.5f + 0.5f * progress
                        }
                    }
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    velocityTracker?.addMovement(event)
                    v.parent?.requestDisallowInterceptTouchEvent(false)

                    if (isDragging) {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val xVel = velocityTracker?.xVelocity ?: 0f
                        val currentX = v.translationX
                        val cardWidth = v.width.toFloat().coerceAtLeast(1f)
                        val threshold = cardWidth * 0.18f

                        val commitNext = currentPeekSong != null && peekDirection == -1 &&
                                ((xVel < -300f) || (currentX < -threshold && xVel < 200f))
                        val commitPrev = currentPeekSong != null && peekDirection == 1 &&
                                ((xVel > 300f) || (currentX > threshold && xVel > -200f))

                        val targetSong = currentPeekSong
                        if (commitNext && targetSong != null) {
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
                                var isCanceled = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanceled = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanceled) return
                                        val songBefore = playbackService?.currentSong
                                        playbackService?.skipNext(forceNext = true)
                                        val songAfter = playbackService?.currentSong

                                        if (songAfter != null && songAfter.id != songBefore?.id) {
                                            binding.peekAlbumArtImage.drawable?.let {
                                                binding.albumArtImage.setImageDrawable(it)
                                            }
                                        } else {
                                            // Fallback: restore current song artwork if track didn't change
                                            val current = songBefore ?: songAfter
                                            if (current != null) {
                                                val cached = AlbumArtLoader.getCachedAlbumArt(current.id)
                                                if (cached != null) {
                                                    binding.albumArtImage.setImageBitmap(cached)
                                                } else {
                                                    binding.albumArtImage.setImageResource(R.drawable.default_album_art)
                                                }
                                            }
                                        }

                                        v.translationX = 0f
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
                        } else if (commitPrev && targetSong != null) {
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
                                var isCanceled = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanceled = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanceled) return
                                        val songBefore = playbackService?.currentSong
                                        playbackService?.skipPrevious(forcePrevious = true)
                                        val songAfter = playbackService?.currentSong

                                        if (songAfter != null && songAfter.id != songBefore?.id) {
                                            binding.peekAlbumArtImage.drawable?.let {
                                                binding.albumArtImage.setImageDrawable(it)
                                            }
                                        } else {
                                            // Fallback: restore current song artwork if track didn't change
                                            val current = songBefore ?: songAfter
                                            if (current != null) {
                                                val cached = AlbumArtLoader.getCachedAlbumArt(current.id)
                                                if (cached != null) {
                                                    binding.albumArtImage.setImageBitmap(cached)
                                                } else {
                                                    binding.albumArtImage.setImageResource(R.drawable.default_album_art)
                                                }
                                            }
                                        }

                                        v.translationX = 0f
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
                            // User dragged back or didn't cross threshold -> smoothly snap back!
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
                                var isCanceled = false
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationCancel(animation: Animator) {
                                        isCanceled = true
                                    }
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isCanceled) return
                                        v.translationX = 0f
                                        v.rotation = 0f
                                        binding.peekAlbumArtCard.visibility = View.INVISIBLE
                                        currentPeekSong = null
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        }
                        isDragging = false
                    } else if (event.actionMasked == MotionEvent.ACTION_UP) {
                        v.performClick()
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
            // Bright album art: use dark text
            binding.peekLabelText.setTextColor(Color.parseColor("#1E3A8A"))
            binding.peekLabelText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#80FFFFFF"))

            binding.peekTitleText.setTextColor(Color.parseColor("#111827"))
            binding.peekTitleText.setShadowLayer(4f, 0f, 1f, Color.parseColor("#99FFFFFF"))

            binding.peekArtistText.setTextColor(Color.parseColor("#374151"))
            binding.peekArtistText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#99FFFFFF"))
        } else {
            // Dark album art: use white text
            binding.peekLabelText.setTextColor(ContextCompat.getColor(this, R.color.primary_accent))
            binding.peekLabelText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#99000000"))

            binding.peekTitleText.setTextColor(Color.parseColor("#FFFFFF"))
            binding.peekTitleText.setShadowLayer(4f, 0f, 1f, Color.parseColor("#B3000000"))

            binding.peekArtistText.setTextColor(Color.parseColor("#D1D5DB"))
            binding.peekArtistText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#B3000000"))
        }
    }

    private fun setupListeners() {
        // Menu button (Hamburger)
        binding.btnMenu.setOnClickListener { view ->
            showOptionsMenu(view)
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
                playbackService?.skipNext(forceNext = true)
            }
        }

        // Previous
        binding.btnPrevious.setOnClickListener {
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

            override fun onProgressChanged(progressMs: Long, fromUser: Boolean) {
                if (fromUser) {
                    binding.currentTimeText.text = Song.formatTime(progressMs)
                }
            }

            override fun onStopTracking(progressMs: Long) {
                isUserTrackingSlider = false
                playbackService?.seekTo(progressMs.toInt())
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
            Toast.makeText(this, "Failed to open folder picker: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMusicFromFolder(treeUri: Uri, isUserInitiated: Boolean = false, isRescan: Boolean = false) {
        lifecycleScope.launch {
            if (isUserInitiated) {
                Toast.makeText(
                    this@MainActivity,
                    if (isRescan) "Rescanning music folder..." else "Scanning music folder...",
                    Toast.LENGTH_SHORT
                ).show()
            }

            val songs = musicScanner.scanFolder(treeUri)
            updateSongList(songs)

            if (isUserInitiated) {
                val message = if (songs.isNotEmpty()) {
                    "Scan complete: ${songs.size} songs found"
                } else {
                    "No songs found in selected folder"
                }
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun rescanMusic() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedFolderUri = prefs.getString(KEY_MUSIC_FOLDER_URI, null)

        if (savedFolderUri != null) {
            loadMusicFromFolder(Uri.parse(savedFolderUri), isUserInitiated = true, isRescan = true)
        } else {
            Toast.makeText(this, "Select a music folder to scan", Toast.LENGTH_SHORT).show()
            openFolderPicker()
        }
    }

    private fun updateSongList(songs: List<Song>) {
        scannedSongs = songs
        songAdapter?.setSongs(songs)

        if (songs.isNotEmpty()) {
            binding.songCountText.text = "${songs.size} songs"
            binding.emptyStateLayout.visibility = View.GONE
            binding.songsRecyclerView.visibility = View.VISIBLE

            if (isBound && playbackService != null) {
                playbackService?.refreshQueue(songs)
            }
        } else {
            binding.songCountText.text = "0 songs"
            binding.emptyStateLayout.visibility = View.VISIBLE
            binding.songsRecyclerView.visibility = View.GONE

            if (isBound && playbackService != null) {
                playbackService?.refreshQueue(emptyList())
            } else {
                setupDefaultView()
            }
        }
    }

    private fun showOptionsMenu(anchor: View) {
        val popup = PopupMenu(this, anchor, Gravity.END)
        popup.menuInflater.inflate(R.menu.main_menu, popup.menu)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isShowArt = prefs.getBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, true)
        popup.menu.findItem(R.id.action_show_album_art)?.isChecked = isShowArt

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_show_album_art -> {
                    val newState = !item.isChecked
                    item.isChecked = newState
                    prefs.edit().putBoolean(KEY_SHOW_ALBUM_ART_IN_PLAYLIST, newState).apply()
                    songAdapter?.setShowAlbumArt(newState)
                    true
                }
                R.id.action_select_folder -> {
                    openFolderPicker()
                    true
                }
                R.id.action_rescan -> {
                    rescanMusic()
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

    private fun showTrackDetailsDialog() {
        val song = playbackService?.currentSong
        if (song == null) {
            Toast.makeText(this, "No track currently playing", Toast.LENGTH_SHORT).show()
            return
        }

        val info = playbackService?.currentAudioInfo
        val format = info?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val isHiRes = info?.isHiRes ?: song.isHiRes
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
            format.startsWith("DSD") && isBitPerfect -> "Bit-Perfect DoP Direct Native Stream (No decimation, 1:1 DSD)"
            format.startsWith("DSD") -> "64-tap Blackman-Nuttall Windowed-Sinc FIR Filter -> Studio 32-bit Float PCM"
            isHiRes && isBitPerfect -> "Direct 1:1 Bit-Exact Stream (Lossless Pass-Through)"
            isHiRes -> "Native 32-bit Float High-Res Audio Engine (Lossless, Non-Truncating)"
            else -> "Native 32-bit Float Audio Engine"
        }

        val outputMode = if (isBitPerfect) {
            "Bit-Perfect Direct HAL (Bypassing Android OS Mixer & Resampler)"
        } else {
            "Android AudioTrack (High-Res 32-bit Float Engine)"
        }

        val bitPerfectStatus = if (isBitPerfect) {
            "ACTIVE (1:1 Bit-Exact Master Audio)"
        } else {
            "Direct High-Res Float (32-bit PCM)"
        }

        val details = """
            ─── AUDIO INPUT (SOURCE) ───
            Source Format: $format ${if (isHiRes) "(Hi-Res Audio)" else ""}
            Resolution: $inputResolution
            Bitrate: $inputBitrate
            Channels: $inputChannels

            ─── PROCESSING PIPELINE ───
            Audio Engine: 32-bit Floating Point High-Res PCM
            Signal Processing: $pipelineProcessing
            Software Resampling: ${if (isBitPerfect) "Bypassed (Zero Resampling)" else "Direct Float"}
            Hardware Acceleration: Enabled (Zero-Copy Buffer Queue)

            ─── AUDIO OUTPUT (HARDWARE) ───
            Active Device: $outputDevice
            Output Mode: $outputMode
            Bit-Perfect Status: $bitPerfectStatus
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
            
            Supports: MP3, WAV, FLAC, AAC, M4A, OGG, OPUS, DSD (DSF/DFF), and more.
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
    override fun onTrackChanged(song: Song?) {
        runOnUiThread {
            if (!::binding.isInitialized) return@runOnUiThread
            if (song == null) {
                setupDefaultView()
                songAdapter?.setCurrentPlayingSongId(-1L)
                return@runOnUiThread
            }

            binding.songTitleText.text = song.title
            binding.artistNameText.text = song.artist
            binding.albumNameText.text = song.album
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

            val cachedArt = AlbumArtLoader.getCachedAlbumArt(song.id)
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

    private fun updateAudioBadges(song: Song?, audioInfo: AudioTrackInfo?) {
        if (song == null) {
            binding.audioBadgeContainer.visibility = View.GONE
            return
        }

        val format = audioInfo?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val quality = audioInfo?.formatQualityString()?.takeIf { it.isNotEmpty() } ?: song.audioQuality
        val isHiRes = audioInfo?.isHiRes ?: song.isHiRes

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
            if (!isUserTrackingSlider && binding.playbackSlider.isEnabled) {
                binding.playbackSlider.setProgress(currentPositionMs.toLong())
                binding.currentTimeText.text = Song.formatTime(currentPositionMs.toLong())
                binding.totalTimeText.text = Song.formatTime(totalDurationMs.toLong())
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
        super.onDestroy()
    }
}
