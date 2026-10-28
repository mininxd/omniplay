package xyz.omniplay.equalizer.ui

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
import android.view.ViewConfiguration
import androidx.core.graphics.ColorUtils
import xyz.omniplay.equalizer.util.ThemeColors
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * Interactive Graphic Equalizer Slider Rack designed strictly in accordance with
 * Material 3 Expressive Vertical Slider Specifications:
 *
 * - Vertical rectangular track with subtle M3 rounded corners (6.dp corner radius).
 * - Filled from bottom up to current value (-12 dB at bottom, 0 dB at 50% midpoint, +12 dB at top).
 * - Distinct M3 handle indicator bar at the active fill level.
 * - 0 dB center reference dashed guideline across all bands.
 * - Touch position strictly locked to initial touched band during drag gestures.
 * - Clean frequency labels and dynamic M3 floating value badges.
 */
class DaxEqualizerBarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    interface OnBandGainChangeListener {
        fun onBandGainChanged(bandIndex: Int, gainDb: Int, fromUser: Boolean)
        fun onBandGainChangeFinished() {}
    }

    private val bandGains = IntArray(10) { 0 }
    private val bandFrequencies = intArrayOf(32, 64, 125, 250, 500, 1000, 2000, 4000, 8000, 16000)
    private val bandLabels = arrayOf("32", "64", "125", "250", "500", "1k", "2k", "4k", "8k", "16k")

    var listener: OnBandGainChangeListener? = null

    private var activeBandIndex: Int = -1
    private var lockedBandIndex: Int = -1
    private var lastTapTime: Long = 0L
    private var lastTapBand: Int = -1
    private var touchDownY: Float = 0f
    private var initialBandGain: Float = 0f
    private var isDragging: Boolean = false
    val isUserDragging: Boolean get() = isDragging || lockedBandIndex != -1
    private val touchSlop: Float by lazy {
        ViewConfiguration.get(context).scaledTouchSlop.toFloat()
    }

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

    private val inactiveBarPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val handlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
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
        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
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

        val horizontalMargin = 2.dp
        val contentWidth = (width - paddingLeft - paddingRight - horizontalMargin * 2).toFloat()
        if (contentWidth <= 0) return false

        val slotWidth = contentWidth / 10f
        val x = event.x - paddingLeft - horizontalMargin
        val y = event.y

        val topMargin = 22.dp
        val bottomMargin = 20.dp
        val usableTrackHeight = height - paddingTop - paddingBottom - topMargin - bottomMargin
        if (usableTrackHeight <= 0) return false

        val touchedBand = (x / slotWidth).toInt().coerceIn(0, 9)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                lockedBandIndex = touchedBand
                activeBandIndex = touchedBand
                touchDownY = y
                initialBandGain = bandGains[touchedBand].toFloat()
                isDragging = false

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

                invalidate()
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                val targetBand = if (lockedBandIndex in 0 until 10) lockedBandIndex else touchedBand
                activeBandIndex = targetBand

                val deltaY = touchDownY - y
                if (!isDragging && abs(deltaY) > touchSlop) {
                    isDragging = true
                    lastTapTime = 0L
                    lastTapBand = -1
                    touchDownY = if (deltaY > 0) y + touchSlop else y - touchSlop
                }

                if (isDragging) {
                    val currentDeltaY = touchDownY - y
                    val deltaGain = (currentDeltaY / usableTrackHeight) * 24f
                    var calculatedGain = initialBandGain + deltaGain

                    if (calculatedGain > 12f) {
                        initialBandGain = 12f
                        touchDownY = y
                        calculatedGain = 12f
                    } else if (calculatedGain < -12f) {
                        initialBandGain = -12f
                        touchDownY = y
                        calculatedGain = -12f
                    }

                    var newDb = calculatedGain.roundToInt().coerceIn(-12, 12)

                    if (abs(calculatedGain) < 0.45f) {
                        newDb = 0
                    }

                    if (bandGains[targetBand] != newDb) {
                        bandGains[targetBand] = newDb
                        performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                        listener?.onBandGainChanged(targetBand, newDb, fromUser = true)
                    }
                    invalidate()
                }
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent?.requestDisallowInterceptTouchEvent(false)
                lockedBandIndex = -1
                activeBandIndex = -1
                isDragging = false
                listener?.onBandGainChangeFinished()
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width
        val h = height
        if (w <= 0 || h <= 0) return

        val primaryColor = ThemeColors.getPrimary(context)
        val onPrimaryColor = ThemeColors.getOnPrimary(context)
        val primaryContainer = ThemeColors.getPrimaryContainer(context)
        val surfaceHighest = ThemeColors.getSurfaceContainerHighest(context)
        val outlineVariant = ThemeColors.getOutlineVariant(context)
        val onSurfaceVariant = ThemeColors.getOnSurfaceVariant(context)
        val onSurface = ThemeColors.getOnSurface(context)

        val isViewEnabled = isEnabled
        val alphaMultiplier = if (isViewEnabled) 1.0f else 0.38f

        trackPaint.color = ColorUtils.setAlphaComponent(
            surfaceHighest,
            (Color.alpha(surfaceHighest) * alphaMultiplier).toInt()
        )
        activeBarPaint.color = ColorUtils.setAlphaComponent(
            primaryColor,
            (Color.alpha(primaryColor) * alphaMultiplier).toInt()
        )
        val restingBarColor = ColorUtils.blendARGB(primaryContainer, primaryColor, 0.40f)
        inactiveBarPaint.color = ColorUtils.setAlphaComponent(
            restingBarColor,
            (Color.alpha(restingBarColor) * alphaMultiplier).toInt()
        )
        handlePaint.color = ColorUtils.setAlphaComponent(
            primaryColor,
            (Color.alpha(primaryColor) * alphaMultiplier).toInt()
        )
        guidelinePaint.color = ColorUtils.setAlphaComponent(
            outlineVariant,
            (Color.alpha(outlineVariant) * 0.7f * alphaMultiplier).toInt()
        )

        frequencyTextPaint.textSize = 10.5f.sp
        gainTextPaint.textSize = 10f.sp
        badgeTextPaint.textSize = 10.5f.sp

        val horizontalMargin = 2.dp
        val contentWidth = (w - paddingLeft - paddingRight - horizontalMargin * 2).toFloat()
        val topMargin = 22.dp
        val bottomMargin = 20.dp
        val usableTrackHeight = h - paddingTop - paddingBottom - topMargin - bottomMargin
        val trackTop = paddingTop + topMargin
        val trackBottom = trackTop + usableTrackHeight
        val centerY = trackTop + usableTrackHeight / 2f

        val slotWidth = contentWidth / 10f
        val barWidth = (slotWidth * 0.72f).coerceIn(12.dp, 28.dp)
        val cornerRadius = 6.dp

        // 1. Draw 0 dB Reference Guideline
        val lineLeft = paddingLeft + horizontalMargin + slotWidth * 0.1f
        val lineRight = w - paddingRight - horizontalMargin - slotWidth * 0.1f
        canvas.drawLine(lineLeft, centerY, lineRight, centerY, guidelinePaint)

        // 2. Draw each of the 10 bands
        for (i in 0 until 10) {
            val centerX = paddingLeft + horizontalMargin + (i + 0.5f) * slotWidth
            val gain = bandGains[i]
            val isActive = (i == activeBandIndex)

            tempRect.set(
                centerX - barWidth / 2f,
                trackTop,
                centerX + barWidth / 2f,
                trackBottom
            )
            canvas.drawRoundRect(tempRect, cornerRadius, cornerRadius, trackPaint)

            val fraction = (gain + 12f) / 24f
            val fillTop = trackBottom - (usableTrackHeight * fraction)
            val chosenBarPaint = if (isActive) activeBarPaint else inactiveBarPaint

            if (fraction > 0f) {
                tempRect.set(
                    centerX - barWidth / 2f,
                    fillTop,
                    centerX + barWidth / 2f,
                    trackBottom
                )
                canvas.drawRoundRect(tempRect, cornerRadius, cornerRadius, chosenBarPaint)
            }

            val handleHeight = 5.dp
            val handleWidth = barWidth + 4.dp
            tempRect.set(
                centerX - handleWidth / 2f,
                fillTop - handleHeight / 2f,
                centerX + handleWidth / 2f,
                fillTop + handleHeight / 2f
            )
            canvas.drawRoundRect(tempRect, 2.5f.dp, 2.5f.dp, handlePaint)

            if (isActive) {
                val badgeText = if (gain > 0) "+$gain dB" else if (gain == 0) "0 dB" else "$gain dB"
                val textWidth = badgeTextPaint.measureText(badgeText)
                val badgePaddingH = 6.dp
                val badgeH = 18.dp
                val badgeW = textWidth + badgePaddingH * 2
                val badgeY = paddingTop + (topMargin - badgeH) / 2f

                badgeBgPaint.color = primaryColor
                tempRect.set(
                    centerX - badgeW / 2f,
                    badgeY,
                    centerX + badgeW / 2f,
                    badgeY + badgeH
                )
                canvas.drawRoundRect(tempRect, badgeH / 2f, badgeH / 2f, badgeBgPaint)

                badgeTextPaint.color = onPrimaryColor
                val fontMetrics = badgeTextPaint.fontMetrics
                val textBaseline = badgeY + (badgeH - fontMetrics.bottom + fontMetrics.top) / 2f - fontMetrics.top
                canvas.drawText(badgeText, centerX, textBaseline, badgeTextPaint)
            } else {
                val gainText = if (gain > 0) "+$gain" else if (gain < 0) "$gain" else "0"
                gainTextPaint.color = if (gain != 0) {
                    ColorUtils.setAlphaComponent(primaryColor, (230 * alphaMultiplier).toInt())
                } else {
                    ColorUtils.setAlphaComponent(onSurfaceVariant, (160 * alphaMultiplier).toInt())
                }
                val textY = paddingTop + topMargin - 4.dp
                canvas.drawText(gainText, centerX, textY, gainTextPaint)
            }

            frequencyTextPaint.color = if (isActive) {
                primaryColor
            } else {
                ColorUtils.setAlphaComponent(
                    onSurfaceVariant,
                    (210 * alphaMultiplier).toInt()
                )
            }
            val freqY = trackBottom + 14.dp
            canvas.drawText(bandLabels[i], centerX, freqY, frequencyTextPaint)
        }
    }
}
