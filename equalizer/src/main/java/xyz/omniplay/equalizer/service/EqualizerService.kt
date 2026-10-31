package xyz.omniplay.equalizer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo
import android.media.AudioAttributes
import android.media.AudioDeviceCallback
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.media.AudioPlaybackConfiguration
import android.media.audiofx.AudioEffect
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import xyz.omniplay.equalizer.R
import xyz.omniplay.equalizer.audio.ActiveSessionScanner
import xyz.omniplay.equalizer.audio.EqualizerManager
import xyz.omniplay.equalizer.receiver.AudioSessionReceiver
import xyz.omniplay.equalizer.ui.MainActivity

class EqualizerService : Service(), EqualizerManager.EqualizerListener {

    companion object {
        private const val TAG = "EqualizerService"
        private const val CHANNEL_ID = "equalizer_channel"
        private const val NOTIFICATION_ID = 2001
        private const val SESSION_KEEP_ALIVE_MS = 15_000L

        const val ACTION_START = "xyz.omniplay.equalizer.action.START"
        const val ACTION_STOP = "xyz.omniplay.equalizer.action.STOP"
        const val ACTION_TOGGLE = "xyz.omniplay.equalizer.action.TOGGLE"

        fun start(context: Context) {
            val intent = Intent(context, EqualizerService::class.java).apply {
                action = ACTION_START
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to start EqualizerService", e)
            }
        }

        fun stop(context: Context) {
            val intent = Intent(context, EqualizerService::class.java).apply {
                action = ACTION_STOP
            }
            try {
                context.startService(intent)
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to stop EqualizerService", e)
            }
        }
    }

    private lateinit var eqManager: EqualizerManager
    private val audioSessionReceiver = AudioSessionReceiver()
    private var isReceiverRegistered = false
    private var audioDeviceCallback: Any? = null
    private var audioPlaybackCallback: Any? = null
    private val handler = Handler(Looper.getMainLooper())

    private val sessionKeepAliveRunnable = object : Runnable {
        override fun run() {
            if (!eqManager.isEnabled()) return
            try {
                eqManager.ensureGlobalSession()

                // Periodically scan for active browser / media playback sessions
                ActiveSessionScanner.scanAsync { detectedSessions ->
                    for (sessionId in detectedSessions) {
                        if (sessionId > 0) {
                            eqManager.openAudioSession(sessionId, "keep_alive_scan")
                        }
                    }
                }
            } catch (e: Throwable) {
                Log.w(TAG, "Session keep-alive check failed", e)
            }
            handler.postDelayed(this, SESSION_KEEP_ALIVE_MS)
        }
    }

    override fun onCreate() {
        super.onCreate()
        eqManager = EqualizerManager.getInstance(this)
        eqManager.addListener(this)
        createNotificationChannel()
        registerAudioSessionReceiver()
        registerAudioDeviceCallback()
        registerAudioPlaybackCallback()
        startSessionKeepAlive()
    }

