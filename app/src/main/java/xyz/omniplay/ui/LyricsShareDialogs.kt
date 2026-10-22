package xyz.omniplay.ui

import android.Manifest
import android.content.ClipData
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
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
    private var lyricsScalePercent = DEFAULT_LYRICS_SCALE_PERCENT
    private var showTitle = false
    private var showArtist = false
    private var showProgress = true
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
    ): android.view.View {
        _binding = BottomSheetLyricsShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lyricsShareTrack.text = listOf(
            requireArguments().getString(ARG_TITLE).orEmpty(),
            requireArguments().getString(ARG_ARTIST).orEmpty()
        ).filter { it.isNotBlank() }.joinToString(" • ")

        binding.lyricsShareThemeGroup.check(R.id.lyrics_theme_material)
        binding.lyricsShareThemeGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            selectedTheme = when (checkedIds.firstOrNull()) {
                R.id.lyrics_theme_midnight -> LyricsShareImageRenderer.THEME_MIDNIGHT
                R.id.lyrics_theme_paper -> LyricsShareImageRenderer.THEME_PAPER
                else -> LyricsShareImageRenderer.THEME_MATERIAL
            }
            renderPreview()
        }
        binding.lyricsShareFontSize.value = lyricsScalePercent.toFloat()
        binding.lyricsShareFontSizeValue.text = getString(R.string.lyrics_share_font_size_value, lyricsScalePercent)
        binding.lyricsShareFontSize.addOnChangeListener { _, value, _ ->
            lyricsScalePercent = value.toInt().coerceIn(MIN_LYRICS_SCALE_PERCENT, MAX_LYRICS_SCALE_PERCENT)
            binding.lyricsShareFontSizeValue.text = getString(R.string.lyrics_share_font_size_value, lyricsScalePercent)
            renderPreview()
        }
        binding.lyricsShareIncludeTitle.setOnCheckedChangeListener { _, checked ->
            showTitle = checked
            renderPreview()
        }
        binding.lyricsShareIncludeArtist.setOnCheckedChangeListener { _, checked ->
            showArtist = checked
            renderPreview()
        }
        binding.lyricsShareIncludeProgress.setOnCheckedChangeListener { _, checked ->
            showProgress = checked
            renderPreview()
        }
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
        renderedBitmap = LyricsShareImageRenderer.render(
            requireContext(),
            lines,
            selectedTheme,
            requireArguments().getLong(ARG_POSITION_MS),
            requireArguments().getLong(ARG_DURATION_MS),
            lyricsScalePercent,
            if (showTitle) requireArguments().getString(ARG_TITLE).orEmpty() else "",
            if (showArtist) requireArguments().getString(ARG_ARTIST).orEmpty() else "",
            showProgress
        )
        binding.lyricsShareImagePreview.setImageBitmap(renderedBitmap)
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
        private const val DEFAULT_LYRICS_SCALE_PERCENT = 100
        private const val MIN_LYRICS_SCALE_PERCENT = 80
        private const val MAX_LYRICS_SCALE_PERCENT = 140

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
    const val THEME_PAPER = 2

    fun render(
        context: android.content.Context,
        lines: List<String>,
        theme: Int,
        positionMs: Long,
        durationMs: Long,
        lyricsScalePercent: Int,
        title: String,
        artist: String,
        showProgress: Boolean
    ): Bitmap {
        val palette = paletteFor(context, theme)
        val quote = lines.joinToString("\n").trim().ifBlank { "♪" }
        val width = IMAGE_WIDTH
        val horizontalInset = 72f
        val bodyWidth = (width - horizontalInset * 2).toInt()

        val quotePaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.foreground
            textSize = 150f * lyricsScalePercent.coerceIn(80, 140) / 100f
            typeface = Typeface.create("sans-serif-black", Typeface.NORMAL)
        }
        fun createQuoteLayout(): StaticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(quote, 0, quote.length, quotePaint, bodyWidth)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(20f, 1f)
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(quote, quotePaint, bodyWidth, Layout.Alignment.ALIGN_NORMAL, 1f, 20f, false)
        }
        var quoteLayout = createQuoteLayout()
        while (quoteLayout.height > MAX_QUOTE_HEIGHT && quotePaint.textSize > MIN_QUOTE_TEXT_SIZE) {
            quotePaint.textSize -= 8f
            quoteLayout = createQuoteLayout()
        }

        val metadata = listOf(title, artist).filter { it.isNotBlank() }
        val metadataHeight = if (metadata.isEmpty()) 0f else 104f + (metadata.size - 1) * 46f
        val progressCardHeight = if (showProgress) PROGRESS_CARD_HEIGHT else NO_PROGRESS_CARD_HEIGHT
        val height = maxOf(MIN_IMAGE_HEIGHT, quoteLayout.height + metadataHeight.toInt() + progressCardHeight)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(palette.background)

        canvas.save()
        canvas.translate(horizontalInset, 48f)
        quoteLayout.draw(canvas)
        canvas.restore()

        var metadataY = 48f + quoteLayout.height + 58f
        metadata.forEachIndexed { index, value ->
            val metadataPaint = textPaint(
                if (index == 0 && title.isNotBlank()) palette.foreground else palette.muted,
                if (index == 0 && title.isNotBlank()) 38f else 30f,
                if (index == 0 && title.isNotBlank()) Typeface.BOLD else Typeface.NORMAL,
                Paint.Align.LEFT
            )
            canvas.drawText(value, horizontalInset, metadataY, metadataPaint)
            metadataY += 46f
        }

        if (!showProgress) return bitmap

        val progressStart = horizontalInset
        val progressEnd = width - horizontalInset
        val progressY = height - 140f
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

        val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.track
            strokeWidth = 12f
            strokeCap = Paint.Cap.ROUND
        }
        canvas.drawLine(progressStart, progressY, progressEnd, progressY, trackPaint)

        val playedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.accent
            style = Paint.Style.STROKE
            strokeWidth = 12f
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
        if (progressX > progressStart + 1f) {
            val wave = Path().apply {
                var x = progressStart
                moveTo(x, progressY)
                while (x < progressX) {
                    x = (x + 4f).coerceAtMost(progressX)
                    val angle = ((x - progressStart) / WAVE_LENGTH) * (Math.PI * 2.0).toFloat()
                    lineTo(x, progressY + WAVE_AMPLITUDE * kotlin.math.sin(angle.toDouble()).toFloat())
                }
            }
            canvas.drawPath(wave, playedPaint)
        }
        canvas.drawCircle(progressX, progressY, 21f, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = palette.accent })

        val timePaint = textPaint(palette.muted, 36f, Typeface.NORMAL, Paint.Align.LEFT)
        val elapsed = Song.formatTime(safePositionMs)
        canvas.drawText(elapsed, progressStart, height - 30f, timePaint)
        timePaint.textAlign = Paint.Align.RIGHT
        val total = Song.formatTime(safeDurationMs)
        canvas.drawText(total, progressEnd, height - 30f, timePaint)
        return bitmap
    }

    fun saveToPictures(context: android.content.Context, bitmap: Bitmap): Boolean {
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

    fun writeShareCopy(context: android.content.Context, bitmap: Bitmap): Uri {
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

    private fun paletteFor(context: android.content.Context, theme: Int) = Palette(
        background = Color.rgb(13, 19, 16),
        foreground = Color.rgb(232, 237, 232),
        muted = Color.rgb(188, 197, 191),
        track = Color.rgb(48, 56, 51),
        accent = when (theme) {
            THEME_MIDNIGHT -> Color.rgb(245, 169, 181)
            THEME_PAPER -> Color.rgb(158, 239, 202)
            else -> ThemeColors.getPrimary(context)
        }
    )

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
        val accent: Int
    )

    private const val IMAGE_WIDTH = 1080
    private const val MIN_IMAGE_HEIGHT = 540
    private const val MAX_QUOTE_HEIGHT = 4000
    private const val MIN_QUOTE_TEXT_SIZE = 32f
    private const val WAVE_LENGTH = 68f
    private const val WAVE_AMPLITUDE = 13f
    private const val PROGRESS_CARD_HEIGHT = 370
    private const val NO_PROGRESS_CARD_HEIGHT = 128
    private const val CACHE_MAX_AGE_MS = 24 * 60 * 60 * 1000L
}
