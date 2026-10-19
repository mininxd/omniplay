package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<q, i, m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4959a;

    public b() {
        super(new net.dinglisch.android.taskerm.m(335, R.string.an_test_current_app, 15, 4, "app_info", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_package_app_name), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_ignore_packages), "apppakc:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_ignore_unlaunchable_apps), "", 0, 0, 3, Integer.valueOf(R.string.pl_get_all_details), "", 0, 0));
        this.f4959a = 5175;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q d() {
        return new q(null, null, null, null, null, null, 63, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public i a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new i(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public m a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new m(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, q qVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.M();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f4959a);
    }
}
