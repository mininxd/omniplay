package com.joaomgcd.taskerm.pattern;

import c.a.j;
import c.a.y;
import c.a.z;
import c.f.b.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, String> f8190b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<String> f8191c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a.a.a.b.c f8192d;

    public c(a.a.a.b.c cVar) {
        k.b(cVar, "matcher");
        this.f8192d = cVar;
        this.f8189a = this.f8192d.d();
        c.i.c cVarB = c.i.d.b(1, this.f8192d.a() + 1);
        ArrayList arrayList = new ArrayList(j.a(cVarB, 10));
        Iterator<Integer> it = cVarB.iterator();
        while (it.hasNext()) {
            int iB = ((y) it).b();
            arrayList.add(new c.k(this.f8192d.c(iB), this.f8192d.b(iB)));
        }
        this.f8190b = z.a(arrayList);
        c.i.c cVarB2 = c.i.d.b(1, this.f8192d.a() + 1);
        ArrayList arrayList2 = new ArrayList(j.a(cVarB2, 10));
        Iterator<Integer> it2 = cVarB2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(this.f8192d.b(((y) it2).b()));
        }
        this.f8191c = arrayList2;
    }

    public final String a() {
        return this.f8189a;
    }

    public final Map<String, String> b() {
        return this.f8190b;
    }

    public final List<String> c() {
        return this.f8191c;
    }
}
