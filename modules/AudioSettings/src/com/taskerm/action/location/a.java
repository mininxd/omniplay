package com.joaomgcd.taskerm.action.location;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<g, c, e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5518a;

    public a() {
        super(new m(366, R.string.an_get_current_location, 60, 4, "get_current_location", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_timeout_seconds), "5:360:30", 0, 0, 1, Integer.valueOf(R.string.pl_minimum_accuracy), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_speed_meters_second), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.location_altitude), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.near_location), "locradi:1:?", 0, 1, 3, Integer.valueOf(R.string.pl_enable_location_if_needed), "", 0, 0, 3, Integer.valueOf(R.string.pl_get_last_location_if_timeout), "", 0, 0, 1, Integer.valueOf(R.string.pl_minimum_accuracy_speed), "t:1:?", 0, 0, 3, Integer.valueOf(R.string.pl_force_high_accuracy), "", 0, 0));
        this.f5518a = 5213;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public g d() {
        return new g(null, null, null, null, null, null, null, null, null, null, 1023, null);
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
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, g gVar) {
        k.b(context, "context");
        return bt.f9101c.f();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public c a(ActionEdit actionEdit) {
        k.b(actionEdit, "actionEdit");
        return new c(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5518a);
    }
}
