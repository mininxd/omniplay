package com.joaomgcd.taskerm.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7934a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(s.class), "iTelephony", "getITelephony()Ljava/lang/Object;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(s.class), "transactionCodeForNetworkType", "getTransactionCodeForNetworkType()Ljava/lang/Integer;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7936c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f7937d;

    /* JADX INFO: Access modifiers changed from: private */
    public final Object b() {
        c.e eVar = this.f7935b;
        c.j.g gVar = f7934a[0];
        return eVar.b();
    }

    public final Integer a() {
        c.e eVar = this.f7936c;
        c.j.g gVar = f7934a[1];
        return (Integer) eVar.b();
    }

    static final class c extends c.f.b.l implements c.f.a.a<Integer> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            return ((Number) Cdo.a((Class) s.this.b().getClass().getDeclaringClass(), "TRANSACTION_setPreferredNetworkType", (String) null, true, 2, (Object) null)).intValue();
        }
    }

    public s(Context context) {
        c.f.b.k.b(context, "context");
        this.f7937d = context;
        this.f7935b = c.f.a(new b());
        this.f7936c = c.f.a(new c());
    }

    static final class b extends c.f.b.l implements c.f.a.a<Object> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public final Object invoke() {
            Object systemService = s.this.f7937d.getSystemService("phone");
            Method methodB = Cdo.b((Class) systemService.getClass(), "getITelephony", "itelephonymobilenetwork", true);
            methodB.setAccessible(true);
            return methodB.invoke(systemService, new Object[0]);
        }
    }

    public final b.a.l<cl> a(t tVar, int i) {
        c.f.b.k.b(tVar, "type");
        return com.joaomgcd.taskerm.rx.i.a(new a(i, tVar));
    }

    static final class a extends c.f.b.l implements c.f.a.a<b.a.l<cl>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7939b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ t f7940c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i, t tVar) {
            super(0);
            this.f7939b = i;
            this.f7940c = tVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cl> invoke() {
            ah.b(s.this.f7937d, 363).c();
            return com.joaomgcd.taskerm.b.g.a("service call phone " + s.this.a() + " i32 " + this.f7939b + " i32 " + this.f7940c.a(), s.this.f7937d);
        }
    }
}
