package com.joaomgcd.taskerm.p;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public enum b {
    Verbose("V"),
    Debug("D"),
    Info("I"),
    Warn("W"),
    Error("E"),
    Assert("A");

    private final String h;

    b(String str) {
        k.b(str, "value");
        this.h = str;
    }

    public final String a() {
        return this.h;
    }
}
