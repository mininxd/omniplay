package com.joaomgcd.taskerm.b;

import android.app.Activity;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Comparator;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class j extends ArrayList<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5908a = new a(null);

    public static final class a extends d<j> {
        @Override // com.joaomgcd.taskerm.b.d
        protected String a() {
            return "cmd overlay list";
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.j$a$a, reason: collision with other inner class name */
        static final class C0108a extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Activity f5909a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f5910b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ boolean f5911c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f5912d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ String f5913e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0108a(Activity activity, boolean z, boolean z2, int i, String str) {
                super(0);
                this.f5909a = activity;
                this.f5910b = z;
                this.f5911c = z2;
                this.f5912d = i;
                this.f5913e = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                ac acVarA = ac.a.a(ac.f6217a, this.f5909a, R.string.getting_services, false, 4, null);
                try {
                    j jVarB = (this.f5910b ? j.f5908a.a(this.f5909a, this.f5911c) : d.a(j.f5908a, this.f5909a, this.f5912d, this.f5913e, 0, 8, null)).b();
                    ac.a(acVarA, null, 1, null);
                    if (jVarB.isEmpty()) {
                        throw new RuntimeException("Device doesn't support overlays");
                    }
                    Activity activity = this.f5909a;
                    int i = R.string.ml_scene_type_overlay;
                    c.f.b.k.a((Object) jVarB, "adbOverlayList");
                    i iVar = (i) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(activity, i, jVarB, true, C0109a.f5914a, null, null, null, true, null, null, null, null, null, null, null, 65248, null)).b()).b();
                    if (iVar == null) {
                        throw new RuntimeException("No overlay selected");
                    }
                    boolean z = false;
                    com.joaomgcd.taskerm.dialog.x xVar = (com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.d(this.f5909a, aj.a(R.string.enable_or_disable, this.f5909a, new Object[0]), aj.a(R.string.enable_or_disable_value, this.f5909a, iVar.b()), 0, 8, null).b();
                    if (xVar.d()) {
                        z = true;
                    } else if (!xVar.e()) {
                        throw new RuntimeException("Didn't select enable or disable");
                    }
                    String strA = iVar.a(z);
                    ac.a(acVarA, null, 1, null);
                    return strA;
                } catch (Throwable th) {
                    ac.a(acVarA, null, 1, null);
                    throw th;
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.j$a$a$a, reason: collision with other inner class name */
            static final class C0109a extends c.f.b.l implements c.f.a.b<i, com.joaomgcd.taskerm.dialog.w> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final C0109a f5914a = new C0109a();

                C0109a() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.w invoke(i iVar) {
                    c.f.b.k.b(iVar, "it");
                    return new com.joaomgcd.taskerm.dialog.w(iVar.a(), null, false, 6, null);
                }
            }
        }

        @Override // com.joaomgcd.taskerm.b.c
        public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(str, "host");
            return com.joaomgcd.taskerm.rx.i.b(new C0108a(activity, z, z2, i, str));
        }

        @Override // com.joaomgcd.taskerm.b.c
        public com.joaomgcd.taskerm.util.i b() {
            return new com.joaomgcd.taskerm.util.j("Overlays");
        }

        @Override // com.joaomgcd.taskerm.b.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public j a(String str) {
            c.f.b.k.b(str, "raw");
            c.k.g gVarB = c.l.k.b(new c.l.k("(\\[x?\\]\\s)?.+"), str, 0, 2, null);
            c.f.b.w.d dVar = new c.f.b.w.d();
            dVar.f2028a = "";
            c.k.g gVarA = c.k.h.a(c.k.h.c(c.k.h.b(gVarB, new c(dVar))), new b());
            j jVar = new j();
            c.a.j.a(jVar, gVarA);
            return jVar;
        }

        static final class c extends c.f.b.l implements c.f.a.b<c.l.i, i> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.b.w.d f5915a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(c.f.b.w.d dVar) {
                super(1);
                this.f5915a = dVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r6v5, types: [T, java.lang.String] */
            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final i invoke(c.l.i iVar) {
                String strA;
                c.f.b.k.b(iVar, "match");
                String strB = iVar.b();
                if (strB == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                ?? string = c.l.n.b((CharSequence) strB).toString();
                if (((CharSequence) string).length() == 0) {
                    return null;
                }
                boolean z = !c.l.n.a((String) string, "[", false, 2, (Object) null);
                boolean zA = c.l.n.a((String) string, "[x", false, 2, (Object) null);
                if (z) {
                    this.f5915a.f2028a = string;
                    return null;
                }
                if (zA) {
                    strA = ak.a((String) string, "[x]");
                } else {
                    strA = ak.a((String) string, "[ ]");
                }
                if (strA == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                return new i(zA, c.l.n.b((CharSequence) strA).toString(), (String) this.f5915a.f2028a);
            }
        }

        public static final class b<T> implements Comparator<T> {
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String strD = ((i) t).d();
                if (strD == null) {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = strD.toLowerCase();
                c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                String str = lowerCase;
                String strD2 = ((i) t2).d();
                if (strD2 == null) {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase2 = strD2.toLowerCase();
                c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                return c.b.a.a(str, lowerCase2);
            }
        }
    }

    public int a() {
        return super.size();
    }

    public boolean a(i iVar) {
        return super.contains(iVar);
    }

    public int b(i iVar) {
        return super.indexOf(iVar);
    }

    public int c(i iVar) {
        return super.lastIndexOf(iVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof i) {
            return a((i) obj);
        }
        return false;
    }

    public boolean d(i iVar) {
        return super.remove(iVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof i) {
            return b((i) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof i) {
            return c((i) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof i) {
            return d((i) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
