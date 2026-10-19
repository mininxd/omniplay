package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class y extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(Context context, String str) {
        super(context, new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)), null, null, null, null, 60, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "packageName");
    }
}
