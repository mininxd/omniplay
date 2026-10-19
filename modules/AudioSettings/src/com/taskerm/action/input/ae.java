package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class ae extends com.joaomgcd.taskerm.helper.actions.execute.l<ap> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ae(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ap, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ap apVar) {
        c.f.b.k.b(apVar, "input");
        String title = apVar.getTitle();
        if (title == null) {
            return cn.a("Must set a title for the dialog");
        }
        String defaultInput = apVar.getDefaultInput();
        boolean z = true;
        Object objD = null;
        if (defaultInput != null && c.l.n.a(defaultInput, "%", false, 2, (Object) null) && gw.c(defaultInput)) {
            defaultInput = (String) null;
        }
        Integer inputTypeFlags = apVar.getInputTypeFlags();
        long timeoutNotNull = apVar.getTimeoutNotNull();
        cl clVarB = new GenericActionDialogInput(title, apVar.getText(), defaultInput, inputTypeFlags, apVar.getUseHtmlNotNull(), apVar.getPreselectInputNotNull(), timeoutNotNull).run(h()).b(timeoutNotNull, TimeUnit.SECONDS).b();
        if (!clVarB.b()) {
            c.f.b.k.a((Object) clVarB, "result");
            return clVarB;
        }
        if (clVarB.b() && (clVarB instanceof cq)) {
            cq cqVar = (cq) clVarB;
            if (cqVar.d() != null && (cqVar.d() instanceof String)) {
                objD = cqVar.d();
            }
        }
        String str = (String) objD;
        String str2 = str;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return cn.a("No input provided");
        }
        return cn.a(new bf(str));
    }
}
