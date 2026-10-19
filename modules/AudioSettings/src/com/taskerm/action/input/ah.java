package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fs;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class ah extends com.joaomgcd.taskerm.helper.actions.execute.l<at> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ah(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<at, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0071  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(at atVar) {
        Object objD;
        c.f.b.k.b(atVar, "input");
        String title = atVar.getTitle();
        long timeoutNotNull = atVar.getTimeoutNotNull();
        cl clVarB = new GenericActionDialogText(title, atVar.getText(), atVar.getPositiveLabel(), atVar.getNegativeLabel(), atVar.getNeutralLabel(), atVar.getUseHtmlNotNull(), timeoutNotNull, atVar.getImage(), atVar.getMaxImageSize()).run(h()).b(timeoutNotNull, TimeUnit.SECONDS).b();
        if (!clVarB.b()) {
            c.f.b.k.a((Object) clVarB, "result");
            return clVarB;
        }
        if (clVarB.b() && (clVarB instanceof cq)) {
            cq cqVar = (cq) clVarB;
            if (cqVar.d() == null || !(cqVar.d() instanceof String)) {
                objD = null;
            } else {
                objD = cqVar.d();
            }
        } else {
            objD = null;
        }
        String str = (String) objD;
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return cn.a("No button pressed");
        }
        fs fsVarF = f();
        if (fsVarF != null) {
            fsVarF.h(str);
        }
        return cn.a(new OutputDialogText(str));
    }
}
