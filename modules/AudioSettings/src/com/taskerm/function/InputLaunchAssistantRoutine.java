package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputLaunchAssistantRoutine {
    private final String name;

    @FunctionInput(explanationResId = R.string.routine_name, index = 0)
    public static /* synthetic */ void name$annotations() {
    }

    public InputLaunchAssistantRoutine(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }
}
