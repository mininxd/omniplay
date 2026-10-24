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
    private var selectedTheme = LyricsShareImageRenderer.THEME_MATERIAL
    private var lyricsFontSizePx = DEFAULT_LYRICS_FONT_SIZE_PX
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

        binding.lyricsShareTrack.text = listOf(title, artist)
            .filter { it.isNotBlank() }
            .joinToString(" • ")

        binding.lyricsShareThemeGroup.check(R.id.lyrics_theme_material)
        binding.lyricsShareThemeGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            selectedTheme = when (checkedIds.firstOrNull()) {
                R.id.lyrics_theme_midnight -> LyricsShareImageRenderer.THEME_MIDNIGHT
                R.id.lyrics_theme_sunset -> LyricsShareImageRenderer.THEME_SUNSET
                R.id.lyrics_theme_forest -> LyricsShareImageRenderer.THEME_FOREST
                R.id.lyrics_theme_paper -> LyricsShareImageRenderer.THEME_PAPER
                else -> LyricsShareImageRenderer.THEME_MATERIAL
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

    private fun renderPreview() {
        val lines = requireArguments().getStringArrayList(ARG_LINES).orEmpty()
        val durationMs = requireArguments().getLong(ARG_DURATION_MS).coerceAtLeast(1000L)
        val posMs = if (customPositionMs >= 0L) customPositionMs else requireArguments().getLong(ARG_POSITION_MS)
        renderedBitmap = LyricsShareImageRenderer.render(
            requireContext(),
            lines,
            selectedTheme,
            posMs,
            durationMs,
            lyricsFontSizePx,
            if (showTitle) requireArguments().getString(ARG_TITLE).orEmpty() else "",
            if (showArtist) requireArguments().getString(ARG_ARTIST).orEmpty() else "",
            showProgress,
            showBadge
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
    const val THEME_MATERIAL = 0
    const val THEME_MIDNIGHT = 1
    const val THEME_SUNSET = 2
    const val THEME_FOREST = 3
    const val THEME_PAPER = 4

    fun render(
        context: Context,
        lines: List<String>,
        theme: Int,
        positionMs: Long,
        durationMs: Long,
        lyricsFontSizePx: Int,
        title: String,
        artist: String,
        showProgress: Boolean,
        showBadge: Boolean = true
    ): Bitmap {
        val palette = paletteFor(context, theme)
        val quote = lines.joinToString("\n").trim().ifBlank { "♪" }
        val width = IMAGE_WIDTH
        val horizontalInset = 84f
        val bodyWidth = (width - horizontalInset * 2).toInt()

        val quotePaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.foreground
            textSize = lyricsFontSizePx.toFloat().coerceIn(40f, 240f)
            typeface = Typeface.create("sans-serif-black", Typeface.NORMAL)
            letterSpacing = -0.02f
        }

        fun createQuoteLayout(): StaticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(quote, 0, quote.length, quotePaint, bodyWidth)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(26f, 1f)
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(quote, quotePaint, bodyWidth, Layout.Alignment.ALIGN_NORMAL, 1f, 26f, false)
        }

        var quoteLayout = createQuoteLayout()
        while (quoteLayout.height > MAX_QUOTE_HEIGHT && quotePaint.textSize > MIN_QUOTE_TEXT_SIZE) {
            quotePaint.textSize -= 6f
            quoteLayout = createQuoteLayout()
        }

        val topPadding = if (showBadge) 140f else 84f
        val metadata = listOf(title, artist).filter { it.isNotBlank() }
        val metadataHeight = if (metadata.isEmpty()) 0f else 116f + (metadata.size - 1) * 48f
        val progressCardHeight = if (showProgress) PROGRESS_CARD_HEIGHT else NO_PROGRESS_CARD_HEIGHT
        val height = maxOf(MIN_IMAGE_HEIGHT, topPadding.toInt() + quoteLayout.height + metadataHeight.toInt() + progressCardHeight)

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Draw rich surface background
        canvas.drawColor(palette.background)

        // Draw atmospheric ambient gradient glow
        val gradient = LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat() * 0.7f,
            palette.topGradientTint, Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        )
        val gradientPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = gradient
        }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), gradientPaint)

        // Optional OmniPlay brand badge in header
        if (showBadge) {
            val badgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.muted
                textSize = 28f
                typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                letterSpacing = 0.08f
            }
            canvas.drawText("♫  OMNIPLAY", horizontalInset, 76f, badgePaint)
        }

        // Draw lyrics text
        canvas.save()
        canvas.translate(horizontalInset, topPadding)
        quoteLayout.draw(canvas)
        canvas.restore()

        // Draw track and artist metadata
        var metadataY = topPadding + quoteLayout.height + 64f
        if (metadata.isNotEmpty()) {
            val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.track
                strokeWidth = 2f
            }
            canvas.drawLine(horizontalInset, metadataY - 24f, horizontalInset + 120f, metadataY - 24f, dividerPaint)

            metadata.forEachIndexed { index, value ->
                val isTitle = (index == 0 && title.isNotBlank())
                val metadataPaint = textPaint(
                    if (isTitle) palette.foreground else palette.muted,
                    if (isTitle) 42f else 32f,
                    if (isTitle) Typeface.BOLD else Typeface.NORMAL,
                    Paint.Align.LEFT
                )
                canvas.drawText(value, horizontalInset, metadataY + (if (isTitle) 0f else 6f), metadataPaint)
                metadataY += 48f
            }
        }

        // Draw wave progress bar
        if (showProgress) {
            val progressStart = horizontalInset
            val progressEnd = width - horizontalInset
            val progressY = height - 120f
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

            // Background inactive track
            val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.track
                strokeWidth = 10f
                strokeCap = Paint.Cap.ROUND
            }
            canvas.drawLine(progressStart, progressY, progressEnd, progressY, trackPaint)

            // Dynamic sine wave for played progress
            val playedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = palette.accent
                style = Paint.Style.STROKE
                strokeWidth = 10f
                strokeCap = Paint.Cap.ROUND
                strokeJoin = Paint.Join.ROUND
            }
            if (progressX > progressStart + 2f) {
                val wave = Path().apply {
                    var x = progressStart
                    moveTo(x, progressY)
                    while (x < progressX) {
                        x = (x + 3f).coerceAtMost(progressX)
                        val angle = ((x - progressStart) / WAVE_LENGTH) * (Math.PI * 2.0).toFloat()
                        lineTo(x, progressY + WAVE_AMPLITUDE * kotlin.math.sin(angle.toDouble()).toFloat())
                    }
                }
                canvas.drawPath(wave, playedPaint)
            }

            // Glow ring + thumb dot
            val glowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(45, Color.red(palette.accent), Color.green(palette.accent), Color.blue(palette.accent))
            }
            canvas.drawCircle(progressX, progressY, 28f, glowPaint)
            canvas.drawCircle(progressX, progressY, 16f, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = palette.accent })

            // Duration labels
            val timePaint = textPaint(palette.muted, 32f, Typeface.NORMAL, Paint.Align.LEFT)
            val elapsed = Song.formatTime(safePositionMs)
            canvas.drawText(elapsed, progressStart, height - 36f, timePaint)
            timePaint.textAlign = Paint.Align.RIGHT
            val total = Song.formatTime(safeDurationMs)
            canvas.drawText(total, progressEnd, height - 36f, timePaint)
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

    private fun paletteFor(context: Context, theme: Int): Palette {
        val primaryColor = ThemeColors.getPrimary(context)

        return when (theme) {
            THEME_MIDNIGHT -> Palette(
                background = Color.rgb(10, 10, 15),
                foreground = Color.rgb(244, 244, 248),
                muted = Color.rgb(157, 157, 174),
                track = Color.rgb(36, 34, 54),
                accent = Color.rgb(167, 139, 250), // Luminous Violet
                topGradientTint = Color.argb(40, 167, 139, 250)
            )
            THEME_SUNSET -> Palette(
                background = Color.rgb(18, 9, 12),
                foreground = Color.rgb(255, 241, 242),
                muted = Color.rgb(184, 156, 158),
                track = Color.rgb(51, 27, 34),
                accent = Color.rgb(251, 113, 133), // Coral Rose
                topGradientTint = Color.argb(45, 251, 113, 133)
            )
            THEME_FOREST -> Palette(
                background = Color.rgb(8, 18, 13),
                foreground = Color.rgb(236, 253, 245),
                muted = Color.rgb(148, 168, 157),
                track = Color.rgb(23, 51, 37),
                accent = Color.rgb(52, 211, 153), // Radiant Emerald Mint
                topGradientTint = Color.argb(40, 52, 211, 153)
            )
            THEME_PAPER -> Palette(
                background = Color.rgb(250, 247, 242),
                foreground = Color.rgb(26, 28, 26),
                muted = Color.rgb(92, 97, 93),
                track = Color.rgb(221, 216, 206),
                accent = Color.rgb(59, 94, 67), // Forest Olive
                topGradientTint = Color.argb(20, 59, 94, 67)
            )
            else -> Palette(
                background = Color.rgb(14, 18, 16),
                foreground = Color.rgb(241, 245, 242),
                muted = Color.rgb(161, 171, 164),
                track = Color.rgb(40, 51, 44),
                accent = primaryColor,
                topGradientTint = Color.argb(50, Color.red(primaryColor), Color.green(primaryColor), Color.blue(primaryColor))
            )
        }
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

    private const val IMAGE_WIDTH = 1080
    private const val MIN_IMAGE_HEIGHT = 560
    private const val MAX_QUOTE_HEIGHT = 4000
    private const val MIN_QUOTE_TEXT_SIZE = 32f
    private const val WAVE_LENGTH = 64f
    private const val WAVE_AMPLITUDE = 12f
    private const val PROGRESS_CARD_HEIGHT = 320
    private const val NO_PROGRESS_CARD_HEIGHT = 110
    private const val CACHE_MAX_AGE_MS = 24 * 60 * 60 * 1000L
}
