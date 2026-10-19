package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<ak, q, aa> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5728a;

    public a() {
        super(new net.dinglisch.android.taskerm.m(375, R.string.an_adb_wifi, 35, 4, "adb_wifi", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_command), "adbsc:3", 0, 1, 1, Integer.valueOf(R.string.pl_host), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_port), "t:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_timeout_seconds), "1:10:1", 0, 0, 3, Integer.valueOf(R.string.pl_toggle_debugging), "", 0, 0, 1, Integer.valueOf(R.string.pl_result_encoding), "t:1:?", 0, 1));
        this.f5728a = 5248;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ak d() {
        return new ak(null, null, null, null, null, null, null, 127, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public aa a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new aa(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public q a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new q(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5728a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ak akVar) {
        String[] strArrH;
        c.f.b.k.b(context, "context");
        if (com.joaomgcd.taskerm.util.aj.c(akVar != null ? akVar.getToggleDebugging() : null)) {
            strArrH = bt.f9101c.E();
        } else {
            strArrH = bt.f9101c.H();
        }
        return (String[]) c.a.d.a((Object[]) strArrH, (Object[]) bt.f9101c.u());
    }
}
