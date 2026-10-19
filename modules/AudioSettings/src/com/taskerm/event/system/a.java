package com.joaomgcd.taskerm.event.system;

import android.content.Context;
import android.os.Bundle;
import com.joaomgcd.taskerm.action.system.ah;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.c<l, f, i> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    @Override // com.joaomgcd.taskerm.event.b
    public boolean n() {
        return true;
    }

    public a() {
        super(new al(2097, R.string.en_clipboard_changed, 90, "clipboard_changed", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 3, Integer.valueOf(R.string.pl_ignore_set_by_tasker), "true", 0));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public i c() {
        return new i(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public l d() {
        return new l(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public f a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new f(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5306;
    }

    @Override // com.joaomgcd.taskerm.event.c, com.joaomgcd.taskerm.event.b, com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, an anVar, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(anVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.event.b
    public boolean a(MonitorService monitorService, l lVar, l lVar2, an anVar) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(lVar, "configured");
        c.f.b.k.b(lVar2, "occurred");
        c.f.b.k.b(anVar, "occurredEvent");
        return (c.f.b.k.a((Object) lVar.getIgnoreTaskerSets(), (Object) true) ^ true) || !ah.f5732a.a();
    }
}
