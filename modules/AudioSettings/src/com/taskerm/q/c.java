package com.joaomgcd.taskerm.q;

import android.content.Context;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8348b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f8349c;

    public c(Context context, String str, String str2) {
        k.b(context, "context");
        k.b(str, "suffix");
        k.b(str2, "prefName");
        this.f8347a = context;
        this.f8348b = str;
        this.f8349c = str2;
    }

    protected final Context a() {
        return this.f8347a;
    }

    public final String a(c.j.g<?> gVar) {
        k.b(gVar, "property");
        return a.f8338a.a(gVar, this.f8348b, this.f8349c);
    }
}
