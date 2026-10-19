package com.dolby.dax;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum DsParams {
    DolbyHeadphoneVirtualizerControl(101),
    DolbyVirtualSpeakerVirtualizerControl(102),
    DolbyVolumeLevelerEnable(103),
    IntelligentEqualizerPreset(104),
    DialogEnhancementEnable(105),
    GraphicEqualizerEnable(106),
    IntelligentEqualizerAmount(107),
    DialogEnhancementAmount(108),
    DialogEnhancementDucking(109),
    GraphicEqualizerBandGains(110),
    BassEnable(111);

    private static final String[] DAP2_PARAM_NAMES;
    public static final int EFFECT_PARAM_CPDP_VALUES = 5;
    public static final int EFFECT_PARAM_EFFECT_ENABLE = 0;
    public static final int EFFECT_PARAM_MONO_SPEAKER = 218103808;
    public static final int EFFECT_PARAM_PROFILE = 167772160;
    public static final int EFFECT_PARAM_PROFILE_NAME = 6;
    public static final int EFFECT_PARAM_PROFILE_NAME_LEN = 7;
    public static final int EFFECT_PARAM_PROFILE_NUM = 50331648;
    public static final int EFFECT_PARAM_PROFILE_PARAMETER = 16777216;
    public static final int EFFECT_PARAM_PROFILE_PORT_PARAMETER = 33554432;
    public static final int EFFECT_PARAM_PROFILE_SETTINGS_MODIFIED = 184549376;
    public static final int EFFECT_PARAM_RESET_PROFILE_SETTINGS = 201326592;
    public static final int EFFECT_PARAM_SELECTED_TUNING = 4;
    public static final int EFFECT_PARAM_TUNING = 1;
    public static final int EFFECT_PARAM_TUNING_LEN = 2;
    public static final Map<Integer, Integer> kParamToLen;
    private static final DsParams[] params;
    private int id_;

    static {
        DsParams dsParams = DolbyHeadphoneVirtualizerControl;
        DsParams dsParams2 = DolbyVirtualSpeakerVirtualizerControl;
        DsParams dsParams3 = DolbyVolumeLevelerEnable;
        DsParams dsParams4 = IntelligentEqualizerPreset;
        DsParams dsParams5 = DialogEnhancementEnable;
        DsParams dsParams6 = GraphicEqualizerEnable;
        DsParams dsParams7 = IntelligentEqualizerAmount;
        DsParams dsParams8 = DialogEnhancementAmount;
        DsParams dsParams9 = DialogEnhancementDucking;
        DsParams dsParams10 = GraphicEqualizerBandGains;
        DsParams dsParams11 = BassEnable;
        DAP2_PARAM_NAMES = new String[]{"null", "vdhe", "vspe", "dvle", "ieid", "deon", "geon", "iea", "dea", "ded", "gebg", "beon"};
        params = new DsParams[]{dsParams, dsParams2, dsParams3, dsParams4, dsParams5, dsParams6, dsParams7, dsParams8, dsParams9, dsParams10, dsParams11};
        kParamToLen = new HashMap<Integer, Integer>() { // from class: com.dolby.dax.DsParams.1
            {
                put(Integer.valueOf(DsParams.GraphicEqualizerBandGains.toInt()), 20);
            }
        };
    }

    DsParams(int id) {
        this.id_ = id;
    }

    public int toInt() {
        return this.id_;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.id_;
        if (i > 100 && i < 112) {
            String name = DAP2_PARAM_NAMES[i - 100];
            return name;
        }
        return "error";
    }

    public static DsParams FromInt(int i) {
        if (i > 100 && i < 112) {
            DsParams item = params[(i - 100) - 1];
            return item;
        }
        return null;
    }

    public static DsParams FromString(String name) {
        if (name == null) {
            return null;
        }
        for (int i = 100; i < 111; i++) {
            if (DAP2_PARAM_NAMES[(i - 100) + 1].equalsIgnoreCase(name.trim())) {
                DsParams item = params[i - 100];
                return item;
            }
        }
        return null;
    }
}
