package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Intent;
import c.f.b.k;
import c.s;
import com.joaomgcd.taskerm.genericaction.GenericActionActivity;

/* JADX INFO: loaded from: classes.dex */
public final class e<TAction extends GenericActionActivity> extends d<ActivityGenericAction, TAction> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(ActivityGenericAction activityGenericAction, Intent intent) {
        super(activityGenericAction, intent);
        k.b(activityGenericAction, "context");
    }

    public final void a(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        TAction tactionA = a();
        if (tactionA != null) {
            tactionA.onActivityResult(activity, i, i2, intent);
        }
    }

    public final void a(Activity activity, int i, String[] strArr, int[] iArr) {
        k.b(activity, "activity");
        k.b(strArr, "permissions");
        k.b(iArr, "grantResults");
        TAction tactionA = a();
        if (tactionA != null) {
            tactionA.onRequestPermissionsResult(activity, i, strArr, iArr);
        }
    }

    public final s a(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        TAction tactionA = a();
        if (tactionA == null) {
            return null;
        }
        tactionA.onCreate(activityGenericAction);
        return s.f2131a;
    }

    public final s b(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        TAction tactionA = a();
        if (tactionA == null) {
            return null;
        }
        tactionA.onPause(activityGenericAction);
        return s.f2131a;
    }

    public final s c(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        TAction tactionA = a();
        if (tactionA == null) {
            return null;
        }
        tactionA.onResume(activityGenericAction);
        return s.f2131a;
    }

    public final s d(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        TAction tactionA = a();
        if (tactionA == null) {
            return null;
        }
        tactionA.onStop(activityGenericAction);
        return s.f2131a;
    }

    public final s e(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        TAction tactionA = a();
        if (tactionA == null) {
            return null;
        }
        tactionA.onConfigurationChanged(activityGenericAction);
        return s.f2131a;
    }
}
