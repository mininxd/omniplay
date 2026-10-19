package com.joaomgcd.taskerm.notification;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionEditTask;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class i extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Context context, String str, String str2, String str3, Integer num) {
        super(context, new GenericActionEditTask(str, str2, str3, num), com.joaomgcd.taskerm.util.aj.a(R.string.an_edit_task, context, new Object[0]), (ap) null, 8, (c.f.b.g) null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "actionNameForLogs");
    }
}
