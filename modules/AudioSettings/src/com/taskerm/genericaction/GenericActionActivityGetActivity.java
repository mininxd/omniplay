package com.joaomgcd.taskerm.genericaction;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityGetActivity extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();
    private final String instanceId;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityGetActivity(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityGetActivity[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.instanceId);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityGetActivity(String str) {
        super("GenericActionActivityGetActivity:" + str);
        k.b(str, "instanceId");
        this.instanceId = str;
    }

    public final String getInstanceId() {
        return this.instanceId;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        l<cl> lVarA = l.a(new cq(true, activityGenericAction, null));
        k.a((Object) lVarA, "Single.just(SimpleResult…oad(true, context, null))");
        return lVarA;
    }
}
