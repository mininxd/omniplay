package com.joaomgcd.taskerm.a;

import android.content.Context;
import b.a.d.g;
import b.a.l;
import b.a.p;
import c.a.j;
import c.f.b.k;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<com.joaomgcd.taskerm.a.a> f4896a = c.a.d.l(com.joaomgcd.taskerm.a.a.values());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f4897b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final List<m> f4898c;

    static {
        List<com.joaomgcd.taskerm.a.a> list = f4896a;
        ArrayList arrayList = new ArrayList(j.a((Iterable) list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((com.joaomgcd.taskerm.a.a) it.next()).c().f12876c));
        }
        f4897b = j.e((Collection<Integer>) arrayList);
        List<com.joaomgcd.taskerm.a.a> list2 = f4896a;
        ArrayList arrayList2 = new ArrayList(j.a((Iterable) list2, 10));
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((com.joaomgcd.taskerm.a.a) it2.next()).c());
        }
        f4898c = arrayList2;
    }

    public static final boolean a(int i) {
        return c.a.d.a(f4897b, i);
    }

    public static final List<com.joaomgcd.taskerm.a.a> a(Set<Integer> set) {
        k.b(set, "taskerActionCodes");
        List<com.joaomgcd.taskerm.a.a> list = f4896a;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (set.contains(Integer.valueOf(((com.joaomgcd.taskerm.a.a) obj).c().f12876c))) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final com.joaomgcd.taskerm.a.a b(int i) {
        Object next;
        Iterator<T> it = f4896a.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((com.joaomgcd.taskerm.a.a) next).c().f12876c == i) {
                return (com.joaomgcd.taskerm.a.a) next;
            }
        }
        next = null;
        return (com.joaomgcd.taskerm.a.a) next;
    }

    public static final com.joaomgcd.taskerm.a.a c(int i) {
        Object next;
        Iterator<T> it = f4896a.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((com.joaomgcd.taskerm.a.a) next).a() == i) {
                return (com.joaomgcd.taskerm.a.a) next;
            }
        }
        next = null;
        return (com.joaomgcd.taskerm.a.a) next;
    }

    public static final Integer d(int i) {
        com.joaomgcd.taskerm.a.a aVarB = b(i);
        if (aVarB != null) {
            return Integer.valueOf(aVarB.a());
        }
        return null;
    }

    public static final boolean a(int i, int i2) {
        if (i == 8 && f.f9323b.f()) {
            return true;
        }
        if (i == 15 && f.f9323b.h()) {
            return true;
        }
        com.joaomgcd.taskerm.a.a aVarC = c(i);
        if (aVarC != null) {
            return aVarC.a(i2);
        }
        return false;
    }

    static final class a<T, R> implements g<Throwable, p<? extends c.C0091c>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f4899a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<c.C0091c> apply(Throwable th) {
            k.b(th, "it");
            bn.a("AccessibilityGlobalAction", "Couldn't toggle accessibility service", th);
            return l.a(new c.C0091c(false, 1, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c.C0091c b(l<c.C0091c> lVar) {
        return lVar.d(a.f4899a).b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.a.b$b, reason: collision with other inner class name */
    static final class C0090b extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f4900a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4901b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0090b(Context context, int i) {
            super(0);
            this.f4900a = context;
            this.f4901b = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            d dVar = new d(this.f4900a);
            c.C0091c c0091cB = b.b(dVar.b());
            if (!MyAccessibilityService.c()) {
                throw new RuntimeException("Accessibility service not running");
            }
            boolean zB = MyAccessibilityService.b(this.f4901b);
            if (c0091cB.a()) {
                b.b(dVar.c());
            }
            return zB;
        }
    }

    public static final l<Boolean> a(Context context, int i) {
        k.b(context, "context");
        return i.b(new C0090b(context, i));
    }
}
