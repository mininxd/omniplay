package com.dolby.dax;

import android.media.audiofx.AudioEffect;
import android.os.SystemProperties;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class DolbyAudioEffect extends AudioEffect {
    private static final int BASIC_BUF_SIZE = 3;
    private static final int BYTES_PER_INT = 4;
    private static final String DAX_VERSION = "DAX3";
    private static final boolean DEBUG = false;
    public static final UUID EFFECT_TYPE_DOLBY_AUDIO_PROCESSING;
    private static final int PROFILE_BUF_SIZE = 4;
    private static final int PROFILE_PORT_BUF_SIZE = 5;
    private static final String TAG = "DolbyAudioEffect";
    private BaseParameterListener mBaseParamListener;
    private int mNumOfProfiles;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock;
    private boolean mProfileSupported;
    private int mSessionId;

    public interface OnParameterChangeListener {
        void onParameterChange(DolbyAudioEffect dolbyAudioEffect, int i, int i2, short s);
    }

    static {
        EFFECT_TYPE_DOLBY_AUDIO_PROCESSING = UUID.fromString(!SystemProperties.get("ro.dolby.mod_uuid", "false").equals("true") ? "9d4921da-8225-4f29-aefa-39537a04bcaa" : "9d4921da-8225-4f29-aefa-5f7279756b69");
    }

    public DolbyAudioEffect(int priority, int audioSession) throws RuntimeException {
        super(EFFECT_TYPE_NULL, EFFECT_TYPE_DOLBY_AUDIO_PROCESSING, priority, audioSession);
        this.mProfileSupported = true;
        this.mParamListener = null;
        this.mBaseParamListener = null;
        this.mParamListenerLock = new Object();
        if (audioSession == 0) {
            Log.w(TAG, "WARNING: attaching a DolbyAudioEffect to global output mix is deprecated!");
        }
        this.mSessionId = audioSession;
        this.mNumOfProfiles = getNumOfProfiles();
    }

    private static int[] byteArrayToInt32Array(byte[] ba, int index, int length) {
        int srcLength = ba.length - index;
        int destLength = (srcLength >> 2) > length ? length : srcLength >> 2;
        int[] dest = new int[destLength];
        for (int i = 0; i < destLength; i++) {
            dest[i] = ((ba[((i * 4) + 3) + index] & 255) << 24) | ((ba[((i * 4) + 2) + index] & 255) << 16) | ((ba[((i * 4) + 1) + index] & 255) << 8) | (ba[(i * 4) + index] & 255);
        }
        return dest;
    }

    private static int byteArrayToInt32(byte[] ba) {
        int dest = ((ba[3] & 255) << 24) | ((ba[2] & 255) << 16) | ((ba[1] & 255) << 8) | (ba[0] & 255);
        return dest;
    }

    private static int int32ToByteArray(int src, byte[] dst, int index) {
        int index2 = index + 1;
        dst[index] = (byte) (src & 255);
        int index3 = index2 + 1;
        dst[index2] = (byte) ((src >>> 8) & 255);
        dst[index3] = (byte) ((src >>> 16) & 255);
        dst[index3 + 1] = (byte) ((src >>> 24) & 255);
        return 4;
    }

    private static int int32ArrayToByteArray(int[] src, byte[] dst, int index) {
        int srcLen = src.length;
        int j = index;
        for (int x : src) {
            int j2 = j + 1;
            dst[j] = (byte) ((x >>> 0) & 255);
            int j3 = j2 + 1;
            dst[j2] = (byte) ((x >>> 8) & 255);
            int j4 = j3 + 1;
            dst[j3] = (byte) ((x >>> 16) & 255);
            j = j4 + 1;
            dst[j4] = (byte) ((x >>> 24) & 255);
        }
        int i = srcLen << 2;
        return i;
    }

    protected int setBoolParam(int i, boolean z) {
        byte[] bArr = new byte[12];
        int iInt32ToByteArray = 0 + int32ToByteArray(i, bArr, 0);
        int iInt32ToByteArray2 = iInt32ToByteArray + int32ToByteArray(1, bArr, iInt32ToByteArray);
        int iInt32ToByteArray3 = iInt32ToByteArray2 + int32ToByteArray(z ? 1 : 0, bArr, iInt32ToByteArray2);
        checkReturnValue(setParameter(5, bArr));
        return 0;
    }

    protected int setIntParam(int effEvt, int val) {
        byte[] baValue = new byte[12];
        int index = 0 + int32ToByteArray(effEvt, baValue, 0);
        int index2 = index + int32ToByteArray(1, baValue, index);
        int iInt32ToByteArray = index2 + int32ToByteArray(val, baValue, index2);
        checkReturnValue(setParameter(5, baValue));
        return 0;
    }

    protected boolean getBoolParam(int effEvt) {
        byte[] baValue = new byte[12];
        int iInt32ToByteArray = 0 + int32ToByteArray(effEvt, baValue, 0);
        checkReturnValue(getParameter(effEvt + 5, baValue));
        int en = byteArrayToInt32(baValue);
        return en > 0;
    }

    protected int getIntParam(int effEvt) {
        byte[] baValue = new byte[12];
        int iInt32ToByteArray = 0 + int32ToByteArray(effEvt, baValue, 0);
        checkReturnValue(getParameter(effEvt + 5, baValue));
        int val = byteArrayToInt32(baValue);
        return val;
    }

    private void checkReturnValue(int ret) {
        if (ret < 0) {
            if (ret == -5) {
                throw new UnsupportedOperationException("DolbyAudioEffect: invalid parameter operation");
            }
            if (ret == -4) {
                throw new IllegalArgumentException("DolbyAudioEffect: bad parameter value");
            }
            throw new RuntimeException("DolbyAudioEffect: set/get parameter error");
        }
    }

    public void setDsOn(boolean enable) {
        setBoolParam(0, enable);
        if (SystemProperties.get("ro.dolby.music_stream", "false").equals("true")) {
            return;
        }
        super.setEnabled(enable);
    }

    public boolean getDsOn() {
        return getBoolParam(0);
    }

    public String getDsVersion() {
        return DAX_VERSION;
    }

    public String getApiVersion() {
        return "Dax-api-version";
    }

    public void setDialogEnhancerEnabled(boolean z) {
        setDapParameter(DsParams.DialogEnhancementEnable.toInt(), new int[]{z ? 1 : 0});
    }

    public boolean getDialogEnhancerEnabled() {
        int[] enabled = getDapParameter(DsParams.DialogEnhancementEnable.toInt());
        return enabled[0] != 0;
    }

    public void setDialogEnhancerAmount(int amount) throws IllegalArgumentException {
        if (amount < 0 || amount > 16) {
            Log.e(TAG, "ERROR in setDialogEnhancerAmount(): Invalid amount " + amount);
            throw new IllegalArgumentException();
        }
        int[] value = {amount};
        setDapParameter(DsParams.DialogEnhancementAmount.toInt(), value);
    }

    public int getDialogEnhancerAmount() {
        int[] amount = getDapParameter(DsParams.DialogEnhancementAmount.toInt());
        return amount[0];
    }

    public void setVolumeLevelerEnabled(boolean z) {
        setDapParameter(DsParams.DolbyVolumeLevelerEnable.toInt(), new int[]{z ? 1 : 0});
    }

    public boolean getVolumeLevelerEnabled() {
        int[] enabled = getDapParameter(DsParams.DolbyVolumeLevelerEnable.toInt());
        return enabled[0] != 0;
    }

    public void setHeadphoneVirtualizerEnabled(boolean z) {
        setDapParameter(DsParams.DolbyHeadphoneVirtualizerControl.toInt(), new int[]{z ? 1 : 0});
    }

    public boolean getHeadphoneVirtualizerEnabled() {
        int[] enabled = getDapParameter(DsParams.DolbyHeadphoneVirtualizerControl.toInt());
        return enabled[0] != 0;
    }

    public void setSpeakerVirtualizerEnabled(boolean z) throws UnsupportedOperationException {
        if (isMonoSpeaker()) {
            throw new UnsupportedOperationException();
        }
        setDapParameter(DsParams.DolbyVirtualSpeakerVirtualizerControl.toInt(), new int[]{z ? 1 : 0});
    }

    public boolean getSpeakerVirtualizerEnabled() {
        int[] enabled = getDapParameter(DsParams.DolbyVirtualSpeakerVirtualizerControl.toInt());
        return enabled[0] != 0;
    }

    public void setBassEnhancerEnabled(boolean z) {
        setDapParameter(DsParams.BassEnable.toInt(), new int[]{z ? 1 : 0});
    }

    public boolean getBassEnhancerEnabled() {
        int[] enabled = getDapParameter(DsParams.BassEnable.toInt());
        return enabled[0] != 0;
    }

    public void setIeqPreset(int preset) throws IllegalArgumentException {
        if (preset < 0 || preset > 3) {
            Log.e(TAG, "ERROR in setIeqPreset(): Invalid IEq preset index" + preset);
            throw new IllegalArgumentException();
        }
        int[] value = {preset};
        setDapParameter(DsParams.IntelligentEqualizerPreset.toInt(), value);
    }

    public int getIeqPreset() {
        int[] preset = getDapParameter(DsParams.IntelligentEqualizerPreset.toInt());
        return preset[0];
    }

    public boolean isGeqEnabled(int port) throws IllegalArgumentException {
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in isGeqEnabled(): Invalid port " + port);
            throw new IllegalArgumentException();
        }
        int[] enabled = getDapParameter(getProfile(), port, DsParams.GraphicEqualizerEnable.toInt());
        return enabled[0] != 0;
    }

    public void setGeqBandGains(int[] gains) throws IllegalArgumentException {
        if (gains == null || gains.length != 20) {
            Log.e(TAG, "ERROR in setGeqBandGains(): Invalid GEq gains for 20 frequency bands!");
            throw new IllegalArgumentException();
        }
        setDapParameter(DsParams.GraphicEqualizerBandGains.toInt(), gains);
    }

    public int[] getGeqBandGains() {
        return getDapParameter(DsParams.GraphicEqualizerBandGains.toInt());
    }

    public void setProfile(int profile) throws IllegalArgumentException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in setProfile(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        setIntParam(DsParams.EFFECT_PARAM_PROFILE, profile);
    }

    public int getProfile() {
        int profile = getIntParam(DsParams.EFFECT_PARAM_PROFILE);
        return profile;
    }

    public int getNumOfProfiles() {
        return getIntParam(DsParams.EFFECT_PARAM_PROFILE_NUM);
    }

    private int getProfileNameLength(int profile) throws IllegalArgumentException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in getProfileNameLength(): Invalid profile" + profile);
            throw new IllegalArgumentException();
        }
        byte[] baValue = new byte[4];
        int packProfile = profile << 16;
        checkReturnValue(getParameter(packProfile + 7, baValue));
        int len = byteArrayToInt32(baValue);
        return len;
    }

    public String getProfileName(int profile) throws IllegalArgumentException, UnsupportedEncodingException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in getProfileName(): Invalid profile" + profile);
            throw new IllegalArgumentException();
        }
        int len = getProfileNameLength(profile);
        byte[] baValue = new byte[((len + 4) >> 2) * 4];
        int packProfile = profile << 16;
        checkReturnValue(getParameter(packProfile + 6, baValue));
        try {
            String profileName = new String(baValue, "UTF-8");
            String[] list = profileName.split("\\s+");
            return list[0];
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
    }

    public boolean isMonoSpeaker() {
        return getBoolParam(DsParams.EFFECT_PARAM_MONO_SPEAKER);
    }

    public void setDapParameter(int paramId, int[] values) throws UnsupportedOperationException, IllegalArgumentException {
        setDapParameter(getProfile(), paramId, values);
    }

    public void setDapParameter(int profile, int paramId, int[] values) throws UnsupportedOperationException, IllegalArgumentException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in setProfileParameter(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        if (isMonoSpeaker() && paramId == DsParams.DolbyVirtualSpeakerVirtualizerControl.toInt()) {
            throw new UnsupportedOperationException();
        }
        int length = values.length;
        byte[] baValue = new byte[(length + 4) * 4];
        int index = 0 + int32ToByteArray(16777216, baValue, 0);
        int index2 = index + int32ToByteArray(length + 1, baValue, index);
        int index3 = index2 + int32ToByteArray(profile, baValue, index2);
        int index4 = index3 + int32ToByteArray(paramId, baValue, index3);
        int iInt32ArrayToByteArray = index4 + int32ArrayToByteArray(values, baValue, index4);
        checkReturnValue(setParameter(5, baValue));
    }

    public void setDapParameter(int profile, int port, int paramId, int[] values) throws UnsupportedOperationException, IllegalArgumentException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in setProfileParameter(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in setProfileParameter(): Invalid port" + port);
            throw new IllegalArgumentException();
        }
        if (isMonoSpeaker() && paramId == DsParams.DolbyVirtualSpeakerVirtualizerControl.toInt()) {
            throw new UnsupportedOperationException();
        }
        int length = values.length;
        byte[] baValue = new byte[(length + 5) * 4];
        int index = 0 + int32ToByteArray(DsParams.EFFECT_PARAM_PROFILE_PORT_PARAMETER, baValue, 0);
        int index2 = index + int32ToByteArray(length + 1, baValue, index);
        int index3 = index2 + int32ToByteArray(profile, baValue, index2);
        int index4 = index3 + int32ToByteArray(port, baValue, index3);
        int index5 = index4 + int32ToByteArray(paramId, baValue, index4);
        int iInt32ArrayToByteArray = index5 + int32ArrayToByteArray(values, baValue, index5);
        checkReturnValue(setParameter(5, baValue));
    }

    public int[] getDapParameter(int paramId) throws IllegalArgumentException {
        return getDapParameter(getProfile(), paramId);
    }

    public int[] getDapParameter(int profile, int paramId) throws IllegalArgumentException {
        int len;
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in getDapParameter(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        try {
            len = DsParams.kParamToLen.get(Integer.valueOf(paramId)).intValue();
        } catch (NullPointerException e) {
            len = 1;
        }
        byte[] baValue = new byte[(len + 2) * 4];
        int packId = paramId << 16;
        int profileId = profile << 8;
        checkReturnValue(getParameter(16777221 + packId + profileId, baValue));
        int[] param = byteArrayToInt32Array(baValue, 0, len);
        return param;
    }

    public int[] getDapParameter(int profile, int port, int paramId) throws IllegalArgumentException {
        int len;
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in getDapParameter(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in getDapParameter(): Invalid port" + port);
            throw new IllegalArgumentException();
        }
        try {
            len = DsParams.kParamToLen.get(Integer.valueOf(paramId)).intValue();
        } catch (NullPointerException e) {
            len = 1;
        }
        byte[] baValue = new byte[(len + 2) * 4];
        int packId = paramId << 16;
        int profileportId = (port << 8) | (profile << 12);
        checkReturnValue(getParameter(33554437 + packId + profileportId, baValue));
        int[] param = byteArrayToInt32Array(baValue, 0, len);
        return param;
    }

    public boolean isProfileSpecificSettingsModified(int profile) throws IllegalArgumentException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in isProfileSpecificSettingsModified(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        byte[] baValue = new byte[12];
        int packProfile = profile << 16;
        checkReturnValue(getParameter(184549381 + packProfile, baValue));
        int en = byteArrayToInt32(baValue);
        return en > 0;
    }

    public void resetProfileSpecificSettings(int profile) throws IllegalArgumentException {
        if (profile < 0 || profile >= this.mNumOfProfiles) {
            Log.e(TAG, "ERROR in resetProfileSpecificSettings(): Invalid profile index" + profile);
            throw new IllegalArgumentException();
        }
        setIntParam(DsParams.EFFECT_PARAM_RESET_PROFILE_SETTINGS, profile);
    }

    private int getPortDeviceNameLength(int port) throws IllegalArgumentException {
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in getPortDeviceNameLength(): Invalid port" + port);
            throw new IllegalArgumentException();
        }
        byte[] baValue = new byte[4];
        int packPort = port << 16;
        checkReturnValue(getParameter(packPort + 2, baValue));
        int len = byteArrayToInt32(baValue);
        return len;
    }

    public String[] getTuningDevicesList(int port) throws IllegalArgumentException, UnsupportedEncodingException {
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in getTuningDevicesList(): Invalid port" + port);
            throw new IllegalArgumentException();
        }
        int nameLen = getPortDeviceNameLength(port);
        int len = ((nameLen + 4) >> 2) * 4;
        byte[] baValue = new byte[len];
        int packPort = port << 16;
        checkReturnValue(getParameter(packPort + 1, baValue));
        try {
            String temp = new String(baValue, "UTF-8");
            String devName = temp.substring(0, nameLen - 1);
            String[] tuningList = devName.split("\\s+");
            return tuningList;
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
    }

    public String getSelectedTuningDevice(int port) throws IllegalArgumentException, UnsupportedEncodingException {
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in getSelectedTuningDevice(): Invalid port" + port);
            throw new IllegalArgumentException();
        }
        int len = getPortDeviceNameLength(port);
        byte[] baValue = new byte[((len + 4) >> 2) * 4];
        int packPort = port << 16;
        checkReturnValue(getParameter(packPort + 4, baValue));
        try {
            String devName = new String(baValue, "UTF-8");
            String[] list = devName.split("\\s+");
            return list[0];
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
    }

    public void setSelectedTuningDevice(int port, String device) throws IllegalArgumentException {
        if (port < DsTuning.internal_speaker.toInt() || port > DsTuning.usb.toInt()) {
            Log.e(TAG, "ERROR in setSelectedTuningDevice(): Invalid port" + port);
            throw new IllegalArgumentException();
        }
        int devlen = device.length();
        byte[] baValue = new byte[devlen + 4];
        int index = 0 + int32ToByteArray(port, baValue, 0);
        System.arraycopy(device.getBytes(), 0, baValue, index, devlen);
        checkReturnValue(setParameter(4, baValue));
    }

    private class BaseParameterListener implements AudioEffect.OnParameterChangeListener {
        private BaseParameterListener() {
        }

        public void onParameterChange(AudioEffect effect, int status, byte[] param, byte[] value) {
            OnParameterChangeListener l = null;
            synchronized (DolbyAudioEffect.this.mParamListenerLock) {
                if (DolbyAudioEffect.this.mParamListener != null) {
                    l = DolbyAudioEffect.this.mParamListener;
                }
            }
            if (l != null) {
                int p = -1;
                short v = -1;
                if (param.length == 4) {
                    p = AudioEffect.byteArrayToInt(param, 0);
                }
                if (value.length == 2) {
                    v = AudioEffect.byteArrayToShort(value, 0);
                }
                if (p != -1 && v != -1) {
                    l.onParameterChange(DolbyAudioEffect.this, status, p, v);
                }
            }
        }
    }

    public void setParameterListener(OnParameterChangeListener listener) {
        synchronized (this.mParamListenerLock) {
            if (this.mParamListener == null) {
                this.mParamListener = listener;
                this.mBaseParamListener = new BaseParameterListener();
                super.setParameterListener(this.mBaseParamListener);
            }
        }
    }
}
