package com.joaomgcd.taskerm.state.sensor;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.f.o;
import com.joaomgcd.taskerm.util.aj;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class f extends e<l, fo, String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ o f8693a;

    public f(l lVar) {
        c.f.b.k.b(lVar, "condition");
        this.f8693a = new o(lVar);
    }

    @Override // com.joaomgcd.taskerm.state.sensor.e
    public boolean a(MonitorService monitorService, c cVar, fo foVar) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(cVar, "input");
        c.f.b.k.b(foVar, "hasArguments");
        boolean zA = super.a(monitorService, cVar, foVar);
        if (zA) {
            MonitorService monitorService2 = monitorService;
            aj.a(foVar, monitorService2, "Sleep", new OutputSleeping(monitorService2, a((Context) monitorService2)));
        }
        return zA;
    }

    @Override // com.joaomgcd.taskerm.state.sensor.e
    public void a(MonitorService monitorService, d dVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(dVar, "sleepingEvent");
        monitorService.a((Integer) 192);
    }
}
