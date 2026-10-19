package com.joaomgcd.taskerm.event.app;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.event.c<q, i, m> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return false;
    }

    public b() {
        super(new al(2093, R.string.en_assistant_action, 95, "assistant_action", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.en_command), "t", 0));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public m c() {
        return new m(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public q d() {
        return new q(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public i a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new i(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5241;
    }
}
