package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.action.setting.a<af> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5107a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5108c;

    public e() {
        super(new net.dinglisch.android.taskerm.m(907, R.string.an_status_bar_icons, 40, 4, "status_bar_icons", 1, Integer.valueOf(R.string.pl_icons_to_hide), "t:1:?", 0, 1));
        this.f5107a = 5126;
        this.f5108c = 23;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public q a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new q(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public x a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new x(executeService, cVar, this, bundle);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, af afVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5107a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public af d() {
        return new af(null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return Integer.valueOf(this.f5108c);
    }

    @Override // com.joaomgcd.taskerm.action.setting.a
    public com.joaomgcd.taskerm.securesettings.r a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "value");
        return new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "icon_blacklist", str, false, 0, 0, 48, null);
    }
}
