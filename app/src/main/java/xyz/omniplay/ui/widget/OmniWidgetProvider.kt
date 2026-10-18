package xyz.omniplay.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.widget.RemoteViews
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.service.PlaybackService
import xyz.omniplay.ui.MainActivity

class OmniWidgetProvider : AppWidgetProvider() {

    companion object {
        private var lastSong: Song? = null
        private var lastIsPlaying: Boolean = false
        private var lastAlbumArt: Bitmap? = null

        fun updateWidget(
            context: Context,
            song: Song?,
            isPlaying: Boolean,
            albumArt: Bitmap?
        ) {
            lastSong = song
            lastIsPlaying = isPlaying
            lastAlbumArt = albumArt

            val appWidgetManager = AppWidgetManager.getInstance(context) ?: return
            val componentName = ComponentName(context, OmniWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName) ?: return
            if (appWidgetIds.isEmpty()) return

            for (appWidgetId in appWidgetIds) {
                val views = buildRemoteViews(context, song, isPlaying, albumArt)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }

        private fun buildRemoteViews(
            context: Context,
            song: Song?,
            isPlaying: Boolean,
            albumArt: Bitmap?
        ): RemoteViews {
            val views = RemoteViews(context.packageName, R.layout.widget_omniplay)

            // Open App on widget body click
            val openAppIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            val pendingOpenApp = PendingIntent.getActivity(
                context,
                0,
                openAppIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_root, pendingOpenApp)

            // Controls PendingIntents
            val flags = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            val prevIntent = Intent(context, PlaybackService::class.java).apply {
                action = PlaybackService.ACTION_PREVIOUS
            }
            val prevPending = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PendingIntent.getForegroundService(context, 1, prevIntent, flags)
            } else {
                PendingIntent.getService(context, 1, prevIntent, flags)
            }
            views.setOnClickPendingIntent(R.id.widget_btn_prev, prevPending)

            val toggleIntent = Intent(context, PlaybackService::class.java).apply {
                action = PlaybackService.ACTION_TOGGLE
            }
            val togglePending = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PendingIntent.getForegroundService(context, 2, toggleIntent, flags)
            } else {
                PendingIntent.getService(context, 2, toggleIntent, flags)
            }
            views.setOnClickPendingIntent(R.id.widget_btn_play_pause, togglePending)

            val nextIntent = Intent(context, PlaybackService::class.java).apply {
                action = PlaybackService.ACTION_NEXT
            }
            val nextPending = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PendingIntent.getForegroundService(context, 3, nextIntent, flags)
            } else {
                PendingIntent.getService(context, 3, nextIntent, flags)
            }
            views.setOnClickPendingIntent(R.id.widget_btn_next, nextPending)

            // Track metadata
            if (song != null) {
                views.setTextViewText(R.id.widget_title, song.title)
                views.setTextViewText(R.id.widget_artist, "${song.artist} • ${song.album}")
            } else {
                views.setTextViewText(R.id.widget_title, context.getString(R.string.widget_no_track))
                views.setTextViewText(R.id.widget_artist, context.getString(R.string.app_name))
            }

            // Play/Pause icon
            val playPauseRes = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
            views.setImageViewResource(R.id.widget_btn_play_pause, playPauseRes)

            // Artwork
            if (albumArt != null) {
                views.setImageViewBitmap(R.id.widget_album_art, albumArt)
            } else {
                views.setImageViewResource(R.id.widget_album_art, R.drawable.ic_music_note)
            }

            return views
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val views = buildRemoteViews(context, lastSong, lastIsPlaying, lastAlbumArt)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
