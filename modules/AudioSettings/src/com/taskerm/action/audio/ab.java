package com.joaomgcd.taskerm.action.audio;

import android.app.usage.UsageEvents;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cd;
import com.joaomgcd.taskerm.util.dm;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends v {

    public static final class a<T> implements Comparator<T> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return c.b.a.a(Long.valueOf(((u) t2).b()), Long.valueOf(((u) t).b()));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(Context context) {
        super(context);
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
    }

    @Override // com.joaomgcd.taskerm.action.audio.v
    public t a(String str, String[] strArr, boolean z, boolean z2) {
        c.k kVar;
        ArrayList arrayList;
        Integer numC;
        c.f.b.k.b(str, "param");
        List listB = c.l.n.b((CharSequence) ak.a(ak.a(str, "most("), ")"), new String[]{","}, false, 0, 6, (Object) null);
        if (listB.size() < 2) {
            throw new RuntimeException("the \"most\" mode has to have at least 2 parameters. Were " + c.a.j.a(listB, null, null, null, 0, null, null, 63, null));
        }
        String str2 = (String) c.a.j.a(listB, 0);
        if (str2 != null) {
            af afVarValueOf = af.valueOf(str2);
            String str3 = (String) c.a.j.a(listB, 1);
            if (str3 != null) {
                String str4 = str3;
                if (!(str4.length() == 0)) {
                    long j = 0;
                    if (c.e(str3)) {
                        List listB2 = c.l.n.b((CharSequence) str4, new String[]{":"}, false, 0, 6, (Object) null);
                        if (listB2.size() != 2) {
                            throw new RuntimeException("\"most\"'s second is a range, but it didn't have a start and an end. Was " + str3);
                        }
                        String str5 = (String) listB2.get(0);
                        String str6 = (String) listB2.get(1);
                        Long lD = c.l.n.d(str5);
                        if (lD != null) {
                            Long lValueOf = Long.valueOf(lD.longValue());
                            Long lD2 = c.l.n.d(str6);
                            if (lD2 != null) {
                                kVar = new c.k(lValueOf, Long.valueOf(lD2.longValue()));
                            } else {
                                throw new RuntimeException("\"most\"'s second parameter has to be a number. Was " + str6);
                            }
                        } else {
                            throw new RuntimeException("\"most\"'s second parameter's range start has to be a number. Was " + str5);
                        }
                    } else {
                        Long lD3 = c.l.n.d(str3);
                        if (lD3 != null) {
                            kVar = new c.k(Long.valueOf(lD3.longValue()), 0L);
                        } else {
                            throw new RuntimeException("\"most\"'s second parameter has to be a number. Was " + str3);
                        }
                    }
                    long jLongValue = ((Number) kVar.c()).longValue();
                    long jLongValue2 = ((Number) kVar.d()).longValue();
                    String str7 = (String) c.a.j.a(listB, 2);
                    cd cdVar = new cd(afVarValueOf, Long.valueOf(aj.b(aj.c(jLongValue))), Long.valueOf(aj.b(aj.c(jLongValue2))), Integer.valueOf((str7 == null || (numC = c.l.n.c(str7)) == null) ? 0 : numC.intValue()));
                    af afVar = (af) cdVar.a();
                    long jLongValue3 = ((Number) cdVar.b()).longValue();
                    long jLongValue4 = ((Number) cdVar.c()).longValue();
                    int iIntValue = ((Number) cdVar.d()).intValue();
                    ArrayList<UsageEvents.Event> arrayListA = ah.a(a(), jLongValue3, jLongValue4, (Integer) 1);
                    switch (afVar) {
                        case time:
                            HashMap map = new HashMap();
                            int i = 0;
                            for (Object obj : arrayListA) {
                                int i2 = i + 1;
                                if (i < 0) {
                                    c.a.j.b();
                                }
                                UsageEvents.Event event = (UsageEvents.Event) obj;
                                String packageName = event.getPackageName();
                                Long lValueOf2 = (Long) map.get(packageName);
                                if (lValueOf2 == null) {
                                    lValueOf2 = Long.valueOf(j);
                                }
                                c.f.b.k.a((Object) lValueOf2, "packagesAndTimes.get(packageName) ?: 0L");
                                long jLongValue5 = lValueOf2.longValue();
                                UsageEvents.Event event2 = (UsageEvents.Event) c.a.j.a((List) arrayListA, i2);
                                long timeStamp = (event2 != null ? event2.getTimeStamp() : dm.f()) - event.getTimeStamp();
                                c.f.b.k.a((Object) packageName, "packageName");
                                map.put(packageName, Long.valueOf(jLongValue5 + timeStamp));
                                i = i2;
                                j = 0;
                            }
                            HashMap map2 = map;
                            ArrayList arrayList2 = new ArrayList(map2.size());
                            for (Map.Entry entry : map2.entrySet()) {
                                arrayList2.add(new u((String) entry.getKey(), ((Number) entry.getValue()).longValue() / 1000));
                            }
                            arrayList = arrayList2;
                            break;
                        case opened:
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            for (Object obj2 : arrayListA) {
                                String packageName2 = ((UsageEvents.Event) obj2).getPackageName();
                                Object arrayList3 = linkedHashMap.get(packageName2);
                                if (arrayList3 == null) {
                                    arrayList3 = new ArrayList();
                                    linkedHashMap.put(packageName2, arrayList3);
                                }
                                ((List) arrayList3).add(obj2);
                            }
                            List<c.k> listD = c.a.z.d(linkedHashMap);
                            ArrayList arrayList4 = new ArrayList(c.a.j.a((Iterable) listD, 10));
                            for (c.k kVar2 : listD) {
                                Object objA = kVar2.a();
                                c.f.b.k.a(objA, "it.first");
                                arrayList4.add(new u((String) objA, ((List) kVar2.b()).size()));
                            }
                            arrayList = arrayList4;
                            break;
                        default:
                            throw new c.i();
                    }
                    ArrayList arrayListA2 = c.a.j.a((Iterable) arrayList, (Comparator) new a());
                    if (strArr != null) {
                        ArrayList arrayList5 = new ArrayList();
                        for (Object obj3 : arrayListA2) {
                            if (!c.a.d.b(strArr, ((u) obj3).a())) {
                                arrayList5.add(obj3);
                            }
                        }
                        arrayListA2 = arrayList5;
                    }
                    ArrayList arrayListC = arrayListA2;
                    if (z2) {
                        ArrayList arrayList6 = new ArrayList();
                        for (Object obj4 : arrayListC) {
                            if (ah.o(a(), ((u) obj4).a())) {
                                arrayList6.add(obj4);
                            }
                        }
                        arrayListC = arrayList6;
                    }
                    if (iIntValue > 0) {
                        arrayListC = c.a.j.c(arrayListC, iIntValue);
                    }
                    com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
                    List list = arrayListC;
                    ArrayList arrayList7 = new ArrayList(c.a.j.a((Iterable) list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList7.add(String.valueOf(((u) it.next()).b()));
                    }
                    Object[] array = arrayList7.toArray(new String[0]);
                    if (array != null) {
                        bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a("app", "used", array));
                        c.s sVar = c.s.f2131a;
                        ArrayList arrayList8 = new ArrayList(c.a.j.a((Iterable) list, 10));
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            arrayList8.add(((u) it2.next()).a());
                        }
                        return new t(arrayList8, bVar);
                    }
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            throw new RuntimeException("\"most\"'s second parameter has to be a number or range (number:number) but was empty");
        }
        throw new RuntimeException("\"most\"'s first parameter has to be \"time\" or \"opened\". Was " + str2);
    }
}
