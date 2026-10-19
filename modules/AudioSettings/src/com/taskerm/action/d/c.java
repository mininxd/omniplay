package com.joaomgcd.taskerm.action.d;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import c.a.z;
import c.f.b.k;
import c.l.n;
import c.s;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.de;
import java.util.HashMap;
import java.util.Iterator;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class c extends l<d> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<d, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00ab  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(d dVar) {
        boolean z;
        int i;
        k.b(dVar, "input");
        fs fsVarF = f();
        if (fsVarF == null) {
            bn.c("E", "return: parent task not present");
            return cn.a(-3);
        }
        HashMap map = new HashMap();
        String strAp = fsVarF.ap();
        Boolean boolAq = fsVarF.aq();
        Boolean boolAr = fsVarF.ar();
        String value = dVar.getValue();
        String str = strAp;
        if (!(str == null || str.length() == 0)) {
            String str2 = value;
            if (!(str2 == null || str2.length() == 0)) {
                map.put(strAp, value);
            }
        }
        if (dVar.getVariablePassthrough()) {
            Bundle bundleQ = fsVarF.q();
            k.a((Object) bundleQ, "childTask.variables");
            aj.a(bundleQ, (HashMap<String, String>) map, true, dVar.getVariablePassthroughList());
        }
        if (map.isEmpty()) {
            bn.b("E", i().n() + ": skipping return of value, parent didn't specify return variable");
        }
        if (dVar.getVariablePassthroughReplace()) {
            k.a((Object) boolAq, "allowOverwriteFromParent");
            if (boolAq.booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        de deVar = h().f10618a;
        k.a((Object) deVar, "service.queue");
        synchronized (deVar) {
            de deVar2 = h().f10618a;
            k.a((Object) deVar2, "service.queue");
            i = -1;
            for (fs fsVar : deVar2) {
                Iterator it = z.d(map).iterator();
                while (true) {
                    if (it.hasNext()) {
                        c.k kVar = (c.k) it.next();
                        String str3 = (String) kVar.a();
                        String str4 = (String) kVar.b();
                        if (fsVar.al() && fsVar.ak().contains(Integer.valueOf(fsVarF.am()))) {
                            String strB = gw.b(h(), str3, fsVar.q(), "exe/doReturn");
                            if (strB == null) {
                                bn.c("E", "return: bad return variable: " + str3);
                                i = -3;
                                break;
                            }
                            fs fsVar2 = fsVarF;
                            if (!n.a(strB, "%caller", false, 2, (Object) null)) {
                                boolean z2 = z || k.a((Object) strAp, (Object) str3);
                                if (gw.o(str3)) {
                                    if (z2 || TextUtils.isEmpty(fsVar.e(str3))) {
                                        k.a((Object) boolAr, "structureReturnVar");
                                        fsVar.a(strB, str4, boolAr.booleanValue());
                                    }
                                } else {
                                    gw.a(h(), strB, str4, "doReturn");
                                }
                            }
                            fsVarF = fsVar2;
                        }
                    }
                    break;
                }
                fsVarF = fsVarF;
            }
            s sVar = s.f2131a;
        }
        if (i == -1) {
            i = dVar.getStop() ? -4 : -1;
        }
        return cn.a(Integer.valueOf(i));
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    protected Integer a(cl clVar) {
        k.b(clVar, "result");
        if (!(clVar instanceof cq)) {
            clVar = null;
        }
        cq cqVar = (cq) clVar;
        if (cqVar != null) {
            return (Integer) cqVar.d();
        }
        return null;
    }
}
