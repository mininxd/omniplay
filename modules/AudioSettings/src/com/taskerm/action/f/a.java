package com.joaomgcd.taskerm.action.f;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<aa, m, s> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5174a;

    public a() {
        super(new net.dinglisch.android.taskerm.m(393, R.string.an_arrays_merge, 120, 0, "arrays_merge", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_names), "t:3", 0, 1, 0, Integer.valueOf(R.string.pl_merge_type), "", 0, 0, 1, Integer.valueOf(R.string.pl_joiner), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_format), "t:3", 0, 1, 1, Integer.valueOf(R.string.output), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_join_output), "t:3:?", 0, 0));
        this.f5174a = 5259;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public m a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new m(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public aa d() {
        return new aa(null, null, null, null, null, null, null, 127, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public s a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new s(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5174a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.arrays_join_types);
    }
}
