package com.joaomgcd.taskerm.action.f;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<ab, n, u> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5223a;

    public d() {
        super(new net.dinglisch.android.taskerm.m(394, R.string.an_format_date_time, 120, 4, "format_date_time", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_input_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_input), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_input_format), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_input_separator), "t:3:?", 0, 0, 1, Integer.valueOf(R.string.pl_output_format), "t:3:?", 0, 1, 1, Integer.valueOf(R.string.pl_output_format_separator), "t:3:?", 0, 0, 1, Integer.valueOf(R.string.pl_formatted_variable_names), "t:3:?", 0, 0, 3, Integer.valueOf(R.string.pl_get_all_details), "", 0, 0, 3, Integer.valueOf(R.string.pl_do_maths), "", 0, 0, 0, Integer.valueOf(R.string.pl_output_offset_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_output_offset), "t:1", 0, 0));
        this.f5223a = 5259;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public n a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new n(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ab d() {
        return new ab(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public u a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new u(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5223a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i == 1) {
            return Integer.valueOf(R.array.date_time_format_types);
        }
        if (i != 10) {
            return null;
        }
        return Integer.valueOf(R.array.time_offset_types);
    }
}
