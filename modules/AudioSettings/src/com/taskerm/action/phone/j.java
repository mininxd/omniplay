package com.joaomgcd.taskerm.action.phone;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.call.ServiceCallScreening;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.helper.actions.execute.l<m> {
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
        return ServiceCallScreening.f6014a.a(h(), mVar);
    }
}
