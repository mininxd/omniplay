package com.joaomgcd.taskerm.genericaction;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import b.a.p;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.az;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityRequestRoot extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityRequestRoot();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestRoot[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeInt(1);
    }

    public GenericActionActivityRequestRoot() {
        super("GenericActionActivityRequestRootAccess");
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6939a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl apply(Boolean bool) {
            k.b(bool, "canRoot");
            if (bool.booleanValue()) {
                return new co();
            }
            return cn.a("No root access");
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        l<cl> lVarD = az.a((Context) activityGenericAction, true).c(b.f6939a).d(c.f6940a);
        k.a((Object) lVarD, "Init.canRoot(context, tr…(SimpleResultError(it)) }");
        return lVarD;
    }

    static final class c<T, R> implements b.a.d.g<Throwable, p<? extends cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6940a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<cm> apply(Throwable th) {
            k.b(th, "it");
            return l.a(new cm(th));
        }
    }
}
