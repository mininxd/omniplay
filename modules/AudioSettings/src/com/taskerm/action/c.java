package com.joaomgcd.taskerm.action;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.s;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a<i, f, g> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.b<ActionEdit, s> f5027a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.b<Context, k> f5028c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public c(int i, int i2, int i3, String str, c.f.a.b<? super Context, ? extends k> bVar) {
        super(new m(i, i3, i2, 5, str, 3, Integer.valueOf(R.string.pl_set), "", 0, 1, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0));
        c.f.b.k.b(str, "helpId");
        c.f.b.k.b(bVar, "togglerGetter");
        this.f5028c = bVar;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i d() {
        return new i(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public f a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new f(actionEdit, this, h_());
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public g a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.a.b<Context, k> bVar = this.f5028c;
        Context applicationContext = executeService.getApplicationContext();
        c.f.b.k.a((Object) applicationContext, "service.applicationContext");
        return new g(executeService, cVar, bundle, bVar.invoke(applicationContext), this);
    }

    public c.f.a.b<ActionEdit, s> h_() {
        return this.f5027a;
    }
}
