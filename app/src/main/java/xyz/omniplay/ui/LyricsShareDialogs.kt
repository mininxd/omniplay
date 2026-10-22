package xyz.omniplay.ui

import android.Manifest
import android.content.ClipData
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
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
import android.text.TextUtils
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
import xyz.omniplay.util.ThemeColors

class LyricsShareThemeBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetLyricsShareBinding? = null
    private val binding get() = _binding!!
    private var selectedTheme = LyricsShareImageRenderer.THEME_MATERIAL
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
        val title = requireArguments().getString(ARG_TITLE).orEmpty()
        val artist = requireArguments().getString(ARG_ARTIST).orEmpty()
        val lines = requireArguments().getStringArrayList(ARG_LINES).orEmpty()
        renderedBitmap = LyricsShareImageRenderer.render(requireContext(), title, artist, lines, selectedTheme)
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
            val title = requireArguments().getString(ARG_TITLE).orEmpty()
            val artist = requireArguments().getString(ARG_ARTIST).orEmpty()
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

        fun newInstance(title: String, artist: String, lines: List<String>) =
            LyricsShareThemeBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_ARTIST, artist)
                    putStringArrayList(ARG_LINES, ArrayList(lines))
                }
            }
    }
}

private object LyricsShareImageRenderer {
    const val THEME_MATERIAL = 0
    const val THEME_MIDNIGHT = 1
    const val THEME_PAPER = 2

    fun render(context: android.content.Context, title: String, artist: String, lines: List<String>, theme: Int): Bitmap {
        val palette = paletteFor(context, theme)
        val quote = lines.joinToString("\n").trim().ifBlank { "♪" }
        val width = IMAGE_WIDTH
        val horizontalInset = 132f
        val bodyWidth = (width - horizontalInset * 2).toInt()

        val quotePaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.body
            textSize = 52f
            typeface = Typeface.create("sans-serif", Typeface.NORMAL)
        }
        val quoteLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(quote, 0, quote.length, quotePaint, bodyWidth)
                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                .setLineSpacing(15f, 1f)
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(quote, quotePaint, bodyWidth, Layout.Alignment.ALIGN_CENTER, 1f, 15f, false)
        }

        val height = maxOf(MIN_IMAGE_HEIGHT, quoteLayout.height + 700)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                palette.background,
                palette.backgroundEnd,
                Shader.TileMode.CLAMP
            )
        }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)

        val accentGlow = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = palette.accentGlow }
        canvas.drawCircle(width - 34f, 108f, 330f, accentGlow)
        canvas.drawCircle(12f, height - 44f, 290f, accentGlow)

        val card = RectF(48f, 48f, width - 48f, height - 48f)
        val cardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = palette.card }
        canvas.drawRoundRect(card, 58f, 58f, cardPaint)

        val headerPaint = textPaint(palette.accent, 25f, Typeface.BOLD, Paint.Align.LEFT)
        canvas.drawText("OMNIPLAY  /  LYRICS", horizontalInset, 154f, headerPaint)

        val titlePaint = textPaint(palette.title, 45f, Typeface.BOLD, Paint.Align.LEFT)
        val safeTitle = ellipsize(title.ifBlank { context.getString(R.string.app_name) }, titlePaint, bodyWidth.toFloat())
        canvas.drawText(safeTitle, horizontalInset, 244f, titlePaint)

        val artistPaint = textPaint(palette.muted, 31f, Typeface.NORMAL, Paint.Align.LEFT)
        if (artist.isNotBlank()) {
            canvas.drawText(ellipsize(artist, artistPaint, bodyWidth.toFloat()), horizontalInset, 302f, artistPaint)
        }

        val accentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = palette.accent }
        canvas.drawRoundRect(RectF(horizontalInset, 350f, horizontalInset + 104f, 360f), 5f, 5f, accentPaint)

        canvas.save()
        canvas.translate(horizontalInset, 410f)
        quoteLayout.draw(canvas)
        canvas.restore()

        val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = palette.divider
            strokeWidth = 2f
        }
        val footerY = height - 146f
        canvas.drawLine(horizontalInset, footerY - 42f, width - horizontalInset, footerY - 42f, dividerPaint)
        canvas.drawText("OMNIPLAY", horizontalInset, footerY, headerPaint)
        val footerNotePaint = textPaint(palette.muted, 22f, Typeface.NORMAL, Paint.Align.RIGHT)
        canvas.drawText("LYRIC SHARE", width - horizontalInset, footerY, footerNotePaint)
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

    private fun paletteFor(context: android.content.Context, theme: Int): Palette = when (theme) {
        THEME_MIDNIGHT -> Palette(
            background = Color.rgb(12, 16, 25),
            backgroundEnd = Color.rgb(35, 43, 61),
            card = Color.rgb(24, 31, 45),
            accent = Color.rgb(184, 199, 255),
            title = Color.rgb(244, 245, 251),
            body = Color.rgb(225, 229, 241),
            muted = Color.rgb(174, 183, 205),
            accentGlow = Color.argb(28, 153, 177, 255),
            divider = Color.argb(65, 184, 199, 255)
        )
        THEME_PAPER -> Palette(
            background = Color.rgb(234, 222, 202),
            backgroundEnd = Color.rgb(248, 239, 222),
            card = Color.rgb(255, 250, 240),
            accent = Color.rgb(126, 83, 48),
            title = Color.rgb(48, 38, 29),
            body = Color.rgb(59, 48, 38),
            muted = Color.rgb(116, 100, 83),
            accentGlow = Color.argb(20, 138, 99, 58),
            divider = Color.argb(55, 126, 83, 48)
        )
        else -> Palette(
            background = ThemeColors.getSurface(context),
            backgroundEnd = ThemeColors.getSurfaceContainerLow(context),
            card = ThemeColors.getSurfaceContainerHigh(context),
            accent = ThemeColors.getPrimary(context),
            title = ThemeColors.getOnSurface(context),
            body = ThemeColors.getOnSurface(context),
            muted = ThemeColors.getOnSurfaceVariant(context),
            accentGlow = withAlpha(ThemeColors.getPrimary(context), 28),
            divider = withAlpha(ThemeColors.getPrimary(context), 58)
        )
    }

    private fun textPaint(color: Int, size: Float, style: Int, align: Paint.Align) =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            textSize = size
            typeface = Typeface.create("sans-serif", style)
            textAlign = align
        }

    private fun ellipsize(text: String, paint: Paint, availableWidth: Float): String =
        TextUtils.ellipsize(text, TextPaint(paint), availableWidth, TextUtils.TruncateAt.END).toString()

    private fun withAlpha(color: Int, alpha: Int): Int = Color.argb(
        alpha,
        Color.red(color),
        Color.green(color),
        Color.blue(color)
    )

    private data class Palette(
        val background: Int,
        val backgroundEnd: Int,
        val card: Int,
        val accent: Int,
        val title: Int,
        val body: Int,
        val muted: Int,
        val accentGlow: Int,
        val divider: Int
    )

    private const val IMAGE_WIDTH = 1080
    private const val MIN_IMAGE_HEIGHT = 1080
    private const val CACHE_MAX_AGE_MS = 24 * 60 * 60 * 1000L
}
