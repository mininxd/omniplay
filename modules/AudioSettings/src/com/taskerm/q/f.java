package com.joaomgcd.taskerm.q;

import android.content.Context;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class f<T> extends c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T f8350a;

    protected abstract T a(String str, T t);

    protected abstract void b(String str, T t);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context, T t, String str, String str2) {
        super(context, str, str2);
        k.b(context, "context");
        k.b(str, "suffix");
        k.b(str2, "prefName");
        this.f8350a = t;
    }

    public final T a(Object obj, c.j.g<?> gVar) {
        k.b(gVar, "property");
        return a(a(gVar), this.f8350a);
    }

    public final void a(Object obj, c.j.g<?> gVar, T t) {
        k.b(gVar, "property");
        b(a(gVar), t);
    }
}
