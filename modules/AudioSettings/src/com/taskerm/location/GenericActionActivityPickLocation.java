package com.joaomgcd.taskerm.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;

/* JADX INFO: loaded from: classes.dex */
final class GenericActionActivityPickLocation extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7653a;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            c.f.b.k.b(parcel, "in");
            return new GenericActionActivityPickLocation(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityPickLocation[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GenericActionActivityPickLocation() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        c.f.b.k.b(parcel, "parcel");
        parcel.writeString(this.f7653a);
    }

    public /* synthetic */ GenericActionActivityPickLocation(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public GenericActionActivityPickLocation(String str) {
        super("GenericActionActivityPickLocation");
        this.f7653a = str;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<Intent> getIntentToStartForResult(Activity activity) {
        c.f.b.k.b(activity, "context");
        Intent intent = new Intent(activity, (Class<?>) ActivityPickLocation.class);
        intent.putExtra("args", this.f7653a);
        b.a.l<Intent> lVarA = b.a.l.a(intent);
        c.f.b.k.a((Object) lVarA, "Single.just(Intent(conte…PickLocation.json)\n    })");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public b.a.l<cl> getResult(Context context, Intent intent) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(intent, "intent");
        Location location = (Location) intent.getParcelableExtra("location");
        if (location == null) {
            b.a.l<cl> lVarB = b.a.l.b(new RuntimeException("No location picked"));
            c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce…on(\"No location picked\"))");
            return lVarB;
        }
        Integer numValueOf = Integer.valueOf(intent.getIntExtra("radius", -1));
        if (numValueOf.intValue() == -1) {
            numValueOf = (Integer) null;
        }
        b.a.l<cl> lVarA = b.a.l.a(cn.a(new h(location.getLatitude(), location.getLongitude(), numValueOf)));
        c.f.b.k.a((Object) lVarA, "Single.just(SimpleResult…tion.longitude, radius)))");
        return lVarA;
    }
}
