package com.joaomgcd.taskerm.rtt;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RTTException extends RuntimeException {
    private final int code;

    public RTTException(int i) {
        super(a.b(i));
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }
}
