package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.a.j;
import c.a.z;
import c.f.b.k;
import c.q;
import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GetThrottledNotifications extends FunctionBase<InputGetThrottledNotifications, OutputGetThrottledNotifications> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.throttle_notification_app_get;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputGetThrottledNotifications> getInputClass() {
        return InputGetThrottledNotifications.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputGetThrottledNotifications> getOutputClass() {
        return OutputGetThrottledNotifications.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputGetThrottledNotifications doIt(Context context, InputGetThrottledNotifications inputGetThrottledNotifications) {
        k.b(context, "context");
        k.b(inputGetThrottledNotifications, "input");
        List<c.k> listD = z.d(ThrottledNotificationsKt.getThrottledNotifications(context));
        ArrayList arrayList = new ArrayList(j.a((Iterable) listD, 10));
        for (c.k kVar : listD) {
            arrayList.add(new ThrottledNotification((String) kVar.a(), ((Number) kVar.b()).intValue()));
        }
        Object[] array = arrayList.toArray(new ThrottledNotification[0]);
        if (array != null) {
            return new OutputGetThrottledNotifications((ThrottledNotification[]) array);
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
