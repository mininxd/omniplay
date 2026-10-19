package com.joaomgcd.taskerm.securesettings;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ae extends ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p f8471a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ae(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
        this.f8471a = p.Secure;
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public p b() {
        return this.f8471a;
    }
}
