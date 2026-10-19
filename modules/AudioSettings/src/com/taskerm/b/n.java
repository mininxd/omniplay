package com.joaomgcd.taskerm.b;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class n extends ArrayList<m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5931a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final boolean f5932b = true;

    public static final String a(String str, String str2) {
        return f5931a.a(str, str2);
    }

    public static final class a extends com.joaomgcd.taskerm.b.d<n> {
        @Override // com.joaomgcd.taskerm.b.d
        protected String a() {
            return "service list";
        }

        static final class f extends c.f.b.l implements c.f.a.m<String, Boolean, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ e f5943a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            f(e eVar) {
                super(2);
                this.f5943a = eVar;
            }

            @Override // c.f.a.m
            public /* synthetic */ String invoke(String str, Boolean bool) {
                return a(str, bool.booleanValue());
            }

            public final String a(String str, boolean z) {
                c.f.b.k.b(str, "part");
                c.k<String, String> kVarA = ak.a(str, 4);
                String strC = kVarA.c();
                String strD = kVarA.d();
                if (z) {
                    return this.f5943a.invoke(ak.k(strD)) + this.f5943a.invoke(ak.k(strC));
                }
                StringBuilder sb = new StringBuilder();
                if (strC == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                sb.append(c.l.n.b((CharSequence) strC).toString());
                if (strD == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                sb.append(c.l.n.b((CharSequence) strD).toString());
                return sb.toString();
            }
        }

        static final class g extends c.f.b.l implements c.f.a.m<c.l.f, Boolean, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ StringBuilder f5944a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ f f5945b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            g(StringBuilder sb, f fVar) {
                super(2);
                this.f5944a = sb;
                this.f5945b = fVar;
            }

            @Override // c.f.a.m
            public /* synthetic */ c.s invoke(c.l.f fVar, Boolean bool) {
                a(fVar, bool.booleanValue());
                return c.s.f2131a;
            }

            public final void a(c.l.f fVar, boolean z) {
                String strA = fVar != null ? fVar.a() : null;
                String str = strA;
                if (str == null || str.length() == 0) {
                    return;
                }
                this.f5944a.append(this.f5945b.a(strA, z));
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.n$a$a, reason: collision with other inner class name */
        static final class C0111a extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Activity f5933a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f5934b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ boolean f5935c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f5936d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ String f5937e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0111a(Activity activity, boolean z, boolean z2, int i, String str) {
                super(0);
                this.f5933a = activity;
                this.f5934b = z;
                this.f5935c = z2;
                this.f5936d = i;
                this.f5937e = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                ac acVarA = ac.a.a(ac.f6217a, this.f5933a, R.string.getting_services, false, 4, null);
                try {
                    n nVarB = (this.f5934b ? n.f5931a.a(this.f5933a, this.f5935c) : com.joaomgcd.taskerm.b.d.a(n.f5931a, this.f5933a, this.f5936d, this.f5937e, 0, 8, null)).b();
                    ac.a(acVarA, null, 1, null);
                    Activity activity = this.f5933a;
                    int i = R.string.bl_services;
                    c.f.b.k.a((Object) nVarB, "adbServiceList");
                    m mVar = (m) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(activity, i, nVarB, true, b.f5939a, null, null, null, null, null, null, null, null, null, null, null, 65504, null)).b()).b();
                    if (mVar == null) {
                        throw new RuntimeException("No service selected");
                    }
                    List<o> listA = mVar.a();
                    if (listA == null) {
                        throw new RuntimeException("Selected service doesn't have any methods");
                    }
                    o oVar = (o) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(this.f5933a, R.string.pl_action, listA, true, C0112a.f5938a, null, null, null, null, null, null, null, null, null, null, null, 65504, null)).b()).b();
                    if (oVar == null) {
                        throw new RuntimeException("No method selected");
                    }
                    Class<?>[] clsArrA = oVar.a();
                    c.f.b.k.a((Object) clsArrA, "types");
                    ArrayList arrayList = new ArrayList(clsArrA.length);
                    int length = clsArrA.length;
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < length) {
                        arrayList.add(com.joaomgcd.taskerm.dialog.y.a(clsArrA[i2], this.f5933a, oVar.c(), i3, (String) null, (String) null, 24, (Object) null));
                        i2++;
                        i3++;
                    }
                    Object[] array = arrayList.toArray(new Object[0]);
                    if (array != null) {
                        if (this.f5934b) {
                            String strA = oVar.a(Arrays.copyOf(array, array.length));
                            ac.a(acVarA, null, 1, null);
                            return strA;
                        }
                        String strB = new p(this.f5933a, mVar, oVar).b(Arrays.copyOf(array, array.length));
                        if (strB == null) {
                            strB = oVar.a(Arrays.copyOf(array, array.length));
                        }
                        ac.a(acVarA, null, 1, null);
                        return strB;
                    }
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                } catch (Throwable th) {
                    ac.a(acVarA, null, 1, null);
                    throw th;
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.n$a$a$b */
            static final class b extends c.f.b.l implements c.f.a.b<m, com.joaomgcd.taskerm.dialog.w> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final b f5939a = new b();

                b() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.w invoke(m mVar) {
                    return new com.joaomgcd.taskerm.dialog.w(mVar.b(), null, false, 6, null);
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.n$a$a$a, reason: collision with other inner class name */
            static final class C0112a extends c.f.b.l implements c.f.a.b<o, com.joaomgcd.taskerm.dialog.w> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final C0112a f5938a = new C0112a();

                C0112a() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.w invoke(o oVar) {
                    c.f.b.k.b(oVar, NotificationCompat.CATEGORY_SERVICE);
                    return new com.joaomgcd.taskerm.dialog.w(ak.j(oVar.c()), null, false, 6, null);
                }
            }
        }

        @Override // com.joaomgcd.taskerm.b.c
        public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(str, "host");
            return com.joaomgcd.taskerm.rx.i.b(new C0111a(activity, z, z2, i, str));
        }

        public static final class b<T> implements Comparator<T> {
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String strB = ((m) t).b();
                if (strB == null) {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = strB.toLowerCase();
                c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                String str = lowerCase;
                String strB2 = ((m) t2).b();
                if (strB2 == null) {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase2 = strB2.toLowerCase();
                c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                return c.b.a.a(str, lowerCase2);
            }
        }

        @Override // com.joaomgcd.taskerm.b.c
        public com.joaomgcd.taskerm.util.i b() {
            return new com.joaomgcd.taskerm.util.j("Services");
        }

        @Override // com.joaomgcd.taskerm.b.d
        protected boolean c() {
            return n.f5932b;
        }

        @Override // com.joaomgcd.taskerm.b.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public n a(String str) {
            c.f.b.k.b(str, "raw");
            c.k.g gVarA = c.k.h.a(c.k.h.c(c.k.h.b(c.l.k.b(new c.l.k("(\\d+)\\s+([^:]+): \\[([^\\]]+)\\]"), str, 0, 2, null), c.f5940a)), new b());
            n nVar = new n();
            c.a.j.a(nVar, gVarA);
            return nVar;
        }

        static final class c extends c.f.b.l implements c.f.a.b<c.l.i, m> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final c f5940a = new c();

            c() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final m invoke(c.l.i iVar) {
                String strA;
                Integer numC;
                String strA2;
                c.l.f fVarA;
                String strA3;
                c.f.b.k.b(iVar, "it");
                c.l.f fVarA2 = iVar.c().a(1);
                if (fVarA2 == null || (strA = fVarA2.a()) == null || (numC = c.l.n.c(strA)) == null) {
                    return null;
                }
                int iIntValue = numC.intValue();
                c.l.f fVarA3 = iVar.c().a(2);
                if (fVarA3 == null || (strA2 = fVarA3.a()) == null || (fVarA = iVar.c().a(3)) == null || (strA3 = fVarA.a()) == null) {
                    return null;
                }
                return new m(iIntValue, strA2, strA3);
            }
        }

        public final String a(String str, String str2) {
            c.f.b.k.b(str, "command");
            c.f.b.k.b(str2, "rawOutput");
            a aVar = this;
            return aVar.c(str) ? aVar.d(str2) : str2;
        }

        public final boolean c(String str) {
            c.f.b.k.b(str, "command");
            return c.l.n.a(str, "service call", false, 2, (Object) null);
        }

        public final b.a.l<String> a(Context context, String str, int i, String str2, boolean z, boolean z2) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "component");
            c.f.b.k.b(str2, "host");
            b.a.l lVarC = (z ? com.joaomgcd.taskerm.b.d.a(this, context, i, str2, 0, 8, null) : a(context, z2)).c(new d(str));
            c.f.b.k.a((Object) lVarC, "listSingle.map { list ->…not found\")\n            }");
            return lVarC;
        }

        static final class d<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f5941a;

            d(String str) {
                this.f5941a = str;
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String apply(n nVar) {
                m next;
                String strC;
                c.f.b.k.b(nVar, "list");
                Iterator<m> it = nVar.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!c.f.b.k.a((Object) next.b(), (Object) this.f5941a));
                m mVar = next;
                if (mVar != null && (strC = mVar.c()) != null) {
                    return strC;
                }
                throw new RuntimeException("Service " + this.f5941a + " not found");
            }
        }

        public final String d(String str) {
            c.f.b.k.b(str, "raw");
            c.k.g gVarB = c.l.k.b(new c.l.k("(0x.+?:\\s+)?([\\da-f]{4,}\\s+)([\\da-f]{4,}\\s+)?([\\da-f]{4,}\\s+)?([\\da-f]{4,}\\s+)?"), str, 0, 2, null);
            StringBuilder sb = new StringBuilder();
            g gVar = new g(sb, new f(new e(new c.l.k("[A-Za-z\\s\\d\\n:;\\.-_]"))));
            Iterator itA = gVarB.a();
            while (itA.hasNext()) {
                c.l.i iVar = (c.l.i) itA.next();
                if (iVar.c().size() >= 6) {
                    c.l.f fVarA = iVar.c().a(1);
                    boolean z = ak.z(fVarA != null ? fVarA.a() : null);
                    gVar.a(iVar.c().a(2), z);
                    gVar.a(iVar.c().a(3), z);
                    gVar.a(iVar.c().a(4), z);
                    gVar.a(iVar.c().a(5), z);
                }
            }
            String string = sb.toString();
            c.f.b.k.a((Object) string, "sb.toString()");
            Integer numC = c.l.n.c(string);
            return numC != null ? String.valueOf(numC.intValue()) : string;
        }

        static final class e extends c.f.b.l implements c.f.a.b<String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.l.k f5942a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            e(c.l.k kVar) {
                super(1);
                this.f5942a = kVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(String str) {
                c.f.b.k.b(str, "char");
                return !this.f5942a.a(str) ? "" : str;
            }
        }
    }

    public int a() {
        return super.size();
    }

    public boolean a(m mVar) {
        return super.contains(mVar);
    }

    public int b(m mVar) {
        return super.indexOf(mVar);
    }

    public int c(m mVar) {
        return super.lastIndexOf(mVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof m) {
            return a((m) obj);
        }
        return false;
    }

    public boolean d(m mVar) {
        return super.remove(mVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof m) {
            return b((m) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof m) {
            return c((m) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof m) {
            return d((m) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
