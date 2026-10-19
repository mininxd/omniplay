package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(30)
public final class l extends com.joaomgcd.taskerm.action.a<az, ab, am> {
    @Override // com.joaomgcd.taskerm.action.a
    public boolean b() {
        return true;
    }

    public l() {
        super(new net.dinglisch.android.taskerm.m(384, R.string.an_power_menu_action, 55, 4, "power_menu_action", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_id), "t:1", 0, 1, 0, Integer.valueOf(R.string.pl_action), "", 0, 0, 0, Integer.valueOf(R.string.pl_type), "", 0, 0, 1, Integer.valueOf(R.string.pl_title), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_subtitle), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_icon), "img:2:?", 0, 0, 1, Integer.valueOf(R.string.pl_command), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.word_active), "strbool:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_range_min), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_range_max), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_range_current), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_range_step), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_range_format), "t:1:?", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5235;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public az d() {
        return new az(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public am a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new am(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 30;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public ab a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new ab(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 8) {
            switch (i) {
                case 2:
                    return Integer.valueOf(R.array.power_menu_action_edit_actions);
                case 3:
                    return Integer.valueOf(R.array.power_menu_action_types);
                default:
                    return null;
            }
        }
        return Integer.valueOf(R.array.power_menu_action_state);
    }
}
