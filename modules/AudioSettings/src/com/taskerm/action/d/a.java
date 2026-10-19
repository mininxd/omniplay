package com.joaomgcd.taskerm.action.d;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<d, b, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5062a;

    public a() {
        super(new m(126, R.string.an_return, 105, 0, "return", 1, Integer.valueOf(R.string.pl_value), "t:1", 0, 1, 3, Integer.valueOf(R.string.pl_stop), "true", 0, 1, 3, Integer.valueOf(R.string.pl_local_variable_passthrough), "false", 0, 1, 3, Integer.valueOf(R.string.pl_local_variable_replace_on_passthrough), "false", 0, 1, 1, Integer.valueOf(R.string.pl_local_variable_passthrough_limit), "t:1:?", 0, 1));
        this.f5062a = 5232;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public b a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new b(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d d() {
        return new d(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public c a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new c(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5062a);
    }
}
