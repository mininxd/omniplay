package com.joaomgcd.taskerm.action.f;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "vm")
class al {
    private final String output;

    public al(String str) {
        c.f.b.k.b(str, "output");
        this.output = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.result_of_the_map_operation, index = 1, labelResId = R.string.output, name = "output")
    public final String getOutput() {
        return this.output;
    }
}
