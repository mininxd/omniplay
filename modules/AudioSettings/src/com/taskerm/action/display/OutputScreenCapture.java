package com.joaomgcd.taskerm.action.display;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "sc")
public final class OutputScreenCapture {
    private String file;
    private boolean isCapturing;

    public OutputScreenCapture(String str, boolean z) {
        this.file = str;
        this.isCapturing = z;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.file_capture_result, labelResId = R.string.pl_file, name = "file")
    public final String getFile() {
        return this.file;
    }

    public final void setFile(String str) {
        this.file = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.is_capturing_description, labelResId = R.string.pl_is_capturing, name = "is_capturing")
    public final boolean isCapturing() {
        return this.isCapturing;
    }

    public final void setCapturing(boolean z) {
        this.isCapturing = z;
    }
}
