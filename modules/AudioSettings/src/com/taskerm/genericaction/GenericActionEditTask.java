package com.joaomgcd.taskerm.genericaction;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.fs;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionEditTask extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();
    private final String actionNameForLogs;
    private final String actionSpec;
    private final Integer taskId;
    private final String taskName;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionEditTask(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionEditTask[i];
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
        parcel.writeString(this.actionNameForLogs);
        parcel.writeString(this.taskName);
        parcel.writeString(this.actionSpec);
        Integer num = this.taskId;
        if (num != null) {
            parcel.writeInt(1);
            iIntValue = num.intValue();
        } else {
            iIntValue = 0;
        }
        parcel.writeInt(iIntValue);
    }

    public GenericActionEditTask(String str, String str2, String str3, Integer num) {
        k.b(str, "actionNameForLogs");
        this.actionNameForLogs = str;
        this.taskName = str2;
        this.actionSpec = str3;
        this.taskId = num;
    }

    public final String getActionNameForLogs() {
        return this.actionNameForLogs;
    }

    public final String getActionSpec() {
        return this.actionSpec;
    }

    public final Integer getTaskId() {
        return this.taskId;
    }

    public final String getTaskName() {
        return this.taskName;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        l<cl> lVarA = l.a(fs.a((Context) activityGenericAction, this.actionNameForLogs, this.taskName, this.actionSpec, this.taskId, false));
        k.a((Object) lVarA, "Single.just(result)");
        return lVarA;
    }
}
