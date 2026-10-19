package com.sonyericsson.soundenhancement;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.sonyericsson.soundenhancement.ahc.Logger;
import com.sonyericsson.soundenhancement.reflect.AudioManagerWrapper;
import com.sonyericsson.soundenhancement.reflect.UserHandleWrapper;
import com.sonymobile.getmore.api.ContributionContract;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class AutomaticHeadphoneService extends Service {
    public static final String ACTION_AHC_USER_SWAP = "com.sonyericsson.soundenhancement.AHC_USER_SWAP";
    public static final String ACTION_ENABLE_AHC = "com.sonyericsson.soundenhancement.ACTION_ENABLE_AHC";
    protected static final String ACTION_EXTERNAL_STEREO_MIC_PLUG = "com.sonymobile.media.intent.action.ACTION_EXTERNAL_STEREO_MIC_PLUG";
    protected static final String ACTION_FIVE_POLE_ACCESSORY = "com.sonymobile.media.intent.action.FIVE_POLE_ACCESSORY_STATUS";
    public static final String AUDIO_FOCUS_CHANGED_ACTION = "com.sonymobile.media.AUDIO_FOCUS_CHANGED_ACTION";
    public static final String AUDIO_MODE_CHANGED_ACTION = "com.sonymobile.media.AUDIO_MODE_CHANGED_ACTION";
    public static final String EXTRA_ACTION = "com.sonyericsson.soundenhancement.EXTRA_ACTION";
    public static final String EXTRA_AUDIO_FOCUS = "com.sonymobile.media.extra.EXTRA_AUDIO_FOCUS";
    public static final String EXTRA_AUDIO_MODE = "com.sonymobile.media.extra.EXTRA_AUDIO_MODE";
    private static final int HS_HI_UNSUPPORTED = 3;
    private static final int HS_NC = 1;
    private static final int HS_NORMAL = 0;
    private static final int HS_STEREO_MIC = 2;
    private static final int IDLE = 1;
    private static final int MAX_FAILED = 3;
    private static final int MAX_REC_ERRORS = 3;
    private static final int MAX_RETRIES = 3;
    private static final int MSG_ANALYZE_FINISHED = 2;
    private static final int MSG_POLL_FOR_MUSIC = 3;
    private static final int MSG_RESTART_ANALYZE = 1;
    private static final int MSG_USER_SWAP = 4;
    private static final int MUSIC_INDEX = 8;
    private static final int NO_MUSIC_SCREEN_OFF = 5;
    private static final int NO_MUSIC_SCREEN_ON = 4;
    private static final int PLAYING = 2;
    private static final int RECORDING_SCREEN_OFF = 3;
    private static final int RECORDING_SCREEN_ON = 2;
    private static final int TIME_FOR_POLL_MUSIC_FIRST_MS = 1000;
    private static final int TIME_FOR_POLL_MUSIC_MS = 5000;
    private static final int TIME_FOR_RESTART_MS = 600000;
    private static final int TIME_FOR_RETRY_AFTER_REC_ERROR_MS = 60000;
    private static final int TIME_FOR_USER_SWAP = 2000;
    private int mPlayingState;
    private int mRecordingState;
    private SharedPreferences mSharedPreferences;
    private AutomaticHeadphoneWorker mWorker;
    private BindServiceListener mListener = null;
    private final IBinder mBinder = new AutomaticHeadsetBinder();
    private AudioManager mAudioManager = null;
    private boolean mReceiverRegistered = false;
    private boolean mHeadsetConnected = false;
    private int mHsTypeConnected = 0;
    private boolean mAhcEnabled = false;
    private int mRetryCount = 0;
    private int mFailedCount = 0;
    private int mRecErrorCount = 0;
    private int mMode = 0;
    private int mFocusStreams = 0;
    private boolean mScreenOn = true;
    private float[] mSavedFilter = null;
    private float[] mNCFilterAhcOff = null;
    private boolean mImpedanceAnalyzed = false;
    private boolean mServiceFirstRun = false;
    private boolean mIsForeground = true;
    private boolean mEnabledDuringFirstRun = false;
    private boolean mServiceBusy = false;
    private boolean mIsEnabledDolby = false;
    private Object mLock = new Object();
    private IncomingHandler mHandler = new IncomingHandler(this);
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Logger.LOGD("onReceive: " + action);
            synchronized (AutomaticHeadphoneService.this.mLock) {
                if (action.equals(AutomaticHeadphoneService.AUDIO_MODE_CHANGED_ACTION)) {
                    int newMode = intent.getIntExtra(AutomaticHeadphoneService.EXTRA_AUDIO_MODE, -2);
                    AutomaticHeadphoneService.this.onModeChanged(newMode);
                } else if (action.equals(AutomaticHeadphoneService.AUDIO_FOCUS_CHANGED_ACTION)) {
                    int newFocus = intent.getIntExtra(AutomaticHeadphoneService.EXTRA_AUDIO_FOCUS, -1);
                    AutomaticHeadphoneService.this.onFocusChanged(newFocus);
                } else if (action.equals("android.intent.action.SCREEN_ON")) {
                    AutomaticHeadphoneService.this.onScreenOn();
                } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                    AutomaticHeadphoneService.this.onScreenOff();
                } else if (action.equals(AutomaticHeadphoneService.ACTION_FIVE_POLE_ACCESSORY)) {
                    AutomaticHeadphoneService.this.onHsConnected();
                    int state = intent.getIntExtra("state", -1);
                    if (state == 1) {
                        AutomaticHeadphoneService.this.mHsTypeConnected = 1;
                        if (!AutomaticHeadphoneService.this.mAhcEnabled) {
                            AutomaticHeadphoneService.this.mWorker.sendFilterParams("onReceive: ", AutomaticHeadphoneService.this.mNCFilterAhcOff);
                        }
                    }
                } else if (action.equals(AutomaticHeadphoneService.ACTION_EXTERNAL_STEREO_MIC_PLUG)) {
                    int state2 = intent.getIntExtra("state", -1);
                    if ((AutomaticHeadphoneService.this.mAhcEnabled || AutomaticHeadphoneService.this.mServiceFirstRun) && state2 == 1) {
                        AutomaticHeadphoneService.this.stopRecording();
                        AutomaticHeadphoneService.this.mHsTypeConnected = 2;
                    }
                } else if (action.equals(AutomaticHeadphoneService.ACTION_ENABLE_AHC)) {
                    int enable = intent.getIntExtra("enable", -1);
                    if (enable == 0) {
                        AutomaticHeadphoneService.this.onIntentEnableAhc(false);
                    } else if (enable == 1) {
                        AutomaticHeadphoneService.this.onIntentEnableAhc(true);
                    } else {
                        Logger.LOGE("onReceive: Invalid enable: " + enable);
                    }
                } else if (action.equals("android.intent.action.USER_BACKGROUND")) {
                    AutomaticHeadphoneService.this.onForegroundChange(false);
                } else if (action.equals("android.intent.action.USER_FOREGROUND")) {
                    AutomaticHeadphoneService.this.onForegroundChange(true);
                } else if (action.equals("dolby_dap_params_update")) {
                    String event = intent.getStringExtra("event name");
                    int value = intent.getIntExtra("Integer Value", 0);
                    AutomaticHeadphoneService.this.handleDolbyParamUpdate(event, value);
                }
            }
        }
    };
    private final AutomaticHeadphoneWorker.AnalyzeCallback mCallback = new AutomaticHeadphoneWorker.AnalyzeCallback() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneService.2
        @Override // com.sonyericsson.soundenhancement.AutomaticHeadphoneWorker.AnalyzeCallback
        public void analyzeFinished(int result) {
            AutomaticHeadphoneService.this.mRecordingState = 1;
            Message.obtain(AutomaticHeadphoneService.this.mHandler, 2, result, 0).sendToTarget();
        }
    };

    public interface BindServiceListener {
        void setFeatureStatus(boolean z);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mSharedPreferences = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this, com.sonyericsson.soundenhancement.ahc.Constants.PREF_AHC, 4);
        this.mAudioManager = (AudioManager) getSystemService("audio");
        this.mRecordingState = 1;
        this.mPlayingState = 1;
        boolean foreground = SoundEnhancementUtility.isForeground();
        initService(foreground);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            stopSelf();
            return 2;
        }
        String action = intent.getStringExtra("com.sonyericsson.soundenhancement.EXTRA_ACTION");
        Logger.LOGD("Service has started: " + action);
        if (action != null) {
            if (action.equals("com.sonyericsson.system.intent.action.HEADSET_PLUG")) {
                int state = intent.getIntExtra("state", -1);
                if (state != 0) {
                    if (state == 1) {
                        synchronized (this.mLock) {
                            reportServiceBusy(false);
                            boolean foreground = SoundEnhancementUtility.isForeground();
                            initService(foreground);
                            onForegroundChange(foreground);
                            onModeChanged(this.mAudioManager.getMode());
                            onFocusChanged(AudioManagerWrapper.getInstance().getAudioFocusState(this.mAudioManager));
                            onHsConnected();
                        }
                    }
                } else {
                    synchronized (this.mLock) {
                        reportServiceBusy(false);
                        onHsDisconnected(this);
                        if (this.mRecordingState != 1) {
                            this.mWorker.stop();
                            this.mRecordingState = 1;
                        }
                    }
                    stopSelf();
                }
            } else if (action.equals(ACTION_AHC_USER_SWAP)) {
                synchronized (this.mLock) {
                    boolean foreground2 = SoundEnhancementUtility.isForeground();
                    Logger.LOGD("ACTION_AHC_USER_SWAP received. foreground=" + foreground2);
                    if (foreground2) {
                        reportServiceBusy(false);
                        initService(foreground2);
                        onForegroundChange(foreground2);
                        onModeChanged(this.mAudioManager.getMode());
                        onFocusChanged(AudioManagerWrapper.getInstance().getAudioFocusState(this.mAudioManager));
                        if (this.mAudioManager.isWiredHeadsetOn()) {
                            onHsConnected();
                        } else {
                            onHsDisconnected(this);
                        }
                    }
                }
                if (!this.mHeadsetConnected) {
                    stopSelf();
                }
            }
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.mReceiverRegistered) {
            unregisterReceiver(this.mBroadcastReceiver);
            this.mReceiverRegistered = false;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent arg0) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.mListener = null;
        return false;
    }

    public class AutomaticHeadsetBinder extends Binder {
        public AutomaticHeadsetBinder() {
        }

        AutomaticHeadphoneService getService() {
            return AutomaticHeadphoneService.this;
        }

        public void setListener(BindServiceListener listener) {
            AutomaticHeadphoneService.this.mListener = listener;
        }
    }

    public boolean isHeadsetConnected() {
        return this.mHeadsetConnected;
    }

    public boolean isServiceBusy() {
        return this.mServiceBusy;
    }

    public void changeState(boolean enabled) {
        synchronized (this.mLock) {
            if (this.mServiceFirstRun) {
                setServiceFirstRun(false);
                this.mEnabledDuringFirstRun = true;
            }
            setContributionContract(enabled);
            changeStateInternal(enabled);
        }
    }

    public boolean isImpedanceAnalyzing() {
        int i = this.mRecordingState;
        if ((i != 2 && i != 3) || this.mImpedanceAnalyzed) {
            return false;
        }
        return true;
    }

    private void changeStateInternal(boolean enabled) {
        if (!this.mIsForeground) {
            Logger.LOGD("changeStateInternal: Not in foreground");
        } else {
            onAhcEnabled(enabled);
        }
    }

    private void initService(boolean foreground) {
        if (this.mWorker == null) {
            this.mWorker = new AutomaticHeadphoneWorker(this.mAudioManager);
            this.mWorker.setFilePath(getFilesDir().getAbsolutePath());
            IntentFilter filter = new IntentFilter();
            filter.addAction(AUDIO_MODE_CHANGED_ACTION);
            filter.addAction(AUDIO_FOCUS_CHANGED_ACTION);
            filter.addAction("android.intent.action.SCREEN_ON");
            filter.addAction("android.intent.action.SCREEN_OFF");
            filter.addAction(ACTION_FIVE_POLE_ACCESSORY);
            filter.addAction(ACTION_EXTERNAL_STEREO_MIC_PLUG);
            filter.addAction(ACTION_ENABLE_AHC);
            filter.addAction("android.intent.action.USER_BACKGROUND");
            filter.addAction("android.intent.action.USER_FOREGROUND");
            if (SoundEnhancementUtility.isSupportedDolbyEffect()) {
                filter.addAction("dolby_dap_params_update");
                SharedPreferences sp = PreferenceUtil.getDolbyPreferences(this);
                this.mIsEnabledDolby = sp.getBoolean(PreferenceUtil.KEY_DOLBY_EFFECT_STATE_CHANGED, false);
            }
            registerReceiver(this.mBroadcastReceiver, filter);
            this.mReceiverRegistered = true;
            setupSharedPreferences(foreground);
            this.mNCFilterAhcOff = new float[]{0.998257f, -1.978034f, 0.979947f, -1.977999f, 0.978239f, 0.996517f, -1.956143f, 0.960297f, -1.956005f, 0.956952f, 0.974047f, 1.080441f, 0.387819f, 1.063272f, 0.379036f, 0.999477f, -1.993403f, 0.993941f, -1.9934f, 0.993421f, 0.281838f, 0.0f, 0.0f, 0.0f, 0.0f, 0.999128f, -1.989008f, 0.989923f, -1.988999f, 0.98906f, 0.998257f, -1.978034f, 0.979947f, -1.977999f, 0.978239f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.999738f, -1.996701f, 0.996966f, -1.9967f, 0.996705f, 0.281838f, 0.0f, 0.0f, 0.0f, 0.0f, 0.999564f, -1.994502f, 0.994949f, -1.9945f, 0.994515f, 0.999128f, -1.989008f, 0.989923f, -1.988999f, 0.98906f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.999869f, -1.99835f, 0.998482f, -1.99835f, 0.998351f, 0.281838f, 0.0f, 0.0f, 0.0f, 0.0f};
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHsConnected() {
        if (this.mHeadsetConnected) {
            return;
        }
        Logger.LOGD("Headset connected");
        this.mHeadsetConnected = true;
        this.mRetryCount = 0;
        this.mFailedCount = 0;
        this.mRecErrorCount = 0;
        if (!this.mIsForeground) {
            Logger.LOGD("onHsConnected: Not in foreground");
            return;
        }
        if (this.mAhcEnabled || this.mServiceFirstRun) {
            if (this.mImpedanceAnalyzed && this.mAhcEnabled) {
                Logger.LOGD("onHsConnected: sendFilterParams");
                setSavedFilter(true);
                this.mWorker.sendFilterParams("onHsConnected: ", this.mSavedFilter);
            } else {
                Logger.LOGD("onHsConnected: No headset analyzed");
                onClearFilter();
            }
            if (this.mPlayingState == 2) {
                if (!startRecording()) {
                    Logger.LOGE("onHsConnected: Could not start filter creation");
                    return;
                }
                int i = this.mRecordingState;
                if (i == 2 || i == 3) {
                    Logger.LOGD("onHsConnected: Filter creation started");
                    return;
                }
                Logger.LOGD("onHsConnected: Waiting for music to start");
                return;
            }
            return;
        }
        onClearFilter();
    }

    private void onHsDisconnected(Context context) {
        if (!this.mHeadsetConnected) {
            return;
        }
        Logger.LOGD("Headset disconnected");
        this.mHeadsetConnected = false;
        setImpedanceAnalyzed(false);
        if (!this.mIsForeground) {
            Logger.LOGD("onHsDisconnected: Not in foreground");
            return;
        }
        if (this.mAhcEnabled || this.mServiceFirstRun) {
            stopRecording();
        }
        if (this.mHsTypeConnected != 0) {
            this.mHsTypeConnected = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onModeChanged(int newMode) {
        if (newMode == -2 || newMode == this.mMode) {
            return;
        }
        Logger.LOGD("onModeChanged: New audio mode " + newMode + " mRecordingState " + this.mRecordingState);
        this.mMode = newMode;
        if (!this.mIsForeground) {
            Logger.LOGD("onModeChanged: Not in foreground");
            return;
        }
        if (newMode != 0) {
            stopRecording();
            return;
        }
        if ((this.mAhcEnabled || this.mServiceFirstRun) && this.mHeadsetConnected && this.mPlayingState == 2 && this.mAudioManager.isMusicActive()) {
            startRecording();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFocusChanged(int newFocus) {
        if (newFocus == -1 || newFocus == this.mFocusStreams) {
            return;
        }
        Logger.LOGD("onFocusChanged: newFocus " + newFocus + " mMode " + this.mMode);
        this.mFocusStreams = newFocus;
        if (!this.mIsForeground) {
            Logger.LOGD("onFocusChanged: Not in foreground");
            return;
        }
        boolean musicActive = (newFocus & 8) != 0;
        if (musicActive && this.mPlayingState != 2 && this.mMode == 0) {
            onAudioStarted();
        } else if (!musicActive && this.mPlayingState == 2) {
            onAudioStopped();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScreenOn() {
        Logger.LOGD("onScreenOn: mRecordingState " + this.mRecordingState);
        this.mScreenOn = true;
        if (!this.mIsForeground) {
            Logger.LOGD("onScreenOn: Not in foreground");
            return;
        }
        int i = this.mRecordingState;
        if (i == 3) {
            this.mRecordingState = 2;
        } else if (i == 5) {
            this.mRecordingState = 4;
            onPollForMusic();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScreenOff() {
        Logger.LOGD("onScreenOff: mRecordingState " + this.mRecordingState);
        this.mScreenOn = false;
        if (!this.mIsForeground) {
            Logger.LOGD("onScreenOff: Not in foreground");
            return;
        }
        int i = this.mRecordingState;
        if (i == 2) {
            this.mRecordingState = 3;
        } else if (i == 4) {
            this.mRecordingState = 5;
            this.mHandler.removeMessages(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onIntentEnableAhc(boolean enable) {
        this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.PREF_STATUS, enable).commit();
        reportServiceBusy(this.mServiceBusy);
        changeStateInternal(enable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onForegroundChange(boolean foreground) {
        Logger.LOGD("onForegroundChange: foreground " + foreground + " mIsForeground " + this.mIsForeground);
        if (this.mIsForeground == foreground) {
            return;
        }
        this.mIsForeground = foreground;
        if (this.mIsForeground) {
            if (this.mImpedanceAnalyzed && this.mAhcEnabled) {
                Logger.LOGD("onForegroundChange: sendFilterParams");
                this.mWorker.sendFilterParams("onForegroundChange: ", this.mSavedFilter);
                return;
            }
            Logger.LOGD("onForegroundChange: No headset analyzed");
            onClearFilter();
            if ((this.mAhcEnabled || this.mServiceFirstRun) && this.mHeadsetConnected && this.mPlayingState == 2 && this.mAudioManager.isMusicActive() && this.mMode == 0) {
                startRecording();
                return;
            }
            return;
        }
        stopRecording();
        this.mHandler.sendEmptyMessageDelayed(4, 2000L);
    }

    private void onAhcEnabled(boolean ahcEnabled) {
        Logger.LOGD("onAhcEnabled: ahcEnabled " + ahcEnabled);
        setAhcEnabled(ahcEnabled);
        this.mRetryCount = 0;
        this.mFailedCount = 0;
        this.mRecErrorCount = 0;
        if (this.mHeadsetConnected) {
            if (this.mAhcEnabled) {
                int i = this.mHsTypeConnected;
                if (i == 0 || i == 1) {
                    if (this.mImpedanceAnalyzed) {
                        setSavedFilter(true);
                        this.mWorker.sendFilterParams("onAhcEnabled: ", this.mSavedFilter);
                        return;
                    } else {
                        if (this.mPlayingState == 2 && startRecording()) {
                            Logger.LOGD("onAhcEnabled: Filter creation started");
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            int i2 = this.mHsTypeConnected;
            if (i2 == 0 || i2 == 1) {
                stopRecording();
            }
            int i3 = this.mHsTypeConnected;
            if (i3 == 0) {
                onClearFilter();
            } else if (i3 == 1) {
                this.mWorker.sendFilterParams("onAhcEnabled: ", this.mNCFilterAhcOff);
            }
        }
    }

    private void onClearFilter() {
        Logger.LOGD("onClearFilter");
        this.mWorker.clearFilter();
    }

    private void onAudioStarted() {
        Logger.LOGD("onAudioStarted: Audio session opened");
        this.mPlayingState = 2;
        if ((this.mAhcEnabled || this.mServiceFirstRun) && this.mHeadsetConnected && !this.mImpedanceAnalyzed && this.mIsForeground && startRecording()) {
            Logger.LOGD("onAudioStarted: Filter creation started");
        }
    }

    private void onAudioStopped() {
        Logger.LOGD("onAudioStopped: Audio session closed");
        stopRecording();
        this.mPlayingState = 1;
    }

    private void onAnalyzeFinished(int result) {
        if (result == 0) {
            Logger.LOGD("onAnalyzeFinished: HSCOMP_RESULT_SAME_HS_DETECTED");
            this.mRetryCount = 0;
            this.mFailedCount = 0;
            this.mRecErrorCount = 0;
            Logger.LOGI("onAnalyzeFinished: Similar headset connected. Using stored filter");
            setImpedanceAnalyzed(true);
            setSavedFilter(true);
            this.mWorker.sendFilterParams("onAnalyzeFinished: ", this.mSavedFilter);
            reportServiceBusy(false);
            return;
        }
        if (result == 1) {
            Logger.LOGD("onAnalyzeFinished: HSCOMP_RESULT_INSUFFICIENT_DATA");
            onInsufficientData();
            return;
        }
        if (result == 2) {
            Logger.LOGD("onAnalyzeFinished: HSCOMP_RESULT_NO_FILTER");
            onNoFilter();
            return;
        }
        if (result == 4) {
            Logger.LOGI("onAnalyzeFinished: HSCOMP_RESULT_NEW_HS_DETECTED");
            onNewHsDetected();
            return;
        }
        if (result == 5) {
            Logger.LOGD("onAnalyzeFinished: HSCOMP_RESULT_MUSIC_STOPPED");
            onMusicStopped();
            return;
        }
        if (result == -2) {
            Logger.LOGI("onAnalyzeFinished: HSCOMP_RESULT_REC_ERROR");
            onRecError();
            return;
        }
        if (result == 3) {
            Logger.LOGI("onAnalyzeFinished: High impedance headset detected");
        } else {
            Logger.LOGI("onAnalyzeFinished: Filter creation failed");
        }
        this.mRetryCount = 0;
        this.mFailedCount = 0;
        this.mRecErrorCount = 0;
        this.mHsTypeConnected = 3;
        onClearFilter();
        reportServiceBusy(false);
    }

    private void setAsDefaultFilter() {
        this.mSavedFilter = this.mWorker.getCurrentFilter();
        if (this.mSavedFilter != null) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < this.mWorker.getFilterSize(); i++) {
                str.append(this.mSavedFilter[i]);
                str.append(",");
            }
            Logger.LOGD("setAsDefaultFilter: " + str.toString());
            boolean result = this.mSharedPreferences.edit().putString(com.sonyericsson.soundenhancement.ahc.Constants.SAVED_FILTER, str.toString()).commit();
            if (!result) {
                Logger.LOGE("setAsDefaultFilter: Commit " + com.sonyericsson.soundenhancement.ahc.Constants.SAVED_FILTER + " failed");
            }
        }
    }

    private void setSavedFilter(boolean foreground) {
        int currentSize = this.mWorker.getFilterSize();
        String[] savedFilter = this.mSharedPreferences.getString(com.sonyericsson.soundenhancement.ahc.Constants.SAVED_FILTER, "").split(",");
        int storedSize = savedFilter.length;
        this.mSavedFilter = new float[storedSize];
        if (storedSize != currentSize) {
            Logger.LOGI("setSavedFilter: Stored filter contains " + storedSize + " biQuads while current filter conteins " + currentSize + "biQuads");
        }
        for (int i = 0; i < storedSize; i++) {
            this.mSavedFilter[i] = Float.parseFloat(savedFilter[i]);
        }
        if (foreground && this.mAhcEnabled) {
            this.mWorker.sendFilterParams("setSavedFilter: ", this.mSavedFilter);
        }
    }

    private void onInsufficientData() {
        if (!this.mAhcEnabled && !this.mServiceFirstRun) {
            Logger.LOGD("onInsufficientData: AHC disabled");
            reportServiceBusy(false);
            return;
        }
        if (this.mRetryCount < 3) {
            if (startRecording()) {
                Logger.LOGD("onInsufficientData: Insufficient data. Retrying");
                this.mRetryCount = this.mRetryCount + 1;
                return;
            }
            Logger.LOGE("onInsufficientData: Could not retry analyze");
            this.mRetryCount = 0;
            reportServiceBusy(false);
            return;
        }
        Logger.LOGD("onInsufficientData: Max retries reached");
        this.mRetryCount = 0;
        this.mHandler.sendEmptyMessageDelayed(1, 600000L);
        reportServiceBusy(false);
    }

    private void onNoFilter() {
        if (!this.mAhcEnabled && !this.mServiceFirstRun) {
            Logger.LOGD("onNoFilter: AHC disabled");
            reportServiceBusy(false);
            return;
        }
        if (this.mFailedCount < 3) {
            if (startRecording()) {
                Logger.LOGD("onNoFilter: Could not create filter. Retrying");
                this.mFailedCount = this.mFailedCount + 1;
                return;
            }
            Logger.LOGE("onNoFilter: Could not retry analyze");
            this.mFailedCount = 0;
            reportServiceBusy(false);
            return;
        }
        Logger.LOGI("onNoFilter: Max failed reached. Unsupported headset");
        this.mHsTypeConnected = 3;
        this.mFailedCount = 0;
        this.mRetryCount = 0;
        this.mRecErrorCount = 0;
        onClearFilter();
        reportServiceBusy(false);
    }

    private void onRecError() {
        reportServiceBusy(false);
        if (!this.mAhcEnabled && !this.mServiceFirstRun) {
            Logger.LOGD("onRecError: AHC disabled");
            return;
        }
        int i = this.mRecErrorCount;
        if (i < 3) {
            this.mRecErrorCount = i + 1;
            this.mHandler.sendEmptyMessageDelayed(1, 60000L);
            return;
        }
        Logger.LOGI("onRecError: Max rec errors reached. Giving up");
        this.mHsTypeConnected = 3;
        this.mRecErrorCount = 0;
        this.mFailedCount = 0;
        this.mRetryCount = 0;
        onClearFilter();
    }

    private void onNewHsDetected() {
        if (!this.mAhcEnabled && !this.mServiceFirstRun) {
            Logger.LOGD("onNewHsDetected: AHC disabled");
            reportServiceBusy(false);
            return;
        }
        Logger.LOGD("onNewHsDetected: New Headset Detected");
        this.mRetryCount = 0;
        this.mFailedCount = 0;
        setImpedanceAnalyzed(true);
        setAsDefaultFilter();
        Logger.LOGI("mEnabledDuringFirstRun = " + this.mEnabledDuringFirstRun + " mAhcEnabled = " + this.mAhcEnabled);
        if (this.mEnabledDuringFirstRun && this.mAhcEnabled) {
            float[] filter = this.mWorker.getCurrentFilter();
            this.mWorker.sendFilterParams("onNewHsDetected: ", filter);
            this.mEnabledDuringFirstRun = false;
        }
        reportServiceBusy(false);
        this.mWorker.copyDebugFiles("/storedFilter");
    }

    private void onMusicStopped() {
        Logger.LOGD("onMusicStopped: mScreenOn " + this.mScreenOn);
        reportServiceBusy(false);
        if (this.mScreenOn) {
            this.mRecordingState = 4;
            onPollForMusic();
        } else {
            this.mRecordingState = 5;
        }
    }

    private void onRestartAnalyze() {
        if ((!this.mAhcEnabled && !this.mServiceFirstRun) || !this.mHeadsetConnected || this.mPlayingState != 2 || this.mMode != 0) {
            Logger.LOGD("onRestartAnalyze: Timer expired. Conditions not fulfilled.");
            return;
        }
        if (!this.mAudioManager.isMusicActive()) {
            Logger.LOGD("onRestartAnalyze: Timer expired. Music not playing");
            this.mHandler.sendEmptyMessageDelayed(1, 600000L);
            return;
        }
        Logger.LOGD("onRestartAnalyze: Timer expired. Restarting headset analyze");
        this.mRetryCount = 0;
        this.mFailedCount = 0;
        if (startRecording()) {
            this.mRetryCount++;
        }
    }

    private void onPollForMusic() {
        if (this.mAudioManager.isMusicActive()) {
            if (this.mRecordingState == 4) {
                startRecording();
            }
        } else if (this.mRecordingState == 4) {
            this.mHandler.sendEmptyMessageDelayed(3, 5000L);
        }
    }

    private boolean startRecording() {
        Logger.LOGD("startRecording: mScreenOn " + this.mScreenOn);
        if (PermissionUtil.isPermissionDenied(this, "android.permission.RECORD_AUDIO")) {
            return false;
        }
        if (this.mAudioManager.isMusicActive()) {
            boolean firstUse = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.SERVICE_FIRST_RUN, true);
            if (this.mWorker.start(this.mCallback, !firstUse)) {
                if (this.mScreenOn) {
                    this.mRecordingState = 2;
                } else {
                    this.mRecordingState = 3;
                }
                reportServiceBusy(true);
                return true;
            }
            reportServiceBusy(false);
            return false;
        }
        boolean firstUse2 = this.mScreenOn;
        if (firstUse2) {
            this.mRecordingState = 4;
            this.mHandler.sendEmptyMessageDelayed(3, 1000L);
        } else {
            this.mRecordingState = 5;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRecording() {
        clearDelayedMessages();
        if (this.mRecordingState != 1) {
            this.mWorker.stop();
            this.mRecordingState = 1;
            Logger.LOGD("Filter creation stopped");
        }
        reportServiceBusy(false);
    }

    private void reportServiceBusy(boolean busy) {
        BindServiceListener bindServiceListener = this.mListener;
        if (bindServiceListener != null) {
            bindServiceListener.setFeatureStatus(!busy);
        }
        this.mServiceBusy = busy;
    }

    private void clearDelayedMessages() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(3);
    }

    public void handleMessage(Message msg) {
        synchronized (this.mLock) {
            if (msg.what == 1) {
                onRestartAnalyze();
            } else if (msg.what == 2) {
                onAnalyzeFinished(msg.arg1);
            } else if (msg.what == 3) {
                onPollForMusic();
            } else if (msg.what == 4) {
                Logger.LOGD("Sending AHC_USER_SWAP broadcast");
                Intent intent = new Intent(ACTION_AHC_USER_SWAP);
                intent.addFlags(268435456);
                sendBroadcastAsUser(intent, UserHandleWrapper.ALL);
            }
        }
    }

    static class IncomingHandler extends Handler {
        private final WeakReference<AutomaticHeadphoneService> mService;

        IncomingHandler(AutomaticHeadphoneService service) {
            this.mService = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AutomaticHeadphoneService service = this.mService.get();
            if (service != null) {
                service.handleMessage(msg);
            }
        }
    }

    private void setupSharedPreferences(boolean foreground) {
        boolean z = false;
        if (this.mSharedPreferences.contains(com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE)) {
            boolean ahcEnabled = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE, false);
            setAhcEnabled(ahcEnabled);
        } else {
            this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE, this.mAhcEnabled).commit();
        }
        if (this.mSharedPreferences.contains(com.sonyericsson.soundenhancement.ahc.Constants.SERVICE_FIRST_RUN)) {
            boolean serviceFirstRun = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.SERVICE_FIRST_RUN, true);
            setServiceFirstRun(serviceFirstRun);
        } else {
            this.mServiceFirstRun = true;
        }
        if (this.mSharedPreferences.contains(com.sonyericsson.soundenhancement.ahc.Constants.AHC_IMP_ANALYZED)) {
            boolean isAnalyzed = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_IMP_ANALYZED, false);
            int size = this.mSharedPreferences.getString(com.sonyericsson.soundenhancement.ahc.Constants.SAVED_FILTER, "").split(",").length;
            boolean isInvalid = size != this.mWorker.getFilterSize();
            if (isInvalid) {
                Logger.LOGD("Stored filter is invalid size. Need to update it.");
            }
            if (isAnalyzed && !isInvalid) {
                z = true;
            }
            setImpedanceAnalyzed(z);
        }
        boolean isAnalyzed2 = this.mImpedanceAnalyzed;
        if (isAnalyzed2 && this.mSharedPreferences.contains(com.sonyericsson.soundenhancement.ahc.Constants.SAVED_FILTER)) {
            setSavedFilter(foreground);
        }
    }

    private void setImpedanceAnalyzed(boolean analyzed) {
        Logger.LOGD("setImpedanceAnalyzed: mImpedanceAnalyzed = " + analyzed);
        this.mImpedanceAnalyzed = analyzed;
        boolean result = this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_IMP_ANALYZED, this.mImpedanceAnalyzed).commit();
        if (!result) {
            Logger.LOGE("setImpedanceAnalyzed: Commit " + com.sonyericsson.soundenhancement.ahc.Constants.AHC_IMP_ANALYZED + " failed");
        }
    }

    private void setAhcEnabled(boolean ahcEnabled) {
        Logger.LOGD("setAhcEnabled: ahcEnabled = " + ahcEnabled + " mIsEnabledDolby = " + this.mIsEnabledDolby);
        this.mAhcEnabled = ahcEnabled && !this.mIsEnabledDolby;
        boolean result = this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE, ahcEnabled).commit();
        StringBuilder sb = new StringBuilder();
        sb.append("ahc_state=");
        sb.append(this.mAhcEnabled ? "1" : "0");
        String ahc_state_param = sb.toString();
        this.mAudioManager.setParameters(ahc_state_param);
        if (!result) {
            Logger.LOGE("setAhcEnabled: Commit " + com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE + " failed");
        }
    }

    private void setServiceFirstRun(boolean first) {
        Logger.LOGD("setServiceFirstRun: mServiceFirstRun = " + first);
        this.mServiceFirstRun = first;
        boolean result = this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.SERVICE_FIRST_RUN, this.mServiceFirstRun).commit();
        if (!result) {
            Logger.LOGE("setServiceFirstRun: Commit " + com.sonyericsson.soundenhancement.ahc.Constants.SERVICE_FIRST_RUN + " failed");
        }
    }

    private void setContributionContract(boolean enabled) {
        Logger.LOGD("setContributionContract: start");
        try {
            ContentValues v = new ContentValues();
            v.put("name", "PREF_STATUS");
            v.put("data", Integer.valueOf(enabled ? 1 : 0));
            getContentResolver().insert(ContributionContract.State.CONTENT_URI, v);
        } catch (Exception e) {
            Logger.LOGW("Failed contributing data: " + e);
        } catch (NoClassDefFoundError e2) {
            Logger.LOGW("Not supported ContributionContract class" + e2);
        }
        Logger.LOGD("setContributionContract: end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDolbyParamUpdate(String event, int value) {
        Logger.LOGD("handleDolbyParamUpdate event:" + event + " value:" + value);
        if (!TextUtils.isEmpty(event) && event.equals("ds_state_change")) {
            this.mIsEnabledDolby = value == 1;
            boolean isEnabledAhc = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE, false);
            onAhcEnabled(isEnabledAhc);
        }
    }
}
