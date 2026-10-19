package com.joaomgcd.taskerm.state.sensor;

import android.annotation.TargetApi;
import androidx.core.app.NotificationCompat;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class k extends com.joaomgcd.taskerm.state.g<c> implements com.joaomgcd.taskerm.f.k<c, fo> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l f8708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final /* synthetic */ f f8709b;

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return this.f8709b.a(monitorService);
    }

    public boolean a(MonitorService monitorService, c cVar, fo foVar) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(cVar, "input");
        c.f.b.k.b(foVar, "hasArguments");
        return this.f8709b.a(monitorService, cVar, foVar);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, fo foVar, c cVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(foVar, "state");
        c.f.b.k.b(cVar, "input");
        return this.f8709b.a(monitorService, gaVar, foVar, cVar);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        this.f8709b.b(monitorService);
    }

    @Override // com.joaomgcd.taskerm.state.g, com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, fo foVar, c cVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(foVar, "state");
        c.f.b.k.b(cVar, "input");
        this.f8709b.b(monitorService, gaVar, foVar, cVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(l lVar) {
        super("Sleeping", lVar);
        c.f.b.k.b(lVar, "state");
        this.f8709b = new f(lVar);
        this.f8708a = lVar;
    }
}
