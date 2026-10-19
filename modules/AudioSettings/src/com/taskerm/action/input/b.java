package com.joaomgcd.taskerm.action.input;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<ap, s, ae> {
    public b() {
        super(new net.dinglisch.android.taskerm.m(360, R.string.an_dialog_input, 55, 4, "dialog_input", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_title), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_text), "t:3:?", 0, 1, 1, Integer.valueOf(R.string.pl_default_input), "t:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_close_after_seconds), "5:120:30", 0, 0, 1, Integer.valueOf(R.string.pl_input_type), "t:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_use_html), "", 0, 0, 3, Integer.valueOf(R.string.pl_pre_select_input), "", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5198;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ap d() {
        return new ap(null, null, null, null, null, null, null, null, 255, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ae a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ae(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public s a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new s(actionEdit, this);
    }
}
