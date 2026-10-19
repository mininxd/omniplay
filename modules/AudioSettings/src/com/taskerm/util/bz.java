package com.joaomgcd.taskerm.util;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class bz extends by<bx> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bz(int i, c.f.a.b<? super bx, c.s> bVar) {
        super(i, bVar);
        c.f.b.k.b(bVar, "callback");
    }

    public void b(bx bxVar) {
        c.f.b.k.b(bxVar, NotificationCompat.CATEGORY_PROGRESS);
        a(bxVar);
    }

    public final ca a(Long l) {
        return new ca(a(), l, b());
    }
}
