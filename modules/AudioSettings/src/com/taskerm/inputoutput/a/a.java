package com.joaomgcd.taskerm.inputoutput.a;

import android.content.Context;
import android.os.Bundle;
import c.f.b.g;
import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.joaomgcd.taskerm.inputoutput.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public static final C0173a f7525a = new C0173a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f7526b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f7527c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ArrayList<Integer> f7528d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f7529e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(String str, String str2, d dVar, Object obj, int i, int i2, ArrayList<Integer> arrayList, boolean z) {
        super(str, f7525a.a(str2, arrayList), dVar.a(), i, i2, false, 32, null);
        k.b(str2, "nameNoSuffix");
        k.b(dVar, "valueGetter");
        this.f7526b = dVar;
        this.f7527c = obj;
        this.f7528d = arrayList;
        this.f7529e = z;
    }

    public /* synthetic */ a(String str, String str2, d dVar, Object obj, int i, int i2, ArrayList arrayList, boolean z, int i3, g gVar) {
        this(str, str2, dVar, (i3 & 8) != 0 ? null : obj, (i3 & 16) != 0 ? -1 : i, (i3 & 32) != 0 ? Integer.MAX_VALUE : i2, (i3 & 64) != 0 ? (ArrayList) null : arrayList, (i3 & 128) != 0 ? false : z);
    }

    public final d j() {
        return this.f7526b;
    }

    public final Object k() {
        return this.f7527c;
    }

    public final ArrayList<Integer> l() {
        return this.f7528d;
    }

    public final boolean m() {
        return this.f7529e;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(String str, String str2, String str3) {
        this(str, str2, new e(str3), null, 0, 0, null, false, 248, null);
        k.b(str2, "nameNoSuffix");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(String str, String str2, Object[] objArr) {
        this(str, str2, new e(objArr), null, 0, 0, null, false, 248, null);
        k.b(str2, "nameNoSuffix");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(String str, String str2, boolean z) {
        this(null, str, new e(str2), null, 0, 0, null, z, 120, null);
        k.b(str, "nameNoSuffix");
    }

    public /* synthetic */ a(String str, String str2, boolean z, int i, g gVar) {
        this(str, str2, (i & 4) != 0 ? false : z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(Context context, TaskerOutputObject taskerOutputObject, TaskerOutputVariable taskerOutputVariable, d dVar, Object obj, ArrayList<Integer> arrayList) {
        this(taskerOutputObject != null ? taskerOutputObject.varPrefix() : null, taskerOutputVariable.name(), dVar, obj, taskerOutputVariable.minApi(), taskerOutputVariable.maxApi(), arrayList, false, 128, null);
        k.b(context, "context");
        k.b(taskerOutputVariable, "taskerVariable");
        k.b(dVar, "valueGetter");
    }

    public final Object i() {
        return this.f7526b.a(this.f7527c);
    }

    public final void a(Bundle bundle) {
        k.b(bundle, "bundle");
        Object objI = i();
        if (objI != null) {
            String[] strArrA = a(objI);
            if (strArrA.length == 0) {
                return;
            }
            String strA = a();
            int length = strArrA.length;
            for (int i = 0; i < length; i++) {
                bundle.putString('%' + (e() ? strA + (i + 1) : strA), strArrA[i]);
            }
        }
    }

    private final String[] a(Object obj) {
        Object[] objArr;
        if (e()) {
            if (obj == null) {
                throw new q("null cannot be cast to non-null type kotlin.Array<*>");
            }
            objArr = (Object[]) obj;
        } else {
            objArr = new Object[]{obj};
        }
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj2 : objArr) {
            arrayList.add(String.valueOf(obj2));
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(b());
        sb.append(" = ");
        Object objI = i();
        sb.append(objI != null ? c.a.d.a(a(objI), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null) : null);
        return sb.toString();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.inputoutput.a.a$a, reason: collision with other inner class name */
    private static final class C0173a {
        private C0173a() {
        }

        public /* synthetic */ C0173a(g gVar) {
            this();
        }

        public final String a(String str, ArrayList<Integer> arrayList) {
            k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
            if (arrayList == null || arrayList.size() == 0) {
                return str;
            }
            return str + arrayList.get(0);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(String str, String str2, Collection<?> collection) {
        Object[] array;
        k.b(str2, "nameNoSuffix");
        if (collection != null) {
            array = collection.toArray(new Object[0]);
            if (array == null) {
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            array = null;
        }
        this(str, str2, new e(array), null, 0, 0, null, false, 248, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(String str, Collection<?> collection) {
        Object[] array;
        k.b(str, "nameNoSuffix");
        if (collection != null) {
            array = collection.toArray(new Object[0]);
            if (array == null) {
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            array = null;
        }
        this(null, str, new e(array), null, 0, 0, null, false, 248, null);
    }
}
