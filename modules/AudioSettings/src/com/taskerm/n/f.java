package com.joaomgcd.taskerm.n;

import c.l.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T f7809a;

    public abstract Object a(String str);

    public abstract List<e> a();

    public abstract void a(String str, ArrayList<Object> arrayList);

    public f(T t) {
        this.f7809a = t;
    }

    public final T c() {
        return this.f7809a;
    }

    public final List<Object> c(String str) {
        c.f.b.k.b(str, "key");
        ArrayList<Object> arrayList = new ArrayList<>();
        a(str, arrayList);
        return arrayList;
    }

    public final List<h> b() {
        ArrayList<h> arrayList = new ArrayList<>();
        b("", arrayList);
        return arrayList;
    }

    private final void b(String str, ArrayList<h> arrayList) {
        String str2;
        for (e eVar : a()) {
            List<h> listB = eVar.b();
            List<j> listC = eVar.c();
            Iterator<T> it = listB.iterator();
            while (true) {
                if (it.hasNext()) {
                    h hVar = (h) it.next();
                    boolean zA = n.a((CharSequence) str);
                    String strA = hVar.a();
                    if (zA && strA == null) {
                        break;
                    }
                    if (zA) {
                        str2 = strA;
                    } else if (strA == null) {
                        str2 = str;
                    } else {
                        str2 = str + '.' + strA;
                    }
                    arrayList.add(new h(str2, hVar.b()));
                } else {
                    for (j jVar : listC) {
                        String strA2 = jVar.a();
                        if (strA2 != null) {
                            if (!n.a((CharSequence) str)) {
                                strA2 = str + '.' + strA2;
                            }
                            jVar.b().b(strA2, arrayList);
                        }
                    }
                    break;
                }
            }
        }
    }

    public String toString() {
        return String.valueOf(this.f7809a);
    }

    protected final Object a(c.k.g<? extends f<?>> gVar, String str) {
        c.f.b.k.b(gVar, "receiver$0");
        c.f.b.k.b(str, "key");
        Iterator<? extends f<?>> itA = gVar.a();
        while (itA.hasNext()) {
            Object objA = itA.next().a(str);
            if (objA != null) {
                return objA;
            }
        }
        return null;
    }

    protected final Object a(c.k.g<? extends f<?>> gVar, String str, ArrayList<Object> arrayList) {
        c.f.b.k.b(gVar, "receiver$0");
        c.f.b.k.b(str, "key");
        c.f.b.k.b(arrayList, "read");
        Iterator<? extends f<?>> itA = gVar.a();
        while (itA.hasNext()) {
            itA.next().a(str, arrayList);
        }
        return null;
    }
}
