package com.joaomgcd.taskerm.function;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ExceptionFunctions extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExceptionFunctions(String str) {
        super(str);
        k.b(str, "message");
    }
}
