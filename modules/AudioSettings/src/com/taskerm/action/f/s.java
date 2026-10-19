package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class s extends com.joaomgcd.taskerm.helper.actions.execute.l<aa> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<aa, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(aa aaVar) {
        List<String> listA;
        c.f.b.k.b(aaVar, "input");
        List<String> namesArray = aaVar.getNamesArray();
        if (namesArray == null) {
            return cn.a("No input arrays defined");
        }
        if (namesArray.isEmpty()) {
            return cn.a("No input arrays defined: 0");
        }
        if (namesArray.size() == 1) {
            return cn.a("You have to specify more than 1 input array");
        }
        List<String> list = namesArray;
        for (String str : list) {
            if (!c.l.n.a(str, "%", false, 2, (Object) null)) {
                return cn.a("Invalid array name: " + str);
            }
        }
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(gw.b(h(), (String) it.next(), true, false, j()));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(c.a.j.a((Iterable) arrayList3, 10));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList4.add(Integer.valueOf(((List) it2.next()).size()));
        }
        ArrayList arrayList5 = arrayList4;
        int iIntValue = ((Number) c.a.j.e((List) arrayList5)).intValue();
        int i = 0;
        for (Object obj : arrayList5) {
            int i2 = i + 1;
            if (i < 0) {
                c.a.j.b();
            }
            int iIntValue2 = ((Number) obj).intValue();
            if (iIntValue2 != iIntValue) {
                return cn.a("All arrays must be the same size. " + namesArray.get(0) + " has " + ((List) arrayList2.get(0)).size() + " elements while " + namesArray.get(i) + " has " + iIntValue2 + " elements");
            }
            i = i2;
        }
        String outputArrayName = aaVar.getOutputArrayName();
        if (outputArrayName == null) {
            return cn.a("Didn't specify an output array");
        }
        k mergeType = aaVar.getMergeType();
        if (mergeType == null) {
            return cn.a("Didn't specify Join type");
        }
        switch (t.f5271a[mergeType.ordinal()]) {
            case 1:
                String joiner = aaVar.getJoiner();
                if (joiner == null) {
                    return cn.a("Didn't specify a joiner for simple join");
                }
                listA = a(arrayList2, joiner);
                break;
                break;
            case 2:
                String format = aaVar.getFormat();
                if (format == null) {
                    return cn.a("Didn't specify a format to join with");
                }
                listA = a(arrayList2, namesArray, format);
                break;
                break;
            default:
                throw new c.i();
        }
        String strA = com.joaomgcd.taskerm.util.ak.A(aaVar.getOutputArrayJoiner());
        if (strA == null) {
            a(outputArrayName, listA);
        } else {
            com.joaomgcd.taskerm.helper.actions.execute.l.a(this, outputArrayName, c.a.j.a(listA, strA, null, null, 0, null, null, 62, null), false, true, 4, null);
        }
        return new co();
    }

    public static final class a<T> implements Comparator<T> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return c.b.a.a(Integer.valueOf(((String) t2).length()), Integer.valueOf(((String) t).length()));
        }
    }

    private final List<String> a(List<? extends List<String>> list, c.f.a.b<? super List<String>, String> bVar) {
        c.i.c cVarA = c.a.j.a((Collection<?>) c.a.j.e((List) list));
        ArrayList arrayList = new ArrayList(c.a.j.a(cVarA, 10));
        Iterator<Integer> it = cVarA.iterator();
        while (it.hasNext()) {
            int iB = ((c.a.y) it).b();
            List<? extends List<String>> list2 = list;
            ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) list2, 10));
            Iterator<T> it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList2.add((String) ((List) it2.next()).get(iB));
            }
            arrayList.add(bVar.invoke(arrayList2));
        }
        return arrayList;
    }

    static final class c extends c.f.b.l implements c.f.a.b<List<? extends String>, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5270a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str) {
            super(1);
            this.f5270a = str;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(List<String> list) {
            c.f.b.k.b(list, "receiver$0");
            return c.a.j.a(list, this.f5270a, null, null, 0, null, null, 62, null);
        }
    }

    private final List<String> a(List<? extends List<String>> list, String str) {
        return a(list, new c(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v11, types: [T, java.lang.String] */
    private final List<String> a(List<? extends List<String>> list, List<String> list2, String str) {
        c.f.b.w.d dVar = new c.f.b.w.d();
        int i = 0;
        dVar.f2028a = c.l.n.a(c.l.n.a(c.l.n.a(str, "'", "''", false, 4, (Object) null), "{", "'{'", false, 4, (Object) null), "}", "'}'", false, 4, (Object) null);
        List<String> list3 = list2;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list3, 10));
        for (Object obj : list3) {
            int i2 = i + 1;
            if (i < 0) {
                c.a.j.b();
            }
            arrayList.add(new c.k((String) obj, Integer.valueOf(i)));
            i = i2;
        }
        Map mapA = c.a.z.a(arrayList);
        for (String str2 : c.a.j.a((Iterable) list3, (Comparator) new a())) {
            String str3 = (String) dVar.f2028a;
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            sb.append((Integer) mapA.get(str2));
            sb.append('}');
            dVar.f2028a = c.l.n.a(str3, str2, sb.toString(), false, 4, (Object) null);
        }
        return a(list, new b(dVar));
    }

    static final class b extends c.f.b.l implements c.f.a.b<List<? extends String>, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f5269a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c.f.b.w.d dVar) {
            super(1);
            this.f5269a = dVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(List<String> list) {
            c.f.b.k.b(list, "receiver$0");
            String str = (String) this.f5269a.f2028a;
            Object[] array = list.toArray(new String[0]);
            if (array != null) {
                String str2 = MessageFormat.format(str, Arrays.copyOf(array, array.length));
                c.f.b.k.a((Object) str2, "MessageFormat.format(rea…at, *this.toTypedArray())");
                return str2;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }
}
