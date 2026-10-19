package com.joaomgcd.taskerm.event.phone;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.c<e, c, d> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    public a() {
        super(new al(2094, R.string.en_call_screened, 70, "call_screened", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_caller), "p", 1));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public d c() {
        return new d(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public e d() {
        return new e(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public c a(EventEdit eventEdit) {
        k.b(eventEdit, "hasArgsEdit");
        return new c(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5241;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 29;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, e eVar) {
        k.b(context, "context");
        return (String[]) c.a.d.a((Object[]) bt.f9101c.A(), (Object[]) bt.f9101c.l());
    }
}
