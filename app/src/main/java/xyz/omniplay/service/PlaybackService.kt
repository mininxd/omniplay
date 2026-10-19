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
import android.media.audiofx.AudioEffect
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
import androidx.media.AudioAttributesCompat
import androidx.media.AudioFocusRequestCompat
import androidx.media.AudioManagerCompat
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
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.ui.AudioDetailsHelper
import xyz.omniplay.ui.MainActivity
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.ExtractorsFactory
import xyz.omniplay.dsd.DsdExtractor
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.AudioInfoExtractor
import xyz.omniplay.util.AudioTrackInfo
import android.os.Bundle
import android.media.audiofx.LoudnessEnhancer
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import androidx.media.MediaBrowserServiceCompat
import xyz.omniplay.util.ReplayGainHelper
import java.util.Locale
import java.util.concurrent.CopyOnWriteArrayList

@OptIn(UnstableApi::class)
class PlaybackService : MediaBrowserServiceCompat() {

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

        const val MEDIA_ROOT_ID = "omniplay_root"
        const val MEDIA_TRACKS_ID = "omniplay_tracks"
        const val MEDIA_QUEUE_ID = "omniplay_queue"
    }

    private val binder = LocalBinder()
    private var loudnessEnhancer: LoudnessEnhancer? = null
    private var player: ExoPlayer? = null
    private var fadeOutPlayer: ExoPlayer? = null
    private var mediaSession: MediaSessionCompat? = null
    private lateinit var audioManager: android.media.AudioManager
    private var audioFocusRequest: AudioFocusRequestCompat? = null
    private var isPausedByTransientLossOfFocus = false

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot {
        return BrowserRoot(MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        val mediaItems = mutableListOf<MediaBrowserCompat.MediaItem>()
        if (parentId == MEDIA_ROOT_ID) {
            val tracksDesc = MediaDescriptionCompat.Builder()
                .setMediaId(MEDIA_TRACKS_ID)
                .setTitle(getString(R.string.all_tracks))
                .setSubtitle(getString(R.string.music_library))
                .build()
            mediaItems.add(MediaBrowserCompat.MediaItem(tracksDesc, MediaBrowserCompat.MediaItem.FLAG_BROWSABLE))

            val queueDesc = MediaDescriptionCompat.Builder()
                .setMediaId(MEDIA_QUEUE_ID)
                .setTitle(getString(R.string.queue_title))
                .setSubtitle("${queue.size} tracks")
                .build()
            mediaItems.add(MediaBrowserCompat.MediaItem(queueDesc, MediaBrowserCompat.MediaItem.FLAG_BROWSABLE))
            result.sendResult(mediaItems)
        } else if (parentId == MEDIA_TRACKS_ID) {
            result.detach()
            serviceScope.launch(Dispatchers.IO) {
                val songs = xyz.omniplay.data.MusicDatabase.getInstance(applicationContext).getAllSongs()
                val items = songs.map { song ->
                    val desc = MediaDescriptionCompat.Builder()
                        .setMediaId(song.id.toString())
                        .setTitle(song.title)
                        .setSubtitle(song.artist)
                        .setDescription(song.album)
                        .setIconUri(song.albumArtUri)
                        .setMediaUri(song.contentUri)
                        .build()
                    MediaBrowserCompat.MediaItem(desc, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
                }.toMutableList()
                result.sendResult(items)
            }
        } else if (parentId == MEDIA_QUEUE_ID) {
            val items = queue.map { song ->
                val desc = MediaDescriptionCompat.Builder()
                    .setMediaId(song.id.toString())
                    .setTitle(song.title)
                    .setSubtitle(song.artist)
                    .setDescription(song.album)
                    .setIconUri(song.albumArtUri)
                    .setMediaUri(song.contentUri)
                    .build()
                MediaBrowserCompat.MediaItem(desc, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
            }.toMutableList()
            result.sendResult(items)
        } else {
            result.sendResult(mediaItems)
        }
    }

    private var lastBroadcastSessionId: Int = -1

    private fun broadcastAudioSessionOpened(audioSessionId: Int) {
        if (audioSessionId <= 0 || audioSessionId == C.AUDIO_SESSION_ID_UNSET) return
        if (lastBroadcastSessionId == audioSessionId) return
        broadcastAudioSessionClosed()
        lastBroadcastSessionId = audioSessionId
        try {
            val intent = Intent(AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION).apply {
                putExtra(AudioEffect.EXTRA_AUDIO_SESSION, audioSessionId)
                putExtra(AudioEffect.EXTRA_PACKAGE_NAME, packageName)
                putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC)
            }
            sendBroadcast(intent)
        } catch (_: Throwable) {}
    }

    private fun broadcastAudioSessionClosed() {
        val sid = lastBroadcastSessionId
        if (sid > 0) {
            lastBroadcastSessionId = -1
            try {
                val intent = Intent(AudioEffect.ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION).apply {
                    putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sid)
                    putExtra(AudioEffect.EXTRA_PACKAGE_NAME, packageName)
                }
                sendBroadcast(intent)
            } catch (_: Throwable) {}
        }
    }

    private fun setupLoudnessEnhancer(audioSessionId: Int) {
        try {
            loudnessEnhancer?.release()
            loudnessEnhancer = null
            if (audioSessionId != C.AUDIO_SESSION_ID_UNSET && audioSessionId > 0) {
                loudnessEnhancer = LoudnessEnhancer(audioSessionId)
                broadcastAudioSessionOpened(audioSessionId)
            }
        } catch (ignored: Throwable) {}
        applyVolumeNormalization(currentSong)
    }

    private fun applyVolumeNormalization(song: Song?) {
        if (crossfadeJob?.isActive == true) return
        val p = player ?: return
        val prefs = getSharedPreferences(xyz.omniplay.ui.MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val isNormalizationEnabled = prefs.getBoolean(xyz.omniplay.ui.MainActivity.KEY_VOLUME_NORMALIZATION, false)

        if (!isNormalizationEnabled) {
            try {
                loudnessEnhancer?.enabled = false
            } catch (ignored: Throwable) {}
            p.volume = 1.0f
            return
        }

        if (song == null) {
            p.volume = 1.0f
            return
        }

        serviceScope.launch(Dispatchers.IO) {
            val gainDb = ReplayGainHelper.extractReplayGainDb(applicationContext, song)
            withContext(Dispatchers.Main) {
                if (crossfadeJob?.isActive == true) return@withContext
                if (gainDb != null) {
                    val linearGain = ReplayGainHelper.dbToLinear(gainDb)
                    player?.volume = linearGain
                    try {
                        loudnessEnhancer?.enabled = false
                    } catch (ignored: Throwable) {}
                } else {
                    player?.volume = 1.0f
                    try {
                        loudnessEnhancer?.let { enhancer ->
                            enhancer.setTargetGain(250)
                            enhancer.enabled = true
                        }
                    } catch (ignored: Throwable) {}
                }
            }
        }
    }

    private val audioFocusChangeListener = android.media.AudioManager.OnAudioFocusChangeListener { focusChange ->
        when (focusChange) {
            android.media.AudioManager.AUDIOFOCUS_GAIN -> {
                if (isPausedByTransientLossOfFocus) {
                    isPausedByTransientLossOfFocus = false
                    play()
                }
                if (crossfadeJob?.isActive != true) {
                    player?.volume = 1f
                }
            }
            android.media.AudioManager.AUDIOFOCUS_LOSS -> {
                isPausedByTransientLossOfFocus = false
                pause()
            }
            android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                if (isPlaying()) {
                    isPausedByTransientLossOfFocus = true
                    pause()
                }
            }
            android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                if (isPlaying() && crossfadeJob?.isActive != true) {
                    player?.volume = 0.2f
                }
            }
        }
    }

    private fun requestAudioFocus(): Boolean {
        if (audioFocusRequest != null) return true
        return try {
            val audioAttributes = AudioAttributesCompat.Builder()
                .setUsage(AudioAttributesCompat.USAGE_MEDIA)
                .setContentType(AudioAttributesCompat.CONTENT_TYPE_MUSIC)
                .build()

            val request = AudioFocusRequestCompat.Builder(AudioManagerCompat.AUDIOFOCUS_GAIN)
                .setAudioAttributes(audioAttributes)
                .setOnAudioFocusChangeListener(audioFocusChangeListener)
                .build()

            val result = AudioManagerCompat.requestAudioFocus(audioManager, request)
            if (result == android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                audioFocusRequest = request
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            true
        }
    }

    private fun abandonAudioFocus() {
        try {
            audioFocusRequest?.let {
                AudioManagerCompat.abandonAudioFocusRequest(audioManager, it)
                audioFocusRequest = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun cancelAndResetCrossfade() {
        crossfadeJob?.cancel()
        crossfadeJob = null
        try {
            fadeOutPlayer?.stop()
            fadeOutPlayer?.clearMediaItems()
            fadeOutPlayer?.volume = 1f
        } catch (e: Exception) {
            e.printStackTrace()
        }
        player?.volume = 1f
    }

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
    private var crossfadeJob: Job? = null
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
        sync.hostSongProvider = { currentSong?.let { getResolvedSongForSync(it) } }
        sync.hostPlaybackPositionProvider = {
            if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) {
                getCurrentPosition().toLong()
            } else {
                sync.currentLivePositionMs
            }
        }
        sync.hostIsPlayingProvider = {
            if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) {
                isPlaying()
            } else {
                sync.isHostLivePlaying
            }
        }
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
                false // Audio focus managed by PlaybackService for dual-player crossfade
            )
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()

        fadeOutPlayer = ExoPlayer.Builder(applicationContext, renderersFactory)
            .setMediaSourceFactory(mediaSourceFactory)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                false // Audio focus managed by PlaybackService
            )
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()

        fun createListener(exoInstance: ExoPlayer) = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (exoInstance != player) return
                when (playbackState) {
                    Player.STATE_READY -> {
                        val dur = exoInstance.duration.takeIf { it > 0L } ?: currentSong?.duration ?: 0L
                        currentSong?.let {
                            if (it.duration <= 0L && dur > 0L) {
                                currentSong = it.copy(duration = dur)
                                listeners.forEach { l -> l.onTrackChanged(currentSong) }
                            }
                            updateMediaMetadata(it, dur, currentAlbumArt)
                        }
                        val isPlaying = exoInstance.isPlaying
                        val state = if (isPlaying) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED
                        updatePlaybackState(state, exoInstance.currentPosition.coerceAtLeast(0L))

                        if (exoInstance == player) {
                            val sid = exoInstance.audioSessionId
                            if (sid > 0 && sid != C.AUDIO_SESSION_ID_UNSET) {
                                xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).rebindAudioSession(sid)
                            }
                        }
                    }
                    Player.STATE_ENDED -> {
                        if (crossfadeJob?.isActive != true) {
                            skipNext(forceNext = false)
                        }
                    }
                    Player.STATE_IDLE, Player.STATE_BUFFERING -> {}
                }
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                if (exoInstance == player) {
                    val sid = exoInstance.audioSessionId
                    if (sid > 0 && sid != C.AUDIO_SESSION_ID_UNSET) {
                        xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).rebindAudioSession(sid)
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (exoInstance != player) return
                val currentPos = exoInstance.currentPosition.coerceAtLeast(0L)
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).updateHostLivePlayback(currentSong, currentPos, isPlaying)
                if (isPlaying && currentSong != null) {
                    updatePlaybackState(PlaybackStateCompat.STATE_PLAYING, currentPos)
                    startProgressTracker()
                    try {
                        startForeground(NOTIFICATION_ID, buildNotification(isPlaying = true))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    currentSong?.let {
                        xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(getResolvedSongForSync(it), currentPos, isPlaying = true)
                    }
                } else {
                    stopProgressTracker()
                    updatePlaybackState(PlaybackStateCompat.STATE_PAUSED, currentPos)
                    updateNotification(isPlaying = false)
                    xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPause()
                }
                listeners.forEach { it.onPlaybackStateChanged(isPlaying) }
            }

            override fun onTracksChanged(tracks: androidx.media3.common.Tracks) {
                if (exoInstance != player) return
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
                if (exoInstance != player) return
                // If a playback error occurs, advance safely without hanging
                skipNext(forceNext = false)
            }

            override fun onAudioSessionIdChanged(audioSessionId: Int) {
                if (exoInstance == player && audioSessionId > 0 && audioSessionId != C.AUDIO_SESSION_ID_UNSET) {
                    setupLoudnessEnhancer(audioSessionId)
                    xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).rebindAudioSession(audioSessionId, forceRecreate = true)
                }
            }
        }

        exo.addListener(createListener(exo))
        fadeOutPlayer?.addListener(createListener(fadeOutPlayer!!))

        player = exo
        if (exo.audioSessionId != C.AUDIO_SESSION_ID_UNSET && exo.audioSessionId > 0) {
            setupLoudnessEnhancer(exo.audioSessionId)
            xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).initAudioSession(exo.audioSessionId)
        }
    }

    var isBitPerfectActive: Boolean = false
        private set

    fun getAudioOutputDeviceInfo(): String {
        return AudioDetailsHelper.getAudioOutputDeviceInfo(applicationContext)
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

                override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {
                    if (mediaId == null) return
                    val songId = mediaId.toLongOrNull() ?: return
                    serviceScope.launch(Dispatchers.IO) {
                        val dbSongs = xyz.omniplay.data.MusicDatabase.getInstance(applicationContext).getAllSongs()
                        val targetSong = dbSongs.find { it.id == songId } ?: queue.find { it.id == songId }
                        if (targetSong != null) {
                            withContext(Dispatchers.Main) {
                                val targetIndex = dbSongs.indexOfFirst { it.id == songId }
                                if (targetIndex >= 0) {
                                    setSongQueue(dbSongs, targetIndex, startPlaying = true)
                                } else {
                                    playSong(targetSong, startPlaying = true)
                                }
                            }
                        }
                    }
                }

                override fun onPlayFromSearch(query: String?, extras: Bundle?) {
                    if (query.isNullOrBlank()) {
                        play()
                        return
                    }
                    serviceScope.launch(Dispatchers.IO) {
                        val dbSongs = xyz.omniplay.data.MusicDatabase.getInstance(applicationContext).getAllSongs()
                        val match = dbSongs.find {
                            it.title.contains(query, ignoreCase = true) ||
                            it.artist.contains(query, ignoreCase = true) ||
                            it.album.contains(query, ignoreCase = true)
                        }
                        if (match != null) {
                            withContext(Dispatchers.Main) {
                                val idx = dbSongs.indexOfFirst { it.id == match.id }
                                setSongQueue(dbSongs, idx, startPlaying = true)
                            }
                        }
                    }
                }

                override fun onPrepare() {
                    if (currentSong != null) {
                        player?.prepare()
                    }
                }
            })
            isActive = true
        }
        sessionToken = mediaSession?.sessionToken
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
        val prefs = getSharedPreferences(xyz.omniplay.ui.MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val crossfadeOnNext = prefs.getBoolean(xyz.omniplay.ui.MainActivity.KEY_CROSSFADE_ON_NEXT, false)
        if (isShuffleEnabled) {
            val idx = queue.indexOfFirst { it.id == song.id }
            if (idx != -1) {
                val selected = queue.removeAt(idx)
                queue.add(0, selected)
                currentIndex = 0
                playSong(selected, startPlaying = startPlaying, allowCrossfade = crossfadeOnNext)
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            } else {
                val matching = originalQueue.find { it.id == song.id } ?: song
                val remaining = originalQueue.filter { it.id != matching.id }.shuffled()
                queue = (listOf(matching) + remaining).toMutableList()
                currentIndex = 0
                playSong(matching, startPlaying = startPlaying, allowCrossfade = crossfadeOnNext)
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            }
        } else {
            val targetIndex = if (index in queue.indices && queue[index].id == song.id) {
                index
            } else {
                val idx = queue.indexOfFirst { it.id == song.id }
                if (idx != -1) idx else index.coerceIn(0, (queue.size - 1).coerceAtLeast(0))
            }
            if (queue.isNotEmpty() && targetIndex in queue.indices) {
                currentIndex = targetIndex
                playSong(queue[currentIndex], startPlaying = startPlaying, allowCrossfade = crossfadeOnNext)
            }
        }
    }

    fun addToQueue(song: Song) {
        if (queue.isEmpty()) {
            setSongQueue(listOf(song), startIndex = 0, startPlaying = false)
            return
        }
        val mutable = queue.toMutableList()
        mutable.add(song)
        queue = mutable
        if (!isShuffleEnabled) {
            originalQueue = queue.toList()
        } else if (!originalQueue.any { it.id == song.id }) {
            originalQueue = originalQueue + song
        }
        listeners.forEach { it.onQueueChanged(queue.toList()) }
    }

    fun playNext(song: Song) {
        if (queue.isEmpty()) {
            setSongQueue(listOf(song), startIndex = 0, startPlaying = false)
            return
        }
        val mutable = queue.toMutableList()
        val existingIdx = mutable.indexOfFirst { it.id == song.id }
        if (existingIdx != -1 && existingIdx != currentIndex) {
            mutable.removeAt(existingIdx)
            if (existingIdx < currentIndex) {
                currentIndex--
            }
        }
        val insertIndex = (currentIndex + 1).coerceIn(0, mutable.size)
        mutable.add(insertIndex, song)
        queue = mutable
        if (!isShuffleEnabled) {
            originalQueue = queue.toList()
        } else if (!originalQueue.any { it.id == song.id }) {
            originalQueue = originalQueue + song
        }
        listeners.forEach { it.onQueueChanged(queue.toList()) }
    }

    fun removeSongAt(position: Int) {
        if (position !in queue.indices) return
        val removedSong = queue[position]
        val isRemovingCurrent = (position == currentIndex)
        val wasPlaying = isPlaying()

        queue.removeAt(position)
        if (!isShuffleEnabled) {
            originalQueue = queue.toList()
        } else {
            originalQueue = originalQueue.filter { it.id != removedSong.id }
        }

        if (queue.isEmpty()) {
            isExternalSongActive = false
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
                } catch (e: Exception) {}
            }
            return
        }

        if (isRemovingCurrent) {
            val nextIndex = position.coerceAtMost(queue.size - 1)
            currentIndex = nextIndex
            val nextSong = queue[nextIndex]
            playSong(nextSong, startPlaying = wasPlaying)
        } else if (position < currentIndex) {
            currentIndex--
        }

        listeners.forEach { it.onQueueChanged(queue.toList()) }
    }

    fun removeSong(song: Song) {
        val idx = queue.indexOfFirst { it.id == song.id }
        if (idx != -1) {
            removeSongAt(idx)
        }
    }

    /**
     * Explicitly updates the active queue order without reshuffling,
     * maintaining the user's manual reordering or sorting in both standard and shuffle modes.
     */
    fun reorderQueue(newOrder: List<Song>) {
        if (newOrder.isEmpty()) return
        queue = newOrder.toMutableList()
        val currentId = currentSong?.id
        currentIndex = if (currentId != null) {
            queue.indexOfFirst { it.id == currentId }.coerceAtLeast(0)
        } else {
            0
        }
        if (!isShuffleEnabled) {
            originalQueue = newOrder.toList()
        }
        listeners.forEach { it.onQueueChanged(queue.toList()) }
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

        val currentId = currentSong?.id

        if (isExternalSongActive) {
            originalQueue = newSongs.toList()
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
                    // Update metadata for existing queue songs while strictly preserving their active order
                    val songMap = newSongs.associateBy { it.id }
                    val preserved = queue.mapNotNull { songMap[it.id] }.toMutableList()
                    val added = newSongs.filterNot { s -> preserved.any { it.id == s.id } }
                    queue = (preserved + added).toMutableList()
                    currentIndex = queue.indexOfFirst { it.id == currentId }.coerceAtLeast(0)
                    originalQueue = originalQueue.filter { s -> newSongs.any { it.id == s.id } } +
                            newSongs.filter { s -> !originalQueue.any { it.id == s.id } }
                } else {
                    val songMap = newSongs.associateBy { it.id }
                    if (queue.isNotEmpty()) {
                        val preserved = queue.mapNotNull { songMap[it.id] ?: it }.toMutableList()
                        queue = preserved
                        currentIndex = queue.indexOfFirst { it.id == currentId }.coerceAtLeast(0)
                        originalQueue = queue.toList()
                    } else {
                        originalQueue = newSongs.toList()
                        queue = newSongs.toMutableList()
                        currentIndex = queue.indexOfFirst { it.id == currentId }.coerceAtLeast(0)
                    }
                }
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            } else {
                // Currently playing song was deleted/removed from folder
                originalQueue = newSongs.toList()
                queue = newSongs.toMutableList()
                currentIndex = 0
                listeners.forEach { it.onQueueChanged(queue.toList()) }
                val nextSong = queue.firstOrNull()
                if (nextSong != null) {
                    prepareWithoutPlaying(nextSong)
                }
            }
        } else {
            // No song currently playing or selected
            originalQueue = newSongs.toList()
            queue = newSongs.toMutableList()
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
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(song, 0L, isPlaying = false)
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

    fun playSong(song: Song, startPlaying: Boolean = true, allowCrossfade: Boolean = true) {
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
            val prefs = getSharedPreferences(xyz.omniplay.ui.MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
            val crossfadeDurationSec = prefs.getInt(xyz.omniplay.ui.MainActivity.KEY_CROSSFADE_DURATION, 0)
            val crossfadeDurationMs = crossfadeDurationSec * 1000L

            val p = player ?: return

            if (allowCrossfade && crossfadeDurationSec > 0 && p.isPlaying && startPlaying && song.contentUri != Uri.EMPTY) {
                // Ensure audio focus is active
                requestAudioFocus()

                // Cancel any previous crossfade and clean up previous fadeOutPlayer
                cancelAndResetCrossfade()

                // Perform crossfade
                val oldPlayer = p
                val newPlayer = fadeOutPlayer ?: return

                // Swap players
                player = newPlayer
                fadeOutPlayer = oldPlayer

                // Setup new player
                progressJob?.cancel()
                newPlayer.stop()
                newPlayer.clearMediaItems()
                val mediaItem = MediaItem.fromUri(song.contentUri)
                newPlayer.setMediaItem(mediaItem)
                newPlayer.prepare()
                newPlayer.volume = 0f
                val newSid = newPlayer.audioSessionId
                if (newSid > 0 && newSid != C.AUDIO_SESSION_ID_UNSET) {
                    setupLoudnessEnhancer(newSid)
                    xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).rebindAudioSession(newSid, forceRecreate = true)
                }
                newPlayer.play()
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(getResolvedSongForSync(song), 0L, isPlaying = true)

                // Start crossfade animation
                crossfadeJob = serviceScope.launch {
                    // Wait for newPlayer to become ready or start playing
                    var waitMs = 0L
                    while (isActive && newPlayer.playbackState != Player.STATE_READY && waitMs < 1000L) {
                        delay(20L)
                        waitMs += 20L
                    }

                    val stepIntervalMs = 25L
                    val totalSteps = (crossfadeDurationMs / stepIntervalMs).toInt().coerceAtLeast(1)

                    for (step in 1..totalSteps) {
                        if (!isActive) break
                        val t = step.toFloat() / totalSteps
                        // Equal-power crossfade curve: cos^2(angle) + sin^2(angle) == 1
                        val angle = (t * (Math.PI / 2.0)).toFloat()
                        val fadeInVol = kotlin.math.sin(angle)
                        val fadeOutVol = kotlin.math.cos(angle)

                        player?.volume = fadeInVol
                        fadeOutPlayer?.volume = fadeOutVol
                        delay(stepIntervalMs)
                    }

                    if (isActive) {
                        player?.volume = 1f
                        fadeOutPlayer?.stop()
                        fadeOutPlayer?.clearMediaItems()
                        fadeOutPlayer?.volume = 1f
                        crossfadeJob = null
                        applyVolumeNormalization(song)
                    }
                }
            } else {
                progressJob?.cancel()
                cancelAndResetCrossfade()
                p.stop()
                p.volume = 1f

                if (song.contentUri != Uri.EMPTY) {
                    val mediaItem = MediaItem.fromUri(song.contentUri)
                    p.setMediaItem(mediaItem)
                    p.prepare()
                    val sid = p.audioSessionId
                    if (sid > 0 && sid != C.AUDIO_SESSION_ID_UNSET) {
                        setupLoudnessEnhancer(sid)
                        xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).rebindAudioSession(sid)
                    }
                    if (startPlaying) {
                        if (requestAudioFocus()) {
                            p.play()
                            applyVolumeNormalization(song)
                            xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(getResolvedSongForSync(song), 0L, isPlaying = true)
                        }
                    } else {
                        stopProgressTracker()
                        updatePlaybackState(PlaybackStateCompat.STATE_PAUSED, 0L)
                        updateNotification(isPlaying = false)
                        listeners.forEach { it.onPlaybackStateChanged(false) }
                        xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(getResolvedSongForSync(song), 0L, isPlaying = false)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getResolvedSongForSync(song: Song): Song {
        val f = currentAudioInfo?.format?.takeIf { it.isNotEmpty() && it != "AUDIO" } ?: song.format
        val q = currentAudioInfo?.formatQualityString()?.takeIf { it.isNotEmpty() } ?: song.audioQuality
        val h = currentAudioInfo?.checkHiRes() ?: song.isHiRes
        return song.copy(format = f, audioQuality = q, isHiRes = h)
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
        info?.let {
            if (it.format.isNotEmpty() && it.format != "AUDIO") {
                val q = it.formatQualityString()
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).updateHostAudioInfo(it.format, q, it.checkHiRes())
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

        if (requestAudioFocus()) {
            player?.let {
                val sid = it.audioSessionId
                if (sid > 0 && sid != C.AUDIO_SESSION_ID_UNSET) {
                    xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).rebindAudioSession(sid)
                }
                if (crossfadeJob?.isActive != true) {
                    it.volume = 1f
                }
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
                        xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPlay(getResolvedSongForSync(s), getCurrentPosition().toLong())
                    }
                }
            }
        }
    }

    fun pause() {
        cancelAndResetCrossfade()
        abandonAudioFocus()
        player?.let {
            if (it.isPlaying) {
                it.pause()
                val pos = getCurrentPosition().toLong()
                stopProgressTracker()
                updatePlaybackState(PlaybackStateCompat.STATE_PAUSED, pos)
                updateNotification(isPlaying = false)
                listeners.forEach { l -> l.onPlaybackStateChanged(false) }
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).updateHostLivePlayback(currentSong, pos, isPlaying = false)
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastPause(pos)
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
        val prefs = getSharedPreferences(xyz.omniplay.ui.MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val crossfadeOnNext = prefs.getBoolean(xyz.omniplay.ui.MainActivity.KEY_CROSSFADE_ON_NEXT, false)
        val allowCrossfade = !forceNext || crossfadeOnNext

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
                playSong(queue[targetIndex], startPlaying = wasPlaying, allowCrossfade = allowCrossfade)
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
            currentSong?.let { playSong(it, startPlaying = wasPlaying, allowCrossfade = allowCrossfade) }
            return
        }

        if (isShuffleEnabled) {
            if (queue.size > 1) {
                val currentIdx = queue.indexOfFirst { it.id == currentSong?.id }.takeIf { it != -1 } ?: 0
                val finished = queue.removeAt(currentIdx)
                queue.add(finished)
                currentIndex = 0
                val nextSong = queue[0]
                playSong(nextSong, startPlaying = wasPlaying, allowCrossfade = allowCrossfade)
                listeners.forEach { it.onQueueChanged(queue.toList()) }
            } else {
                currentSong?.let { playSong(it, startPlaying = wasPlaying, allowCrossfade = allowCrossfade) }
            }
            return
        }

        if (queue.size <= 1) {
            if (repeatMode == REPEAT_ALL || forceNext) {
                currentSong?.let { playSong(it, startPlaying = wasPlaying, allowCrossfade = allowCrossfade) }
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

        playSong(queue[currentIndex], startPlaying = wasPlaying, allowCrossfade = allowCrossfade)
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
                playSong(queue[targetIndex], startPlaying = wasPlaying, allowCrossfade = false)
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
                playSong(prev, startPlaying = wasPlaying, allowCrossfade = false)
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

        playSong(queue[currentIndex], startPlaying = wasPlaying, allowCrossfade = false)
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
            cancelAndResetCrossfade()
            if (currentSong != null && currentSong?.contentUri != Uri.EMPTY) {
                player?.seekTo(positionMs.toLong())
            }
            val state = if (isPlaying()) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED
            updatePlaybackState(state, positionMs.toLong())
            listeners.forEach { it.onProgressUpdate(positionMs, getDuration()) }
            xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).updateHostLivePlayback(currentSong, positionMs.toLong(), isPlaying = isPlaying())
            xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).broadcastSeek(positionMs.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCurrentPosition(): Int {
        return try {
            if (player?.isPlaying == true || currentSong != null) {
                val pos = player?.currentPosition?.takeIf { it >= 0L && it != C.TIME_UNSET }?.toInt() ?: 0
                pos.coerceAtLeast(0)
            } else 0
        } catch (e: Exception) {
            0
        }
    }

    fun getDuration(): Int {
        return try {
            if (currentSong != null) {
                val dur = player?.duration?.takeIf { it > 0L && it != C.TIME_UNSET }?.toInt()
                dur ?: (currentSong?.duration?.toInt()?.takeIf { it > 0 } ?: 0)
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
            var crossfadeTriggered = false
            while (isActive) {
                val current = getCurrentPosition()
                val total = getDuration()
                listeners.forEach { it.onProgressUpdate(current, total) }
                xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).updateHostLivePlayback(currentSong, current.toLong(), isPlaying = true)

                val now = SystemClock.elapsedRealtime()
                if (now - lastStateSync >= 1000L) {
                    lastStateSync = now
                    updatePlaybackState(PlaybackStateCompat.STATE_PLAYING, current.toLong())
                }

                val prefs = getSharedPreferences(xyz.omniplay.ui.MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
                val crossfadeDurationSec = prefs.getInt(xyz.omniplay.ui.MainActivity.KEY_CROSSFADE_DURATION, 0)
                if (crossfadeDurationSec > 0 && total > 0 && !crossfadeTriggered && crossfadeJob?.isActive != true) {
                    val hasNext = repeatMode == REPEAT_ONE || getNextSong() != null || isShuffleEnabled || (isExternalSongActive && queue.isNotEmpty())
                    if (hasNext) {
                        val crossfadeDurationMs = crossfadeDurationSec * 1000L
                        val effectiveCrossfadeMs = crossfadeDurationMs.coerceAtMost((total / 2L).coerceAtLeast(1000L))
                        if (current > 0 && total - current <= effectiveCrossfadeMs) {
                            crossfadeTriggered = true
                            skipNext(forceNext = false)
                        }
                    }
                }

                delay(80)
            }
        }
    }

    private fun stopProgressTracker() {
        progressJob?.cancel()
        progressJob = null
        xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext).updateHostLivePlayback(
            currentSong,
            getCurrentPosition().toLong(),
            isPlaying = false
        )
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

    override fun onBind(intent: Intent?): IBinder? {
        if (intent?.action == "android.media.browse.MediaBrowserService") {
            return super.onBind(intent)
        }
        return binder
    }

    override fun onDestroy() {
        try {
            loudnessEnhancer?.release()
            loudnessEnhancer = null
        } catch (_: Throwable) {}

        broadcastAudioSessionClosed()

        try {
            xyz.omniplay.audio.EqualizerManager.getInstance(applicationContext).releaseEffects()
        } catch (_: Throwable) {}

        val sync = xyz.omniplay.sync.OmniSyncManager.getInstance(applicationContext)
        sync.removeListener(omniSyncListener)
        sync.hostSongProvider = null
        sync.hostPlaybackPositionProvider = null
        sync.hostIsPlayingProvider = null

        cancelAndResetCrossfade()
        abandonAudioFocus()
        teardownBitPerfectAudio()
        stopProgressTracker()
        try {
            unregisterReceiver(noisyReceiver)
        } catch (e: Exception) {}
        try {
            player?.release()
            player = null
        } catch (e: Exception) {}
        try {
            fadeOutPlayer?.release()
            fadeOutPlayer = null
        } catch (e: Exception) {}
        mediaSession?.release()
        super.onDestroy()
    }
}
