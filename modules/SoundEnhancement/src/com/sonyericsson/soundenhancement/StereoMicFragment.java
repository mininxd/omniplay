package com.sonyericsson.soundenhancement;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;

/* JADX INFO: loaded from: classes2.dex */
public class StereoMicFragment extends PreferenceFragment {
    private static final String LOG_TAG = "SoundEnhancement";
    private AccessorySetting mAccessorySetting;
    private Preference.OnPreferenceChangeListener mCheckBoxPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.sonyericsson.soundenhancement.StereoMicFragment.1
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean checked = ((Boolean) newValue).booleanValue();
            if (!checked) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                StereoMicFragment stereoMicFragment = StereoMicFragment.this;
                CheckBoxPreference checkbox = (CheckBoxPreference) stereoMicFragment.findPreference(stereoMicFragment.changeStereoMicGainIntToString(i));
                if (preference == checkbox && checkbox != null) {
                    StereoMicFragment.this.mAccessorySetting.setStereoMicGain(StereoMicFragment.this.changeStereoMicGainIntToString(i));
                    Reporter.reportEvent(StereoMicFragment.this.getActivity().getApplicationContext(), ReportContents.EVENT_MIC_SENSITIVITY, StereoMicFragment.this.changeStereoMicGainIntToString(i));
                    break;
                }
            }
            StereoMicFragment.this.getActivity().finish();
            return true;
        }
    };
    private ListView mListView;

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAccessorySetting = new AccessorySetting(getActivity());
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.settinglist, viewGroup, false);
        this.mListView = (ListView) contentView.findViewById(android.R.id.list);
        addPreferencesFromResource(R.xml.settings_stereo_mic);
        for (int i = 0; i < 4; i++) {
            CheckBoxPreference preference = (CheckBoxPreference) findPreference(changeStereoMicGainIntToString(i));
            preference.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
        }
        return contentView;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = this.mListView;
        if (listView != null) {
            listView.setDivider(null);
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        updateRadioButton();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    private void updateRadioButton() {
        int gain = getStereoMicGain();
        for (int i = 0; i < 4; i++) {
            CheckBoxPreference preference = (CheckBoxPreference) findPreference(changeStereoMicGainIntToString(i));
            boolean isChecked = false;
            if (gain == i) {
                isChecked = true;
            }
            preference.setChecked(isChecked);
        }
    }

    private int getStereoMicGain() {
        String gain = this.mAccessorySetting.getStereoMicGain();
        if (AccessorySetting.STEREO_MIC_GAIN_HIGH.equals(gain)) {
            return 1;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_MID.equals(gain)) {
            return 2;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_LOW.equals(gain)) {
            return 3;
        }
        if (AccessorySetting.STEREO_MIC_GAIN_AUTO.equals(gain)) {
            return 0;
        }
        Log.e(LOG_TAG, "Stereo Mic gain is wrong: " + gain);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String changeStereoMicGainIntToString(int value) {
        if (value == 1) {
            return AccessorySetting.STEREO_MIC_GAIN_HIGH;
        }
        if (value == 2) {
            return AccessorySetting.STEREO_MIC_GAIN_MID;
        }
        if (value == 3) {
            return AccessorySetting.STEREO_MIC_GAIN_LOW;
        }
        if (value == 0) {
            return AccessorySetting.STEREO_MIC_GAIN_AUTO;
        }
        Log.e(LOG_TAG, "Stereo Mic gain is wrong: " + value);
        return AccessorySetting.STEREO_MIC_GAIN_AUTO;
    }
}
