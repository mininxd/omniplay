package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionUninstallApp extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final String packageName;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionUninstallApp(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionUninstallApp[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.packageName);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionUninstallApp(String str) {
        super("GenericActionUninstallApp");
        k.b(str, "packageName");
        this.packageName = str;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        l<Intent> lVarA = l.a(new Intent("android.intent.action.UNINSTALL_PACKAGE", ak.C("package:" + this.packageName)));
        k.a((Object) lVarA, "Single.just(Intent(Inten…age:$packageName\".asUri))");
        return lVarA;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public co checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        return new co();
    }
}
