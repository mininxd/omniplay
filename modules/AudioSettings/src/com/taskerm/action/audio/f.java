package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class f extends ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4964a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context) {
        super(context, null);
        c.f.b.k.b(context, "context");
        this.f4964a = R.string.select_package_directly;
    }

    @Override // com.joaomgcd.taskerm.action.audio.ae
    public int a() {
        return this.f4964a;
    }
}
