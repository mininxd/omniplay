package com.dolby.daxservice;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class DefaultValues {
    private int default_de_amount;
    private int default_ds_profile;
    private boolean default_ds_state;
    private int[] default_geq_gains;
    private int default_ieq_preset;
    private final boolean DEFAULT_DS_STATE = true;
    private final int DEFAULT_DS_PROFILE = 0;
    private final int DEFAULT_IEQ_PRESET = 0;
    private final int DEFAULT_DE_AMOUNT = 6;
    private final int[] DEFAULT_GEQ_GAINS = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    DefaultValues(Context context) {
        this.default_ds_state = true;
        this.default_ds_profile = 0;
        this.default_ieq_preset = 0;
        this.default_de_amount = 6;
        this.default_geq_gains = this.DEFAULT_GEQ_GAINS;
        try {
            this.default_ds_state = context.getResources().getBoolean(R.bool.default_state);
            this.default_ds_profile = context.getResources().getInteger(R.integer.default_profile);
            this.default_ieq_preset = context.getResources().getInteger(R.integer.default_ieqPreset);
            this.default_de_amount = context.getResources().getInteger(R.integer.default_DeAmount);
            this.default_geq_gains = context.getResources().getIntArray(R.array.default_GeqGains);
        } catch (NullPointerException | NumberFormatException unused) {
            this.default_ds_state = Boolean.FALSE.booleanValue();
            this.default_ds_profile = 0;
            this.default_ieq_preset = 0;
            this.default_de_amount = 6;
            this.default_geq_gains = this.DEFAULT_GEQ_GAINS;
        }
    }

    public boolean getDefaultDsState() {
        return this.default_ds_state;
    }

    public int getDefaultDsProfile() {
        return this.default_ds_profile;
    }

    public int getDefaultIeqPreset() {
        return this.default_ieq_preset;
    }

    public int getDefaultDEAmount() {
        return this.default_de_amount;
    }
}
