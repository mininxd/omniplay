package com.joaomgcd.taskerm.event.app;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "aa")
public final class OutputAssistantAction {
    private final String command;

    /* JADX WARN: Multi-variable type inference failed */
    public OutputAssistantAction() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public OutputAssistantAction(String str) {
        this.command = str;
    }

    public /* synthetic */ OutputAssistantAction(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.assistant_action_command_description, labelResId = R.string.command_text, name = "command")
    public final String getCommand() {
        return this.command;
    }
}
