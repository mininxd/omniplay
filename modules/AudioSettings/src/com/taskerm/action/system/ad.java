package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ad extends com.joaomgcd.taskerm.helper.actions.execute.l<ap> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ad(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ap, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ap apVar) {
        c.f.b.k.b(apVar, "input");
        ExecuteService executeServiceH = h();
        Boolean outputHashtags = apVar.getOutputHashtags();
        return new cq(true, new OutputGetMaterialYouColors(executeServiceH, outputHashtags != null ? outputHashtags.booleanValue() : true), null);
    }
}
