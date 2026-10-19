package com.sonyericsson.soundenhancement.audioeffect;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.Log;
import com.sonyericsson.soundenhancement.PreferenceUtil;
import com.sonyericsson.soundenhancement.SoundEnhancementService;
import com.sonyericsson.soundenhancement.SoundEnhancementUtility;
import com.sonyericsson.soundenhancement.ahc.Constants;

/* JADX INFO: loaded from: classes2.dex */
public class AccessorySetting {
    public static final int ACCESSORY_TYPE_INVALID = -1;
    public static final String ACTION_CHANGE_ACCESSORY_SETTING = "com.sonyericsson.soundenhancement.intent.action.CHANGE_ACCESSORY_SETTING";
    private static final int INVALID_VALUE = Integer.MAX_VALUE;
    private static final String KEY_5_POLE_ACCESSORY_TYPE = "5pole_accessory_type";
    private static final String KEY_ANC_LOAD_PARAM = "load_parameter";
    private static final String KEY_DNC_ENV = "dnc_env";
    private static final String KEY_DNC_MODE_NAME = "dnc_mode_name";
    private static final String KEY_DNC_NUM_OF_MODES = "dnc_num_modes";
    private static final String KEY_DNC_POWER_SAVE = "dnc_ps";
    private static final String KEY_DNC_STATE = "dnc_state";
    private static final String KEY_DNC_SUPPORTED = "dnc_supported";
    private static final String KEY_STEREO_MIC_GAIN = "somc_mic_gain_setting";
    private static final String KEY_STEREO_MIC_SUPPORTED = "mic_supported";
    private static final String KEY_SUPPORTED_EFFECT = "supported_effect";
    private static final String LOG_TAG = "SoundEnhancement";
    public static final String PRESET_NAME_NC_AIRPLANE = "Airplane";
    public static final String PRESET_NAME_NC_BUS_TRAIN = "Bus/Train";
    public static final String PRESET_NAME_NC_OFFICE = "Office";
    public static final int STATE_NC_AUTO = 1;
    public static final int STATE_NC_HEADPHONE_SUMMARY_TYPE_WALKMAN = 0;
    public static final int STATE_NC_HEADPHONE_SUMMARY_TYPE_XPERIA = 1;
    public static final int STATE_NC_HEADPHONE_TYPE_A = 0;
    public static final int STATE_NC_HEADPHONE_TYPE_B = 1;
    public static final int STATE_NC_HEADPHONE_TYPE_C = 2;
    public static final int STATE_NC_HEADPHONE_TYPE_D = 3;
    public static final int STATE_NC_HEADPHONE_TYPE_OTHER = 4;
    public static final int STATE_NC_OFF = 0;
    public static final int STATE_NC_ON = 2;
    public static final String STEREO_MIC_GAIN_AUTO = "auto";
    public static final String STEREO_MIC_GAIN_HIGH = "high";
    public static final String STEREO_MIC_GAIN_LOW = "low";
    public static final String STEREO_MIC_GAIN_MID = "mid";
    public static final int STEREO_MIC_GAIN_VALUE_AUTO = 0;
    public static final int STEREO_MIC_GAIN_VALUE_HIGH = 1;
    public static final int STEREO_MIC_GAIN_VALUE_LOW = 3;
    public static final int STEREO_MIC_GAIN_VALUE_MID = 2;
    public static final int STEREO_MIC_GAIN_VALUE_NUM_MAX = 4;
    private AudioManager mAudioManager;
    private Context mContext;

    public boolean isSupportedFivePoleAccessory() {
        return true;
    }

    public boolean isSupportedStereoMic() {
        return true;
    }

