package com.joaomgcd.taskerm.helper;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.notification.ak;
import com.joaomgcd.taskerm.notification.ao;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.bt;
import java.util.ArrayList;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public class u<T extends Service> extends j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7514d = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(u.class), "powerManager", "getPowerManager()Landroid/os/PowerManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final T f7516c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f7517e;

    @TargetApi(26)
    public final boolean a(bt btVar) {
        return a(this, btVar, (com.joaomgcd.taskerm.notification.b) null, 2, (Object) null);
    }

    public final void b(String str) {
        a(this, str, (Exception) null, 2, (Object) null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(T t, String str) {
        super(t);
        c.f.b.k.b(t, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(str, "tag");
        this.f7516c = t;
        this.f7517e = str;
        this.f7515b = c.f.a(new a());
    }

    public final T u() {
        return this.f7516c;
    }

    static final class a extends c.f.b.l implements c.f.a.a<PowerManager> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final PowerManager invoke() {
            Object systemService = u.this.u().getSystemService("power");
            if (systemService != null) {
                return (PowerManager) systemService;
            }
            throw new c.q("null cannot be cast to non-null type android.os.PowerManager");
        }
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public void b() {
        super.b();
        com.joaomgcd.taskerm.util.g.b(this.f7516c, this.f7517e);
    }

    protected final com.joaomgcd.taskerm.notification.c p() {
        Context contextT = t();
        Intent intentA = Settings.a(t().getPackageName());
        c.f.b.k.a((Object) intentA, "Settings.getAndroidSetti…tent(context.packageName)");
        return new com.joaomgcd.taskerm.notification.c(contextT, intentA, null, null, null, null, 60, null);
    }

    @TargetApi(26)
    public static /* synthetic */ boolean a(u uVar, bt btVar, com.joaomgcd.taskerm.notification.b bVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hasPermissionsNotifyIfNot");
        }
        if ((i & 2) != 0) {
            bVar = new com.joaomgcd.taskerm.notification.k(uVar.f7516c, btVar);
        }
        return uVar.a(btVar, bVar);
    }

    @TargetApi(26)
    public final boolean a(bt btVar, com.joaomgcd.taskerm.notification.b bVar) {
        int i;
        c.f.b.k.b(btVar, "permissions");
        if (btVar.g()) {
            return true;
        }
        String strA = aj.a(R.string.dt_missing_permissions, t(), new Object[0]);
        String strA2 = aj.a(R.string.dc_missing_permissions, t(), ah.a(t()), c.a.j.a(btVar.l(), ", ", null, null, 0, null, null, 62, null));
        as asVar = com.joaomgcd.taskerm.util.f.f9323b.a() ? new as(R.drawable.mw_hardware_security) : new as(ah.K(t()));
        ak akVarA = bt.f9101c.a();
        as asVar2 = asVar;
        at atVar = new at(t(), strA, strA2, null, null, null, false, asVar2, new ao("missingpermisssions", false, 2), strA2, null, 2, 0L, null, false, false, null, null, null, akVarA, null, false, false, 7861368, null);
        if (bVar != null) {
            atVar.c(aj.a(R.string.dc_click_to_grant_permissions, atVar.e(), new Object[0]));
            atVar.a(bVar);
        }
        ArrayList arrayListD = c.a.j.d(atVar.c());
        if (com.joaomgcd.taskerm.util.f.f9323b.t()) {
            i = 2;
            arrayListD.add(new at(t(), strA, null, null, null, null, false, asVar2, new ao("missingpermisssions", true, 2), null, null, 0, 0L, null, false, false, null, null, null, akVarA, null, false, false, 7863932, null).c());
        } else {
            i = 2;
        }
        b.a.l lVarG = b.a.l.a((Iterable) arrayListD).g();
        c.f.b.k.a((Object) lVarG, "Single.concat(actions).toList()");
        j.a(this, lVarG, (b.a.d.f) null, i, (Object) null);
        bn.c("E", strA2);
        return false;
    }

    public static /* synthetic */ void a(u uVar, String str, Exception exc, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: logDebug");
        }
        if ((i & 2) != 0) {
            exc = (Exception) null;
        }
        uVar.a(str, exc);
    }

    public final void a(String str, Exception exc) {
        c.f.b.k.b(str, "log");
        bn.b(this.f7517e, str);
    }
}
