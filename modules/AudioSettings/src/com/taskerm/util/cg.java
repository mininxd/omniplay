package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
final class cg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9191b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f9192c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9193d;

    public cg(String str, String str2, String str3, String str4) {
        c.f.b.k.b(str, "raw");
        c.f.b.k.b(str2, "taskName");
        this.f9190a = str;
        this.f9191b = str2;
        this.f9192c = str3;
        this.f9193d = str4;
    }

    public /* synthetic */ cg(String str, String str2, String str3, String str4, int i, c.f.b.g gVar) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4);
    }

    public final String a() {
        return this.f9191b;
    }

    public final String b() {
        return this.f9192c;
    }

    public final String c() {
        return this.f9193d;
    }
}
