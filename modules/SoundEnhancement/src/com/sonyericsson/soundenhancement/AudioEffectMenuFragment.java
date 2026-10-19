package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import com.sonyericsson.soundenhancement.widget.TwoTargetSwitchPreference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectMenuFragment extends PreferenceFragment implements ServiceConnection {
    private static final int DELAY_TIME = 500;
    private static final int DSEE_DELAY_TIME = 2000;
    private static final String LOG_TAG = "SoundEnhancement";
    private AccessorySetting mAccessorySetting;
    private PreferenceScreen mAhcPref;
    private SwitchPreference mClearAudioPlusPref;
    private int mContentType;
    private TwoTargetSwitchPreference mDolbyEffectPref;
    private SwitchPreference mDseeHxPref;
    private SwitchPreference mDynamicNormalizerPref;
    private boolean mIsCreatedViewItems;
    private boolean mIsStartActivity;
    private boolean mIsSupported5PoleAccessory;
    private boolean mIsSupportedCAPlus;
    private boolean mIsSupportedDolbyEffect;
    private boolean mIsSupportedStereoMic;
    private boolean mIsUpdatedViewItems;
    private ListView mListView;
    private SoundEnhancementLocalise mLocalise;
    private PreferenceScreen mNoiseCancelingPref;
    private boolean mNoiseCancelingState;
    private String mPackageName;
    private AudioEffectService mService;
    private boolean mServiceConnected;
    private PreferenceScreen mSoundEnhancementPref;
    private PreferenceScreen mStereoMicPref;
    private Timer mTimer;
    private Handler mHandler = new Handler();
    private boolean mLocalDseeHxEnabled = false;
    private AudioEffectManager.OnControlStatusChangeListener mControlStatusChangeListener = new AudioEffectManager.OnControlStatusChangeListener() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.1
        @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager.OnControlStatusChangeListener
        public void onControlStatusChange(boolean controlGranted) {
            if (!controlGranted) {
                AudioEffectMenuFragment.this.showErrorNoControl();
            }
        }
    };
    private Preference.OnPreferenceChangeListener mPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.2
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return AudioEffectMenuFragment.this.onEffectChange(preference, newValue);
        }
    };
    private BroadcastReceiver mCAPReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(AudioEffectMenuFragment.LOG_TAG, "AudioEffectMenuFragment.onReceive() : action is null.");
                return;
            }
            if (action.equals("com.sonymobile.audioeffect.intent.action.CLEARAUDIO_PLUS_STATUS")) {
                if (AudioEffectMenuFragment.this.mClearAudioPlusPref == null) {
                    Log.e(AudioEffectMenuFragment.LOG_TAG, "AudioEffectMenuFragment.onReceive() : ClearAudio+ is not supported.");
                    return;
                }
                int status = intent.getIntExtra("com.sonymobile.audioeffect.intent.extra.CLEARAUDIO_PLUS_STATUS", 0);
                AudioEffectMenuFragment.this.mClearAudioPlusPref.setChecked(status == 1);
                AudioEffectMenuFragment.this.updateListViewItems();
            }
        }
    };
    private BroadcastReceiver mNCSettingReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean newState;
            String action = intent.getAction();
            if (action == null) {
                Log.w(AudioEffectMenuFragment.LOG_TAG, "AudioEffectMenuFragment.mNCSettingReceiver.onReceive() : action is null.");
            } else if (action.equals(SoundEnhancementService.ACTION_CHANGE_NOISE_CANCELING_SETTING) && (newState = AudioEffectMenuFragment.this.mAccessorySetting.getNoiseCancelingState()) != AudioEffectMenuFragment.this.mNoiseCancelingState) {
                AudioEffectMenuFragment.this.mNoiseCancelingState = newState;
                AudioEffectMenuFragment.this.updateNoiseCancelingSummary();
            }
        }
    };
    private BroadcastReceiver mHeadphoneConnectReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(AudioEffectMenuFragment.LOG_TAG, "AudioEffectMenuFragment.mHeadphoneConnectReceiver.onReceive() : action is null.");
            } else if (action.equals("android.intent.action.HEADSET_PLUG")) {
                AudioEffectMenuFragment.this.updateNoiseCancelingSummary();
                AudioEffectMenuFragment.this.updateStereoMicSummary();
            }
        }
    };
    private BroadcastReceiver mMicConnectReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(AudioEffectMenuFragment.LOG_TAG, "AudioEffectMenuFragment.mMicConnectReceiver.onReceive() : action is null.");
            } else if (action.equals("com.sonymobile.media.intent.action.ACTION_EXTERNAL_STEREO_MIC_PLUG")) {
                AudioEffectMenuFragment.this.updateNoiseCancelingSummary();
                AudioEffectMenuFragment.this.updateStereoMicSummary();
            }
        }
    };
    private BroadcastReceiver mAccessoryChangeReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(AudioEffectMenuFragment.LOG_TAG, "AudioEffectMenuFragment.mAccessoryChangeReceiver.onReceive() : action is null.");
            } else if (action.equals(AccessorySetting.ACTION_CHANGE_ACCESSORY_SETTING)) {
                AudioEffectMenuFragment.this.updateNoiseCancelingSummary();
            }
        }
    };
    private Preference.OnPreferenceClickListener mPreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.8
        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(Preference preference) {
            if (AudioEffectMenuFragment.this.mIsStartActivity) {
                return true;
            }
            if (AudioEffectMenuFragment.this.mSoundEnhancementPref == null || preference != AudioEffectMenuFragment.this.mSoundEnhancementPref) {
                if (AudioEffectMenuFragment.this.mAhcPref != null && preference == AudioEffectMenuFragment.this.mAhcPref) {
                    Intent intent = new Intent(AudioEffectMenuFragment.this.getActivity().getIntent());
                    intent.setClass(AudioEffectMenuFragment.this.getActivity(), AutomaticHeadphoneActivity.class);
                    AudioEffectMenuFragment.this.startActivity(intent);
                } else if (!"noise_canceling".equals(preference.getKey())) {
                    if (AudioEffectMenuFragment.this.mStereoMicPref == null || preference != AudioEffectMenuFragment.this.mStereoMicPref) {
                        if (AudioEffectMenuFragment.this.mDolbyEffectPref != null && preference == AudioEffectMenuFragment.this.mDolbyEffectPref) {
                            Intent startIntent = new Intent(AudioEffectMenuFragment.this.getActivity().getIntent());
                            startIntent.setClass(AudioEffectMenuFragment.this.getActivity(), DolbyEffectActivity.class);
                            AudioEffectMenuFragment.this.startActivity(startIntent);
                        }
                    } else {
                        Intent startIntent2 = new Intent(AudioEffectMenuFragment.this.getActivity().getIntent());
                        startIntent2.setClass(AudioEffectMenuFragment.this.getActivity(), StereoMicActivity.class);
                        AudioEffectMenuFragment.this.startActivity(startIntent2);
                    }
                } else {
                    Intent startIntent3 = new Intent(AudioEffectMenuFragment.this.getActivity().getIntent());
                    startIntent3.setClass(AudioEffectMenuFragment.this.getActivity(), NoiseCancelingActivity.class);
                    AudioEffectMenuFragment.this.startActivity(startIntent3);
                }
            } else {
                Intent startIntent4 = new Intent(AudioEffectMenuFragment.this.getActivity().getIntent());
                startIntent4.setClass(AudioEffectMenuFragment.this.getActivity(), SoundEnhancementActivity.class);
                AudioEffectMenuFragment.this.startActivity(startIntent4);
            }
            AudioEffectMenuFragment.this.mIsStartActivity = true;
            return true;
        }
    };
    private Preference.OnPreferenceChangeListener mSwitchPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.13
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return AudioEffectMenuFragment.this.onPreferenceSwitchChange(preference, newValue);
        }
    };
    private AudioEffectService.DolbyEventListener mDolbyEventListener = new AudioEffectService.DolbyEventListener() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.14
        @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService.DolbyEventListener
        public void receiveEvents() {
            Activity activity = AudioEffectMenuFragment.this.getActivity();
            if (activity != null && !activity.isFinishing()) {
                AudioEffectMenuFragment.this.updateListViewItems();
            }
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
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null) {
            audioEffectService.setDolbyEventListener(this.mDolbyEventListener);
        }
        setupViewItems();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        String str;
        if (this.mService != null && (str = this.mPackageName) != null && str.length() > 0) {
            this.mService.cancelControlStatusListener(this.mPackageName);
        }
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null) {
            audioEffectService.setDolbyEventListener(null);
        }
        this.mService = null;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        this.mPackageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        this.mContentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", 0);
        this.mAccessorySetting = new AccessorySetting(getActivity().getApplicationContext());
        this.mIsSupportedCAPlus = SoundEnhancementUtility.isSupportedClearAudioPlus();
        this.mIsSupported5PoleAccessory = this.mAccessorySetting.isSupportedFivePoleAccessory();
        this.mIsSupportedStereoMic = this.mAccessorySetting.isSupportedStereoMic();
        this.mIsSupportedDolbyEffect = SoundEnhancementUtility.isSupportedDolbyEffect();
        this.mLocalise = new SoundEnhancementLocalise();
        startAndBindToService();
    }

    private void startAndBindToService() {
        Activity activity = getActivity();
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName cn = new ComponentName(activity, (Class<?>) SoundEnhancementService.class);
        serviceStartIntent.setComponent(cn);
        ComponentName cn2 = activity.startService(serviceStartIntent);
        if (cn2 == null) {
            Log.e(LOG_TAG, "Failed to connect to SoundEnhancementService!");
        }
        this.mServiceConnected = activity.getApplicationContext().bindService(serviceStartIntent, this, 1);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.settinglist, viewGroup, false);
        this.mListView = (ListView) contentView.findViewById(android.R.id.list);
        this.mListView.setItemsCanFocus(true);
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
        if (this.mIsSupportedCAPlus) {
            IntentFilter filter = new IntentFilter("com.sonymobile.audioeffect.intent.action.CLEARAUDIO_PLUS_STATUS");
            context.registerReceiver(this.mCAPReceiver, filter);
        }
        if (this.mIsSupported5PoleAccessory) {
            IntentFilter filter2 = new IntentFilter(SoundEnhancementService.ACTION_CHANGE_NOISE_CANCELING_SETTING);
            context.registerReceiver(this.mNCSettingReceiver, filter2);
            this.mNoiseCancelingState = this.mAccessorySetting.getNoiseCancelingState();
            IntentFilter plugIntentFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
            context.registerReceiver(this.mHeadphoneConnectReceiver, plugIntentFilter);
            IntentFilter accessoryChangeIntentFilter = new IntentFilter(AccessorySetting.ACTION_CHANGE_ACCESSORY_SETTING);
            context.registerReceiver(this.mAccessoryChangeReceiver, accessoryChangeIntentFilter);
        }
        if (this.mIsSupportedStereoMic) {
            IntentFilter micIntentFilter = new IntentFilter("com.sonymobile.media.intent.action.ACTION_EXTERNAL_STEREO_MIC_PLUG");
            context.registerReceiver(this.mMicConnectReceiver, micIntentFilter);
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mIsStartActivity = false;
        setupViewItems();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Fragment
    public void onPause() {
        this.mIsUpdatedViewItems = false;
        super.onPause();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onStop() {
        Context context = getActivity().getApplicationContext();
        if (this.mIsSupported5PoleAccessory) {
            context.unregisterReceiver(this.mNCSettingReceiver);
            context.unregisterReceiver(this.mHeadphoneConnectReceiver);
            context.unregisterReceiver(this.mAccessoryChangeReceiver);
        }
        if (this.mIsSupportedStereoMic) {
            context.unregisterReceiver(this.mMicConnectReceiver);
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        if (this.mIsSupportedCAPlus) {
            context.unregisterReceiver(this.mCAPReceiver);
        }
        super.onStop();
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
            updateListViewItems();
            this.mIsUpdatedViewItems = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onEffectChange(Preference preference, Object newValue) {
        SwitchPreference switchPreference = this.mClearAudioPlusPref;
        if (preference == switchPreference && switchPreference != null) {
            boolean clearAudioPlusEnabled = ((Boolean) newValue).booleanValue();
            boolean clearAudioPlusEnabledOld = this.mClearAudioPlusPref.isChecked();
            if (clearAudioPlusEnabled != clearAudioPlusEnabledOld) {
                this.mService.setClearAudioPlus(clearAudioPlusEnabled);
                this.mService.saveSettingsToPrefs();
                updateListViewItems();
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_CLAER_AUDIO_PLUS, clearAudioPlusEnabled);
                return true;
            }
            return true;
        }
        SwitchPreference switchPreference2 = this.mDynamicNormalizerPref;
        if (preference == switchPreference2 && switchPreference2 != null) {
            boolean dynamicNormalizerEnabled = ((Boolean) newValue).booleanValue();
            boolean dynamicNormalizerEnabledOld = this.mDynamicNormalizerPref.isChecked();
            if (dynamicNormalizerEnabled != dynamicNormalizerEnabledOld) {
                this.mService.setDynamicNormalizer(dynamicNormalizerEnabled);
                this.mService.saveSettingsToPrefs();
                Reporter.reportEvent(getActivity().getApplicationContext(), ReportContents.EVENT_DYNAMIC_NORMALIZER, dynamicNormalizerEnabled);
                return true;
            }
            return true;
        }
        return true;
    }

    private void setListViewItems() {
        if (this.mIsSupportedDolbyEffect) {
            createDolbyEffectPref();
        }
        createDseeHxPref();
        createClearAudioPlusPref();
        createSoundEnhancementPref();
        createDynamicNormalizerPref();
        createAhcPref();
        createNoiseCancelingPref();
        createStereoMicPref();
    }

    private void setListViewItemsOld() {
        if (this.mIsSupportedDolbyEffect) {
            createDolbyEffectPref();
            createDseeHxPref();
            return;
        }
        createDseeHxPref();
        createClearAudioPlusPref();
        createSoundEnhancementPref();
        createDynamicNormalizerPref();
        createAhcPref();
        createNoiseCancelingPref();
        createStereoMicPref();
    }

    private void createDolbyEffectPref() {
        if (this.mIsSupportedDolbyEffect) {
            addPreferencesFromResource(R.xml.switch_dolby_effect);
            this.mDolbyEffectPref = (TwoTargetSwitchPreference) findPreference(Constants.KEY_PREF_DOLBY_EFFECT);
            this.mDolbyEffectPref.setOnPreferenceClickListener(this.mPreferenceClickListener);
            this.mDolbyEffectPref.setOnPreferenceChangeListener(this.mSwitchPreferenceChangeListener);
        }
    }

    private void createDseeHxPref() {
        addPreferencesFromResource(R.xml.checkbox_dsee_hx);
        this.mDseeHxPref = (SwitchPreference) findPreference(Constants.KEY_PREF_DSEE_HX);
        this.mDseeHxPref.setOnPreferenceChangeListener(this.mSwitchPreferenceChangeListener);
    }

    private void createClearAudioPlusPref() {
        if (this.mIsSupportedCAPlus) {
            addPreferencesFromResource(R.xml.checkbox_clear_audio_plus);
            this.mClearAudioPlusPref = (SwitchPreference) findPreference(Constants.KEY_PREF_CLEAR_AUDIO_PLUS);
            this.mClearAudioPlusPref.setOnPreferenceChangeListener(this.mPreferenceChangeListener);
        }
    }

    private void createSoundEnhancementPref() {
        addPreferencesFromResource(R.xml.screen_sound_enhancement);
        this.mSoundEnhancementPref = (PreferenceScreen) findPreference(Constants.KEY_PREF_SOUND_ENHANCEMENT);
        this.mSoundEnhancementPref.setOnPreferenceClickListener(this.mPreferenceClickListener);
    }

    private void createDynamicNormalizerPref() {
        Resources res = getActivity().getApplicationContext().getResources();
        if (res.getBoolean(R.bool.enabled_dynamic_normalizer)) {
            addPreferencesFromResource(R.xml.checkbox_dynamic_normalizer);
            this.mDynamicNormalizerPref = (SwitchPreference) findPreference(Constants.KEY_PREF_DYNAMIC_NORMALIZER);
            this.mDynamicNormalizerPref.setOnPreferenceChangeListener(this.mPreferenceChangeListener);
        }
    }

    private void createAhcPref() {
        if (SoundEnhancementUtility.getSupportAutoHeadsetCompensation()) {
            addPreferencesFromResource(R.xml.screen_headset_optimization);
            this.mAhcPref = (PreferenceScreen) findPreference(Constants.KEY_PREF_HEADSET_OPTIMIZATION);
            this.mAhcPref.setOnPreferenceClickListener(this.mPreferenceClickListener);
        }
    }

    private void createNoiseCancelingPref() {
        if (this.mIsSupported5PoleAccessory) {
            addPreferencesFromResource(R.xml.screen_noise_canceling_settings);
            this.mNoiseCancelingPref = (PreferenceScreen) findPreference("noise_canceling");
            this.mNoiseCancelingPref.setOnPreferenceClickListener(this.mPreferenceClickListener);
        }
    }

    private void createStereoMicPref() {
        if (this.mIsSupportedStereoMic) {
            addPreferencesFromResource(R.xml.screen_stereo_mic);
            this.mStereoMicPref = (PreferenceScreen) findPreference(Constants.KEY_PREF_STEREO_MIC);
            this.mStereoMicPref.setOnPreferenceClickListener(this.mPreferenceClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListViewItems() {
        updateListViewItems(this.mService.getCurrentDseeHx());
    }

    private void updateListViewItems(boolean dseeEnabled) {
        updateDolbyAudioPref();
        updateDseeHxPref();
        SwitchPreference switchPreference = this.mClearAudioPlusPref;
        if (switchPreference != null) {
            if (!dseeEnabled) {
                switchPreference.setChecked(this.mService.getCurrentClearAudioPlus());
                this.mClearAudioPlusPref.setSummary(R.string.sound_enhance_strings_setting_clearaudio_plus_desc_txt);
                this.mClearAudioPlusPref.setEnabled(true);
            } else {
                switchPreference.setChecked(false);
                this.mClearAudioPlusPref.setSummary(R.string.sound_enhance_strings_setting_exclusive_dsee_desc_txt);
                this.mClearAudioPlusPref.setEnabled(false);
            }
        }
        SwitchPreference switchPreference2 = this.mDynamicNormalizerPref;
        if (switchPreference2 != null) {
            if (!dseeEnabled) {
                switchPreference2.setChecked(this.mService.getCurrentDynamicNormalizer());
                this.mDynamicNormalizerPref.setSummary(R.string.sound_enhance_strings_setting_dynamic_normalizer_desc_txt);
                this.mDynamicNormalizerPref.setEnabled(true);
            } else {
                switchPreference2.setChecked(false);
                this.mDynamicNormalizerPref.setSummary(R.string.sound_enhance_strings_setting_exclusive_dsee_desc_txt);
                this.mDynamicNormalizerPref.setEnabled(false);
            }
        }
        PreferenceScreen preferenceScreen = this.mSoundEnhancementPref;
        if (preferenceScreen != null) {
            if (dseeEnabled) {
                preferenceScreen.setSummary(R.string.sound_enhance_strings_setting_exclusive_dsee_desc_txt);
                this.mSoundEnhancementPref.setEnabled(false);
            } else {
                SwitchPreference switchPreference3 = this.mClearAudioPlusPref;
                if (switchPreference3 != null && switchPreference3.isChecked()) {
                    this.mSoundEnhancementPref.setSummary(R.string.sound_enhance_strings_setting_sound_effect_desc_txt);
                    this.mSoundEnhancementPref.setEnabled(false);
                } else {
                    this.mSoundEnhancementPref.setSummary(R.string.sound_enhance_strings_setting_sound_effect_desc_txt);
                    this.mSoundEnhancementPref.setEnabled(true);
                }
            }
        }
        updateNoiseCancelingSummary();
        updateAhcSummary();
        updateStereoMicSummary();
    }

    private String getDolbyProfileName(int profile) {
        int id = -1;
        if (profile == 0) {
            id = R.string.sound_enhance_strings_dolby_atmos_mode_dynamic_title_txt;
        } else if (profile == 1) {
            id = R.string.sound_enhance_strings_dolby_atmos_mode_movie_title_txt;
        } else if (profile == 2) {
            id = R.string.sound_enhance_strings_dolby_atmos_mode_music_title_txt;
        } else if (profile == 3) {
            id = R.string.sound_enhance_strings_dolby_atmos_mode_custom_title_txt;
        }
        return getActivity().getApplicationContext().getResources().getString(id);
    }

    private void updateAhcSummary() {
        int summary;
        if (this.mAhcPref != null) {
            boolean isPermissionDenied = PermissionUtil.isPermissionDenied(getActivity().getApplicationContext(), "android.permission.RECORD_AUDIO");
            boolean isAvailable = isPermissionDenied ? false : this.mAccessorySetting.getAutoHeadsetCompensation();
            this.mAhcPref.setEnabled(true);
            if (isAvailable) {
                summary = R.string.sound_enhance_strings_setting_on_txt;
            } else {
                summary = R.string.sound_enhance_strings_setting_off_txt;
            }
            this.mAhcPref.setSummary(summary);
        }
    }

    private void updateAhcSummaryOld() {
        int summary;
        if (this.mAhcPref != null) {
            if (this.mDolbyEffectPref != null && this.mService.getDolbyState() == 1) {
                this.mAhcPref.setEnabled(false);
                this.mAhcPref.setSummary(R.string.sound_enhance_strings_invalid_due_to_dolby_atmos_txt);
                return;
            }
            boolean isPermissionDenied = PermissionUtil.isPermissionDenied(getActivity().getApplicationContext(), "android.permission.RECORD_AUDIO");
            boolean isAvailable = isPermissionDenied ? false : this.mAccessorySetting.getAutoHeadsetCompensation();
            this.mAhcPref.setEnabled(true);
            if (isAvailable) {
                summary = R.string.sound_enhance_strings_setting_on_txt;
            } else {
                summary = R.string.sound_enhance_strings_setting_off_txt;
            }
            this.mAhcPref.setSummary(summary);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0071  */
    public void updateNoiseCancelingSummary() {
        String summary;
        if (getActivity() == null) {
            return;
        }
        boolean enabled = false;
        if (this.mNoiseCancelingPref != null) {
            Resources res = getActivity().getApplicationContext().getResources();
            int plugAccessory = SoundEnhancementUtility.pluggedAccessory(getActivity());
            if (plugAccessory == 0 || plugAccessory == 1) {
                summary = res.getString(R.string.sound_enhance_strings_setting_noise_cancel_not_connect_nc_headphones_txt);
            } else if (plugAccessory == 2) {
                int type = this.mAccessorySetting.getAccessoryType();
                if (type == 0 || type == 1 || type == 2 || type == 3) {
                    String summary2 = this.mLocalise.getLocalisedNoiseCancelingStateName(getActivity(), this.mNoiseCancelingState);
                    enabled = true;
                    summary = summary2;
                } else {
                    String summary3 = res.getString(R.string.sound_enhance_strings_setting_noise_cancel_incorrect_headphones_txt);
                    summary = summary3;
                }
            } else if (plugAccessory == 3) {
                summary = res.getString(R.string.sound_enhance_strings_setting_noise_cancel_not_connect_nc_headphones_txt);
            } else {
                Log.e(LOG_TAG, "Illegal Accessory is plugged. type is " + plugAccessory);
                summary = res.getString(R.string.sound_enhance_strings_setting_noise_cancel_not_connect_nc_headphones_txt);
            }
            this.mNoiseCancelingPref.setEnabled(enabled);
            this.mNoiseCancelingPref.setSummary(summary);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStereoMicSummary() {
        if (getActivity() != null && this.mStereoMicPref != null) {
            String gain = this.mAccessorySetting.getStereoMicGain();
            String summary = this.mLocalise.getLocalisedStereoMicGain(getActivity(), gain);
            this.mStereoMicPref.setSummary(summary);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorNoControl() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setResult(-1);
        if (isResumed()) {
            new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setTitle(android.R.string.dialog_alert_title).setMessage(R.string.sound_enhance_strings_error_not_available_by_prio_txt).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.9
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

    public boolean onPreferenceSwitchChange(Preference preference, Object newValue) {
        SwitchPreference switchPreference = this.mDseeHxPref;
        if (preference == switchPreference && switchPreference != null) {
            boolean isChecked = ((Boolean) newValue).booleanValue();
            boolean prev = this.mDseeHxPref.isChecked();
            if (isChecked != prev) {
                this.mDseeHxPref.setChecked(isChecked);
                this.mDseeHxPref.setEnabled(false);
                this.mLocalDseeHxEnabled = isChecked;
                updateListViewItems(isChecked);
                changeDseeHx();
            }
        } else {
            TwoTargetSwitchPreference twoTargetSwitchPreference = this.mDolbyEffectPref;
            if (preference == twoTargetSwitchPreference && twoTargetSwitchPreference != null) {
                boolean isChecked2 = ((Boolean) newValue).booleanValue();
                boolean prev2 = this.mService.getDolbyState() == 1;
                if (isChecked2 != prev2) {
                    this.mService.swapDolbyState();
                    updateListViewItems();
                }
            }
        }
        return true;
    }

    private void changeDseeHx() {
        this.mTimer = new Timer(true);
        this.mTimer.schedule(new TimerTask() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.10
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                AudioEffectMenuFragment.this.mHandler.post(new Runnable() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Activity act = AudioEffectMenuFragment.this.getActivity();
                        if (act != null) {
                            AudioEffectMenuFragment.this.delayToSetEnableDseeHx();
                            AudioEffectMenuFragment.this.mService.setDseeHx(AudioEffectMenuFragment.this.mLocalDseeHxEnabled, false);
                            AudioEffectMenuFragment.this.mService.setEnabled(!AudioEffectMenuFragment.this.mLocalDseeHxEnabled);
                            Reporter.reportEvent(act.getApplicationContext(), ReportContents.EVENT_DSEE_HX, AudioEffectMenuFragment.this.mLocalDseeHxEnabled);
                            return;
                        }
                        Log.w(AudioEffectMenuFragment.LOG_TAG, "Cannot set DSEE-HX. Activity is null.");
                    }
                });
            }
        }, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayToSetEnableDseeHx() {
        this.mTimer = new Timer(true) { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.11
            @Override // java.util.Timer
            public void cancel() {
                AudioEffectMenuFragment.this.mDseeHxPref.setEnabled(true);
                super.cancel();
            }
        };
        this.mTimer.schedule(new TimerTask() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.12
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                AudioEffectMenuFragment.this.mHandler.post(new Runnable() { // from class: com.sonyericsson.soundenhancement.AudioEffectMenuFragment.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AudioEffectMenuFragment.this.mDseeHxPref.setEnabled(true);
                    }
                });
            }
        }, 2000L);
    }

    private void updateDseeHxPref() {
        if (this.mDseeHxPref != null) {
            boolean dseeEnabled = this.mService.getCurrentDseeHx();
            this.mDseeHxPref.setChecked(dseeEnabled);
        }
    }

    private void updateDolbyAudioPref() {
        if (this.mDolbyEffectPref != null) {
            boolean enabled = this.mService.getDolbyState() == 1;
            this.mDolbyEffectPref.setChecked(enabled);
            if (enabled) {
                int dolbyProfile = this.mService.getDolbyProfile();
                this.mDolbyEffectPref.setSummary(getDolbyProfileName(dolbyProfile));
            } else {
                this.mDolbyEffectPref.setSummary("");
            }
        }
    }
}
