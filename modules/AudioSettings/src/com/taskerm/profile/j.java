package com.joaomgcd.taskerm.profile;

import android.content.Context;
import android.os.Bundle;
import c.l.n;
import com.joaomgcd.taskerm.helper.r;
import com.joaomgcd.taskerm.util.aj;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ay;
import net.dinglisch.android.taskerm.dg;
import net.dinglisch.android.taskerm.dh;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.gw;
import net.dinglisch.android.taskerm.gx;

/* JADX INFO: loaded from: classes.dex */
public final class j extends ArrayList<com.joaomgcd.taskerm.profile.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8303a = new a(null);

    public static final j a(dg dgVar, String str) {
        return f8303a.a(dgVar, str);
    }

    public static final void a(StringBuilder sb, ay ayVar) {
        f8303a.a(sb, ayVar);
    }

    public static final void a(fx fxVar, fs fsVar) {
        f8303a.a(fxVar, fsVar);
    }

    public static final boolean a(String str, Bundle bundle) {
        return a.a(f8303a, str, bundle, false, 4, null);
    }

    public static final boolean a(String str, Bundle bundle, boolean z) {
        return f8303a.a(str, bundle, z);
    }

    public boolean a(com.joaomgcd.taskerm.profile.c cVar) {
        return super.contains(cVar);
    }

    public int b() {
        return super.size();
    }

    public int b(com.joaomgcd.taskerm.profile.c cVar) {
        return super.indexOf(cVar);
    }

    public int c(com.joaomgcd.taskerm.profile.c cVar) {
        return super.lastIndexOf(cVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.profile.c) {
            return a((com.joaomgcd.taskerm.profile.c) obj);
        }
        return false;
    }

    public boolean d(com.joaomgcd.taskerm.profile.c cVar) {
        return super.remove(cVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.profile.c) {
            return b((com.joaomgcd.taskerm.profile.c) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.profile.c) {
            return c((com.joaomgcd.taskerm.profile.c) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.profile.c) {
            return d((com.joaomgcd.taskerm.profile.c) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return b();
    }

    public j() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(List<com.joaomgcd.taskerm.profile.c> list) {
        super(list);
        c.f.b.k.b(list, "c");
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final void a(StringBuilder sb, ay ayVar) {
            c.f.b.k.b(sb, "sb");
            j jVarB = ayVar != null ? ayVar.b() : null;
            j jVar = jVarB;
            if (jVar == null || jVar.isEmpty()) {
                return;
            }
            sb.append(jVarB.a());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.j$a$a, reason: collision with other inner class name */
        static final class C0222a extends c.f.b.l implements c.f.a.b<dg, com.joaomgcd.taskerm.profile.c> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0222a f8304a = new C0222a();

            C0222a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.profile.c invoke(dg dgVar) {
                c.f.b.k.b(dgVar, "it");
                return com.joaomgcd.taskerm.profile.c.f8229a.a(dgVar);
            }
        }

        public final j a(dg dgVar, String str) {
            c.f.b.k.b(dgVar, "packedObject");
            c.f.b.k.b(str, "key");
            ArrayList arrayListA = aj.a(dgVar, str, C0222a.f8304a);
            if (arrayListA != null) {
                return new j(arrayListA);
            }
            return null;
        }

        public static /* synthetic */ boolean a(a aVar, String str, Bundle bundle, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = true;
            }
            return aVar.a(str, bundle, z);
        }

        public final boolean a(String str, Bundle bundle, boolean z) {
            List<h> listB;
            if ((str == null && z) || bundle == null || (listB = k.b(bundle)) == null) {
                return false;
            }
            boolean z2 = false;
            for (h hVar : listB) {
                String strE = hVar.e();
                z2 = !z || z2 || (str != null && n.b((CharSequence) str, (CharSequence) strE, false, 2, (Object) null));
                String strA = hVar.a();
                if (z2 && strA != null && (!hVar.f() || !bundle.containsKey(strE))) {
                    bundle.putString(strE, strA);
                }
            }
            return z2;
        }

        public final void a(fx fxVar, fs fsVar) {
            List listB;
            c.f.b.k.b(fxVar, "data");
            c.f.b.k.b(fsVar, "task");
            Bundle bundleQ = fsVar.q();
            if (bundleQ == null || (listB = k.b(bundleQ)) == null) {
                return;
            }
            Iterator it = listB.iterator();
            while (it.hasNext()) {
                String strE = ((h) it.next()).e();
                aj.a(fxVar, fsVar, strE, bundleQ.getString(strE));
            }
        }
    }

    public static final class b<T> implements Comparator<T> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return c.b.a.a(((com.joaomgcd.taskerm.profile.c) t).g().a(), ((com.joaomgcd.taskerm.profile.c) t2).g().a());
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.profile.c, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8305a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(com.joaomgcd.taskerm.profile.c cVar) {
            c.f.b.k.b(cVar, "it");
            return cVar.c();
        }
    }

    public final String a() {
        return "\nVariables: [ " + c.a.j.a(this, " ", null, null, 0, null, c.f8305a, 30, null) + " ]";
    }

    public final void a(dg dgVar, int i, String str) {
        c.f.b.k.b(dgVar, "packedObject");
        c.f.b.k.b(str, "key");
        if (isEmpty()) {
            return;
        }
        dgVar.a(str, this, i);
    }

    public final void a(Context context, Bundle bundle) {
        c.f.b.k.b(context, "context");
        if (bundle == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (com.joaomgcd.taskerm.profile.c cVar : c.a.j.a((Iterable) this, (Comparator) new b())) {
            String strH = cVar.h();
            d dVarG = cVar.g();
            arrayList.add(new h(dVarG.a(), dVarG.b(), strH, cVar.p()));
            bundle.putString(strH, gw.a(context, cVar.e(), false, bundle));
            gx.f13177a.a(bundle, strH, cVar.m() ? com.joaomgcd.taskerm.v.b.Auto : null, context);
        }
        k.b(bundle, arrayList);
    }

    public final void a(Context context, Collection<String> collection, int i) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(collection, "set");
        a(context, collection, aj.a(i, context, new Object[0]));
    }

    public final void a(Context context, Collection<String> collection, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(collection, "set");
        c.f.b.k.b(str, "source");
        for (com.joaomgcd.taskerm.profile.c cVar : this) {
            String strB = dh.b(dh.a.Action, str, new com.joaomgcd.taskerm.inputoutput.f(cVar.h(), cVar.i(), (String) null, 4, (c.f.b.g) null).toString());
            if (strB != null) {
                collection.add(strB);
            }
        }
    }

    public final boolean a(String str, String str2) {
        com.joaomgcd.taskerm.profile.c next;
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        Iterator<com.joaomgcd.taskerm.profile.c> it = iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!c.f.b.k.a((Object) next.h(), (Object) str));
        com.joaomgcd.taskerm.profile.c cVar = next;
        if (cVar == null) {
            return false;
        }
        if (cVar.p()) {
            return true;
        }
        if (str2 == null) {
            cVar.f();
            cVar.a("");
        } else {
            cVar.a(str2);
        }
        r.f7464c.a(str, str2);
        return true;
    }
}
