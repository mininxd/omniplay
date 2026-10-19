package com.joaomgcd.taskerm.action.net;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.tasker.InputConnectToWifi;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class al extends com.joaomgcd.taskerm.helper.actions.execute.l<av> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public al(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<av, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"MissingPermission"})
    public cl a(av avVar) {
        Object next;
        String strA;
        c.f.b.k.b(avVar, "input");
        String strA2 = com.joaomgcd.taskerm.util.ak.A(avVar.getSsid());
        if (strA2 == null) {
            return cn.a("Must specify SSID");
        }
        WifiManager wifiManagerR = com.joaomgcd.taskerm.util.ah.r(h());
        if (wifiManagerR == null) {
            return cn.a("Couldn't get Wifi Manager");
        }
        List<WifiConfiguration> configuredNetworks = wifiManagerR.getConfiguredNetworks();
        c.f.b.k.a((Object) configuredNetworks, "configuredNetworks");
        Iterator<T> it = configuredNetworks.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            strA = com.joaomgcd.taskerm.util.ak.A(((WifiConfiguration) next).SSID);
        } while (!(strA != null ? c.f.b.k.a((Object) c.l.n.a(strA, "\"", "", false, 4, (Object) null), (Object) avVar.getSsid()) : false));
        WifiConfiguration wifiConfiguration = (WifiConfiguration) next;
        if (wifiConfiguration == null) {
            return cn.a("Couldn't find matching configured network");
        }
        if (wifiManagerR.enableNetwork(wifiConfiguration.networkId, true)) {
            return new co();
        }
        cl clVarB = net.dinglisch.android.tasker.d.f10445a.a(h(), new InputConnectToWifi(strA2)).b();
        c.f.b.k.a((Object) clVarB, "TaskerBackCompat.connect…Wifi(ssid)).blockingGet()");
        return clVarB;
    }
}
