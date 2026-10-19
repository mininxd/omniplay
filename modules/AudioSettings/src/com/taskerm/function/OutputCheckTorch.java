package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputCheckTorch {
    private final boolean isTorchOn;

    public OutputCheckTorch(boolean z) {
        this.isTorchOn = z;
    }

    @TaskerOutputVariable(labelResId = R.string.an_torch, name = "is_torch_on")
    public final boolean isTorchOn() {
        return this.isTorchOn;
    }
}
