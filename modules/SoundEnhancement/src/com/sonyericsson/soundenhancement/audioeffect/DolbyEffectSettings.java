package com.sonyericsson.soundenhancement.audioeffect;

import android.content.Context;
import android.media.AudioManager;
import com.dolby.dax.DolbyAudioEffect;

/* JADX INFO: loaded from: classes2.dex */
public class DolbyEffectSettings {
    public static final String ACTION_DAP_PARAMS_UPDATE = "dolby_dap_params_update";
    public static final int DOLBY_PROFILE_CUSTOM = 3;
    public static final int DOLBY_PROFILE_DYNAMIC = 0;
    public static final int DOLBY_PROFILE_MOVIE = 1;
    public static final int DOLBY_PROFILE_MUSIC = 2;
    public static final int DOLBY_PROFILE_NUM = 4;
    public static final int DS_STATE_OFF = 0;
    public static final int DS_STATE_ON = 1;
    public static final String EVENT_DOLBY_CLIENT_NUM = "dec_client_num";
    public static final String EVENT_DOLBY_STATE_CHANGE = "ds_state_change";
    public static final String EXTRA_DOLBY_EVENT = "event name";
    public static final String EXTRA_DOLBY_INT_VALUE = "Integer Value";
    private static final String KEY_DOLBY_DAP_ENABLE = "dolby_dap_enable";
    private static final String KEY_DOLBY_DEC_EXIST = "dolby_dec_exist";
    private static final String LOG_TAG = "SoundEnhancement";
    public static final int PROFILE_DEFAULT = -1;
    public static final int STATE_DEFAULT = 2;
    private static int mPriority = -1;
    private AudioManager mAudioManager;
    private Context mContext;
    private DolbyAudioEffect mDolbyAudio = null;

    public DolbyEffectSettings(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        createDolbyAudioEffect();
    }

    private boolean createDolbyAudioEffect() {
        if (this.mDolbyAudio == null) {
            try {
                this.mDolbyAudio = new DolbyAudioEffect(mPriority, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.mDolbyAudio != null;
    }

    public void release() {
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            dolbyAudioEffect.release();
            this.mDolbyAudio = null;
        }
    }

    public void setDolbyDecClientExist(boolean exist) {
        setDolbyDecClientExistWithAudioManager(exist);
    }

    public void setDAPStatus(boolean enable) {
        setDAPStatusWithAudioManager(enable);
    }

    public void swapDolbyState() {
        if (createDolbyAudioEffect()) {
            try {
                boolean newState = !this.mDolbyAudio.getDsOn();
                if (!this.mDolbyAudio.hasControl()) {
                    this.mDolbyAudio.release();
                    mPriority = 0;
                    this.mDolbyAudio = new DolbyAudioEffect(mPriority, 0);
                }
                this.mDolbyAudio.setDsOn(newState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getDolbyState() {
        if (!createDolbyAudioEffect()) {
            return 2;
        }
        try {
            return this.mDolbyAudio.getDsOn() ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    public void setDolbyProfile(int profile) {
        if (createDolbyAudioEffect()) {
            try {
                String version = this.mDolbyAudio.getDsVersion().substring(0, 3);
                if (!this.mDolbyAudio.hasControl()) {
                    this.mDolbyAudio.release();
                    mPriority = 0;
                    this.mDolbyAudio = new DolbyAudioEffect(mPriority, 0);
                }
                if (version.equals("DS1")) {
                    this.mDolbyAudio.setProfile(profile + 1);
                } else {
                    this.mDolbyAudio.setProfile(profile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getDolbyProfile() {
        int profile = -1;
        if (createDolbyAudioEffect()) {
            try {
                String version = this.mDolbyAudio.getDsVersion().substring(0, 3);
                if (version.equals("DS1")) {
                    profile = this.mDolbyAudio.getProfile() - 1;
                } else {
                    profile = this.mDolbyAudio.getProfile();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profile;
    }

    private void setDolbyDecClientExistWithAudioManager(boolean exist) {
        StringBuilder sb = new StringBuilder();
        sb.append("dolby_dec_exist=");
        sb.append(exist ? "1" : "0");
        String dlbDecParam = sb.toString();
        this.mAudioManager.setParameters(dlbDecParam);
    }

    private void setDAPStatusWithAudioManager(boolean enable) {
        StringBuilder sb = new StringBuilder();
        sb.append("dolby_dap_enable=");
        sb.append(enable ? "1" : "0");
        String dlbDapState = sb.toString();
        this.mAudioManager.setParameters(dlbDapState);
    }
}
