package com.joaomgcd.taskerm.action.display;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public class r extends com.joaomgcd.taskerm.action.setting.e<com.joaomgcd.taskerm.helper.actions.execute.w> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(ActionEdit actionEdit, com.joaomgcd.taskerm.action.setting.a<com.joaomgcd.taskerm.helper.actions.execute.w> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        if (com.joaomgcd.taskerm.util.f.f9323b.l()) {
            return;
        }
        com.joaomgcd.taskerm.helper.l.a(this, com.joaomgcd.taskerm.dialog.y.c(u(), R.string.warning_dialog_title, "Google have broken immersive mode for some devices on Android 11+, so it may not work on this device."), (b.a.d.f) null, 1, (Object) null);
    }
}
