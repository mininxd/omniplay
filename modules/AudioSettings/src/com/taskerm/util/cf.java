package com.joaomgcd.taskerm.util;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class cf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f9187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<String> f9188b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<String> f9189c;

    public cf(int i, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        c.f.b.k.b(arrayList, "out");
        c.f.b.k.b(arrayList2, "error");
        this.f9187a = i;
        this.f9188b = arrayList;
        this.f9189c = arrayList2;
    }

    public final ArrayList<String> e() {
        return this.f9188b;
    }

    public final ArrayList<String> f() {
        return this.f9189c;
    }

    public final boolean a() {
        return this.f9187a == 0;
    }

    public String toString() {
        return b() + '\n' + c();
    }

    public final String b() {
        return c.a.j.a(this.f9188b, "\n", null, null, 0, null, null, 62, null);
    }

    public final String c() {
        return c.a.j.a(this.f9189c, "\n", null, null, 0, null, null, 62, null);
    }

    public final cl d() {
        return a() ? new co() : cn.a(c());
    }
}
