package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.action.a<aq, u, ae> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5786a;

    public f() {
        super(new net.dinglisch.android.taskerm.m(364, R.string.an_query_next_alarm, 104, 4, "query_next_alarm", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_minutes_difference), "-60:60:0", 0, 1));
        this.f5786a = 5211;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public aq d() {
        return new aq(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ae a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ae(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public u a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new u(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5786a);
    }
}
