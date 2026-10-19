package com.joaomgcd.taskerm.notification;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class as extends ap {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8044a;

    public as(int i) {
        super(null);
        this.f8044a = i;
    }

    public final int a() {
        return this.f8044a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public as(Context context) {
        this(com.joaomgcd.taskerm.util.ah.K(context));
        c.f.b.k.b(context, "context");
    }
}
