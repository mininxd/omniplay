package com.joaomgcd.taskerm.action.location;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.util.ac;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class f extends l<i> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<i, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(i iVar) {
        com.joaomgcd.taskerm.location.h hVarD;
        k.b(iVar, "input");
        cq<com.joaomgcd.taskerm.location.h, ac> cqVarB = com.joaomgcd.taskerm.location.a.a(h(), new com.joaomgcd.taskerm.location.b(iVar.getTitle(), iVar.getSelectRadius(), iVar.getLocationAndRadius(), iVar.getMapType())).b();
        if (cqVarB == null || (hVarD = cqVarB.d()) == null) {
            return cn.a("Location not picked");
        }
        return cn.a(new OutputPickLocation(hVarD));
    }
}
