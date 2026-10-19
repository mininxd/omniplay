package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityForResultForIntent extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final Intent intent;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityForResultForIntent((Intent) parcel.readParcelable(GenericActionActivityForResultForIntent.class.getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityForResultForIntent[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeParcelable(this.intent, i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityForResultForIntent(Intent intent) {
        super("GenericActionActivityForResultForIntent:" + intent);
        k.b(intent, "intent");
        this.intent = intent;
    }

    public final Intent getIntent() {
        return this.intent;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        k.b(activity, "context");
        return l.a(this.intent);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        if (intent == null) {
            return cn.a("No result intent");
        }
        return new co();
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        l<cl> lVarA = l.a(new cq(true, intent, null));
        k.a((Object) lVarA, "Single.just(SimpleResult…load(true, intent, null))");
        return lVarA;
    }
}
