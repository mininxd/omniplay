package com.joaomgcd.taskerm.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final String[] a(com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(jVar, "receiver$0");
        List listA = c.a.j.a((Iterable) jVar, (Comparator) new a());
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(((com.joaomgcd.taskerm.inputoutput.f) it.next()).toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final class a<T> implements Comparator<T> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return c.b.a.a(((com.joaomgcd.taskerm.inputoutput.f) t).i(), ((com.joaomgcd.taskerm.inputoutput.f) t2).i());
        }
    }
}
