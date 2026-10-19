package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.dh;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class CheckTorch extends FunctionBase<InputCheckTorch, OutputCheckTorch> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.check_torch;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputCheckTorch> getInputClass() {
        return InputCheckTorch.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputCheckTorch> getOutputClass() {
        return OutputCheckTorch.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputCheckTorch doIt(Context context, InputCheckTorch inputCheckTorch) {
        k.b(context, "context");
        k.b(inputCheckTorch, "input");
        Boolean boolB = new dh(context).a().b();
        k.a((Object) boolB, "isOn");
        return new OutputCheckTorch(boolB.booleanValue());
    }
}
