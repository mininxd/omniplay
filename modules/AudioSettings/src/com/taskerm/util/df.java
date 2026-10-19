package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class df {
    public static final Context a(String str) {
        c.f.b.k.b(str, "tag");
        Context contextA = TaskerApp.f8860b.a();
        if (contextA != null) {
            return contextA;
        }
        throw new RuntimeException("No context: " + str);
    }

    static final class a extends c.f.b.l implements c.f.a.a<Context> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f9256a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f9256a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Context invoke() {
            return df.a(this.f9256a);
        }
    }

    public static final Context b(String str) {
        c.f.b.k.b(str, "tag");
        return (Context) aj.a((c.f.a.b) null, new a(str), 1, (Object) null);
    }
}
