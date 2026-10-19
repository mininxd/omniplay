package com.joaomgcd.taskerm.f;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.TriggerEvent;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public abstract class p<THasArguments extends au, TId> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6823a = {x.a(new v(x.a(p.class), "monitorsById", "getMonitorsById()Ljava/util/HashMap;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6824b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.joaomgcd.taskerm.f.c<?, ?, ?, ?, ?, ?> f6825c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f6826d;

    private final HashMap<TId, r<com.joaomgcd.taskerm.event.sensor.n, THasArguments>> b() {
        c.e eVar = this.f6824b;
        c.j.g gVar = f6823a[0];
        return (HashMap) eVar.b();
    }

    public abstract TId a(Context context, ga gaVar, THasArguments thasarguments);

    public abstract void a(MonitorService monitorService, com.joaomgcd.taskerm.event.sensor.n nVar, com.joaomgcd.taskerm.inputoutput.a.b bVar, TId tid, THasArguments thasarguments);

    public final boolean c(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return true;
    }

    static final class a extends c.f.b.l implements c.f.a.b<Object, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MonitorService f6828b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Sensor f6829c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.event.sensor.n f6830d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object f6831e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ au f6832f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(MonitorService monitorService, Sensor sensor, com.joaomgcd.taskerm.event.sensor.n nVar, Object obj, au auVar) {
            super(1);
            this.f6828b = monitorService;
            this.f6829c = sensor;
            this.f6830d = nVar;
            this.f6831e = obj;
            this.f6832f = auVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Object obj) {
            a(obj);
            return c.s.f2131a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(Object obj) {
            com.joaomgcd.taskerm.inputoutput.a.b<com.joaomgcd.taskerm.inputoutput.a.a> bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
            bVar.a(this.f6828b, obj);
            bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a(OutputAnySensor.VAR_PREFIX, "sensor_type", String.valueOf(this.f6829c.getType())));
            if (ak.z(p.this.a())) {
                for (com.joaomgcd.taskerm.inputoutput.a.a aVar : bVar) {
                    aVar.a(p.this.a() + aVar.c());
                }
            }
            p.this.a(this.f6828b, this.f6830d, bVar, this.f6831e, this.f6832f);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<Object, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.b f6833a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a f6834b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(b.a.k.b bVar, a aVar) {
            super(1);
            this.f6833a = bVar;
            this.f6834b = aVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Object obj) {
            a(obj);
            return c.s.f2131a;
        }

        public final void a(Object obj) {
            if (obj == null) {
                return;
            }
            if (this.f6833a == null) {
                this.f6834b.a(obj);
            } else {
                this.f6833a.b_(obj);
            }
        }
    }

    public p(com.joaomgcd.taskerm.f.c<?, ?, ?, ?, ?, ?> cVar, String str) {
        c.f.b.k.b(cVar, "conditionBase");
        c.f.b.k.b(str, "variablePrefix");
        this.f6825c = cVar;
        this.f6826d = str;
        this.f6824b = c.f.a(h.f6850a);
    }

    public final String a() {
        return this.f6826d;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:27:0x00b6  */
    private final r<com.joaomgcd.taskerm.event.sensor.n, THasArguments> a(MonitorService monitorService, TId tid, com.joaomgcd.taskerm.event.sensor.n nVar, THasArguments thasarguments) {
        b.a.b.b bVarD;
        b.a.k.b bVarC;
        MonitorService monitorService2 = monitorService;
        Integer type = nVar.getType(monitorService2);
        if (type == null) {
            return null;
        }
        int iIntValue = type.intValue();
        Sensor sensorA = aj.a((Context) monitorService2, Integer.valueOf(iIntValue));
        if (sensorA == null) {
            return null;
        }
        a aVar = new a(monitorService, sensorA, nVar, tid, thasarguments);
        com.joaomgcd.taskerm.event.sensor.p minIntervalType = nVar.getMinIntervalType();
        b.a.k.b bVarJ = (minIntervalType == null || minIntervalType == com.joaomgcd.taskerm.event.sensor.p.None) ? null : b.a.k.b.j();
        if (bVarJ == null) {
            bVarD = null;
        } else {
            if (minIntervalType != null) {
                switch (q.f6851a[minIntervalType.ordinal()]) {
                    case 1:
                        bVarC = com.joaomgcd.taskerm.rx.i.c(bVarJ, nVar.getMinIntervalNotNull(), TimeUnit.MILLISECONDS);
                        break;
                    case 2:
                        bVarC = com.joaomgcd.taskerm.rx.i.b(bVarJ, nVar.getMinIntervalNotNull(), TimeUnit.MILLISECONDS);
                        break;
                    case 3:
                        bVarC = com.joaomgcd.taskerm.rx.i.d(bVarJ, nVar.getMinIntervalNotNull(), TimeUnit.MILLISECONDS);
                        break;
                    case 4:
                        bVarC = com.joaomgcd.taskerm.rx.i.e(bVarJ, nVar.getMinIntervalNotNull(), TimeUnit.MILLISECONDS).d(c.f6835a);
                        break;
                    case 5:
                        bVarC = com.joaomgcd.taskerm.rx.i.f(bVarJ, nVar.getMinIntervalNotNull(), TimeUnit.MILLISECONDS).d(d.f6836a);
                        break;
                    default:
                        bVarC = bVarJ;
                        break;
                }
            } else {
                bVarC = bVarJ;
            }
            if (bVarC != null) {
                bVarD = bVarC.d(new e(aVar));
            } else {
                bVarD = null;
            }
        }
        b bVar = new b(bVarJ, aVar);
        String str = "Any Sensor " + iIntValue;
        if (aj.d(sensorA)) {
            return new f(bVar, nVar, bVarD, iIntValue, sensorA, str, str, this.f6825c);
        }
        return new g(bVar, nVar, bVarD, monitorService, iIntValue, sensorA, str, str, this.f6825c);
    }

    static final class c<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6835a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object[] apply(List<Object> list) {
            c.f.b.k.b(list, "it");
            Object[] array = list.toArray(new Object[0]);
            if (array != null) {
                return array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    static final class d<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f6836a = new d();

        d() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object[] apply(List<? extends Object> list) {
            c.f.b.k.b(list, "it");
            Object[] array = list.toArray(new Object[0]);
            if (array != null) {
                return array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    static final class e<T> implements b.a.d.f<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f6837a;

        e(a aVar) {
            this.f6837a = aVar;
        }

        @Override // b.a.d.f
        public final void accept(Object obj) {
            this.f6837a.a(obj);
        }
    }

    public static final class f extends t<com.joaomgcd.taskerm.event.sensor.n, THasArguments> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b f6839b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.event.sensor.n f6840c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f6841d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f6842e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Sensor f6843f;
        final /* synthetic */ String g;
        private final int h;
        private final boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(b bVar, com.joaomgcd.taskerm.event.sensor.n nVar, b.a.b.b bVar2, int i, Sensor sensor, String str, String str2, com.joaomgcd.taskerm.f.c cVar) {
            super(str2, cVar);
            this.f6839b = bVar;
            this.f6840c = nVar;
            this.f6841d = bVar2;
            this.f6842e = i;
            this.f6843f = sensor;
            this.g = str;
            this.h = i;
            this.i = sensor.isWakeUpSensor();
        }

        @Override // com.joaomgcd.taskerm.f.r
        protected int c() {
            return this.h;
        }

        @Override // com.joaomgcd.taskerm.f.r
        protected void a(MonitorService monitorService, Object obj) {
            c.f.b.k.b(monitorService, "receiver$0");
            this.f6839b.a(obj);
        }

        @Override // com.joaomgcd.taskerm.f.t
        protected Object a(TriggerEvent triggerEvent) {
            c.f.b.k.b(triggerEvent, "receiver$0");
            return new OutputAnySensor(triggerEvent, this.f6840c.getConvertOrientation());
        }

        @Override // com.joaomgcd.taskerm.f.r, com.joaomgcd.taskerm.f.j
        public void b(MonitorService monitorService) {
            c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
            super.b(monitorService);
            b.a.b.b bVar = this.f6841d;
            if (bVar != null) {
                bVar.b();
            }
        }

        @Override // com.joaomgcd.taskerm.f.t, com.joaomgcd.taskerm.f.r
        protected boolean d() {
            return this.i;
        }
    }

    public static final class g extends s<com.joaomgcd.taskerm.event.sensor.n, THasArguments> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b f6845b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.event.sensor.n f6846c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f6847d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ MonitorService f6848e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ int f6849f;
        final /* synthetic */ Sensor g;
        final /* synthetic */ String h;
        private final int j;
        private final int k;
        private final Handler l;
        private final int m;
        private final boolean n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(b bVar, com.joaomgcd.taskerm.event.sensor.n nVar, b.a.b.b bVar2, MonitorService monitorService, int i, Sensor sensor, String str, String str2, com.joaomgcd.taskerm.f.c cVar) {
            super(str2, cVar);
            this.f6845b = bVar;
            this.f6846c = nVar;
            this.f6847d = bVar2;
            this.f6848e = monitorService;
            this.f6849f = i;
            this.g = sensor;
            this.h = str;
            this.j = nVar.getDelayNotNull();
            this.k = nVar.isMinIntervalTypeNone() ? nVar.getMaxLatencyNotNull() : c.g.a.a(((double) nVar.getMaxLatencyNotNull()) * 0.8d);
            this.l = monitorService.f11105f.c().d();
            this.m = i;
            this.n = sensor.isWakeUpSensor();
        }

        @Override // com.joaomgcd.taskerm.f.s
        protected int e() {
            return this.j;
        }

        @Override // com.joaomgcd.taskerm.f.s
        protected int f() {
            return this.k;
        }

        @Override // com.joaomgcd.taskerm.f.s
        protected Handler g() {
            return this.l;
        }

        @Override // com.joaomgcd.taskerm.f.r
        protected int c() {
            return this.m;
        }

        @Override // com.joaomgcd.taskerm.f.r
        protected void a(MonitorService monitorService, Object obj) {
            c.f.b.k.b(monitorService, "receiver$0");
            this.f6845b.a(obj);
        }

        @Override // com.joaomgcd.taskerm.f.s
        protected Object a(SensorEvent sensorEvent) {
            c.f.b.k.b(sensorEvent, "receiver$0");
            return new OutputAnySensor(sensorEvent, this.f6846c.getConvertOrientation());
        }

        @Override // com.joaomgcd.taskerm.f.s, com.joaomgcd.taskerm.f.r
        protected boolean d() {
            return this.n;
        }

        @Override // com.joaomgcd.taskerm.f.r, com.joaomgcd.taskerm.f.j
        public void b(MonitorService monitorService) {
            c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
            super.b(monitorService);
            b.a.b.b bVar = this.f6847d;
            if (bVar != null) {
                bVar.b();
            }
        }
    }

    static final class h extends c.f.b.l implements c.f.a.a<HashMap<TId, r<com.joaomgcd.taskerm.event.sensor.n, THasArguments>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f6850a = new h();

        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<TId, r<com.joaomgcd.taskerm.event.sensor.n, THasArguments>> invoke() {
            return new HashMap<>();
        }
    }

    public final void a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        b(monitorService);
    }

    public final boolean a(MonitorService monitorService, ga gaVar, THasArguments thasarguments, com.joaomgcd.taskerm.event.sensor.n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(nVar, "input");
        TId tidA = a(monitorService, gaVar, thasarguments);
        r<com.joaomgcd.taskerm.event.sensor.n, THasArguments> rVarA = b().get(tidA);
        if (rVarA == null) {
            rVarA = a(monitorService, tidA, nVar, thasarguments);
            if (rVarA == null) {
                return false;
            }
            b().put(tidA, rVarA);
        }
        rVarA.a(monitorService);
        return true;
    }

    public final void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        Iterator<Map.Entry<TId, r<com.joaomgcd.taskerm.event.sensor.n, THasArguments>>> it = b().entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().d(monitorService);
        }
        b().clear();
    }

    public final void b(MonitorService monitorService, ga gaVar, THasArguments thasarguments, com.joaomgcd.taskerm.event.sensor.n nVar) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(nVar, "input");
        TId tidA = a(monitorService, gaVar, thasarguments);
        r<com.joaomgcd.taskerm.event.sensor.n, THasArguments> rVar = b().get(tidA);
        if (rVar != null) {
            rVar.d(monitorService);
        }
        b().remove(tidA);
    }
}
