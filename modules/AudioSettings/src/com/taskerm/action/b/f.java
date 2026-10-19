package com.joaomgcd.taskerm.action.b;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import c.s;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.notification.q;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class f extends l<i> {

    static final class a extends c.f.b.l implements c.f.a.b<at, s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(at atVar) {
            a(atVar);
            return s.f2131a;
        }

        public final void a(at atVar) {
            k.b(atVar, "receiver$0");
            atVar.a(new as(R.drawable.mw_hardware_security));
            atVar.a(new q(f.this.h()));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<i, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0039  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(i iVar) {
        String packageName;
        boolean z;
        k.b(iVar, "input");
        boolean useRoot = iVar.getUseRoot();
        AppBasic appPackage = iVar.getAppPackage();
        if (appPackage == null || (packageName = appPackage.getPackageName()) == null) {
            return cn.a("No app specified");
        }
        if (useRoot) {
            Boolean boolB = az.a((Context) h(), true).b();
            k.a((Object) boolB, "Init.canRoot(service, true).blockingGet()");
            z = boolB.booleanValue();
        }
        bn.b("E", "kill app: " + packageName + " root: " + useRoot + " canRoot: " + z);
        ActivityManager activityManagerAr = ah.ar(h());
        if (activityManagerAr == null) {
            h().a(R.string.err_no_activity_manager, new Object[0]);
            return cn.a(aj.a(R.string.err_no_activity_manager, h(), new Object[0]));
        }
        if (new c.l.k("acore").a(packageName)) {
            String str = "killApp: ignoring package resembling Android core: " + packageName;
            bn.d("E", str);
            return cn.a(str);
        }
        Boolean boolB2 = com.joaomgcd.taskerm.b.a.f5839b.a(h()).b();
        String str2 = "am force-stop " + packageName;
        if (!useRoot) {
            k.a((Object) boolB2, "hasAdbWifi");
            if (boolB2.booleanValue()) {
                com.joaomgcd.taskerm.b.a.b(new com.joaomgcd.taskerm.b.a(h()), str2, 0, false, 0, null, false, 62, null).b();
            } else {
                at.f8046b.b(h(), "adbwifikillwarning", a(R.string.an_kill_app), a(R.string.tip_action_requires_root_or_ADB_Wifi_root_equivalent), new a()).b();
                activityManagerAr.killBackgroundProcesses(packageName);
            }
            return new co();
        }
        if (!z) {
            bn.d("E", "killApp: can't root");
            return cn.a("killApp: can't root");
        }
        cf cfVar = (cf) ck.a.a(ck.f9218a, str2, 0L, 2, null).b();
        if (!cfVar.a()) {
            String str3 = "killApp: command not successful: " + cfVar;
            bn.c("E", str3);
            return cn.a(str3);
        }
        return new co();
    }
}
