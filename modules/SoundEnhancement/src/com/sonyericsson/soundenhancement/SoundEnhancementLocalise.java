package com.sonyericsson.soundenhancement;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementLocalise {
    private static final String LOG_TAG = "SoundEnhancement";

    public String getLocalisedEqualizerPresetName(Context context, String presetName) {
        Resources res = context.getResources();
        if (presetName == null) {
            return "";
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_ROCK)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_heavy_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_POP)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_pops_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_JAZZ)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_jazz_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_UNIQUE)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_unique_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_SOUL)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_soul_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_EASY_LISTENING)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_easy_listening_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_BASS_BOOST)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_bass_boost_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_TREBLE_BOOST)) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_treble_boost_txt);
        }
        if (presetName.equals("Off")) {
            return res.getString(R.string.sound_enhance_strings_setting_preset_off_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_MANUAL)) {
            return res.getString(R.string.sound_enhance_strings_setting_manual_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_USER1)) {
            return res.getString(R.string.sound_enhance_strings_setting_user_defined_1_txt);
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_EQUALIZER_USER2)) {
            return res.getString(R.string.sound_enhance_strings_setting_user_defined_2_txt);
        }
        Log.w(LOG_TAG, "This equalizer preset name haven't got any translation: " + presetName);
        return presetName;
    }

    public String getLocalisedBandLevel(Context context, int bandLevel) {
        String retString = null;
        Resources res = context.getResources();
        if (bandLevel == -10) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m10_txt);
        } else if (bandLevel == -9) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m9_txt);
        } else if (bandLevel == -8) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m8_txt);
        } else if (bandLevel == -7) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m7_txt);
        } else if (bandLevel == -6) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m6_txt);
        } else if (bandLevel == -5) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m5_txt);
        } else if (bandLevel == -4) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m4_txt);
        } else if (bandLevel == -3) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m3_txt);
        } else if (bandLevel == -2) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m2_txt);
        } else if (bandLevel == -1) {
            retString = res.getString(R.string.sound_enhance_gauge_value_m1_txt);
        } else if (bandLevel == 0) {
            retString = res.getString(R.string.sound_enhance_gauge_value_pm0_txt);
        } else if (bandLevel == 1) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p1_txt);
        } else if (bandLevel == 2) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p2_txt);
        } else if (bandLevel == 3) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p3_txt);
        } else if (bandLevel == 4) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p4_txt);
        } else if (bandLevel == 5) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p5_txt);
        } else if (bandLevel == 6) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p6_txt);
        } else if (bandLevel == 7) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p7_txt);
        } else if (bandLevel == 8) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p8_txt);
        } else if (bandLevel == 9) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p9_txt);
        } else if (bandLevel == 10) {
            retString = res.getString(R.string.sound_enhance_gauge_value_p10_txt);
        } else {
            Log.w(LOG_TAG, "This band level haven't got any translation: " + bandLevel);
        }
        if (retString == null) {
            if (bandLevel > 0) {
                String retString2 = "+" + String.valueOf(bandLevel);
                return retString2;
            }
            String retString3 = String.valueOf(bandLevel);
            return retString3;
        }
        return retString;
    }

    public String getLocalisedScaleNum(Context context, int level) {
        String retString = null;
        Resources res = context.getResources();
        if (level == -10) {
            retString = res.getString(R.string.sound_enhance_guide_swell_m10_txt);
        } else if (level == 0) {
            retString = res.getString(R.string.sound_enhance_guide_swell_pm0_txt);
        } else if (level == 10) {
            retString = res.getString(R.string.sound_enhance_guide_swell_p10_txt);
        } else {
            Log.w(LOG_TAG, "This scale number haven't got any translation: " + level);
        }
        if (retString == null) {
            if (level > 0) {
                String retString2 = "+" + String.valueOf(level);
                return retString2;
            }
            String retString3 = String.valueOf(level);
            return retString3;
        }
        return retString;
    }

    public String getLocalisedCenterFreq(Context context, double freq) {
        String retString = null;
        Resources res = context.getResources();
        if (freq == 0.4d) {
            retString = res.getString(R.string.sound_enhance_strings_guide_freq_0_4_txt);
        } else if (freq == 1.0d) {
            retString = res.getString(R.string.sound_enhance_strings_guide_freq_1_0_txt);
        } else if (freq == 2.5d) {
            retString = res.getString(R.string.sound_enhance_strings_guide_freq_2_5_txt);
        } else if (freq == 6.3d) {
            retString = res.getString(R.string.sound_enhance_strings_guide_freq_6_3_txt);
        } else if (freq == 16.0d) {
            retString = res.getString(R.string.sound_enhance_strings_guide_freq_16_txt);
        } else {
            Log.w(LOG_TAG, "This frequency haven't got any translation: " + freq);
        }
        if (retString == null) {
            String retString2 = String.valueOf(freq);
            return retString2;
        }
        return retString;
    }

    public String getLocalisedCenterFreqForSpeech(Context context, double freq) {
        String retString = null;
        Resources res = context.getResources();
        if (freq == 0.4d) {
            retString = res.getString(R.string.sound_enhance_strings_accessibility_freq_0_4_txt);
        } else if (freq == 1.0d) {
            retString = res.getString(R.string.sound_enhance_strings_accessibility_freq_1_0_txt);
        } else if (freq == 2.5d) {
            retString = res.getString(R.string.sound_enhance_strings_accessibility_freq_2_5_txt);
        } else if (freq == 6.3d) {
            retString = res.getString(R.string.sound_enhance_strings_accessibility_freq_6_3_txt);
        } else if (freq == 16.0d) {
            retString = res.getString(R.string.sound_enhance_strings_accessibility_freq_16_txt);
        } else {
            Log.w(LOG_TAG, "This frequency haven't got any translation: " + freq);
        }
        if (retString == null) {
            String retString2 = String.valueOf(freq);
            return retString2;
        }
        return retString;
    }

    public String getLocalisedClearBassForSpeech(Context context) {
        Resources res = context.getResources();
        return res.getString(R.string.sound_enhance_strings_accessibility_clear_bass_txt);
    }

    public String getLocalisedSurroundPresetName(Context context, String presetName) {
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB)) {
            String localisedPresetName = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_club_txt);
            return localisedPresetName;
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL)) {
            String localisedPresetName2 = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_concert_hall_txt);
            return localisedPresetName2;
        }
        if (presetName.equals("Off")) {
            String localisedPresetName3 = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_off_txt);
            return localisedPresetName3;
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
            String localisedPresetName4 = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_studio_txt);
            return localisedPresetName4;
        }
        Log.w(LOG_TAG, "This Surround preset name haven't got any translation: " + presetName);
        return "";
    }

    public String getLocalisedSurroundDescription(Context context, String presetName) {
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB)) {
            String localisedDescription = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_club_desc_txt);
            return localisedDescription;
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL)) {
            String localisedDescription2 = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_concert_hall_desc_txt);
            return localisedDescription2;
        }
        if (presetName.equals("Off")) {
            String localisedDescription3 = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_off_desc_txt);
            return localisedDescription3;
        }
        if (presetName.equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
            String localisedDescription4 = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_studio_desc_txt);
            return localisedDescription4;
        }
        Log.w(LOG_TAG, "This Surround description haven't got any translation: " + presetName);
        return "";
    }

    public String[] getLocalisedSurroundDescriptions(Context context, String[] presetNames) {
        if (context == null || presetNames == null) {
            return null;
        }
        String[] descriptions = new String[presetNames.length];
        for (int i = 0; i < presetNames.length; i++) {
            if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB)) {
                descriptions[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_club_desc_txt);
            } else if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL)) {
                descriptions[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_concert_hall_desc_txt);
            } else if (presetNames[i].equals("Off")) {
                descriptions[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_off_desc_txt);
            } else if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
                descriptions[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_studio_desc_txt);
            } else {
                descriptions[i] = "";
            }
        }
        return descriptions;
    }

    public Integer[] getImageIdsFromSurroundPresetNames(Context context, String[] presetNames) {
        if (context == null || presetNames == null) {
            return null;
        }
        Integer[] ids = new Integer[presetNames.length];
        for (int i = 0; i < presetNames.length; i++) {
            if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB)) {
                ids[i] = Integer.valueOf(R.drawable.sound_enhance_surround_club_icn);
            } else if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL)) {
                ids[i] = Integer.valueOf(R.drawable.sound_enhance_surround_concert_hall_icn);
            } else if (presetNames[i].equals("Off")) {
                ids[i] = Integer.valueOf(R.drawable.sound_enhance_surround_off_icn);
            } else if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
                ids[i] = Integer.valueOf(R.drawable.sound_enhance_surround_studio_icn);
            } else {
                ids[i] = Integer.valueOf(R.drawable.sound_enhance_surround_pressed_icn);
            }
        }
        return ids;
    }

    public String[] getLocalisedSurroundPresetNames(Context context, String[] presetNames) {
        if (context == null || presetNames == null) {
            return null;
        }
        String[] localisedPresetNames = new String[presetNames.length];
        for (int i = 0; i < presetNames.length; i++) {
            if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB)) {
                localisedPresetNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_club_txt);
            } else if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL)) {
                localisedPresetNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_concert_hall_txt);
            } else if (presetNames[i].equals("Off")) {
                localisedPresetNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_off_txt);
            } else if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
                localisedPresetNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_surround_studio_txt);
            } else {
                localisedPresetNames[i] = "";
            }
        }
        return localisedPresetNames;
    }

    public String getLocalisedNoiseCancelingEnvironmentName(Context context, String environmentName) {
        if (environmentName.equals(AccessorySetting.PRESET_NAME_NC_BUS_TRAIN)) {
            String localisedEnvironmentName = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_env_bus_train_txt);
            return localisedEnvironmentName;
        }
        if (environmentName.equals(AccessorySetting.PRESET_NAME_NC_AIRPLANE)) {
            String localisedEnvironmentName2 = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_env_airplane_txt);
            return localisedEnvironmentName2;
        }
        if (environmentName.equals(AccessorySetting.PRESET_NAME_NC_OFFICE)) {
            String localisedEnvironmentName3 = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_env_office_txt);
            return localisedEnvironmentName3;
        }
        Log.w(LOG_TAG, "This noise canceling environment name haven't got any translation: " + environmentName);
        return "";
    }

    public String[] getLocalisedNoiseCancelingEnvironmentNames(Context context, String[] environmentNames) {
        if (context == null || environmentNames == null) {
            return null;
        }
        String[] localisedEnvironmentNames = new String[environmentNames.length];
        for (int i = 0; i < environmentNames.length; i++) {
            if (environmentNames[i].equals(AccessorySetting.PRESET_NAME_NC_BUS_TRAIN)) {
                localisedEnvironmentNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_env_bus_train_txt);
            } else if (environmentNames[i].equals(AccessorySetting.PRESET_NAME_NC_AIRPLANE)) {
                localisedEnvironmentNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_env_airplane_txt);
            } else if (environmentNames[i].equals(AccessorySetting.PRESET_NAME_NC_OFFICE)) {
                localisedEnvironmentNames[i] = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_env_office_txt);
            } else {
                localisedEnvironmentNames[i] = "";
            }
        }
        return localisedEnvironmentNames;
    }

    public String getLocalisedNoiseCancelingStateName(Context context, boolean state) {
        if (state) {
            String localisedStateName = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_cancel_on_simple_txt);
            return localisedStateName;
        }
        String localisedStateName2 = context.getResources().getString(R.string.sound_enhance_strings_setting_noise_cancel_off_txt);
        return localisedStateName2;
    }

    public String getLocalisedStereoMicGain(Context context, String gain) {
        if (AccessorySetting.STEREO_MIC_GAIN_HIGH.equals(gain)) {
            String localisedGainName = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_concert_txt);
            return localisedGainName;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_MID.equals(gain)) {
            String localisedGainName2 = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_event_txt);
            return localisedGainName2;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_LOW.equals(gain)) {
            String localisedGainName3 = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_conversation_txt);
            return localisedGainName3;
        }
        String localisedGainName4 = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_auto_txt);
        return localisedGainName4;
    }

    public String getLocalisedStereoMicGainDesc(Context context, String gain) {
        if (AccessorySetting.STEREO_MIC_GAIN_HIGH.equals(gain)) {
            String localisedGainDesc = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_concert_desc_txt);
            return localisedGainDesc;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_MID.equals(gain)) {
            String localisedGainDesc2 = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_event_desc_txt);
            return localisedGainDesc2;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_LOW.equals(gain)) {
            String localisedGainDesc3 = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_conversation_desc_txt);
            return localisedGainDesc3;
        }
        String localisedGainDesc4 = context.getResources().getString(R.string.sound_enhance_strings_setting_stereo_mic_val_auto_desc_txt);
        return localisedGainDesc4;
    }

    public String getLocalisedEqualizerSliderValue(Context context, int value) {
        int rscId;
        switch (value) {
            case -10:
                rscId = R.string.sound_enhance_strings_accessibility_minus_10_txt;
                break;
            case -9:
                rscId = R.string.sound_enhance_strings_accessibility_minus_9_txt;
                break;
            case -8:
                rscId = R.string.sound_enhance_strings_accessibility_minus_8_txt;
                break;
            case -7:
                rscId = R.string.sound_enhance_strings_accessibility_minus_7_txt;
                break;
            case -6:
                rscId = R.string.sound_enhance_strings_accessibility_minus_6_txt;
                break;
            case -5:
                rscId = R.string.sound_enhance_strings_accessibility_minus_5_txt;
                break;
            case FontsContractCompat.FontRequestCallback.FAIL_REASON_SECURITY_VIOLATION /* -4 */:
                rscId = R.string.sound_enhance_strings_accessibility_minus_4_txt;
                break;
            case FontsContractCompat.FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR /* -3 */:
                rscId = R.string.sound_enhance_strings_accessibility_minus_3_txt;
                break;
            case -2:
                rscId = R.string.sound_enhance_strings_accessibility_minus_2_txt;
                break;
            case -1:
                rscId = R.string.sound_enhance_strings_accessibility_minus_1_txt;
                break;
            case 0:
                rscId = R.string.sound_enhance_strings_accessibility_zero_txt;
                break;
            case 1:
                rscId = R.string.sound_enhance_strings_accessibility_plus_1_txt;
                break;
            case 2:
                rscId = R.string.sound_enhance_strings_accessibility_plus_2_txt;
                break;
            case 3:
                rscId = R.string.sound_enhance_strings_accessibility_plus_3_txt;
                break;
            case 4:
                rscId = R.string.sound_enhance_strings_accessibility_plus_4_txt;
                break;
            case 5:
                rscId = R.string.sound_enhance_strings_accessibility_plus_5_txt;
                break;
            case 6:
                rscId = R.string.sound_enhance_strings_accessibility_plus_6_txt;
                break;
            case 7:
                rscId = R.string.sound_enhance_strings_accessibility_plus_7_txt;
                break;
            case 8:
                rscId = R.string.sound_enhance_strings_accessibility_plus_8_txt;
                break;
            case 9:
                rscId = R.string.sound_enhance_strings_accessibility_plus_9_txt;
                break;
            case 10:
                rscId = R.string.sound_enhance_strings_accessibility_plus_10_txt;
                break;
            default:
                return "";
        }
        return context.getResources().getString(rscId);
    }
}
