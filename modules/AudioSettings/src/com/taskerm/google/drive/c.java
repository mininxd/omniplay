package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f7003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f7004c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f7005d;

    public c(String str, l lVar, boolean z, boolean z2) {
        c.f.b.k.b(lVar, "spaces");
        this.f7002a = str;
        this.f7003b = lVar;
        this.f7004c = z;
        this.f7005d = z2;
    }

    public /* synthetic */ c(String str, l lVar, boolean z, boolean z2, int i, c.f.b.g gVar) {
        this(str, lVar, z, (i & 8) != 0 ? false : z2);
    }

    public final String a() {
        return this.f7002a;
    }

    public final l b() {
        return this.f7003b;
    }

    public final boolean c() {
        return this.f7004c;
    }

    public final boolean d() {
        return this.f7005d;
    }
}
