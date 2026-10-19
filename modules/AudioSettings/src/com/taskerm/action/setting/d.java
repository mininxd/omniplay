package com.joaomgcd.taskerm.action.setting;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(29)
public final class d extends com.joaomgcd.taskerm.action.a<l, g, i> {
    public d() {
        super(new net.dinglisch.android.taskerm.m(383, R.string.an_settings_panel, 30, 0, "settings_panel", 0, Integer.valueOf(R.string.pl_type), "", 0, 1, 3, Integer.valueOf(R.string.pl_continue_task_immediately), "", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5235;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public l d() {
        return new l(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public i a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new i(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public g a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "hasArgsEdit");
        return new g(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 0) {
            return null;
        }
        return Integer.valueOf(R.array.settings_panels);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 29;
    }
}
