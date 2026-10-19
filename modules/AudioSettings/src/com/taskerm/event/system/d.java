package com.joaomgcd.taskerm.event.system;

import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.ga;
import net.dinglisch.android.taskerm.he;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.event.d<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<Integer, a> f6762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f6763b;

    private final String d() {
        return "Tick";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar) {
        super("LogCat", eVar);
        c.f.b.k.b(eVar, NotificationCompat.CATEGORY_EVENT);
        this.f6763b = eVar;
        this.f6762a = new HashMap<>();
    }

    private final void c() {
        Iterator<Map.Entry<Integer, a>> it = this.f6762a.entrySet().iterator();
        while (it.hasNext()) {
            a value = it.next().getValue();
            if (value.a()) {
                return;
            } else {
                value.b();
            }
        }
        this.f6762a.clear();
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        he.a(monitorService, d());
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        he.c(monitorService, d());
        c();
    }

    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b.a.b.b f6764a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f6765b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private MonitorService f6766c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ga f6767d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private n f6768e;

        public a(e eVar, MonitorService monitorService, ga gaVar, n nVar) {
            Long lD;
            c.f.b.k.b(eVar, NotificationCompat.CATEGORY_EVENT);
            c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
            c.f.b.k.b(gaVar, "profile");
            c.f.b.k.b(nVar, "input");
            this.f6765b = eVar;
            this.f6766c = monitorService;
            this.f6767d = gaVar;
            this.f6768e = nVar;
            String millis = this.f6768e.getMillis();
            long jLongValue = (millis == null || (lD = c.l.n.d(millis)) == null) ? 1000 : lD.longValue();
            b.a.b.b bVarA = b.a.h.a(jLongValue < ((long) 100) ? 1000 : jLongValue, TimeUnit.MILLISECONDS).a(new C0145a(), new b());
            c.f.b.k.a((Object) bVarA, "Observable.interval((inp…r(service, it)\n        })");
            this.f6764a = bVarA;
        }

        public final MonitorService c() {
            return this.f6766c;
        }

        public final ga d() {
            return this.f6767d;
        }

        public final n e() {
            return this.f6768e;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.system.d$a$a, reason: collision with other inner class name */
        static final class C0145a<T> implements b.a.d.f<Long> {
            C0145a() {
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(Long l) {
                c.f.b.k.b(l, "it");
                a.this.a(a.this.c(), a.this.e(), new OutputTick(), a.this.d().J());
            }
        }

        static final class b<T> implements b.a.d.f<Throwable> {
            b() {
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(Throwable th) {
                c.f.b.k.b(th, "it");
                com.joaomgcd.taskerm.rx.i.a(a.this.c(), th);
            }
        }

        public final boolean a() {
            return this.f6764a.a();
        }

        public final void b() {
            this.f6764a.b();
        }

        public final void a(MonitorService monitorService, ga gaVar, n nVar) {
            c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
            c.f.b.k.b(gaVar, "profile");
            c.f.b.k.b(nVar, "input");
            this.f6766c = monitorService;
            this.f6767d = gaVar;
            this.f6768e = nVar;
        }

        public final void a(MonitorService monitorService, n nVar, OutputTick outputTick, int i) {
            c.f.b.k.b(monitorService, "receiver$0");
            c.f.b.k.b(nVar, "input");
            c.f.b.k.b(outputTick, "output");
            com.joaomgcd.taskerm.event.b.a(this.f6765b, monitorService, monitorService, nVar, outputTick, Integer.valueOf(i), null, 32, null);
        }
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, an anVar, n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(nVar, "input");
        a aVar = this.f6762a.get(Integer.valueOf(gaVar.J()));
        if (aVar == null || aVar.a()) {
            this.f6762a.put(Integer.valueOf(gaVar.J()), new a(this.f6763b, monitorService, gaVar, nVar));
            return true;
        }
        aVar.a(monitorService, gaVar, nVar);
        return true;
    }

    private final void a(int i) {
        a aVar = this.f6762a.get(Integer.valueOf(i));
        if (aVar != null) {
            aVar.b();
        }
        this.f6762a.remove(Integer.valueOf(i));
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, an anVar, n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(nVar, "input");
        a(gaVar.J());
    }
}
