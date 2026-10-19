package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "ld")
public final class OutputDialogListMultiple extends bg {
    private final String buttonLabel;
    private final String[] selected;
    private final Integer[] selectedIndexes;

    @TaskerOutputVariable(labelResId = R.string.pl_selected, name = "selected")
    public final String[] getSelected() {
        return this.selected;
    }

    @TaskerOutputVariable(labelResId = R.string.selected_index, name = "selected_index")
    public final Integer[] getSelectedIndexes() {
        return this.selectedIndexes;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.dialog_text_button_description, labelResId = R.string.pl_button, name = "button")
    public final String getButtonLabel() {
        return this.buttonLabel;
    }

    public OutputDialogListMultiple(String[] strArr, Integer[] numArr, String str) {
        c.f.b.k.b(strArr, "selected");
        c.f.b.k.b(str, "buttonLabel");
        this.selected = strArr;
        this.selectedIndexes = numArr;
        this.buttonLabel = str;
    }
}
