package com.joaomgcd.taskerm.action.e;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<l, f, i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5148a;

    public b() {
        super(new net.dinglisch.android.taskerm.m(322, R.string.an_data_backup, 110, 4, "data_backup", 1, Integer.valueOf(R.string.pl_path), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_google_drive_account), "t:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_include_user_variables), "", 0, 1));
        this.f5148a = 5139;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public f a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new f(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public l d() {
        return new l(null, null, null, 7, null);
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
    public Integer e() {
        return Integer.valueOf(this.f5148a);
    }
}
