package com.joaomgcd.taskerm.action.audio;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.action.a<s, k, o> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4963a;

    public e() {
        super(new net.dinglisch.android.taskerm.m(338, R.string.an_notification_channel_info, 30, 4, "notification_channel_info", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_category), "t:1:?", 0, 1));
        this.f4963a = 5175;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public s d() {
        return new s(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public k a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new k(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public o a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new o(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f4963a);
    }
}
