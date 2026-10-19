package com.joaomgcd.taskerm.util;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ax<T> implements c.e<T>, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile Object f9020a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f9021b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.a<T> f9022c;

    /* JADX WARN: Multi-variable type inference failed */
    public ax(c.f.a.a<? extends T> aVar, Object obj) {
        c.f.b.k.b(aVar, "initializer");
        this.f9022c = aVar;
        this.f9020a = dj.f9275a;
        this.f9021b = obj == null ? this : obj;
    }

    public /* synthetic */ ax(c.f.a.a aVar, Object obj, int i, c.f.b.g gVar) {
        this(aVar, (i & 2) != 0 ? null : obj);
    }

    public final void a() {
        this.f9020a = dj.f9275a;
    }

    @Override // c.e
    public T b() {
        T tInvoke;
        T t = (T) this.f9020a;
        if (t != dj.f9275a) {
            return t;
        }
        synchronized (this.f9021b) {
            tInvoke = (T) this.f9020a;
            if (tInvoke == dj.f9275a) {
                tInvoke = this.f9022c.invoke();
                this.f9020a = tInvoke;
            }
        }
        return tInvoke;
    }

    public boolean c() {
        return this.f9020a != dj.f9275a;
    }

    public String toString() {
        return c() ? String.valueOf(b()) : "Lazy value not initialized yet.";
    }
}
