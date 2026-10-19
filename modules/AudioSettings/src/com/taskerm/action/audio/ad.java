package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ad extends ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4953a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ad(Context context) {
        super(context, null);
        c.f.b.k.b(context, "context");
        this.f4953a = R.string.select_previous_apps;
    }

    @Override // com.joaomgcd.taskerm.action.audio.ae
    public int a() {
        return this.f4953a;
    }
}
