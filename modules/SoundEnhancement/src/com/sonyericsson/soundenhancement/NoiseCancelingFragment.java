package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import com.sonyericsson.soundenhancement.switchbar.SwitchBar;

/* JADX INFO: loaded from: classes2.dex */
public class NoiseCancelingFragment extends PreferenceFragment implements ListDialogFragment.Callbacks {
    private static final String BUNDLE_KEY_CURRENT_ACCESSORY_TYPE = "key_current_accessory_type";
    private static final String BUNDLE_KEY_CURRENT_ENVIRONMENT = "key_current_environment";
    private static final String BUNDLE_KEY_PREVIOUS_ACCESSORY_TYPE = "key_previous_accessory_type";
    private static final String BUNDLE_KEY_PREVIOUS_ENVIRONMENT = "key_previous_environment";
    private static final String BUNDLE_KEY_SHOW_HEADPHONE_TYPE_DIALOG = "key_show_headphone_type_dialog";
    private static final String BUNDLE_KEY_SHOW_NC_ENVIRONMENT_DIALOG = "key_show_noise_canceling_environment_dialog";
    private static final String HEADPHONE_TYPE_DIALOG_TAG = "HeadphoneTypementDialog";
    private static final String KEY_HEADPHONE_TYPE = "headphone_type";
    private static final String KEY_NOISE_CANCELING_ENVIRONMENT_SETTINGS_ENABLED = "noise_canceling_environment_settings";
    private static final String KEY_NOISE_CANCELING_STATE_SETTINGS_ENABLED = "noise_canceling";
    private static final String KEY_POWER_SAVE_MODE_ENABLED = "power_save_mode";
    private static final String LOG_TAG = "SoundEnhancement";
    private static final String NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG = "NoiseCancelingEnvironmentDialog";
    private AccessorySetting mAccessorySetting;
    private int mCurrentAccessoryType;
    private int mCurrentEnvironment;
    private PreferenceScreen mHeadphoneTypePref;
    private ListView mListView;
    private SoundEnhancementLocalise mLocalise;
    private NoiseCancelingHeadphoneInfoManager mNcHeadphoneInfoManager;
    private PreferenceScreen mNoiseEnvPref;
    private SwitchPreference mPowerSaveModePref;
    private SoundEnhancementPresetList mPresetList;
    private int mPrevAccessoryType;
    private int mPreviousEnvironment;
    private ListDialogFragment mNoiseEnvFragment = null;
    private boolean mShowNoiseEnvDialog = false;
    private ListDialogFragment mHeadphoneTypeFragment = null;
    private boolean mShowHeadphoneTypeDialog = false;
    private SwitchBar mEnabledSwitchBar = null;
    private BroadcastReceiver mHeadsetPlugReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.NoiseCancelingFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(NoiseCancelingFragment.LOG_TAG, "NoiseCancelingFragment.onReceive() : action is null.");
            } else if (action.equals("android.intent.action.HEADSET_PLUG") && !NoiseCancelingFragment.this.isNCHeadphonePlugged()) {
                NoiseCancelingFragment.this.getActivity().finish();
            }
        }
    };
    private BroadcastReceiver mChangeSettingReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.NoiseCancelingFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(NoiseCancelingFragment.LOG_TAG, "NoiseCancelingFragment.mChangeSettingReceiver.onReceive() : action is null.");
            } else if (action.equals(SoundEnhancementService.ACTION_CHANGE_NOISE_CANCELING_SETTING)) {
                NoiseCancelingFragment.this.updateListViewItems();
            }
        }
    };
    private Preference.OnPreferenceClickListener mOnPreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.sonyericsson.soundenhancement.NoiseCancelingFragment.3
        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(Preference preference) {
            if (!NoiseCancelingFragment.this.getActivity().isFinishing()) {
                if (NoiseCancelingFragment.KEY_NOISE_CANCELING_ENVIRONMENT_SETTINGS_ENABLED.equals(preference.getKey())) {
                    NoiseCancelingFragment noiseCancelingFragment = NoiseCancelingFragment.this;
                    noiseCancelingFragment.mPreviousEnvironment = noiseCancelingFragment.mAccessorySetting.getNoiseCancelingEnvironment();
                    NoiseCancelingFragment noiseCancelingFragment2 = NoiseCancelingFragment.this;
                    noiseCancelingFragment2.mCurrentEnvironment = noiseCancelingFragment2.mPreviousEnvironment;
                    NoiseCancelingFragment.this.showSelectNoiseEnvFragment();
                    return true;
                }
                if (NoiseCancelingFragment.KEY_HEADPHONE_TYPE.equals(preference.getKey())) {
                    NoiseCancelingFragment noiseCancelingFragment3 = NoiseCancelingFragment.this;
                    noiseCancelingFragment3.mPrevAccessoryType = noiseCancelingFragment3.mAccessorySetting.getAccessoryType();
                    NoiseCancelingFragment noiseCancelingFragment4 = NoiseCancelingFragment.this;
                    noiseCancelingFragment4.mCurrentAccessoryType = noiseCancelingFragment4.mPrevAccessoryType;
                    NoiseCancelingFragment.this.showSelectHeadphoneTypeFragment();
                    return true;
                }
                return true;
            }
            return true;
        }
    };
    private Preference.OnPreferenceChangeListener mCheckBoxPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.sonyericsson.soundenhancement.NoiseCancelingFragment.4
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return NoiseCancelingFragment.this.onPreferenceSwitchChange(preference, newValue);
        }
    };

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        this.mAccessorySetting = new AccessorySetting(activity.getApplicationContext());
        this.mLocalise = new SoundEnhancementLocalise();
        this.mPresetList = new SoundEnhancementPresetList();
        this.mNcHeadphoneInfoManager = new NoiseCancelingHeadphoneInfoManager();
        this.mPresetList.queryAvailableNoiseCancelingEnvironment(this.mAccessorySetting.getNoiseCancelingEnvironmentNames());
        if (savedInstanceState != null) {
            this.mShowNoiseEnvDialog = savedInstanceState.getBoolean(BUNDLE_KEY_SHOW_NC_ENVIRONMENT_DIALOG);
            if (this.mShowNoiseEnvDialog) {
                this.mCurrentEnvironment = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_ENVIRONMENT);
                this.mPreviousEnvironment = savedInstanceState.getInt(BUNDLE_KEY_PREVIOUS_ENVIRONMENT);
            }
            this.mShowHeadphoneTypeDialog = savedInstanceState.getBoolean(BUNDLE_KEY_SHOW_HEADPHONE_TYPE_DIALOG);
            if (this.mShowHeadphoneTypeDialog) {
                this.mCurrentAccessoryType = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_ACCESSORY_TYPE);
                this.mPrevAccessoryType = savedInstanceState.getInt(BUNDLE_KEY_PREVIOUS_ACCESSORY_TYPE);
            }
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.noisecanceling_settings, viewGroup, false);
        this.mEnabledSwitchBar = (SwitchBar) contentView.findViewById(R.id.switch_bar);
        this.mEnabledSwitchBar.show();
        this.mEnabledSwitchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() { // from class: com.sonyericsson.soundenhancement.NoiseCancelingFragment.5
            @Override // com.sonyericsson.soundenhancement.switchbar.SwitchBar.OnSwitchChangeListener
            public void onSwitchChanged(Switch switchView, boolean isChecked) {
                if (isChecked != NoiseCancelingFragment.this.mAccessorySetting.getNoiseCancelingState()) {
                    NoiseCancelingFragment.this.setNoiseCancelingState(isChecked);
                    Reporter.reportEvent(NoiseCancelingFragment.this.getActivity().getApplicationContext(), ReportContents.EVENT_NOISE_CANCELING, isChecked);
                }
            }
        });
        this.mListView = (ListView) contentView.findViewById(android.R.id.list);
        setListViewItems();
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

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        Context context = getActivity().getApplicationContext();
        IntentFilter headsetPlugFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        context.registerReceiver(this.mHeadsetPlugReceiver, headsetPlugFilter);
        IntentFilter settingFilter = new IntentFilter(SoundEnhancementService.ACTION_CHANGE_NOISE_CANCELING_SETTING);
        context.registerReceiver(this.mChangeSettingReceiver, settingFilter);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (!isNCHeadphonePlugged()) {
            getActivity().finish();
        }
        updateNoiseCancelingEnvironmentSummary();
        updateHeadphoneTypeSummary();
        updateListViewItems();
        if (this.mShowNoiseEnvDialog) {
            showSelectNoiseEnvFragment();
            this.mShowNoiseEnvDialog = false;
        } else if (this.mShowHeadphoneTypeDialog) {
            showSelectHeadphoneTypeFragment();
            this.mShowHeadphoneTypeDialog = false;
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BUNDLE_KEY_SHOW_NC_ENVIRONMENT_DIALOG, this.mShowNoiseEnvDialog);
        if (this.mShowNoiseEnvDialog) {
            outState.putInt(BUNDLE_KEY_CURRENT_ENVIRONMENT, this.mCurrentEnvironment);
            outState.putInt(BUNDLE_KEY_PREVIOUS_ENVIRONMENT, this.mPreviousEnvironment);
        }
        outState.putBoolean(BUNDLE_KEY_SHOW_HEADPHONE_TYPE_DIALOG, this.mShowHeadphoneTypeDialog);
        if (this.mShowHeadphoneTypeDialog) {
            outState.putInt(BUNDLE_KEY_CURRENT_ACCESSORY_TYPE, this.mCurrentAccessoryType);
            outState.putInt(BUNDLE_KEY_PREVIOUS_ACCESSORY_TYPE, this.mPrevAccessoryType);
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if ((getActivity().getChangingConfigurations() & 128) == 128) {
            if (this.mNoiseEnvFragment != null) {
                this.mShowNoiseEnvDialog = true;
            } else if (this.mHeadphoneTypeFragment != null) {
                this.mShowHeadphoneTypeDialog = true;
            }
        }
        dismissDialog();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onStop() {
        Context context = getActivity().getApplicationContext();
        context.unregisterReceiver(this.mHeadsetPlugReceiver);
        context.unregisterReceiver(this.mChangeSettingReceiver);
        super.onStop();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    private void setListViewItems() {
        if (SoundEnhancementUtility.getSupportAutoHeadsetCompensation()) {
            addPreferencesFromResource(R.xml.screen_headphone_type);
            this.mHeadphoneTypePref = (PreferenceScreen) findPreference(KEY_HEADPHONE_TYPE);
            this.mHeadphoneTypePref.setOnPreferenceClickListener(this.mOnPreferenceClickListener);
        }
        this.mPresetList.queryAvailableNoiseCancelingEnvironment(this.mAccessorySetting.getNoiseCancelingEnvironmentNames());
        addPreferencesFromResource(R.xml.noise_canceling_settings);
        this.mNoiseEnvPref = (PreferenceScreen) findPreference(KEY_NOISE_CANCELING_ENVIRONMENT_SETTINGS_ENABLED);
        this.mNoiseEnvPref.setOnPreferenceClickListener(this.mOnPreferenceClickListener);
        this.mPowerSaveModePref = (SwitchPreference) findPreference(KEY_POWER_SAVE_MODE_ENABLED);
        this.mPowerSaveModePref.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
    }

    private void updateNoiseCancelingEnvironmentSummary() {
        int environment;
        if (getActivity() != null && this.mNoiseEnvPref != null) {
            if (this.mShowNoiseEnvDialog) {
                environment = this.mPreviousEnvironment;
            } else {
                environment = this.mAccessorySetting.getNoiseCancelingEnvironment();
            }
            int listIndex = this.mPresetList.getUiListIndexFromNoiseCancelingEnvironment(environment);
            String[] presetList = this.mPresetList.getNoiseCancelingEnvironmentNames();
            this.mNoiseEnvPref.setSummary(this.mLocalise.getLocalisedNoiseCancelingEnvironmentName(getActivity(), presetList[listIndex]));
        }
    }

    private void updateHeadphoneTypeSummary() {
        int accessoryType;
        if (getActivity() != null && this.mHeadphoneTypePref != null) {
            if (this.mShowNoiseEnvDialog) {
                accessoryType = this.mPrevAccessoryType;
            } else {
                accessoryType = this.mAccessorySetting.getAccessoryType();
            }
            String summary = this.mNcHeadphoneInfoManager.getNcHeadphoneName(accessoryType);
            this.mHeadphoneTypePref.setSummary(summary);
        }
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onPositiveButtonClicked(DialogInterface dialog, String tag) {
        if (NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG.equals(tag)) {
            int position = this.mAccessorySetting.getNoiseCancelingEnvironment();
            String[] presetList = this.mPresetList.getNoiseCancelingEnvironmentNames();
            Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_NC_ENVIRONMENT, presetList[position]);
        } else if (HEADPHONE_TYPE_DIALOG_TAG.equals(tag)) {
            int acType = this.mAccessorySetting.getAccessoryType();
            String name = this.mNcHeadphoneInfoManager.getNcHeadphoneName(acType);
            Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_NC_HEADPHONE_TYPE, name);
        }
        dialog.dismiss();
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onNegativeButtonClicked(DialogInterface dialog, String tag) {
        if (NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG.equals(tag)) {
            this.mAccessorySetting.setNoiseCancelingEnvironment(this.mPreviousEnvironment);
        } else if (HEADPHONE_TYPE_DIALOG_TAG.equals(tag)) {
            this.mAccessorySetting.setAccessoryType(this.mPrevAccessoryType);
        }
        dialog.dismiss();
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onListItemClicked(DialogInterface dialog, String tag, int position) {
        if (NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG.equals(tag)) {
            this.mAccessorySetting.setNoiseCancelingEnvironment(position);
            this.mCurrentEnvironment = position;
        } else if (HEADPHONE_TYPE_DIALOG_TAG.equals(tag)) {
            int acType = this.mNcHeadphoneInfoManager.getAccessoryType(position);
            this.mAccessorySetting.setAccessoryType(acType);
            this.mCurrentAccessoryType = acType;
        }
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onDismiss(DialogInterface dialog, String tag) {
        if (NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG.equals(tag)) {
            this.mNoiseEnvFragment = null;
            updateNoiseCancelingEnvironmentSummary();
        } else if (HEADPHONE_TYPE_DIALOG_TAG.equals(tag)) {
            this.mHeadphoneTypeFragment = null;
            updateHeadphoneTypeSummary();
        }
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onCancel(DialogInterface dialog, String tag) {
        if (NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG.equals(tag)) {
            this.mAccessorySetting.setNoiseCancelingEnvironment(this.mPreviousEnvironment);
        } else if (HEADPHONE_TYPE_DIALOG_TAG.equals(tag)) {
            this.mAccessorySetting.setAccessoryType(this.mPrevAccessoryType);
        }
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSelectNoiseEnvFragment() {
        if (this.mNoiseEnvFragment != null) {
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        Resources res = getActivity().getApplicationContext().getResources();
        ListDialogFragment.Builder builder = new ListDialogFragment.Builder();
        builder.setTargetFragment(this);
        builder.setPositiveButton(true);
        builder.setNegativeButton(true);
        int listIndex = this.mPresetList.getUiListIndexFromNoiseCancelingEnvironment(this.mCurrentEnvironment);
        String[] noiseCancelingEnvironmentNames = this.mAccessorySetting.getNoiseCancelingEnvironmentNames();
        builder.setTitle(res.getString(R.string.sound_enhance_strings_setting_noise_env_txt));
        builder.setPresetNames(this.mLocalise.getLocalisedNoiseCancelingEnvironmentNames(getActivity().getApplicationContext(), noiseCancelingEnvironmentNames));
        builder.setCurrentPosition(listIndex);
        this.mNoiseEnvFragment = builder.create();
        this.mNoiseEnvFragment.show(fragmentManager, NOISE_CANCELING_ENVIRONMENT_DIALOG_TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSelectHeadphoneTypeFragment() {
        if (this.mHeadphoneTypeFragment != null) {
            return;
        }
        int itemNum = this.mNcHeadphoneInfoManager.getMaxListItem();
        String[] names = new String[itemNum];
        String[] summaries = new String[itemNum];
        int[] icons = new int[itemNum];
        for (int i = 0; i < itemNum; i++) {
            names[i] = this.mNcHeadphoneInfoManager.getNcHeadphoneNameByListOrder(i);
            summaries[i] = getSummaryFromType(this.mNcHeadphoneInfoManager.getSummaryType(i));
            icons[i] = this.mNcHeadphoneInfoManager.isHiresSupported(i) ? R.drawable.sound_enhance_hires_icn : -1;
        }
        int listIndex = this.mNcHeadphoneInfoManager.getListOrderIndex(this.mCurrentAccessoryType);
        Resources res = getActivity().getApplicationContext().getResources();
        ListDialogFragment.Builder builder = new ListDialogFragment.Builder();
        builder.setTargetFragment(this);
        builder.setPositiveButton(true);
        builder.setNegativeButton(true);
        builder.setTitle(res.getString(R.string.sound_enhance_strings_setting_headset_headphone_type_txt));
        builder.setPresetNamesWithSummary(names, summaries, icons);
        builder.setCurrentPosition(listIndex);
        FragmentManager fragmentManager = getFragmentManager();
        this.mHeadphoneTypeFragment = builder.create();
        this.mHeadphoneTypeFragment.show(fragmentManager, HEADPHONE_TYPE_DIALOG_TAG);
    }

    public void dismissDialog() {
        if (this.mNoiseEnvFragment != null) {
            if (!this.mShowNoiseEnvDialog) {
                this.mAccessorySetting.setNoiseCancelingEnvironment(this.mPreviousEnvironment);
            }
            this.mNoiseEnvFragment.dismiss();
            this.mNoiseEnvFragment = null;
            return;
        }
        if (this.mHeadphoneTypeFragment != null) {
            if (!this.mShowHeadphoneTypeDialog) {
                this.mAccessorySetting.setAccessoryType(this.mPrevAccessoryType);
            }
            this.mHeadphoneTypeFragment.dismiss();
            this.mHeadphoneTypeFragment = null;
        }
    }

    public boolean onPreferenceSwitchChange(Preference preference, Object newValue) {
        SwitchPreference switchPreference = this.mPowerSaveModePref;
        if (preference == switchPreference && switchPreference != null) {
            boolean powerSaveModeEnabled = ((Boolean) newValue).booleanValue();
            boolean powerSaveModeEnabledOld = this.mPowerSaveModePref.isChecked();
            if (powerSaveModeEnabled != powerSaveModeEnabledOld) {
                this.mAccessorySetting.setNoiseCancelingPowerSaveMode(powerSaveModeEnabled);
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_NC_POWER_SAVE, powerSaveModeEnabled);
                return true;
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListViewItems() {
        SwitchPreference switchPreference = this.mPowerSaveModePref;
        if (switchPreference != null) {
            switchPreference.setChecked(this.mAccessorySetting.getNoiseCancelingPowerSaveMode());
        }
        boolean isNoiseCancelingEnabled = this.mAccessorySetting.getNoiseCancelingState();
        SwitchBar switchBar = this.mEnabledSwitchBar;
        if (switchBar != null) {
            switchBar.setChecked(isNoiseCancelingEnabled);
        }
        setEnabledPreference(isNoiseCancelingEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNoiseCancelingState(boolean enabled) {
        this.mAccessorySetting.setNoiseCancelingState(enabled);
        Context context = getActivity().getApplicationContext();
        SoundEnhancementUtility.sendChangeNoiseCancelingSettingsAsAll(context);
        setEnabledPreference(enabled);
    }

    private void setEnabledPreference(boolean isEnabled) {
        PreferenceScreen preferenceScreen = this.mNoiseEnvPref;
        if (preferenceScreen != null) {
            preferenceScreen.setEnabled(isEnabled);
        }
        SwitchPreference switchPreference = this.mPowerSaveModePref;
        if (switchPreference != null) {
            switchPreference.setEnabled(isEnabled);
        }
        PreferenceScreen preferenceScreen2 = this.mHeadphoneTypePref;
        if (preferenceScreen2 != null) {
            preferenceScreen2.setEnabled(isEnabled);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNCHeadphonePlugged() {
        if (2 != SoundEnhancementUtility.pluggedAccessory(getActivity())) {
            return false;
        }
        int type = this.mAccessorySetting.getAccessoryType();
        if (type != 0 && type != 1 && type != 2 && type != 3) {
            return false;
        }
        return true;
    }

    private String getSummaryFromType(int summaryType) {
        Resources res = getActivity().getApplicationContext().getResources();
        if (summaryType == 0) {
            return res.getString(R.string.sound_enhance_strings_setting_audio_accessory_nc_headset_txt);
        }
        if (summaryType == 1) {
            return res.getString(R.string.sound_enhance_strings_setting_audio_accessory_nc_headset2_txt);
        }
        return "";
    }
}
