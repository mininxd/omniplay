package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;
import net.dinglisch.android.taskerm.HTMLView;

/* JADX INFO: loaded from: classes.dex */
public final class ActionDialogHTML extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final String docName;
    private final HTMLView.c style;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new ActionDialogHTML(parcel.readString(), (HTMLView.c) Enum.valueOf(HTMLView.c.class, parcel.readString()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new ActionDialogHTML[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.docName);
        parcel.writeString(this.style.name());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionDialogHTML(String str, HTMLView.c cVar) {
        super("ActionDialogHTML");
        c.f.b.k.b(str, "docName");
        c.f.b.k.b(cVar, "style");
        this.docName = str;
        this.style = cVar;
    }

    public final String getDocName() {
        return this.docName;
    }

    public final HTMLView.c getStyle() {
        return this.style;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        c.f.b.k.b(activity, "context");
        return b.a.l.a(HTMLView.a(activity, this.docName, this.style));
    }
}
