package com.joaomgcd.taskerm.util;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ce {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<cl> f9186a;

    public ce(ArrayList<cl> arrayList) {
        c.f.b.k.b(arrayList, "attempts");
        this.f9186a = arrayList;
    }

    public final ArrayList<cl> c() {
        return this.f9186a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ce(cl clVar) {
        this((ArrayList<cl>) c.a.j.d(clVar));
        c.f.b.k.b(clVar, "result");
    }

    public final boolean a() {
        return this.f9186a.size() > 0;
    }

    public final cl b() {
        if (a()) {
            return (cl) c.a.j.g((List) this.f9186a);
        }
        return null;
    }
}
