package com.joaomgcd.taskerm.action.location;

import android.location.Location;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import c.l.n;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.location.m;
import com.joaomgcd.taskerm.location.p;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends l<g> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<g, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(g gVar) throws Throwable {
        k.b(gVar, "input");
        if (!ah.av(h())) {
            return cn.a("Need Google Play Services for this action");
        }
        m mVar = (m) null;
        try {
            if (!ah.af(h())) {
                if (!k.a((Object) gVar.getEnableLocationIfNeeded(), (Object) true)) {
                    return cn.a("Location is off so location can't be gotten");
                }
                bt btVarH = bt.a.h(bt.f9101c, h(), 0, 2, null);
                if (!btVarH.g()) {
                    bt.a(btVarH, (String) null, 1, (Object) null).b();
                    return cn.a("Can't enable location without secure settings permission");
                }
                m mVarA = p.a(h());
                try {
                    if (!((Boolean) p.a(h(), m.HighAccuracy, 0L, 2, null).b()).booleanValue()) {
                        cq<?, ad> cqVarA = cn.a("Couldn't enable location.");
                        if (mVarA != null) {
                            p.a(h(), mVarA);
                        }
                        return cqVarA;
                    }
                    mVar = mVarA;
                } catch (Throwable th) {
                    th = th;
                    mVar = mVarA;
                    if (mVar != null) {
                        p.a(h(), mVar);
                    }
                    throw th;
                }
            }
            String minAccuracy = gVar.getMinAccuracy();
            Integer numC = minAccuracy != null ? n.c(minAccuracy) : null;
            String minAccuracySpeed = gVar.getMinAccuracySpeed();
            Integer numC2 = minAccuracySpeed != null ? n.c(minAccuracySpeed) : null;
            com.joaomgcd.taskerm.location.d dVarA = com.joaomgcd.taskerm.location.c.g.a(gVar.getSpeed());
            com.joaomgcd.taskerm.location.d dVarA2 = com.joaomgcd.taskerm.location.c.g.a(gVar.getAltitude());
            com.joaomgcd.taskerm.location.j.a.C0191a c0191aA = com.joaomgcd.taskerm.location.j.a.C0191a.f7700a.a(gVar.getNearLocation());
            Integer timeout = gVar.getTimeout();
            Location locationB = new com.joaomgcd.taskerm.location.j(h()).a(new com.joaomgcd.taskerm.location.j.a(numC, dVarA, dVarA2, c0191aA, Long.valueOf(timeout != null ? timeout.intValue() : 360), gVar.getGetLastLocationIfTimeout(), numC2, gVar.getForceHighAccuracy())).b();
            k.a((Object) locationB, "result");
            cq cqVarA2 = cn.a(new OutputGetCurrentLocation(locationB));
            if (mVar != null) {
                p.a(h(), mVar);
            }
            return cqVarA2;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
