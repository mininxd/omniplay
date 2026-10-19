package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class l extends com.joaomgcd.taskerm.action.a<bb, ae, ap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5641a;

    public l() {
        super(new net.dinglisch.android.taskerm.m(320, R.string.an_ping, 80, 4, "ping", 1, Integer.valueOf(R.string.pl_host), "t:1", 0, 1, 0, Integer.valueOf(R.string.pl_number), "1:10", 0, 1, 1, Integer.valueOf(R.string.pl_average_result_variable), "uvar:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_min_result_variable), "uvar:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_max_result_variable), "uvar:1:?", 0, 0));
        this.f5641a = 5136;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public bb d() {
        return new bb(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ap a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ap(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public ae a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new ae(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5641a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, bb bbVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.u();
    }
}
