package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<ad, o, v> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5103a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5104c;

    public b() {
        super(new net.dinglisch.android.taskerm.m(316, R.string.an_display_density, 40, 4, "display_density", 0, Integer.valueOf(R.string.pl_size), "", 0, 1, 1, Integer.valueOf(R.string.pl_manual), "t:1:?", 0, 1));
        this.f5103a = 5126;
        this.f5104c = 24;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ad d() {
        return new ad(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public o a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new o(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public v a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new v(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ad adVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5103a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.display_density_sources);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return Integer.valueOf(this.f5104c);
    }
}
