package com.joaomgcd.taskerm.event.hardware;

import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.event.d<f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f6670a;

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, an anVar, f fVar) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        k.b(gaVar, "profile");
        k.b(anVar, "state");
        k.b(fVar, "input");
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, an anVar, f fVar) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        k.b(gaVar, "profile");
        k.b(anVar, "state");
        k.b(fVar, "input");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(a aVar) {
        super("FingerprintGesture", aVar);
        k.b(aVar, NotificationCompat.CATEGORY_EVENT);
        this.f6670a = aVar;
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        MyAccessibilityService.a(true);
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        MyAccessibilityService.a(false);
    }
}
