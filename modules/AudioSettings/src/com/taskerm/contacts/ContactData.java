package com.joaomgcd.taskerm.contacts;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import androidx.annotation.Keep;
import c.a.j;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ContactData {
    private final String _id;
    private final String data1;
    private final String data4;
    private final String display_name;
    private final String mimetype;

    public ContactData(String str, String str2, String str3, String str4, String str5) {
        k.b(str, "_id");
        this._id = str;
        this.display_name = str2;
        this.mimetype = str3;
        this.data1 = str4;
        this.data4 = str5;
    }

    public final String getName() {
        return this.display_name;
    }

    public final String getMimeType() {
        return this.mimetype;
    }

    public final boolean isKnownMimeType() {
        return aj.a(getMimeType(), a.b());
    }

    public final Uri getContactUri() {
        Uri uriC = ak.C(getContactUriString());
        if (uriC != null) {
            return uriC;
        }
        throw new RuntimeException("Couldn't get contact Uri");
    }

    public final String getContactUriString() {
        return "content://com.android.contacts/data/" + this._id;
    }

    public final ActivityInfo getApp(Context context) {
        k.b(context, "context");
        return (ActivityInfo) j.a((List) ah.a(context, getContactUri()), 0);
    }

    public final String getNumber() {
        String str = this.data4;
        return str != null ? str : this.data1;
    }
}
