package com.joaomgcd.taskerm.action.phone;

import com.joaomgcd.taskerm.dialog.y;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.helper.actions.c<m> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<m, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        ActionEdit actionEditU = u();
        String string = u().getString(R.string.you_have_to_user_action_after_call_screened);
        c.f.b.k.a((Object) string, "actionEdit.getString(R.s…tion_after_call_screened)");
        com.joaomgcd.taskerm.helper.l.a(this, y.b(actionEditU, R.string.an_call_screening, string, 0, 8, (Object) null), (b.a.d.f) null, 1, (Object) null);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002a A[ORIG_RETURN, RETURN] */
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i == 0) {
            return true;
        }
        if (i == 1) {
            return false;
        }
        switch (j().getAllowType()) {
            case Disallow:
                if (i == 3) {
                    return true;
                }
                return false;
            case Allow:
                if (i != 3) {
                    return true;
                }
                return false;
            default:
                throw new c.i();
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        a(2, 3, 4, 5);
        if (i2 == 0) {
            b(3, false);
            return;
        }
        b(2, false);
        b(4, false);
        b(5, false);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (z && 4 == i) {
            m(R.string.on_some_phones_this_option_wont_work);
        }
        return super.a(i, z);
    }
}
