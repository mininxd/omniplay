package com.sonyericsson.soundenhancement;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.SearchIndexableResource;
import android.provider.SearchIndexablesContract;
import android.provider.SearchIndexablesProvider;
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementSearchIndexablesProvider extends SearchIndexablesProvider {
    private static final String LOG_TAG = "SoundEnhancement";

    public boolean onCreate() {
        return true;
    }

    public Cursor queryXmlResources(String[] projection) {
        boolean isSupportedDolby = SoundEnhancementUtility.isSupportedDolbyEffect();
        boolean isSupportedClearAudioPlus = SoundEnhancementUtility.isSupportedClearAudioPlus();
        boolean isMonoSpeaker = SoundEnhancementUtility.isMonoSpeaker();
        AccessorySetting accessorySetting = new AccessorySetting(getContext());
        boolean isSupported5PoleAccessory = accessorySetting.isSupportedFivePoleAccessory();
        boolean isSupportedStereoMic = accessorySetting.isSupportedStereoMic();
        ArrayList<SearchIndexableResource> resourceList = new ArrayList<>();
        int size = resourceList.size();
        if (isSupportedDolby) {
            new SearchIndexableResource(size, R.xml.checkbox_dsee_hx, AudioEffectMenuForDolbyAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn);
        }
        resourceList.add(new SearchIndexableResource(size, R.xml.checkbox_dsee_hx, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        if (isSupportedClearAudioPlus) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.checkbox_clear_audio_plus, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        if (!isSupportedDolby) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_sound_enhancement, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.checkbox_dynamic_normalizer, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_equalizer_settings, SoundEnhancementForSearchActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_surround_settings, SoundEnhancementForSearchActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            if (isMonoSpeaker) {
                resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.checkbox_s_force_surround, SoundEnhancementForSearchActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            }
        }
        if (isSupportedStereoMic) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_stereo_mic, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        if (isSupported5PoleAccessory) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.switch_noise_canceling, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        if (SoundEnhancementUtility.getSupportAutoHeadsetCompensation()) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_headset_optimization, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS);
        for (SearchIndexableResource resource : resourceList) {
            Object[] ref = new Object[SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS.length];
            ref[0] = Integer.valueOf(resource.rank);
            ref[1] = Integer.valueOf(resource.xmlResId);
            ref[2] = null;
            ref[3] = Integer.valueOf(resource.iconResId);
            ref[4] = "android.intent.action.MAIN";
            ref[5] = getContext().getApplicationInfo().packageName;
            ref[6] = resource.className;
            cursor.addRow(ref);
        }
        return cursor;
    }

    public Cursor queryXmlResourcesOld(String[] projection) {
        String name;
        boolean isSupportedDolby = SoundEnhancementUtility.isSupportedDolbyEffect();
        boolean isSupportedClearAudioPlus = SoundEnhancementUtility.isSupportedClearAudioPlus();
        boolean isMonoSpeaker = SoundEnhancementUtility.isMonoSpeaker();
        AccessorySetting accessorySetting = new AccessorySetting(getContext());
        boolean isSupported5PoleAccessory = accessorySetting.isSupportedFivePoleAccessory();
        boolean isSupportedStereoMic = accessorySetting.isSupportedStereoMic();
        ArrayList<SearchIndexableResource> resourceList = new ArrayList<>();
        int size = resourceList.size();
        if (isSupportedDolby) {
            name = AudioEffectMenuForDolbyAudioActivity.class.getName();
        } else {
            name = AudioEffectMenuForAudioActivity.class.getName();
        }
        resourceList.add(new SearchIndexableResource(size, R.xml.checkbox_dsee_hx, name, R.drawable.sound_enhance_application_icn));
        if (isSupportedClearAudioPlus) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.checkbox_clear_audio_plus, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        if (!isSupportedDolby) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_sound_enhancement, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.checkbox_dynamic_normalizer, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_equalizer_settings, SoundEnhancementForSearchActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_surround_settings, SoundEnhancementForSearchActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            if (isMonoSpeaker) {
                resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.checkbox_s_force_surround, SoundEnhancementForSearchActivity.class.getName(), R.drawable.sound_enhance_application_icn));
            }
        }
        if (isSupportedStereoMic) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_stereo_mic, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        if (isSupported5PoleAccessory) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.switch_noise_canceling, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        if (SoundEnhancementUtility.getSupportAutoHeadsetCompensation()) {
            resourceList.add(new SearchIndexableResource(resourceList.size(), R.xml.screen_headset_optimization, AudioEffectMenuForAudioActivity.class.getName(), R.drawable.sound_enhance_application_icn));
        }
        MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS);
        for (SearchIndexableResource resource : resourceList) {
            Object[] ref = new Object[SearchIndexablesContract.INDEXABLES_XML_RES_COLUMNS.length];
            ref[0] = Integer.valueOf(resource.rank);
            ref[1] = Integer.valueOf(resource.xmlResId);
            ref[2] = null;
            ref[3] = Integer.valueOf(resource.iconResId);
            ref[4] = "android.intent.action.MAIN";
            ref[5] = getContext().getApplicationInfo().packageName;
            ref[6] = resource.className;
            cursor.addRow(ref);
        }
        return cursor;
    }

    public Cursor queryRawData(String[] projection) {
        MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.INDEXABLES_RAW_COLUMNS);
        boolean isSupportedDolby = SoundEnhancementUtility.isSupportedDolbyEffect();
        if (isSupportedDolby) {
            Context context = getContext();
            Object[] ref = new Object[SearchIndexablesContract.INDEXABLES_RAW_COLUMNS.length];
            ref[0] = 1;
            ref[1] = context.getString(R.string.sound_enhance_strings_dolby_atmos_txt);
            ref[5] = context.getString(R.string.sound_enhance_strings_dolby_atmos_txt);
            ref[9] = "android.intent.action.MAIN";
            ref[10] = getContext().getApplicationInfo().packageName;
            ref[11] = DolbyEffectActivity.class.getName();
            ref[12] = Constants.KEY_PREF_DOLBY_EFFECT;
            cursor.addRow(ref);
        }
        return cursor;
    }

    public Cursor queryNonIndexableKeys(String[] projection) {
        MatrixCursor cursor = new MatrixCursor(SearchIndexablesContract.NON_INDEXABLES_KEYS_COLUMNS);
        return cursor;
    }
}
