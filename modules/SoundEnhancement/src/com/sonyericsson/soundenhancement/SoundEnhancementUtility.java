package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.media.AudioManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.reflect.ActivityManagerWrapper;
import com.sonyericsson.soundenhancement.reflect.SystemPropertiesWrapper;
import com.sonyericsson.soundenhancement.reflect.UserHandleWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementUtility {
    private static final String DSEE_HX_SUPPORTED = "ro.somc.dseehx.supported";
    private static final String KEY_ENABLE_DOLBY_AUTO_MODE = "persist.sony.enable.dolby_auto_mode";
    private static final String KEY_IS_MONO_SPEAKER = "persist.sony.mono_speaker";
    private static final String KEY_SUPPORTED_AHC = "persist.sony.effect.ahc";
    private static final String KEY_SUPPORTED_CLEAR_AUDIO_PLUS = "persist.sony.effect.clear_audio_plus";
    private static final String KEY_SUPPORTED_DOLBY_EFFECT = "persist.sony.effect.dolby_atmos";
    private static final String LOG_TAG = "SoundEnhancement";
    public static final int PLUGGED_ID_3_5mm_OFF = -1;
    public static final int PLUGGED_ID_ALL_OFF = 0;
    public static final int PLUGGED_ID_NC_HEADPHONE = 2;
    public static final int PLUGGED_ID_OTHER_ACCESSORY = 3;
    public static final int PLUGGED_ID_STEREO_MIC = 1;
    public static final String RECEIVER_CLASS_NAME = "com.sonyericsson.soundenhancement.AudioEffectControlReceiver";
    private static final String SOMC_WALL_APP_PKG_NAME = "com.sonymobile.cta";
    public static final String SOUND_SETTINGS_PACKAGENAME = "com.android.settings";

    public static int pluggedAccessory(Context context) {
        IntentFilter plugIntentFilter = new IntentFilter("com.sonyericsson.system.intent.action.HEADSET_PLUG");
        Intent plugIntent = context.registerReceiver(null, plugIntentFilter);
        int ancType = getAncDectectedType(context);
        if (plugIntent != null && "com.sonyericsson.system.intent.action.HEADSET_PLUG".equals(plugIntent.getAction()) && plugIntent.getIntExtra("state", 0) > 0) {
            if (ancType == -1) {
                return 3;
            }
            return ancType;
        }
        if (ancType == -1) {
            return 0;
        }
        return ancType;
    }

    public static int getAncDectectedType(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        String ancDetect = audioManager.getParameters("5pole_accessory_detect");
        if (ancDetect != null) {
            String[] splitResult = ancDetect.split("=");
            if (splitResult.length == 2) {
                try {
                    int ancType = Integer.parseInt(splitResult[1]);
                    if (ancType == -1) {
                        return -1;
                    }
                    if (ancType == 0 || ancType == 1) {
                        return 3;
                    }
                    if (ancType == 2) {
                        return 2;
                    }
                    if (ancType != 3) {
                        return -1;
                    }
                    return 1;
                } catch (NumberFormatException e) {
                    Log.w(LOG_TAG, "getAncDectectType: unknown type! ");
                    return -1;
                }
            }
        }
        return Settings.Global.getInt(context.getContentResolver(), "5pole_accessory_detect", 0);
    }

    public static boolean getSupportAutoHeadsetCompensation() {
        String state = SystemPropertiesWrapper.get(KEY_SUPPORTED_AHC, "");
        if (state != null && state.equals("true")) {
            return true;
        }
        return false;
    }

    public static final boolean supportedGlobalDseeHx() {
        String supported = SystemPropertiesWrapper.get(DSEE_HX_SUPPORTED, "false");
        if (supported == null || !supported.equals("true")) {
            return true;
        }
        return false;
    }

    public static final boolean isMonoSpeaker() {
        String supported = SystemPropertiesWrapper.get(KEY_IS_MONO_SPEAKER, "false");
        if (supported == null || !supported.equals("true")) {
            return false;
        }
        return true;
    }

    public static boolean isSoundEnhancementEnabled(Context context) {
        PackageManager pmgr = context.getPackageManager();
        ComponentName comp = new ComponentName(context.getPackageName(), RECEIVER_CLASS_NAME);
        int state = pmgr.getComponentEnabledSetting(comp);
        if (state == 2) {
            return false;
        }
        return true;
    }

    public static int fetchAccentColor(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{android.R.attr.colorAccent});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public static final int fetchPrimaryColor(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{android.R.attr.colorPrimary});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public static final boolean isCTACandidate(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(SOMC_WALL_APP_PKG_NAME, 0);
            if (info == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static final boolean isForeground() {
        try {
            int current = ActivityManagerWrapper.getCurrentUser();
            int calling = UserHandleWrapper.getCallingUserId();
            boolean foreground = current == calling;
            return foreground;
        } catch (Exception e) {
            return true;
        }
    }

    public static final void sendChangeNoiseCancelingSettingsAsAll(Context context) {
        Intent i = new Intent(SoundEnhancementService.ACTION_CHANGE_NOISE_CANCELING_SETTING);
        i.setPackage(context.getPackageName());
        context.sendBroadcastAsUser(i, UserHandleWrapper.ALL);
    }

    public static final boolean isMultiWindowMode(Activity activity) {
        return activity.isInMultiWindowMode();
    }

    public static final boolean isPortraitLayout(Context context) {
        if (context == null) {
            return false;
        }
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.x < p.y;
    }

    public static final boolean canShowSoundEffectsScreen(AudioEffectService service) {
        if (service == null) {
            return true;
        }
        if (!service.getCurrentClearAudioPlus() && !service.getCurrentDseeHx()) {
            return true;
        }
        return false;
    }

    public static final Intent createExplicitIntent(Context context, String action) {
        if (context == null || TextUtils.isEmpty(action)) {
            return null;
        }
        Intent intent = new Intent(action);
        intent.setPackage(context.getPackageName());
        return intent;
    }

    public static final void sendBroadcastExplicitIntent(Context context, String action) {
        Intent intent = createExplicitIntent(context, action);
        if (intent != null) {
            context.sendBroadcast(intent);
        }
    }

    public static final boolean enabledExternalAudioEffectApp(Context context) {
        boolean result = false;
        if (context == null) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        Intent i = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
        List<ResolveInfo> ris = pm.queryBroadcastReceivers(i, 512);
        for (ResolveInfo ri : ris) {
            String packageName = ri.activityInfo.packageName;
            if (!packageName.equals(context.getPackageName())) {
                try {
                    ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
                    if ((ai.flags & 1) != 0) {
                        return false;
                    }
                    result = true;
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
        }
        return result;
    }

    public static final boolean isSupportedDolbyEffect() {
        return SystemPropertiesWrapper.get(KEY_SUPPORTED_DOLBY_EFFECT, "").equals("true");
    }

    public static final boolean isEnabledDolbyAutoMode() {
        return isSupportedDolbyEffect() && SystemPropertiesWrapper.get(KEY_ENABLE_DOLBY_AUTO_MODE, "true").equals("true");
    }

    public static final boolean isSupportedClearAudioPlus() {
        return SystemPropertiesWrapper.get(KEY_SUPPORTED_CLEAR_AUDIO_PLUS, "true").equals("true");
    }
}
