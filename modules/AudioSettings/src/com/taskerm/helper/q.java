package com.joaomgcd.taskerm.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.ax;
import com.joaomgcd.taskerm.util.ba;
import com.joaomgcd.taskerm.util.bw;
import com.joaomgcd.taskerm.util.cr;
import com.joaomgcd.taskerm.util.dc;
import com.joaomgcd.taskerm.util.dr;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Main;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.dl;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class q extends com.joaomgcd.taskerm.helper.h<Main> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ax<b.a.k.a> f7436b;

    public final boolean a(int i2, c.f.a.a<Boolean> aVar, Runnable runnable) {
        return a(this, i2, aVar, runnable, null, null, 24, null);
    }

    static final class h extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f7450b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(List list) {
            super(0);
            this.f7450b = list;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.q$h$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ga f7452b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(ga gaVar) {
                super(0);
                this.f7452b = gaVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                q.this.c().a(this.f7452b.J(), true);
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        static final class a extends c.f.b.l implements c.f.a.b<ga, com.joaomgcd.taskerm.dialog.w> {
            a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(ga gaVar) {
                c.f.b.k.b(gaVar, "it");
                String strA = gaVar.a(q.this.c());
                c.f.b.k.a((Object) strA, "it.makeTitleText(activity)");
                return new com.joaomgcd.taskerm.dialog.w(strA, null, false, 6, null);
            }
        }

        public final void a() {
            ga gaVar;
            if (this.f7450b.size() == 1) {
                gaVar = (ga) this.f7450b.get(0);
            } else {
                ae aeVar = (ae) com.joaomgcd.taskerm.dialog.k.a(new ad(q.this.c(), R.string.show_linked_profiles, this.f7450b, false, new a(), null, null, null, null, null, null, null, null, null, null, null, 65512, null)).b();
                if (aeVar == null || (gaVar = (ga) aeVar.b()) == null) {
                    return;
                }
            }
            gaVar.ac();
            com.joaomgcd.taskerm.rx.i.e(new AnonymousClass1(gaVar));
        }
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f7437a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7438b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f7439c;

        a(boolean z, int i, int i2) {
            this.f7437a = z;
            this.f7438b = i;
            this.f7439c = i2;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Integer.valueOf(a((com.joaomgcd.taskerm.dialog.x) obj));
        }

        public final int a(com.joaomgcd.taskerm.dialog.x xVar) {
            Integer numC;
            c.f.b.k.b(xVar, "it");
            String strJ = xVar.j();
            int iIntValue = (strJ == null || (numC = c.l.n.c(strJ)) == null) ? 1 : numC.intValue();
            return Math.min(Math.max(1, this.f7437a ? this.f7438b - iIntValue : this.f7438b + iIntValue), this.f7439c - 1);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.v.b f7441b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f7442c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(com.joaomgcd.taskerm.v.b bVar, String str) {
            super(0);
            this.f7441b = bVar;
            this.f7442c = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            com.joaomgcd.taskerm.v.a.f9402a.a(q.this.c(), this.f7442c, this.f7441b);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<c.k<? extends Integer, ? extends dl>, c.s> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(c.k<? extends Integer, ? extends dl> kVar) {
            a(kVar);
            return c.s.f2131a;
        }

        public final void a(c.k<Integer, ? extends dl> kVar) {
            c.f.b.k.b(kVar, "it");
            q.this.c().c(kVar.a().intValue());
            q.this.c().f(q.this.c().j());
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<c.s> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                com.joaomgcd.taskerm.o.c.b bVarB = com.joaomgcd.taskerm.o.c.f8097b.a(q.this.c(), false).b(20L, TimeUnit.SECONDS).b();
                if (bVarB.a()) {
                    return;
                }
                if (!ah.V(q.this.c())) {
                    com.joaomgcd.taskerm.dialog.y.d(q.this.c(), R.string.licence_status_not_licensed, R.string.licence_feature_not_licensed_app).b();
                }
                try {
                    Intent intentB = bVarB.b();
                    if (intentB != null) {
                        ah.a(q.this.c(), new cr(intentB, false, 0, null, 14, null));
                    }
                } catch (Exception e2) {
                    ai.a(e2, q.this.c());
                }
                q.this.c().finish();
            } catch (Exception e3) {
                bn.a("T", "Can't check license", (Throwable) e3);
            }
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Runnable f7447b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(Runnable runnable) {
            super(0);
            this.f7447b = runnable;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            q.this.a("init ended async");
            this.f7447b.run();
        }
    }

    static final class i extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.j f7455b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(com.joaomgcd.taskerm.util.j jVar) {
            super(0);
            this.f7455b = jVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                com.joaomgcd.taskerm.dialog.x xVarA = com.joaomgcd.taskerm.dialog.q.a(new com.joaomgcd.taskerm.dialog.d(q.this.c(), new a(), Integer.valueOf(dr.a((Context) q.this.c(), 200)), null, aj.a(R.string.support_continuous_development, q.this.c()), ak.D(aj.a(R.string.patreon_support, q.this.c(), new Object[0])), aj.a(R.string.button_label_ok, q.this.c()), aj.a(R.string.button_label_cancel, q.this.c()), aj.a(R.string.button_label_stop_reminding, q.this.c()), null, null, 1544, null)).b().a();
                if (xVarA.d()) {
                    com.joaomgcd.taskerm.q.b.b(q.this.t(), this.f7455b);
                    ah.x(q.this.c());
                } else if (xVarA.g()) {
                    com.joaomgcd.taskerm.q.b.b(q.this.t(), this.f7455b);
                    com.joaomgcd.taskerm.dialog.y.a(q.this.c(), aj.a(R.string.support_on_patreon, q.this.c(), new Object[0]), aj.a(R.string.patreon_support_negative_response, q.this.c(), new Object[0]), (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).b();
                }
            } catch (Throwable th) {
                com.joaomgcd.taskerm.rx.i.a(q.this.c(), th);
            }
        }

        static final class a extends c.f.b.l implements c.f.a.a<b.a.l<Bitmap>> {
            a() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<Bitmap> invoke() {
                b.a.l<Bitmap> lVarA = b.a.l.a(BitmapFactory.decodeResource(q.this.c().getResources(), R.drawable.patreon));
                c.f.b.k.a((Object) lVarA, "Single.just(BitmapFactor…ces, R.drawable.patreon))");
                return lVarA;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(Main main) {
        super(main);
        c.f.b.k.b(main, "activity");
        this.f7436b = ba.a(g.f7448a);
    }

    public static /* synthetic */ boolean a(q qVar, int i2, c.f.a.a aVar, Runnable runnable, String str, Boolean bool, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            str = (String) null;
        }
        String str2 = str;
        if ((i3 & 16) != 0) {
            bool = false;
        }
        return qVar.a(i2, (c.f.a.a<Boolean>) aVar, runnable, str2, bool);
    }

    public final boolean a(int i2, c.f.a.a<Boolean> aVar, Runnable runnable, String str, Boolean bool) {
        c.f.b.k.b(aVar, "condition");
        c.f.b.k.b(runnable, "actionOnYes");
        if (!bw.e(t()) || !aVar.invoke().booleanValue()) {
            return false;
        }
        com.joaomgcd.taskerm.util.h jVar = str != null ? new com.joaomgcd.taskerm.util.j(str) : new com.joaomgcd.taskerm.util.k(c(), i2, new Object[0]);
        if (com.joaomgcd.taskerm.q.b.a(c(), jVar)) {
            return false;
        }
        a(com.joaomgcd.taskerm.dialog.y.a(c(), R.string.tip_dialog_title, i2, jVar), new k(runnable, bool, jVar));
        return true;
    }

    static final class k<T> implements b.a.d.f<com.joaomgcd.taskerm.dialog.x> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Runnable f7460b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Boolean f7461c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.h f7462d;

        k(Runnable runnable, Boolean bool, com.joaomgcd.taskerm.util.h hVar) {
            this.f7460b = runnable;
            this.f7461c = bool;
            this.f7462d = hVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "message");
            if (xVar.d()) {
                this.f7460b.run();
                if (c.f.b.k.a((Object) this.f7461c, (Object) true)) {
                    com.joaomgcd.taskerm.q.b.b(q.this.c(), this.f7462d);
                }
            }
        }
    }

    public final void d() {
        a(new e());
    }

    public final b.a.l<Integer> a(int i2, boolean z, int i3) {
        return com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) c(), R.string.how_many_positions, "1", 0, 0, 0, true, 16385, (String) null, 0, (Integer) null, 1848, (c.f.b.g) null)).c(new a(z, i2, i3));
    }

    public final void e() {
        Main mainC = c();
        fx fxVar = c().g;
        c.f.b.k.a((Object) fxVar, "activity.data");
        com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.a(mainC, fxVar, R.string.word_project, (List) null, 8, (Object) null), c(), new c());
    }

    public static final class j extends Handler {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ dl f7458b;

        j(dl dlVar) {
            this.f7458b = dlVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            c.f.b.k.b(message, "m");
            if (message.what != 0) {
                return;
            }
            Bundle data = message.getData();
            c.f.b.k.a((Object) data, "m.getData()");
            String string = data.getString("v");
            if (string != null) {
                this.f7458b.a((com.joaomgcd.taskerm.profile.j) com.joaomgcd.taskerm.n.b.a().a(string, com.joaomgcd.taskerm.profile.j.class));
            }
            q.this.c().f();
        }
    }

    public final void a(int i2, dl dlVar) {
        c.f.b.k.b(dlVar, "project");
        com.joaomgcd.taskerm.profile.a.f8209b.a(c(), new j(dlVar), i2, dlVar).a(c());
    }

    public final void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        c.k<com.joaomgcd.taskerm.v.b, dc> kVarA = com.joaomgcd.taskerm.v.b.f9408f.a(str2);
        com.joaomgcd.taskerm.v.b bVarA = kVarA != null ? kVarA.a() : null;
        if (bVarA == null) {
            com.joaomgcd.taskerm.v.a.f9402a.a(c(), str, (com.joaomgcd.taskerm.v.b) null);
        } else {
            if (com.joaomgcd.taskerm.v.a.f9402a.a(c(), str) == bVarA) {
                return;
            }
            com.joaomgcd.taskerm.dialog.y.a(c(), R.string.an_set_variable_structure_type, new com.joaomgcd.taskerm.util.j(aj.a(R.string.structure_global_variable_help, t(), aj.a(bVarA.a(), t(), new Object[0]))), new b(bVarA, str));
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<b.a.k.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f7448a = new g();

        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.a invoke() {
            return b.a.k.a.g();
        }
    }

    public final void f() {
        a("starting init");
        this.f7436b.a();
    }

    public final void g() {
        a("ending init");
        this.f7436b.b().q_();
    }

    public final void h() {
        if (com.joaomgcd.taskerm.rx.i.c()) {
            throw new IllegalStateException("Can't run waitForInitEnd on UI Thread");
        }
        a("waiting for init to end sync");
        this.f7436b.b().b();
        a("init ended sync");
    }

    public final boolean i() {
        return !this.f7436b.b().h();
    }

    public final void g(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        if (i()) {
            a("waiting for init to end async");
            b.a.k.a aVarB = this.f7436b.b();
            c.f.b.k.a((Object) aVarB, "init.value");
            a(com.joaomgcd.taskerm.rx.i.c(aVarB), new f(runnable));
            return;
        }
        a("don't need to wait for init to end async");
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str) {
        bn.b("T", str);
    }

    public final void j() {
        ah.a(t(), "https://taskernet.com/?public", true, false, true, 4, (Object) null);
    }

    public final void k() {
        ah.a(t(), "https://forum.joaoapps.com/index.php?resources", true, false, true, 4, (Object) null);
    }

    private final fx o() {
        fx fxVar = c().g;
        c.f.b.k.a((Object) fxVar, "activity.data");
        return fxVar;
    }

    static final class d extends c.f.b.l implements c.f.a.b<ga, String> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(ga gaVar) {
            c.f.b.k.b(gaVar, "it");
            return "- " + gaVar.a(q.this.c());
        }
    }

    public final void a(fs fsVar) {
        c.f.b.k.b(fsVar, "task");
        List<ga> listE = aj.e(o(), fsVar);
        List<ga> list = listE;
        if (list == null || list.isEmpty()) {
            ak.a(Integer.valueOf(R.string.task_not_linked_to_any_profiles), c());
        } else {
            a(new h(listE));
        }
    }

    public final void l() {
        List<ga> listB = o().B(c().i());
        c.f.b.k.a((Object) listB, "data.getProfilesInProjec…tivity.curProjectIndex())");
        for (ga gaVar : c.a.j.f((Iterable) listB)) {
            if (!gaVar.O()) {
                gaVar.P();
            }
        }
    }

    @SuppressLint({"CheckResult"})
    public final boolean n() {
        com.joaomgcd.taskerm.util.j jVarI = ak.i("showpatreondialog");
        if (com.joaomgcd.taskerm.q.b.a(t(), jVarI) || ah.i(c()) < 30 || o().y() < 10 || o().n() < 15 || o().A() < 3) {
            return false;
        }
        a(new i(jVarI));
        return true;
    }

    public final boolean a(int[] iArr) {
        c.f.b.k.b(iArr, "ids");
        for (int i2 : iArr) {
            List<ga> listA = aj.a(o(), Integer.valueOf(i2));
            int size = listA.size();
            if (size != 0) {
                com.joaomgcd.taskerm.helper.j.a(this, com.joaomgcd.taskerm.dialog.y.c(c(), R.string.word_error, aj.a(R.string.f_profile_delete_referenced, c(), Integer.valueOf(size)) + "\n\n" + c.a.j.a(listA, "\n", null, null, 0, null, new d(), 30, null)), (b.a.d.f) null, 2, (Object) null);
                return false;
            }
        }
        return true;
    }
}
