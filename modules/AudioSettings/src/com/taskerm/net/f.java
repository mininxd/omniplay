package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.bz;

/* JADX INFO: loaded from: classes.dex */
public final class f<TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends e<TResult, TResultError> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7891c = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(f.class), "http", "getHttp()Lcom/joaomgcd/taskerm/net/Http;"))};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7892d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final v f7893e;

    @Override // com.joaomgcd.taskerm.net.e, com.joaomgcd.taskerm.net.c
    public l<?, TResult, TResultError> a() {
        c.e eVar = this.f7892d;
        c.j.g gVar = f7891c[0];
        return (l) eVar.b();
    }

    public final v o() {
        return this.f7893e;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(v vVar, Object obj, String str, String str2, Class<TResult> cls, Class<TResultError> cls2, b.a.l<String> lVar, bz bzVar, TResult tresult) {
        super(obj, str, str2, cls, cls2, lVar, bzVar != null ? bzVar.a(vVar.b()) : null, tresult);
        c.f.b.k.b(vVar, "content");
        c.f.b.k.b(obj, "body");
        c.f.b.k.b(str, "url");
        c.f.b.k.b(str2, "method");
        c.f.b.k.b(cls, "resultClass");
        c.f.b.k.b(cls2, "errorClass");
        this.f7893e = vVar;
        this.f7892d = c.f.a(new a());
    }

    public /* synthetic */ f(v vVar, Object obj, String str, String str2, Class cls, Class cls2, b.a.l lVar, bz bzVar, Object obj2, int i, c.f.b.g gVar) {
        this(vVar, obj, str, str2, cls, cls2, lVar, bzVar, (i & 256) != 0 ? null : obj2);
    }

    static final class a extends c.f.b.l implements c.f.a.a<p<TResult, TResultError>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final p<TResult, TResultError> invoke() {
            return new p<>(f.this);
        }
    }
}
