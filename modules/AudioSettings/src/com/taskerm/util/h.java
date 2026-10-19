package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9331a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(String str) {
        super(null);
        c.f.b.k.b(str, "value");
        this.f9331a = str;
    }

    public final String a() {
        return this.f9331a;
    }

    @Override // com.joaomgcd.taskerm.util.i
    public String a(Context context) {
        c.f.b.k.b(context, "context");
        return this.f9331a;
    }
}
