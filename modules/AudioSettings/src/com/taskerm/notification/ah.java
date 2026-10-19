package com.joaomgcd.taskerm.notification;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionShowOkDialog;

/* JADX INFO: loaded from: classes.dex */
public final class ah extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ah(Context context, String str, String str2) {
        super(context, new GenericActionShowOkDialog(str, str2), (String) null, (ap) null, 12, (c.f.b.g) null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(str2, "text");
    }
}
