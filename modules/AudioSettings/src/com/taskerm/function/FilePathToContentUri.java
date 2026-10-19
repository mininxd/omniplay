package com.joaomgcd.taskerm.function;

import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import com.joaomgcd.taskerm.util.dk;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class FilePathToContentUri extends FunctionBase<InputFilePathToContentUri, OutputFilePathToContentUri> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.file_to_content_uri;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputFilePathToContentUri> getInputClass() {
        return InputFilePathToContentUri.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputFilePathToContentUri> getOutputClass() {
        return OutputFilePathToContentUri.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputFilePathToContentUri doIt(Context context, InputFilePathToContentUri inputFilePathToContentUri) {
        k.b(context, "context");
        k.b(inputFilePathToContentUri, "input");
        String string = dk.a(inputFilePathToContentUri.getFile(), context, (String[]) null, (Intent) null, false, 14, (Object) null).toString();
        k.a((Object) string, "input.file.getUriForShare(context).toString()");
        return new OutputFilePathToContentUri(string);
    }
}
