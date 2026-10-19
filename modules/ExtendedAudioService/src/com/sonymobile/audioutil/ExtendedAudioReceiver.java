package com.sonymobile.audioutil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedAudioReceiver extends BroadcastReceiver {
    private static final String TAG = ExtendedAudioReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".onReceive : action = " + intentAction);
        if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intentAction) || "android.intent.action.BOOT_COMPLETED".equals(intentAction)) {
            context.startServiceAsUser(new Intent(context, (Class<?>) ExtendedAudioService.class), UserHandle.CURRENT_OR_SELF);
        }
    }
}
