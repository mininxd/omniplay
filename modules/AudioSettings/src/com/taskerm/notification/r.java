package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.dinglisch.android.taskerm.bp;

/* JADX INFO: loaded from: classes.dex */
public final class r extends o {
    /* JADX WARN: Illegal instructions before constructor call */
    public r(Context context) {
        c.f.b.k.b(context, "context");
        Intent intentB = bp.b(context);
        c.f.b.k.a((Object) intentB, "ManifestPermissions.getU…ManagementIntent(context)");
        super(context, intentB);
    }
}
