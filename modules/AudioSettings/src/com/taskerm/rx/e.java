package com.joaomgcd.taskerm.rx;

import c.q;

/* JADX INFO: loaded from: classes.dex */
public abstract class e<T> {
    protected abstract b.a.k.b<T> a();

    public final void a(T t) {
        a().b_(t);
    }

    public final b.a.h<T> b() {
        b.a.k.b<T> bVarA = a();
        if (bVarA != null) {
            return bVarA;
        }
        throw new q("null cannot be cast to non-null type io.reactivex.Observable<T>");
    }
}
