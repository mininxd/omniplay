package com.joaomgcd.taskerm.helper.actions.execute;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.action.a<InputRingerMode, j, p> {
    public g() {
        super(new net.dinglisch.android.taskerm.m(313, R.string.an_ringer_mode, 20, 1, "ringer_mode", 0, Integer.valueOf(R.string.pl_mode), "", 0, 1, 3, Integer.valueOf(R.string.pl_ignore_dnd), "", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public InputRingerMode d() {
        return new InputRingerMode(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public j a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new j(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public p a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new p(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.actionedit_array_action_choices_ringer_mode);
    }
}
