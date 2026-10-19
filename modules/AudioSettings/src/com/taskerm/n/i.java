package com.joaomgcd.taskerm.n;

/* JADX INFO: loaded from: classes.dex */
abstract class i<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7810a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f7811b;

    private i(String str, T t) {
        this.f7810a = str;
        this.f7811b = t;
    }

    public /* synthetic */ i(String str, Object obj, c.f.b.g gVar) {
        this(str, obj);
    }

    public final String a() {
        return this.f7810a;
    }

    public final T b() {
        return this.f7811b;
    }

    public String toString() {
        return this.f7810a + ':' + this.f7811b;
    }
}
