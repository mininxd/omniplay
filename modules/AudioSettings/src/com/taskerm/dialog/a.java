package com.joaomgcd.taskerm.dialog;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6209a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6210b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f6211c;

    public a(Context context, com.joaomgcd.taskerm.util.h hVar, boolean z) {
        c.f.b.k.b(context, "context");
        this.f6209a = context;
        this.f6210b = hVar;
        this.f6211c = z;
    }

    public final Context a() {
        return this.f6209a;
    }

    public final com.joaomgcd.taskerm.util.h b() {
        return this.f6210b;
    }

    public final boolean c() {
        return this.f6211c;
    }
}
