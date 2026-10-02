package xyz.omniplay.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.media.AudioDeviceCallback
import android.media.AudioDeviceInfo
import android.media.AudioMixerAttributes
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.annotation.OptIn
import androidx.core.app.NotificationCompat
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.ui.MainActivity
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.ExtractorsFactory
import xyz.omniplay.dsd.DsdExtractor
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.AudioInfoExtractor
import xyz.omniplay.util.AudioTrackInfo
import java.util.Locale
import java.util.concurrent.CopyOnWriteArrayList

@OptIn(UnstableApi::class)
class PlaybackService : Service() {

    companion object {
        const val CHANNEL_ID = "omniplay_playback_channel"
        const val NOTIFICATION_ID = 1001

        const val ACTION_PLAY = "xyz.omniplay.ACTION_PLAY"
        const val ACTION_PAUSE = "xyz.omniplay.ACTION_PAUSE"
        const val ACTION_TOGGLE = "xyz.omniplay.ACTION_TOGGLE"
        const val ACTION_NEXT = "xyz.omniplay.ACTION_NEXT"
        const val ACTION_PREVIOUS = "xyz.omniplay.ACTION_PREVIOUS"

        const val REPEAT_OFF = 0
        const val REPEAT_ALL = 1
        const val REPEAT_ONE = 2
    }

    private val binder = LocalBinder()
    private var player: ExoPlayer? = null
    private var mediaSession: MediaSessionCompat? = null
    private lateinit var audioManager: android.media.AudioManager

    // Android 14+ Bit-Perfect Audio variables (activated silently for USB DACs)
    private var audioDeviceCallback: AudioDeviceCallback? = null

    // Playback state
    var currentSong: Song? = null
        private set
    var currentAudioInfo: AudioTrackInfo? = null
        private set
    var queue: MutableList<Song> = mutableListOf()
        private set
    private var originalQueue: List<Song> = emptyList()
    var currentIndex: Int = -1
        private set

    var isShuffleEnabled: Boolean = false
        private set
    var repeatMode: Int = REPEAT_OFF
        private set

    var isExternalSongActive: Boolean = false
        private set
    var externalSong: Song? = null
        private set

    private var currentAlbumArt: Bitmap? = null
    private var progressJob: Job? = null
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val listeners = CopyOnWriteArrayList<PlaybackListener>()

    interface PlaybackListener {
        fun onTrackChanged(song: Song?)
        fun onPlaybackStateChanged(isPlaying: Boolean)
        fun onProgressUpdate(currentPositionMs: Int, totalDurationMs: Int)
        fun onShuffleModeChanged(enabled: Boolean)
        fun onRepeatModeChanged(mode: Int)
        fun onQueueChanged(queue: List<Song>)
        fun onAudioInfoChanged(audioInfo: AudioTrackInfo?) {}
    }

    inner class LocalBinder : Binder() {
        fun getService(): PlaybackService = this@PlaybackService
    }

