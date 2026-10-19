package com.joaomgcd.taskerm.event.app;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "sa")
public final class OutputSecondaryAppOpened {
    private final String action;

    /* JADX WARN: Multi-variable type inference failed */
    public OutputSecondaryAppOpened() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public OutputSecondaryAppOpened(String str) {
        this.action = str;
    }

    public /* synthetic */ OutputSecondaryAppOpened(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.secondary_app_opened_action_description, labelResId = R.string.word_action, name = "action")
    public final String getAction() {
        return this.action;
    }
}
