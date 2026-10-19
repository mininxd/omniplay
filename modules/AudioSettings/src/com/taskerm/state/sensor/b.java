package com.joaomgcd.taskerm.state.sensor;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.state.d<c> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(StateEdit stateEdit, l lVar) {
        super(stateEdit, lVar);
        c.f.b.k.b(stateEdit, "stateEdit");
        c.f.b.k.b(lVar, "stateSleeping");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(fo foVar, String str) {
        c.f.b.k.b(foVar, "configurable");
        super.a(foVar, str);
        m(R.string.sleep_condition_tip_on_select);
    }
}
