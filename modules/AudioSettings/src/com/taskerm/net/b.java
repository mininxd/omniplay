package com.joaomgcd.taskerm.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.dm;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fc;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a implements com.joaomgcd.taskerm.action.k {
    private final String c() {
        return "AirplaneModeToggler";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context) {
        super(context, be.SET_AIRPLANE_MODE_LABEL);
        c.f.b.k.b(context, "context");
    }

    @Override // com.joaomgcd.taskerm.action.k
    public b.a.b a(boolean z, long j, long j2, long j3) {
        return com.joaomgcd.taskerm.action.k.a.a(this, z, j, j2, j3);
    }

    @Override // com.joaomgcd.taskerm.action.k
    public cl a(boolean z, com.joaomgcd.taskerm.action.i iVar) {
        c.k kVar;
        c.f.b.k.b(iVar, "input");
        Boolean boolB = az.a(b(), true).b();
        c.f.b.k.a((Object) boolB, "Init.canRoot(context,true).blockingGet()");
        if (boolB.booleanValue()) {
            kVar = new c.k("root", a(z));
        } else {
            kVar = new c.k("ADB Wifi", b(z));
        }
        String str = (String) kVar.c();
        cl clVar = (cl) kVar.d();
        bn.b(c(), "Toggling with " + str + " to " + z);
        return clVar;
    }

    private final cl a(boolean z) {
        String str = z ? "1" : "0";
        StringBuilder sb = new StringBuilder();
        sb.append("settings put global airplane_mode_on ");
        sb.append(str);
        if (!(fc.a(true, 8000L, sb.toString(), false) == 0)) {
            return cn.a("Couldn't put setting");
        }
        dm.a(200L);
        String str2 = z ? "true" : "false";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("am broadcast -a android.intent.action.AIRPLANE_MODE --ez state ");
        sb2.append(str2);
        if (!(fc.a(true, 8000L, sb2.toString(), false) == 0)) {
            bn.d(c(), "failed to broadcast status change");
            return cn.a("Couldn't toggle airplane mode with root: failed to broadcast status change");
        }
        return new co();
    }

    private final cl b(boolean z) {
        ah.b(b(), 333).b();
        String str = (String) com.joaomgcd.taskerm.b.p.a(this, new Object[]{Boolean.valueOf(z)}, 0, 2, null).b();
        if (c.f.b.k.a((Object) str, (Object) "0")) {
            return new co();
        }
        return cn.a("Couldn't toggle airplane mode with ADB Wifi: " + str);
    }

    @Override // com.joaomgcd.taskerm.action.k
    public boolean a() {
        Integer numC;
        String strB = com.joaomgcd.taskerm.securesettings.aa.b(b(), new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Global, "airplane_mode_on", false, 0, 0, 28, null)).b();
        return ((strB == null || (numC = c.l.n.c(strB)) == null) ? 0 : numC.intValue()) == 1;
    }
}
