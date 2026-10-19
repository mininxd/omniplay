package com.joaomgcd.taskerm.function;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class InputForFunction {
    private String[] rawInput = new String[0];

    public final String[] getRawInput() {
        return this.rawInput;
    }

    public final void setRawInput(String[] strArr) {
        k.b(strArr, "<set-?>");
        this.rawInput = strArr;
    }
}
