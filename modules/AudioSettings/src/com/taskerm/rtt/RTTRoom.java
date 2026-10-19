package com.joaomgcd.taskerm.rtt;

import androidx.annotation.Keep;
import c.f.b.k;
import c.q;
import cyanogenmod.app.ProfileManager;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RTTRoom {
    private final String name;
    private final RTTReadings readings;

    public RTTRoom(String str, RTTReadings rTTReadings) {
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        k.b(rTTReadings, "readings");
        this.name = str;
        this.readings = rTTReadings;
    }

    public final String getName() {
        return this.name;
    }

    public final RTTReadings getReadings() {
        return this.readings;
    }

    public static /* synthetic */ boolean isInRoom$default(RTTRoom rTTRoom, RTTReading rTTReading, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return rTTRoom.isInRoom(rTTReading, i);
    }

    public final boolean isInRoom(RTTReading rTTReading, int i) {
        k.b(rTTReading, "reading");
        RTTReadings rTTReadings = this.readings;
        if ((rTTReadings instanceof Collection) && rTTReadings.isEmpty()) {
            return false;
        }
        Iterator<RTTReading> it = rTTReadings.iterator();
        while (it.hasNext()) {
            if (it.next().matches$Tasker_5_15_14__marketNoTrialRelease(rTTReading, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return !(k.a((Object) this.name, (Object) ((RTTRoom) obj).name) ^ true);
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.rtt.RTTRoom");
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public final List<String> getMacs() {
        return this.readings.getMacs();
    }
}