    override fun onCreate() {
        super.onCreate()
        audioManager = getSystemService(Context.AUDIO_SERVICE) as android.media.AudioManager
        createNotificationChannel()
        initMediaSession()
        initPlayer()
        setupBitPerfectAudio()
        registerBecomingNoisyReceiver()

        val sync = xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext)
        sync.hostSongProvider = { currentSong }
        sync.hostPlaybackPositionProvider = { getCurrentPosition().toLong() }
        sync.hostIsPlayingProvider = { isPlaying() }
        sync.addListener(omniSyncListener)
    }

    private val omniSyncListener = object : xyz.omniplay.sync.OmniSyncManager.OmniSyncListener {
        override fun onRoleChanged(role: xyz.omniplay.sync.OmniSyncRole) {
            if (role == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
                if (isPlaying()) {
                    pause()
                }
            }
        }
        override fun onHostsDiscovered(hosts: List<xyz.omniplay.sync.OmniSyncHost>) {}
        override fun onPeersChanged(peers: List<xyz.omniplay.sync.OmniSyncPeer>) {}
        override fun onTrackInfoChanged(title: String, artist: String) {}
        override fun onPlaybackStateChanged(isPlaying: Boolean) {}
        override fun onError(message: String) {}
    }

    /**
     * Initializes ExoPlayer configured for High-Res Audio output by default.
     * Enables 32-bit floating point PCM output (setEnableAudioFloatOutput)
     * so 24-bit and 32-bit / 192kHz FLAC, WAV, ALAC, etc. play without downsampling or distortion.
     */
    private fun initPlayer() {
        val renderersFactory = DefaultRenderersFactory(applicationContext)
            .setEnableAudioFloatOutput(true) // Native 32-bit float Hi-Res output
            .setEnableAudioTrackPlaybackParams(true)

        val extractorsFactory = ExtractorsFactory {
            arrayOf(
                DsdExtractor(),
                *DefaultExtractorsFactory().createExtractors()
            )
        }
        val mediaSourceFactory = DefaultMediaSourceFactory(applicationContext, extractorsFactory)

        val exo = ExoPlayer.Builder(applicationContext, renderersFactory)
            .setMediaSourceFactory(mediaSourceFactory)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                true // Auto handle audio focus (pause on call, duck, resume)
            )
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()

        exo.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> {
                        val dur = exo.duration.takeIf { it > 0L } ?: currentSong?.duration ?: 0L
                        currentSong?.let {
                            if (it.duration <= 0L && dur > 0L) {
                                currentSong = it.copy(duration = dur)
                                listeners.forEach { l -> l.onTrackChanged(currentSong) }
                            }
                            updateMediaMetadata(it, dur, currentAlbumArt)
                        }
                        val isPlaying = exo.isPlaying
                        val state = if (isPlaying) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED
                        updatePlaybackState(state, exo.currentPosition.coerceAtLeast(0L))
                    }
                    Player.STATE_ENDED -> {
                        skipNext(forceNext = false)
                    }
                    Player.STATE_IDLE, Player.STATE_BUFFERING -> {}
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying && currentSong != null) {
                    updatePlaybackState(PlaybackStateCompat.STATE_PLAYING, exo.currentPosition.coerceAtLeast(0L))
                    startProgressTracker()
                    try {
                        startForeground(NOTIFICATION_ID, buildNotification(isPlaying = true))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    stopProgressTracker()
                    updatePlaybackState(PlaybackStateCompat.STATE_PAUSED, exo.currentPosition.coerceAtLeast(0L))
                    updateNotification(isPlaying = false)
                }
                listeners.forEach { it.onPlaybackStateChanged(isPlaying) }
            }

            override fun onTracksChanged(tracks: androidx.media3.common.Tracks) {
                for (group in tracks.groups) {
                    if (group.type == C.TRACK_TYPE_AUDIO && group.isSelected) {
                        for (i in 0 until group.length) {
                            if (group.isTrackSelected(i)) {
                                val exoFormat = group.getTrackFormat(i)
                                val exoInfo = AudioInfoExtractor.fromExoFormat(exoFormat, currentSong?.format ?: "")
                                currentAudioInfo = AudioInfoExtractor.merge(currentAudioInfo, exoInfo)
                                notifyAudioInfoChanged(currentAudioInfo)
                                break
                            }
                        }
                    }
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                // If a playback error occurs, advance safely without hanging
                skipNext(forceNext = false)
            }
        })

        player = exo
    }

    var isBitPerfectActive: Boolean = false
        private set

    fun getAudioOutputDeviceInfo(): String {
        return try {
            val devices = audioManager.getDevices(android.media.AudioManager.GET_DEVICES_OUTPUTS)
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

    /**
     * Checks if a bit-perfect capable audio output (such as an external USB DAC)
     * is connected and active.
     */
    fun checkBitPerfectCapability(): Boolean {
        return try {
            val devices = audioManager.getDevices(android.media.AudioManager.GET_DEVICES_OUTPUTS)
            devices.any {
                it.type == AudioDeviceInfo.TYPE_USB_DEVICE ||
                it.type == AudioDeviceInfo.TYPE_USB_HEADSET ||
                it.type == AudioDeviceInfo.TYPE_USB_ACCESSORY
            }
        } catch (e: Throwable) {
            false
        }
    }

    private fun setupBitPerfectAudio() {
        updateBitPerfectState()

        audioDeviceCallback = object : AudioDeviceCallback() {
            override fun onAudioDevicesAdded(addedDevices: Array<out AudioDeviceInfo>?) {
                updateBitPerfectState()
            }

            override fun onAudioDevicesRemoved(removedDevices: Array<out AudioDeviceInfo>?) {
                updateBitPerfectState()
            }
        }
        try {
            audioManager.registerAudioDeviceCallback(audioDeviceCallback, null)
        } catch (ignored: Throwable) {}
    }

    private fun updateBitPerfectState() {
        val hasUsbDac = checkBitPerfectCapability()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && hasUsbDac) {
            applyBitPerfectIfCapable()
        }
        isBitPerfectActive = hasUsbDac
        currentAudioInfo = currentAudioInfo?.copy(isBitPerfect = isBitPerfectActive)
        notifyAudioInfoChanged(currentAudioInfo)
    }

    private fun applyBitPerfectIfCapable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            try {
                val mediaAttributes = android.media.AudioAttributes.Builder()
                    .setUsage(android.media.AudioAttributes.USAGE_MEDIA)
                    .setContentType(android.media.AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()

                val devices = audioManager.getDevices(android.media.AudioManager.GET_DEVICES_OUTPUTS)
                for (device in devices) {
                    if (device.type == AudioDeviceInfo.TYPE_USB_DEVICE ||
                        device.type == AudioDeviceInfo.TYPE_USB_HEADSET ||
                        device.type == AudioDeviceInfo.TYPE_USB_ACCESSORY) {

                        val supportedAttrs = audioManager.getSupportedMixerAttributes(device)
                        val bitPerfectAttr = supportedAttrs.find {
                            it.mixerBehavior == AudioMixerAttributes.MIXER_BEHAVIOR_BIT_PERFECT
                        }
                        if (bitPerfectAttr != null) {
                            audioManager.setPreferredMixerAttributes(mediaAttributes, device, bitPerfectAttr)
                        } else {
                            try {
                                val customBitPerfect = AudioMixerAttributes.Builder(
                                    android.media.AudioFormat.Builder()
                                        .setEncoding(android.media.AudioFormat.ENCODING_PCM_FLOAT)
                                        .setSampleRate(192000)
                                        .setChannelMask(android.media.AudioFormat.CHANNEL_OUT_STEREO)
                                        .build()
                                )
                                    .setMixerBehavior(AudioMixerAttributes.MIXER_BEHAVIOR_BIT_PERFECT)
                                    .build()
                                audioManager.setPreferredMixerAttributes(mediaAttributes, device, customBitPerfect)
                            } catch (ignored: Throwable) {}
                        }
                    }
                }
            } catch (ignored: Throwable) {}
        }
    }

    private fun teardownBitPerfectAudio() {
        try {
            audioDeviceCallback?.let {
                audioManager.unregisterAudioDeviceCallback(it)
            }
            audioDeviceCallback = null
        } catch (ignored: Throwable) {}
    }

    private fun initMediaSession() {
        mediaSession = MediaSessionCompat(this, "OmniplayMediaSession").apply {
            setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
            )
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() { play() }
                override fun onPause() { pause() }
                override fun onSkipToNext() { skipNext(forceNext = true) }
                override fun onSkipToPrevious() { skipPrevious(forcePrevious = false) }
                override fun onSeekTo(pos: Long) { seekTo(pos.toInt()) }
                override fun onStop() { pause() }
            })
            isActive = true
        }
    }

    fun addListener(listener: PlaybackListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
        currentSong?.let { listener.onTrackChanged(it) }
        listener.onPlaybackStateChanged(isPlaying())
        listener.onShuffleModeChanged(isShuffleEnabled)
        listener.onRepeatModeChanged(repeatMode)
        if (queue.isNotEmpty()) {
            listener.onQueueChanged(queue.toList())
        }
    }

    fun removeListener(listener: PlaybackListener) {
        listeners.remove(listener)
    }

    fun setSongQueue(songs: List<Song>, startIndex: Int = 0, startPlaying: Boolean = true) {
        if (songs.isEmpty()) return
        originalQueue = songs.toList()
        if (isShuffleEnabled) {
            val selected = if (startIndex in songs.indices) songs[startIndex] else songs.first()
            val remaining = songs.filter { it.id != selected.id }.shuffled()
            queue = (listOf(selected) + remaining).toMutableList()
            currentIndex = 0
        } else {
            queue = songs.toMutableList()
            currentIndex = if (startIndex in queue.indices) startIndex else 0
        }

        listeners.forEach { it.onQueueChanged(queue.toList()) }

        if (startPlaying) {
            isExternalSongActive = false
            externalSong = null
            if (queue.isNotEmpty() && currentIndex in queue.indices) {
                val song = queue[currentIndex]
                currentSong = song
                playSong(song, startPlaying = true)
            }
        } else {
            if (currentSong == null) {
                if (startIndex in queue.indices) {
                    val song = queue[currentIndex]
                    currentSong = song
                    playSong(song, startPlaying = false)
                } else {
                    currentIndex = -1
                }
            } else if (isExternalSongActive) {
                currentIndex = startIndex.coerceIn(0, (queue.size - 1).coerceAtLeast(0))
            } else {
                currentIndex = if (isShuffleEnabled) {
                    0
                } else {
                    queue.indexOfFirst { it.id == currentSong?.id }.coerceAtLeast(0)
                }
            }
        }
    }

    fun playSongFromPlaylist(song: Song, index: Int = -1, startPlaying: Boolean = true) {
        isExternalSongActive = false
        externalSong = null
        if (isShuffleEnabled) {
            val matching = originalQueue.find { it.id == song.id } ?: queue.find { it.id == song.id } ?: song
            val remaining = originalQueue.filter { it.id != matching.id }.shuffled()
            queue = (listOf(matching) + remaining).toMutableList()
            currentIndex = 0
            playSong(matching, startPlaying = startPlaying)
            listeners.forEach { it.onQueueChanged(queue.toList()) }
        } else {
            val targetIndex = if (index in queue.indices && queue[index].id == song.id) {
                index
            } else {
                val idx = queue.indexOfFirst { it.id == song.id }
                if (idx != -1) idx else index.coerceIn(0, (queue.size - 1).coerceAtLeast(0))
            }
            if (queue.isNotEmpty() && targetIndex in queue.indices) {
                currentIndex = targetIndex
                playSong(queue[currentIndex], startPlaying = startPlaying)
            }
        }
    }

    /**
     * Refreshes the active queue with newly scanned songs.
     * If the current song is still in the list, its metadata is updated and UI notified without interrupting playback.
     */
    fun refreshQueue(newSongs: List<Song>) {
        if (newSongs.isEmpty()) {
            originalQueue = emptyList()
            queue.clear()
            if (!isExternalSongActive) {
                currentIndex = -1
                currentSong = null
                currentAlbumArt = null
                try {
                    player?.stop()
                    player?.clearMediaItems()
                } catch (e: Exception) {}
                stopProgressTracker()
                updatePlaybackState(PlaybackStateCompat.STATE_NONE)
                mediaSession?.setMetadata(null)
                updateNotification(isPlaying = false)
                listeners.forEach {
                    try {
                        it.onTrackChanged(null)
                        it.onQueueChanged(emptyList())
                        it.onPlaybackStateChanged(false)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else {
                listeners.forEach {
                    try {
                        it.onQueueChanged(emptyList())
                    } catch (e: Exception) {}
                }
            }
            return
        }

        originalQueue = newSongs.toList()
        val currentId = currentSong?.id

        if (isExternalSongActive) {
            queue = newSongs.toMutableList()
            listeners.forEach { it.onQueueChanged(queue.toList()) }
            return
        }

        if (currentId != null) {
            val updatedCurrent = newSongs.find { it.id == currentId }
            if (updatedCurrent != null) {
                currentSong = updatedCurrent
                listeners.forEach { it.onTrackChanged(updatedCurrent) }

                if (isShuffleEnabled) {
                    val remaining = newSongs.filter { it.id != currentId }.shuffled()
                    queue = (listOf(updatedCurrent) + remaining).toMutableList()
                    currentIndex = 0
                } else {
                    queue = newSongs.toMutableList()
                    currentIndex = queue.indexOfFirst { it.id == currentId }.coerceAtLeast(0)
                }
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            } else {
                // Currently playing song was deleted/removed from folder
                if (isShuffleEnabled) {
                    queue = newSongs.shuffled().toMutableList()
                } else {
                    queue = newSongs.toMutableList()
                }
                currentIndex = 0
                listeners.forEach { it.onQueueChanged(queue.toList()) }
                val nextSong = queue[0]
                prepareWithoutPlaying(nextSong)
            }
        } else {
            // No song currently playing or selected
            queue = if (isShuffleEnabled) newSongs.shuffled().toMutableList() else newSongs.toMutableList()
            currentIndex = -1
            listeners.forEach { it.onQueueChanged(queue.toList()) }
        }
    }

    private fun prepareWithoutPlaying(song: Song) {
        currentSong = song
        updateAudioInfoForSong(song)
        listeners.forEach { it.onTrackChanged(song) }
        serviceScope.launch {
            currentAlbumArt = AlbumArtLoader.loadAlbumArt(this@PlaybackService, song)
        }
        try {
            val p = player ?: return
            p.stop()
            if (song.contentUri != Uri.EMPTY) {
                val mediaItem = MediaItem.fromUri(song.contentUri)
                p.setMediaItem(mediaItem)
                p.prepare()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playExternalSong(song: Song) {
        isExternalSongActive = true
        externalSong = song
        playSong(song, startPlaying = true)
    }

    fun playSong(song: Song, startPlaying: Boolean = true) {
        if (xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
            return
        }
        if (isExternalSongActive && song != externalSong) {
            isExternalSongActive = false
            externalSong = null
        }
        currentSong = song
        updateAudioInfoForSong(song)
        listeners.forEach { it.onTrackChanged(song) }
        updateMediaMetadata(song)

        serviceScope.launch {
            val art = AlbumArtLoader.loadAlbumArt(this@PlaybackService, song)
            currentAlbumArt = art
            updateMediaMetadata(song, song.duration, art)
            updateNotification(isPlaying = startPlaying && isPlaying())
        }

        try {
            val p = player ?: return
            p.stop()
            if (song.contentUri != Uri.EMPTY) {
                val mediaItem = MediaItem.fromUri(song.contentUri)
                p.setMediaItem(mediaItem)
                p.prepare()
                if (startPlaying) {
                    p.play()
                    xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(song, 0L)
                } else {
                    stopProgressTracker()
                    updatePlaybackState(PlaybackStateCompat.STATE_PAUSED, 0L)
                    updateNotification(isPlaying = false)
                    listeners.forEach { it.onPlaybackStateChanged(false) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateAudioInfoForSong(song: Song) {
        val qNorm = (song.audioQuality + " " + song.filePath).lowercase(Locale.ROOT).replace(" ", "").replace("-", "")
        val initialHiRes = song.isHiRes ||
                song.format.startsWith("DSD", true) ||
                song.format.equals("DSF", true) ||
                song.format.equals("DFF", true) ||
                qNorm.contains("24bit") || qNorm.contains("32bit") ||
                qNorm.contains("24/") || qNorm.contains("32/") ||
                qNorm.contains("/24") || qNorm.contains("/32") ||
                qNorm.contains("96khz") || qNorm.contains("88.2khz") ||
                qNorm.contains("176.4khz") || qNorm.contains("192khz") ||
                qNorm.contains("352.8khz") || qNorm.contains("384khz") ||
                qNorm.contains("88.2") || qNorm.contains("96") ||
                qNorm.contains("176.4") || qNorm.contains("192") ||
                qNorm.contains("352.8") || qNorm.contains("384") ||
                qNorm.contains("mhz") || qNorm.contains("dsd") ||
                qNorm.contains("hires") || qNorm.contains("hi-res")

        val initialBitDepth = when {
            qNorm.contains("32bit") || qNorm.contains("32/") || qNorm.contains("/32") -> 32
            qNorm.contains("24bit") || qNorm.contains("24/") || qNorm.contains("/24") -> 24
            qNorm.contains("16bit") || qNorm.contains("16/") || qNorm.contains("/16") -> 16
            song.format.startsWith("DSD", true) || song.format.equals("DSF", true) || song.format.equals("DFF", true) -> 1
            initialHiRes -> 24
            song.format in listOf("FLAC", "WAV", "ALAC", "AIFF") -> 16
            else -> 0
        }

        val initialSampleRate = when {
            qNorm.contains("384khz") || qNorm.contains("384000") || qNorm.contains("384") -> 384000
            qNorm.contains("352.8khz") || qNorm.contains("352800") || qNorm.contains("352.8") -> 352800
            qNorm.contains("192khz") || qNorm.contains("192000") || qNorm.contains("192") -> 192000
            qNorm.contains("176.4khz") || qNorm.contains("176400") || qNorm.contains("176.4") -> 176400
            qNorm.contains("96khz") || qNorm.contains("96000") || qNorm.contains("96") -> 96000
            qNorm.contains("88.2khz") || qNorm.contains("88200") || qNorm.contains("88.2") -> 88200
            qNorm.contains("48khz") || qNorm.contains("48000") -> 48000
            qNorm.contains("44.1khz") || qNorm.contains("44100") || qNorm.contains("44.1") -> 44100
            else -> 0
        }

        currentAudioInfo = AudioTrackInfo(
            format = song.format,
            bitDepth = initialBitDepth,
            sampleRate = initialSampleRate,
            isHiRes = initialHiRes,
            isBitPerfect = isBitPerfectActive
        )
        notifyAudioInfoChanged(currentAudioInfo)

        serviceScope.launch(Dispatchers.IO) {
            val extracted = AudioInfoExtractor.extractFromUri(applicationContext, song.contentUri, song.format)
            val merged = AudioInfoExtractor.merge(extracted, currentAudioInfo).copy(
                isBitPerfect = isBitPerfectActive
            )
            serviceScope.launch(Dispatchers.Main) {
                if (currentSong?.id == song.id) {
                    currentAudioInfo = merged
                    notifyAudioInfoChanged(currentAudioInfo)
                }
            }
        }
    }

    private fun notifyAudioInfoChanged(info: AudioTrackInfo?) {
        listeners.forEach {
            try {
                it.onAudioInfoChanged(info)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun play() {
        if (xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).currentRole == xyz.omniplay.sync.OmniSyncRole.LISTENER) {
            return
        }
        if (currentSong == null) {
            if (queue.isNotEmpty()) {
                currentIndex = 0
                playSong(queue[0])
            }
            return
        }

        player?.let {
            if (!it.isPlaying) {
                it.play()
                updatePlaybackState(PlaybackStateCompat.STATE_PLAYING, getCurrentPosition().toLong())
                startProgressTracker()
                try {
                    startForeground(NOTIFICATION_ID, buildNotification(isPlaying = true))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                listeners.forEach { l -> l.onPlaybackStateChanged(true) }
                currentSong?.let { s ->
                    xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(s, getCurrentPosition().toLong())
                }
            }
        }
    }

    fun pause() {
        player?.let {
            if (it.isPlaying) {
                it.pause()
                stopProgressTracker()
                updatePlaybackState(PlaybackStateCompat.STATE_PAUSED, getCurrentPosition().toLong())
                updateNotification(isPlaying = false)
                listeners.forEach { l -> l.onPlaybackStateChanged(false) }
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPause()
            }
        }
    }

    fun togglePlayPause() {
        if (isPlaying()) {
            pause()
        } else {
            play()
        }
    }

    fun isPlaying(): Boolean {
        return player?.isPlaying == true
    }

    fun skipNext(forceNext: Boolean = false) {
        val wasPlaying = if (forceNext) isPlaying() else true

        if (isExternalSongActive) {
            isExternalSongActive = false
            externalSong = null
            if (queue.isNotEmpty()) {
                val targetIndex = if (currentIndex in queue.indices) {
                    if (forceNext) (currentIndex + 1) % queue.size else currentIndex
                } else {
                    0
                }
                currentIndex = targetIndex
                playSong(queue[targetIndex], startPlaying = wasPlaying)
            } else {
                currentSong = null
                currentAlbumArt = null
                try {
                    player?.stop()
                    player?.clearMediaItems()
                } catch (e: Exception) {}
                stopProgressTracker()
                updatePlaybackState(PlaybackStateCompat.STATE_NONE)
                mediaSession?.setMetadata(null)
                updateNotification(isPlaying = false)
                listeners.forEach {
                    it.onTrackChanged(null)
                    it.onPlaybackStateChanged(false)
                }
            }
            return
        }

        if (queue.isEmpty()) return
        if (!forceNext && repeatMode == REPEAT_ONE) {
            currentSong?.let { playSong(it, startPlaying = wasPlaying) }
            return
        }

        if (isShuffleEnabled) {
            if (queue.size > 1) {
                val finished = queue.removeAt(0)
                queue.add(finished)
                currentIndex = 0
                val nextSong = queue[0]
                playSong(nextSong, startPlaying = wasPlaying)
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            } else {
                currentSong?.let { playSong(it, startPlaying = wasPlaying) }
            }
            return
        }

        if (queue.size <= 1) {
            if (repeatMode == REPEAT_ALL || forceNext) {
                currentSong?.let { playSong(it, startPlaying = wasPlaying) }
            } else {
                pause()
                seekTo(0)
            }
            return
        }

        currentIndex++
        if (currentIndex >= queue.size) {
            if (repeatMode == REPEAT_ALL) {
                currentIndex = 0
            } else {
                currentIndex = queue.size - 1
                pause()
                seekTo(0)
                return
            }
        }

        playSong(queue[currentIndex], startPlaying = wasPlaying)
    }

    fun skipPrevious(forcePrevious: Boolean = false) {
        val wasPlaying = isPlaying()

        if (isExternalSongActive) {
            if (!forcePrevious && getCurrentPosition() > 3000) {
                seekTo(0)
                return
            }
            isExternalSongActive = false
            externalSong = null
            if (queue.isNotEmpty()) {
                val targetIndex = if (currentIndex in queue.indices) {
                    if (forcePrevious) {
                        if (currentIndex - 1 < 0) queue.size - 1 else currentIndex - 1
                    } else {
                        currentIndex
                    }
                } else {
                    0
                }
                currentIndex = targetIndex
                playSong(queue[targetIndex], startPlaying = wasPlaying)
            } else {
                seekTo(0)
            }
            return
        }

        if (queue.isEmpty()) return

        if (!forcePrevious && getCurrentPosition() > 3000) {
            seekTo(0)
            return
        }

        if (isShuffleEnabled) {
            if (queue.size > 1) {
                val prev = queue.removeAt(queue.size - 1)
                queue.add(0, prev)
                currentIndex = 0
                playSong(prev, startPlaying = wasPlaying)
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            } else {
                seekTo(0)
            }
            return
        }

        if (queue.size <= 1) {
            seekTo(0)
            return
        }

        currentIndex--
        if (currentIndex < 0) {
            if (repeatMode == REPEAT_ALL) {
                currentIndex = queue.size - 1
            } else {
                currentIndex = 0
                seekTo(0)
                return
            }
        }

        playSong(queue[currentIndex], startPlaying = wasPlaying)
    }

    fun getNextSong(): Song? {
        if (queue.isEmpty() || queue.size <= 1) return null
        if (isShuffleEnabled) {
            return queue.getOrNull(1)
        }
        val nextIdx = currentIndex + 1
        return if (nextIdx < queue.size) {
            queue[nextIdx]
        } else if (repeatMode == REPEAT_ALL) {
            queue.firstOrNull()
        } else {
            null
        }
    }

    fun getPreviousSong(): Song? {
        if (queue.isEmpty() || queue.size <= 1) return null
        if (isShuffleEnabled) {
            return queue.lastOrNull()
        }
        val prevIdx = currentIndex - 1
        return if (prevIdx >= 0) {
            queue[prevIdx]
        } else if (repeatMode == REPEAT_ALL) {
            queue.lastOrNull()
        } else {
            null
        }
    }

    fun seekTo(positionMs: Int) {
        try {
            if (currentSong != null && currentSong?.contentUri != Uri.EMPTY) {
                player?.seekTo(positionMs.toLong())
            }
            val state = if (isPlaying()) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED
            updatePlaybackState(state, positionMs.toLong())
            listeners.forEach { it.onProgressUpdate(positionMs, getDuration()) }
            xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastSeek(positionMs.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCurrentPosition(): Int {
        return try {
            if (player?.isPlaying == true || currentSong != null) {
                player?.currentPosition?.toInt()?.coerceAtLeast(0) ?: 0
            } else 0
        } catch (e: Exception) {
            0
        }
    }

    fun getDuration(): Int {
        return try {
            if (currentSong != null) {
                val dur = player?.duration?.toInt()?.takeIf { it > 0 }
                dur ?: (currentSong?.duration?.toInt() ?: 0)
            } else 0
        } catch (e: Exception) {
            0
        }
    }

    fun toggleShuffle() {
        isShuffleEnabled = !isShuffleEnabled
        if (isShuffleEnabled) {
            val current = currentSong ?: originalQueue.firstOrNull()
            if (current != null) {
                val remaining = originalQueue.filter { it.id != current.id }.shuffled()
                queue = (listOf(current) + remaining).toMutableList()
                currentIndex = 0
            } else {
                queue = originalQueue.shuffled().toMutableList()
                currentIndex = 0
            }
        } else {
            val current = currentSong
            queue = originalQueue.toMutableList()
            currentIndex = if (current != null) {
                queue.indexOfFirst { it.id == current.id }.coerceAtLeast(0)
            } else 0
        }
        listeners.forEach {
            it.onShuffleModeChanged(isShuffleEnabled)
            it.onQueueChanged(queue.toList())
        }
    }

    fun cycleRepeatMode() {
        repeatMode = when (repeatMode) {
            REPEAT_OFF -> REPEAT_ALL
            REPEAT_ALL -> REPEAT_ONE
            else -> REPEAT_OFF
        }
        listeners.forEach { it.onRepeatModeChanged(repeatMode) }
    }

    private fun startProgressTracker() {
        progressJob?.cancel()
        progressJob = serviceScope.launch {
            var lastStateSync = 0L
            while (isActive) {
                val current = getCurrentPosition()
                val total = getDuration()
                listeners.forEach { it.onProgressUpdate(current, total) }

                val now = SystemClock.elapsedRealtime()
                if (now - lastStateSync >= 1000L) {
                    lastStateSync = now
                    updatePlaybackState(PlaybackStateCompat.STATE_PLAYING, current.toLong())
                }

                delay(80)
            }
        }
    }

    private fun stopProgressTracker() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun updateMediaMetadata(song: Song, durationMs: Long = song.duration, art: Bitmap? = currentAlbumArt) {
        val builder = MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artist)
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, song.album)
            .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, durationMs.coerceAtLeast(0L))

        art?.let {
            builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, it)
            builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ART, it)
        }
        mediaSession?.setMetadata(builder.build())
    }

    private fun updatePlaybackState(state: Int, positionMs: Long = getCurrentPosition().toLong()) {
        val speed = if (state == PlaybackStateCompat.STATE_PLAYING) 1.0f else 0.0f
        val playbackState = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or
                        PlaybackStateCompat.ACTION_SEEK_TO or
                        PlaybackStateCompat.ACTION_PLAY_PAUSE
            )
            .setState(state, positionMs.coerceAtLeast(0L), speed, SystemClock.elapsedRealtime())
            .build()
        mediaSession?.setPlaybackState(playbackState)
    }

    private fun buildNotification(isPlaying: Boolean): Notification {
        val song = currentSong ?: return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_music_note)
            .setContentTitle("Omniplay")
            .setContentText("No track playing")
            .build()

        val mainIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val mainPendingIntent = PendingIntent.getActivity(
            this, 0, mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val prevIntent = Intent(this, PlaybackService::class.java).apply { action = ACTION_PREVIOUS }
        val prevPending = PendingIntent.getService(this, 1, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val toggleIntent = Intent(this, PlaybackService::class.java).apply { action = ACTION_TOGGLE }
        val togglePending = PendingIntent.getService(this, 2, toggleIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val nextIntent = Intent(this, PlaybackService::class.java).apply { action = ACTION_NEXT }
        val nextPending = PendingIntent.getService(this, 3, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val playPauseIcon = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_music_note)
            .setContentTitle(song.title)
            .setContentText("${song.artist} • ${song.album}")
            .setContentIntent(mainPendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOngoing(isPlaying)
            .addAction(R.drawable.ic_skip_previous, "Previous", prevPending)
            .addAction(playPauseIcon, if (isPlaying) "Pause" else "Play", togglePending)
            .addAction(R.drawable.ic_skip_next, "Next", nextPending)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession?.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2)
            )

        currentAlbumArt?.let {
            builder.setLargeIcon(it)
        }

        return builder.build()
    }

    private fun updateNotification(isPlaying: Boolean) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (currentSong == null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    stopForeground(STOP_FOREGROUND_REMOVE)
                } else {
                    @Suppress("DEPRECATION")
                    stopForeground(true)
                }
                manager.cancel(NOTIFICATION_ID)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return
        }

        try {
            manager.notify(NOTIFICATION_ID, buildNotification(isPlaying))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Playback Controls",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Omniplay audio playback controls"
                setShowBadge(false)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private val noisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
                pause()
            }
        }
    }

    private fun registerBecomingNoisyReceiver() {
        val filter = IntentFilter(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        registerReceiver(noisyReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> play()
            ACTION_PAUSE -> pause()
            ACTION_TOGGLE -> togglePlayPause()
            ACTION_NEXT -> skipNext(forceNext = true)
            ACTION_PREVIOUS -> skipPrevious(forcePrevious = false)
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onDestroy() {
        val sync = xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext)
        sync.removeListener(omniSyncListener)
        sync.hostSongProvider = null
        sync.hostPlaybackPositionProvider = null
        sync.hostIsPlayingProvider = null

        teardownBitPerfectAudio()
        stopProgressTracker()
        try {
            unregisterReceiver(noisyReceiver)
        } catch (e: Exception) {}
        try {
            player?.release()
            player = null
        } catch (e: Exception) {}
        mediaSession?.release()
        super.onDestroy()
    }
}
