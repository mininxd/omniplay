package com.joaomgcd.taskerm.util;

import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes.dex */
final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9344a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9345b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f9346c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Integer f9347d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Integer f9348e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final PackageManager f9349f;
    private final n g;

    public o(PackageManager packageManager, n nVar) {
        c.f.b.k.b(packageManager, "packageManager");
        c.f.b.k.b(nVar, "args");
        this.f9349f = packageManager;
        this.g = nVar;
        this.f9344a = this.g.a();
        this.f9345b = this.g.b();
        String strC = this.g.c();
        this.f9346c = strC == null ? ah.a(this.f9349f, this.f9345b) : strC;
        this.f9347d = this.g.d();
        this.f9348e = this.g.e();
    }

    public final String a() {
        return this.f9344a;
    }

    public final String b() {
        return this.f9345b;
    }

    public final String c() {
        return this.f9346c;
    }

    public final Integer d() {
        return this.f9347d;
    }

    public final Integer e() {
        return this.f9348e;
    }
}
