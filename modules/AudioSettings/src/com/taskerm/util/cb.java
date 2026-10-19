package com.joaomgcd.taskerm.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class cb<TAnnotation extends Annotation> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<?> f9177a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f9178b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Method f9179c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TAnnotation f9180d;

    public cb(Method method, Method method2, TAnnotation tannotation) {
        c.f.b.k.b(method, "getter");
        c.f.b.k.b(tannotation, "annotation");
        this.f9178b = method;
        this.f9179c = method2;
        this.f9180d = tannotation;
        Class<?> returnType = this.f9178b.getReturnType();
        c.f.b.k.a((Object) returnType, "getter.returnType");
        this.f9177a = returnType;
    }

    public final Method b() {
        return this.f9178b;
    }

    public final Method c() {
        return this.f9179c;
    }

    public final TAnnotation d() {
        return this.f9180d;
    }

    public final Class<?> a() {
        return this.f9177a;
    }
}
