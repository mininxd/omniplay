package com.joaomgcd.taskerm.action.display;

/* JADX INFO: loaded from: classes.dex */
public enum n {
    Off(0, -1),
    Portrait(0, 1),
    PortraitReverse(2, 9),
    Landscape(1, 0),
    LandscapeReverse(3, 8);

    private final int g;
    private final int h;

    n(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public final int a() {
        return this.g;
    }

    public final int b() {
        return this.h;
    }
}
