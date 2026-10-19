package xyz.omniplay.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.os.SystemClock
import android.util.AttributeSet
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.ColorUtils
import xyz.omniplay.util.ThemeColors
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Interactive Graphic Equalizer Bars View styled after Material 3 Expressive
 * and reference vertical pill-rack bar aesthetics.
 *
 * Displays 10 vertical rounded capsule columns (32 Hz to 16 kHz) side-by-side:
 * - 0 dB center reference dashed guideline.
 * - Pill-shaped tracks and filled capsules rising or falling relative to 0 dB.
 * - Real-time touch dragging, swipe-curve drawing, and double-tap reset.
 * - Active pill badge highlight matching the featured pillar aesthetic.
 */
class DaxEqualizerBarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    interface OnBandGainChangeListener {
        fun onBandGainChanged(bandIndex: Int, gainDb: Int, fromUser: Boolean)
    }

    private val bandGains = IntArray(10) { 0 }
    private val bandFrequencies = intArrayOf(32, 64, 125, 250, 500, 1000, 2000, 4000, 8000, 16000)
    private val bandLabels = arrayOf("32", "64", "125", "250", "500", "1k", "2k", "4k", "8k", "16k")

    var listener: OnBandGainChangeListener? = null

    private var activeBandIndex: Int = -1
    private var lastTapTime: Long = 0L
    private var lastTapBand: Int = -1

    // Density helpers
    private val Float.dp get() = this * resources.displayMetrics.density
    private val Int.dp get() = this * resources.displayMetrics.density
    private val Float.sp get() = this * resources.displayMetrics.scaledDensity
    private val Int.sp get() = this * resources.displayMetrics.scaledDensity

    // Paints
    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val activeBarPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val highlightedBarPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val guidelinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.5f.dp
    }

    private val frequencyTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
    }

    private val gainTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("sans-serif-bold", Typeface.BOLD)
    }

    private val badgeBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val badgeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("sans-serif-bold", Typeface.BOLD)
    }

    private val tempRect = RectF()

    init {
        isClickable = true
        isFocusable = true
        guidelinePaint.pathEffect = DashPathEffect(floatArrayOf(4.dp, 4.dp), 0f)
    }

    fun setBandGains(levels: IntArray) {
        var changed = false
        for (i in 0 until minOf(bandGains.size, levels.size)) {
            val clamped = levels[i].coerceIn(-12, 12)
            if (bandGains[i] != clamped) {
                bandGains[i] = clamped
                changed = true
            }
        }
        if (changed) {
            invalidate()
        }
    }

    fun getBandGain(bandIndex: Int): Int {
        if (bandIndex !in 0 until 10) return 0
        return bandGains[bandIndex]
    }

    fun setBandGain(bandIndex: Int, gainDb: Int) {
        if (bandIndex !in 0 until 10) return
        val clamped = gainDb.coerceIn(-12, 12)
        if (bandGains[bandIndex] != clamped) {
            bandGains[bandIndex] = clamped
            invalidate()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) return false

        val contentWidth = (width - paddingLeft - paddingRight).toFloat()
        if (contentWidth <= 0) return false

        val slotWidth = contentWidth / 10f
        val x = event.x - paddingLeft
        val y = event.y

        val topMargin = 32.dp
        val bottomMargin = 26.dp
        val usableTrackHeight = height - paddingTop - paddingBottom - topMargin - bottomMargin
        val centerY = paddingTop + topMargin + usableTrackHeight / 2f
        val halfTrackHeight = usableTrackHeight / 2f

        val touchedBand = (x / slotWidth).toInt().coerceIn(0, 9)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                activeBandIndex = touchedBand

                // Check for double tap reset to 0 dB
                val now = SystemClock.uptimeMillis()
                if (touchedBand == lastTapBand && (now - lastTapTime) < 300) {
                    bandGains[touchedBand] = 0
                    listener?.onBandGainChanged(touchedBand, 0, fromUser = true)
                    performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    invalidate()
                    lastTapTime = 0L
                    lastTapBand = -1
                    return true
                }
                lastTapTime = now
                lastTapBand = touchedBand

                updateBandFromY(touchedBand, y, centerY, halfTrackHeight)
                invalidate()
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                activeBandIndex = touchedBand
                updateBandFromY(touchedBand, y, centerY, halfTrackHeight)
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent?.requestDisallowInterceptTouchEvent(false)
                activeBandIndex = -1
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateBandFromY(band: Int, y: Float, centerY: Float, halfTrackHeight: Float) {
        val normalizedY = ((centerY - y) / halfTrackHeight).coerceIn(-1f, 1f)
        var newDb = (normalizedY * 12f).roundToInt().coerceIn(-12, 12)

        // Center snap: if very close to 0 dB, snap to 0
        if (abs(normalizedY * 12f) < 0.45f) {
            newDb = 0
        }

        if (bandGains[band] != newDb) {
            bandGains[band] = newDb
            performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
            listener?.onBandGainChanged(band, newDb, fromUser = true)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width
        val h = height
        if (w <= 0 || h <= 0) return

        val primaryColor = ThemeColors.getPrimary(context)
        val onPrimaryColor = ThemeColors.getOnPrimary(context)
        val surfaceHighest = ThemeColors.getSurfaceContainerHighest(context)
        val outlineVariant = ThemeColors.getOutlineVariant(context)
        val onSurfaceVariant = ThemeColors.getOnSurfaceVariant(context)
        val onSurface = ThemeColors.getOnSurface(context)

        // Featured highlight color (Vibrant Emerald / Material Green)
        val highlightColor = Color.parseColor("#34A853")

        val isViewEnabled = isEnabled
        val alphaMultiplier = if (isViewEnabled) 1.0f else 0.4f

        trackPaint.color = ColorUtils.setAlphaComponent(
            surfaceHighest,
            (Color.alpha(surfaceHighest) * alphaMultiplier).toInt()
        )
        activeBarPaint.color = ColorUtils.setAlphaComponent(
            primaryColor,
            (Color.alpha(primaryColor) * alphaMultiplier).toInt()
        )
        highlightedBarPaint.color = ColorUtils.setAlphaComponent(
            highlightColor,
            (Color.alpha(highlightColor) * alphaMultiplier).toInt()
        )
        guidelinePaint.color = ColorUtils.setAlphaComponent(
            outlineVariant,
            (Color.alpha(outlineVariant) * 0.7f * alphaMultiplier).toInt()
        )

        frequencyTextPaint.textSize = 10.5f.sp
        gainTextPaint.textSize = 11f.sp
        badgeTextPaint.textSize = 11f.sp

        val contentWidth = (w - paddingLeft - paddingRight).toFloat()
        val topMargin = 32.dp
        val bottomMargin = 26.dp
        val usableTrackHeight = h - paddingTop - paddingBottom - topMargin - bottomMargin
        val centerY = paddingTop + topMargin + usableTrackHeight / 2f
        val halfTrackHeight = usableTrackHeight / 2f

        val slotWidth = contentWidth / 10f
        // Pillar width: 62% of slot, capped between 14dp and 26dp
        val barWidth = (slotWidth * 0.62f).coerceIn(14.dp, 26.dp)
        val barRadius = barWidth / 2f

        // 1. Draw 0 dB Reference Guideline across all pillars
        val lineLeft = paddingLeft + slotWidth * 0.2f
        val lineRight = w - paddingRight - slotWidth * 0.2f
        canvas.drawLine(lineLeft, centerY, lineRight, centerY, guidelinePaint)

        // 2. Draw each of the 10 bands
        for (i in 0 until 10) {
            val centerX = paddingLeft + (i + 0.5f) * slotWidth
            val gain = bandGains[i]
            val isActive = (i == activeBandIndex)

            // Background Track Pill (Full height column)
            val trackTop = centerY - halfTrackHeight
            val trackBottom = centerY + halfTrackHeight
            tempRect.set(
                centerX - barRadius,
                trackTop,
                centerX + barRadius,
                trackBottom
            )
            canvas.drawRoundRect(tempRect, barRadius, barRadius, trackPaint)

            // Active Filled Bar
            val chosenBarPaint = if (isActive) highlightedBarPaint else activeBarPaint

            if (gain == 0) {
                // At 0 dB: draw a subtle centered circle / pill disc
                val discRadius = barRadius * 0.75f
                tempRect.set(
                    centerX - discRadius,
                    centerY - discRadius,
                    centerX + discRadius,
                    centerY + discRadius
                )
                canvas.drawRoundRect(tempRect, discRadius, discRadius, chosenBarPaint)
            } else if (gain > 0) {
                // Positive boost: extends upwards from centerY
                val barTop = centerY - (gain / 12f) * halfTrackHeight
                tempRect.set(
                    centerX - barRadius,
                    barTop,
                    centerX + barRadius,
                    centerY + barRadius
                )
                canvas.drawRoundRect(tempRect, barRadius, barRadius, chosenBarPaint)
            } else {
                // Negative cut: extends downwards from centerY
                val barBottom = centerY + (abs(gain) / 12f) * halfTrackHeight
                tempRect.set(
                    centerX - barRadius,
                    centerY - barRadius,
                    centerX + barRadius,
                    barBottom
                )
                canvas.drawRoundRect(tempRect, barRadius, barRadius, chosenBarPaint)
            }

            // 3. Top Gain Label / Active Floating Badge
            if (isActive) {
                // Draw floating badge above the bar (like the checkmark badge in screenshot)
                val badgeText = if (gain > 0) "+$gain dB" else if (gain == 0) "0 dB" else "$gain dB"
                val textWidth = badgeTextPaint.measureText(badgeText)
                val badgePaddingH = 7.dp
                val badgeH = 20.dp
                val badgeW = textWidth + badgePaddingH * 2
                val badgeY = paddingTop + (topMargin - badgeH) / 2f

                badgeBgPaint.color = highlightColor
                tempRect.set(
                    centerX - badgeW / 2f,
                    badgeY,
                    centerX + badgeW / 2f,
                    badgeY + badgeH
                )
                canvas.drawRoundRect(tempRect, badgeH / 2f, badgeH / 2f, badgeBgPaint)

                badgeTextPaint.color = Color.WHITE
                val fontMetrics = badgeTextPaint.fontMetrics
                val textBaseline = badgeY + (badgeH - fontMetrics.bottom + fontMetrics.top) / 2f - fontMetrics.top
                canvas.drawText(badgeText, centerX, textBaseline, badgeTextPaint)
            } else if (gain != 0) {
                // Static subtle gain indicator when non-zero
                val gainText = if (gain > 0) "+$gain" else "$gain"
                gainTextPaint.color = ColorUtils.setAlphaComponent(
                    onSurface,
                    (200 * alphaMultiplier).toInt()
                )
                val textY = paddingTop + topMargin - 6.dp
                canvas.drawText(gainText, centerX, textY, gainTextPaint)
            }

            // 4. Bottom Frequency Label
            frequencyTextPaint.color = if (isActive) {
                primaryColor
            } else {
                ColorUtils.setAlphaComponent(onSurfaceVariant, (220 * alphaMultiplier).toInt())
            }
            if (isActive) {
                frequencyTextPaint.typeface = Typeface.create("sans-serif-bold", Typeface.BOLD)
            } else {
                frequencyTextPaint.typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
            }

            val freqY = h - paddingBottom - 6.dp
            canvas.drawText(bandLabels[i], centerX, freqY, frequencyTextPaint)
        }
    }
}
