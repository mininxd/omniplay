package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class CheckRoot extends FunctionBase<InputCheckRoot, OutputCheckRoot> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.check_root;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputCheckRoot> getInputClass() {
        return InputCheckRoot.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputCheckRoot> getOutputClass() {
        return OutputCheckRoot.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputCheckRoot doIt(Context context, InputCheckRoot inputCheckRoot) {
        k.b(context, "context");
        k.b(inputCheckRoot, "input");
        return new OutputCheckRoot(((cf) ck.a.a(ck.f9218a, "cat /proc/cmdline", 0L, 2, null).b()).a());
    }
}
