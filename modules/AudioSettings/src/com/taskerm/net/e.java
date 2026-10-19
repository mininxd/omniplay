package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.ca;

/* JADX INFO: loaded from: classes.dex */
public class e<TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends c<TResult, TResultError> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7885b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(e.class), "bodyString", "getBodyString()Ljava/lang/String;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(e.class), "http", "getHttp()Lcom/joaomgcd/taskerm/net/Http;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7886c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Object f7888e;

    @Override // com.joaomgcd.taskerm.net.c
    public l<?, TResult, TResultError> a() {
        c.e eVar = this.f7887d;
        c.j.g gVar = f7885b[1];
        return (l) eVar.b();
    }

    public final String n() {
        c.e eVar = this.f7886c;
        c.j.g gVar = f7885b[0];
        return (String) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Object obj, String str, String str2, Class<TResult> cls, Class<TResultError> cls2, b.a.l<String> lVar, ca caVar, TResult tresult) {
        super(str, str2, cls, cls2, tresult, lVar, null, caVar, null, null, false, false, 3904, null);
        c.f.b.k.b(obj, "body");
        c.f.b.k.b(str, "url");
        c.f.b.k.b(str2, "method");
        c.f.b.k.b(cls, "resultClass");
        c.f.b.k.b(cls2, "errorClass");
        this.f7888e = obj;
        this.f7886c = c.f.a(new a());
        this.f7887d = c.f.a(new b());
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return e.this.f7888e instanceof String ? (String) e.this.f7888e : com.joaomgcd.taskerm.n.b.a().a(e.this.f7888e);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<o<e<TResult, TResultError>, TResult, TResultError>> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final o<e<TResult, TResultError>, TResult, TResultError> invoke() {
            return new o<>(e.this);
        }
    }
}
