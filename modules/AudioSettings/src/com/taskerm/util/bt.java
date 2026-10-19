package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDrawOverlayPermissions;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestMissingPermissions;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestNormalPermissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Kid;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.MyDeviceAdminReceiver;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.gq;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(23)
public final class bt {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b.a.k.d<cl> f9102d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f9103e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Context f9104f;
    private final int g;
    private final String[] h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9099a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(bt.class), "specialPermissions", "getSpecialPermissions()Ljava/util/HashMap;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f9101c = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f9100b = {"android.permission.WRITE_SETTINGS", "android.permission.PACKAGE_USAGE_STATS", "android.permission.BIND_ACCESSIBILITY_SERVICE", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.BIND_DEVICE_ADMIN"};
    private static final c.e i = c.f.a(b.f9109a);

    public static final bt a(Context context) {
        return a.a(f9101c, context, 0, 2, (Object) null);
    }

    public static final bt a(Context context, int i2) {
        return f9101c.a(context, i2);
    }

    public static final bt a(Context context, int i2, int i3, boolean z) {
        return f9101c.a(context, i2, i3, z);
    }

    public static final bt b(Context context) {
        return a.b(f9101c, context, 0, 2, null);
    }

    public static final bt b(Context context, int i2) {
        return f9101c.e(context, i2);
    }

    public static final bt b(Context context, int i2, int i3, boolean z) {
        return f9101c.b(context, i2, i3, z);
    }

    public static final bt c(Context context) {
        return a.c(f9101c, context, 0, 2, null);
    }

    public static final bt c(Context context, int i2, int i3, boolean z) {
        return f9101c.c(context, i2, i3, z);
    }

    public static final bt d(Context context) {
        return a.d(f9101c, context, 0, 2, null);
    }

    public static final b.a.l<Boolean> e(Activity activity) {
        return a.a(f9101c, activity, 0, 2, (Object) null);
    }

    public static final bt e(Context context) {
        return a.e(f9101c, context, 0, 2, null);
    }

    public static final bt f(Context context) {
        return a.f(f9101c, context, 0, 2, null);
    }

    public static final bt g(Context context) {
        return a.g(f9101c, context, 0, 2, null);
    }

    public static final bt h(Context context) {
        return a.h(f9101c, context, 0, 2, null);
    }

    public static final bt i(Context context) {
        return a.i(f9101c, context, 0, 2, null);
    }

    public static final bt j(Context context) {
        return a.q(f9101c, context, 0, 2, null);
    }

    public static final bt k(Context context) {
        return a.s(f9101c, context, 0, 2, null);
    }

    public static final bt l(Context context) {
        return a.t(f9101c, context, 0, 2, null);
    }

    public static final String[] r() {
        return f9101c.b();
    }

    public static final String[] s() {
        return f9101c.d();
    }

    public static final String[] t() {
        return f9101c.f();
    }

    public static final String[] u() {
        return f9101c.k();
    }

    public static final String[] v() {
        return f9101c.C();
    }

    public static final String[] w() {
        return f9101c.D();
    }

    public static final String[] x() {
        return f9101c.O();
    }

    @TargetApi(26)
    public final b.a.l<List<Notification>> a() {
        return a(this, (String) null, 1, (Object) null);
    }

    public final HashMap<String, b.a.l<cl>> c() {
        c.e eVar = this.f9103e;
        c.j.g gVar = f9099a[0];
        return (HashMap) eVar.b();
    }

    static final class f<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f9118a = new f();

        f() {
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((cl) obj));
        }

        public final boolean a(cl clVar) {
            c.f.b.k.b(clVar, "it");
            return !clVar.b();
        }
    }

    public bt(Context context, int i2, String... strArr) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(strArr, "permissions");
        this.f9104f = context;
        this.g = i2;
        this.h = strArr;
        this.f9103e = c.f.a(new p());
    }

    public /* synthetic */ bt(Context context, int i2, String[] strArr, int i3, c.f.b.g gVar) {
        this(context, (i3 & 2) != 0 ? 241 : i2, strArr);
    }

    public final Context n() {
        return this.f9104f;
    }

    public final int o() {
        return this.g;
    }

    public final String[] p() {
        return this.h;
    }

    @TargetApi(26)
    public static /* synthetic */ b.a.l a(bt btVar, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = (String) null;
        }
        return btVar.a(str);
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v8 */
    @TargetApi(26)
    public final b.a.l<List<Notification>> a(String str) {
        bt btVar = this;
        int i2 = 0;
        if (str != null ? false : btVar.g()) {
            b.a.l<List<Notification>> lVarA = b.a.l.a(new ArrayList());
            c.f.b.k.a((Object) lVarA, "Single.just(arrayListOf())");
            return lVarA;
        }
        ArrayList arrayList = new ArrayList();
        List listF = c.a.j.f((Collection) btVar.i());
        if (str != null) {
            listF.add(new bo(str, false));
        }
        com.joaomgcd.taskerm.notification.ak akVarA = f9101c.a();
        Iterator it = listF.iterator();
        while (it.hasNext()) {
            bo boVar = (bo) it.next();
            String strA = aj.a(R.string.dt_missing_permissions, this.f9104f, new Object[i2]);
            Context context = this.f9104f;
            Object[] objArr = new Object[2];
            objArr[i2] = ah.a(this.f9104f);
            objArr[1] = boVar.b(this.f9104f);
            String strA2 = aj.a(R.string.dc_missing_permissions, context, objArr);
            com.joaomgcd.taskerm.notification.as asVar = com.joaomgcd.taskerm.util.f.f9323b.a() ? new com.joaomgcd.taskerm.notification.as(R.drawable.mw_hardware_security) : new com.joaomgcd.taskerm.notification.as(ah.K(this.f9104f));
            Iterator it2 = it;
            String str2 = null;
            com.joaomgcd.taskerm.notification.at atVar = new com.joaomgcd.taskerm.notification.at(this.f9104f, strA, strA2, str2, null, null, false, asVar, new com.joaomgcd.taskerm.notification.ao("missingpermisssions", i2, 2), strA2, null, 2, 0L, null, false, false, null, null, null, akVarA, null, false, false, 7861368, null);
            com.joaomgcd.taskerm.notification.c cVarA = boVar.a(this.f9104f);
            atVar.c(aj.a(R.string.dc_click_to_grant_permissions, atVar.e(), new Object[0]));
            atVar.a(cVarA);
            arrayList.add(atVar.c());
            if (com.joaomgcd.taskerm.util.f.f9323b.t()) {
                arrayList.add(new com.joaomgcd.taskerm.notification.at(this.f9104f, strA, null, null, null, null, false, asVar, new com.joaomgcd.taskerm.notification.ao("missingpermisssions", true, 2), null, null, 0, 0L, null, false, false, null, null, null, akVarA, null, false, false, 7863932, null).c());
            }
            it = it2;
            i2 = 0;
        }
        b.a.l<List<Notification>> lVarG = b.a.l.a((Iterable) arrayList).g();
        c.f.b.k.a((Object) lVarG, "Single.concat(actions).toList()");
        return lVarG;
    }

    public final b.a.l<cl> a(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return b(activity);
    }

    public final b.a.l<cl> b(Activity activity) {
        c.f.b.k.b(activity, "activity");
        bt btVar = this;
        if (!btVar.g()) {
            return com.joaomgcd.taskerm.rx.i.b(new d(btVar, activity));
        }
        b.a.l<cl> lVarA = b.a.l.a(new co());
        c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }

    static final class d extends c.f.b.l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ bt f9113b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f9114c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(bt btVar, Activity activity) {
            super(0);
            this.f9113b = btVar;
            this.f9114c = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            cl clVarB = new GenericActionActivityRequestNormalPermissions(this.f9113b, 12).run(bt.this.n()).b();
            if (!clVarB.b()) {
                return clVarB;
            }
            String[] strArrK = this.f9113b.k();
            if (new bt(bt.this.n(), 0, (String[]) Arrays.copyOf(strArrK, strArrK.length), 2, (c.f.b.g) null).g()) {
                return new co();
            }
            Map mapB = c.a.z.b(this.f9113b.c());
            mapB.remove("android.permission.ACCESS_SUPERUSER");
            mapB.remove("android.permission.SET_LIGHT");
            ArrayList arrayList = new ArrayList(mapB.size());
            for (Map.Entry entry : mapB.entrySet()) {
                arrayList.add(new c.k(entry.getKey(), ((b.a.l) entry.getValue()).b()));
            }
            ArrayList arrayList2 = arrayList;
            boolean z = true;
            if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    if (!((cl) ((c.k) it.next()).b()).b()) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                return new co();
            }
            Iterator<T> it2 = this.f9113b.i().iterator();
            while (it2.hasNext()) {
                cl clVarB2 = ((bo) it2.next()).a(this.f9114c).b();
                if (!clVarB2.b()) {
                    return clVarB2;
                }
            }
            return new co();
        }
    }

    public /* synthetic */ bt(Context context, int i2, Collection collection, int i3, c.f.b.g gVar) {
        this(context, (i3 & 2) != 0 ? 241 : i2, (Collection<String>) collection);
    }

    private final boolean b(String str) {
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ADB_WIFI")) {
            return true;
        }
        boolean zA = net.dinglisch.android.taskerm.az.a(this.f9104f);
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_SUPERUSER")) {
            return zA;
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.SET_LIGHT")) {
            return zA && net.dinglisch.android.taskerm.bj.b(this.f9104f).b();
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.NOTIFICATION_ACCESS")) {
            return net.dinglisch.android.taskerm.db.a();
        }
        if (com.joaomgcd.taskerm.util.f.f9323b.j() && c.f.b.k.a((Object) str, (Object) "android.permission.MANAGE_EXTERNAL_STORAGE")) {
            return dm.c();
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_NOTIFICATION_POLICY")) {
            return net.dinglisch.android.taskerm.db.a(this.f9104f);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.BIND_DEVICE_ADMIN")) {
            return MyDeviceAdminReceiver.c(this.f9104f);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.DRAW_OVERLAYS") || c.f.b.k.a((Object) str, (Object) "android.permission.SYSTEM_ALERT_WINDOW")) {
            return net.dinglisch.android.taskerm.bp.e(this.f9104f);
        }
        if (f9101c.a(str)) {
            return true;
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.WRITE_SETTINGS")) {
            return net.dinglisch.android.taskerm.bp.a(this.f9104f);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.PACKAGE_USAGE_STATS")) {
            return gq.b(this.f9104f);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.BIND_ACCESSIBILITY_SERVICE")) {
            return MyAccessibilityService.c();
        }
        return c.f.b.k.a((Object) str, (Object) "android.permission.CALL_SCREENING") ? ah.O(this.f9104f) : ah.g(this.f9104f, str);
    }

    private final List<bo> y() {
        String[] strArr = this.h;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!c.a.d.b(f9100b, str)) {
                arrayList.add(str);
            }
        }
        ArrayList<String> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        for (String str2 : arrayList2) {
            arrayList3.add(new bo(str2, b(str2)));
        }
        return arrayList3;
    }

    private final List<bo> z() {
        String[] strArr = this.h;
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(new bo(str, b(str)));
        }
        return arrayList;
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<bo> f9110a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f9111b;

        public c(List<bo> list, boolean z) {
            c.f.b.k.b(list, "permissions");
            this.f9110a = list;
            this.f9111b = z;
        }

        public final List<bo> a() {
            return this.f9110a;
        }

        public final boolean b() {
            return this.f9111b;
        }
    }

    public static /* synthetic */ b.a.l a(bt btVar, Activity activity, bq bqVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bqVar = (bq) null;
        }
        return btVar.a(activity, bqVar);
    }

    public final b.a.l<c> a(Activity activity, bq bqVar) {
        c.f.b.k.b(activity, "activity");
        if (com.joaomgcd.taskerm.util.f.f9323b.d()) {
            b.a.l<c> lVarA = b.a.l.a(new c(c.a.j.a(), false));
            c.f.b.k.a((Object) lVarA, "Single.just(ResultReques…fNeeded(listOf(), false))");
            return lVarA;
        }
        if (!f()) {
            return com.joaomgcd.taskerm.rx.i.b(new e(activity, bqVar));
        }
        b.a.l<c> lVarA2 = b.a.l.a(new c(h(), false));
        c.f.b.k.a((Object) lVarA2, "Single.just(ResultReques…eeded(notGranted, false))");
        return lVarA2;
    }

    static final class e extends c.f.b.l implements c.f.a.a<c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9116b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ bq f9117c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Activity activity, bq bqVar) {
            super(0);
            this.f9116b = activity;
            this.f9117c = bqVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            Object next;
            String strA;
            List<bo> listD = bt.this.d();
            boolean z = true;
            if (listD.isEmpty()) {
                List<bo> listI = bt.this.i();
                ArrayList arrayList = new ArrayList();
                for (Object obj : listI) {
                    if (c.f.b.k.a((Object) ((bo) obj).b(), (Object) "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                if (!arrayList2.isEmpty()) {
                    listD = arrayList2;
                }
            }
            if (!listD.isEmpty()) {
                bt.this.d(this.f9116b);
                List<bo> list = listD;
                Iterator<T> it = list.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!bt.this.a(this.f9116b, (bo) next));
                bo boVar = (bo) next;
                if (boVar != null) {
                    if (this.f9117c == null) {
                        strA = "This functionality needs the " + boVar.b(this.f9116b) + " permission. Open Settings now to grant it?";
                    } else {
                        bp bpVarA = this.f9117c.a(this.f9116b, boVar.b());
                        strA = aj.a(R.string.dc_explain_open_permissions_settings, this.f9116b, bpVarA.a(), bpVarA.b());
                    }
                    if (((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(this.f9116b, R.string.dt_missing_permissions, strA, 0, false, (String) null, 56, (Object) null).b()).d()) {
                        try {
                            this.f9116b.startActivity(Settings.s(this.f9116b));
                        } catch (Exception unused) {
                        }
                    }
                    z = false;
                } else {
                    Iterator<T> it2 = list.iterator();
                    while (it2.hasNext()) {
                        bt.this.a((Context) this.f9116b, ((bo) it2.next()).b(), true);
                    }
                    Activity activity = this.f9116b;
                    ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) list, 10));
                    Iterator<T> it3 = list.iterator();
                    while (it3.hasNext()) {
                        arrayList3.add(((bo) it3.next()).b());
                    }
                    Object[] array = arrayList3.toArray(new String[0]);
                    if (array != null) {
                        activity.requestPermissions((String[]) array, bt.this.o());
                    } else {
                        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            } else {
                z = false;
            }
            b.a.k.d dVar = bt.this.f9102d;
            if (dVar != null) {
                dVar.d_(new co());
            }
            return new c(bt.this.h(), z);
        }
    }

    public final boolean a(Activity activity, bo boVar) {
        boolean zA;
        c.f.b.k.b(activity, "receiver$0");
        c.f.b.k.b(boVar, "permission");
        return (com.joaomgcd.taskerm.util.f.f9323b.d() || boVar.c() || !(zA = a((Context) activity, boVar.b())) || zA == activity.shouldShowRequestPermissionRationale(boVar.b())) ? false : true;
    }

    private final String c(String str) {
        return "peramsmds:" + str;
    }

    public final boolean a(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "key");
        String strC = c(str);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, strC, false, sharedPreferencesA);
    }

    public final void a(Context context, String str, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "key");
        String strC = c(str);
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, strC, boolValueOf, sharedPreferencesA);
    }

    public final b.a.l<cl> b() {
        if (!f()) {
            return new GenericActionActivityRequestNormalPermissions(this, this.g).run(this.f9104f);
        }
        b.a.l<cl> lVarA = b.a.l.a(new co());
        c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }

    public final b.a.l<Boolean> c(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(activity).c(f.f9118a);
    }

    public static /* synthetic */ b.a.l b(bt btVar, Activity activity, bq bqVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bqVar = (bq) null;
        }
        return btVar.b(activity, bqVar);
    }

    public final b.a.l<cl> b(Activity activity, bq bqVar) {
        c.f.b.k.b(activity, "activity");
        if (f()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.l lVarA2 = a(activity, bqVar).a(new g());
        c.f.b.k.a((Object) lVarA2, "requestIfNeeded(activity…)\n            }\n        }");
        return lVarA2;
    }

    static final class g<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {
        g() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cl> apply(c cVar) {
            cm cmVar;
            c.f.b.k.b(cVar, "resultRequestIfNeeded");
            if (!cVar.b()) {
                if (bt.this.j().length == 0) {
                    cmVar = new co();
                } else {
                    cmVar = new cm("Missing permissions: " + c.a.d.a(bt.this.j(), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null));
                }
                return b.a.l.a(cmVar);
            }
            b.a.k.d dVarF = b.a.k.d.f();
            bt.this.f9102d = dVarF;
            c.f.b.k.a((Object) dVarF, "SingleSubject.create<Sim…lisher = it\n            }");
            return dVarF.d(new b.a.d.g<Throwable, b.a.p<? extends cl>>() { // from class: com.joaomgcd.taskerm.util.bt.g.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<cm> apply(Throwable th) {
                    c.f.b.k.b(th, "it");
                    return b.a.l.a(new cm(th));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final cm A() {
        return new cm(aj.a(R.string.dt_enable_permission_in_settings, this.f9104f, aj.a(l(), (CharSequence) null, (c.f.a.b) null, 3, (Object) null)));
    }

    public final List<bo> a(int i2, String[] strArr, int[] iArr) {
        cm cmVarA;
        c.f.b.k.b(strArr, "permissions");
        c.f.b.k.b(iArr, "grantResults");
        if (i2 != this.g) {
            return y();
        }
        b.a.k.d<cl> dVar = this.f9102d;
        if (dVar != null) {
            if (f()) {
                cmVarA = new co();
            } else {
                cmVarA = A();
            }
            dVar.d_(cmVarA);
            this.f9102d = (b.a.k.d) null;
        }
        return y();
    }

    static final class p extends c.f.b.l implements c.f.a.a<HashMap<String, b.a.l<cl>>> {
        p() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, b.a.l<cl>> invoke() {
            HashMap<String, b.a.l<cl>> map = new HashMap<>();
            l lVar = new l(map);
            map.put("android.permission.ACCESS_SUPERUSER", net.dinglisch.android.taskerm.az.b(bt.this.n()).c(new a()));
            map.put("android.permission.SET_LIGHT", net.dinglisch.android.taskerm.bj.a(bt.this.n()));
            lVar.invoke("android.permission.NOTIFICATION_ACCESS", new d());
            if (com.joaomgcd.taskerm.util.f.f9323b.j()) {
                String str = net.dinglisch.android.taskerm.bp.f11893a;
                c.f.b.k.a((Object) str, "ManifestPermissions.MANAGE_EXTERNAL_STORAGE");
                lVar.invoke(str, new e());
            }
            lVar.invoke("android.permission.ACCESS_BACKGROUND_LOCATION", new f());
            lVar.invoke("android.permission.ACCESS_NOTIFICATION_POLICY", new g());
            lVar.invoke("android.permission.BIND_DEVICE_ADMIN", new h());
            lVar.invoke("android.permission.DRAW_OVERLAYS", new i());
            lVar.invoke("android.permission.PACKAGE_USAGE_STATS", new j());
            lVar.invoke("android.permission.BIND_ACCESSIBILITY_SERVICE", new k());
            lVar.invoke("android.permission.CALL_SCREENING", new b());
            Set<String> setKeySet = bt.f9101c.Q().keySet();
            c.f.b.k.a((Object) setKeySet, "minApisPermissions.keys");
            for (String str2 : setKeySet) {
                c.f.b.k.a((Object) str2, "permission");
                lVar.invoke(str2, new c(str2, lVar, this));
            }
            return map;
        }

        static final class l extends c.f.b.l implements c.f.a.m<String, c.f.a.a<? extends cl>, b.a.l<cl>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ HashMap f9153a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            l(HashMap map) {
                super(2);
                this.f9153a = map;
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<cl> invoke(String str, final c.f.a.a<? extends cl> aVar) {
                c.f.b.k.b(str, "permission");
                c.f.b.k.b(aVar, "callable");
                return (b.a.l) this.f9153a.put(str, b.a.l.b(new Callable<T>() { // from class: com.joaomgcd.taskerm.util.bt.p.l.1
                    @Override // java.util.concurrent.Callable
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final cl call() {
                        return (cl) aVar.invoke();
                    }
                }));
            }
        }

        static final class a<T, R> implements b.a.d.g<T, R> {
            a() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl apply(Boolean bool) {
                c.f.b.k.b(bool, "it");
                return bool.booleanValue() ? new co() : new cm(aj.a(R.string.f_no_root, bt.this.n(), new Object[0]));
            }
        }

        static final class d extends c.f.b.l implements c.f.a.a<cl> {
            d() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl clVarB = net.dinglisch.android.taskerm.db.b(bt.this.n());
                c.f.b.k.a((Object) clVarB, "NotificationListenerInte…ningSimpleResult(context)");
                return clVarB;
            }
        }

        static final class e extends c.f.b.l implements c.f.a.a<cl> {
            e() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl cmVar;
                if (dm.c()) {
                    cmVar = new co();
                } else {
                    String strA = net.dinglisch.android.taskerm.cs.a(bt.this.n(), R.string.dc_manage_external_storage, new Object[0]);
                    c.f.b.k.a((Object) strA, "MyRes.getString(context,…_manage_external_storage)");
                    cmVar = new cm(strA);
                }
                return cmVar;
            }
        }

        static final class f extends c.f.b.l implements c.f.a.a<cl> {
            f() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl cmVar;
                if (ah.g(bt.this.n(), "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    cmVar = new co();
                } else {
                    String strA = net.dinglisch.android.taskerm.cs.a(bt.this.n(), R.string.background_location_permission_justification, new Object[0]);
                    c.f.b.k.a((Object) strA, "MyRes.getString(context,…permission_justification)");
                    cmVar = new cm(strA);
                }
                return cmVar;
            }
        }

        static final class g extends c.f.b.l implements c.f.a.a<cl> {
            g() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl clVarC = net.dinglisch.android.taskerm.db.c(bt.this.n());
                c.f.b.k.a((Object) clVarC, "NotificationListenerInte…turbSimpleResult(context)");
                return clVarC;
            }
        }

        static final class h extends c.f.b.l implements c.f.a.a<cl> {
            h() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl clVarB = MyDeviceAdminReceiver.b(bt.this.n());
                c.f.b.k.a((Object) clVarB, "MyDeviceAdminReceiver.enabledSimpleResult(context)");
                return clVarB;
            }
        }

        static final class i extends c.f.b.l implements c.f.a.a<cl> {
            i() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl clVarG = net.dinglisch.android.taskerm.bp.g(bt.this.n());
                c.f.b.k.a((Object) clVarG, "ManifestPermissions.have…sionSimpleResult(context)");
                return clVarG;
            }
        }

        static final class j extends c.f.b.l implements c.f.a.a<cl> {
            j() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl clVarA = gq.a(bt.this.n());
                c.f.b.k.a((Object) clVarA, "UsageStatUtils.haveSetti…sionSimpleResult(context)");
                return clVarA;
            }
        }

        static final class k extends c.f.b.l implements c.f.a.a<cl> {
            k() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl clVarC = MyAccessibilityService.c(bt.this.n());
                c.f.b.k.a((Object) clVarC, "MyAccessibilityService.r…ningSimpleResult(context)");
                return clVarC;
            }
        }

        static final class b extends c.f.b.l implements c.f.a.a<cl> {
            b() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                cl cmVar;
                if (ah.O(bt.this.n())) {
                    cmVar = new co();
                } else {
                    String strA = net.dinglisch.android.taskerm.cs.a(bt.this.n(), R.string.dc_call_screener, new Object[0]);
                    c.f.b.k.a((Object) strA, "MyRes.getString(context,….string.dc_call_screener)");
                    cmVar = new cm(strA);
                }
                return cmVar;
            }
        }

        static final class c extends c.f.b.l implements c.f.a.a<cl> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f9142a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ l f9143b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f9144c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(String str, l lVar, p pVar) {
                super(0);
                this.f9142a = str;
                this.f9143b = lVar;
                this.f9144c = pVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cl invoke() {
                a aVar = bt.f9101c;
                String str = this.f9142a;
                c.f.b.k.a((Object) str, "permission");
                if (aVar.a(str)) {
                    return new co();
                }
                Context contextN = bt.this.n();
                String str2 = this.f9142a;
                c.f.b.k.a((Object) str2, "permission");
                if (ah.g(contextN, str2)) {
                    return new co();
                }
                Context contextN2 = bt.this.n();
                Object[] objArr = new Object[1];
                Object obj = (Integer) bt.f9101c.Q().get(this.f9142a);
                if (obj == null) {
                    obj = "";
                }
                objArr[0] = obj;
                return new cm(aj.a(R.string.tip_need_min_api, contextN2, objArr));
            }
        }
    }

    public final b.a.l<cl> a(Activity activity, String str) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "permission");
        HashMap map = new HashMap();
        map.put("android.permission.WRITE_SECURE_SETTINGS", com.joaomgcd.taskerm.dialog.y.a(activity, (String) null, (String) null, 0, false, false, false, 126, (Object) null).a((b.a.d.g) new h(activity)));
        map.put("android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER", com.joaomgcd.taskerm.dialog.y.b(activity).a(new i(activity)));
        map.put("android.permission.SET_MEDIA_KEY_LISTENER", com.joaomgcd.taskerm.dialog.y.c(activity).a(new j(activity)));
        map.put("android.permission.DUMP", com.joaomgcd.taskerm.dialog.y.f(activity).a(new k(activity)));
        map.put("android.permission.ADB_WIFI", com.joaomgcd.taskerm.dialog.y.d(activity).a(new l(activity)));
        map.put("android.permission.READ_LOGS", com.joaomgcd.taskerm.dialog.y.i(activity).a(new m(activity)));
        map.put("android.permission.NOTIFICATION_ACCESS", com.joaomgcd.taskerm.dialog.y.a(activity, (cm) null, 2, (Object) null));
        map.put("android.permission.ACCESS_NOTIFICATION_POLICY", com.joaomgcd.taskerm.dialog.y.f(activity, null, 2, null));
        if (com.joaomgcd.taskerm.util.f.f9323b.j()) {
            map.put(net.dinglisch.android.taskerm.bp.f11893a, com.joaomgcd.taskerm.dialog.y.b(activity, (cm) null, 2, (Object) null));
        }
        map.put("android.permission.ACCESS_BACKGROUND_LOCATION", com.joaomgcd.taskerm.dialog.y.c(activity, (cm) null, 2, (Object) null));
        map.put("android.permission.CALL_SCREENING", com.joaomgcd.taskerm.dialog.y.d(activity, (cm) null, 2, (Object) null));
        map.put("android.permission.ACCESS_SUPERUSER", com.joaomgcd.taskerm.dialog.y.e(activity, null, 2, null));
        map.put("android.permission.DRAW_OVERLAYS", com.joaomgcd.taskerm.dialog.y.k(activity).a(new n(activity)));
        Set<String> setKeySet = f9101c.Q().keySet();
        c.f.b.k.a((Object) setKeySet, "minApisPermissions.keys");
        for (String str2 : setKeySet) {
            map.put(str2, b.a.l.b(new o(str2, map, this, activity)));
        }
        b.a.l<cl> lVar = (b.a.l) map.get(str);
        if (lVar != null) {
            return lVar;
        }
        b.a.l<cl> lVarA = b.a.l.a(new cm("no requester for special permission"));
        c.f.b.k.a((Object) lVarA, "Single.just(SimpleResult…for special permission\"))");
        return lVarA;
    }

    static final class h<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9122b;

        h(Activity activity) {
            this.f9122b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return b.a.l.a(new co());
        }
    }

    static final class i<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9124b;

        i(Activity activity) {
            this.f9124b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return b.a.l.a(new co());
        }
    }

    static final class j<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9126b;

        j(Activity activity) {
            this.f9126b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return b.a.l.a(new co());
        }
    }

    static final class k<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9128b;

        k(Activity activity) {
            this.f9128b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return b.a.l.a(new co());
        }
    }

    static final class l<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9130b;

        l(Activity activity) {
            this.f9130b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return b.a.l.a(new co());
        }
    }

    static final class m<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9132b;

        m(Activity activity) {
            this.f9132b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return b.a.l.a(new co());
        }
    }

    static final class n<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f9134b;

        n(Activity activity) {
            this.f9134b = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.d()) {
                return b.a.l.a(bt.this.A());
            }
            return new GenericActionActivityRequestDrawOverlayPermissions().run(bt.this.n());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class o<V, T> implements Callable<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f9135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ HashMap f9136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ bt f9137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Activity f9138d;

        o(String str, HashMap map, bt btVar, Activity activity) {
            this.f9135a = str;
            this.f9136b = map;
            this.f9137c = btVar;
            this.f9138d = activity;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co call() {
            this.f9138d.requestPermissions(new String[]{this.f9135a}, this.f9137c.o());
            return new co();
        }
    }

    public final List<bo> d() {
        String[] strArrB = f9101c.b();
        List<bo> listH = h();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listH) {
            if (!c.a.d.b(strArrB, ((bo) obj).b())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List<bo> e() {
        List<bo> listY = y();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listY) {
            if (!c().containsKey(((bo) obj).b())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final boolean f() {
        return h().isEmpty();
    }

    public final boolean g() {
        return i().isEmpty();
    }

    public final List<bo> h() {
        List<bo> listY = y();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listY) {
            if (!((bo) obj).c()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List<bo> i() {
        List<bo> listZ = z();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listZ) {
            if (!((bo) obj).c()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final String[] j() {
        List<bo> listH = h();
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listH, 10));
        Iterator<T> it = listH.iterator();
        while (it.hasNext()) {
            arrayList.add(((bo) it.next()).b());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final String[] k() {
        List<bo> listI = i();
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listI, 10));
        Iterator<T> it = listI.iterator();
        while (it.hasNext()) {
            arrayList.add(((bo) it.next()).b());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final List<String> l() {
        String[] strArrK = k();
        ArrayList arrayList = new ArrayList(strArrK.length);
        for (String str : strArrK) {
            arrayList.add(bu.a(str, this.f9104f));
        }
        return c.a.j.l(arrayList);
    }

    public final List<c.k<Integer, bo>> m() {
        List<bo> listI = i();
        ArrayList arrayList = new ArrayList();
        for (bo boVar : listI) {
            Integer numA = boVar.a();
            c.k kVar = numA != null ? new c.k(Integer.valueOf(numA.intValue()), boVar) : null;
            if (kVar != null) {
                arrayList.add(kVar);
            }
        }
        return c.a.j.l(arrayList);
    }

    public final void d(Activity activity) {
        c.f.b.k.b(activity, "activity");
        Iterator<T> it = m().iterator();
        while (it.hasNext()) {
            c.k kVar = (c.k) it.next();
            com.joaomgcd.taskerm.dialog.y.a(activity, ((bo) kVar.b()).b(this.f9104f), aj.a(((Number) kVar.a()).intValue(), this.f9104f, new Object[0]), (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).b();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ c.j.g[] f9105a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "minApisPermissions", "getMinApisPermissions()Ljava/util/HashMap;"))};

        /* JADX INFO: Access modifiers changed from: private */
        public final HashMap<String, Integer> Q() {
            c.e eVar = bt.i;
            c.j.g gVar = f9105a[0];
            return (HashMap) eVar.b();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bt$a$a, reason: collision with other inner class name */
        static final class C0247a extends c.f.b.l implements c.f.a.b<HashMap<Integer, String[]>, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9106a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0247a(Context context) {
                super(1);
                this.f9106a = context;
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(HashMap<Integer, String[]> map) {
                a(map);
                return c.s.f2131a;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void a(HashMap<Integer, String[]> map) {
                c.f.b.k.b(map, "receiver$0");
                map.put(176, bt.f9101c.d());
                bt.f9101c.a(map, new Integer[]{731, 733}, bt.f9101c.k());
                map.put(910, c.a.d.a((Object[]) bt.f9101c.l(), (Object[]) bt.f9101c.n()));
                map.put(90, bt.f9101c.j());
                map.put(567, bt.f9101c.g());
                map.put(341, bt.f9101c.q());
                map.put(346, bt.f9101c.l());
                map.put(344, bt.f9101c.g());
                bt.f9101c.a(map, new Integer[]{902, 332}, bt.f9101c.f());
                bt.f9101c.a(map, new Integer[]{41, 42}, bt.f9101c.p());
                bt.f9101c.a(map, new Integer[]{410, 400, 404, 405, 406, 408, 409, 420, 422, 187, 699, 116, 118, 775, 112, 428, 434, 429, 435}, bt.f9101c.d());
                map.put(101, c.a.d.a((Object[]) bt.f9101c.e(), (Object[]) bt.f9101c.d()));
                map.put(455, c.a.d.a((Object[]) bt.f9101c.r(), (Object[]) bt.f9101c.d()));
                bt.f9101c.a(map, new Integer[]{566, 543}, bt.f9101c.s());
                bt.f9101c.a(map, new Integer[]{900, 412, 102, 417, 415, 416, 188, 445, 447, 776, 667, 192, 109}, bt.f9101c.d());
                bt.f9101c.a(map, new Integer[]{175, 701, 703, 702, 915, 59, 124, 999}, bt.f9101c.w());
                map.put(999, bt.f9101c.x());
                bt.f9101c.a(map, new Integer[]{312, 304, 308, 313, 310}, bt.f9101c.B());
                bt.f9101c.a(map, new Integer[]{175, 905}, bt.f9101c.E());
                map.put(16, bt.f9101c.C());
                map.put(457, bt.f9101c.K());
                bt.f9101c.a(map, new Integer[]{254, 296}, bt.f9101c.L());
                if (com.joaomgcd.taskerm.util.f.f9323b.h()) {
                    map.put(513, bt.f9101c.z());
                }
                Collection<com.joaomgcd.taskerm.action.a<?, ?, ?>> collectionValues = com.joaomgcd.taskerm.action.d.a().values();
                c.f.b.k.a((Object) collectionValues, "allActions.values");
                Iterator<T> it = collectionValues.iterator();
                while (it.hasNext()) {
                    com.joaomgcd.taskerm.action.a aVar = (com.joaomgcd.taskerm.action.a) it.next();
                    String[] strArrA = com.joaomgcd.taskerm.f.d.a(aVar, this.f9106a, null, 2, null);
                    if (strArrA != null) {
                    }
                }
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<HashMap<Integer, String[]>, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9107a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context) {
                super(1);
                this.f9107a = context;
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(HashMap<Integer, String[]> map) {
                a(map);
                return c.s.f2131a;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void a(HashMap<Integer, String[]> map) {
                c.f.b.k.b(map, "receiver$0");
                map.put(2003, c.a.d.a((Object[]) bt.f9101c.l(), (Object[]) bt.f9101c.n()));
                map.put(6, c.a.d.a((Object[]) bt.f9101c.l(), (Object[]) bt.f9101c.h()));
                bt.f9101c.a(map, new Integer[]{2010, 2005}, bt.f9101c.l());
                bt.f9101c.a(map, new Integer[]{4, 2}, bt.f9101c.h());
                bt.f9101c.a(map, new Integer[]{8, 7}, bt.f9101c.o());
                map.put(2076, bt.f9101c.v());
                map.put(461, bt.f9101c.y());
                map.put(2079, bt.f9101c.F());
                map.put(309, bt.f9101c.P());
                Iterable<com.joaomgcd.taskerm.event.b> iterableValues = com.joaomgcd.taskerm.event.h.a().values();
                c.f.b.k.a((Object) iterableValues, "allEvents.values");
                for (com.joaomgcd.taskerm.event.b bVar : iterableValues) {
                    String[] strArrA = com.joaomgcd.taskerm.f.d.a(bVar, this.f9107a, null, 2, null);
                    if (strArrA != null) {
                    }
                }
            }
        }

        static final class c extends c.f.b.l implements c.f.a.b<HashMap<Integer, String[]>, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final c f9108a = new c();

            c() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(HashMap<Integer, String[]> map) {
                a(map);
                return c.s.f2131a;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void a(HashMap<Integer, String[]> map) {
                c.f.b.k.b(map, "receiver$0");
                map.put(107, c.a.d.a((Object[]) bt.f9101c.n(), (Object[]) bt.f9101c.l()));
                map.put(5, bt.f9101c.g());
                map.put(147, bt.f9101c.o());
                map.put(182, bt.f9101c.t());
                map.put(40, c.a.d.a((Object[]) bt.f9101c.m(), (Object[]) bt.f9101c.n()));
                map.put(7, bt.f9101c.f());
                map.put(145, bt.f9101c.h());
                map.put(110, bt.f9101c.i());
                bt.f9101c.a(map, new Integer[]{160, 170, 3, 4}, bt.f9101c.f());
                if (com.joaomgcd.taskerm.util.f.f9323b.r()) {
                    map.put(105, bt.f9101c.G());
                }
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final b.a.l<List<Notification>> a(Context context) {
            c.f.b.k.b(context, "context");
            String[] strArrH = bt.f9101c.H();
            return new bt(context, 21, (String[]) Arrays.copyOf(strArrH, strArrH.length)).a("android.permission.ADB_WIFI");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final b.a.l<cl> a(Context context, bt btVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(btVar, "permissions");
            return new GenericActionActivityRequestMissingPermissions(btVar.k(), null, 2, 0 == true ? 1 : 0).run(context);
        }

        public final com.joaomgcd.taskerm.notification.ak a() {
            return new com.joaomgcd.taskerm.notification.ak("Permissions", "Permissions", "Notifications related to missing permissions or permissions in general", 5, null, null, false, null, false, null, null, 2032, null);
        }

        public final String[] b() {
            ArrayList arrayListD = c.a.j.d("android.permission.WRITE_SETTINGS", "android.permission.WRITE_SECURE_SETTINGS", "android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER", "android.permission.SET_MEDIA_KEY_LISTENER", "android.permission.DUMP", "android.permission.ADB_WIFI", "android.permission.READ_LOGS", "android.permission.DRAW_OVERLAYS", "android.permission.PACKAGE_USAGE_STATS", "android.permission.BIND_ACCESSIBILITY_SERVICE", "android.permission.CALL_SCREENING", "android.permission.BIND_DEVICE_ADMIN", "android.permission.NOTIFICATION_ACCESS", "android.permission.ACCESS_NOTIFICATION_POLICY", "android.permission.ACCESS_SUPERUSER", "android.permission.ACCESS_BACKGROUND_LOCATION");
            if (com.joaomgcd.taskerm.util.f.f9323b.j()) {
                arrayListD.add(net.dinglisch.android.taskerm.bp.f11893a);
            }
            Object[] array = arrayListD.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }

        public final HashMap<String, Integer> c() {
            if (!Kid.a()) {
                return new HashMap<>();
            }
            return new HashMap<>();
        }

        public static /* synthetic */ bt a(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.a(context, i);
        }

        public final bt a(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrD = d();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrD, strArrD.length));
        }

        public static /* synthetic */ bt b(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.b(context, i);
        }

        public final bt b(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrH = h();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrH, strArrH.length));
        }

        public static /* synthetic */ bt c(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.c(context, i);
        }

        public final bt c(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrI = i();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrI, strArrI.length));
        }

        public static /* synthetic */ bt d(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.d(context, i);
        }

        public final bt d(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrN = n();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrN, strArrN.length));
        }

        public static /* synthetic */ bt e(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.e(context, i);
        }

        public final bt e(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrF = f();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrF, strArrF.length));
        }

        public static /* synthetic */ bt f(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.f(context, i);
        }

        public final bt f(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrG = g();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrG, strArrG.length));
        }

        public static /* synthetic */ bt g(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.g(context, i);
        }

        public final bt g(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrD = D();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrD, strArrD.length));
        }

        public static /* synthetic */ bt h(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.h(context, i);
        }

        public final bt h(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrE = E();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrE, strArrE.length));
        }

        public static /* synthetic */ bt i(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.i(context, i);
        }

        public final bt i(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrF = F();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrF, strArrF.length));
        }

        public static /* synthetic */ bt j(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.j(context, i);
        }

        public final bt j(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrO = O();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrO, strArrO.length));
        }

        public static /* synthetic */ bt k(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.k(context, i);
        }

        public final bt k(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrY = y();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrY, strArrY.length));
        }

        public static /* synthetic */ bt l(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.l(context, i);
        }

        public final bt l(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrD = d();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrD, strArrD.length));
        }

        public static /* synthetic */ bt m(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.m(context, i);
        }

        public final bt m(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrA = A();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrA, strArrA.length));
        }

        public static /* synthetic */ bt n(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.n(context, i);
        }

        public final bt n(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrB = B();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrB, strArrB.length));
        }

        public static /* synthetic */ bt o(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.o(context, i);
        }

        public final bt o(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrW = w();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrW, strArrW.length));
        }

        public static /* synthetic */ bt p(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.p(context, i);
        }

        public final bt p(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrJ = J();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrJ, strArrJ.length));
        }

        public static /* synthetic */ bt q(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.q(context, i);
        }

        public final bt q(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrG = G();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrG, strArrG.length));
        }

        public static /* synthetic */ b.a.l a(a aVar, Activity activity, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.a(activity, i);
        }

        public final b.a.l<Boolean> a(Activity activity, int i) {
            c.f.b.k.b(activity, "activity");
            return a((Context) activity, i).c(activity);
        }

        public static /* synthetic */ bt r(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.r(context, i);
        }

        public final bt r(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrR = r();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrR, strArrR.length));
        }

        public static /* synthetic */ bt s(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.s(context, i);
        }

        public final bt s(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrL = l();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrL, strArrL.length));
        }

        public static /* synthetic */ bt t(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.t(context, i);
        }

        public final bt t(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrN = N();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrN, strArrN.length));
        }

        public static /* synthetic */ bt u(a aVar, Context context, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 241;
            }
            return aVar.u(context, i);
        }

        public final bt u(Context context, int i) {
            c.f.b.k.b(context, "context");
            String[] strArrP = P();
            return new bt(context, i, (String[]) Arrays.copyOf(strArrP, strArrP.length));
        }

        public final String[] d() {
            return new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", net.dinglisch.android.taskerm.bp.f11893a};
        }

        public final String[] e() {
            return new String[]{"android.permission.CAMERA"};
        }

        public final String[] f() {
            return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", net.dinglisch.android.taskerm.bp.f11895c};
        }

        public final String[] g() {
            return new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
        }

        public final String[] h() {
            return new String[]{"android.permission.READ_PHONE_STATE", "android.permission.READ_CALL_LOG"};
        }

        public final String[] i() {
            return new String[]{"android.permission.READ_PHONE_STATE"};
        }

        public final String[] j() {
            return new String[]{"android.permission.CALL_PHONE"};
        }

        public final String[] k() {
            return new String[]{"android.permission.ANSWER_PHONE_CALLS"};
        }

        public final String[] l() {
            return new String[]{"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"};
        }

        public final String[] m() {
            return new String[]{"android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE"};
        }

        public final String[] n() {
            return new String[]{"android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.PROCESS_OUTGOING_CALLS"};
        }

        public final String[] o() {
            return new String[]{"android.permission.RECEIVE_SMS", "android.permission.READ_CONTACTS", "android.permission.READ_SMS", "android.permission.READ_PHONE_STATE"};
        }

        public final String[] p() {
            return (String[]) c.a.d.a(bt.f9101c.o(), "android.permission.SEND_SMS");
        }

        public final String[] q() {
            return new String[]{"android.permission.READ_SYNC_SETTINGS"};
        }

        public final String[] r() {
            return new String[]{"android.permission.RECORD_AUDIO"};
        }

        public final String[] s() {
            return new String[]{"com.android.alarm.permission.SET_ALARM"};
        }

        public final String[] t() {
            return new String[]{"android.permission.BODY_SENSORS"};
        }

        public final String[] u() {
            return new String[]{"android.permission.INTERNET"};
        }

        public final String[] v() {
            return new String[]{"android.permission.NFC"};
        }

        public final String[] w() {
            return new String[]{"android.permission.ACCESS_SUPERUSER"};
        }

        public final String[] x() {
            return new String[]{"android.permission.SET_LIGHT"};
        }

        public final String[] y() {
            return new String[]{"android.permission.NOTIFICATION_ACCESS"};
        }

        public final String[] z() {
            return new String[]{"android.permission.BIND_ACCESSIBILITY_SERVICE"};
        }

        public final String[] A() {
            return new String[]{"android.permission.CALL_SCREENING"};
        }

        public final String[] B() {
            return com.joaomgcd.taskerm.util.f.f9323b.d() ? bt.f9101c.y() : new String[]{"android.permission.ACCESS_NOTIFICATION_POLICY"};
        }

        public final String[] C() {
            return new String[]{"android.permission.BIND_DEVICE_ADMIN"};
        }

        public final String[] D() {
            return new String[]{"android.permission.DRAW_OVERLAYS"};
        }

        public final String[] E() {
            return new String[]{"android.permission.WRITE_SECURE_SETTINGS", "android.permission.WRITE_SETTINGS"};
        }

        public final String[] F() {
            return new String[]{"android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER"};
        }

        public final String[] G() {
            return new String[]{"android.permission.SET_MEDIA_KEY_LISTENER"};
        }

        public final String[] H() {
            return new String[]{"android.permission.ADB_WIFI"};
        }

        public final String[] I() {
            return net.dinglisch.android.taskerm.az.a((Context) null) ? bt.f9101c.w() : bt.f9101c.H();
        }

        public final String[] J() {
            return new String[]{"android.permission.READ_LOGS"};
        }

        public final String[] K() {
            return new String[]{"android.permission.WRITE_SETTINGS"};
        }

        public final String[] L() {
            return new String[]{"android.permission.MODIFY_AUDIO_SETTINGS"};
        }

        public final String[] M() {
            return new String[]{"android.permission.PACKAGE_USAGE_STATS"};
        }

        public final String[] N() {
            return new String[]{"android.permission.BLUETOOTH"};
        }

        public final String[] O() {
            return com.joaomgcd.taskerm.util.f.f9323b.n() ? new String[0] : new String[]{"android.permission.DRAW_OVERLAYS", "android.permission.WRITE_SECURE_SETTINGS", "android.permission.READ_LOGS"};
        }

        public final String[] P() {
            return new String[]{net.dinglisch.android.taskerm.bp.f11896d};
        }

        public static /* synthetic */ bt a(a aVar, Context context, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i2 = 241;
            }
            if ((i3 & 8) != 0) {
                z = false;
            }
            return aVar.a(context, i, i2, z);
        }

        public final bt a(Context context, int i, int i2, boolean z) {
            c.f.b.k.b(context, "context");
            return a(context, i, i2, z, c.f9108a);
        }

        public static /* synthetic */ bt b(a aVar, Context context, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i2 = 241;
            }
            if ((i3 & 8) != 0) {
                z = false;
            }
            return aVar.b(context, i, i2, z);
        }

        public final bt b(Context context, int i, int i2, boolean z) {
            c.f.b.k.b(context, "context");
            return a(context, i, i2, z, new C0247a(context));
        }

        public static /* synthetic */ bt c(a aVar, Context context, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i2 = 241;
            }
            if ((i3 & 8) != 0) {
                z = false;
            }
            return aVar.c(context, i, i2, z);
        }

        public final bt c(Context context, int i, int i2, boolean z) {
            c.f.b.k.b(context, "context");
            return a(context, i, i2, z, new b(context));
        }

        private final bt a(Context context, int i, int i2, boolean z, c.f.a.b<? super HashMap<Integer, String[]>, c.s> bVar) {
            HashMap map = new HashMap();
            bVar.invoke(map);
            String[] strArr = (String[]) map.get(Integer.valueOf(i));
            if (strArr == null) {
                strArr = new String[0];
            }
            bt btVar = new bt(context, i2, (String[]) Arrays.copyOf(strArr, strArr.length));
            if (!z) {
                return btVar;
            }
            List<bo> listE = btVar.e();
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listE, 10));
            Iterator<T> it = listE.iterator();
            while (it.hasNext()) {
                arrayList.add(((bo) it.next()).b());
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                String[] strArr2 = (String[]) array;
                return new bt(context, i2, (String[]) Arrays.copyOf(strArr2, strArr2.length));
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }

        public final com.joaomgcd.taskerm.notification.b a(Context context, String str) {
            c.f.b.k.b(context, "context");
            if (str == null) {
                return null;
            }
            HashMap mapB = c.a.z.b(new c.k("android.permission.WRITE_SECURE_SETTINGS", new com.joaomgcd.taskerm.notification.af(context)), new c.k("android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER", new com.joaomgcd.taskerm.notification.ae(context)), new c.k("android.permission.SET_MEDIA_KEY_LISTENER", new com.joaomgcd.taskerm.notification.aa(context)), new c.k("android.permission.DUMP", new com.joaomgcd.taskerm.notification.w(context)), new c.k("android.permission.ADB_WIFI", new com.joaomgcd.taskerm.notification.q(context)), new c.k("android.permission.READ_LOGS", new com.joaomgcd.taskerm.notification.ac(context)), new c.k("android.permission.WRITE_SETTINGS", new com.joaomgcd.taskerm.notification.ag(context)), new c.k("android.permission.NOTIFICATION_ACCESS", new com.joaomgcd.taskerm.notification.ab(context)), new c.k("android.permission.ACCESS_NOTIFICATION_POLICY", new com.joaomgcd.taskerm.notification.u(context)), new c.k("android.permission.BIND_DEVICE_ADMIN", new com.joaomgcd.taskerm.notification.t(context)), new c.k("android.permission.ACCESS_SUPERUSER", new com.joaomgcd.taskerm.notification.ad(context)), new c.k("android.permission.DRAW_OVERLAYS", new com.joaomgcd.taskerm.notification.v(context)), new c.k("android.permission.SYSTEM_ALERT_WINDOW", new com.joaomgcd.taskerm.notification.v(context)), new c.k("android.permission.PACKAGE_USAGE_STATS", new com.joaomgcd.taskerm.notification.r(context)), new c.k("android.permission.BIND_ACCESSIBILITY_SERVICE", new com.joaomgcd.taskerm.notification.p(context)), new c.k("android.permission.CALL_SCREENING", new com.joaomgcd.taskerm.notification.s(context)), new c.k("android.permission.ACCESS_BACKGROUND_LOCATION", new com.joaomgcd.taskerm.notification.k(context, new bt(context, 0, new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"}, 2, (c.f.b.g) null))));
            if (com.joaomgcd.taskerm.util.f.f9323b.j()) {
                String str2 = net.dinglisch.android.taskerm.bp.f11893a;
                c.f.b.k.a((Object) str2, "ManifestPermissions.MANAGE_EXTERNAL_STORAGE");
                mapB.put(str2, new com.joaomgcd.taskerm.notification.z(context));
            }
            return (com.joaomgcd.taskerm.notification.b) mapB.get(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean a(String str) {
            Integer num = Q().get(str);
            return num != null && com.joaomgcd.taskerm.util.f.f9323b.c(num.intValue());
        }

        public final cm b(Context context) {
            c.f.b.k.b(context, "context");
            return new cm(aj.a(R.string.dt_enable_permission_in_settings, context, ""));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void a(HashMap<Integer, String[]> map, Integer[] numArr, String[] strArr) {
            for (Integer num : numArr) {
                map.put(Integer.valueOf(num.intValue()), strArr);
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<HashMap<String, Integer>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9109a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, Integer> invoke() {
            return c.a.z.b(new c.k("android.permission.ANSWER_PHONE_CALLS", 26));
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public bt(Context context, int i2, Collection<String> collection) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(collection, "permissions");
        Object[] array = collection.toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            this(context, i2, (String[]) Arrays.copyOf(strArr, strArr.length));
            return;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
