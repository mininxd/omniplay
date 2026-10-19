package com.joaomgcd.taskerm.f;

import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final <T extends e> void a(T[] tArr, String str, List<String> list, c.f.a.b<? super Integer, String> bVar) {
        c.f.b.k.b(tArr, "configurableTags");
        c.f.b.k.b(str, "match");
        c.f.b.k.b(list, "list");
        c.f.b.k.b(bVar, "getNameByCode");
        String lowerCase = str.toLowerCase();
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        for (T t : tArr) {
            if (t.a(lowerCase)) {
                int[] iArrA = t.a();
                ArrayList arrayList = new ArrayList(iArrA.length);
                for (int i : iArrA) {
                    arrayList.add(bVar.invoke(Integer.valueOf(i)));
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    aj.a((Collection<String>) list, (String) it.next());
                }
            }
        }
    }
}
