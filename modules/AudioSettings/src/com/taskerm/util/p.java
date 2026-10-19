package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f9351b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f9352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f9353d;

    public p(String str, boolean z, long j, boolean z2) {
        c.f.b.k.b(str, "command");
        this.f9350a = str;
        this.f9351b = z;
        this.f9352c = j;
        this.f9353d = z2;
    }

    public /* synthetic */ p(String str, boolean z, long j, boolean z2, int i, c.f.b.g gVar) {
        this(str, z, j, (i & 8) != 0 ? false : z2);
    }

    public final String a() {
        return this.f9350a;
    }

    public final boolean b() {
        return this.f9351b;
    }

    public final long c() {
        return this.f9352c;
    }

    public final boolean d() {
        return this.f9353d;
    }
}
