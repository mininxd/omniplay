package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class ag extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ag(String str, Throwable th) {
        super(str, th);
        c.f.b.k.b(str, "message");
    }

    public /* synthetic */ ag(String str, Throwable th, int i, c.f.b.g gVar) {
        this(str, (i & 2) != 0 ? (Throwable) null : th);
    }
}
