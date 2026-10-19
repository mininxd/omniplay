package com.joaomgcd.taskerm.state.sensor;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.event.sensor.n;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fm;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.state.e<n, g, com.joaomgcd.taskerm.state.sensor.a, j, h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8694a = {x.a(new v(x.a(g.class), "stateChecker", "getStateChecker()Lcom/joaomgcd/taskerm/state/sensor/StateCheckerAnySensor;")), x.a(new v(x.a(g.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/state/sensor/StateMonitorAnySensor;")), x.a(new v(x.a(g.class), "helperEditAnySensor", "getHelperEditAnySensor()Lcom/joaomgcd/taskerm/configurable/HelperAnySensor;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8695c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8696d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f8697e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f8698f;

    private final com.joaomgcd.taskerm.f.h q() {
        c.e eVar = this.f8698f;
        c.j.g gVar = f8694a[2];
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

    @Override // com.joaomgcd.taskerm.f.d
    public boolean l() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.state.e
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public h c() {
        c.e eVar = this.f8695c;
        c.j.g gVar = f8694a[0];
        return (h) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public j b() {
        c.e eVar = this.f8696d;
        c.j.g gVar = f8694a[1];
        return (j) eVar.b();
    }

    public g() {
        super(new fm(190, R.string.en_any_sensor, 70, null, null, "any_sensor", 5, Integer.valueOf(R.string.pl_output_variables), "", 0, 0, 1, Integer.valueOf(R.string.pl_type), "t:1", 0, 1, 0, Integer.valueOf(R.string.pl_min_interval), "100:15000:1000", 0, 1, 0, Integer.valueOf(R.string.pl_min_interval_type), "", 0, 1, 3, Integer.valueOf(R.string.convert_orientation), "", 0, 0));
        this.f8695c = c.f.a(new c());
        this.f8696d = c.f.a(new b());
        this.f8697e = 28;
        this.f8698f = c.f.a(a.f8699a);
    }

    static final class c extends c.f.b.l implements c.f.a.a<h> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final h invoke() {
            return new h(g.this);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<j> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final j invoke() {
            return new j(g.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public n d() {
        return new n(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.state.sensor.a a(StateEdit stateEdit) {
        c.f.b.k.b(stateEdit, "hasArgsEdit");
        return new com.joaomgcd.taskerm.state.sensor.a(stateEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5219;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return Integer.valueOf(this.f8697e);
    }

    @Override // com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, fo foVar, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(foVar, "ssc");
        return a(foVar, context, bundle).getBatteryConsumptionLevel(context);
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.f.h> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8699a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.f.h invoke() {
            return new com.joaomgcd.taskerm.f.h("");
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, fo foVar) {
        c.f.b.k.b(resources, "res");
        return q().a(resources, i);
    }
}
