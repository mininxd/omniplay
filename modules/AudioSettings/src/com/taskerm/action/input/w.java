package com.joaomgcd.taskerm.action.input;

import java.util.Map;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class w extends com.joaomgcd.taskerm.helper.actions.c<at> {

    static final class a extends c.f.b.l implements c.f.a.b<String, c.s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            w.this.u().a(9);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<at, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(8, new a()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return i == 9 && com.joaomgcd.taskerm.util.ak.A(j().getImage()) == null;
    }
}
