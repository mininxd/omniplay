package com.sonyericsson.soundenhancement.audioeffect;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioEffectService {
    public static final int INVALID = -1;
    public static final int VALID_AUDIO_SESSION_ID = 1;

    public interface DolbyEventListener {
        void receiveEvents();
    }

    void cancelControlStatusListener(String str);

    void createDolbyEffectSettings(Context context);

    void createSessionHolder(String str, int i);

    void createSettings(Context context);

    int getBandLevel(int i);

    int[] getBandLevels();

    boolean getCurrentClearAudioPlus();

    boolean getCurrentClearPhase();

    boolean getCurrentClearStereo();

    boolean getCurrentDseeHx();

    boolean getCurrentDynamicNormalizer();

    int getCurrentEqualizerPreset();

    boolean getCurrentSForceSurround();

    boolean getCurrentSpeakerBundle();

    int getCurrentSurroundPreset();

    boolean getCurrentXLoud();

    int getDolbyProfile();

    int getDolbyState();

    boolean hasRunningSessions();

    void releaseBackgroundAudioSession(String str, int i);

    void releaseDolbyAudioEffect();

    void resetAudioSession(String str);

    void restoreAudioSessionFromPreference();

    void resumeActiveAudioSession();

    void saveSettingsToPrefs();

    void setBandLevel(int i, int i2);

    void setClearAudioPlus(boolean z);

    void setClearPhase(boolean z);

    void setClearStereo(boolean z);

    void setControlStatusListener(String str, AudioEffectManager.OnControlStatusChangeListener onControlStatusChangeListener);

    void setDAPStatus(boolean z);

    void setDolbyDecClientExist(boolean z);

    void setDolbyEventListener(DolbyEventListener dolbyEventListener);

    void setDolbyProfile(int i);

    void setDseeHx(boolean z, boolean z2);

    void setDynamicNormalizer(boolean z);

    void setEnabled(boolean z);

    void setSForceSurround(boolean z);

    void setSpeakerBundle(boolean z);

    void setSurroundPreset(int i);

    void setXLoud(boolean z);

    void swapDolbyState();

    void updateAudioSession(String str, int i, int i2);

    void updateDolbyUI();

    void useEqualizerPreset(int i);
}
