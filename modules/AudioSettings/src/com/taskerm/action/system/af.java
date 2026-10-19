package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.SensorTasker;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class af extends com.joaomgcd.taskerm.helper.actions.execute.l<ar> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public af(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ar, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(ar arVar) {
        c.f.b.k.b(arVar, "input");
        Sensor sensorA = com.joaomgcd.taskerm.util.aj.a((Context) h(), arVar.getTypeString());
        if (sensorA == null) {
            return cn.a("Sensor " + arVar.getTypeString() + " not found");
        }
        return cn.a(new SensorTasker(h(), sensorA));
    }
}
