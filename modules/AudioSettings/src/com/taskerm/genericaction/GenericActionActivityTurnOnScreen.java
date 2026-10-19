package com.joaomgcd.taskerm.genericaction;

import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ch;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(27)
public final class GenericActionActivityTurnOnScreen extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();
    private final Integer blockMs;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityTurnOnScreen(parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityTurnOnScreen[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iIntValue;
        k.b(parcel, "parcel");
        Integer num = this.blockMs;
        if (num != null) {
            parcel.writeInt(1);
            iIntValue = num.intValue();
        } else {
            iIntValue = 0;
        }
        parcel.writeInt(iIntValue);
    }

    public GenericActionActivityTurnOnScreen(Integer num) {
        super("GenericActionActivityTurnOnScreen");
        this.blockMs = num;
    }

    public final Integer getBlockMs() {
        return this.blockMs;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) throws InterruptedException {
        k.b(activityGenericAction, "context");
        if (new ch(activityGenericAction).a()) {
            l<cl> lVarA = l.a(cn.a((Object) null));
            k.a((Object) lVarA, "Single.just(SimpleResultSuccess(null))");
            return lVarA;
        }
        if (com.joaomgcd.taskerm.util.f.f9323b.s()) {
            activityGenericAction.setTurnScreenOn(true);
        }
        Integer num = this.blockMs;
        Thread.sleep(num != null ? num.intValue() : 500L);
        l<cl> lVarA2 = l.a(cn.a((Object) null));
        k.a((Object) lVarA2, "Single.just(SimpleResultSuccess(null))");
        return lVarA2;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity
    public Integer[] getWindowFlags() {
        return new Integer[]{524288, 2097152};
    }
}
