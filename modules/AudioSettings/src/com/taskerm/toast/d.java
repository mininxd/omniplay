package com.joaomgcd.taskerm.toast;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public enum d {
    TopLeft(51, new e(true, true)),
    Top(49, new e(false, true, 1, null)),
    TopRight(53, new e(true, true)),
    Right(21, new e(true, false, 2, null)),
    BottomRight(85, new e(true, true)),
    Bottom(81, new e(false, true, 1, null)),
    BottomLeft(83, new e(true, true)),
    Left(19, new e(true, false, 2, null));

    private final int j;
    private final e k;

    d(int i2, e eVar) {
        k.b(eVar, "offsets");
        this.j = i2;
        this.k = eVar;
    }

    public final int a() {
        return this.j;
    }

    public final e b() {
        return this.k;
    }
}
