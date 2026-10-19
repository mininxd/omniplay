package com.joaomgcd.taskerm.notification;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class x extends m {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Context context, String str) {
        super(context, "Functionality Not Working", str + "\n\nThanks in advance!");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "explanation");
    }
}
