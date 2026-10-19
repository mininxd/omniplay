package com.joaomgcd.taskerm.u;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Thread implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Semaphore f8814a = new Semaphore(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Exception f8815b;

    protected abstract List<String> a();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public abstract void d();

    public final List<String> b() {
        try {
            this.f8814a.acquire();
            return a();
        } finally {
            this.f8814a.release();
        }
    }

    public final Exception c() {
        return this.f8815b;
    }

    @Override // java.lang.Thread
    public void start() throws InterruptedException {
        this.f8814a.acquire();
        super.start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            try {
                d();
            } catch (IOException e2) {
                this.f8815b = e2;
            }
        } finally {
            this.f8814a.release();
        }
    }
}
