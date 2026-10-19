package com.joaomgcd.taskerm.event.system;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.cl;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.event.i<n> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return i == 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(EventEdit eventEdit, e eVar) {
        super(eventEdit, eVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(eVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar) {
        c.f.b.k.b(anVar, "configurable");
        super.a(anVar);
        j();
        if (i()) {
            com.joaomgcd.taskerm.helper.l.a(this, y.b(f(), R.string.en_tick, R.string.dc_tick_event_tips, 0, 8, (Object) null), (b.a.d.f) null, 1, (Object) null);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public String h(int i) {
        return String.valueOf(1000);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.helper.l
    public cl a(n nVar) {
        c.f.b.k.b(nVar, "inputFromActivity");
        return a(R.string.pl_min_interval, nVar.getMillis(), 100, 120000, true);
    }
}
