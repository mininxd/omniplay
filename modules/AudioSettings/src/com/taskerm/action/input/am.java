package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(30)
public final class am extends com.joaomgcd.taskerm.helper.actions.execute.l<az> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public am(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<az, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(az azVar) {
        c.f.b.k.b(azVar, "input");
        PowerMenuActionEditAction powerMenuActionEditAction = (PowerMenuActionEditAction) com.joaomgcd.taskerm.util.aj.a(azVar.getAction(), "No action specified");
        StoredPowerMenuActions storedPowerMenuActionsC = m.c(h());
        StoredPowerMenuAction storedPowerMenuAction = azVar.getStoredPowerMenuAction();
        switch (powerMenuActionEditAction) {
            case AddEdit:
                storedPowerMenuAction.getControl(h()).b();
                storedPowerMenuActionsC.edit(storedPowerMenuAction);
                break;
            case Delete:
                storedPowerMenuActionsC.remove((Object) storedPowerMenuAction);
                break;
        }
        m.b(h(), storedPowerMenuActionsC);
        return new co();
    }
}
