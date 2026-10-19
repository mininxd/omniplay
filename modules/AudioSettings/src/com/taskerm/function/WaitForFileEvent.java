package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.al;
import java.io.File;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class WaitForFileEvent extends FunctionBase<InputWaitForFileEvent, OutputWaitForFileEvent> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.wait_for_file_event;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputWaitForFileEvent> getInputClass() {
        return InputWaitForFileEvent.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputWaitForFileEvent> getOutputClass() {
        return OutputWaitForFileEvent.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputWaitForFileEvent doIt(Context context, InputWaitForFileEvent inputWaitForFileEvent) {
        k.b(context, "context");
        k.b(inputWaitForFileEvent, "input");
        File fileB = new al(inputWaitForFileEvent.getFilePath(), inputWaitForFileEvent.getEvent().a()).a().b(inputWaitForFileEvent.getTimeoutSeconds(), TimeUnit.SECONDS).b();
        k.a((Object) fileB, "path");
        return new OutputWaitForFileEvent(fileB.getAbsolutePath());
    }
}
