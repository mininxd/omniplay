package com.joaomgcd.taskerm.notification;

import android.app.NotificationManager;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class au {
    public static final boolean a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return com.joaomgcd.taskerm.util.f.f9323b.r() && com.joaomgcd.taskerm.util.g.b(context);
    }

    public static final NotificationManager b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (NotificationManager) context.getSystemService("notification");
    }
}
