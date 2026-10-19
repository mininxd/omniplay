package com.joaomgcd.taskerm.action.input;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ak extends com.joaomgcd.taskerm.helper.actions.execute.l<ax> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ak(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ax, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ax axVar) {
        c.f.b.k.b(axVar, "input");
        Boolean dontRestoreKeyboard = axVar.getDontRestoreKeyboard();
        cl clVarB = com.joaomgcd.taskerm.keyboard.v.f7602a.a(h(), axVar.getInput(), axVar.getTimeBetweenInputs(), dontRestoreKeyboard != null ? true ^ dontRestoreKeyboard.booleanValue() : true).b();
        c.f.b.k.a((Object) clVarB, "KeyboardInputSenders.sen…reKeyboard).blockingGet()");
        return clVarB;
    }
}
