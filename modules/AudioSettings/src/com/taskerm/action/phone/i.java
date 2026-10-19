package com.joaomgcd.taskerm.action.phone;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.helper.actions.c<o> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return i == 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<o, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        if (com.joaomgcd.taskerm.util.f.f9323b.c(u())) {
            return;
        }
        m(R.string.tip_end_call_only_on_p);
    }
}
