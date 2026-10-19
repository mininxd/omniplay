package com.joaomgcd.taskerm.event.datetime;

import android.content.Context;
import android.content.Intent;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Intent f6646b;

    public a(Context context, Intent intent) {
        k.b(context, "context");
        this.f6645a = context;
        this.f6646b = intent;
    }

    public final Context a() {
        return this.f6645a;
    }
}
