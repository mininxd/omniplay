package com.joaomgcd.taskerm.keyboard;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "kb")
public final class OutputKeyboardCommand {
    private final String inputText;
    private final String selectedText;
    private final String textAfterCursor;
    private final String textBeforeCursor;

    public OutputKeyboardCommand() {
        this(null, null, null, null, 15, null);
    }

    public OutputKeyboardCommand(String str, String str2, String str3, String str4) {
        this.inputText = str;
        this.selectedText = str2;
        this.textBeforeCursor = str3;
        this.textAfterCursor = str4;
    }

    public /* synthetic */ OutputKeyboardCommand(String str, String str2, String str3, String str4, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.input_text_description, labelResId = R.string.pl_text, name = "text")
    public final String getInputText() {
        return this.inputText;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.input_text_selected_description, labelResId = R.string.input_text_selected, name = "text_selected")
    public final String getSelectedText() {
        return this.selectedText;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.input_text_before_cursor_description, labelResId = R.string.input_text_before_cursor, name = "text_before_cursor")
    public final String getTextBeforeCursor() {
        return this.textBeforeCursor;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.input_text_after_cursor_description, labelResId = R.string.input_text_after_cursor, name = "text_after_cursor")
    public final String getTextAfterCursor() {
        return this.textAfterCursor;
    }
}
