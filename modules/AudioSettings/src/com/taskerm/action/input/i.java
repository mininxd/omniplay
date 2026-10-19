package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.action.a<ax, z, ak> {
    public i() {
        super(new net.dinglisch.android.taskerm.m(328, R.string.an_keyboard, 55, 4, "keyboard", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_input), "t:5:?", 0, 1, 0, Integer.valueOf(R.string.pl_time_between_inputs), "50:999:500", 0, 1, 3, Integer.valueOf(R.string.pl_dont_restore_keyboard), "", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ax d() {
        return new ax(null, null, null, null, 15, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ak a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ak(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ax axVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public z a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new z(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5160;
    }
}
