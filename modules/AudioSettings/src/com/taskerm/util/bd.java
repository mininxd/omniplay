package com.joaomgcd.taskerm.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class bd<TAnnotation extends Annotation> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f9026a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TAnnotation f9027b;

    public bd(Method method, TAnnotation tannotation) {
        c.f.b.k.b(method, "method");
        c.f.b.k.b(tannotation, "annotation");
        this.f9026a = method;
        this.f9027b = tannotation;
    }

    public final Method a() {
        return this.f9026a;
    }

    public final TAnnotation b() {
        return this.f9027b;
    }
}
