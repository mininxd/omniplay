package com.joaomgcd.taskerm.notification;

import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.genericaction.GenericActionActivity;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public class o extends c {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(Context context, Intent intent) {
        super(context, intent, com.joaomgcd.taskerm.util.aj.a(R.string.word_settings, context, new Object[0]), null, null, null, 56, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(intent, "intent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public o(Context context, GenericActionActivity genericActionActivity) {
        this(context, genericActionActivity.getStartIntent(context));
        c.f.b.k.b(context, "context");
        c.f.b.k.b(genericActionActivity, "genericAction");
    }
}
