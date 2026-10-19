package com.joaomgcd.taskerm.event.sensor;

import androidx.core.app.NotificationCompat;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.event.d<com.joaomgcd.taskerm.state.sensor.c> implements com.joaomgcd.taskerm.f.k<com.joaomgcd.taskerm.state.sensor.c, an> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g f6722a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final /* synthetic */ q f6723b;

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return this.f6723b.a(monitorService);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, an anVar, com.joaomgcd.taskerm.state.sensor.c cVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(cVar, "input");
        return this.f6723b.a(monitorService, gaVar, anVar, cVar);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        this.f6723b.b(monitorService);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, an anVar, com.joaomgcd.taskerm.state.sensor.c cVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(cVar, "input");
        this.f6723b.b(monitorService, gaVar, anVar, cVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar) {
        super("Sleeping", gVar);
        c.f.b.k.b(gVar, NotificationCompat.CATEGORY_EVENT);
        this.f6723b = new q(gVar);
        this.f6722a = gVar;
    }
}
