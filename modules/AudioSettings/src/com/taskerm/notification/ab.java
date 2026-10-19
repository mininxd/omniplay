package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.dinglisch.android.taskerm.db;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends o {
    /* JADX WARN: Illegal instructions before constructor call */
    public ab(Context context) {
        c.f.b.k.b(context, "context");
        Intent intentH = db.h();
        c.f.b.k.a((Object) intentH, "NotificationListenerInterface.getSettingsIntent()");
        super(context, intentH);
    }
}
