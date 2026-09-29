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
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.os.PowerManager
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.ui.MainActivity
import xyz.omniplay.util.AlbumArtLoader

class PlaybackService : Service(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener,
    AudioManager.OnAudioFocusChangeListener {

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
    private var mediaPlayer: MediaPlayer? = null
    private var mediaSession: MediaSessionCompat? = null
    private lateinit var audioManager: AudioManager
    private var audioFocusRequest: AudioFocusRequest? = null

    // Playback state
    var currentSong: Song? = null
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

    private var currentAlbumArt: Bitmap? = null
    private var progressJob: Job? = null
    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    private var resumeOnFocusGain = false

    private var currentAfd: AssetFileDescriptor? = null
    private var currentPfd: ParcelFileDescriptor? = null

    private fun releaseCurrentFd() {
        try {
            currentAfd?.close()
        } catch (ignored: Exception) {}
        currentAfd = null

        try {
            currentPfd?.close()
        } catch (ignored: Exception) {}
        currentPfd = null
    }

    private val listeners = mutableListOf<PlaybackListener>()

    interface PlaybackListener {
        fun onTrackChanged(song: Song?)
        fun onPlaybackStateChanged(isPlaying: Boolean)
        fun onProgressUpdate(currentPositionMs: Int, totalDurationMs: Int)
        fun onShuffleModeChanged(enabled: Boolean)
        fun onRepeatModeChanged(mode: Int)
    }

    inner class LocalBinder : Binder() {
        fun getService(): PlaybackService = this@PlaybackService
    }

    override fun onCreate() {
        super.onCreate()
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        createNotificationChannel()
        initMediaSession()
        initMediaPlayer()
        registerBecomingNoisyReceiver()
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setOnPreparedListener(this@PlaybackService)
            setOnCompletionListener(this@PlaybackService)
            setOnErrorListener(this@PlaybackService)
        }
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
                override fun onSkipToNext() { skipNext() }
                override fun onSkipToPrevious() { skipPrevious() }
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
    }

    fun removeListener(listener: PlaybackListener) {
        listeners.remove(listener)
    }

    fun setSongQueue(songs: List<Song>, startIndex: Int = 0, startPlaying: Boolean = true) {
        if (songs.isEmpty()) return
        originalQueue = songs.toList()
        queue = if (isShuffleEnabled) {
            val shuffled = songs.toMutableList()
            if (startIndex in songs.indices) {
                val selected = songs[startIndex]
                shuffled.removeAt(startIndex)
                shuffled.shuffle()
                shuffled.add(0, selected)
            } else {
                shuffled.shuffle()
            }
            shuffled
        } else {
            songs.toMutableList()
        }

        if (startPlaying) {
            currentIndex = if (isShuffleEnabled) 0 else startIndex.coerceIn(0, (queue.size - 1).coerceAtLeast(0))
            if (queue.isNotEmpty() && currentIndex in queue.indices) {
                val song = queue[currentIndex]
                currentSong = song
                playSong(song)
            }
        } else {
            if (currentSong == null) {
                currentIndex = -1
            } else {
                currentIndex = if (isShuffleEnabled) {
                    0
                } else {
                    queue.indexOfFirst { it.contentUri == currentSong?.contentUri }.coerceAtLeast(0)
                }
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
            currentIndex = -1
            currentSong = null
            currentAlbumArt = null
            try {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                }
                mediaPlayer?.reset()
                releaseCurrentFd()
            } catch (e: Exception) {}
            stopProgressTracker()
            updatePlaybackState(PlaybackStateCompat.STATE_NONE)
            try {
                stopForeground(true)
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.cancel(NOTIFICATION_ID)
            } catch (e: Exception) {}
            listeners.forEach { it.onTrackChanged(null) }
            listeners.forEach { it.onPlaybackStateChanged(false) }
            return
        }

        originalQueue = newSongs.toList()
        val current = currentSong

        if (current != null) {
            val matchingSong = newSongs.find {
                it.contentUri == current.contentUri ||
                it.id == current.id ||
                (it.filePath.isNotEmpty() && it.filePath == current.filePath)
            }

            if (matchingSong != null) {
                // Currently playing/selected song is still present; refresh its metadata
                currentSong = matchingSong
                if (isShuffleEnabled) {
                    val shuffled = newSongs.toMutableList()
                    shuffled.remove(matchingSong)
                    shuffled.shuffle()
                    shuffled.add(0, matchingSong)
                    queue = shuffled
                    currentIndex = 0
                } else {
                    queue = newSongs.toMutableList()
                    currentIndex = queue.indexOfFirst { it.contentUri == matchingSong.contentUri }.coerceAtLeast(0)
                }

                updateMediaMetadata(matchingSong)
                serviceScope.launch {
                    currentAlbumArt = AlbumArtLoader.loadAlbumArt(this@PlaybackService, matchingSong)
                    if (isPlaying()) {
                        updateNotification(isPlaying = true)
                    }
                }
                // Notify listeners so UI updates immediately with the refreshed metadata
                listeners.forEach { it.onTrackChanged(matchingSong) }
            } else {
                // Previously playing song was removed from folder
                pause()
                if (isShuffleEnabled) {
                    queue = newSongs.shuffled().toMutableList()
                } else {
                    queue = newSongs.toMutableList()
                }
                currentIndex = 0
                val nextSong = queue[0]
                prepareWithoutPlaying(nextSong)
            }
        } else {
            // No song currently playing or selected
            queue = if (isShuffleEnabled) newSongs.shuffled().toMutableList() else newSongs.toMutableList()
            currentIndex = -1
        }
    }

    private fun prepareWithoutPlaying(song: Song) {
        currentSong = song
        listeners.forEach { it.onTrackChanged(song) }
        serviceScope.launch {
            currentAlbumArt = AlbumArtLoader.loadAlbumArt(this@PlaybackService, song)
        }
        try {
            mediaPlayer?.reset()
            if (song.contentUri != Uri.EMPTY) {
                setMediaPlayerDataSource(song.contentUri)
                mediaPlayer?.prepareAsync()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playSong(song: Song) {
        currentSong = song
        listeners.forEach { it.onTrackChanged(song) }
        updateMediaMetadata(song)

        serviceScope.launch {
            currentAlbumArt = AlbumArtLoader.loadAlbumArt(this@PlaybackService, song)
            if (isPlaying()) {
                updateNotification(isPlaying = true)
            }
        }

        if (!requestAudioFocus()) {
            return
        }

        try {
            mediaPlayer?.reset()
            if (song.contentUri != Uri.EMPTY) {
                setMediaPlayerDataSource(song.contentUri)
                mediaPlayer?.prepareAsync()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setMediaPlayerDataSource(uri: Uri) {
        releaseCurrentFd()
        try {
            mediaPlayer?.setDataSource(applicationContext, uri)
        } catch (e: Exception) {
            var loaded = false
            try {
                val afd = applicationContext.contentResolver.openAssetFileDescriptor(uri, "r")
                if (afd != null) {
                    currentAfd = afd
                    if (afd.declaredLength < 0) {
                        mediaPlayer?.setDataSource(afd.fileDescriptor)
                    } else {
                        mediaPlayer?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.declaredLength)
                    }
                    loaded = true
                }
            } catch (ignored: Exception) {}

            if (!loaded) {
                try {
                    val pfd = applicationContext.contentResolver.openFileDescriptor(uri, "r")
                    if (pfd != null) {
                        currentPfd = pfd
                        mediaPlayer?.setDataSource(pfd.fileDescriptor)
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        updatePlaybackState(PlaybackStateCompat.STATE_PLAYING)
        startProgressTracker()
        startForeground(NOTIFICATION_ID, buildNotification(isPlaying = true))
        listeners.forEach { it.onPlaybackStateChanged(true) }
    }

    fun play() {
        if (currentSong == null) {
            if (queue.isNotEmpty()) {
                currentIndex = 0
                playSong(queue[0])
            }
            return
        }

        if (!requestAudioFocus()) return

        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
                updatePlaybackState(PlaybackStateCompat.STATE_PLAYING)
                startProgressTracker()
                startForeground(NOTIFICATION_ID, buildNotification(isPlaying = true))
                listeners.forEach { l -> l.onPlaybackStateChanged(true) }
            }
        }
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                stopProgressTracker()
                updatePlaybackState(PlaybackStateCompat.STATE_PAUSED)
                updateNotification(isPlaying = false)
                listeners.forEach { l -> l.onPlaybackStateChanged(false) }
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
        return mediaPlayer?.isPlaying == true
    }

    fun skipNext() {
        if (queue.isEmpty()) return
        if (repeatMode == REPEAT_ONE) {
            currentSong?.let { playSong(it) }
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

        playSong(queue[currentIndex])
    }

    fun skipPrevious() {
        if (queue.isEmpty()) return

        if (getCurrentPosition() > 3000) {
            seekTo(0)
            return
        }

        currentIndex--
        if (currentIndex < 0) {
            currentIndex = if (repeatMode == REPEAT_ALL) queue.size - 1 else 0
        }

        playSong(queue[currentIndex])
    }

    fun seekTo(positionMs: Int) {
        try {
            if (currentSong != null && currentSong?.contentUri != Uri.EMPTY) {
                mediaPlayer?.seekTo(positionMs)
            }
            listeners.forEach { it.onProgressUpdate(positionMs, getDuration()) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCurrentPosition(): Int {
        return try {
            if (mediaPlayer?.isPlaying == true || currentSong != null) {
                mediaPlayer?.currentPosition ?: 0
            } else 0
        } catch (e: Exception) {
            0
        }
    }

    fun getDuration(): Int {
        return try {
            if (currentSong != null) {
                mediaPlayer?.duration ?: (currentSong?.duration?.toInt() ?: 0)
            } else 0
        } catch (e: Exception) {
            0
        }
    }

    fun toggleShuffle() {
        isShuffleEnabled = !isShuffleEnabled
        if (isShuffleEnabled) {
            val current = currentSong
            val shuffled = originalQueue.toMutableList()
            if (current != null) {
                shuffled.remove(current)
                shuffled.shuffle()
                shuffled.add(0, current)
                currentIndex = 0
            } else {
                shuffled.shuffle()
                currentIndex = 0
            }
            queue = shuffled
        } else {
            val current = currentSong
            queue = originalQueue.toMutableList()
            currentIndex = if (current != null) queue.indexOf(current).coerceAtLeast(0) else 0
        }
        listeners.forEach { it.onShuffleModeChanged(isShuffleEnabled) }
    }

    fun cycleRepeatMode() {
        repeatMode = when (repeatMode) {
            REPEAT_OFF -> REPEAT_ALL
            REPEAT_ALL -> REPEAT_ONE
            else -> REPEAT_OFF
        }
        listeners.forEach { it.onRepeatModeChanged(repeatMode) }
    }

    override fun onCompletion(mp: MediaPlayer?) {
        skipNext()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return true
    }

    private fun startProgressTracker() {
        progressJob?.cancel()
        progressJob = serviceScope.launch {
            while (isActive) {
                val current = getCurrentPosition()
                val total = getDuration()
                listeners.forEach { it.onProgressUpdate(current, total) }
                delay(250)
            }
        }
    }

    private fun stopProgressTracker() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun requestAudioFocus(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val request = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(this)
                .build()
            audioFocusRequest = request
            audioManager.requestAudioFocus(request) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        } else {
            @Suppress("DEPRECATION")
            audioManager.requestAudioFocus(
                this,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            ) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        }
    }

    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS -> {
                pause()
                resumeOnFocusGain = false
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                if (isPlaying()) {
                    pause()
                    resumeOnFocusGain = true
                }
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                mediaPlayer?.setVolume(0.2f, 0.2f)
            }
            AudioManager.AUDIOFOCUS_GAIN -> {
                mediaPlayer?.setVolume(1.0f, 1.0f)
                if (resumeOnFocusGain) {
                    play()
                    resumeOnFocusGain = false
                }
            }
        }
    }

    private fun updateMediaMetadata(song: Song) {
        val metadata = MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, song.title)
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, song.artist)
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, song.album)
            .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, song.duration)
            .build()
        mediaSession?.setMetadata(metadata)
    }

    private fun updatePlaybackState(state: Int) {
        val playbackState = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or
                        PlaybackStateCompat.ACTION_SEEK_TO or
                        PlaybackStateCompat.ACTION_PLAY_PAUSE
            )
            .setState(state, getCurrentPosition().toLong(), 1.0f)
            .build()
        mediaSession?.setPlaybackState(playbackState)
    }

    private fun buildNotification(isPlaying: Boolean): Notification {
        val song = currentSong ?: return NotificationCompat.Builder(this, CHANNEL_ID).build()

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
        manager.notify(NOTIFICATION_ID, buildNotification(isPlaying))
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
            if (intent?.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
                pause()
            }
        }
    }

    private fun registerBecomingNoisyReceiver() {
        val filter = IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        registerReceiver(noisyReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> play()
            ACTION_PAUSE -> pause()
            ACTION_TOGGLE -> togglePlayPause()
            ACTION_NEXT -> skipNext()
            ACTION_PREVIOUS -> skipPrevious()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onDestroy() {
        stopProgressTracker()
        try {
            unregisterReceiver(noisyReceiver)
        } catch (e: Exception) {}
        releaseCurrentFd()
        mediaPlayer?.release()
        mediaPlayer = null
        mediaSession?.release()
        super.onDestroy()
    }
}
