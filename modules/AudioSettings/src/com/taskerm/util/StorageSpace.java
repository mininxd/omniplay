package com.joaomgcd.taskerm.util;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import java.io.File;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject
public final class StorageSpace {
    private final File file;

    public StorageSpace(File file) {
        this.file = file;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.number_in_bytes, labelResId = R.string.free_space, name = "free_space")
    public final long getFreeSpace() {
        File file = this.file;
        if (file != null) {
            return file.getFreeSpace();
        }
        return -1L;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.number_in_bytes, labelResId = R.string.total_space, name = "total_space")
    public final long getTotalSpace() {
        File file = this.file;
        if (file != null) {
            return file.getTotalSpace();
        }
        return -1L;
    }

    public final double getFreePercentage() {
        return (getFreeSpace() / getTotalSpace()) * ((double) 100);
    }

    public final double getUsedPercentage() {
        return aj.a(((double) 100) - getFreePercentage(), 2);
    }

    @TaskerOutputVariable(labelResId = R.string.free_percentage, name = "free_percentage")
    public final String getFreePercentageString() {
        return aj.b(getFreePercentage(), 2);
    }

    @TaskerOutputVariable(labelResId = R.string.used_percentage, name = "used_percentage")
    public final String getUsedPercentageString() {
        return aj.b(getUsedPercentage(), 2);
    }
}
