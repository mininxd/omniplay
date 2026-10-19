package com.joaomgcd.taskerm.location;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;
import c.s;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dm;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static com.google.android.gms.maps.model.d f7744a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static com.google.android.gms.maps.model.c f7745b;

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ MapView f7746a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f7747b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(MapView mapView, b.a.k.d dVar) {
            super(0);
            this.f7746a = mapView;
            this.f7747b = dVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            this.f7746a.a(new com.google.android.gms.maps.e() { // from class: com.joaomgcd.taskerm.location.p.a.1
                @Override // com.google.android.gms.maps.e
                public final void onMapReady(com.google.android.gms.maps.c cVar) {
                    a.this.f7747b.d_(cVar);
                }
            });
        }
    }

    public static final b.a.l<com.google.android.gms.maps.c> a(MapView mapView) {
        c.f.b.k.b(mapView, "receiver$0");
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<GoogleMap>()");
        com.joaomgcd.taskerm.rx.i.e(new a(mapView, dVarF));
        return dVarF;
    }

    public static final void a(com.google.android.gms.maps.c cVar, i iVar) {
        c.f.b.k.b(cVar, "receiver$0");
        c.f.b.k.b(iVar, "value");
        cVar.b(iVar.a());
    }

    public static final void a(com.google.android.gms.maps.c cVar, Location location) {
        c.f.b.k.b(cVar, "receiver$0");
        c.f.b.k.b(location, "location");
        cVar.b(com.google.android.gms.maps.b.a(new LatLng(location.getLatitude(), location.getLongitude())));
    }

    public static final void b(com.google.android.gms.maps.c cVar, Location location) {
        c.f.b.k.b(cVar, "receiver$0");
        c.f.b.k.b(location, "position");
        a(cVar, location);
        c(cVar, location);
    }

    public static final void c(com.google.android.gms.maps.c cVar, Location location) {
        c.f.b.k.b(cVar, "receiver$0");
        c.f.b.k.b(location, "position");
        com.google.android.gms.maps.model.d dVar = f7744a;
        if (dVar != null) {
            dVar.a();
        }
        f7744a = cVar.a(new MarkerOptions().a(a(location)));
    }

    public static final void a(com.google.android.gms.maps.c cVar, e eVar) {
        c.f.b.k.b(cVar, "receiver$0");
        c.f.b.k.b(eVar, "circle");
        com.google.android.gms.maps.model.c cVar2 = f7745b;
        if (cVar2 != null) {
            cVar2.a();
        }
        f7745b = cVar.a(eVar.a());
    }

    public static final void b(MapView mapView) {
        c.f.b.k.b(mapView, "receiver$0");
        com.google.android.gms.maps.model.d dVar = f7744a;
        if (dVar != null) {
            dVar.a();
        }
        com.google.android.gms.maps.model.c cVar = f7745b;
        if (cVar != null) {
            cVar.a();
        }
        f7744a = (com.google.android.gms.maps.model.d) null;
    }

    public static final void a(com.google.android.gms.maps.c cVar, float f2) {
        c.f.b.k.b(cVar, "receiver$0");
        cVar.b(com.google.android.gms.maps.b.a(f2));
    }

    public static final Location a(LatLng latLng) {
        c.f.b.k.b(latLng, "receiver$0");
        return dm.a(latLng.f3552a, latLng.f3553b);
    }

    public static final LatLng a(Location location) {
        c.f.b.k.b(location, "receiver$0");
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public static final float a(CircleOptions circleOptions) {
        return (circleOptions != null ? a(circleOptions.b()) : 0.0f) + 0.5f;
    }

    public static final float a(double d2) {
        return ((int) (((double) 16) - (Math.log(d2 / ((double) 500)) / Math.log(2.0d)))) + 0.5f;
    }

    public static final com.joaomgcd.taskerm.securesettings.f a() {
        return new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Secure, "location_mode", false, 0, 0, 28, null);
    }

    public static final m a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (m) ((Enum) aj.a(Settings.Secure.getInt(context.getContentResolver(), a().c()), m.class));
    }

    public static final void a(Context context, m mVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(mVar, "value");
        Settings.Secure.putInt(context.getContentResolver(), a().c(), mVar.ordinal());
    }

    public static /* synthetic */ b.a.l a(Context context, m mVar, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 3000;
        }
        return a(context, mVar, j);
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.l<Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f7749a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ m f7750b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context, m mVar) {
            super(0);
            this.f7749a = context;
            this.f7750b = mVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> invoke() {
            p.a(this.f7749a, this.f7750b);
            return b.a.h.a(200L, TimeUnit.MILLISECONDS).a(new b.a.d.i<Long>() { // from class: com.joaomgcd.taskerm.location.p.b.1
                @Override // b.a.d.i
                public final boolean a(Long l) {
                    c.f.b.k.b(l, "it");
                    return ah.af(b.this.f7749a);
                }
            }).d().c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.location.p.b.2
                public final boolean a(Long l) {
                    c.f.b.k.b(l, "it");
                    return true;
                }

                @Override // b.a.d.g
                public /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(a((Long) obj));
                }
            });
        }
    }

    public static final b.a.l<Boolean> a(Context context, m mVar, long j) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(mVar, "locationMode");
        b.a.l<Boolean> lVarD = com.joaomgcd.taskerm.rx.i.a(new b(context, mVar)).b(j, TimeUnit.MILLISECONDS).d(c.f7753a);
        c.f.b.k.a((Object) lVarD, "getInBackground {\n      …xt { Single.just(false) }");
        return lVarD;
    }

    static final class c<T, R> implements b.a.d.g<Throwable, b.a.p<? extends Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f7753a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a(false);
        }
    }
}
