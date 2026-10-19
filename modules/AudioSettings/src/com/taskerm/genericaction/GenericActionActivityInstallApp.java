package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityInstallApp extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final String packageName;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityInstallApp(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityInstallApp[i];
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
    public GenericActionActivityInstallApp(String str) {
        super("GenericActionActivityInstallApp");
        k.b(str, "packageName");
        this.packageName = str;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        l<Intent> lVarA = l.a(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.packageName)));
        k.a((Object) lVarA, "Single.just(Intent(Inten…L_PREFIX}$packageName\")))");
        return lVarA;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public cq<? extends Object, ad> checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        if (ah.a(activity, this.packageName)) {
            return cn.a((Object) null);
        }
        return cn.a(this.packageName + " not installed");
    }
}
