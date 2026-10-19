package com.joaomgcd.taskerm.event.app;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.dialog.y;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.event.i<q> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(EventEdit eventEdit, b bVar) {
        super(eventEdit, bVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(bVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar) {
        c.f.b.k.b(anVar, "configurable");
        super.a(anVar);
        com.joaomgcd.taskerm.helper.l.a(this, y.b(f(), R.string.en_assistant_action, R.string.dc_assistant_action_instructions, 0, 8, (Object) null), (b.a.d.f) null, 1, (Object) null);
    }
}
