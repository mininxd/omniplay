package com.joaomgcd.taskerm.genericaction;

import android.app.Service;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.x;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionServiceClearBridgeItem extends GenericActionService {
    public static final Parcelable.Creator CREATOR = new a();
    private final String key;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionServiceClearBridgeItem(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionServiceClearBridgeItem[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.key);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionServiceClearBridgeItem(String str) {
        super("GenericActionServiceClearBridgeItem");
        k.b(str, "key");
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(Service service) {
        k.b(service, "context");
        x.a(this.key);
        l<cl> lVarA = l.a(new co());
        k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }
}
