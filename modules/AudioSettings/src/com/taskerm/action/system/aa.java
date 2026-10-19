package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends com.joaomgcd.taskerm.helper.actions.execute.l<ak> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ak, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, java.lang.String] */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(ak akVar) {
        Integer numC;
        c.f.b.k.b(akVar, "input");
        com.joaomgcd.taskerm.b.a aVar = new com.joaomgcd.taskerm.b.a(h());
        c.f.b.w.d dVar = new c.f.b.w.d();
        ?? A = com.joaomgcd.taskerm.util.ak.A(akVar.getCommand());
        if (A == 0) {
            return cn.a("No command entered");
        }
        dVar.f2028a = A;
        String strA = com.joaomgcd.taskerm.util.ak.A(akVar.getHost());
        if (strA == null) {
            strA = "localhost";
        }
        String str = strA;
        String port = akVar.getPort();
        int iA = (port == null || (numC = c.l.n.c(port)) == null) ? com.joaomgcd.taskerm.settings.ad.a(h()) : numC.intValue();
        Integer timeout = akVar.getTimeout();
        int iIntValue = (timeout != null ? timeout.intValue() : 1) * 1000;
        com.joaomgcd.taskerm.b.q qVar = (com.joaomgcd.taskerm.b.q) com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new a(dVar), 1, (Object) null);
        if (qVar != null) {
            com.joaomgcd.taskerm.b.p pVarA = qVar.a();
            Object[] objArrB = qVar.b();
            ?? A2 = pVarA.a(Arrays.copyOf(objArrB, objArrB.length));
            if (A2 != 0) {
                dVar.f2028a = A2;
            }
        }
        String strB = aVar.a(new com.joaomgcd.taskerm.b.a.C0106a(str, iA, (String) dVar.f2028a, iIntValue, true, akVar.getToggleDebuggingNotNull(), akVar.getResultEncoding())).b(((long) iIntValue) + ((long) 3000), TimeUnit.MILLISECONDS).b();
        com.joaomgcd.taskerm.b.n.a aVar2 = com.joaomgcd.taskerm.b.n.f5931a;
        String str2 = (String) dVar.f2028a;
        c.f.b.k.a((Object) strB, "result");
        String strA2 = aVar2.a(str2, strB);
        if (strA2 != null) {
            return cn.a(new OutputADBWifi(c.l.n.b((CharSequence) strA2).toString()));
        }
        throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.b.q> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f5730b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(c.f.b.w.d dVar) {
            super(0);
            this.f5730b = dVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.b.q invoke() {
            return (com.joaomgcd.taskerm.b.q) com.joaomgcd.taskerm.b.p.a.a(com.joaomgcd.taskerm.b.p.f5955a, aa.this.h(), (String) this.f5730b.f2028a, 0, null, false, false, 60, null).b();
        }
    }
}
