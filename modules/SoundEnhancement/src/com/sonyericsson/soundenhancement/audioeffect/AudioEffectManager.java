package com.sonyericsson.soundenhancement.audioeffect;

import android.media.audiofx.AudioEffect;
import android.util.Log;
import com.sonyericsson.soundenhancement.reflect.SystemPropertiesWrapper;
import com.sonymobile.audioeffect.SonyAudioEffect;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectManager {
    public static final int EFFECT_CA = 8;
    public static final int EFFECT_CLEARAUDIOPLUS = 128;
    public static final int EFFECT_CLEARPHASE = 32;
    public static final int EFFECT_CLEARPHASE_HP = 4;
    public static final int EFFECT_CLEAR_STEREO = 512;
    public static final int EFFECT_DN = 1;
    public static final int EFFECT_NONE = 0;
    public static final int EFFECT_SFORCE = 2;
    public static final int EFFECT_SPEAKER_BUNDLE = 256;
    public static final int EFFECT_VPT = 16;
    public static final int EFFECT_XLOUD = 64;
    private static final String LOG_TAG = "SoundEnhancement";
    private static final int PRESET_OFF = 0;
    private SonyAudioEffect mSonyAudioEffect;
    private int mEnabledEffects = 0;
    private boolean mEnabled = false;
    private OnControlStatusChangeListener mControlChangeStatusListener = null;

    public interface OnControlStatusChangeListener {
        void onControlStatusChange(boolean z);
    }

    public AudioEffectManager(int audioSession) {
        this.mSonyAudioEffect = null;
        this.mSonyAudioEffect = createSonyAudioEffect(audioSession);
    }

    public void release() {
        SonyAudioEffect sonyAudioEffect = this.mSonyAudioEffect;
        if (sonyAudioEffect != null) {
            sonyAudioEffect.release();
            this.mSonyAudioEffect = null;
        }
    }

    public int getCurrentSurroundPreset() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return 0;
            }
            int currentPreset = this.mSonyAudioEffect.getCurrentSurroundPreset();
            return currentPreset;
        } catch (IllegalArgumentException e) {
            return 0;
        } catch (IllegalStateException e2) {
            return 0;
        } catch (UnsupportedOperationException e3) {
            return 0;
        } catch (RuntimeException e4) {
            return 0;
        }
    }

    public boolean getCurrentDynamicNormalizer() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentDynamicNormalizer();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public boolean getCurrentClearStereo() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentClearStereo();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public boolean getCurrentXLoud() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentXLoud();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public boolean getCurrentClearPhase() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentClearPhase();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public boolean getCurrentSForceSurround() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentSForceSurround();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public boolean getCurrentClearAudioPlus() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentClearAudioPlus();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public boolean getCurrentSpeakerBundle() {
        try {
            if (this.mSonyAudioEffect == null || !this.mSonyAudioEffect.getEnabled()) {
                return false;
            }
            boolean enabled = this.mSonyAudioEffect.getCurrentSpeakerBundle();
            return enabled;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalStateException e2) {
            return false;
        } catch (UnsupportedOperationException e3) {
            return false;
        } catch (RuntimeException e4) {
            return false;
        }
    }

    public void setSurroundPreset(int preset) {
        if (preset < 0) {
            throw new IllegalArgumentException("Preset number must not be negative.");
        }
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setSurroundPreset((short) preset);
                setEnabled(16, preset != 0);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setDynamicNormalizer(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setDynamicNormalizer(enabled);
                setEnabled(1, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setClearStereo(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setClearStereo(enabled);
                setEnabled(512, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setXLoud(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setXLoud(enabled);
                setEnabled(64, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setClearPhase(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setClearPhase(enabled);
                setEnabled(32, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setSForceSurround(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setSForceSurround(enabled);
                setEnabled(2, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setClearAudioPlus(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setClearAudioPlus(enabled);
                setEnabled(128, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setSpeakerBundle(boolean enabled) {
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setSpeakerBundle(enabled);
                setEnabled(256, enabled);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public void setBandLevels(int[] levels, boolean allowDisable) {
        if (levels == null) {
            Log.e(LOG_TAG, "AudioEffectManager.setBandLevels(): levels parameter is null");
            return;
        }
        try {
            if (this.mSonyAudioEffect != null) {
                this.mSonyAudioEffect.setBandLevels(levels);
                boolean enabled = false;
                for (int i : levels) {
                    if (i != 0) {
                        enabled = true;
                        break;
                    }
                }
                setEnabled(8, enabled, allowDisable);
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        } catch (UnsupportedOperationException e3) {
        } catch (RuntimeException e4) {
        }
    }

    public boolean hasControl() {
        try {
            if (this.mSonyAudioEffect != null) {
                return this.mSonyAudioEffect.hasControl();
            }
            return true;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    public void setControlStatusListener(OnControlStatusChangeListener listener) {
        SonyAudioEffect sonyAudioEffect = this.mSonyAudioEffect;
        if (sonyAudioEffect != null) {
            this.mControlChangeStatusListener = listener;
            sonyAudioEffect.setControlStatusListener(new AudioEffect.OnControlStatusChangeListener() { // from class: com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager.1
                @Override // android.media.audiofx.AudioEffect.OnControlStatusChangeListener
                public void onControlStatusChange(AudioEffect effect, boolean controlGranted) {
                    if (AudioEffectManager.this.mControlChangeStatusListener != null) {
                        AudioEffectManager.this.mControlChangeStatusListener.onControlStatusChange(controlGranted);
                    }
                }
            });
            OnControlStatusChangeListener onControlStatusChangeListener = this.mControlChangeStatusListener;
            if (onControlStatusChangeListener != null) {
                onControlStatusChangeListener.onControlStatusChange(this.mSonyAudioEffect.hasControl());
            }
        }
    }

    protected SonyAudioEffect createSonyAudioEffect(int audioSession) {
        if (audioSession >= 1) {
            try {
                SonyAudioEffect SonyAudioEffect = constructSonyAudioEffect(audioSession);
                return SonyAudioEffect;
            } catch (IllegalArgumentException e) {
                return null;
            } catch (UnsupportedOperationException e2) {
                return null;
            } catch (RuntimeException e3) {
                return null;
            }
        }
        return null;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
        setEnabled(0, false);
    }

    private void setEnabled(int effectType, boolean enabled) {
        setEnabled(effectType, enabled, true);
    }

    private void setEnabled(int effectType, boolean enabled, boolean allowDisable) {
        if (enabled) {
            this.mEnabledEffects |= effectType;
        } else {
            this.mEnabledEffects &= ~effectType;
        }
        SonyAudioEffect sonyAudioEffect = this.mSonyAudioEffect;
        if (sonyAudioEffect != null) {
            boolean wasEnabled = sonyAudioEffect.getEnabled();
            boolean isEnabled = this.mEnabled;
            if (this.mEnabled) {
                isEnabled = this.mEnabledEffects != 0;
            }
            if (wasEnabled != isEnabled) {
                if (isEnabled || allowDisable) {
                    this.mSonyAudioEffect.setEnabled(isEnabled);
                }
            }
        }
    }

    protected SonyAudioEffect constructSonyAudioEffect(int audioSession) {
        String str;
        if (SystemPropertiesWrapper.get("ro.sony.global.effect", "true").equals("true")) {
            str = "Global type soundfx";
            audioSession = 0;
        } else {
            str = "Post process type soundfx";
        }
        Log.d(LOG_TAG, str);
        return new SonyAudioEffect(0, audioSession);
    }
}
