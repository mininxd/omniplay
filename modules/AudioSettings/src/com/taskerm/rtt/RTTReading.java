package com.joaomgcd.taskerm.rtt;

import androidx.annotation.Keep;
import c.f.b.k;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RTTReading {
    private final RTTDatas rttDatas;

    public RTTReading(RTTDatas rTTDatas) {
        k.b(rTTDatas, "rttDatas");
        this.rttDatas = rTTDatas;
    }

    public final RTTDatas getRttDatas() {
        return this.rttDatas;
    }

    private final RTTData forMac(String str) {
        RTTData next;
        Iterator<RTTData> it = this.rttDatas.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (k.a((Object) next.getMac(), (Object) str)) {
                return next;
            }
        }
        next = null;
        return next;
    }

    public final boolean matches$Tasker_5_15_14__marketNoTrialRelease(RTTReading rTTReading, int i) {
        k.b(rTTReading, "other");
        RTTDatas rTTDatas = this.rttDatas;
        if ((rTTDatas instanceof Collection) && rTTDatas.isEmpty()) {
            return true;
        }
        for (RTTData rTTData : rTTDatas) {
            RTTData rTTDataForMac = rTTReading.forMac(rTTData.getMac());
            if (!(rTTDataForMac != null ? rTTDataForMac.matches$Tasker_5_15_14__marketNoTrialRelease(rTTData, i) : false)) {
                return false;
            }
        }
        return true;
    }

    public final List<String> getMacs() {
        return this.rttDatas.getMacs();
    }
}
