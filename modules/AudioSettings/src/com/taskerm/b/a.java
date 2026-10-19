package com.joaomgcd.taskerm.b;

import android.content.Context;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dk;
import com.joaomgcd.taskerm.util.dm;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5838a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "publicKey", "getPublicKey()Ljava/io/File;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "privateKey", "getPrivateKey()Ljava/io/File;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "base64", "getBase64()Lcom/joaomgcd/taskerm/adblib/AdbBase64;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "crypto", "getCrypto()Lcom/joaomgcd/taskerm/adblib/AdbCrypto;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f5839b = new b(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5840c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f5841d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f5842e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f5843f;
    private final Context g;

    /* JADX INFO: Access modifiers changed from: private */
    public final File c() {
        c.e eVar = this.f5840c;
        c.j.g gVar = f5838a[0];
        return (File) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File d() {
        c.e eVar = this.f5841d;
        c.j.g gVar = f5838a[1];
        return (File) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final v e() {
        c.e eVar = this.f5842e;
        c.j.g gVar = f5838a[2];
        return (v) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final x f() {
        c.e eVar = this.f5843f;
        c.j.g gVar = f5838a[3];
        return (x) eVar.b();
    }

    static final class h extends c.f.b.l implements c.f.a.b<String, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f5856b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.f f5857c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(boolean z, com.joaomgcd.taskerm.securesettings.f fVar) {
            super(1);
            this.f5856b = z;
            this.f5857c = fVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "receiver$0");
            if (this.f5856b) {
                com.joaomgcd.taskerm.securesettings.aa.a(a.this.b(), new com.joaomgcd.taskerm.securesettings.r(this.f5857c, str)).b();
                dm.a(250L);
                if (c.f.b.k.a((Object) str, (Object) "1")) {
                    com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.rx.i.a(new AnonymousClass1()), 3, 1L, TimeUnit.SECONDS, null, 8, null).b();
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.a$h$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<b.a.l<Boolean>> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<Boolean> invoke() {
                return a.this.a().b(2L, TimeUnit.SECONDS).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.b.a.h.1.1
                    @Override // b.a.d.g
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final Boolean apply(Boolean bool) {
                        c.f.b.k.b(bool, "it");
                        if (bool.booleanValue()) {
                            return bool;
                        }
                        throw new RuntimeException("Can't enable debugging");
                    }
                });
            }
        }
    }

    static final class i extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ h f5860a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f5861b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f5862c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(h hVar, c.f.b.w.d dVar, c.f.b.w.d dVar2) {
            super(0);
            this.f5860a = hVar;
            this.f5861b = dVar;
            this.f5862c = dVar2;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a() {
            try {
                this.f5860a.a("0");
                z zVar = (z) this.f5861b.f2028a;
                if (zVar != null) {
                    zVar.close();
                }
                w wVar = (w) this.f5862c.f2028a;
                if (wVar != null) {
                    wVar.close();
                }
            } catch (Throwable unused) {
            }
        }
    }

    static final class m<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final m f5879a = new m();

        m() {
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "it");
            return true;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((String) obj));
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(c.f.b.g gVar) {
            this();
        }

        public final b.a.l<Boolean> a(Context context) {
            c.f.b.k.b(context, "context");
            return new a(context).a();
        }

        public final String a(String str, String str2) {
            c.f.b.k.b(str, "packageName");
            c.f.b.k.b(str2, "className");
            return "cmd package set-home-activity \"" + str + '/' + str2 + '\"';
        }
    }

    public a(Context context) {
        c.f.b.k.b(context, "context");
        this.g = context;
        this.f5840c = c.f.a(new g());
        this.f5841d = c.f.a(new f());
        this.f5842e = c.f.a(d.f5850a);
        this.f5843f = c.f.a(new e());
    }

    public final Context b() {
        return this.g;
    }

    static final class g extends c.f.b.l implements c.f.a.a<File> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final File invoke() {
            return dk.a(a.this.b(), "pub.key", (String) null, 2, (Object) null);
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<File> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final File invoke() {
            return dk.a(a.this.b(), "priv.key", (String) null, 2, (Object) null);
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<v> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f5850a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final v invoke() {
            return new v() { // from class: com.joaomgcd.taskerm.b.a.d.1
                @Override // com.joaomgcd.taskerm.b.v
                public final String a(byte[] bArr) {
                    c.f.b.k.a((Object) bArr, "it");
                    return aj.c(bArr);
                }
            };
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<x> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final x invoke() throws NoSuchAlgorithmException, IOException {
            x xVarA;
            if (a.this.c().exists() && a.this.d().exists()) {
                try {
                    xVarA = x.a(a.this.e(), a.this.d(), a.this.c());
                } catch (Throwable th) {
                    a.this.a("Couldn't read adb crypto from file", th);
                    xVarA = null;
                }
            } else {
                xVarA = null;
            }
            if (xVarA == null) {
                x xVarA2 = x.a(a.this.e());
                c.f.b.k.a((Object) xVarA2, "AdbCrypto.generateAdbKeyPair(base64)");
                xVarA2.a(a.this.d(), a.this.c());
                a.a(a.this, "Generated new adb crypto key pair", (Throwable) null, 1, (Object) null);
                return xVarA2;
            }
            a.a(a.this, "Loaded existing adb crypto key pair", (Throwable) null, 1, (Object) null);
            return xVarA;
        }
    }

    public static /* synthetic */ void a(a aVar, String str, Throwable th, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            th = (Throwable) null;
        }
        aVar.a(str, th);
    }

    public final void a(String str, Throwable th) {
        bn.a("ADB", str, th);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.a$a, reason: collision with other inner class name */
    public static final class C0106a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5844a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5845b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f5846c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f5847d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f5848e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final boolean f5849f;
        private final String g;

        public C0106a(String str, int i, String str2, int i2, boolean z, boolean z2, String str3) {
            c.f.b.k.b(str, "ip");
            c.f.b.k.b(str2, "command");
            this.f5844a = str;
            this.f5845b = i;
            this.f5846c = str2;
            this.f5847d = i2;
            this.f5848e = z;
            this.f5849f = z2;
            this.g = str3;
        }

        public /* synthetic */ C0106a(String str, int i, String str2, int i2, boolean z, boolean z2, String str3, int i3, c.f.b.g gVar) {
            this(str, i, str2, (i3 & 8) != 0 ? 1000 : i2, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? false : z2, (i3 & 64) != 0 ? (String) null : str3);
        }

        public final String a() {
            return this.f5844a;
        }

        public final int b() {
            return this.f5845b;
        }

        public final String c() {
            return this.f5846c;
        }

        public final int d() {
            return this.f5847d;
        }

        public final boolean e() {
            return this.f5848e;
        }

        public final boolean f() {
            return this.f5849f;
        }

        public final String g() {
            return this.g;
        }
    }

    public final b.a.l<Boolean> a() {
        return a(this, "test", 0, 2, (Object) null);
    }

    static final class n<T, R> implements b.a.d.g<Throwable, b.a.p<? extends Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final n f5880a = new n();

        n() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a(false);
        }
    }

    public static /* synthetic */ b.a.l a(a aVar, String str, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1000;
        }
        return aVar.a(str, i2);
    }

    public final b.a.l<Boolean> a(String str, int i2) {
        c.f.b.k.b(str, "command");
        try {
            b.a.l<Boolean> lVarD = b(this, str, i2, false, 0, null, false, 60, null).c(m.f5879a).d(n.f5880a);
            c.f.b.k.a((Object) lVarD, "sendLocalCommand(command…xt { Single.just(false) }");
            return lVarD;
        } catch (Throwable unused) {
            b.a.l<Boolean> lVarA = b.a.l.a(false);
            c.f.b.k.a((Object) lVarA, "Single.just(false)");
            return lVarA;
        }
    }

    static final class l<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final l f5878a = new l();

        l() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> apply(String str) {
            c.f.b.k.b(str, "it");
            return c.l.n.b((CharSequence) str, new String[]{"\n"}, false, 0, 6, (Object) null);
        }
    }

    public static /* synthetic */ b.a.l a(a aVar, String str, int i2, boolean z, int i3, String str2, boolean z2, int i4, Object obj) {
        int i5 = (i4 & 2) != 0 ? 1000 : i2;
        boolean z3 = (i4 & 4) != 0 ? false : z;
        if ((i4 & 8) != 0) {
            i3 = ad.a(aVar.g);
        }
        int i6 = i3;
        if ((i4 & 16) != 0) {
            str2 = "localhost";
        }
        return aVar.a(str, i5, z3, i6, str2, (i4 & 32) != 0 ? false : z2);
    }

    public final b.a.l<List<String>> a(String str, int i2, boolean z, int i3, String str2, boolean z2) {
        c.f.b.k.b(str, "command");
        c.f.b.k.b(str2, "host");
        return b(str, i2, z, i3, str2, z2).c(l.f5878a);
    }

    public static /* synthetic */ b.a.l b(a aVar, String str, int i2, boolean z, int i3, String str2, boolean z2, int i4, Object obj) {
        int i5 = (i4 & 2) != 0 ? 1000 : i2;
        boolean z3 = (i4 & 4) != 0 ? false : z;
        if ((i4 & 8) != 0) {
            i3 = ad.a(aVar.g);
        }
        int i6 = i3;
        if ((i4 & 16) != 0) {
            str2 = "localhost";
        }
        return aVar.b(str, i5, z3, i6, str2, (i4 & 32) != 0 ? false : z2);
    }

    public final b.a.l<String> b(String str, int i2, boolean z, int i3, String str2, boolean z2) {
        c.f.b.k.b(str, "command");
        c.f.b.k.b(str2, "host");
        return a(new C0106a(str2, i3, str, i2, z, z2, null, 64, null));
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, com.joaomgcd.taskerm.b.z] */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.joaomgcd.taskerm.b.w] */
    public final b.a.l<String> a(C0106a c0106a) {
        c.f.b.k.b(c0106a, "args");
        c.f.b.w.d dVar = new c.f.b.w.d();
        dVar.f2028a = (w) 0;
        c.f.b.w.d dVar2 = new c.f.b.w.d();
        dVar2.f2028a = (z) 0;
        com.joaomgcd.taskerm.securesettings.f fVar = new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Global, "adb_enabled", false, 0, 0, 28, null);
        h hVar = new h(c0106a.f() && (c.f.b.k.a((Object) com.joaomgcd.taskerm.securesettings.aa.b(this.g, fVar).b(), (Object) "1") ^ true), fVar);
        i iVar = new i(hVar, dVar2, dVar);
        b.a.l<String> lVarA = com.joaomgcd.taskerm.rx.i.a(new j(c0106a, hVar, dVar, dVar2, iVar)).a((b.a.d.a) new k(iVar));
        c.f.b.k.a((Object) lVarA, "getInBackground {\n      …       finish()\n        }");
        return lVarA;
    }

    static final class j extends c.f.b.l implements c.f.a.a<b.a.l<String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ C0106a f5864b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f5865c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f5866d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f5867e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ i f5868f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(C0106a c0106a, h hVar, c.f.b.w.d dVar, c.f.b.w.d dVar2, i iVar) {
            super(0);
            this.f5864b = c0106a;
            this.f5865c = hVar;
            this.f5866d = dVar;
            this.f5867e = dVar2;
            this.f5868f = iVar;
        }

        /* JADX WARN: Type inference failed for: r0v6, types: [T, com.joaomgcd.taskerm.b.w] */
        /* JADX WARN: Type inference failed for: r0v9, types: [T, com.joaomgcd.taskerm.b.z] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke() throws InterruptedException, IOException {
            String strA = this.f5864b.a();
            int iB = this.f5864b.b();
            String strC = this.f5864b.c();
            ah.b(a.this.b(), 375).c();
            this.f5865c.a("1");
            try {
                ?? A = w.a(new Socket(strA, iB), a.this.f());
                this.f5866d.f2028a = A;
                A.a();
                if (A == 0) {
                    throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.adblib.AdbConnection");
                }
                try {
                    ?? A2 = A.a("shell:");
                    this.f5867e.f2028a = A2;
                    A2.a(strC + '\n');
                    StringBuilder sb = new StringBuilder();
                    final AnonymousClass1 anonymousClass1 = new AnonymousClass1(sb);
                    return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass2(A2, sb, anonymousClass1)).b(this.f5864b.d(), TimeUnit.MILLISECONDS).d(new b.a.d.g<Throwable, b.a.p<? extends String>>() { // from class: com.joaomgcd.taskerm.b.a.j.3
                        @Override // b.a.d.g
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public final b.a.l<String> apply(Throwable th) {
                            c.f.b.k.b(th, "it");
                            return b.a.l.a(anonymousClass1.invoke());
                        }
                    }).a(new b.a.d.a() { // from class: com.joaomgcd.taskerm.b.a.j.4
                        @Override // b.a.d.a
                        public final void run() {
                            j.this.f5868f.a();
                        }
                    });
                } catch (Throwable unused) {
                    throw new c();
                }
            } catch (ConnectException unused2) {
                throw new c();
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.a$j$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ StringBuilder f5870b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(StringBuilder sb) {
                super(0);
                this.f5870b = sb;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String string = this.f5870b.toString();
                c.f.b.k.a((Object) string, "result.toString()");
                String strA = ak.a(ak.a(string, "\r"), "^@");
                return j.this.f5864b.e() ? c.a.j.a(c.a.j.b(c.a.j.b((Iterable) c.l.n.b((CharSequence) strA, new String[]{"\n"}, false, 0, 6, (Object) null), 2), 1), "\n", null, null, 0, null, null, 62, null) : strA;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.a$j$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ z f5872b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ StringBuilder f5873c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f5874d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(z zVar, StringBuilder sb, AnonymousClass1 anonymousClass1) {
                super(0);
                this.f5872b = zVar;
                this.f5873c = sb;
                this.f5874d = anonymousClass1;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String str = (String) null;
                while (true) {
                    try {
                        try {
                            z zVar = this.f5872b;
                            c.f.b.k.a((Object) zVar, "stream");
                            if (!zVar.e()) {
                                byte[] bArrD = this.f5872b.d();
                                c.f.b.k.a((Object) bArrD, "responseBytes");
                                String strG = j.this.f5864b.g();
                                if (strG == null) {
                                    strG = "US-ASCII";
                                }
                                Charset charsetForName = Charset.forName(strG);
                                c.f.b.k.a((Object) charsetForName, "Charset.forName(charsetName)");
                                String str2 = new String(bArrD, charsetForName);
                                this.f5873c.append(str2);
                                if (str != null && c.l.n.b((CharSequence) str2, (CharSequence) str, false, 2, (Object) null)) {
                                    break;
                                }
                                if (str == null) {
                                    String string = this.f5873c.toString();
                                    c.f.b.k.a((Object) string, "result.toString()");
                                    String str3 = string;
                                    int i = 0;
                                    int i2 = 0;
                                    while (true) {
                                        boolean z = true;
                                        if (i >= str3.length()) {
                                            break;
                                        }
                                        if (str3.charAt(i) != '\n') {
                                            z = false;
                                        }
                                        if (z) {
                                            i2++;
                                        }
                                        i++;
                                    }
                                    if (i2 >= 2) {
                                        String string2 = this.f5873c.toString();
                                        c.f.b.k.a((Object) string2, "result.toString()");
                                        str = c.l.n.a(ak.a((String) c.l.n.b((CharSequence) string2, new String[]{"\n"}, false, 0, 6, (Object) null).get(1), "^@"), ":/", (String) null, 2, (Object) null) + ":/ $";
                                        if (c.l.n.b((CharSequence) str2, (CharSequence) ('|' + str), false, 2, (Object) null)) {
                                            break;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            } else {
                                break;
                            }
                        } catch (Throwable th) {
                            c.l.n.a(this.f5873c);
                            this.f5873c.append("Error: " + th.getMessage());
                        }
                    } catch (Throwable th2) {
                        j.this.f5868f.a();
                        throw th2;
                    }
                }
                j.this.f5868f.a();
                return this.f5874d.invoke();
            }
        }
    }

    static final class k implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ i f5877a;

        k(i iVar) {
            this.f5877a = iVar;
        }

        @Override // b.a.d.a
        public final void run() {
            this.f5877a.a();
        }
    }

    public static final class c extends RuntimeException {
        public c() {
            super("Can't connect to ADB Wifi. Check here: https://tasker.joaoapps.com/userguide/en/help/ah_adb_wifi.html");
        }
    }
}
