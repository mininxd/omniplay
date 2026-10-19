package com.joaomgcd.taskerm.genericaction;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionServiceSetBooleanPrefState extends GenericActionService {
    public static final Parcelable.Creator CREATOR = new a();
    private final String notificationIdToCancel;
    private final String prefKey;
    private final boolean value;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionServiceSetBooleanPrefState(parcel.readString(), parcel.readInt() != 0, parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionServiceSetBooleanPrefState[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.prefKey);
        parcel.writeInt(this.value ? 1 : 0);
        parcel.writeString(this.notificationIdToCancel);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionServiceSetBooleanPrefState(String str, boolean z, String str2) {
        super("GenericActionServiceSetBooleanPref");
        k.b(str, "prefKey");
        this.prefKey = str;
        this.value = z;
        this.notificationIdToCancel = str2;
    }

    public /* synthetic */ GenericActionServiceSetBooleanPrefState(String str, boolean z, String str2, int i, c.f.b.g gVar) {
        this(str, z, (i & 4) != 0 ? (String) null : str2);
    }

    public final String getNotificationIdToCancel() {
        return this.notificationIdToCancel;
    }

    public final String getPrefKey() {
        return this.prefKey;
    }

    public final boolean getValue() {
        return this.value;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(Service service) {
        k.b(service, "context");
        Service service2 = service;
        String str = this.prefKey;
        Boolean boolValueOf = Boolean.valueOf(this.value);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(service2);
        k.a((Object) sharedPreferencesA, "context.preferencesState");
        com.joaomgcd.taskerm.q.b.a((Context) service2, str, boolValueOf, sharedPreferencesA);
        String str2 = this.notificationIdToCancel;
        if (str2 != null) {
            at.f8046b.a(service2, str2);
        }
        l<cl> lVarA = l.a(new co());
        k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }
}
