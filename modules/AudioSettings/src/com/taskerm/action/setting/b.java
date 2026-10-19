package com.joaomgcd.taskerm.action.setting;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.action.setting.j;
import com.joaomgcd.taskerm.helper.actions.c;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<TInput extends j, THelperEdit extends com.joaomgcd.taskerm.helper.actions.c<TInput>> extends com.joaomgcd.taskerm.action.a<TInput, THelperEdit, h<TInput>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(net.dinglisch.android.taskerm.m mVar) {
        super(mVar);
        c.f.b.k.b(mVar, "spec");
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public h<TInput> a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new h<>(executeService, cVar, bundle, this);
    }
}
