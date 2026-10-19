package com.joaomgcd.taskerm.notification;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityConfigureNotificationChannel;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public class f extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context, String str) {
        super(context, new GenericActionActivityConfigureNotificationChannel(str, null, null, 6, null), context.getString(R.string.configure_these), (ap) null, 8, (c.f.b.g) null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "channelId");
    }
}
