package xyz.omniplay.ui

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import xyz.omniplay.R
import xyz.omniplay.databinding.BottomSheetLyricsShareBinding
import xyz.omniplay.model.Song
import xyz.omniplay.util.ThemeColors

class LyricsShareThemeBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLyricsShareBinding? = null
    private val binding get() = _binding!!
    private var selectedLyricStyle = LyricsShareImageRenderer.STYLE_EDITORIAL
    private var lyricsFontSizePx = DEFAULT_LYRICS_FONT_SIZE_PX
    private var cardWidthPx = LyricsShareImageRenderer.DEFAULT_CARD_WIDTH_PX
    private var cornerRadiusPx = LyricsShareImageRenderer.DEFAULT_CORNER_RADIUS_PX
    private var customPositionMs = -1L
    private var showTitle = true
    private var showArtist = true
    private var showProgress = true
    private var showBadge = true
    private var renderedBitmap: Bitmap? = null

    private val writePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            saveImage()
        } else {
            showToast(R.string.lyrics_image_permission_denied)
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onStart() {
        super.onStart()
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLyricsShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = requireArguments().getString(ARG_TITLE).orEmpty()
        val artist = requireArguments().getString(ARG_ARTIST).orEmpty()
        val durationMs = requireArguments().getLong(ARG_DURATION_MS).coerceAtLeast(1000L)
        if (customPositionMs < 0L) {
            customPositionMs = requireArguments().getLong(ARG_POSITION_MS).coerceIn(0L, durationMs)
        }

        updatePreviewWidth()

        binding.lyricsShareTrack.text = listOf(title, artist)
            .filter { it.isNotBlank() }
            .joinToString(" • ")

        // Card Width Slider
        binding.lyricsShareWidthSlider.valueFrom = LyricsShareImageRenderer.MIN_CARD_WIDTH_PX.toFloat()
        binding.lyricsShareWidthSlider.valueTo = LyricsShareImageRenderer.MAX_CARD_WIDTH_PX.toFloat()
        binding.lyricsShareWidthSlider.stepSize = 20f
        binding.lyricsShareWidthSlider.setLabelFormatter { value -> "${value.toInt()} px" }
        binding.lyricsShareWidthSlider.value = cardWidthPx.toFloat().coerceIn(
            LyricsShareImageRenderer.MIN_CARD_WIDTH_PX.toFloat(),
            LyricsShareImageRenderer.MAX_CARD_WIDTH_PX.toFloat()
        )
        binding.lyricsShareWidthValue.text = getString(R.string.lyrics_share_width_value, cardWidthPx)
        binding.lyricsShareWidthSlider.addOnChangeListener { _, value, _ ->
            cardWidthPx = value.toInt()
            binding.lyricsShareWidthValue.text = getString(R.string.lyrics_share_width_value, cardWidthPx)
            updatePreviewWidth()
            renderPreview()
        }

        // Corner Rounding Slider
        binding.lyricsShareCornersSlider.valueFrom = LyricsShareImageRenderer.MIN_CORNER_RADIUS_PX
        binding.lyricsShareCornersSlider.valueTo = LyricsShareImageRenderer.MAX_CORNER_RADIUS_PX
        binding.lyricsShareCornersSlider.stepSize = 4f
        binding.lyricsShareCornersSlider.setLabelFormatter { value -> "${value.toInt()} dp" }
        binding.lyricsShareCornersSlider.value = cornerRadiusPx.coerceIn(
            LyricsShareImageRenderer.MIN_CORNER_RADIUS_PX,
            LyricsShareImageRenderer.MAX_CORNER_RADIUS_PX
        )
        binding.lyricsShareCornersValue.text = getString(R.string.lyrics_share_corners_value, cornerRadiusPx.toInt())
        binding.lyricsShareCornersSlider.addOnChangeListener { _, value, _ ->
            cornerRadiusPx = value
            binding.lyricsShareCornersValue.text = getString(R.string.lyrics_share_corners_value, cornerRadiusPx.toInt())
            renderPreview()
        }

        binding.lyricsShareThemeGroup.check(R.id.lyrics_style_editorial)
        binding.lyricsShareThemeGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            selectedLyricStyle = when (checkedIds.firstOrNull()) {
                R.id.lyrics_style_quote -> LyricsShareImageRenderer.STYLE_QUOTE
                R.id.lyrics_style_studio -> LyricsShareImageRenderer.STYLE_STUDIO
                R.id.lyrics_style_minimalist -> LyricsShareImageRenderer.STYLE_MINIMALIST
                R.id.lyrics_style_atmospheric -> LyricsShareImageRenderer.STYLE_ATMOSPHERIC
                else -> LyricsShareImageRenderer.STYLE_EDITORIAL
            }
            renderPreview()
        }

        binding.lyricsShareFontSize.valueFrom = MIN_LYRICS_FONT_SIZE_PX.toFloat()
        binding.lyricsShareFontSize.valueTo = MAX_LYRICS_FONT_SIZE_PX.toFloat()
        binding.lyricsShareFontSize.stepSize = 4f
        binding.lyricsShareFontSize.value = lyricsFontSizePx.toFloat().coerceIn(
            MIN_LYRICS_FONT_SIZE_PX.toFloat(),
            MAX_LYRICS_FONT_SIZE_PX.toFloat()
        )
        binding.lyricsShareFontSizeValue.text = getString(R.string.lyrics_share_font_size_value, lyricsFontSizePx)
        binding.lyricsShareFontSize.addOnChangeListener { _, value, _ ->
            lyricsFontSizePx = value.toInt().coerceIn(MIN_LYRICS_FONT_SIZE_PX, MAX_LYRICS_FONT_SIZE_PX)
            binding.lyricsShareFontSizeValue.text = getString(R.string.lyrics_share_font_size_value, lyricsFontSizePx)
            renderPreview()
        }

        binding.lyricsShareIncludeTitle.isChecked = showTitle
        binding.lyricsShareIncludeTitle.setOnCheckedChangeListener { _, checked ->
            showTitle = checked
            renderPreview()
        }

        binding.lyricsShareIncludeArtist.isChecked = showArtist
        binding.lyricsShareIncludeArtist.setOnCheckedChangeListener { _, checked ->
            showArtist = checked
            renderPreview()
        }

        binding.lyricsShareIncludeProgress.isChecked = showProgress
        binding.lyricsShareIncludeProgress.setOnCheckedChangeListener { _, checked ->
            showProgress = checked
            binding.lyricsShareProgressLayout.visibility = if (checked) View.VISIBLE else View.GONE
            renderPreview()
        }

        binding.lyricsShareIncludeBadge.isChecked = showBadge
        binding.lyricsShareIncludeBadge.setOnCheckedChangeListener { _, checked ->
            showBadge = checked
            renderPreview()
        }

        // Progress customization slider
        binding.lyricsShareProgressLayout.visibility = if (showProgress) View.VISIBLE else View.GONE
        binding.lyricsShareProgressSlider.valueFrom = 0f
        binding.lyricsShareProgressSlider.valueTo = durationMs.toFloat()
        binding.lyricsShareProgressSlider.setLabelFormatter { value ->
            Song.formatTime(value.toLong())
        }
        binding.lyricsShareProgressSlider.value = customPositionMs.toFloat().coerceIn(0f, durationMs.toFloat())
        binding.lyricsShareProgressTimeValue.text = "${Song.formatTime(customPositionMs)} / ${Song.formatTime(durationMs)}"
        binding.lyricsShareProgressSlider.addOnChangeListener { _, value, _ ->
            customPositionMs = value.toLong()
            binding.lyricsShareProgressTimeValue.text = "${Song.formatTime(customPositionMs)} / ${Song.formatTime(durationMs)}"
            renderPreview()
        }

        binding.lyricsShareCopy.setOnClickListener { copyLyricsText() }
        binding.lyricsShareSave.setOnClickListener { requestSaveImage() }
        binding.lyricsShareSend.setOnClickListener { shareImage() }
        renderPreview()
    }

    override fun onDestroyView() {
        renderedBitmap = null
        _binding = null
        super.onDestroyView()
    }

    private fun updatePreviewWidth() {
        val screenW = resources.displayMetrics.widthPixels
        val ratio = cardWidthPx.toFloat() / LyricsShareImageRenderer.DEFAULT_CARD_WIDTH_PX.toFloat()
        val targetWidth = (screenW * 0.54f * ratio).toInt().coerceIn(
            (screenW * 0.36f).toInt(),
            (screenW * 0.85f).toInt()
        )
        binding.lyricsShareImagePreview.layoutParams = binding.lyricsShareImagePreview.layoutParams.apply {
            width = targetWidth
        }
    }

    private fun renderPreview() {
        val lines = requireArguments().getStringArrayList(ARG_LINES).orEmpty()
        val durationMs = requireArguments().getLong(ARG_DURATION_MS).coerceAtLeast(1000L)
        val posMs = if (customPositionMs >= 0L) customPositionMs else requireArguments().getLong(ARG_POSITION_MS)
        renderedBitmap = LyricsShareImageRenderer.render(
            requireContext(),
            lines,
            selectedLyricStyle,
            posMs,
            durationMs,
            lyricsFontSizePx,
            if (showTitle) requireArguments().getString(ARG_TITLE).orEmpty() else "",
            if (showArtist) requireArguments().getString(ARG_ARTIST).orEmpty() else "",
            showProgress,
            showBadge,
            cardWidthPx,
            cornerRadiusPx
        )
        binding.lyricsShareImagePreview.setImageBitmap(renderedBitmap)
    }

    private fun copyLyricsText() {
        val lines = requireArguments().getStringArrayList(ARG_LINES).orEmpty()
        val text = lines.joinToString("\n").trim()
        if (text.isEmpty()) return
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        clipboard?.setPrimaryClip(ClipData.newPlainText("Lyrics", text))
        showToast(R.string.lyrics_share_copied)
    }

    private fun requestSaveImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            writePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            return
        }
        saveImage()
    }

    private fun saveImage() {
        val bitmap = renderedBitmap ?: return
        try {
            if (LyricsShareImageRenderer.saveToPictures(requireContext(), bitmap)) {
                showToast(R.string.lyrics_image_saved)
            } else {
                showToast(R.string.lyrics_image_save_failed)
            }
        } catch (_: Exception) {
            showToast(R.string.lyrics_image_save_failed)
        }
    }

    private fun shareImage() {
        val bitmap = renderedBitmap ?: return
        try {
            val context = requireContext()
            val uri = LyricsShareImageRenderer.writeShareCopy(context, bitmap)
            val title = if (showTitle) requireArguments().getString(ARG_TITLE).orEmpty() else ""
            val artist = if (showArtist) requireArguments().getString(ARG_ARTIST).orEmpty() else ""
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, title)
                putExtra(Intent.EXTRA_TEXT, listOf(title, artist).filter { it.isNotBlank() }.joinToString(" • "))
                clipData = ClipData.newUri(context.contentResolver, getString(R.string.lyrics_share_chooser), uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(shareIntent, getString(R.string.lyrics_share_chooser)))
        } catch (_: Exception) {
            showToast(R.string.lyrics_image_share_failed)
        }
    }

    private fun showToast(messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "LyricsShareThemeBottomSheet"
        private const val ARG_TITLE = "title"
        private const val ARG_ARTIST = "artist"
        private const val ARG_LINES = "lines"
        private const val ARG_POSITION_MS = "position_ms"
        private const val ARG_DURATION_MS = "duration_ms"
        private const val DEFAULT_LYRICS_FONT_SIZE_PX = 120
        private const val MIN_LYRICS_FONT_SIZE_PX = 60
        private const val MAX_LYRICS_FONT_SIZE_PX = 200

        fun newInstance(
            title: String,
            artist: String,
            lines: List<String>,
            positionMs: Long,
            durationMs: Long
        ) =
            LyricsShareThemeBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_ARTIST, artist)
                    putStringArrayList(ARG_LINES, ArrayList(lines))
                    putLong(ARG_POSITION_MS, positionMs)
                    putLong(ARG_DURATION_MS, durationMs)
                }
            }
    }
}

