package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.bw;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityInsertGoogleApiKey extends GenericActionActivity {
    public static final a Companion = new a(null);
    public static final Parcelable.Creator CREATOR = new b();

    public static class b implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityInsertGoogleApiKey();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityInsertGoogleApiKey[i];
        }
    }

    public static final at getNotificationInfo(Context context) {
        return Companion.a(context);
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

    public GenericActionActivityInsertGoogleApiKey() {
        super("GenericActionActivityInsertGoogleApiKey");
    }

    static final class c<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f6925a;

        c(ActivityGenericAction activityGenericAction) {
            this.f6925a = activityGenericAction;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<? extends Object, ad> apply(x xVar) {
            k.b(xVar, "it");
            if (!xVar.d()) {
                return cn.a("User cancelled");
            }
            bw.a(this.f6925a, xVar.j());
            return cn.a(xVar.j());
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        l<cl> lVarC = y.a((Activity) activityGenericAction, R.string.dc_insert_your_google_api_key_check_help, bw.c(activityGenericAction), 0, true, (String) null, 40, (Object) null).c(new c(activityGenericAction));
        k.a((Object) lVarC, "dialogOkCancel(context, …)\n            }\n        }");
        return lVarC;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final at a(Context context) {
            k.b(context, "context");
            String string = context.getString(R.string.dt_google_api_key);
            k.a((Object) string, "context.getString(R.string.dt_google_api_key)");
            com.joaomgcd.taskerm.notification.e eVar = new com.joaomgcd.taskerm.notification.e(context);
            return new at(context, string, context.getString(R.string.dc_action_needs_google_api_key_click_notification), null, null, null, false, new as(R.drawable.mw_hardware_security), null, "googleapikey", null, 0, 0L, eVar, false, false, null, null, null, null, null, false, false, 8379768, null);
        }
    }
}
