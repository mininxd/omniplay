package com.joaomgcd.taskerm.event.app;

import android.app.Activity;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final void a(Activity activity, String str) {
        Object next;
        c.f.b.k.b(activity, "receiver$0");
        Iterator it = com.joaomgcd.taskerm.event.h.a().c().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof b));
        if (next != null) {
            com.joaomgcd.taskerm.event.b.a((b) next, activity, null, new q(str, null, 2, null), new OutputAssistantAction(str), null, null, 48, null);
            return;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.app.EventAssistantAction");
    }
}
