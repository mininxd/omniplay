package com.joaomgcd.taskerm.state.sensor;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.app.NotificationCompat;
import c.s;
import com.joaomgcd.taskerm.f.c;
import com.joaomgcd.taskerm.f.m;
import com.joaomgcd.taskerm.util.bt;
import java.util.List;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public abstract class e<TCondition extends com.joaomgcd.taskerm.f.c<c, ?, ?, ?, ?, ?>, THasArguments extends au, TId> implements com.joaomgcd.taskerm.f.k<c, THasArguments>, m<TCondition, c, THasArguments, TId> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.b.b f8689a;

    public abstract void a(MonitorService monitorService, d dVar);

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, THasArguments thasarguments, c cVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(cVar, "input");
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, THasArguments thasarguments, c cVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(cVar, "input");
    }

    static final class a extends c.f.b.l implements c.f.a.b<List<Notification>, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8690a = new a();

        a() {
            super(1);
        }

        public final void a(List<Notification> list) {
            c.f.b.k.b(list, "it");
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(List<Notification> list) {
            a(list);
            return s.f2131a;
        }
    }

    public boolean a(MonitorService monitorService, c cVar, THasArguments thasarguments) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(cVar, "input");
        c.f.b.k.b(thasarguments, "hasArguments");
        d dVarA = a((Context) monitorService);
        int iA = dVarA.a();
        Integer minConfidence = cVar.getMinConfidence();
        if (iA >= (minConfidence != null ? minConfidence.intValue() : 85)) {
            int iB = dVarA.b();
            Integer maxLight = cVar.getMaxLight();
            if (iB <= (maxLight != null ? maxLight.intValue() : 2)) {
                int iC = dVarA.c();
                Integer maxMotion = cVar.getMaxMotion();
                if (iC <= (maxMotion != null ? maxMotion.intValue() : 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected final d a(Context context) {
        Object dVar;
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        String string = sharedPreferencesA.getString("latestsleep", null);
        if (string == null || (dVar = com.joaomgcd.taskerm.n.b.a().a(string, (Class<Object>) d.class)) == null) {
            dVar = new d();
        }
        return (d) dVar;
    }

    protected final void a(Context context, d dVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(dVar, "value");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "latestsleep", dVar, sharedPreferencesA);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        MonitorService monitorService2 = monitorService;
        bt btVarU = bt.a.u(bt.f9101c, monitorService2, 0, 2, null);
        if (!btVarU.g()) {
            com.joaomgcd.taskerm.rx.i.a(bt.a(btVarU, (String) null, 1, (Object) null), monitorService2, a.f8690a);
            return false;
        }
        this.f8689a = IntentServiceSleeping.f8670a.b().d(new b(monitorService));
        IntentServiceSleeping.f8670a.a((Context) monitorService2);
        return true;
    }

    static final class b<T> implements b.a.d.f<d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MonitorService f8692b;

        b(MonitorService monitorService) {
            this.f8692b = monitorService;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(d dVar) {
            c.f.b.k.b(dVar, "it");
            e.this.a((Context) this.f8692b, dVar);
            e.this.a(this.f8692b, dVar);
        }
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        b.a.b.b bVar = this.f8689a;
        if (bVar != null) {
            bVar.b();
        }
        this.f8689a = (b.a.b.b) null;
        IntentServiceSleeping.f8670a.b(monitorService);
    }
}
