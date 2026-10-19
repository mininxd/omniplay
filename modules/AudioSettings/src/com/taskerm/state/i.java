package com.joaomgcd.taskerm.state;

import c.f.b.k;
import c.f.b.l;
import c.f.b.t;
import c.f.b.x;
import java.util.List;
import net.dinglisch.android.taskerm.fn;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8664a = {x.a(new t(x.a(i.class, "Tasker(5.15.14)_marketNoTrialRelease"), "allStates", "getAllStates()Lcom/joaomgcd/taskerm/state/AllStates;")), x.a(new t(x.a(i.class, "Tasker(5.15.14)_marketNoTrialRelease"), "stateTags", "getStateTags()[Lcom/joaomgcd/taskerm/state/StateTag;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f8665b = c.f.a(b.f8668a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f8666c = c.f.a(c.f8669a);

    public static final com.joaomgcd.taskerm.state.a a() {
        c.e eVar = f8665b;
        c.j.g gVar = f8664a[0];
        return (com.joaomgcd.taskerm.state.a) eVar.b();
    }

    private static final h[] b() {
        c.e eVar = f8666c;
        c.j.g gVar = f8664a[1];
        return (h[]) eVar.b();
    }

    static final class b extends l implements c.f.a.a<com.joaomgcd.taskerm.state.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8668a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.state.a invoke() {
            return new com.joaomgcd.taskerm.state.a(new com.joaomgcd.taskerm.state.a.d(), new com.joaomgcd.taskerm.state.sensor.g(), new com.joaomgcd.taskerm.state.sensor.l());
        }
    }

    public static final boolean a(int i) {
        return a().containsKey(Integer.valueOf(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final e<?, ?, ?, ?, ?> b(int i) {
        return (e) a().get(Integer.valueOf(i));
    }

    static final class c extends l implements c.f.a.a<h[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8669a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final h[] invoke() {
            return new h[]{new h("bluetooth", 3)};
        }
    }

    static final class a extends l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8667a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }

        public final String a(int i) {
            String strC = fn.c(i);
            k.a((Object) strC, "StateSpecs.getStateNameByCode(code)");
            return strC;
        }
    }

    public static final void a(String str, List<String> list) {
        k.b(str, "match");
        k.b(list, "list");
        com.joaomgcd.taskerm.f.f.a(b(), str, list, a.f8667a);
    }
}
