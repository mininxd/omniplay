package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.action.a<aw, y, aj> {
    public h() {
        super(new net.dinglisch.android.taskerm.m(804, R.string.an_input_method_select, 55, 0, "input_method_select", new Object[0]));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public aw d() {
        return new aw();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public y a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "hasArgsEdit");
        return new y(actionEdit, this);
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
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, aw awVar) {
        c.f.b.k.b(context, "context");
        if (com.joaomgcd.taskerm.util.f.f9323b.g()) {
            return bt.f9101c.D();
        }
        return super.b(context, awVar);
    }
}
