package com.joaomgcd.taskerm.action.input;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class r extends com.joaomgcd.taskerm.helper.actions.c<ao> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ao, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        boolean zA = com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 8, 9);
        if (com.joaomgcd.taskerm.util.f.f9323b.n() && zA) {
            return true;
        }
        return j().getType() != n.Biometric && zA;
    }
}
