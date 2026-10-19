package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class w extends com.joaomgcd.taskerm.helper.actions.execute.l<ac> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean c() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ac, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ac acVar) {
        c.f.b.k.b(acVar, "input");
        String strA = com.joaomgcd.taskerm.util.ak.A(acVar.getText());
        if (strA == null) {
            return cn.a("No text provided");
        }
        if (com.joaomgcd.taskerm.util.ak.t(strA)) {
            return cn.a(strA + " variable is unset");
        }
        String strA2 = com.joaomgcd.taskerm.util.ak.A(acVar.getPattern());
        if (strA2 == null) {
            return cn.a("No pattern provided");
        }
        com.joaomgcd.taskerm.pattern.d dVarInvoke = acVar.getTypeNotNull().b().invoke(strA2, strA);
        HashMap<String, ArrayList<String>> mapA = dVarInvoke.a();
        Set<String> setKeySet = mapA.keySet();
        c.f.b.k.a((Object) setKeySet, "groups.keys");
        com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
        bVar.a(h(), dVarInvoke.d());
        bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a("mt", "match", dVarInvoke.b()));
        for (String str : setKeySet) {
            ArrayList<String> arrayList = mapA.get(str);
            c.f.b.k.a((Object) str, "groupName");
            bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a(str, arrayList != null ? (String) c.a.j.f((List) arrayList) : null, true));
            bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a(str, arrayList));
        }
        return cn.a(bVar);
    }
}
