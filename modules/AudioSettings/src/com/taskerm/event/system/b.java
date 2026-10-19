package com.joaomgcd.taskerm.event.system;

import android.content.Context;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.event.b<m, g, j, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6748a = {x.a(new v(x.a(b.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/event/system/EventMonitorLogCat;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6749c;

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public c b() {
        c.e eVar = this.f6749c;
        c.j.g gVar = f6748a[0];
        return (c) eVar.b();
    }

    public b() {
        super(new al(2085, R.string.en_logcat_entry, 90, "logcat_entry", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_component), "t:1", 1, 1, Integer.valueOf(R.string.word_filter), "t:8:?", 1, 3, Integer.valueOf(R.string.pl_grep_for_filter), "", 0));
        this.f6749c = c.f.a(new a());
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public j c() {
        return new j(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public m d() {
        return new m(null, null, null, null, 15, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public g a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new g(eventEdit, this);
    }

    static final class a extends c.f.b.l implements c.f.a.a<c> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            return new c(b.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5214;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, m mVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.J();
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

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 24;
    }
}
