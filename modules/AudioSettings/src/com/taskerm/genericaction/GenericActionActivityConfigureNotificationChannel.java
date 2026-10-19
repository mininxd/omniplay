package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.notification.al;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityConfigureNotificationChannel extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final String channelId;
    private final Boolean expectedEnabledStatus;
    private final String packageName;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            Boolean boolValueOf;
            k.b(parcel, "in");
            String string = parcel.readString();
            if (parcel.readInt() != 0) {
                boolValueOf = Boolean.valueOf(parcel.readInt() != 0);
            } else {
                boolValueOf = null;
            }
            return new GenericActionActivityConfigureNotificationChannel(string, boolValueOf, parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityConfigureNotificationChannel[i];
        }
    }

    public GenericActionActivityConfigureNotificationChannel(String str) {
        this(str, null, null, 6, null);
    }

    public GenericActionActivityConfigureNotificationChannel(String str, Boolean bool) {
        this(str, bool, null, 4, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.Parcel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [int] */
    /* JADX WARN: Type inference failed for: r3v8 */
    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ?? BooleanValue;
        k.b(parcel, "parcel");
        parcel.writeString(this.channelId);
        Boolean bool = this.expectedEnabledStatus;
        if (bool != null) {
            parcel.writeInt(1);
            BooleanValue = bool.booleanValue();
        } else {
            BooleanValue = 0;
        }
        parcel.writeInt(BooleanValue);
        parcel.writeString(this.packageName);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityConfigureNotificationChannel(String str, Boolean bool, String str2) {
        super("GenericActionActivityConfigureNotificationChannel");
        k.b(str, "channelId");
        this.channelId = str;
        this.expectedEnabledStatus = bool;
        this.packageName = str2;
    }

    public /* synthetic */ GenericActionActivityConfigureNotificationChannel(String str, Boolean bool, String str2, int i, c.f.b.g gVar) {
        this(str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (String) null : str2);
    }

    public final String getChannelId() {
        return this.channelId;
    }

    public final Boolean getExpectedEnabledStatus() {
        return this.expectedEnabledStatus;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        String packageName = this.packageName;
        if (packageName == null) {
            packageName = activity.getPackageName();
        }
        l<Intent> lVarA = l.a(al.a(packageName, this.channelId));
        k.a((Object) lVarA, "Single.just(\n           …nelId\n            )\n    )");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        Boolean bool = this.expectedEnabledStatus;
        if (bool == null) {
            return new co();
        }
        if (al.d(activity, this.channelId) == bool.booleanValue()) {
            return new co();
        }
        return cn.a("Notification Channel doesn't have required state");
    }
}
