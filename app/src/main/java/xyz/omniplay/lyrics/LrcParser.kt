package xyz.omniplay.lyrics

object LrcParser {

    private val TIME_TAG_REGEX = Regex("""\[(\d{1,2}):(\d{2})(?:[.:](\d{1,3}))?\]""")

    /**
     * Parses standard LRC formatted lyrics into a chronologically sorted list of [LyricLine].
     * Supports multiple timestamp tags on a single line (e.g. repeated choruses),
     * hundredths and thousandths of seconds, and skips metadata tags (e.g. [ti:...], [ar:...]).
     */
    fun parse(lrcText: String): List<LyricLine> {
        val lines = mutableListOf<LyricLine>()

        for (rawLine in lrcText.lines()) {
            val line = rawLine.trim()
            if (line.isEmpty()) continue

            val matches = TIME_TAG_REGEX.findAll(line).toList()
            if (matches.isEmpty()) continue

            // Strip all timestamps from the line to extract the lyric text
            val text = line.replace(TIME_TAG_REGEX, "").trim()

            for (match in matches) {
                val mins = match.groupValues[1].toLongOrNull() ?: 0L
                val secs = match.groupValues[2].toLongOrNull() ?: 0L
                val fracStr = match.groupValues.getOrNull(3).orEmpty()
                val frac = when (fracStr.length) {
                    1 -> (fracStr.toLongOrNull() ?: 0L) * 100
                    2 -> (fracStr.toLongOrNull() ?: 0L) * 10
                    3 -> fracStr.toLongOrNull() ?: 0L
                    else -> 0L
                }

                val timeMs = (mins * 60 + secs) * 1000 + frac
                lines.add(LyricLine(timeMs = timeMs, text = text))
            }
        }

        lines.sortBy { it.timeMs }
        return lines
    }
}
