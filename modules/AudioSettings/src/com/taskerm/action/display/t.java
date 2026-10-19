package com.joaomgcd.taskerm.action.display;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class t extends com.joaomgcd.taskerm.helper.actions.c<ah> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ah, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        boolean zA = super.a(i, z);
        if (!z) {
            return true;
        }
        if (i != 1 || com.joaomgcd.taskerm.util.f.f9323b.f()) {
            return zA;
        }
        com.joaomgcd.taskerm.helper.j.a(u().f10455a, com.joaomgcd.taskerm.dialog.y.d(u(), 28), (b.a.d.f) null, 2, (Object) null);
        return false;
    }
}
