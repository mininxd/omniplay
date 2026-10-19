package com.joaomgcd.taskerm.event.hardware;

import android.annotation.TargetApi;
import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.event.h;
import java.util.Iterator;
import net.dinglisch.android.taskerm.MyAccessibilityService;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    @TargetApi(26)
    private static final boolean a(int i) {
        return i == 2;
    }

    @TargetApi(26)
    private static final boolean b(int i) {
        return i == 1;
    }

    @TargetApi(26)
    private static final boolean c(int i) {
        return i == 4;
    }

    @TargetApi(26)
    private static final boolean d(int i) {
        return i == 8;
    }

    public static final void a(MyAccessibilityService myAccessibilityService, int i) {
        Object next;
        k.b(myAccessibilityService, "receiver$0");
        Iterator it = h.a().c().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof a));
        if (next != null) {
            MyAccessibilityService myAccessibilityService2 = myAccessibilityService;
            com.joaomgcd.taskerm.event.b.a((a) next, myAccessibilityService2, null, new f(Boolean.valueOf(a(i)), Boolean.valueOf(b(i)), Boolean.valueOf(c(i)), Boolean.valueOf(d(i)), null, 16, null), new OutputFingerprintGesture(myAccessibilityService2, i), null, null, 48, null);
            return;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.hardware.EventFingerprintGesture");
    }
}
