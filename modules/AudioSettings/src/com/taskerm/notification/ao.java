package com.joaomgcd.taskerm.notification;

import android.annotation.TargetApi;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(26)
public final class ao {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8039a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f8040b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f8041c;

    public ao(String str, boolean z, int i) {
        c.f.b.k.b(str, "key");
        this.f8039a = str;
        this.f8040b = z;
        this.f8041c = i;
    }

    public /* synthetic */ ao(String str, boolean z, int i, int i2, c.f.b.g gVar) {
        this(str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? 0 : i);
    }

    public final String a() {
        return this.f8039a;
    }

    public final boolean b() {
        return this.f8040b;
    }

    public final int c() {
        return this.f8041c;
    }
}
