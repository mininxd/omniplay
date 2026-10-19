package com.joaomgcd.taskerm.state.a;

import android.annotation.TargetApi;
import c.f.b.k;
import com.joaomgcd.taskerm.state.f;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class c extends f<b, d, a, e, c> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(d dVar) {
        super(dVar);
        k.b(dVar, "state");
    }

    @Override // com.joaomgcd.taskerm.state.f
    @TargetApi(28)
    public boolean a(MonitorService monitorService, b bVar, fo foVar) {
        k.b(monitorService, "context");
        k.b(bVar, "input");
        k.b(foVar, "stateTaskerContext");
        if (com.joaomgcd.taskerm.util.f.f9323b.m()) {
            return false;
        }
        return com.joaomgcd.taskerm.display.a.f6589b.a(monitorService);
    }
}
