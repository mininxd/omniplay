package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.k.a;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GetSims extends FunctionBase<InputGetSims, OutputGetSims> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.get_sims;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGetSims> getInputClass() {
        return InputGetSims.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGetSims> getOutputClass() {
        return OutputGetSims.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputGetSims doIt(Context context, InputGetSims inputGetSims) {
        k.b(context, "context");
        k.b(inputGetSims, "input");
        return new OutputGetSims(a.f7559a.a(context));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public String[] getPermissions(Context context, InputGetSims inputGetSims) {
        k.b(context, "context");
        k.b(inputGetSims, "input");
        return new String[]{"android.permission.READ_PHONE_STATE"};
    }
}
