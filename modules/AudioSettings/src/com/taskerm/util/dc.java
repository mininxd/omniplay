package com.joaomgcd.taskerm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class dc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9252a;

    public abstract Object a(String str);

    public abstract List<Object> b(String str);

    public dc(String str) {
        c.f.b.k.b(str, "stringToParse");
        this.f9252a = str;
    }

    protected final boolean d(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.l.n.b((CharSequence) str, (CharSequence) ".", false, 2, (Object) null);
    }

    protected final List<String> e(String str) {
        c.f.b.k.b(str, "receiver$0");
        List listB = c.l.n.b((CharSequence) c.l.n.a(str, "\\.", "#\"OIOPJFE)\"J#=J\"=)DMJ=()\"J=)I\"M)?D!\")?POMA\"", false, 4, (Object) null), new String[]{"."}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listB, 10));
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            arrayList.add(c.l.n.a((String) it.next(), "#\"OIOPJFE)\"J#=J\"=)DMJ=()\"J=)I\"M)?D!\")?POMA\"", ".", false, 4, (Object) null));
        }
        return arrayList;
    }
}
