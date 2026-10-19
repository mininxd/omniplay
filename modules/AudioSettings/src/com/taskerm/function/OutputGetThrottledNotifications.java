package com.joaomgcd.taskerm.function;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class OutputGetThrottledNotifications {
    private final ThrottledNotification[] throttledNotifications;

    public OutputGetThrottledNotifications(ThrottledNotification[] throttledNotificationArr) {
        k.b(throttledNotificationArr, "throttledNotifications");
        this.throttledNotifications = throttledNotificationArr;
    }

    public final ThrottledNotification[] getThrottledNotifications() {
        return this.throttledNotifications;
    }
}
