package com.joaomgcd.taskerm.datashare.export;

import c.f.a.m;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.q;
import c.s;
import java.util.List;
import net.dinglisch.android.taskerm.df;
import net.dinglisch.android.taskerm.dg;

/* JADX INFO: loaded from: classes.dex */
public final class e implements df {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6120a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f6122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f6123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f6124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String[] f6125f;

    public static final e a(dg dgVar) {
        return f6120a.a(dgVar);
    }

    public static final void a(dg dgVar, e eVar, int i) {
        f6120a.a(dgVar, eVar, i);
    }

    public static final e b(dg dgVar) {
        return f6120a.b(dgVar);
    }

    static final class c extends l implements m<String, Boolean, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ dg f6130a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(dg dgVar) {
            super(2);
            this.f6130a = dgVar;
        }

        @Override // c.f.a.m
        public /* synthetic */ s invoke(String str, Boolean bool) {
            a(str, bool.booleanValue());
            return s.f2131a;
        }

        public final void a(String str, boolean z) {
            k.b(str, "receiver$0");
            this.f6130a.b(str, z);
        }
    }

    public e(String str, String str2, Boolean bool, Boolean bool2, String[] strArr) {
        this.f6121b = str;
        this.f6122c = str2;
        this.f6123d = bool;
        this.f6124e = bool2;
        this.f6125f = strArr;
    }

    public final String b() {
        return this.f6121b;
    }

    public final String c() {
        return this.f6122c;
    }

    public final Boolean d() {
        return this.f6124e;
    }

    public final String[] e() {
        return this.f6125f;
    }

    public final boolean a() {
        Boolean bool = this.f6123d;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    static final class b extends l implements m<String, String, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ dg f6129a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(dg dgVar) {
            super(2);
            this.f6129a = dgVar;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final s invoke(String str, String str2) {
            k.b(str, "receiver$0");
            if (str2 == null) {
                return null;
            }
            this.f6129a.c(str, str2);
            return s.f2131a;
        }
    }

    @Override // net.dinglisch.android.taskerm.df
    public dg a(int i) {
        dg dgVar = new dg("Share", 1);
        b bVar = new b(dgVar);
        c cVar = new c(dgVar);
        d dVar = new d(dgVar);
        bVar.invoke("d", this.f6121b);
        bVar.invoke("t", this.f6122c);
        cVar.a("b", a());
        Boolean bool = this.f6124e;
        cVar.a("p", bool != null ? bool.booleanValue() : false);
        dVar.invoke("g", this.f6125f);
        return dgVar;
    }

    static final class d extends l implements m<String, String[], s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ dg f6131a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(dg dgVar) {
            super(2);
            this.f6131a = dgVar;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final s invoke(String str, String[] strArr) {
            k.b(str, "receiver$0");
            if (strArr == null) {
                return null;
            }
            this.f6131a.c(str, c.a.d.a(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null));
            return s.f2131a;
        }
    }

    public static final class a {

        static final class b extends l implements m<String, Boolean, Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ dg f6127a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(dg dgVar) {
                super(2);
                this.f6127a = dgVar;
            }

            @Override // c.f.a.m
            public /* synthetic */ Boolean invoke(String str, Boolean bool) {
                return Boolean.valueOf(a(str, bool));
            }

            public static /* synthetic */ boolean a(b bVar, String str, Boolean bool, int i, Object obj) {
                if ((i & 1) != 0) {
                    bool = (Boolean) null;
                }
                return bVar.a(str, bool);
            }

            public final boolean a(String str, Boolean bool) {
                k.b(str, "receiver$0");
                if (bool == null) {
                    return this.f6127a.d(str);
                }
                return !this.f6127a.c(str) ? bool.booleanValue() : this.f6127a.d(str);
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final void a(dg dgVar, e eVar, int i) {
            k.b(dgVar, "p");
            if (eVar == null) {
                return;
            }
            dgVar.a("Share", eVar.a(i));
        }

        public final e a(dg dgVar) {
            if (dgVar != null && dgVar.c("Share")) {
                return b(dgVar.q("Share"));
            }
            return null;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.export.e$a$a, reason: collision with other inner class name */
        static final class C0121a extends l implements c.f.a.b<String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ dg f6126a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0121a(dg dgVar) {
                super(1);
                this.f6126a = dgVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(String str) {
                k.b(str, "receiver$0");
                return this.f6126a.k(str);
            }
        }

        public final e b(dg dgVar) {
            if (dgVar == null) {
                return null;
            }
            C0121a c0121a = new C0121a(dgVar);
            b bVar = new b(dgVar);
            return new e(c0121a.invoke("d"), c0121a.invoke("t"), Boolean.valueOf(b.a(bVar, "b", null, 1, null)), Boolean.valueOf(b.a(bVar, "p", null, 1, null)), new c(dgVar).invoke("g"));
        }

        static final class c extends l implements c.f.a.b<String, String[]> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ dg f6128a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(dg dgVar) {
                super(1);
                this.f6128a = dgVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String[] invoke(String str) {
                List listB;
                k.b(str, "receiver$0");
                String strK = this.f6128a.k(str);
                if (strK == null || (listB = n.b((CharSequence) strK, new String[]{","}, false, 0, 6, (Object) null)) == null) {
                    return null;
                }
                Object[] array = listB.toArray(new String[0]);
                if (array != null) {
                    return (String[]) array;
                }
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
    }
}
