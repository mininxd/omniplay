package com.sonymobile.audioutil;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    public static final boolean DEBUG = false;
    public static final String LOG_TAG = "ExtendedAudioService";
    public static final boolean IS_LOGGABLE_VERBOSE = Log.isLoggable(LOG_TAG, 2);
    public static final boolean IS_LOGGABLE_DEBUG = Log.isLoggable(LOG_TAG, 3);
    public static final boolean IS_LOGGABLE_INFO = Log.isLoggable(LOG_TAG, 4);
    public static final boolean IS_LOGGABLE_WARN = Log.isLoggable(LOG_TAG, 5);
    public static final boolean IS_LOGGABLE_ERROR = Log.isLoggable(LOG_TAG, 6);

    public static boolean isDebug() {
        if (IS_LOGGABLE_VERBOSE) {
            return true;
        }
        return false;
    }

    public static int v(String tag, String... msg) {
        if (!IS_LOGGABLE_VERBOSE) {
            return 0;
        }
        String str = concatenate(msg);
        int ret = Log.v(tag, str);
        return ret;
    }

    public static int d(String tag, String... msg) {
        if (!IS_LOGGABLE_DEBUG) {
            return 0;
        }
        String str = concatenate(msg);
        int ret = Log.d(tag, str);
        return ret;
    }

    public static int i(String tag, String... msg) {
        if (!IS_LOGGABLE_INFO) {
            return 0;
        }
        String str = concatenate(msg);
        int ret = Log.i(tag, str);
        return ret;
    }

    public static int w(String tag, String... msg) {
        if (!IS_LOGGABLE_WARN) {
            return 0;
        }
        String str = concatenate(msg);
        int ret = Log.w(tag, str);
        return ret;
    }

    public static int e(String tag, String... msg) {
        if (!IS_LOGGABLE_ERROR) {
            return 0;
        }
        String str = concatenate(msg);
        int ret = Log.e(tag, str);
        return ret;
    }

    public static int e(String tag, Throwable th, String... msg) {
        if (!IS_LOGGABLE_ERROR) {
            return 0;
        }
        String str = concatenate(msg);
        int ret = Log.e(tag, str, th);
        return ret;
    }

    private static String concatenate(String[] str) {
        StringBuffer buf = new StringBuffer();
        if (str != null) {
            for (String s : str) {
                buf.append(s);
            }
        }
        return buf.toString();
    }
}
