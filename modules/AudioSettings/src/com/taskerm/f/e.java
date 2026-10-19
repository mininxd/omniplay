package com.joaomgcd.taskerm.f;

import com.joaomgcd.taskerm.util.ak;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6811a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f6812b;

    public e(String str, int... iArr) {
        c.f.b.k.b(str, "tag");
        c.f.b.k.b(iArr, "codes");
        this.f6811a = str;
        this.f6812b = iArr;
    }

    public final int[] a() {
        return this.f6812b;
    }

    public final boolean a(String str) {
        c.f.b.k.b(str, "match");
        return ak.e(this.f6811a, str);
    }
}
