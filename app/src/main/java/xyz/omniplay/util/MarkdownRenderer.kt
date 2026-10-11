package xyz.omniplay.util

import android.text.Spanned
import androidx.core.text.HtmlCompat

object MarkdownRenderer {

    fun render(markdown: String): Spanned {
        if (markdown.isBlank()) {
            return HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

        val lines = markdown.replace("\r\n", "\n").replace("\r", "\n").split("\n")
        val htmlSb = StringBuilder()
        var inList = false

        for (rawLine in lines) {
            val line = rawLine.trimEnd()
            val trimmed = line.trim()

            // Headings
            if (trimmed.startsWith("### ")) {
                if (inList) { htmlSb.append("<br/>"); inList = false }
                val content = formatInline(trimmed.substring(4).trim())
                htmlSb.append("<br/><b><big>").append(content).append("</big></b><br/>")
                continue
            }
            if (trimmed.startsWith("## ")) {
                if (inList) { htmlSb.append("<br/>"); inList = false }
                val content = formatInline(trimmed.substring(3).trim())
                htmlSb.append("<br/><b><big><big>").append(content).append("</big></big></b><br/>")
                continue
            }
            if (trimmed.startsWith("# ")) {
                if (inList) { htmlSb.append("<br/>"); inList = false }
                val content = formatInline(trimmed.substring(2).trim())
                htmlSb.append("<br/><b><big><big><big>").append(content).append("</big></big></big></b><br/>")
                continue
            }

            // Bullet points (*, -, •)
            if (trimmed.startsWith("* ") || trimmed.startsWith("- ") || trimmed.startsWith("• ")) {
                inList = true
                val bulletContent = trimmed.substring(2).trim()
                htmlSb.append("&nbsp;&nbsp;•&nbsp;&nbsp;").append(formatInline(bulletContent)).append("<br/>")
                continue
            }

            // Empty line
            if (trimmed.isEmpty()) {
                if (inList) {
                    inList = false
                }
                htmlSb.append("<br/>")
                continue
            }

            // Horizontal rule
            if (trimmed == "---" || trimmed == "***" || trimmed == "___") {
                htmlSb.append("<hr/>")
                continue
            }

            // Normal paragraph line
            htmlSb.append(formatInline(line)).append("<br/>")
        }

        val htmlString = htmlSb.toString()
        return HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    private fun formatInline(text: String): String {
        var result = text
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")

        // Code spans first so markdown inside code isn't formatted
        val codeSpans = mutableListOf<String>()
        val codeRegex = Regex("""`([^`]+)`""")
        result = codeRegex.replace(result) { match ->
            codeSpans.add(match.groupValues[1])
            "%%CODE_${codeSpans.size - 1}%%"
        }

        // Markdown links: [title](url)
        val links = mutableListOf<Pair<String, String>>()
        val linkRegex = Regex("""\[([^\]]+)\]\(([^)]+)\)""")
        result = linkRegex.replace(result) { match ->
            links.add(Pair(match.groupValues[1], match.groupValues[2]))
            "%%LINK_${links.size - 1}%%"
        }

        // Auto-link standalone URLs (http:// or https://)
        val bareUrlRegex = Regex("""(https?://[^\s<>"')]+)""")
        result = bareUrlRegex.replace(result) { match ->
            var url = match.groupValues[1]
            var trailing = ""
            while (url.endsWith(".") || url.endsWith(",") || url.endsWith(";")) {
                trailing = url.takeLast(1) + trailing
                url = url.dropLast(1)
            }
            """<a href="$url">$url</a>$trailing"""
        }

        // Restore markdown links
        links.forEachIndexed { index, (title, url) ->
            result = result.replace("%%LINK_${index}%%", """<a href="$url">$title</a>""")
        }

        // Bold: **text** or __text__
        val boldRegex = Regex("""(\*\*|__)(.*?)\1""")
        result = boldRegex.replace(result) { matchResult ->
            "<b>${matchResult.groupValues[2]}</b>"
        }

        // Italic: *text* or _text_
        val italicRegex = Regex("""(\*|_)(.*?)\1""")
        result = italicRegex.replace(result) { matchResult ->
            "<i>${matchResult.groupValues[2]}</i>"
        }

        // Strikethrough: ~~text~~
        val strikeRegex = Regex("""~~(.*?)~~""")
        result = strikeRegex.replace(result) { matchResult ->
            "<s>${matchResult.groupValues[1]}</s>"
        }

        // Restore code spans
        codeSpans.forEachIndexed { index, code ->
            result = result.replace("%%CODE_${index}%%", """<tt><font color="#80CBC4">$code</font></tt>""")
        }

        return result
    }
}
