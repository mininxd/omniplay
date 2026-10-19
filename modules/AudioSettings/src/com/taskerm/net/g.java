package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.bz;

/* JADX INFO: loaded from: classes.dex */
public class g<TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends c<TResult, TResultError> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7895b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(g.class), "http", "getHttp()Lcom/joaomgcd/taskerm/net/Http;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7896c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final v f7897d;

    @Override // com.joaomgcd.taskerm.net.c
    public l<?, TResult, TResultError> a() {
        c.e eVar = this.f7896c;
        c.j.g gVar = f7895b[0];
        return (l) eVar.b();
    }

    public final v n() {
        return this.f7897d;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(v vVar, String str, String str2, Class<TResult> cls, Class<TResultError> cls2, b.a.l<String> lVar, bz bzVar, TResult tresult) {
        super(str, str2, cls, cls2, tresult, lVar, null, bzVar != null ? bzVar.a(vVar.b()) : null, null, null, false, false, 3904, null);
        c.f.b.k.b(vVar, "content");
        c.f.b.k.b(str, "url");
        c.f.b.k.b(str2, "method");
        c.f.b.k.b(cls, "resultClass");
        c.f.b.k.b(cls2, "errorClass");
        this.f7897d = vVar;
        this.f7896c = c.f.a(new a());
    }

    static final class a extends c.f.b.l implements c.f.a.a<q<g<TResult, TResultError>, TResult, TResultError>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final q<g<TResult, TResultError>, TResult, TResultError> invoke() {
            return new q<>(g.this);
        }
    }
}
