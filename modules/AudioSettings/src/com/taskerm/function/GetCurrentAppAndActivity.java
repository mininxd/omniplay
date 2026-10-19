package com.joaomgcd.taskerm.function;

import android.content.ComponentName;
import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GetCurrentAppAndActivity extends FunctionBase<InputGetCurrentAppAndActivity, OutputGetCurrentAppAndActivity> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.get_current_activity;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGetCurrentAppAndActivity> getInputClass() {
        return InputGetCurrentAppAndActivity.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGetCurrentAppAndActivity> getOutputClass() {
        return OutputGetCurrentAppAndActivity.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public String[] getPermissions(Context context, InputGetCurrentAppAndActivity inputGetCurrentAppAndActivity) {
        k.b(context, "context");
        k.b(inputGetCurrentAppAndActivity, "input");
        return bt.f9101c.M();
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputGetCurrentAppAndActivity doIt(Context context, InputGetCurrentAppAndActivity inputGetCurrentAppAndActivity) {
        k.b(context, "context");
        k.b(inputGetCurrentAppAndActivity, "input");
        ComponentName componentNameAi = ah.ai(context);
        if (componentNameAi == null) {
            throw new ExceptionFunctions("Couldn't get current app info");
        }
        return new OutputGetCurrentAppAndActivity(componentNameAi);
    }
}
