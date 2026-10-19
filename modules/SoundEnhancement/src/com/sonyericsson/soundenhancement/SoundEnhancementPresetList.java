package com.sonyericsson.soundenhancement;

import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementPresetList {
    private boolean mIsRtlAlphabet = false;
    String[] mNoiseCancelingEnvironmentNamesOfUiListOrder;
    int[] mNoiseCancelingEnvironmentOfUiListOrder;
    String[] mSurroundPresetNamesOfUiListOrder;
    private String[] mSurroundPresetNamesOfUiListOrderLtr;
    private String[] mSurroundPresetNamesOfUiListOrderRtl;
    int[] mSurroundPresetOfUiListOrder;
    private int[] mSurroundPresetOfUiListOrderLtr;
    private int[] mSurroundPresetOfUiListOrderRtl;

    public void queryAvailableSurroundPresets(String[] presetNames) {
        if (presetNames == null || this.mSurroundPresetNamesOfUiListOrder != null) {
            return;
        }
        int avaliableCount = 0;
        for (int i = 0; i < presetNames.length; i++) {
            if (presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB) || presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL) || presetNames[i].equals("Off") || presetNames[i].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
                avaliableCount++;
            }
        }
        this.mSurroundPresetNamesOfUiListOrderLtr = new String[avaliableCount];
        this.mSurroundPresetOfUiListOrderLtr = new int[avaliableCount];
        this.mSurroundPresetNamesOfUiListOrderRtl = new String[avaliableCount];
        this.mSurroundPresetOfUiListOrderRtl = new int[avaliableCount];
        int avaliableCount2 = 0;
        for (int i2 = 0; i2 < presetNames.length; i2++) {
            if (presetNames[i2].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CLUB) || presetNames[i2].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_CONCERT_HALL) || presetNames[i2].equals("Off") || presetNames[i2].equals(AudioEffectUtilities.PRESET_NAME_SURROUND_STUDIO)) {
                this.mSurroundPresetNamesOfUiListOrderLtr[avaliableCount2] = presetNames[i2];
                this.mSurroundPresetOfUiListOrderLtr[avaliableCount2] = i2;
                String[] strArr = this.mSurroundPresetNamesOfUiListOrderRtl;
                strArr[(strArr.length - avaliableCount2) - 1] = presetNames[i2];
                int[] iArr = this.mSurroundPresetOfUiListOrderRtl;
                iArr[(iArr.length - avaliableCount2) - 1] = i2;
                avaliableCount2++;
            }
        }
        setIsRtlAlphabet(this.mIsRtlAlphabet);
    }

    public String[] getSurroundPresetNames() {
        String[] strArr = this.mSurroundPresetNamesOfUiListOrder;
        if (strArr == null) {
            throw new IllegalStateException("Did not set preset names.");
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public int getSurroundPresetFromUiListIndex(int uiListIndex) {
        int[] iArr = this.mSurroundPresetOfUiListOrder;
        if (iArr == null) {
            throw new IllegalStateException("Did not set preset names.");
        }
        if (uiListIndex < 0 || uiListIndex >= iArr.length) {
            throw new IllegalArgumentException("Invalid argument " + uiListIndex + ".");
        }
        return iArr[uiListIndex];
    }

    public int getUiListIndexFromSurroundPreset(int libPreset) {
        if (this.mSurroundPresetOfUiListOrder == null) {
            throw new IllegalStateException("Did not set preset names.");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.mSurroundPresetOfUiListOrder;
            if (i < iArr.length) {
                if (iArr[i] != libPreset) {
                    i++;
                } else {
                    return i;
                }
            } else {
                throw new IllegalArgumentException("Invalid argument " + libPreset + ".");
            }
        }
    }

    public void queryAvailableNoiseCancelingEnvironment(String[] noiseCancelingEnvironmentNames) {
        if (noiseCancelingEnvironmentNames == null || this.mNoiseCancelingEnvironmentNamesOfUiListOrder != null) {
            return;
        }
        int avaliableCount = 0;
        String[] noiseCancelingEnvironmentList = new String[noiseCancelingEnvironmentNames.length];
        int[] noiseCancelingEnvironmentNumbers = new int[noiseCancelingEnvironmentNames.length];
        for (int i = 0; i < noiseCancelingEnvironmentNames.length; i++) {
            noiseCancelingEnvironmentList[avaliableCount] = noiseCancelingEnvironmentNames[i];
            noiseCancelingEnvironmentNumbers[avaliableCount] = i;
            avaliableCount++;
        }
        this.mNoiseCancelingEnvironmentNamesOfUiListOrder = noiseCancelingEnvironmentList;
        this.mNoiseCancelingEnvironmentOfUiListOrder = noiseCancelingEnvironmentNumbers;
    }

    public String[] getNoiseCancelingEnvironmentNames() {
        String[] strArr = this.mNoiseCancelingEnvironmentNamesOfUiListOrder;
        if (strArr == null) {
            throw new IllegalStateException("Did not set preset names.");
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public int getNoiseCancelingEnvironmentFromUiListIndex(int uiListIndex) {
        int[] iArr = this.mNoiseCancelingEnvironmentOfUiListOrder;
        if (iArr == null) {
            throw new IllegalStateException("Did not set preset names.");
        }
        if (uiListIndex < 0 || uiListIndex >= iArr.length) {
            throw new IllegalArgumentException("Invalid argument " + uiListIndex + ".");
        }
        return iArr[uiListIndex];
    }

    public int getUiListIndexFromNoiseCancelingEnvironment(int libNoiseCancelingEnvironment) {
        if (this.mNoiseCancelingEnvironmentOfUiListOrder == null) {
            throw new IllegalStateException("Did not set preset names.");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.mNoiseCancelingEnvironmentOfUiListOrder;
            if (i < iArr.length) {
                if (iArr[i] != libNoiseCancelingEnvironment) {
                    i++;
                } else {
                    return i;
                }
            } else {
                throw new IllegalArgumentException("Invalid argument " + libNoiseCancelingEnvironment + ".");
            }
        }
    }

    public void setIsRtlAlphabet(boolean isRtl) {
        this.mIsRtlAlphabet = isRtl;
        if (isRtl) {
            this.mSurroundPresetNamesOfUiListOrder = this.mSurroundPresetNamesOfUiListOrderRtl;
            this.mSurroundPresetOfUiListOrder = this.mSurroundPresetOfUiListOrderRtl;
        } else {
            this.mSurroundPresetNamesOfUiListOrder = this.mSurroundPresetNamesOfUiListOrderLtr;
            this.mSurroundPresetOfUiListOrder = this.mSurroundPresetOfUiListOrderLtr;
        }
    }
}
