package com.joaomgcd.taskerm.action.audio;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class m extends com.joaomgcd.taskerm.helper.actions.execute.l<q> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean c() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<q, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(q qVar) {
        String[] strArr;
        ArrayList arrayListA;
        w abVar;
        c.f.b.k.b(qVar, "input");
        Boolean ignoreAppsThatCantBeLaunched = qVar.getIgnoreAppsThatCantBeLaunched();
        boolean zBooleanValue = ignoreAppsThatCantBeLaunched != null ? ignoreAppsThatCantBeLaunched.booleanValue() : false;
        List<String> appPackagesIgnore = qVar.getAppPackagesIgnore();
        if (appPackagesIgnore != null) {
            Object[] array = appPackagesIgnore.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        } else {
            strArr = null;
        }
        try {
            if (qVar.isEmpty()) {
                arrayListA = c.a.j.a(new x(h()).a("", strArr, true, zBooleanValue));
            } else {
                List<String> appPackages = qVar.getAppPackages();
                ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) appPackages, 10));
                for (String str : appPackages) {
                    if (c.g(str)) {
                        abVar = c.h(str) ? new aa(h()) : new z(h());
                    } else {
                        abVar = c.f(str) ? new ab(h()) : new w(h());
                    }
                    arrayList.add(abVar.a(str, strArr, true, zBooleanValue));
                }
                arrayListA = arrayList;
            }
            boolean shouldGetFullInfo = qVar.getShouldGetFullInfo();
            try {
                Iterable iterable = arrayListA;
                ArrayList arrayList2 = new ArrayList(c.a.j.a(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((t) it.next()).a(h(), shouldGetFullInfo));
                }
                List listA = c.a.j.a((Iterable) arrayList2);
                if (listA.isEmpty()) {
                    return cn.a("No apps found for input");
                }
                com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
                List list = listA;
                if (!list.isEmpty()) {
                    bVar.a(h(), listA.get(0));
                }
                if (!qVar.getHasJustOneAppOriginal()) {
                    ExecuteService executeServiceH = h();
                    Object[] array2 = list.toArray(new AppBasic[0]);
                    if (array2 != null) {
                        bVar.a(executeServiceH, array2);
                    } else {
                        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
                ArrayList arrayList3 = new ArrayList();
                Iterator it2 = arrayListA.iterator();
                while (it2.hasNext()) {
                    com.joaomgcd.taskerm.inputoutput.a.b bVarA = ((t) it2.next()).a();
                    if (bVarA != null) {
                        arrayList3.add(bVarA);
                    }
                }
                Iterator it3 = arrayList3.iterator();
                while (it3.hasNext()) {
                    bVar.addAll((com.joaomgcd.taskerm.inputoutput.a.b) it3.next());
                }
                return new cq(true, bVar, null);
            } catch (Exception e2) {
                return cn.a(e2.getMessage());
            }
        } catch (Exception e3) {
            return cn.a(e3.getMessage());
        }
    }
}
