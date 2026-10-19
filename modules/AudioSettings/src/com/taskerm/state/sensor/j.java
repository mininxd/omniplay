package com.joaomgcd.taskerm.state.sensor;

import android.annotation.TargetApi;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.event.sensor.n;
import com.joaomgcd.taskerm.f.p;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class j extends com.joaomgcd.taskerm.state.g<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8702a = {x.a(new v(x.a(j.class), "monitorMultipleSensors", "getMonitorMultipleSensors()Lcom/joaomgcd/taskerm/state/sensor/StateMonitorAnySensor$monitorMultipleSensors$2$1;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f8703b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8704c;

    private final a.AnonymousClass1 e() {
        c.e eVar = this.f8704c;
        c.j.g gVar = f8702a[0];
        return (a.AnonymousClass1) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(g gVar) {
        super("AnySensor", gVar);
        c.f.b.k.b(gVar, "state");
        this.f8704c = c.f.a(new a(gVar));
    }

    public final void a(Boolean bool) {
        this.f8703b = bool;
    }

    public final Boolean c() {
        return this.f8703b;
    }

    public final void d() {
        this.f8703b = (Boolean) null;
    }

    static final class a extends c.f.b.l implements c.f.a.a<AnonymousClass1> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ g f8706b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(g gVar) {
            super(0);
            this.f8706b = gVar;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.joaomgcd.taskerm.state.sensor.j$a$1] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final AnonymousClass1 invoke() {
            return new p<fo, String>(this.f8706b, "") { // from class: com.joaomgcd.taskerm.state.sensor.j.a.1
                @Override // com.joaomgcd.taskerm.f.p
                public void a(MonitorService monitorService, n nVar, com.joaomgcd.taskerm.inputoutput.a.b bVar, String str, fo foVar) {
                    c.f.b.k.b(monitorService, "context");
                    c.f.b.k.b(nVar, "input");
                    c.f.b.k.b(bVar, "output");
                    c.f.b.k.b(str, be.EXTRA_ID);
                    c.f.b.k.b(foVar, "hasArguments");
                    MonitorService monitorService2 = monitorService;
                    j.this.a(monitorService2, foVar, (Object) null, bVar);
                    boolean zA = a.this.f8706b.a((Context) monitorService2, foVar);
                    if (!c.f.b.k.a(j.this.c(), Boolean.valueOf(zA))) {
                        j.this.a(Boolean.valueOf(zA));
                        j.this.e(monitorService);
                    }
                }

                @Override // com.joaomgcd.taskerm.f.p
                public String a(Context context, ga gaVar, fo foVar) {
                    c.f.b.k.b(context, "context");
                    c.f.b.k.b(gaVar, "profile");
                    c.f.b.k.b(foVar, "state");
                    String strA = foVar.a(context, gaVar);
                    c.f.b.k.a((Object) strA, "state.getId(context, profile)");
                    return strA;
                }
            };
        }
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return e().c(monitorService);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        d();
        e().a(monitorService);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, fo foVar, n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(foVar, "state");
        c.f.b.k.b(nVar, "input");
        d();
        return e().a(monitorService, gaVar, foVar, nVar);
    }

    @Override // com.joaomgcd.taskerm.state.g, com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, fo foVar, n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(foVar, "state");
        c.f.b.k.b(nVar, "input");
        super.b(monitorService, gaVar, foVar, nVar);
        d();
        e().b(monitorService, gaVar, foVar, nVar);
    }
}
