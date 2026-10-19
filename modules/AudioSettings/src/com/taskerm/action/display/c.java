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
public final class c extends com.joaomgcd.taskerm.action.setting.a<ae> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5105a;

    public c() {
        super(new net.dinglisch.android.taskerm.m(318, R.string.an_force_rotation, 40, 4, "force_rotation", 0, Integer.valueOf(R.string.pl_mode), "", 0, 1, 3, Integer.valueOf(R.string.pl_alternative_method), "", 0, 0));
        this.f5105a = 5126;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public p a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new p(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ae d() {
        return new ae(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public w a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new w(executeService, cVar, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.force_rotation_sources);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ae aeVar) {
        c.f.b.k.b(context, "context");
        if (com.joaomgcd.taskerm.util.aj.a(aeVar != null ? aeVar.getAlternativeMethod() : null)) {
            return (String[]) c.a.d.a((Object[]) bt.f9101c.K(), (Object[]) bt.f9101c.E());
        }
        return bt.f9101c.D();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5105a);
    }

    private final String b(String str) {
        return (str.hashCode() == 48 && str.equals("0")) ? "1" : "0";
    }

    @Override // com.joaomgcd.taskerm.action.setting.a
    public com.joaomgcd.taskerm.securesettings.r a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "value");
        return new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.System, "accelerometer_rotation", b(str), false, 0, 0, 48, null);
    }
}
