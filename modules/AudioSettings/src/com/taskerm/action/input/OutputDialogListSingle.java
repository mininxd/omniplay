package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "ld")
public final class OutputDialogListSingle extends bg {
    private final String buttonLabel;
    private final boolean isLongClick;
    private final String selected;
    private final Integer selectedIndex;

    @TaskerOutputVariable(labelResId = R.string.pl_selected, name = "selected")
    public final String getSelected() {
        return this.selected;
    }

    @TaskerOutputVariable(labelResId = R.string.selected_index, name = "selected_index")
    public final Integer getSelectedIndex() {
        return this.selectedIndex;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.dialog_text_button_description, labelResId = R.string.pl_button, name = "button")
    public final String getButtonLabel() {
        return this.buttonLabel;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.long_click_description, labelResId = R.string.pl_long, name = "long")
    public final boolean isLongClick() {
        return this.isLongClick;
    }

    public OutputDialogListSingle(String str, Integer num, String str2, boolean z) {
        this.selected = str;
        this.selectedIndex = num;
        this.buttonLabel = str2;
        this.isLongClick = z;
    }
}
