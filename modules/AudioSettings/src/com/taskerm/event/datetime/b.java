package com.joaomgcd.taskerm.event.datetime;

import c.f.b.k;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.event.c<f, d, e> {
    public b() {
        super(new al(2084, R.string.en_alarm_changed, 10, "alarm_changed", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_package), "apppakc", 0));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public e c() {
        return new e(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public f d() {
        return new f(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public d a(EventEdit eventEdit) {
        k.b(eventEdit, "hasArgsEdit");
        return new d(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5213;
    }
}
