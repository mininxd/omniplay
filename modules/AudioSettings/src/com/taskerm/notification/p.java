package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.dinglisch.android.taskerm.MyAccessibilityService;

/* JADX INFO: loaded from: classes.dex */
public final class p extends o {
    /* JADX WARN: Illegal instructions before constructor call */
    public p(Context context) {
        c.f.b.k.b(context, "context");
        Intent intentD = MyAccessibilityService.d();
        c.f.b.k.a((Object) intentD, "MyAccessibilityService.getSettingsIntent()");
        super(context, intentD);
    }
}
