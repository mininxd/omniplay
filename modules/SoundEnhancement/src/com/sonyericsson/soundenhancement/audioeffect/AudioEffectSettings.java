package com.sonyericsson.soundenhancement.audioeffect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.util.Log;
import com.sonyericsson.soundenhancement.PreferenceUtil;
import com.sonyericsson.soundenhancement.R;
import com.sonyericsson.soundenhancement.SoundEnhancementUtility;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
class AudioEffectSettings {
    private static final String BAND = "BAND";
    static final String CLEAR_AUDIO_PLUS = "C_A_PLUS";
    private static final String CLEAR_STEREO = "C_STEREO";
    static final String DSEE_HX = "DSEE_HX";
    private static final String DYNAMIC_NORMALIZER = "D_NORMAL";
    private static final String EQUALIZER_PRESET = "EQUALIZER_PRESET";
    private static final int INVALID_VALUE = Integer.MAX_VALUE;
    private static final String KEY_CLIENT_ALL_CLEAR = "s_e_client_session_clear=";
    private static final String KEY_CLIENT_ID = "s_e_client_session_id=";
    private static final String KEY_CLIENT_STATE = "s_e_client_session_state=";
    private static final String KEY_DSEE_HX_STATE = "dsee_hx_state";
    private static final String KEY_VPT_MODE = "vpt_mode=";
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = "SoundEnhancement";
    static final String PREFERENCE_CLEAR_AUDIO_PLUS_NAME = "SE-CLEAR_AUDIO_PLUS";
    static final String PREFERENCE_DSEE_HX_NAME = "SE-DSEE-HX";
    private static final String PREFERENCE_SETTING_NAME = "SE-SETTINGS";
    private static final String PREFERENCE_SUFFIX = "-SESSION-INFO";
    public static final String SHARED_PREFS_FOLDER_PATH = "shared_prefs";
    private static final String SPEAKER_BUNDLE = "SPEAKER_BUNDLE";
    private static final String SURROUND_PRESET = "SURROUND_PRESET";
    private static final String S_FORCE_SURROUND = "S_FORCE";
    private static final String USER1BAND = "USER1BAND";
    private static final String USER2BAND = "USER2BAND";
    private static final String XML_EXTENSION = ".xml";
    private static final String X_LOUD = "X_LOUD";
    private AudioManager mAudioManager;
    private boolean mClearAudioPlus;
    private boolean mClearPhase;
    private boolean mClearStereo;
    private Context mContext;
    private boolean mDseeHx;
    private boolean mDynamicNormalizer;
    private int mEqualizerPreset;
    private boolean mSForceSurround;
    private boolean mSpeakerBundle;
    private int mSurroundPreset;
    private boolean mXLoud;
    private ArrayList<AudioEffectSessionHolder> mSessionHolder = new ArrayList<>();
    private boolean mIsDseeHxGlobalSetting = SoundEnhancementUtility.supportedGlobalDseeHx();
    private int[] mBandLevels = new int[6];
    private int[] mUser1BandLevels = new int[6];
    private int[] mUser2BandLevels = new int[6];

    public AudioEffectSettings(Context context) {
        this.mContext = null;
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        getSettingFromPrefs();
        resetAllAudioSessions();
    }

    public void updateAudioSession(String packageName, int audioSession, int contentType) {
        AudioEffectManager audioEffect;
        boolean needAllClear = this.mSessionHolder.size() == 0;
        AudioEffectSessionHolder sessionHolder = findSessionHolder(packageName);
        if (sessionHolder != null) {
            int oldAudioSession = sessionHolder.getAudioSession();
            if (oldAudioSession != audioSession) {
                AudioEffectManager audioEffect2 = sessionHolder.getAudioEffect();
                if (audioEffect2 != null) {
                    audioEffect2.release();
                    sessionHolder.setAudioEffect(null);
                }
                this.mSessionHolder.remove(sessionHolder);
                createNewSessionHolder(packageName, audioSession, contentType);
            } else if (audioSession > -1 && (audioEffect = sessionHolder.getAudioEffect()) != null && !audioEffect.hasControl()) {
                throw new UnsupportedOperationException("AudioEffect: invalid parameter operation");
            }
        } else {
            createNewSessionHolder(packageName, audioSession, contentType);
        }
        setClientId(audioSession, true, needAllClear);
        saveSessionInfoToPrefs(packageName);
    }

