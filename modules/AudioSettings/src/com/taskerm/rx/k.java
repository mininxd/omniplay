package com.joaomgcd.taskerm.rx;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final class k implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.a f8454a;

    k(c.f.a.a aVar) {
        this.f8454a = aVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.f8454a.invoke();
    }
}
