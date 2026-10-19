package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class b<TResult> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final z f6998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Class<TResult> f6999b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f7000c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TResult f7001d;

    public b(z zVar, Class<TResult> cls, String str, TResult tresult) {
        c.f.b.k.b(zVar, "queryInfo");
        c.f.b.k.b(cls, "resultClass");
        c.f.b.k.b(str, "contenName");
        this.f6998a = zVar;
        this.f6999b = cls;
        this.f7000c = str;
        this.f7001d = tresult;
    }

    public /* synthetic */ b(z zVar, Class cls, String str, Object obj, int i, c.f.b.g gVar) {
        this(zVar, cls, (i & 4) != 0 ? zVar.toString() : str, (i & 8) != 0 ? null : obj);
    }

    public final z a() {
        return this.f6998a;
    }

    public final Class<TResult> b() {
        return this.f6999b;
    }

    public final String c() {
        return this.f7000c;
    }

    public final TResult d() {
        return this.f7001d;
    }
}
