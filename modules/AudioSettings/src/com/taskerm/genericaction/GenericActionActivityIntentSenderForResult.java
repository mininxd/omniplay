package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionActivityIntentSenderForResult extends GenericActionActivityForResult {
    public abstract l<IntentSender> getIntentSenderToStartForResult(Activity activity);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityIntentSenderForResult(String str) {
        super(str);
        k.b(str, be.EXTRA_ID);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult, com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        return super.execute$Tasker_5_15_14__marketNoTrialRelease(activityGenericAction);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        l<Intent> lVarA = l.a(new Intent());
        k.a((Object) lVarA, "Single.just(Intent())");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected void startForResult(Activity activity) throws IntentSender.SendIntentException {
        k.b(activity, "context");
        activity.startIntentSenderForResult(getIntentSenderToStartForResult(activity).b(), 12, getIntentToStartForResult(activity).b(), 0, 0, 0);
    }
}
