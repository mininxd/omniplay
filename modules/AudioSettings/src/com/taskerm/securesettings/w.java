package com.joaomgcd.taskerm.securesettings;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class w extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p f8564b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
        this.f8564b = p.Secure;
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public p b() {
        return this.f8564b;
    }
}
