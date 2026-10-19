package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class t extends com.joaomgcd.taskerm.helper.actions.c<aq> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<aq, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, aq aqVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        Class cls;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(aqVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, aqVar, jVar);
        o mode = aqVar.getMode();
        if (mode != null) {
            switch (mode) {
                case Single:
                    cls = OutputDialogListSingle.class;
                    break;
                case Multiple:
                    cls = OutputDialogListMultiple.class;
                    break;
                default:
                    throw new c.i();
            }
            com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, cls, null, null, false, null, 60, null);
        }
    }

    private final Integer[] f() {
        return new Integer[]{4};
    }

    private final Integer[] v() {
        return new Integer[]{5, 11};
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        aq aqVarJ = j();
        if (c.a.d.b(f(), Integer.valueOf(i))) {
            if (aqVarJ.getMode() == o.Multiple) {
                return false;
            }
        } else if (!c.a.d.b(v(), Integer.valueOf(i)) || aqVarJ.getMode() == o.Single) {
            return false;
        }
        return true;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            arrayList.add(0);
            ArrayList arrayList2 = arrayList;
            c.a.j.a(arrayList2, f());
            c.a.j.a(arrayList2, v());
        }
        ActionEdit actionEditU = u();
        int[] iArrE = c.a.j.e((Collection<Integer>) arrayList);
        actionEditU.a(Arrays.copyOf(iArrE, iArrE.length));
    }
}
