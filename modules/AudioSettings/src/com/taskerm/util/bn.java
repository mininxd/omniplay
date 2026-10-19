package com.joaomgcd.taskerm.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class bn extends ArrayList<bi> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9051a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(bn.class), "onlyNew", "getOnlyNew()Ljava/util/List;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(bn.class), "mapCategories", "getMapCategories()Ljava/util/HashMap;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(bn.class), "mapCodes", "getMapCodes()Ljava/util/List;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(bn.class), "areThereNewCodes", "getAreThereNewCodes()Z"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f9052b = new a(null);
    private static bn g;
    private static bn h;
    private static bn i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9053c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f9054d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f9055e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f9056f;

    public static final void a(Context context, int i2) {
        f9052b.a(context, i2);
    }

    public static final void b(Context context, int i2) {
        f9052b.b(context, i2);
    }

    public static final void c(Context context, int i2) {
        f9052b.c(context, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<bi> g() {
        c.e eVar = this.f9053c;
        c.j.g gVar = f9051a[0];
        return (List) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HashMap<Integer, Integer> h() {
        c.e eVar = this.f9054d;
        c.j.g gVar = f9051a[1];
        return (HashMap) eVar.b();
    }

    private final List<Integer> i() {
        c.e eVar = this.f9055e;
        c.j.g gVar = f9051a[2];
        return (List) eVar.b();
    }

    public final boolean a() {
        c.e eVar = this.f9056f;
        c.j.g gVar = f9051a[3];
        return ((Boolean) eVar.b()).booleanValue();
    }

    static final class b extends c.f.b.l implements c.f.a.a<Boolean> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return bn.this.h().size() > 0;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bn(bi... biVarArr) {
        super(c.a.d.l(biVarArr));
        c.f.b.k.b(biVarArr, "c");
        this.f9053c = c.f.a(new e());
        this.f9054d = c.f.a(new c());
        this.f9055e = c.f.a(new d());
        this.f9056f = c.f.a(new b());
    }

    public boolean a(bi biVar) {
        return super.contains(biVar);
    }

    public int b(bi biVar) {
        return super.indexOf(biVar);
    }

    public int c() {
        return super.size();
    }

    public int c(bi biVar) {
        return super.lastIndexOf(biVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof bi) {
            return a((bi) obj);
        }
        return false;
    }

    public boolean d(bi biVar) {
        return super.remove(biVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof bi) {
            return b((bi) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof bi) {
            return c((bi) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof bi) {
            return d((bi) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return c();
    }

    static final class e extends c.f.b.l implements c.f.a.a<List<? extends bi>> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<bi> invoke() {
            bn bnVar = bn.this;
            ArrayList arrayList = new ArrayList();
            for (bi biVar : bnVar) {
                if (biVar.c()) {
                    arrayList.add(biVar);
                }
            }
            return arrayList;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<HashMap<Integer, Integer>> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Integer, Integer> invoke() {
            List listG = bn.this.g();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : listG) {
                Integer numA = ((bi) obj).a();
                Object arrayList = linkedHashMap.get(numA);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(numA, arrayList);
                }
                ((List) arrayList).add(obj);
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            Iterator it = linkedHashMap.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey() != null) {
                    linkedHashMap2.put(entry.getKey(), entry.getValue());
                }
            }
            ArrayList arrayList2 = new ArrayList(linkedHashMap2.size());
            for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                arrayList2.add(new c.k(entry2.getKey(), Integer.valueOf(((List) entry2.getValue()).size())));
            }
            Object[] array = arrayList2.toArray(new c.k[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            c.k[] kVarArr = (c.k[]) array;
            return c.a.z.b((c.k[]) Arrays.copyOf(kVarArr, kVarArr.length));
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<List<? extends Integer>> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<Integer> invoke() {
            List listG = bn.this.g();
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listG, 10));
            Iterator it = listG.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((bi) it.next()).d()));
            }
            return arrayList;
        }
    }

    public final Integer a(int i2) {
        return h().get(Integer.valueOf(i2));
    }

    public final boolean b(int i2) {
        return i().contains(Integer.valueOf(i2));
    }

    public final void b() {
        net.dinglisch.android.taskerm.bn.b("NewCodes", h().size() + " new categories");
        net.dinglisch.android.taskerm.bn.b("NewCodes", i().size() + " new codes");
        net.dinglisch.android.taskerm.bn.b("NewCodes", "are there new codes: " + a());
    }

    public final void c(int i2) {
        Object next;
        Iterator<T> it = g().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((bi) next).d() == i2));
        bi biVar = (bi) next;
        if (biVar != null) {
            biVar.a(true);
            b();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bn$a$a, reason: collision with other inner class name */
        static final class C0246a extends c.f.b.l implements c.f.a.q<bn, Integer, int[], bn> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9057a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0246a(Context context) {
                super(3);
                this.f9057a = context;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bn$a$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, bj> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ int f9059b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(int i) {
                    super(1);
                    this.f9059b = i;
                }

                @Override // c.f.a.b
                public /* synthetic */ bj invoke(Integer num) {
                    return a(num.intValue());
                }

                public final bj a(int i) {
                    return new bj(C0246a.this.f9057a, i, new bm(this.f9059b));
                }
            }

            @Override // c.f.a.q
            public /* synthetic */ bn a(bn bnVar, Integer num, int[] iArr) {
                return a(bnVar, num.intValue(), iArr);
            }

            public final bn a(bn bnVar, int i, int... iArr) {
                c.f.b.k.b(bnVar, "receiver$0");
                c.f.b.k.b(iArr, "codes");
                return bnVar.a(c.a.d.a(iArr), new AnonymousClass1(i));
            }
        }

        static final class b extends c.f.b.l implements c.f.a.q<bn, Integer, int[], bn> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9060a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context) {
                super(3);
                this.f9060a = context;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bn$a$b$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, bk> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ int f9062b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(int i) {
                    super(1);
                    this.f9062b = i;
                }

                @Override // c.f.a.b
                public /* synthetic */ bk invoke(Integer num) {
                    return a(num.intValue());
                }

                public final bk a(int i) {
                    return new bk(b.this.f9060a, i, new bm(this.f9062b));
                }
            }

            @Override // c.f.a.q
            public /* synthetic */ bn a(bn bnVar, Integer num, int[] iArr) {
                return a(bnVar, num.intValue(), iArr);
            }

            public final bn a(bn bnVar, int i, int... iArr) {
                c.f.b.k.b(bnVar, "receiver$0");
                c.f.b.k.b(iArr, "codes");
                return bnVar.a(c.a.d.a(iArr), new AnonymousClass1(i));
            }
        }

        static final class c extends c.f.b.l implements c.f.a.q<bn, bm, int[], bn> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9063a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(Context context) {
                super(3);
                this.f9063a = context;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bn$a$c$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, bk> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ bm f9065b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(bm bmVar) {
                    super(1);
                    this.f9065b = bmVar;
                }

                @Override // c.f.a.b
                public /* synthetic */ bk invoke(Integer num) {
                    return a(num.intValue());
                }

                public final bk a(int i) {
                    return new bk(c.this.f9063a, i, this.f9065b);
                }
            }

            @Override // c.f.a.q
            public final bn a(bn bnVar, bm bmVar, int... iArr) {
                c.f.b.k.b(bnVar, "receiver$0");
                c.f.b.k.b(bmVar, "versions");
                c.f.b.k.b(iArr, "codes");
                return bnVar.a(c.a.d.a(iArr), new AnonymousClass1(bmVar));
            }
        }

        static final class d extends c.f.b.l implements c.f.a.q<bn, Integer, int[], bn> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9066a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            d(Context context) {
                super(3);
                this.f9066a = context;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bn$a$d$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, bl> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ int f9068b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(int i) {
                    super(1);
                    this.f9068b = i;
                }

                @Override // c.f.a.b
                public /* synthetic */ bl invoke(Integer num) {
                    return a(num.intValue());
                }

                public final bl a(int i) {
                    return new bl(d.this.f9066a, i, new bm(this.f9068b));
                }
            }

            @Override // c.f.a.q
            public /* synthetic */ bn a(bn bnVar, Integer num, int[] iArr) {
                return a(bnVar, num.intValue(), iArr);
            }

            public final bn a(bn bnVar, int i, int... iArr) {
                c.f.b.k.b(bnVar, "receiver$0");
                c.f.b.k.b(iArr, "codes");
                return bnVar.a(c.a.d.a(iArr), new AnonymousClass1(i));
            }
        }

        static final class e extends c.f.b.l implements c.f.a.q<bn, bm, int[], bn> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f9069a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            e(Context context) {
                super(3);
                this.f9069a = context;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.bn$a$e$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, bl> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ bm f9071b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(bm bmVar) {
                    super(1);
                    this.f9071b = bmVar;
                }

                @Override // c.f.a.b
                public /* synthetic */ bl invoke(Integer num) {
                    return a(num.intValue());
                }

                public final bl a(int i) {
                    return new bl(e.this.f9069a, i, this.f9071b);
                }
            }

            @Override // c.f.a.q
            public final bn a(bn bnVar, bm bmVar, int... iArr) {
                c.f.b.k.b(bnVar, "receiver$0");
                c.f.b.k.b(bmVar, "versions");
                c.f.b.k.b(iArr, "codes");
                return bnVar.a(c.a.d.a(iArr), new AnonymousClass1(bmVar));
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final bn a() {
            return bn.g;
        }

        public final void a(bn bnVar) {
            bn.g = bnVar;
        }

        public final bn a(Context context) {
            c.f.b.k.b(context, "context");
            b bVar = new b(context);
            c cVar = new c(context);
            a aVar = this;
            bn bnVarA = cVar.a(new bn(new bi[0]), new bm(5126), 2075, 2076, 2079);
            for (com.joaomgcd.taskerm.event.b bVar2 : com.joaomgcd.taskerm.event.h.a().c()) {
                Integer numE = bVar2.e();
                if (numE != null) {
                    bVar.a(bnVarA, numE.intValue(), bVar2.i());
                }
            }
            bnVarA.b();
            aVar.a(bnVarA);
            return aVar.a();
        }

        public final bn b() {
            return bn.h;
        }

        public final void b(bn bnVar) {
            bn.h = bnVar;
        }

        public final bn b(Context context) {
            c.f.b.k.b(context, "context");
            d dVar = new d(context);
            e eVar = new e(context);
            a aVar = this;
            bn bnVarA = eVar.a(new bn(new bi[0]), new bm(5126), 186);
            for (com.joaomgcd.taskerm.state.e eVar2 : com.joaomgcd.taskerm.state.i.a().c()) {
                Integer numE = eVar2.e();
                if (numE != null) {
                    dVar.a(bnVarA, numE.intValue(), eVar2.i());
                }
            }
            bnVarA.b();
            aVar.b(bnVarA);
            return aVar.b();
        }

        public final bn c() {
            return bn.i;
        }

        public final void c(bn bnVar) {
            bn.i = bnVar;
        }

        public final bn c(Context context) {
            c.f.b.k.b(context, "context");
            C0246a c0246a = new C0246a(context);
            a aVar = this;
            bn bnVarA = c0246a.a(c0246a.a(new bn(new bi[0]), 5248, 25), 5162, 523);
            Iterator<T> it = com.joaomgcd.taskerm.action.d.a().a().iterator();
            while (it.hasNext()) {
                com.joaomgcd.taskerm.action.a aVar2 = (com.joaomgcd.taskerm.action.a) it.next();
                Integer numE = aVar2.e();
                if (numE != null) {
                    c0246a.a(bnVarA, numE.intValue(), aVar2.i());
                }
            }
            bnVarA.b();
            aVar.c(bnVarA);
            return aVar.c();
        }

        public final void a(Context context, int i) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            bn bnVarC = aVar.c();
            if (bnVarC != null) {
                bnVarC.c(i);
            }
            aVar.c(context);
        }

        public final void b(Context context, int i) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            bn bnVarA = aVar.a();
            if (bnVarA != null) {
                bnVarA.c(i);
            }
            aVar.a(context);
        }

        public final void c(Context context, int i) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            bn bnVarB = aVar.b();
            if (bnVarB != null) {
                bnVarB.c(i);
            }
            aVar.b(context);
        }
    }

    public final bn a(Integer[] numArr, c.f.a.b<? super Integer, ? extends bi> bVar) {
        c.f.b.k.b(numArr, "codes");
        c.f.b.k.b(bVar, "constructor");
        for (Integer num : numArr) {
            add(bVar.invoke(Integer.valueOf(num.intValue())));
        }
        return this;
    }
}
