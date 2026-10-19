package com.sonymobile.audioutil;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;
import com.sonymobile.audioutil.listener.AudioListener;
import com.sonymobile.audioutil.listener.EventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedAudioService extends Service {
    public static final String ACTION_AUDIO_SETTINGS_UPDATED_WITH_USER_SWTICH = "com.sonymobile.media.intent.action.ACTION_AUDIO_SETTINGS_UPDATED_WITH_USER_SWTICH";
    public static final String ACTION_USER_SWITCHED = "android.intent.action.USER_SWITCHED";
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 16777216;
    public static final String STREAM_DEVICES_CHANGED_ACTION = "android.media.STREAM_DEVICES_CHANGED_ACTION";
    private static final String TAG = ExtendedAudioService.class.getSimpleName();
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private AudioListener mAudioListener;
    private AudioManager mAudioManager;
    private Context mContext;
    private List<EventListener> mListeners;
    private BroadcastReceiver mReceiver = null;
    private BroadcastReceiver mHeadsetChangedReceiver = null;
    private CameraManager mCameraManager = null;
    private AudioServerStateCallback mServerStateCallback = new AudioServerStateCallback();
    private final String PACKAGE_NAME_KEY = "package_name";
    private final String MEDIA_VOLUME_KEY = "stream_music_volume";
    private final String WIRELESS_CHARGING_STATE_KEY = "wireless_charging";
    private final int INVALID_APP_ID = -1;
    private EventListener.Callback mEventCallback = new EventListener.Callback() { // from class: com.sonymobile.audioutil.ExtendedAudioService.1
        @Override // com.sonymobile.audioutil.listener.EventListener.Callback
        public void onEvent(String event) {
            LogUtil.d(LogUtil.LOG_TAG, ExtendedAudioService.TAG + ".EventCallback event =" + event);
            if (event.equals(AudioListener.EVENT_AUDIO_PLAYBACK_CALLBACK)) {
                ExtendedAudioService extendedAudioService = ExtendedAudioService.this;
                extendedAudioService.setParameters("package_name", extendedAudioService.mAudioListener.getPackageName(ExtendedAudioService.this.mAudioManager.isMusicActive(), ExtendedAudioService.this.mAudioListener.getForegroundPackage()));
            }
        }
    };
    private final CameraManager.AvailabilityCallback mAvailabilityCallback = new CameraManager.AvailabilityCallback() { // from class: com.sonymobile.audioutil.ExtendedAudioService.3
        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(String cameraId) {
            LogUtil.d(LogUtil.LOG_TAG, "onCameraAvailable(" + cameraId + ")");
            setCameraAvailable(cameraId, true);
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(String cameraId) {
            LogUtil.d(LogUtil.LOG_TAG, "onCameraUnavailable(" + cameraId + ")");
            setCameraAvailable(cameraId, false);
        }

        private void setCameraAvailable(String cameraId, boolean available) {
            if (available) {
                LogUtil.d(LogUtil.LOG_TAG, "camera_off=" + cameraId);
                ExtendedAudioService.this.mAudioManager.setParameters("camera_off=" + cameraId);
                return;
            }
            LogUtil.d(LogUtil.LOG_TAG, "camera_on=" + cameraId);
            ExtendedAudioService.this.mAudioManager.setParameters("camera_on=" + cameraId);
        }
    };

    private class AudioServerStateCallback extends AudioManager.AudioServerStateCallback {
        private AudioServerStateCallback() {
        }

        public void onAudioServerUp() {
            LogUtil.d(LogUtil.LOG_TAG, ExtendedAudioService.TAG + " notify onAudioServerUp");
            ExtendedAudioService.this.sendGameEnhancerAppId();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        this.mListeners = new ArrayList();
        this.mAudioManager = (AudioManager) getSystemService("audio");
        sendMediaVolume();
        registerIntentReceiver();
        Executor exec = getMainExecutor();
        this.mAudioManager.setAudioServerStateCallback(exec, this.mServerStateCallback);
        this.mCameraManager = (CameraManager) getSystemService("camera");
        CameraManager cameraManager = this.mCameraManager;
        if (cameraManager != null) {
            cameraManager.registerAvailabilityCallback(this.mAvailabilityCallback, (Handler) null);
        }
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".onCreate completed");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".onStartCommand : intent = " + intent);
        sendGameEnhancerAppId();
        if (this.mListeners.size() == 0) {
            LogUtil.d(LogUtil.LOG_TAG, TAG + ".registerListener create listeners.");
            this.mAudioListener = new AudioListener(this.mContext, this.mEventCallback);
            this.mListeners.add(this.mAudioListener);
        }
        for (EventListener listener : this.mListeners) {
            LogUtil.d(LogUtil.LOG_TAG, TAG + ".registerListener listener=" + listener.getClass().getName());
            listener.register();
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediaVolume() {
        int vol = this.mAudioManager.getStreamVolume(3);
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".sendMediaVolume : volume = " + vol);
        setParameters("stream_music_volume", String.valueOf(vol));
    }

    private void registerIntentReceiver() {
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".registerIntentReceiver: registerIntentReceiver = " + this.mReceiver);
        if (this.mReceiver == null) {
            this.mReceiver = new BroadcastReceiver() { // from class: com.sonymobile.audioutil.ExtendedAudioService.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent == null) {
                        return;
                    }
                    String action = intent.getAction();
                    LogUtil.d(LogUtil.LOG_TAG, ExtendedAudioService.TAG + ".registerIntentReceiver.onReceive : action = " + action);
                    if ((action.equals(ExtendedAudioService.VOLUME_CHANGED_ACTION) || action.equals(ExtendedAudioService.STREAM_DEVICES_CHANGED_ACTION)) && intent.getIntExtra(ExtendedAudioService.EXTRA_VOLUME_STREAM_TYPE, -1) == 3) {
                        ExtendedAudioService.this.sendMediaVolume();
                    }
                    if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        ExtendedAudioService.this.setParameters("wireless_charging", "off");
                    } else if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        int plugged = intent.getIntExtra("plugged", 0);
                        if (plugged == 4) {
                            ExtendedAudioService.this.setParameters("wireless_charging", "on");
                        } else {
                            ExtendedAudioService.this.setParameters("wireless_charging", "off");
                        }
                    }
                    if (action.equals(ExtendedAudioService.ACTION_USER_SWITCHED)) {
                        Intent extraIntent = new Intent(intent);
                        extraIntent.setAction(ExtendedAudioService.ACTION_AUDIO_SETTINGS_UPDATED_WITH_USER_SWTICH);
                        extraIntent.setFlags(ExtendedAudioService.FLAG_RECEIVER_INCLUDE_BACKGROUND);
                        context.sendBroadcastAsUser(extraIntent, UserHandle.CURRENT_OR_SELF);
                    }
                    if (action.equals("android.intent.action.HEADSET_PLUG")) {
                        Intent extraIntent2 = new Intent(intent);
                        extraIntent2.setAction("com.sonyericsson.system.intent.action.HEADSET_PLUG");
                        extraIntent2.setFlags(ExtendedAudioService.FLAG_RECEIVER_INCLUDE_BACKGROUND);
                        context.sendBroadcastAsUser(extraIntent2, UserHandle.CURRENT_OR_SELF);
                    }
                }
            };
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(VOLUME_CHANGED_ACTION);
        intentFilter.addAction(STREAM_DEVICES_CHANGED_ACTION);
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.USER_FOREGROUND");
        intentFilter.addAction("android.intent.action.USER_BACKGROUND");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction(ACTION_USER_SWITCHED);
        registerReceiver(this.mReceiver, intentFilter);
    }

    private void unregisterIntentReceiver() {
        BroadcastReceiver broadcastReceiver = this.mReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            this.mReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParameters(String key, String value) {
        this.mAudioManager.setParameters(key + "=" + value);
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterIntentReceiver();
        if (this.mAudioManager != null) {
            LogUtil.d(LogUtil.LOG_TAG, TAG + " unregistering audio server state callback");
            this.mAudioManager.clearAudioServerStateCallback();
            this.mAudioManager = null;
        }
        if (this.mCameraManager != null) {
            finalizeCameraAvailability();
        }
        this.mCameraManager = null;
        for (EventListener listener : this.mListeners) {
            LogUtil.d(LogUtil.LOG_TAG, TAG + ".unregisterListener Destroy listener=" + listener.getClass().getName());
            listener.unregister();
        }
        super.onDestroy();
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".onCreate completed");
    }

    private void finalizeCameraAvailability() {
        this.mCameraManager.unregisterAvailabilityCallback(this.mAvailabilityCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendGameEnhancerAppId() {
        int app_id = -1;
        try {
            ApplicationInfo appInfo = this.mContext.getPackageManager().getApplicationInfo("com.sonymobile.gameenhancer", 128);
            app_id = UserHandle.getAppId(appInfo.uid);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.d(LogUtil.LOG_TAG, "package not found");
        }
        LogUtil.d(LogUtil.LOG_TAG, "game_enhancer_app_id = " + app_id);
        this.mAudioManager.setParameters("game_enhancer_app_id=" + app_id);
    }
}
