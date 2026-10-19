package com.sonyericsson.soundenhancement.ahc;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class Logger {
    public static void LOGD(String message) {
    }

    public static void LOGD(String tag, String message) {
    }

    public static void LOGI(String message) {
        Log.i(Constants.TAG, message);
    }

    public static void LOGI(String tag, String message) {
        Log.i(tag, message);
    }

    public static final void LOGW(String message) {
        Log.w(Constants.TAG, message);
    }

    public static final void LOGW(String tag, String message) {
        Log.w(tag, message);
    }

    public static void LOGE(String message) {
        Log.e(Constants.TAG, message);
    }

    public static void LOGE(String tag, String message) {
        Log.e(tag, message);
    }
}
