package com.sonyericsson.soundenhancement;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.sonyericsson.soundenhancement.audioeffect.AccessorySetting;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectServiceImpl;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;
import com.sonyericsson.soundenhancement.audioeffect.DolbyEffectSettings;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementService extends Service {
    private static final String ACTION_AUDIO_CONFIG_SET_FOR_USER = "com.sonymobile.media.intent.action.ACTION_AUDIO_SETTINGS_UPDATED_WITH_USER_SWTICH";
    public static final String ACTION_CHANGE_NOISE_CANCELING_SETTING = "com.sonyericsson.soundenhancement.intent.action.CHANGE_NOISE_CANCELING_SETTING";
    public static final String ACTION_CONFIG_UPDATED = "com.sonyericsson.idd.CONFIG_UPDATED";
    protected static final String ACTION_EXTERNAL_STEREO_MIC_PLUG = "com.sonymobile.media.intent.action.ACTION_EXTERNAL_STEREO_MIC_PLUG";
    protected static final String ACTION_FIVE_POLE_ACCESSORY = "com.sonymobile.media.intent.action.FIVE_POLE_ACCESSORY_STATUS";
    protected static final String ACTION_NOISE_CANCEL_TOGGLE = "com.sonyericsson.soundenhancement.intent.action.NOISE_CANCELING_TOGGLE_CHANGE";
    public static final String EXTRA_ACTION = "com.sonyericsson.soundenhancement.EXTRA_ACTION";
    protected static final String EXTRA_NEXT_NOISE_CANCELING_STATE = "intent.extra.next_noise_canceling_state";
    private static final String LOG_TAG = "SoundEnhancement";
    private static final int NC_DELAY_TIME = 5000;
    private static final String NOTIFICATION_CHANNEL_ID_DEFAULT = "channel_id_default";
    private static final int NOTIFICATION_ID_NC = 1;
    private static final String NOTIFICATION_TAG = "soundenhancement_notification";
    private static final int PENDING_INTENT_ID_START_ACCESSORY_DIALOG = 1;
    private static final int PENDING_INTENT_ID_START_NOISE_CANCELING_DIALOG = 5;
    private static final int PENDING_INTENT_ID_START_SOUND_EFFECT_DIALOG = 2;
    private static final int PENDING_INTENT_ID_TOGGLE_NOISE_CANCELING = 3;
    private static final int PENDING_INTENT_ID_TOGGLE_NOISE_CANCELING_FOR_EXPANDED = 4;
    private static int sCurrent3Dot5Type = -1;
    private AccessorySetting mAccessorySetting;
    private boolean mIsSupported5PoleAccessory;
    private boolean mIsSupportedCAPlus;
    private boolean mIsSupportedStereoMic;
    private SoundEnhancementLocalise mLocalise;
    private NotificationManager mNotificationManager;
    private Timer mTimer;
    private Toast mToast;
    private final IBinder mBinder = new SoundEnhancementServiceBinder();
    private final AudioEffectService mAudioEffectService = new AudioEffectServiceImpl();

    public class SoundEnhancementServiceBinder extends Binder {
        public SoundEnhancementServiceBinder() {
        }

        public AudioEffectService getAudioEffectService() {
            return SoundEnhancementService.this.mAudioEffectService;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notification = createNotification(this);
            startForeground(1, notification);
        }
        this.mIsSupportedCAPlus = SoundEnhancementUtility.isSupportedClearAudioPlus();
        this.mAccessorySetting = new AccessorySetting(getApplicationContext());
        this.mIsSupported5PoleAccessory = this.mAccessorySetting.isSupportedFivePoleAccessory();
        this.mIsSupportedStereoMic = this.mAccessorySetting.isSupportedStereoMic();
        this.mNotificationManager = (NotificationManager) getSystemService(ReportContents.CALLER_NOTIFICATION);
        registerNotificationChannel(this.mNotificationManager, NOTIFICATION_CHANNEL_ID_DEFAULT);
        this.mLocalise = new SoundEnhancementLocalise();
        this.mAudioEffectService.createSettings(this);
        this.mAudioEffectService.createDolbyEffectSettings(this);
        if (this.mIsSupported5PoleAccessory) {
            this.mAccessorySetting.loadAncParameters();
        }
    }

    public static Notification createNotification(Context context) {
        Intent startIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, startIntent, 268435456);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(ReportContents.CALLER_NOTIFICATION);
        NotificationChannel channel = new NotificationChannel("channel_id_audio_effect", "SoundEnhancementEffect", 2);
        notificationManager.createNotificationChannel(channel);
        Notification notification = new Notification.Builder(context, "SoundEnhancementEffect").setChannelId("channel_id_audio_effect").setContentIntent(pendingIntent).setContentTitle("AudioEffect Notify").setContentText("audio effect setting").setAutoCancel(true).build();
        notification.tickerText = "SoundEnhancementEffect";
        return notification;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mAudioEffectService.releaseDolbyAudioEffect();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        int new3Dot5Type;
        if (intent == null) {
            return 2;
        }
        String exAction = intent.getStringExtra("com.sonyericsson.soundenhancement.EXTRA_ACTION");
        showDeviceDetectedDialog(intent);
        boolean canStopSelf = false;
        if (exAction != null) {
            if (exAction.equals("com.sonyericsson.system.intent.action.HEADSET_PLUG") || exAction.equals(ACTION_EXTERNAL_STEREO_MIC_PLUG) || exAction.equals(ACTION_FIVE_POLE_ACCESSORY) || exAction.equals(ACTION_CHANGE_NOISE_CANCELING_SETTING) || exAction.equals(ACTION_AUDIO_CONFIG_SET_FOR_USER)) {
                canStopSelf = true;
                if (this.mIsSupported5PoleAccessory && (sCurrent3Dot5Type != (new3Dot5Type = SoundEnhancementUtility.getAncDectectedType(this)) || exAction.equals(ACTION_CHANGE_NOISE_CANCELING_SETTING) || exAction.equals(ACTION_AUDIO_CONFIG_SET_FOR_USER))) {
                    controlNotification(new3Dot5Type, true);
                    sCurrent3Dot5Type = new3Dot5Type;
                }
                if (exAction.equals(ACTION_AUDIO_CONFIG_SET_FOR_USER) && SoundEnhancementUtility.isForeground()) {
                    reportAllEvents();
                    enableActivityComponent();
                }
            } else if (exAction.equals(ACTION_NOISE_CANCEL_TOGGLE)) {
                canStopSelf = true;
                changeNoiseCancelingState(intent);
            } else if (exAction.equals("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION")) {
                if (canCreateAudioEffect()) {
                    handleOpenAudioEffect(intent);
                } else {
                    handleCloseAudioEffect(intent);
                }
            } else if (exAction.equals("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION")) {
                canStopSelf = true;
                handleCloseAudioEffect(intent);
            } else if (exAction.equals(ACTION_CONFIG_UPDATED)) {
                canStopSelf = true;
                reportAllEvents();
            } else if (exAction.equals("dolby_dap_params_update")) {
                String event = intent.getStringExtra("event name");
                int value = intent.getIntExtra("Integer Value", 0);
                handleDolbyParamUpdate(event, value);
            }
        }
        if (this.mTimer == null && !hasRunningSessions() && canStopSelf) {
            stopSelf();
        }
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    protected void handleOpenAudioEffect(Intent intent) {
        String packageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        int audioSession = intent.getIntExtra("android.media.extra.AUDIO_SESSION", -1);
        int contentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", 0);
        if (packageName != null && packageName.length() > 0 && audioSession >= -1) {
            try {
                this.mAudioEffectService.releaseBackgroundAudioSession(packageName, audioSession);
                this.mAudioEffectService.updateAudioSession(packageName, audioSession, contentType);
            } catch (UnsupportedOperationException e) {
                Log.w(LOG_TAG, "Service.handleOpenAudioEffect: " + packageName + " can not control Equalizer cause of the restricted by another application.");
            }
        }
    }

    protected void handleCloseAudioEffect(Intent intent) {
        String packageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        if (packageName != null && packageName.length() > 0) {
            this.mAudioEffectService.resetAudioSession(packageName);
            this.mAudioEffectService.resumeActiveAudioSession();
            if (!hasRunningSessions()) {
                stopSelf();
            }
        }
    }

    private void controlNotification(int pluggedAccessory, boolean isChangeStateOfHPCnnection) {
        int resourceIcon;
        String actionTitle;
        String notificationTitle;
        int applicableType;
        Context context = getApplicationContext();
        if (pluggedAccessory != 2) {
            if (pluggedAccessory == 1) {
                if (!SoundEnhancementUtility.isForeground()) {
                    return;
                }
                Intent startIntent = new Intent(context, (Class<?>) StereoMicActivity.class);
                startIntent.setFlags(67108864);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, startIntent, 268435456);
                Notification.Builder builder = new Notification.Builder(this).setSmallIcon(R.drawable.sound_enhance_notification_microphone_icn).setContentTitle(context.getText(R.string.sound_enhance_strings_notification_stereo_mic_txt)).setContentText(context.getText(R.string.sound_enhance_strings_notification_stereo_mic_desc_txt)).setOngoing(false).setContentIntent(pendingIntent).setAutoCancel(false).setChannelId(NOTIFICATION_CHANNEL_ID_DEFAULT);
                Notification notification = builder.build();
                this.mNotificationManager.notify(NOTIFICATION_TAG, 1, notification);
                return;
            }
            this.mNotificationManager.cancel(NOTIFICATION_TAG, 1);
            return;
        }
        int type = this.mAccessorySetting.getAccessoryType();
        if (SoundEnhancementUtility.getSupportAutoHeadsetCompensation() && type != (applicableType = AccessorySetting.getApplicableAccessoryType(type))) {
            type = applicableType;
            this.mAccessorySetting.setAccessoryType(type);
        }
        if (!SoundEnhancementUtility.isForeground()) {
            return;
        }
        if (type == 0 || type == 1 || type == 2 || type == 3) {
            Intent startIntent2 = new Intent(context, (Class<?>) NoiseCancelingActivity.class);
            startIntent2.setFlags(67108864);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 5, startIntent2, 268435456);
            boolean state = this.mAccessorySetting.getNoiseCancelingState();
            Intent toggleIntent = SoundEnhancementUtility.createExplicitIntent(this, ACTION_NOISE_CANCEL_TOGGLE);
            toggleIntent.putExtra(EXTRA_NEXT_NOISE_CANCELING_STATE, !state);
            PendingIntent togglePendingIntent = PendingIntent.getBroadcast(context, 3, toggleIntent, 268435456);
            if (state) {
                resourceIcon = R.drawable.sound_enhance_stat_notify_noise_on_icn;
                actionTitle = context.getResources().getString(R.string.sound_enhance_strings_notification_nc_turn_off_txt);
                notificationTitle = context.getResources().getString(R.string.sound_enhance_strings_toast_notification_nc_on_simple_txt);
            } else {
                resourceIcon = R.drawable.sound_enhance_stat_notify_noise_off_icn;
                actionTitle = context.getResources().getString(R.string.sound_enhance_strings_notification_nc_turn_on_txt);
                notificationTitle = context.getResources().getString(R.string.sound_enhance_strings_toast_notification_nc_off_txt);
            }
            Notification.Action action = new Notification.Action.Builder((Icon) null, actionTitle, togglePendingIntent).build();
            Notification.Builder builder2 = new Notification.Builder(context).setSmallIcon(resourceIcon).setContentTitle(notificationTitle).setContentText(context.getString(R.string.sound_enhance_strings_notification_nc_desc_txt)).addAction(action).setAutoCancel(false).setOngoing(true).setContentIntent(pendingIntent2).setChannelId(NOTIFICATION_CHANNEL_ID_DEFAULT);
            Notification notification2 = builder2.build();
            this.mNotificationManager.notify(NOTIFICATION_TAG, 1, notification2);
            return;
        }
        this.mNotificationManager.cancel(NOTIFICATION_TAG, 1);
    }

    private void changeNoiseCancelingState(Intent intent) {
        int string;
        if (!this.mIsSupported5PoleAccessory) {
            return;
        }
        boolean state = this.mAccessorySetting.getNoiseCancelingState();
        boolean newState = intent.getBooleanExtra(EXTRA_NEXT_NOISE_CANCELING_STATE, !state);
        if (newState) {
            string = R.string.sound_enhance_strings_toast_notification_nc_on_simple_txt;
        } else {
            string = R.string.sound_enhance_strings_toast_notification_nc_off_txt;
        }
        if (newState != state) {
            this.mAccessorySetting.setNoiseCancelingState(newState);
            Context context = getApplicationContext();
            SoundEnhancementUtility.sendChangeNoiseCancelingSettingsAsAll(context);
            Toast toast = this.mToast;
            if (toast != null) {
                toast.cancel();
            }
            this.mToast = Toast.makeText(getApplicationContext(), string, 1);
            this.mToast.show();
            controlNotification(SoundEnhancementUtility.pluggedAccessory(this), false);
            if (!hasRunningSessions()) {
                Timer timer = this.mTimer;
                if (timer != null) {
                    timer.cancel();
                }
                this.mTimer = new Timer(false);
                this.mTimer.schedule(new TimerTask() { // from class: com.sonyericsson.soundenhancement.SoundEnhancementService.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        SoundEnhancementService.this.stopSelf();
                    }
                }, 5000L);
            }
        }
    }

    private boolean hasRunningSessions() {
        return this.mAudioEffectService.hasRunningSessions();
    }

    private void showDeviceDetectedDialog(Intent intent) {
        if (intent == null || intent.getAction() == null || !SoundEnhancementUtility.isCTACandidate(this)) {
            return;
        }
        boolean showDialog = false;
        String action = intent.getStringExtra("com.sonyericsson.soundenhancement.EXTRA_ACTION");
        if ("com.sonyericsson.system.intent.action.HEADSET_PLUG".equals(action)) {
            showDialog = intent.getIntExtra("state", 0) > 0;
        } else if (ACTION_EXTERNAL_STEREO_MIC_PLUG.equals(action)) {
            showDialog = intent.getIntExtra("state", 0) > 0;
        } else if (ACTION_FIVE_POLE_ACCESSORY.equals(action)) {
            showDialog = intent.getIntExtra("state", 0) > 0;
        }
        if (showDialog) {
            Intent i = new Intent(getApplicationContext(), (Class<?>) AlertDialogActivity.class);
            i.setFlags(335544320);
            i.putExtra(AlertDialogActivity.DIALOG_TYPE, 1);
            getApplicationContext().startActivity(i);
        }
    }

    private void reportAllEvents() {
        boolean sforceSurround;
        String ncEnvName;
        AudioEffectService service = this.mAudioEffectService;
        AccessorySetting setting = this.mAccessorySetting;
        SoundEnhancementPresetList presetList = new SoundEnhancementPresetList();
        boolean dseeHx = service.getCurrentDseeHx();
        Reporter.reportEventWithInit(this, ReportContents.EVENT_DSEE_HX, dseeHx);
        if (this.mIsSupportedCAPlus) {
            boolean clearAudio = service.getCurrentClearAudioPlus();
            Reporter.reportEventWithInit(this, ReportContents.EVENT_CLAER_AUDIO_PLUS, clearAudio);
        }
        boolean dynamicNormalizer = service.getCurrentDynamicNormalizer();
        Reporter.reportEventWithInit(this, ReportContents.EVENT_DYNAMIC_NORMALIZER, dynamicNormalizer);
        int equalizerPreset = service.getCurrentEqualizerPreset();
        String[] equalizerList = AudioEffectUtilities.getEqualizerPresets();
        int[] bandLevels = service.getBandLevels();
        String[] equalizer = {equalizerList[equalizerPreset], AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[0]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[1]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[2]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[3]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[4]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[5])};
        Reporter.reportEventWithInit(this, ReportContents.EVENT_EQUALIZER, equalizer);
        presetList.queryAvailableSurroundPresets(AudioEffectUtilities.getSurroundPresetNames());
        String[] surroundNames = presetList.getSurroundPresetNames();
        int surroundPreset = service.getCurrentSurroundPreset();
        int surroundIndex = presetList.getUiListIndexFromSurroundPreset(surroundPreset);
        Reporter.reportEventWithInit(this, ReportContents.EVENT_SURROUND, surroundNames[surroundIndex]);
        if (getResources().getBoolean(R.bool.enabled_speaker_bundle)) {
            sforceSurround = service.getCurrentSpeakerBundle();
        } else {
            sforceSurround = service.getCurrentSForceSurround();
        }
        Reporter.reportEventWithInit(this, ReportContents.EVENT_SFORCE, sforceSurround);
        boolean ahc = setting.getAutoHeadsetCompensation();
        setting.setAhcStateWithAudioManager(ahc);
        Reporter.reportEventWithInit(this, ReportContents.EVENT_AUTO_OPTIMIZATION, ahc);
        if (this.mIsSupported5PoleAccessory) {
            boolean ncState = setting.getNoiseCancelingState();
            Reporter.reportEventWithInit(this, ReportContents.EVENT_NOISE_CANCELING, ncState);
            int acType = setting.getAccessoryType();
            String ncName = new NoiseCancelingHeadphoneInfoManager().getNcHeadphoneName(AccessorySetting.getApplicableAccessoryType(acType));
            Reporter.reportEventWithInit(this, ReportContents.EVENT_NC_HEADPHONE_TYPE, ncName);
            String[] ncEnvNames = setting.getNoiseCancelingEnvironmentNames();
            if (ncEnvNames == null) {
                ncEnvName = AccessorySetting.PRESET_NAME_NC_BUS_TRAIN;
            } else {
                presetList.queryAvailableNoiseCancelingEnvironment(ncEnvNames);
                int ncEnvState = setting.getNoiseCancelingEnvironment();
                String[] ncEnvOrderedNames = presetList.getNoiseCancelingEnvironmentNames();
                String ncEnvName2 = ncEnvOrderedNames[ncEnvState];
                ncEnvName = ncEnvName2;
            }
            Reporter.reportEventWithInit(this, ReportContents.EVENT_NC_ENVIRONMENT, ncEnvName);
            boolean ncPowerSave = setting.getNoiseCancelingPowerSaveMode();
            Reporter.reportEventWithInit(this, ReportContents.EVENT_NC_POWER_SAVE, ncPowerSave);
        }
        if (this.mIsSupportedStereoMic) {
            String micSensitivity = setting.getStereoMicGain();
            Reporter.reportEventWithInit(this, ReportContents.EVENT_MIC_SENSITIVITY, micSensitivity);
        }
    }

    private void registerNotificationChannel(NotificationManager manager, String channelId) {
        if (manager == null || channelId == null) {
            return;
        }
        CharSequence name = null;
        int importance = 0;
        if (channelId.equals(NOTIFICATION_CHANNEL_ID_DEFAULT)) {
            name = getString(R.string.sound_enhance_strings_notification_category_audio_accessory_txt);
            importance = 2;
        }
        if (name != null) {
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setShowBadge(false);
            manager.createNotificationChannel(channel);
        }
    }

    private boolean canCreateAudioEffect() {
        return (SoundEnhancementUtility.isCTACandidate(this) && SoundEnhancementUtility.enabledExternalAudioEffectApp(this)) ? false : true;
    }

    private void handleDolbyParamUpdate(String event, int value) {
        if (TextUtils.isEmpty(event)) {
            return;
        }
        if (event.equals(DolbyEffectSettings.EVENT_DOLBY_CLIENT_NUM)) {
            boolean existed = value > 0;
            this.mAudioEffectService.setDolbyDecClientExist(existed);
            if (SoundEnhancementUtility.isEnabledDolbyAutoMode()) {
                boolean enabled = this.mAudioEffectService.getDolbyState() == 1;
                SharedPreferences sp = PreferenceUtil.getDolbyPreferences(this);
                boolean autoEnable = sp.getBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_AUTO_ENABLE, false);
                if (existed) {
                    if (!enabled && !autoEnable) {
                        this.mAudioEffectService.swapDolbyState();
                        sp.edit().putBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_AUTO_ENABLE, true).apply();
                        return;
                    }
                    return;
                }
                if (enabled && autoEnable) {
                    this.mAudioEffectService.swapDolbyState();
                }
                sp.edit().putBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_AUTO_ENABLE, false).apply();
                return;
            }
            return;
        }
        if (event.equals("ds_state_change")) {
            boolean enable = this.mAudioEffectService.getDolbyState() == 1;
            this.mAudioEffectService.setDAPStatus(enable);
            this.mAudioEffectService.updateDolbyUI();
            SharedPreferences sp2 = PreferenceUtil.getDolbyPreferences(this);
            SharedPreferences.Editor editor = sp2.edit();
            editor.putBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_STATE_CHANGED, enable).apply();
            if (SoundEnhancementUtility.isEnabledDolbyAutoMode()) {
                boolean autoEnable2 = sp2.getBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_AUTO_ENABLE, false);
                if (!enable && autoEnable2) {
                    sp2.edit().putBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_AUTO_ENABLE, false).apply();
                }
            }
        }
    }

    private void enableActivityComponent() {
        boolean supportedDolby = SoundEnhancementUtility.isSupportedDolbyEffect();
        String packageName = getPackageName();
        getPackageManager().setComponentEnabledSetting(new ComponentName(packageName, packageName + ".AudioEffectMenuForDolbyAudioActivity"), supportedDolby ? 1 : 2, 1);
    }

    private void enableActivityComponentOld() {
        int i;
        boolean supportedDolby = SoundEnhancementUtility.isSupportedDolbyEffect();
        String packageName = getPackageName();
        PackageManager packageManager = getPackageManager();
        ComponentName componentName = new ComponentName(packageName, packageName + ".AudioEffectMenuForAudioActivity");
        if (supportedDolby) {
            i = 2;
        } else {
            i = 1;
        }
        packageManager.setComponentEnabledSetting(componentName, i, 1);
        getPackageManager().setComponentEnabledSetting(new ComponentName(packageName, packageName + ".AudioEffectMenuForDolbyAudioActivity"), supportedDolby ? 1 : 2, 1);
    }
}
