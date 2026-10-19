package com.joaomgcd.taskerm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class c<Z> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Z f9174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f9175b;

    public c(Z z, Method method) {
        c.f.b.k.b(method, "method");
        this.f9174a = z;
        this.f9175b = method;
    }

    public final <T> void a(T t) throws IllegalAccessException, InvocationTargetException {
        this.f9175b.invoke(this.f9174a, t);
    }

    public final <T1, T2> void a(T1 t1, T2 t2) throws IllegalAccessException, InvocationTargetException {
        this.f9175b.invoke(this.f9174a, t1, t2);
    }

    public final <T1, T2, T3> void a(T1 t1, T2 t2, T3 t3) throws IllegalAccessException, InvocationTargetException {
        this.f9175b.invoke(this.f9174a, t1, t2, t3);
    }
}
