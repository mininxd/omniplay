package com.joaomgcd.taskerm.action.input;

import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionDialog;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionDialogInput extends GenericActionDialog {
    public static final Parcelable.Creator CREATOR = new a();
    private final String defaultInput;
    private final Integer inputType;
    private final boolean preSelectText;
    private final String text;
    private final long timeoutSeconds;
    private final String title;
    private final boolean useHtml;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionDialogInput(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionDialogInput[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iIntValue;
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.title);
        parcel.writeString(this.text);
        parcel.writeString(this.defaultInput);
        Integer num = this.inputType;
        if (num != null) {
            parcel.writeInt(1);
            iIntValue = num.intValue();
        } else {
            iIntValue = 0;
        }
        parcel.writeInt(iIntValue);
        parcel.writeInt(this.useHtml ? 1 : 0);
        parcel.writeInt(this.preSelectText ? 1 : 0);
        parcel.writeLong(this.timeoutSeconds);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionDialogInput(String str, String str2, String str3, Integer num, boolean z, boolean z2, long j) {
        super("ActionDialogInput");
        c.f.b.k.b(str, "title");
        this.title = str;
        this.text = str2;
        this.defaultInput = str3;
        this.inputType = num;
        this.useHtml = z;
        this.preSelectText = z2;
        this.timeoutSeconds = j;
    }

    public /* synthetic */ GenericActionDialogInput(String str, String str2, String str3, Integer num, boolean z, boolean z2, long j, int i, c.f.b.g gVar) {
        this(str, str2, str3, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? false : z, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? 30L : j);
    }

    public final String getDefaultInput() {
        return this.defaultInput;
    }

    public final Integer getInputType() {
        return this.inputType;
    }

    public final boolean getPreSelectText() {
        return this.preSelectText;
    }

    public final String getText() {
        return this.text;
    }

    public final long getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean getUseHtml() {
        return this.useHtml;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public b.a.l<cl> showDialog(ActivityGenericAction activityGenericAction, int i) {
        c.f.b.k.b(activityGenericAction, "activity");
        Integer num = this.inputType;
        int iIntValue = num != null ? num.intValue() : 0;
        int i2 = this.preSelectText ? 16384 : 0;
        if (this.useHtml) {
            i2 |= 4;
        }
        com.joaomgcd.taskerm.dialog.x xVar = (com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(activityGenericAction, this.title, this.defaultInput, 0, true, this.text, Integer.valueOf(i2), iIntValue, Integer.valueOf(i), null, 520, null).b(this.timeoutSeconds, TimeUnit.SECONDS).b();
        if (!xVar.d()) {
            b.a.l<cl> lVarA = b.a.l.a(cn.a("Cancelled"));
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultErrorString(\"Cancelled\"))");
            return lVarA;
        }
        String strJ = xVar.j();
        if (strJ == null) {
            b.a.l<cl> lVarA2 = b.a.l.a(cn.a("No Input"));
            c.f.b.k.a((Object) lVarA2, "Single.just(SimpleResultErrorString(\"No Input\"))");
            return lVarA2;
        }
        b.a.l<cl> lVarA3 = b.a.l.a(cn.a(strJ));
        c.f.b.k.a((Object) lVarA3, "Single.just(SimpleResultSuccess(enteredText))");
        return lVarA3;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog, com.joaomgcd.taskerm.genericaction.GenericActionActivity
    public void onCreate(ActivityGenericAction activityGenericAction) {
        c.f.b.k.b(activityGenericAction, "activityGenericAction");
        super.onCreate(activityGenericAction);
        activityGenericAction.getWindow().setSoftInputMode(32);
    }
}
