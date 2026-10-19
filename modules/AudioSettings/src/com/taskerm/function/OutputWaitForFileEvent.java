package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "fe")
public final class OutputWaitForFileEvent {
    private final String path;

    public OutputWaitForFileEvent(String str) {
        this.path = str;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_path, name = "path")
    public final String getPath() {
        return this.path;
    }
}
