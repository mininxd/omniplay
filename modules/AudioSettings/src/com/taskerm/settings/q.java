package com.joaomgcd.taskerm.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class q {
    /* JADX INFO: Access modifiers changed from: private */
    public static final HashMap<String, k<?>> c() {
        return c.a.z.b(new c.k("i", new n()), new c.k("b", new l()), new c.k("l", new o()), new c.k("f", new m()), new c.k("s", new p()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HashMap<Class<? extends Object>, String> d() {
        HashMap<String, k<?>> mapC = c();
        ArrayList arrayList = new ArrayList(mapC.size());
        Iterator<Map.Entry<String, k<?>>> it = mapC.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, k<?>> next = it.next();
            ArrayList arrayListD = c.a.j.d(new c.k(next.getValue().a(), next.getKey()));
            for (Class<?> cls : next.getValue().b()) {
                arrayListD.add(new c.k(cls, next.getKey()));
            }
            arrayList.add(arrayListD);
        }
        Object[] array = c.a.j.a((Iterable) arrayList).toArray(new c.k[0]);
        if (array != null) {
            c.k[] kVarArr = (c.k[]) array;
            return c.a.z.b((c.k[]) Arrays.copyOf(kVarArr, kVarArr.length));
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
