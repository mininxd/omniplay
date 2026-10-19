package com.joaomgcd.taskerm.retrofit;

import c.q;
import f.n;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class b extends f.c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<?> f8359a;

    public b(d<?> dVar) {
        c.f.b.k.b(dVar, "args");
        this.f8359a = dVar;
    }

    @Override // f.c.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b(Type type, Annotation[] annotationArr, n nVar) {
        c.f.b.k.b(type, "returnType");
        c.f.b.k.b(annotationArr, "annotations");
        c.f.b.k.b(nVar, "retrofit");
        d<?> dVar = this.f8359a;
        f.c<?, ?> cVarB = f.a.a.h.a().b(type, annotationArr, nVar);
        if (cVarB != null) {
            return new a(dVar, nVar, cVarB);
        }
        throw new q("null cannot be cast to non-null type retrofit2.CallAdapter<io.reactivex.Single<*>, io.reactivex.Single<*>>");
    }
}
