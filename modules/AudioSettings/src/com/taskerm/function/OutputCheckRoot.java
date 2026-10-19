package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputCheckRoot {
    private final boolean distance;

    public OutputCheckRoot(boolean z) {
        this.distance = z;
    }

    @TaskerOutputVariable(labelResId = R.string.permission_ROOT, name = "has_root")
    public final boolean getDistance() {
        return this.distance;
    }
}
