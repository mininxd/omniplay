package com.joaomgcd.taskerm.action.system;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "aa")
public final class OutputActionAdmin {
    private final String output;

    public OutputActionAdmin(String str) {
        this.output = str;
    }

    @TaskerOutputVariable(labelResId = R.string.output, name = "output")
    public final String getOutput() {
        return this.output;
    }
}
