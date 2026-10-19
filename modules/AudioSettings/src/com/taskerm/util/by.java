package com.joaomgcd.taskerm.util;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class by<TProgress> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f9172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.b<bx, c.s> f9173b;

    /* JADX WARN: Multi-variable type inference failed */
    public by(int i, c.f.a.b<? super bx, c.s> bVar) {
        c.f.b.k.b(bVar, "callback");
        this.f9172a = i;
        this.f9173b = bVar;
    }

    public final int a() {
        return this.f9172a;
    }

    public final c.f.a.b<bx, c.s> b() {
        return this.f9173b;
    }

    protected final void a(bx bxVar) {
        c.f.b.k.b(bxVar, NotificationCompat.CATEGORY_PROGRESS);
        this.f9173b.invoke(bxVar);
    }

    public final void a(long j) {
        a(new bx(Long.valueOf(j), j, false, null, 12, null));
    }
}
