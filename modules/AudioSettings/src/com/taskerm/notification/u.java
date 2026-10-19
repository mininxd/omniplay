package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.dinglisch.android.taskerm.db;

/* JADX INFO: loaded from: classes.dex */
public final class u extends o {
    /* JADX WARN: Illegal instructions before constructor call */
    public u(Context context) {
        c.f.b.k.b(context, "context");
        Intent intentI = db.i();
        c.f.b.k.a((Object) intentI, "NotificationListenerInte…tingsIntentDoNotDisturb()");
        super(context, intentI);
    }
}
