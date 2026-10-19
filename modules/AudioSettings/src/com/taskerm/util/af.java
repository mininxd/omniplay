package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class af extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8884a;

    public af(int i) {
        super("Error in action");
        this.f8884a = i;
    }

    public final String a(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "actionName");
        return aj.a(this.f8884a, context, str);
    }
}
