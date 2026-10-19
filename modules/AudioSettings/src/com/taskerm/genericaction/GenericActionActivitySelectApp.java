package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Parcel;
import android.os.Parcelable;
import b.a.l;
import c.a.y;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.AppSelect;
import net.dinglisch.android.taskerm.dg;
import net.dinglisch.android.taskerm.q;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivitySelectApp extends GenericActionActivityForResult {
    public static final Parcelable.Creator CREATOR = new a();
    private final Intent filterIntent;
    private final boolean multiple;
    private final List<String> packageNames;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivitySelectApp((Intent) parcel.readParcelable(GenericActionActivitySelectApp.class.getClassLoader()), parcel.createStringArrayList(), parcel.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivitySelectApp[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeParcelable(this.filterIntent, i);
        parcel.writeStringList(this.packageNames);
        parcel.writeInt(this.multiple ? 1 : 0);
    }

    public GenericActionActivitySelectApp(Intent intent, List<String> list, boolean z) {
        super("GenericActionActivitySelectApp");
        this.filterIntent = intent;
        this.packageNames = list;
        this.multiple = z;
    }

    public /* synthetic */ GenericActionActivitySelectApp(Intent intent, List list, boolean z, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Intent) null : intent, (i & 2) != 0 ? (List) null : list, z);
    }

    public final Intent getFilterIntent() {
        return this.filterIntent;
    }

    public final boolean getMultiple() {
        return this.multiple;
    }

    public final List<String> getPackageNames() {
        return this.packageNames;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0056  */
    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<Intent> getIntentToStartForResult(Activity activity) {
        ActivityInfo activityInfo;
        k.b(activity, "context");
        List<String> listL = this.packageNames;
        if (listL == null) {
            if (this.filterIntent != null) {
                List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(this.filterIntent, 0);
                k.a((Object) listQueryIntentActivities, "context.packageManager.q…tivities(filterIntent, 0)");
                ArrayList arrayList = new ArrayList();
                for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                    String str = (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null) ? null : activityInfo.packageName;
                    if (str != null) {
                        arrayList.add(str);
                    }
                }
                listL = c.a.j.l(arrayList);
                if (listL.isEmpty()) {
                    listL = null;
                }
            } else {
                listL = null;
            }
        }
        l<Intent> lVarA = l.a(AppSelect.a(activity, !this.multiple, false, false, false, true, true, listL != null ? new ArrayList(listL) : null, null));
        k.a((Object) lVarA, "Single.just(AppSelect.ge… true, true, pkgs, null))");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivityForResult
    public l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        q qVar = new q(new dg(intent.getBundleExtra("ssc")));
        c.i.c cVarB = c.i.d.b(0, qVar.f());
        ArrayList arrayList = new ArrayList(c.a.j.a(cVarB, 10));
        Iterator<Integer> it = cVarB.iterator();
        while (it.hasNext()) {
            arrayList.add(qVar.d(((y) it).b()));
        }
        ArrayList arrayList2 = arrayList;
        l<cl> lVarA = l.a(new cq(!arrayList2.isEmpty(), c.a.j.a(arrayList2, "/", null, null, 0, null, null, 62, null), new ad("Couldn't get app")));
        k.a((Object) lVarA, "Single.just(SimpleResult…ing(\"Couldn't get app\")))");
        return lVarA;
    }
}
