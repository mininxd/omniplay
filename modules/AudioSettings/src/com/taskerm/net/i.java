package com.joaomgcd.taskerm.net;

/* JADX INFO: loaded from: classes.dex */
public final class i extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f7900a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Object obj, c.f.a.b<Object, String> bVar) {
        super(bVar.invoke(obj));
        c.f.b.k.b(obj, "error");
        c.f.b.k.b(bVar, "getErrorMessage");
        this.f7900a = obj;
    }

    public final Object a() {
        return this.f7900a;
    }
}
