package com.joaomgcd.taskerm.event.sensor;

import c.f.b.v;
import c.f.b.x;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.event.b<o, i, l, d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6724a = {x.a(new v(x.a(f.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/event/sensor/EventMonitorSignificantMotion;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6725c;

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public d b() {
        c.e eVar = this.f6725c;
        c.j.g gVar = f6724a[0];
        return (d) eVar.b();
    }

    public f() {
        super(new al(2083, R.string.en_significant_motion, 50, "significant_motion", null, null, false, false, new Object[0]));
        this.f6725c = c.f.a(new a());
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public l c() {
        return new l(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public o d() {
        return new o();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public i a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new i(eventEdit, this);
    }

    static final class a extends c.f.b.l implements c.f.a.a<d> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final d invoke() {
            return new d(f.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5211;
    }
}
