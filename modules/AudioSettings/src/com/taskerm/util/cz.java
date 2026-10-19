package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class cz<T> extends cx<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<T> f9250a;

    public cz(Class<T> cls) {
        c.f.b.k.b(cls, "clzz");
        this.f9250a = cls;
    }

    @Override // com.joaomgcd.taskerm.util.cx
    public T b(byte[] bArr, String str) {
        c.f.b.k.b(bArr, "receiver$0");
        c.f.b.k.b(str, "contentType");
        return (T) com.joaomgcd.taskerm.n.b.a().a(aj.a(bArr), (Class) this.f9250a);
    }
}
