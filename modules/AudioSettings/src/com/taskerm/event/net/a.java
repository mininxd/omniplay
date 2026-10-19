package com.joaomgcd.taskerm.event.net;

import android.content.Context;
import android.content.res.Resources;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.c<i, e, g> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    public a() {
        super(new al(2080, R.string.en_bt_connect_disconnect, 45, "bt_connect_disconnect", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_name), "btn:1:?", 1, 1, Integer.valueOf(R.string.pl_address), "bta:1:?", 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, i iVar) {
        c.f.b.k.b(context, "context");
        return (String[]) c.a.d.a((Object[]) bt.f9101c.N(), (Object[]) bt.f9101c.f());
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public g c() {
        return new g(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public i d() {
        return new i(null, null, null, 7, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public e a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new e(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5189;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, an anVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.connection_events);
    }
}
