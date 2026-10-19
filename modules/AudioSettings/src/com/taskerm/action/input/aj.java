package com.joaomgcd.taskerm.action.input;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityShowKeyboardSelector;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class aj extends com.joaomgcd.taskerm.helper.actions.execute.l<aw> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aj(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<aw, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(aw awVar) {
        c.f.b.k.b(awVar, "input");
        if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
            InputMethodManager inputMethodManagerP = com.joaomgcd.taskerm.util.ah.p(h());
            if (inputMethodManagerP == null) {
                return cn.a("Couldn't get input method manager");
            }
            inputMethodManagerP.showInputMethodPicker();
            return new co();
        }
        cl clVarB = new GenericActionActivityShowKeyboardSelector().run(h()).b();
        c.f.b.k.a((Object) clVarB, "GenericActionActivitySho…un(service).blockingGet()");
        return clVarB;
    }
}
