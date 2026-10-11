package xyz.omniplay.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Color
import android.view.GestureDetector
import android.view.Gravity
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.ThemeColors

class PlaybackGestureController(
    private val activity: AppCompatActivity,
    private val binding: ActivityMainBinding,
    private val getPlaybackService: () -> PlaybackService?,
    private val lyricsController: LyricsController,
    private val onSkipNext: () -> Unit,
    private val onSkipPrevious: () -> Unit,
    private val onAlbumArtTap: () -> Unit,
    private val onOpenDrawerRequested: () -> Unit
) {

    fun setup() {
        setupAlbumArtSwipeGesture()
        setupTitleDoubleTapGestures()
    }

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

    private fun updatePeekTextContrast(isBright: Boolean) {
        if (isBright) {
            binding.peekLabelText.setTextColor(ThemeColors.getPrimary(activity))
            binding.peekLabelText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#80FFFFFF"))

            binding.peekTitleText.setTextColor(ThemeColors.getOnSurface(activity))
            binding.peekTitleText.setShadowLayer(4f, 0f, 1f, Color.parseColor("#99FFFFFF"))

            binding.peekArtistText.setTextColor(ThemeColors.getOnSurfaceVariant(activity))
            binding.peekArtistText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#99FFFFFF"))
        } else {
            binding.peekLabelText.setTextColor(ThemeColors.getPrimary(activity))
            binding.peekLabelText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#99000000"))

            binding.peekTitleText.setTextColor(Color.parseColor("#FFFFFF"))
            binding.peekTitleText.setShadowLayer(4f, 0f, 1f, Color.parseColor("#B3000000"))

            binding.peekArtistText.setTextColor(Color.parseColor("#D1D5DB"))
            binding.peekArtistText.setShadowLayer(3f, 0f, 1f, Color.parseColor("#B3000000"))
        }
    }

    private fun setupAlbumArtSwipeGesture() {
        var startX = 0f
        var startY = 0f
        var isDraggingX = false
        var isDraggingY = false
        var velocityTracker: VelocityTracker? = null
        val touchSlop = ViewConfiguration.get(activity).scaledTouchSlop
        var activeAnimator: ValueAnimator? = null
        var currentPeekSong: Song? = null
        var peekDirection = 0 // -1 = next (swipe left), +1 = prev (swipe right)

        fun updatePeekCard(direction: Int) {
            val service = getPlaybackService()
            val peekSong = if (direction == -1) {
                service?.getNextSong()
            } else {
                service?.getPreviousSong()
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
                    binding.peekArtistText.text = peekSong.artist.trim().ifEmpty { activity.getString(R.string.unknown_artist) }

                    val cached = AlbumArtLoader.getCachedAlbumArt(peekSong)
                    if (cached != null) {
                        binding.peekAlbumArtImage.setImageBitmap(cached)
                        updatePeekTextContrast(isBitmapBright(cached))
                    } else {
                        binding.peekAlbumArtImage.setImageResource(R.drawable.default_album_art)
                        updatePeekTextContrast(isBright = false)
                        activity.lifecycleScope.launch {
                            val bitmap = AlbumArtLoader.loadAlbumArt(activity, peekSong)
                            if (currentPeekSong?.id == peekSong.id) {
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
                            lyricsController.prepareLyricsForDisplay()
                        } else if (deltaY > 35f && Math.abs(deltaY) > Math.abs(deltaX) * 2f) {
                            v.parent?.requestDisallowInterceptTouchEvent(false)
                        }
                    }

                    if (isDraggingX) {
                        val cardWidth = v.width.toFloat().coerceAtLeast(1f)
                        val dir = if (deltaX < 0) -1 else 1
                        updatePeekCard(dir)

                        val effectiveDeltaX = if (currentPeekSong == null) deltaX * 0.25f else deltaX
                        v.translationX = effectiveDeltaX
                        val progress = (Math.abs(effectiveDeltaX) / cardWidth).coerceIn(0f, 1f)
                        v.alpha = (1f - (progress * 0.45f)).coerceIn(0.55f, 1f)

                        val peekScale = 0.90f + (progress * 0.10f)
                        binding.peekAlbumArtCard.scaleX = peekScale
                        binding.peekAlbumArtCard.scaleY = peekScale
                        binding.peekAlbumArtCard.alpha = (0.6f + (progress * 0.4f)).coerceIn(0.6f, 1f)
                    } else if (isDraggingY) {
                        val cardHeight = v.height.toFloat().coerceAtLeast(1f)
                        val effectiveDeltaY = deltaY.coerceAtMost(0f)
                        v.translationY = effectiveDeltaY
                        val progress = (Math.abs(effectiveDeltaY) / cardHeight).coerceIn(0f, 1f)
                        v.alpha = (1f - (progress * 0.5f)).coerceIn(0.5f, 1f)

                        val lyricsScale = 0.92f + (progress * 0.08f)
                        binding.lyricsCard.scaleX = lyricsScale
                        binding.lyricsCard.scaleY = lyricsScale
                        binding.lyricsCard.alpha = (0.5f + (progress * 0.5f)).coerceIn(0.5f, 1f)
                    }
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    velocityTracker?.addMovement(event)
                    v.parent?.requestDisallowInterceptTouchEvent(false)

                    if (isDraggingX) {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val xVel = velocityTracker?.xVelocity ?: 0f
                        val deltaX = event.rawX - startX
                        val cardWidth = v.width.toFloat().coerceAtLeast(1f)
                        val dragDistance = Math.abs(v.translationX)
                        val progress = (dragDistance / cardWidth).coerceIn(0f, 1f)

                        val shouldCommit = when {
                            currentPeekSong == null -> false
                            Math.abs(xVel) > 650f -> true
                            progress >= 0.22f -> true
                            else -> false
                        }

                        if (shouldCommit) {
                            val isSwipeLeft = if (Math.abs(xVel) > 400f) xVel < 0 else deltaX < 0
                            val targetX = if (isSwipeLeft) -cardWidth * 1.15f else cardWidth * 1.15f

                            binding.peekAlbumArtCard.animate()
                                .scaleX(1f).scaleY(1f).alpha(1f)
                                .setDuration(160L).start()

                            activeAnimator = ValueAnimator.ofFloat(v.translationX, targetX).apply {
                                duration = 160L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    val currentX = anim.animatedValue as Float
                                    v.translationX = currentX
                                    val p = (Math.abs(currentX) / cardWidth).coerceIn(0f, 1f)
                                    v.alpha = (1f - (p * 0.45f)).coerceIn(0.55f, 1f)
                                }
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        if (isSwipeLeft) {
                                            onSkipNext()
                                        } else {
                                            onSkipPrevious()
                                        }

                                        v.postDelayed({
                                            v.translationX = 0f
                                            v.translationY = 0f
                                            v.alpha = 1f
                                            binding.peekAlbumArtCard.visibility = View.INVISIBLE
                                            binding.peekAlbumArtCard.scaleX = 0.90f
                                            binding.peekAlbumArtCard.scaleY = 0.90f
                                            binding.peekAlbumArtCard.alpha = 0.6f
                                        }, 280L)
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        } else {
                            val startTransX = v.translationX
                            val startAlpha = v.alpha

                            binding.peekAlbumArtCard.animate()
                                .scaleX(0.90f).scaleY(0.90f).alpha(0.6f)
                                .setDuration(180L)
                                .withEndAction { binding.peekAlbumArtCard.visibility = View.INVISIBLE }
                                .start()

                            activeAnimator = ValueAnimator.ofFloat(startTransX, 0f).apply {
                                duration = 180L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    val currentX = anim.animatedValue as Float
                                    v.translationX = currentX
                                    val p = (Math.abs(currentX) / cardWidth).coerceIn(0f, 1f)
                                    v.alpha = (1f - (p * 0.45f)).coerceIn(startAlpha, 1f)
                                }
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        v.translationX = 0f
                                        v.alpha = 1f
                                        binding.peekAlbumArtCard.visibility = View.INVISIBLE
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
                        val deltaY = event.rawY - startY
                        val cardHeight = v.height.toFloat().coerceAtLeast(1f)
                        val dragDistance = Math.abs(v.translationY)
                        val progress = (dragDistance / cardHeight).coerceIn(0f, 1f)

                        val shouldOpenLyrics = when {
                            yVel < -500f -> true
                            deltaY < 0 && progress >= 0.20f -> true
                            else -> false
                        }

                        if (shouldOpenLyrics) {
                            lyricsController.openLyricsCard()
                        } else {
                            binding.lyricsCard.animate()
                                .scaleX(0.92f).scaleY(0.92f).alpha(0f)
                                .setDuration(160L)
                                .withEndAction { binding.lyricsCard.visibility = View.INVISIBLE }
                                .start()

                            activeAnimator = ValueAnimator.ofFloat(v.translationY, 0f).apply {
                                duration = 160L
                                interpolator = DecelerateInterpolator()
                                addUpdateListener { anim ->
                                    val currentY = anim.animatedValue as Float
                                    v.translationY = currentY
                                    val p = (Math.abs(currentY) / cardHeight).coerceIn(0f, 1f)
                                    v.alpha = (1f - (p * 0.5f)).coerceIn(0.5f, 1f)
                                }
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        v.translationY = 0f
                                        v.alpha = 1f
                                        binding.lyricsCard.visibility = View.INVISIBLE
                                        activeAnimator = null
                                    }
                                })
                                start()
                            }
                        }
                        isDraggingY = false
                    } else {
                        val totalMovement = Math.hypot((event.rawX - startX).toDouble(), (event.rawY - startY).toDouble())
                        if (totalMovement < touchSlop && event.actionMasked == MotionEvent.ACTION_UP) {
                            onAlbumArtTap()
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

    fun seekRelative(offsetMs: Long) {
        val service = getPlaybackService() ?: return
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

    fun showTitleSeekBadge(isForward: Boolean) {
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

    private fun setupTitleDoubleTapGestures() {
        val gestureDetector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean = true

            override fun onDoubleTap(e: MotionEvent): Boolean {
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

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                onOpenDrawerRequested()
                return true
            }
        })

        binding.titleGestureArea.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }
}
