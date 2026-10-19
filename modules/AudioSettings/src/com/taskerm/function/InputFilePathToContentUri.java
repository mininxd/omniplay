package com.joaomgcd.taskerm.function;

import c.f.b.k;
import java.io.File;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputFilePathToContentUri {
    private final File file;

    @FunctionInput(explanationResId = R.string.pl_file, index = 0)
    public static /* synthetic */ void file$annotations() {
    }

    public InputFilePathToContentUri(File file) {
        k.b(file, "file");
        this.file = file;
    }

    public final File getFile() {
        return this.file;
    }
}
