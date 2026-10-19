package com.joaomgcd.taskerm.event.net;

import android.content.Context;
import c.q;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final void a(Context context, boolean z) {
        Object next;
        c.f.b.k.b(context, "receiver$0");
        Iterator it = com.joaomgcd.taskerm.event.h.a().c().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof c));
        if (next != null) {
            com.joaomgcd.taskerm.event.b.a((c) next, context, null, new l(null, 1, null), new OutputPowerMenuShown(z), null, null, 48, null);
            return;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.net.EventPowerMenuShown");
    }
}
