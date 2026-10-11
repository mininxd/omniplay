package xyz.omniplay.ui

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import xyz.omniplay.R
import xyz.omniplay.databinding.ActivityMainBinding
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.sync.OmniSyncManager
import xyz.omniplay.sync.OmniSyncQuality
import xyz.omniplay.sync.OmniSyncRole
import xyz.omniplay.util.AudioTrackInfo
import java.util.Locale

object AudioDetailsHelper {

    fun isHiResAudio(
        song: Song?,
        audioInfo: AudioTrackInfo?,
        format: String,
        quality: String
    ): Boolean {
        if (song == null) return false
        val qNorm = quality.lowercase(Locale.ROOT).replace(" ", "").replace("-", "")
        val isHiResQuality = qNorm.contains("24bit") ||
                qNorm.contains("32bit") ||
                qNorm.contains("24/") ||
                qNorm.contains("32/") ||
                qNorm.contains("/24") ||
                qNorm.contains("/32") ||
                qNorm.contains("88.2") ||
                qNorm.contains("96") ||
                qNorm.contains("176.4") ||
                qNorm.contains("192") ||
                qNorm.contains("352.8") ||
                qNorm.contains("384") ||
                qNorm.contains("mhz") ||
                qNorm.contains("dsd") ||
                qNorm.contains("hires") ||
                qNorm.contains("hi-res")

        val sampleRate = maxOf(audioInfo?.sampleRate ?: 0, 0)
        val bitDepth = maxOf(audioInfo?.bitDepth ?: 0, 0)
        val isHiResNumeric = sampleRate > 48000 || bitDepth > 16 || bitDepth == 1

        val isHiResFormat = format.startsWith("DSD", true) ||
                format.equals("DSF", true) ||
                format.equals("DFF", true)

        val pathNorm = song.filePath.lowercase(Locale.ROOT)
        val isHiResPath = pathNorm.contains("hires") ||
                pathNorm.contains("hi-res") ||
                pathNorm.contains("24bit") ||
                pathNorm.contains("32bit") ||
                pathNorm.contains("96khz") ||
                pathNorm.contains("192khz") ||
                pathNorm.contains("dsd")

        return (audioInfo?.checkHiRes() == true) ||
                song.isHiRes ||
                isHiResNumeric ||
                isHiResFormat ||
                isHiResQuality ||
                isHiResPath
    }

    fun updateAudioBadges(
        context: Context,
        binding: ActivityMainBinding,
        song: Song?,
        audioInfo: AudioTrackInfo?,
        playbackService: PlaybackService?
    ) {
        val syncManager = OmniSyncManager.getInstance(context)
        val isListener = syncManager.currentRole == OmniSyncRole.LISTENER

        if (isListener) {
            val format = syncManager.currentTrackFormat
            val quality = syncManager.currentTrackQuality
            val isHiRes = syncManager.currentTrackIsHiRes

            var hasAnyBadge = false
            if (format.isNotEmpty()) {
                binding.badgeFormat.text = format
                binding.badgeFormat.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeFormat.visibility = View.GONE
            }

            if (quality.isNotEmpty()) {
                binding.badgeQuality.text = quality
                binding.badgeQuality.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeQuality.visibility = View.GONE
            }

            if (isHiRes) {
                binding.badgeHires.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeHires.visibility = View.GONE
            }

            binding.badgeBitPerfect.visibility = View.GONE

            val latency = syncManager.currentLatencyMs
            if (latency > 0L) {
                binding.badgeLatency.text = "${latency}ms"
                binding.badgeLatency.visibility = View.VISIBLE
                hasAnyBadge = true
            } else {
                binding.badgeLatency.visibility = View.GONE
            }

            binding.audioBadgeContainer.visibility = if (hasAnyBadge) View.VISIBLE else View.GONE
            return
        }

        binding.badgeLatency.visibility = View.GONE
        if (song == null) {
            binding.audioBadgeContainer.visibility = View.GONE
            return
        }

        val format = audioInfo?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val quality = audioInfo?.formatQualityString()?.takeIf { it.isNotEmpty() } ?: song.audioQuality
        val isHiRes = isHiResAudio(song, audioInfo, format, quality)

        if (format.isNotEmpty()) {
            binding.audioBadgeContainer.visibility = View.VISIBLE
            binding.badgeFormat.text = format
            binding.badgeFormat.visibility = View.VISIBLE

            if (quality.isNotEmpty()) {
                binding.badgeQuality.visibility = View.VISIBLE
                binding.badgeQuality.text = quality
            } else {
                binding.badgeQuality.visibility = View.GONE
            }

            if (isHiRes) {
                binding.badgeHires.visibility = View.VISIBLE
            } else {
                binding.badgeHires.visibility = View.GONE
            }

            val isBitPerfect = audioInfo?.isBitPerfect == true || playbackService?.isBitPerfectActive == true
            if (isBitPerfect) {
                binding.badgeBitPerfect.visibility = View.VISIBLE
            } else {
                binding.badgeBitPerfect.visibility = View.GONE
            }
        } else {
            binding.audioBadgeContainer.visibility = View.GONE
        }
    }

