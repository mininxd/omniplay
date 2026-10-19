package com.joaomgcd.taskerm.plugin;

import android.app.NotificationManager;
import android.content.Context;
import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.notification.au;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.f;
import com.joaomgcd.taskerm.util.g;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final void a(Context context) {
        k.b(context, "receiver$0");
        if (f.f9323b.o()) {
            return;
        }
        i.c(new C0215a(context));
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.plugin.a$a, reason: collision with other inner class name */
    static final class C0215a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8205a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0215a(Context context) {
            super(0);
            this.f8205a = context;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            NotificationManager notificationManagerB;
            try {
                boolean zF = g.f(this.f8205a);
                if (zF && (notificationManagerB = au.b(this.f8205a)) != null) {
                    notificationManagerB.notify("quick".hashCode(), g.e(this.f8205a));
                }
                ah.b(this.f8205a, ServiceRequestQuery.class, !zF).c();
            } catch (Exception e2) {
                ai.b(e2, this.f8205a, "Enabling Disabling Service Request Query based on unimporatant notification");
            }
        }
    }
}
