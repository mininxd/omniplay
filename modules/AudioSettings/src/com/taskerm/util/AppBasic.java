package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.navigationbar.IconProvider;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "app")
public class AppBasic {
    private final ApplicationInfo appInfo;
    private final Context context;
    private final String icon;
    private final String name;
    private final PackageInfo packageInfo;
    private final String packageName;

    public AppBasic(Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "packageName");
        this.context = context;
        this.packageName = str;
        this.packageInfo = ah.b(this.context, this.packageName, 4609);
        this.appInfo = ah.a(this.context, this.packageName, 8704);
        this.name = ah.a(this.context, this.packageName, (String) null, 2, (Object) null);
        this.icon = IconProvider.f7827a.c(this.packageName);
    }

    public final Context getContext() {
        return this.context;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_package, labelResId = R.string.pl_package, name = "package")
    public final String getPackageName() {
        return this.packageName;
    }

    protected final PackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    protected final ApplicationInfo getAppInfo() {
        return this.appInfo;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_name, labelResId = R.string.pl_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getName() {
        return this.name;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_icon, labelResId = R.string.pl_icon, name = "icon")
    public final String getIcon() {
        return this.icon;
    }

    public final boolean isInstalled() {
        return this.packageInfo != null;
    }
}
