package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ac extends com.joaomgcd.taskerm.helper.actions.execute.l<ao> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ac(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ao, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(ao aoVar) {
        c.f.b.k.b(aoVar, "input");
        return new cq(true, new OutputGetClipboard(com.joaomgcd.taskerm.e.b.a(h())), null);
    }
}
