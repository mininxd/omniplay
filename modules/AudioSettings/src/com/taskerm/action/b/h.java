package com.joaomgcd.taskerm.action.b;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.action.g {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<com.joaomgcd.taskerm.action.i, ?, ?> aVar) {
        super(executeService, cVar, bundle, new com.joaomgcd.taskerm.c.a(executeService), aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.action.g
    public boolean i_() {
        return !com.joaomgcd.taskerm.util.f.f9323b.c(h());
    }
}
