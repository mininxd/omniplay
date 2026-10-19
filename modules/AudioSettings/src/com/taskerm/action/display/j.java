package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.action.a<ah, t, ab> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5112a;

    public j() {
        super(new net.dinglisch.android.taskerm.m(248, R.string.an_accessibility_lock_screen, 40, 4, "turn_off_display", 3, Integer.valueOf(R.string.pl_dim), "", 0, 1, 3, Integer.valueOf(R.string.an_lock), "", 0, 1));
        this.f5112a = 5167;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ah d() {
        return new ah(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public t a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new t(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ab a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ab(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5112a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ah ahVar) {
        c.f.b.k.b(context, "context");
        if (c.f.b.k.a((Object) (ahVar != null ? ahVar.getLock() : null), (Object) true)) {
            if (bt.a.h(bt.f9101c, context, 0, 2, null).g()) {
                return null;
            }
            return bt.f9101c.z();
        }
        return super.b(context, ahVar);
    }
}
