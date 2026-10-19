package com.joaomgcd.taskerm.n;

import c.l.n;
import com.joaomgcd.taskerm.util.dc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class k extends dc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f<?> f7812a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str) {
        super(str);
        c.f.b.k.b(str, "jsonString");
        this.f7812a = l.c(str);
    }

    @Override // com.joaomgcd.taskerm.util.dc
    public Object a(String str) {
        c.f.b.k.b(str, "key");
        if (!d(str)) {
            return this.f7812a.a(str);
        }
        f<?> fVarC = this.f7812a;
        Iterator<T> it = e(str).iterator();
        Object objA = null;
        while (it.hasNext()) {
            objA = fVarC != null ? fVarC.a((String) it.next()) : null;
            if (objA == null) {
                return null;
            }
            fVarC = l.c(objA);
        }
        return objA;
    }

    @Override // com.joaomgcd.taskerm.util.dc
    public List<Object> b(String str) {
        c.f.b.k.b(str, "key");
        if (c.f.b.k.a((Object) str, (Object) "=:=root=:=")) {
            Object objC = this.f7812a.c();
            return !(objC instanceof JSONArray) ? c.a.j.a() : l.d((JSONArray) objC);
        }
        if (!d(str)) {
            return a((List<? extends Object>) this.f7812a.c(str));
        }
        String strA = n.a(str, "\\.", ".", false, 4, (Object) null);
        List<h> listB = this.f7812a.b();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listB) {
            if (c.f.b.k.a((Object) ((h) obj).a(), (Object) strA)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((h) it.next()).b());
        }
        return a(arrayList3);
    }

    private final List<Object> a(List<? extends Object> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof JSONArray) {
                arrayList.addAll(l.d((JSONArray) obj));
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final String a() {
        List<e> listA = this.f7812a.a();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listA.iterator();
        while (it.hasNext()) {
            h hVarA = ((e) it.next()).a();
            if (hVarA != null) {
                arrayList.add(hVarA);
            }
        }
        h hVar = (h) c.a.j.f((List) arrayList);
        if (hVar != null) {
            return hVar.a();
        }
        return null;
    }
}
