package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
final class GenericActionActivityEnableLocation extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityEnableLocation();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityEnableLocation[i];
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

    public GenericActionActivityEnableLocation() {
        super("GenericActionActivityEnableLocation");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        c.f.b.k.b(activity, "context");
        b.a.l<Intent> lVarA = b.a.l.a(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        c.f.b.k.a((Object) lVarA, "Single.just(Intent(Setti…OCATION_SOURCE_SETTINGS))");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.util.ah.af(activity) ? new co() : cn.a("Location wasn't enabled");
    }
}
