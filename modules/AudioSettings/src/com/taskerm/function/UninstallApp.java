package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.genericaction.GenericActionUninstallApp;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.az;

/* JADX INFO: loaded from: classes.dex */
public final class UninstallApp extends FunctionBase<InputUninstallApp, OutputUninstallApp> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.uninstall_app;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputUninstallApp> getInputClass() {
        return InputUninstallApp.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputUninstallApp> getOutputClass() {
        return OutputUninstallApp.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputUninstallApp doIt(Context context, InputUninstallApp inputUninstallApp) {
        k.b(context, "context");
        k.b(inputUninstallApp, "input");
        AppBasic app = inputUninstallApp.getApp();
        String packageName = app.getPackageName();
        if (!app.isInstalled()) {
            throw new ExceptionFunctions("Couldn't find " + packageName);
        }
        String name = app.getName();
        if (!inputUninstallApp.getUseRoot()) {
            if (!new GenericActionUninstallApp(packageName).run(context).b().b()) {
                throw new ExceptionFunctions("Couldn't uninstall " + name);
            }
        } else {
            if (!az.a(context, true).b().booleanValue()) {
                throw new ExceptionFunctions("Device is not rooted");
            }
            if (!((cf) ck.a.a(ck.f9218a, "pm uninstall " + packageName, 0L, 2, null).b()).a()) {
                throw new ExceptionFunctions("Couldn't uninstall " + name + " with root");
            }
        }
        return new OutputUninstallApp();
    }
}
