package com.joaomgcd.taskerm.event.phone;

import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.event.i;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class c extends i<e> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(EventEdit eventEdit, a aVar) {
        super(eventEdit, aVar);
        k.b(eventEdit, "eventEdit");
        k.b(aVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar) {
        k.b(anVar, "configurable");
        super.a(anVar);
        m(R.string.tip_on_some_devices_only_incoming_calls);
    }
}
