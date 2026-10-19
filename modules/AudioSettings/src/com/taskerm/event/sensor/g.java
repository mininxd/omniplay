package com.joaomgcd.taskerm.event.sensor;

import android.content.Context;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.event.b<com.joaomgcd.taskerm.state.sensor.c, j, m, e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6727a = {x.a(new v(x.a(g.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/event/sensor/EventMonitorSleeping;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6728c;

    @Override // com.joaomgcd.taskerm.f.d
    public boolean d(int i) {
        return true;
    }

    @Override // com.joaomgcd.taskerm.event.b
    public boolean n() {
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public e b() {
        c.e eVar = this.f6728c;
        c.j.g gVar = f6727a[0];
        return (e) eVar.b();
    }

    public g() {
        super(new al(2096, R.string.sn_sleeping, 50, "sleeping", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, Integer.valueOf(R.string.pl_min_confidence), "0:100:85", 1, 0, Integer.valueOf(R.string.pl_max_light), "1:6:2", 1, 0, Integer.valueOf(R.string.pl_max_motion), "1:6:2", 1));
        this.f6728c = c.f.a(new a());
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public m c() {
        return new m(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.state.sensor.c d() {
        return new com.joaomgcd.taskerm.state.sensor.c(null, null, null, null, 15, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public j a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new j(eventEdit, this);
    }

    static final class a extends c.f.b.l implements c.f.a.a<e> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final e invoke() {
            return new e(g.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5257;
    }

    @Override // com.joaomgcd.taskerm.event.b, com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, an anVar, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(anVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level6;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.event.b
    public boolean a(MonitorService monitorService, com.joaomgcd.taskerm.state.sensor.c cVar, com.joaomgcd.taskerm.state.sensor.c cVar2, an anVar) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(cVar, "configured");
        c.f.b.k.b(cVar2, "occurred");
        c.f.b.k.b(anVar, "occurredEvent");
        Integer minConfidence = cVar2.getMinConfidence();
        int iIntValue = minConfidence != null ? minConfidence.intValue() : 85;
        Integer minConfidence2 = cVar.getMinConfidence();
        if (iIntValue >= (minConfidence2 != null ? minConfidence2.intValue() : 85)) {
            Integer maxLight = cVar2.getMaxLight();
            int iIntValue2 = maxLight != null ? maxLight.intValue() : 2;
            Integer maxLight2 = cVar.getMaxLight();
            if (iIntValue2 <= (maxLight2 != null ? maxLight2.intValue() : 2)) {
                Integer maxMotion = cVar2.getMaxMotion();
                int iIntValue3 = maxMotion != null ? maxMotion.intValue() : 2;
                Integer maxMotion2 = cVar.getMaxMotion();
                if (iIntValue3 <= (maxMotion2 != null ? maxMotion2.intValue() : 2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
