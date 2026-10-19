package com.joaomgcd.taskerm.f;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.f.c;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.fj;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<TCondition extends c<?, ?, ?, THasArguments, TSpec, ?>, THasArguments extends au, TSpec extends fj> extends b<TCondition, THasArguments, TSpec> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(TCondition... tconditionArr) {
        super((c[]) Arrays.copyOf(tconditionArr, tconditionArr.length));
        c.f.b.k.b(tconditionArr, "actions");
    }

    public final void a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            ((c) ((Map.Entry) it.next()).getValue()).b(monitorService);
        }
    }

    public final void a() {
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            ((c) ((Map.Entry) it.next()).getValue()).a(false);
        }
    }

    public final void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            ((c) ((Map.Entry) it.next()).getValue()).a(monitorService);
        }
    }
}
