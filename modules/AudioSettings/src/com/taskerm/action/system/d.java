package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<ao, s, ac> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5784a;

    public d() {
        super(new net.dinglisch.android.taskerm.m(402, R.string.an_get_clipboard, 104, 4, "get_clipboard", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0));
        this.f5784a = 5306;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ao d() {
        return new ao(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ac a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ac(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public s a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new s(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5784a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ao aoVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.O();
    }
}
