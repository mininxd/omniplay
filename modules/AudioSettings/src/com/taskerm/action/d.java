package com.joaomgcd.taskerm.action;

import android.content.Context;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.NotificationCompat;
import c.a.z;
import c.f.b.l;
import c.f.b.t;
import c.f.b.x;
import c.q;
import com.joaomgcd.taskerm.action.net.m;
import com.joaomgcd.taskerm.b.s;
import com.joaomgcd.taskerm.function.FunctionBase;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ax;
import com.joaomgcd.taskerm.util.ba;
import com.joaomgcd.taskerm.util.df;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.n;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5047a = {x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "allActions", "getAllActions()Lcom/joaomgcd/taskerm/action/AllActions;")), x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "actionTags", "getActionTags()[Lcom/joaomgcd/taskerm/action/ActionTag;")), x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "actionsThatAcceptSearchTerms", "getActionsThatAcceptSearchTerms()[Ljava/lang/Integer;")), x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "actionsWithRootAndGlobalNamespaceOptions", "getActionsWithRootAndGlobalNamespaceOptions()Ljava/util/HashMap;")), x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "ignoreErrorNoticationsForActions", "getIgnoreErrorNoticationsForActions()Ljava/util/List;")), x.a(new t(x.a(d.class, "Tasker(5.15.14)_marketNoTrialRelease"), "actionsWhereBooleanFalseShouldAppearInDescription", "getActionsWhereBooleanFalseShouldAppearInDescription()Ljava/util/HashMap;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f5048b = c.f.a(f.f5058a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f5049c = c.f.a(a.f5053a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final ax<com.joaomgcd.taskerm.action.b[]> f5050d = ba.a(g.f5059a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final c.e f5051e = c.f.a(b.f5054a);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final c.e f5052f = c.f.a(C0093d.f5056a);
    private static final c.e g = c.f.a(h.f5061a);
    private static final c.e h = c.f.a(c.f5055a);

    public static final com.joaomgcd.taskerm.action.e a() {
        c.e eVar = f5048b;
        c.j.g gVar = f5047a[0];
        return (com.joaomgcd.taskerm.action.e) eVar.b();
    }

    public static final Integer[] c() {
        c.e eVar = f5051e;
        c.j.g gVar = f5047a[2];
        return (Integer[]) eVar.b();
    }

    public static final HashMap<Integer, c.k<Integer, Integer>> d() {
        c.e eVar = f5052f;
        c.j.g gVar = f5047a[3];
        return (HashMap) eVar.b();
    }

    public static final List<Integer> e() {
        c.e eVar = g;
        c.j.g gVar = f5047a[4];
        return (List) eVar.b();
    }

    public static final HashMap<Integer, List<Integer>> f() {
        c.e eVar = h;
        c.j.g gVar = f5047a[5];
        return (HashMap) eVar.b();
    }

    private static final com.joaomgcd.taskerm.action.b[] g() {
        c.e eVar = f5049c;
        c.j.g gVar = f5047a[1];
        return (com.joaomgcd.taskerm.action.b[]) eVar.b();
    }

    static final class f extends l implements c.f.a.a<com.joaomgcd.taskerm.action.e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f5058a = new f();

        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.action.e invoke() {
            return new com.joaomgcd.taskerm.action.e(new com.joaomgcd.taskerm.action.display.g(), new com.joaomgcd.taskerm.action.input.a(), new com.joaomgcd.taskerm.helper.actions.execute.d(), new com.joaomgcd.taskerm.helper.actions.execute.c(), new com.joaomgcd.taskerm.helper.actions.execute.f(), new com.joaomgcd.taskerm.helper.actions.execute.b(), new com.joaomgcd.taskerm.helper.actions.execute.e(), new com.joaomgcd.taskerm.helper.actions.execute.g(), new com.joaomgcd.taskerm.helper.actions.execute.h(), new com.joaomgcd.taskerm.action.display.b(), new com.joaomgcd.taskerm.action.net.k(), new com.joaomgcd.taskerm.action.display.e(), new com.joaomgcd.taskerm.action.display.c(), new com.joaomgcd.taskerm.action.system.b(), new com.joaomgcd.taskerm.action.net.l(), new com.joaomgcd.taskerm.action.audio.d(), new com.joaomgcd.taskerm.action.googledrive.e(), new com.joaomgcd.taskerm.action.net.b(), new com.joaomgcd.taskerm.action.e.b(), new com.joaomgcd.taskerm.action.display.k(), new com.joaomgcd.taskerm.action.googledrive.a(), new com.joaomgcd.taskerm.action.googledrive.b(), new com.joaomgcd.taskerm.action.googledrive.d(), new com.joaomgcd.taskerm.action.googledrive.c(), new com.joaomgcd.taskerm.action.input.i(), new com.joaomgcd.taskerm.action.input.j(), new com.joaomgcd.taskerm.action.net.j(), new com.joaomgcd.taskerm.action.a.b(), new com.joaomgcd.taskerm.action.display.j(), new com.joaomgcd.taskerm.action.setting.c(), new com.joaomgcd.taskerm.action.audio.e(), new com.joaomgcd.taskerm.action.audio.b(), new com.joaomgcd.taskerm.action.net.c(), new com.joaomgcd.taskerm.action.net.d(), new com.joaomgcd.taskerm.action.net.g(), new com.joaomgcd.taskerm.action.net.f(), new com.joaomgcd.taskerm.action.input.b(), new com.joaomgcd.taskerm.action.display.a(), new com.joaomgcd.taskerm.action.input.h(), new com.joaomgcd.taskerm.action.system.j(), new m(), new com.joaomgcd.taskerm.action.system.f(), new com.joaomgcd.taskerm.action.e.c(), new com.joaomgcd.taskerm.action.location.a(), new com.joaomgcd.taskerm.action.b.a(), new com.joaomgcd.taskerm.action.location.b(), new com.joaomgcd.taskerm.action.system.l(), new com.joaomgcd.taskerm.action.b.c(), new com.joaomgcd.taskerm.action.system.i(), new com.joaomgcd.taskerm.action.system.m(), new com.joaomgcd.taskerm.action.system.a(), new com.joaomgcd.taskerm.action.input.g(), new com.joaomgcd.taskerm.action.net.i(), new com.joaomgcd.taskerm.action.net.a(), new com.joaomgcd.taskerm.action.input.f(), new com.joaomgcd.taskerm.action.input.c(), new com.joaomgcd.taskerm.action.phone.b(), new com.joaomgcd.taskerm.action.d.a(), new com.joaomgcd.taskerm.action.b.b(), new com.joaomgcd.taskerm.action.display.i(), new com.joaomgcd.taskerm.action.setting.d(), new com.joaomgcd.taskerm.action.input.l(), new com.joaomgcd.taskerm.action.e.a(), new com.joaomgcd.taskerm.action.phone.a(), new com.joaomgcd.taskerm.action.phone.d(), new com.joaomgcd.taskerm.action.audio.a(), new com.joaomgcd.taskerm.action.f.h(), new com.joaomgcd.taskerm.action.input.d(), new com.joaomgcd.taskerm.action.f.f(), new com.joaomgcd.taskerm.action.f.a(), new com.joaomgcd.taskerm.action.f.d(), new com.joaomgcd.taskerm.action.f.e(), new com.joaomgcd.taskerm.action.system.e(), new com.joaomgcd.taskerm.action.net.e(), new com.joaomgcd.taskerm.action.f.j(), new com.joaomgcd.taskerm.action.a.a(), new com.joaomgcd.taskerm.action.system.k(), new com.joaomgcd.taskerm.action.system.d());
        }
    }

    static final class a extends l implements c.f.a.a<com.joaomgcd.taskerm.action.b[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5053a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.action.b[] invoke() {
            ArrayList arrayList = new ArrayList();
            List<FunctionBase<?, ?>> all = FunctionBase.Companion.getAll();
            ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) all, 10));
            Iterator<T> it = all.iterator();
            while (it.hasNext()) {
                String simpleName = ((FunctionBase) it.next()).getClass().getSimpleName();
                c.f.b.k.a((Object) simpleName, "it::class.java.simpleName");
                if (simpleName == null) {
                    throw new q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = simpleName.toLowerCase();
                c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                arrayList2.add(new com.joaomgcd.taskerm.action.b(lowerCase, 365));
            }
            arrayList.addAll(arrayList2);
            com.joaomgcd.taskerm.b.h hVar = new com.joaomgcd.taskerm.b.h();
            ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) hVar, 10));
            Iterator<s> it2 = hVar.iterator();
            while (it2.hasNext()) {
                String simpleName2 = it2.next().getClass().getSimpleName();
                c.f.b.k.a((Object) simpleName2, "it::class.java.simpleName");
                if (simpleName2 == null) {
                    throw new q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase2 = simpleName2.toLowerCase();
                c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                arrayList3.add(new com.joaomgcd.taskerm.action.b(lowerCase2, 375));
            }
            arrayList.addAll(arrayList3);
            com.joaomgcd.taskerm.profile.i[] iVarArrValues = com.joaomgcd.taskerm.profile.i.values();
            ArrayList arrayList4 = new ArrayList(iVarArrValues.length);
            for (com.joaomgcd.taskerm.profile.i iVar : iVarArrValues) {
                String strName = iVar.name();
                if (strName == null) {
                    throw new q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase3 = strName.toLowerCase();
                c.f.b.k.a((Object) lowerCase3, "(this as java.lang.String).toLowerCase()");
                arrayList4.add(new com.joaomgcd.taskerm.action.b(lowerCase3, 390));
            }
            arrayList.addAll(arrayList4);
            ArrayList arrayList5 = arrayList;
            aj.a((Collection) arrayList5, (Object[]) new com.joaomgcd.taskerm.action.b[]{new com.joaomgcd.taskerm.action.b("shell", 375), new com.joaomgcd.taskerm.action.b("toast", 548), new com.joaomgcd.taskerm.action.b("screen", 248, 806, 318, 822, 810, 808, 812, 222, 316, 906), new com.joaomgcd.taskerm.action.b("display", 248, 806, 318, 808, 906), new com.joaomgcd.taskerm.action.b("google", 326, 324, 327, 325, 321), new com.joaomgcd.taskerm.action.b("drive", 326, 324, 327, 325, 321), new com.joaomgcd.taskerm.action.b("2g", 363), new com.joaomgcd.taskerm.action.b("3g", 363), new com.joaomgcd.taskerm.action.b("4g", 363), new com.joaomgcd.taskerm.action.b("lte", 363), new com.joaomgcd.taskerm.action.b("flight", 333, 323), new com.joaomgcd.taskerm.action.b("gps", 905), new com.joaomgcd.taskerm.action.b("bluetooth", 311), new com.joaomgcd.taskerm.action.b("bt", 340, 358, 295, 296, 294, 218), new com.joaomgcd.taskerm.action.b("adb", 123), new com.joaomgcd.taskerm.action.b("full", 906), new com.joaomgcd.taskerm.action.b(NotificationCompat.CATEGORY_STATUS, 906), new com.joaomgcd.taskerm.action.b(NotificationCompat.CATEGORY_NAVIGATION, 906), new com.joaomgcd.taskerm.action.b("bio", 314), new com.joaomgcd.taskerm.action.b("fingerprint", 314), new com.joaomgcd.taskerm.action.b("credentials", 314), new com.joaomgcd.taskerm.action.b(be.LOCK_LABEL, 314), new com.joaomgcd.taskerm.action.b("sleep", 30, 35), new com.joaomgcd.taskerm.action.b("ssid", 341), new com.joaomgcd.taskerm.action.b("wifi", 341), new com.joaomgcd.taskerm.action.b("hang up", 733), new com.joaomgcd.taskerm.action.b("system", 235), new com.joaomgcd.taskerm.action.b("global", 235), new com.joaomgcd.taskerm.action.b("secure", 235), new com.joaomgcd.taskerm.action.b("menu", 378), new com.joaomgcd.taskerm.action.b("sound", 445), new com.joaomgcd.taskerm.action.b("media", 445), new com.joaomgcd.taskerm.action.b("notification", NotificationCompat.FLAG_GROUP_SUMMARY), new com.joaomgcd.taskerm.action.b("call", 381), new com.joaomgcd.taskerm.action.b("button", 443), new com.joaomgcd.taskerm.action.b("next", 443), new com.joaomgcd.taskerm.action.b("pause", 443), new com.joaomgcd.taskerm.action.b("stop", 443), new com.joaomgcd.taskerm.action.b("play", 443), new com.joaomgcd.taskerm.action.b("rewind", 443), new com.joaomgcd.taskerm.action.b("forward", 443), new com.joaomgcd.taskerm.action.b("rotate", 348), new com.joaomgcd.taskerm.action.b("rotation", 348), new com.joaomgcd.taskerm.action.b("dnd", 312), new com.joaomgcd.taskerm.action.b("flash", FrameMetricsAggregator.EVERY_DURATION), new com.joaomgcd.taskerm.action.b("shutdown", 59), new com.joaomgcd.taskerm.action.b("hotspot", 113), new com.joaomgcd.taskerm.action.b(be.LOCK_LABEL, 248), new com.joaomgcd.taskerm.action.b("record", 374)});
            Object[] array = arrayList5.toArray(new com.joaomgcd.taskerm.action.b[0]);
            if (array != null) {
                return (com.joaomgcd.taskerm.action.b[]) array;
            }
            throw new q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    static final class g extends l implements c.f.a.a<com.joaomgcd.taskerm.action.b[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f5059a = new g();

        g() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.d$g$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<Context> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f5060a = new AnonymousClass1();

            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Context invoke() {
                return df.a("addActionNamesFromTags");
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.action.b[] invoke() {
            ArrayList<fs> arrayListS;
            Context context = (Context) aj.a((c.f.a.b) null, AnonymousClass1.f5060a, 1, (Object) null);
            if (context != null && (arrayListS = fx.c(context).s(-2)) != null) {
                ArrayList<fs> arrayList = arrayListS;
                ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) arrayList, 10));
                for (fs fsVar : arrayList) {
                    c.f.b.k.a((Object) fsVar, "it");
                    String strL = fsVar.l();
                    c.f.b.k.a((Object) strL, "it.name");
                    if (strL == null) {
                        throw new q("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase = strL.toLowerCase();
                    c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                    arrayList2.add(new com.joaomgcd.taskerm.action.b(lowerCase, 130));
                }
                Object[] array = arrayList2.toArray(new com.joaomgcd.taskerm.action.b[0]);
                if (array == null) {
                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                com.joaomgcd.taskerm.action.b[] bVarArr = (com.joaomgcd.taskerm.action.b[]) array;
                if (bVarArr != null) {
                    return bVarArr;
                }
            }
            return new com.joaomgcd.taskerm.action.b[0];
        }
    }

    public static final void b() {
        f5050d.a();
    }

    static final class e extends l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f5057a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }

        public final String a(int i) {
            String strU = n.u(i);
            c.f.b.k.a((Object) strU, "ActionSpecs.getActionNameByCode(code)");
            return strU;
        }
    }

    public static final void a(String str, List<String> list) {
        c.f.b.k.b(str, "match");
        c.f.b.k.b(list, "list");
        com.joaomgcd.taskerm.f.f.a((com.joaomgcd.taskerm.action.b[]) c.a.d.a((Object[]) g(), (Object[]) f5050d.b()), str, list, e.f5057a);
    }

    static final class b extends l implements c.f.a.a<Integer[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5054a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer[] invoke() {
            return new Integer[]{365, 390, 380, 130};
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.d$d, reason: collision with other inner class name */
    static final class C0093d extends l implements c.f.a.a<HashMap<Integer, c.k<? extends Integer, ? extends Integer>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0093d f5056a = new C0093d();

        C0093d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, c.k<Integer, Integer>> invoke() {
            return z.b(new c.k(667, new c.k(10, 9)), new c.k(123, new c.k(6, 2)), new c.k(412, new c.k(6, 3)), new c.k(342, new c.k(4, 3)), new c.k(406, new c.k(3, 2)), new c.k(400, new c.k(3, 2)), new c.k(409, new c.k(3, 2)), new c.k(408, new c.k(3, 2)), new c.k(404, new c.k(3, 2)), new c.k(405, new c.k(3, 2)));
        }
    }

    static final class h extends l implements c.f.a.a<List<? extends Integer>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f5061a = new h();

        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<Integer> invoke() {
            return c.a.j.b(378, 377, 360, 390, 368);
        }
    }

    static final class c extends l implements c.f.a.a<HashMap<Integer, List<? extends Integer>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5055a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, List<Integer>> invoke() {
            return z.b(new c.k(425, c.a.j.a(0)), new c.k(433, c.a.j.a(0)));
        }
    }

    public static final boolean a(int i, int i2) {
        List<Integer> list = f().get(Integer.valueOf(i));
        if (list != null) {
            return list.contains(Integer.valueOf(i2));
        }
        return false;
    }
}
