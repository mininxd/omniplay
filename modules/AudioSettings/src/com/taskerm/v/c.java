package com.joaomgcd.taskerm.v;

import android.content.Context;
import android.os.BaseBundle;
import android.os.Bundle;
import c.a.z;
import c.e;
import c.f;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.k;
import com.joaomgcd.taskerm.util.aj;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.dinglisch.android.taskerm.am;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.fn;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.gx;
import net.dinglisch.android.taskerm.n;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f9415a = {x.a(new v(x.a(c.class), "oldActionsStructuredOutputArgs", "getOldActionsStructuredOutputArgs()Ljava/util/HashMap;")), x.a(new v(x.a(c.class), "eventsStructuredOutputArgs", "getEventsStructuredOutputArgs()Ljava/util/HashMap;")), x.a(new v(x.a(c.class), "statesStructuredOutputArgs", "getStatesStructuredOutputArgs()Ljava/util/HashMap;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f9416b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f9417c = f.a(b.f9421a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final e f9418d = f.a(a.f9420a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final e f9419e = f.a(C0251c.f9422a);

    private final HashMap<Integer, Integer> a() {
        e eVar = f9417c;
        g gVar = f9415a[0];
        return (HashMap) eVar.b();
    }

    private final HashMap<Integer, Integer> b() {
        e eVar = f9418d;
        g gVar = f9415a[1];
        return (HashMap) eVar.b();
    }

    private final HashMap<Integer, Integer> c() {
        e eVar = f9419e;
        g gVar = f9415a[2];
        return (HashMap) eVar.b();
    }

    private c() {
    }

    static final class b extends l implements c.f.a.a<HashMap<Integer, Integer>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9421a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, Integer> invoke() {
            return z.b(new k(547, 6), new k(417, 2), new k(415, 3), new k(416, 3), new k(130, 10), new k(39, 2));
        }
    }

    static final class a extends l implements c.f.a.a<HashMap<Integer, Integer>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f9420a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, Integer> invoke() {
            return z.b(new k(2091, 4));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.v.c$c, reason: collision with other inner class name */
    static final class C0251c extends l implements c.f.a.a<HashMap<Integer, Integer>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0251c f9422a = new C0251c();

        C0251c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, Integer> invoke() {
            return z.b(new k(2091, 4));
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0085  */
    public static final com.joaomgcd.taskerm.v.b a(au auVar) {
        HashMap<Integer, Integer> mapC;
        Boolean boolValueOf;
        Integer num;
        c.f.b.k.b(auVar, "hasArguments");
        boolean z = auVar instanceof net.dinglisch.android.taskerm.c;
        if (z) {
            mapC = f9416b.a();
        } else if (auVar instanceof an) {
            mapC = f9416b.b();
        } else {
            mapC = auVar instanceof fo ? f9416b.c() : null;
        }
        int i = auVar.i();
        if (z) {
            boolValueOf = Boolean.valueOf(n.s(i));
        } else if (auVar instanceof an) {
            boolValueOf = Boolean.valueOf(am.q(i));
        } else {
            boolValueOf = auVar instanceof fo ? Boolean.valueOf(fn.b(i)) : null;
        }
        if (c.f.b.k.a((Object) boolValueOf, (Object) true)) {
            if (z) {
                num = 4;
            } else if ((auVar instanceof an) || (auVar instanceof fo)) {
                num = 3;
            } else {
                num = null;
            }
        } else if (mapC != null) {
            num = mapC.get(Integer.valueOf(i));
        } else {
            num = null;
        }
        if (num == null) {
            return null;
        }
        net.dinglisch.android.taskerm.e eVarH = auVar.h(num.intValue());
        c.f.b.k.a((Object) eVarH, "hasArguments.getBoolArg(arg)");
        if (eVarH.b()) {
            return com.joaomgcd.taskerm.v.b.Auto;
        }
        return null;
    }

    public static final void a(Bundle bundle, au auVar) {
        com.joaomgcd.taskerm.v.b bVarA;
        if (auVar == null || (bVarA = a(auVar)) == null) {
            return;
        }
        HashMap<String, Object> mapA = aj.a((BaseBundle) bundle);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : mapA.entrySet()) {
            if (c.l.n.a(entry.getKey(), "%", false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            gx.a.a(gx.f13177a, bundle, (String) ((Map.Entry) it.next()).getKey(), bVarA, (Context) null, 8, (Object) null);
        }
    }
}
