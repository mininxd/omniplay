package com.joaomgcd.taskerm.action.display;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class y extends com.joaomgcd.taskerm.helper.actions.execute.q<com.joaomgcd.taskerm.helper.actions.execute.w> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f5142a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<com.joaomgcd.taskerm.helper.actions.execute.w> aVar) {
        super(executeService, cVar, aVar, null, 8, null);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return this.f5142a;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(com.joaomgcd.taskerm.helper.actions.execute.w wVar) {
        c.f.b.k.b(wVar, "input");
        return a((Integer) com.joaomgcd.taskerm.helper.actions.execute.l.a(this, 0, Integer.class, false, 4, null));
    }
}
