package com.joaomgcd.taskerm.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class dp implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.q f9317a;

    public dp(c.f.a.q qVar) {
        this.f9317a = qVar;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final /* synthetic */ Object invoke(Object obj, Method method, Object[] objArr) {
        return this.f9317a.a(obj, method, objArr);
    }
}
