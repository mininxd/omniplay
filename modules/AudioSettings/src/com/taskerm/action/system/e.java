package com.joaomgcd.taskerm.action.system;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.action.a<ap, t, ad> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5785a;

    public e() {
        super(new net.dinglisch.android.taskerm.m(397, R.string.an_get_material_you_colors, 104, 4, "get_material_you_colors", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 3, Integer.valueOf(R.string.pl_output_hashtags), "true", 0, 1));
        this.f5785a = 5281;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public t a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new t(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ap d() {
        return new ap(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ad a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ad(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5785a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 31;
    }
}
