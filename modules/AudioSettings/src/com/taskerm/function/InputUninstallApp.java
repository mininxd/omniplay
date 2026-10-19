package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.util.AppBasic;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputUninstallApp {
    private final AppBasic app;
    private final boolean useRoot;

    @FunctionInput(explanationResId = R.string.pl_package_app_name, index = 0)
    public static /* synthetic */ void app$annotations() {
    }

    @FunctionInput(explanationResId = R.string.pl_use_root, index = 1)
    public static /* synthetic */ void useRoot$annotations() {
    }

    public InputUninstallApp(AppBasic appBasic, boolean z) {
        k.b(appBasic, "app");
        this.app = appBasic;
        this.useRoot = z;
    }

    public final AppBasic getApp() {
        return this.app;
    }

    public final boolean getUseRoot() {
        return this.useRoot;
    }
}
