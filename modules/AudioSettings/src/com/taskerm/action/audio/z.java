package com.joaomgcd.taskerm.action.audio;

import android.app.usage.UsageEvents;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ah;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class z extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5015a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(z.class), "appEvents", "getAppEvents()Ljava/util/List;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5016b;

    private final List<c.k<String, List<UsageEvents.Event>>> b() {
        c.e eVar = this.f5016b;
        c.j.g gVar = f5015a[0];
        return (List) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(Context context) {
        super(context);
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
        this.f5016b = c.f.a(new a(context));
    }

    static final class a extends c.f.b.l implements c.f.a.a<List<? extends c.k<? extends String, ? extends List<? extends UsageEvents.Event>>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5017a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(0);
            this.f5017a = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<c.k<String, List<UsageEvents.Event>>> invoke() {
            List listG = c.a.j.g((Iterable) ah.a(this.f5017a, 1200000000L, (Integer) null, 2, (Object) null));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : listG) {
                String packageName = ((UsageEvents.Event) obj).getPackageName();
                Object arrayList = linkedHashMap.get(packageName);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(packageName, arrayList);
                }
                ((List) arrayList).add(obj);
            }
            return c.a.z.d(linkedHashMap);
        }
    }

    private final String a(int i) {
        c.k kVar = (c.k) c.a.j.a((List) b(), i);
        if (kVar != null) {
            return (String) kVar.a();
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.action.audio.v
    public t a(String str, String[] strArr, boolean z, boolean z2) {
        c.f.b.k.b(str, "param");
        y.a aVarA = a(str);
        c.i.c cVarA = aVarA.a();
        ArrayList arrayList = new ArrayList(c.a.j.a(cVarA, 10));
        Iterator<Integer> it = cVarA.iterator();
        int i = 0;
        while (true) {
            String str2 = null;
            if (it.hasNext()) {
                int iB = ((c.a.y) it).b();
                String strA = a(iB + i);
                if (strA != null) {
                    while (true) {
                        if (!a(strA, z2) && (strArr == null || !c.a.d.b(strArr, strA))) {
                            str2 = strA;
                            break;
                        }
                        if (!z) {
                            break;
                        }
                        i++;
                        strA = a(iB + i);
                    }
                }
                arrayList.add(str2);
            } else {
                return new t(a(arrayList, !aVarA.b()), null, 2, null);
            }
        }
    }
}
