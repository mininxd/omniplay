package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputTermuxCommand extends InputForFunction {
    private final String arguments;
    private final Boolean background;
    private final String path;
    private final String workingDirectory;

    @FunctionInput(explanationResId = R.string.program_script_arguments, index = 1)
    public static /* synthetic */ void arguments$annotations() {
    }

    @FunctionInput(explanationResId = R.string.run_command_in_background, index = 3)
    public static /* synthetic */ void background$annotations() {
    }

    @FunctionInput(explanationResId = R.string.program_script_path, index = 0)
    public static /* synthetic */ void path$annotations() {
    }

    @FunctionInput(explanationResId = R.string.session_working_directory, index = 2)
    public static /* synthetic */ void workingDirectory$annotations() {
    }

    public final String getPath() {
        return this.path;
    }

    public final String getArguments() {
        return this.arguments;
    }

    public final String getWorkingDirectory() {
        return this.workingDirectory;
    }

    public final Boolean getBackground() {
        return this.background;
    }

    public InputTermuxCommand(String str, String str2, String str3, Boolean bool) {
        this.path = str;
        this.arguments = str2;
        this.workingDirectory = str3;
        this.background = bool;
    }
}
