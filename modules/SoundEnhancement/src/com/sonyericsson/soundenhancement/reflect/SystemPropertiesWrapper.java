package com.sonyericsson.soundenhancement.reflect;

/* JADX INFO: loaded from: classes2.dex */
public class SystemPropertiesWrapper extends ReflectionHelper {
    private static final String NAME_TARGET = "android.os.SystemProperties";

    public static final String get(String key, String def) {
        return (String) invokeStaticMethod(NAME_TARGET, "get", key, def);
    }
}
