package com.joaomgcd.taskerm.action.phone;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<o, i, l> {
    public d() {
        super(new net.dinglisch.android.taskerm.m(733, R.string.an_end_call, 90, 0, null, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5241;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public o d() {
        return new o(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public l a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new l(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public i a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new i(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public String[] b(Context context, o oVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.k();
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean a(Context context, o oVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(oVar, "input");
        return com.joaomgcd.taskerm.util.f.f9323b.c(context);
    }
}
