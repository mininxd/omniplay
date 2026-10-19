package com.joaomgcd.taskerm.retrofit;

import b.a.l;
import b.a.p;
import b.a.q;
import d.ac;
import f.m;
import f.n;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class a implements f.c<l<?>, l<?>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<?> f8354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n f8355b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f.c<l<?>, l<?>> f8356c;

    public a(d<?> dVar, n nVar, f.c<l<?>, l<?>> cVar) {
        c.f.b.k.b(dVar, "args");
        c.f.b.k.b(nVar, "retrofit");
        c.f.b.k.b(cVar, "original");
        this.f8354a = dVar;
        this.f8355b = nVar;
        this.f8356c = cVar;
    }

    @Override // f.c
    public Type a() {
        Type typeA = this.f8356c.a();
        c.f.b.k.a((Object) typeA, "original.responseType()");
        return typeA;
    }

    @Override // f.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public l<?> b(f.b<l<?>> bVar) {
        l lVarA = this.f8356c.b(bVar).a((q<? super Object, ? extends R>) b.f8358a);
        l lVarD = lVarA;
        if (this.f8354a instanceof e) {
            lVarD = lVarA.d(new C0225a());
        }
        c.f.b.k.a((Object) lVarD, "result");
        return lVarD;
    }

    static final class b<Upstream, Downstream> implements q<Object, Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8358a = new b();

        b() {
        }

        @Override // b.a.q
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final l<Object> a(l<Object> lVar) {
            c.f.b.k.b(lVar, "upstream");
            return com.joaomgcd.taskerm.rx.i.b(lVar);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.retrofit.a$a, reason: collision with other inner class name */
    static final class C0225a<T, R> implements b.a.d.g<Throwable, p<? extends Object>> {
        C0225a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<Object> apply(Throwable th) throws IOException {
            c.f.b.k.b(th, "it");
            g gVarA = a.this.a(th);
            if (gVarA != null) {
                th = gVarA;
            }
            return l.b(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final g a(Throwable th) throws IOException {
        ac acVarE;
        if (!(th instanceof f.h) || !(this.f8354a instanceof e)) {
            return null;
        }
        d<?> dVar = this.f8354a;
        if (dVar == null) {
            throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.retrofit.ClientArgsWithErrorHandler<*, kotlin.Any>");
        }
        e eVar = (e) dVar;
        f.h hVar = (f.h) th;
        m<?> mVarB = hVar.b();
        if (mVarB == null || (acVarE = mVarB.e()) == null) {
            return null;
        }
        Object objA = this.f8355b.b(eVar.h(), new Annotation[0]).a(acVarE);
        c.f.a.b bVarI = eVar.i();
        c.f.b.k.a(objA, "errorObject");
        return new g(hVar.a(), (String) bVarI.invoke(objA), th);
    }
}
