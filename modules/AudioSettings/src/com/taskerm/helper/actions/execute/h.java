package com.joaomgcd.taskerm.helper.actions.execute;

import android.os.Bundle;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.action.a<x, k, t> {
    public h() {
        super(new net.dinglisch.android.taskerm.m(FrameMetricsAggregator.EVERY_DURATION, R.string.an_torch, 10, 1, "torch", 3, Integer.valueOf(R.string.pl_set), "", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public x d() {
        return new x();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public k a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new k(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public t a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new t(executeService, cVar, bundle, this);
    }
}
