package com.joaomgcd.taskerm.o;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import b.a.l;
import c.f.b.k;
import c.f.b.p;
import c.f.b.v;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.notification.ak;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.TaskerApp;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8096a = {x.a(new v(x.a(c.class), "policy", "getPolicy()Lcom/google/android/vending/licensing/ServerManagedPolicy;")), x.a(new v(x.a(c.class), "licenseChecker", "getLicenseChecker()Lcom/google/android/vending/licensing/LicenseChecker;")), x.a(new v(x.a(c.class), "licenseCheckerCallback", "getLicenseCheckerCallback()Lcom/joaomgcd/taskerm/licensing/LicenceCheckerCallbackTasker;")), x.a(new v(x.a(c.class), "publisher", "getPublisher()Lio/reactivex/subjects/PublishSubject;")), x.a(new p(x.a(c.class), "directRetries", "getDirectRetries()I")), x.a(new p(x.a(c.class), "directLastState", "getDirectLastState()I"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f8097b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8099d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f8100e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f8101f;
    private final com.joaomgcd.taskerm.q.e g;
    private final com.joaomgcd.taskerm.q.e h;
    private cq<com.joaomgcd.taskerm.o.h, ad> i;
    private final Context j;
    private final String k;

    public static final l<b> a(Context context) {
        return a.a(f8097b, context, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.google.android.vending.licensing.l f() {
        c.e eVar = this.f8098c;
        c.j.g gVar = f8096a[0];
        return (com.google.android.vending.licensing.l) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.google.android.vending.licensing.d g() {
        c.e eVar = this.f8099d;
        c.j.g gVar = f8096a[1];
        return (com.google.android.vending.licensing.d) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.o.b h() {
        c.e eVar = this.f8100e;
        c.j.g gVar = f8096a[2];
        return (com.joaomgcd.taskerm.o.b) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.k.b<cq<com.joaomgcd.taskerm.o.e, ad>> i() {
        c.e eVar = this.f8101f;
        c.j.g gVar = f8096a[3];
        return (b.a.k.b) eVar.b();
    }

    public final l<cq<com.joaomgcd.taskerm.o.h, ad>> a(boolean z) {
        return a(this, z, false, 2, null);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.o.c$c, reason: collision with other inner class name */
    static final class C0209c extends c.f.b.l implements c.f.a.a<s> {
        C0209c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            dm.b(1L);
            c.this.g().a(c.this.h());
        }
    }

    public c(Context context, String str) {
        k.b(context, "context");
        k.b(str, "publicKey");
        this.j = context;
        this.k = str;
        this.f8098c = c.f.a(new h());
        this.f8099d = c.f.a(new f());
        this.f8100e = c.f.a(new g());
        this.f8101f = c.f.a(i.f8114a);
        this.g = new com.joaomgcd.taskerm.q.e(this.j, 0, null, "raj1", 6, null);
        this.h = new com.joaomgcd.taskerm.q.e(this.j, 561, null, "pl33", 4, null);
    }

    public final Context d() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"HardwareIds"})
    public final String e() {
        return Settings.Secure.getString(this.j.getContentResolver(), "android_id");
    }

    static final class h extends c.f.b.l implements c.f.a.a<com.google.android.vending.licensing.l> {
        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.google.android.vending.licensing.l invoke() {
            return new com.google.android.vending.licensing.l(c.this.d(), new com.google.android.vending.licensing.a(com.joaomgcd.taskerm.o.d.f8115a, c.this.d().getPackageName(), c.this.e()));
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<com.google.android.vending.licensing.d> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.google.android.vending.licensing.d invoke() {
            return new com.google.android.vending.licensing.d(c.this.d(), c.this.f(), c.this.k);
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.o.b> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.o.b invoke() {
            b.a.k.b bVarI = c.this.i();
            k.a((Object) bVarI, "publisher");
            return new com.joaomgcd.taskerm.o.b(bVarI);
        }
    }

    static final class i extends c.f.b.l implements c.f.a.a<b.a.k.b<cq<com.joaomgcd.taskerm.o.e, ad>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f8114a = new i();

        i() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<cq<com.joaomgcd.taskerm.o.e, ad>> invoke() {
            return b.a.k.b.j();
        }
    }

    private final long j() {
        return System.currentTimeMillis();
    }

    public final l<cq<com.joaomgcd.taskerm.o.h, ad>> a() {
        cq<com.joaomgcd.taskerm.o.h, ad> cqVar = this.i;
        if (cqVar != null) {
            l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarA = l.a(cqVar);
            k.a((Object) lVarA, "Single.just(statusCache)");
            return lVarA;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jA = g().a();
        if (jCurrentTimeMillis < jA) {
            bn.b("LicenseCheckerTasker", "Couldn't use license cache but validating license because validity timestamp is " + jA + " and now is " + jCurrentTimeMillis);
            cq<com.joaomgcd.taskerm.o.h, ad> cqVar2 = new cq<>(true, com.joaomgcd.taskerm.o.h.Licensed, null, 4, null);
            this.i = cqVar2;
            l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarA2 = l.a(cqVar2);
            k.a((Object) lVarA2, "Single.just(status)");
            return lVarA2;
        }
        return a(this, false, false, 2, null);
    }

    public static /* synthetic */ l a(c cVar, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return cVar.a(z, z2);
    }

    public final l<cq<com.joaomgcd.taskerm.o.h, ad>> a(boolean z, boolean z2) {
        cq<com.joaomgcd.taskerm.o.h, ad> cqVar;
        if (!z2 && (cqVar = this.i) != null) {
            l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarA = l.a(cqVar);
            k.a((Object) lVarA, "Single.just(statusCache)");
            return lVarA;
        }
        if (ah.j(this.j)) {
            return k();
        }
        if (z && (j() - ah.h(this.j)) / 86400000 <= 7) {
            l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarA2 = l.a(cn.a(com.joaomgcd.taskerm.o.h.Licensed));
            k.a((Object) lVarA2, "Single.just(SimpleResult…(LicenseStatus.Licensed))");
            return lVarA2;
        }
        com.joaomgcd.taskerm.rx.i.c(new C0209c());
        l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarD = i().a(d.f8109a).b(new e()).c(1L).d();
        k.a((Object) lVarD, "publisher.flatMap {\n    … }.take(1).firstOrError()");
        return lVarD;
    }

    static final class d<T, R> implements b.a.d.g<T, b.a.i<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f8109a = new d();

        d() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<cq<com.joaomgcd.taskerm.o.h, ad>> apply(cq<com.joaomgcd.taskerm.o.e, ad> cqVar) {
            k.b(cqVar, "it");
            com.joaomgcd.taskerm.o.e eVarD = cqVar.d();
            if (eVarD == null) {
                return b.a.h.a((Throwable) new com.joaomgcd.taskerm.o.a("Couldn't check license"));
            }
            if (eVarD instanceof com.joaomgcd.taskerm.o.g) {
                com.joaomgcd.taskerm.o.g gVar = (com.joaomgcd.taskerm.o.g) eVarD;
                return b.a.h.a(new cq(gVar.b() == com.joaomgcd.taskerm.o.h.Licensed, gVar.b(), cqVar.e()));
            }
            if (eVarD instanceof com.joaomgcd.taskerm.o.f) {
                return b.a.h.a((Throwable) new com.joaomgcd.taskerm.o.a(((com.joaomgcd.taskerm.o.f) eVarD).b()));
            }
            throw new c.i();
        }
    }

    static final class e<T> implements b.a.d.f<cq<com.joaomgcd.taskerm.o.h, ad>> {
        e() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(cq<com.joaomgcd.taskerm.o.h, ad> cqVar) {
            k.b(cqVar, "it");
            c cVar = c.this;
            if (cqVar.d() == com.joaomgcd.taskerm.o.h.Licensed) {
                ah.a(c.this.d(), Long.valueOf(System.currentTimeMillis()));
            } else {
                cqVar = null;
            }
            cVar.i = cqVar;
        }
    }

    public final l<cl> b() {
        l<cl> lVarA = l.a(new cm("App version doesn't have joaoapps license"));
        k.a((Object) lVarA, "Single.just(SimpleResult… have joaoapps license\"))");
        return lVarA;
    }

    private final l<cq<com.joaomgcd.taskerm.o.h, ad>> k() {
        cq<com.joaomgcd.taskerm.o.h, ad> cqVarA = cn.a(com.joaomgcd.taskerm.o.h.Licensed);
        this.i = cqVarA;
        l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarA = l.a(cqVarA);
        k.a((Object) lVarA, "Single.just(statusCache)");
        return lVarA;
    }

    public final Intent c() {
        Intent intentA = g().a(this.j);
        k.a((Object) intentA, "it");
        String str = intentA.getPackage();
        Uri data = intentA.getData();
        if (str == null && data != null) {
            aj.a(intentA, this.j, data, true);
        }
        return intentA;
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f8106a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Intent f8107b;

        public b(boolean z, Intent intent) {
            this.f8106a = z;
            this.f8107b = intent;
        }

        public /* synthetic */ b(boolean z, Intent intent, int i, c.f.b.g gVar) {
            this(z, (i & 2) != 0 ? (Intent) null : intent);
        }

        public final boolean a() {
            return this.f8106a;
        }

        public final Intent b() {
            return this.f8107b;
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public static /* synthetic */ l a(a aVar, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            return aVar.a(context, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final l<b> a(Context context, boolean z) {
            l<cq<com.joaomgcd.taskerm.o.h, ad>> lVarA;
            k.b(context, "context");
            TaskerApp taskerAppC = ah.c(context);
            boolean z2 = true;
            if (ah.j(taskerAppC)) {
                l<b> lVarA2 = l.a(new b(z2, null, 2, 0 == true ? 1 : 0));
                k.a((Object) lVarA2, "Single.just(IsLicensedResult(true))");
                return lVarA2;
            }
            if (z) {
                lVarA = taskerAppC.f().a();
            } else {
                lVarA = taskerAppC.f().a(false, true);
            }
            l lVarC = lVarA.b(new C0207a(context, taskerAppC)).d(b.f8104a).c(new C0208c(taskerAppC));
            k.a((Object) lVarC, "status.doOnSuccess { lic…nt)\n                    }");
            return lVarC;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.o.c$a$a, reason: collision with other inner class name */
        static final class C0207a<T> implements b.a.d.f<cq<com.joaomgcd.taskerm.o.h, ad>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f8102a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ TaskerApp f8103b;

            C0207a(Context context, TaskerApp taskerApp) {
                this.f8102a = context;
                this.f8103b = taskerApp;
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(cq<com.joaomgcd.taskerm.o.h, ad> cqVar) {
                String errorMessage;
                k.b(cqVar, "licenseStatus");
                if (cqVar.d() != com.joaomgcd.taskerm.o.h.Licensed) {
                    String strA = aj.a(R.string.licence_feature_not_licensed_notification, this.f8102a, new Object[0]);
                    ad adVar = (ad) cqVar.e();
                    if (adVar != null && (errorMessage = adVar.getErrorMessage()) != null) {
                        strA = strA + "\n\n" + errorMessage;
                    }
                    as asVar = new as(R.drawable.mw_hardware_security);
                    Context context = this.f8102a;
                    Intent intentC = this.f8103b.f().c();
                    k.a((Object) intentC, "applicationContext.licen…hecker.licensingURLIntent");
                    new at(this.f8103b, aj.a(R.string.licence_status_not_licensed, this.f8102a, new Object[0]), strA, null, null, null, false, asVar, null, "notlicensed", null, 0, 0L, new com.joaomgcd.taskerm.notification.c(context, intentC, null, null, null, null, 60, null), false, false, null, null, null, new ak("LicenseCheckerTasker", "License", "License related notifications", 0, null, null, false, null, false, null, null, 2040, null), null, false, false, 7855480, null).c().b();
                }
            }
        }

        static final class b<T, R> implements b.a.d.g<Throwable, b.a.p<? extends cq<com.joaomgcd.taskerm.o.h, ad>>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final b f8104a = new b();

            b() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final l<cq<com.joaomgcd.taskerm.o.h, ad>> apply(Throwable th) {
                k.b(th, "it");
                return l.a(cn.a(com.joaomgcd.taskerm.o.h.Unlicensed));
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.o.c$a$c, reason: collision with other inner class name */
        static final class C0208c<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ TaskerApp f8105a;

            C0208c(TaskerApp taskerApp) {
                this.f8105a = taskerApp;
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b apply(cq<com.joaomgcd.taskerm.o.h, ad> cqVar) {
                k.b(cqVar, "it");
                boolean z = cqVar.d() == com.joaomgcd.taskerm.o.h.Licensed;
                return new b(z, z ? null : this.f8105a.f().c());
            }
        }
    }
}
