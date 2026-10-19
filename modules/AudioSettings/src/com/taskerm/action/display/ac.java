package com.joaomgcd.taskerm.action.display;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityTurnOnScreen;
import com.joaomgcd.taskerm.util.ch;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ac extends com.joaomgcd.taskerm.helper.actions.execute.l<ai> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ac(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ai, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ai aiVar) {
        int blockMs;
        c.f.b.k.b(aiVar, "input");
        if (new ch(h()).a()) {
            return cn.a((Object) null);
        }
        Integer blockMs2 = aiVar.getBlockMs();
        if (blockMs2 != null && blockMs2.intValue() == 0) {
            blockMs = 500;
        } else {
            blockMs = aiVar.getBlockMs();
        }
        cl clVarB = new GenericActionActivityTurnOnScreen(blockMs).run(h()).b();
        c.f.b.k.a((Object) clVarB, "GenericActionActivityTur…un(service).blockingGet()");
        return clVarB;
    }
}
