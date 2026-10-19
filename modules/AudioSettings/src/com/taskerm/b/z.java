package com.joaomgcd.taskerm.b;

import java.io.Closeable;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class z implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private w f5991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f5993c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Queue<byte[]> f5995e = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private AtomicBoolean f5994d = new AtomicBoolean(false);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f5996f = false;

    public z(w wVar, int i) {
        this.f5991a = wVar;
        this.f5992b = i;
    }

    void a(byte[] bArr) {
        synchronized (this.f5995e) {
            this.f5995e.add(bArr);
            this.f5995e.notifyAll();
        }
    }

    void a() throws IOException {
        this.f5991a.f5972a.write(y.b(this.f5992b, this.f5993c));
        this.f5991a.f5972a.flush();
    }

    void a(int i) {
        this.f5993c = i;
    }

    void b() {
        this.f5994d.set(true);
    }

    void c() {
        this.f5996f = true;
        synchronized (this) {
            notifyAll();
        }
        synchronized (this.f5995e) {
            this.f5995e.notifyAll();
        }
    }

    public byte[] d() throws InterruptedException, IOException {
        byte[] bArrPoll;
        synchronized (this.f5995e) {
            bArrPoll = null;
            while (!this.f5996f && (bArrPoll = this.f5995e.poll()) == null) {
                this.f5995e.wait();
            }
            if (this.f5996f) {
                throw new IOException("Stream closed");
            }
        }
        return bArrPoll;
    }

    public void a(String str) throws InterruptedException, IOException {
        a(str.getBytes("UTF-8"), false);
        a(new byte[]{0}, true);
    }

    public void a(byte[] bArr, boolean z) throws InterruptedException, IOException {
        synchronized (this) {
            while (!this.f5996f && !this.f5994d.compareAndSet(true, false)) {
                wait();
            }
            if (this.f5996f) {
                throw new IOException("Stream closed");
            }
        }
        this.f5991a.f5972a.write(y.a(this.f5992b, this.f5993c, bArr));
        if (z) {
            this.f5991a.f5972a.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            if (this.f5996f) {
                return;
            }
            c();
            this.f5991a.f5972a.write(y.a(this.f5992b, this.f5993c));
            this.f5991a.f5972a.flush();
        }
    }

    public boolean e() {
        return this.f5996f;
    }
}
