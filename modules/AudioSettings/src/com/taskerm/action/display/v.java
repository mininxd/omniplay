package com.joaomgcd.taskerm.action.display;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.tasker.InputDisplayDensity;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class v extends com.joaomgcd.taskerm.helper.actions.execute.l<ad> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ad, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ad adVar) {
        int iIntValue;
        c.f.b.k.b(adVar, "input");
        Integer num = (Integer) com.joaomgcd.taskerm.helper.actions.execute.l.a(this, 0, Integer.class, false, 4, null);
        if (num == null) {
            return new cm("No display size value to set");
        }
        m mVar = (m) ((Enum) com.joaomgcd.taskerm.util.aj.a(num.intValue(), m.class));
        com.joaomgcd.taskerm.y.c cVarA = com.joaomgcd.taskerm.y.b.f9454a.a();
        String manual = adVar.getManual();
        Integer numC = manual != null ? c.l.n.c(manual) : null;
        if (numC == null) {
            iIntValue = c.g.a.a(((double) cVarA.a()) * mVar.a());
        } else {
            iIntValue = numC.intValue();
        }
        if (com.joaomgcd.taskerm.util.f.f9323b.m() || com.joaomgcd.taskerm.util.g.d(h())) {
            try {
                cVarA.a(iIntValue);
                return new co();
            } catch (Throwable unused) {
                cl clVarB = net.dinglisch.android.tasker.d.f10445a.a(h(), new InputDisplayDensity(iIntValue)).b();
                c.f.b.k.a((Object) clVarB, "TaskerBackCompat.display…y(density)).blockingGet()");
                return clVarB;
            }
        }
        cl clVarB2 = net.dinglisch.android.tasker.d.f10445a.a(h(), new InputDisplayDensity(iIntValue)).b();
        c.f.b.k.a((Object) clVarB2, "TaskerBackCompat.display…y(density)).blockingGet()");
        return clVarB2;
    }
}
