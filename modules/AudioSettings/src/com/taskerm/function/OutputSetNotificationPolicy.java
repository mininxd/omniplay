package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputSetNotificationPolicy {
    private final boolean success;

    public OutputSetNotificationPolicy(boolean z) {
        this.success = z;
    }

    @TaskerOutputVariable(labelResId = R.string.word_success, name = "success")
    public final boolean getSuccess() {
        return this.success;
    }
}
