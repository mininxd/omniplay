package com.joaomgcd.taskerm.function;

import android.content.ComponentName;
import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputGetCurrentAppAndActivity {
    private final String activity;
    private final String packageName;

    public OutputGetCurrentAppAndActivity(String str, String str2) {
        k.b(str, "packageName");
        k.b(str2, "activity");
        this.packageName = str;
        this.activity = str2;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_package, labelResId = R.string.pl_package, name = "package")
    public final String getPackageName() {
        return this.packageName;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_activity, labelResId = R.string.word_activity, name = "activity")
    public final String getActivity() {
        return this.activity;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OutputGetCurrentAppAndActivity(ComponentName componentName) {
        k.b(componentName, "componentName");
        String packageName = componentName.getPackageName();
        k.a((Object) packageName, "componentName.packageName");
        String className = componentName.getClassName();
        k.a((Object) className, "componentName.className");
        this(packageName, className);
    }
}
