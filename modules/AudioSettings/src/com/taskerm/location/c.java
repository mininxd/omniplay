package com.joaomgcd.taskerm.location;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum c {
    Equals(R.string.equals, "=", AnonymousClass1.f7669a),
    DifferentThan(R.string.different_than, "!=", AnonymousClass2.f7670a),
    Less(R.string.less_than, "<", AnonymousClass3.f7671a),
    Greater(R.string.greater_than, ">", AnonymousClass4.f7672a),
    LessOrEquals(R.string.less_or_equals_than, "<=", AnonymousClass5.f7673a),
    GreaterOrEquals(R.string.greater_or_equals_than, ">=", AnonymousClass6.f7674a);

    private final int i;
    private final String j;
    private final c.f.a.m<Double, Double, Boolean> k;
    public static final a g = new a(null);
    private static final c.l.k l = new c.l.k("(.+?)([\\d.]+)");

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.c$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<Double, Double, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f7669a = new AnonymousClass1();

        AnonymousClass1() {
            super(2);
        }

        public final boolean a(double d2, double d3) {
            return d3 == d2;
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Double d2, Double d3) {
            return Boolean.valueOf(a(d2.doubleValue(), d3.doubleValue()));
        }
    }

    c(int i, String str, c.f.a.m mVar) {
        c.f.b.k.b(str, "symbol");
        c.f.b.k.b(mVar, "function");
        this.i = i;
        this.j = str;
        this.k = mVar;
    }

    public final int a() {
        return this.i;
    }

    public final String b() {
        return this.j;
    }

    public final c.f.a.m<Double, Double, Boolean> c() {
        return this.k;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.c$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.m<Double, Double, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f7670a = new AnonymousClass2();

        AnonymousClass2() {
            super(2);
        }

        public final boolean a(double d2, double d3) {
            return c.g.a.a(d3) != c.g.a.a(d2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Double d2, Double d3) {
            return Boolean.valueOf(a(d2.doubleValue(), d3.doubleValue()));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.c$3, reason: invalid class name */
    static final class AnonymousClass3 extends c.f.b.l implements c.f.a.m<Double, Double, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass3 f7671a = new AnonymousClass3();

        AnonymousClass3() {
            super(2);
        }

        public final boolean a(double d2, double d3) {
            return d3 < d2;
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Double d2, Double d3) {
            return Boolean.valueOf(a(d2.doubleValue(), d3.doubleValue()));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.c$4, reason: invalid class name */
    static final class AnonymousClass4 extends c.f.b.l implements c.f.a.m<Double, Double, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass4 f7672a = new AnonymousClass4();

        AnonymousClass4() {
            super(2);
        }

        public final boolean a(double d2, double d3) {
            return d3 > d2;
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Double d2, Double d3) {
            return Boolean.valueOf(a(d2.doubleValue(), d3.doubleValue()));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.c$5, reason: invalid class name */
    static final class AnonymousClass5 extends c.f.b.l implements c.f.a.m<Double, Double, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass5 f7673a = new AnonymousClass5();

        AnonymousClass5() {
            super(2);
        }

        public final boolean a(double d2, double d3) {
            return d3 <= d2;
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Double d2, Double d3) {
            return Boolean.valueOf(a(d2.doubleValue(), d3.doubleValue()));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.c$6, reason: invalid class name */
    static final class AnonymousClass6 extends c.f.b.l implements c.f.a.m<Double, Double, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass6 f7674a = new AnonymousClass6();

        AnonymousClass6() {
            super(2);
        }

        public final boolean a(double d2, double d3) {
            return d3 >= d2;
        }

        @Override // c.f.a.m
        public /* synthetic */ Boolean invoke(Double d2, Double d3) {
            return Boolean.valueOf(a(d2.doubleValue(), d3.doubleValue()));
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final d a(String str) {
            String strA;
            Double dB;
            c cVar;
            if (str == null) {
                return null;
            }
            int i = 0;
            c.l.i iVarA = c.l.k.a(c.l, str, 0, 2, null);
            if (iVarA == null) {
                return null;
            }
            c.l.f fVarA = iVarA.c().a(1);
            String strA2 = fVarA != null ? fVarA.a() : null;
            if (strA2 == null) {
                strA2 = "";
            }
            c.l.f fVarA2 = iVarA.c().a(2);
            if (fVarA2 == null || (strA = fVarA2.a()) == null || (dB = c.l.n.b(strA)) == null) {
                return null;
            }
            double dDoubleValue = dB.doubleValue();
            if (strA2.length() == 0) {
                cVar = c.LessOrEquals;
            } else {
                c[] cVarArrValues = c.values();
                int length = cVarArrValues.length;
                while (true) {
                    if (i >= length) {
                        cVar = null;
                        break;
                    }
                    c cVar2 = cVarArrValues[i];
                    if (c.f.b.k.a((Object) cVar2.b(), (Object) strA2)) {
                        cVar = cVar2;
                        break;
                    }
                    i++;
                }
                if (cVar == null) {
                    return null;
                }
            }
            return new d(cVar, dDoubleValue);
        }
    }
}
