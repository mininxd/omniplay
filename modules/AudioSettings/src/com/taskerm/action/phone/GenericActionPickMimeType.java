package com.joaomgcd.taskerm.action.phone;

import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionDialog;
import com.joaomgcd.taskerm.util.ac;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionPickMimeType extends GenericActionDialog {
    public static final Parcelable.Creator CREATOR = new a();
    private final String contactName;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionPickMimeType(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionPickMimeType[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.contactName);
    }

    public GenericActionPickMimeType(String str) {
        super("GenericActionPickContact");
        this.contactName = str;
    }

    public final String getContactName() {
        return this.contactName;
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5668a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<String, ac> apply(String str) {
            c.f.b.k.b(str, "it");
            return new cq<>(true, str, null, 4, null);
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public b.a.l<cl> showDialog(ActivityGenericAction activityGenericAction, int i) {
        c.f.b.k.b(activityGenericAction, "activity");
        b.a.l<cl> lVarC = c.b(activityGenericAction, this.contactName).c(b.f5668a);
        c.f.b.k.a((Object) lVarC, "pickMimeType(activity, c…tion>(true, it)\n        }");
        return lVarC;
    }
}
