package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class y extends v {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(Context context) {
        super(context);
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
    }

    protected static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.i.c f5013a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f5014b;

        public a(c.i.c cVar, boolean z) {
            c.f.b.k.b(cVar, "range");
            this.f5013a = cVar;
            this.f5014b = z;
        }

        public final c.i.c a() {
            return this.f5013a;
        }

        public final boolean b() {
            return this.f5014b;
        }
    }

    protected final a a(String str) {
        String[] strArr;
        c.i.c cVar;
        c.f.b.k.b(str, "receiver$0");
        String strA = ak.a(ak.a(str, "last("), ")");
        String str2 = strA;
        if (!c.l.n.b((CharSequence) str2, (CharSequence) ":", false, 2, (Object) null)) {
            strArr = new String[]{strA};
        } else {
            Object[] array = c.l.n.b((CharSequence) str2, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str3 : strArr) {
            Integer numC = c.l.n.c(ak.a(str3, "s"));
            if (numC == null) {
                throw new RuntimeException("last's parameter has to be a number or a range (separated by :)");
            }
            arrayList.add(Integer.valueOf(numC.intValue()));
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.size() > 2 || arrayList2.isEmpty()) {
            throw new RuntimeException("last's parameter has to be a number or a range (separated by :)");
        }
        int iIntValue = ((Number) c.a.j.e((List) arrayList2)).intValue();
        int iIntValue2 = arrayList2.size() == 1 ? iIntValue : ((Number) arrayList2.get(1)).intValue();
        boolean z = iIntValue < iIntValue2;
        if (z) {
            cVar = new c.i.c(iIntValue, iIntValue2);
        } else {
            cVar = new c.i.c(iIntValue2, iIntValue);
        }
        return new a(cVar, z);
    }

    protected final boolean a(String str, boolean z) {
        if (!z) {
            return false;
        }
        if (str != null) {
            return !ah.o(a(), str);
        }
        return true;
    }

    protected final List<String> a(List<String> list, boolean z) {
        c.f.b.k.b(list, "receiver$0");
        List<String> listF = c.a.j.f((Iterable) list);
        return z ? c.a.j.g((Iterable) listF) : listF;
    }
}
