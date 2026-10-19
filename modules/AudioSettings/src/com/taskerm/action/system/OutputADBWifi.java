package com.joaomgcd.taskerm.action.system;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "aw")
public final class OutputADBWifi {
    private final String output;

    public OutputADBWifi(String str) {
        c.f.b.k.b(str, "output");
        this.output = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.command_output_description, labelResId = R.string.output, name = "output")
    public final String getOutput() {
        return this.output;
    }
}
