package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestMissingPermissions;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;
import java.util.Arrays;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends com.joaomgcd.taskerm.helper.actions.execute.l<am> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<am, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(am amVar) {
        c.f.b.k.b(amVar, "input");
        String[] permissions = amVar.getPermissions();
        if (new bt(h(), 0, (String[]) Arrays.copyOf(permissions, permissions.length), 2, (c.f.b.g) null).g()) {
            return new co();
        }
        cl clVarB = new GenericActionActivityRequestMissingPermissions(permissions, amVar.getPromptIfNotSet()).run(h()).b();
        c.f.b.k.a((Object) clVarB, "GenericActionActivityReq…un(service).blockingGet()");
        return clVarB;
    }
}
