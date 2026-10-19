package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.function.ThrottledNotifications;
import com.joaomgcd.taskerm.function.ThrottledNotificationsKt;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class az implements b.a.b.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.b.b f8076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f8077b;

    static final class a extends c.f.b.l implements c.f.a.b<Intent, c.s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Intent intent) {
            a(intent);
            return c.s.f2131a;
        }

        public final void a(Intent intent) {
            c.f.b.k.b(intent, "receiver$0");
            az.this.a(intent);
        }
    }

    public az(Context context) {
        c.f.b.k.b(context, "context");
        this.f8077b = context;
    }

    @Override // b.a.b.b
    public boolean a() {
        b.a.b.b bVar = this.f8076a;
        if (bVar != null) {
            return bVar.a();
        }
        return false;
    }

    @Override // b.a.b.b
    public void b() {
        b.a.b.b bVar = this.f8076a;
        if (bVar != null) {
            bVar.b();
        }
    }

    public final void a(String str, Intent intent) {
        c.f.b.k.b(intent, "intent");
        if (str == null) {
            return;
        }
        Integer num = (Integer) ThrottledNotificationsKt.getThrottledNotifications(this.f8077b).get((Object) str);
        if (num != null) {
            ThrottledNotifications.Companion.getPublisher(num.intValue(), new a()).b_(intent);
            bn.b("NOAH", "Throttling notification from " + str + " for " + num);
            return;
        }
        a(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(Intent intent) {
        this.f8077b.sendOrderedBroadcast(intent, null);
    }
}
