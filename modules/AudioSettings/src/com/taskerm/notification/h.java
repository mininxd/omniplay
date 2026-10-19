package com.joaomgcd.taskerm.notification;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionServiceClearBridgeItem;

/* JADX INFO: loaded from: classes.dex */
public class h extends l {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Context context, String str) {
        super(context, new GenericActionServiceClearBridgeItem(str), (String) null, (ap) null, 12, (c.f.b.g) null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "key");
    }
}
