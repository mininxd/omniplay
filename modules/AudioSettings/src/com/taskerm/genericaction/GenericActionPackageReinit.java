package com.joaomgcd.taskerm.genericaction;

import android.app.Service;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.io.IOException;
import net.dinglisch.android.taskerm.AppSelect;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.cs;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionPackageReinit extends GenericActionService {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionPackageReinit();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionPackageReinit[i];
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

    public GenericActionPackageReinit() {
        super("GenericActionPackageReinit");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(Service service) throws IOException {
        k.b(service, "context");
        bn.b(getId$Tasker_5_15_14__marketNoTrialRelease(), "reinit static data");
        AppSelect.a();
        Service service2 = service;
        az.d(service2);
        cs.a(service2);
        l<cl> lVarA = l.a(cn.a((Object) null));
        k.a((Object) lVarA, "Single.just(SimpleResultSuccess(null))");
        return lVarA;
    }
}
