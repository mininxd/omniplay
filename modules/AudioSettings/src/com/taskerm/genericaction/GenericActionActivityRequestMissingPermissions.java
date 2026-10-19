package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import java.util.Arrays;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityRequestMissingPermissions extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();
    private final String[] permissions;
    private final String promptIfNotGranted;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityRequestMissingPermissions(parcel.createStringArray(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestMissingPermissions[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeStringArray(this.permissions);
        parcel.writeString(this.promptIfNotGranted);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityRequestMissingPermissions(String[] strArr, String str) {
        super("GenericActionActivityRequestMissingPermissions");
        k.b(strArr, "permissions");
        this.permissions = strArr;
        this.promptIfNotGranted = str;
    }

    public /* synthetic */ GenericActionActivityRequestMissingPermissions(String[] strArr, String str, int i, c.f.b.g gVar) {
        this(strArr, (i & 2) != 0 ? (String) null : str);
    }

    public final String[] getPermissions() {
        return this.permissions;
    }

    public final String getPromptIfNotGranted() {
        return this.promptIfNotGranted;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        String[] strArr = this.permissions;
        bt btVar = new bt(activityGenericAction, 0, (String[]) Arrays.copyOf(strArr, strArr.length), 2, (c.f.b.g) null);
        String str = this.promptIfNotGranted;
        if (!(str == null || str.length() == 0) && !btVar.g()) {
            y.c(activityGenericAction, R.string.dt_missing_permissions, this.promptIfNotGranted).b();
        }
        return btVar.b((Activity) activityGenericAction);
    }
}