private object LyricsShareImageRenderer {
    const val STYLE_EDITORIAL = 0
    const val STYLE_QUOTE = 1
    const val STYLE_STUDIO = 2
    const val STYLE_MINIMALIST = 3
    const val STYLE_ATMOSPHERIC = 4

    const val DEFAULT_CARD_WIDTH_PX = 1080
    const val MIN_CARD_WIDTH_PX = 680
    const val MAX_CARD_WIDTH_PX = 1240

    const val DEFAULT_CORNER_RADIUS_PX = 36f
    const val MIN_CORNER_RADIUS_PX = 0f
    const val MAX_CORNER_RADIUS_PX = 72f

    fun render(
        context: Context,
        lines: List<String>,
        lyricStyle: Int,
        positionMs: Long,
        durationMs: Long,
        lyricsFontSizePx: Int,
        title: String,
        artist: String,
        showProgress: Boolean,
        showBadge: Boolean = true,
        cardWidthPx: Int = DEFAULT_CARD_WIDTH_PX,
        cornerRadiusPx: Float = DEFAULT_CORNER_RADIUS_PX
    ): Bitmap {
        val palette = getAppThemePalette(context)
        val quote = lines.joinToString("\n").trim().ifBlank { "♪" }
        val width = cardWidthPx.coerceIn(500, 2000)
        val horizontalInset = (width * 0.08f).coerceIn(48f, 100f)
        val bodyWidth = (width - horizontalInset * 2).toInt()

        val quotePaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.foreground
            textSize = lyricsFontSizePx.toFloat().coerceIn(40f, 240f)
            typeface = when (lyricStyle) {
                STYLE_QUOTE -> Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                STYLE_MINIMALIST -> Typeface.create("sans-serif-medium", Typeface.NORMAL)
                STYLE_STUDIO -> Typeface.create("sans-serif-bold", Typeface.NORMAL)
                else -> Typeface.create("sans-serif-black", Typeface.NORMAL)
            }
            letterSpacing = when (lyricStyle) {
                STYLE_QUOTE -> 0.0f
                STYLE_MINIMALIST -> 0.01f
                else -> -0.02f
            }
        }

