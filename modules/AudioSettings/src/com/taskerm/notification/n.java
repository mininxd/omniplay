package com.joaomgcd.taskerm.notification;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionServiceSetBooleanPrefState;
import cyanogenmod.alarmclock.ClockContract;

/* JADX INFO: loaded from: classes.dex */
public class n extends l {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(Context context, String str, String str2, boolean z, String str3) {
        super(context, new GenericActionServiceSetBooleanPrefState(str2, z, str3), str, (ap) null, 8, (c.f.b.g) null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, ClockContract.AlarmSettingColumns.LABEL);
        c.f.b.k.b(str2, "prefKey");
        c.f.b.k.b(str3, "notificationIdToCancel");
    }
}
