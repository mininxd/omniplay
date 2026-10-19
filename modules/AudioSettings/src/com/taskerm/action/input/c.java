package com.joaomgcd.taskerm.action.input;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.action.a<aq, t, af> {
    public c() {
        super(new net.dinglisch.android.taskerm.m(378, R.string.an_dialog_list, 55, 4, "dialog_list", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_mode), "", 0, 0, 1, Integer.valueOf(R.string.pl_title), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_items), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_selected_items), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_long_click_task), "m:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_positive_label), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_negative_label), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_neutral_label), "t:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_close_after_seconds), "5:120:30", 0, 0, 3, Integer.valueOf(R.string.pl_use_html), "", 0, 0, 0, Integer.valueOf(R.string.first_visible_index), "0:100:0", 0, 0, 3, Integer.valueOf(R.string.pl_hide_filter), "", 0, 0, 1, Integer.valueOf(R.string.pl_text), "t:3:?", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5227;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public aq d() {
        return new aq(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public af a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new af(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public t a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new t(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.dialog_list_modes);
    }
}
