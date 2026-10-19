package com.joaomgcd.taskerm.action.location;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "pl")
public final class OutputPickLocation {
    private final com.joaomgcd.taskerm.location.h locationAndRadius;

    public OutputPickLocation(com.joaomgcd.taskerm.location.h hVar) {
        k.b(hVar, "locationAndRadius");
        this.locationAndRadius = hVar;
    }

    @TaskerOutputVariable(labelResId = R.string.location_latitude, name = "latitude")
    public final String getLatitude() {
        return aj.a(this.locationAndRadius.d());
    }

    @TaskerOutputVariable(labelResId = R.string.location_longitude, name = "longitude")
    public final String getLongitude() {
        return aj.a(this.locationAndRadius.e());
    }

    @TaskerOutputVariable(labelResId = R.string.location_latitude_longitude, name = "coordinates")
    public final String getLatitudeLongitude() {
        return getLatitude() + ',' + getLongitude();
    }

    @TaskerOutputVariable(labelResId = R.string.locselect_radius_label, name = "radius")
    public final String getRadius() {
        Integer numF = this.locationAndRadius.f();
        if (numF != null) {
            return String.valueOf(numF.intValue());
        }
        return null;
    }

    @TaskerOutputVariable(labelResId = R.string.google_maps_url, name = "map_url")
    public final String getMapUrl() {
        return this.locationAndRadius.c();
    }
}
