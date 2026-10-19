package com.joaomgcd.taskerm.action.googledrive;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<aa, n, u> {
    public d() {
        super(new net.dinglisch.android.taskerm.m(325, R.string.an_google_drive_trash, 51, 4, "google_drive_trash", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_google_drive_account), "t:1", 0, 0, 0, Integer.valueOf(R.string.pl_trash_value), "", 0, 1, 0, Integer.valueOf(R.string.pl_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_file_id), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_remote_folder), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_remote_file_name), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_remote_name), "t:1", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public aa d() {
        return new aa(null, null, null, null, null, null, null, null, 255, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public u a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new u(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5149;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public n a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new n(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        int i2;
        c.f.b.k.b(resources, "res");
        switch (i) {
            case 2:
                i2 = R.array.google_drive_trash_type_sources;
                break;
            case 3:
                i2 = R.array.google_drive_download_type_sources;
                break;
            default:
                i2 = -1;
                break;
        }
        return Integer.valueOf(i2);
    }
}
