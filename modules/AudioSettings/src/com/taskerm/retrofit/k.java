package com.joaomgcd.taskerm.retrofit;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f8374a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TimeUnit f8375b;

    public k(long j, TimeUnit timeUnit) {
        c.f.b.k.b(timeUnit, "timeUnit");
        this.f8374a = j;
        this.f8375b = timeUnit;
    }

    public final long a() {
        return this.f8374a;
    }

    public final TimeUnit b() {
        return this.f8375b;
    }
}
