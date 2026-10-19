package com.dolby.daxappui;

import android.util.Log;
import com.dolby.dax.DolbyAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public class DsClientSettings {
    public static final DsClientSettings INSTANCE = new DsClientSettings();

    void setDialogEnhancerAmount(IDsFragObserver iDsFragObserver, int i) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null || dolbyAudioEffect.getDialogEnhancerAmount() == i) {
            return;
        }
        if (dolbyAudioEffect.hasControl()) {
            dolbyAudioEffect.setDialogEnhancerAmount(i);
        } else {
            Log.w("DsClientSettings", "Dolby audio effect is out of control in setDialogEnhancerAmount");
        }
    }

    int getDialogEnhancerAmount(IDsFragObserver iDsFragObserver) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            return 0;
        }
        return dolbyAudioEffect.getDialogEnhancerAmount();
    }

    boolean getGraphicEqualizerOn(IDsFragObserver iDsFragObserver, int i) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        return dolbyAudioEffect != null && dolbyAudioEffect.isGeqEnabled(i);
    }

    void setGraphicEqualizerBandGains(IDsFragObserver iDsFragObserver, int[] iArr) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            return;
        }
        if (dolbyAudioEffect.hasControl()) {
            dolbyAudioEffect.setGeqBandGains(iArr);
        } else {
            Log.w("DsClientSettings", "Dolby audio effect is out of control in setGraphicEqualizerBandGains");
        }
    }

    int[] getGraphicEqualizerBandGains(IDsFragObserver iDsFragObserver) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            return null;
        }
        return dolbyAudioEffect.getGeqBandGains();
    }

    void setIeqPreset(IDsFragObserver iDsFragObserver, int i) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            return;
        }
        if (i == 3) {
            i = -1;
        }
        int i2 = i + 1;
        if (dolbyAudioEffect.getIeqPreset() != i2) {
            if (dolbyAudioEffect.hasControl()) {
                dolbyAudioEffect.setIeqPreset(i2);
            } else {
                Log.w("DsClientSettings", "Dolby audio effect is out of control in setIeqPreset");
            }
        }
    }

    int getIeqPreset(IDsFragObserver iDsFragObserver) {
        int ieqPreset;
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null || (ieqPreset = dolbyAudioEffect.getIeqPreset() - 1) == -1) {
            return 3;
        }
        return ieqPreset;
    }

    boolean isProfileSpecificSettingsModified(IDsFragObserver iDsFragObserver, int i) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        return dolbyAudioEffect != null && dolbyAudioEffect.isProfileSpecificSettingsModified(i);
    }

    void resetProfileSpecificSettings(IDsFragObserver iDsFragObserver, int i) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            return;
        }
        if (dolbyAudioEffect.hasControl()) {
            dolbyAudioEffect.resetProfileSpecificSettings(i);
        } else {
            Log.w("DsClientSettings", "Dolby audio effect is out of control in resetProfileSpecificSettings");
        }
    }

    public void resetAllProfileSpecificSettings(IDsFragObserver iDsFragObserver) {
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect == null) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (dolbyAudioEffect.hasControl()) {
                dolbyAudioEffect.resetProfileSpecificSettings(i);
            } else {
                Log.w("DsClientSettings", "Dolby audio effect is out of control in resetAllProfileSpecificSettings");
            }
        }
    }
}
