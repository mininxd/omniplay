package com.joaomgcd.taskerm.action.e;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.helper.actions.execute.l<k> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<k, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(k kVar) {
        c.f.b.k.b(kVar, "input");
        String command = kVar.getCommand();
        if (command == null) {
            return cn.a("No command specified");
        }
        ah.v(h(), command);
        return new co();
    }
}
