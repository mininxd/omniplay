package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class bp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final bq f9078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9079b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f9080c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9081d;

    public bp(Context context, bq bqVar, String str, String str2, String str3) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bqVar, "requester");
        c.f.b.k.b(str, "permission");
        c.f.b.k.b(str2, "action");
        c.f.b.k.b(str3, "description");
        this.f9078a = bqVar;
        this.f9079b = str;
        this.f9080c = str2;
        this.f9081d = str3;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ bp(Context context, bq bqVar, String str, String str2, String str3, int i, c.f.b.g gVar) {
        String strA = (i & 8) != 0 ? bqVar.a() : str2;
        if ((i & 16) != 0) {
            String strA2 = bu.a(str, context);
            if (strA2 == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            str3 = strA2.toLowerCase();
            c.f.b.k.a((Object) str3, "(this as java.lang.String).toLowerCase()");
        }
        this(context, bqVar, str, strA, str3);
    }

    public final String a() {
        return this.f9080c;
    }

    public final String b() {
        return this.f9081d;
    }
}
