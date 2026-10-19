package com.joaomgcd.taskerm.event.app;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.event.c<s, k, o> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    public f() {
        super(new al(2077, R.string.en_secondary_app_opened, 95, "secondary_app_opened", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public o c() {
        return new o(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public s d() {
        return new s(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public k a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new k(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5192;
    }
}
