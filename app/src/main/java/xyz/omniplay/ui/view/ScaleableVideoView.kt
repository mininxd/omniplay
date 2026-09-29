package xyz.omniplay.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * Custom VideoView that supports Fit (aspect ratio), Fill (crop to screen), and Stretch modes.
 */
class ScaleableVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : VideoView(context, attrs, defStyleAttr) {

    enum class ScaleMode {
        FIT,
        FILL,
        STRETCH
    }

    var scaleMode: ScaleMode = ScaleMode.FIT
        set(value) {
            field = value
            requestLayout()
            invalidate()
        }

    private var videoWidth = 0
    private var videoHeight = 0

    fun setVideoDimensions(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            videoWidth = width
            videoHeight = height
            requestLayout()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getDefaultSize(videoWidth, widthMeasureSpec)
        val height = getDefaultSize(videoHeight, heightMeasureSpec)

        if (videoWidth > 0 && videoHeight > 0) {
            when (scaleMode) {
                ScaleMode.STRETCH -> {
                    setMeasuredDimension(width, height)
                }
                ScaleMode.FILL -> {
                    // Zoom/crop to fill container completely
                    var measuredWidth = width
                    var measuredHeight = height
                    if (videoWidth * height > width * videoHeight) {
                        measuredWidth = height * videoWidth / videoHeight
                    } else {
                        measuredHeight = width * videoHeight / videoWidth
                    }
                    setMeasuredDimension(measuredWidth, measuredHeight)
                }
                ScaleMode.FIT -> {
                    // Maintain aspect ratio within container
                    var measuredWidth = width
                    var measuredHeight = height
                    if (videoWidth * height < width * videoHeight) {
                        measuredWidth = height * videoWidth / videoHeight
                    } else if (videoWidth * height > width * videoHeight) {
                        measuredHeight = width * videoHeight / videoWidth
                    }
                    setMeasuredDimension(measuredWidth, measuredHeight)
                }
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
