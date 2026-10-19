package com.joaomgcd.taskerm.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import net.dinglisch.android.taskerm.cq;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8079a = {c.f.b.x.a(new c.f.b.p(c.f.b.x.a(b.class), "lastAutoId", "getLastAutoId()I"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.q.e f8080b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f8081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Intent f8082d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8083e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final ap f8084f;
    private final Bundle g;
    private final av h;

    private final void a(int i) {
        this.f8080b.a(this, f8079a[0], Integer.valueOf(i));
    }

    private final int d() {
        return this.f8080b.a(this, f8079a[0]).intValue();
    }

    public abstract PendingIntent b();

    private b(Context context, Intent intent, String str, ap apVar, Bundle bundle, av avVar) {
        this.f8081c = context;
        this.f8082d = intent;
        this.f8083e = str;
        this.f8084f = apVar;
        this.g = bundle;
        this.h = avVar;
        this.f8080b = new com.joaomgcd.taskerm.q.e(this.f8081c, 0, null, "lastAutoIdNotificationAction", 6, null);
    }

    /* synthetic */ b(Context context, Intent intent, String str, ap apVar, Bundle bundle, av avVar, int i, c.f.b.g gVar) {
        this(context, intent, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? new as(context) : apVar, (i & 16) != 0 ? (Bundle) null : bundle, (i & 32) != 0 ? (av) null : avVar);
    }

    protected final int a() {
        int iD = d();
        a(iD + 1);
        return iD;
    }

    public final Notification.Action c() {
        if (this.f8083e == null) {
            throw new RuntimeException("Notification Buttons need a title");
        }
        Notification.Action.Builder builder = new Notification.Action.Builder(this.f8084f instanceof as ? ((as) this.f8084f).a() : cq.d(this.f8081c), this.f8083e, b());
        Bundle bundle = this.g;
        if (bundle != null) {
            builder.addExtras(bundle);
        }
        Notification.Action actionBuild = builder.build();
        c.f.b.k.a((Object) actionBuild, "builder.build()");
        return actionBuild;
    }
}
