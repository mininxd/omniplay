package com.joaomgcd.taskerm.location;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import c.f.b.v;
import c.f.b.w;
import c.f.b.x;
import c.q;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dm;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7691a = {x.a(new v(x.a(j.class), "provider", "getProvider()Lcom/google/android/gms/location/FusedLocationProviderClient;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7692b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f7693c;

    /* JADX INFO: Access modifiers changed from: private */
    public final com.google.android.gms.location.e b() {
        c.e eVar = this.f7692b;
        c.j.g gVar = f7691a[0];
        return (com.google.android.gms.location.e) eVar.b();
    }

    static final class k extends c.f.b.l implements c.f.a.a<com.google.android.gms.location.e> {
        k() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.google.android.gms.location.e invoke() {
            return com.google.android.gms.location.i.a(j.this.f7693c);
        }
    }

    public j(Context context) {
        c.f.b.k.b(context, "context");
        this.f7693c = context;
        this.f7692b = c.f.a(new k());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean a(Location location) {
        return dm.g() - location.getElapsedRealtimeNanos() > 5000000000L;
    }

    public final b.a.h<Location> a() {
        LocationManager locationManagerAd = ah.ad(this.f7693c);
        if (locationManagerAd == null) {
            b.a.h<Location> hVarA = b.a.h.a((Throwable) new RuntimeException("Couldn't get location manager"));
            c.f.b.k.a((Object) hVarA, "Observable.error(java.la…t get location manager\"))");
            return hVarA;
        }
        b.a.k.b bVarJ = b.a.k.b.j();
        if (bVarJ == null) {
            throw new q("null cannot be cast to non-null type io.reactivex.subjects.PublishSubject<android.location.Location>");
        }
        C0193j c0193j = new C0193j(bVarJ);
        locationManagerAd.requestLocationUpdates("gps", 0L, 0.0f, c0193j, com.joaomgcd.taskerm.location.k.f7727a.e());
        b.a.h hVarA2 = bVarJ.a((b.a.d.a) new i(locationManagerAd, c0193j));
        c.f.b.k.a((Object) hVarA2, "publisher.doFinally {\n  …cationListener)\n        }");
        return hVarA2;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.j$j, reason: collision with other inner class name */
    public static final class C0193j implements LocationListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.a.k.b f7725b;

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            c.f.b.k.b(str, "provider");
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        C0193j(b.a.k.b bVar) {
            this.f7725b = bVar;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            c.f.b.k.b(location, "location");
            if (j.this.a(location)) {
                bn.b("LocationGetter", "Got an old school update that's too old");
            } else {
                bn.b("LocationGetter", "Got an old school update!");
                this.f7725b.b_(location);
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            c.f.b.k.b(str, "provider");
            if (!c.f.b.k.a((Object) str, (Object) "gps")) {
                return;
            }
            com.joaomgcd.taskerm.rx.i.a(this.f7725b, new RuntimeException("Location was disabled (" + str + ')'));
        }
    }

    static final class i implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ LocationManager f7722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ C0193j f7723b;

        i(LocationManager locationManager, C0193j c0193j) {
            this.f7722a = locationManager;
            this.f7723b = c0193j;
        }

        @Override // b.a.d.a
        public final void run() {
            bn.b("LocationGetter", "Stopping location old school updates");
            this.f7722a.removeUpdates(this.f7723b);
        }
    }

    public final b.a.h<LocationResult> a(com.joaomgcd.taskerm.location.g gVar, long j) {
        c.f.b.k.b(gVar, "accuracy");
        if (!ah.af(this.f7693c)) {
            b.a.h<LocationResult> hVarA = b.a.h.a((Throwable) new RuntimeException("Location is off. Can't get location."));
            c.f.b.k.a((Object) hVarA, "Observable.error(Runtime…f. Can't get location.\"))");
            return hVarA;
        }
        b.a.k.b bVarJ = b.a.k.b.j();
        if (bVarJ == null) {
            throw new q("null cannot be cast to non-null type io.reactivex.subjects.PublishSubject<com.google.android.gms.location.LocationResult>");
        }
        LocationRequest locationRequestA = new LocationRequest().b(100L).a(j).a(gVar.a());
        h hVar = new h(gVar, bVarJ);
        bn.b("LocationGetter", "Requesting location updates with " + gVar + " accuracy");
        b().a(locationRequestA, hVar, com.joaomgcd.taskerm.location.k.f7727a.e());
        b.a.h hVarA2 = bVarJ.a((b.a.d.a) new g(gVar, hVar));
        c.f.b.k.a((Object) hVarA2, "publisher.doFinally {\n  …dates(callback)\n        }");
        return hVarA2;
    }

    public static final class h extends com.google.android.gms.location.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.location.g f7720a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.a.k.b f7721b;

        h(com.joaomgcd.taskerm.location.g gVar, b.a.k.b bVar) {
            this.f7720a = gVar;
            this.f7721b = bVar;
        }

        @Override // com.google.android.gms.location.g
        public void a(LocationResult locationResult) {
            Location locationA;
            if (locationResult == null || (locationA = locationResult.a()) == null) {
                return;
            }
            long jG = dm.g() - locationA.getElapsedRealtimeNanos();
            if (jG > 5000000000L) {
                bn.b("LocationGetter", "Got a " + this.f7720a + " update that's too old: " + (jG / 1000000000) + " seconds of age");
                return;
            }
            bn.b("LocationGetter", "Got a " + this.f7720a + " update!");
            this.f7721b.b_(locationResult);
        }

        @Override // com.google.android.gms.location.g
        public void a(LocationAvailability locationAvailability) {
            c.f.b.k.b(locationAvailability, "availability");
            super.a(locationAvailability);
        }
    }

    static final class g implements b.a.d.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.location.g f7718b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f7719c;

        g(com.joaomgcd.taskerm.location.g gVar, h hVar) {
            this.f7718b = gVar;
            this.f7719c = hVar;
        }

        @Override // b.a.d.a
        public final void run() {
            bn.b("LocationGetter", "Stopping location updates with " + this.f7718b + " accuracy");
            j.this.b().a((com.google.android.gms.location.g) this.f7719c);
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Integer f7694a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.joaomgcd.taskerm.location.d f7695b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.joaomgcd.taskerm.location.d f7696c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final C0191a f7697d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Long f7698e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Boolean f7699f;
        private final Integer g;
        private final Boolean h;

        public a() {
            this(null, null, null, null, null, null, null, null, 255, null);
        }

        public a(Integer num, com.joaomgcd.taskerm.location.d dVar, com.joaomgcd.taskerm.location.d dVar2, C0191a c0191a, Long l, Boolean bool, Integer num2, Boolean bool2) {
            this.f7694a = num;
            this.f7695b = dVar;
            this.f7696c = dVar2;
            this.f7697d = c0191a;
            this.f7698e = l;
            this.f7699f = bool;
            this.g = num2;
            this.h = bool2;
        }

        public /* synthetic */ a(Integer num, com.joaomgcd.taskerm.location.d dVar, com.joaomgcd.taskerm.location.d dVar2, C0191a c0191a, Long l, Boolean bool, Integer num2, Boolean bool2, int i, c.f.b.g gVar) {
            this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (com.joaomgcd.taskerm.location.d) null : dVar, (i & 4) != 0 ? (com.joaomgcd.taskerm.location.d) null : dVar2, (i & 8) != 0 ? (C0191a) null : c0191a, (i & 16) != 0 ? (Long) null : l, (i & 32) != 0 ? (Boolean) null : bool, (i & 64) != 0 ? (Integer) null : num2, (i & 128) != 0 ? (Boolean) null : bool2);
        }

        public final Long a() {
            return this.f7698e;
        }

        public final Boolean b() {
            return this.f7699f;
        }

        public final Boolean c() {
            return this.h;
        }

        @TargetApi(26)
        public final boolean a(Location location) {
            Integer num;
            c.f.b.k.b(location, "location");
            Integer num2 = this.f7694a;
            if (num2 != null) {
                int iIntValue = num2.intValue();
                if (!location.hasAccuracy() || location.getAccuracy() > iIntValue) {
                    return false;
                }
            }
            com.joaomgcd.taskerm.location.d dVar = this.f7695b;
            if (dVar != null && (!location.hasSpeed() || !dVar.a(location.getSpeed()))) {
                return false;
            }
            com.joaomgcd.taskerm.location.d dVar2 = this.f7696c;
            if (dVar2 != null && (!location.hasAltitude() || !dVar2.a(location.getAltitude()).booleanValue())) {
                return false;
            }
            C0191a c0191a = this.f7697d;
            if (c0191a != null && !c0191a.a(location)) {
                return false;
            }
            if (c.f.b.k.a((Object) this.h, (Object) true) && com.joaomgcd.taskerm.location.k.a(location) < 1) {
                return false;
            }
            if (com.joaomgcd.taskerm.util.f.f9323b.r() && (num = this.g) != null) {
                int iIntValue2 = num.intValue();
                if (!location.hasSpeedAccuracy() || location.getSpeedAccuracyMetersPerSecond() > iIntValue2) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.j$a$a, reason: collision with other inner class name */
        public static final class C0191a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0192a f7700a = new C0192a(null);

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final double f7701b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private final double f7702c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private final int f7703d;

            public C0191a(double d2, double d3, int i) {
                this.f7701b = d2;
                this.f7702c = d3;
                this.f7703d = i;
            }

            public final boolean a(Location location) {
                c.f.b.k.b(location, "location");
                return ((float) this.f7703d) >= dm.a(this.f7701b, this.f7702c).distanceTo(location);
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.j$a$a$a, reason: collision with other inner class name */
            public static final class C0192a {
                private C0192a() {
                }

                public /* synthetic */ C0192a(c.f.b.g gVar) {
                    this();
                }

                public final C0191a a(String str) {
                    if (str == null) {
                        return null;
                    }
                    List listB = c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
                    if (listB.size() != 3) {
                        throw new RuntimeException("Near Location has to be 3 values: latitude,longitude,radius");
                    }
                    String str2 = (String) listB.get(0);
                    String str3 = (String) listB.get(1);
                    String str4 = (String) listB.get(2);
                    Double dB = c.l.n.b(str2);
                    if (dB == null) {
                        return null;
                    }
                    double dDoubleValue = dB.doubleValue();
                    Double dB2 = c.l.n.b(str3);
                    if (dB2 == null) {
                        return null;
                    }
                    double dDoubleValue2 = dB2.doubleValue();
                    Integer numC = c.l.n.c(str4);
                    if (numC != null) {
                        return new C0191a(dDoubleValue, dDoubleValue2, numC.intValue());
                    }
                    return null;
                }
            }
        }
    }

    public final b.a.l<Location> a(Boolean bool) {
        if (c.f.b.k.a((Object) bool, (Object) true)) {
            LocationManager locationManagerAd = ah.ad(this.f7693c);
            return b.a.l.a(locationManagerAd != null ? locationManagerAd.getLastKnownLocation("gps") : null);
        }
        com.google.android.gms.location.e eVarB = b();
        c.f.b.k.a((Object) eVarB, "provider");
        com.google.android.gms.c.e<Location> eVarA = eVarB.a();
        c.f.b.k.a((Object) eVarA, "provider.lastLocation");
        return aj.a((com.google.android.gms.c.e) eVarA);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, android.location.Location] */
    public final b.a.l<Location> a(a aVar) {
        b.a.b.b bVarA;
        b.a.b.b bVarA2;
        Object lVar;
        b.a.b.b bVarA3;
        Long lA;
        Boolean boolC;
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<Location>()");
        w.d dVar = new w.d();
        dVar.f2028a = (Location) 0;
        c cVar = new c(dVar, aVar, dVarF);
        e eVar = e.f7712a;
        boolean zBooleanValue = (aVar == null || (boolC = aVar.c()) == null) ? false : boolC.booleanValue();
        b.a.d.f<? super Throwable> dVar2 = new d<>(dVarF);
        b.a.b.b bVarA4 = a().a(cVar, dVar2);
        if (zBooleanValue) {
            bVarA = null;
        } else {
            bVarA = a(com.joaomgcd.taskerm.location.g.HighAccuracy, 1000L).d((b.a.d.g<? super LocationResult, ? extends R>) (eVar != null ? new l(eVar) : eVar)).a(cVar, dVar2);
        }
        if (zBooleanValue) {
            bVarA2 = null;
        } else {
            bVarA2 = a(com.joaomgcd.taskerm.location.g.Balanced, 1000L).d((b.a.d.g<? super LocationResult, ? extends R>) (eVar != null ? new l(eVar) : eVar)).a(cVar, dVar2);
        }
        if (zBooleanValue) {
            bVarA3 = null;
        } else {
            b.a.h<LocationResult> hVarA = a(com.joaomgcd.taskerm.location.g.LowPower, 1000L);
            if (eVar != null) {
                lVar = eVar;
                lVar = new l(eVar);
            }
            lVar = eVar;
            bVarA3 = hVarA.d((b.a.d.g<? super LocationResult, ? extends R>) lVar).a(cVar, dVar2);
        }
        b.a.l<Location> lVarB = dVarF.a((b.a.d.a) new f(bVarA4, bVarA, bVarA2, bVarA3)).b((aVar == null || (lA = aVar.a()) == null) ? 30L : lA.longValue(), TimeUnit.SECONDS);
        if (c.f.b.k.a((Object) (aVar != null ? aVar.b() : null), (Object) true)) {
            lVarB = lVarB.d(new b(dVar, aVar));
        }
        c.f.b.k.a((Object) lVarB, "result");
        return lVarB;
    }

    static final class c<T> implements b.a.d.f<Location> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.d f7708a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a f7709b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f7710c;

        c(w.d dVar, a aVar, b.a.k.d dVar2) {
            this.f7708a = dVar;
            this.f7709b = aVar;
            this.f7710c = dVar2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Location location) {
            c.f.b.k.b(location, "result");
            this.f7708a.f2028a = location;
            if (this.f7709b == null || this.f7709b.a(location)) {
                this.f7710c.d_(location);
            }
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<LocationResult, Location> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f7712a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Location invoke(LocationResult locationResult) {
            c.f.b.k.b(locationResult, "locationResult");
            return locationResult.a();
        }
    }

    static final class d<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f7711a;

        d(b.a.k.d dVar) {
            this.f7711a = dVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "error");
            com.joaomgcd.taskerm.rx.i.a(this.f7711a, th);
        }
    }

    static final class f implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f7713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f7714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f7715c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f7716d;

        f(b.a.b.b bVar, b.a.b.b bVar2, b.a.b.b bVar3, b.a.b.b bVar4) {
            this.f7713a = bVar;
            this.f7714b = bVar2;
            this.f7715c = bVar3;
            this.f7716d = bVar4;
        }

        @Override // b.a.d.a
        public final void run() {
            b.a.b.b bVar = this.f7713a;
            if (bVar != null) {
                bVar.b();
            }
            b.a.b.b bVar2 = this.f7714b;
            if (bVar2 != null) {
                bVar2.b();
            }
            b.a.b.b bVar3 = this.f7715c;
            if (bVar3 != null) {
                bVar3.b();
            }
            b.a.b.b bVar4 = this.f7716d;
            if (bVar4 != null) {
                bVar4.b();
            }
        }
    }

    static final class b<T, R> implements b.a.d.g<Throwable, b.a.p<? extends Location>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.d f7705b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ a f7706c;

        b(w.d dVar, a aVar) {
            this.f7705b = dVar;
            this.f7706c = aVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.p<? extends Location> apply(Throwable th) {
            b.a.l<Location> lVarD;
            c.f.b.k.b(th, "it");
            if (((Location) this.f7705b.f2028a) != null) {
                return b.a.l.a((Location) this.f7705b.f2028a);
            }
            b.a.l<Location> lVarA = j.this.a(this.f7706c.c());
            return (lVarA == null || (lVarD = lVarA.d(new b.a.d.g<Throwable, b.a.p<? extends Location>>() { // from class: com.joaomgcd.taskerm.location.j.b.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<Location> apply(Throwable th2) {
                    c.f.b.k.b(th2, "<anonymous parameter 0>");
                    return b.a.l.b(new RuntimeException("Timed out and no last location present"));
                }
            })) == null) ? b.a.l.b(th) : lVarD;
        }
    }
}
