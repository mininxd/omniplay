package com.joaomgcd.taskerm.location;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Location f7689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f7690b;

    public i(Location location, float f2) {
        c.f.b.k.b(location, "location");
        this.f7689a = location;
        this.f7690b = f2;
    }

    public final com.google.android.gms.maps.a a() {
        return com.google.android.gms.maps.b.a(new LatLng(this.f7689a.getLatitude(), this.f7689a.getLongitude()), this.f7690b);
    }
}
