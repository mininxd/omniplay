package com.joaomgcd.taskerm.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Wifi$wifiNetworks$wifiScanReceiver$1 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ac f7837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ WifiManager f7838b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ b.a.k.d f7839c;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        c.f.b.k.b(context, "c");
        c.f.b.k.b(intent, "intent");
        if (intent.getAction() == "android.net.wifi.SCAN_RESULTS") {
            this.f7837a.b().unregisterReceiver(this);
            ArrayList scanResults = this.f7838b.getScanResults();
            if (scanResults == null) {
                scanResults = new ArrayList();
            }
            this.f7839c.d_(scanResults);
        }
    }
}
