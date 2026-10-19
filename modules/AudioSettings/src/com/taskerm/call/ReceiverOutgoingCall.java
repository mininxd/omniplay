package com.joaomgcd.taskerm.call;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import net.dinglisch.android.taskerm.MonitorService;

/* JADX INFO: loaded from: classes.dex */
public final class ReceiverOutgoingCall extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        k.b(context, "context");
        if (intent == null || (!k.a((Object) intent.getAction(), (Object) "android.intent.action.NEW_OUTGOING_CALL"))) {
            return;
        }
        MonitorService.b(context, intent, -1);
    }
}
