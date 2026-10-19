package com.joaomgcd.taskerm.event.app;

import android.content.Intent;
import com.joaomgcd.taskerm.util.ActivitySecondaryApp;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final void a(ActivitySecondaryApp activitySecondaryApp, com.joaomgcd.taskerm.inputoutput.a.b bVar) {
        Object next;
        c.f.b.k.b(activitySecondaryApp, "receiver$0");
        Iterator it = com.joaomgcd.taskerm.event.h.a().c().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof f));
        if (next != null) {
            f fVar = (f) next;
            ActivitySecondaryApp activitySecondaryApp2 = activitySecondaryApp;
            s sVar = new s(null, 1, null);
            Intent intent = activitySecondaryApp.getIntent();
            com.joaomgcd.taskerm.event.b.a(fVar, activitySecondaryApp2, null, sVar, new OutputSecondaryAppOpened(intent != null ? intent.getAction() : null), null, bVar, 16, null);
            return;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.app.EventSecondaryAppOpened");
    }
}
