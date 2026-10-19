package com.joaomgcd.taskerm.action.net;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.action.a<as, v, ah> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5629a;

    public c() {
        super(new net.dinglisch.android.taskerm.m(340, R.string.an_bluetooth_connection, 80, 4, "bluetooth_connection", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_action), "", 0, 1, 1, Integer.valueOf(R.string.pl_device), "t:1", 0, 1, 0, Integer.valueOf(R.string.pl_timeout_seconds), "5:120:60", 0, 0));
        this.f5629a = 5190;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public as d() {
        return new as(null, null, null, null, 15, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ah a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ah(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public v a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new v(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5629a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.bluetooth_connection_action);
    }
}
