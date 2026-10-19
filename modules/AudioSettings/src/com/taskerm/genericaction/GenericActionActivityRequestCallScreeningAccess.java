package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.app.role.RoleManager;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityRequestCallScreeningAccess extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityRequestCallScreeningAccess();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestCallScreeningAccess[i];
        }
    }

    static final class b extends l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6930a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "need call screening access";
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

    public GenericActionActivityRequestCallScreeningAccess() {
        super("GenericActionActivityRequestCallScreeningAccess");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        RoleManager roleManagerN = ah.N(activity);
        if (roleManagerN == null) {
            b.a.l<Intent> lVarB = b.a.l.b(new RuntimeException("Couldn't get role manager"));
            k.a((Object) lVarB, "Single.error(java.lang.R…ldn't get role manager\"))");
            return lVarB;
        }
        b.a.l<Intent> lVarA = b.a.l.a(roleManagerN.createRequestRoleIntent("android.app.role.CALL_SCREENING"));
        k.a((Object) lVarA, "Single.just(roleManager.…ger.ROLE_CALL_SCREENING))");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        return getSimpleResultErrorIf(!ah.O(activity), b.f6930a);
    }
}
