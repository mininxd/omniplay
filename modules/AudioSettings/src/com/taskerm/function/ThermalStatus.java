package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum ThermalStatus {
    None(0, R.string.word_none),
    Light(1, R.string.word_light),
    Moderate(2, R.string.word_moderate),
    Severe(3, R.string.word_severe),
    Critical(4, R.string.word_critical),
    Emergency(5, R.string.word_emergency),
    Shutdown(6, R.string.word_shutdown);

    private final int intValue;
    private final int textResId;

    ThermalStatus(int i, int i2) {
        this.intValue = i;
        this.textResId = i2;
    }

    public final int getIntValue() {
        return this.intValue;
    }

    public final int getTextResId() {
        return this.textResId;
    }
}
