package com.joaomgcd.taskerm.event.datetime;

import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.event.i;
import net.dinglisch.android.taskerm.EventEdit;

/* JADX INFO: loaded from: classes.dex */
public final class d extends i<f> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(EventEdit eventEdit, b bVar) {
        super(eventEdit, bVar);
        k.b(eventEdit, "eventEdit");
        k.b(bVar, NotificationCompat.CATEGORY_EVENT);
    }
}
