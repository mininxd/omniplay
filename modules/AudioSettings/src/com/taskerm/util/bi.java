package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class bi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9041a = {c.f.b.x.a(new c.f.b.p(c.f.b.x.a(bi.class), "wasSelected", "getWasSelected()Z"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.q.d f9042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f9043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f9044d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f9045e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final bm f9046f;
    private final String g;

    public abstract Integer a();

    public final void a(boolean z) {
        this.f9042b.a(this, f9041a[0], Boolean.valueOf(z));
    }

    public final boolean b() {
        return this.f9042b.a(this, f9041a[0]).booleanValue();
    }

    public bi(Context context, int i, bm bmVar, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bmVar, "versions");
        c.f.b.k.b(str, "type");
        this.f9044d = context;
        this.f9045e = i;
        this.f9046f = bmVar;
        this.g = str;
        this.f9042b = new com.joaomgcd.taskerm.q.d(this.f9044d, false, null, "codewasselectedddddd:" + this.g + ':' + this.f9045e + ':' + this.f9046f.a(), 6, null);
        this.f9043c = b() ^ true;
    }

    public final int d() {
        return this.f9045e;
    }

    public final boolean c() {
        return this.f9043c;
    }
}
