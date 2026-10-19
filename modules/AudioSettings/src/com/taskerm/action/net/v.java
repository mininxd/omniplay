package com.joaomgcd.taskerm.action.net;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class v extends com.joaomgcd.taskerm.helper.actions.c<as> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<as, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (as) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 2);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, as asVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(asVar, "input");
        a(bVar, 2);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        super.a(i, i2);
        a(3);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return j().getActionNonNull() == o.Unbond && i == 3;
    }
}
