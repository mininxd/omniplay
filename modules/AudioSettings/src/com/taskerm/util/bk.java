package com.joaomgcd.taskerm.util;

import android.content.Context;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class bk extends bi {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9048b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bk(Context context, int i, bm bmVar) {
        super(context, i, bmVar, NotificationCompat.CATEGORY_EVENT);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bmVar, "versions");
        this.f9048b = net.dinglisch.android.taskerm.am.c(i);
    }

    @Override // com.joaomgcd.taskerm.util.bi
    public Integer a() {
        return Integer.valueOf(this.f9048b);
    }
}
