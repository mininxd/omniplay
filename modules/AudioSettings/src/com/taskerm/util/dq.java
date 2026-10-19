package com.joaomgcd.taskerm.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class dq implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.q f9318a;

    dq(c.f.a.q qVar) {
        this.f9318a = qVar;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final /* synthetic */ Object invoke(Object obj, Method method, Object[] objArr) {
        return this.f9318a.a(obj, method, objArr);
    }
}
