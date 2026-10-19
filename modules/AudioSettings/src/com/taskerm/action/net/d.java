package com.joaomgcd.taskerm.action.net;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<at, w, aj> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5630a;

    public d() {
        super(new net.dinglisch.android.taskerm.m(358, R.string.an_bluetooth_info, 80, 4, "bluetooth_info", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_device), "t:1", 0, 1, 0, Integer.valueOf(R.string.pl_timeout_seconds), "1:60:5", 0, 0));
        this.f5630a = 5190;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public at d() {
        return new at(null, null, null, null, 15, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public aj a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new aj(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public w a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new w(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5630a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.bluetooth_info_type);
    }
}
