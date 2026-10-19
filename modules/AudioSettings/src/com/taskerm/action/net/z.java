package com.joaomgcd.taskerm.action.net;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class z extends com.joaomgcd.taskerm.helper.actions.c<aw> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<aw, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001d A[ORIG_RETURN, RETURN] */
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        aw awVarJ = j();
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                if (awVarJ.getMethod() != n.OAuth2) {
                    return true;
                }
                return false;
            case 9:
            case 10:
                if (awVarJ.getMethod() != n.UserPass) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        if (i == 1) {
            u().a(9, 10, 2, 3, 4, 5, 6, 8, 7);
        }
    }
}
