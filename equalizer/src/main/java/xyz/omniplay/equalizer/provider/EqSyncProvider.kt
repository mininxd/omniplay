package xyz.omniplay.equalizer.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import xyz.omniplay.equalizer.audio.EqualizerManager

/**
 * Direct Binder IPC ContentProvider for instantaneous, zero-latency EQ synchronization
 * between OmniPlay and OmniQualizer.
 */
class EqSyncProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "xyz.omniplay.equalizer.core"
        const val METHOD_SYNC_EQ_STATE = "SYNC_EQ_STATE"
        const val METHOD_GET_EQ_STATE = "GET_EQ_STATE"
    }

    override fun onCreate(): Boolean = true

    override fun call(method: String, arg: String?, extras: Bundle?): Bundle? {
        return handleCall(method, extras)
    }

    override fun call(authority: String, method: String, arg: String?, extras: Bundle?): Bundle? {
        return handleCall(method, extras)
    }

    private fun handleCall(method: String, extras: Bundle?): Bundle? {
        return try {
            val ctx = context ?: return null
            val eqManager = EqualizerManager.getInstance(ctx)

            when (method) {
                METHOD_SYNC_EQ_STATE -> {
                    if (extras != null) {
                        eqManager.applySyncBundle(extras)
                    }
                    Bundle().apply { putBoolean("success", true) }
                }
                METHOD_GET_EQ_STATE -> {
                    eqManager.buildStateBundle().apply { putBoolean("success", true) }
                }
                else -> null
            }
        } catch (e: Throwable) {
            android.util.Log.e("EqSyncProvider", "Error in call method=$method", e)
            null
        }
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? = null
    override fun getType(uri: Uri): String? = null
    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0
}
