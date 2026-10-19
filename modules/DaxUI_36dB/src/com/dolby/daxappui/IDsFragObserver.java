package com.dolby.daxappui;

import com.dolby.dax.DolbyAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public interface IDsFragObserver {
    void chooseProfile(int i);

    int getActivePort();

    DolbyAudioEffect getDolbyAudioEffect();

    void resetProfile(int i);
}
