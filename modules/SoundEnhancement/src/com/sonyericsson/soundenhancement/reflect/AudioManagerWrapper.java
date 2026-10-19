package com.sonyericsson.soundenhancement.reflect;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class AudioManagerWrapper extends ReflectionHelper {
    private static AudioManagerWrapper sInstance = new AudioManagerWrapper();

    private AudioManagerWrapper() {
    }

    public static final AudioManagerWrapper getInstance() {
        return sInstance;
    }

    public int getAudioFocusState(AudioManager manager) {
        int ret = 0;
        if (manager != null) {
            try {
                ret = ((Integer) invokeMethod(manager, "getAudioFocusState", new Object[0])).intValue();
            } catch (NullPointerException unused) {
                ret = requestAudioFocus(manager);
            }
        }
        Log.i("AHC Audio Focus", String.valueOf(ret));
        return ret;
    }

    public static int requestAudioFocus(AudioManager audioManager) {
        return audioManager.requestAudioFocus(new AudioFocusRequest.Builder(1).build());
    }
}
