package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.helper.actions.execute.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5797a;

    public j() {
        super(new net.dinglisch.android.taskerm.m(362, R.string.an_set_assistant, 104, 4, "set_assistant", 1, Integer.valueOf(R.string.pl_assistant), "", 0, 1));
        this.f5797a = 5208;
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
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.helper.actions.execute.w wVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5797a);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.a, com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public w a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new w(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.setting.a
    public com.joaomgcd.taskerm.securesettings.r a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "value");
        return new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "voice_interaction_service", str, false, 0, 0, 48, null);
    }
}
