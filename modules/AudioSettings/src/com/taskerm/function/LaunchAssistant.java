package com.joaomgcd.taskerm.function;

import android.content.Context;
import android.os.Bundle;
import c.f.b.k;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ah;
import java.lang.reflect.InvocationTargetException;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class LaunchAssistant extends FunctionBase<InputLaunchAssistant, OutputLaunchAssistant> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.launch_assistant;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputLaunchAssistant> getInputClass() {
        return InputLaunchAssistant.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputLaunchAssistant> getOutputClass() {
        return OutputLaunchAssistant.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputLaunchAssistant doIt(Context context, InputLaunchAssistant inputLaunchAssistant) throws IllegalAccessException, InvocationTargetException {
        k.b(context, "context");
        k.b(inputLaunchAssistant, "input");
        Cdo.b(ah.ae(context), "launchAssist", "launchAssist", true).a(new Bundle());
        return new OutputLaunchAssistant();
    }
}
