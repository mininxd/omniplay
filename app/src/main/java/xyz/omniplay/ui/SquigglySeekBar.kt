package xyz.omniplay.ui

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import androidx.core.content.ContextCompat
import com.google.android.material.color.MaterialColors
import xyz.omniplay.R
import xyz.omniplay.model.Song
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * Android 13/14 (Tiramisu/UpsideDownCake) native style Squiggly Progress Line Seek Bar.
 * When playing, a lively squiggly wave flows along the played portion of the track.
 * When paused, the wave settles into a calm line.
 * When scrubbing, the wave smoothly flattens into a straight guide line and displays a floating timestamp bubble.
 */
class SquigglySeekBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    interface OnSeekListener {
        fun onStartTracking()
        fun onProgressChanged(progressMs: Long, fromUser: Boolean, isCancelled: Boolean = false)
        fun onStopTracking(progressMs: Long, isCancelled: Boolean = false)
    }

    var seekListener: OnSeekListener? = null
    var isSeekable: Boolean = true
    private var isNeutralMode: Boolean = false

    private var maxDurationMs: Long = 1000L

    val maxDuration: Long
        get() = maxDurationMs

    fun updateDuration(durationMs: Long) {
        if (durationMs > 0L && durationMs != maxDurationMs) {
            maxDurationMs = durationMs
            invalidate()
        }
    }

    fun setNeutralMode(neutral: Boolean) {
        if (isNeutralMode == neutral) return
        isNeutralMode = neutral
        refreshThemeColors()
    }
    private var currentProgressMs: Long = 0L
    private var displayedProgressMs: Float = 0f
    private var isUserDragging: Boolean = false
    private var isPlaying: Boolean = false

    // Seek cancellation tracking
    private var initialPlayingProgressMs: Long = 0L
    private var hasSlidAway: Boolean = false
    private var isSeekCancelled: Boolean = false
    private val cancelMessage: String
        get() = context.getString(R.string.release_to_cancel)

    private val density = context.resources.displayMetrics.density
    private val strokeWidthPx = 4f * density
    private val waveAmplitudePx = 3.5f * density
    private val waveLengthPx = 22f * density
    private val thumbRadiusPx = 7f * density
    private val thumbHaloRadiusPx = 14f * density

    // Material 3 Expressive dynamic thumb scaling & motion
    private var currentThumbRadius: Float = thumbRadiusPx
    private var currentHaloRadius: Float = 0f
    private var thumbAnimator: ValueAnimator? = null
    private val expressiveInterpolator = PathInterpolator(0.2f, 0f, 0f, 1f)

    private var wavePhase: Float = 0f
    private var currentAmplitudeFactor: Float = 1f
    private var amplitudeAnimator: ValueAnimator? = null
    private var phaseAnimator: ValueAnimator? = null
    private var smoothProgressAnimator: ValueAnimator? = null

    // Message popup tooltip animations
    private var bubbleAlpha: Float = 0f
    private var bubbleScale: Float = 0.85f
    private var bubbleAnimator: ValueAnimator? = null

    private val squigglyPath = Path()

    private val activeTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = strokeWidthPx
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val inactiveTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = strokeWidthPx
        strokeCap = Paint.Cap.ROUND
    }

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val thumbHaloPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val bubblePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val bubbleStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f * density
    }

    private val bubbleCancelStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f * density
    }

    private val bubbleTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 12f * context.resources.displayMetrics.scaledDensity
        typeface = Typeface.create("sans-serif-medium", Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }

    init {
        isClickable = true
        isFocusable = true
        alpha = if (isEnabled) 1.0f else 0.45f
        refreshThemeColors()
    }

    fun refreshThemeColors() {
        if (!isNeutralMode) {
            val primary = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimary, ContextCompat.getColor(context, R.color.primary_accent))
            activeTrackPaint.color = primary
            thumbPaint.color = primary
            thumbHaloPaint.color = primary
            thumbHaloPaint.alpha = 50
        } else {
            val neutral = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnSurfaceVariant, ContextCompat.getColor(context, R.color.text_secondary))
            activeTrackPaint.color = neutral
            thumbPaint.color = neutral
            thumbHaloPaint.color = neutral
            thumbHaloPaint.alpha = 40
        }
        inactiveTrackPaint.color = MaterialColors.getColor(context, com.google.android.material.R.attr.colorSurfaceContainerHighest, ContextCompat.getColor(context, R.color.slider_track_inactive))
        bubblePaint.color = MaterialColors.getColor(context, com.google.android.material.R.attr.colorSurfaceContainerHigh, ContextCompat.getColor(context, R.color.surface_container_high))
        bubbleStrokePaint.color = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOutlineVariant, ContextCompat.getColor(context, R.color.badge_stroke))
        bubbleCancelStrokePaint.color = MaterialColors.getColor(context, com.google.android.material.R.attr.colorError, ContextCompat.getColor(context, R.color.slider_cancel_accent))
        bubbleTextPaint.color = MaterialColors.getColor(context, com.google.android.material.R.attr.colorOnSurface, ContextCompat.getColor(context, R.color.text_primary))
        invalidate()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        refreshThemeColors()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) 1.0f else 0.45f
    }

    fun setDuration(durationMs: Long) {
        maxDurationMs = max(1L, durationMs)
        currentProgressMs = 0L
        displayedProgressMs = 0f
        smoothProgressAnimator?.cancel()
        invalidate()
    }

    fun setProgress(progressMs: Long) {
        if (isUserDragging) return
        val clamped = progressMs.coerceIn(0L, maxDurationMs)
        currentProgressMs = clamped

        smoothProgressAnimator?.cancel()
        smoothProgressAnimator = ValueAnimator.ofFloat(displayedProgressMs, clamped.toFloat()).apply {
            duration = 90
            interpolator = LinearInterpolator()
            addUpdateListener { anim ->
                displayedProgressMs = anim.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    fun setPlaying(playing: Boolean) {
        if (isPlaying == playing) return
        isPlaying = playing
        if (playing && isAttachedToWindow) {
            startPhaseAnimation()
        } else {
            stopPhaseAnimation()
            invalidate()
        }
    }

    private fun startPhaseAnimation() {
        if (phaseAnimator?.isRunning == true) return
        phaseAnimator = ValueAnimator.ofFloat(0f, (2 * PI).toFloat()).apply {
            duration = 1400
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener { anim ->
                wavePhase = anim.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    private fun stopPhaseAnimation() {
        phaseAnimator?.cancel()
        phaseAnimator = null
    }

    private fun animateAmplitude(target: Float) {
        amplitudeAnimator?.cancel()
        amplitudeAnimator = ValueAnimator.ofFloat(currentAmplitudeFactor, target).apply {
            duration = 180
            interpolator = expressiveInterpolator
            addUpdateListener {
                currentAmplitudeFactor = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    private fun animateThumbInteraction(dragging: Boolean) {
        thumbAnimator?.cancel()
        val targetThumb = if (dragging) 9.5f * density else thumbRadiusPx
        val targetHalo = if (dragging) thumbHaloRadiusPx else 0f
        val startThumb = currentThumbRadius
        val startHalo = currentHaloRadius

        thumbAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = if (dragging) 160L else 200L
            interpolator = expressiveInterpolator
            addUpdateListener { anim ->
                val f = anim.animatedValue as Float
                currentThumbRadius = startThumb + (targetThumb - startThumb) * f
                currentHaloRadius = startHalo + (targetHalo - startHalo) * f
                invalidate()
            }
            start()
        }
    }

    private fun animateBubbleVisibility(show: Boolean) {
        bubbleAnimator?.cancel()
        val targetAlpha = if (show) 1f else 0f
        val targetScale = if (show) 1f else 0.85f
        val startAlpha = bubbleAlpha
        val startScale = bubbleScale

        if (show && bubbleAlpha == 1f && bubbleScale == 1f) return
        if (!show && bubbleAlpha == 0f) return

        bubbleAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = if (show) 160L else 120L
            interpolator = expressiveInterpolator
            addUpdateListener { anim ->
                val fraction = anim.animatedValue as Float
                bubbleAlpha = startAlpha + (targetAlpha - startAlpha) * fraction
                bubbleScale = startScale + (targetScale - startScale) * fraction
                invalidate()
            }
            start()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        refreshThemeColors()
        if (isPlaying) {
            startPhaseAnimation()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopPhaseAnimation()
        smoothProgressAnimator?.cancel()
        amplitudeAnimator?.cancel()
        bubbleAnimator?.cancel()
        thumbAnimator?.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val startX = paddingLeft.toFloat() + thumbRadiusPx
        val endX = (width - paddingRight).toFloat() - thumbRadiusPx
        val trackWidth = endX - startX

        if (trackWidth <= 0 || maxDurationMs <= 0) return

        val centerY = height / 2f
        val progressRatio = (displayedProgressMs / maxDurationMs).coerceIn(0f, 1f)
        val progressX = startX + progressRatio * trackWidth

        // 1. Draw Inactive Track (straight line from progressX to endX)
        if (progressX < endX) {
            canvas.drawLine(progressX, centerY, endX, centerY, inactiveTrackPaint)
        }

        // 2. Draw Active Track (Android 13/14 Squiggly Wave from startX to progressX)
        squigglyPath.reset()
        if (progressX > startX) {
            squigglyPath.moveTo(startX, centerY)

            val step = max(2f, 2f * density)
            var x = startX

            while (x <= progressX) {
                val relX = x - startX
                val rampIn = min(1f, relX / (waveLengthPx * 0.75f))
                val rampOut = min(1f, (progressX - x) / (waveLengthPx * 0.75f))
                val ramp = min(rampIn, rampOut)

                val baseAmp = if (isPlaying) waveAmplitudePx else (waveAmplitudePx * 0.35f)
                val effectiveAmp = baseAmp * ramp * currentAmplitudeFactor
                val angle = (relX / waveLengthPx) * (2 * PI).toFloat() - wavePhase
                val y = centerY + effectiveAmp * sin(angle)

                squigglyPath.lineTo(x, y)
                x += step
            }
            squigglyPath.lineTo(progressX, centerY)

            canvas.drawPath(squigglyPath, activeTrackPaint)
        }

        // 3. Draw Thumb (circle at progressX, centerY)
        if (isEnabled && maxDurationMs > 0) {
            if (currentHaloRadius > 0f) {
                canvas.drawCircle(progressX, centerY, currentHaloRadius, thumbHaloPaint)
            }
            canvas.drawCircle(progressX, centerY, currentThumbRadius, thumbPaint)
        }

        // 4. Draw Floating Time Bubble Tooltip above finger during scrubbing
        if (bubbleAlpha > 0f && isEnabled && maxDurationMs > 0) {
            val displayText = if (isSeekCancelled) cancelMessage else Song.formatTime(currentProgressMs)
            val textWidth = bubbleTextPaint.measureText(displayText)
            val bubbleWidth = textWidth + 20f * density
            val bubbleHeight = 24f * density
            val bubbleRadius = 8f * density
            val bubbleBottom = centerY - thumbHaloRadiusPx - 6f * density
            val bubbleTop = bubbleBottom - bubbleHeight

            val halfWidth = bubbleWidth / 2f
            val bubbleCenterX = progressX.coerceIn(
                paddingLeft + halfWidth + 2f * density,
                width - paddingRight - halfWidth - 2f * density
            )

            val bubbleRect = RectF(
                bubbleCenterX - halfWidth,
                bubbleTop,
                bubbleCenterX + halfWidth,
                bubbleBottom
            )

            canvas.save()
            canvas.scale(bubbleScale, bubbleScale, bubbleCenterX, bubbleRect.centerY())

            bubblePaint.alpha = (255 * bubbleAlpha).toInt()
            bubbleStrokePaint.alpha = (255 * bubbleAlpha).toInt()
            bubbleCancelStrokePaint.alpha = (255 * bubbleAlpha).toInt()
            bubbleTextPaint.alpha = (255 * bubbleAlpha).toInt()

            canvas.drawRoundRect(bubbleRect, bubbleRadius, bubbleRadius, bubblePaint)
            if (isSeekCancelled) {
                canvas.drawRoundRect(bubbleRect, bubbleRadius, bubbleRadius, bubbleCancelStrokePaint)
            } else {
                canvas.drawRoundRect(bubbleRect, bubbleRadius, bubbleRadius, bubbleStrokePaint)
            }

            val textY = bubbleRect.centerY() - (bubbleTextPaint.descent() + bubbleTextPaint.ascent()) / 2f
            canvas.drawText(displayText, bubbleCenterX, textY, bubbleTextPaint)
            canvas.restore()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled || !isSeekable) return false

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isUserDragging = true
                initialPlayingProgressMs = currentProgressMs
                hasSlidAway = false
                isSeekCancelled = false
                animateAmplitude(0f) // Smoothly flatten wave into straight line during scrubbing
                animateBubbleVisibility(true)
                animateThumbInteraction(true)
                parent?.requestDisallowInterceptTouchEvent(true)
                seekListener?.onStartTracking()
                smoothProgressAnimator?.cancel()
                updateTouchPosition(event.x)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (isUserDragging) {
                    updateTouchPosition(event.x)
                }
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (isUserDragging) {
                    val wasCancelled = isSeekCancelled
                    isUserDragging = false
                    animateAmplitude(1f) // Smoothly bounce back to squiggly wave on release
                    animateBubbleVisibility(false)
                    animateThumbInteraction(false)
                    if (wasCancelled) {
                        currentProgressMs = initialPlayingProgressMs
                        displayedProgressMs = initialPlayingProgressMs.toFloat()
                        invalidate()
                    } else {
                        updateTouchPosition(event.x)
                    }
                    seekListener?.onStopTracking(currentProgressMs, wasCancelled)
                }
                parent?.requestDisallowInterceptTouchEvent(false)
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateTouchPosition(touchX: Float) {
        val startX = paddingLeft.toFloat() + thumbRadiusPx
        val endX = (width - paddingRight).toFloat() - thumbRadiusPx
        val trackWidth = endX - startX

        if (trackWidth <= 0) return

        val fraction = ((touchX - startX) / trackWidth).coerceIn(0f, 1f)
        val progress = (fraction * maxDurationMs).toLong()
        currentProgressMs = progress
        displayedProgressMs = progress.toFloat()

        val gapMs = min(5000L, max(1000L, maxDurationMs / 2))
        val distMs = abs(progress - initialPlayingProgressMs)

        // User must slide away past the 5-second gap before bringing it back can trigger cancel
        if (distMs > gapMs) {
            hasSlidAway = true
        }

        val newCancelled = hasSlidAway && (distMs <= gapMs)
        if (newCancelled != isSeekCancelled) {
            isSeekCancelled = newCancelled
            try {
                performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            } catch (ignored: Exception) {}
        }

        invalidate()
        seekListener?.onProgressChanged(progress, true, isSeekCancelled)
    }

    fun isTracking(): Boolean = isUserDragging
    fun isCancelled(): Boolean = isSeekCancelled
}
