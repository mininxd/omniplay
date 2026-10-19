package com.joaomgcd.taskerm.net;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.ca;
import com.joaomgcd.taskerm.util.cu;
import com.joaomgcd.taskerm.util.db;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c<TResult, TResultError extends com.joaomgcd.taskerm.util.ab> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7878a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(c.class), "http", "getHttp()Lcom/joaomgcd/taskerm/net/Http;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f7880c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f7881d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f7882e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f7883f;
    private final Class<TResult> g;
    private final Class<TResultError> h;
    private final TResult i;
    private final b.a.l<String> j;
    private final cu<TResult> k;
    private final ca l;
    private HashMap<String, String> m;
    private c.f.a.b<? super HttpURLConnection, c.s> n;
    private boolean o;
    private boolean p;

    public l<?, TResult, TResultError> a() {
        c.e eVar = this.f7879b;
        c.j.g gVar = f7878a[0];
        return (l) eVar.b();
    }

    public c(String str, String str2, Class<TResult> cls, Class<TResultError> cls2, TResult tresult, b.a.l<String> lVar, cu<TResult> cuVar, ca caVar, HashMap<String, String> map, c.f.a.b<? super HttpURLConnection, c.s> bVar, boolean z, boolean z2) {
        c.f.b.k.b(str, "url");
        c.f.b.k.b(str2, "method");
        c.f.b.k.b(cls, "resultClass");
        c.f.b.k.b(cls2, "errorClass");
        c.f.b.k.b(cuVar, "resultConverter");
        this.f7882e = str;
        this.f7883f = str2;
        this.g = cls;
        this.h = cls2;
        this.i = tresult;
        this.j = lVar;
        this.k = cuVar;
        this.l = caVar;
        this.m = map;
        this.n = bVar;
        this.o = z;
        this.p = z2;
        this.f7879b = c.f.a(new a());
    }

    public final void b(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.f7882e = str;
    }

    public final String d() {
        return this.f7882e;
    }

    public final void c(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.f7883f = str;
    }

    public final String e() {
        return this.f7883f;
    }

    public final Class<TResultError> f() {
        return this.h;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ c(String str, String str2, Class cls, Class cls2, Object obj, b.a.l lVar, cu cuVar, ca caVar, HashMap map, c.f.a.b bVar, boolean z, boolean z2, int i, c.f.b.g gVar) {
        Object obj2 = (i & 16) != 0 ? null : obj;
        this(str, str2, cls, cls2, obj2, (i & 32) != 0 ? (b.a.l) null : lVar, (i & 64) != 0 ? db.a(cls, obj2) : cuVar, (i & 128) != 0 ? (ca) null : caVar, (i & 256) != 0 ? (HashMap) null : map, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (c.f.a.b) null : bVar, (i & 1024) != 0 ? false : z, (i & 2048) != 0 ? false : z2);
    }

    public final b.a.l<String> g() {
        return this.j;
    }

    public final cu<TResult> h() {
        return this.k;
    }

    public final ca i() {
        return this.l;
    }

    public final void a(HashMap<String, String> map) {
        this.m = map;
    }

    public final HashMap<String, String> j() {
        return this.m;
    }

    public final void a(c.f.a.b<? super HttpURLConnection, c.s> bVar) {
        this.n = bVar;
    }

    public final c.f.a.b<HttpURLConnection, c.s> k() {
        return this.n;
    }

    public final void a(boolean z) {
        this.o = z;
    }

    public final boolean l() {
        return this.o;
    }

    public final void b(boolean z) {
        this.p = z;
    }

    public final boolean m() {
        return this.p;
    }

    static final class a extends c.f.b.l implements c.f.a.a<l<c<TResult, TResultError>, TResult, TResultError>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<c<TResult, TResultError>, TResult, TResultError> invoke() {
            return new l<>(c.this);
        }
    }

    public final b.a.l<TResult> a(k kVar) {
        return a().b(kVar);
    }

    public final Context a(String str) {
        c.f.b.k.b(str, "useFor");
        Context context = this.f7881d;
        if (context != null) {
            return context;
        }
        throw new RuntimeException("Using " + str + " requires a context");
    }

    public final boolean b() {
        return this.f7880c;
    }

    public final void a(Context context, boolean z) {
        c.f.b.k.b(context, "context");
        this.f7881d = context;
        this.f7880c = z;
    }

    public final h c() {
        Context context = this.f7881d;
        if (context != null) {
            return ab.a(context, this.f7882e);
        }
        return null;
    }

    public final h a(List<String> list) {
        Context context = this.f7881d;
        if (context != null) {
            return ab.a(context, this.f7882e, list);
        }
        return null;
    }
}
