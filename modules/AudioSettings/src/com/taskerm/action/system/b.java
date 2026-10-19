package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<am, r, ab> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5783a;

    public b() {
        super(new net.dinglisch.android.taskerm.m(319, R.string.an_ask_permissions, 104, 4, "ask_permissions", 1, Integer.valueOf(R.string.pl_required_permissions), "t:5", 0, 1, 1, Integer.valueOf(R.string.pl_prompt_if_not_granted), "t:5:?", 0, 1));
        this.f5783a = 5132;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public r a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new r(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public am d() {
        return new am(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ab a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ab(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5783a);
    }
}
