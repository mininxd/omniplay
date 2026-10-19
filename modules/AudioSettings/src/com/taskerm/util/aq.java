package com.joaomgcd.taskerm.util;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class aq<Z> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Z f8987a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f8988b;

    public aq(Z z, Method method) {
        c.f.b.k.b(method, "method");
        this.f8987a = z;
        this.f8988b = method;
    }

    public final <R> R a() {
        return (R) this.f8988b.invoke(this.f8987a, new Object[0]);
    }

    public final <T, R> R a(T t) {
        return (R) this.f8988b.invoke(this.f8987a, t);
    }
}
