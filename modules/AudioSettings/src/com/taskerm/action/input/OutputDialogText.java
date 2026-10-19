package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "td")
public final class OutputDialogText {
    private final String buttonLabel;

    public OutputDialogText(String str) {
        c.f.b.k.b(str, "buttonLabel");
        this.buttonLabel = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.dialog_text_button_description, labelResId = R.string.pl_button, name = "button")
    public final String getButtonLabel() {
        return this.buttonLabel;
    }
}
