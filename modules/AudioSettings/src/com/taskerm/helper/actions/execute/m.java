package com.joaomgcd.taskerm.helper.actions.execute;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class m extends q<w> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<w> aVar) {
        super(executeService, cVar, aVar, null, 8, null);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(w wVar) {
        c.f.b.k.b(wVar, "input");
        return a((Integer) l.a(this, 0, Integer.class, false, 4, null));
    }
}
