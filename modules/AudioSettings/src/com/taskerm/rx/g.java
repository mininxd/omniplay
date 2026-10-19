package com.joaomgcd.taskerm.rx;

import cyanogenmod.app.ProfileManager;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class g implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AtomicInteger f8391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8392b;

    public g(String str) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        this.f8392b = str;
        this.f8391a = new AtomicInteger(0);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        return new Thread(runnable, this.f8392b + '-' + this.f8391a.incrementAndGet());
    }
}
