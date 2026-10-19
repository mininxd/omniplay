package com.joaomgcd.taskerm.keyboard;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public enum e {
    Shift(1),
    Control(Integer.valueOf(NotificationCompat.FLAG_BUBBLE)),
    Alt(2),
    Long(null);


    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Integer f7595f;

    e(Integer num) {
        this.f7595f = num;
    }

    public final Integer a() {
        return this.f7595f;
    }
}
