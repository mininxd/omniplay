package com.joaomgcd.taskerm.helper.actions.execute;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.dh;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.gj;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateApi"})
public final class t extends l<x> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<x, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(x xVar) {
        c.f.b.k.b(xVar, "input");
        if (!gj.a()) {
            return new cm("Torch API not available");
        }
        dh dhVar = new dh(h());
        net.dinglisch.android.taskerm.e eVarH = i().h(0);
        c.f.b.k.a((Object) eVarH, "action.getBoolArg(0)");
        return !((Boolean) dh.a(dhVar, eVarH, (String) null, (Integer) null, 6, (Object) null).b()).booleanValue() ? new cm("Unknown error") : new co();
    }
}
