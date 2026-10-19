package com.joaomgcd.taskerm.function;

import android.annotation.TargetApi;
import android.content.Context;
import c.f.b.k;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GetThermalStatus extends FunctionBase<InputGetThermalStatus, OutputGetThermalStatus> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public int getMinApi() {
        return 29;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.get_thermal_status;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGetThermalStatus> getInputClass() {
        return InputGetThermalStatus.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGetThermalStatus> getOutputClass() {
        return OutputGetThermalStatus.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    @TargetApi(29)
    public OutputGetThermalStatus doIt(Context context, InputGetThermalStatus inputGetThermalStatus) {
        k.b(context, "context");
        k.b(inputGetThermalStatus, "input");
        ThermalStatus currentThermalStatus = GetThermalStatusKt.getCurrentThermalStatus(context);
        if (currentThermalStatus == null) {
            throw new ExceptionFunctions("Couldn't get power manager");
        }
        return new OutputGetThermalStatus(context, currentThermalStatus);
    }
}
