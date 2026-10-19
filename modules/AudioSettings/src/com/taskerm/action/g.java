package com.joaomgcd.taskerm.action;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public class g extends l<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k f5299a;

    public boolean i_() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, k kVar, a<i, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(kVar, "toggler");
        c.f.b.k.b(aVar, "actionBase");
        this.f5299a = kVar;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(i iVar) {
        Boolean bool;
        c.f.b.k.b(iVar, "input");
        boolean zA = this.f5299a.a();
        boolean newValue = iVar.getToggle().getNewValue(zA);
        if (i_() && zA == newValue) {
            return cn.a(new j(newValue));
        }
        cl clVarA = this.f5299a.a(newValue, iVar);
        if (!clVarA.b()) {
            return clVarA;
        }
        if (!i_() && (clVarA instanceof cq) && (bool = (Boolean) ((cq) clVarA).d()) != null) {
            newValue = bool.booleanValue();
        }
        return cn.a(new j(newValue));
    }
}
