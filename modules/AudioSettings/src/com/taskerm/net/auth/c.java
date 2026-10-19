package com.joaomgcd.taskerm.net.auth;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.k;
import net.dinglisch.android.taskerm.bg;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final SharedPreferences a(Context context) {
        k.b(context, "receiver$0");
        return context.getSharedPreferences(bg.k, 0);
    }
}
