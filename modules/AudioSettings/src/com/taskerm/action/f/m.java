package com.joaomgcd.taskerm.action.f;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class m extends com.joaomgcd.taskerm.helper.actions.c<aa> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(ActionEdit actionEdit, a aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i == 0) {
            return true;
        }
        if (j().getMergeType() == k.Formatted) {
            if (i == 3) {
                return true;
            }
        } else if (i == 4) {
            return true;
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        super.a(i, i2);
        a(3, 4);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, String str) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, str);
        if (com.joaomgcd.taskerm.util.ak.A(l(5)) != null) {
            return;
        }
        a(5, "%");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean g(int i) {
        return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 4, 3, 6);
    }
}
