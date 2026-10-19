package com.joaomgcd.taskerm.rx;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f8393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f8394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f8395c;

    public h(Object obj, Method method, d dVar) {
        c.f.b.k.b(obj, "receiver");
        c.f.b.k.b(method, "method");
        c.f.b.k.b(dVar, "subscription");
        this.f8393a = obj;
        this.f8394b = method;
        this.f8395c = dVar;
    }

    public final Object a() {
        return this.f8393a;
    }

    public final Method b() {
        return this.f8394b;
    }

    public final d c() {
        return this.f8395c;
    }
}
