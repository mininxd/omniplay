package com.joaomgcd.taskerm.event.sensor;

import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.event.i<com.joaomgcd.taskerm.state.sensor.c> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(EventEdit eventEdit, g gVar) {
        super(eventEdit, gVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(gVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar, String str) {
        c.f.b.k.b(anVar, "configurable");
        super.a(anVar, str);
        m(R.string.sleep_condition_tip_on_select);
        if (com.joaomgcd.taskerm.q.b.a(f(), new com.joaomgcd.taskerm.util.k(f(), R.string.sleep_condition_tip_on_select, new Object[0]))) {
            m(R.string.sleep_event_tip_on_select);
        }
    }
}
