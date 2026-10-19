package com.joaomgcd.taskerm.util;

import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class be {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9028a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(be.class), "minApis", "getMinApis()Ljava/util/HashMap;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f9029b = new a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final c.e f9030d = c.f.a(b.f9035a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final c.e f9031e = c.f.a(c.f9036a);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final c.e f9032f = c.f.a(d.f9037a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9033c;

    public static final bf e() {
        return f9029b.a();
    }

    public static final bg f() {
        return f9029b.b();
    }

    private final HashMap<Integer, Integer> g() {
        c.e eVar = this.f9033c;
        c.j.g gVar = f9028a[0];
        return (HashMap) eVar.b();
    }

    protected abstract HashMap<Integer, Integer> a();

    static final class f extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f9040a = new f();

        f() {
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

    public be(com.joaomgcd.taskerm.f.b<?, ?, ?> bVar) {
        c.f.b.k.b(bVar, "allConfigurables");
        this.f9033c = c.f.a(new e(bVar));
    }

    static final class e extends c.f.b.l implements c.f.a.a<HashMap<Integer, Integer>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.f.b f9039b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(com.joaomgcd.taskerm.f.b bVar) {
            super(0);
            this.f9039b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, Integer> invoke() {
            HashMap<Integer, Integer> mapA = be.this.a();
            com.joaomgcd.taskerm.f.b bVar = this.f9039b;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator it = bVar.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (((com.joaomgcd.taskerm.f.d) entry.getValue()).f() != null) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            ArrayList arrayList = new ArrayList(linkedHashMap.size());
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                Object key = entry2.getKey();
                Integer numF = ((com.joaomgcd.taskerm.f.d) entry2.getValue()).f();
                arrayList.add(new c.k(key, Integer.valueOf(numF != null ? numF.intValue() : 0)));
            }
            c.a.z.a(mapA, arrayList);
            return mapA;
        }
    }

    public final List<Integer> b() {
        HashMap<Integer, Integer> mapG = g();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Integer, Integer> entry : mapG.entrySet()) {
            if (!com.joaomgcd.taskerm.util.f.f9323b.a(entry.getValue().intValue())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Number) ((Map.Entry) it.next()).getKey()).intValue()));
        }
        return arrayList;
    }

    public final aa a(int i) {
        Integer num = g().get(Integer.valueOf(i));
        if (num == null) {
            return new aa(true, 0);
        }
        com.joaomgcd.taskerm.util.f.a aVar = com.joaomgcd.taskerm.util.f.f9323b;
        c.f.b.k.a((Object) num, "it");
        return new aa(aVar.a(num.intValue()), num.intValue());
    }

    public final boolean b(int i) {
        return g().get(Integer.valueOf(i)) != null;
    }

    public final boolean a(Activity activity, int i) {
        c.f.b.k.b(activity, "activity");
        if (!b(i)) {
            return false;
        }
        aa aaVarA = a(i);
        if (aaVarA.a()) {
            return true;
        }
        com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.d(activity, aaVarA.b()), activity, f.f9040a);
        return true;
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ c.j.g[] f9034a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "minApisActions", "getMinApisActions()Lcom/joaomgcd/taskerm/util/MinApisActions;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "minApisEvents", "getMinApisEvents()Lcom/joaomgcd/taskerm/util/MinApisEvents;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(a.class), "minApisStates", "getMinApisStates()Lcom/joaomgcd/taskerm/util/MinApisStates;"))};

        public final bf a() {
            c.e eVar = be.f9030d;
            c.j.g gVar = f9034a[0];
            return (bf) eVar.b();
        }

        public final bg b() {
            c.e eVar = be.f9031e;
            c.j.g gVar = f9034a[1];
            return (bg) eVar.b();
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<bf> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9035a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final bf invoke() {
            return new bf();
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<bg> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9036a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final bg invoke() {
            return new bg();
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<bh> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9037a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final bh invoke() {
            return new bh();
        }
    }
}
