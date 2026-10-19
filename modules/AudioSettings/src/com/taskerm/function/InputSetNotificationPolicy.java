package com.joaomgcd.taskerm.function;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.notification.au;
import com.joaomgcd.taskerm.util.f;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputSetNotificationPolicy {
    private final int callSenders;
    private final int categories;
    private final int messageSenders;
    private final int suppressedVisualEffects;

    @FunctionInput(explanationResId = R.string.flag_call_senders, index = 1)
    public static /* synthetic */ void callSenders$annotations() {
    }

    @FunctionInput(explanationResId = R.string.flag_categories, index = 0)
    public static /* synthetic */ void categories$annotations() {
    }

    @FunctionInput(explanationResId = R.string.flag_message_senders, index = 2)
    public static /* synthetic */ void messageSenders$annotations() {
    }

    @FunctionInput(explanationResId = R.string.flag_suppressed_visual_effects, index = 2)
    public static /* synthetic */ void suppressedVisualEffects$annotations() {
    }

    public InputSetNotificationPolicy(int i, int i2, int i3, int i4) {
        this.categories = i;
        this.callSenders = i2;
        this.messageSenders = i3;
        this.suppressedVisualEffects = i4;
    }

    public final int getCategories() {
        return this.categories;
    }

    public final int getCallSenders() {
        return this.callSenders;
    }

    public final int getMessageSenders() {
        return this.messageSenders;
    }

    public final int getSuppressedVisualEffects() {
        return this.suppressedVisualEffects;
    }

    @TargetApi(24)
    public final boolean set(Context context) {
        NotificationManager.Policy policy;
        k.b(context, "context");
        NotificationManager notificationManagerB = au.b(context);
        if (notificationManagerB == null || !notificationManagerB.isNotificationPolicyAccessGranted()) {
            return false;
        }
        if (f.f9323b.e()) {
            policy = new NotificationManager.Policy(this.categories, this.callSenders, this.messageSenders);
        } else {
            policy = new NotificationManager.Policy(this.categories, this.callSenders, this.messageSenders, this.suppressedVisualEffects);
        }
        notificationManagerB.setNotificationPolicy(policy);
        notificationManagerB.setInterruptionFilter(2);
        return true;
    }
}
