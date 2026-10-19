package com.joaomgcd.taskerm.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cp;

/* JADX INFO: loaded from: classes.dex */
final class ActionGoogleSignIn extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8594a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8595b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f8596c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String[] f8597d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f8598e;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new ActionGoogleSignIn(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.createStringArray(), parcel.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new ActionGoogleSignIn[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeString(this.f8594a);
        parcel.writeString(this.f8595b);
        parcel.writeInt(this.f8596c ? 1 : 0);
        parcel.writeStringArray(this.f8597d);
        parcel.writeInt(this.f8598e ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionGoogleSignIn(String str, String str2, boolean z, String[] strArr, boolean z2) {
        super("ActionGoogleSignIn");
        k.b(strArr, "extraScopes");
        this.f8594a = str;
        this.f8595b = str2;
        this.f8596c = z;
        this.f8597d = strArr;
        this.f8598e = z2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActionGoogleSignIn(com.joaomgcd.taskerm.signin.a aVar) {
        this(aVar.b(), aVar.c(), aVar.d(), aVar.e(), aVar.f());
        k.b(aVar, "args");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        com.google.android.gms.auth.api.signin.c cVarA = com.google.android.gms.auth.api.signin.a.a(activity, new com.joaomgcd.taskerm.signin.a(this.f8594a, this.f8595b, this.f8596c, this.f8597d, null, false, 48, null).a());
        k.a((Object) cVarA, "client");
        l<Intent> lVarA = l.a(cVarA.a());
        k.a((Object) lVarA, "Single.just(client.signInIntent)");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        cm cmVar;
        k.b(activity, "activity");
        if (intent == null) {
            return cn.a("No result intent for sign in");
        }
        try {
            a(intent);
            return new co();
        } catch (com.google.android.gms.common.api.b e2) {
            if (this.f8598e) {
                i.a(activity, e2);
                cmVar = new co();
            } else {
                cmVar = new cm(ai.a(e2));
            }
            return cmVar;
        }
    }

    public final GoogleSignInAccount a(Intent intent) {
        k.b(intent, "receiver$0");
        return com.google.android.gms.auth.api.signin.a.a(intent).a(com.google.android.gms.common.api.b.class);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        try {
            l<cl> lVarA = l.a(new cp(com.google.android.gms.auth.api.signin.a.a(intent).a(com.google.android.gms.common.api.b.class)));
            k.a((Object) lVarA, "Single.just(SimpleResult…ccessWithPayload(result))");
            return lVarA;
        } catch (com.google.android.gms.common.api.b e2) {
            l<cl> lVarA2 = l.a(new cm(e2));
            k.a((Object) lVarA2, "Single.just(SimpleResultError(ex))");
            return lVarA2;
        }
    }
}
