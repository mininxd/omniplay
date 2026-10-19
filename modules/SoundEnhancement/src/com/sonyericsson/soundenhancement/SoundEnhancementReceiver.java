package com.sonyericsson.soundenhancement;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "SoundEnhancement";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName componentName = new ComponentName(context, (Class<?>) SoundEnhancementService.class);
        serviceStartIntent.setComponent(componentName);
        serviceStartIntent.putExtra("com.sonyericsson.soundenhancement.EXTRA_ACTION", action);
        if (action.equals("com.sonymobile.media.intent.action.ACTION_EXTERNAL_STEREO_MIC_PLUG") || action.equals("com.sonyericsson.system.intent.action.HEADSET_PLUG") || action.equals("com.sonymobile.media.intent.action.FIVE_POLE_ACCESSORY_STATUS")) {
            int state = intent.getIntExtra("state", -1);
            serviceStartIntent.putExtra("state", state);
        } else if (action.equals("com.sonyericsson.soundenhancement.intent.action.NOISE_CANCELING_TOGGLE_CHANGE")) {
            boolean nextState = intent.getBooleanExtra("intent.extra.next_noise_canceling_state", true);
            serviceStartIntent.putExtra("intent.extra.next_noise_canceling_state", nextState);
            Reporter.reportEvent(context, ReportContents.EVENT_NOISE_CANCELING, nextState);
        } else if (!action.equals(SoundEnhancementService.ACTION_CONFIG_UPDATED)) {
            if (action.equals("dolby_dap_params_update")) {
                String event = intent.getStringExtra("event name");
                serviceStartIntent.putExtra("event name", event);
                int value = intent.getIntExtra("Integer Value", 0);
                serviceStartIntent.putExtra("Integer Value", value);
            } else {
                String packageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
                int audioSession = intent.getIntExtra("android.media.extra.AUDIO_SESSION", -1);
                int contentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", -1);
                serviceStartIntent.putExtra("android.media.extra.PACKAGE_NAME", packageName);
                serviceStartIntent.putExtra("android.media.extra.AUDIO_SESSION", audioSession);
                serviceStartIntent.putExtra("android.media.extra.CONTENT_TYPE", contentType);
            }
        }
        try {
            context.startService(serviceStartIntent);
        } catch (IllegalStateException e) {
        } catch (SecurityException e2) {
        }
    }
}
