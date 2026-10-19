package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionPickContact extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final b type;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionPickContact(parcel.readInt() != 0 ? (b) Enum.valueOf(b.class, parcel.readString()) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionPickContact[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        b bVar = this.type;
        if (bVar == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(bVar.name());
        }
    }

    public GenericActionPickContact(b bVar) {
        super("GenericActionPickContact");
        this.type = bVar;
    }

    public final b getType() {
        return this.type;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        Intent intent = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
        if (this.type != null) {
            intent.setType(this.type.a());
        }
        l<Intent> lVarA = l.a(intent);
        k.a((Object) lVarA, "Single.just(intent)");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        Uri data = intent.getData();
        if (data == null) {
            l<cl> lVarA = l.a(cn.a("Couldn't get contact uri"));
            k.a((Object) lVarA, "Single.just(SimpleResult…uldn't get contact uri\"))");
            return lVarA;
        }
        ContactData contactDataA = com.joaomgcd.taskerm.contacts.a.a(context, data);
        if (contactDataA == null) {
            l<cl> lVarA2 = l.a(cn.a("Couldn't get contact for uri"));
            k.a((Object) lVarA2, "Single.just(SimpleResult…'t get contact for uri\"))");
            return lVarA2;
        }
        l<cl> lVarA3 = l.a(cn.a(contactDataA));
        k.a((Object) lVarA3, "Single.just(SimpleResultSuccess(contactData))");
        return lVarA3;
    }
}
