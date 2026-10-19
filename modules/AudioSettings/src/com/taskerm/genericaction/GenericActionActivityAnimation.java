package com.joaomgcd.taskerm.genericaction;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityAnimation extends GenericActionActivity {
    public static final Parcelable.Creator CREATOR = new a();
    private final Intent originalIntent;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityAnimation((Intent) parcel.readParcelable(GenericActionActivityAnimation.class.getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityAnimation[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeParcelable(this.originalIntent, i);
    }

    static final class b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f6919b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ActivityGenericAction activityGenericAction) {
            super(0);
            this.f6919b = activityGenericAction;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            this.f6919b.startActivity(GenericActionActivityAnimation.this.getOriginalIntent(), ActivityOptions.makeSceneTransitionAnimation(this.f6919b, new Pair[0]).toBundle());
        }
    }

    public GenericActionActivityAnimation(Intent intent) {
        k.b(intent, "originalIntent");
        this.originalIntent = intent;
    }

    public final Intent getOriginalIntent() {
        return this.originalIntent;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity, com.joaomgcd.taskerm.genericaction.GenericAction
    public Class<ActivityGenericActionForAnimation> getRunnerClass() {
        return ActivityGenericActionForAnimation.class;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public b.a.l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        com.joaomgcd.taskerm.rx.i.e(new b(activityGenericAction));
        b.a.l<cl> lVarA = b.a.l.a(new co());
        k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }
}
