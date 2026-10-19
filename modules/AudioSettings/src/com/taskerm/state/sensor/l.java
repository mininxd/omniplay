package com.joaomgcd.taskerm.state.sensor;

import android.content.Context;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fm;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class l extends com.joaomgcd.taskerm.state.e<c, l, com.joaomgcd.taskerm.state.sensor.b, k, i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8710a = {x.a(new v(x.a(l.class), "stateChecker", "getStateChecker()Lcom/joaomgcd/taskerm/state/sensor/StateCheckerSleeping;")), x.a(new v(x.a(l.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/state/sensor/StateMonitorSleeping;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8712d;

    @Override // com.joaomgcd.taskerm.f.d
    public boolean d(int i) {
        return true;
    }

    @Override // com.joaomgcd.taskerm.state.e
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public i c() {
        c.e eVar = this.f8711c;
        c.j.g gVar = f8710a[0];
        return (i) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public k b() {
        c.e eVar = this.f8712d;
        c.j.g gVar = f8710a[1];
        return (k) eVar.b();
    }

    public l() {
        super(new fm(192, R.string.sn_sleeping, 70, null, null, "sleeping", 5, Integer.valueOf(R.string.pl_output_variables), "", 0, 0, 0, Integer.valueOf(R.string.pl_min_confidence), "0:100:85", 1, 1, 0, Integer.valueOf(R.string.pl_max_light), "1:6:2", 1, 1, 0, Integer.valueOf(R.string.pl_max_motion), "1:6:2", 1, 1));
        this.f8711c = c.f.a(new b());
        this.f8712d = c.f.a(new a());
    }

    static final class b extends c.f.b.l implements c.f.a.a<i> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final i invoke() {
            return new i(l.this);
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<k> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final k invoke() {
            return new k(l.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public c d() {
        return new c(null, null, null, null, 15, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.state.sensor.b a(StateEdit stateEdit) {
        c.f.b.k.b(stateEdit, "hasArgsEdit");
        return new com.joaomgcd.taskerm.state.sensor.b(stateEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5257;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, c cVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.P();
    }

    @Override // com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, fo foVar, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(foVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level6;
    }
}
