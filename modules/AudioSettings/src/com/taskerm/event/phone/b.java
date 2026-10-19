package com.joaomgcd.taskerm.event.phone;

import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.call.ServiceCallScreening;
import com.joaomgcd.taskerm.event.h;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final void a(ServiceCallScreening serviceCallScreening, String str, boolean z, String str2, String str3) {
        Object next;
        k.b(serviceCallScreening, "receiver$0");
        k.b(str2, "capabilities");
        k.b(str3, "properties");
        Iterator it = h.a().c().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof a));
        if (next != null) {
            ServiceCallScreening serviceCallScreening2 = serviceCallScreening;
            com.joaomgcd.taskerm.event.b.a((a) next, serviceCallScreening2, null, new e(str, null, 2, null), new OutputCallScreened(serviceCallScreening2, str, Boolean.valueOf(z), str2, str3), null, null, 48, null);
            return;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.phone.EventCallScreened");
    }
}
