package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class bx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Long f9168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f9169b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f9170c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9171d;

    public bx(Long l, long j, boolean z, String str) {
        this.f9168a = l;
        this.f9169b = j;
        this.f9170c = z;
        this.f9171d = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ bx(Long l, long j, boolean z, String str, int i, c.f.b.g gVar) {
        if ((i & 4) != 0) {
            z = l != null && l.longValue() == j;
        }
        this(l, j, z, (i & 8) != 0 ? (String) null : str);
    }

    public final Long a() {
        return this.f9168a;
    }

    public final long b() {
        return this.f9169b;
    }

    public final boolean c() {
        return this.f9170c;
    }

    public final String d() {
        return this.f9171d;
    }
}
