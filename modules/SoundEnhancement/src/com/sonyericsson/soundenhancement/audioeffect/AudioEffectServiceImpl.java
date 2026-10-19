package com.sonyericsson.soundenhancement.audioeffect;

import android.content.Context;
import com.sonyericsson.soundenhancement.SoundEnhancementUtility;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectServiceImpl implements AudioEffectService {
    private DolbyEffectSettings mDolby;
    private AudioEffectService.DolbyEventListener mDolbyEventListener = null;
    private AudioEffectSettings mSettings;

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void createSettings(Context context) {
        this.mSettings = new AudioEffectSettings(context);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void updateAudioSession(String packageName, int audioSession, int contentType) {
        try {
            this.mSettings.updateAudioSession(packageName, audioSession, contentType);
        } catch (RuntimeException e) {
            this.mSettings.createSessionHolder(packageName, contentType);
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void createSessionHolder(String packageName, int contentType) {
        this.mSettings.createSessionHolder(packageName, contentType);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void resetAudioSession(String packageName) {
        this.mSettings.resetAudioSession(packageName);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void releaseBackgroundAudioSession(String packageName, int audioSession) {
        this.mSettings.releaseBackgroundAudioSession(packageName, audioSession);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void resumeActiveAudioSession() {
        this.mSettings.resumeActiveAudioSession();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean hasRunningSessions() {
        return this.mSettings.hasRunningSessions();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public int getCurrentEqualizerPreset() {
        return this.mSettings.getCurrentEqualizerPreset();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public int getCurrentSurroundPreset() {
        return this.mSettings.getCurrentSurroundPreset();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setSurroundPreset(int preset) {
        this.mSettings.setSurroundPreset(preset);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void useEqualizerPreset(int preset) {
        this.mSettings.useEqualizerPreset(preset);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public int[] getBandLevels() {
        return this.mSettings.getBandLevels();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public int getBandLevel(int band) {
        return this.mSettings.getBandLevel(band);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setBandLevel(int band, int level) {
        this.mSettings.setBandLevel(band, level);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentClearStereo() {
        return this.mSettings.getCurrentClearStereo();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setClearStereo(boolean enabled) {
        this.mSettings.setClearStereo(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentDynamicNormalizer() {
        return this.mSettings.getCurrentDynamicNormalizer();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setDynamicNormalizer(boolean enabled) {
        this.mSettings.setDynamicNormalizer(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentClearAudioPlus() {
        return this.mSettings.getCurrentClearAudioPlus();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setClearAudioPlus(boolean enabled) {
        this.mSettings.setClearAudioPlus(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentXLoud() {
        return this.mSettings.getCurrentXLoud();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setXLoud(boolean enabled) {
        this.mSettings.setXLoud(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentSForceSurround() {
        return this.mSettings.getCurrentSForceSurround();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setSForceSurround(boolean enabled) {
        this.mSettings.setSForceSurround(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentSpeakerBundle() {
        return this.mSettings.getCurrentSpeakerBundle();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setSpeakerBundle(boolean enabled) {
        this.mSettings.setSpeakerBundle(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentClearPhase() {
        return this.mSettings.getCurrentClearPhase();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setClearPhase(boolean enabled) {
        this.mSettings.setClearPhase(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public boolean getCurrentDseeHx() {
        return this.mSettings.getCurrentDseeHx();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setDseeHx(boolean enabled, boolean doSendIntent) {
        this.mSettings.setDseeHx(enabled, doSendIntent);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setEnabled(boolean enabled) {
        this.mSettings.setEnabled(enabled);
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void saveSettingsToPrefs() {
        this.mSettings.saveSettingsToPrefs();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setControlStatusListener(String packageName, AudioEffectManager.OnControlStatusChangeListener listener) {
        try {
            this.mSettings.setControlStatusListener(packageName, listener);
        } catch (RuntimeException e) {
            resetAudioSession(packageName);
            this.mSettings.setControlStatusListener(packageName, listener);
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void cancelControlStatusListener(String packageName) {
        try {
            createControlStatusListener(packageName);
        } catch (RuntimeException e) {
            resetAudioSession(packageName);
            createControlStatusListener(packageName);
        }
    }

    private void createControlStatusListener(String packageName) {
        this.mSettings.setControlStatusListener(packageName, new AudioEffectManager.OnControlStatusChangeListener() { // from class: com.sonyericsson.soundenhancement.audioeffect.AudioEffectServiceImpl.1
            @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager.OnControlStatusChangeListener
            public void onControlStatusChange(boolean controlGranted) {
            }
        });
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void restoreAudioSessionFromPreference() {
        this.mSettings.restoreAudioSessionFromPreference();
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void createDolbyEffectSettings(Context context) {
        if (SoundEnhancementUtility.isSupportedDolbyEffect()) {
            this.mDolby = new DolbyEffectSettings(context);
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void releaseDolbyAudioEffect() {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings != null) {
            dolbyEffectSettings.release();
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setDolbyEventListener(AudioEffectService.DolbyEventListener listener) {
        if (this.mDolby != null) {
            this.mDolbyEventListener = listener;
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void updateDolbyUI() {
        AudioEffectService.DolbyEventListener dolbyEventListener;
        if (this.mDolby != null && (dolbyEventListener = this.mDolbyEventListener) != null) {
            dolbyEventListener.receiveEvents();
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setDolbyDecClientExist(boolean exist) {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings != null) {
            dolbyEffectSettings.setDolbyDecClientExist(exist);
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setDAPStatus(boolean enable) {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings != null) {
            dolbyEffectSettings.setDAPStatus(enable);
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void swapDolbyState() {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings != null) {
            dolbyEffectSettings.swapDolbyState();
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public int getDolbyState() {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings == null) {
            return 2;
        }
        int state = dolbyEffectSettings.getDolbyState();
        return state;
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public void setDolbyProfile(int profile) {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings != null) {
            dolbyEffectSettings.setDolbyProfile(profile);
        }
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService
    public int getDolbyProfile() {
        DolbyEffectSettings dolbyEffectSettings = this.mDolby;
        if (dolbyEffectSettings == null) {
            return -1;
        }
        int profile = dolbyEffectSettings.getDolbyProfile();
        return profile;
    }
}
