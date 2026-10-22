package xyz.omniplay.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.style.LineBackgroundSpan
import android.text.style.StyleSpan
import android.view.GestureDetector
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import android.util.TypedValue
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.lyrics.Lyrics
import xyz.omniplay.lyrics.LyricsResult
import xyz.omniplay.lyrics.LrcLibClient
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.sync.OmniSyncManager
import xyz.omniplay.sync.OmniSyncRole
import xyz.omniplay.util.ThemeColors
import kotlin.math.abs

class LyricsController(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding,
    private val getPlaybackService: () -> PlaybackService?,
    private val onQueueDrag: ((MotionEvent) -> Boolean)? = null
) {

    private var lyricAdapter: LyricAdapter? = null
    private var fullScreenLyricAdapter: LyricAdapter? = null
    private var currentLyrics: Lyrics? = null
    private var isLyricsShareSelectionMode = false
    private val lyricsShareSelectedLines = sortedSetOf<Int>()
    private var lyricsShareLastDragIndex = -1
    private var lyricsShareSelectionIsFullscreen = false
    private var lyricsShareSelectionAdapter: LyricAdapter? = null
    private var lyricsShareSelectionTextView: TextView? = null
    private var lyricsShareSelectionPlainBaseTextSizePx: Float? = null
    private var lyricsShareSelectionFontScale = 1f
    private var lastOnlineLyricsSongId: Long? = null

    var isLyricsShowing: Boolean = false
        private set

    var isFullScreenLyricsShowing: Boolean = false
        private set(value) {
            field = value
            updateKeepScreenOnState()
        }

    fun isMusicCurrentlyPlaying(): Boolean {
        val sync = OmniSyncManager.getInstance(activity)
        if (sync.currentRole == OmniSyncRole.LISTENER) {
            return sync.isStreamPlaying
        }
        return getPlaybackService()?.isPlaying() == true
    }

    fun updateKeepScreenOnState() {
        val shouldKeepOn = isFullScreenLyricsShowing && isMusicCurrentlyPlaying()
        if (shouldKeepOn) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            binding.fullscreenLyricsContainer.keepScreenOn = true
        } else {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            binding.fullscreenLyricsContainer.keepScreenOn = false
        }
    }

    fun onPlaybackStateChanged(isPlaying: Boolean) {
        updateKeepScreenOnState()
    }

    val isAnyLyricsShowing: Boolean
        get() = isLyricsShowing || isFullScreenLyricsShowing

    fun getActiveSong(): Song? {
        val syncManager = OmniSyncManager.getInstance(activity)
        if (syncManager.currentRole == OmniSyncRole.LISTENER) {
            val rawTitle = syncManager.currentTrackTitle.trim()
            if (rawTitle.isNotEmpty() && !rawTitle.equals("Waiting for stream…", ignoreCase = true)) {
                var title = rawTitle
                var artist = syncManager.currentTrackArtist.trim()
                if (artist.equals("OmniSync Stream", ignoreCase = true) || artist.equals("Host Broadcast", ignoreCase = true)) {
                    artist = ""
                }
                if (artist.isEmpty() && title.contains(" - ")) {
                    val pArtist = title.substringBefore(" - ").trim()
                    val pTitle = title.substringAfter(" - ").trim()
                    if (pArtist.isNotEmpty() && pTitle.isNotEmpty()) {
                        artist = pArtist
                        title = pTitle
                    }
                }
                val dur = syncManager.currentStreamDurationMs
                val songId = (java.util.UUID.nameUUIDFromBytes((title + artist).toByteArray()).mostSignificantBits and Long.MAX_VALUE).let { if (it == 0L) 1L else it }
                return Song(
                    id = songId,
                    title = title,
                    artist = artist,
                    album = "",
                    duration = dur,
                    contentUri = android.net.Uri.EMPTY
                )
            }
            return null
        }
        return getPlaybackService()?.currentSong
    }

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

                if (isLyricsShareSelectionMode) {
                    val newScale = (lyricsShareSelectionFontScale * scaleFactor).coerceIn(0.7f, 1.6f)
                    if (abs(newScale - lyricsShareSelectionFontScale) > 0.01f) {
                        applyLyricsShareSelectionFontScale(newScale)
                    }
                    return true
                }

                val newSize = (baseFontSizeSp * scaleFactor).coerceIn(24f, 72f)
                if (abs(newSize - baseFontSizeSp) > 0.25f) {
                    baseFontSizeSp = newSize
                    applyFullscreenFontSize(baseFontSizeSp)
                }
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                if (!isLyricsShareSelectionMode) {
                    prefs.edit().putFloat("fullscreen_lyrics_font_size_sp", baseFontSizeSp).apply()
                    fullScreenLyricAdapter?.notifyDataSetChanged()
                }
            }
        })

        lyricAdapter = LyricAdapter(
            isFullScreen = false,
            onLineClick = { line ->
                if (OmniSyncManager.getInstance(activity).currentRole != OmniSyncRole.LISTENER) {
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
            },
            onLineDoubleTap = { view ->
                openFullScreenLyrics(view)
            },
            onLineLongPress = { index ->
                if (isLyricsShareSelectionMode) addLyricsShareSelection(index)
                else beginLyricsShareSelection(index, isFullscreen = false)
            },
            onSelectionLineClick = { index -> toggleLyricsShareSelection(index) },
            onSelectionLineDragStart = { index -> setLyricsShareSelectionDragAnchor(index) },
            onSelectionLineDrag = { index -> extendLyricsShareSelection(index) }
        )
        binding.lyricsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.lyricsRecyclerView.adapter = lyricAdapter
        disableLyricsChangeAnimations(binding.lyricsRecyclerView)
        setupSelectionPinchGesture(binding.lyricsRecyclerView)

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
        fullScreenLyricAdapter = LyricAdapter(
            isFullScreen = true,
            onLineClick = { line ->
                if (OmniSyncManager.getInstance(activity).currentRole != OmniSyncRole.LISTENER) {
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
                }
            },
            onLineLongPress = { index ->
                if (isLyricsShareSelectionMode) addLyricsShareSelection(index)
                else beginLyricsShareSelection(index, isFullscreen = true)
            },
            onSelectionLineClick = { index -> toggleLyricsShareSelection(index) },
            onSelectionLineDragStart = { index -> setLyricsShareSelectionDragAnchor(index) },
            onSelectionLineDrag = { index -> extendLyricsShareSelection(index) }
        ).apply {
            baseFontSizeSp = this@LyricsController.baseFontSizeSp
        }
        binding.fullscreenLyricsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.fullscreenLyricsRecyclerView.adapter = fullScreenLyricAdapter
        disableLyricsChangeAnimations(binding.fullscreenLyricsRecyclerView)

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
        setupPlainLyricsShareGesture(binding.lyricsPlainText)
        setupPlainLyricsShareGesture(binding.fullscreenLyricsPlainText)

        binding.btnCloseLyrics.setOnClickListener {
            if (isLyricsShareSelectionMode) finishLyricsShareSelectionMode()
            closeLyricsCard()
        }

        binding.btnCloseFullscreenLyrics.setOnClickListener {
            if (isLyricsShareSelectionMode && lyricsShareSelectionIsFullscreen) finishLyricsShareSelectionMode()
            closeFullScreenLyrics()
        }

        binding.btnLyricsShareNext.setOnClickListener {
            shareSelectedLyrics()
        }

        binding.btnLyricsCardShareNext.setOnClickListener {
            shareSelectedLyrics()
        }

        binding.btnLyricsRetry.setOnClickListener {
            loadLyricsForCurrentSong(forceRefresh = true)
        }

        binding.fullscreenBtnLyricsRetry.setOnClickListener {
            loadLyricsForCurrentSong(forceRefresh = true)
        }

        setupSmallLyricsCardInteractions()
        setupFullscreenControlsSwipeUp()
        setupFullscreenTopBarDismissGesture()
    }

    private fun setupPlainLyricsShareGesture(textView: TextView) {
        var selectionDragActive = false
        val detector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(event: MotionEvent) {
                val lineIndex = plainLyricsLineAt(textView, event) ?: return
                beginLyricsShareSelection(
                    lineIndex,
                    isFullscreen = textView.id == R.id.fullscreen_lyrics_plain_text,
                    plainTextView = textView
                )
                selectionDragActive = isLyricsShareSelectionMode && lyricsShareSelectionTextView === textView
                if (selectionDragActive) {
                    textView.parent?.requestDisallowInterceptTouchEvent(true)
                }
            }

            override fun onSingleTapUp(event: MotionEvent): Boolean {
                if (!isLyricsShareSelectionMode || lyricsShareSelectionTextView !== textView) {
                    return super.onSingleTapUp(event)
                }
                plainLyricsLineAt(textView, event)?.let(::toggleLyricsShareSelection)
                return true
            }

            override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
                return isLyricsShareSelectionMode && lyricsShareSelectionTextView === textView
            }
        })

        textView.setOnTouchListener { _, event ->
            val selectionTextActive = isLyricsShareSelectionMode && lyricsShareSelectionTextView === textView
            if (selectionTextActive && event.actionMasked == MotionEvent.ACTION_DOWN) {
                plainLyricsLineAt(textView, event)?.let(::setLyricsShareSelectionDragAnchor)
            }
            if (selectionTextActive) {
                scaleGestureDetector.onTouchEvent(event)
            }
            detector.onTouchEvent(event)

            val isPinching = selectionTextActive &&
                (scaleGestureDetector.isInProgress || event.pointerCount >= 2)
            if (isPinching) {
                selectionDragActive = false
                return@setOnTouchListener true
            }

            if (selectionTextActive && selectionDragActive && event.actionMasked == MotionEvent.ACTION_MOVE) {
                plainLyricsLineAt(textView, event)?.let(::extendLyricsShareSelection)
            }

            val consumeSelectionDrag = selectionDragActive
            if (event.actionMasked == MotionEvent.ACTION_UP || event.actionMasked == MotionEvent.ACTION_CANCEL) {
                selectionDragActive = false
            }
            consumeSelectionDrag
        }
    }

    private fun setupSelectionPinchGesture(recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, event: MotionEvent): Boolean {
                if (!isLyricsShareSelectionMode || lyricsShareSelectionIsFullscreen) return false
                scaleGestureDetector.onTouchEvent(event)
                if (scaleGestureDetector.isInProgress || event.pointerCount >= 2) {
                    isUserScrollingLyrics = false
                    return true
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, event: MotionEvent) {
                scaleGestureDetector.onTouchEvent(event)
            }
        })
    }

    private fun disableLyricsChangeAnimations(recyclerView: RecyclerView) {
        (recyclerView.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
    }

    private fun applyLyricsShareSelectionFontScale(scale: Float) {
        lyricsShareSelectionFontScale = scale
        lyricsShareSelectionAdapter?.let { adapter ->
            adapter.setShareSelectionFontScale(scale)
            val recyclerView = if (lyricsShareSelectionIsFullscreen) {
                binding.fullscreenLyricsRecyclerView
            } else {
                binding.lyricsRecyclerView
            }
            for (index in 0 until recyclerView.childCount) {
                val child = recyclerView.getChildAt(index)
                val holder = recyclerView.getChildViewHolder(child) as? LyricAdapter.LyricViewHolder
                if (holder != null) adapter.updateSelectionTextSize(holder)
            }
        }
        lyricsShareSelectionTextView?.let { textView ->
            val baseSizePx = lyricsShareSelectionPlainBaseTextSizePx ?: textView.textSize
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, baseSizePx * scale)
            renderPlainLyricsSelection(textView)
        }
    }

    private fun plainLyricsLineAt(textView: TextView, event: MotionEvent): Int? {
        val textLayout = textView.layout ?: return null
        val text = textView.text ?: return null
        if (text.isBlank() || textLayout.height <= 0) return null

        val y = (event.y + textView.scrollY).toInt().coerceIn(0, textLayout.height - 1)
        val visualLine = textLayout.getLineForVertical(y)
        val offset = textLayout.getOffsetForHorizontal(visualLine, event.x).coerceIn(0, text.length)
        return text.subSequence(0, offset).count { it == '\n' }
    }

    private fun getLyricsShareLines(): List<String> {
        val lyrics = currentLyrics ?: return emptyList()
        return when {
            !lyrics.syncedLyrics.isNullOrEmpty() -> lyrics.syncedLyrics.map { it.text }
            !lyrics.plainLyrics.isNullOrBlank() -> lyrics.plainLyrics.lines().map { it.trimEnd() }
            else -> emptyList()
        }
    }

    private fun beginLyricsShareSelection(
        anchorIndex: Int,
        isFullscreen: Boolean,
        plainTextView: TextView? = null
    ) {
        if (isLyricsShareSelectionMode) {
            addLyricsShareSelection(anchorIndex)
            return
        }
        val lines = getLyricsShareLines()
        if (lines.isEmpty()) return
        val safeAnchor = anchorIndex.coerceIn(lines.indices)
        isLyricsShareSelectionMode = true
        lyricsShareSelectedLines.clear()
        lyricsShareSelectedLines.add(safeAnchor)
        lyricsShareLastDragIndex = safeAnchor
        lyricsShareSelectionFontScale = 1f
        lyricsShareSelectionIsFullscreen = isFullscreen
        lyricsShareSelectionTextView = plainTextView
        lyricsShareSelectionPlainBaseTextSizePx = plainTextView?.textSize
        lyricsShareSelectionAdapter = if (plainTextView == null) {
            if (isFullscreen) fullScreenLyricAdapter else lyricAdapter
        } else {
            null
        }

        lyricsShareSelectionAdapter?.setShareSelectionMode(true, lyricsShareSelectedLines)
        if (plainTextView != null) renderPlainLyricsSelection(plainTextView)

        if (isFullscreen) {
            binding.lyricsShareCardFooter.visibility = View.GONE
            binding.lyricsShareFullscreenFooter.visibility = View.VISIBLE
            setLyricsContentBottomConstraint(R.id.lyrics_share_fullscreen_footer)
            animateSelectionControls(listOf(binding.fullscreenLyricsControls), hide = true)
        } else {
            binding.lyricsShareFullscreenFooter.visibility = View.GONE
            binding.lyricsShareCardFooter.visibility = View.VISIBLE
            setLyricsCardContentBottomConstraint(showFooter = true)
            animateSelectionControls(
                listOf(binding.playbackSlider, binding.currentTimeText, binding.totalTimeText, binding.controlsLayout),
                hide = true
            )
        }
    }

    private fun toggleLyricsShareSelection(index: Int) {
        val lines = getLyricsShareLines()
        if (!isLyricsShareSelectionMode || index !in lines.indices) return
        if (index in lyricsShareSelectedLines) {
            lyricsShareSelectedLines.remove(index)
            if (lyricsShareSelectedLines.isEmpty()) {
                finishLyricsShareSelectionMode()
                return
            }
        } else {
            lyricsShareSelectedLines.add(index)
        }
        lyricsShareLastDragIndex = index
        updateLyricsShareSelection()
    }

    private fun addLyricsShareSelection(index: Int) {
        val lines = getLyricsShareLines()
        if (!isLyricsShareSelectionMode || index !in lines.indices) return
        lyricsShareSelectedLines.add(index)
        lyricsShareLastDragIndex = index
        updateLyricsShareSelection()
    }

    private fun setLyricsShareSelectionDragAnchor(index: Int) {
        if (isLyricsShareSelectionMode && index in getLyricsShareLines().indices) {
            lyricsShareLastDragIndex = index
        }
    }

    private fun extendLyricsShareSelection(index: Int) {
        val lines = getLyricsShareLines()
        if (!isLyricsShareSelectionMode || index !in lines.indices) return
        val previous = lyricsShareLastDragIndex.takeIf { it in lines.indices } ?: index
        val first = minOf(previous, index)
        val last = maxOf(previous, index)
        val changed = (first..last).any { it !in lyricsShareSelectedLines }
        lyricsShareSelectedLines.addAll(first..last)
        lyricsShareLastDragIndex = index
        if (changed) updateLyricsShareSelection()
    }

    private fun updateLyricsShareSelection() {
        lyricsShareSelectionAdapter?.setShareSelectionLines(lyricsShareSelectedLines)
        lyricsShareSelectionTextView?.let(::renderPlainLyricsSelection)
        binding.root.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
    }

    private fun renderPlainLyricsSelection(textView: TextView) {
        val plainLyrics = currentLyrics?.plainLyrics ?: return
        val annotatedLyrics = SpannableString(plainLyrics)
        lyricsShareSelectedLines.forEach { lineIndex ->
            val start = plainLineStartOffset(plainLyrics, lineIndex)
            if (start >= plainLyrics.length) return@forEach
            val newline = plainLyrics.indexOf('\n', start)
            val end = if (newline >= 0) newline + 1 else plainLyrics.length
            if (end <= start) return@forEach
            annotatedLyrics.setSpan(
                RoundedLyricsSelectionSpan(
                    ThemeColors.getPrimaryContainer(activity),
                    dpToPx(12).toFloat(),
                    dpToPx(10).toFloat()
                ),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            annotatedLyrics.setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.text = annotatedLyrics
    }

    private fun plainLineStartOffset(text: String, lineIndex: Int): Int {
        if (lineIndex <= 0) return 0
        var offset = 0
        repeat(lineIndex) {
            val newline = text.indexOf('\n', offset)
            if (newline < 0) return text.length
            offset = newline + 1
        }
        return offset
    }

    private fun setLyricsContentBottomConstraint(targetId: Int) {
        listOf(
            binding.fullscreenLyricsRecyclerView,
            binding.fullscreenLyricsPlainScroll,
            binding.fullscreenLyricsStatusLayout
        ).forEach { view ->
            val params = view.layoutParams as? ConstraintLayout.LayoutParams ?: return@forEach
            params.bottomToTop = targetId
            view.layoutParams = params
        }
    }

    private fun setLyricsCardContentBottomConstraint(showFooter: Boolean) {
        listOf(binding.lyricsRecyclerView, binding.lyricsPlainScroll, binding.lyricsStatusLayout).forEach { view ->
            val params = view.layoutParams as? ConstraintLayout.LayoutParams ?: return@forEach
            params.bottomToTop = if (showFooter) R.id.lyrics_share_card_footer else -1
            params.bottomToBottom = if (showFooter) -1 else 0
            view.layoutParams = params
        }
    }

    private fun animateSelectionControls(views: List<View>, hide: Boolean) {
        val sharedParent = views.firstOrNull()?.parent as? View
        val sharedDistance = if (hide && views.all { it.parent === views.firstOrNull()?.parent } && sharedParent != null) {
            (sharedParent.height - views.minOf { it.top } + dpToPx(24)).coerceAtLeast(dpToPx(96)).toFloat()
        } else {
            null
        }
        views.forEach { view ->
            view.animate().cancel()
            if (hide) {
                val parentHeight = (view.parent as? View)?.height ?: binding.root.height
                val distance = sharedDistance
                    ?: (parentHeight - view.top + dpToPx(24)).coerceAtLeast(dpToPx(96)).toFloat()
                view.animate()
                    .translationY(distance)
                    .alpha(0f)
                    .setDuration(220L)
                    .start()
            } else {
                view.animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(220L)
                    .start()
            }
        }
    }

    private fun finishLyricsShareSelectionMode() {
        if (!isLyricsShareSelectionMode) return
        val wasFullscreen = lyricsShareSelectionIsFullscreen
        lyricsShareSelectionAdapter?.setShareSelectionMode(false)
        lyricsShareSelectionTextView?.let { textView ->
            lyricsShareSelectionPlainBaseTextSizePx?.let { baseSizePx ->
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, baseSizePx)
            }
            currentLyrics?.plainLyrics?.let { textView.text = it }
        }
        isLyricsShareSelectionMode = false
        lyricsShareSelectedLines.clear()
        lyricsShareLastDragIndex = -1
        lyricsShareSelectionAdapter = null
        lyricsShareSelectionTextView = null
        lyricsShareSelectionPlainBaseTextSizePx = null
        lyricsShareSelectionIsFullscreen = false
        lyricsShareSelectionFontScale = 1f

        if (wasFullscreen) {
            binding.lyricsShareFullscreenFooter.visibility = View.GONE
            setLyricsContentBottomConstraint(R.id.fullscreen_lyrics_controls)
            animateSelectionControls(listOf(binding.fullscreenLyricsControls), hide = false)
        } else {
            binding.lyricsShareCardFooter.visibility = View.GONE
            setLyricsCardContentBottomConstraint(showFooter = false)
            animateSelectionControls(
                listOf(binding.playbackSlider, binding.currentTimeText, binding.totalTimeText, binding.controlsLayout),
                hide = false
            )
        }
    }

    private fun shareSelectedLyrics() {
        val lyrics = currentLyrics ?: return
        val lines = getLyricsShareLines()
        if (!isLyricsShareSelectionMode || lines.isEmpty()) return
        val excerpt = lyricsShareSelectedLines
            .filter { it in lines.indices }
            .map { lines[it].trim() }
            .filter { it.isNotEmpty() }
        if (excerpt.isEmpty()) return

        val manager = activity.supportFragmentManager
        if (manager.findFragmentByTag(LyricsShareThemeBottomSheet.TAG) != null) return
        val title = lyrics.trackName.ifBlank { getActiveSong()?.title.orEmpty() }
        val artist = lyrics.artistName.ifBlank { getActiveSong()?.artist.orEmpty() }
        val sync = OmniSyncManager.getInstance(activity)
        val activeSongDuration = getActiveSong()?.duration ?: 0L
        val positionMs = if (sync.currentRole == OmniSyncRole.LISTENER) {
            sync.getListenerCurrentPosition()
        } else {
            getPlaybackService()?.getCurrentPosition()?.toLong() ?: 0L
        }
        val durationMs = if (sync.currentRole == OmniSyncRole.LISTENER) {
            sync.currentStreamDurationMs.takeIf { it > 0L } ?: activeSongDuration
        } else {
            getPlaybackService()?.getDuration()?.takeIf { it > 0 }?.toLong() ?: activeSongDuration
        }
        finishLyricsShareSelectionMode()
        LyricsShareThemeBottomSheet.newInstance(title, artist, excerpt, positionMs, durationMs)
            .show(manager, LyricsShareThemeBottomSheet.TAG)
    }

    private fun prepareFullscreenLyricsForOpen() {
        val song = getActiveSong()
        if (song != null) {
            binding.fullscreenLyricsSongTitle.text = song.title
            binding.fullscreenLyricsArtistName.text = song.artist.trim().ifEmpty {
                if (xyz.omniplay.sync.OmniSyncManager.getInstance(activity).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                    "OmniSync Stream"
                } else {
                    activity.getString(R.string.unknown_artist)
                }
            }
        }
        val currLyrics = currentLyrics
        val currentBadge = when {
            currLyrics == null -> ""
            currLyrics.source.startsWith("EMBEDDED") -> currLyrics.source.removePrefix("EMBEDDED (").removeSuffix(")")
            currLyrics.source == "LOCAL FILE" -> "LOCAL"
            currLyrics.isOffline -> "OFFLINE"
            else -> "LRCLIB"
        }
        if (currLyrics?.isOffline == true || currentBadge.equals("OFFLINE", ignoreCase = true) || currentBadge.isBlank()) {
            binding.fullscreenLyricsSourceBadge.visibility = View.GONE
            binding.fullscreenLyricsSourceBadge.text = ""
        } else {
            binding.fullscreenLyricsSourceBadge.visibility = View.VISIBLE
            binding.fullscreenLyricsSourceBadge.text = currentBadge
        }

        val curr = currentLyrics
        if (curr != null) {
            renderLyricsResult(LyricsResult.Success(curr, isOffline = curr.isOffline))
        } else {
            prepareLyricsForDisplay()
        }
    }

    private fun setupSmallLyricsCardInteractions() {
        // Double-tap on the plain lyric text to open fullscreen lyrics.
        val plainTextDoubleTapDetector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (isLyricsShareSelectionMode) return false
                openFullScreenLyrics(binding.lyricsPlainText)
                return true
            }
        })
        binding.lyricsPlainScroll.setOnTouchListener { _, event ->
            plainTextDoubleTapDetector.onTouchEvent(event)
            false // let normal scroll still work
        }
    }

    private fun setupFullscreenTopBarDismissGesture() {
        val touchSlop = ViewConfiguration.get(activity).scaledTouchSlop
        var startY = 0f
        var startX = 0f
        var isDismissDragging = false
        var dismissVelocityTracker: VelocityTracker? = null
        var dismissAnimator: ValueAnimator? = null

        // 1:1 Sliding downward gesture from fullscreen top bar/title area to dismiss
        binding.fullscreenLyricsTopBar.setOnTouchListener { v, event ->
            if (isLyricsShareSelectionMode) return@setOnTouchListener false
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dismissAnimator?.cancel()
                    startY = event.rawY
                    startX = event.rawX
                    isDismissDragging = false
                    dismissVelocityTracker?.recycle()
                    dismissVelocityTracker = VelocityTracker.obtain().apply {
                        addMovement(event)
                    }
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    dismissVelocityTracker?.addMovement(event)
                    val deltaY = event.rawY - startY
                    val deltaX = event.rawX - startX

                    if (!isDismissDragging && deltaY > touchSlop && abs(deltaY) > abs(deltaX)) {
                        isDismissDragging = true
                        v.parent?.requestDisallowInterceptTouchEvent(true)
                    }

                    if (isDismissDragging) {
                        val maxDismissTravel = dpToPx(300).toFloat()
                        val travel = deltaY.coerceAtLeast(0f)
                        val progress = (travel / maxDismissTravel).coerceIn(0f, 1f)

                        binding.fullscreenLyricsContainer.translationY = travel
                        binding.fullscreenLyricsContainer.alpha = (1f - progress * 0.4f).coerceIn(0.6f, 1f)
                        true
                    } else {
                        false
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    dismissVelocityTracker?.addMovement(event)
                    if (isDismissDragging) {
                        dismissVelocityTracker?.computeCurrentVelocity(1000)
                        val yVel = dismissVelocityTracker?.yVelocity ?: 0f
                        val maxDismissTravel = dpToPx(300).toFloat()
                        val currentTrans = binding.fullscreenLyricsContainer.translationY
                        val progress = (currentTrans / maxDismissTravel).coerceIn(0f, 1f)

                        val shouldDismiss = yVel > 600f || progress >= 0.35f
                        val containerH = binding.fullscreenLyricsContainer.height.takeIf { it > 0 } ?: dpToPx(600)
                        val targetY = if (shouldDismiss) containerH.toFloat() else 0f
                        val targetAlpha = if (shouldDismiss) 0f else 1f

                        val duration = (220 * if (shouldDismiss) (1f - progress) else progress).toLong().coerceIn(100L, 260L)

                        dismissAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
                            this.duration = duration
                            interpolator = DecelerateInterpolator()
                            val startTrans = binding.fullscreenLyricsContainer.translationY
                            val startAlpha = binding.fullscreenLyricsContainer.alpha

                            addUpdateListener { anim ->
                                val f = anim.animatedValue as Float
                                binding.fullscreenLyricsContainer.translationY = startTrans + (targetY - startTrans) * f
                                binding.fullscreenLyricsContainer.alpha = startAlpha + (targetAlpha - startAlpha) * f
                            }
                            addListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    if (shouldDismiss) {
                                        binding.fullscreenLyricsContainer.visibility = View.GONE
                                        binding.fullscreenLyricsContainer.translationY = 0f
                                        binding.fullscreenLyricsContainer.alpha = 1f
                                        isFullScreenLyricsShowing = false
                                        if (isLyricsShowing) {
                                            binding.lyricsCard.visibility = View.VISIBLE
                                            binding.lyricsCard.translationY = 0f
                                            binding.lyricsCard.alpha = 1f
                                        }
                                    } else {
                                        binding.fullscreenLyricsContainer.translationY = 0f
                                        binding.fullscreenLyricsContainer.alpha = 1f
                                    }
                                    dismissAnimator = null
                                }
                            })
                            start()
                        }
                        isDismissDragging = false
                        dismissVelocityTracker?.recycle()
                        dismissVelocityTracker = null
                        true
                    } else {
                        dismissVelocityTracker?.recycle()
                        dismissVelocityTracker = null
                        false
                    }
                }
                else -> false
            }
        }
    }

    private fun setupFullscreenControlsSwipeUp() {
        val touchSlop = ViewConfiguration.get(activity).scaledTouchSlop

        val areaTouchListener = View.OnTouchListener { _, event ->
            onQueueDrag?.invoke(event) ?: false
        }

        binding.fullscreenLyricsControls.setOnTouchListener(areaTouchListener)
        binding.fullscreenPlaybackControlsRow.setOnTouchListener(areaTouchListener)
        binding.fullscreenCurrentTimeText.setOnTouchListener(areaTouchListener)
        binding.fullscreenTotalTimeText.setOnTouchListener(areaTouchListener)

        // Action buttons: allow normal tap clicks to proceed, but intercept vertical swipe-up with 1:1 sliding
        val actionButtons = listOf(
            binding.fullscreenBtnPlayPauseCard,
            binding.fullscreenBtnNext,
            binding.fullscreenBtnPrevious,
            binding.fullscreenBtnShuffle,
            binding.fullscreenBtnRepeat
        )

        for (btn in actionButtons) {
            var startY = 0f
            var startX = 0f
            var isDraggingQueue = false

            btn.setOnTouchListener { v, event ->
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        startY = event.rawY
                        startX = event.rawX
                        isDraggingQueue = false
                        v.isPressed = true
                        onQueueDrag?.invoke(event)
                        false // Allow default pressed ripple
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val deltaY = event.rawY - startY
                        val deltaX = event.rawX - startX
                        if (!isDraggingQueue && deltaY < -touchSlop && abs(deltaY) > abs(deltaX)) {
                            isDraggingQueue = true
                            v.isPressed = false
                            v.parent?.requestDisallowInterceptTouchEvent(true)
                            try {
                                v.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                            } catch (ignored: Exception) {}
                        }

                        if (isDraggingQueue) {
                            onQueueDrag?.invoke(event)
                            true
                        } else {
                            false
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        v.isPressed = false
                        if (isDraggingQueue) {
                            isDraggingQueue = false
                            onQueueDrag?.invoke(event)
                            true // Consume gesture without triggering button click
                        } else {
                            val cancelEvent = MotionEvent.obtain(event).apply { action = MotionEvent.ACTION_CANCEL }
                            onQueueDrag?.invoke(cancelEvent)
                            cancelEvent.recycle()
                            false // Allow normal click to trigger
                        }
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        v.isPressed = false
                        if (isDraggingQueue) {
                            onQueueDrag?.invoke(event)
                        } else {
                            val cancelEvent = MotionEvent.obtain(event).apply { action = MotionEvent.ACTION_CANCEL }
                            onQueueDrag?.invoke(cancelEvent)
                            cancelEvent.recycle()
                        }
                        isDraggingQueue = false
                        false
                    }
                    else -> false
                }
            }
        }
    }

    fun openLyricsCard() {
        if (isLyricsShowing) return
        val card = binding.albumArtCard
        val cardHeight = card.height.toFloat().coerceAtLeast(1f)

        binding.lyricsSourceBadge.visibility = View.GONE
        binding.lyricsSourceBadge.text = ""

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
                val currentPos = if (OmniSyncManager.getInstance(activity).currentRole == OmniSyncRole.LISTENER) {
                    OmniSyncManager.getInstance(activity).getListenerCurrentPosition()
                } else {
                    getPlaybackService()?.getCurrentPosition()?.toLong() ?: 0L
                }
                updateLyricsProgress(currentPos)
                if (lyricAdapter?.activeIndex == -1) {
                    (binding.lyricsRecyclerView.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(0, 0)
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

    fun openFullScreenLyrics(clickedView: View? = null) {
        if (isFullScreenLyricsShowing) return
        prepareFullscreenLyricsForOpen()

        isFullScreenLyricsShowing = true

        binding.fullscreenLyricsContainer.visibility = View.VISIBLE
        binding.fullscreenLyricsContainer.alpha = 0f

        // Ensure the container is laid out before calculating animations
        binding.fullscreenLyricsContainer.post {
            val currentPos = if (OmniSyncManager.getInstance(activity).currentRole == OmniSyncRole.LISTENER) {
                OmniSyncManager.getInstance(activity).getListenerCurrentPosition()
            } else {
                getPlaybackService()?.getCurrentPosition()?.toLong() ?: 0L
            }
            updateLyricsProgress(currentPos)
            if (fullScreenLyricAdapter?.activeIndex == -1 && !isLyricsShareSelectionMode) {
                (binding.fullscreenLyricsRecyclerView.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(0, 0)
            }

            // Fallback to albumArtCard if lyricsCard is invisible or 0-width (e.g. direct fullscreen)
            val startView = if (binding.lyricsCard.visibility == View.VISIBLE && binding.lyricsCard.width > 0) {
                binding.lyricsCard
            } else {
                binding.albumArtCard
            }

            val startLoc = IntArray(2)
            startView.getLocationOnScreen(startLoc)

            val rootLoc = IntArray(2)
            val rootContentView = activity.findViewById<ViewGroup>(android.R.id.content)
            rootContentView.getLocationOnScreen(rootLoc)

            val startX = startLoc[0].toFloat() - rootLoc[0].toFloat()
            val startY = startLoc[1].toFloat() - rootLoc[1].toFloat()
            val startW = startView.width.toFloat()
            val startH = startView.height.toFloat()

            val targetW = if (binding.fullscreenLyricsContainer.width > 0) binding.fullscreenLyricsContainer.width.toFloat() else rootContentView.width.toFloat()
            val targetH = if (binding.fullscreenLyricsContainer.height > 0) binding.fullscreenLyricsContainer.height.toFloat() else rootContentView.height.toFloat()

            if (targetW > 0 && targetH > 0 && startW > 0 && startH > 0) {
                val scaleX = startW / targetW
                val scaleY = startH / targetH

                binding.fullscreenLyricsContainer.pivotX = 0f
                binding.fullscreenLyricsContainer.pivotY = 0f
                binding.fullscreenLyricsContainer.scaleX = scaleX
                binding.fullscreenLyricsContainer.scaleY = scaleY
                binding.fullscreenLyricsContainer.translationX = startX
                binding.fullscreenLyricsContainer.translationY = startY

                binding.fullscreenLyricsContainer.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .translationX(0f)
                    .translationY(0f)
                    .setDuration(220L)
                    .setInterpolator(androidx.interpolator.view.animation.FastOutSlowInInterpolator())
                    .start()
            } else {
                binding.fullscreenLyricsContainer.alpha = 0f
                binding.fullscreenLyricsContainer.translationY = dpToPx(160).toFloat()
                binding.fullscreenLyricsContainer.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(220L)
                    .setInterpolator(android.view.animation.DecelerateInterpolator())
                    .start()
            }
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
        val song = getActiveSong() ?: return
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
        val song = getActiveSong() ?: return
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
            if (getActiveSong()?.id == song.id) {
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

                    val currentPos = if (OmniSyncManager.getInstance(activity).currentRole == OmniSyncRole.LISTENER) {
                        OmniSyncManager.getInstance(activity).getListenerCurrentPosition()
                    } else {
                        getPlaybackService()?.getCurrentPosition()?.toLong() ?: 0L
                    }
                    updateLyricsProgress(currentPos)
                    updateLyricsSourceBadge(lyrics.source, result.isOffline)
                    return
                }

                if (lyrics.hasPlain) {
                    binding.lyricsStatusLayout.visibility = View.GONE
                    binding.lyricsRecyclerView.visibility = View.GONE
                    binding.lyricsPlainScroll.visibility = View.VISIBLE
                    val plainLyrics = lyrics.plainLyrics.orEmpty()
                    if (binding.lyricsPlainText.text.toString() != plainLyrics) {
                        binding.lyricsPlainText.text = plainLyrics
                    }

                    binding.fullscreenLyricsStatusLayout.visibility = View.GONE
                    binding.fullscreenLyricsRecyclerView.visibility = View.GONE
                    binding.fullscreenLyricsPlainScroll.visibility = View.VISIBLE
                    if (binding.fullscreenLyricsPlainText.text.toString() != plainLyrics) {
                        binding.fullscreenLyricsPlainText.text = plainLyrics
                    }

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
        // Small lyrics card must not show any badge ("LRCLIB", "OFFLINE", or any other source badge)
        binding.lyricsSourceBadge.visibility = View.GONE
        binding.lyricsSourceBadge.text = ""

        // Don't show "OFFLINE" badge when in fullscreen lyrics
        if (isOffline) {
            binding.fullscreenLyricsSourceBadge.visibility = View.GONE
            binding.fullscreenLyricsSourceBadge.text = ""
        } else {
            binding.fullscreenLyricsSourceBadge.visibility = View.VISIBLE
            binding.fullscreenLyricsSourceBadge.text = badge
        }
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
                    if (changed && !isUserScrollingLyrics && !isLyricsShareSelectionMode) {
                        val layoutManager = binding.lyricsRecyclerView.layoutManager as? LinearLayoutManager
                        if (layoutManager != null && binding.lyricsRecyclerView.height > 0) {
                            if (activeIndex >= 0) {
                                val offset = (binding.lyricsRecyclerView.height / 2) - dpToPx(24)
                                layoutManager.scrollToPositionWithOffset(activeIndex, offset)
                            } else {
                                layoutManager.scrollToPositionWithOffset(0, 0)
                            }
                        }
                    }
                }
            }
        }

        if (isFullScreenLyricsShowing) {
            fullScreenLyricAdapter?.let { adapter ->
                if (activeIndex != adapter.activeIndex) {
                    val changed = adapter.setActiveIndex(activeIndex)
                    if (changed && !isUserScrollingFullScreenLyrics && !isLyricsShareSelectionMode) {
                        val layoutManager = binding.fullscreenLyricsRecyclerView.layoutManager as? LinearLayoutManager
                        val rvHeight = binding.fullscreenLyricsRecyclerView.height
                        if (layoutManager != null && rvHeight > 0) {
                            if (activeIndex >= 0) {
                                val remainingBelow = (synced.size - 1) - activeIndex
                                val offset = if (remainingBelow > 0) {
                                    // Anchor active line at upper area (~28% from top) so upcoming lyrics are never blank and clearly visible below
                                    (rvHeight * 0.28f).toInt()
                                } else {
                                    // Final line reading position
                                    (rvHeight * 0.38f).toInt()
                                }
                                layoutManager.scrollToPositionWithOffset(activeIndex, offset)
                            } else {
                                layoutManager.scrollToPositionWithOffset(0, 0)
                            }
                        }
                    }
                }
            }
        }
    }

    fun scrollToTop() {
        (binding.lyricsRecyclerView.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(0, 0)
        (binding.fullscreenLyricsRecyclerView.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(0, 0)
        binding.lyricsPlainScroll.scrollTo(0, 0)
        binding.fullscreenLyricsPlainScroll.scrollTo(0, 0)
    }

    fun onTrackChanged(newSong: Song?) {
        finishLyricsShareSelectionMode()
        lyricsFetchJob?.cancel()
        currentLyrics = null
        lastOnlineLyricsSongId = null
        isUserScrollingLyrics = false
        isUserScrollingFullScreenLyrics = false
        lyricsScrollResetHandler.removeCallbacks(resetUserScrollingRunnable)
        fullScreenScrollResetHandler.removeCallbacks(resetUserFullScreenScrollingRunnable)

        lyricAdapter?.submitLines(emptyList())
        fullScreenLyricAdapter?.submitLines(emptyList())

        scrollToTop()

        binding.lyricsPlainText.text = ""
        binding.lyricsSourceBadge.text = ""
        binding.lyricsSourceBadge.visibility = View.GONE
        binding.fullscreenLyricsPlainText.text = ""
        binding.fullscreenLyricsSourceBadge.text = ""
        binding.fullscreenLyricsSourceBadge.visibility = View.GONE

        if (newSong != null) {
            binding.fullscreenLyricsSongTitle.text = newSong.title
            binding.fullscreenLyricsArtistName.text = newSong.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) }
        } else {
            binding.fullscreenLyricsSongTitle.text = ""
            binding.fullscreenLyricsArtistName.text = ""
        }

        if (newSong == null) {
            renderLyricsResult(LyricsResult.NotFound("No song is currently playing"))
            updateKeepScreenOnState()
            return
        }
        updateKeepScreenOnState()

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
        if (isLyricsShareSelectionMode) {
            finishLyricsShareSelectionMode()
            return true
        }
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
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding.fullscreenLyricsContainer.keepScreenOn = false
    }
}

private class RoundedLyricsSelectionSpan(
    color: Int,
    private val horizontalInset: Float,
    private val cornerRadius: Float
) : LineBackgroundSpan {
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = color
        style = Paint.Style.FILL
    }

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lineNumber: Int
    ) {
        if (right <= left) return
        val bounds = RectF(
            left - horizontalInset,
            top.toFloat(),
            right + horizontalInset,
            bottom.toFloat()
        )
        canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, backgroundPaint)
    }
}
