package xyz.omniplay.ui

import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Build
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

    fun isAudioFloatSupported(): Boolean {
        return try {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    AudioTrack.getMinBufferSize(
                        48000,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_FLOAT
                    ) > 0
        } catch (e: Throwable) {
            false
        }
    }

    fun isAudio24BitSupported(): Boolean {
        return try {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                    AudioTrack.getMinBufferSize(
                        48000,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_24BIT_PACKED
                    ) > 0
        } catch (e: Throwable) {
            false
        }
    }

    fun isAudio16BitSupported(): Boolean {
        return try {
            AudioTrack.getMinBufferSize(
                48000,
                AudioFormat.CHANNEL_OUT_STEREO,
                AudioFormat.ENCODING_PCM_16BIT
            ) > 0
        } catch (e: Throwable) {
            true
        }
    }

    fun getDeviceSupportedPcmBitDepth(context: Context): Int {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
                val devices = audioManager?.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
                if (devices != null) {
                    for (dev in devices) {
                        val encodings = dev.encodings
                        if (encodings.contains(AudioFormat.ENCODING_PCM_FLOAT) || encodings.contains(AudioFormat.ENCODING_PCM_32BIT)) {
                            return 32
                        }
                        if (encodings.contains(AudioFormat.ENCODING_PCM_24BIT_PACKED)) {
                            return 24
                        }
                    }
                }
            }
            16
        } catch (e: Throwable) {
            16
        }
    }

    fun encodingToBitDepth(encoding: Int): Int {
        return when (encoding) {
            AudioFormat.ENCODING_PCM_FLOAT -> 32
            AudioFormat.ENCODING_PCM_32BIT -> 32
            AudioFormat.ENCODING_PCM_24BIT_PACKED -> 24
            AudioFormat.ENCODING_PCM_16BIT -> 16
            AudioFormat.ENCODING_PCM_8BIT -> 8
            else -> 0
        }
    }

    fun encodingToDescription(encoding: Int): String {
        return when (encoding) {
            AudioFormat.ENCODING_PCM_FLOAT -> "32-bit Float Audio Engine"
            AudioFormat.ENCODING_PCM_32BIT -> "32-bit Integer PCM Engine"
            AudioFormat.ENCODING_PCM_24BIT_PACKED -> "24-bit PCM Engine"
            AudioFormat.ENCODING_PCM_16BIT -> "16-bit Standard PCM Engine"
            AudioFormat.ENCODING_PCM_8BIT -> "8-bit PCM Engine"
            else -> "Standard PCM Engine"
        }
    }

    fun isQualityStringHiRes(quality: String): Boolean {
        if (quality.isEmpty()) return false
        val qNorm = quality.lowercase(Locale.ROOT).replace(" ", "").replace("-", "")
        return qNorm.contains("24bit") ||
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
    }

    fun getDeviceNativeSampleRate(context: Context): Int {
        return try {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
            val prop = audioManager?.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE)
            prop?.toIntOrNull()?.takeIf { it > 0 } ?: 48000
        } catch (e: Throwable) {
            48000
        }
    }

    fun getAudioOutputDeviceInfo(context: Context): String {
        return try {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
                ?: return "Default Audio Output"
            val devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
            val usbDevice = devices.find {
                it.type == AudioDeviceInfo.TYPE_USB_DEVICE ||
                it.type == AudioDeviceInfo.TYPE_USB_HEADSET ||
                it.type == AudioDeviceInfo.TYPE_USB_ACCESSORY
            }
            if (usbDevice != null) {
                val prodName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    usbDevice.productName?.toString()?.takeIf { it.isNotBlank() }
                } else null
                prodName ?: "USB DAC / External Audio Device"
            } else {
                val btDevice = devices.find {
                    it.type == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP ||
                    it.type == AudioDeviceInfo.TYPE_BLUETOOTH_SCO
                }
                if (btDevice != null) {
                    val prodName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        btDevice.productName?.toString()?.takeIf { it.isNotBlank() }
                    } else null
                    prodName ?: "Bluetooth Audio (Wireless A2DP)"
                } else {
                    val wired = devices.find {
                        it.type == AudioDeviceInfo.TYPE_WIRED_HEADPHONES ||
                        it.type == AudioDeviceInfo.TYPE_WIRED_HEADSET
                    }
                    if (wired != null) {
                        "Wired Headphones (3.5mm Analog Output)"
                    } else {
                        "Built-in Device Speaker"
                    }
                }
            }
        } catch (e: Throwable) {
            "Default Audio Output"
        }
    }

    fun isHiResAudio(
        song: Song?,
        audioInfo: AudioTrackInfo?,
        format: String,
        quality: String
    ): Boolean {
        if (song == null) return false
        val isHiResQuality = isQualityStringHiRes(quality)

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
            val outputDevice = playbackService?.getAudioOutputDeviceInfo() ?: getAudioOutputDeviceInfo(context)

            val isHighQuality = syncManager.streamQuality == OmniSyncQuality.HIGH
            val renderingBitDepth = syncManager.getRenderingBitDepth(syncManager.streamQuality)
            val actualEncoding = syncManager.actualAudioTrackEncoding

            val sr = if (syncManager.actualAudioTrackSampleRate > 0) {
                syncManager.actualAudioTrackSampleRate
            } else if (syncManager.listenerSampleRate > 0) {
                syncManager.listenerSampleRate
            } else {
                getDeviceNativeSampleRate(context)
            }
            val khz = if (sr % 1000 == 0) "${sr / 1000}khz" else String.format(Locale.US, "%.1fkhz", sr / 1000.0)
            val renderedResolution = "${renderingBitDepth}bit/$khz"

            val streamQualityStr = if (isHighQuality) {
                if (syncManager.isAudioFloatSupported()) "High ($renderingBitDepth-bit Float Audio Engine)" else "High ($renderingBitDepth-bit Native Engine)"
            } else {
                "Low ($renderingBitDepth-bit Standard PCM Engine)"
            }

            val pipelineStr = if (isHighQuality) {
                if (syncManager.isAudioFloatSupported()) "Direct $renderingBitDepth-bit Float Pipeline" else "Native $renderingBitDepth-bit Bitstream Pipeline"
            } else {
                "Standard $renderingBitDepth-bit Integer PCM Pipeline"
            }

            val outputEncodingDesc = if (actualEncoding > 0) {
                encodingToDescription(actualEncoding)
            } else if (isHighQuality && syncManager.isAudioFloatSupported()) {
                "High-Res 32-bit Float Engine"
            } else {
                "Standard $renderingBitDepth-bit PCM Engine"
            }
            val outputModeStr = "Android AudioTrack ($outputEncodingDesc)"

            val sourceResolution = syncManager.currentSourceQuality.ifEmpty {
                quality.ifEmpty { if (isHiRes) "24-bit PCM" else "16-bit PCM" }
            }

            val details = """
                AUDIO INPUT
                Source Format: $format
                Resolution: $sourceResolution
                Stream Quality: $streamQualityStr
                Broadcast Host: $hostName

                PROCESSING PIPELINE
                Audio Engine: OmniSync Ultra-Low-Latency Audio Engine
                Latency: ${latency}ms
                Pipeline: $pipelineStr

                AUDIO OUTPUT
                Active Device: $outputDevice
                Output: $renderedResolution
                Output Mode: $outputModeStr
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
        val outputDevice = playbackService?.getAudioOutputDeviceInfo() ?: getAudioOutputDeviceInfo(context)

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
            val nativeSr = getDeviceNativeSampleRate(context)
            val nativeKhz = if (nativeSr % 1000 == 0) "${nativeSr / 1000}khz" else String.format(Locale.US, "%.1fkhz", nativeSr / 1000.0)
            if (isAudioFloatSupported()) {
                val outKhz = if (info != null && info.sampleRate > 0) {
                    if (info.sampleRate % 1000 == 0) "${info.sampleRate / 1000}khz" else String.format(Locale.US, "%.1fkhz", info.sampleRate / 1000.0)
                } else nativeKhz
                "32bit/$outKhz (Float Engine)"
            } else {
                "16bit/$nativeKhz"
            }
        }

        val outputMode = if (isBitPerfect) {
            "Direct HAL / USB Hardware Pass-Through"
        } else if (isAudioFloatSupported() && (isHiRes || (info != null && info.bitDepth > 16))) {
            "Android AudioTrack (High-Res 32-bit Float Engine)"
        } else if (isAudioFloatSupported()) {
            "Android AudioTrack (Direct 32-bit Float Engine)"
        } else {
            "Android AudioTrack (Standard 16-bit PCM Engine)"
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
