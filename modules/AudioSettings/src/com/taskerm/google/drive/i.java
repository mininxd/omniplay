package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class i extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7017a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(String str) {
        super(str + " didn't have any results");
        c.f.b.k.b(str, "query");
        this.f7017a = str;
    }
}
