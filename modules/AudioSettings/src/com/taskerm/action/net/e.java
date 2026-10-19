package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.action.a<av, y, al> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5631a;

    public e() {
        super(new net.dinglisch.android.taskerm.m(398, R.string.an_connect_to_wifi, 80, 4, "connect_to_wifi", 1, Integer.valueOf(R.string.pl_ssid), "ssid:1", 0, 1));
        this.f5631a = 5278;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public av d() {
        return new av(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public al a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new al(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public y a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new y(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5631a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, av avVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.f();
    }
}
