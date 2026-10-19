package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.as;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityPickFile extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final boolean localOnly;
    private final String type;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityPickFile(parcel.readInt() != 0, parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityPickFile[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GenericActionActivityPickFile() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeInt(this.localOnly ? 1 : 0);
        parcel.writeString(this.type);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityPickFile(boolean z, String str) {
        super("GenericActionActivityPickFile");
        k.b(str, "type");
        this.localOnly = z;
        this.type = str;
    }

    public /* synthetic */ GenericActionActivityPickFile(boolean z, String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? "*/*" : str);
    }

    public final boolean getLocalOnly() {
        return this.localOnly;
    }

    public final String getType() {
        return this.type;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.putExtra("android.intent.extra.LOCAL_ONLY", this.localOnly);
        intent.setType(this.type);
        l<Intent> lVarA = l.a(intent);
        k.a((Object) lVarA, "Single.just(Intent(Inten…ivityPickFile.type\n    })");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        if ((intent != null ? intent.getData() : null) != null) {
            return new co();
        }
        return cn.a("No data from file picker");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        Uri data = intent.getData();
        int flags = intent.getFlags();
        if ((flags & 64) > 0) {
            as.a(context.getContentResolver(), data, flags, true);
        }
        l<cl> lVarA = l.a(new cq(true, data, null));
        k.a((Object) lVarA, "Single.just(SimpleResult…(true, contentUri, null))");
        return lVarA;
    }
}
