package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class l extends com.joaomgcd.taskerm.helper.actions.execute.l<p> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<p, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @TargetApi(24)
    public cl a(p pVar) {
        c.f.b.k.b(pVar, "input");
        Integer level = pVar.getLevel();
        if (level == null) {
            return cn.a("No volume set");
        }
        int iIntValue = level.intValue();
        com.joaomgcd.taskerm.a.d dVar = new com.joaomgcd.taskerm.a.d(h());
        com.joaomgcd.taskerm.a.c.C0091c c0091cB = dVar.b().b();
        h().a(iIntValue, net.dinglisch.android.taskerm.n.g(i().i()), i());
        if (c0091cB.a()) {
            try {
                dVar.c().b();
            } catch (Throwable th) {
                bn.c("E", "Couldn't toggle accesibility service back off", th);
            }
        }
        return new co();
    }
}
