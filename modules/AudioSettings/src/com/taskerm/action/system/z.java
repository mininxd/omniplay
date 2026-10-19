package com.joaomgcd.taskerm.action.system;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class z extends com.joaomgcd.taskerm.helper.actions.c<au> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<au, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (au) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, au auVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(auVar, "input");
        a((com.joaomgcd.taskerm.helper.actions.b) bVar);
    }
}
