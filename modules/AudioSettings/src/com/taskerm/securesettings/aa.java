package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.ArraySet;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8459a = {c.f.b.x.a(new c.f.b.t(c.f.b.x.a(aa.class, "Tasker(5.15.14)_marketNoTrialRelease"), "publicSystemSettings", "getPublicSystemSettings()Ljava/util/HashSet;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f8460b = "=:=toggle=:=";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f8461c = c.f.a(e.f8468a);

    public static final HashSet<String> a() {
        c.e eVar = f8461c;
        c.j.g gVar = f8459a[0];
        return (HashSet) eVar.b();
    }

    private static final void a(com.joaomgcd.taskerm.securesettings.f fVar) {
        if (!com.joaomgcd.taskerm.util.f.f9323b.a(fVar.e()) || !com.joaomgcd.taskerm.util.f.f9323b.b(fVar.f())) {
            throw new com.joaomgcd.taskerm.securesettings.b("Secure Setting not available for this Android Version");
        }
    }

    private static final <T> T a(Context context, com.joaomgcd.taskerm.securesettings.f fVar, c.f.a.b<? super t, ? extends T> bVar) {
        a(fVar);
        return bVar.invoke(k.a(context, fVar.b(), fVar.d(), false));
    }

    public static final b.a.l<Boolean> a(Context context, com.joaomgcd.taskerm.securesettings.f fVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(fVar, "secureSetting");
        return com.joaomgcd.taskerm.rx.i.b(new a(context, fVar));
    }

    static final class a extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8462a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.f f8463b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, com.joaomgcd.taskerm.securesettings.f fVar) {
            super(0);
            this.f8462a = context;
            this.f8463b = fVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke() {
            try {
                String strB = aa.b(this.f8462a, this.f8463b).b();
                Context context = this.f8462a;
                com.joaomgcd.taskerm.securesettings.f fVar = this.f8463b;
                c.f.b.k.a((Object) strB, "value");
                return aa.a(context, new r(fVar, strB)).b();
            } catch (Exception unused) {
                return false;
            }
        }
    }

    private static final b.a.l<Boolean> a(Context context, r rVar, c.f.a.m<? super t, ? super r, ? extends b.a.l<Boolean>> mVar) {
        return (b.a.l) a(context, rVar, new b(mVar, rVar));
    }

    static final class b extends c.f.b.l implements c.f.a.b<t, b.a.l<Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.m f8464a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ r f8465b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c.f.a.m mVar, r rVar) {
            super(1);
            this.f8464a = mVar;
            this.f8465b = rVar;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> invoke(t tVar) {
            c.f.b.k.b(tVar, "receiver$0");
            try {
                return (b.a.l) this.f8464a.invoke(tVar, this.f8465b);
            } catch (SecurityException e2) {
                return com.joaomgcd.taskerm.util.ai.a(e2);
            }
        }
    }

    static final class f extends c.f.b.l implements c.f.a.m<t, r, b.a.l<Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f8469a = new f();

        f() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> invoke(t tVar, r rVar) {
            c.f.b.k.b(tVar, "receiver$0");
            c.f.b.k.b(rVar, "it");
            return tVar.a(rVar.c(), rVar.h());
        }
    }

    public static final b.a.l<Boolean> a(Context context, r rVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(rVar, "secureSetting");
        return a(context, rVar, f.f8469a);
    }

    static final class d extends c.f.b.l implements c.f.a.b<t, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.f f8467a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(com.joaomgcd.taskerm.securesettings.f fVar) {
            super(1);
            this.f8467a = fVar;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(t tVar) {
            c.f.b.k.b(tVar, "receiver$0");
            return tVar.a(this.f8467a.c());
        }
    }

    public static final b.a.l<String> b(Context context, com.joaomgcd.taskerm.securesettings.f fVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(fVar, "secureSetting");
        return (b.a.l) a(context, fVar, new d(fVar));
    }

    static final class c extends c.f.b.l implements c.f.a.m<t, r, b.a.l<Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8466a = new c();

        c() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> invoke(t tVar, r rVar) {
            c.f.b.k.b(tVar, "receiver$0");
            c.f.b.k.b(rVar, "it");
            return tVar.b(rVar.c());
        }
    }

    public static final b.a.l<Boolean> b(Context context, r rVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(rVar, "secureSetting");
        return a(context, rVar, c.f8466a);
    }

    static final class e extends c.f.b.l implements c.f.a.a<HashSet<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f8468a = new e();

        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashSet<String> invoke() {
            try {
                if (Build.VERSION.SDK_INT < 23) {
                    return new HashSet<>();
                }
                Object obj = Settings.System.class.getField("PUBLIC_SETTINGS").get(null);
                if (obj != null) {
                    return new HashSet<>((ArraySet) obj);
                }
                throw new c.q("null cannot be cast to non-null type android.util.ArraySet<kotlin.String>");
            } catch (Throwable unused) {
                return new HashSet<>();
            }
        }
    }
}
