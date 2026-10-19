package com.joaomgcd.taskerm.function;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputGetThermalStatus {
    private final String status;
    private final int statusCode;

    public OutputGetThermalStatus(int i, String str) {
        k.b(str, NotificationCompat.CATEGORY_STATUS);
        this.statusCode = i;
        this.status = str;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_status_code, name = "thermal_status_code")
    public final int getStatusCode() {
        return this.statusCode;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_status, name = "thermal_status")
    public final String getStatus() {
        return this.status;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OutputGetThermalStatus(Context context, ThermalStatus thermalStatus) {
        this(thermalStatus.getIntValue(), aj.a(thermalStatus.getTextResId(), context, new Object[0]));
        k.b(context, "context");
        k.b(thermalStatus, "thermalStatus");
    }
}
