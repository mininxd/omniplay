package com.joaomgcd.taskerm.helper;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ay;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;
import com.joaomgcd.taskerm.util.dm;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Set;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.gl;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7325a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(j.class), "disposables", "getDisposables()Lio/reactivex/disposables/CompositeDisposable;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(j.class), "disposablesSet", "getDisposablesSet()Ljava/util/Set;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(j.class), "sensorManager", "getSensorManager()Landroid/hardware/SensorManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7326b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7327c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f7328d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b.a.k f7329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f7330f;
    private final az g;
    private final Context h;

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.b.a a() {
        c.e eVar = this.f7326b;
        c.j.g gVar = f7325a[0];
        return (b.a.b.a) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<b.a.b.b> c() {
        c.e eVar = this.f7327c;
        c.j.g gVar = f7325a[1];
        return (Set) eVar.b();
    }

    public <T> b.a.h<T> a(b.a.h<T> hVar) {
        c.f.b.k.b(hVar, "observable");
        return hVar;
    }

    public <T> b.a.l<T> a(b.a.l<T> lVar) {
        c.f.b.k.b(lVar, "single");
        return lVar;
    }

    public final void a(b.a.b bVar) {
        a(this, bVar, (c.f.a.a) null, 2, (Object) null);
    }

    public final void b(b.a.b bVar) {
        a(this, bVar, (Runnable) null, 2, (Object) null);
    }

    public final <T> void b(b.a.l<T> lVar) {
        a(this, lVar, (b.a.d.f) null, 2, (Object) null);
    }

    public b.a.b c(b.a.b bVar) {
        c.f.b.k.b(bVar, "completable");
        return bVar;
    }

    public void m() {
    }

    public final SensorManager r() {
        c.e eVar = this.f7330f;
        c.j.g gVar = f7325a[2];
        return (SensorManager) eVar.b();
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            Set setC = j.this.c();
            c.f.b.k.a((Object) setC, "disposablesSet");
            ArrayList arrayList = new ArrayList();
            for (Object obj : setC) {
                b.a.b.b bVar = (b.a.b.b) obj;
                c.f.b.k.a((Object) bVar, "it");
                if (bVar.a()) {
                    arrayList.add(obj);
                }
            }
            ArrayList<b.a.b.b> arrayList2 = arrayList;
            bn.b("HB", "Disposing " + arrayList2.size() + "; Sizes: " + j.this.a().d() + '-' + j.this.c().size());
            for (b.a.b.b bVar2 : arrayList2) {
                j.this.a().b(bVar2);
                j.this.c().remove(bVar2);
            }
            j.this.f7328d = false;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class d<T> extends c.f.b.l implements c.f.a.b<T, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.a f7334a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(com.joaomgcd.taskerm.util.a aVar) {
            super(1);
            this.f7334a = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Object obj) {
            a(obj);
            return c.s.f2131a;
        }

        public final void a(T t) {
            this.f7334a.call(t);
        }
    }

    public j(Context context) {
        c.f.b.k.b(context, "context");
        this.h = context;
        this.f7326b = c.f.a(b.f7332a);
        this.f7327c = c.f.a(c.f7333a);
        this.f7329e = com.joaomgcd.taskerm.rx.i.f8396a;
        this.f7330f = c.f.a(new e());
        this.g = ba.a("Sensors");
    }

    public final Context t() {
        return this.h;
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.b.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7332a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b.a invoke() {
            return new b.a.b.a();
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<Set<b.a.b.b>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f7333a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Set<b.a.b.b> invoke() {
            return dm.i();
        }
    }

    protected final void a(b.a.b.b bVar) {
        c.f.b.k.b(bVar, "disposable");
        a().a(bVar);
        c().add(bVar);
        if (c().size() <= 300 || this.f7328d) {
            return;
        }
        this.f7328d = true;
        com.joaomgcd.taskerm.rx.i.c(q(), new a());
    }

    public b.a.k q() {
        return this.f7329e;
    }

    public static /* synthetic */ b.a.b.b a(j jVar, b.a.h hVar, b.a.d.f fVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribe");
        }
        if ((i & 2) != 0) {
            fVar = (b.a.d.f) null;
        }
        return jVar.a(hVar, fVar);
    }

    public final <T> b.a.b.b a(b.a.h<T> hVar, b.a.d.f<T> fVar) {
        c.f.b.k.b(hVar, "single");
        b.a.b.b bVarA = com.joaomgcd.taskerm.rx.i.a(a(hVar), this.h, fVar);
        a(bVarA);
        return bVarA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> void a(b.a.l<T> lVar, b.a.d.f<T> fVar, b.a.d.f<Throwable> fVar2) {
        c.f.b.k.b(lVar, "single");
        b.a.b.b bVarA = a(lVar).a(fVar, fVar2);
        c.f.b.k.a((Object) bVarA, "modify(single).subscribe(consumer, consumerErrors)");
        a(bVarA);
    }

    public static /* synthetic */ void a(j jVar, b.a.l lVar, b.a.d.f fVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribe");
        }
        if ((i & 2) != 0) {
            fVar = (b.a.d.f) null;
        }
        jVar.a(lVar, fVar);
    }

    public final <T> void a(b.a.l<T> lVar, b.a.d.f<T> fVar) {
        c.f.b.k.b(lVar, "single");
        a(com.joaomgcd.taskerm.rx.i.a(a(lVar), this.h, fVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(j jVar, b.a.b bVar, c.f.a.a aVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribe");
        }
        if ((i & 2) != 0) {
            aVar = (c.f.a.a) null;
        }
        jVar.a(bVar, (c.f.a.a<c.s>) aVar);
    }

    public final void a(b.a.b bVar, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(bVar, "completable");
        a(com.joaomgcd.taskerm.rx.i.a(c(bVar), this.h, aVar));
    }

    public static /* synthetic */ void a(j jVar, b.a.b bVar, Runnable runnable, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribeJava");
        }
        if ((i & 2) != 0) {
            runnable = (Runnable) null;
        }
        jVar.a(bVar, runnable);
    }

    public final void a(b.a.b bVar, Runnable runnable) {
        c.f.b.k.b(bVar, "completable");
        a(com.joaomgcd.taskerm.rx.i.a(c(bVar), this.h, runnable));
    }

    public static /* synthetic */ void a(j jVar, b.a.l lVar, c.f.a.b bVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribeKt");
        }
        if ((i & 2) != 0) {
            bVar = (c.f.a.b) null;
        }
        jVar.a(lVar, bVar);
    }

    public final <T> void a(b.a.l<T> lVar, c.f.a.b<? super T, c.s> bVar) {
        c.f.b.k.b(lVar, "single");
        a(com.joaomgcd.taskerm.rx.i.a(a(lVar), this.h, bVar));
    }

    public void b() {
        s().g();
        a().b();
    }

    public final void a(c.f.a.a<c.s> aVar) {
        c.f.b.k.b(aVar, "getter");
        b.a.b.b bVarC = com.joaomgcd.taskerm.rx.i.c(q(), aVar);
        c.f.b.k.a((Object) bVarC, "com.joaomgcd.taskerm.rx.…backgroundThread, getter)");
        a(bVarC);
    }

    public final void a(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        b.a.b.b bVarA = com.joaomgcd.taskerm.rx.i.a(q(), runnable);
        c.f.b.k.a((Object) bVarA, "com.joaomgcd.taskerm.rx.…ckgroundThread, runnable)");
        a(bVarA);
    }

    public final void b(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        b.a.b.b bVarA = com.joaomgcd.taskerm.rx.i.a(s().f(), runnable);
        c.f.b.k.a((Object) bVarA, "com.joaomgcd.taskerm.rx.…sors.scheduler, runnable)");
        a(bVarA);
    }

    public final void a(b.a.k kVar, Runnable runnable) {
        c.f.b.k.b(kVar, "scheduler");
        c.f.b.k.b(runnable, "runnable");
        b.a.b.b bVarA = com.joaomgcd.taskerm.rx.i.a(kVar, runnable);
        c.f.b.k.a((Object) bVarA, "com.joaomgcd.taskerm.rx.doIn(scheduler, runnable)");
        a(bVarA);
    }

    public final void c(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        com.joaomgcd.taskerm.rx.i.c(runnable);
    }

    public final void d(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        com.joaomgcd.taskerm.rx.i.d(runnable).b();
    }

    public final <T> b.a.l<T> b(c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(aVar, "runnable");
        return com.joaomgcd.taskerm.rx.i.b(q(), aVar);
    }

    public final <T> b.a.l<T> c(c.f.a.a<? extends b.a.l<T>> aVar) {
        c.f.b.k.b(aVar, "runnable");
        return com.joaomgcd.taskerm.rx.i.a(q(), aVar);
    }

    public final void e(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        a(this, f(runnable), (c.f.a.a) null, 2, (Object) null);
    }

    public final b.a.b f(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        return com.joaomgcd.taskerm.rx.i.b(q(), runnable);
    }

    public final b.a.b a(ay<?> ayVar, Runnable runnable) {
        c.f.b.k.b(ayVar, "scheduler");
        c.f.b.k.b(runnable, "runnable");
        return com.joaomgcd.taskerm.rx.i.a(ayVar, runnable);
    }

    static final class e extends c.f.b.l implements c.f.a.a<SensorManager> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final SensorManager invoke() {
            return gl.a(j.this.t(), "HB");
        }
    }

    public az s() {
        return this.g;
    }

    public final boolean a(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor, int i) {
        if (sensorEventListener == null || sensor == null || sensorManager == null) {
            return false;
        }
        return sensorManager.registerListener(sensorEventListener, sensor, i, s().d());
    }

    public final c.s a(SensorManager sensorManager, SensorEventListener sensorEventListener) {
        c.f.b.k.b(sensorEventListener, "listener");
        if (sensorManager == null) {
            return null;
        }
        sensorManager.unregisterListener(sensorEventListener);
        return c.s.f2131a;
    }

    public final void a(String str, String str2, Bundle bundle, String str3) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(str2, "value");
        c.f.b.k.b(bundle, "localVars");
        c.f.b.k.b(str3, "tag");
        gw.a(this.h, str, str2, true, bundle, str3);
    }

    protected final <T> void a(b.a.l<T> lVar, com.joaomgcd.taskerm.util.a<T> aVar) {
        c.f.b.k.b(lVar, "receiver$0");
        c.f.b.k.b(aVar, "action");
        a(com.joaomgcd.taskerm.rx.i.c(lVar), new d(aVar));
    }

    public final String q(int i) {
        return aj.a(i, this.h, new Object[0]);
    }
}
