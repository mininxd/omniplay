package xyz.omniplay.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import xyz.omniplay.R
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object UpdateChecker {

    private const val GITHUB_API_URL = "https://api.github.com/repos/mininxd/omniplay/releases/latest"

    data class ReleaseInfo(
        val tagName: String,
        val name: String,
        val changelog: String,
        val downloadUrl: String,
        val releasePageUrl: String,
        val isNewer: Boolean
    )

    fun getAppVersion(context: Context?): String {
        if (context == null) return "0.6.1"
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "0.6.1"
        } catch (e: Exception) {
            "0.6.1"
        }
    }

    suspend fun checkLatestRelease(context: Context? = null): ReleaseInfo? = withContext(Dispatchers.IO) {
        val currentVersion = getAppVersion(context)
        var connection: HttpURLConnection? = null
        try {
            val url = URL(GITHUB_API_URL)
            connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = 10000
                readTimeout = 15000
                setRequestProperty("Accept", "application/vnd.github.v3+json")
                setRequestProperty("User-Agent", "Omniplay/$currentVersion")
            }

            if (connection.responseCode !in 200..299) {
                return@withContext null
            }

            val response = connection.inputStream.bufferedReader().use(BufferedReader::readText)
            val json = JSONObject(response)

            val tagName = json.optString("tag_name", "")
            val name = json.optString("name", tagName)
            val body = json.optString("body", "")
            val htmlUrl = json.optString("html_url", "https://github.com/mininxd/omniplay/releases")

            var downloadUrl = htmlUrl
            val assets = json.optJSONArray("assets")
            if (assets != null && assets.length() > 0) {
                var chosenApkUrl: String? = null
                var fallbackApkUrl: String? = null

                for (i in 0 until assets.length()) {
                    val asset = assets.getJSONObject(i)
                    val assetName = asset.optString("name", "").lowercase()
                    val browserUrl = asset.optString("browser_download_url", "")
                    if (assetName.endsWith(".apk")) {
                        if (fallbackApkUrl == null) {
                            fallbackApkUrl = browserUrl
                        }
                        if (android.os.Build.VERSION.SDK_INT >= 26) {
                            if (!assetName.contains("legacy")) {
                                chosenApkUrl = browserUrl
                                break
                            }
                        } else {
                            if (assetName.contains("legacy")) {
                                chosenApkUrl = browserUrl
                                break
                            }
                        }
                    }
                }
                downloadUrl = chosenApkUrl ?: fallbackApkUrl ?: htmlUrl
            }

            val isNewer = isNewerVersion(currentVersion, tagName)
            ReleaseInfo(
                tagName = tagName,
                name = name,
                changelog = body,
                downloadUrl = downloadUrl,
                releasePageUrl = htmlUrl,
                isNewer = isNewer
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            connection?.disconnect()
        }
    }

    fun isNewerVersion(currentVersion: String, remoteVersion: String): Boolean {
        val currentParts = parseVersionParts(currentVersion)
        val remoteParts = parseVersionParts(remoteVersion)
        if (currentParts.isEmpty() && remoteParts.isEmpty()) return false

        val maxLen = maxOf(currentParts.size, remoteParts.size)
        for (i in 0 until maxLen) {
            val curr = currentParts.getOrElse(i) { 0 }
            val remote = remoteParts.getOrElse(i) { 0 }
            if (remote > curr) return true
            if (remote < curr) return false
        }
        return false
    }

    private fun parseVersionParts(version: String): List<Int> {
        val cleaned = version.trim().removePrefix("v").removePrefix("V")
        val mainNumeric = cleaned.takeWhile { it.isDigit() || it == '.' }
        return mainNumeric.split('.')
            .mapNotNull { it.toIntOrNull() }
    }

    fun showUpdateDialog(context: Context, release: ReleaseInfo) {
        val displayTag = if (release.tagName.startsWith("v", ignoreCase = true)) release.tagName else "v${release.tagName}"
        val changelogTrimmed = release.changelog.trim()
        val message = if (changelogTrimmed.isNotBlank()) {
            context.getString(R.string.update_available_format, displayTag) + "\n\n" + changelogTrimmed.take(400)
        } else {
            context.getString(R.string.update_available_format, displayTag)
        }

        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.update_available_title)
            .setMessage(message)
            .setPositiveButton(R.string.download) { _, _ ->
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(release.downloadUrl)).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                } catch (e: Exception) {
                    try {
                        val fallback = Intent(Intent.ACTION_VIEW, Uri.parse(release.releasePageUrl)).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(fallback)
                    } catch (ignored: Exception) {}
                }
            }
            .setNegativeButton(R.string.close, null)
            .show()
    }
}
