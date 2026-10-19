package com.joaomgcd.taskerm.event.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "pms")
public final class OutputPowerMenuShown {
    private final boolean addControls;

    public OutputPowerMenuShown(boolean z) {
        this.addControls = z;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.add_controls_description, labelResId = R.string.add_controls, name = "add_controls")
    public final boolean getAddControls() {
        return this.addControls;
    }
}
