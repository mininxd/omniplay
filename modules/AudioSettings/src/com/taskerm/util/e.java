package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f9320a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f9321b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, boolean z) {
            super(0);
            this.f9320a = context;
            this.f9321b = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            com.joaomgcd.taskerm.settings.ad.a(this.f9320a, this.f9321b);
            ah.a(this.f9320a, ActivitySecondaryApp.class, this.f9321b).b();
        }
    }

    public static final b.a.b a(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        return com.joaomgcd.taskerm.rx.i.d(new a(context, z));
    }
}
