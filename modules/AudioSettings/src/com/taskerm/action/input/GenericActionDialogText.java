package com.joaomgcd.taskerm.action.input;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionDialog;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.dr;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionDialogText extends GenericActionDialog {
    public static final Parcelable.Creator CREATOR = new a();
    private final String image;
    private final String imageSize;
    private final String negativeLabel;
    private final String neutralLabel;
    private final String positiveLabel;
    private final String text;
    private final long timeoutSeconds;
    private final String title;
    private final boolean useHtml;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionDialogText(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readLong(), parcel.readString(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionDialogText[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.title);
        parcel.writeString(this.text);
        parcel.writeString(this.positiveLabel);
        parcel.writeString(this.negativeLabel);
        parcel.writeString(this.neutralLabel);
        parcel.writeInt(this.useHtml ? 1 : 0);
        parcel.writeLong(this.timeoutSeconds);
        parcel.writeString(this.image);
        parcel.writeString(this.imageSize);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getText() {
        return this.text;
    }

    public final String getPositiveLabel() {
        return this.positiveLabel;
    }

    public final String getNegativeLabel() {
        return this.negativeLabel;
    }

    public final String getNeutralLabel() {
        return this.neutralLabel;
    }

    public final boolean getUseHtml() {
        return this.useHtml;
    }

    public /* synthetic */ GenericActionDialogText(String str, String str2, String str3, String str4, String str5, boolean z, long j, String str6, String str7, int i, c.f.b.g gVar) {
        this(str, str2, str3, str4, str5, (i & 32) != 0 ? false : z, (i & 64) != 0 ? 30L : j, str6, str7);
    }

    public final long getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public final String getImage() {
        return this.image;
    }

    public final String getImageSize() {
        return this.imageSize;
    }

    public GenericActionDialogText(String str, String str2, String str3, String str4, String str5, boolean z, long j, String str6, String str7) {
        super("ActionDialogText");
        this.title = str;
        this.text = str2;
        this.positiveLabel = str3;
        this.negativeLabel = str4;
        this.neutralLabel = str5;
        this.useHtml = z;
        this.timeoutSeconds = j;
        this.image = str6;
        this.imageSize = str7;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public b.a.l<cl> showDialog(ActivityGenericAction activityGenericAction, int i) {
        com.joaomgcd.taskerm.dialog.x xVarA;
        Integer numC;
        String str;
        c.f.b.k.b(activityGenericAction, "activity");
        int i2 = this.useHtml ? 4 : 0;
        com.joaomgcd.taskerm.util.j[] jVarArr = new com.joaomgcd.taskerm.util.j[5];
        String str2 = this.title;
        Integer numValueOf = null;
        jVarArr[0] = str2 != null ? com.joaomgcd.taskerm.util.ak.i(str2) : null;
        String str3 = this.text;
        jVarArr[1] = str3 != null ? com.joaomgcd.taskerm.util.ak.i(str3) : null;
        String str4 = this.positiveLabel;
        jVarArr[2] = str4 != null ? com.joaomgcd.taskerm.util.ak.i(str4) : null;
        String str5 = this.negativeLabel;
        jVarArr[3] = str5 != null ? com.joaomgcd.taskerm.util.ak.i(str5) : null;
        String str6 = this.neutralLabel;
        jVarArr[4] = str6 != null ? com.joaomgcd.taskerm.util.ak.i(str6) : null;
        com.joaomgcd.taskerm.util.j jVar = jVarArr[0];
        com.joaomgcd.taskerm.util.j jVar2 = jVarArr[1];
        com.joaomgcd.taskerm.util.j jVar3 = jVarArr[2];
        com.joaomgcd.taskerm.util.j jVar4 = jVarArr[3];
        com.joaomgcd.taskerm.util.j jVar5 = jVarArr[4];
        if (this.image == null) {
            com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h(activityGenericAction, jVar, jVar2, jVar3, jVar4, jVar5, false, i2, null, 0, Integer.valueOf(i), null, null, 6976, null)).b(this.timeoutSeconds, TimeUnit.SECONDS).b();
            c.f.b.k.a((Object) xVarB, "dialogTextBox(args).time…it.SECONDS).blockingGet()");
            xVarA = xVarB;
        } else {
            String str7 = this.imageSize;
            if (str7 != null && (numC = c.l.n.c(str7)) != null) {
                numValueOf = Integer.valueOf(dr.a(numC.intValue(), activityGenericAction));
            }
            Integer num = numValueOf;
            xVarA = com.joaomgcd.taskerm.dialog.q.a(new com.joaomgcd.taskerm.dialog.d(activityGenericAction, new b(activityGenericAction), num, num, jVar, jVar2, jVar3, jVar4, jVar5, Boolean.valueOf(this.useHtml), Integer.valueOf(i))).b().a();
        }
        if (xVarA.f()) {
            b.a.l<cl> lVarA = b.a.l.a(cn.a("Cancelled"));
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultErrorString(\"Cancelled\"))");
            return lVarA;
        }
        if (xVarA.d()) {
            str = this.positiveLabel;
        } else {
            str = xVarA.e() ? this.negativeLabel : this.neutralLabel;
        }
        b.a.l<cl> lVarA2 = b.a.l.a(cn.a(str));
        c.f.b.k.a((Object) lVarA2, "Single.just(SimpleResultSuccess(buttonLabel))");
        return lVarA2;
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.l<Bitmap>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f5363b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ActivityGenericAction activityGenericAction) {
            super(0);
            this.f5363b = activityGenericAction;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Bitmap> invoke() {
            return com.joaomgcd.taskerm.m.b.a(GenericActionDialogText.this.getImage(), this.f5363b, (Integer) null, (Integer) null, 6, (Object) null);
        }
    }
}
