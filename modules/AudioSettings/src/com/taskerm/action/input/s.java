package com.joaomgcd.taskerm.action.input;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class s extends com.joaomgcd.taskerm.helper.actions.c<ap> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ap, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ap) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 5);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ap apVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(apVar, "input");
        super.a((com.joaomgcd.taskerm.helper.actions.b) bVar, iVar, apVar);
        if (bVar.a() != 5) {
            return;
        }
        b(com.joaomgcd.taskerm.dialog.u.a(u()), new a());
    }

    static final class a<T> implements b.a.d.f<Integer> {
        a() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Integer num) {
            c.f.b.k.b(num, "it");
            s.this.a(5, String.valueOf(num.intValue()));
        }
    }
}
