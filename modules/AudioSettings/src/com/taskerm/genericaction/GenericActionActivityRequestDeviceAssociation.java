package com.joaomgcd.taskerm.genericaction;

import android.annotation.TargetApi;
import android.app.Activity;
import android.companion.AssociationRequest;
import android.companion.CompanionDeviceManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import b.a.m;
import b.a.o;
import c.f.b.k;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(26)
public final class GenericActionActivityRequestDeviceAssociation extends GenericActionActivityIntentSenderForResult {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityRequestDeviceAssociation();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestDeviceAssociation[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeInt(1);
    }

    public GenericActionActivityRequestDeviceAssociation() {
        super("GenericActionActivityRequestDeviceAssociation");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityIntentSenderForResult, com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult, com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        int i;
        k.b(activityGenericAction, "context");
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            l<cl> lVarA = l.a(cn.a((Object) null));
            k.a((Object) lVarA, "Single.just(SimpleResultSuccess(null))");
            return lVarA;
        }
        ActivityGenericAction activityGenericAction2 = activityGenericAction;
        ActivityGenericAction activityGenericAction3 = activityGenericAction;
        if (!((x) y.a((Activity) activityGenericAction2, R.string.dc_associate_device_title, aj.a(R.string.dc_associate_device_text, activityGenericAction3, ah.a((Context) activityGenericAction3)), 0, false, (String) null, 56, (Object) null).b()).d()) {
            l<cl> lVarA2 = l.a(cn.a("Use cancelled device association"));
            k.a((Object) lVarA2, "Single.just(SimpleResult…led device association\"))");
            return lVarA2;
        }
        ac acVarA = ac.a.a(ac.f6217a, activityGenericAction3, R.string.dc_associate_device_title, false, 4, null);
        cl clVarB = super.execute$Tasker_5_15_14__marketNoTrialRelease(activityGenericAction).b();
        ac.a(acVarA, null, 1, null);
        if (clVarB.b()) {
            ah.l(activityGenericAction3, ah.J(activityGenericAction3));
            i = R.string.dc_associated_device;
        } else {
            i = R.string.dc_not_associated_device;
        }
        y.d(activityGenericAction2, R.string.dc_associate_device_title, i).b();
        l<cl> lVarA3 = l.a(clVarB);
        k.a((Object) lVarA3, "Single.just(result)");
        return lVarA3;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityIntentSenderForResult
    public l<IntentSender> getIntentSenderToStartForResult(Activity activity) {
        k.b(activity, "context");
        CompanionDeviceManager companionDeviceManagerH = ah.H(activity);
        if (companionDeviceManagerH == null) {
            l<IntentSender> lVarB = l.b(new RuntimeException("Could not get Companion Device Manager"));
            k.a((Object) lVarB, "Single.error(RuntimeExce…mpanion Device Manager\"))");
            return lVarB;
        }
        l<IntentSender> lVarB2 = l.a((o) new b(companionDeviceManagerH)).b(20L, TimeUnit.SECONDS);
        k.a((Object) lVarB2, "Single.create<IntentSend…out(20, TimeUnit.SECONDS)");
        return lVarB2;
    }

    static final class b<T> implements o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CompanionDeviceManager f6932a;

        b(CompanionDeviceManager companionDeviceManager) {
            this.f6932a = companionDeviceManager;
        }

        @Override // b.a.o
        public final void a(final m<IntentSender> mVar) {
            k.b(mVar, "emitter");
            this.f6932a.associate(new AssociationRequest.Builder().setSingleDevice(false).build(), new CompanionDeviceManager.Callback() { // from class: com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDeviceAssociation.b.1
                @Override // android.companion.CompanionDeviceManager.Callback
                public void onFailure(CharSequence charSequence) {
                    String string;
                    m mVar2 = mVar;
                    if (charSequence == null || (string = charSequence.toString()) == null) {
                        string = "Error associating device";
                    }
                    mVar2.a((Throwable) new RuntimeException(string));
                }

                @Override // android.companion.CompanionDeviceManager.Callback
                public void onDeviceFound(IntentSender intentSender) {
                    if (intentSender == null) {
                        mVar.a((Throwable) new RuntimeException("IntentSender for association is null"));
                    } else {
                        mVar.a(intentSender);
                    }
                }
            }, (Handler) null);
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        Bundle extras;
        k.b(activity, "activity");
        if (intent == null || (extras = intent.getExtras()) == null) {
            return new cm("No result from association");
        }
        Object obj = extras.get("android.companion.extra.DEVICE");
        if (obj == null) {
            return new cm("No device associated");
        }
        return cn.a(obj);
    }
}
