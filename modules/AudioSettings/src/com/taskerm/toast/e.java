package com.joaomgcd.taskerm.toast;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f8781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f8782b;

    /* JADX WARN: Illegal instructions before constructor call */
    public e() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public e(boolean z, boolean z2) {
        this.f8781a = z;
        this.f8782b = z2;
    }

    public /* synthetic */ e(boolean z, boolean z2, int i, g gVar) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean a() {
        return this.f8781a;
    }

    public final boolean b() {
        return this.f8782b;
    }
}
