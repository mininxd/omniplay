package com.sonyericsson.soundenhancement.reflect;

import android.app.ActivityManager;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityManagerWrapper extends ReflectionHelper {
    public static int getCurrentUser() {
        return ((Integer) invokeStaticMethod(ActivityManager.class, "getCurrentUser", new Object[0])).intValue();
    }
}
