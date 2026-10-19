package com.joaomgcd.taskerm.function;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.k;
import com.joaomgcd.taskerm.n.b;

/* JADX INFO: loaded from: classes.dex */
public final class ThrottledNotificationsKt {
    private static final String PREF_THROTTLED_NOTIFICATIONS = "throttled_notifications";
    private static final String TAG = "ThrottledNotifications";
    private static ThrottledNotifications throttledNotificationsCache;

    public static final ThrottledNotifications getThrottledNotifications(Context context) {
        k.b(context, "receiver$0");
        ThrottledNotifications throttledNotifications = throttledNotificationsCache;
        if (throttledNotifications != null) {
            return throttledNotifications;
        }
        SharedPreferences preferencesFunctions = FunctionBaseKt.getPreferencesFunctions(context);
        k.a((Object) preferencesFunctions, "preferencesFunctions");
        String string = preferencesFunctions.getString(PREF_THROTTLED_NOTIFICATIONS, null);
        ThrottledNotifications throttledNotifications2 = (ThrottledNotifications) (string != null ? b.a().a(string, ThrottledNotifications.class) : null);
        if (throttledNotifications2 == null) {
            throttledNotifications2 = new ThrottledNotifications();
        }
        throttledNotificationsCache = throttledNotifications2;
        return throttledNotifications2;
    }

    public static final void setThrottledNotifications(Context context, ThrottledNotifications throttledNotifications) {
        k.b(context, "receiver$0");
        k.b(throttledNotifications, "value");
        throttledNotificationsCache = throttledNotifications;
        SharedPreferences preferencesFunctions = FunctionBaseKt.getPreferencesFunctions(context);
        k.a((Object) preferencesFunctions, "preferencesFunctions");
        com.joaomgcd.taskerm.q.b.a(preferencesFunctions, PREF_THROTTLED_NOTIFICATIONS, throttledNotifications);
    }
}
