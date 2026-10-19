package com.joaomgcd.taskerm.action.input;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionDialog;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionPickInput extends GenericActionDialog {
    public static final Parcelable.Creator CREATOR = new a();
    private final String defaultInput;
    private final String description;
    private final boolean selectingDefaultValue;
    private final String title;
    private final com.joaomgcd.taskerm.profile.i type;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionPickInput((com.joaomgcd.taskerm.profile.i) Enum.valueOf(com.joaomgcd.taskerm.profile.i.class, parcel.readString()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionPickInput[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.type.name());
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.defaultInput);
        parcel.writeInt(this.selectingDefaultValue ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionPickInput(com.joaomgcd.taskerm.profile.i iVar, String str, String str2, String str3, boolean z) {
        super("GenericActionPickInput");
        c.f.b.k.b(iVar, "type");
        this.type = iVar;
        this.title = str;
        this.description = str2;
        this.defaultInput = str3;
        this.selectingDefaultValue = z;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003b  */
    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public b.a.l<cl> showDialog(ActivityGenericAction activityGenericAction, int i) {
        b.a.l lVarA;
        c.f.b.k.b(activityGenericAction, "activity");
        gn.d(activityGenericAction);
        if (this.type.g()) {
            lVarA = b.a.l.a(true);
        } else {
            String str = this.title;
            if (str == null || str.length() == 0) {
                lVarA = b.a.l.a(true);
            } else {
                lVarA = com.joaomgcd.taskerm.dialog.y.a(activityGenericAction, this.title, this.description, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null);
            }
        }
        String str2 = this.title;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = str2;
        String str4 = this.defaultInput;
        if (str4 == null) {
            str4 = "";
        }
        String str5 = str4;
        String str6 = this.description;
        if (str6 == null) {
            str6 = "";
        }
        b.a.l<cl> lVarA2 = lVarA.a((b.a.d.g) new b(activityGenericAction, new com.joaomgcd.taskerm.profile.c("task", 13, "%asdasdasdasdas", str3, str5, str6, this.type.d(), false, true, null, false, false)));
        c.f.b.k.a((Object) lVarA2, "initial.flatMap {\n      …s SimpleResult)\n        }");
        return lVarA2;
    }

    static final class b<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f5365b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.profile.c f5366c;

        b(ActivityGenericAction activityGenericAction, com.joaomgcd.taskerm.profile.c cVar) {
            this.f5365b = activityGenericAction;
            this.f5366c = cVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cl> apply(Object obj) {
            b.a.l<String> lVarInvoke;
            c.f.a.m<Activity, com.joaomgcd.taskerm.profile.c, b.a.l<String>> mVarC = GenericActionPickInput.this.selectingDefaultValue ? GenericActionPickInput.this.type.c() : GenericActionPickInput.this.type.b();
            b.a.l<R> lVarC = (mVarC == null || (lVarInvoke = mVarC.invoke(this.f5365b, this.f5366c)) == null) ? (b.a.l<R>) null : lVarInvoke.c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.action.input.GenericActionPickInput.b.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final cq<String, com.joaomgcd.taskerm.util.ad> apply(String str) {
                    c.f.b.k.b(str, "it");
                    return new cq<>(true, str, (com.joaomgcd.taskerm.util.ad) null);
                }
            });
            return lVarC != null ? lVarC : b.a.l.a(new cm("No valid picker"));
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public boolean getGiveUpOnActivityDeath() {
        return !this.type.f();
    }
}
