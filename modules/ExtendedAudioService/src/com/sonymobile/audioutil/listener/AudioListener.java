package com.sonymobile.audioutil.listener;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.os.Binder;
import android.os.Handler;
import android.os.UserHandle;
import com.sonymobile.audioutil.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AudioListener extends EventListener {
    public static final String EVENT_AUDIO_PLAYBACK_CALLBACK = "audio_playback_callback";
    private static final String PACKAGE_NAME_GOOGLE_TTS = "com.google.android.tts";
    private static final String PACKAGE_NAME_SERVICE_MENU = "com.sonyericsson.android.servicemenu";
    private static final String PACKAGE_NAME_SYSTEM = "android.uid.system";
    private static final String PACKAGE_NAME_TV = "jp.co.fsi.fs1seg";
    private final int POLLING_COUNT_FAST_MAX;
    private final int POLLING_COUNT_SLOW_MAX;
    private final int POLLING_TIME_FAST;
    private final int POLLING_TIME_SLOW;
    private ActivityManager mActivityManager;
    private AllPlaybackCallback mAllPlaybackCallback;
    private AudioManager mAudioManager;
    private Context mContext;
    private Handler mHandler;
    private String mLastPackageName;
    private LauncherApps mLauncherApps;
    private Map<String, Boolean> mLauncherMap;
    private Map<String, Integer> mPlayingPackageMap;
    private int mPollingCount;
    private Handler mPollingHandler;
    private final Runnable mPollingRunnable;
    private String mRequestTtsPackage;
    private static final String TAG = AudioListener.class.getSimpleName();
    private static final List<String> FORCE_SOUND_LIST = new ArrayList();

    static /* synthetic */ int access$808(AudioListener x0) {
        int i = x0.mPollingCount;
        x0.mPollingCount = i + 1;
        return i;
    }

    static {
        FORCE_SOUND_LIST.add(PACKAGE_NAME_SERVICE_MENU);
        FORCE_SOUND_LIST.add(PACKAGE_NAME_TV);
    }

    public AudioListener(Context context, EventListener.Callback callback) {
        super(context, callback);
        this.mHandler = new Handler();
        this.mLauncherMap = new HashMap();
        this.mPlayingPackageMap = new HashMap();
        this.mPollingHandler = new Handler();
        this.POLLING_TIME_FAST = 500;
        this.POLLING_TIME_SLOW = 2000;
        this.POLLING_COUNT_FAST_MAX = 5;
        this.POLLING_COUNT_SLOW_MAX = 65;
        this.mPollingRunnable = new Runnable() { // from class: com.sonymobile.audioutil.listener.AudioListener.1
            @Override // java.lang.Runnable
            public void run() {
                if (AudioListener.this.mPlayingPackageMap.size() > 0) {
                    AudioListener.access$808(AudioListener.this);
                    if (AudioListener.this.mPollingCount <= 65) {
                        LogUtil.d(LogUtil.LOG_TAG, AudioListener.TAG + ".mPollingRunnable mPollingCount=" + AudioListener.this.mPollingCount);
                        AudioListener.this.sendEvent(AudioListener.EVENT_AUDIO_PLAYBACK_CALLBACK);
                        int pollingTime = 500;
                        if (5 <= AudioListener.this.mPollingCount) {
                            pollingTime = 2000;
                        }
                        if (AudioListener.this.mPlayingPackageMap.containsKey(AudioListener.PACKAGE_NAME_TV) || !AudioListener.this.mAudioManager.isMusicActive() || pollingTime != 2000) {
                            AudioListener.this.mPollingHandler.postDelayed(AudioListener.this.mPollingRunnable, pollingTime);
                        }
                    }
                }
            }
        };
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mActivityManager = (ActivityManager) context.getSystemService("activity");
        this.mLauncherApps = (LauncherApps) this.mContext.getSystemService("launcherapps");
        if (this.mAllPlaybackCallback == null) {
            this.mAllPlaybackCallback = new AllPlaybackCallback();
            this.mAudioManager.registerAudioPlaybackCallback(this.mAllPlaybackCallback, this.mHandler);
        }
    }

    public String getPackageName(boolean isMusicActive, String foregroundPackage) {
        String packageName = null;
        Integer playerType = this.mPlayingPackageMap.get(foregroundPackage);
        if (isMusicActive) {
            if (playerType != null) {
                packageName = foregroundPackage;
            } else if (this.mPlayingPackageMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : this.mPlayingPackageMap.entrySet()) {
                    if (isPackageLauncher(entry.getKey())) {
                        packageName = entry.getKey();
                        break;
                    }
                }
                if (this.mPlayingPackageMap.size() == 1) {
                    Iterator<Map.Entry<String, Integer>> it = this.mPlayingPackageMap.entrySet().iterator();
                    while (it.hasNext()) {
                        if (PACKAGE_NAME_GOOGLE_TTS.equals(it.next().getKey())) {
                            if (this.mRequestTtsPackage == null) {
                                break;
                            }
                            packageName = this.mRequestTtsPackage;
                            break;
                        }
                    }
                }
            }
        }
        if (FORCE_SOUND_LIST.contains(foregroundPackage)) {
            packageName = foregroundPackage;
        }
        if (packageName != null) {
            this.mLastPackageName = packageName;
        } else {
            packageName = this.mLastPackageName;
        }
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".getPackageName packageName=" + packageName + " isMusicActive=" + isMusicActive + " foregroundPackage=" + foregroundPackage + " playingPackageMap=" + this.mPlayingPackageMap.toString());
        return packageName;
    }

    public String getForegroundPackage() {
        List<ActivityManager.RunningTaskInfo> list = this.mActivityManager.getRunningTasks(1);
        if (list == null || list.size() <= 0) {
            return null;
        }
        ActivityManager.RunningTaskInfo taskInfo = list.get(0);
        return taskInfo.topActivity.getPackageName();
    }

    @Override // com.sonymobile.audioutil.listener.EventListener
    public boolean register() {
        if (!super.register()) {
            return false;
        }
        startPolling();
        if (this.mAllPlaybackCallback == null) {
            this.mAllPlaybackCallback = new AllPlaybackCallback();
            this.mAudioManager.registerAudioPlaybackCallback(this.mAllPlaybackCallback, this.mHandler);
            return true;
        }
        return true;
    }

    @Override // com.sonymobile.audioutil.listener.EventListener
    public boolean unregister() {
        if (!super.unregister()) {
            return false;
        }
        this.mPollingHandler.removeCallbacksAndMessages(null);
        return true;
    }

    private final class AllPlaybackCallback extends AudioManager.AudioPlaybackCallback {
        AllPlaybackCallback() {
        }

        @Override // android.media.AudioManager.AudioPlaybackCallback
        public void onPlaybackConfigChanged(List<AudioPlaybackConfiguration> configs) {
            String packageName;
            Iterator<AudioPlaybackConfiguration> it;
            boolean isPlayer;
            AudioListener.this.mPlayingPackageMap.clear();
            boolean isPlayer2 = false;
            Iterator<AudioPlaybackConfiguration> it2 = configs.iterator();
            while (true) {
                int i = 0;
                int i2 = 1;
                if (!it2.hasNext()) {
                    break;
                }
                AudioPlaybackConfiguration apc = it2.next();
                String packageNameUid = AudioListener.this.mContext.getPackageManager().getNameForUid(apc.getClientUid());
                if (packageNameUid == null) {
                    LogUtil.w(LogUtil.LOG_TAG, AudioListener.TAG + ".onPlaybackConfigChanged packageNameUid is null! Skip this uid. uid=" + apc.getClientUid());
                } else {
                    boolean isSharedUserIdApp = false;
                    if (packageNameUid.contains(":")) {
                        String[] packageNames = packageNameUid.split(":");
                        packageName = packageNames[0];
                        if (!packageName.startsWith("android")) {
                            isSharedUserIdApp = true;
                        }
                    } else {
                        packageName = packageNameUid;
                    }
                    LogUtil.d(LogUtil.LOG_TAG, AudioListener.TAG + ".onPlaybackConfigChanged packageName=" + packageName + " packageNameUid=" + packageNameUid + " isSharedUserIdApp=" + isSharedUserIdApp);
                    int playerType = apc.getPlayerType();
                    int playerState = apc.getPlayerState();
                    int streamType = apc.getAudioAttributes().getVolumeControlStream();
                    if (AudioListener.PACKAGE_NAME_TV.equals(packageName)) {
                        playerState = 2;
                    }
                    if (playerType == 3 || playerState != 2) {
                        it = it2;
                    } else if (streamType == 3 || streamType == Integer.MIN_VALUE) {
                        if (playerType == 1 || playerType == 2) {
                            isPlayer2 = true;
                        }
                        if (AudioListener.PACKAGE_NAME_SYSTEM.equals(packageName)) {
                            String[] processPackageNames = AudioListener.this.getProcessPackageNames(apc.getClientPid());
                            if (processPackageNames == null) {
                                isPlayer = isPlayer2;
                                it = it2;
                            } else {
                                int length = processPackageNames.length;
                                while (i < length) {
                                    boolean isPlayer3 = isPlayer2;
                                    String processPackageName = processPackageNames[i];
                                    Iterator<AudioPlaybackConfiguration> it3 = it2;
                                    String[] strArr = new String[i2];
                                    StringBuilder sb = new StringBuilder();
                                    String packageNameUid2 = packageNameUid;
                                    sb.append(AudioListener.TAG);
                                    sb.append(".onPlaybackConfigChanged processPackageName=");
                                    sb.append(processPackageName);
                                    strArr[0] = sb.toString();
                                    LogUtil.d(LogUtil.LOG_TAG, strArr);
                                    if (AudioListener.PACKAGE_NAME_SERVICE_MENU.equals(processPackageName)) {
                                        AudioListener.this.mPlayingPackageMap.put(processPackageName, Integer.valueOf(playerType));
                                    }
                                    i++;
                                    isPlayer2 = isPlayer3;
                                    it2 = it3;
                                    packageNameUid = packageNameUid2;
                                    i2 = 1;
                                }
                                isPlayer = isPlayer2;
                                it = it2;
                            }
                        } else {
                            isPlayer = isPlayer2;
                            it = it2;
                            if (isSharedUserIdApp) {
                                String[] processPackageNames2 = AudioListener.this.getProcessPackageNames(apc.getClientPid());
                                if (processPackageNames2 != null) {
                                    for (String processPackageName2 : processPackageNames2) {
                                        LogUtil.i(LogUtil.LOG_TAG, AudioListener.TAG + ".onPlaybackConfigChanged processPackageName=" + processPackageName2);
                                        AudioListener.this.mPlayingPackageMap.put(processPackageName2, Integer.valueOf(playerType));
                                    }
                                }
                            } else {
                                AudioListener.this.mPlayingPackageMap.put(packageName, Integer.valueOf(playerType));
                            }
                        }
                        isPlayer2 = isPlayer;
                    } else {
                        it = it2;
                    }
                    if (AudioListener.this.mPlayingPackageMap.size() == 1) {
                        for (Map.Entry<String, Integer> entry : AudioListener.this.mPlayingPackageMap.entrySet()) {
                            if (AudioListener.PACKAGE_NAME_GOOGLE_TTS.equals(entry.getKey())) {
                                AudioListener audioListener = AudioListener.this;
                                audioListener.mRequestTtsPackage = audioListener.getForegroundPackage();
                                break;
                            }
                        }
                    }
                    if (LogUtil.isDebug()) {
                        AudioListener.this.dumpAudioPlaybackConfiguration(apc, packageName);
                    }
                    it2 = it;
                }
            }
            LogUtil.d(LogUtil.LOG_TAG, AudioListener.TAG + ".onPlaybackConfigChanged isPlayer=" + isPlayer2 + " mPlayingPackageMap.size() =" + AudioListener.this.mPlayingPackageMap.size());
            if (isPlayer2 && AudioListener.this.mPlayingPackageMap.size() > 1) {
                Iterator<Integer> it4 = AudioListener.this.mPlayingPackageMap.values().iterator();
                while (it4.hasNext()) {
                    Integer playerType2 = it4.next();
                    if (playerType2.intValue() == 11 || playerType2.intValue() == 12) {
                        LogUtil.d(LogUtil.LOG_TAG, AudioListener.TAG + ".onPlaybackConfigChanged remove playerType=" + playerType2);
                        it4.remove();
                    }
                }
            }
            if (LogUtil.isDebug()) {
                AudioListener.this.dumpLauncherMap();
            }
            if (AudioListener.this.isRegistered()) {
                AudioListener.this.sendEvent(AudioListener.EVENT_AUDIO_PLAYBACK_CALLBACK);
                AudioListener.this.startPolling();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] getProcessPackageNames(int pid) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = this.mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == pid) {
                return runningAppProcessInfo.pkgList;
            }
        }
        return null;
    }

    private boolean isPackageLauncher(String packageName) {
        Boolean isPackageLauncher = this.mLauncherMap.get(packageName);
        if (isPackageLauncher == null) {
            List<LauncherActivityInfo> list = this.mLauncherApps.getActivityList(packageName, UserHandle.getUserHandleForUid(Binder.getCallingUid()));
            if (list.size() > 0) {
                LogUtil.d(LogUtil.LOG_TAG, TAG + ".isPackageLauncherlauncher packageName=" + packageName);
                isPackageLauncher = true;
            } else {
                isPackageLauncher = false;
            }
            this.mLauncherMap.put(packageName, isPackageLauncher);
        }
        return isPackageLauncher.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPolling() {
        this.mPollingHandler.removeCallbacksAndMessages(null);
        this.mPollingCount = 0;
        this.mPollingHandler.postDelayed(this.mPollingRunnable, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpLauncherMap() {
        for (Map.Entry<String, Boolean> entry : this.mLauncherMap.entrySet()) {
            LogUtil.d(LogUtil.LOG_TAG, TAG + ".dumpLauncherMap package=" + entry.getKey() + " launcher=" + entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpAudioPlaybackConfiguration(AudioPlaybackConfiguration apc, String packageName) {
        CharSequence appName = getAppName(getActivityInfo(this.mContext, packageName));
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".onPlaybackConfigChanged Type=" + toLogFriendlyPlayerType(apc.getPlayerType()) + " State=" + toLogFriendlyPlayerState(apc.getPlayerState()) + " appName=" + ((Object) appName) + " packageName=" + packageName + " Id=" + apc.getPlayerInterfaceId() + " flags=" + apc.getAudioAttributes().getAllFlags() + " LOW_LATENCY=" + (apc.getAudioAttributes().getFlags() & 256));
    }

    private CharSequence getAppName(LauncherActivityInfo launcherActivityInfo) {
        if (launcherActivityInfo == null) {
            return null;
        }
        CharSequence label = launcherActivityInfo.getLabel();
        return label;
    }

    private LauncherActivityInfo getActivityInfo(Context context, String packageName) {
        LauncherApps launcherApps = (LauncherApps) context.getSystemService("launcherapps");
        List<LauncherActivityInfo> list = launcherApps.getActivityList(packageName, UserHandle.getUserHandleForUid(Binder.getCallingUid()));
        if (list.size() <= 0) {
            return null;
        }
        LauncherActivityInfo info = list.get(0);
        return info;
    }

    private String toLogFriendlyPlayerType(int type) {
        if (type == -1) {
            return "unknown";
        }
        if (type == 1) {
            return "android.media.AudioTrack";
        }
        if (type == 2) {
            return "android.media.MediaPlayer";
        }
        if (type == 3) {
            return "android.media.SoundPool";
        }
        switch (type) {
            case 11:
                return "OpenSL ES AudioPlayer (Buffer Queue)";
            case 12:
                return "OpenSL ES AudioPlayer (URI/FD)";
            case 13:
                return "AAudio";
            case 14:
                return "hardware source";
            case 15:
                return "external proxy";
            default:
                return "unknown player type - FIXME";
        }
    }

    private String toLogFriendlyPlayerState(int state) {
        if (state == -1) {
            return "unknown";
        }
        if (state == 0) {
            return "released";
        }
        if (state == 1) {
            return "idle";
        }
        if (state == 2) {
            return "started";
        }
        if (state == 3) {
            return "paused";
        }
        if (state == 4) {
            return "stopped";
        }
        return "unknown player state - FIXME";
    }
}
