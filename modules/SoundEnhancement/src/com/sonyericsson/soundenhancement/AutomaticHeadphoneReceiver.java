package com.sonyericsson.soundenhancement;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.sonyericsson.soundenhancement.ahc.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class AutomaticHeadphoneReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null || !SoundEnhancementUtility.getSupportAutoHeadsetCompensation()) {
            return;
        }
        Logger.LOGD("received headset broadcast");
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName componentName = new ComponentName(context, (Class<?>) AutomaticHeadphoneService.class);
        serviceStartIntent.setComponent(componentName);
        serviceStartIntent.putExtra("com.sonyericsson.soundenhancement.EXTRA_ACTION", action);
        if (action.equals("com.sonyericsson.system.intent.action.HEADSET_PLUG") || action.equals("com.sonymobile.media.intent.action.FIVE_POLE_ACCESSORY_STATUS")) {
            int state = intent.getIntExtra("state", -1);
            serviceStartIntent.putExtra("state", state);
        }
        try {
            context.startService(serviceStartIntent);
        } catch (IllegalStateException e) {
            Logger.LOGW("Failed to start service as background with intent : " + action);
        } catch (SecurityException e2) {
            Logger.LOGW("Failed to start service as security with intent : " + action);
        }
    }
}
