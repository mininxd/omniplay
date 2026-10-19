package com.joaomgcd.taskerm.action.a;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<g, c, e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4912a;

    public a() {
        super(new m(548, R.string.an_flash, 10, 4, "flash", 1, Integer.valueOf(R.string.pl_text), "s:4", 0, 1, 3, Integer.valueOf(R.string.pl_long), "", 0, 0, 3, Integer.valueOf(R.string.pl_tasker_layout), "", 0, 0, 1, Integer.valueOf(R.string.pl_title), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_icon), "img:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_icon_size), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_back_colour), "col:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_task), "m:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_timeout), "t:1:?", 0, 1, 3, Integer.valueOf(R.string.pl_continue_task_immediately), "true", 0, 0, 1, Integer.valueOf(R.string.pl_text_colour), "col:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_dismiss_on_click), "true", 0, 0, 3, Integer.valueOf(R.string.pl_show_over_everything), "false", 0, 0, 1, Integer.valueOf(R.string.pl_position), "t:1:?", 0, 1, 3, Integer.valueOf(R.string.pl_use_html), "false", 0, 0));
        this.f4912a = 5299;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public g d() {
        return new g(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public c a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new c(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new e(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f4912a);
    }
}
