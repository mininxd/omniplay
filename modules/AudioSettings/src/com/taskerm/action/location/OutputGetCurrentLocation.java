package com.joaomgcd.taskerm.action.location;

import android.annotation.TargetApi;
import android.location.Location;
import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gl")
public final class OutputGetCurrentLocation {
    private final Location location;

    public OutputGetCurrentLocation(Location location) {
        k.b(location, "location");
        this.location = location;
    }

    public final Location getLocation() {
        return this.location;
    }

    @TaskerOutputVariable(labelResId = R.string.location_latitude, name = "latitude")
    public final String getLatitude() {
        return aj.a(this.location.getLatitude());
    }

    @TaskerOutputVariable(labelResId = R.string.location_longitude, name = "longitude")
    public final String getLongitude() {
        return aj.a(this.location.getLongitude());
    }

    @TaskerOutputVariable(labelResId = R.string.location_latitude_longitude, name = "coordinates")
    public final String getLatitudeLongitude() {
        return getLatitude() + ',' + getLongitude();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_in_meters, labelResId = R.string.location_latitude_longitude_accuracy, name = "coordinates_accuracy")
    public final String getAccuracy() {
        return aj.a(this.location.getAccuracy(), this.location.hasAccuracy());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_in_meters_per_second, labelResId = R.string.pl_speed, name = "speed")
    public final String getSpeed() {
        return aj.a(this.location.getSpeed(), this.location.hasSpeed());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_in_meters_per_second, labelResId = R.string.pl_speed, minApi = 26, name = "speed_accuracy")
    @TargetApi(26)
    public final String getSpeedAccuracy() {
        return aj.a(this.location.getSpeedAccuracyMetersPerSecond(), this.location.hasSpeedAccuracy());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_in_meters, labelResId = R.string.location_altitude, name = "altitude")
    public final String getAltitude() {
        return aj.a(this.location.getAltitude(), this.location.hasAltitude());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_in_meters, labelResId = R.string.location_altitude_accuracy, minApi = 26, name = "altitude_accuracy")
    @TargetApi(26)
    public final String getAltitudeAccuracy() {
        return aj.a(this.location.getVerticalAccuracyMeters(), this.location.hasAltitude());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_bearing_description, labelResId = R.string.location_bearing, name = "bearing")
    public final String getBearing() {
        return aj.b(this.location.getBearing(), this.location.hasBearing());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_in_degrees, labelResId = R.string.location_accuracy_bearing, minApi = 26, name = "bearing_accuracy")
    @TargetApi(26)
    public final String getBearingAccuracy() {
        return aj.b(this.location.getBearingAccuracyDegrees(), this.location.hasBearingAccuracy());
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.location_time_description, labelResId = R.string.pl_time, minApi = 26, name = "time_seconds")
    public final String getTime() {
        return String.valueOf(this.location.getTime() / ((long) 1000));
    }

    @TaskerOutputVariable(labelResId = R.string.google_maps_url, name = "map_url")
    public final String getMapUrl() {
        return new com.joaomgcd.taskerm.location.h(getLatitude(), getLongitude(), Integer.valueOf(c.g.a.a(this.location.getAccuracy()))).c();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.satellites_explained, labelResId = R.string.satellites, name = "satellites")
    public final String getSatellites() {
        return String.valueOf(com.joaomgcd.taskerm.location.k.a(this.location));
    }
}
