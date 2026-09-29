package xyz.omniplay.util

object AlbumColorGenerator {

    // Distinct, vibrant Material You tones for album-colored placeholders
    private val ALBUM_PALETTE = intArrayOf(
        0xFF1E88E5.toInt(), // Vibrant Blue
        0xFF7B1FA2.toInt(), // Royal Purple
        0xFF00897B.toInt(), // Teal Green
        0xFFE65100.toInt(), // Deep Orange
        0xFF43A047.toInt(), // Fresh Green
        0xFFD81B60.toInt(), // Pink Rose
        0xFF5E35B1.toInt(), // Deep Indigo
        0xFF00ACC1.toInt(), // Cyan
        0xFF8D6E63.toInt(), // Warm Brown
        0xFF3949AB.toInt(), // Navy Indigo
        0xFFC2185B.toInt(), // Crimson
        0xFF0097A7.toInt(), // Ocean Teal
        0xFFF57C00.toInt(), // Amber Orange
        0xFF689F38.toInt(), // Light Olive Green
        0xFF512DA8.toInt()  // Midnight Purple
    )

    /**
     * Returns a deterministic, attractive color based on the album or artist name.
     */
    fun getColorForAlbum(album: String?, artist: String?): Int {
        val key = when {
            !album.isNullOrBlank() -> album.trim().lowercase()
            !artist.isNullOrBlank() -> artist.trim().lowercase()
            else -> "omniplay_default"
        }
        val hash = Math.abs(key.hashCode())
        return ALBUM_PALETTE[hash % ALBUM_PALETTE.size]
    }
}
