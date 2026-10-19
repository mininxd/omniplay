package com.joaomgcd.taskerm.helper.actions.execute;

import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bv;
import net.dinglisch.android.taskerm.cq;

/* JADX INFO: loaded from: classes.dex */
public final class r extends q<w> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<w> aVar) {
        super(executeService, cVar, aVar, null, 8, null);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(w wVar) {
        c.f.b.k.b(wVar, "input");
        net.dinglisch.android.taskerm.d dVarK = i().k(0);
        c.f.b.k.a((Object) dVarK, "action.getAppArg(ArgIndex.Action.SetAppArg.APP)");
        String strI = dVarK.i();
        c.f.b.k.a((Object) strI, "wantedPkg");
        Boolean boolB = b(strI).b();
        c.f.b.k.a((Object) boolB, "canApply(wantedPkg).blockingGet()");
        if (boolB.booleanValue() && q.a((q) this, strI, true, false, 4, (Object) null) != -3) {
            return new co();
        }
        if (!cq.c(h().getPackageManager()).contains(strI)) {
            return new cm("actionName: " + strI + ": not a valid SMS app");
        }
        if (bv.ar.b() && c.f.b.k.a((Object) strI, (Object) bv.ar.a(h()))) {
            return new co();
        }
        ExecuteService executeServiceH = h();
        Intent intent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
        intent.putExtra("package", strI);
        executeServiceH.a(intent, true);
        return new co();
    }
}
