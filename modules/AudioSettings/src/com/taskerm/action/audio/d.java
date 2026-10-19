package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.action.a<r, j, n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4961a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4962c;

    public d() {
        super(new net.dinglisch.android.taskerm.m(312, R.string.an_interrupt_mode, 20, 5, "interrupt_mode", 0, Integer.valueOf(R.string.pl_mode), "", 0, 1, 0, Integer.valueOf(R.string.allow_call_senders), "", 0, 0, 3, Integer.valueOf(R.string.repeat_callers), "", 0, 0, 0, Integer.valueOf(R.string.allow_sms_senders), "", 0, 0, 1, Integer.valueOf(R.string.allow_categories), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.suppressed_effects), "t:1:?", 0, 0, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0));
        this.f4961a = 5226;
        this.f4962c = 21;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public r d() {
        return new r(null, null, null, null, null, null, null, 127, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public j a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new j(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public n a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new n(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, r rVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.B();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f4961a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i == 1 || i == 3) {
            return Integer.valueOf(R.array.do_not_disturb_senders);
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return Integer.valueOf(this.f4962c);
    }
}
