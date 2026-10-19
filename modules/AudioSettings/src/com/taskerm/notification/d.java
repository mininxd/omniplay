package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public class d extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Context context, String str, boolean z) {
        super(context, com.joaomgcd.taskerm.util.aj.b(new Intent(), context, str, z), com.joaomgcd.taskerm.util.aj.a(R.string.bl_download, context, new Object[0]), null, null, null, 56, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "packageName");
    }
}
