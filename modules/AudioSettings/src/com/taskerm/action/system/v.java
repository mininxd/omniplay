package com.joaomgcd.taskerm.action.system;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class v extends com.joaomgcd.taskerm.helper.actions.c<ar> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ar, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ar) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ar arVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(arVar, "input");
        a((com.joaomgcd.taskerm.helper.actions.b) bVar);
    }
}
