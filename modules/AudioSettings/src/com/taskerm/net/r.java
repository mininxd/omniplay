package com.joaomgcd.taskerm.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.fc;

/* JADX INFO: loaded from: classes.dex */
public final class r implements com.joaomgcd.taskerm.action.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f7929a;

    static final class a extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7931b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(boolean z) {
            super(0);
            this.f7931b = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return com.joaomgcd.taskerm.action.k.a.a(r.this, this.f7931b, 100L, 2000L, 0L, 8, null).c() == null;
        }
    }

    public r(Context context) {
        c.f.b.k.b(context, "context");
        this.f7929a = context;
    }

    @Override // com.joaomgcd.taskerm.action.k
    public b.a.b a(boolean z, long j, long j2, long j3) {
        return com.joaomgcd.taskerm.action.k.a.a(this, z, j, j2, j3);
    }

    public final Context b() {
        return this.f7929a;
    }

    @Override // com.joaomgcd.taskerm.action.k
    public cl a(boolean z, com.joaomgcd.taskerm.action.i iVar) {
        c.f.b.k.b(iVar, "input");
        a aVar = new a(z);
        Boolean boolB = az.a(this.f7929a, true).b();
        c.f.b.k.a((Object) boolB, "Init.canRoot(context,true).blockingGet()");
        if (boolB.booleanValue() && a(z)) {
            return new co();
        }
        if (((String) aj.a((c.f.a.b) null, new b(z), 1, (Object) null)) != null) {
            return new co();
        }
        if (!ah.l(this.f7929a)) {
            return cn.a("Can't toggle mobile data without root, local Wifi ADB or permission to write secure settings");
        }
        String str = z ? "1" : "0";
        if (!com.joaomgcd.taskerm.securesettings.aa.a(this.f7929a, new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Global, "mobile_data", str, false, 0, 0, 48, null)).b().booleanValue()) {
            return cn.a("Can't toggle global setting");
        }
        if (com.joaomgcd.taskerm.securesettings.aa.a(this.f7929a, new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "mobile_data", str, false, 0, 0, 48, null)).b().booleanValue()) {
            return aVar.a() ? new co() : cn.a("Couldn't toggle mobile data on this device");
        }
        return cn.a("Can't toggle secure setting");
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7933b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(boolean z) {
            super(0);
            this.f7933b = z;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return (String) com.joaomgcd.taskerm.b.a.b(new com.joaomgcd.taskerm.b.a(r.this.b()), r.this.b(this.f7933b), 0, false, 0, null, false, 62, null).b();
        }
    }

    private final boolean a(boolean z) {
        return fc.a(true, 5000L, b(z), false) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String b(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("svc data ");
        sb.append(z ? "enable" : be.DISABLE_LABEL);
        return sb.toString();
    }

    @Override // com.joaomgcd.taskerm.action.k
    public boolean a() {
        Boolean boolAo = ah.ao(this.f7929a);
        if (boolAo != null) {
            return boolAo.booleanValue();
        }
        return false;
    }
}
