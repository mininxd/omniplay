package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputShortcutDialog {
    private final String shortcutUri;

    public OutputShortcutDialog(String str) {
        this.shortcutUri = str;
    }

    @TaskerOutputVariable(labelResId = R.string.an_shortcut, name = "shortcut")
    public final String getShortcutUri() {
        return this.shortcutUri;
    }
}
