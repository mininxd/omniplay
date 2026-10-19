package com.joaomgcd.taskerm.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class cc<TInstance, TAnnotation extends Annotation> extends cb<TAnnotation> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TInstance f9181a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cc(TInstance tinstance, Method method, Method method2, TAnnotation tannotation) {
        super(method, method2, tannotation);
        c.f.b.k.b(tinstance, "instance");
        c.f.b.k.b(method, "getter");
        c.f.b.k.b(tannotation, "annotation");
        this.f9181a = tinstance;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public cc(TInstance tinstance, cb<? extends TAnnotation> cbVar) {
        this(tinstance, cbVar.b(), cbVar.c(), cbVar.d());
        c.f.b.k.b(tinstance, "instance");
        c.f.b.k.b(cbVar, "propertyAndAnnotation");
    }

    public final Object e() {
        return b().invoke(this.f9181a, new Object[0]);
    }

    public final void a(Object obj) throws IllegalAccessException, InvocationTargetException {
        Method methodC = c();
        if (methodC != null) {
            methodC.invoke(this.f9181a, obj);
        }
    }
}
