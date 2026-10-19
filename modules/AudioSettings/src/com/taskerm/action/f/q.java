package com.joaomgcd.taskerm.action.f;

import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class q extends com.joaomgcd.taskerm.helper.actions.c<af> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean e(int i) {
        return i == 3;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean g(int i) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(ActionEdit actionEdit, h hVar) {
        super(actionEdit, hVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(hVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i == 0) {
            return true;
        }
        if (i == 6) {
            return !c.f.b.k.a((Object) j().getDoMaths(), (Object) true);
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i == 5) {
            a(6);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.helper.l
    public cl a(af afVar) {
        c.f.b.k.b(afVar, "inputFromActivity");
        if (afVar.getNamesArray() == null) {
            return cn.a("No variable names specified");
        }
        return new co();
    }
}
