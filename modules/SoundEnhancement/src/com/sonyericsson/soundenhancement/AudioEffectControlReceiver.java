package com.sonyericsson.soundenhancement;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectControlReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "SoundEnhancement";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION") && !SoundEnhancementUtility.isForeground()) {
            return;
        }
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName componentName = new ComponentName(context, (Class<?>) SoundEnhancementService.class);
        serviceStartIntent.setComponent(componentName);
        serviceStartIntent.putExtra("com.sonyericsson.soundenhancement.EXTRA_ACTION", action);
        String packageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        int audioSession = intent.getIntExtra("android.media.extra.AUDIO_SESSION", -1);
        int contentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", -1);
        serviceStartIntent.putExtra("android.media.extra.PACKAGE_NAME", packageName);
        serviceStartIntent.putExtra("android.media.extra.AUDIO_SESSION", audioSession);
        serviceStartIntent.putExtra("android.media.extra.CONTENT_TYPE", contentType);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(serviceStartIntent);
            } else {
                context.startService(serviceStartIntent);
            }
        } catch (IllegalStateException e) {
        }
    }
}
