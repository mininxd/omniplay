package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.action.setting.a<ay> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5480a;

    public j() {
        super(new net.dinglisch.android.taskerm.m(329, R.string.an_navigation_bar, 55, 4, "navigation_bar", 1, Integer.valueOf(R.string.pl_left), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_center), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_right), "t:1:?", 0, 1));
        this.f5480a = 5165;
    }

    @Override // com.joaomgcd.taskerm.action.setting.a
    public com.joaomgcd.taskerm.securesettings.r a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "value");
        return new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "sysui_nav_bar", str, false, 0, 0, 48, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ay d() {
        return new ay(null, null, null, 7, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ay ayVar) {
        c.f.b.k.b(context, "context");
        return (String[]) c.a.d.a((Object[]) bt.f9101c.E(), (Object[]) bt.f9101c.K());
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public al a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new al(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5480a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public aa a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new aa(actionEdit, this);
    }
}
