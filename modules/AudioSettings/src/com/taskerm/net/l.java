package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.net.c;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ca;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
public class l<TArgs extends com.joaomgcd.taskerm.net.c<TResult, TResultError>, TResult, TResultError extends com.joaomgcd.taskerm.util.ab> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7906a = {c.f.b.x.a(new c.f.b.t(c.f.b.x.a(l.class), "responseHeadersLocal", "<v#0>"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f7907b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final cl f7908c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TArgs f7909d;

    public l(TArgs targs) {
        c.f.b.k.b(targs, "args");
        this.f7909d = targs;
        this.f7908c = new co();
    }

    public final TArgs b() {
        return this.f7909d;
    }

    protected cl a() {
        return this.f7908c;
    }

    public static /* synthetic */ Object a(l lVar, k kVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: doHttpSync");
        }
        if ((i & 1) != 0) {
            kVar = (k) null;
        }
        return lVar.a(kVar);
    }

    public TResult a(k kVar) {
        cl clVarA = a();
        if (clVarA instanceof cm) {
            throw new RuntimeException(((cm) clVarA).c());
        }
        HashMap<String, String> mapJ = this.f7909d.j();
        if (mapJ == null) {
            mapJ = new HashMap<>();
        }
        URL urlA = ab.a(mapJ, new URL(this.f7909d.d()));
        this.f7909d.a(mapJ);
        boolean z = c.l.n.a(this.f7909d.d(), "https://", false, 2, (Object) null) && this.f7909d.l();
        return (TResult) ab.a(z, new c(urlA, this.f7909d.b(), z, kVar));
    }

    static final class c extends c.f.b.l implements c.f.a.a<TResult> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ URL f7913b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f7914c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f7915d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ k f7916e;

        static final class a implements HostnameVerifier {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final a f7919a = new a();

            a() {
            }

            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(URL url, boolean z, boolean z2, k kVar) {
            super(0);
            this.f7913b = url;
            this.f7914c = z;
            this.f7915d = z2;
            this.f7916e = kVar;
        }

        @Override // c.f.a.a
        public final TResult invoke() throws IOException {
            String errorMessage;
            String lowerCase;
            GZIPInputStream inputStream;
            String strB;
            URLConnection uRLConnectionOpenConnection = this.f7913b.openConnection();
            if (uRLConnectionOpenConnection == null) {
                throw new c.q("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod(l.this.b().e());
            b.a.l<String> lVarG = l.this.b().g();
            if (lVarG != null && (strB = lVarG.b()) != null) {
                c.k<String, String> kVarA = ab.a(strB);
                httpURLConnection.setRequestProperty(kVarA.a(), kVarA.b());
            }
            if (this.f7914c) {
                h hVarA = ab.a(l.this.b().a("cookies"), l.this.b().d());
                if (!hVarA.isEmpty()) {
                    httpURLConnection.setRequestProperty("Cookie", hVarA.toString());
                }
            }
            HashMap<String, String> mapJ = l.this.b().j();
            if (mapJ != null) {
                for (c.k kVar : c.a.z.d(mapJ)) {
                    httpURLConnection.setRequestProperty((String) kVar.a(), (String) kVar.b());
                }
            }
            c.f.a.b<HttpURLConnection, c.s> bVarK = l.this.b().k();
            if (bVarK != null) {
                bVarK.invoke(httpURLConnection);
            }
            if (this.f7915d) {
                if (httpURLConnection == null) {
                    throw new c.q("null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
                }
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(a.f7919a);
            }
            l.this.a(httpURLConnection);
            InputStream inputStream2 = (InputStream) null;
            try {
                try {
                    ca caVarI = l.this.b().i();
                    Long lValueOf = (caVarI == null && this.f7916e == null) ? null : Long.valueOf(ab.a(httpURLConnection));
                    if (caVarI != null) {
                        k kVar2 = this.f7916e;
                        if (kVar2 != null) {
                            kVar2.a(lValueOf);
                        }
                        if (lValueOf == null || lValueOf.longValue() != 0) {
                            caVarI.a(lValueOf);
                            caVarI.b(0L);
                        }
                    }
                    String contentEncoding = httpURLConnection.getContentEncoding();
                    if (contentEncoding == null) {
                        lowerCase = null;
                    } else {
                        if (contentEncoding == null) {
                            throw new c.q("null cannot be cast to non-null type java.lang.String");
                        }
                        lowerCase = contentEncoding.toLowerCase();
                        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                    }
                    if (c.f.b.k.a((Object) lowerCase, (Object) "gzip")) {
                        inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                    } else {
                        inputStream = httpURLConnection.getInputStream();
                        c.f.b.k.a((Object) inputStream, "urlConnection.inputStream");
                    }
                    InputStream inputStream3 = inputStream;
                    String contentType = httpURLConnection.getContentType();
                    if (contentType == null) {
                        HashMap<String, String> mapJ2 = l.this.b().j();
                        contentType = mapJ2 != null ? mapJ2.get(ab.b()) : null;
                    }
                    if (contentType == null) {
                        contentType = "application/octet-stream";
                    }
                    TResult tresultA = l.this.b().h().a(inputStream3, caVarI, contentType, ab.c(httpURLConnection));
                    c.e eVarA = c.f.a(new C0201c(httpURLConnection));
                    c.j.g gVar = l.f7906a[0];
                    AnonymousClass1 anonymousClass1 = AnonymousClass1.f7917a;
                    k kVar3 = this.f7916e;
                    if (kVar3 != null) {
                        kVar3.a(Integer.valueOf(httpURLConnection.getResponseCode()));
                        kVar3.a((Map<String, String>) eVarA.b());
                        kVar3.a(lValueOf);
                    }
                    if (this.f7914c) {
                        l.this.b().a(httpURLConnection.getHeaderFields().get("Set-Cookie"));
                    }
                    k kVar4 = this.f7916e;
                    if (kVar4 != null) {
                        kVar4.a(l.this.b().c());
                    }
                    String strInvoke = anonymousClass1.invoke((Map) eVarA.b(), "Location");
                    if (strInvoke != null && c.l.n.a(strInvoke, "/", false, 2, (Object) null)) {
                        strInvoke = this.f7913b.getProtocol() + "://" + this.f7913b.getHost() + strInvoke;
                    }
                    if (l.this.b().m() && strInvoke != null) {
                        l.this.b().b(strInvoke);
                        l.this.b().c("GET");
                        TResult tresult = (TResult) l.this.a(this.f7916e);
                        ca caVarI2 = l.this.b().i();
                        if (caVarI2 != null) {
                            caVarI2.c();
                        }
                        if (!l.this.b().h().a() && inputStream3 != null) {
                            inputStream3.close();
                        }
                        return tresult;
                    }
                    ca caVarI3 = l.this.b().i();
                    if (caVarI3 != null) {
                        caVarI3.c();
                    }
                    if (!l.this.b().h().a() && inputStream3 != null) {
                        inputStream3.close();
                    }
                    return tresultA;
                } catch (Exception e2) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                    k kVar5 = this.f7916e;
                    if (kVar5 != null) {
                        kVar5.a(Integer.valueOf(httpURLConnection.getResponseCode()));
                        kVar5.a(ab.d(httpURLConnection));
                    }
                    if (httpURLConnection.getErrorStream() == null) {
                        anonymousClass2.invoke(e2);
                    }
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    c.f.b.k.a((Object) errorStream, "urlConnection.errorStream");
                    cq<?, ?> cqVarB = aj.b(errorStream, l.this.b().f());
                    if (!cqVarB.b()) {
                        com.joaomgcd.taskerm.util.ab abVarE = cqVarB.e();
                        if (abVarE == null || (errorMessage = abVarE.getErrorMessage()) == null) {
                            errorMessage = "Unknown error";
                        }
                        anonymousClass2.invoke(new RuntimeException(errorMessage));
                    }
                    Object objD = cqVarB.d();
                    if (objD == null) {
                        objD = anonymousClass2.invoke(new RuntimeException("Unknown error"));
                    }
                    i iVar = new i(objD, b.f7920a);
                    k kVar6 = this.f7916e;
                    if (kVar6 != null) {
                        kVar6.a(Integer.valueOf(httpURLConnection.getResponseCode()));
                        kVar6.a(ab.d(httpURLConnection));
                        String message = iVar.getMessage();
                        kVar6.a(Long.valueOf(message != null ? message.length() : 0L));
                    }
                    throw anonymousClass2.invoke(iVar);
                }
            } catch (Throwable th) {
                ca caVarI4 = l.this.b().i();
                if (caVarI4 != null) {
                    caVarI4.c();
                }
                if (!l.this.b().h().a() && inputStream2 != null) {
                    inputStream2.close();
                }
                throw th;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.net.l$c$c, reason: collision with other inner class name */
        static final class C0201c extends c.f.b.l implements c.f.a.a<Map<String, ? extends String>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ HttpURLConnection f7921a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0201c(HttpURLConnection httpURLConnection) {
                super(0);
                this.f7921a = httpURLConnection;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Map<String, String> invoke() {
                return ab.d(this.f7921a);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.net.l$c$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<Map<String, ? extends String>, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f7917a = new AnonymousClass1();

            AnonymousClass1() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Map<String, String> map, String str) {
                Object next;
                c.f.b.k.b(map, "receiver$0");
                c.f.b.k.b(str, "key");
                String lowerCase = str.toLowerCase();
                c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                Iterator<T> it = map.keySet().iterator();
                while (it.hasNext()) {
                    next = it.next();
                    String str2 = (String) next;
                    if (str2 == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase2 = str2.toLowerCase();
                    c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                    if (c.f.b.k.a((Object) lowerCase2, (Object) lowerCase)) {
                        return map.get((String) next);
                    }
                }
                next = null;
                return map.get((String) next);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.net.l$c$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<Exception, Exception> {
            AnonymousClass2() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Exception invoke(Exception exc) throws Exception {
                c.f.b.k.b(exc, "receiver$0");
                k kVar = c.this.f7916e;
                if (kVar != null) {
                    String message = exc.getMessage();
                    kVar.a(Long.valueOf(message != null ? message.length() : 0L));
                }
                throw exc;
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<Object, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final b f7920a = new b();

            b() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Object obj) {
                c.f.b.k.b(obj, "it");
                return ((com.joaomgcd.taskerm.util.ab) obj).getErrorMessage();
            }
        }
    }

    public void a(HttpURLConnection httpURLConnection) {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        ca caVarI = this.f7909d.i();
        if (caVarI != null) {
            caVarI.b(0L);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<TResult> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ k f7911b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(k kVar) {
            super(0);
            this.f7911b = kVar;
        }

        @Override // c.f.a.a
        public final TResult invoke() {
            return (TResult) l.this.a(this.f7911b);
        }
    }

    public static /* synthetic */ b.a.l b(l lVar, k kVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: doHttp");
        }
        if ((i & 1) != 0) {
            kVar = (k) null;
        }
        return lVar.b(kVar);
    }

    public final b.a.l<TResult> b(k kVar) {
        return com.joaomgcd.taskerm.rx.i.b(new b(kVar));
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
