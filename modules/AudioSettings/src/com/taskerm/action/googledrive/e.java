package com.joaomgcd.taskerm.action.googledrive;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.action.a<ab, o, w> {
    public e() {
        super(new net.dinglisch.android.taskerm.m(321, R.string.an_google_drive_upload_file, 51, 4, "google_drive_upload_file", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_google_drive_account), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_data_or_file), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_remote_file_name), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_remote_folder), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_content_description), "t:1:?", 0, 1, 3, Integer.valueOf(R.string.pl_overwrite_if_exists), "", 0, 0, 3, Integer.valueOf(R.string.pl_publicly_share_file), "", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ab d() {
        return new ab(null, null, null, null, null, null, null, null, 255, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public w a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new w(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5149;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public o a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new o(actionEdit, this);
    }
}
