package xyz.omniplay.ui

import android.animation.ValueAnimator
import android.content.Context
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
import androidx.core.content.ContextCompat
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

    private var maxDurationMs: Long = 1000L
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

    private var wavePhase: Float = 0f
    private var currentAmplitudeFactor: Float = 1f
    private var amplitudeAnimator: ValueAnimator? = null
    private var phaseAnimator: ValueAnimator? = null
    private var smoothProgressAnimator: ValueAnimator? = null

    private val squigglyPath = Path()

    private val activeTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.primary_accent)
        style = Paint.Style.STROKE
        strokeWidth = strokeWidthPx
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val inactiveTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_track_inactive)
        style = Paint.Style.STROKE
        strokeWidth = strokeWidthPx
        strokeCap = Paint.Cap.ROUND
    }

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_thumb)
        style = Paint.Style.FILL
    }

    private val thumbHaloPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_halo)
        style = Paint.Style.FILL
    }

    private val bubblePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.surface_container_high)
        style = Paint.Style.FILL
    }

    private val bubbleStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.primary_accent)
        style = Paint.Style.STROKE
        strokeWidth = 1.5f * density
    }

    private val bubbleCancelStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_cancel_accent)
        style = Paint.Style.STROKE
        strokeWidth = 1.5f * density
    }

    private val bubbleTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.text_primary)
        textSize = 12f * context.resources.displayMetrics.scaledDensity
        typeface = Typeface.create("sans-serif-medium", Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }

    init {
        isClickable = true
        isFocusable = true
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
            interpolator = DecelerateInterpolator()
            addUpdateListener {
                currentAmplitudeFactor = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isPlaying) {
            startPhaseAnimation()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopPhaseAnimation()
        smoothProgressAnimator?.cancel()
        amplitudeAnimator?.cancel()
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
            if (isUserDragging) {
                canvas.drawCircle(progressX, centerY, thumbHaloRadiusPx, thumbHaloPaint)
            }
            canvas.drawCircle(progressX, centerY, thumbRadiusPx, thumbPaint)
        }

        // 4. Draw Floating Time Bubble Tooltip above finger during scrubbing
        if (isUserDragging && isEnabled && maxDurationMs > 0) {
            val displayText = if (isSeekCancelled) cancelMessage else Song.formatTime(currentProgressMs)
            val textWidth = bubbleTextPaint.measureText(displayText)
            val bubbleWidth = textWidth + 18f * density
            val bubbleHeight = 22f * density
            val bubbleRadius = 11f * density
            val bubbleBottom = centerY - thumbHaloRadiusPx - 4f * density
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

            canvas.drawRoundRect(bubbleRect, bubbleRadius, bubbleRadius, bubblePaint)
            if (isSeekCancelled) {
                canvas.drawRoundRect(bubbleRect, bubbleRadius, bubbleRadius, bubbleCancelStrokePaint)
            } else {
                canvas.drawRoundRect(bubbleRect, bubbleRadius, bubbleRadius, bubbleStrokePaint)
            }

            val textY = bubbleRect.centerY() - (bubbleTextPaint.descent() + bubbleTextPaint.ascent()) / 2f
            canvas.drawText(displayText, bubbleCenterX, textY, bubbleTextPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) return false

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isUserDragging = true
                initialPlayingProgressMs = currentProgressMs
                hasSlidAway = false
                isSeekCancelled = false
                animateAmplitude(0f) // Smoothly flatten wave into straight line during scrubbing
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

        // Calculate initial playing position on screen
        val initialRatio = (initialPlayingProgressMs.toFloat() / max(1L, maxDurationMs)).coerceIn(0f, 1f)
        val initialX = startX + initialRatio * trackWidth
        val distPx = abs(touchX - initialX)
        val distMs = abs(progress - initialPlayingProgressMs)

        val thresholdPx = 24f * density
        val thresholdMs = max(2000L, (maxDurationMs * 0.025f).toLong())

        // User must slide away before bringing it back can trigger cancel
        if (distPx > thresholdPx * 1.25f || distMs > max(3000L, (maxDurationMs * 0.035f).toLong())) {
            hasSlidAway = true
        }

        val newCancelled = hasSlidAway && (distPx <= thresholdPx || distMs <= thresholdMs)
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
