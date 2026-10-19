package com.joaomgcd.taskerm.event.app;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.c<p, h, l> {
    public a() {
        super(new al(2078, R.string.en_app_changed, 120, "app_changed", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_package), "apppakc", 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, p pVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.M();
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public l c() {
        return new l(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public p d() {
        return new p(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public h a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new h(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5187;
    }
}
