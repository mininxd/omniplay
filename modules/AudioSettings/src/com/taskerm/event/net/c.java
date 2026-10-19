package com.joaomgcd.taskerm.event.net;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.event.c<l, f, h> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    public c() {
        super(new al(2092, R.string.en_power_menu_shown, 90, "power_menu_shown", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public h c() {
        return new h(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public l d() {
        return new l(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public f a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new f(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5236;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 30;
    }
}
