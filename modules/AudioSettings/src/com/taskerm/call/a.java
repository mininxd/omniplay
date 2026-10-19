package com.joaomgcd.taskerm.call;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import c.f.b.l;
import c.f.b.v;
import c.f.b.w;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.ay;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6016a = {x.a(new v(x.a(a.class), "phoneStatePublisher", "getPhoneStatePublisher()Lio/reactivex/subjects/PublishSubject;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6017b = c.f.a(k.f6029a);

    private final b.a.k.b<C0117a> a() {
        c.e eVar = this.f6017b;
        c.j.g gVar = f6016a[0];
        return (b.a.k.b) eVar.b();
    }

    static final class k extends l implements c.f.a.a<b.a.k.b<C0117a>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final k f6029a = new k();

        k() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<C0117a> invoke() {
            return b.a.k.b.j();
        }
    }

    public final b.a.h<C0117a> a(Context context, ay<?> ayVar, c.f.a.b<? super b.a.h<?>, s> bVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(ayVar, "handler");
        c.f.b.k.b(bVar, "subscribe");
        w.a aVar = new w.a();
        aVar.f2025a = false;
        b.a.k.b<C0117a> bVarA = a();
        c.f.b.k.a((Object) bVarA, "phoneStatePublisher");
        b.a.h hVarC = com.joaomgcd.taskerm.rx.i.c(bVarA, ayVar);
        b.a.h hVarA = hVarC.a((b.a.d.i) e.f6023a);
        b.a.h hVarA2 = hVarC.a((b.a.d.i) f.f6024a);
        b.a.h hVarB = hVarA2.a((b.a.d.i) i.f6027a).b((b.a.d.f) new j(aVar));
        b.a.h hVarC2 = hVarA2.a((b.a.d.i) g.f6025a).c(1L, TimeUnit.SECONDS);
        c.f.b.k.a((Object) hVarC2, "ringing.filter { !it.has…unce(1, TimeUnit.SECONDS)");
        b.a.h hVarA3 = b.a.h.a(hVarA, hVarB, com.joaomgcd.taskerm.rx.i.c(hVarC2, ayVar).a((b.a.d.i) new h(aVar)));
        c.f.b.k.a((Object) hVarA3, "Observable.merge(nonRing…hNumber, ringingNoNumber)");
        b.a.h<C0117a> hVarA4 = com.joaomgcd.taskerm.rx.i.b(hVarA3, 3, TimeUnit.SECONDS, c.f6021a).a((b.a.d.f<? super Throwable>) new d(context));
        b.a.h<C0117a> hVarB2 = hVarA4.d(3L, TimeUnit.SECONDS).b(new b(aVar));
        c.f.b.k.a((Object) hVarB2, "merge.delay(3, TimeUnit.…)\n            }\n        }");
        bVar.invoke(hVarB2);
        c.f.b.k.a((Object) hVarA4, "merge");
        return hVarA4;
    }

    static final class e<T> implements b.a.d.i<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f6023a = new e();

        e() {
        }

        @Override // b.a.d.i
        public final boolean a(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            return !c0117a.c();
        }
    }

    static final class f<T> implements b.a.d.i<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f6024a = new f();

        f() {
        }

        @Override // b.a.d.i
        public final boolean a(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            return c0117a.c();
        }
    }

    static final class i<T> implements b.a.d.i<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f6027a = new i();

        i() {
        }

        @Override // b.a.d.i
        public final boolean a(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            return c0117a.a();
        }
    }

    static final class j<T> implements b.a.d.f<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.a f6028a;

        j(w.a aVar) {
            this.f6028a = aVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            this.f6028a.f2025a = true;
            bn.b("IncomingPhoneStateHandler", "Set Got Number");
        }
    }

    static final class g<T> implements b.a.d.i<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f6025a = new g();

        g() {
        }

        @Override // b.a.d.i
        public final boolean a(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            return !c0117a.a();
        }
    }

    static final class h<T> implements b.a.d.i<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.a f6026a;

        h(w.a aVar) {
            this.f6026a = aVar;
        }

        @Override // b.a.d.i
        public final boolean a(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            boolean z = !this.f6026a.f2025a;
            bn.b("IncomingPhoneStateHandler", "No number result: " + z);
            return z;
        }
    }

    static final class c extends l implements c.f.a.b<C0117a, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6021a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(C0117a c0117a) {
            return c0117a.d();
        }
    }

    static final class d<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6022a;

        d(Context context) {
            this.f6022a = context;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            ai.b(th, this.f6022a, "phoneStatePublisher");
        }
    }

    static final class b<T> implements b.a.d.f<C0117a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.a f6020a;

        b(w.a aVar) {
            this.f6020a = aVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(C0117a c0117a) {
            c.f.b.k.b(c0117a, "it");
            if (c0117a.c()) {
                this.f6020a.f2025a = false;
                bn.b("IncomingPhoneStateHandler", "Reset Got Number");
            }
        }
    }

    public final boolean a(String str, Bundle bundle) {
        C0117a c0117a;
        c.f.b.k.b(str, "eventAction");
        if (bundle == null || (!c.f.b.k.a((Object) str, (Object) "android.intent.action.PHONE_STATE"))) {
            return false;
        }
        try {
            c0117a = new C0117a(bundle);
        } catch (Exception e2) {
            bn.b("M", "Error getting phone state: " + e2.getMessage(), e2);
            c0117a = null;
        }
        if (c0117a == null) {
            return false;
        }
        a(c0117a);
        return true;
    }

    public final void a(C0117a c0117a) {
        c.f.b.k.b(c0117a, "phoneState");
        a().b_(c0117a);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.call.a$a, reason: collision with other inner class name */
    public static final class C0117a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f6018a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f6019b;

        public C0117a(String str, String str2) {
            c.f.b.k.b(str, "state");
            this.f6018a = str;
            this.f6019b = str2;
        }

        public final String d() {
            return this.f6018a;
        }

        public final String e() {
            return this.f6019b;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public C0117a(Bundle bundle) {
            c.f.b.k.b(bundle, "eventExtras");
            String string = bundle.getString("state");
            if (string != null) {
                this(string, bundle.getString("incoming_number"));
                return;
            }
            throw new RuntimeException("PhoneState must have state variable");
        }

        public final boolean a() {
            return ak.z(this.f6019b);
        }

        public final Integer b() {
            String str = this.f6018a;
            if (c.f.b.k.a((Object) str, (Object) TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                return 2;
            }
            if (c.f.b.k.a((Object) str, (Object) TelephonyManager.EXTRA_STATE_IDLE)) {
                return 4;
            }
            return c.f.b.k.a((Object) str, (Object) TelephonyManager.EXTRA_STATE_RINGING) ? 6 : null;
        }

        public final boolean c() {
            return c.f.b.k.a((Object) this.f6018a, (Object) TelephonyManager.EXTRA_STATE_RINGING);
        }
    }
}
