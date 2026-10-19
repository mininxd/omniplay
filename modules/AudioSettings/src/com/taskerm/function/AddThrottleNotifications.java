package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import c.s;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class AddThrottleNotifications extends IFunctionNoOutput<InputAddThrottleNotifications> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.throttle_notification_app;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public /* bridge */ /* synthetic */ s doIt(Context context, Object obj) {
        doIt(context, (InputAddThrottleNotifications) obj);
        return s.f2131a;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputAddThrottleNotifications> getInputClass() {
        return InputAddThrottleNotifications.class;
    }

    public void doIt(Context context, InputAddThrottleNotifications inputAddThrottleNotifications) {
        k.b(context, "context");
        k.b(inputAddThrottleNotifications, "input");
        int seconds = inputAddThrottleNotifications.getSeconds();
        ThrottledNotifications throttledNotifications = ThrottledNotificationsKt.getThrottledNotifications(context);
        throttledNotifications.put(inputAddThrottleNotifications.getApp().getPackageName(), Integer.valueOf(seconds));
        ThrottledNotificationsKt.setThrottledNotifications(context, throttledNotifications);
    }
}
