package com.joaomgcd.taskerm.retrofit;

import b.a.l;
import c.f.b.v;
import c.f.b.x;
import d.t;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8360a = {x.a(new v(x.a(d.class), "client", "getClient()Ljava/lang/Object;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f8361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Class<T> f8362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f8363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final l<HashMap<String, String>> f8364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final t[] f8365f;
    private final k g;

    public final T a() {
        c.e eVar = this.f8361b;
        c.j.g gVar = f8360a[0];
        return (T) eVar.b();
    }

    public d(Class<T> cls, String str, l<HashMap<String, String>> lVar, t[] tVarArr, k kVar) {
        c.f.b.k.b(cls, "apiClass");
        c.f.b.k.b(str, "baseUrl");
        c.f.b.k.b(tVarArr, "interceptors");
        c.f.b.k.b(kVar, "timeout");
        this.f8362c = cls;
        this.f8363d = str;
        this.f8364e = lVar;
        this.f8365f = tVarArr;
        this.g = kVar;
        this.f8361b = c.f.a(new a());
    }

    public final Class<T> c() {
        return this.f8362c;
    }

    public final String d() {
        return this.f8363d;
    }

    public final l<HashMap<String, String>> e() {
        return this.f8364e;
    }

    public final t[] f() {
        return this.f8365f;
    }

    public final k g() {
        return this.g;
    }

    static final class a extends c.f.b.l implements c.f.a.a<T> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public final T invoke() {
            return (T) f.b(d.this);
        }
    }

    public final b b() {
        return new b(this);
    }
}
