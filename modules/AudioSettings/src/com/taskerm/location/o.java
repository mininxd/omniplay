package com.joaomgcd.taskerm.location;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum o {
    Vehicle(0, R.string.in_vehicle),
    OnFoot(2, R.string.on_foot),
    Running(8, R.string.running),
    Walking(7, R.string.walking),
    Bicycle(1, R.string.on_bicycle),
    Still(3, R.string.still);

    private final int h;
    private final int i;

    o(int i, int i2) {
        this.h = i;
        this.i = i2;
    }

    public final int a() {
        return this.h;
    }

    public final int b() {
        return this.i;
    }
}
