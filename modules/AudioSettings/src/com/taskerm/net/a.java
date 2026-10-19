package com.joaomgcd.taskerm.net;

import android.content.Context;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.joaomgcd.taskerm.b.p {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context, String str) {
        super(context, "connectivity", "android.net.IConnectivityManager", be.SET_AIRPLANE_MODE_LABEL);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "methodName");
    }
}
