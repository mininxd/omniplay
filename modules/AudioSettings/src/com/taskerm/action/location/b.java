package com.joaomgcd.taskerm.action.location;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<i, d, f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5519a;

    public b() {
        super(new m(368, R.string.an_pick_location, 60, 4, "pick_location", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_title), "t:1:?", 0, 1, 3, Integer.valueOf(R.string.pl_select_radius), "", 0, 0, 1, Integer.valueOf(R.string.pl_initial_location), "locradi:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_type), "", 0, 1));
        this.f5519a = 5213;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i d() {
        return new i(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public f a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new f(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public d a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new d(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5519a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        k.b(resources, "res");
        if (i != 4) {
            return null;
        }
        return Integer.valueOf(R.array.map_type_sources);
    }
}
