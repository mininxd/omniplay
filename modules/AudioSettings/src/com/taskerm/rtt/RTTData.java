package com.joaomgcd.taskerm.rtt;

import android.net.MacAddress;
import android.net.wifi.rtt.RangingResult;
import androidx.annotation.Keep;
import c.f.b.k;
import cyanogenmod.os.Build;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RTTData {
    private final int deviation;
    private final int distance;
    private final String mac;

    public static /* synthetic */ RTTData copy$default(RTTData rTTData, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = rTTData.mac;
        }
        if ((i3 & 2) != 0) {
            i = rTTData.distance;
        }
        if ((i3 & 4) != 0) {
            i2 = rTTData.deviation;
        }
        return rTTData.copy(str, i, i2);
    }

    public final String component1() {
        return this.mac;
    }

    public final int component2() {
        return this.distance;
    }

    public final int component3() {
        return this.deviation;
    }

    public final RTTData copy(String str, int i, int i2) {
        k.b(str, "mac");
        return new RTTData(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RTTData) {
                RTTData rTTData = (RTTData) obj;
                if (k.a((Object) this.mac, (Object) rTTData.mac)) {
                    if (this.distance == rTTData.distance) {
                        if (this.deviation == rTTData.deviation) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.mac;
        return ((((str != null ? str.hashCode() : 0) * 31) + this.distance) * 31) + this.deviation;
    }

    public String toString() {
        return "RTTData(mac=" + this.mac + ", distance=" + this.distance + ", deviation=" + this.deviation + ")";
    }

    public RTTData(String str, int i, int i2) {
        k.b(str, "mac");
        this.mac = str;
        this.distance = i;
        this.deviation = i2;
    }

    public final int getDeviation() {
        return this.deviation;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final String getMac() {
        return this.mac;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public RTTData(RangingResult rangingResult) {
        String string;
        k.b(rangingResult, "rangingResult");
        MacAddress macAddress = rangingResult.getMacAddress();
        this((macAddress == null || (string = macAddress.toString()) == null) ? Build.UNKNOWN : string, rangingResult.getDistanceMm(), rangingResult.getDistanceStdDevMm());
    }

    public final boolean matches$Tasker_5_15_14__marketNoTrialRelease(RTTData rTTData, int i) {
        c.k kVar;
        k.b(rTTData, "other");
        if (!k.a((Object) this.mac, (Object) rTTData.mac)) {
            return false;
        }
        if (this.distance < rTTData.distance) {
            kVar = new c.k(this, rTTData);
        } else {
            kVar = new c.k(rTTData, this);
        }
        return ((RTTData) kVar.c()).getDistancePlusDeviation(i) > ((RTTData) kVar.d()).getDistanceMinusDeviation(i);
    }

    private final int getDeviationWithAdjustment(int i) {
        return this.deviation + i;
    }

    private final int getDistanceMinusDeviation(int i) {
        return this.distance - getDeviationWithAdjustment(i);
    }

    private final int getDistancePlusDeviation(int i) {
        return this.distance + getDeviationWithAdjustment(i);
    }
}
