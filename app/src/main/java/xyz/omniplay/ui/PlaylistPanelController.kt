package xyz.omniplay.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.app.Activity
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.DecelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import xyz.omniplay.databinding.ActivityMainBinding

class PlaylistPanelController(
    private val activity: Activity,
    private val binding: ActivityMainBinding,
    private val onUpdateAppTitle: () -> Unit,
    private val onPanelCollapsed: () -> Unit
) {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
        private set

    var safeTopCutoutInset: Int = 0
        private set
    var safeLeftCutoutInset: Int = 0
        private set
    var safeRightCutoutInset: Int = 0
        private set

    fun setup() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.playlistSlidingPanel)
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.halfExpandedRatio = 0.62f
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        fun updateSheetDimensions() {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.isFitToContents = false
                bottomSheetBehavior.halfExpandedRatio = 0.62f
            }
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

            val basePeekHeight = (64 * activity.resources.displayMetrics.density).toInt()
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
                navInsets.bottom + (28 * activity.resources.displayMetrics.density).toInt()
            )

            updateSheetDimensions()
            updatePlaylistCutoutPadding()
            insets
        }

        binding.coordinatorRoot.post {
            updateSheetDimensions()
            updatePlaylistCutoutPadding()
        }

        // Outer Scrim: Close queue when pressing / tapping / dragging down on the area outside the half-expanded queue
        val queueScrimGestureDetector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
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

        var wasExpanded = false

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        wasExpanded = true
                        bottomSheetBehavior.isFitToContents = true
                        binding.ivChevron.rotation = 180f
                        binding.queueScrimOverlay.visibility = View.VISIBLE
                        binding.queueScrimOverlay.alpha = 0.5f
                        updatePlaylistCutoutPadding(1f)
                        onUpdateAppTitle()
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        if (wasExpanded) {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                            return
                        }
                        binding.ivChevron.rotation = 90f
                        binding.queueScrimOverlay.visibility = View.VISIBLE
                        binding.queueScrimOverlay.alpha = 0.4f
                        updatePlaylistCutoutPadding(0f)
                        onUpdateAppTitle()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        wasExpanded = false
                        bottomSheetBehavior.isFitToContents = false
                        bottomSheetBehavior.halfExpandedRatio = 0.62f
                        binding.ivChevron.rotation = 0f
                        binding.queueScrimOverlay.visibility = View.GONE
                        binding.queueScrimOverlay.alpha = 0f
                        updatePlaylistCutoutPadding(0f)
                        onPanelCollapsed()
                        onUpdateAppTitle()
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

        binding.playlistPeekHeader.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.isFitToContents = false
                bottomSheetBehavior.halfExpandedRatio = 0.62f
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        setupBottomSwipeGesture()
    }

    private var startY = 0f
    private var isDraggingSheet = false
    private var velocityTracker: VelocityTracker? = null
    private var activeAnimator: ValueAnimator? = null

    fun handleSlidingPanelDrag(event: MotionEvent, isFromFullscreen: Boolean = false): Boolean {
        if (!::bottomSheetBehavior.isInitialized || bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
            return false
        }

        val parentH = binding.coordinatorRoot.height.takeIf { it > 0 } ?: binding.root.height
        val maxSheetH = (parentH * 0.62f).toInt()
        val targetTop = (parentH - maxSheetH).coerceAtLeast(0)
        val collapsedTop = parentH - bottomSheetBehavior.peekHeight
        val maxTravel = (collapsedTop - targetTop).toFloat().coerceAtLeast(1f)
        val touchSlop = ViewConfiguration.get(activity).scaledTouchSlop
        val dp = activity.resources.displayMetrics.density

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                activeAnimator?.cancel()
                startY = event.rawY
                isDraggingSheet = false
                velocityTracker?.recycle()
                velocityTracker = VelocityTracker.obtain().apply {
                    addMovement(event)
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                velocityTracker?.addMovement(event)
                val deltaTotalY = event.rawY - startY

                if (!isDraggingSheet && Math.abs(deltaTotalY) > touchSlop) {
                    if (deltaTotalY < 0) { // Moving upwards
                        isDraggingSheet = true
                        if (isFromFullscreen) {
                            binding.playlistSlidingPanel.elevation = 20 * dp
                            binding.queueScrimOverlay.elevation = 18 * dp
                            binding.queueScrimOverlay.bringToFront()
                            binding.playlistSlidingPanel.bringToFront()
                            binding.queueScrimOverlay.visibility = View.VISIBLE
                            binding.queueScrimOverlay.alpha = 0f
                        }
                    }
                }

                if (isDraggingSheet) {
                    // Clamp translation between -maxTravel (expanded) and 0f (collapsed)
                    val targetTranslation = deltaTotalY.coerceIn(-maxTravel, 0f)
                    binding.playlistSlidingPanel.translationY = targetTranslation
                    val progress = (-targetTranslation / maxTravel).coerceIn(0f, 1f)
                    binding.ivChevron.rotation = progress * 90f
                    if (isFromFullscreen) {
                        binding.queueScrimOverlay.alpha = progress * 0.45f
                    }
                }
                return isDraggingSheet
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                velocityTracker?.addMovement(event)
                if (isDraggingSheet) {
                    velocityTracker?.computeCurrentVelocity(1000)
                    val yVel = velocityTracker?.yVelocity ?: 0f
                    val currentTrans = binding.playlistSlidingPanel.translationY
                    val progress = (-currentTrans / maxTravel).coerceIn(0f, 1f)

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
                            val p = (-v / maxTravel).coerceIn(0f, 1f)
                            binding.ivChevron.rotation = p * 90f
                            if (isFromFullscreen) {
                                binding.queueScrimOverlay.alpha = p * 0.45f
                            }
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
                                    if (isFromFullscreen) {
                                        binding.queueScrimOverlay.alpha = 0.4f
                                    }
                                } else {
                                    panel.translationY = 0f
                                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                                    binding.ivChevron.rotation = 0f
                                    if (isFromFullscreen) {
                                        binding.playlistSlidingPanel.elevation = 12 * dp
                                        binding.queueScrimOverlay.elevation = 6 * dp
                                        binding.queueScrimOverlay.visibility = View.GONE
                                        binding.queueScrimOverlay.alpha = 0f
                                    }
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
                return true
            }
            else -> false
        }
    }

    private fun setupBottomSwipeGesture() {
        binding.bottomGestureArea.setOnTouchListener { _, event ->
            handleSlidingPanelDrag(event, isFromFullscreen = false)
        }
    }

    fun updatePlaylistCutoutPadding(customProgress: Float? = null) {
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

    fun isCollapsed(): Boolean {
        return !::bottomSheetBehavior.isInitialized || bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED
    }

    fun collapse() {
        if (::bottomSheetBehavior.isInitialized) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    fun expand() {
        if (::bottomSheetBehavior.isInitialized) {
            bottomSheetBehavior.isFitToContents = false
            bottomSheetBehavior.halfExpandedRatio = 0.62f
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }

    fun getState(): Int = if (::bottomSheetBehavior.isInitialized) bottomSheetBehavior.state else BottomSheetBehavior.STATE_COLLAPSED

    fun setState(state: Int) {
        if (::bottomSheetBehavior.isInitialized) {
            if (state == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                bottomSheetBehavior.isFitToContents = false
                bottomSheetBehavior.halfExpandedRatio = 0.62f
            }
            bottomSheetBehavior.state = state
        }
    }

    fun handleBackPressed(): Boolean {
        if (::bottomSheetBehavior.isInitialized && bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            return true
        }
        return false
    }
}
