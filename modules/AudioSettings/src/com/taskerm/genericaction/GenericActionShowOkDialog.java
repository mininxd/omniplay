package com.joaomgcd.taskerm.genericaction;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionShowOkDialog extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();
    private final String text;
    private final String title;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionShowOkDialog(parcel.readString(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionShowOkDialog[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.title);
        parcel.writeString(this.text);
    }

    public GenericActionShowOkDialog(String str, String str2) {
        k.b(str, "title");
        k.b(str2, "text");
        this.title = str;
        this.text = str2;
    }

    public final String getText() {
        return this.text;
    }

    public final String getTitle() {
        return this.title;
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6951a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(x xVar) {
            k.b(xVar, "it");
            return new co();
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        l<cl> lVarC = y.a(activityGenericAction, this.title, this.text, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).c(b.f6951a);
        k.a((Object) lVarC, "dialogOk(context, title,…{ SimpleResultSuccess() }");
        return lVarC;
    }
}
