package com.joaomgcd.taskerm.event.system;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "lc")
public final class OutputLogCatEntry {
    private final String text;

    public OutputLogCatEntry(String str) {
        c.f.b.k.b(str, "text");
        this.text = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.logcat_entry_text_description, labelResId = R.string.pl_text, name = "text")
    public final String getText() {
        return this.text;
    }
}
