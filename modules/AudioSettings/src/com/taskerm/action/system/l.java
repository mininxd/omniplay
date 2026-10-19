package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class l extends com.joaomgcd.taskerm.action.a<at, y, ai> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5799a;

    public l() {
        super(new net.dinglisch.android.taskerm.m(370, R.string.an_shortcut, 15, 4, "shortcut", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.an_shortcut), "", 0, 1));
        this.f5799a = 5214;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public at d() {
        return new at(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ai a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ai(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public y a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new y(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5799a);
    }
}
