package com.joaomgcd.taskerm.action.phone;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.telecom.TelecomManager;
import androidx.core.app.NotificationCompat;
import c.s;
import com.android.internal.telephony.ITelephony;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ck;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.gb;

/* JADX INFO: loaded from: classes.dex */
public final class l extends com.joaomgcd.taskerm.helper.actions.execute.l<o> {

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5689a = new a();

        a() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<o, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(o oVar) throws RemoteException {
        c.f.b.k.b(oVar, "input");
        if (com.joaomgcd.taskerm.util.f.f9323b.c(h())) {
            cl clVarB = com.joaomgcd.taskerm.b.g.a("input keyevent KEYCODE_ENDCALL", h()).b();
            if (!clVarB.b()) {
                com.joaomgcd.taskerm.rx.i.a(at.a.a(at.f8046b, h(), "endcallandroid10", aj.a(R.string.ending_calls_android_10, h(), new Object[0]), aj.a(R.string.ending_calls_android_10_explained, h(), new Object[0]), null, 16, null), h(), a.f5689a);
            }
            c.f.b.k.a((Object) clVarB, "result");
            return clVarB;
        }
        if (com.joaomgcd.taskerm.util.f.f9323b.f()) {
            TelecomManager telecomManagerQ = ah.q(h());
            if (telecomManagerQ == null) {
                return cn.a("Couldn't get Telecom Manager");
            }
            telecomManagerQ.endCall();
            return new co();
        }
        Boolean boolB = az.a((Context) h(), true).b();
        c.f.b.k.a((Object) boolB, "Init.canRoot(service, true).blockingGet()");
        if (boolB.booleanValue()) {
            ck.f9218a.a(6).b();
            return new co();
        }
        ITelephony iTelephonyC = gb.c(h());
        if (iTelephonyC == null) {
            return cn.a("Couldn't get Telephony Service");
        }
        iTelephonyC.endCall();
        return new co();
    }
}
