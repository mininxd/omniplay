package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class MorePreferenceFragment extends PreferenceFragment implements ServiceConnection {
    private static final int DELAY_TIME = 500;
    private static final String KEY_CLEAR_PHASE_ENABLED = "clear_phase";
    private static final String KEY_CLEAR_STEREO_ENABLED = "clear_stereo";
    private static final String KEY_XLOUD_ENABLED = "xloud";
    private static final String LOG_TAG = "SoundEnhancement";
    private static final String WALKMAN_PACKAGENAME = "com.sonyericsson.music";
    private SwitchPreference mClearPhasePref;
    private SwitchPreference mClearStereoPref;
    private int mContentType;
    private View mContentView;
    private SwitchPreference mDseeHxPref;
    private PreferenceScreen mEqualizerPref;
    private boolean mIsCreatedViewItems;
    private boolean mIsUpdatedViewItems;
    private ListView mListView;
    private String mPackageName;
    private SoundEnhancementPresetList mPresetList;
    private SwitchPreference mSForceSurroundPref;
    private AudioEffectService mService;
    private boolean mServiceConnected;
    boolean mSupportClearPhase;
    boolean mSupportSForceSurround;
    boolean mSupportSpeakerBundle;
    boolean mSupportXLoud;
    private PreferenceScreen mSurroundPref;
    private Timer mTimer;
    private SwitchPreference mXLoudPref;
    Handler mHandler = new Handler();
    private AudioEffectManager.OnControlStatusChangeListener mControlStatusChangeListener = new AudioEffectManager.OnControlStatusChangeListener() { // from class: com.sonyericsson.soundenhancement.MorePreferenceFragment.1
        @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager.OnControlStatusChangeListener
        public void onControlStatusChange(boolean controlGranted) {
            if (!controlGranted) {
                MorePreferenceFragment.this.showErrorNoControl();
            }
        }
    };
    private Preference.OnPreferenceChangeListener mCheckBoxPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.sonyericsson.soundenhancement.MorePreferenceFragment.2
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return MorePreferenceFragment.this.onPreferenceSwitchChange(preference, newValue);
        }
    };
    private Preference.OnPreferenceClickListener mOnPreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.sonyericsson.soundenhancement.MorePreferenceFragment.3
        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(Preference preference) {
            if (!MorePreferenceFragment.this.getActivity().isFinishing()) {
                if (MorePreferenceFragment.this.mSurroundPref != preference) {
                    if (MorePreferenceFragment.this.mEqualizerPref == preference) {
                        Intent equalizerIntent = new Intent(MorePreferenceFragment.this.getActivity().getIntent());
                        equalizerIntent.setClass(MorePreferenceFragment.this.getActivity(), EqualizerActivity.class);
                        MorePreferenceFragment.this.startActivity(equalizerIntent);
                        return true;
                    }
                    return true;
                }
                Intent surroundIntent = new Intent(MorePreferenceFragment.this.getActivity().getIntent());
                surroundIntent.setClass(MorePreferenceFragment.this.getActivity(), SurroundSelectorActivity.class);
                MorePreferenceFragment.this.startActivity(surroundIntent);
                return true;
            }
            return true;
        }
    };

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.mService = ((SoundEnhancementService.SoundEnhancementServiceBinder) service).getAudioEffectService();
        String str = this.mPackageName;
        if (str != null && str.length() > 0) {
            this.mService.createSessionHolder(this.mPackageName, this.mContentType);
            this.mService.restoreAudioSessionFromPreference();
            this.mService.setControlStatusListener(this.mPackageName, this.mControlStatusChangeListener);
        }
        setupViewItems();
        finishActivityBySettings();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        String str;
        if (this.mService != null && (str = this.mPackageName) != null && str.length() > 0) {
            this.mService.cancelControlStatusListener(this.mPackageName);
        }
        this.mService = null;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        this.mPackageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        this.mContentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", 0);
        startAndBindToService();
    }

    private void startAndBindToService() {
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName cn = new ComponentName(getActivity(), (Class<?>) SoundEnhancementService.class);
        serviceStartIntent.setComponent(cn);
        ComponentName cn2 = getActivity().startService(serviceStartIntent);
        if (cn2 == null) {
            Log.e(LOG_TAG, "Failed to connect to SoundEnhancementService!");
        }
        this.mServiceConnected = getActivity().getApplicationContext().bindService(serviceStartIntent, this, 1);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        this.mContentView = inflater.inflate(R.layout.settinglist, viewGroup, false);
        this.mListView = (ListView) this.mContentView.findViewById(android.R.id.list);
        return this.mContentView;
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
        setupViewItems();
        finishActivityBySettings();
    }

    @Override // android.app.Fragment
    public void onPause() {
        this.mIsUpdatedViewItems = false;
        super.onPause();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onDestroy() {
        if (this.mServiceConnected) {
            getActivity().getApplicationContext().unbindService(this);
            this.mServiceConnected = false;
        }
        super.onDestroy();
    }

    private void setupViewItems() {
        if (this.mService != null && !this.mIsUpdatedViewItems) {
            if (!this.mIsCreatedViewItems) {
                this.mIsCreatedViewItems = true;
                setListViewItems();
            }
            this.mIsUpdatedViewItems = true;
            updateSurroundSummary();
            updateListViewItems();
            updateEqualizerSummary();
        }
    }

    public boolean onPreferenceSwitchChange(Preference preference, Object newValue) {
        SwitchPreference switchPreference = this.mXLoudPref;
        if (preference == switchPreference && switchPreference != null) {
            boolean xloudEnabled = ((Boolean) newValue).booleanValue();
            boolean xloudEnabledOld = this.mXLoudPref.isChecked();
            if (xloudEnabled != xloudEnabledOld) {
                this.mService.setXLoud(xloudEnabled);
                this.mService.saveSettingsToPrefs();
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_XLOUD, xloudEnabled);
            }
        }
        SwitchPreference switchPreference2 = this.mClearPhasePref;
        if (preference == switchPreference2 && switchPreference2 != null) {
            boolean ClearPhaseEnabled = ((Boolean) newValue).booleanValue();
            boolean ClearPhaseEnabledOld = this.mClearPhasePref.isChecked();
            if (ClearPhaseEnabled != ClearPhaseEnabledOld) {
                this.mService.setClearPhase(ClearPhaseEnabled);
                this.mService.saveSettingsToPrefs();
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_CLEAR_PHASE, ClearPhaseEnabled);
            }
        }
        SwitchPreference switchPreference3 = this.mClearStereoPref;
        if (preference == switchPreference3 && switchPreference3 != null) {
            boolean ClearStereoEnabled = ((Boolean) newValue).booleanValue();
            boolean ClearStereoEnabledOld = this.mClearStereoPref.isChecked();
            if (ClearStereoEnabled != ClearStereoEnabledOld) {
                this.mService.setClearStereo(ClearStereoEnabled);
                this.mService.saveSettingsToPrefs();
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_CLEAR_STEREO, ClearStereoEnabled);
            }
        }
        SwitchPreference switchPreference4 = this.mSForceSurroundPref;
        if (preference == switchPreference4 && switchPreference4 != null) {
            boolean SForceSurroundEnabled = ((Boolean) newValue).booleanValue();
            boolean SForceSurroundEnabledOld = this.mSForceSurroundPref.isChecked();
            if (SForceSurroundEnabled != SForceSurroundEnabledOld) {
                if (this.mSupportSpeakerBundle) {
                    this.mService.setSpeakerBundle(SForceSurroundEnabled);
                } else {
                    this.mService.setSForceSurround(SForceSurroundEnabled);
                }
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_SFORCE, SForceSurroundEnabled);
                this.mService.saveSettingsToPrefs();
            }
        }
        SwitchPreference switchPreference5 = this.mDseeHxPref;
        if (preference == switchPreference5 && switchPreference5 != null) {
            boolean dseeHxEnabled = ((Boolean) newValue).booleanValue();
            boolean dseeHxEnabledOld = this.mDseeHxPref.isChecked();
            if (dseeHxEnabled != dseeHxEnabledOld) {
                boolean doSendIntent = false;
                int accessory = SoundEnhancementUtility.pluggedAccessory(getActivity().getApplicationContext());
                if (accessory == 3 || accessory == 2) {
                    doSendIntent = true;
                }
                this.mService.setDseeHx(dseeHxEnabled, doSendIntent);
                this.mService.saveSettingsToPrefs();
                this.mDseeHxPref.setEnabled(false);
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_DSEE_HX, dseeHxEnabled);
                this.mTimer = new Timer(true);
                this.mTimer.schedule(new TimerTask() { // from class: com.sonyericsson.soundenhancement.MorePreferenceFragment.4
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        MorePreferenceFragment.this.mHandler.post(new Runnable() { // from class: com.sonyericsson.soundenhancement.MorePreferenceFragment.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MorePreferenceFragment.this.mDseeHxPref.setEnabled(true);
                            }
                        });
                    }
                }, 500L);
            }
        }
        return true;
    }

    private void updateEqualizerSummary() {
        if (this.mEqualizerPref != null) {
            int preset = this.mService.getCurrentEqualizerPreset();
            String[] presetList = AudioEffectUtilities.getEqualizerPresets();
            SoundEnhancementLocalise localise = new SoundEnhancementLocalise();
            this.mEqualizerPref.setSummary(localise.getLocalisedEqualizerPresetName(getActivity(), presetList[preset]));
        }
    }

    private void updateSurroundSummary() {
        this.mContentView.setVisibility(0);
        PreferenceScreen pref = (PreferenceScreen) findPreference(Constants.KEY_PREF_SURROUND_SETTINGS);
        if (pref != null) {
            int preset = this.mService.getCurrentSurroundPreset();
            int listIndex = this.mPresetList.getUiListIndexFromSurroundPreset(preset);
            String[] presetList = this.mPresetList.getSurroundPresetNames();
            SoundEnhancementLocalise localise = new SoundEnhancementLocalise();
            pref.setSummary(localise.getLocalisedSurroundPresetName(getActivity(), presetList[listIndex]));
        }
    }

    private void updateListViewItems() {
        SwitchPreference switchPreference = this.mSForceSurroundPref;
        if (switchPreference != null) {
            if (this.mSupportSpeakerBundle) {
                switchPreference.setChecked(this.mService.getCurrentSpeakerBundle());
            } else {
                switchPreference.setChecked(this.mService.getCurrentSForceSurround());
            }
        }
        SwitchPreference switchPreference2 = this.mClearStereoPref;
        if (switchPreference2 != null) {
            switchPreference2.setChecked(this.mService.getCurrentClearStereo());
        }
        SwitchPreference switchPreference3 = this.mClearPhasePref;
        if (switchPreference3 != null) {
            switchPreference3.setChecked(this.mService.getCurrentClearPhase());
        }
        SwitchPreference switchPreference4 = this.mXLoudPref;
        if (switchPreference4 != null) {
            switchPreference4.setChecked(this.mService.getCurrentXLoud());
        }
        SwitchPreference switchPreference5 = this.mDseeHxPref;
        if (switchPreference5 != null) {
            switchPreference5.setChecked(this.mService.getCurrentDseeHx());
        }
    }

    private void setListViewItems() {
        this.mPresetList = new SoundEnhancementPresetList();
        String[] equalizerPresetList = AudioEffectUtilities.getEqualizerPresets();
        if (equalizerPresetList != null) {
            addPreferencesFromResource(R.xml.screen_equalizer_settings);
            this.mEqualizerPref = (PreferenceScreen) findPreference(Constants.KEY_PREF_EQUALIZER_SETTINGS);
            this.mEqualizerPref.setOnPreferenceClickListener(this.mOnPreferenceClickListener);
        }
        addPreferencesFromResource(R.xml.category_headphone);
        String[] surroundPresetList = AudioEffectUtilities.getSurroundPresetNames();
        if (surroundPresetList != null) {
            this.mPresetList.queryAvailableSurroundPresets(surroundPresetList);
            addPreferencesFromResource(R.xml.screen_surround_settings);
            this.mSurroundPref = (PreferenceScreen) findPreference(Constants.KEY_PREF_SURROUND_SETTINGS);
            this.mSurroundPref.setOnPreferenceClickListener(this.mOnPreferenceClickListener);
        }
        Resources res = getActivity().getApplicationContext().getResources();
        boolean enableClearStereo = res.getBoolean(R.bool.enabled_clear_stereo);
        if (enableClearStereo) {
            addPreferencesFromResource(R.xml.checkbox_clear_stereo);
            this.mClearStereoPref = (SwitchPreference) findPreference(KEY_CLEAR_STEREO_ENABLED);
            this.mClearStereoPref.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
        }
        this.mSupportXLoud = res.getBoolean(R.bool.enabled_xloud);
        this.mSupportClearPhase = res.getBoolean(R.bool.enabled_clear_phase);
        this.mSupportSForceSurround = res.getBoolean(R.bool.enabled_s_force_surround);
        this.mSupportSpeakerBundle = res.getBoolean(R.bool.enabled_speaker_bundle);
        if ((this.mSupportXLoud || this.mSupportClearPhase || this.mSupportSForceSurround || this.mSupportSpeakerBundle) && !SoundEnhancementUtility.isMonoSpeaker()) {
            addPreferencesFromResource(R.xml.category_speaker);
        }
        if (this.mSupportClearPhase) {
            addPreferencesFromResource(R.xml.checkbox_clear_phase);
            this.mClearPhasePref = (SwitchPreference) findPreference(KEY_CLEAR_PHASE_ENABLED);
            this.mClearPhasePref.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
        }
        if (this.mSupportXLoud) {
            addPreferencesFromResource(R.xml.checkbox_xloud);
            this.mXLoudPref = (SwitchPreference) findPreference(KEY_XLOUD_ENABLED);
            this.mXLoudPref.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
        }
        if ((this.mSupportSForceSurround || this.mSupportSpeakerBundle) && !SoundEnhancementUtility.isMonoSpeaker()) {
            addPreferencesFromResource(R.xml.checkbox_s_force_surround);
            this.mSForceSurroundPref = (SwitchPreference) findPreference(Constants.KEY_PREF_S_FORCE_SURROUND);
            this.mSForceSurroundPref.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorNoControl() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (isResumed()) {
            new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setTitle(android.R.string.dialog_alert_title).setMessage(R.string.sound_enhance_strings_error_not_available_by_prio_txt).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.MorePreferenceFragment.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    activity.finish();
                }
            }).setCancelable(false).create().show();
        } else {
            activity.finish();
        }
    }

    private void finishActivityBySettings() {
        Activity activity;
        if (!SoundEnhancementUtility.canShowSoundEffectsScreen(this.mService) && (activity = getActivity()) != null && !activity.isFinishing()) {
            activity.finish();
        }
    }
}
