package com.joaomgcd.taskerm.action.googledrive;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.action.a<z, m, t> {
    public c() {
        super(new net.dinglisch.android.taskerm.m(327, R.string.an_google_drive_sign_in, 51, 4, "google_drive_sign_in", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_google_drive_account), "t:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_full_access), "", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public z d() {
        return new z(null, null, null, 7, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public t a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new t(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5153;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public m a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new m(actionEdit, this);
    }
}
