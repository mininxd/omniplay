package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.bo;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class GenericActionActivityRequestNormalPermissions extends GenericActionActivity {
    static final /* synthetic */ c.j.g[] $$delegatedProperties = {x.a(new v(x.a(GenericActionActivityRequestNormalPermissions.class), "publisher", "getPublisher()Lio/reactivex/subjects/SingleSubject;"))};
    public static final Parcelable.Creator CREATOR = new a();
    private final String[] permissions;
    private final c.e publisher$delegate;
    private final int requestCode;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            k.b(parcel, "in");
            return new GenericActionActivityRequestNormalPermissions(parcel.createStringArray(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new GenericActionActivityRequestNormalPermissions[i];
        }
    }

    private final b.a.k.d<cl> getPublisher() {
        c.e eVar = this.publisher$delegate;
        c.j.g gVar = $$delegatedProperties[0];
        return (b.a.k.d) eVar.b();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        k.b(parcel, "parcel");
        parcel.writeStringArray(this.permissions);
        parcel.writeInt(this.requestCode);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public GenericActionActivityRequestNormalPermissions(String[] strArr, int i) {
        k.b(strArr, "permissions");
        String string = UUID.randomUUID().toString();
        k.a((Object) string, "UUID.randomUUID().toString()");
        super(string);
        this.permissions = strArr;
        this.requestCode = i;
        this.publisher$delegate = c.f.a(b.f6937a);
    }

    public final String[] getPermissions() {
        return this.permissions;
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityRequestNormalPermissions(bt btVar, int i) {
        this(btVar.j(), i);
        k.b(btVar, "permissions");
    }

    static final class b extends l implements c.f.a.a<b.a.k.d<cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6937a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.d<cl> invoke() {
            return b.a.k.d.f();
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity, com.joaomgcd.taskerm.genericaction.GenericAction
    public Class<ActivityGenericActionForResult> getRunnerClass() {
        return ActivityGenericActionForResult.class;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity
    public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
        k.b(activity, "activity");
        k.b(strArr, "permissions");
        k.b(iArr, "grantResults");
        if (i != this.requestCode) {
            return;
        }
        String[] strArrB = bt.f9101c.b();
        String[] strArrJ = new bt(activity, i, (String[]) Arrays.copyOf(strArr, strArr.length)).j();
        ArrayList arrayList = new ArrayList();
        for (String str : strArrJ) {
            if (!c.a.d.b(strArrB, str)) {
                arrayList.add(str);
            }
        }
        getPublisher().d_(handleNotGrantedKeys(arrayList));
    }

    private final cl handleNotGrantedKeys(List<String> list) {
        if (list.isEmpty()) {
            return new co();
        }
        return new cm("Missing permissions: " + c.a.j.a(list, ", ", null, null, 0, null, null, 62, null));
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public b.a.l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        int i = this.requestCode;
        String[] strArr = this.permissions;
        bt btVar = new bt(activityGenericAction, i, (String[]) Arrays.copyOf(strArr, strArr.length));
        if (!c.a.d.b(btVar.p(), "android.permission.ACCESS_BACKGROUND_LOCATION") && btVar.d().isEmpty()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        bt.c cVar = (bt.c) bt.a(btVar, activityGenericAction, null, 2, null).b();
        if (!cVar.b()) {
            List<bo> listA = cVar.a();
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
            Iterator<T> it = listA.iterator();
            while (it.hasNext()) {
                arrayList.add(((bo) it.next()).b());
            }
            b.a.l<cl> lVarA2 = b.a.l.a(handleNotGrantedKeys(arrayList));
            k.a((Object) lVarA2, "Single.just(handleNotGrantedKeys(notGranted))");
            return lVarA2;
        }
        b.a.k.d<cl> publisher = getPublisher();
        k.a((Object) publisher, "publisher");
        return publisher;
    }
}
