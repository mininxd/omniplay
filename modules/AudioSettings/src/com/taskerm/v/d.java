package com.joaomgcd.taskerm.v;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class d<TInput> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.b<TInput, Boolean> f9423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String[] f9424b;

    /* JADX WARN: Multi-variable type inference failed */
    public d(c.f.a.b<? super TInput, Boolean> bVar, String... strArr) {
        k.b(bVar, "getIsStructured");
        k.b(strArr, "varsToStructure");
        this.f9423a = bVar;
        this.f9424b = strArr;
    }

    public final c.f.a.b<TInput, Boolean> a() {
        return this.f9423a;
    }

    public final String[] b() {
        return this.f9424b;
    }
}
