package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f9341c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Integer f9342d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Integer f9343e;

    public n(String str, String str2, String str3, Integer num, Integer num2) {
        c.f.b.k.b(str, "tag");
        c.f.b.k.b(str2, "packageName");
        this.f9339a = str;
        this.f9340b = str2;
        this.f9341c = str3;
        this.f9342d = num;
        this.f9343e = num2;
    }

    public final String a() {
        return this.f9339a;
    }

    public final String b() {
        return this.f9340b;
    }

    public /* synthetic */ n(String str, String str2, String str3, Integer num, Integer num2, int i, c.f.b.g gVar) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (Integer) null : num2);
    }

    public final String c() {
        return this.f9341c;
    }

    public final Integer d() {
        return this.f9342d;
    }

    public final Integer e() {
        return this.f9343e;
    }
}
