package com.joaomgcd.taskerm.action.b;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<i, d, f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5020a;

    public b() {
        super(new m(18, R.string.an_kill_app, 15, 4, "kill_app", 2, Integer.valueOf(R.string.pl_app), "", 0, 1, 3, Integer.valueOf(R.string.pl_use_root), "", 0, 1));
        this.f5020a = 5232;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i d() {
        return new i(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public d a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new d(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public f a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new f(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, i iVar) {
        k.b(context, "context");
        if (iVar == null || !iVar.getUseRoot()) {
            return null;
        }
        return bt.f9101c.w();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5020a);
    }
}
