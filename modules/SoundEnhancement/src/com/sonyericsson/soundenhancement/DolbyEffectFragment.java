package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.switchbar.SwitchBar;

/* JADX INFO: loaded from: classes2.dex */
public class DolbyEffectFragment extends PreferenceFragment implements ServiceConnection, AudioEffectService.DolbyEventListener {
    private static final String INTENT_ACTION_LAUNCH_DOLBY_APP = "com.dolby.LAUNCH_DS_APP";
    private static final String LOG_TAG = "SoundEnhancement";
    private PreferenceScreen mDolbyEffectAppStartPref;
    private ListView mListView;
    private AudioEffectService mService;
    private boolean mServiceConnected;
    private SwitchBar mEnabledSwitchBar = null;
    private Preference.OnPreferenceChangeListener mCheckBoxPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.sonyericsson.soundenhancement.DolbyEffectFragment.1
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean checked = ((Boolean) newValue).booleanValue();
            if (!checked) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                DolbyEffectFragment dolbyEffectFragment = DolbyEffectFragment.this;
                CheckBoxPreference checkbox = (CheckBoxPreference) dolbyEffectFragment.findPreference(dolbyEffectFragment.getPreferenceKeyFromProfile(i));
                if (preference == checkbox && checkbox != null) {
                    DolbyEffectFragment.this.mService.setDolbyProfile(i);
                    break;
                }
            }
            DolbyEffectFragment.this.updateListViewItems();
            return true;
        }
    };
    private Preference.OnPreferenceClickListener mOnPreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.sonyericsson.soundenhancement.DolbyEffectFragment.2
        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(Preference preference) {
            if (!DolbyEffectFragment.this.getActivity().isFinishing() && DolbyEffectFragment.this.mDolbyEffectAppStartPref != null && preference == DolbyEffectFragment.this.mDolbyEffectAppStartPref) {
                try {
                    Intent intent = new Intent(DolbyEffectFragment.INTENT_ACTION_LAUNCH_DOLBY_APP);
                    DolbyEffectFragment.this.startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    return true;
                }
            }
            return true;
        }
    };

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.mService = ((SoundEnhancementService.SoundEnhancementServiceBinder) service).getAudioEffectService();
        this.mService.setDolbyEventListener(this);
        updateListViewItems();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        this.mService = null;
    }

    @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectService.DolbyEventListener
    public void receiveEvents() {
        updateListViewItems();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startAndBindToService();
    }

    private void startAndBindToService() {
        Activity activity = getActivity();
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName cn = new ComponentName(activity, (Class<?>) SoundEnhancementService.class);
        serviceStartIntent.setComponent(cn);
        activity.startService(serviceStartIntent);
        this.mServiceConnected = activity.getApplicationContext().bindService(serviceStartIntent, this, 1);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.dolby_effect, viewGroup, false);
        this.mEnabledSwitchBar = (SwitchBar) contentView.findViewById(R.id.switch_bar);
        this.mEnabledSwitchBar.show();
        this.mEnabledSwitchBar.setFixedText(R.string.sound_enhance_strings_switch_use_dolby_atmos_txt);
        this.mEnabledSwitchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() { // from class: com.sonyericsson.soundenhancement.DolbyEffectFragment.3
            @Override // com.sonyericsson.soundenhancement.switchbar.SwitchBar.OnSwitchChangeListener
            public void onSwitchChanged(Switch switchView, boolean isChecked) {
                if (DolbyEffectFragment.this.mService != null) {
                    boolean enabled = DolbyEffectFragment.this.enableDolbyAudioEffect();
                    if (isChecked != enabled) {
                        DolbyEffectFragment.this.mService.swapDolbyState();
                        DolbyEffectFragment.this.updateListViewItems();
                    }
                }
            }
        });
        this.mListView = (ListView) contentView.findViewById(android.R.id.list);
        addPreferencesFromResource(R.xml.screen_dolby_atmos_desc);
        addPreferencesFromResource(R.xml.screen_divider);
        addPreferencesFromResource(R.xml.screen_dolby_profile);
        for (int i = 0; i < 4; i++) {
            String key = getPreferenceKeyFromProfile(i);
            CheckBoxPreference preference = (CheckBoxPreference) findPreference(key);
            preference.setOnPreferenceChangeListener(this.mCheckBoxPreferenceChangeListener);
        }
        addPreferencesFromResource(R.xml.screen_divider);
        addPreferencesFromResource(R.xml.screen_open_dolby_app);
        this.mDolbyEffectAppStartPref = (PreferenceScreen) findPreference(Constants.KEY_PREF_OPEN_DOLBY_APP);
        this.mDolbyEffectAppStartPref.setOnPreferenceClickListener(this.mOnPreferenceClickListener);
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
        updateListViewItems();
    }

    @Override // android.app.Fragment
    public void onPause() {
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

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean enableDolbyAudioEffect() {
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService == null) {
            return false;
        }
        boolean ret = audioEffectService.getDolbyState() == 1;
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListViewItems() {
        if (this.mServiceConnected && this.mService != null) {
            boolean enabled = enableDolbyAudioEffect();
            int profile = this.mService.getDolbyProfile();
            this.mEnabledSwitchBar.setCheckedInternal(enabled);
            int i = 0;
            while (i < 4) {
                String key = getPreferenceKeyFromProfile(i);
                CheckBoxPreference preference = (CheckBoxPreference) findPreference(key);
                preference.setChecked(profile == i);
                preference.setEnabled(enabled);
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPreferenceKeyFromProfile(int profile) {
        if (profile == 0) {
            return Constants.KEY_PREF_DOLBY_PROFILE_DYNAMIC;
        }
        if (profile == 1) {
            return Constants.KEY_PREF_DOLBY_PROFILE_MOVIE;
        }
        if (profile == 2) {
            return Constants.KEY_PREF_DOLBY_PROFILE_MUSIC;
        }
        if (profile != 3) {
            return "";
        }
        return Constants.KEY_PREF_DOLBY_PROFILE_CUSTOM;
    }
}
