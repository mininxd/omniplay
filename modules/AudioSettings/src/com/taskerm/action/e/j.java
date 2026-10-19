package com.joaomgcd.taskerm.action.e;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.helper.actions.execute.l<m> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<m, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(m mVar) {
        c.f.b.k.b(mVar, "input");
        List<d> functions$Tasker_5_15_14__marketNoTrialRelease = mVar.getFunctions$Tasker_5_15_14__marketNoTrialRelease();
        List<d> list = functions$Tasker_5_15_14__marketNoTrialRelease;
        if (list == null || list.isEmpty()) {
            return cn.a("No valid functions found in input");
        }
        cl clVarDoIt = (cl) null;
        for (d dVar : functions$Tasker_5_15_14__marketNoTrialRelease) {
            clVarDoIt = dVar.a().doIt(h(), dVar.b());
            if (!clVarDoIt.b()) {
                return clVarDoIt;
            }
        }
        return clVarDoIt != null ? clVarDoIt : new co();
    }
}
