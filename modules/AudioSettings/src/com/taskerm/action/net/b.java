package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.setting.a<ar> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5612a;

    public b() {
        super(new net.dinglisch.android.taskerm.m(323, R.string.an_airplane_radios, 80, 1, "airplane_radios", 3, Integer.valueOf(R.string.an_bluetooth_status), "", 0, 1, 3, Integer.valueOf(R.string.pl_cell), "", 0, 1, 3, Integer.valueOf(R.string.pl_nfc), "", 0, 1, 3, Integer.valueOf(R.string.pl_wifi), "", 0, 1, 3, Integer.valueOf(R.string.pl_wimax), "", 0, 1));
        this.f5612a = 5139;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public u a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new u(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ag a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ag(executeService, cVar, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5612a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ar d() {
        return new ar(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.setting.a
    public com.joaomgcd.taskerm.securesettings.r a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "value");
        return new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Global, "airplane_mode_radios", str, false, 0, 0, 48, null);
    }
}
