package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class m extends com.joaomgcd.taskerm.action.a<au, z, aj> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5800a;

    public m() {
        super(new net.dinglisch.android.taskerm.m(373, R.string.an_test_sensor, 104, 4, "test_sensor", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_type), "", 0, 1, 0, Integer.valueOf(R.string.pl_timeout_seconds), "1:360:30", 0, 0, 3, Integer.valueOf(R.string.convert_orientation), "", 0, 0));
        this.f5800a = 5219;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public au d() {
        return new au(null, null, null, null, 15, null);
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
    public z a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new z(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5800a);
    }
}
