package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f5012a;

    public abstract t a(String str, String[] strArr, boolean z, boolean z2);

    public v(Context context) {
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
        this.f5012a = context;
    }

    protected final Context a() {
        return this.f5012a;
    }
}
