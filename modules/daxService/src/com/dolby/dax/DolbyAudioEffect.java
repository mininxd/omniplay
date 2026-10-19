package com.dolby.dax;

import android.media.audiofx.AudioEffect;
import android.os.SystemProperties;
import android.util.Log;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class DolbyAudioEffect extends AudioEffect {
    public static final UUID EFFECT_TYPE_DOLBY_AUDIO_PROCESSING;
    private BaseParameterListener mBaseParamListener;
    private int mNumOfProfiles;
    private OnParameterChangeListener mParamListener;
    private final Object mParamListenerLock;
    private boolean mProfileSupported;
    private int mSessionId;

    private class BaseParameterListener implements AudioEffect.OnParameterChangeListener {
    }

    public interface OnParameterChangeListener {
    }

    static {
        EFFECT_TYPE_DOLBY_AUDIO_PROCESSING = UUID.fromString(!SystemProperties.get("ro.dolby.mod_uuid", "false").equals("true") ? "9d4921da-8225-4f29-aefa-39537a04bcaa" : "9d4921da-8225-4f29-aefa-5f7279756b69");
    }

    public DolbyAudioEffect(int i, int i2) throws RuntimeException {
        super(AudioEffect.EFFECT_TYPE_NULL, EFFECT_TYPE_DOLBY_AUDIO_PROCESSING, i, i2);
        this.mProfileSupported = true;
        this.mParamListener = null;
        this.mBaseParamListener = null;
        this.mParamListenerLock = new Object();
        if (i2 == 0) {
            Log.w("DolbyAudioEffect", "WARNING: attaching a DolbyAudioEffect to global output mix is deprecated!");
        }
        this.mSessionId = i2;
        this.mNumOfProfiles = getNumOfProfiles();
    }

    private static int[] byteArrayToInt32Array(byte[] bArr, int i, int i2) {
        int length = (bArr.length - i) >> 2;
        if (length <= i2) {
            i2 = length;
        }
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 4;
            iArr[i3] = (bArr[i4 + i] & 255) | ((bArr[(i4 + 3) + i] & 255) << 24) | ((bArr[(i4 + 2) + i] & 255) << 16) | ((bArr[(i4 + 1) + i] & 255) << 8);
        }
        return iArr;
    }

    private static int byteArrayToInt32(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8);
    }

    private static int int32ToByteArray(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 8) & 255);
        bArr[i4] = (byte) ((i >>> 16) & 255);
        bArr[i4 + 1] = (byte) ((i >>> 24) & 255);
        return 4;
    }

    private static int int32ArrayToByteArray(int[] iArr, byte[] bArr, int i) {
        int length = iArr.length;
        for (int i2 : iArr) {
            int i3 = i + 1;
            bArr[i] = (byte) ((i2 >>> 0) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >>> 8) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((i2 >>> 16) & 255);
            i = i5 + 1;
            bArr[i5] = (byte) ((i2 >>> 24) & 255);
        }
        return length << 2;
    }

    protected int setBoolParam(int i, boolean z) {
        byte[] bArr = new byte[12];
        int iInt32ToByteArray = int32ToByteArray(i, bArr, 0) + 0;
        int32ToByteArray(z ? 1 : 0, bArr, iInt32ToByteArray + int32ToByteArray(1, bArr, iInt32ToByteArray));
        checkReturnValue(setParameter(5, bArr));
        return 0;
    }

    protected int setIntParam(int i, int i2) {
        byte[] bArr = new byte[12];
        int iInt32ToByteArray = int32ToByteArray(i, bArr, 0) + 0;
        int32ToByteArray(i2, bArr, iInt32ToByteArray + int32ToByteArray(1, bArr, iInt32ToByteArray));
        checkReturnValue(setParameter(5, bArr));
        return 0;
    }

    protected boolean getBoolParam(int i) {
        byte[] bArr = new byte[12];
        int32ToByteArray(i, bArr, 0);
        checkReturnValue(getParameter(i + 5, bArr));
        return byteArrayToInt32(bArr) > 0;
    }

    protected int getIntParam(int i) {
        byte[] bArr = new byte[12];
        int32ToByteArray(i, bArr, 0);
        checkReturnValue(getParameter(i + 5, bArr));
        return byteArrayToInt32(bArr);
    }

    private void checkReturnValue(int i) {
        if (i < 0) {
            if (i == -5) {
                throw new UnsupportedOperationException("DolbyAudioEffect: invalid parameter operation");
            }
            if (i == -4) {
                throw new IllegalArgumentException("DolbyAudioEffect: bad parameter value");
            }
            throw new RuntimeException("DolbyAudioEffect: set/get parameter error");
        }
    }

    public void setDsOn(boolean z) {
        setBoolParam(0, z);
        if (SystemProperties.get("ro.dolby.music_stream", "false").equals("true")) {
            return;
        }
        super.setEnabled(z);
    }

    public boolean getDsOn() {
        return getBoolParam(0);
    }

    public void setProfile(int i) throws IllegalArgumentException {
        if (i < 0 || i >= this.mNumOfProfiles) {
            Log.e("DolbyAudioEffect", "ERROR in setProfile(): Invalid profile index" + i);
            throw new IllegalArgumentException();
        }
        setIntParam(167772160, i);
    }

    public int getProfile() {
        return getIntParam(167772160);
    }

    public int getNumOfProfiles() {
        return getIntParam(50331648);
    }

    public boolean isMonoSpeaker() {
        return getBoolParam(218103808);
    }

    public void setDapParameter(int i, int i2, int[] iArr) throws UnsupportedOperationException, IllegalArgumentException {
        if (i < 0 || i >= this.mNumOfProfiles) {
            Log.e("DolbyAudioEffect", "ERROR in setProfileParameter(): Invalid profile index" + i);
            throw new IllegalArgumentException();
        }
        if (isMonoSpeaker() && i2 == DsParams.DolbyVirtualSpeakerVirtualizerControl.toInt()) {
            throw new UnsupportedOperationException();
        }
        int length = iArr.length;
        byte[] bArr = new byte[(length + 4) * 4];
        int iInt32ToByteArray = int32ToByteArray(16777216, bArr, 0) + 0;
        int iInt32ToByteArray2 = iInt32ToByteArray + int32ToByteArray(length + 1, bArr, iInt32ToByteArray);
        int iInt32ToByteArray3 = iInt32ToByteArray2 + int32ToByteArray(i, bArr, iInt32ToByteArray2);
        int32ArrayToByteArray(iArr, bArr, iInt32ToByteArray3 + int32ToByteArray(i2, bArr, iInt32ToByteArray3));
        checkReturnValue(setParameter(5, bArr));
    }

    public int[] getDapParameter(int i, int i2) throws IllegalArgumentException {
        int iIntValue;
        if (i < 0 || i >= this.mNumOfProfiles) {
            Log.e("DolbyAudioEffect", "ERROR in getDapParameter(): Invalid profile index" + i);
            throw new IllegalArgumentException();
        }
        try {
            iIntValue = DsParams.kParamToLen.get(Integer.valueOf(i2)).intValue();
        } catch (NullPointerException unused) {
            iIntValue = 1;
        }
        byte[] bArr = new byte[(iIntValue + 2) * 4];
        checkReturnValue(getParameter((i2 << 16) + 16777221 + (i << 8), bArr));
        return byteArrayToInt32Array(bArr, 0, iIntValue);
    }
}
