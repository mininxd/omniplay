package com.joaomgcd.taskerm.event.sensor;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.f.b.v;
import c.f.b.x;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.event.d<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6716a = {x.a(new v(x.a(c.class), "monitorMultipleSensors", "getMonitorMultipleSensors()Lcom/joaomgcd/taskerm/event/sensor/EventMonitorAnySensor$monitorMultipleSensors$2$1;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.joaomgcd.taskerm.event.sensor.a f6718c;

    private final a.AnonymousClass1 d() {
        c.e eVar = this.f6717b;
        c.j.g gVar = f6716a[0];
        return (a.AnonymousClass1) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(com.joaomgcd.taskerm.event.sensor.a aVar) {
        super("AnySensor", aVar);
        c.f.b.k.b(aVar, NotificationCompat.CATEGORY_EVENT);
        this.f6718c = aVar;
        this.f6717b = c.f.a(new a());
    }

    public final com.joaomgcd.taskerm.event.sensor.a c() {
        return this.f6718c;
    }

    static final class a extends c.f.b.l implements c.f.a.a<AnonymousClass1> {
        a() {
            super(0);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.joaomgcd.taskerm.event.sensor.c$a$1] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final AnonymousClass1 invoke() {
            return new com.joaomgcd.taskerm.f.p<an, Integer>(c.this.c(), "") { // from class: com.joaomgcd.taskerm.event.sensor.c.a.1
                @Override // com.joaomgcd.taskerm.f.p
                public /* synthetic */ void a(MonitorService monitorService, n nVar, com.joaomgcd.taskerm.inputoutput.a.b bVar, Integer num, au auVar) {
                    a(monitorService, nVar, bVar, num.intValue(), (an) auVar);
                }

                public void a(MonitorService monitorService, n nVar, com.joaomgcd.taskerm.inputoutput.a.b bVar, int i, an anVar) {
                    c.f.b.k.b(monitorService, "context");
                    c.f.b.k.b(nVar, "input");
                    c.f.b.k.b(bVar, "output");
                    c.f.b.k.b(anVar, "hasArguments");
                    com.joaomgcd.taskerm.event.b.a(c.this.c(), monitorService, monitorService, nVar, null, Integer.valueOf(i), bVar, 8, null);
                }

                @Override // com.joaomgcd.taskerm.f.p
                public Integer a(Context context, ga gaVar, an anVar) {
                    c.f.b.k.b(context, "context");
                    c.f.b.k.b(gaVar, "profile");
                    c.f.b.k.b(anVar, "state");
                    return Integer.valueOf(gaVar.J());
                }
            };
        }
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return d().c(monitorService);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        d().a(monitorService);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, an anVar, n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(nVar, "input");
        return d().a(monitorService, gaVar, anVar, nVar);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, an anVar, n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(nVar, "input");
        d().b(monitorService, gaVar, anVar, nVar);
    }
}
