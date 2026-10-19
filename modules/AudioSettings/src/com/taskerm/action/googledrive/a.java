package com.joaomgcd.taskerm.action.googledrive;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<x, k, p> {
    public a() {
        super(new net.dinglisch.android.taskerm.m(326, R.string.an_google_drive_download, 51, 4, "google_drive_download", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_google_drive_account), "t:1", 0, 0, 0, Integer.valueOf(R.string.pl_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_file_id), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_remote_folder), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_remote_file_name), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_local_path), "t:1", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public x d() {
        return new x(null, null, null, null, null, null, null, 127, null);
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
    public Integer e() {
        return 5149;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public k a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new k(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.google_drive_download_type_sources);
    }
}
