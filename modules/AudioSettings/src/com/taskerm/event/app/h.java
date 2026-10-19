package com.joaomgcd.taskerm.event.app;

import androidx.core.app.NotificationCompat;
import net.dinglisch.android.taskerm.EventEdit;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.event.i<p> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(EventEdit eventEdit, a aVar) {
        super(eventEdit, aVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(aVar, NotificationCompat.CATEGORY_EVENT);
    }
}
