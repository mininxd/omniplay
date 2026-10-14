package xyz.omniplay.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.util.concurrent.Executors

class AlbumArtDatabase private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "omniplay_album_art.db"
        const val DATABASE_VERSION = 1
        const val TABLE_ART = "album_art"
        const val COLUMN_KEY = "cache_key"
        const val COLUMN_DATA = "image_data"
        const val COLUMN_TIMESTAMP = "timestamp"
        const val MAX_CACHE_SIZE_BYTES = 50 * 1024 * 1024L // 50 MB

        private val executor = Executors.newSingleThreadExecutor()

        @Volatile
        private var instance: AlbumArtDatabase? = null

        fun getInstance(context: Context): AlbumArtDatabase {
            return instance ?: synchronized(this) {
                instance ?: AlbumArtDatabase(context.applicationContext).also { instance = it }
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS $TABLE_ART (
                $COLUMN_KEY TEXT PRIMARY KEY,
                $COLUMN_DATA BLOB NOT NULL,
                $COLUMN_TIMESTAMP INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_timestamp ON $TABLE_ART($COLUMN_TIMESTAMP)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ART")
        onCreate(db)
    }

    fun getArt(key: String): Bitmap? {
        val db = readableDatabase
        var bitmap: Bitmap? = null
        db.query(
            TABLE_ART,
            arrayOf(COLUMN_DATA),
            "$COLUMN_KEY = ?",
            arrayOf(key),
            null,
            null,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val data = cursor.getBlob(0)
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            }
        }
        if (bitmap != null) {
            // Update timestamp for LRU without blocking
            executor.execute {
                try {
                    val writeDb = writableDatabase
                    val values = ContentValues().apply {
                        put(COLUMN_TIMESTAMP, System.currentTimeMillis())
                    }
                    writeDb.update(TABLE_ART, values, "$COLUMN_KEY = ?", arrayOf(key))
                } catch (e: Exception) {}
            }
        }
        return bitmap
    }

    fun saveArt(key: String, bitmap: Bitmap) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream)
        val data = stream.toByteArray()

        val values = ContentValues().apply {
            put(COLUMN_KEY, key)
            put(COLUMN_DATA, data)
            put(COLUMN_TIMESTAMP, System.currentTimeMillis())
        }

        val db = writableDatabase
        db.insertWithOnConflict(TABLE_ART, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        trimCache(db)
    }

    private fun trimCache(db: SQLiteDatabase) {
        try {
            val pageCount = db.compileStatement("PRAGMA page_count").simpleQueryForLong()
            val freelistCount = db.compileStatement("PRAGMA freelist_count").simpleQueryForLong()
            val pageSize = db.compileStatement("PRAGMA page_size").simpleQueryForLong()
            if ((pageCount - freelistCount) * pageSize > MAX_CACHE_SIZE_BYTES) {
                // Delete oldest 20%
                db.execSQL(
                    """
                    DELETE FROM $TABLE_ART WHERE $COLUMN_KEY IN (
                        SELECT $COLUMN_KEY FROM $TABLE_ART ORDER BY $COLUMN_TIMESTAMP ASC LIMIT (SELECT COUNT(*)/5 FROM $TABLE_ART)
                    )
                    """.trimIndent()
                )
            }
        } catch (e: Exception) {}
    }

    fun clearAll() {
        try {
            writableDatabase.delete(TABLE_ART, null, null)
        } catch (e: Exception) {}
    }
}
