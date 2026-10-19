package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.bp;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityRequestSystemPermissions extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityRequestSystemPermissions();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestSystemPermissions[i];
        }
    }

    static final class b extends l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6941a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "need write settings permissions";
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

    public GenericActionActivityRequestSystemPermissions() {
        super("GenericActionActivityRequestSystemPermissions");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        b.a.l<Intent> lVarA = b.a.l.a(bp.c(activity));
        k.a((Object) lVarA, "Single.just(ManifestPerm…anagementIntent(context))");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        return getSimpleResultErrorIf(!bp.a(activity), b.f6941a);
    }
}
