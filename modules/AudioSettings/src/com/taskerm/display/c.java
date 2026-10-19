package com.joaomgcd.taskerm.display;

import android.content.Context;
import android.provider.Settings;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean b(Context context, String str, int i) {
        return Settings.System.putInt(context.getContentResolver(), str, i);
    }

    public static final int a(Context context, String str) {
        k.b(context, "receiver$0");
        k.b(str, "setting");
        return Settings.System.getInt(context.getContentResolver(), str);
    }
}