    private fun registerAudioSessionReceiver() {
        if (isReceiverRegistered) return
        val filter = IntentFilter().apply {
            addAction(AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION)
            addAction(AudioEffect.ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION)
            addAction("xyz.omniplay.equalizer.action.SYNC_EQ_STATE")
            addAction("xyz.omniplay.equalizer.action.REQUEST_SYNC")
            addAction("xyz.omniplay.action.SYNC_EQ_STATE")
            addAction("xyz.omniplay.action.SET_STEREO_EFFECTS")
            addAction("xyz.omniplay.action.REQUEST_SYNC")
            priority = 1000
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(audioSessionReceiver, filter, Context.RECEIVER_EXPORTED)
            } else {
                registerReceiver(audioSessionReceiver, filter)
            }
            isReceiverRegistered = true
            Log.d(TAG, "Dynamically registered AudioSessionReceiver with priority 1000")
        } catch (e: Throwable) {
            Log.e(TAG, "Failed to register dynamic AudioSessionReceiver", e)
        }
    }

    private fun unregisterAudioSessionReceiver() {
        if (!isReceiverRegistered) return
        try {
            unregisterReceiver(audioSessionReceiver)
            Log.d(TAG, "Unregistered dynamic AudioSessionReceiver")
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to unregister AudioSessionReceiver", e)
        } finally {
            isReceiverRegistered = false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopForegroundCompat()
                stopSelf()
                return START_NOT_STICKY
            }
            ACTION_TOGGLE -> {
                val newState = !eqManager.isEnabled()
                eqManager.setEnabled(newState)
                if (!newState) {
                    stopForegroundCompat()
                    stopSelf()
                    return START_NOT_STICKY
                }
            }
            else -> {
                // START or rebind
            }
        }

        registerAudioSessionReceiver()
        startSessionKeepAlive()

        val notification = buildNotification()
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                startForeground(
                    NOTIFICATION_ID,
                    notification,
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
                )
            } else {
                startForeground(NOTIFICATION_ID, notification)
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Failed to startForeground", e)
        }

        return START_STICKY
    }

    override fun onEqualizerConfigChanged() {
        if (!eqManager.isEnabled()) {
            stopForegroundCompat()
            stopSelf()
        } else {
            val manager = androidx.core.content.ContextCompat.getSystemService(this, NotificationManager::class.java)
            manager?.notify(NOTIFICATION_ID, buildNotification())
        }
    }

    private fun stopForegroundCompat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else {
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSessionKeepAlive()
        unregisterAudioPlaybackCallback()
        unregisterAudioDeviceCallback()
        unregisterAudioSessionReceiver()
        ActiveSessionScanner.stop()
        eqManager.removeListener(this)
    }

    private fun registerAudioPlaybackCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val audioManager = getSystemService(AudioManager::class.java) ?: return
            val callback = object : AudioManager.AudioPlaybackCallback() {
                override fun onPlaybackConfigChanged(configs: List<AudioPlaybackConfiguration>?) {
                    super.onPlaybackConfigChanged(configs)
                    if (!eqManager.isEnabled() || configs == null) return

                    val activeSessionIds = mutableSetOf<Int>()
                    var foundAny = false
                    for (config in configs) {
                        val usage = try {
                            config.audioAttributes.usage
                        } catch (_: Throwable) {
                            AudioAttributes.USAGE_MEDIA
                        }

                        // Filter out non-media/game noise: ignore notifications, alarms, and UI clicks
                        val isMediaOrGame = usage == AudioAttributes.USAGE_MEDIA ||
                                usage == AudioAttributes.USAGE_GAME ||
                                usage == AudioAttributes.USAGE_UNKNOWN

                        if (!isMediaOrGame) continue

                        val sessionId = ActiveSessionScanner.extractSessionId(config)
                        if (sessionId > 0) {
                            foundAny = true
                            activeSessionIds.add(sessionId)
                            val detectedPkg = ActiveSessionScanner.extractClientPackageName(config, packageManager)
                            Log.d(TAG, "AudioPlaybackCallback detected active session: $sessionId (pkg: $detectedPkg)")
                            eqManager.openAudioSession(sessionId, detectedPkg ?: "playback_callback")
                        }
                    }

                    // Auto-prune sessions that were detected via playback callback/scanners but have now stopped playing
                    val autoDetectedSessions = eqManager.getActiveSessionList()
                        .filter { !it.isGlobal }
                    for (session in autoDetectedSessions) {
                        val pkg = session.packageName
                        val isAutoScanned = pkg == "playback_callback" || pkg == "dumpsys_scan" || pkg == "keep_alive_scan" ||
                                (foundAny && !activeSessionIds.contains(session.sessionId))
                        if (isAutoScanned && !activeSessionIds.contains(session.sessionId)) {
                            Log.d(TAG, "AudioPlaybackCallback pruning inactive session: ${session.sessionId} (${session.packageName})")
                            eqManager.closeAudioSession(session.sessionId)
                        }
                    }

                    // If configs are present (audio playing) but reflection could not extract session ID,
                    // run background dumpsys scan to detect browser session
                    if (!foundAny && configs.isNotEmpty()) {
                        ActiveSessionScanner.scanAsync { sessions ->
                            for (sessionId in sessions) {
                                if (sessionId > 0) {
                                    Log.d(TAG, "ActiveSessionScanner detected session: $sessionId")
                                    eqManager.openAudioSession(sessionId, "dumpsys_scan")
                                }
                            }
                        }
                    }
                }
            }
            audioPlaybackCallback = callback
            try {
                audioManager.registerAudioPlaybackCallback(callback, handler)
                Log.d(TAG, "Registered AudioPlaybackCallback for browser session detection")
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to register AudioPlaybackCallback", e)
            }
        }
    }

    private fun unregisterAudioPlaybackCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val callback = audioPlaybackCallback as? AudioManager.AudioPlaybackCallback ?: return
            val audioManager = getSystemService(AudioManager::class.java)
            try {
                audioManager?.unregisterAudioPlaybackCallback(callback)
                Log.d(TAG, "Unregistered AudioPlaybackCallback")
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to unregister AudioPlaybackCallback", e)
            } finally {
                audioPlaybackCallback = null
            }
        }
    }

    private fun registerAudioDeviceCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val audioManager = getSystemService(AudioManager::class.java) ?: return
            val callback = object : AudioDeviceCallback() {
                override fun onAudioDevicesAdded(addedDevices: Array<out AudioDeviceInfo>?) {
                    Log.d(TAG, "Audio devices added, refreshing audio effects")
                    handler.postDelayed({
                        if (eqManager.isEnabled()) {
                            eqManager.applyCurrentStateToEffects()
                        }
                    }, 500)
                }

                override fun onAudioDevicesRemoved(removedDevices: Array<out AudioDeviceInfo>?) {
                    Log.d(TAG, "Audio devices removed, refreshing audio effects")
                    handler.postDelayed({
                        if (eqManager.isEnabled()) {
                            eqManager.applyCurrentStateToEffects()
                        }
                    }, 500)
                }
            }
            audioDeviceCallback = callback
            audioManager.registerAudioDeviceCallback(callback, handler)
        }
    }

    private fun unregisterAudioDeviceCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val callback = audioDeviceCallback as? AudioDeviceCallback ?: return
            val audioManager = getSystemService(AudioManager::class.java)
            audioManager?.unregisterAudioDeviceCallback(callback)
            audioDeviceCallback = null
        }
    }

    private fun startSessionKeepAlive() {
        handler.removeCallbacks(sessionKeepAliveRunnable)
        handler.postDelayed(sessionKeepAliveRunnable, SESSION_KEEP_ALIVE_MS)
    }

    private fun stopSessionKeepAlive() {
        handler.removeCallbacks(sessionKeepAliveRunnable)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.equalizer_service_channel_name),
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = getString(R.string.equalizer_service_channel_desc)
                setShowBadge(false)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val openIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val openPendingIntent = PendingIntent.getActivity(
            this,
            0,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val toggleIntent = Intent(this, EqualizerService::class.java).apply {
            action = ACTION_TOGGLE
        }
        val togglePendingIntent = PendingIntent.getService(
            this,
            1,
            toggleIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val presetIndex = eqManager.getCurrentPresetIndex()
        val presetName = eqManager.presets.getOrNull(presetIndex)?.name ?: "Active"
        val activeApps = eqManager.getActiveSessionList().filter { !it.isGlobal }
        val sessionCount = activeApps.size
        val subtitle = if (sessionCount > 0) {
            "$presetName · $sessionCount session${if (sessionCount != 1) "s" else ""}"
        } else {
            presetName
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_equalizer)
            .setContentTitle(getString(R.string.equalizer_active_title))
            .setContentText(subtitle)
            .setContentIntent(openPendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .addAction(
                R.drawable.ic_power,
                getString(R.string.turn_off),
                togglePendingIntent
            )
            .build()
    }
}
