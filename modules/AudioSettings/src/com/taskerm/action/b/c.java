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
public final class c extends com.joaomgcd.taskerm.action.a<j, e, g> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5021a;

    public c() {
        super(new m(22, R.string.an_load_last_app, 15, 0, "load_last_app", new Object[0]));
        this.f5021a = 5216;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public j d() {
        return new j();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public e a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new e(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public g a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new g(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, j jVar) {
        k.b(context, "context");
        return bt.f9101c.M();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5021a);
    }
}
