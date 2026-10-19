package com.joaomgcd.taskerm.location;

import android.location.Location;
import com.google.android.gms.maps.model.CircleOptions;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Location f7677a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final double f7678b;

    public e(Location location, double d2) {
        c.f.b.k.b(location, "location");
        this.f7677a = location;
        this.f7678b = d2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public e(Location location, int i) {
        this(location, i);
        c.f.b.k.b(location, "location");
    }

    public final CircleOptions a() {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.a(p.a(this.f7677a));
        circleOptions.a(this.f7678b);
        circleOptions.a(-16777216);
        circleOptions.b(822018048);
        circleOptions.a(1.0f);
        return circleOptions;
    }

    public final float b() {
        return p.a(a());
    }
}
