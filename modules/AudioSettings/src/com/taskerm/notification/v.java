package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.dinglisch.android.taskerm.bp;

/* JADX INFO: loaded from: classes.dex */
public final class v extends o {
    /* JADX WARN: Illegal instructions before constructor call */
    public v(Context context) {
        c.f.b.k.b(context, "context");
        Intent intentH = bp.h(context);
        c.f.b.k.a((Object) intentH, "ManifestPermissions.getD…ManagementIntent(context)");
        super(context, intentH);
    }
}
