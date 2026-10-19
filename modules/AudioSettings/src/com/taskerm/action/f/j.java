package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.action.a<ag, r, z> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5228a;

    public j() {
        super(new net.dinglisch.android.taskerm.m(399, R.string.an_variable_map, 120, 4, "variable_map", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_input), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_input_minimum), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_input_maximum), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_output_minimum), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_output_maximum), "t:1", 0, 1, 3, Integer.valueOf(R.string.word_invert), "", 0, 1, 3, Integer.valueOf(R.string.restrict_range), "", 0, 1, 0, Integer.valueOf(R.string.pl_max_rounding_digits), "1:10:3", 0, 0, 1, Integer.valueOf(R.string.output_variable_name), "t:1:?", 0, 1));
        this.f5228a = 5291;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public r a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new r(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ag d() {
        return new ag(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public z a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new z(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5228a);
    }
}
