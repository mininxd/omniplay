package com.joaomgcd.taskerm.state.sensor;

import android.annotation.TargetApi;
import com.joaomgcd.taskerm.event.sensor.n;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.state.f<n, g, a, j, h> {
    @Override // com.joaomgcd.taskerm.state.f
    @TargetApi(28)
    public boolean a(MonitorService monitorService, n nVar, fo foVar) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(nVar, "input");
        c.f.b.k.b(foVar, "stateTaskerContext");
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(g gVar) {
        super(gVar);
        c.f.b.k.b(gVar, "state");
    }
}