    fun showTrackDetailsDialog(
        context: Context,
        playbackService: PlaybackService?
    ) {
        val syncManager = OmniSyncManager.getInstance(context)
        if (syncManager.currentRole == OmniSyncRole.LISTENER) {
            val format = syncManager.currentTrackFormat.ifEmpty { "Audio" }
            val quality = syncManager.currentTrackQuality
            val isHiRes = syncManager.currentTrackIsHiRes
            val latency = syncManager.currentLatencyMs
            val hostName = syncManager.currentHostRoomName ?: syncManager.currentHost?.name ?: "Host"
            val streamQualityStr = if (syncManager.streamQuality == OmniSyncQuality.LOW) "Low (32-bit Float Direct)" else "High (Native)"

            val details = """
                AUDIO INPUT
                Source Format: $format
                Resolution: ${quality.ifEmpty { if (isHiRes) "24-bit PCM" else "16-bit PCM" }}
                Stream Quality: $streamQualityStr
                Broadcast Host: $hostName

                PROCESSING PIPELINE
                Audio Engine: OmniSync Ultra-Low-Latency Audio Engine
                Latency: ${latency}ms
                Pipeline: ${if (syncManager.streamQuality == OmniSyncQuality.LOW) "Direct 32-bit Float Pipeline" else "Native Bitstream Pipeline"}

                AUDIO OUTPUT
                Output Mode: Android AudioTrack (${if (syncManager.streamQuality == OmniSyncQuality.LOW) "32-bit Float Engine" else "Native Engine"})
            """.trimIndent()

            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.track_details)
                .setMessage(details)
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val song = playbackService?.currentSong
        if (song == null) {
            Toast.makeText(context, "No track currently playing", Toast.LENGTH_SHORT).show()
            return
        }

        val info = playbackService?.currentAudioInfo
        val format = info?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val quality = info?.formatQualityString()?.takeIf { it.isNotEmpty() } ?: song.audioQuality
        val isHiRes = isHiResAudio(song, info, format, quality)
        val isBitPerfect = info?.isBitPerfect == true || playbackService?.isBitPerfectActive == true
        val outputDevice = playbackService?.getAudioOutputDeviceInfo() ?: "Default Audio Output"

        val inputResolution = when {
            format.startsWith("DSD") -> {
                val mhz = when {
                    info != null && info.sampleRate >= 11289600 -> "11.28 MHz"
                    info != null && info.sampleRate >= 5644800 -> "5.64 MHz"
                    info != null && info.sampleRate >= 2822400 -> "2.82 MHz"
                    else -> "2.82 MHz"
                }
                "1-bit PDM @ $mhz"
            }
            info != null && info.bitDepth > 0 && info.sampleRate > 0 -> {
                val khz = String.format(Locale.US, "%.1f kHz", info.sampleRate / 1000.0)
                "${info.bitDepth}-bit PCM @ $khz"
            }
            song.audioQuality.isNotEmpty() -> song.audioQuality
            else -> if (isHiRes) "24-bit PCM @ 96.0 kHz" else "16-bit PCM @ 44.1 kHz"
        }

        val inputBitrate = when {
            info != null && info.bitrate > 0 -> "${info.bitrate / 1000} kbps"
            song.duration > 0 && song.fileSize > 0 -> "${(song.fileSize * 8) / song.duration} kbps"
            else -> "Lossless Variable"
        }

        val inputChannels = "${info?.channels ?: 2}.0 Stereo"

        val pipelineProcessing = when {
            format.startsWith("DSD") -> "64-tap Blackman-Nuttall Windowed-Sinc FIR Filter -> Studio 32-bit Float PCM"
            isHiRes -> "Native 32-bit Float High-Res Audio Engine"
            else -> "Native 32-bit Float Audio Engine"
        }

        val audioEngine = if (isHiRes) {
            "32-bit Floating Point High-Res PCM"
        } else {
            "32-bit Floating Point PCM"
        }

        val audioOutputResolution = if (isBitPerfect) {
            when {
                format.startsWith("DSD") -> "Direct DSD Bitstream (Bit-Exact)"
                info != null && info.bitDepth > 0 && info.sampleRate > 0 -> {
                    val khz = if (info.sampleRate % 1000 == 0) "${info.sampleRate / 1000}khz" else String.format(Locale.US, "%.1fkhz", info.sampleRate / 1000.0)
                    "${info.bitDepth}bit/$khz (Bit-Exact)"
                }
                else -> "Bit-Exact Pass-Through"
            }
        } else {
            "16bit/48khz"
        }

        val outputMode = if (isBitPerfect) {
            "Direct HAL / USB Hardware Pass-Through"
        } else if (isHiRes) {
            "Android AudioTrack (High-Res 32-bit Float Engine)"
        } else {
            "Android AudioTrack (Direct 32-bit Float Engine)"
        }

        val details = """
            AUDIO INPUT
            Source Format: $format
            Resolution: $inputResolution
            Bitrate: $inputBitrate
            Channels: $inputChannels

            PROCESSING PIPELINE
            Audio Engine: $audioEngine
            Signal Processing: $pipelineProcessing
            Software Resampling: ${if (isBitPerfect) "Bypassed (Bit-Exact)" else "Direct Float"}
            Hardware Acceleration: Enabled (Zero-Copy Buffer Queue)

            AUDIO OUTPUT
            Active Device: $outputDevice
            Output: $audioOutputResolution
            Output Mode: $outputMode
        """.trimIndent()

        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.track_details)
            .setMessage(details)
            .setPositiveButton("OK", null)
            .show()
    }
}
