package com.sonyericsson.soundenhancement.audioeffect;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.sonyericsson.soundenhancement.PreferenceUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SettingsProvider extends ContentProvider {
    static final String COLUMN_STATE = "state";
    static final String SETTINGS_PROVIDER_AUTHORITY = "com.sonyericsson.soundenhancement.audioeffect.settingsprovider";
    private static final int URI_ID_CLEAR_AUDIO_PLUS = 1;
    private static final int URI_ID_DSEE_HX = 2;
    private UriMatcher mUriMatcher;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mUriMatcher = new UriMatcher(-1);
        this.mUriMatcher.addURI(SETTINGS_PROVIDER_AUTHORITY, "C_A_PLUS", 1);
        this.mUriMatcher.addURI(SETTINGS_PROVIDER_AUTHORITY, "DSEE_HX", 2);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        boolean retState;
        int match = this.mUriMatcher.match(uri);
        if (match == 1) {
            SharedPreferences sp = PreferenceUtil.getSharedPreferencesWithDeviceProtected(getContext(), "SE-CLEAR_AUDIO_PLUS", 0);
            retState = sp.getBoolean("C_A_PLUS", false);
        } else if (match == 2) {
            SharedPreferences sp2 = PreferenceUtil.getSharedPreferencesWithDeviceProtected(getContext(), "SE-DSEE-HX", 0);
            retState = sp2.getBoolean("DSEE_HX", false);
        } else {
            throw new IllegalArgumentException("No matches for Uri: " + uri.toString());
        }
        MatrixCursor result = new MatrixCursor(new String[]{COLUMN_STATE});
        String[] values = {String.valueOf(retState)};
        result.addRow(values);
        return result;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Invalid operation getType()");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Invalid operation insert()");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String s, String[] strings) {
        throw new UnsupportedOperationException("Invalid operation delete()");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        throw new UnsupportedOperationException("Invalid operation update()");
    }
}
