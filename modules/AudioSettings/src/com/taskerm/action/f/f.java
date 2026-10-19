package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.action.a<ae, p, x> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5225a;

    public f() {
        super(new net.dinglisch.android.taskerm.m(392, R.string.an_set_variable_structure_type, 120, 4, "set_variable_structure_type", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_name), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_structure_type), "t:1", 0, 1));
        this.f5225a = 5255;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public p a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new p(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ae d() {
        return new ae(null, null, null, 7, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public x a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new x(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5225a);
    }
}
