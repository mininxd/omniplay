package com.joaomgcd.taskerm.genericaction;

import android.os.Parcel;
import android.os.Parcelable;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.w;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionPickContactWithMimeType extends GenericActionDialog {
    public static final Parcelable.Creator CREATOR = new a();
    private final String mimetype;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionPickContactWithMimeType(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionPickContactWithMimeType[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.mimetype);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionPickContactWithMimeType(String str) {
        super("GenericActionPickContactWithMimeType");
        k.b(str, "mimetype");
        this.mimetype = str;
    }

    public final String getMimetype() {
        return this.mimetype;
    }

    static final class b extends l implements c.f.a.a<cq<ContactData, ad>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f6949b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ActivityGenericAction activityGenericAction) {
            super(0);
            this.f6949b = activityGenericAction;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<ContactData, ad> invoke() {
            ContactData contactData = (ContactData) ((ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6949b, R.string.an_contact_via_app, com.joaomgcd.taskerm.contacts.a.a(this.f6949b, GenericActionPickContactWithMimeType.this.getMimetype()), false, a.f6950a, null, null, true, null, Integer.valueOf(gn.c(this.f6949b)), null, null, null, null, null, null, 64872, null)).b()).b();
            if (contactData == null) {
                throw new RuntimeException("No contact Selected");
            }
            return cn.a(contactData);
        }

        static final class a extends l implements c.f.a.b<ContactData, w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final a f6950a = new a();

            a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final w invoke(ContactData contactData) {
                k.b(contactData, "it");
                String name = contactData.getName();
                if (name == null) {
                    name = "Unknown";
                }
                return new w(name, null, false, 6, null);
            }
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionDialog
    public b.a.l<cl> showDialog(ActivityGenericAction activityGenericAction, int i) {
        k.b(activityGenericAction, "activity");
        return ac.f6217a.a(activityGenericAction, R.string.getting_contacts, new b(activityGenericAction));
    }
}
