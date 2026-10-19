package com.joaomgcd.taskerm.notification;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestNormalPermissions;
import com.joaomgcd.taskerm.util.bt;

/* JADX INFO: loaded from: classes.dex */
public final class k extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Context context, bt btVar) {
        super(context, new GenericActionActivityRequestNormalPermissions(btVar, 31), (String) null, (ap) null, 12, (c.f.b.g) null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(btVar, "permissions");
    }
}
