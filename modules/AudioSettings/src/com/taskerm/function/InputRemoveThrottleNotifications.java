package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.util.AppBasic;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputRemoveThrottleNotifications {
    private final AppBasic app;

    @FunctionInput(explanationResId = R.string.throttle_notification_app_remove, index = 0)
    public static /* synthetic */ void app$annotations() {
    }

    public InputRemoveThrottleNotifications(AppBasic appBasic) {
        k.b(appBasic, "app");
        this.app = appBasic;
    }

    public final AppBasic getApp() {
        return this.app;
    }
}
