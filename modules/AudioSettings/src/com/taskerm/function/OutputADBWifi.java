package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputADBWifi {
    private final boolean distance;

    public OutputADBWifi(boolean z) {
        this.distance = z;
    }

    @TaskerOutputVariable(labelResId = R.string.permission_ADB_WIFI, name = "has_adb_wifi")
    public final boolean getDistance() {
        return this.distance;
    }
}
