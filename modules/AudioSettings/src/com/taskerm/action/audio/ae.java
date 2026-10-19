package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import com.joaomgcd.taskerm.util.aj;

/* JADX INFO: loaded from: classes.dex */
public abstract class ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f4954a;

    public abstract int a();

    private ae(Context context) {
        this.f4954a = context;
    }

    public /* synthetic */ ae(Context context, c.f.b.g gVar) {
        this(context);
    }

    public String toString() {
        return aj.a(a(), this.f4954a, new Object[0]);
    }
}
