package com.joaomgcd.taskerm.event.datetime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.action.system.g;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class BroadcastReceiverNextAlarmChanged extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (g.a()) {
            bn.b("AlarmChanged", "Ignoring alarm changes in receiver");
            g.a(false);
            return;
        }
        bn.b("AlarmChanged", "System reported alarm changed");
        b.a.k.b bVarC = c.c();
        if (context != null) {
            bVarC.b_(new a(context, intent));
        }
    }
}
