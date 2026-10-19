package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class bh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5476a;

    public bh(String str) {
        c.f.b.k.b(str, "inputText");
        this.f5476a = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.dialog_pick_input_input_description, labelResId = R.string.pl_input, name = "input")
    public final String getInputText() {
        return this.f5476a;
    }
}
