package com.joaomgcd.taskerm.google.vision;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public enum Likelihood {
    UNKNOWN(-1),
    VERY_UNLIKELY(1),
    UNLIKELY(2),
    POSSIBLE(3),
    LIKELY(4),
    VERY_LIKELY(5);

    private final int level;

    Likelihood(int i) {
        this.level = i;
    }

    public final int getLevel() {
        return this.level;
    }
}
