package com.joaomgcd.taskerm.util;

import net.dinglisch.android.taskerm.fn;

/* JADX INFO: loaded from: classes.dex */
public enum bs {
    Event(AnonymousClass1.f9096a),
    State(AnonymousClass2.f9097a),
    Action(AnonymousClass3.f9098a);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.f.a.b<Integer, String> f9095e;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bs$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f9096a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        public final String a(int i) {
            String strG = net.dinglisch.android.taskerm.am.g(i);
            c.f.b.k.a((Object) strG, "EventSpecs.getEventNameByCode(it)");
            return strG;
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }
    }

    bs(c.f.a.b bVar) {
        c.f.b.k.b(bVar, "getName");
        this.f9095e = bVar;
    }

    public final c.f.a.b<Integer, String> a() {
        return this.f9095e;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bs$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f9097a = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        public final String a(int i) {
            String strC = fn.c(i);
            c.f.b.k.a((Object) strC, "StateSpecs.getStateNameByCode(it)");
            return strC;
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bs$3, reason: invalid class name */
    static final class AnonymousClass3 extends c.f.b.l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass3 f9098a = new AnonymousClass3();

        AnonymousClass3() {
            super(1);
        }

        public final String a(int i) {
            String strU = net.dinglisch.android.taskerm.n.u(i);
            c.f.b.k.a((Object) strU, "ActionSpecs.getActionNameByCode(it)");
            return strU;
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }
    }
}
