package com.joaomgcd.taskerm.util;

import android.content.BroadcastReceiver;

/* JADX INFO: loaded from: classes.dex */
final class ct {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BroadcastReceiver f9246b;

    public ct(String str, BroadcastReceiver broadcastReceiver) {
        c.f.b.k.b(str, "tag");
        c.f.b.k.b(broadcastReceiver, "receiver");
        this.f9245a = str;
        this.f9246b = broadcastReceiver;
    }

    public final String a() {
        return this.f9245a;
    }

    public final BroadcastReceiver b() {
        return this.f9246b;
    }
}
