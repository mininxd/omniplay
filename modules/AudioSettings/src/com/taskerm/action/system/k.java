package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class k extends com.joaomgcd.taskerm.action.a<as, x, ah> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5798a;

    public k() {
        super(new net.dinglisch.android.taskerm.m(105, R.string.an_set_clipboard, 104, 4, "set_clipboard", 1, Integer.valueOf(R.string.pl_text), "t:5:?", 0, 1, 3, Integer.valueOf(R.string.pl_add), "", 0, 0, 1, Integer.valueOf(R.string.pl_image), "img:1:?", 0, 1));
        this.f5798a = 5306;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public as d() {
        return new as(null, null, null, 7, null);
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
    public x a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new x(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5798a);
    }
}
