package com.joaomgcd.taskerm.action;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f5515a;

    public j(boolean z) {
        this.f5515a = z;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.action_toggle_new_state_description, labelResId = R.string.action_toggle_new_state, name = "new_state")
    public final boolean getNewState() {
        return this.f5515a;
    }
}
