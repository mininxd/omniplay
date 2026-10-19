package com.joaomgcd.taskerm.function;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.os.Process;
import c.a.j;
import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.util.ActivityAddShortcut;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class LaunchAssistantRoutine extends FunctionBase<InputLaunchAssistantRoutine, OutputLaunchAssistantRoutine> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.launch_assistant_routine;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputLaunchAssistantRoutine> getInputClass() {
        return InputLaunchAssistantRoutine.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputLaunchAssistantRoutine> getOutputClass() {
        return OutputLaunchAssistantRoutine.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputLaunchAssistantRoutine doIt(Context context, InputLaunchAssistantRoutine inputLaunchAssistantRoutine) {
        k.b(context, "context");
        k.b(inputLaunchAssistantRoutine, "input");
        ComponentName componentNameX = ah.X(context);
        if (componentNameX == null) {
            componentNameX = (ComponentName) j.f((List) ah.au(context));
        }
        if (componentNameX == null) {
            throw new ExceptionFunctions("No launcher is currently set");
        }
        try {
            ah.a(context, ActivityAddShortcut.class, true).b();
            cl clVarB = ah.b(context, new ComponentName(context.getPackageName(), ActivityAddShortcut.class.getName()));
            if (!clVarB.b()) {
                throw new ExceptionFunctions(clVarB.a());
            }
            Object systemService = context.getSystemService("launcherapps");
            if (systemService == null) {
                throw new q("null cannot be cast to non-null type android.content.pm.LauncherApps");
            }
            LauncherApps launcherApps = (LauncherApps) systemService;
            StringBuilder sb = new StringBuilder();
            sb.append("gsa/");
            String name = inputLaunchAssistantRoutine.getName();
            if (name == null) {
                throw new ExceptionFunctions("No routine name provided");
            }
            sb.append(name);
            launcherApps.startShortcut("com.google.android.googlequicksearchbox", sb.toString(), null, null, Process.myUserHandle());
            try {
                cl clVarB2 = ah.b(context, componentNameX);
                if (!clVarB2.b()) {
                    throw new ExceptionFunctions(clVarB2.a());
                }
                ah.a(context, ActivityAddShortcut.class, false).b();
                return new OutputLaunchAssistantRoutine();
            } catch (Throwable th) {
                ah.a(context, ActivityAddShortcut.class, false).b();
                throw th;
            }
        } catch (Throwable th2) {
            try {
                cl clVarB3 = ah.b(context, componentNameX);
                if (!clVarB3.b()) {
                    throw new ExceptionFunctions(clVarB3.a());
                }
                ah.a(context, ActivityAddShortcut.class, false).b();
                throw th2;
            } catch (Throwable th3) {
                ah.a(context, ActivityAddShortcut.class, false).b();
                throw th3;
            }
        }
    }
}
