package com.joaomgcd.taskerm.state.a;

import android.content.Context;
import android.os.Bundle;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fm;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.state.e<com.joaomgcd.taskerm.state.a.b, d, com.joaomgcd.taskerm.state.a.a, e, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f8632a = {x.a(new v(x.a(d.class), "stateChecker", "getStateChecker()Lcom/joaomgcd/taskerm/state/display/StateCheckerDarkMode;")), x.a(new v(x.a(d.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/state/display/StateMonitorDarkMode;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8634d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f8635e;

    @Override // com.joaomgcd.taskerm.state.e
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public c c() {
        c.e eVar = this.f8633c;
        g gVar = f8632a[0];
        return (c) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public e b() {
        c.e eVar = this.f8634d;
        g gVar = f8632a[1];
        return (e) eVar.b();
    }

    public d() {
        super(new fm(188, R.string.an_dark_mode, 15, null, null, "dark_mode", new Object[0]));
        this.f8633c = f.a(new b());
        this.f8634d = f.a(new a());
        this.f8635e = 28;
    }

    static final class b extends l implements c.f.a.a<c> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            return new c(d.this);
        }
    }

    static final class a extends l implements c.f.a.a<e> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final e invoke() {
            return new e(d.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.state.a.b d() {
        return new com.joaomgcd.taskerm.state.a.b();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.state.a.a a(StateEdit stateEdit) {
        k.b(stateEdit, "hasArgsEdit");
        return new com.joaomgcd.taskerm.state.a.a(stateEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5207;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return Integer.valueOf(this.f8635e);
    }

    @Override // com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, fo foVar, Bundle bundle) {
        k.b(context, "context");
        k.b(foVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level1;
    }
}
