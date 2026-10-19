package com.joaomgcd.taskerm.action.phone;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<m, f, j> {
    public a() {
        super(new net.dinglisch.android.taskerm.m(386, R.string.an_call_screening, 90, 4, "call_screening", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_disallow_allow), "", 0, 1, 3, Integer.valueOf(R.string.pl_reject), "", 0, 1, 3, Integer.valueOf(R.string.pl_silence), "", 0, 1, 3, Integer.valueOf(R.string.pl_skip_call_log), "", 0, 1, 3, Integer.valueOf(R.string.pl_skip_notification), "", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5241;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 29;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public m d() {
        return new m(null, null, null, null, null, null, 63, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public j a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new j(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public f a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new f(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, m mVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.A();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.disallow_allow);
    }
}
