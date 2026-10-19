package com.sonyericsson.soundenhancement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;

/* JADX INFO: loaded from: classes2.dex */
public class SoundSliceProvider extends SliceProvider {
    private static final String TAG = "SoundSliceProvider";

    @Override // androidx.slice.SliceProvider
    public boolean onCreateSliceProvider() {
        return true;
    }

    @Override // androidx.slice.SliceProvider
    public Slice onBindSlice(Uri uri) {
        if (uri == null) {
            return null;
        }
        String path = uri.getPath();
        String key = path.replace("/", "");
        if (!isSupportedFunction(key)) {
            return new Slice.Builder(uri).build();
        }
        Class clazz = null;
        int title = -1;
        int subTitle = -1;
        Class subClazz = null;
        for (SliceItem item : SliceItem.values()) {
            if (key.equals(item.key)) {
                clazz = item.clazz;
                title = item.title;
                subTitle = item.subTitle;
                subClazz = item.subClazz;
                break;
            }
        }
        if (clazz == null) {
            return new Slice.Builder(uri).build();
        }
        if (SoundEnhancementUtility.isSupportedDolbyEffect() && subClazz != null) {
            clazz = subClazz;
        }
        Context context = getContext();
        Resources resources = context.getResources();
        Intent i = new Intent(context, (Class<?>) clazz);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
        SliceAction sliceAction = new SliceAction(pi, (IconCompat) null, (CharSequence) null);
        return new ListBuilder(context, uri, -1L).setHeader(new ListBuilder.HeaderBuilder().setPrimaryAction(sliceAction).setTitle(resources.getText(title)).setSubtitle(resources.getText(subTitle))).build();
    }

    private boolean isSupportedFunction(String path) {
        boolean ret = true;
        if (SoundEnhancementUtility.isSupportedDolbyEffect()) {
            if (path.equals(Constants.KEY_PREF_SOUND_ENHANCEMENT) || path.equals(Constants.KEY_PREF_DYNAMIC_NORMALIZER) || path.equals(Constants.KEY_PREF_SURROUND_SETTINGS) || path.equals(Constants.KEY_PREF_EQUALIZER_SETTINGS) || path.equals(Constants.KEY_PREF_SOUND_ENHANCEMENT)) {
                ret = false;
            } else if (path.equals(Constants.KEY_PREF_S_FORCE_SURROUND)) {
                ret = SoundEnhancementUtility.isSupportedClearAudioPlus();
            }
        } else if (path.equals(Constants.KEY_PREF_DOLBY_EFFECT)) {
            ret = false;
        }
        if (path.equals(Constants.KEY_PREF_HEADSET_OPTIMIZATION)) {
            return SoundEnhancementUtility.getSupportAutoHeadsetCompensation();
        }
        if (path.equals("noise_canceling")) {
            AccessorySetting setting = new AccessorySetting(getContext());
            return setting.isSupportedFivePoleAccessory();
        }
        if (path.equals(Constants.KEY_PREF_STEREO_MIC)) {
            AccessorySetting setting2 = new AccessorySetting(getContext());
            return setting2.isSupportedStereoMic();
        }
        if (path.equals(Constants.KEY_PREF_CLEAR_AUDIO_PLUS)) {
            return SoundEnhancementUtility.isSupportedClearAudioPlus();
        }
        return ret;
    }

    private enum SliceItem {
        AudioSettings(Constants.KEY_PREF_AUDIO_SETTINGS, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_audio_settings_txt, R.string.sound_enhance_strings_slice_open_settings_txt, AudioEffectMenuForDolbyAudioActivity.class),
        DseeHx(Constants.KEY_PREF_DSEE_HX, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_dsee_txt, R.string.sound_enhance_strings_slice_open_settings_txt, AudioEffectMenuForDolbyAudioActivity.class),
        ClearAudioPlus(Constants.KEY_PREF_CLEAR_AUDIO_PLUS, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_clearaudio_plus_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null),
        SoundEffects(Constants.KEY_PREF_SOUND_ENHANCEMENT, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_sound_effect_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null),
        DynamicNormalizer(Constants.KEY_PREF_DYNAMIC_NORMALIZER, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_dynamic_normalizer_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null),
        DolbyEffect(Constants.KEY_PREF_DOLBY_EFFECT, DolbyEffectActivity.class, R.string.sound_enhance_strings_dolby_atmos_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null),
        Equalizer(Constants.KEY_PREF_EQUALIZER_SETTINGS, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_tab_equalizer_txt, R.string.sound_enhance_strings_slice_under_sound_effecsts_txt, null),
        SurroundSound(Constants.KEY_PREF_SURROUND_SETTINGS, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_virtual_surround_txt, R.string.sound_enhance_strings_slice_under_sound_effecsts_txt, null),
        SForceSurround(Constants.KEY_PREF_S_FORCE_SURROUND, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_s_force3d_txt, R.string.sound_enhance_strings_slice_under_sound_effecsts_txt, null),
        AutomaticOptimization(Constants.KEY_PREF_HEADSET_OPTIMIZATION, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_ahc_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null),
        NoiseCanceling("noise_canceling", AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_noise_cancel_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null),
        StereoMic(Constants.KEY_PREF_STEREO_MIC, AudioEffectMenuForAudioActivity.class, R.string.sound_enhance_strings_setting_stereo_mic_txt, R.string.sound_enhance_strings_slice_open_settings_txt, null);

        private final Class clazz;
        private final String key;
        private final Class subClazz;
        private final int subTitle;
        private final int title;

        SliceItem(String key, Class clazz, int title, int subTitle, Class subClazz) {
            this.key = key;
            this.clazz = clazz;
            this.title = title;
            this.subTitle = subTitle;
            this.subClazz = subClazz;
        }
    }
}
