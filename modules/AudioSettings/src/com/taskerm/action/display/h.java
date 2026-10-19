package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(Context context) {
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.b(context, "last-immersvice", null, sharedPreferencesC);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Context context, String str) {
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "last-immersvice", str, sharedPreferencesC);
    }
}
