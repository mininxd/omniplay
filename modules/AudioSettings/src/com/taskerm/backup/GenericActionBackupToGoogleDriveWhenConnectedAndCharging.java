package com.joaomgcd.taskerm.backup;

import android.os.Parcel;
import android.os.Parcelable;
import c.f.b.k;
import com.joaomgcd.taskerm.genericaction.h;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionBackupToGoogleDriveWhenConnectedAndCharging extends GenericActionBackupToGoogleDrive {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionBackupToGoogleDriveWhenConnectedAndCharging();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionBackupToGoogleDriveWhenConnectedAndCharging[i];
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

    public GenericActionBackupToGoogleDriveWhenConnectedAndCharging() {
        super(new h(false, 1, null));
    }
}
