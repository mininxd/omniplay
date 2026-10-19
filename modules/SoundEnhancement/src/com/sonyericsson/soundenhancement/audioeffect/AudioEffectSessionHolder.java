package com.sonyericsson.soundenhancement.audioeffect;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes2.dex */
class AudioEffectSessionHolder {
    private static final String AUDIO_SESSION_ID = "AUDIO_SESSION_ID";
    private static final String CONTENT_TYPE = "CONTENT_TYPE";
    private AudioEffectManager mAudioEffect;
    private int mAudioSession;
    private int mContentType;
    private String mPackageName;

    public AudioEffectSessionHolder(String packageName, int audioSession, int contentType) {
        if (packageName == null || packageName.length() <= 0) {
            throw new IllegalArgumentException("Invalid package name.");
        }
        if (audioSession < -1) {
            throw new IllegalArgumentException("Invalid audio session ID.");
        }
        if (contentType < -1) {
            throw new IllegalArgumentException("Invalid content type.");
        }
        this.mPackageName = packageName;
        this.mAudioSession = audioSession;
        this.mContentType = contentType;
    }

    public String getPackage() {
        return this.mPackageName;
    }

    public int getAudioSession() {
        return this.mAudioSession;
    }

    public void setAudioSession(int audioSession) {
        if (audioSession >= -1) {
            this.mAudioSession = audioSession;
            return;
        }
        throw new IllegalArgumentException("audioSession is invalid.");
    }

    public int getContentType() {
        return this.mContentType;
    }

    public AudioEffectManager getAudioEffect() {
        return this.mAudioEffect;
    }

    public void setAudioEffect(AudioEffectManager audioEffect) {
        AudioEffectManager audioEffectManager = this.mAudioEffect;
        if (audioEffectManager != null && audioEffectManager != audioEffect) {
            audioEffectManager.release();
        }
        this.mAudioEffect = audioEffect;
    }

    final void saveToPrefs(SharedPreferences.Editor editor) {
        editor.putInt(CONTENT_TYPE, this.mContentType);
        editor.putInt(AUDIO_SESSION_ID, this.mAudioSession);
    }

    public static final int getAudioSession(SharedPreferences prefs) {
        return prefs.getInt(AUDIO_SESSION_ID, -1);
    }

    public static final int getContentType(SharedPreferences prefs) {
        return prefs.getInt(CONTENT_TYPE, -1);
    }
}
