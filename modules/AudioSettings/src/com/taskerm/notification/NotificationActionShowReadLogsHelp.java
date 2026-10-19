package com.joaomgcd.taskerm.notification;

import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionActivity;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class NotificationActionShowReadLogsHelp extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new NotificationActionShowReadLogsHelp();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new NotificationActionShowReadLogsHelp[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeInt(1);
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8027a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public b.a.l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        c.f.b.k.b(activityGenericAction, "context");
        b.a.l lVarC = com.joaomgcd.taskerm.dialog.y.i(activityGenericAction).c(b.f8027a);
        c.f.b.k.a((Object) lVarC, "dialogReadLogsPermission…{ SimpleResultSuccess() }");
        return lVarC;
    }
}
