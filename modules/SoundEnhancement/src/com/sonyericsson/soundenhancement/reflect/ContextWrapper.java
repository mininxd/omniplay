package com.sonyericsson.soundenhancement.reflect;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class ContextWrapper extends ReflectionHelper {
    private Context mContext;

    public ContextWrapper(Context context) {
        this.mContext = context;
    }

    public Context createCredentialProtectedStorageContext() {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        Context ret = (Context) invokeMethod(context, "createCredentialProtectedStorageContext", new Object[0]);
        return ret;
    }
}
