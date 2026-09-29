package xyz.omniplay.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import xyz.omniplay.R
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * Android 13/14 (Tiramisu/UpsideDownCake) native style Squiggly Progress Line Seek Bar.
 * When playing, a lively squiggly wave flows along the played portion of the track.
 * When paused, the wave settles into a calm line.
 * Moves continuously and smoothly without discrete step jumps.
 */
class SquigglySeekBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    interface OnSeekListener {
        fun onStartTracking()
        fun onProgressChanged(progressMs: Long, fromUser: Boolean)
        fun onStopTracking(progressMs: Long)
    }

    var seekListener: OnSeekListener? = null

    private var maxDurationMs: Long = 1000L
    private var currentProgressMs: Long = 0L
    private var displayedProgressMs: Float = 0f
    private var isUserDragging: Boolean = false
    private var isPlaying: Boolean = false

    private val density = context.resources.displayMetrics.density
    private val strokeWidthPx = 4f * density
    private val waveAmplitudePx = 3.5f * density
    private val waveLengthPx = 22f * density
    private val thumbRadiusPx = 7f * density
    private val thumbHaloRadiusPx = 14f * density

    private var wavePhase: Float = 0f
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

            val activeDist = progressX - startX
            // Use 2dp steps along X for smooth curve rendering
            val step = max(2f, 2f * density)
            var x = startX

            while (x <= progressX) {
                val relX = x - startX
                // Envelope ramp-up at beginning and ramp-down at thumb for smooth connection
                val rampIn = min(1f, relX / (waveLengthPx * 0.75f))
                val rampOut = min(1f, (progressX - x) / (waveLengthPx * 0.75f))
                val ramp = min(rampIn, rampOut)

                val effectiveAmp = if (isPlaying) waveAmplitudePx * ramp else (waveAmplitudePx * 0.35f) * ramp
                val angle = (relX / waveLengthPx) * (2 * PI).toFloat() - wavePhase
                val y = centerY + effectiveAmp * sin(angle)

                squigglyPath.lineTo(x, y)
                x += step
            }
            // Ensure path ends right at progressX and centerY
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
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) return false

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isUserDragging = true
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
                    isUserDragging = false
                    updateTouchPosition(event.x)
                    seekListener?.onStopTracking(currentProgressMs)
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
        invalidate()
        seekListener?.onProgressChanged(progress, true)
    }

    fun isTracking(): Boolean = isUserDragging
}
