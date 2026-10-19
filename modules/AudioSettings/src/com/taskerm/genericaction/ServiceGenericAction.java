package com.joaomgcd.taskerm.genericaction;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.helper.u;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceGenericAction extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6952a = {x.a(new v(x.a(ServiceGenericAction.class), "helper", "getHelper()Lcom/joaomgcd/taskerm/helper/HelperService;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6953b;

    private final u<ServiceGenericAction> a() {
        c.e eVar = this.f6953b;
        c.j.g gVar = f6952a[0];
        return (u) eVar.b();
    }

    static final class a extends l implements c.f.a.a<u<ServiceGenericAction>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final u<ServiceGenericAction> invoke() {
            return new u<>(ServiceGenericAction.this, "GenericAction");
        }
    }

    public ServiceGenericAction() {
        super("ServiceGenericAction");
        this.f6953b = c.f.a(new a());
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        com.joaomgcd.taskerm.util.g.a(this, "GenericAction");
        super.onCreate();
        try {
            setTheme(gn.c(this));
        } catch (Throwable th) {
            com.joaomgcd.taskerm.rx.i.a(this, th);
        }
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        com.joaomgcd.taskerm.util.g.a(this, "GenericAction");
        if (intent == null) {
            return;
        }
        d dVar = new d(this, intent);
        com.joaomgcd.taskerm.helper.j.a(a(), com.joaomgcd.taskerm.util.g.a((Service) this), (c.f.a.a) null, 2, (Object) null);
        d.a(dVar, null, null, 3, null);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        a().b();
        super.onDestroy();
    }
}
