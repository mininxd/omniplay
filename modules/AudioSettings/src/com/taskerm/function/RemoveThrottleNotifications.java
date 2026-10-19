package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import c.s;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class RemoveThrottleNotifications extends IFunctionNoOutput<InputRemoveThrottleNotifications> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.throttle_notification_app_remove;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public /* bridge */ /* synthetic */ s doIt(Context context, Object obj) {
        doIt(context, (InputRemoveThrottleNotifications) obj);
        return s.f2131a;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputRemoveThrottleNotifications> getInputClass() {
        return InputRemoveThrottleNotifications.class;
    }

    public void doIt(Context context, InputRemoveThrottleNotifications inputRemoveThrottleNotifications) {
        k.b(context, "context");
        k.b(inputRemoveThrottleNotifications, "input");
        ThrottledNotifications throttledNotifications = ThrottledNotificationsKt.getThrottledNotifications(context);
        throttledNotifications.remove((Object) inputRemoveThrottleNotifications.getApp().getPackageName());
        ThrottledNotificationsKt.setThrottledNotifications(context, throttledNotifications);
    }
}
