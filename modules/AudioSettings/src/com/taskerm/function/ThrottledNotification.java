package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "tn")
public final class ThrottledNotification {
    private final String packageName;
    private final int seconds;

    public ThrottledNotification(String str, int i) {
        k.b(str, "packageName");
        this.packageName = str;
        this.seconds = i;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_package, labelResId = R.string.pl_package, name = "package")
    public final String getPackageName() {
        return this.packageName;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.throttle_notification_seconds, labelResId = R.string.pl_seconds, name = "seconds")
    public final int getSeconds() {
        return this.seconds;
    }
}
