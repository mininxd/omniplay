package com.joaomgcd.taskerm.action.file;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "bf")
public final class OutputBrowseFiles {
    private final String[] files;

    public OutputBrowseFiles(String[] strArr) {
        k.b(strArr, "files");
        this.files = strArr;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_file, name = "files")
    public final String[] getFiles() {
        return this.files;
    }
}
