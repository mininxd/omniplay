package com.joaomgcd.taskerm.event.sensor;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.b<n, h, k, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6711a = {x.a(new v(x.a(a.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/event/sensor/EventMonitorAnySensor;")), x.a(new v(x.a(a.class), "helperEditAnySensor", "getHelperEditAnySensor()Lcom/joaomgcd/taskerm/configurable/HelperAnySensor;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6712c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f6713d;

    private final com.joaomgcd.taskerm.f.h r() {
        c.e eVar = this.f6713d;
        c.j.g gVar = f6711a[1];
        return (com.joaomgcd.taskerm.f.h) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public boolean d(int i) {
        return false;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public c b() {
        c.e eVar = this.f6712c;
        c.j.g gVar = f6711a[0];
        return (c) eVar.b();
    }

    public a() {
        super(new al(2088, R.string.en_any_sensor, 50, "any_sensor", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_type), "t:1", 1, 0, Integer.valueOf(R.string.pl_min_interval), "100:15000:1000", 1, 0, Integer.valueOf(R.string.pl_min_interval_type), "", 1, 3, Integer.valueOf(R.string.convert_orientation), "", 0, 0));
        this.f6712c = c.f.a(new b());
        this.f6713d = c.f.a(C0143a.f6714a);
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public k c() {
        return new k(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public n d() {
        return new n(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public h a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new h(eventEdit, this);
    }

    static final class b extends c.f.b.l implements c.f.a.a<c> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            return new c(a.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5219;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.sensor.a$a, reason: collision with other inner class name */
    static final class C0143a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.f.h> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0143a f6714a = new C0143a();

        C0143a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.f.h invoke() {
            return new com.joaomgcd.taskerm.f.h("");
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, an anVar) {
        c.f.b.k.b(resources, "res");
        return r().a(resources, i);
    }

    @Override // com.joaomgcd.taskerm.event.b, com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, an anVar, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(anVar, "ssc");
        return a(anVar, context, bundle).getBatteryConsumptionLevel(context);
    }
}
