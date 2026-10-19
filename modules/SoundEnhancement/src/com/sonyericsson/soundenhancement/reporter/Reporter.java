package com.sonyericsson.soundenhancement.reporter;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class Reporter {
    private Reporter() {
    }

    public static final void reportEvent(Context context, String name, boolean value) {
        IddAnalyticsReporter.reportEvent(context, name, false, value);
    }

    public static final void reportEvent(Context context, String name, String value) {
        IddAnalyticsReporter.reportEvent(context, name, false, value);
    }

    public static final void reportEvent(Context context, String name, String[] values) {
        IddAnalyticsReporter.reportEvent(context, name, false, values);
    }

    public static final void reportEventWithInit(Context context, String name, boolean value) {
        IddAnalyticsReporter.reportEvent(context, name, true, value);
    }

    public static final void reportEventWithInit(Context context, String name, String value) {
        IddAnalyticsReporter.reportEvent(context, name, true, value);
    }

    public static final void reportEventWithInit(Context context, String name, String[] values) {
        IddAnalyticsReporter.reportEvent(context, name, true, values);
    }
}
