package com.joaomgcd.taskerm.action.input;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<ar, v, ag> {
    public d() {
        super(new net.dinglisch.android.taskerm.m(390, R.string.an_dialog_pick_input, 55, 4, "dialog_pick_input", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_type), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_title), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_text), "t:3:?", 0, 1, 1, Integer.valueOf(R.string.pl_default_input), "t:1:?", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5248;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ar d() {
        return new ar(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ag a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ag(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public v a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new v(actionEdit, this);
    }
}
