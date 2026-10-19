package com.joaomgcd.taskerm.state.sensor;

import android.annotation.TargetApi;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.state.f<c, l, b, k, i> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(l lVar) {
        super(lVar);
        c.f.b.k.b(lVar, "state");
    }

    @Override // com.joaomgcd.taskerm.state.f
    @TargetApi(28)
    public boolean a(MonitorService monitorService, c cVar, fo foVar) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(cVar, "input");
        c.f.b.k.b(foVar, "stateTaskerContext");
        return a().b().a(monitorService, cVar, foVar);
    }
}