    private void initializeParameters(AudioEffectManager audioEffect) {
        Resources res = this.mContext.getResources();
        boolean supportXLoud = res.getBoolean(R.bool.enabled_xloud);
        boolean supportClearPhase = res.getBoolean(R.bool.enabled_clear_phase);
        boolean supportSForceSurround = res.getBoolean(R.bool.enabled_s_force_surround);
        boolean supportSpeakerBundle = res.getBoolean(R.bool.enabled_speaker_bundle);
        boolean supportClearStereo = res.getBoolean(R.bool.enabled_clear_stereo);
        boolean supportDynamicNormalizer = res.getBoolean(R.bool.enabled_dynamic_normalizer);
        boolean supportClearAudioPlus = SoundEnhancementUtility.isSupportedClearAudioPlus();
        if (this.mIsDseeHxGlobalSetting) {
            boolean enabledDseeHx = getDseeHxWithAudioManager();
            audioEffect.setEnabled(!enabledDseeHx);
        } else {
            audioEffect.setEnabled(true);
        }
        audioEffect.setBandLevels(getBandLevels(), true);
        setSurroundPreset(this.mContext, this.mSurroundPreset);
        audioEffect.setSurroundPreset(this.mSurroundPreset);
        if (supportDynamicNormalizer) {
            audioEffect.setDynamicNormalizer(this.mDynamicNormalizer);
        }
        if (supportClearStereo) {
            audioEffect.setClearStereo(this.mClearStereo);
        }
        if (supportXLoud) {
            audioEffect.setXLoud(this.mXLoud);
        }
        if (supportSForceSurround) {
            audioEffect.setSForceSurround(this.mSForceSurround);
        }
        if (supportSpeakerBundle) {
            audioEffect.setSpeakerBundle(this.mSpeakerBundle);
        }
        if (supportClearPhase) {
            audioEffect.setClearPhase(this.mClearPhase);
        }
        if (supportClearAudioPlus) {
            audioEffect.setClearAudioPlus(this.mClearAudioPlus);
        }
    }

    public void createSessionHolder(String packageName, int contentType) {
        createNewSessionHolder(packageName, -1, contentType);
    }

    public void resetAudioSession(String packageName) {
        AudioEffectSessionHolder sessionHolder = findSessionHolder(packageName);
        if (sessionHolder != null) {
            int oldSession = sessionHolder.getAudioSession();
            sessionHolder.setAudioSession(-1);
            AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
            if (audioEffect != null) {
                audioEffect.release();
                sessionHolder.setAudioEffect(null);
            }
            boolean needAllClear = this.mSessionHolder.size() == 0;
            setClientId(oldSession, false, needAllClear);
        }
        saveSessionInfoToPrefs(packageName);
    }

