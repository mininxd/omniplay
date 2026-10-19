package com.sonyericsson.soundenhancement;

import android.app.Application;
import android.util.Log;
import com.sonymobile.audioeffect.SonyAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public class SoundEnhancementApplication extends Application {
    public static void createSonyAudioEffectInstance() {
        try {
            new SonyAudioEffect(0, 0);
            Log.d("SonyAudioEffectWrapper", "createSonyAudioEffectInstance() : instance has created successfully");
        } catch (Exception e) {
            Log.e("SonyAudioEffectWrapper", "createSonyAudioEffectInstance() : creating instance has failed, " + e.toString());
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.i("SoundEnhancementApplication", "onCreate()");
        createSonyAudioEffectInstance();
    }
}
