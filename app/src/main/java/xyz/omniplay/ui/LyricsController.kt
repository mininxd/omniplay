package xyz.omniplay.ui

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.lyrics.Lyrics
import xyz.omniplay.lyrics.LyricsResult
import xyz.omniplay.lyrics.LrcLibClient
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import kotlin.math.abs

class LyricsController(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding,
    private val getPlaybackService: () -> PlaybackService?
) {

    private var lyricAdapter: LyricAdapter? = null
    private var fullScreenLyricAdapter: LyricAdapter? = null
    private var currentLyrics: Lyrics? = null
    private var lastOnlineLyricsSongId: Long? = null

    var isLyricsShowing: Boolean = false
        private set

    var isFullScreenLyricsShowing: Boolean = false
        private set

    val isAnyLyricsShowing: Boolean
        get() = isLyricsShowing || isFullScreenLyricsShowing

    private var isUserScrollingLyrics = false
    private val lyricsScrollResetHandler = Handler(Looper.getMainLooper())
    private val resetUserScrollingRunnable = Runnable { isUserScrollingLyrics = false }

    private var isUserScrollingFullScreenLyrics = false
    private val fullScreenScrollResetHandler = Handler(Looper.getMainLooper())
    private val resetUserFullScreenScrollingRunnable = Runnable { isUserScrollingFullScreenLyrics = false }

    private var lyricsFetchJob: Job? = null

    private val prefs by lazy {
        activity.getSharedPreferences("lyrics_prefs", Context.MODE_PRIVATE)
    }

    /** Fullscreen font size in SP (default 48sp, 2x base size, pinch to zoom). */
    var baseFontSizeSp: Float = 48f
        private set

    private lateinit var scaleGestureDetector: ScaleGestureDetector

    private fun dpToPx(dp: Int): Int = (dp * activity.resources.displayMetrics.density).toInt()

    private fun applyFullscreenFontSize(sizeSp: Float) {
        fullScreenLyricAdapter?.baseFontSizeSp = sizeSp
        binding.fullscreenLyricsPlainText.textSize = sizeSp

        // Dynamically update visible ViewHolders for 120 FPS live preview during pinch
        val rv = binding.fullscreenLyricsRecyclerView
        for (i in 0 until rv.childCount) {
            val child = rv.getChildAt(i)
            val vh = rv.getChildViewHolder(child) as? LyricAdapter.LyricViewHolder
            vh?.updateTextSize(sizeSp)
        }
    }

    fun setup(onSeek: (Long) -> Unit) {
        baseFontSizeSp = prefs.getFloat("fullscreen_lyrics_font_size_sp", 48f)
        binding.fullscreenLyricsPlainText.textSize = baseFontSizeSp

        scaleGestureDetector = ScaleGestureDetector(activity, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val scaleFactor = detector.scaleFactor
                if (scaleFactor.isNaN() || scaleFactor.isInfinite()) return false

                val newSize = (baseFontSizeSp * scaleFactor).coerceIn(24f, 72f)
                if (abs(newSize - baseFontSizeSp) > 0.25f) {
                    baseFontSizeSp = newSize
                    applyFullscreenFontSize(baseFontSizeSp)
                }
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                prefs.edit().putFloat("fullscreen_lyrics_font_size_sp", baseFontSizeSp).apply()
                fullScreenLyricAdapter?.notifyDataSetChanged()
            }
        })

        lyricAdapter = LyricAdapter(isFullScreen = false) { line ->
            val service = getPlaybackService()
            service?.seekTo(line.timeMs.toInt())
            binding.playbackSlider.setProgress(line.timeMs)
            binding.currentTimeText.text = Song.formatTime(line.timeMs)
            binding.fullscreenPlaybackSlider.setProgress(line.timeMs)
            binding.fullscreenCurrentTimeText.text = Song.formatTime(line.timeMs)
            isUserScrollingLyrics = false
            try {
                binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            } catch (ignored: Exception) {}
            onSeek(line.timeMs)
        }
        binding.lyricsRecyclerView.layoutManager = LinearLayoutManager(activity)
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

        // Fullscreen Lyrics Setup
        fullScreenLyricAdapter = LyricAdapter(isFullScreen = true) { line ->
            val service = getPlaybackService()
            service?.seekTo(line.timeMs.toInt())
            binding.playbackSlider.setProgress(line.timeMs)
            binding.currentTimeText.text = Song.formatTime(line.timeMs)
            binding.fullscreenPlaybackSlider.setProgress(line.timeMs)
            binding.fullscreenCurrentTimeText.text = Song.formatTime(line.timeMs)
            isUserScrollingFullScreenLyrics = false
            try {
                binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            } catch (ignored: Exception) {}
            onSeek(line.timeMs)
        }.apply {
            baseFontSizeSp = this@LyricsController.baseFontSizeSp
        }
        binding.fullscreenLyricsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.fullscreenLyricsRecyclerView.adapter = fullScreenLyricAdapter

        binding.fullscreenLyricsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isUserScrollingFullScreenLyrics = true
                    fullScreenScrollResetHandler.removeCallbacks(resetUserFullScreenScrollingRunnable)
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE && isUserScrollingFullScreenLyrics) {
                    fullScreenScrollResetHandler.removeCallbacks(resetUserFullScreenScrollingRunnable)
                    fullScreenScrollResetHandler.postDelayed(resetUserFullScreenScrollingRunnable, 3500L)
                }
            }
        })

        // Intercept 2-finger pinch gesture to adjust lyrics font size smoothly
        binding.fullscreenLyricsRecyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                scaleGestureDetector.onTouchEvent(e)
                if (scaleGestureDetector.isInProgress || e.pointerCount >= 2) {
                    isUserScrollingFullScreenLyrics = false
                    return true
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                scaleGestureDetector.onTouchEvent(e)
            }
        })

        binding.fullscreenLyricsPlainScroll.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            scaleGestureDetector.isInProgress || event.pointerCount >= 2
        }

        binding.btnCloseLyrics.setOnClickListener {
            closeLyricsCard()
        }

        binding.btnOpenLyrics.setOnClickListener {
            openFullScreenLyrics()
        }

        binding.btnCloseFullscreenLyrics.setOnClickListener {
            closeFullScreenLyrics()
        }

        binding.btnLyricsRetry.setOnClickListener {
            loadLyricsForCurrentSong(forceRefresh = true)
        }

        binding.fullscreenBtnLyricsRetry.setOnClickListener {
            loadLyricsForCurrentSong(forceRefresh = true)
        }
    }

    fun openLyricsCard() {
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
                getPlaybackService()?.let { service ->
                    updateLyricsProgress(service.getCurrentPosition().toLong())
                }
            }
            .start()
    }

    fun closeLyricsCard() {
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

    fun openFullScreenLyrics() {
        if (isFullScreenLyricsShowing) return
        val song = getPlaybackService()?.currentSong
        if (song != null) {
            binding.fullscreenLyricsSongTitle.text = song.title
            binding.fullscreenLyricsArtistName.text = song.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) }
        }
        binding.fullscreenLyricsSourceBadge.text = binding.lyricsSourceBadge.text

        isFullScreenLyricsShowing = true
        binding.fullscreenLyricsContainer.apply {
            visibility = View.VISIBLE
            alpha = 0f
            val startY = dpToPx(160).toFloat()
            translationY = startY
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(220L)
                .setInterpolator(DecelerateInterpolator())
                .withEndAction {
                    getPlaybackService()?.let { service ->
                        updateLyricsProgress(service.getCurrentPosition().toLong())
                    }
                }
                .start()
        }

        val curr = currentLyrics
        if (curr != null) {
            renderLyricsResult(LyricsResult.Success(curr, isOffline = curr.isOffline))
        } else {
            prepareLyricsForDisplay()
        }
    }


    fun closeFullScreenLyrics() {
        if (!isFullScreenLyricsShowing) return
        binding.fullscreenLyricsContainer.animate()
            .alpha(0f)
            .translationY(dpToPx(160).toFloat())
            .setDuration(200L)
            .setInterpolator(DecelerateInterpolator())
            .withEndAction {
                binding.fullscreenLyricsContainer.visibility = View.GONE
                binding.fullscreenLyricsContainer.translationY = 0f
                binding.fullscreenLyricsContainer.alpha = 1f
                isFullScreenLyricsShowing = false
            }
            .start()
    }

    fun prepareLyricsForDisplay() {
        val song = getPlaybackService()?.currentSong ?: return
        val lyrics = currentLyrics ?: LrcLibClient.getCachedLyrics(activity, song)?.also {
            currentLyrics = it
        }

        if (lyrics != null && lyrics.songId == song.id) {
            renderLyricsResult(LyricsResult.Success(lyrics, isOffline = lyrics.isOffline))
            if (!lyrics.hasSynced && !lyrics.isInstrumental && lastOnlineLyricsSongId != song.id) {
                loadLyricsForSong(song, forceRefresh = false)
            }
        } else {
            showLyricsLoading()
            loadLyricsForSong(song, forceRefresh = false)
        }
    }

    fun loadLyricsForCurrentSong(forceRefresh: Boolean = false) {
        val song = getPlaybackService()?.currentSong ?: return
        loadLyricsForSong(song, forceRefresh)
    }

    fun loadLyricsForSong(song: Song, forceRefresh: Boolean = false) {
        lyricsFetchJob?.cancel()

        val isSyncedOrInstrumental = currentLyrics?.let { it.hasSynced || it.isInstrumental } == true
        val alreadyFetchedOnline = (lastOnlineLyricsSongId == song.id)

        val curr = currentLyrics
        if (curr != null && curr.songId == song.id && !forceRefresh && (isSyncedOrInstrumental || alreadyFetchedOnline)) {
            if (isLyricsShowing || isFullScreenLyricsShowing) {
                renderLyricsResult(LyricsResult.Success(curr, isOffline = curr.isOffline))
            }
            return
        }

        if ((isLyricsShowing || isFullScreenLyricsShowing) && (currentLyrics == null || forceRefresh)) {
            showLyricsLoading()
        }

        lastOnlineLyricsSongId = song.id
        lyricsFetchJob = activity.lifecycleScope.launch {
            val result = LrcLibClient.getLyrics(activity, song, forceRefresh)
            if (getPlaybackService()?.currentSong?.id == song.id) {
                when (result) {
                    is LyricsResult.Success -> {
                        currentLyrics = result.lyrics
                        if (isLyricsShowing || isFullScreenLyricsShowing) {
                            renderLyricsResult(result)
                        }
                    }
                    is LyricsResult.NotFound, is LyricsResult.Error -> {
                        if (currentLyrics == null || forceRefresh) {
                            currentLyrics = null
                            if (isLyricsShowing || isFullScreenLyricsShowing) {
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
        binding.lyricsRecyclerView.visibility = View.GONE
        binding.lyricsPlainScroll.visibility = View.GONE
        binding.lyricsStatusLayout.visibility = View.VISIBLE
        binding.lyricsProgressBar.visibility = View.VISIBLE
        binding.lyricsStatusIcon.visibility = View.GONE
        binding.lyricsStatusText.text = "Loading lyrics..."
        binding.lyricsStatusSubtext.visibility = View.GONE
        binding.btnLyricsRetry.visibility = View.GONE

        binding.fullscreenLyricsRecyclerView.visibility = View.GONE
        binding.fullscreenLyricsPlainScroll.visibility = View.GONE
        binding.fullscreenLyricsStatusLayout.visibility = View.VISIBLE
        binding.fullscreenLyricsProgressBar.visibility = View.VISIBLE
        binding.fullscreenLyricsStatusIcon.visibility = View.GONE
        binding.fullscreenLyricsStatusText.text = "Loading lyrics..."
        binding.fullscreenLyricsStatusSubtext.visibility = View.GONE
        binding.fullscreenBtnLyricsRetry.visibility = View.GONE
    }

    private fun renderLyricsResult(result: LyricsResult) {
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

                    binding.fullscreenLyricsRecyclerView.visibility = View.GONE
                    binding.fullscreenLyricsPlainScroll.visibility = View.GONE
                    binding.fullscreenLyricsStatusLayout.visibility = View.VISIBLE
                    binding.fullscreenLyricsProgressBar.visibility = View.GONE
                    binding.fullscreenLyricsStatusIcon.visibility = View.VISIBLE
                    binding.fullscreenLyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                    binding.fullscreenLyricsStatusText.text = "Instrumental Track"
                    binding.fullscreenLyricsStatusSubtext.text = "This track contains no vocal lyrics"
                    binding.fullscreenLyricsStatusSubtext.visibility = View.VISIBLE
                    binding.fullscreenBtnLyricsRetry.visibility = View.GONE

                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                if (lyrics.hasSynced) {
                    binding.lyricsStatusLayout.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.GONE
                    binding.lyricsRecyclerView.visibility = View.VISIBLE
                    lyricAdapter?.submitLines(lyrics.syncedLyrics ?: emptyList())

                    binding.fullscreenLyricsStatusLayout.visibility = View.GONE
                    binding.fullscreenLyricsPlainScroll.visibility = View.GONE
                    binding.fullscreenLyricsRecyclerView.visibility = View.VISIBLE
                    fullScreenLyricAdapter?.submitLines(lyrics.syncedLyrics ?: emptyList())

                    getPlaybackService()?.let { service ->
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

                    binding.fullscreenLyricsStatusLayout.visibility = View.GONE
                    binding.fullscreenLyricsRecyclerView.visibility = View.GONE
                    binding.fullscreenLyricsPlainScroll.visibility = View.VISIBLE
                    binding.fullscreenLyricsPlainText.text = lyrics.plainLyrics

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

                binding.fullscreenLyricsRecyclerView.visibility = View.GONE
                binding.fullscreenLyricsPlainScroll.visibility = View.GONE
                binding.fullscreenLyricsStatusLayout.visibility = View.VISIBLE
                binding.fullscreenLyricsProgressBar.visibility = View.GONE
                binding.fullscreenLyricsStatusIcon.visibility = View.VISIBLE
                binding.fullscreenLyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                binding.fullscreenLyricsStatusText.text = "No lyrics available"
                binding.fullscreenLyricsStatusSubtext.text = "No lyric lines available for this song"
                binding.fullscreenLyricsStatusSubtext.visibility = View.VISIBLE
                binding.fullscreenBtnLyricsRetry.visibility = View.VISIBLE
                binding.fullscreenBtnLyricsRetry.text = "Search Again"
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

                binding.fullscreenLyricsRecyclerView.visibility = View.GONE
                binding.fullscreenLyricsPlainScroll.visibility = View.GONE
                binding.fullscreenLyricsStatusLayout.visibility = View.VISIBLE
                binding.fullscreenLyricsProgressBar.visibility = View.GONE
                binding.fullscreenLyricsStatusIcon.visibility = View.VISIBLE
                binding.fullscreenLyricsStatusIcon.setImageResource(R.drawable.ic_music_note)
                binding.fullscreenLyricsStatusText.text = "No lyrics found"
                binding.fullscreenLyricsStatusSubtext.text = result.message
                binding.fullscreenLyricsStatusSubtext.visibility = View.VISIBLE
                binding.fullscreenBtnLyricsRetry.visibility = View.VISIBLE
                binding.fullscreenBtnLyricsRetry.text = "Search Again"
            }
            is LyricsResult.Error -> {
                binding.lyricsRecyclerView.visibility = View.GONE
                binding.lyricsPlainScroll.visibility = View.GONE
                binding.lyricsStatusLayout.visibility = View.VISIBLE
                binding.lyricsProgressBar.visibility = View.GONE
                binding.lyricsStatusIcon.visibility = View.VISIBLE
                binding.lyricsStatusIcon.setImageResource(R.drawable.ic_info)
                val statusText = if (result.isRateLimited) "Too many requests" else "Could not load lyrics"
                binding.lyricsStatusText.text = statusText
                binding.lyricsStatusSubtext.text = result.message
                binding.lyricsStatusSubtext.visibility = View.VISIBLE
                binding.btnLyricsRetry.visibility = View.VISIBLE
                binding.btnLyricsRetry.text = "Retry"

                binding.fullscreenLyricsRecyclerView.visibility = View.GONE
                binding.fullscreenLyricsPlainScroll.visibility = View.GONE
                binding.fullscreenLyricsStatusLayout.visibility = View.VISIBLE
                binding.fullscreenLyricsProgressBar.visibility = View.GONE
                binding.fullscreenLyricsStatusIcon.visibility = View.VISIBLE
                binding.fullscreenLyricsStatusIcon.setImageResource(R.drawable.ic_info)
                binding.fullscreenLyricsStatusText.text = statusText
                binding.fullscreenLyricsStatusSubtext.text = result.message
                binding.fullscreenLyricsStatusSubtext.visibility = View.VISIBLE
                binding.fullscreenBtnLyricsRetry.visibility = View.VISIBLE
                binding.fullscreenBtnLyricsRetry.text = "Retry"
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
        binding.fullscreenLyricsSourceBadge.text = badge
    }

    fun updateLyricsProgress(currentPositionMs: Long) {
        if (!isLyricsShowing && !isFullScreenLyricsShowing) return
        val lyrics = currentLyrics ?: return
        val synced = lyrics.syncedLyrics ?: return
        if (synced.isEmpty()) return

        val activeIndex = synced.indexOfLast { it.timeMs <= currentPositionMs }

        if (isLyricsShowing) {
            lyricAdapter?.let { adapter ->
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
        }

        if (isFullScreenLyricsShowing) {
            fullScreenLyricAdapter?.let { adapter ->
                if (activeIndex != adapter.activeIndex) {
                    val changed = adapter.setActiveIndex(activeIndex)
                    if (changed && activeIndex >= 0 && !isUserScrollingFullScreenLyrics) {
                        val layoutManager = binding.fullscreenLyricsRecyclerView.layoutManager as? LinearLayoutManager
                        if (layoutManager != null && binding.fullscreenLyricsRecyclerView.height > 0) {
                            // Center active line in the RecyclerView (half screen minus ~half item height)
                            val estimatedItemHalfHeight = dpToPx(36)
                            val offset = (binding.fullscreenLyricsRecyclerView.height / 2) - estimatedItemHalfHeight
                            layoutManager.scrollToPositionWithOffset(activeIndex, offset)
                        }
                    }
                }
            }
        }
    }

    fun onTrackChanged(newSong: Song?) {
        lyricsFetchJob?.cancel()
        currentLyrics = null
        lastOnlineLyricsSongId = null
        isUserScrollingLyrics = false
        isUserScrollingFullScreenLyrics = false
        lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
        fullScreenScrollResetHandler.removeCallbacks(resetUserFullScreenScrollingRunnable)

        lyricAdapter?.submitLines(emptyList())
        lyricAdapter?.setActiveIndex(-1)
        fullScreenLyricAdapter?.submitLines(emptyList())
        fullScreenLyricAdapter?.setActiveIndex(-1)

        binding.lyricsPlainText.text = ""
        binding.lyricsSourceBadge.text = ""
        binding.fullscreenLyricsPlainText.text = ""
        binding.fullscreenLyricsSourceBadge.text = ""

        if (newSong != null) {
            binding.fullscreenLyricsSongTitle.text = newSong.title
            binding.fullscreenLyricsArtistName.text = newSong.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) }
        } else {
            binding.fullscreenLyricsSongTitle.text = ""
            binding.fullscreenLyricsArtistName.text = ""
        }

        if (newSong == null) {
            renderLyricsResult(LyricsResult.NotFound("No song is currently playing"))
            return
        }

        if (isLyricsShowing || isFullScreenLyricsShowing) {
            val cached = LrcLibClient.getCachedLyrics(activity, newSong)
            if (cached != null) {
                currentLyrics = cached
                renderLyricsResult(LyricsResult.Success(cached, isOffline = cached.isOffline))
                if (!cached.hasSynced && !cached.isInstrumental) {
                    loadLyricsForSong(newSong, forceRefresh = false)
                }
            } else {
                showLyricsLoading()
                loadLyricsForSong(newSong, forceRefresh = false)
            }
        } else {
            LrcLibClient.getCachedLyrics(activity, newSong)?.let { cached ->
                currentLyrics = cached
            }
        }
    }

    fun handleBackPressed(): Boolean {
        if (isFullScreenLyricsShowing) {
            closeFullScreenLyrics()
            return true
        }
        if (isLyricsShowing) {
            closeLyricsCard()
            return true
        }
        return false
    }

    fun onDestroy() {
        lyricsFetchJob?.cancel()
        lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
        fullScreenScrollResetHandler.removeCallbacks(resetUserFullScreenScrollingRunnable)
    }
}