    public void resetAllAudioSessions() {
        int count = this.mSessionHolder.size();
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                sessionHolder.setAudioSession(-1);
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioEffect != null) {
                    audioEffect.release();
                    sessionHolder.setAudioEffect(null);
                }
            }
        }
    }

    public void releaseBackgroundAudioSession(String packageName, int audioSession) {
    }

    public void resumeActiveAudioSession() {
    }

    boolean hasRunningSessions() {
        int count = this.mSessionHolder.size();
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder.getAudioEffect() != null) {
                return true;
            }
        }
        return false;
    }

    public int getCurrentEqualizerPreset() {
        return this.mEqualizerPreset;
    }

    public int getCurrentSurroundPreset() {
        return this.mSurroundPreset;
    }

    public int[] getBandLevels() {
        int i = this.mEqualizerPreset;
        if (i == 9) {
            return this.mBandLevels;
        }
        if (i == 10) {
            return this.mUser1BandLevels;
        }
        if (i == 11) {
            return this.mUser2BandLevels;
        }
        return AudioEffectUtilities.getPresetBandLevels(i);
    }

    public int getBandLevel(int band) {
        int[] levels = getBandLevels();
        if (levels != null) {
            return levels[band];
        }
        Log.e(LOG_TAG, "preset is invalid. preset : " + this.mEqualizerPreset);
        return 0;
    }

    public boolean getCurrentDynamicNormalizer() {
        return this.mDynamicNormalizer;
    }

    public boolean getCurrentClearStereo() {
        return this.mClearStereo;
    }

    public boolean getCurrentClearAudioPlus() {
        return this.mClearAudioPlus;
    }

    public boolean getCurrentXLoud() {
        return this.mXLoud;
    }

    public boolean getCurrentSForceSurround() {
        return this.mSForceSurround;
    }

    public boolean getCurrentSpeakerBundle() {
        return this.mSpeakerBundle;
    }

    public boolean getCurrentClearPhase() {
        return this.mClearPhase;
    }

    public boolean getCurrentDseeHx() {
        if (this.mIsDseeHxGlobalSetting) {
            return getDseeHxWithAudioManager();
        }
        return this.mDseeHx;
    }

    public void useEqualizerPreset(int preset) {
        this.mEqualizerPreset = preset;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        int[] bandLevels = getBandLevels();
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setBandLevels(bandLevels, true);
                }
            }
        }
    }

    public void setSurroundPreset(int preset) {
        this.mSurroundPreset = preset;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    setSurroundPreset(this.mContext, this.mSurroundPreset);
                    audioEffect.setSurroundPreset(preset);
                }
            }
        }
    }

    public void setClearAudioPlus(boolean enabled) {
        this.mClearAudioPlus = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setClearAudioPlus(enabled);
                }
            }
        }
        Intent intent = new Intent("com.sonymobile.audioeffect.intent.action.CLEARAUDIO_PLUS_STATUS");
        intent.putExtra("com.sonymobile.audioeffect.intent.extra.CLEARAUDIO_PLUS_STATUS", enabled ? 1 : 0);
        this.mContext.sendBroadcast(intent);
    }

    public void setBandLevel(int band, int level) {
        int[] bandLevels = getBandLevels();
        if (bandLevels == null) {
            Log.e(LOG_TAG, "getBandLevels() return null");
            return;
        }
        int currentBandLevel = convertMillibelToBandLevel(bandLevels[band]);
        int requestBandLevel = convertMillibelToBandLevel(level);
        if (currentBandLevel == requestBandLevel) {
            return;
        }
        bandLevels[band] = requestBandLevel * 100;
        switch (this.mEqualizerPreset) {
            case 9:
                this.mBandLevels = bandLevels;
                break;
            case 10:
                this.mUser1BandLevels = bandLevels;
                break;
            case 11:
                this.mUser2BandLevels = bandLevels;
                break;
            default:
                this.mBandLevels = bandLevels;
                this.mEqualizerPreset = 9;
                break;
        }
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setBandLevels(bandLevels, false);
                }
            }
        }
    }

    private int convertMillibelToBandLevel(int millibel) {
        if (millibel > 0) {
            return (millibel + 50) / 100;
        }
        return (millibel - 50) / 100;
    }

    public void setClearStereo(boolean enabled) {
        this.mClearStereo = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setClearStereo(enabled);
                }
            }
        }
    }

    public void setDynamicNormalizer(boolean enabled) {
        this.mDynamicNormalizer = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setDynamicNormalizer(enabled);
                }
            }
        }
    }

    public void setXLoud(boolean enabled) {
        this.mXLoud = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setXLoud(enabled);
                }
            }
        }
    }

    public void setSForceSurround(boolean enabled) {
        this.mSForceSurround = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setSForceSurround(enabled);
                }
            }
        }
    }

    public void setSpeakerBundle(boolean enabled) {
        this.mSpeakerBundle = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setSpeakerBundle(enabled);
                }
            }
        }
    }

    public void setClearPhase(boolean enabled) {
        this.mClearPhase = enabled;
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setClearPhase(enabled);
                }
            }
        }
    }

    public void setDseeHx(boolean enabled, boolean doSendIntent) {
        setDseeHxWithAudioManager(enabled);
        if (this.mIsDseeHxGlobalSetting) {
            return;
        }
        this.mDseeHx = enabled;
        if (doSendIntent) {
            Intent intent = new Intent("com.sonymobile.audioeffect.intent.action.MUSIC_RESTART_REQUEST");
            this.mContext.sendBroadcast(intent);
        }
    }

    private AudioEffectSessionHolder findSessionHolder(String packageName) {
        if (packageName != null && packageName.length() > 0) {
            int count = this.mSessionHolder.size();
            for (int i = 0; i < count; i++) {
                AudioEffectSessionHolder retVal = this.mSessionHolder.get(i);
                if (retVal.getPackage().equals(packageName)) {
                    return retVal;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Invalid package name.");
    }

    public void setEnabled(boolean enabled) {
        int count = this.mSessionHolder.size();
        if (count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            AudioEffectSessionHolder sessionHolder = this.mSessionHolder.get(i);
            if (sessionHolder != null) {
                int audioSession = sessionHolder.getAudioSession();
                AudioEffectManager audioEffect = sessionHolder.getAudioEffect();
                if (audioSession > -1) {
                    if (audioEffect == null) {
                        audioEffect = new AudioEffectManager(audioSession);
                        sessionHolder.setAudioEffect(audioEffect);
                    }
                    audioEffect.setEnabled(enabled);
                }
            }
        }
    }

    private AudioEffectSessionHolder createNewSessionHolder(String packageName, int audioSession, int contentType) {
        AudioEffectSessionHolder sessionHolder = findSessionHolder(packageName);
        if (sessionHolder == null) {
            sessionHolder = new AudioEffectSessionHolder(packageName, audioSession, contentType);
            this.mSessionHolder.add(sessionHolder);
            if (audioSession > -1) {
                AudioEffectManager audioEffect = new AudioEffectManager(audioSession);
                sessionHolder.setAudioEffect(audioEffect);
                initializeParameters(audioEffect);
            }
        }
        return sessionHolder;
    }

    public void restoreAudioSessionFromPreference() {
        AudioEffectSessionHolder holder;
        String[] fileList = getPackageListFromPrefs(this.mContext);
        if (fileList == null) {
            return;
        }
        synchronized (LOCK) {
            for (String packageName : fileList) {
                if (packageName != null && ((holder = findSessionHolder(packageName)) == null || holder.getAudioSession() == -1)) {
                    SharedPreferences pref = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, packageName.concat(PREFERENCE_SUFFIX), 0);
                    int audioSession = AudioEffectSessionHolder.getAudioSession(pref);
                    int contentType = AudioEffectSessionHolder.getContentType(pref);
                    updateAudioSession(packageName, audioSession, contentType);
                }
            }
        }
    }

    public void saveSettingsToPrefs() {
        synchronized (LOCK) {
            SharedPreferences pref = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, PREFERENCE_SETTING_NAME, 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(EQUALIZER_PRESET, this.mEqualizerPreset);
            editor.putInt(SURROUND_PRESET, this.mSurroundPreset);
            for (int band = 0; band < this.mBandLevels.length; band++) {
                editor.putInt("BAND_" + band, this.mBandLevels[band]);
            }
            for (int band2 = 0; band2 < this.mUser1BandLevels.length; band2++) {
                editor.putInt("USER1BAND_" + band2, this.mUser1BandLevels[band2]);
            }
            for (int band3 = 0; band3 < this.mUser2BandLevels.length; band3++) {
                editor.putInt("USER2BAND_" + band3, this.mUser2BandLevels[band3]);
            }
            editor.putBoolean(DYNAMIC_NORMALIZER, this.mDynamicNormalizer);
            editor.putBoolean(CLEAR_STEREO, this.mClearStereo);
            editor.putBoolean(X_LOUD, this.mXLoud);
            editor.putBoolean(S_FORCE_SURROUND, this.mSForceSurround);
            editor.putBoolean(SPEAKER_BUNDLE, this.mSpeakerBundle);
            editor.apply();
            SharedPreferences pref2 = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, PREFERENCE_CLEAR_AUDIO_PLUS_NAME, 0);
            SharedPreferences.Editor editor2 = pref2.edit();
            editor2.putBoolean(CLEAR_AUDIO_PLUS, this.mClearAudioPlus);
            editor2.apply();
            if (!this.mIsDseeHxGlobalSetting) {
                SharedPreferences pref3 = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, PREFERENCE_DSEE_HX_NAME, 0);
                SharedPreferences.Editor editor3 = pref3.edit();
                editor3.putBoolean(DSEE_HX, this.mDseeHx);
                editor3.apply();
            }
        }
    }

    public void saveSessionInfoToPrefs(String packageName) {
        if (packageName == null || packageName.equals("")) {
            return;
        }
        synchronized (LOCK) {
            AudioEffectSessionHolder setting = findSessionHolder(packageName);
            if (setting != null) {
                SharedPreferences pref = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, packageName.concat(PREFERENCE_SUFFIX), 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                setting.saveToPrefs(editor);
                editor.apply();
            }
        }
    }

    public void setControlStatusListener(String packageName, AudioEffectManager.OnControlStatusChangeListener listener) {
        AudioEffectManager audioEffect;
        AudioEffectSessionHolder setting = findSessionHolder(packageName);
        if (setting != null && (audioEffect = setting.getAudioEffect()) != null) {
            audioEffect.setControlStatusListener(listener);
        }
    }

    public String[] getPackageListFromPrefs(Context context) {
        String[] fileList;
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            String dataPath = info.dataDir + File.separator + SHARED_PREFS_FOLDER_PATH + File.separator;
            File folder = new File(dataPath);
            if (!folder.exists() || !folder.isDirectory() || (fileList = folder.list()) == null || fileList.length <= 0) {
                return null;
            }
            for (int i = 0; i < fileList.length; i++) {
                int index = getPackagenameIndex(fileList[i]);
                if (index != -1) {
                    fileList[i] = fileList[i].substring(0, index);
                } else {
                    fileList[i] = null;
                }
            }
            return fileList;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getPackagenameIndex(String filename) {
        String checkString = PREFERENCE_SUFFIX.concat(XML_EXTENSION);
        int index = filename.lastIndexOf(checkString);
        return index;
    }

    private void getSettingFromPrefs() {
        SharedPreferences prefs = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, PREFERENCE_SETTING_NAME, 0);
        this.mEqualizerPreset = prefs.getInt(EQUALIZER_PRESET, -1);
        this.mSurroundPreset = prefs.getInt(SURROUND_PRESET, -1);
        if (this.mEqualizerPreset == -1) {
            this.mEqualizerPreset = 0;
        }
        if (this.mSurroundPreset == -1) {
            this.mSurroundPreset = 0;
            return;
        }
        for (int band = 0; band < 6; band++) {
            this.mBandLevels[band] = prefs.getInt("BAND_" + band, 0);
        }
        for (int band2 = 0; band2 < 6; band2++) {
            this.mUser1BandLevels[band2] = prefs.getInt("USER1BAND_" + band2, 0);
        }
        for (int band3 = 0; band3 < 6; band3++) {
            this.mUser2BandLevels[band3] = prefs.getInt("USER2BAND_" + band3, 0);
        }
        this.mDynamicNormalizer = prefs.getBoolean(DYNAMIC_NORMALIZER, false);
        this.mClearStereo = prefs.getBoolean(CLEAR_STEREO, false);
        this.mXLoud = prefs.getBoolean(X_LOUD, false);
        this.mSForceSurround = prefs.getBoolean(S_FORCE_SURROUND, false);
        this.mSpeakerBundle = prefs.getBoolean(SPEAKER_BUNDLE, false);
        this.mClearAudioPlus = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, PREFERENCE_CLEAR_AUDIO_PLUS_NAME, 0).getBoolean(CLEAR_AUDIO_PLUS, false);
        this.mDseeHx = false;
        if (!this.mIsDseeHxGlobalSetting) {
            this.mDseeHx = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, PREFERENCE_DSEE_HX_NAME, 0).getBoolean(DSEE_HX, false);
        }
    }

    private void setSurroundPreset(Context context, int preset) {
        String surroundPresetParam = KEY_VPT_MODE + preset;
        this.mAudioManager.setParameters(surroundPresetParam);
    }

    private void setClientId(int id, boolean isOpen, boolean needAllClear) {
        StringBuilder sb = new StringBuilder();
        sb.append(KEY_CLIENT_STATE);
        sb.append(isOpen ? "1" : "0");
        sb.append(";");
        sb.append(KEY_CLIENT_ID);
        sb.append(id);
        sb.append(";");
        sb.append(KEY_CLIENT_ALL_CLEAR);
        sb.append(needAllClear ? "1" : "0");
        String clietParam = sb.toString();
        this.mAudioManager.setParameters(clietParam);
    }

    public boolean getDseeHxWithAudioManager() {
        try {
            boolean state = getState(KEY_DSEE_HX_STATE);
            return state;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private void setDseeHxWithAudioManager(boolean enabled) {
        StringBuilder sb = new StringBuilder();
        sb.append("dsee_hx_state=");
        sb.append(enabled ? "1" : "0");
        String dseeHxParam = sb.toString();
        this.mAudioManager.setParameters(dseeHxParam);
    }

    private boolean getState(String param) {
        String state = this.mAudioManager.getParameters(param);
        int currentState = splitIntValue(state);
        return convertIntToBoolean(currentState);
    }

    private int splitIntValue(String value) {
        String[] splitResult = value.split("=");
        if (splitResult.length < 2) {
            Log.e(LOG_TAG, "splitIntValue() : split failed. value : " + value);
            return Integer.MAX_VALUE;
        }
        return Integer.parseInt(splitResult[1]);
    }

    private boolean convertIntToBoolean(int integer) {
        if (integer == 0) {
            return false;
        }
        if (integer == 1) {
            return true;
        }
        if (integer == Integer.MAX_VALUE) {
            throw new IllegalStateException("Invalid status. param : " + integer);
        }
        throw new IllegalStateException("Invalid status value. param : " + integer);
    }
}
