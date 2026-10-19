package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class w extends com.joaomgcd.taskerm.helper.actions.execute.q<ae> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, c cVar2) {
        super(executeService, cVar, cVar2, null, 8, null);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(cVar2, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ae aeVar) {
        b.a.b bVarA;
        c.f.b.k.b(aeVar, "input");
        n forceRotationMode = aeVar.getForceRotationMode();
        if (forceRotationMode == null) {
            return new cm("No value to set");
        }
        boolean z = forceRotationMode == n.Off;
        if (z) {
            l();
            d();
            return new co();
        }
        if (com.joaomgcd.taskerm.util.aj.a(aeVar.getAlternativeMethod())) {
            d();
            return a(forceRotationMode);
        }
        l();
        if (d.f5106a == null && !z) {
            d.f5106a = new aj(h());
        }
        try {
            aj ajVar = d.f5106a;
            if (ajVar != null && (bVarA = ajVar.a(forceRotationMode)) != null) {
                bVarA.b();
            }
            if (z) {
                d();
            }
            return new co();
        } catch (Exception e2) {
            return new cm(e2);
        }
    }

    private final cl a(n nVar) {
        com.joaomgcd.taskerm.f.d dVarK = k();
        if (dVarK != null) {
            return com.joaomgcd.taskerm.helper.actions.execute.q.a((com.joaomgcd.taskerm.helper.actions.execute.q) this, new com.joaomgcd.taskerm.securesettings.r[]{((com.joaomgcd.taskerm.action.setting.a) dVarK).a((Context) h(), String.valueOf(nVar.ordinal())), new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.System, "user_rotation", String.valueOf(nVar.a()), false, 0, 0, 48, null)}, false, 2, (Object) null);
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.action.setting.ActionCustomSettingBase<com.joaomgcd.taskerm.action.display.InputForceRotation>");
    }

    private final void d() {
        b.a.b bVarA;
        aj ajVar = d.f5106a;
        if (ajVar != null && (bVarA = ajVar.a(n.Off)) != null) {
            bVarA.b();
        }
        d.f5106a = (aj) null;
    }

    private final void l() {
        a(n.Off);
    }
}
