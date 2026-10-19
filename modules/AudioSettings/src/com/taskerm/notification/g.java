package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class g extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, String str, boolean z, String str2) {
        super(context, com.joaomgcd.taskerm.util.aj.a(new Intent(), context, str, z), str2, null, null, null, 56, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "url");
    }

    public /* synthetic */ g(Context context, String str, boolean z, String str2, int i, c.f.b.g gVar) {
        this(context, str, (i & 4) != 0 ? false : z, (i & 8) != 0 ? (String) null : str2);
    }
}
