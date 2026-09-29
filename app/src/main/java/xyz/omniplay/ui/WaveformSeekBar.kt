package xyz.omniplay.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import xyz.omniplay.R
import java.util.Random
import kotlin.math.max

class WaveformSeekBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    interface OnWaveformSeekListener {
        fun onStartTracking()
        fun onProgressChanged(progressMs: Long, fromUser: Boolean)
        fun onStopTracking(progressMs: Long)
    }

    var seekListener: OnWaveformSeekListener? = null

    private var maxDurationMs: Long = 1000L
    private var currentProgressMs: Long = 0L
    private var displayedProgressMs: Float = 0f
    private var isUserDragging: Boolean = false

    private val density = context.resources.displayMetrics.density
    private val barWidth = 3f * density
    private val barGap = 2.2f * density
    private val barCornerRadius = 1.5f * density
    private val thumbRadius = 6f * density

    private val activePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.primary_accent)
        style = Paint.Style.FILL
    }

    private val inactivePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_track_inactive)
        style = Paint.Style.FILL
    }

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_thumb)
        style = Paint.Style.FILL
    }

    private val thumbGlowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.slider_halo)
        style = Paint.Style.FILL
    }

    private val barRect = RectF()
    private var amplitudes = FloatArray(0)
    private var currentSongSeed: Long = 0L

    private var smoothAnimator: ValueAnimator? = null

    init {
        isClickable = true
        isFocusable = true
    }

    fun setDuration(durationMs: Long) {
        maxDurationMs = max(1L, durationMs)
        currentProgressMs = 0L
        displayedProgressMs = 0f
        smoothAnimator?.cancel()
        invalidate()
    }

    fun setSongSeed(seed: Long) {
        currentSongSeed = seed
        regenerateWaveform()
        invalidate()
    }

    fun setProgress(progressMs: Long) {
        if (isUserDragging) return
        val clamped = progressMs.coerceIn(0L, maxDurationMs)
        currentProgressMs = clamped

        smoothAnimator?.cancel()
        smoothAnimator = ValueAnimator.ofFloat(displayedProgressMs, clamped.toFloat()).apply {
            duration = 100
            interpolator = LinearInterpolator()
            addUpdateListener { anim ->
                displayedProgressMs = anim.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        regenerateWaveform()
    }

    private fun regenerateWaveform() {
        val usableWidth = width.toFloat() - paddingLeft - paddingRight
        if (usableWidth <= 0) return

        val totalBarSpace = barWidth + barGap
        val count = (usableWidth / totalBarSpace).toInt().coerceAtLeast(10)
        amplitudes = FloatArray(count)

        val rng = Random(currentSongSeed)
        var prev = 0.4f
        for (i in 0 until count) {
            val progressRatio = i.toFloat() / count
            val envelope = when {
                progressRatio < 0.08f -> 0.35f + progressRatio * 5f
                progressRatio > 0.92f -> 0.35f + (1f - progressRatio) * 5f
                else -> 0.7f + 0.3f * Math.sin(progressRatio * Math.PI * 6.0).toFloat()
            }
            val rand = 0.25f + 0.75f * rng.nextFloat()
            val height = (prev * 0.35f + (rand * envelope) * 0.65f).coerceIn(0.15f, 1.0f)
            prev = height
            amplitudes[i] = height
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (amplitudes.isEmpty() || maxDurationMs <= 0) return

        val totalBarSpace = barWidth + barGap
        val startX = paddingLeft.toFloat()
        val centerY = height / 2f
        val maxBarHeight = height - paddingTop - paddingBottom - (thumbRadius * 2)

        val progressRatio = (displayedProgressMs / maxDurationMs).coerceIn(0f, 1f)
        val progressX = startX + progressRatio * (amplitudes.size * totalBarSpace)

        for (i in amplitudes.indices) {
            val barX = startX + i * totalBarSpace
            val barH = amplitudes[i] * maxBarHeight
            val halfH = barH / 2f

            barRect.set(barX, centerY - halfH, barX + barWidth, centerY + halfH)

            if (barX + barWidth <= progressX) {
                canvas.drawRoundRect(barRect, barCornerRadius, barCornerRadius, activePaint)
            } else if (barX >= progressX) {
                canvas.drawRoundRect(barRect, barCornerRadius, barCornerRadius, inactivePaint)
            } else {
                barRect.right = progressX
                canvas.drawRoundRect(barRect, barCornerRadius, barCornerRadius, activePaint)
                barRect.left = progressX
                barRect.right = barX + barWidth
                canvas.drawRoundRect(barRect, barCornerRadius, barCornerRadius, inactivePaint)
            }
        }

        if (isEnabled && maxDurationMs > 0) {
            val clampedThumbX = progressX.coerceIn(startX, startX + amplitudes.size * totalBarSpace)
            canvas.drawCircle(clampedThumbX, centerY, thumbRadius * 1.8f, thumbGlowPaint)
            canvas.drawCircle(clampedThumbX, centerY, thumbRadius, thumbPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) return false

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isUserDragging = true
                parent?.requestDisallowInterceptTouchEvent(true)
                seekListener?.onStartTracking()
                smoothAnimator?.cancel()
                updateTouchProgress(event.x)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (isUserDragging) {
                    updateTouchProgress(event.x)
                }
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (isUserDragging) {
                    isUserDragging = false
                    updateTouchProgress(event.x)
                    seekListener?.onStopTracking(currentProgressMs)
                }
                parent?.requestDisallowInterceptTouchEvent(false)
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateTouchProgress(touchX: Float) {
        val totalBarSpace = barWidth + barGap
        val totalWaveformWidth = amplitudes.size * totalBarSpace
        val startX = paddingLeft.toFloat()

        if (totalWaveformWidth <= 0) return

        val fraction = ((touchX - startX) / totalWaveformWidth).coerceIn(0f, 1f)
        val progress = (fraction * maxDurationMs).toLong()
        currentProgressMs = progress
        displayedProgressMs = progress.toFloat()
        invalidate()
        seekListener?.onProgressChanged(progress, true)
    }

    fun isTracking(): Boolean = isUserDragging
}
