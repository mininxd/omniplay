package com.joaomgcd.taskerm.action.e;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<k, e, h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5147a;

    public a() {
        super(new net.dinglisch.android.taskerm.m(385, R.string.an_command, 110, 0, "command", 1, Integer.valueOf(R.string.pl_command), "t:3", 0, 1));
        this.f5147a = 5235;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public e a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new e(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public k d() {
        return new k(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public h a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new h(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5147a);
    }
}
