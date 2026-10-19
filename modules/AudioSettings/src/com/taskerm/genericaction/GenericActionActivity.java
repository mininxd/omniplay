package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import java.io.Serializable;
import java.util.UUID;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionActivity extends GenericAction<ActivityGenericAction> {
    private final Integer[] windowFlags;

    public boolean getFinishOnStop() {
        return false;
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
    }

    public void onConfigurationChanged(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
    }

    public void onCreate(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
    }

    public void onPause(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
    }

    public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        k.b(activity, "activity");
        k.b(strArr, "permissions");
        k.b(iArr, "grantResults");
    }

    public void onResume(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
    }

    public void onStop(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public GenericActionActivity() {
        String string = UUID.randomUUID().toString();
        k.a((Object) string, "UUID.randomUUID().toString()");
        super(string);
        this.windowFlags = new Integer[0];
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivity(String str) {
        super(str);
        k.b(str, be.EXTRA_ID);
        this.windowFlags = new Integer[0];
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public Class<? extends ActivityGenericAction> getRunnerClass() {
        return ActivityGenericAction.class;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public void startRunning(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "startIntent");
        intent.setFlags(402653184);
        context.startActivity(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public void modifyIntent(Intent intent) {
        k.b(intent, "intent");
        intent.addFlags(268435456);
        intent.putExtra(f.a(), (Serializable) getWindowFlags());
    }

    public Integer[] getWindowFlags() {
        return this.windowFlags;
    }
}
