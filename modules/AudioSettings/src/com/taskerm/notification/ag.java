package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.dinglisch.android.taskerm.bp;

/* JADX INFO: loaded from: classes.dex */
public final class ag extends o {
    /* JADX WARN: Illegal instructions before constructor call */
    public ag(Context context) {
        c.f.b.k.b(context, "context");
        Intent intentC = bp.c(context);
        c.f.b.k.a((Object) intentC, "ManifestPermissions.getW…ManagementIntent(context)");
        super(context, intentC);
    }
}
