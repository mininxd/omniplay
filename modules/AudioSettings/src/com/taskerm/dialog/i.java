package com.joaomgcd.taskerm.dialog;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6324a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f6325b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.a<String> f6326c;

    public i(Context context, int i, c.f.a.a<String> aVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(aVar, "action");
        this.f6324a = context;
        this.f6325b = i;
        this.f6326c = aVar;
    }

    public final c.f.a.a<String> b() {
        return this.f6326c;
    }

    public final String a() {
        return com.joaomgcd.taskerm.util.aj.a(this.f6325b, this.f6324a, new Object[0]);
    }

    public String toString() {
        return a();
    }
}
