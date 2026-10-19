package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ag extends ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4958a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ag(Context context) {
        super(context, null);
        c.f.b.k.b(context, "context");
        this.f4958a = R.string.select_used_apps;
    }

    @Override // com.joaomgcd.taskerm.action.audio.ae
    public int a() {
        return this.f4958a;
    }
}
