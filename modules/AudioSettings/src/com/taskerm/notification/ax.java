package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.service.notification.StatusBarNotification;

/* JADX INFO: loaded from: classes.dex */
public final class ax {
    public static final aw a(StatusBarNotification statusBarNotification, Context context) {
        c.f.b.k.b(statusBarNotification, "receiver$0");
        c.f.b.k.b(context, "context");
        return new aw(context, statusBarNotification);
    }

    public static final void a(String str, boolean z, String str2) {
        c.f.b.k.b(str, "packageName");
        if (com.joaomgcd.taskerm.helper.r.f7464c.b()) {
            new ay(str, z, str2, null, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null).r();
        }
    }
}
