package com.joaomgcd.taskerm.event.sensor;

import androidx.core.app.NotificationCompat;
import net.dinglisch.android.taskerm.MonitorService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.event.e<o> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f6721a;

    @Override // com.joaomgcd.taskerm.f.r
    protected int c() {
        return 17;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(f fVar) {
        super("SignificantMotion", fVar);
        c.f.b.k.b(fVar, NotificationCompat.CATEGORY_EVENT);
        this.f6721a = fVar;
    }

    @Override // com.joaomgcd.taskerm.f.r
    protected void a(MonitorService monitorService, Object obj) {
        c.f.b.k.b(monitorService, "receiver$0");
        com.joaomgcd.taskerm.event.b.a(this.f6721a, monitorService, monitorService, new o(), obj, null, null, 48, null);
    }
}
