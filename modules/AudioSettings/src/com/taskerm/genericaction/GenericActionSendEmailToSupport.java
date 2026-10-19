package com.joaomgcd.taskerm.genericaction;

import android.app.Service;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionSendEmailToSupport extends GenericActionService {
    public static final Parcelable.Creator CREATOR = new a();
    private final String text;
    private final String title;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionSendEmailToSupport(parcel.readString(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionSendEmailToSupport[i];
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public GenericActionSendEmailToSupport(String str, String str2) {
        super(null, 1, 0 == true ? 1 : 0);
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

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(Service service) {
        k.b(service, "context");
        ah.a(service, this.title, this.text, (File[]) null, 4, (Object) null);
        l<cl> lVarA = l.a(new co());
        k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }
}
