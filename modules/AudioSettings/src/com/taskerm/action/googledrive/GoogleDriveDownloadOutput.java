package com.joaomgcd.taskerm.action.googledrive;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gd")
public final class GoogleDriveDownloadOutput {
    private String[] localPaths;

    /* JADX WARN: Multi-variable type inference failed */
    public GoogleDriveDownloadOutput() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public GoogleDriveDownloadOutput(String[] strArr) {
        c.f.b.k.b(strArr, "localPaths");
        this.localPaths = strArr;
    }

    public /* synthetic */ GoogleDriveDownloadOutput(String[] strArr, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? new String[0] : strArr);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.local_paths_description, labelResId = R.string.local_paths, name = "local_paths")
    public final String[] getLocalPaths() {
        return this.localPaths;
    }

    public final void setLocalPaths(String[] strArr) {
        c.f.b.k.b(strArr, "<set-?>");
        this.localPaths = strArr;
    }
}
