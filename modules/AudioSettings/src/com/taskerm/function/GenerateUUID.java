package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.dm;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GenerateUUID extends FunctionBase<InputGenerateUUID, OutputGenerateUUID> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.generate_uuid;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGenerateUUID> getInputClass() {
        return InputGenerateUUID.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGenerateUUID> getOutputClass() {
        return OutputGenerateUUID.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputGenerateUUID doIt(Context context, InputGenerateUUID inputGenerateUUID) {
        k.b(context, "context");
        k.b(inputGenerateUUID, "input");
        return new OutputGenerateUUID(dm.j());
    }
}
