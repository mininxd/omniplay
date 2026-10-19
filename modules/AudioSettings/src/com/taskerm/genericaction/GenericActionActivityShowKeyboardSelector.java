package com.joaomgcd.taskerm.genericaction;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.inputmethod.InputMethodManager;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.dm;
import net.dinglisch.android.taskerm.de;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityShowKeyboardSelector extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            if (parcel.readInt() != 0) {
                return new GenericActionActivityShowKeyboardSelector();
            }
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityShowKeyboardSelector[i];
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

    public GenericActionActivityShowKeyboardSelector() {
        super("GenericActionActivityShowKeyboardSelector");
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        dm.a(500L);
        InputMethodManager inputMethodManager = (InputMethodManager) de.a(activityGenericAction, "input_method", "E", "exeimm");
        if (inputMethodManager == null) {
            l<cl> lVarA = l.a(cn.a("Couldn't get input manager"));
            k.a((Object) lVarA, "Single.just(SimpleResult…dn't get input manager\"))");
            return lVarA;
        }
        inputMethodManager.showInputMethodPicker();
        l<cl> lVarA2 = l.a(new co());
        k.a((Object) lVarA2, "Single.just(SimpleResultSuccess())");
        return lVarA2;
    }
}
