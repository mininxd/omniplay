package com.joaomgcd.taskerm.action.net;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class aq extends com.joaomgcd.taskerm.helper.actions.execute.l<bd> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aq(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<bd, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @TargetApi(22)
    public cl a(bd bdVar) {
        int iIntValue;
        SubscriptionInfo subscriptionInfoA;
        c.f.b.k.b(bdVar, "input");
        com.joaomgcd.taskerm.net.t type = bdVar.getType();
        if (type == null) {
            return cn.a("No mobile network type received from input");
        }
        com.joaomgcd.taskerm.net.s sVar = new com.joaomgcd.taskerm.net.s(h());
        Integer numValueOf = (!com.joaomgcd.taskerm.util.f.f9323b.b() || (subscriptionInfoA = com.joaomgcd.taskerm.k.a.f7559a.a(h(), bdVar.getSimCard())) == null) ? null : Integer.valueOf(subscriptionInfoA.getSubscriptionId());
        if (numValueOf != null) {
            try {
                iIntValue = numValueOf.intValue();
            } catch (Throwable th) {
                if (com.joaomgcd.taskerm.util.f.f9323b.c(h())) {
                    return cn.a("Unfortunately Google disabled this feature for apps that target Android 10 and above (" + th.getClass().getSimpleName() + "). Check here for more info: https://tasker.joaoapps.com/userguide/en/target_api.html");
                }
                throw th;
            }
        } else {
            iIntValue = 1;
        }
        cl clVarB = sVar.a(type, iIntValue).b();
        if (clVarB.b()) {
            return new cq(true, new be(null), null);
        }
        c.f.b.k.a((Object) clVarB, "result");
        return clVarB;
    }
}
