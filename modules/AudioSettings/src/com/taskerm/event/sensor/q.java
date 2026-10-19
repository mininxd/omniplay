package com.joaomgcd.taskerm.event.sensor;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.state.sensor.OutputSleeping;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class q extends com.joaomgcd.taskerm.state.sensor.e<g, an, Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g f6746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final /* synthetic */ com.joaomgcd.taskerm.f.n f6747b;

    public q(g gVar) {
        c.f.b.k.b(gVar, "condition");
        this.f6747b = new com.joaomgcd.taskerm.f.n(gVar);
        this.f6746a = gVar;
    }

    @Override // com.joaomgcd.taskerm.state.sensor.e
    public void a(MonitorService monitorService, com.joaomgcd.taskerm.state.sensor.d dVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(dVar, "sleepingEvent");
        MonitorService monitorService2 = monitorService;
        com.joaomgcd.taskerm.event.b.a(this.f6746a, monitorService2, monitorService, new com.joaomgcd.taskerm.state.sensor.c(Integer.valueOf(dVar.a()), Integer.valueOf(dVar.b()), Integer.valueOf(dVar.c()), null, 8, null), new OutputSleeping(monitorService2, dVar), null, null, 48, null);
    }
}
