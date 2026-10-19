package com.joaomgcd.taskerm.action.audio;

import android.app.usage.UsageEvents;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends y {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(Context context) {
        super(context);
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
    }

    @Override // com.joaomgcd.taskerm.action.audio.v
    public t a(String str, String[] strArr, boolean z, boolean z2) {
        c.k kVar;
        ArrayList<UsageEvents.Event> arrayListInvoke;
        c.f.b.k.b(str, "param");
        y.a aVarA = a(str);
        c.i.c cVarA = aVarA.a();
        long jE = aj.e(cVarA.a());
        long jE2 = aj.e(cVarA.b());
        if (aj.a(cVarA)) {
            kVar = new c.k(Long.valueOf(jE2), Long.valueOf(jE));
        } else {
            kVar = new c.k(Long.valueOf(jE), Long.valueOf(jE2));
        }
        long jLongValue = ((Number) kVar.c()).longValue();
        long jLongValue2 = ((Number) kVar.d()).longValue();
        a aVar = new a(jLongValue2);
        if (jLongValue != jLongValue2) {
            arrayListInvoke = ah.a(a(), aj.b(jLongValue), aj.b(jLongValue2), (Integer) null, 4, (Object) null);
            if (arrayListInvoke.isEmpty()) {
                arrayListInvoke = aVar.invoke();
            }
        } else {
            arrayListInvoke = aVar.invoke();
        }
        String str2 = (String) null;
        ArrayList<UsageEvents.Event> arrayList = arrayListInvoke;
        ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            String packageName = ((UsageEvents.Event) it.next()).getPackageName();
            if (packageName != null && !c.f.b.k.a((Object) str2, (Object) packageName)) {
                if (!a(packageName, z2)) {
                    str2 = packageName;
                }
                arrayList2.add(packageName);
            }
            packageName = null;
            arrayList2.add(packageName);
        }
        return new t(a(arrayList2, aVarA.b()), null, 2, null);
    }

    static final class a extends c.f.b.l implements c.f.a.a<ArrayList<UsageEvents.Event>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f4951b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(long j) {
            super(0);
            this.f4951b = j;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ArrayList<UsageEvents.Event> invoke() {
            ArrayList<UsageEvents.Event> arrayListD;
            UsageEvents.Event event = (UsageEvents.Event) c.a.j.h((List) ah.a(aa.this.a(), aj.b(1200000000L), aj.b(this.f4951b), (Integer) null, 4, (Object) null));
            return (event == null || (arrayListD = c.a.j.d(event)) == null) ? new ArrayList<>() : arrayListD;
        }
    }
}
