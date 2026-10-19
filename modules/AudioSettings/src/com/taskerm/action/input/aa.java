package com.joaomgcd.taskerm.action.input;

import android.app.Activity;
import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile;
import com.joaomgcd.taskerm.navigationbar.IconProvider;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;
import net.dinglisch.android.taskerm.gh;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends com.joaomgcd.taskerm.action.setting.e<ay> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5372a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aa.class), "changePublisher", "getChangePublisher()Lio/reactivex/subjects/PublishSubject;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aa.class), "navigationBarTasks", "getNavigationBarTasks()Lcom/joaomgcd/taskerm/navigationbar/NavigationBarTasks;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aa.class), "taskerData", "getTaskerData()Lnet/dinglisch/android/taskerm/TaskerDataRO;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5373c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.f.a.b<String, c.s> f5374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5375e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b.a.b.b f5376f;
    private b.a.b.b g;
    private String h;
    private boolean i;
    private boolean j;
    private final c.e k;
    private final c.e l;

    private final b.a.k.b<String> v() {
        c.e eVar = this.f5373c;
        c.j.g gVar = f5372a[0];
        return (b.a.k.b) eVar.b();
    }

    private final com.joaomgcd.taskerm.navigationbar.k z() {
        c.e eVar = this.k;
        c.j.g gVar = f5372a[1];
        return (com.joaomgcd.taskerm.navigationbar.k) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i2) {
        return true;
    }

    public final fy k_() {
        c.e eVar = this.l;
        c.j.g gVar = f5372a[2];
        return (fy) eVar.b();
    }

    static final class g<T> implements b.a.d.f<String> {
        g() {
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.input.aa$g$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                gh.b(aa.this.u(), 0, R.string.dc_navigation_bar_auto_testing, 3);
            }
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "value");
            try {
                if (!aa.this.i) {
                    aa.this.h = com.joaomgcd.taskerm.securesettings.aa.b(aa.this.u(), (com.joaomgcd.taskerm.securesettings.f) aa.this.f().invoke("")).b();
                }
                aa.this.i = true;
                com.joaomgcd.taskerm.securesettings.r rVarX = aa.this.x();
                rVarX.a(str);
                k.b(rVarX, aa.this.u());
                com.joaomgcd.taskerm.rx.i.e(new AnonymousClass1());
            } catch (Exception e2) {
                bn.b("NavigationBar", "Could not apply setting: " + e2);
            }
        }
    }

    static final class a extends c.f.b.l implements c.f.a.b<String, c.s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            aa.a(aa.this, (String) null, 1, (Object) null);
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
            com.joaomgcd.taskerm.securesettings.r rVar;
            try {
                String str = aa.this.f5375e;
                if (str == null || (rVar = (com.joaomgcd.taskerm.securesettings.r) aa.this.f().invoke(str)) == null) {
                    return;
                }
                k.b(rVar, aa.this.u());
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(ActionEdit actionEdit, com.joaomgcd.taskerm.action.setting.a<ay> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
        this.f5373c = c.f.a(c.f5383a);
        this.f5374d = new a();
        this.j = true;
        this.k = c.f.a(new d(actionEdit));
        this.l = c.f.a(new i(actionEdit));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ay) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ay ayVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(ayVar, "input");
        this.j = true;
        String strA = com.joaomgcd.taskerm.util.aj.a(R.string.dt_task_button, u(), new Object[0]);
        String strA2 = com.joaomgcd.taskerm.util.aj.a(R.string.dt_custom_button, u(), new Object[0]);
        com.joaomgcd.taskerm.helper.l.a(this, c.a.j.d(new bd(strA), new bd(strA2), new bd("back"), new bd("home"), new bd("recent"), new bd("menu_ime"), new bd("space"), new bd("clipboard"), new bd("left"), new bd("right"), new bd("contextual"), new bd("pin")), R.string.dt_navigation_bar_icon, bVar.a(), iVar, null, null, false, new b(strA2, strA), null, 152, null);
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, b.a.l<String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5379b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f5380c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, String str2) {
            super(1);
            this.f5379b = str;
            this.f5380c = str2;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.input.aa$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f5382b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(String str) {
                super(0);
                this.f5382b = str;
            }

            /* JADX WARN: Code duplicated, block: B:32:0x0116  */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                b.a.l lVarA;
                com.joaomgcd.taskerm.dialog.ae aeVar;
                fs fsVar;
                String strL;
                String strB;
                if (c.l.n.b((CharSequence) this.f5382b, (CharSequence) b.this.f5379b, false, 2, (Object) null)) {
                    ActionEdit actionEditU = aa.this.u();
                    Collection<String> collectionValues = com.joaomgcd.taskerm.keyboard.w.f7613a.c().values();
                    c.f.b.k.a((Object) collectionValues, "keyMap.values");
                    String str = (String) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(actionEditU, R.string.dt_custom_button, c.a.j.i(collectionValues), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b();
                    if (str == null) {
                        return "";
                    }
                    c.f.b.k.a((Object) str, "resultKeyCode.selectedIt…@getResultInBackground \"\"");
                    Integer numA = com.joaomgcd.taskerm.keyboard.w.f7613a.c().a(str);
                    if (numA == null) {
                        return "";
                    }
                    int iIntValue = numA.intValue();
                    cl clVarB = new GenericActionActivitySelectFile(null, false, 3, null).run(aa.this.u()).b();
                    if (!(clVarB instanceof cp)) {
                        clVarB = null;
                    }
                    cp cpVar = (cp) clVarB;
                    return (cpVar == null || (strB = IconProvider.f7827a.b((String) cpVar.c())) == null) ? "" : new bb(strB, iIntValue).a();
                }
                if (c.l.n.b((CharSequence) this.f5382b, (CharSequence) b.this.f5380c, false, 2, (Object) null)) {
                    ArrayList arrayList = new ArrayList();
                    int i = 1;
                    while (true) {
                        if (i > 1) {
                            if (!((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(aa.this.u(), R.string.taskselect_title, "Select task to perform when clicking the icon " + i + " times?", 0, 8, (Object) null).b()).d()) {
                                break;
                            }
                            lVarA = com.joaomgcd.taskerm.dialog.y.a((Activity) aa.this.u(), fx.c(aa.this.u()), (String) null, (List) null, false, 28, (Object) null);
                            if (lVarA == null || (aeVar = (com.joaomgcd.taskerm.dialog.ae) lVarA.b()) == null || (fsVar = (fs) aeVar.b()) == null || (strL = fsVar.l()) == null) {
                                break;
                            }
                            fy fyVarK_ = aa.this.k_();
                            c.f.b.k.a((Object) fyVarK_, "taskerData");
                            if (com.joaomgcd.taskerm.util.aj.a(fyVarK_, strL) == null) {
                                com.joaomgcd.taskerm.util.ak.b("Please select an icon for the " + strL + " task first", aa.this.u());
                            } else if (c.l.n.b((CharSequence) strL, (CharSequence) "=:=", false, 2, (Object) null)) {
                                com.joaomgcd.taskerm.util.ak.b("Please select a task that doesn't have =:= in its name.", aa.this.u());
                            } else {
                                arrayList.add(strL);
                                i++;
                            }
                        } else {
                            lVarA = com.joaomgcd.taskerm.dialog.y.a((Activity) aa.this.u(), fx.c(aa.this.u()), (String) null, (List) null, false, 28, (Object) null);
                            if (lVarA == null) {
                                break;
                            }
                            break;
                        }
                    }
                    return arrayList.isEmpty() ? "" : new be(c.a.j.a(arrayList, "=:=", null, null, 0, null, null, 62, null)).a();
                }
                return this.f5382b;
            }
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(String str) {
            c.f.b.k.b(str, "it");
            return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass1(str));
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<b.a.k.b<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5383a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<String> invoke() {
            return b.a.k.b.j();
        }
    }

    static /* synthetic */ void a(aa aaVar, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = (String) null;
        }
        aaVar.a(str);
    }

    private final void a(String str) {
        b.a.k.b<String> bVarV = v();
        if (str == null) {
            str = x().h();
        }
        if (str == null) {
            str = "";
        }
        bVarV.b_(str);
    }

    static final class f extends c.f.b.l implements c.f.a.b<String, String> {
        f() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            fy fyVarK_ = aa.this.k_();
            c.f.b.k.a((Object) fyVarK_, "taskerData");
            return com.joaomgcd.taskerm.util.aj.a(fyVarK_, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String w() {
        return z().a(((ay) j()).getNavigationBarValue(), new f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.securesettings.r x() {
        return f().invoke(w());
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.b(new c.k(0, this.f5374d), new c.k(1, this.f5374d), new c.k(2, this.f5374d));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, Integer num) {
        c.f.b.k.b(cVar, "action");
        super.a(cVar, num);
        this.f5375e = com.joaomgcd.taskerm.securesettings.aa.b((Context) u(), (com.joaomgcd.taskerm.securesettings.f) f().invoke("")).b();
        a(this, (String) null, 1, (Object) null);
        if (bt.a.h(bt.f9101c, u(), 0, 2, null).g()) {
            gh.b(u(), 1, R.string.dc_navigation_bar_warning, 1);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void c() {
        super.c();
        y();
        com.joaomgcd.taskerm.rx.i.c(new e());
    }

    private final void y() {
        b.a.b.b bVar = this.f5376f;
        if (bVar != null) {
            bVar.b();
        }
        b.a.b.b bVar2 = this.g;
        if (bVar2 != null) {
            bVar2.b();
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void d() {
        super.d();
        a(this.j);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        a(this, false, 1, (Object) null);
    }

    static /* synthetic */ void a(aa aaVar, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = true;
        }
        aaVar.a(z);
    }

    private final void a(boolean z) {
        y();
        b.a.k.b<String> bVarV = v();
        c.f.b.k.a((Object) bVarV, "changePublisher");
        b.a.h hVarA = com.joaomgcd.taskerm.rx.i.a(bVarV);
        c.f.b.k.a((Object) hVarA, "changePublisher.observeInBackground()");
        b.a.h hVarB = com.joaomgcd.taskerm.rx.i.b(hVarA);
        this.f5376f = hVarB.c(1000L, TimeUnit.MILLISECONDS).d(new g());
        this.g = hVarB.c(4L, TimeUnit.SECONDS).d(new h());
        if (z) {
            a(this, (String) null, 1, (Object) null);
        }
        this.j = false;
    }

    static final class h<T> implements b.a.d.f<String> {
        h() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            com.joaomgcd.taskerm.securesettings.r rVar;
            c.f.b.k.b(str, "value");
            try {
                String str2 = aa.this.h;
                if (str2 != null && (rVar = (com.joaomgcd.taskerm.securesettings.r) aa.this.f().invoke(str2)) != null) {
                    k.b(rVar, aa.this.u());
                }
                aa.this.i = false;
            } catch (Exception e2) {
                bn.b("NavigationBar", "Could not restore setting: " + e2);
            }
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.navigationbar.k> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ActionEdit f5384a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(ActionEdit actionEdit) {
            super(0);
            this.f5384a = actionEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.navigationbar.k invoke() {
            return new com.joaomgcd.taskerm.navigationbar.k(this.f5384a);
        }
    }

    static final class i extends c.f.b.l implements c.f.a.a<fy> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ActionEdit f5390a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(ActionEdit actionEdit) {
            super(0);
            this.f5390a = actionEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final fy invoke() {
            return fx.c(this.f5390a);
        }
    }
}
