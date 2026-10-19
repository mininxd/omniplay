package com.joaomgcd.taskerm.inputoutput;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public enum Toggle {
    Off,
    On,
    Toggle;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:8:0x0016 A[ORIG_RETURN, RETURN] */
    public final boolean getNewValue(boolean z) {
        switch (k.f7550a[ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                if (z) {
                    return false;
                }
                return true;
            default:
                throw new c.i();
        }
    }
}
