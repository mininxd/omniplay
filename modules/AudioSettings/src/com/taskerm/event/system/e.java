package com.joaomgcd.taskerm.event.system;

import android.content.Context;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.event.b<n, h, k, d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6771a = {x.a(new v(x.a(e.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/event/system/EventMonitorTick;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6772c;

    @Override // com.joaomgcd.taskerm.f.d
    public boolean d(int i) {
        return false;
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public d b() {
        c.e eVar = this.f6772c;
        c.j.g gVar = f6771a[0];
        return (d) eVar.b();
    }

    public e() {
        super(new al(2095, R.string.en_tick, 90, "tick", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_min_interval), "t:1", 1));
        this.f6772c = c.f.a(new a());
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public k c() {
        return new k(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public n d() {
        return new n(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public h a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new h(eventEdit, this);
    }

    static final class a extends c.f.b.l implements c.f.a.a<d> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final d invoke() {
            return new d(e.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5254;
    }

    @Override // com.joaomgcd.taskerm.event.b, com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, an anVar, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(anVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level15;
    }

    @Override // com.joaomgcd.taskerm.event.b
    public Boolean a(int i, String str, String str2) {
        return true;
    }
}
