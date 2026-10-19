package com.joaomgcd.taskerm.n;

import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<h> f7807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<j> f7808b;

    public final List<h> b() {
        return this.f7807a;
    }

    public final List<j> c() {
        return this.f7808b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return c.f.b.k.a(this.f7807a, eVar.f7807a) && c.f.b.k.a(this.f7808b, eVar.f7808b);
    }

    public int hashCode() {
        List<h> list = this.f7807a;
        int iHashCode = (list != null ? list.hashCode() : 0) * 31;
        List<j> list2 = this.f7808b;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "JSONChildren(values=" + this.f7807a + ", children=" + this.f7808b + ")";
    }

    public e(List<h> list, List<j> list2) {
        c.f.b.k.b(list, OutputAnySensor.VAR_VALUES);
        c.f.b.k.b(list2, "children");
        this.f7807a = list;
        this.f7808b = list2;
    }

    public final h a() {
        if (!this.f7807a.isEmpty()) {
            return (h) c.a.j.e((List) this.f7807a);
        }
        Iterator<T> it = this.f7808b.iterator();
        while (it.hasNext()) {
            List<e> listC = ((j) it.next()).c();
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listC, 10));
            Iterator<T> it2 = listC.iterator();
            while (it2.hasNext()) {
                arrayList.add(((e) it2.next()).a());
            }
            ArrayList arrayList2 = arrayList;
            if (!arrayList2.isEmpty()) {
                return (h) c.a.j.e((List) arrayList2);
            }
        }
        return null;
    }
}
