package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class bv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final bv f9165a = new bv();

    private bv() {
    }

    public final net.dinglisch.android.taskerm.g a(Context context, int i) {
        c.f.b.k.b(context, "context");
        return new net.dinglisch.android.taskerm.g(net.dinglisch.android.taskerm.dh.b(net.dinglisch.android.taskerm.dh.a.Action, context.getPackageManager(), i));
    }
}