    public void setNoiseCanceling(boolean z, int i, boolean z2) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Settings.Global.putInt(contentResolver, KEY_DNC_STATE, z ? 1 : 0);
        Settings.Global.putInt(contentResolver, KEY_DNC_ENV, i);
        Settings.Global.putInt(contentResolver, KEY_DNC_POWER_SAVE, z2 ? 1 : 0);
        StringBuilder sb = new StringBuilder();
        sb.append("dnc_state=");
        sb.append(z ? "1" : "0");
        sb.append(";");
        sb.append(KEY_DNC_POWER_SAVE);
        sb.append("=");
        sb.append(z2 ? "1" : "0");
        sb.append(";");
        sb.append(KEY_DNC_ENV);
        sb.append("=");
        sb.append(i);
        this.mAudioManager.setParameters(sb.toString());
        sendChangeNoiseCancelingSetting();
    }

    public AccessorySetting(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mContext = context;
    }

    /* JADX WARN: Unreachable blocks removed: 4, instructions: 11 */
    public String[] getNoiseCancelingEnvironmentNames() {
        String numOfNoiseCancelingEnvironments = this.mAudioManager.getParameters(KEY_DNC_NUM_OF_MODES);
        int noiseCancelingEnvironmentCount = splitAudioManagerIntValue(numOfNoiseCancelingEnvironments);
        if (noiseCancelingEnvironmentCount == Integer.MAX_VALUE) {
            noiseCancelingEnvironmentCount = Settings.Global.getInt(this.mContext.getContentResolver(), KEY_DNC_NUM_OF_MODES, 0);
        }
        String[] noiseCancelingEnvironmentNames = new String[noiseCancelingEnvironmentCount];
        for (int cnt = 0; cnt < noiseCancelingEnvironmentCount; cnt++) {
            String noiseCancelingEnvironmentName = this.mAudioManager.getParameters("dnc_mode_name_" + cnt);
            String strSplitAudioManagerStringValue = splitAudioManagerStringValue(noiseCancelingEnvironmentName);
            while (true) {
                noiseCancelingEnvironmentNames[cnt] = strSplitAudioManagerStringValue;
                if (noiseCancelingEnvironmentNames[cnt].equals("")) {
                    strSplitAudioManagerStringValue = Settings.Global.getString(this.mContext.getContentResolver(), "dnc_mode_name_" + cnt);
                }
            }
        }
        return noiseCancelingEnvironmentNames;
    }

    public boolean isSupportedStereoMicOld() {
        String state = this.mAudioManager.getParameters(KEY_STEREO_MIC_SUPPORTED);
        if (state == null) {
            return false;
        }
        String[] splitResult = state.split("=");
        if (splitResult.length != 2) {
            return false;
        }
        try {
            boolean result = Integer.parseInt(splitResult[1]) == 1;
            return result;
        } catch (NumberFormatException e) {
            Log.w(LOG_TAG, "Failed to parse int");
            return false;
        }
    }

    public boolean isSupportedFivePoleAccessoryOld() {
        String state = this.mAudioManager.getParameters(KEY_DNC_SUPPORTED);
        if (state == null) {
            return false;
        }
        String[] splitResult = state.split("=");
        if (splitResult.length != 2) {
            Log.e(LOG_TAG, "isSupportedFivePoleAccessory() : split failed. state : " + state);
            return false;
        }
        try {
            int retval = Integer.parseInt(splitResult[1]);
            if (retval != 0 && retval == 1) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            Log.w(LOG_TAG, "return : false");
            return false;
        }
    }

    public boolean getAutoHeadsetCompensation() {
        SharedPreferences sp = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this.mContext, Constants.PREF_AHC, 4);
        boolean state = sp.getBoolean(Constants.AHC_STATE, false);
        return state;
    }

    public void setAhcStateWithAudioManager(boolean state) {
        StringBuilder sb = new StringBuilder();
        sb.append("ahc_state=");
        sb.append(state ? "1" : "0");
        String ahc_state_param = sb.toString();
        this.mAudioManager.setParameters(ahc_state_param);
    }

    public String getStereoMicGain() {
        String gain = this.mAudioManager.getParameters(KEY_STEREO_MIC_GAIN);
        String value = splitAudioManagerStringValue(gain);
        return value != "" ? value : Settings.Global.getString(this.mContext.getContentResolver(), KEY_STEREO_MIC_GAIN);
    }

    public int getNoiseCancelingEnvironment() {
        try {
            int[] state = getNoiseCanceling();
            int value = state[1];
            return value;
        } catch (IllegalStateException e) {
            return 0;
        }
    }

    public boolean getNoiseCancelingState() {
        try {
            int[] state = getNoiseCanceling();
            boolean ret = convertIntToBoolean(state[0]);
            return ret;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    public boolean getNoiseCancelingPowerSaveMode() {
        try {
            int[] state = getNoiseCanceling();
            boolean ret = convertIntToBoolean(state[2]);
            return ret;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    /* JADX WARN: Unreachable blocks removed: 4, instructions: 13 */
    public int[] getNoiseCanceling() {
        String value = this.mAudioManager.getParameters("dnc_state;dnc_ps;dnc_env");
        String[] splitResult = value.split(";");
        if (splitResult.length == 3) {
            int[] retVal = new int[3];
            for (int loop1 = 0; loop1 < 3; loop1++) {
                String[] splitVal = splitResult[loop1].split("=");
                if (splitVal.length == 2) {
                    if (splitVal[0].equals(KEY_DNC_STATE)) {
                        retVal[0] = Integer.parseInt(splitVal[1]);
                    }
                    if (splitVal[0].equals(KEY_DNC_ENV)) {
                        retVal[1] = Integer.parseInt(splitVal[1]);
                    }
                    if (splitVal[0].equals(KEY_DNC_POWER_SAVE)) {
                        retVal[2] = Integer.parseInt(splitVal[1]);
                    }
                }
            }
            return retVal;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return new int[]{Settings.Global.getInt(contentResolver, KEY_DNC_STATE, 0), Settings.Global.getInt(contentResolver, KEY_DNC_ENV, 0), Settings.Global.getInt(contentResolver, KEY_DNC_POWER_SAVE, 0)};
    }

    public int getAccessoryType() {
        String accessoryType = this.mAudioManager.getParameters(KEY_5_POLE_ACCESSORY_TYPE);
        int ret = splitAudioManagerIntValue(accessoryType);
        return ret != Integer.MAX_VALUE ? ret : Settings.Global.getInt(this.mContext.getContentResolver(), KEY_5_POLE_ACCESSORY_TYPE, 0);
    }

    public void setStereoMicGain(String gain) {
        String stereoMicGain = "somc_mic_gain_setting=" + gain;
        this.mAudioManager.setParameters(stereoMicGain);
        Settings.Global.putString(this.mContext.getContentResolver(), KEY_STEREO_MIC_GAIN, gain);
    }

    public void setNoiseCancelingEnvironment(int noiseCancelingEnvironment) {
        try {
            int[] noiseCancelingState = getNoiseCanceling();
            setNoiseCanceling(convertIntToBoolean(noiseCancelingState[0]), noiseCancelingEnvironment, convertIntToBoolean(noiseCancelingState[2]));
        } catch (IllegalStateException e) {
        }
    }

    public void setNoiseCancelingState(boolean state) {
        try {
            int[] noiseCancelingState = getNoiseCanceling();
            setNoiseCanceling(state, noiseCancelingState[1], convertIntToBoolean(noiseCancelingState[2]));
        } catch (IllegalStateException e) {
        }
    }

    public void setNoiseCancelingPowerSaveMode(boolean powerSaveMode) {
        try {
            int[] noiseCancelingState = getNoiseCanceling();
            setNoiseCanceling(convertIntToBoolean(noiseCancelingState[0]), noiseCancelingState[1], powerSaveMode);
        } catch (IllegalStateException e) {
        }
    }

    public void loadAncParameters() {
        this.mAudioManager.setParameters(KEY_ANC_LOAD_PARAM);
    }

    public void setAccessoryType(int type) {
        String accessoryTypeParam = "5pole_accessory_type=" + type;
        this.mAudioManager.setParameters(accessoryTypeParam);
        Settings.Global.putInt(this.mContext.getContentResolver(), KEY_5_POLE_ACCESSORY_TYPE, type);
        sendChnageAccessrySetting();
    }

    private int splitAudioManagerIntValue(String value) {
        String[] splitResult = value.split("=");
        if (splitResult.length < 2) {
            Log.e(LOG_TAG, "splitAudioManagerIntValue() : split failed. value : " + value);
            return Integer.MAX_VALUE;
        }
        return Integer.parseInt(splitResult[1]);
    }

    private String splitAudioManagerStringValue(String value) {
        String[] splitResult = value.split("=");
        if (splitResult.length < 2) {
            Log.e(LOG_TAG, "splitAudioManagerStringValue() : split failed. value : " + value);
            return "";
        }
        return splitResult[1];
    }

    private boolean isSupported(String effect) {
        boolean supported = false;
        String supportedEffect = this.mAudioManager.getParameters(KEY_SUPPORTED_EFFECT);
        String[] supportedEffectList = splitSupportedEffect(supportedEffect);
        if (supportedEffectList == null) {
            Log.e(LOG_TAG, "Invalid supported effect list.");
            return false;
        }
        for (String str : supportedEffectList) {
            if (str.equals(effect)) {
                supported = true;
            }
        }
        return supported;
    }

    private String[] splitSupportedEffect(String value) {
        String[] splitResult = value.split("=");
        if (splitResult.length < 2) {
            Log.i(LOG_TAG, "splitAudioManagerStringArrayValue() : splitResult.length < 2  : value : " + value);
            return null;
        }
        String[] supportedEffect = splitResult[1].split(":");
        return supportedEffect;
    }

    private void sendChangeNoiseCancelingSetting() {
        SoundEnhancementUtility.sendBroadcastExplicitIntent(this.mContext, SoundEnhancementService.ACTION_CHANGE_NOISE_CANCELING_SETTING);
    }

    private void sendChnageAccessrySetting() {
        SoundEnhancementUtility.sendBroadcastExplicitIntent(this.mContext, ACTION_CHANGE_ACCESSORY_SETTING);
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

    public static final int getApplicableAccessoryType(int type) {
        if (type == 4) {
            return 3;
        }
        return type;
    }
}
