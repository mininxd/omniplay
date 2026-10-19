package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import android.content.SharedPreferences;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean b(com.joaomgcd.taskerm.securesettings.r rVar, Context context) {
        b.a.l<Boolean> lVarA;
        if (rVar.h().length() == 0) {
            lVarA = com.joaomgcd.taskerm.securesettings.aa.b(context, rVar);
            b(context, rVar.h());
        } else {
            lVarA = com.joaomgcd.taskerm.securesettings.aa.a(context, rVar);
        }
        return lVarA.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(Context context, ay ayVar) {
        com.joaomgcd.taskerm.q.b.a(context, "LAST_NAVIGATION_BAR", ayVar, (SharedPreferences) null, 4, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(Context context, ay ayVar) {
        bn.b("NavigationBar", "Applying Navigation bar input: " + com.joaomgcd.taskerm.util.aj.a(ayVar));
        b(new j().a(context, new com.joaomgcd.taskerm.navigationbar.k(context).a(ayVar.getNavigationBarValue(), new a(fx.c(context)))), context);
    }

    static final class a extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fy f5481a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(fy fyVar) {
            super(1);
            this.f5481a = fyVar;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            fy fyVar = this.f5481a;
            c.f.b.k.a((Object) fyVar, "taskerData");
            return com.joaomgcd.taskerm.util.aj.a(fyVar, str);
        }
    }

    public static final void a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        com.joaomgcd.taskerm.rx.i.c(new b(context));
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5482a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context) {
            super(0);
            this.f5482a = context;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                ay ayVarC = k.c(this.f5482a);
                if (ayVarC != null) {
                    k.d(this.f5482a, new ay(null, null, null, 7, null));
                    Thread.sleep(1000L);
                    k.d(this.f5482a, ayVarC);
                }
            } catch (Exception e2) {
                bn.a("NavigationBar", "Can't apply last navigation bar input", e2);
            }
        }
    }

    private static final com.joaomgcd.taskerm.securesettings.f a() {
        return new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Global, "navigationbar_hide_bar", false, 0, 0, 28, null);
    }

    private static final com.joaomgcd.taskerm.securesettings.r a(boolean z) {
        return new com.joaomgcd.taskerm.securesettings.r(a(), z ? "1" : "0");
    }

    private static final com.joaomgcd.taskerm.securesettings.f b() {
        return new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Global, "navigationbar_key_order", false, 0, 0, 28, null);
    }

    private static final com.joaomgcd.taskerm.securesettings.r b(boolean z) {
        return new com.joaomgcd.taskerm.securesettings.r(b(), z ? "1" : "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Context context, String str) {
        try {
            String strB = com.joaomgcd.taskerm.securesettings.aa.b(context, b()).b();
            String str2 = strB;
            if (str2 == null || str2.length() == 0) {
                return;
            }
            com.joaomgcd.taskerm.securesettings.aa.a(context, a(!c.l.n.b((CharSequence) str, (CharSequence) "pin[0.0000001]", false, 2, (Object) null))).b();
            if (!c.f.b.k.a((Object) str, (Object) "")) {
                return;
            }
            boolean zA = c.f.b.k.a((Object) strB, (Object) "1");
            com.joaomgcd.taskerm.securesettings.aa.a(context, b(!zA)).b();
            com.joaomgcd.taskerm.securesettings.aa.a(context, b(zA)).b();
        } catch (Exception e2) {
            bn.a("NavigationBar", "Couldn't toggle bar order", (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ay c(Context context) {
        SharedPreferences sharedPreferencesE = com.joaomgcd.taskerm.q.b.e(context);
        c.f.b.k.a((Object) sharedPreferencesE, "preferences");
        String string = sharedPreferencesE.getString("LAST_NAVIGATION_BAR", null);
        return (ay) (string != null ? com.joaomgcd.taskerm.n.b.a().a(string, ay.class) : null);
    }
}
