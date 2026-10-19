package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.util.AppBasic;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputAddThrottleNotifications {
    private final AppBasic app;
    private final int seconds;

    @FunctionInput(explanationResId = R.string.throttle_notification_app, index = 1)
    public static /* synthetic */ void app$annotations() {
    }

    @FunctionInput(explanationResId = R.string.throttle_notification_seconds, index = 0)
    public static /* synthetic */ void seconds$annotations() {
    }

    public InputAddThrottleNotifications(int i, AppBasic appBasic) {
        k.b(appBasic, "app");
        this.seconds = i;
        this.app = appBasic;
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final AppBasic getApp() {
        return this.app;
    }
}
