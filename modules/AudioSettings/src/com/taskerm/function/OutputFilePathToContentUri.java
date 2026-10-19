package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputFilePathToContentUri {
    private final String uri;

    public OutputFilePathToContentUri(String str) {
        k.b(str, "uri");
        this.uri = str;
    }

    @TaskerOutputVariable(labelResId = R.string.content_uri, name = "content_uri")
    public final String getUri() {
        return this.uri;
    }
}
