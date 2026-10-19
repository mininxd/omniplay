package com.joaomgcd.taskerm.helper;

import android.app.Activity;
import android.view.View;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.au;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bv;
import com.joaomgcd.taskerm.util.bw;
import java.util.ArrayList;
import java.util.Iterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.MacroEditView;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class p extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7416b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(p.class), "addLongClickListener", "getAddLongClickListener()Landroid/view/View$OnLongClickListener;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7417c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final MacroEditView f7418d;

    private final View.OnLongClickListener d() {
        c.e eVar = this.f7417c;
        c.j.g gVar = f7416b[0];
        return (View.OnLongClickListener) eVar.b();
    }

    public final b.a.b a(fs fsVar, net.dinglisch.android.taskerm.c cVar) {
        return a(this, fsVar, cVar, false, 2, null);
    }

    public final b.a.b a(fs fsVar, net.dinglisch.android.taskerm.c cVar, int i) {
        return a(this, fsVar, cVar, i, false, 4, null);
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f7420b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f7420b = activity;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.p$a$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.a<c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ au f7427b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(au auVar) {
                super(0);
                this.f7427b = auVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                p.this.a().a(Integer.valueOf(this.f7427b.a()), this.f7427b.b());
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                com.joaomgcd.taskerm.q.b.b(this.f7420b, new com.joaomgcd.taskerm.util.k(this.f7420b, R.string.favorite_actions_reminder, new Object[0]));
                com.joaomgcd.taskerm.settings.b bVarG = bw.g(this.f7420b);
                if (bVarG.isEmpty()) {
                    com.joaomgcd.taskerm.dialog.y.b(this.f7420b, R.string.favorite_actions, R.string.favorite_actions_instructions, 0, 8, (Object) null).b();
                } else {
                    com.joaomgcd.taskerm.dialog.y.b(this.f7420b, R.string.favorite_actions, R.string.favorite_actions_remove_instructions, 0, 8, (Object) null).b();
                }
                au auVarInvoke = new AnonymousClass1(bVarG).invoke();
                if (auVarInvoke != null) {
                    com.joaomgcd.taskerm.rx.i.e(new AnonymousClass2(auVarInvoke));
                }
            } catch (Throwable th) {
                com.joaomgcd.taskerm.rx.i.a(this.f7420b, th);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.p$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<au> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.settings.b f7422b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(com.joaomgcd.taskerm.settings.b bVar) {
                super(0);
                this.f7422b = bVar;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.p$a$1$a, reason: collision with other inner class name */
            static final class C0167a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.a, com.joaomgcd.taskerm.dialog.w> {
                C0167a() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.w invoke(com.joaomgcd.taskerm.settings.a aVar) {
                    net.dinglisch.android.taskerm.g gVarA;
                    net.dinglisch.android.taskerm.g gVar;
                    c.f.b.k.b(aVar, "it");
                    if (net.dinglisch.android.taskerm.n.s(aVar.f())) {
                        gVarA = bv.f9165a.a(p.this.t(), aVar.f());
                    } else {
                        Integer numC = aVar.c();
                        if (numC != null) {
                            gVar = new net.dinglisch.android.taskerm.g(a.this.f7420b.getResources(), gn.c(a.this.f7420b, numC.intValue()));
                        } else {
                            gVarA = null;
                        }
                        return new com.joaomgcd.taskerm.dialog.w(aVar.e(), gVar, false, 4, null);
                    }
                    gVar = gVarA;
                    return new com.joaomgcd.taskerm.dialog.w(aVar.e(), gVar, false, 4, null);
                }
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final au invoke() {
                C0167a c0167a = new C0167a();
                boolean zH = bw.h(a.this.f7420b);
                int i = zH ? R.string.switch_to_long_click : R.string.switch_to_short_click;
                Activity activity = a.this.f7420b;
                com.joaomgcd.taskerm.settings.b bVar = this.f7422b;
                ArrayList arrayList = new ArrayList();
                Iterator<com.joaomgcd.taskerm.settings.a> it = bVar.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    com.joaomgcd.taskerm.settings.a next = it.next();
                    if (next.b() != null) {
                        arrayList.add(next);
                    }
                }
                ae aeVar = (ae) com.joaomgcd.taskerm.dialog.k.a(new ad(activity, R.string.favorite_actions, c.a.j.a((Iterable) arrayList, c.b.a.a(b.f7424a, c.f7425a)), false, c0167a, null, null, true, null, null, null, new com.joaomgcd.taskerm.util.k(a.this.f7420b, R.string.pl_add, new Object[0]), null, new com.joaomgcd.taskerm.util.k(a.this.f7420b, i, new Object[0]), true, true, 5984, null)).b();
                if (aeVar.a().d()) {
                    com.joaomgcd.taskerm.q.b.b(a.this.f7420b, new com.joaomgcd.taskerm.util.k(a.this.f7420b, R.string.favorite_actions_instructions, new Object[0]));
                    au auVar = (au) com.joaomgcd.taskerm.dialog.k.a(a.this.f7420b, true, (Integer) null, 4, (Object) null).b();
                    aj.a(this.f7422b, new com.joaomgcd.taskerm.settings.a(auVar.a(), auVar.b()));
                    bw.a(a.this.f7420b, this.f7422b);
                    return auVar;
                }
                if (aeVar.a().g()) {
                    ak.b(aj.a(zH ? R.string.showing_favorite_actions_long_click : R.string.showing_favorite_actions_short_click, a.this.f7420b, new Object[0]), a.this.f7420b);
                    bw.e(a.this.f7420b, !zH);
                    return invoke();
                }
                com.joaomgcd.taskerm.settings.a aVar = aeVar != null ? (com.joaomgcd.taskerm.settings.a) aeVar.b() : null;
                if (aVar == null) {
                    return null;
                }
                int iF = aVar.f();
                if (!aeVar.a().o()) {
                    return new au(iF, aVar.g());
                }
                com.joaomgcd.taskerm.q.b.b(a.this.f7420b, new com.joaomgcd.taskerm.util.k(a.this.f7420b, R.string.favorite_actions_remove_instructions, new Object[0]));
                this.f7422b.remove(new com.joaomgcd.taskerm.settings.a(iF, aVar.g()));
                bw.a(a.this.f7420b, this.f7422b);
                return invoke();
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.p$a$1$b */
            static final class b extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.a, Integer> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final b f7424a = new b();

                b() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final Integer invoke(com.joaomgcd.taskerm.settings.a aVar) {
                    c.f.b.k.b(aVar, "it");
                    return aVar.b();
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.p$a$1$c */
            static final class c extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.a, String> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final c f7425a = new c();

                c() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String invoke(com.joaomgcd.taskerm.settings.a aVar) {
                    c.f.b.k.b(aVar, "it");
                    return aVar.a();
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(Activity activity, MacroEditView macroEditView) {
        super(activity);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(macroEditView, "macroEditView");
        this.f7418d = macroEditView;
        this.f7417c = c.f.a(new b(activity));
        this.f7418d.f10896c.setOnLongClickListener(d());
    }

    public final MacroEditView a() {
        return this.f7418d;
    }

    static final class c extends c.f.b.l implements c.f.a.b<b.a.b, b.a.b> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b invoke(b.a.b bVar) {
            c.f.b.k.b(bVar, "receiver$0");
            b.a.b bVarB = bVar.b(new b.a.d.a() { // from class: com.joaomgcd.taskerm.helper.p.c.1

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.p$c$1$1, reason: invalid class name and collision with other inner class name */
                static final class C01681 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public static final C01681 f7433a = new C01681();

                    C01681() {
                        super(1);
                    }

                    public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
                        c.f.b.k.b(xVar, "it");
                    }

                    @Override // c.f.a.b
                    public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
                        a(xVar);
                        return c.s.f2131a;
                    }
                }

                @Override // b.a.d.a
                public final void run() {
                    com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.b(p.this.c(), R.string.favorite_actions, R.string.favorite_actions_reminder, 0, 8, (Object) null), p.this.t(), C01681.f7433a);
                }
            });
            c.f.b.k.a((Object) bVarB, "this.doOnComplete {\n    …context) {}\n            }");
            return bVarB;
        }
    }

    private final b.a.b b(fs fsVar, net.dinglisch.android.taskerm.c cVar, int i, boolean z) {
        com.joaomgcd.taskerm.helper.a aVar;
        c cVar2 = new c();
        if (!z) {
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return cVar2.invoke(bVarA);
        }
        com.joaomgcd.taskerm.helper.a[] aVarArrValues = com.joaomgcd.taskerm.helper.a.values();
        int length = aVarArrValues.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                aVar = null;
                break;
            }
            aVar = aVarArrValues[i2];
            if (aVar.a() == cVar.i()) {
                break;
            }
            i2++;
        }
        if (aVar == null) {
            b.a.b bVarA2 = b.a.b.a();
            c.f.b.k.a((Object) bVarA2, "Completable.complete()");
            return cVar2.invoke(bVarA2);
        }
        c.f.b.w.b bVar = new c.f.b.w.b();
        bVar.f2026a = i + 1;
        b.a.b bVarD = aVar.a(c()).a(new d(fsVar, bVar)).d();
        c.f.b.k.a((Object) bVarD, "actionGroup.dialog(activ…        }.toCompletable()");
        return cVar2.invoke(bVarD);
    }

    public static /* synthetic */ b.a.b a(p pVar, fs fsVar, net.dinglisch.android.taskerm.c cVar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return pVar.a(fsVar, cVar, z);
    }

    public final b.a.b a(fs fsVar, net.dinglisch.android.taskerm.c cVar, boolean z) {
        c.f.b.k.b(fsVar, "receiver$0");
        c.f.b.k.b(cVar, "action");
        fsVar.a(cVar);
        return b(fsVar, cVar, fsVar.z().size() - 1, z);
    }

    public static /* synthetic */ b.a.b a(p pVar, fs fsVar, net.dinglisch.android.taskerm.c cVar, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return pVar.a(fsVar, cVar, i, z);
    }

    public final b.a.b a(fs fsVar, net.dinglisch.android.taskerm.c cVar, int i, boolean z) {
        c.f.b.k.b(fsVar, "receiver$0");
        c.f.b.k.b(cVar, "action");
        fsVar.a(cVar, i);
        return b(fsVar, cVar, i, z);
    }

    static final class b extends c.f.b.l implements c.f.a.a<View.OnLongClickListener> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f7429b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Activity activity) {
            super(0);
            this.f7429b = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final View.OnLongClickListener invoke() {
            return new View.OnLongClickListener() { // from class: com.joaomgcd.taskerm.helper.p.b.1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    p.this.a(b.this.f7429b, false);
                    return true;
                }
            };
        }
    }

    public final void a(Activity activity, boolean z) {
        c.f.b.k.b(activity, "activity");
        if (!bw.h(activity)) {
            z = !z;
        }
        if (z) {
            a(activity);
        } else {
            this.f7418d.h();
        }
    }

    private final void a(Activity activity) {
        a(new a(activity));
    }

    static final class d<T> implements b.a.d.f<Integer[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fs f7434a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.b f7435b;

        d(fs fsVar, c.f.b.w.b bVar) {
            this.f7434a = fsVar;
            this.f7435b = bVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Integer[] numArr) {
            c.f.b.k.b(numArr, "codes");
            for (Integer num : numArr) {
                int iIntValue = num.intValue();
                fs fsVar = this.f7434a;
                net.dinglisch.android.taskerm.c cVar = new net.dinglisch.android.taskerm.c(iIntValue);
                c.f.b.w.b bVar = this.f7435b;
                int i = bVar.f2026a;
                bVar.f2026a = i + 1;
                fsVar.a(cVar, i);
            }
        }
    }
}
