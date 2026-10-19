package com.joaomgcd.taskerm.event.system;

import androidx.core.app.NotificationCompat;
import c.s;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.ar;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.event.d<m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<Integer, b> f6751a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.event.system.b f6752b;

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(com.joaomgcd.taskerm.event.system.b bVar) {
        super("LogCat", bVar);
        c.f.b.k.b(bVar, NotificationCompat.CATEGORY_EVENT);
        this.f6752b = bVar;
        this.f6751a = new HashMap<>();
    }

    private final void c() {
        Iterator<Map.Entry<Integer, b>> it = this.f6751a.entrySet().iterator();
        while (it.hasNext()) {
            b value = it.next().getValue();
            if (value.a()) {
                return;
            } else {
                value.b();
            }
        }
        this.f6751a.clear();
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c();
    }

    private static final class a extends com.joaomgcd.taskerm.p.a.C0210a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.system.c$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<String, s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f6753a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            public final void a(String str) {
                c.f.b.k.b(str, "log");
                bn.b("LogCatEntry", str);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(String str) {
                a(str);
                return s.f2131a;
            }
        }

        public a(String str, String str2, Boolean bool) {
            super(str, str2, bool, AnonymousClass1.f6753a);
        }

        @Override // com.joaomgcd.taskerm.p.a.C0210a
        public boolean a(String str) {
            c.f.b.k.b(str, "log");
            String strC = c();
            if (strC == null || strC.length() == 0) {
                String strA = a();
                if (strA == null || strA.length() == 0) {
                    return false;
                }
            }
            if (super.a(str)) {
                return true;
            }
            return ar.d(a(), str);
        }
    }

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a f6754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b.a.b.b f6755b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.joaomgcd.taskerm.event.system.b f6756c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private MonitorService f6757d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ga f6758e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private m f6759f;

        public b(com.joaomgcd.taskerm.event.system.b bVar, MonitorService monitorService, ga gaVar, m mVar) {
            c.f.b.k.b(bVar, NotificationCompat.CATEGORY_EVENT);
            c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
            c.f.b.k.b(gaVar, "profile");
            c.f.b.k.b(mVar, "input");
            this.f6756c = bVar;
            this.f6757d = monitorService;
            this.f6758e = gaVar;
            this.f6759f = mVar;
            this.f6754a = new a(this.f6759f.getComponent(), this.f6759f.getFilter(), this.f6759f.getGrepForFilter());
            b.a.b.b bVarA = com.joaomgcd.taskerm.p.a.f8124a.b(this.f6754a).a(new a(), new C0144b());
            c.f.b.k.a((Object) bVarA, "Logcat.addListenerRaw(li…r(service, it)\n        })");
            this.f6755b = bVarA;
        }

        public final MonitorService c() {
            return this.f6757d;
        }

        public final ga d() {
            return this.f6758e;
        }

        public final m e() {
            return this.f6759f;
        }

        static final class a<T> implements b.a.d.f<String> {
            a() {
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(String str) {
                c.f.b.k.b(str, "it");
                b.this.a(b.this.c(), b.this.e(), new OutputLogCatEntry(str), b.this.d().J());
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.system.c$b$b, reason: collision with other inner class name */
        static final class C0144b<T> implements b.a.d.f<Throwable> {
            C0144b() {
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(Throwable th) {
                c.f.b.k.b(th, "it");
                com.joaomgcd.taskerm.rx.i.a(b.this.c(), th);
            }
        }

        public final boolean a() {
            return this.f6755b.a();
        }

        public final void b() {
            this.f6755b.b();
        }

        public final void a(MonitorService monitorService, ga gaVar, m mVar) {
            c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
            c.f.b.k.b(gaVar, "profile");
            c.f.b.k.b(mVar, "input");
            this.f6757d = monitorService;
            this.f6758e = gaVar;
            this.f6759f = mVar;
            this.f6754a.c(mVar.getComponent());
            this.f6754a.b(mVar.getFilter());
            this.f6754a.a(mVar.getGrepForFilter());
        }

        public final void a(MonitorService monitorService, m mVar, OutputLogCatEntry outputLogCatEntry, int i) {
            c.f.b.k.b(monitorService, "receiver$0");
            c.f.b.k.b(mVar, "input");
            c.f.b.k.b(outputLogCatEntry, "output");
            com.joaomgcd.taskerm.event.b.a(this.f6756c, monitorService, monitorService, mVar, outputLogCatEntry, Integer.valueOf(i), null, 32, null);
        }
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, an anVar, m mVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(mVar, "input");
        b bVar = this.f6751a.get(Integer.valueOf(gaVar.J()));
        if (bVar == null || bVar.a()) {
            this.f6751a.put(Integer.valueOf(gaVar.J()), new b(this.f6752b, monitorService, gaVar, mVar));
            return true;
        }
        bVar.a(monitorService, gaVar, mVar);
        return true;
    }

    private final void a(int i) {
        b bVar = this.f6751a.get(Integer.valueOf(i));
        if (bVar != null) {
            bVar.b();
        }
        this.f6751a.remove(Integer.valueOf(i));
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, an anVar, m mVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(anVar, "state");
        c.f.b.k.b(mVar, "input");
        a(gaVar.J());
    }
}
