package com.joaomgcd.taskerm.action.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityForResultForIntentSettingsPanel extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final Intent intent;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionActivityForResultForIntentSettingsPanel((Intent) parcel.readParcelable(GenericActionActivityForResultForIntentSettingsPanel.class.getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityForResultForIntentSettingsPanel[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeParcelable(this.intent, i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityForResultForIntentSettingsPanel(Intent intent) {
        super("GenericActionActivityForResultForIntentSettingsPanel");
        c.f.b.k.b(intent, "intent");
        this.intent = intent;
    }

    public final Intent getIntent() {
        return this.intent;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        c.f.b.k.b(activity, "context");
        return b.a.l.a(this.intent);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        c.f.b.k.b(activity, "activity");
        return new co();
    }
}
