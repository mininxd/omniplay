package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ae extends com.joaomgcd.taskerm.helper.actions.execute.l<aq> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ae(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<aq, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(aq aqVar) {
        cq cqVarA;
        c.f.b.k.b(aqVar, "input");
        try {
            Object objB = g.a(h(), false, 1, null).b();
            c.f.b.k.a(objB, "service.getNextAlarm().blockingGet()");
            OutputQueryNextAlarm outputQueryNextAlarm = (OutputQueryNextAlarm) objB;
            Integer minutesDifference = aqVar.getMinutesDifference();
            cqVarA = new cq(true, g.a(outputQueryNextAlarm, minutesDifference != null ? minutesDifference.intValue() : 0), null);
        } catch (Exception e2) {
            cqVarA = cn.a(new com.joaomgcd.taskerm.util.ac(e2));
        }
        return cqVarA;
    }
}
