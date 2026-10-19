package com.sonyericsson.soundenhancement.reflect;

import android.os.UserHandle;

/* JADX INFO: loaded from: classes2.dex */
public class UserHandleWrapper extends ReflectionHelper {
    public static final UserHandle ALL = (UserHandle) getStaticFieldValue(UserHandle.class, "ALL");

    public static int getCallingUserId() {
        return ((Integer) invokeStaticMethod(UserHandle.class, "getCallingUserId", new Object[0])).intValue();
    }
}
