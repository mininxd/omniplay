package com.joaomgcd.taskerm.function;

import android.content.Context;
import android.os.PowerManager;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;

/* JADX INFO: loaded from: classes.dex */
public final class GetThermalStatusKt {
    public static final ThermalStatus getCurrentThermalStatus(Context context) {
        k.b(context, "receiver$0");
        PowerManager powerManagerAq = ah.aq(context);
        if (powerManagerAq == null) {
            return null;
        }
        int currentThermalStatus = powerManagerAq.getCurrentThermalStatus();
        for (ThermalStatus thermalStatus : ThermalStatus.values()) {
            if (thermalStatus.getIntValue() == currentThermalStatus) {
                return thermalStatus;
            }
        }
        return null;
    }
}
