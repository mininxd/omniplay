package com.joaomgcd.taskerm.f;

import androidx.core.app.NotificationCompat;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public abstract class l<TInput, THasArguments extends au> implements j<TInput, THasArguments> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f6816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f6817b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f6818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f6819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c<?, ?, ?, ?, ?, ?> f6820e;

    public l(String str, c<?, ?, ?, ?, ?, ?> cVar) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(cVar, "stateBase");
        this.f6819d = str;
        this.f6820e = cVar;
        this.f6818c = new Object();
    }

    public final String a() {
        return this.f6819d;
    }

    public final c<?, ?, ?, ?, ?, ?> b() {
        return this.f6820e;
    }

    public final void a(boolean z) {
        this.f6816a = z;
    }

    public final void c(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        synchronized (this.f6818c) {
            if (this.f6816a) {
                if (this.f6817b) {
                    return;
                }
                this.f6817b = a(monitorService);
                bn.b("EasyState", "Starting " + this.f6819d + " monitor: " + this.f6817b);
            } else if (!this.f6817b) {
                return;
            } else {
                d(monitorService);
            }
            c.s sVar = c.s.f2131a;
        }
    }

    public final void d(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        b(monitorService);
        this.f6817b = false;
        bn.b("EasyState", "Stopped " + this.f6819d + " monitor");
    }

    public final boolean c(MonitorService monitorService, ga gaVar, THasArguments thasarguments, TInput tinput) {
        boolean zA;
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(tinput, "input");
        synchronized (this.f6818c) {
            bn.b("EasyState", "Monitoring state " + this.f6819d + " for " + thasarguments.b(monitorService));
            zA = a(monitorService, gaVar, thasarguments, tinput);
        }
        return zA;
    }

    public final void d(MonitorService monitorService, ga gaVar, THasArguments thasarguments, TInput tinput) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(tinput, "input");
        synchronized (this.f6818c) {
            bn.b("EasyState", "Unmonitoring state " + this.f6819d + " for " + thasarguments.b(monitorService));
            b(monitorService, gaVar, thasarguments, tinput);
            c.s sVar = c.s.f2131a;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return !(c.f.b.k.a((Object) this.f6819d, (Object) ((l) obj).f6819d) ^ true);
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.configurable.Monitor<*, *>");
    }

    public int hashCode() {
        return this.f6819d.hashCode();
    }
}
