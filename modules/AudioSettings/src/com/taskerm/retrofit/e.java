package com.joaomgcd.taskerm.retrofit;

import b.a.l;
import d.t;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class e<T, TError> extends d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Class<TError> f8367b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.b<TError, String> f8368c;

    public final Class<TError> h() {
        return this.f8367b;
    }

    public final c.f.a.b<TError, String> i() {
        return this.f8368c;
    }

    public /* synthetic */ e(Class cls, String str, Class cls2, c.f.a.b bVar, l lVar, t[] tVarArr, k kVar, int i, c.f.b.g gVar) {
        this(cls, str, cls2, bVar, (i & 16) != 0 ? (l) null : lVar, (i & 32) != 0 ? new t[0] : tVarArr, (i & 64) != 0 ? f.b() : kVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public e(Class<T> cls, String str, Class<TError> cls2, c.f.a.b<? super TError, String> bVar, l<HashMap<String, String>> lVar, t[] tVarArr, k kVar) {
        super(cls, str, lVar, tVarArr, kVar);
        c.f.b.k.b(cls, "apiClass");
        c.f.b.k.b(str, "baseUrl");
        c.f.b.k.b(cls2, "errorClass");
        c.f.b.k.b(bVar, "errorMessageGetter");
        c.f.b.k.b(tVarArr, "interceptors");
        c.f.b.k.b(kVar, "timeout");
        this.f8367b = cls2;
        this.f8368c = bVar;
    }
}
