package com.dolby.daxservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import com.dolby.dax.DolbyAudioEffect;
import com.dolby.dax.DsParams;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import vendor.dolby.hardware.dms.V1_0.IDms;
import vendor.dolby.hardware.dms.V1_0.IDmsCallbacks;

/* JADX INFO: loaded from: classes.dex */
public class DaxService extends Service {
    private static Context mContext;
    private static Handler mHandler;
    private DefaultValues mDefaultValueConfiguration;
    private IDms mDms;
    private int mServiceConnectRetryTimes;
    private DolbyAudioEffect mDolbyAudio = null;
    private int MAX_DOLBY_SERVICE_CONNECT_RETRY_TIMES = 5;
    private long DOLBY_SERVICE_RECONNECT_DELAY = 1000;
    private int mUserId = 0;
    private int[] default_geq_gains_dynamic = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] default_geq_gains_movie = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] default_geq_gains_music = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] default_geq_gains_custom = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private AudioManager mAudioManager = null;
    private EffectSetting mEffectSetting = null;
    private IHwBinder.DeathRecipient mDmsDeathRecipient = new IHwBinder.DeathRecipient() { // from class: com.dolby.daxservice.DaxService.2
        public void serviceDied(long j) {
            if (DaxService.mHandler != null) {
                Log.e("DaxService", "Dolby service has died, try to reconnect 1s later.");
                DaxService.mHandler.sendEmptyMessageDelayed(100, DaxService.this.DOLBY_SERVICE_RECONNECT_DELAY);
            }
        }
    };
    private IDmsCallbacks mDmsClient = new IDmsCallbacks.Stub() { // from class: com.dolby.daxservice.DaxService.3
        @Override // vendor.dolby.hardware.dms.V1_0.IDmsCallbacks
        public void onDapParamUpdate(ArrayList<Byte> arrayList) {
            try {
                byte[] bArr = new byte[arrayList.size()];
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = arrayList.get(i).byteValue();
                }
                if (DaxService.mHandler != null) {
                    DaxService.mHandler.sendMessage(DaxService.mHandler.obtainMessage(1, arrayList.size(), 0, bArr));
                }
            } catch (Exception e) {
                Log.e("DaxService", "Error while processing Dolby service callback: " + e);
                e.printStackTrace();
            }
        }
    };
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() { // from class: com.dolby.daxservice.DaxService.4
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws Throwable {
            String str;
            String str2;
            String str3;
            String str4;
            try {
                String action = intent.getAction();
                Log.d("DaxService", "mIntentReceiver, action = " + action);
                byte b = -1;
                DolbyAudioEffect dolbyAudioEffect = null;
                str = null;
                str = null;
                String str5 = null;
                dolbyAudioEffect = null;
                dolbyAudioEffect = null;
                dolbyAudioEffect = null;
                dolbyAudioEffect = null;
                dolbyAudioEffect = null;
                boolean z = true;
                if (action.equals("dolby_dap_params_update")) {
                    SharedPreferences sharedPreferences = DaxService.this.getSharedPreferences("dap_settings_" + String.valueOf(DaxService.this.mUserId), 0);
                    String stringExtra = intent.getStringExtra("event name");
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    switch (stringExtra.hashCode()) {
                        case -1146943738:
                            if (stringExtra.equals("profile_change")) {
                                b = 2;
                            }
                            break;
                        case 173585614:
                            if (stringExtra.equals("ds_state_change")) {
                                b = 3;
                            }
                            break;
                        case 893024970:
                            if (stringExtra.equals("reset_profile_setting")) {
                                b = 1;
                            }
                            break;
                        case 1577092565:
                            if (stringExtra.equals("profile_setting_change")) {
                                b = 0;
                            }
                            break;
                    }
                    if (b == 0) {
                        int intExtra = intent.getIntExtra("Integer Value", DaxService.this.mDolbyAudio.getProfile());
                        if (intExtra == 0) {
                            str3 = "GeqGainsForDynamic";
                            str4 = null;
                        } else if (intExtra == 1) {
                            str3 = "GeqGainsForMovie";
                            str4 = "DialogEnhancerAmountForMovie";
                        } else if (intExtra == 2) {
                            str5 = "IeqPresetForMusic";
                            str3 = "GeqGainsForMusic";
                            str4 = null;
                        } else if (intExtra == 3) {
                            str5 = "IeqPresetForCustom";
                            str3 = "GeqGainsForCustom";
                            str4 = "DialogEnhancerAmountForCustom";
                        } else {
                            str3 = null;
                            str4 = null;
                        }
                        DaxService.this.mEffectSetting.mProfile = intExtra;
                        ProfileSetting profileSetting = DaxService.this.mEffectSetting.mProfileSettings.get(Integer.valueOf(intExtra));
                        if (str5 != null) {
                            int[] dapParameter = DaxService.this.mDolbyAudio.getDapParameter(intExtra, DsParams.IntelligentEqualizerPreset.toInt());
                            editorEdit.putInt(str5, dapParameter[0]);
                            profileSetting.mIeqPreset = dapParameter[0];
                        }
                        if (str3 != null) {
                            int[] dapParameter2 = DaxService.this.mDolbyAudio.getDapParameter(intExtra, DsParams.GraphicEqualizerBandGains.toInt());
                            JSONArray jSONArray = new JSONArray();
                            for (int i : dapParameter2) {
                                jSONArray.put(i);
                            }
                            editorEdit.putString(str3, jSONArray.toString());
                            profileSetting.mGeqGains = jSONArray.toString();
                        }
                        if (str4 != null) {
                            int[] dapParameter3 = DaxService.this.mDolbyAudio.getDapParameter(intExtra, DsParams.DialogEnhancementAmount.toInt());
                            editorEdit.putInt(str4, dapParameter3[0]);
                            profileSetting.mDenAmount = dapParameter3[0];
                        }
                        editorEdit.commit();
                        DaxService.this.mAudioManager.setParameters(DaxService.this.mEffectSetting.toIddString());
                        return;
                    }
                    if (b != 1) {
                        if (b == 2) {
                            int profile = DaxService.this.mDolbyAudio.getProfile();
                            editorEdit.putInt("dsProfile", profile);
                            editorEdit.commit();
                            DaxService.this.mEffectSetting.mProfile = profile;
                            DaxService.this.mAudioManager.setParameters(DaxService.this.mEffectSetting.toIddString());
                            return;
                        }
                        if (b != 3) {
                            return;
                        }
                        if (intent.getIntExtra("Integer Value", 0) <= 0) {
                            z = false;
                        }
                        editorEdit.putBoolean("dsState", z);
                        editorEdit.commit();
                        DaxService.this.mEffectSetting.mDsState = z;
                        DaxService.this.mAudioManager.setParameters(DaxService.this.mEffectSetting.toIddString());
                        return;
                    }
                    int intExtra2 = intent.getIntExtra("Integer Value", 0);
                    DaxService.this.mEffectSetting.mProfile = intExtra2;
                    ProfileSetting profileSetting2 = DaxService.this.mEffectSetting.mProfileSettings.get(Integer.valueOf(intExtra2));
                    SharedPreferences sharedPreferences2 = DaxService.this.getSharedPreferences("dap_settings_default", 0);
                    if (intExtra2 == 0) {
                        JSONArray jSONArray2 = new JSONArray(sharedPreferences2.getString("GeqGainsForDynamic", "[]"));
                        editorEdit.putString("GeqGainsForDynamic", jSONArray2.toString());
                        profileSetting2.mGeqGains = jSONArray2.toString();
                    } else if (intExtra2 == 1) {
                        JSONArray jSONArray3 = new JSONArray(sharedPreferences2.getString("GeqGainsForMovie", "[]"));
                        int i2 = sharedPreferences2.getInt("DialogEnhancerAmountForMovie", DaxService.this.mDefaultValueConfiguration.getDefaultDEAmount());
                        editorEdit.putString("GeqGainsForMovie", jSONArray3.toString());
                        editorEdit.putInt("DialogEnhancerAmountForMovie", i2);
                        profileSetting2.mGeqGains = jSONArray3.toString();
                        profileSetting2.mDenAmount = i2;
                    } else if (intExtra2 == 2) {
                        JSONArray jSONArray4 = new JSONArray(sharedPreferences2.getString("GeqGainsForMusic", "[]"));
                        int i3 = sharedPreferences2.getInt("IeqPresetForMusic", DaxService.this.mDefaultValueConfiguration.getDefaultIeqPreset());
                        editorEdit.putString("GeqGainsForMusic", jSONArray4.toString());
                        editorEdit.putInt("IeqPresetForMusic", i3);
                        profileSetting2.mGeqGains = jSONArray4.toString();
                        profileSetting2.mIeqPreset = i3;
                    } else if (intExtra2 == 3) {
                        JSONArray jSONArray5 = new JSONArray(sharedPreferences2.getString("GeqGainsForCustom", "[]"));
                        int i4 = sharedPreferences2.getInt("IeqPresetForCustom", DaxService.this.mDefaultValueConfiguration.getDefaultIeqPreset());
                        int i5 = sharedPreferences2.getInt("DialogEnhancerAmountForCustom", DaxService.this.mDefaultValueConfiguration.getDefaultDEAmount());
                        editorEdit.putString("GeqGainsForCustom", jSONArray5.toString());
                        editorEdit.putInt("IeqPresetForCustom", i4);
                        editorEdit.putInt("DialogEnhancerAmountForCustom", i5);
                        profileSetting2.mGeqGains = jSONArray5.toString();
                        profileSetting2.mIeqPreset = i4;
                        profileSetting2.mDenAmount = i5;
                    }
                    editorEdit.commit();
                    DaxService.this.mAudioManager.setParameters(DaxService.this.mEffectSetting.toIddString());
                    return;
                }
                if (action.equals("android.intent.action.USER_SWITCHED")) {
                    int intExtra3 = intent.getIntExtra("android.intent.extra.user_handle", 0);
                    synchronized (this) {
                        DaxService.this.mUserId = intExtra3;
                    }
                    DaxService.this.setDaxSettingsForUser();
                    return;
                }
                if (action.equals("android.intent.action.USER_REMOVED")) {
                    new File(DaxService.this.getApplicationContext().getFilesDir().getParent() + "/shared_prefs/" + ("dap_settings_" + String.valueOf(intent.getIntExtra("android.intent.extra.user_handle", 0))) + ".xml").delete();
                    return;
                }
                if (action.equals("android.intent.action.USER_ADDED")) {
                    int intExtra4 = intent.getIntExtra("android.intent.extra.user_handle", 0);
                    synchronized (this) {
                        DaxService.this.mUserId = intExtra4;
                    }
                    return;
                }
                if (action.equals("audio_server_restarted")) {
                    if (DaxService.this.mDolbyAudio != null) {
                        DaxService.this.mDolbyAudio.release();
                        DaxService.this.mDolbyAudio = new DolbyAudioEffect(-1, 0);
                        if (DaxService.this.mDms != null) {
                            boolean dapEnabled = DaxService.this.mDms.getDapEnabled();
                            if (DaxService.this.mDolbyAudio.hasControl()) {
                                DaxService.this.mDolbyAudio.setDsOn(dapEnabled);
                            } else {
                                Log.w("DaxService", "Dolby audio effect is out of control in audio_server_started");
                                try {
                                    try {
                                        DolbyAudioEffect dolbyAudioEffect2 = new DolbyAudioEffect(1, 0);
                                        try {
                                            dolbyAudioEffect2.setDsOn(dapEnabled);
                                            dolbyAudioEffect2.release();
                                            str = "DaxService";
                                            str2 = "audio_server_started::dolbyAudio.release()";
                                        } catch (Exception e) {
                                            e = e;
                                            dolbyAudioEffect = dolbyAudioEffect2;
                                            e.printStackTrace();
                                            if (dolbyAudioEffect != null) {
                                                dolbyAudioEffect.release();
                                                str = "DaxService";
                                                str2 = "audio_server_started::dolbyAudio.release()";
                                            }
                                            Log.d("DaxService", "Dax effect recreate successfully");
                                            return;
                                        } catch (Throwable th) {
                                            th = th;
                                            dolbyAudioEffect = dolbyAudioEffect2;
                                            if (dolbyAudioEffect != null) {
                                                dolbyAudioEffect.release();
                                                Log.e("DaxService", "audio_server_started::dolbyAudio.release()");
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                }
                                Log.e(str, str2);
                            }
                        }
                    }
                    Log.d("DaxService", "Dax effect recreate successfully");
                    return;
                }
                if (action.equals("android.intent.action.REBOOT") || (action.equals("android.intent.action.ACTION_SHUTDOWN") && getSendingUserId() == -1)) {
                    Log.d("DaxService", "Save DS state and current settings before shutting down...");
                    DaxService.this.release();
                    DaxService.this.mDolbyAudio.release();
                    return;
                }
                return;
                Log.e("DaxService", "Exception found in intentReceiver_::onReceive()");
                e.printStackTrace();
            } catch (Exception e3) {
                Log.e("DaxService", "Exception found in intentReceiver_::onReceive()");
                e3.printStackTrace();
            }
        }
    };

    static /* synthetic */ int access$208(DaxService daxService) {
        int i = daxService.mServiceConnectRetryTimes;
        daxService.mServiceConnectRetryTimes = i + 1;
        return i;
    }

    private static class ProfileSetting {
        public int mDenAmount;
        public String mGeqGains;
        public int mIeqPreset;

        private ProfileSetting() {
        }
    }

    private static class EffectSetting {
        public boolean mDsState;
        public int mProfile;
        public ArrayMap<Integer, ProfileSetting> mProfileSettings = new ArrayMap<>();

        public EffectSetting() {
            for (int i = 0; i < 4; i++) {
                this.mProfileSettings.put(Integer.valueOf(i), new ProfileSetting());
            }
        }

        public String toIddString() {
            ProfileSetting profileSetting = this.mProfileSettings.get(Integer.valueOf(this.mProfile));
            return "dle_ds_state=" + this.mDsState + ";dle_profile=" + this.mProfile + ";dle_geq_gains=" + profileSetting.mGeqGains + ";dle_ieq_preset=" + profileSetting.mIeqPreset + ";dle_den_amount=" + profileSetting.mDenAmount;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int byteArrayToInt32(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }

    public void init(Context context) {
        synchronized (this) {
            if (mHandler != null) {
                return;
            }
            Looper mainLooper = Looper.getMainLooper();
            mContext = context;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
            this.mEffectSetting = new EffectSetting();
            if (mainLooper != null) {
                mHandler = new Handler(mainLooper) { // from class: com.dolby.daxservice.DaxService.1
                    protected void sendCallbackIntent(String str, int i) {
                        Intent intent = new Intent("dolby_dap_params_update");
                        intent.putExtra("event name", str);
                        intent.putExtra("Integer Value", i);
                        intent.addFlags(16777216);
                        DaxService.mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT_OR_SELF);
                    }

                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        int i = message.what;
                        if (i != 1) {
                            if (i != 100) {
                                return;
                            }
                            if (DaxService.this.mServiceConnectRetryTimes < DaxService.this.MAX_DOLBY_SERVICE_CONNECT_RETRY_TIMES) {
                                Log.i("DaxService", "Try to reconnect to Dolby service...");
                                DaxService.access$208(DaxService.this);
                                if (!DaxService.this.initDolbyHidlClient()) {
                                    Log.i("DaxService", "Reconnect to Dolby service failed, tried " + DaxService.this.mServiceConnectRetryTimes);
                                    sendEmptyMessageDelayed(100, DaxService.this.DOLBY_SERVICE_RECONNECT_DELAY);
                                    return;
                                }
                                Log.i("DaxService", "Reconnected to Dolby service, reset retry count.");
                                DaxService.this.mServiceConnectRetryTimes = 0;
                                return;
                            }
                            Log.e("DaxService", "Reconnect Dolby service reached MAX retry times, abort...");
                            return;
                        }
                        byte[] bArr = (byte[]) message.obj;
                        int length = bArr.length;
                        int iByteArrayToInt32 = DaxService.byteArrayToInt32(bArr, 0);
                        String str = "dl_dec_type=None";
                        if (iByteArrayToInt32 == -1) {
                            try {
                                int iByteArrayToInt33 = DaxService.byteArrayToInt32(bArr, 4);
                                sendCallbackIntent("dec_client_num", iByteArrayToInt33);
                                int iByteArrayToInt34 = DaxService.byteArrayToInt32(bArr, 8);
                                if (DaxService.byteArrayToInt32(bArr, 12) == 1) {
                                    if (iByteArrayToInt34 == 5) {
                                        DaxService.this.mAudioManager.setParameters("dl_dec_type=audio/ac4");
                                    }
                                } else if (iByteArrayToInt33 <= 0) {
                                    DaxService.this.mAudioManager.setParameters("dl_dec_type=None");
                                }
                                return;
                            } catch (Exception e) {
                                Log.e("DaxService", "DeadObjectException in Enabled");
                                e.printStackTrace();
                                return;
                            }
                        }
                        if (iByteArrayToInt32 == 0) {
                            try {
                                sendCallbackIntent("ds_state_change", DaxService.byteArrayToInt32(bArr, 4));
                                return;
                            } catch (Exception e2) {
                                Log.e("DaxService", "DeadObjectException in Enabled");
                                e2.printStackTrace();
                                return;
                            }
                        }
                        if (iByteArrayToInt32 == 16777216 || iByteArrayToInt32 == 33554432) {
                            try {
                                sendCallbackIntent("profile_setting_change", DaxService.byteArrayToInt32(bArr, 8));
                                return;
                            } catch (Exception e3) {
                                Log.e("DaxService", "DeadObjectException in Enabled");
                                e3.printStackTrace();
                                return;
                            }
                        }
                        if (iByteArrayToInt32 == 167772160) {
                            try {
                                sendCallbackIntent("profile_change", DaxService.byteArrayToInt32(bArr, 4));
                                return;
                            } catch (Exception e4) {
                                Log.e("DaxService", "DeadObjectException in Enabled");
                                e4.printStackTrace();
                                return;
                            }
                        }
                        if (iByteArrayToInt32 == 201326592) {
                            try {
                                sendCallbackIntent("reset_profile_setting", DaxService.byteArrayToInt32(bArr, 4));
                                return;
                            } catch (Exception e5) {
                                Log.e("DaxService", "DeadObjectException in Enabled");
                                e5.printStackTrace();
                                return;
                            }
                        }
                        if (iByteArrayToInt32 != 268369920) {
                            return;
                        }
                        try {
                            int iByteArrayToInt35 = DaxService.byteArrayToInt32(bArr, 4);
                            if (iByteArrayToInt35 == 1) {
                                str = "dl_dec_type=audio/eac3-joc";
                            } else if (iByteArrayToInt35 == 2) {
                                str = "dl_dec_type=audio/eac3";
                            }
                            DaxService.this.mAudioManager.setParameters(str);
                        } catch (Exception e6) {
                            Log.e("DaxService", "DeadObjectException in Enabled");
                            e6.printStackTrace();
                        }
                    }
                };
                if (!initDolbyHidlClient()) {
                    Log.e("DaxService", "Initial connect to Dolby service failed, retry 1s later.");
                    mHandler.sendEmptyMessageDelayed(100, this.DOLBY_SERVICE_RECONNECT_DELAY);
                }
            } else {
                mHandler = null;
            }
        }
    }

    public void release() {
        IDmsCallbacks iDmsCallbacks;
        Log.d("DaxService", "Release Dolby service HIDL client");
        IDms iDms = this.mDms;
        if (iDms != null && (iDmsCallbacks = this.mDmsClient) != null) {
            try {
                iDms.unregisterClient(iDmsCallbacks, 3, iDmsCallbacks.hashCode());
                this.mDms.unlinkToDeath(this.mDmsDeathRecipient);
            } catch (Exception unused) {
            }
        }
        mHandler = null;
        mContext = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initDolbyHidlClient() {
        try {
            this.mDms = IDms.getService();
            if (this.mDms == null) {
                return false;
            }
            this.mDms.linkToDeath(this.mDmsDeathRecipient, this.mDmsClient.hashCode());
            this.mDms.registerClient(this.mDmsClient, 3, this.mDmsClient.hashCode());
            if (this.mDolbyAudio != null) {
                this.mDolbyAudio.release();
            }
            this.mDolbyAudio = new DolbyAudioEffect(-1, 0);
            boolean dapEnabled = this.mDms.getDapEnabled();
            if (this.mDolbyAudio.hasControl()) {
                this.mDolbyAudio.setDsOn(dapEnabled);
            } else {
                Log.w("DaxService", "Dolby audio effect is out of control in initDolbyHidlClient");
            }
            Log.d("DaxService", "Dolby service client initialized, create and enable global effect.");
            return true;
        } catch (Exception e) {
            Log.e("DaxService", "Failed to init HIDL client: " + e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:63:0x020d  */
    /* JADX WARN: Code duplicated, block: B:69:0x0218  */
    /* JADX WARN: Code duplicated, block: B:84:? A[RETURN, SYNTHETIC] */
    public void setDaxSettingsForUser() throws Throwable {
        String str;
        DolbyAudioEffect dolbyAudioEffect;
        DolbyAudioEffect dolbyAudioEffect2;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONArray jSONArray3;
        JSONArray jSONArray4;
        try {
            Log.d("DaxService", "setDaxSettingsForUser: mUserId = " + this.mUserId);
            SharedPreferences sharedPreferences = getSharedPreferences("dap_settings_" + String.valueOf(this.mUserId), 0);
            SharedPreferences sharedPreferences2 = getSharedPreferences("dap_settings_default", 0);
            Boolean boolValueOf = Boolean.valueOf(sharedPreferences.getBoolean("dsState", Boolean.valueOf(sharedPreferences2.getBoolean("dsState", this.mDefaultValueConfiguration.getDefaultDsState())).booleanValue()));
            dolbyAudioEffect = new DolbyAudioEffect(1, 0);
            try {
                Log.d("DaxService", "setDaxSettingsForUser(): Create the effect with control priority 1");
                dolbyAudioEffect.setDsOn(boolValueOf.booleanValue());
                int[] iArr = new int[20];
                if (sharedPreferences.contains("GeqGainsForDynamic")) {
                    jSONArray = new JSONArray(sharedPreferences.getString("GeqGainsForDynamic", "[]"));
                } else {
                    jSONArray = new JSONArray(sharedPreferences2.getString("GeqGainsForDynamic", "[]"));
                }
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        try {
                            try {
                                iArr[i] = jSONArray.getInt(i);
                            } catch (Exception e) {
                                e = e;
                                dolbyAudioEffect2 = dolbyAudioEffect;
                                str = "setDaxSettingsForUser::dolbyAudio.release()";
                                try {
                                    Log.e("DaxService", "Exception found in intentReceiver_::onReceive()");
                                    e.printStackTrace();
                                    if (dolbyAudioEffect2 != null) {
                                        dolbyAudioEffect2.release();
                                        Log.d("DaxService", str);
                                    }
                                    return;
                                } catch (Throwable th) {
                                    th = th;
                                    dolbyAudioEffect = dolbyAudioEffect2;
                                    if (dolbyAudioEffect != null) {
                                        dolbyAudioEffect.release();
                                        Log.d("DaxService", str);
                                    }
                                    throw th;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            str = "setDaxSettingsForUser::dolbyAudio.release()";
                            if (dolbyAudioEffect != null) {
                                dolbyAudioEffect.release();
                                Log.d("DaxService", str);
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        str = "setDaxSettingsForUser::dolbyAudio.release()";
                        dolbyAudioEffect2 = dolbyAudioEffect;
                        Log.e("DaxService", "Exception found in intentReceiver_::onReceive()");
                        e.printStackTrace();
                        if (dolbyAudioEffect2 != null) {
                            dolbyAudioEffect2.release();
                            Log.d("DaxService", str);
                        }
                        return;
                    }
                }
                dolbyAudioEffect.setDapParameter(0, DsParams.GraphicEqualizerBandGains.toInt(), iArr);
                Arrays.fill(iArr, 0);
                if (sharedPreferences.contains("GeqGainsForMovie")) {
                    jSONArray2 = new JSONArray(sharedPreferences.getString("GeqGainsForMovie", "[]"));
                } else {
                    jSONArray2 = new JSONArray(sharedPreferences2.getString("GeqGainsForMovie", "[]"));
                }
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    iArr[i2] = jSONArray2.getInt(i2);
                }
                dolbyAudioEffect.setDapParameter(1, DsParams.GraphicEqualizerBandGains.toInt(), iArr);
                int[] iArr2 = {sharedPreferences.getInt("DialogEnhancerAmountForMovie", sharedPreferences2.getInt("DialogEnhancerAmountForMovie", this.mDefaultValueConfiguration.getDefaultDEAmount()))};
                dolbyAudioEffect.setDapParameter(1, DsParams.DialogEnhancementAmount.toInt(), iArr2);
                int[] iArr3 = {sharedPreferences.getInt("IeqPresetForMusic", sharedPreferences2.getInt("IeqPresetForMusic", this.mDefaultValueConfiguration.getDefaultIeqPreset()))};
                dolbyAudioEffect.setDapParameter(2, DsParams.IntelligentEqualizerPreset.toInt(), iArr3);
                Arrays.fill(iArr, 0);
                if (sharedPreferences.contains("GeqGainsForMusic")) {
                    jSONArray3 = new JSONArray(sharedPreferences.getString("GeqGainsForMusic", "[]"));
                } else {
                    jSONArray3 = new JSONArray(sharedPreferences2.getString("GeqGainsForMusic", "[]"));
                }
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    iArr[i3] = jSONArray3.getInt(i3);
                }
                dolbyAudioEffect.setDapParameter(2, DsParams.GraphicEqualizerBandGains.toInt(), iArr);
                iArr3[0] = sharedPreferences.getInt("IeqPresetForCustom", sharedPreferences2.getInt("IeqPresetForCustom", this.mDefaultValueConfiguration.getDefaultIeqPreset()));
                dolbyAudioEffect.setDapParameter(3, DsParams.IntelligentEqualizerPreset.toInt(), iArr3);
                Arrays.fill(iArr, 0);
                if (sharedPreferences.contains("GeqGainsForCustom")) {
                    jSONArray4 = new JSONArray(sharedPreferences.getString("GeqGainsForCustom", "[]"));
                } else {
                    jSONArray4 = new JSONArray(sharedPreferences2.getString("GeqGainsForCustom", "[]"));
                }
                for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                    iArr[i4] = jSONArray4.getInt(i4);
                }
                dolbyAudioEffect.setDapParameter(3, DsParams.GraphicEqualizerBandGains.toInt(), iArr);
                iArr2[0] = sharedPreferences.getInt("DialogEnhancerAmountForCustom", sharedPreferences2.getInt("DialogEnhancerAmountForCustom", this.mDefaultValueConfiguration.getDefaultDEAmount()));
                dolbyAudioEffect.setDapParameter(3, DsParams.DialogEnhancementAmount.toInt(), iArr2);
                SystemClock.sleep(300L);
                dolbyAudioEffect.setProfile(sharedPreferences.getInt("dsProfile", sharedPreferences2.getInt("dsProfile", this.mDefaultValueConfiguration.getDefaultDsProfile())));
                dolbyAudioEffect.release();
                str = "setDaxSettingsForUser::dolbyAudio.release()";
            } catch (Exception e3) {
                e = e3;
                str = "setDaxSettingsForUser::dolbyAudio.release()";
            } catch (Throwable th3) {
                th = th3;
                str = "setDaxSettingsForUser::dolbyAudio.release()";
            }
        } catch (Exception e4) {
            e = e4;
            str = "setDaxSettingsForUser::dolbyAudio.release()";
            dolbyAudioEffect2 = null;
        } catch (Throwable th4) {
            th = th4;
            str = "setDaxSettingsForUser::dolbyAudio.release()";
            dolbyAudioEffect = null;
        }
        Log.d("DaxService", str);
    }

    private void initDaxSettings() {
        DolbyAudioEffect dolbyAudioEffect;
        DolbyAudioEffect dolbyAudioEffect2 = null;
        try {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("dap_settings_default", 0);
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                if (sharedPreferences.getBoolean("firstrun", true)) {
                    Log.d("DaxService", "initDaxSettings,first run");
                    sharedPreferences.edit().putBoolean("firstrun", false).apply();
                    dolbyAudioEffect = new DolbyAudioEffect(1, 0);
                    try {
                        Log.d("DaxService", "initDaxSettings(): Create the effect with control priority 1");
                        int profile = dolbyAudioEffect.getProfile();
                        editorEdit.putBoolean("dsState", dolbyAudioEffect.getDsOn());
                        editorEdit.putInt("dsProfile", profile);
                        for (int i = 0; i < 4; i++) {
                            int[] dapParameter = dolbyAudioEffect.getDapParameter(i, DsParams.GraphicEqualizerBandGains.toInt());
                            JSONArray jSONArray = new JSONArray();
                            for (int i2 : dapParameter) {
                                jSONArray.put(i2);
                            }
                            int[] dapParameter2 = dolbyAudioEffect.getDapParameter(i, DsParams.DialogEnhancementAmount.toInt());
                            int[] dapParameter3 = dolbyAudioEffect.getDapParameter(i, DsParams.IntelligentEqualizerPreset.toInt());
                            if (i == 0) {
                                editorEdit.putString("GeqGainsForDynamic", jSONArray.toString());
                            } else if (i == 1) {
                                editorEdit.putString("GeqGainsForMovie", jSONArray.toString());
                                editorEdit.putInt("DialogEnhancerAmountForMovie", dapParameter2[0]);
                            } else if (i == 2) {
                                editorEdit.putString("GeqGainsForMusic", jSONArray.toString());
                                editorEdit.putInt("IeqPresetForMusic", dapParameter3[0]);
                            } else if (i == 3) {
                                editorEdit.putString("GeqGainsForCustom", jSONArray.toString());
                                editorEdit.putInt("IeqPresetForCustom", dapParameter3[0]);
                                editorEdit.putInt("DialogEnhancerAmountForCustom", dapParameter2[0]);
                            }
                        }
                        editorEdit.apply();
                    } catch (Exception e) {
                        e = e;
                        dolbyAudioEffect2 = dolbyAudioEffect;
                        e.printStackTrace();
                        if (dolbyAudioEffect2 != null) {
                            dolbyAudioEffect2.release();
                            Log.e("DaxService", "initDaxSettings::dolbyAudio.release()");
                            return;
                        }
                        return;
                    } catch (Throwable unused) {
                        if (dolbyAudioEffect != null) {
                            dolbyAudioEffect.release();
                            Log.e("DaxService", "initDaxSettings::dolbyAudio.release()");
                            return;
                        }
                        return;
                    }
                } else {
                    setDaxSettingsForUser();
                    dolbyAudioEffect = null;
                }
                if (dolbyAudioEffect != null) {
                    dolbyAudioEffect.release();
                    Log.e("DaxService", "initDaxSettings::dolbyAudio.release()");
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable unused2) {
            dolbyAudioEffect = null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i("DaxService", "onCreate() executed");
        init(this);
        this.mDefaultValueConfiguration = new DefaultValues(this);
        initDaxSettings();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("dolby_dap_params_update");
        intentFilter.addAction("audio_server_restarted");
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction("android.intent.action.USER_REMOVED");
        intentFilter.addAction("android.intent.action.USER_ADDED");
        intentFilter.addAction("android.intent.action.REBOOT");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        registerReceiverAsUser(this.mIntentReceiver, UserHandle.CURRENT_OR_SELF, intentFilter, null, null);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i("DaxService", "onStartCommand() executed");
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.i("DaxService", "onDestory() executed");
        this.mDolbyAudio.release();
        unregisterReceiver(this.mIntentReceiver);
        release();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i("DaxService", "onBind()");
        return null;
    }
}