        val alignment = if (lyricStyle == STYLE_STUDIO) {
            Layout.Alignment.ALIGN_CENTER
        } else {
            Layout.Alignment.ALIGN_NORMAL
        }

        fun createQuoteLayout(): StaticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(quote, 0, quote.length, quotePaint, bodyWidth)
                .setAlignment(alignment)
                .setLineSpacing(26f, 1f)
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(quote, quotePaint, bodyWidth, alignment, 1f, 26f, false)
        }

        var quoteLayout = createQuoteLayout()
        while (quoteLayout.height > MAX_QUOTE_HEIGHT && quotePaint.textSize > MIN_QUOTE_TEXT_SIZE) {
            quotePaint.textSize -= 6f
            quoteLayout = createQuoteLayout()
        }

        val topPadding = if (showBadge) 140f else 84f
        val metadata = listOf(title, artist).filter { it.isNotBlank() }
        val metadataHeight = if (metadata.isEmpty()) {
            0f
        } else {
            72f + metadata.size * 50f + 14f
        }
        val lastContentBottomY = topPadding + quoteLayout.height + metadataHeight
        // Margin between artist/metadata and progress bar reduced by 40% (86f)
        val progressY = lastContentBottomY + 86f
        val durationLabelsY = progressY + 47f
        val contentEndHeight = if (showProgress) (durationLabelsY + 48f).toInt() else (lastContentBottomY + 72f).toInt()
        val height = maxOf(MIN_IMAGE_HEIGHT, contentEndHeight)

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        if (cornerRadiusPx > 0f) {
            val roundPath = Path().apply {
                addRoundRect(
                    RectF(0f, 0f, width.toFloat(), height.toFloat()),
                    cornerRadiusPx,
                    cornerRadiusPx,
                    Path.Direction.CW
                )
            }
            canvas.clipPath(roundPath)
        }

        // Draw rich surface background matching app theme
        canvas.drawColor(palette.background)

        // Draw style-specific visual treatment
        when (lyricStyle) {
            STYLE_QUOTE -> {
                val quoteWatermarkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.argb(26, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent))
                    textSize = 320f
                    typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
                }
                canvas.drawText("“", horizontalInset - 16f, topPadding + 64f, quoteWatermarkPaint)

                val gradient = LinearGradient(
                    0f, 0f, width.toFloat(), height.toFloat() * 0.6f,
                    palette.topGradientTint, Color.TRANSPARENT,
                    Shader.TileMode.CLAMP
                )
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), Paint(Paint.ANTI_ALIAS_FLAG).apply { shader = gradient })
            }
            STYLE_STUDIO -> {
                val studioGlow = RadialGradient(
                    width / 2f,
                    topPadding + quoteLayout.height * 0.5f,
                    width * 0.65f,
                    Color.argb(45, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent)),
                    Color.TRANSPARENT,
                    Shader.TileMode.CLAMP
                )
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), Paint(Paint.ANTI_ALIAS_FLAG).apply { shader = studioGlow })
            }
            STYLE_MINIMALIST -> {
                val innerMargin = 20f
                val innerRect = RectF(innerMargin, innerMargin, width - innerMargin, height - innerMargin)
                val framePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.argb(48, Color.red(palette.track), Color.green(palette.track), Color.blue(palette.track))
                    style = Paint.Style.STROKE
                    strokeWidth = 2.5f
                }
                val innerRadius = (cornerRadiusPx - innerMargin * 0.5f).coerceAtLeast(0f)
                canvas.drawRoundRect(innerRect, innerRadius, innerRadius, framePaint)
            }
            STYLE_ATMOSPHERIC -> {
                val bloomPaintTop = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    shader = RadialGradient(
                        width * 0.85f,
                        height * 0.18f,
                        width * 0.6f,
                        Color.argb(55, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent)),
                        Color.TRANSPARENT,
                        Shader.TileMode.CLAMP
                    )
                }
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bloomPaintTop)

                val bloomPaintBottom = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    shader = RadialGradient(
                        width * 0.15f,
                        height * 0.82f,
                        width * 0.55f,
                        Color.argb(35, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent)),
                        Color.TRANSPARENT,
                        Shader.TileMode.CLAMP
                    )
                }
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bloomPaintBottom)

                val accentBarPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = palette.accent
                    strokeWidth = 6f
                    strokeCap = Paint.Cap.ROUND
                }
                canvas.drawLine(horizontalInset - 20f, topPadding + 6f, horizontalInset - 20f, topPadding + quoteLayout.height - 6f, accentBarPaint)
            }
            else -> {
                // STYLE_EDITORIAL: Diagonal ambient gradient glow
                val gradient = LinearGradient(
                    0f, 0f, width.toFloat(), height.toFloat() * 0.7f,
                    palette.topGradientTint, Color.TRANSPARENT,
                    Shader.TileMode.CLAMP
                )
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), Paint(Paint.ANTI_ALIAS_FLAG).apply { shader = gradient })
            }
        }

        // Draw badge
        if (showBadge) {
            when (lyricStyle) {
                STYLE_STUDIO -> {
                    val badgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = palette.muted
                        textSize = 28f
                        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                        letterSpacing = 0.14f
                        textAlign = Paint.Align.CENTER
                    }
                    canvas.drawText("✦  OMNIPLAY  ✦", width / 2f, 76f, badgePaint)
                }
                STYLE_QUOTE -> {
                    val badgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = palette.muted
                        textSize = 28f
                        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                        letterSpacing = 0.10f
                    }
                    canvas.drawText("“ OMNIPLAY ”", horizontalInset, 76f, badgePaint)
                }
                STYLE_MINIMALIST -> {
                    val badgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = palette.muted
                        textSize = 26f
                        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                        letterSpacing = 0.16f
                    }
                    canvas.drawText("OMNIPLAY // LYRICS", horizontalInset, 76f, badgePaint)
                }
                STYLE_ATMOSPHERIC -> {
                    val pillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = Color.argb(36, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent))
                    }
                    val pillRect = RectF(horizontalInset, 46f, horizontalInset + 180f, 92f)
                    canvas.drawRoundRect(pillRect, 23f, 23f, pillPaint)

                    val badgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = palette.accent
                        textSize = 24f
                        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                        letterSpacing = 0.08f
                    }
                    canvas.drawText("♫  OMNIPLAY", horizontalInset + 20f, 76f, badgePaint)
                }
                else -> {
                    val badgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = palette.muted
                        textSize = 28f
                        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                        letterSpacing = 0.08f
                    }
                    canvas.drawText("♫  OMNIPLAY", horizontalInset, 76f, badgePaint)
                }
            }
        }

        // Draw lyrics text
        canvas.save()
        canvas.translate(horizontalInset, topPadding)
        quoteLayout.draw(canvas)
        canvas.restore()

        // Draw track and artist metadata
        if (metadata.isNotEmpty()) {
            val metaStartY = topPadding + quoteLayout.height + 72f
            val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.track
                strokeWidth = 2f
            }
            if (lyricStyle == STYLE_STUDIO) {
                canvas.drawLine(width / 2f - 60f, metaStartY - 24f, width / 2f + 60f, metaStartY - 24f, dividerPaint)
            } else {
                canvas.drawLine(horizontalInset, metaStartY - 24f, horizontalInset + 120f, metaStartY - 24f, dividerPaint)
            }

            var currentMetaY = metaStartY
            metadata.forEachIndexed { index, value ->
                val isTitle = (index == 0 && title.isNotBlank())
                val metadataPaint = textPaint(
                    if (isTitle) palette.foreground else palette.muted,
                    if (isTitle) 42f else 32f,
                    if (isTitle) Typeface.BOLD else Typeface.NORMAL,
                    if (lyricStyle == STYLE_STUDIO) Paint.Align.CENTER else Paint.Align.LEFT
                )
                val textX = if (lyricStyle == STYLE_STUDIO) width / 2f else horizontalInset
                val textToDraw = if (lyricStyle == STYLE_QUOTE && isTitle) "— $value" else value
                canvas.drawText(textToDraw, textX, currentMetaY + (if (isTitle) 8f else 14f), metadataPaint)
                currentMetaY += 50f
            }
        }

        // Draw wave progress bar (with 40% reduced top-margin between artist and progress bar)
        if (showProgress) {
            val progressStart = horizontalInset
            val progressEnd = width - horizontalInset
            val safeDurationMs = durationMs.coerceAtLeast(0L)
            val safePositionMs = if (safeDurationMs > 0L) {
                positionMs.coerceIn(0L, safeDurationMs)
            } else {
                positionMs.coerceAtLeast(0L)
            }
            val progress = if (safeDurationMs > 0L) {
                safePositionMs.toFloat() / safeDurationMs.toFloat()
            } else {
                0f
            }
            val progressX = progressStart + (progressEnd - progressStart) * progress

            // 1. Draw Inactive Track
            val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.track
                strokeWidth = 10f
                strokeCap = Paint.Cap.ROUND
            }
            if (progressX < progressEnd) {
                canvas.drawLine(progressX, progressY, progressEnd, progressY, trackPaint)
            }

            // 2. Draw Active Track (Squiggly wave with smooth ramp)
            val playedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.accent
                style = Paint.Style.STROKE
                strokeWidth = 10f
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
            if (progressX > progressStart + 2f) {
                val wavePath = Path().apply {
                    moveTo(progressStart, progressY)
                    var x = progressStart
                    val step = 3f
                    while (x <= progressX) {
                        val relX = x - progressStart
                        val rampIn = kotlin.math.min(1f, relX / (WAVE_LENGTH * 0.75f))
                        val rampOut = kotlin.math.min(1f, (progressX - x) / (WAVE_LENGTH * 0.75f))
                        val ramp = kotlin.math.min(rampIn, rampOut)

                        val effectiveAmp = WAVE_AMPLITUDE * ramp
                        val angle = (relX / WAVE_LENGTH) * (2.0 * Math.PI).toFloat()
                        val y = progressY + effectiveAmp * kotlin.math.sin(angle.toDouble()).toFloat()

                        lineTo(x, y)
                        x += step
                    }
                    lineTo(progressX, progressY)
                }
                canvas.drawPath(wavePath, playedPaint)
            }

            // 3. Draw Thumb (glowing halo + solid thumb dot)
            val haloPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(45, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent))
            }
            canvas.drawCircle(progressX, progressY, 26f, haloPaint)
            canvas.drawCircle(progressX, progressY, 15f, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = palette.accent })

            // 4. Duration labels
            val timePaint = textPaint(palette.muted, 32f, Typeface.NORMAL, Paint.Align.LEFT)
            val elapsed = Song.formatTime(safePositionMs)
            canvas.drawText(elapsed, progressStart, durationLabelsY, timePaint)
            timePaint.textAlign = Paint.Align.RIGHT
            val total = Song.formatTime(safeDurationMs)
            canvas.drawText(total, progressEnd, durationLabelsY, timePaint)
        }

        return bitmap
    }

    fun saveToPictures(context: Context, bitmap: Bitmap): Boolean {
        val name = "OmniPlay_Lyrics_${System.currentTimeMillis()}.png"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(android.provider.MediaStore.Images.Media.DISPLAY_NAME, name)
                put(android.provider.MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(
                    android.provider.MediaStore.Images.Media.RELATIVE_PATH,
                    "${Environment.DIRECTORY_PICTURES}/OmniPlay"
                )
                put(android.provider.MediaStore.Images.Media.IS_PENDING, 1)
            }
            val resolver = context.contentResolver
            val uri = resolver.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) ?: return false
            try {
                val output = resolver.openOutputStream(uri, "w") ?: throw IOException("Unable to open image output")
                output.use { if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) throw IOException("Unable to encode image") }
                val published = ContentValues().apply { put(android.provider.MediaStore.Images.Media.IS_PENDING, 0) }
                resolver.update(uri, published, null, null)
                return true
            } catch (error: Exception) {
                resolver.delete(uri, null, null)
                throw error
            }
        }

        val pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val directory = File(pictures, "OmniPlay")
        if (!directory.exists() && !directory.mkdirs()) return false
        val file = File(directory, name)
        FileOutputStream(file).use {
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) throw IOException("Unable to encode image")
        }
        MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), arrayOf("image/png"), null)
        return true
    }

    fun writeShareCopy(context: Context, bitmap: Bitmap): Uri {
        val directory = File(context.cacheDir, "lyrics-share")
        if (!directory.exists() && !directory.mkdirs()) throw IOException("Unable to create share cache")
        directory.listFiles()?.filter { System.currentTimeMillis() - it.lastModified() > CACHE_MAX_AGE_MS }
            ?.forEach { it.delete() }

        val file = File(directory, "lyrics_${System.nanoTime()}.png")
        FileOutputStream(file).use {
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) throw IOException("Unable to encode image")
        }
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    private fun getAppThemePalette(context: Context): Palette {
        val primaryColor = ThemeColors.getPrimary(context)
        return Palette(
            background = ThemeColors.getSurfaceContainerHigh(context),
            foreground = ThemeColors.getOnSurface(context),
            muted = ThemeColors.getOnSurfaceVariant(context),
            track = ThemeColors.getSurfaceContainerHighest(context),
            accent = primaryColor,
            topGradientTint = Color.argb(36, Color.red(primaryColor), Color.green(primaryColor), Color.blue(primaryColor))
        )
    }

    private fun textPaint(color: Int, size: Float, style: Int, align: Paint.Align) =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            textSize = size
            typeface = Typeface.create("sans-serif", style)
            textAlign = align
        }

    private data class Palette(
        val background: Int,
        val foreground: Int,
        val muted: Int,
        val track: Int,
        val accent: Int,
        val topGradientTint: Int
    )

    private const val MIN_IMAGE_HEIGHT = 440
    private const val MAX_QUOTE_HEIGHT = 4000
    private const val MIN_QUOTE_TEXT_SIZE = 32f
    private const val WAVE_LENGTH = 64f
    private const val WAVE_AMPLITUDE = 12f
    private const val CACHE_MAX_AGE_MS = 24 * 60 * 60 * 1000L
}
