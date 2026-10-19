package com.joaomgcd.taskerm.dialog;

import android.hardware.Sensor;

/* JADX INFO: loaded from: classes.dex */
public final class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Sensor f6286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6287b;

    public final Sensor b() {
        return this.f6286a;
    }

    public final String c() {
        return this.f6287b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof at)) {
            return false;
        }
        at atVar = (at) obj;
        return c.f.b.k.a(this.f6286a, atVar.f6286a) && c.f.b.k.a((Object) this.f6287b, (Object) atVar.f6287b);
    }

    public int hashCode() {
        Sensor sensor = this.f6286a;
        int iHashCode = (sensor != null ? sensor.hashCode() : 0) * 31;
        String str = this.f6287b;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "ResultPickSensor(sensor=" + this.f6286a + ", sensorString=" + this.f6287b + ")";
    }

    public at(Sensor sensor, String str) {
        c.f.b.k.b(sensor, "sensor");
        c.f.b.k.b(str, "sensorString");
        this.f6286a = sensor;
        this.f6287b = str;
    }

    public final String a() {
        return this.f6287b;
    }
}
