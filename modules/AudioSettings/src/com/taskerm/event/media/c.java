package com.joaomgcd.taskerm.event.media;

import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.event.i;
import net.dinglisch.android.taskerm.EventEdit;

/* JADX INFO: loaded from: classes.dex */
public final class c extends i<e> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(EventEdit eventEdit, a aVar) {
        super(eventEdit, aVar);
        k.b(eventEdit, "eventEdit");
        k.b(aVar, NotificationCompat.CATEGORY_EVENT);
    }
}
