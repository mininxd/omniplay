package com.joaomgcd.taskerm.util;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public enum am {
    Create(256),
    Delete(NotificationCompat.FLAG_GROUP_SUMMARY),
    Modify(2),
    MovedFrom(64),
    MovedTo(128),
    ClosedWrite(8);

    private final int h;

    am(int i) {
        this.h = i;
    }

    public final int a() {
        return this.h;
    }
}
